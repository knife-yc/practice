package com.yc.practice.test.circuitbreaker;

public class HalfOpenState extends CircuitState {
    private final CircuitBreaker circuit;
    private CircularBitSet bitSet;

    public HalfOpenState(CircuitBreaker circuit) {
        this.circuit = circuit;
        Ratio successThreshold = circuit.getSuccessThreshold();
        Ratio failureThreshold = circuit.getFailureThreshold();
        if (null != successThreshold) {
            setSuccessThreshold(successThreshold);
        } else if (null != failureThreshold) {
            setSuccessThreshold(failureThreshold);
        } else {
            setSuccessThreshold(DEFAULT_THRESHOLD);
        }
    }

    @Override
    public boolean allowsExecution(CircuitBreakerStats stats) {
        if (circuit.onHalfOpen != null) {
            if (circuit.tryAcquire()) {
                circuit.onHalfOpen.run();
            }
            return false;
        }
        return stats.getCurrentExecutions() < maxConcurrentExecutions() && circuit.tryAcquire();
    }

    @Override
    public CircuitBreaker.State getState() {
        return CircuitBreaker.State.HALF_OPEN;
    }

    @Override
    public synchronized void recordFailure() {
        bitSet.setNext(false);
        checkThreshold();
    }

    @Override
    public synchronized void recordSuccess() {
        bitSet.setNext(true);
        checkThreshold();
    }

    @Override
    public void setFailureThreshold(Ratio threshold) {
        if (null == circuit.getSuccessThreshold()) {
            bitSet = new CircularBitSet(threshold.denominator, bitSet);
        }
    }

    @Override
    public void setSuccessThreshold(Ratio threshold) {
        bitSet = new CircularBitSet(threshold.denominator, bitSet);
    }

    synchronized void checkThreshold() {
        Ratio success = circuit.getSuccessThreshold();
        Ratio failure = circuit.getFailureThreshold();
        int occupied = bitSet.occupiedBits();

        if (null != success) {
            double positive = bitSet.positiveRatio();
            if (occupied == success.denominator || (success.totally() && positive < 1.0)) {
                if (positive >= success.ratio) circuit.close();
                else circuit.open();
            }
        } else if (null != failure) {
            double negative = bitSet.negativeRatio();
            if (occupied == failure.denominator || (failure.totally() && negative < 1.0)) {
                if (negative >= failure.ratio) circuit.open();
                else circuit.close();
            }
        } else {
            if (bitSet.positiveRatio() >= 1.0) circuit.close();
            else circuit.open();
        }
    }

    int maxConcurrentExecutions() {
        if (null != circuit.getSuccessThreshold()) {
            return circuit.getSuccessThreshold().denominator;
        } else if (null != circuit.getFailureThreshold()) {
            return circuit.getFailureThreshold().denominator;
        } else {
            return 1;
        }
    }
}

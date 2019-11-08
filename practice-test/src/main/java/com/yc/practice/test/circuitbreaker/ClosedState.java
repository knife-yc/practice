package com.yc.practice.test.circuitbreaker;

public class ClosedState extends CircuitState {
    private final CircuitBreaker circuit;
    private CircularBitSet bitSet;

    public ClosedState(CircuitBreaker circuit) {
        this.circuit = circuit;
        Ratio failure = circuit.getFailureThreshold();
        setFailureThreshold(null != failure ? failure : DEFAULT_THRESHOLD);
    }

    @Override
    public boolean allowsExecution(CircuitBreakerStats stats) {
        return true;
    }

    @Override
    public CircuitBreaker.State getState() {
        return CircuitBreaker.State.CLOSED;
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
        bitSet = new CircularBitSet(threshold.denominator, bitSet);
    }

    synchronized void checkThreshold() {
        Ratio failure = circuit.getFailureThreshold();
        int occupied = bitSet.occupiedBits();

        if (null != failure) {
            if (occupied >= failure.denominator && bitSet.negativeRatio() >= failure.ratio) {
                circuit.open();
            }
        } else if (bitSet.negativeRatio() >= 1.0 && occupied >= DEFAULT_THRESHOLD.denominator) {
            circuit.open();
        }
    }
}

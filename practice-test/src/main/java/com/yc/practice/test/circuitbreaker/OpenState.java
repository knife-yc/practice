package com.yc.practice.test.circuitbreaker;

public class OpenState extends CircuitState {
    private final CircuitBreaker circuit;
    private final long startTime = System.nanoTime();

    public OpenState(CircuitBreaker circuit) {
        this.circuit = circuit;
    }

    @Override
    public boolean allowsExecution(CircuitBreakerStats stats) {
        if (System.nanoTime() - startTime >= circuit.getDelay()) {
            circuit.halfOpen();
            return circuit.onHalfOpen == null;
        }

        return false;
    }

    @Override
    public CircuitBreaker.State getState() {
        return CircuitBreaker.State.OPEN;
    }
}

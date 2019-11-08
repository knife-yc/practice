package com.yc.practice.test.circuitbreaker;

public abstract class CircuitState {
    static final Ratio DEFAULT_THRESHOLD = new Ratio(32, 32);

    public abstract boolean allowsExecution(CircuitBreakerStats stats);

    public abstract CircuitBreaker.State getState();

    public void recordFailure() {}

    public void recordSuccess() {}

    public void setFailureThreshold(Ratio threshold) {}

    public void setSuccessThreshold(Ratio threshold) {}
}

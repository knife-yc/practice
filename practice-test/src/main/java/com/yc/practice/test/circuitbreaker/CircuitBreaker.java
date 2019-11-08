package com.yc.practice.test.circuitbreaker;


import com.google.common.util.concurrent.RateLimiter;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public class CircuitBreaker {

    private final AtomicReference<CircuitState> state = new AtomicReference<>();

    private final AtomicInteger working = new AtomicInteger();
    private final CircuitBreakerStats stats = working::get;

    private long delay = 0;
    //private long timeout;
    private Ratio failureThreshold;
    private Ratio successThreshold;

    private RateLimiter checkLimiter;

    Runnable onOpen;
    Runnable onHalfOpen;
    Runnable onClose;

    public CircuitBreaker() {
        state.set(new ClosedState(this));
    }

    /*
     * 状态
     */
    public enum State {
        /*
         * 闭合状态，表示可以正常使用
         */
        CLOSED,
        /*
         * 断开状态，表示不可以使用
         */
        OPEN,
        /*
         * 检测状态
         */
        HALF_OPEN;
    }

    public boolean allowsExecution() {
        return state.get().allowsExecution(stats);
    }

    public void acquire() {
        working.incrementAndGet();
    }

    public void success() {
        try {
            state.get().recordSuccess();
        } finally {
            working.decrementAndGet();
        }
    }

    public void failure() {
        try {
            state.get().recordFailure();
        } finally {
            working.decrementAndGet();
        }
    }

    public void close() {
        transitionTo(State.CLOSED, onClose);
    }

    public void halfOpen() {
        transitionTo(State.HALF_OPEN, onHalfOpen);
    }

    public void open() {
        transitionTo(State.OPEN, onOpen);
    }

    public State getState() {
        return state.get().getState();
    }

    public long getDelay() {
        return delay;
    }

    public Ratio getFailureThreshold() {
        return failureThreshold;
    }

    public Ratio getSuccessThreshold() {
        return successThreshold;
    }

    public int getWorking() {
        return working.get();
    }
    //public long getTimeout() {
    //  return timeout;
    //}

    public boolean isClosed() {
        return State.CLOSED.equals(getState());
    }

    public boolean isHalfOpen() {
        return State.HALF_OPEN.equals(getState());
    }

    public boolean isOpen() {
        return State.OPEN.equals(getState());
    }

    public CircuitBreaker onClose(Runnable runnable) {
        onClose = runnable;
        return this;
    }

    public CircuitBreaker onHalfOpen(Runnable runnable) {
        onHalfOpen = runnable;
        return this;
    }

    public CircuitBreaker onOpen(Runnable runnable) {
        onOpen = runnable;
        return this;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("{ state :").append(state.get().getState());
        builder.append(", working: ").append(working.get());
        builder.append(", delay: ").append(delay);
        builder.append(", failureThreshold: ").append(failureThreshold);
        builder.append(", successThreshold: ").append(successThreshold);
        builder.append("}");
        return builder.toString();
    }

    /**
     * 设定从“断开”状态进入“检测”状态的条件，即经历delay（时长）之后转入“断开”状态
     *
     * @param delay
     * @param unit
     * @return
     */
    public CircuitBreaker withDelay(long delay, TimeUnit unit) {
        if (delay > 0 && null != unit) {
            this.delay = unit.toNanos(delay);
        }
        return this;
    }

    /**
     * 设定从“断开”状态进入“检测”状态的条件，即经历delay（时长）之后转入“断开”状态
     *
     * @param d1
     * @param d2
     * @param unit
     * @return
     */
    public CircuitBreaker withDelay(long d1, long d2, TimeUnit unit) {
        if (null != unit) {
            if (d1 > 0) {
                this.delay = unit.toNanos(d1);
            } else {
                this.delay = unit.toNanos(d2);
            }
        }
        return this;
    }
    /**
     * 设定进入“断开”状态的条件，即经历failureThreshold次失败后转入“断开”状态
     *
     * @param failureThreshold
     * @return
     */
    public CircuitBreaker withFailureThreshold(int failureThreshold) {
        if (failureThreshold >= 1) {
            withFailureThreshold(failureThreshold, failureThreshold);
        }
        return this;
    }

    /**
     * 设定进入“断开”状态的条件，即连续执行 executions 中如果存在 failures次，那么进入“断开”状态
     *
     * @param failures
     * @param executions
     * @return
     */
    public synchronized CircuitBreaker withFailureThreshold(int failures, int executions) {
        if (failures >= 1 && executions >= failures) {
            this.failureThreshold = new Ratio(failures, executions);
            state.get().setFailureThreshold(failureThreshold);
        }
        return this;
    }

    /**
     * 设定进入“断开”状态的条件，即经历failureThreshold次失败后转入“断开”状态
     *
     * @param threshold
     * @return
     */
    public CircuitBreaker withFailureThreshold(Ratio threshold) {
        if (null != threshold) {
            withFailureThreshold(threshold.getNumerator(), threshold.getDenominator());
        }
        return this;
    }

    /**
     * 设定进入“断开”状态的条件，即经历failureThreshold次失败后转入“断开”状态
     *
     * @return
     */
    public CircuitBreaker withFailureThreshold(Ratio th1, Ratio th2) {
        if (null != th1) {
            withFailureThreshold(th1.getNumerator(), th1.getDenominator());
        } else {
            withFailureThreshold(th2.getNumerator(), th2.getDenominator());
        }
        return this;
    }

    /**
     * 设定从"检测"状态进入“闭合”状态的条件，即经历 successThreshold 次成功后进入“闭合”状态
     *
     * @param successThreshold
     * @return
     */
    public CircuitBreaker withSuccessThreshold(int successThreshold) {
        if (successThreshold >= 1) withSuccessThreshold(successThreshold, successThreshold);
        return this;
    }

    /**
     * 设定从"检测"状态进入“闭合”状态的条件，即连续执行 executions 中如果存在 successes次，那么进入“闭合”状态
     *
     * @param successes
     * @param executions
     * @return
     */
    public synchronized CircuitBreaker withSuccessThreshold(int successes, int executions) {
        if (successes >= 1 && executions >= successes) {
            this.successThreshold = new Ratio(successes, executions);
            state.get().setSuccessThreshold(successThreshold);
        }
        return this;
    }

    /**
     * 设定从"检测"状态进入“闭合”状态的条件
     *
     * @param threshold
     * @return
     */
    public CircuitBreaker withSuccessThreshold(Ratio threshold) {
        if (null != threshold) {
            withSuccessThreshold(threshold.getNumerator(), threshold.getDenominator());
        }
        return this;
    }

    /**
     * 设定从"检测"状态进入“闭合”状态的条件
     *
     * @return
     */
    public CircuitBreaker withSuccessThreshold(Ratio th1, Ratio th2) {
        if (null != th1) {
            withSuccessThreshold(th1.getNumerator(), th2.getDenominator());
        } else {
            withSuccessThreshold(th2.getNumerator(), th2.getDenominator());
        }
        return this;
    }

    //public CircuitBreaker withTimeout(long timeout, TimeUnit unit) {
    //  if (timeout > 0 && null != unit) {
    //    this.timeout = unit.toNanos(timeout);
    //  }
    //  return this;
    //}

    private void transitionTo(State newState, Runnable listener) {
        synchronized (this) {
            if (getState() != newState) {
                if (State.CLOSED == newState) {
                    state.set(new ClosedState(this));
                } else if (State.OPEN == newState) {
                    state.set(new OpenState(this));
                } else if (State.HALF_OPEN == newState) {
                    state.set(new HalfOpenState(this));
                }
                if (null != listener) {
                    try {
                        listener.run();
                    } catch (Exception ignore) {
                    }
                }
            }
        }
    }

    public CircuitBreaker withCheckLimiter(RateLimiter rateLimiter) {
        this.checkLimiter = rateLimiter;
        return this;
    }

    public boolean tryAcquire() {
        return checkLimiter != null && checkLimiter.tryAcquire();
    }
}

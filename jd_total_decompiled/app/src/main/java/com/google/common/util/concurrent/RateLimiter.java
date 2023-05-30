package com.google.common.util.concurrent;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Preconditions;
import com.google.common.base.Stopwatch;
import com.google.common.util.concurrent.SmoothRateLimiter;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

@Beta
@GwtIncompatible
/* loaded from: classes12.dex */
public abstract class RateLimiter {
    private volatile Object mutexDoNotUseDirectly;
    private final SleepingStopwatch stopwatch;

    /* loaded from: classes12.dex */
    public static abstract class SleepingStopwatch {
        protected SleepingStopwatch() {
        }

        public static final SleepingStopwatch createFromSystemTimer() {
            return new SleepingStopwatch() { // from class: com.google.common.util.concurrent.RateLimiter.SleepingStopwatch.1
                final Stopwatch stopwatch = Stopwatch.createStarted();

                @Override // com.google.common.util.concurrent.RateLimiter.SleepingStopwatch
                protected long readMicros() {
                    return this.stopwatch.elapsed(TimeUnit.MICROSECONDS);
                }

                @Override // com.google.common.util.concurrent.RateLimiter.SleepingStopwatch
                protected void sleepMicrosUninterruptibly(long j2) {
                    if (j2 > 0) {
                        Uninterruptibles.sleepUninterruptibly(j2, TimeUnit.MICROSECONDS);
                    }
                }
            };
        }

        protected abstract long readMicros();

        protected abstract void sleepMicrosUninterruptibly(long j2);
    }

    public RateLimiter(SleepingStopwatch sleepingStopwatch) {
        this.stopwatch = (SleepingStopwatch) Preconditions.checkNotNull(sleepingStopwatch);
    }

    private boolean canAcquire(long j2, long j3) {
        return queryEarliestAvailable(j2) - j3 <= j2;
    }

    private static void checkPermits(int i2) {
        Preconditions.checkArgument(i2 > 0, "Requested permits (%s) must be positive", i2);
    }

    public static RateLimiter create(double d) {
        return create(d, SleepingStopwatch.createFromSystemTimer());
    }

    private Object mutex() {
        Object obj = this.mutexDoNotUseDirectly;
        if (obj == null) {
            synchronized (this) {
                obj = this.mutexDoNotUseDirectly;
                if (obj == null) {
                    obj = new Object();
                    this.mutexDoNotUseDirectly = obj;
                }
            }
        }
        return obj;
    }

    @CanIgnoreReturnValue
    public double acquire() {
        return acquire(1);
    }

    abstract double doGetRate();

    abstract void doSetRate(double d, long j2);

    public final double getRate() {
        double doGetRate;
        synchronized (mutex()) {
            doGetRate = doGetRate();
        }
        return doGetRate;
    }

    abstract long queryEarliestAvailable(long j2);

    final long reserve(int i2) {
        long reserveAndGetWaitLength;
        checkPermits(i2);
        synchronized (mutex()) {
            reserveAndGetWaitLength = reserveAndGetWaitLength(i2, this.stopwatch.readMicros());
        }
        return reserveAndGetWaitLength;
    }

    final long reserveAndGetWaitLength(int i2, long j2) {
        return Math.max(reserveEarliestAvailable(i2, j2) - j2, 0L);
    }

    abstract long reserveEarliestAvailable(int i2, long j2);

    public final void setRate(double d) {
        Preconditions.checkArgument(d > 0.0d && !Double.isNaN(d), "rate must be positive");
        synchronized (mutex()) {
            doSetRate(d, this.stopwatch.readMicros());
        }
    }

    public String toString() {
        return String.format(Locale.ROOT, "RateLimiter[stableRate=%3.1fqps]", Double.valueOf(getRate()));
    }

    public boolean tryAcquire(long j2, TimeUnit timeUnit) {
        return tryAcquire(1, j2, timeUnit);
    }

    @VisibleForTesting
    static RateLimiter create(double d, SleepingStopwatch sleepingStopwatch) {
        SmoothRateLimiter.SmoothBursty smoothBursty = new SmoothRateLimiter.SmoothBursty(sleepingStopwatch, 1.0d);
        smoothBursty.setRate(d);
        return smoothBursty;
    }

    @CanIgnoreReturnValue
    public double acquire(int i2) {
        long reserve = reserve(i2);
        this.stopwatch.sleepMicrosUninterruptibly(reserve);
        double d = reserve;
        Double.isNaN(d);
        double micros = TimeUnit.SECONDS.toMicros(1L);
        Double.isNaN(micros);
        return (d * 1.0d) / micros;
    }

    public boolean tryAcquire(int i2) {
        return tryAcquire(i2, 0L, TimeUnit.MICROSECONDS);
    }

    public boolean tryAcquire() {
        return tryAcquire(1, 0L, TimeUnit.MICROSECONDS);
    }

    public static RateLimiter create(double d, long j2, TimeUnit timeUnit) {
        Preconditions.checkArgument(j2 >= 0, "warmupPeriod must not be negative: %s", j2);
        return create(d, j2, timeUnit, 3.0d, SleepingStopwatch.createFromSystemTimer());
    }

    public boolean tryAcquire(int i2, long j2, TimeUnit timeUnit) {
        long max = Math.max(timeUnit.toMicros(j2), 0L);
        checkPermits(i2);
        synchronized (mutex()) {
            long readMicros = this.stopwatch.readMicros();
            if (canAcquire(readMicros, max)) {
                this.stopwatch.sleepMicrosUninterruptibly(reserveAndGetWaitLength(i2, readMicros));
                return true;
            }
            return false;
        }
    }

    @VisibleForTesting
    static RateLimiter create(double d, long j2, TimeUnit timeUnit, double d2, SleepingStopwatch sleepingStopwatch) {
        SmoothRateLimiter.SmoothWarmingUp smoothWarmingUp = new SmoothRateLimiter.SmoothWarmingUp(sleepingStopwatch, j2, timeUnit, d2);
        smoothWarmingUp.setRate(d);
        return smoothWarmingUp;
    }
}

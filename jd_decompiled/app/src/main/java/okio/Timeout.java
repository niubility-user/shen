package okio;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.util.concurrent.TimeUnit;

/* loaded from: classes11.dex */
public class Timeout {
    public static final Timeout NONE = new Timeout() { // from class: okio.Timeout.1
        @Override // okio.Timeout
        public Timeout deadlineNanoTime(long j2) {
            return this;
        }

        @Override // okio.Timeout
        public void throwIfReached() throws IOException {
        }

        @Override // okio.Timeout
        public Timeout timeout(long j2, TimeUnit timeUnit) {
            return this;
        }
    };
    private long deadlineNanoTime;
    private boolean hasDeadline;
    private long timeoutNanos;

    /* JADX INFO: Access modifiers changed from: package-private */
    public static long minTimeout(long j2, long j3) {
        return j2 == 0 ? j3 : (j3 != 0 && j2 >= j3) ? j3 : j2;
    }

    public Timeout clearDeadline() {
        this.hasDeadline = false;
        return this;
    }

    public Timeout clearTimeout() {
        this.timeoutNanos = 0L;
        return this;
    }

    public final Timeout deadline(long j2, TimeUnit timeUnit) {
        if (j2 > 0) {
            if (timeUnit != null) {
                return deadlineNanoTime(System.nanoTime() + timeUnit.toNanos(j2));
            }
            throw new IllegalArgumentException("unit == null");
        }
        throw new IllegalArgumentException("duration <= 0: " + j2);
    }

    public long deadlineNanoTime() {
        if (this.hasDeadline) {
            return this.deadlineNanoTime;
        }
        throw new IllegalStateException("No deadline");
    }

    public boolean hasDeadline() {
        return this.hasDeadline;
    }

    public void throwIfReached() throws IOException {
        if (!Thread.interrupted()) {
            if (this.hasDeadline && this.deadlineNanoTime - System.nanoTime() <= 0) {
                throw new InterruptedIOException("deadline reached");
            }
            return;
        }
        Thread.currentThread().interrupt();
        throw new InterruptedIOException("interrupted");
    }

    public Timeout timeout(long j2, TimeUnit timeUnit) {
        if (j2 >= 0) {
            if (timeUnit != null) {
                this.timeoutNanos = timeUnit.toNanos(j2);
                return this;
            }
            throw new IllegalArgumentException("unit == null");
        }
        throw new IllegalArgumentException("timeout < 0: " + j2);
    }

    public long timeoutNanos() {
        return this.timeoutNanos;
    }

    public final void waitUntilNotified(Object obj) throws InterruptedIOException {
        try {
            boolean hasDeadline = hasDeadline();
            long timeoutNanos = timeoutNanos();
            long j2 = 0;
            if (!hasDeadline && timeoutNanos == 0) {
                obj.wait();
                return;
            }
            long nanoTime = System.nanoTime();
            if (hasDeadline && timeoutNanos != 0) {
                timeoutNanos = Math.min(timeoutNanos, deadlineNanoTime() - nanoTime);
            } else if (hasDeadline) {
                timeoutNanos = deadlineNanoTime() - nanoTime;
            }
            if (timeoutNanos > 0) {
                long j3 = timeoutNanos / 1000000;
                Long.signum(j3);
                obj.wait(j3, (int) (timeoutNanos - (1000000 * j3)));
                j2 = System.nanoTime() - nanoTime;
            }
            if (j2 >= timeoutNanos) {
                throw new InterruptedIOException("timeout");
            }
        } catch (InterruptedException unused) {
            Thread.currentThread().interrupt();
            throw new InterruptedIOException("interrupted");
        }
    }

    public Timeout deadlineNanoTime(long j2) {
        this.hasDeadline = true;
        this.deadlineNanoTime = j2;
        return this;
    }
}

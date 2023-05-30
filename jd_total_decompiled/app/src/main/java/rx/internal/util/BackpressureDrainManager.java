package rx.internal.util;

import java.util.concurrent.atomic.AtomicLong;
import rx.Producer;
import rx.annotations.Experimental;

@Experimental
/* loaded from: classes11.dex */
public final class BackpressureDrainManager extends AtomicLong implements Producer {
    private static final long serialVersionUID = 2826241102729529449L;
    protected final BackpressureQueueCallback actual;
    protected boolean emitting;
    protected Throwable exception;
    protected volatile boolean terminated;

    /* loaded from: classes11.dex */
    public interface BackpressureQueueCallback {
        boolean accept(Object obj);

        void complete(Throwable th);

        Object peek();

        Object poll();
    }

    public BackpressureDrainManager(BackpressureQueueCallback backpressureQueueCallback) {
        this.actual = backpressureQueueCallback;
    }

    /* JADX WARN: Code restructure failed: missing block: B:23:0x002f, code lost:
        if (r2 == 0) goto L85;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x0038, code lost:
        monitor-enter(r13);
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x0039, code lost:
        r1 = r13.terminated;
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x003f, code lost:
        if (r5.peek() == null) goto L31;
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x0041, code lost:
        r2 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x0043, code lost:
        r2 = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x004f, code lost:
        if (get() != Long.MAX_VALUE) goto L41;
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x0051, code lost:
        if (r2 != false) goto L40;
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x0053, code lost:
        if (r1 != false) goto L40;
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x0055, code lost:
        r13.emitting = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x0057, code lost:
        monitor-exit(r13);
     */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x0058, code lost:
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x0059, code lost:
        r2 = Long.MAX_VALUE;
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x005d, code lost:
        r9 = addAndGet(-r6);
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x0063, code lost:
        if (r9 == 0) goto L45;
     */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x0065, code lost:
        if (r2 != false) goto L48;
     */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x0067, code lost:
        if (r1 == false) goto L91;
     */
    /* JADX WARN: Code restructure failed: missing block: B:46:0x0069, code lost:
        if (r2 == false) goto L48;
     */
    /* JADX WARN: Code restructure failed: missing block: B:48:0x006c, code lost:
        r2 = r9;
     */
    /* JADX WARN: Code restructure failed: missing block: B:51:0x006f, code lost:
        r13.emitting = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:52:0x0071, code lost:
        monitor-exit(r13);
     */
    /* JADX WARN: Code restructure failed: missing block: B:53:0x0072, code lost:
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:54:0x0073, code lost:
        r1 = th;
     */
    /* JADX WARN: Code restructure failed: missing block: B:56:0x0075, code lost:
        monitor-exit(r13);
     */
    /* JADX WARN: Code restructure failed: missing block: B:57:0x0076, code lost:
        throw r1;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void drain() {
        synchronized (this) {
            if (this.emitting) {
                return;
            }
            boolean z = true;
            this.emitting = true;
            boolean z2 = this.terminated;
            long j2 = get();
            try {
                BackpressureQueueCallback backpressureQueueCallback = this.actual;
                while (true) {
                    int i2 = 0;
                    while (true) {
                        try {
                            if (j2 > 0 || z2) {
                                if (z2) {
                                    if (backpressureQueueCallback.peek() == null) {
                                        backpressureQueueCallback.complete(this.exception);
                                        return;
                                    }
                                }
                                Object poll = backpressureQueueCallback.poll();
                                if (poll != null) {
                                    if (backpressureQueueCallback.accept(poll)) {
                                        return;
                                    }
                                    j2--;
                                    i2++;
                                }
                            }
                            try {
                                break;
                            } catch (Throwable th) {
                                th = th;
                            }
                        } catch (Throwable th2) {
                            th = th2;
                            if (!z) {
                                synchronized (this) {
                                    this.emitting = false;
                                }
                            }
                            throw th;
                        }
                    }
                }
            } catch (Throwable th3) {
                th = th3;
                z = false;
            }
        }
    }

    public final boolean isTerminated() {
        return this.terminated;
    }

    @Override // rx.Producer
    public final void request(long j2) {
        boolean z;
        long j3;
        if (j2 == 0) {
            return;
        }
        while (true) {
            long j4 = get();
            boolean z2 = true;
            z = j4 == 0;
            if (j4 == Long.MAX_VALUE) {
                break;
            }
            if (j2 == Long.MAX_VALUE) {
                j3 = j2;
            } else {
                j3 = j4 <= Long.MAX_VALUE - j2 ? j4 + j2 : Long.MAX_VALUE;
                z2 = z;
            }
            if (compareAndSet(j4, j3)) {
                z = z2;
                break;
            }
        }
        if (z) {
            drain();
        }
    }

    public final void terminate() {
        this.terminated = true;
    }

    public final void terminateAndDrain() {
        this.terminated = true;
        drain();
    }

    public final void terminate(Throwable th) {
        if (this.terminated) {
            return;
        }
        this.exception = th;
        this.terminated = true;
    }

    public final void terminateAndDrain(Throwable th) {
        if (this.terminated) {
            return;
        }
        this.exception = th;
        this.terminated = true;
        drain();
    }
}

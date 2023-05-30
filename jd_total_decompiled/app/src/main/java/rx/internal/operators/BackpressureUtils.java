package rx.internal.operators;

import java.util.Queue;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicLongFieldUpdater;
import rx.Subscriber;
import rx.functions.Func1;
import rx.internal.util.UtilityFunctions;

/* loaded from: classes11.dex */
public final class BackpressureUtils {
    static final long COMPLETED_MASK = Long.MIN_VALUE;
    static final long REQUESTED_MASK = Long.MAX_VALUE;

    private BackpressureUtils() {
        throw new IllegalStateException("No instances!");
    }

    public static long addCap(long j2, long j3) {
        long j4 = j2 + j3;
        if (j4 < 0) {
            return Long.MAX_VALUE;
        }
        return j4;
    }

    @Deprecated
    public static <T> long getAndAddRequest(AtomicLongFieldUpdater<T> atomicLongFieldUpdater, T t, long j2) {
        long j3;
        do {
            j3 = atomicLongFieldUpdater.get(t);
        } while (!atomicLongFieldUpdater.compareAndSet(t, j3, addCap(j3, j2)));
        return j3;
    }

    public static long multiplyCap(long j2, long j3) {
        long j4 = j2 * j3;
        if (((j2 | j3) >>> 31) == 0 || j3 == 0 || j4 / j3 == j2) {
            return j4;
        }
        return Long.MAX_VALUE;
    }

    public static <T> void postCompleteDone(AtomicLong atomicLong, Queue<T> queue, Subscriber<? super T> subscriber) {
        postCompleteDone(atomicLong, queue, subscriber, UtilityFunctions.identity());
    }

    /* JADX WARN: Code restructure failed: missing block: B:35:0x0066, code lost:
        r0 = r10.addAndGet(-(r6 & Long.MAX_VALUE));
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    static <T, R> void postCompleteDrain(AtomicLong atomicLong, Queue<T> queue, Subscriber<? super R> subscriber, Func1<? super T, ? extends R> func1) {
        long j2 = atomicLong.get();
        if (j2 == Long.MAX_VALUE) {
            while (!subscriber.isUnsubscribed()) {
                Object poll = queue.poll();
                if (poll == null) {
                    subscriber.onCompleted();
                    return;
                }
                subscriber.onNext((R) func1.call(poll));
            }
            return;
        }
        do {
            long j3 = Long.MIN_VALUE;
            while (true) {
                if (j3 != j2) {
                    if (subscriber.isUnsubscribed()) {
                        return;
                    }
                    Object poll2 = queue.poll();
                    if (poll2 == null) {
                        subscriber.onCompleted();
                        return;
                    } else {
                        subscriber.onNext((R) func1.call(poll2));
                        j3++;
                    }
                } else {
                    if (j3 == j2) {
                        if (subscriber.isUnsubscribed()) {
                            return;
                        }
                        if (queue.isEmpty()) {
                            subscriber.onCompleted();
                            return;
                        }
                    }
                    j2 = atomicLong.get();
                    if (j2 == j3) {
                        break;
                    }
                }
            }
        } while (j2 != Long.MIN_VALUE);
    }

    public static <T> boolean postCompleteRequest(AtomicLong atomicLong, long j2, Queue<T> queue, Subscriber<? super T> subscriber) {
        return postCompleteRequest(atomicLong, j2, queue, subscriber, UtilityFunctions.identity());
    }

    public static long produced(AtomicLong atomicLong, long j2) {
        long j3;
        long j4;
        do {
            j3 = atomicLong.get();
            if (j3 == Long.MAX_VALUE) {
                return Long.MAX_VALUE;
            }
            j4 = j3 - j2;
            if (j4 < 0) {
                throw new IllegalStateException("More produced than requested: " + j4);
            }
        } while (!atomicLong.compareAndSet(j3, j4));
        return j4;
    }

    public static <T, R> void postCompleteDone(AtomicLong atomicLong, Queue<T> queue, Subscriber<? super R> subscriber, Func1<? super T, ? extends R> func1) {
        long j2;
        do {
            j2 = atomicLong.get();
            if ((j2 & Long.MIN_VALUE) != 0) {
                return;
            }
        } while (!atomicLong.compareAndSet(j2, Long.MIN_VALUE | j2));
        if (j2 != 0) {
            postCompleteDrain(atomicLong, queue, subscriber, func1);
        }
    }

    public static <T, R> boolean postCompleteRequest(AtomicLong atomicLong, long j2, Queue<T> queue, Subscriber<? super R> subscriber, Func1<? super T, ? extends R> func1) {
        long j3;
        long j4;
        if (j2 < 0) {
            throw new IllegalArgumentException("n >= 0 required but it was " + j2);
        } else if (j2 == 0) {
            return (atomicLong.get() & Long.MIN_VALUE) == 0;
        } else {
            do {
                j3 = atomicLong.get();
                j4 = j3 & Long.MIN_VALUE;
            } while (!atomicLong.compareAndSet(j3, addCap(Long.MAX_VALUE & j3, j2) | j4));
            if (j3 != Long.MIN_VALUE) {
                return j4 == 0;
            }
            postCompleteDrain(atomicLong, queue, subscriber, func1);
            return false;
        }
    }

    public static long getAndAddRequest(AtomicLong atomicLong, long j2) {
        long j3;
        do {
            j3 = atomicLong.get();
        } while (!atomicLong.compareAndSet(j3, addCap(j3, j2)));
        return j3;
    }
}

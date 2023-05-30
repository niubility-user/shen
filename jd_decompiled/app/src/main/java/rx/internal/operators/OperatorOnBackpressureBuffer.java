package rx.internal.operators;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;
import rx.BackpressureOverflow;
import rx.Observable;
import rx.Producer;
import rx.Subscriber;
import rx.functions.Action0;
import rx.internal.util.BackpressureDrainManager;

/* loaded from: classes11.dex */
public class OperatorOnBackpressureBuffer<T> implements Observable.Operator<T, T> {
    private final Long capacity;
    private final Action0 onOverflow;
    private final BackpressureOverflow.Strategy overflowStrategy;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes11.dex */
    public static final class BufferSubscriber<T> extends Subscriber<T> implements BackpressureDrainManager.BackpressureQueueCallback {
        private final AtomicLong capacity;
        private final Subscriber<? super T> child;
        private final BackpressureDrainManager manager;
        private final Action0 onOverflow;
        private final BackpressureOverflow.Strategy overflowStrategy;
        private final ConcurrentLinkedQueue<Object> queue = new ConcurrentLinkedQueue<>();
        private final AtomicBoolean saturated = new AtomicBoolean(false);
        private final NotificationLite<T> on = NotificationLite.instance();

        public BufferSubscriber(Subscriber<? super T> subscriber, Long l2, Action0 action0, BackpressureOverflow.Strategy strategy) {
            this.child = subscriber;
            this.capacity = l2 != null ? new AtomicLong(l2.longValue()) : null;
            this.onOverflow = action0;
            this.manager = new BackpressureDrainManager(this);
            this.overflowStrategy = strategy;
        }

        /* JADX WARN: Removed duplicated region for block: B:32:0x0039 A[EXC_TOP_SPLITTER, SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:34:0x0049 A[SYNTHETIC] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        private boolean assertCapacity() {
            /*
                r6 = this;
                java.util.concurrent.atomic.AtomicLong r0 = r6.capacity
                r1 = 1
                if (r0 != 0) goto L6
                return r1
            L6:
                java.util.concurrent.atomic.AtomicLong r0 = r6.capacity
                long r2 = r0.get()
                r4 = 0
                int r0 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
                if (r0 > 0) goto L4a
                r0 = 0
                rx.BackpressureOverflow$Strategy r4 = r6.overflowStrategy     // Catch: rx.exceptions.MissingBackpressureException -> L23
                boolean r4 = r4.mayAttemptDrop()     // Catch: rx.exceptions.MissingBackpressureException -> L23
                if (r4 == 0) goto L34
                java.lang.Object r4 = r6.poll()     // Catch: rx.exceptions.MissingBackpressureException -> L23
                if (r4 == 0) goto L34
                r4 = 1
                goto L35
            L23:
                r4 = move-exception
                java.util.concurrent.atomic.AtomicBoolean r5 = r6.saturated
                boolean r5 = r5.compareAndSet(r0, r1)
                if (r5 == 0) goto L34
                r6.unsubscribe()
                rx.Subscriber<? super T> r5 = r6.child
                r5.onError(r4)
            L34:
                r4 = 0
            L35:
                rx.functions.Action0 r5 = r6.onOverflow
                if (r5 == 0) goto L47
                r5.call()     // Catch: java.lang.Throwable -> L3d
                goto L47
            L3d:
                r1 = move-exception
                rx.exceptions.Exceptions.throwIfFatal(r1)
                rx.internal.util.BackpressureDrainManager r2 = r6.manager
                r2.terminateAndDrain(r1)
                return r0
            L47:
                if (r4 != 0) goto L4a
                return r0
            L4a:
                java.util.concurrent.atomic.AtomicLong r0 = r6.capacity
                r4 = 1
                long r4 = r2 - r4
                boolean r0 = r0.compareAndSet(r2, r4)
                if (r0 == 0) goto L6
                return r1
            */
            throw new UnsupportedOperationException("Method not decompiled: rx.internal.operators.OperatorOnBackpressureBuffer.BufferSubscriber.assertCapacity():boolean");
        }

        @Override // rx.internal.util.BackpressureDrainManager.BackpressureQueueCallback
        public boolean accept(Object obj) {
            return this.on.accept(this.child, obj);
        }

        @Override // rx.internal.util.BackpressureDrainManager.BackpressureQueueCallback
        public void complete(Throwable th) {
            if (th != null) {
                this.child.onError(th);
            } else {
                this.child.onCompleted();
            }
        }

        protected Producer manager() {
            return this.manager;
        }

        @Override // rx.Observer
        public void onCompleted() {
            if (this.saturated.get()) {
                return;
            }
            this.manager.terminateAndDrain();
        }

        @Override // rx.Observer
        public void onError(Throwable th) {
            if (this.saturated.get()) {
                return;
            }
            this.manager.terminateAndDrain(th);
        }

        @Override // rx.Observer
        public void onNext(T t) {
            if (assertCapacity()) {
                this.queue.offer(this.on.next(t));
                this.manager.drain();
            }
        }

        @Override // rx.Subscriber
        public void onStart() {
            request(Long.MAX_VALUE);
        }

        @Override // rx.internal.util.BackpressureDrainManager.BackpressureQueueCallback
        public Object peek() {
            return this.queue.peek();
        }

        @Override // rx.internal.util.BackpressureDrainManager.BackpressureQueueCallback
        public Object poll() {
            Object poll = this.queue.poll();
            AtomicLong atomicLong = this.capacity;
            if (atomicLong != null && poll != null) {
                atomicLong.incrementAndGet();
            }
            return poll;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes11.dex */
    public static class Holder {
        static final OperatorOnBackpressureBuffer<?> INSTANCE = new OperatorOnBackpressureBuffer<>();

        private Holder() {
        }
    }

    OperatorOnBackpressureBuffer() {
        this.capacity = null;
        this.onOverflow = null;
        this.overflowStrategy = BackpressureOverflow.ON_OVERFLOW_DEFAULT;
    }

    public static <T> OperatorOnBackpressureBuffer<T> instance() {
        return (OperatorOnBackpressureBuffer<T>) Holder.INSTANCE;
    }

    @Override // rx.functions.Func1
    public /* bridge */ /* synthetic */ Object call(Object obj) {
        return call((Subscriber) ((Subscriber) obj));
    }

    public Subscriber<? super T> call(Subscriber<? super T> subscriber) {
        BufferSubscriber bufferSubscriber = new BufferSubscriber(subscriber, this.capacity, this.onOverflow, this.overflowStrategy);
        subscriber.add(bufferSubscriber);
        subscriber.setProducer(bufferSubscriber.manager());
        return bufferSubscriber;
    }

    public OperatorOnBackpressureBuffer(long j2) {
        this(j2, null, BackpressureOverflow.ON_OVERFLOW_DEFAULT);
    }

    public OperatorOnBackpressureBuffer(long j2, Action0 action0) {
        this(j2, action0, BackpressureOverflow.ON_OVERFLOW_DEFAULT);
    }

    public OperatorOnBackpressureBuffer(long j2, Action0 action0, BackpressureOverflow.Strategy strategy) {
        if (j2 <= 0) {
            throw new IllegalArgumentException("Buffer capacity must be > 0");
        }
        if (strategy != null) {
            this.capacity = Long.valueOf(j2);
            this.onOverflow = action0;
            this.overflowStrategy = strategy;
            return;
        }
        throw new NullPointerException("The BackpressureOverflow strategy must not be null");
    }
}

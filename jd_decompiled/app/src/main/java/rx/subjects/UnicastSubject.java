package rx.subjects;

import java.util.Queue;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import rx.Observable;
import rx.Observer;
import rx.Producer;
import rx.Subscriber;
import rx.Subscription;
import rx.annotations.Experimental;
import rx.exceptions.Exceptions;
import rx.functions.Action0;
import rx.internal.operators.BackpressureUtils;
import rx.internal.operators.NotificationLite;
import rx.internal.util.atomic.SpscLinkedAtomicQueue;
import rx.internal.util.atomic.SpscUnboundedAtomicArrayQueue;
import rx.internal.util.unsafe.SpscLinkedQueue;
import rx.internal.util.unsafe.SpscUnboundedArrayQueue;
import rx.internal.util.unsafe.UnsafeAccess;

@Experimental
/* loaded from: classes11.dex */
public final class UnicastSubject<T> extends Subject<T, T> {
    final State<T> state;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes11.dex */
    public static final class State<T> extends AtomicLong implements Producer, Observer<T>, Observable.OnSubscribe<T>, Subscription {
        private static final long serialVersionUID = -9044104859202255786L;
        volatile boolean caughtUp;
        volatile boolean done;
        boolean emitting;
        Throwable error;
        boolean missed;
        final Queue<Object> queue;
        final AtomicReference<Action0> terminateOnce;
        final NotificationLite<T> nl = NotificationLite.instance();
        final AtomicReference<Subscriber<? super T>> subscriber = new AtomicReference<>();

        public State(int i2, Action0 action0) {
            Queue<Object> spscLinkedQueue;
            this.terminateOnce = action0 != null ? new AtomicReference<>(action0) : null;
            if (i2 > 1) {
                spscLinkedQueue = UnsafeAccess.isUnsafeAvailable() ? new SpscUnboundedArrayQueue<>(i2) : new SpscUnboundedAtomicArrayQueue<>(i2);
            } else {
                spscLinkedQueue = UnsafeAccess.isUnsafeAvailable() ? new SpscLinkedQueue<>() : new SpscLinkedAtomicQueue<>();
            }
            this.queue = spscLinkedQueue;
        }

        @Override // rx.functions.Action1
        public /* bridge */ /* synthetic */ void call(Object obj) {
            call((Subscriber) ((Subscriber) obj));
        }

        boolean checkTerminated(boolean z, boolean z2, Subscriber<? super T> subscriber) {
            if (subscriber.isUnsubscribed()) {
                this.queue.clear();
                return true;
            } else if (z) {
                Throwable th = this.error;
                if (th != null) {
                    this.queue.clear();
                    subscriber.onError(th);
                    return true;
                } else if (z2) {
                    subscriber.onCompleted();
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        }

        void doTerminate() {
            Action0 action0;
            AtomicReference<Action0> atomicReference = this.terminateOnce;
            if (atomicReference == null || (action0 = atomicReference.get()) == null || !atomicReference.compareAndSet(action0, null)) {
                return;
            }
            action0.call();
        }

        @Override // rx.Subscription
        public boolean isUnsubscribed() {
            return this.done;
        }

        @Override // rx.Observer
        public void onCompleted() {
            if (this.done) {
                return;
            }
            doTerminate();
            boolean z = true;
            this.done = true;
            if (!this.caughtUp) {
                synchronized (this) {
                    if (this.caughtUp) {
                        z = false;
                    }
                }
                if (z) {
                    replay();
                    return;
                }
            }
            this.subscriber.get().onCompleted();
        }

        @Override // rx.Observer
        public void onError(Throwable th) {
            if (this.done) {
                return;
            }
            doTerminate();
            this.error = th;
            boolean z = true;
            this.done = true;
            if (!this.caughtUp) {
                synchronized (this) {
                    if (this.caughtUp) {
                        z = false;
                    }
                }
                if (z) {
                    replay();
                    return;
                }
            }
            this.subscriber.get().onError(th);
        }

        @Override // rx.Observer
        public void onNext(T t) {
            if (this.done) {
                return;
            }
            if (!this.caughtUp) {
                boolean z = false;
                synchronized (this) {
                    if (!this.caughtUp) {
                        this.queue.offer(this.nl.next(t));
                        z = true;
                    }
                }
                if (z) {
                    replay();
                    return;
                }
            }
            Subscriber<? super T> subscriber = this.subscriber.get();
            try {
                subscriber.onNext(t);
            } catch (Throwable th) {
                Exceptions.throwOrReport(th, subscriber, t);
            }
        }

        /* JADX WARN: Code restructure failed: missing block: B:46:0x0081, code lost:
            if (r6 == false) goto L50;
         */
        /* JADX WARN: Code restructure failed: missing block: B:48:0x0087, code lost:
            if (r0.isEmpty() == false) goto L50;
         */
        /* JADX WARN: Code restructure failed: missing block: B:49:0x0089, code lost:
            r14.caughtUp = true;
         */
        /* JADX WARN: Code restructure failed: missing block: B:50:0x008b, code lost:
            r14.emitting = false;
         */
        /* JADX WARN: Code restructure failed: missing block: B:52:0x008e, code lost:
            return;
         */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        void replay() {
            /*
                r14 = this;
                monitor-enter(r14)
                boolean r0 = r14.emitting     // Catch: java.lang.Throwable -> L97
                r1 = 1
                if (r0 == 0) goto La
                r14.missed = r1     // Catch: java.lang.Throwable -> L97
                monitor-exit(r14)     // Catch: java.lang.Throwable -> L97
                return
            La:
                r14.emitting = r1     // Catch: java.lang.Throwable -> L97
                monitor-exit(r14)     // Catch: java.lang.Throwable -> L97
                java.util.Queue<java.lang.Object> r0 = r14.queue
            Lf:
                java.util.concurrent.atomic.AtomicReference<rx.Subscriber<? super T>> r2 = r14.subscriber
                java.lang.Object r2 = r2.get()
                rx.Subscriber r2 = (rx.Subscriber) r2
                r3 = 0
                if (r2 == 0) goto L7b
                boolean r4 = r14.done
                boolean r5 = r0.isEmpty()
                boolean r4 = r14.checkTerminated(r4, r5, r2)
                if (r4 == 0) goto L27
                return
            L27:
                long r4 = r14.get()
                r6 = 9223372036854775807(0x7fffffffffffffff, double:NaN)
                int r8 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
                if (r8 != 0) goto L36
                r6 = 1
                goto L37
            L36:
                r6 = 0
            L37:
                r7 = 0
                r9 = r7
            L3a:
                int r11 = (r4 > r7 ? 1 : (r4 == r7 ? 0 : -1))
                if (r11 == 0) goto L70
                boolean r11 = r14.done
                java.lang.Object r12 = r0.poll()
                if (r12 != 0) goto L48
                r13 = 1
                goto L49
            L48:
                r13 = 0
            L49:
                boolean r11 = r14.checkTerminated(r11, r13, r2)
                if (r11 == 0) goto L50
                return
            L50:
                if (r13 == 0) goto L53
                goto L70
            L53:
                rx.internal.operators.NotificationLite<T> r11 = r14.nl
                java.lang.Object r11 = r11.getValue(r12)
                r2.onNext(r11)     // Catch: java.lang.Throwable -> L61
                r11 = 1
                long r4 = r4 - r11
                long r9 = r9 + r11
                goto L3a
            L61:
                r1 = move-exception
                r0.clear()
                rx.exceptions.Exceptions.throwIfFatal(r1)
                java.lang.Throwable r0 = rx.exceptions.OnErrorThrowable.addValueAsLastCause(r1, r11)
                r2.onError(r0)
                return
            L70:
                if (r6 != 0) goto L7c
                int r2 = (r9 > r7 ? 1 : (r9 == r7 ? 0 : -1))
                if (r2 == 0) goto L7c
                long r4 = -r9
                r14.addAndGet(r4)
                goto L7c
            L7b:
                r6 = 0
            L7c:
                monitor-enter(r14)
                boolean r2 = r14.missed     // Catch: java.lang.Throwable -> L94
                if (r2 != 0) goto L8f
                if (r6 == 0) goto L8b
                boolean r0 = r0.isEmpty()     // Catch: java.lang.Throwable -> L94
                if (r0 == 0) goto L8b
                r14.caughtUp = r1     // Catch: java.lang.Throwable -> L94
            L8b:
                r14.emitting = r3     // Catch: java.lang.Throwable -> L94
                monitor-exit(r14)     // Catch: java.lang.Throwable -> L94
                return
            L8f:
                r14.missed = r3     // Catch: java.lang.Throwable -> L94
                monitor-exit(r14)     // Catch: java.lang.Throwable -> L94
                goto Lf
            L94:
                r0 = move-exception
                monitor-exit(r14)     // Catch: java.lang.Throwable -> L94
                throw r0
            L97:
                r0 = move-exception
                monitor-exit(r14)     // Catch: java.lang.Throwable -> L97
                goto L9b
            L9a:
                throw r0
            L9b:
                goto L9a
            */
            throw new UnsupportedOperationException("Method not decompiled: rx.subjects.UnicastSubject.State.replay():void");
        }

        @Override // rx.Producer
        public void request(long j2) {
            if (j2 < 0) {
                throw new IllegalArgumentException("n >= 0 required");
            }
            if (j2 > 0) {
                BackpressureUtils.getAndAddRequest(this, j2);
                replay();
            } else if (this.done) {
                replay();
            }
        }

        @Override // rx.Subscription
        public void unsubscribe() {
            doTerminate();
            this.done = true;
            synchronized (this) {
                if (this.emitting) {
                    return;
                }
                this.emitting = true;
                this.queue.clear();
            }
        }

        public void call(Subscriber<? super T> subscriber) {
            if (this.subscriber.compareAndSet(null, subscriber)) {
                subscriber.add(this);
                subscriber.setProducer(this);
                return;
            }
            subscriber.onError(new IllegalStateException("Only a single subscriber is allowed"));
        }
    }

    private UnicastSubject(State<T> state) {
        super(state);
        this.state = state;
    }

    public static <T> UnicastSubject<T> create() {
        return create(16);
    }

    @Override // rx.subjects.Subject
    public boolean hasObservers() {
        return this.state.subscriber.get() != null;
    }

    @Override // rx.Observer
    public void onCompleted() {
        this.state.onCompleted();
    }

    @Override // rx.Observer
    public void onError(Throwable th) {
        this.state.onError(th);
    }

    @Override // rx.Observer
    public void onNext(T t) {
        this.state.onNext(t);
    }

    public static <T> UnicastSubject<T> create(int i2) {
        return new UnicastSubject<>(new State(i2, null));
    }

    public static <T> UnicastSubject<T> create(int i2, Action0 action0) {
        return new UnicastSubject<>(new State(i2, action0));
    }
}

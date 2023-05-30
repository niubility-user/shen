package rx.internal.operators;

import java.util.Queue;
import java.util.concurrent.atomic.AtomicLong;
import rx.Observable;
import rx.Producer;
import rx.Scheduler;
import rx.Subscriber;
import rx.exceptions.MissingBackpressureException;
import rx.functions.Action0;
import rx.internal.schedulers.ImmediateScheduler;
import rx.internal.schedulers.TrampolineScheduler;
import rx.internal.util.RxRingBuffer;
import rx.internal.util.atomic.SpscAtomicArrayQueue;
import rx.internal.util.unsafe.SpscArrayQueue;
import rx.internal.util.unsafe.UnsafeAccess;
import rx.plugins.RxJavaPlugins;
import rx.schedulers.Schedulers;

/* loaded from: classes11.dex */
public final class OperatorObserveOn<T> implements Observable.Operator<T, T> {
    private final int bufferSize;
    private final boolean delayError;
    private final Scheduler scheduler;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes11.dex */
    public static final class ObserveOnSubscriber<T> extends Subscriber<T> implements Action0 {
        final Subscriber<? super T> child;
        final boolean delayError;
        long emitted;
        Throwable error;
        volatile boolean finished;
        final int limit;
        final Queue<Object> queue;
        final Scheduler.Worker recursiveScheduler;
        final AtomicLong requested = new AtomicLong();
        final AtomicLong counter = new AtomicLong();
        final NotificationLite<T> on = NotificationLite.instance();

        public ObserveOnSubscriber(Scheduler scheduler, Subscriber<? super T> subscriber, boolean z, int i2) {
            this.child = subscriber;
            this.recursiveScheduler = scheduler.createWorker();
            this.delayError = z;
            i2 = i2 <= 0 ? RxRingBuffer.SIZE : i2;
            this.limit = i2 - (i2 >> 2);
            if (UnsafeAccess.isUnsafeAvailable()) {
                this.queue = new SpscArrayQueue(i2);
            } else {
                this.queue = new SpscAtomicArrayQueue(i2);
            }
            request(i2);
        }

        @Override // rx.functions.Action0
        public void call() {
            long j2 = this.emitted;
            Queue<Object> queue = this.queue;
            Subscriber<? super T> subscriber = this.child;
            NotificationLite<T> notificationLite = this.on;
            long j3 = 1;
            do {
                long j4 = this.requested.get();
                while (j4 != j2) {
                    boolean z = this.finished;
                    Object poll = queue.poll();
                    boolean z2 = poll == null;
                    if (checkTerminated(z, z2, subscriber, queue)) {
                        return;
                    }
                    if (z2) {
                        break;
                    }
                    subscriber.onNext((T) notificationLite.getValue(poll));
                    j2++;
                    if (j2 == this.limit) {
                        j4 = BackpressureUtils.produced(this.requested, j2);
                        request(j2);
                        j2 = 0;
                    }
                }
                if (j4 == j2 && checkTerminated(this.finished, queue.isEmpty(), subscriber, queue)) {
                    return;
                }
                this.emitted = j2;
                j3 = this.counter.addAndGet(-j3);
            } while (j3 != 0);
        }

        boolean checkTerminated(boolean z, boolean z2, Subscriber<? super T> subscriber, Queue<Object> queue) {
            if (subscriber.isUnsubscribed()) {
                queue.clear();
                return true;
            } else if (z) {
                if (this.delayError) {
                    if (z2) {
                        Throwable th = this.error;
                        try {
                            if (th != null) {
                                subscriber.onError(th);
                            } else {
                                subscriber.onCompleted();
                            }
                            return false;
                        } finally {
                        }
                    }
                    return false;
                }
                Throwable th2 = this.error;
                if (th2 != null) {
                    queue.clear();
                    try {
                        subscriber.onError(th2);
                        return true;
                    } finally {
                    }
                } else if (z2) {
                    try {
                        subscriber.onCompleted();
                        return true;
                    } finally {
                    }
                } else {
                    return false;
                }
            } else {
                return false;
            }
        }

        void init() {
            Subscriber<? super T> subscriber = this.child;
            subscriber.setProducer(new Producer() { // from class: rx.internal.operators.OperatorObserveOn.ObserveOnSubscriber.1
                @Override // rx.Producer
                public void request(long j2) {
                    if (j2 > 0) {
                        BackpressureUtils.getAndAddRequest(ObserveOnSubscriber.this.requested, j2);
                        ObserveOnSubscriber.this.schedule();
                    }
                }
            });
            subscriber.add(this.recursiveScheduler);
            subscriber.add(this);
        }

        @Override // rx.Observer
        public void onCompleted() {
            if (isUnsubscribed() || this.finished) {
                return;
            }
            this.finished = true;
            schedule();
        }

        @Override // rx.Observer
        public void onError(Throwable th) {
            if (!isUnsubscribed() && !this.finished) {
                this.error = th;
                this.finished = true;
                schedule();
                return;
            }
            RxJavaPlugins.getInstance().getErrorHandler().handleError(th);
        }

        @Override // rx.Observer
        public void onNext(T t) {
            if (isUnsubscribed() || this.finished) {
                return;
            }
            if (!this.queue.offer(this.on.next(t))) {
                onError(new MissingBackpressureException());
            } else {
                schedule();
            }
        }

        protected void schedule() {
            if (this.counter.getAndIncrement() == 0) {
                this.recursiveScheduler.schedule(this);
            }
        }
    }

    public OperatorObserveOn(Scheduler scheduler, boolean z) {
        this(scheduler, z, RxRingBuffer.SIZE);
    }

    public static <T> Observable.Operator<T, T> rebatch(final int i2) {
        return new Observable.Operator<T, T>() { // from class: rx.internal.operators.OperatorObserveOn.1
            @Override // rx.functions.Func1
            public /* bridge */ /* synthetic */ Object call(Object obj) {
                return call((Subscriber) ((Subscriber) obj));
            }

            public Subscriber<? super T> call(Subscriber<? super T> subscriber) {
                ObserveOnSubscriber observeOnSubscriber = new ObserveOnSubscriber(Schedulers.immediate(), subscriber, false, i2);
                observeOnSubscriber.init();
                return observeOnSubscriber;
            }
        };
    }

    @Override // rx.functions.Func1
    public /* bridge */ /* synthetic */ Object call(Object obj) {
        return call((Subscriber) ((Subscriber) obj));
    }

    public OperatorObserveOn(Scheduler scheduler, boolean z, int i2) {
        this.scheduler = scheduler;
        this.delayError = z;
        this.bufferSize = i2 <= 0 ? RxRingBuffer.SIZE : i2;
    }

    public Subscriber<? super T> call(Subscriber<? super T> subscriber) {
        Scheduler scheduler = this.scheduler;
        if ((scheduler instanceof ImmediateScheduler) || (scheduler instanceof TrampolineScheduler)) {
            return subscriber;
        }
        ObserveOnSubscriber observeOnSubscriber = new ObserveOnSubscriber(scheduler, subscriber, this.delayError, this.bufferSize);
        observeOnSubscriber.init();
        return observeOnSubscriber;
    }
}

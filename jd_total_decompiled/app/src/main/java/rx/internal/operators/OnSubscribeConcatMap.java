package rx.internal.operators;

import java.util.Queue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import rx.Observable;
import rx.Producer;
import rx.Subscriber;
import rx.exceptions.Exceptions;
import rx.exceptions.MissingBackpressureException;
import rx.functions.Func1;
import rx.internal.producers.ProducerArbiter;
import rx.internal.util.ExceptionsUtils;
import rx.internal.util.ScalarSynchronousObservable;
import rx.internal.util.atomic.SpscAtomicArrayQueue;
import rx.internal.util.unsafe.SpscArrayQueue;
import rx.internal.util.unsafe.UnsafeAccess;
import rx.observers.SerializedSubscriber;
import rx.plugins.RxJavaPlugins;
import rx.subscriptions.SerialSubscription;

/* loaded from: classes11.dex */
public final class OnSubscribeConcatMap<T, R> implements Observable.OnSubscribe<R> {
    public static final int BOUNDARY = 1;
    public static final int END = 2;
    public static final int IMMEDIATE = 0;
    final int delayErrorMode;
    final Func1<? super T, ? extends Observable<? extends R>> mapper;
    final int prefetch;
    final Observable<? extends T> source;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes11.dex */
    public static final class ConcatMapInnerScalarProducer<T, R> implements Producer {
        boolean once;
        final ConcatMapSubscriber<T, R> parent;
        final R value;

        public ConcatMapInnerScalarProducer(R r, ConcatMapSubscriber<T, R> concatMapSubscriber) {
            this.value = r;
            this.parent = concatMapSubscriber;
        }

        @Override // rx.Producer
        public void request(long j2) {
            if (this.once || j2 <= 0) {
                return;
            }
            this.once = true;
            ConcatMapSubscriber<T, R> concatMapSubscriber = this.parent;
            concatMapSubscriber.innerNext(this.value);
            concatMapSubscriber.innerCompleted(1L);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes11.dex */
    public static final class ConcatMapInnerSubscriber<T, R> extends Subscriber<R> {
        final ConcatMapSubscriber<T, R> parent;
        long produced;

        public ConcatMapInnerSubscriber(ConcatMapSubscriber<T, R> concatMapSubscriber) {
            this.parent = concatMapSubscriber;
        }

        @Override // rx.Observer
        public void onCompleted() {
            this.parent.innerCompleted(this.produced);
        }

        @Override // rx.Observer
        public void onError(Throwable th) {
            this.parent.innerError(th, this.produced);
        }

        @Override // rx.Observer
        public void onNext(R r) {
            this.produced++;
            this.parent.innerNext(r);
        }

        @Override // rx.Subscriber
        public void setProducer(Producer producer) {
            this.parent.arbiter.setProducer(producer);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes11.dex */
    public static final class ConcatMapSubscriber<T, R> extends Subscriber<T> {
        volatile boolean active;
        final Subscriber<? super R> actual;
        final int delayErrorMode;
        volatile boolean done;
        final SerialSubscription inner;
        final Func1<? super T, ? extends Observable<? extends R>> mapper;
        final Queue<Object> queue;
        final ProducerArbiter arbiter = new ProducerArbiter();
        final AtomicInteger wip = new AtomicInteger();
        final AtomicReference<Throwable> error = new AtomicReference<>();

        public ConcatMapSubscriber(Subscriber<? super R> subscriber, Func1<? super T, ? extends Observable<? extends R>> func1, int i2, int i3) {
            Queue<Object> spscAtomicArrayQueue;
            this.actual = subscriber;
            this.mapper = func1;
            this.delayErrorMode = i3;
            if (UnsafeAccess.isUnsafeAvailable()) {
                spscAtomicArrayQueue = new SpscArrayQueue<>(i2);
            } else {
                spscAtomicArrayQueue = new SpscAtomicArrayQueue<>(i2);
            }
            this.queue = spscAtomicArrayQueue;
            this.inner = new SerialSubscription();
            request(i2);
        }

        void drain() {
            if (this.wip.getAndIncrement() != 0) {
                return;
            }
            int i2 = this.delayErrorMode;
            while (!this.actual.isUnsubscribed()) {
                if (!this.active) {
                    if (i2 == 1 && this.error.get() != null) {
                        Throwable terminate = ExceptionsUtils.terminate(this.error);
                        if (ExceptionsUtils.isTerminated(terminate)) {
                            return;
                        }
                        this.actual.onError(terminate);
                        return;
                    }
                    boolean z = this.done;
                    Object poll = this.queue.poll();
                    boolean z2 = poll == null;
                    if (z && z2) {
                        Throwable terminate2 = ExceptionsUtils.terminate(this.error);
                        if (terminate2 == null) {
                            this.actual.onCompleted();
                            return;
                        } else if (ExceptionsUtils.isTerminated(terminate2)) {
                            return;
                        } else {
                            this.actual.onError(terminate2);
                            return;
                        }
                    } else if (!z2) {
                        try {
                            Observable<? extends R> call = this.mapper.call((Object) NotificationLite.instance().getValue(poll));
                            if (call == null) {
                                drainError(new NullPointerException("The source returned by the mapper was null"));
                                return;
                            } else if (call != Observable.empty()) {
                                if (call instanceof ScalarSynchronousObservable) {
                                    this.active = true;
                                    this.arbiter.setProducer(new ConcatMapInnerScalarProducer(((ScalarSynchronousObservable) call).get(), this));
                                } else {
                                    ConcatMapInnerSubscriber concatMapInnerSubscriber = new ConcatMapInnerSubscriber(this);
                                    this.inner.set(concatMapInnerSubscriber);
                                    if (concatMapInnerSubscriber.isUnsubscribed()) {
                                        return;
                                    }
                                    this.active = true;
                                    call.unsafeSubscribe(concatMapInnerSubscriber);
                                }
                                request(1L);
                            } else {
                                request(1L);
                            }
                        } catch (Throwable th) {
                            Exceptions.throwIfFatal(th);
                            drainError(th);
                            return;
                        }
                    }
                }
                if (this.wip.decrementAndGet() == 0) {
                    return;
                }
            }
        }

        void drainError(Throwable th) {
            unsubscribe();
            if (ExceptionsUtils.addThrowable(this.error, th)) {
                Throwable terminate = ExceptionsUtils.terminate(this.error);
                if (ExceptionsUtils.isTerminated(terminate)) {
                    return;
                }
                this.actual.onError(terminate);
                return;
            }
            pluginError(th);
        }

        void innerCompleted(long j2) {
            if (j2 != 0) {
                this.arbiter.produced(j2);
            }
            this.active = false;
            drain();
        }

        void innerError(Throwable th, long j2) {
            if (!ExceptionsUtils.addThrowable(this.error, th)) {
                pluginError(th);
            } else if (this.delayErrorMode == 0) {
                Throwable terminate = ExceptionsUtils.terminate(this.error);
                if (!ExceptionsUtils.isTerminated(terminate)) {
                    this.actual.onError(terminate);
                }
                unsubscribe();
            } else {
                if (j2 != 0) {
                    this.arbiter.produced(j2);
                }
                this.active = false;
                drain();
            }
        }

        void innerNext(R r) {
            this.actual.onNext(r);
        }

        @Override // rx.Observer
        public void onCompleted() {
            this.done = true;
            drain();
        }

        @Override // rx.Observer
        public void onError(Throwable th) {
            if (ExceptionsUtils.addThrowable(this.error, th)) {
                this.done = true;
                if (this.delayErrorMode == 0) {
                    Throwable terminate = ExceptionsUtils.terminate(this.error);
                    if (!ExceptionsUtils.isTerminated(terminate)) {
                        this.actual.onError(terminate);
                    }
                    this.inner.unsubscribe();
                    return;
                }
                drain();
                return;
            }
            pluginError(th);
        }

        @Override // rx.Observer
        public void onNext(T t) {
            if (!this.queue.offer(NotificationLite.instance().next(t))) {
                unsubscribe();
                onError(new MissingBackpressureException());
                return;
            }
            drain();
        }

        void pluginError(Throwable th) {
            RxJavaPlugins.getInstance().getErrorHandler().handleError(th);
        }

        void requestMore(long j2) {
            if (j2 > 0) {
                this.arbiter.request(j2);
            } else if (j2 >= 0) {
            } else {
                throw new IllegalArgumentException("n >= 0 required but it was " + j2);
            }
        }
    }

    public OnSubscribeConcatMap(Observable<? extends T> observable, Func1<? super T, ? extends Observable<? extends R>> func1, int i2, int i3) {
        this.source = observable;
        this.mapper = func1;
        this.prefetch = i2;
        this.delayErrorMode = i3;
    }

    @Override // rx.functions.Action1
    public /* bridge */ /* synthetic */ void call(Object obj) {
        call((Subscriber) ((Subscriber) obj));
    }

    public void call(Subscriber<? super R> subscriber) {
        final ConcatMapSubscriber concatMapSubscriber = new ConcatMapSubscriber(this.delayErrorMode == 0 ? new SerializedSubscriber<>(subscriber) : subscriber, this.mapper, this.prefetch, this.delayErrorMode);
        subscriber.add(concatMapSubscriber);
        subscriber.add(concatMapSubscriber.inner);
        subscriber.setProducer(new Producer() { // from class: rx.internal.operators.OnSubscribeConcatMap.1
            @Override // rx.Producer
            public void request(long j2) {
                concatMapSubscriber.requestMore(j2);
            }
        });
        if (subscriber.isUnsubscribed()) {
            return;
        }
        this.source.unsafeSubscribe(concatMapSubscriber);
    }
}

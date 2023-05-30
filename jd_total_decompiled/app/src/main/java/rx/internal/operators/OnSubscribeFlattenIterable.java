package rx.internal.operators;

import java.util.Iterator;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import rx.Observable;
import rx.Producer;
import rx.Subscriber;
import rx.exceptions.Exceptions;
import rx.exceptions.MissingBackpressureException;
import rx.functions.Func1;
import rx.internal.operators.OnSubscribeFromIterable;
import rx.internal.util.ExceptionsUtils;
import rx.internal.util.RxJavaPluginUtils;
import rx.internal.util.RxRingBuffer;
import rx.internal.util.ScalarSynchronousObservable;
import rx.internal.util.atomic.SpscAtomicArrayQueue;
import rx.internal.util.atomic.SpscLinkedArrayQueue;
import rx.internal.util.unsafe.SpscArrayQueue;
import rx.internal.util.unsafe.UnsafeAccess;

/* loaded from: classes11.dex */
public final class OnSubscribeFlattenIterable<T, R> implements Observable.OnSubscribe<R> {
    final Func1<? super T, ? extends Iterable<? extends R>> mapper;
    final int prefetch;
    final Observable<? extends T> source;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes11.dex */
    public static final class FlattenIterableSubscriber<T, R> extends Subscriber<T> {
        Iterator<? extends R> active;
        final Subscriber<? super R> actual;
        volatile boolean done;
        final long limit;
        final Func1<? super T, ? extends Iterable<? extends R>> mapper;
        long produced;
        final Queue<Object> queue;
        final AtomicReference<Throwable> error = new AtomicReference<>();
        final AtomicInteger wip = new AtomicInteger();
        final AtomicLong requested = new AtomicLong();
        final NotificationLite<T> nl = NotificationLite.instance();

        public FlattenIterableSubscriber(Subscriber<? super R> subscriber, Func1<? super T, ? extends Iterable<? extends R>> func1, int i2) {
            this.actual = subscriber;
            this.mapper = func1;
            if (i2 == Integer.MAX_VALUE) {
                this.limit = Long.MAX_VALUE;
                this.queue = new SpscLinkedArrayQueue(RxRingBuffer.SIZE);
            } else {
                this.limit = i2 - (i2 >> 2);
                if (UnsafeAccess.isUnsafeAvailable()) {
                    this.queue = new SpscArrayQueue(i2);
                } else {
                    this.queue = new SpscAtomicArrayQueue(i2);
                }
            }
            request(i2);
        }

        boolean checkTerminated(boolean z, boolean z2, Subscriber<?> subscriber, Queue<?> queue) {
            if (subscriber.isUnsubscribed()) {
                queue.clear();
                this.active = null;
                return true;
            } else if (z) {
                if (this.error.get() == null) {
                    if (z2) {
                        subscriber.onCompleted();
                        return true;
                    }
                    return false;
                }
                Throwable terminate = ExceptionsUtils.terminate(this.error);
                unsubscribe();
                queue.clear();
                this.active = null;
                subscriber.onError(terminate);
                return true;
            } else {
                return false;
            }
        }

        /* JADX WARN: Removed duplicated region for block: B:28:0x0068  */
        /* JADX WARN: Removed duplicated region for block: B:75:0x00da A[SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:78:0x00d1 A[SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:81:0x0010 A[SYNTHETIC] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        void drain() {
            int i2;
            if (this.wip.getAndIncrement() != 0) {
                return;
            }
            Subscriber<? super R> subscriber = this.actual;
            Queue<?> queue = this.queue;
            int i3 = 1;
            while (true) {
                Iterator<? extends R> it = this.active;
                boolean z = false;
                if (it == null) {
                    boolean z2 = this.done;
                    Object poll = queue.poll();
                    boolean z3 = poll == null;
                    if (checkTerminated(z2, z3, subscriber, queue)) {
                        return;
                    }
                    if (!z3) {
                        long j2 = this.produced + 1;
                        i2 = i3;
                        if (j2 == this.limit) {
                            this.produced = 0L;
                            request(j2);
                        } else {
                            this.produced = j2;
                        }
                        try {
                            it = this.mapper.call((T) this.nl.getValue(poll)).iterator();
                        } catch (Throwable th) {
                            Exceptions.throwIfFatal(th);
                            onError(th);
                        }
                        if (it.hasNext()) {
                            this.active = it;
                            if (it == null) {
                                long j3 = this.requested.get();
                                long j4 = 0;
                                while (j4 != j3) {
                                    if (checkTerminated(this.done, false, subscriber, queue)) {
                                        return;
                                    }
                                    try {
                                        subscriber.onNext((R) it.next());
                                    } catch (Throwable th2) {
                                        Exceptions.throwIfFatal(th2);
                                        this.active = null;
                                        onError(th2);
                                    }
                                    if (checkTerminated(this.done, false, subscriber, queue)) {
                                        return;
                                    }
                                    j4++;
                                    try {
                                    } catch (Throwable th3) {
                                        Exceptions.throwIfFatal(th3);
                                        this.active = null;
                                        onError(th3);
                                    }
                                    if (!it.hasNext()) {
                                        this.active = null;
                                        it = null;
                                        break;
                                    }
                                }
                                if (j4 == j3) {
                                    boolean z4 = this.done;
                                    if (queue.isEmpty() && it == null) {
                                        z = true;
                                    }
                                    if (checkTerminated(z4, z, subscriber, queue)) {
                                        return;
                                    }
                                }
                                if (j4 != 0) {
                                    BackpressureUtils.produced(this.requested, j4);
                                }
                                if (it == null) {
                                    i3 = i2;
                                }
                            }
                            i3 = this.wip.addAndGet(-i2);
                            if (i3 != 0) {
                                return;
                            }
                        } else {
                            i3 = i2;
                        }
                    }
                }
                i2 = i3;
                if (it == null) {
                }
                i3 = this.wip.addAndGet(-i2);
                if (i3 != 0) {
                }
            }
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
                drain();
                return;
            }
            RxJavaPluginUtils.handleException(th);
        }

        @Override // rx.Observer
        public void onNext(T t) {
            if (!this.queue.offer(this.nl.next(t))) {
                unsubscribe();
                onError(new MissingBackpressureException());
                return;
            }
            drain();
        }

        void requestMore(long j2) {
            if (j2 > 0) {
                BackpressureUtils.getAndAddRequest(this.requested, j2);
                drain();
            } else if (j2 >= 0) {
            } else {
                throw new IllegalStateException("n >= 0 required but it was " + j2);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes11.dex */
    public static final class OnSubscribeScalarFlattenIterable<T, R> implements Observable.OnSubscribe<R> {
        final Func1<? super T, ? extends Iterable<? extends R>> mapper;
        final T value;

        public OnSubscribeScalarFlattenIterable(T t, Func1<? super T, ? extends Iterable<? extends R>> func1) {
            this.value = t;
            this.mapper = func1;
        }

        @Override // rx.functions.Action1
        public /* bridge */ /* synthetic */ void call(Object obj) {
            call((Subscriber) ((Subscriber) obj));
        }

        public void call(Subscriber<? super R> subscriber) {
            try {
                Iterator<? extends R> it = this.mapper.call((T) this.value).iterator();
                if (!it.hasNext()) {
                    subscriber.onCompleted();
                } else {
                    subscriber.setProducer(new OnSubscribeFromIterable.IterableProducer(subscriber, it));
                }
            } catch (Throwable th) {
                Exceptions.throwOrReport(th, subscriber, this.value);
            }
        }
    }

    protected OnSubscribeFlattenIterable(Observable<? extends T> observable, Func1<? super T, ? extends Iterable<? extends R>> func1, int i2) {
        this.source = observable;
        this.mapper = func1;
        this.prefetch = i2;
    }

    public static <T, R> Observable<R> createFrom(Observable<? extends T> observable, Func1<? super T, ? extends Iterable<? extends R>> func1, int i2) {
        if (observable instanceof ScalarSynchronousObservable) {
            return Observable.create(new OnSubscribeScalarFlattenIterable(((ScalarSynchronousObservable) observable).get(), func1));
        }
        return Observable.create(new OnSubscribeFlattenIterable(observable, func1, i2));
    }

    @Override // rx.functions.Action1
    public /* bridge */ /* synthetic */ void call(Object obj) {
        call((Subscriber) ((Subscriber) obj));
    }

    public void call(Subscriber<? super R> subscriber) {
        final FlattenIterableSubscriber flattenIterableSubscriber = new FlattenIterableSubscriber(subscriber, this.mapper, this.prefetch);
        subscriber.add(flattenIterableSubscriber);
        subscriber.setProducer(new Producer() { // from class: rx.internal.operators.OnSubscribeFlattenIterable.1
            @Override // rx.Producer
            public void request(long j2) {
                flattenIterableSubscriber.requestMore(j2);
            }
        });
        this.source.unsafeSubscribe(flattenIterableSubscriber);
    }
}

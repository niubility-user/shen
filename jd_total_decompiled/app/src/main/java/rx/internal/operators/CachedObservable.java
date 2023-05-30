package rx.internal.operators;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;
import rx.Observable;
import rx.Observer;
import rx.Producer;
import rx.Subscriber;
import rx.Subscription;
import rx.exceptions.Exceptions;
import rx.exceptions.OnErrorThrowable;
import rx.internal.util.LinkedArrayList;
import rx.subscriptions.SerialSubscription;

/* loaded from: classes11.dex */
public final class CachedObservable<T> extends Observable<T> {
    private final CacheState<T> state;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes11.dex */
    public static final class CacheState<T> extends LinkedArrayList implements Observer<T> {
        static final ReplayProducer<?>[] EMPTY = new ReplayProducer[0];
        final SerialSubscription connection;
        volatile boolean isConnected;
        final NotificationLite<T> nl;
        volatile ReplayProducer<?>[] producers;
        final Observable<? extends T> source;
        boolean sourceDone;

        public CacheState(Observable<? extends T> observable, int i2) {
            super(i2);
            this.source = observable;
            this.producers = EMPTY;
            this.nl = NotificationLite.instance();
            this.connection = new SerialSubscription();
        }

        /* JADX WARN: Multi-variable type inference failed */
        public void addProducer(ReplayProducer<T> replayProducer) {
            synchronized (this.connection) {
                ReplayProducer<?>[] replayProducerArr = this.producers;
                int length = replayProducerArr.length;
                ReplayProducer<?>[] replayProducerArr2 = new ReplayProducer[length + 1];
                System.arraycopy(replayProducerArr, 0, replayProducerArr2, 0, length);
                replayProducerArr2[length] = replayProducer;
                this.producers = replayProducerArr2;
            }
        }

        public void connect() {
            Subscriber<T> subscriber = new Subscriber<T>() { // from class: rx.internal.operators.CachedObservable.CacheState.1
                @Override // rx.Observer
                public void onCompleted() {
                    CacheState.this.onCompleted();
                }

                @Override // rx.Observer
                public void onError(Throwable th) {
                    CacheState.this.onError(th);
                }

                @Override // rx.Observer
                public void onNext(T t) {
                    CacheState.this.onNext(t);
                }
            };
            this.connection.set(subscriber);
            this.source.unsafeSubscribe(subscriber);
            this.isConnected = true;
        }

        void dispatch() {
            for (ReplayProducer<?> replayProducer : this.producers) {
                replayProducer.replay();
            }
        }

        @Override // rx.Observer
        public void onCompleted() {
            if (this.sourceDone) {
                return;
            }
            this.sourceDone = true;
            add(this.nl.completed());
            this.connection.unsubscribe();
            dispatch();
        }

        @Override // rx.Observer
        public void onError(Throwable th) {
            if (this.sourceDone) {
                return;
            }
            this.sourceDone = true;
            add(this.nl.error(th));
            this.connection.unsubscribe();
            dispatch();
        }

        @Override // rx.Observer
        public void onNext(T t) {
            if (this.sourceDone) {
                return;
            }
            add(this.nl.next(t));
            dispatch();
        }

        public void removeProducer(ReplayProducer<T> replayProducer) {
            synchronized (this.connection) {
                ReplayProducer<?>[] replayProducerArr = this.producers;
                int length = replayProducerArr.length;
                int i2 = -1;
                int i3 = 0;
                while (true) {
                    if (i3 >= length) {
                        break;
                    } else if (replayProducerArr[i3].equals(replayProducer)) {
                        i2 = i3;
                        break;
                    } else {
                        i3++;
                    }
                }
                if (i2 < 0) {
                    return;
                }
                if (length == 1) {
                    this.producers = EMPTY;
                    return;
                }
                ReplayProducer<?>[] replayProducerArr2 = new ReplayProducer[length - 1];
                System.arraycopy(replayProducerArr, 0, replayProducerArr2, 0, i2);
                System.arraycopy(replayProducerArr, i2 + 1, replayProducerArr2, i2, (length - i2) - 1);
                this.producers = replayProducerArr2;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes11.dex */
    public static final class CachedSubscribe<T> extends AtomicBoolean implements Observable.OnSubscribe<T> {
        private static final long serialVersionUID = -2817751667698696782L;
        final CacheState<T> state;

        public CachedSubscribe(CacheState<T> cacheState) {
            this.state = cacheState;
        }

        @Override // rx.functions.Action1
        public /* bridge */ /* synthetic */ void call(Object obj) {
            call((Subscriber) ((Subscriber) obj));
        }

        public void call(Subscriber<? super T> subscriber) {
            ReplayProducer<T> replayProducer = new ReplayProducer<>(subscriber, this.state);
            this.state.addProducer(replayProducer);
            subscriber.add(replayProducer);
            subscriber.setProducer(replayProducer);
            if (get() || !compareAndSet(false, true)) {
                return;
            }
            this.state.connect();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes11.dex */
    public static final class ReplayProducer<T> extends AtomicLong implements Producer, Subscription {
        private static final long serialVersionUID = -2557562030197141021L;
        final Subscriber<? super T> child;
        Object[] currentBuffer;
        int currentIndexInBuffer;
        boolean emitting;
        int index;
        boolean missed;
        final CacheState<T> state;

        public ReplayProducer(Subscriber<? super T> subscriber, CacheState<T> cacheState) {
            this.child = subscriber;
            this.state = cacheState;
        }

        @Override // rx.Subscription
        public boolean isUnsubscribed() {
            return get() < 0;
        }

        public long produced(long j2) {
            return addAndGet(-j2);
        }

        public void replay() {
            boolean z;
            synchronized (this) {
                boolean z2 = true;
                if (this.emitting) {
                    this.missed = true;
                    return;
                }
                this.emitting = true;
                try {
                    NotificationLite<T> notificationLite = this.state.nl;
                    Subscriber<? super T> subscriber = this.child;
                    while (true) {
                        long j2 = get();
                        if (j2 < 0) {
                            return;
                        }
                        int size = this.state.size();
                        try {
                            if (size != 0) {
                                Object[] objArr = this.currentBuffer;
                                if (objArr == null) {
                                    objArr = this.state.head();
                                    this.currentBuffer = objArr;
                                }
                                int length = objArr.length - 1;
                                int i2 = this.index;
                                int i3 = this.currentIndexInBuffer;
                                if (j2 == 0) {
                                    Object obj = objArr[i3];
                                    if (notificationLite.isCompleted(obj)) {
                                        subscriber.onCompleted();
                                        unsubscribe();
                                        return;
                                    } else if (notificationLite.isError(obj)) {
                                        subscriber.onError(notificationLite.getError(obj));
                                        unsubscribe();
                                        return;
                                    }
                                } else if (j2 > 0) {
                                    int i4 = 0;
                                    while (i2 < size && j2 > 0) {
                                        if (subscriber.isUnsubscribed()) {
                                            return;
                                        }
                                        if (i3 == length) {
                                            objArr = (Object[]) objArr[length];
                                            i3 = 0;
                                        }
                                        Object obj2 = objArr[i3];
                                        try {
                                            if (notificationLite.accept(subscriber, obj2)) {
                                                try {
                                                    unsubscribe();
                                                    return;
                                                } catch (Throwable th) {
                                                    th = th;
                                                    z = true;
                                                    try {
                                                        Exceptions.throwIfFatal(th);
                                                        unsubscribe();
                                                        if (notificationLite.isError(obj2) || notificationLite.isCompleted(obj2)) {
                                                            return;
                                                        }
                                                        subscriber.onError(OnErrorThrowable.addValueAsLastCause(th, notificationLite.getValue(obj2)));
                                                        return;
                                                    } catch (Throwable th2) {
                                                        th = th2;
                                                        z2 = z;
                                                        if (!z2) {
                                                            synchronized (this) {
                                                                this.emitting = false;
                                                            }
                                                        }
                                                        throw th;
                                                    }
                                                }
                                            }
                                            i3++;
                                            i2++;
                                            j2--;
                                            i4++;
                                        } catch (Throwable th3) {
                                            th = th3;
                                            z = false;
                                        }
                                    }
                                    if (subscriber.isUnsubscribed()) {
                                        return;
                                    }
                                    this.index = i2;
                                    this.currentIndexInBuffer = i3;
                                    this.currentBuffer = objArr;
                                    produced(i4);
                                }
                            }
                            try {
                                synchronized (this) {
                                    try {
                                        if (!this.missed) {
                                            this.emitting = false;
                                            return;
                                        }
                                        this.missed = false;
                                    } catch (Throwable th4) {
                                        th = th4;
                                        throw th;
                                    }
                                }
                            } catch (Throwable th5) {
                                th = th5;
                            }
                        } catch (Throwable th6) {
                            th = th6;
                        }
                    }
                } catch (Throwable th7) {
                    th = th7;
                    z2 = false;
                }
            }
        }

        @Override // rx.Producer
        public void request(long j2) {
            long j3;
            long j4;
            do {
                j3 = get();
                if (j3 < 0) {
                    return;
                }
                j4 = j3 + j2;
                if (j4 < 0) {
                    j4 = Long.MAX_VALUE;
                }
            } while (!compareAndSet(j3, j4));
            replay();
        }

        @Override // rx.Subscription
        public void unsubscribe() {
            if (get() < 0 || getAndSet(-1L) < 0) {
                return;
            }
            this.state.removeProducer(this);
        }
    }

    private CachedObservable(Observable.OnSubscribe<T> onSubscribe, CacheState<T> cacheState) {
        super(onSubscribe);
        this.state = cacheState;
    }

    public static <T> CachedObservable<T> from(Observable<? extends T> observable) {
        return from(observable, 16);
    }

    boolean hasObservers() {
        return this.state.producers.length != 0;
    }

    boolean isConnected() {
        return this.state.isConnected;
    }

    public static <T> CachedObservable<T> from(Observable<? extends T> observable, int i2) {
        if (i2 >= 1) {
            CacheState cacheState = new CacheState(observable, i2);
            return new CachedObservable<>(new CachedSubscribe(cacheState), cacheState);
        }
        throw new IllegalArgumentException("capacityHint > 0 required");
    }
}

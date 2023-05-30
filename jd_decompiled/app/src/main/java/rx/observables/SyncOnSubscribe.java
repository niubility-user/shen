package rx.observables;

import java.util.concurrent.atomic.AtomicLong;
import rx.Observable;
import rx.Observer;
import rx.Producer;
import rx.Subscriber;
import rx.Subscription;
import rx.annotations.Beta;
import rx.exceptions.Exceptions;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Action2;
import rx.functions.Func0;
import rx.functions.Func2;
import rx.internal.operators.BackpressureUtils;
import rx.plugins.RxJavaPlugins;

@Beta
/* loaded from: classes11.dex */
public abstract class SyncOnSubscribe<S, T> implements Observable.OnSubscribe<T> {

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes11.dex */
    public static class SubscriptionProducer<S, T> extends AtomicLong implements Producer, Subscription, Observer<T> {
        private static final long serialVersionUID = -3736864024352728072L;
        private final Subscriber<? super T> actualSubscriber;
        private boolean hasTerminated;
        private boolean onNextCalled;
        private final SyncOnSubscribe<S, T> parent;
        private S state;

        SubscriptionProducer(Subscriber<? super T> subscriber, SyncOnSubscribe<S, T> syncOnSubscribe, S s) {
            this.actualSubscriber = subscriber;
            this.parent = syncOnSubscribe;
            this.state = s;
        }

        private void doUnsubscribe() {
            try {
                this.parent.onUnsubscribe(this.state);
            } catch (Throwable th) {
                Exceptions.throwIfFatal(th);
                RxJavaPlugins.getInstance().getErrorHandler().handleError(th);
            }
        }

        private void fastpath() {
            SyncOnSubscribe<S, T> syncOnSubscribe = this.parent;
            Subscriber<? super T> subscriber = this.actualSubscriber;
            do {
                try {
                    this.onNextCalled = false;
                    nextIteration(syncOnSubscribe);
                } catch (Throwable th) {
                    handleThrownError(subscriber, th);
                    return;
                }
            } while (!tryUnsubscribe());
        }

        private void handleThrownError(Subscriber<? super T> subscriber, Throwable th) {
            if (this.hasTerminated) {
                RxJavaPlugins.getInstance().getErrorHandler().handleError(th);
                return;
            }
            this.hasTerminated = true;
            subscriber.onError(th);
            unsubscribe();
        }

        private void nextIteration(SyncOnSubscribe<S, T> syncOnSubscribe) {
            this.state = syncOnSubscribe.next(this.state, this);
        }

        private void slowPath(long j2) {
            SyncOnSubscribe<S, T> syncOnSubscribe = this.parent;
            Subscriber<? super T> subscriber = this.actualSubscriber;
            do {
                long j3 = j2;
                do {
                    try {
                        this.onNextCalled = false;
                        nextIteration(syncOnSubscribe);
                        if (tryUnsubscribe()) {
                            return;
                        }
                        if (this.onNextCalled) {
                            j3--;
                        }
                    } catch (Throwable th) {
                        handleThrownError(subscriber, th);
                        return;
                    }
                } while (j3 != 0);
                j2 = addAndGet(-j2);
            } while (j2 > 0);
            tryUnsubscribe();
        }

        private boolean tryUnsubscribe() {
            if (this.hasTerminated || get() < -1) {
                set(-1L);
                doUnsubscribe();
                return true;
            }
            return false;
        }

        @Override // rx.Subscription
        public boolean isUnsubscribed() {
            return get() < 0;
        }

        @Override // rx.Observer
        public void onCompleted() {
            if (!this.hasTerminated) {
                this.hasTerminated = true;
                if (this.actualSubscriber.isUnsubscribed()) {
                    return;
                }
                this.actualSubscriber.onCompleted();
                return;
            }
            throw new IllegalStateException("Terminal event already emitted.");
        }

        @Override // rx.Observer
        public void onError(Throwable th) {
            if (!this.hasTerminated) {
                this.hasTerminated = true;
                if (this.actualSubscriber.isUnsubscribed()) {
                    return;
                }
                this.actualSubscriber.onError(th);
                return;
            }
            throw new IllegalStateException("Terminal event already emitted.");
        }

        @Override // rx.Observer
        public void onNext(T t) {
            if (!this.onNextCalled) {
                this.onNextCalled = true;
                this.actualSubscriber.onNext(t);
                return;
            }
            throw new IllegalStateException("onNext called multiple times!");
        }

        @Override // rx.Producer
        public void request(long j2) {
            if (j2 <= 0 || BackpressureUtils.getAndAddRequest(this, j2) != 0) {
                return;
            }
            if (j2 == Long.MAX_VALUE) {
                fastpath();
            } else {
                slowPath(j2);
            }
        }

        @Override // rx.Subscription
        public void unsubscribe() {
            long j2;
            do {
                j2 = get();
                if (compareAndSet(0L, -1L)) {
                    doUnsubscribe();
                    return;
                }
            } while (!compareAndSet(j2, -2L));
        }
    }

    @Beta
    public static <S, T> SyncOnSubscribe<S, T> createSingleState(Func0<? extends S> func0, final Action2<? super S, ? super Observer<? super T>> action2) {
        return new SyncOnSubscribeImpl(func0, new Func2<S, Observer<? super T>, S>() { // from class: rx.observables.SyncOnSubscribe.1
            @Override // rx.functions.Func2
            public /* bridge */ /* synthetic */ Object call(Object obj, Object obj2) {
                return call((AnonymousClass1) obj, (Observer) ((Observer) obj2));
            }

            public S call(S s, Observer<? super T> observer) {
                Action2.this.call(s, observer);
                return s;
            }
        });
    }

    @Beta
    public static <S, T> SyncOnSubscribe<S, T> createStateful(Func0<? extends S> func0, Func2<? super S, ? super Observer<? super T>, ? extends S> func2, Action1<? super S> action1) {
        return new SyncOnSubscribeImpl(func0, func2, action1);
    }

    @Beta
    public static <T> SyncOnSubscribe<Void, T> createStateless(final Action1<? super Observer<? super T>> action1) {
        return new SyncOnSubscribeImpl(new Func2<Void, Observer<? super T>, Void>() { // from class: rx.observables.SyncOnSubscribe.3
            @Override // rx.functions.Func2
            public /* bridge */ /* synthetic */ Void call(Void r1, Object obj) {
                return call(r1, (Observer) ((Observer) obj));
            }

            public Void call(Void r2, Observer<? super T> observer) {
                Action1.this.call(observer);
                return r2;
            }
        });
    }

    @Override // rx.functions.Action1
    public /* bridge */ /* synthetic */ void call(Object obj) {
        call((Subscriber) ((Subscriber) obj));
    }

    protected abstract S generateState();

    protected abstract S next(S s, Observer<? super T> observer);

    protected void onUnsubscribe(S s) {
    }

    @Beta
    public static <S, T> SyncOnSubscribe<S, T> createStateful(Func0<? extends S> func0, Func2<? super S, ? super Observer<? super T>, ? extends S> func2) {
        return new SyncOnSubscribeImpl(func0, func2);
    }

    public final void call(Subscriber<? super T> subscriber) {
        try {
            SubscriptionProducer subscriptionProducer = new SubscriptionProducer(subscriber, this, generateState());
            subscriber.add(subscriptionProducer);
            subscriber.setProducer(subscriptionProducer);
        } catch (Throwable th) {
            Exceptions.throwIfFatal(th);
            subscriber.onError(th);
        }
    }

    @Beta
    public static <S, T> SyncOnSubscribe<S, T> createSingleState(Func0<? extends S> func0, final Action2<? super S, ? super Observer<? super T>> action2, Action1<? super S> action1) {
        return new SyncOnSubscribeImpl(func0, new Func2<S, Observer<? super T>, S>() { // from class: rx.observables.SyncOnSubscribe.2
            @Override // rx.functions.Func2
            public /* bridge */ /* synthetic */ Object call(Object obj, Object obj2) {
                return call((AnonymousClass2) obj, (Observer) ((Observer) obj2));
            }

            public S call(S s, Observer<? super T> observer) {
                Action2.this.call(s, observer);
                return s;
            }
        }, action1);
    }

    @Beta
    public static <T> SyncOnSubscribe<Void, T> createStateless(final Action1<? super Observer<? super T>> action1, final Action0 action0) {
        return new SyncOnSubscribeImpl(new Func2<Void, Observer<? super T>, Void>() { // from class: rx.observables.SyncOnSubscribe.4
            @Override // rx.functions.Func2
            public /* bridge */ /* synthetic */ Void call(Void r1, Object obj) {
                return call(r1, (Observer) ((Observer) obj));
            }

            public Void call(Void r1, Observer<? super T> observer) {
                Action1.this.call(observer);
                return null;
            }
        }, new Action1<Void>() { // from class: rx.observables.SyncOnSubscribe.5
            @Override // rx.functions.Action1
            public void call(Void r1) {
                Action0.this.call();
            }
        });
    }

    /* loaded from: classes11.dex */
    private static final class SyncOnSubscribeImpl<S, T> extends SyncOnSubscribe<S, T> {
        private final Func0<? extends S> generator;
        private final Func2<? super S, ? super Observer<? super T>, ? extends S> next;
        private final Action1<? super S> onUnsubscribe;

        SyncOnSubscribeImpl(Func0<? extends S> func0, Func2<? super S, ? super Observer<? super T>, ? extends S> func2, Action1<? super S> action1) {
            this.generator = func0;
            this.next = func2;
            this.onUnsubscribe = action1;
        }

        @Override // rx.observables.SyncOnSubscribe, rx.functions.Action1
        public /* bridge */ /* synthetic */ void call(Object obj) {
            super.call((Subscriber) ((Subscriber) obj));
        }

        @Override // rx.observables.SyncOnSubscribe
        protected S generateState() {
            Func0<? extends S> func0 = this.generator;
            if (func0 == null) {
                return null;
            }
            return func0.call();
        }

        @Override // rx.observables.SyncOnSubscribe
        protected S next(S s, Observer<? super T> observer) {
            return this.next.call(s, observer);
        }

        @Override // rx.observables.SyncOnSubscribe
        protected void onUnsubscribe(S s) {
            Action1<? super S> action1 = this.onUnsubscribe;
            if (action1 != null) {
                action1.call(s);
            }
        }

        public SyncOnSubscribeImpl(Func0<? extends S> func0, Func2<? super S, ? super Observer<? super T>, ? extends S> func2) {
            this(func0, func2, null);
        }

        public SyncOnSubscribeImpl(Func2<S, Observer<? super T>, S> func2, Action1<? super S> action1) {
            this(null, func2, action1);
        }

        public SyncOnSubscribeImpl(Func2<S, Observer<? super T>, S> func2) {
            this(null, func2, null);
        }
    }
}

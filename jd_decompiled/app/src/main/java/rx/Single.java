package rx;

import java.util.Collection;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import rx.Completable;
import rx.Observable;
import rx.Scheduler;
import rx.annotations.Beta;
import rx.annotations.Experimental;
import rx.exceptions.Exceptions;
import rx.exceptions.OnErrorNotImplementedException;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func0;
import rx.functions.Func1;
import rx.functions.Func2;
import rx.functions.Func3;
import rx.functions.Func4;
import rx.functions.Func5;
import rx.functions.Func6;
import rx.functions.Func7;
import rx.functions.Func8;
import rx.functions.Func9;
import rx.functions.FuncN;
import rx.internal.operators.OnSubscribeToObservableFuture;
import rx.internal.operators.OperatorDelay;
import rx.internal.operators.OperatorDoOnEach;
import rx.internal.operators.OperatorDoOnSubscribe;
import rx.internal.operators.OperatorDoOnUnsubscribe;
import rx.internal.operators.OperatorMap;
import rx.internal.operators.OperatorObserveOn;
import rx.internal.operators.OperatorOnErrorResumeNextViaFunction;
import rx.internal.operators.OperatorTimeout;
import rx.internal.operators.SingleDoAfterTerminate;
import rx.internal.operators.SingleOnSubscribeDelaySubscriptionOther;
import rx.internal.operators.SingleOnSubscribeUsing;
import rx.internal.operators.SingleOperatorOnErrorResumeNext;
import rx.internal.operators.SingleOperatorZip;
import rx.internal.producers.SingleDelayedProducer;
import rx.internal.util.ScalarSynchronousSingle;
import rx.internal.util.UtilityFunctions;
import rx.observers.SafeSubscriber;
import rx.observers.SerializedSubscriber;
import rx.plugins.RxJavaPlugins;
import rx.plugins.RxJavaSingleExecutionHook;
import rx.schedulers.Schedulers;
import rx.singles.BlockingSingle;
import rx.subscriptions.Subscriptions;

@Beta
/* loaded from: classes11.dex */
public class Single<T> {
    static RxJavaSingleExecutionHook hook = RxJavaPlugins.getInstance().getSingleExecutionHook();
    final Observable.OnSubscribe<T> onSubscribe;

    /* loaded from: classes11.dex */
    public interface OnSubscribe<T> extends Action1<SingleSubscriber<? super T>> {
    }

    /* loaded from: classes11.dex */
    public interface Transformer<T, R> extends Func1<Single<T>, Single<R>> {
    }

    public Single(final OnSubscribe<T> onSubscribe) {
        this.onSubscribe = new Observable.OnSubscribe<T>() { // from class: rx.Single.1
            {
                Single.this = this;
            }

            @Override // rx.functions.Action1
            public /* bridge */ /* synthetic */ void call(Object obj) {
                call((Subscriber) ((Subscriber) obj));
            }

            public void call(final Subscriber<? super T> subscriber) {
                final SingleDelayedProducer singleDelayedProducer = new SingleDelayedProducer(subscriber);
                subscriber.setProducer(singleDelayedProducer);
                SingleSubscriber<T> singleSubscriber = new SingleSubscriber<T>() { // from class: rx.Single.1.1
                    {
                        AnonymousClass1.this = this;
                    }

                    @Override // rx.SingleSubscriber
                    public void onError(Throwable th) {
                        subscriber.onError(th);
                    }

                    @Override // rx.SingleSubscriber
                    public void onSuccess(T t) {
                        singleDelayedProducer.setValue(t);
                    }
                };
                subscriber.add(singleSubscriber);
                onSubscribe.call(singleSubscriber);
            }
        };
    }

    private static <T> Observable<T> asObservable(Single<T> single) {
        return Observable.create(single.onSubscribe);
    }

    public static <T> Observable<T> concat(Single<? extends T> single, Single<? extends T> single2) {
        return Observable.concat(asObservable(single), asObservable(single2));
    }

    public static <T> Single<T> create(OnSubscribe<T> onSubscribe) {
        return new Single<>(hook.onCreate(onSubscribe));
    }

    @Experimental
    public static <T> Single<T> defer(final Callable<Single<T>> callable) {
        return create(new OnSubscribe<T>() { // from class: rx.Single.25
            @Override // rx.functions.Action1
            public /* bridge */ /* synthetic */ void call(Object obj) {
                call((SingleSubscriber) ((SingleSubscriber) obj));
            }

            public void call(SingleSubscriber<? super T> singleSubscriber) {
                try {
                    ((Single) callable.call()).subscribe(singleSubscriber);
                } catch (Throwable th) {
                    Exceptions.throwIfFatal(th);
                    singleSubscriber.onError(th);
                }
            }
        });
    }

    public static <T> Single<T> error(final Throwable th) {
        return create(new OnSubscribe<T>() { // from class: rx.Single.3
            @Override // rx.functions.Action1
            public /* bridge */ /* synthetic */ void call(Object obj) {
                call((SingleSubscriber) ((SingleSubscriber) obj));
            }

            public void call(SingleSubscriber<? super T> singleSubscriber) {
                singleSubscriber.onError(th);
            }
        });
    }

    public static <T> Single<T> from(Future<? extends T> future) {
        return new Single<>(OnSubscribeToObservableFuture.toObservableFuture(future));
    }

    @Beta
    public static <T> Single<T> fromCallable(final Callable<? extends T> callable) {
        return create(new OnSubscribe<T>() { // from class: rx.Single.4
            @Override // rx.functions.Action1
            public /* bridge */ /* synthetic */ void call(Object obj) {
                call((SingleSubscriber) ((SingleSubscriber) obj));
            }

            public void call(SingleSubscriber<? super T> singleSubscriber) {
                try {
                    singleSubscriber.onSuccess((Object) callable.call());
                } catch (Throwable th) {
                    Exceptions.throwIfFatal(th);
                    singleSubscriber.onError(th);
                }
            }
        });
    }

    static <T> Single<? extends T>[] iterableToArray(Iterable<? extends Single<? extends T>> iterable) {
        if (iterable instanceof Collection) {
            Collection collection = (Collection) iterable;
            return (Single[]) collection.toArray(new Single[collection.size()]);
        }
        Single<? extends T>[] singleArr = new Single[8];
        int i2 = 0;
        for (Single<? extends T> single : iterable) {
            if (i2 == singleArr.length) {
                Single<? extends T>[] singleArr2 = new Single[(i2 >> 2) + i2];
                System.arraycopy(singleArr, 0, singleArr2, 0, i2);
                singleArr = singleArr2;
            }
            singleArr[i2] = single;
            i2++;
        }
        if (singleArr.length == i2) {
            return singleArr;
        }
        Single<? extends T>[] singleArr3 = new Single[i2];
        System.arraycopy(singleArr, 0, singleArr3, 0, i2);
        return singleArr3;
    }

    public static <T> Single<T> just(T t) {
        return ScalarSynchronousSingle.create(t);
    }

    public static <T> Single<T> merge(Single<? extends Single<? extends T>> single) {
        if (single instanceof ScalarSynchronousSingle) {
            return ((ScalarSynchronousSingle) single).scalarFlatMap(UtilityFunctions.identity());
        }
        return create(new OnSubscribe<T>() { // from class: rx.Single.5
            {
                Single.this = single;
            }

            @Override // rx.functions.Action1
            public /* bridge */ /* synthetic */ void call(Object obj) {
                call((SingleSubscriber) ((SingleSubscriber) obj));
            }

            public void call(final SingleSubscriber<? super T> singleSubscriber) {
                SingleSubscriber<Single<? extends T>> singleSubscriber2 = new SingleSubscriber<Single<? extends T>>() { // from class: rx.Single.5.1
                    {
                        AnonymousClass5.this = this;
                    }

                    @Override // rx.SingleSubscriber
                    public void onError(Throwable th) {
                        singleSubscriber.onError(th);
                    }

                    @Override // rx.SingleSubscriber
                    public /* bridge */ /* synthetic */ void onSuccess(Object obj) {
                        onSuccess((Single) ((Single) obj));
                    }

                    public void onSuccess(Single<? extends T> single2) {
                        single2.subscribe(singleSubscriber);
                    }
                };
                singleSubscriber.add(singleSubscriber2);
                Single.this.subscribe(singleSubscriber2);
            }
        });
    }

    private Single<Observable<T>> nest() {
        return just(asObservable(this));
    }

    @Experimental
    public static <T, Resource> Single<T> using(Func0<Resource> func0, Func1<? super Resource, ? extends Single<? extends T>> func1, Action1<? super Resource> action1) {
        return using(func0, func1, action1, false);
    }

    public static <T1, T2, R> Single<R> zip(Single<? extends T1> single, Single<? extends T2> single2, final Func2<? super T1, ? super T2, ? extends R> func2) {
        return SingleOperatorZip.zip(new Single[]{single, single2}, new FuncN<R>() { // from class: rx.Single.6
            @Override // rx.functions.FuncN
            public R call(Object... objArr) {
                return (R) func2.call(objArr[0], objArr[1]);
            }
        });
    }

    public <R> Single<R> compose(Transformer<? super T, ? extends R> transformer) {
        return (Single) transformer.call(this);
    }

    public final Observable<T> concatWith(Single<? extends T> single) {
        return concat(this, single);
    }

    @Experimental
    public final Single<T> delay(long j2, TimeUnit timeUnit, Scheduler scheduler) {
        return (Single<T>) lift(new OperatorDelay(j2, timeUnit, scheduler));
    }

    @Experimental
    public final Single<T> delaySubscription(Observable<?> observable) {
        observable.getClass();
        return create(new SingleOnSubscribeDelaySubscriptionOther(this, observable));
    }

    @Experimental
    public final Single<T> doAfterTerminate(Action0 action0) {
        return create(new SingleDoAfterTerminate(this, action0));
    }

    @Experimental
    public final Single<T> doOnError(final Action1<Throwable> action1) {
        return (Single<T>) lift(new OperatorDoOnEach(new Observer<T>() { // from class: rx.Single.23
            {
                Single.this = this;
            }

            @Override // rx.Observer
            public void onCompleted() {
            }

            @Override // rx.Observer
            public void onError(Throwable th) {
                action1.call(th);
            }

            @Override // rx.Observer
            public void onNext(T t) {
            }
        }));
    }

    @Experimental
    public final Single<T> doOnSubscribe(Action0 action0) {
        return (Single<T>) lift(new OperatorDoOnSubscribe(action0));
    }

    @Experimental
    public final Single<T> doOnSuccess(final Action1<? super T> action1) {
        return (Single<T>) lift(new OperatorDoOnEach(new Observer<T>() { // from class: rx.Single.24
            {
                Single.this = this;
            }

            @Override // rx.Observer
            public void onCompleted() {
            }

            @Override // rx.Observer
            public void onError(Throwable th) {
            }

            @Override // rx.Observer
            public void onNext(T t) {
                action1.call(t);
            }
        }));
    }

    @Experimental
    public final Single<T> doOnUnsubscribe(Action0 action0) {
        return (Single<T>) lift(new OperatorDoOnUnsubscribe(action0));
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final <R> Single<R> flatMap(Func1<? super T, ? extends Single<? extends R>> func1) {
        if (this instanceof ScalarSynchronousSingle) {
            return ((ScalarSynchronousSingle) this).scalarFlatMap(func1);
        }
        return merge(map(func1));
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final <R> Observable<R> flatMapObservable(Func1<? super T, ? extends Observable<? extends R>> func1) {
        return Observable.merge(asObservable(map(func1)));
    }

    @Experimental
    public final <R> Single<R> lift(final Observable.Operator<? extends R, ? super T> operator) {
        return new Single<>(new Observable.OnSubscribe<R>() { // from class: rx.Single.2
            {
                Single.this = this;
            }

            @Override // rx.functions.Action1
            public /* bridge */ /* synthetic */ void call(Object obj) {
                call((Subscriber) ((Subscriber) obj));
            }

            public void call(Subscriber<? super R> subscriber) {
                try {
                    Subscriber subscriber2 = (Subscriber) Single.hook.onLift(operator).call(subscriber);
                    subscriber2.onStart();
                    Single.this.onSubscribe.call(subscriber2);
                } catch (Throwable th) {
                    Exceptions.throwOrReport(th, subscriber);
                }
            }
        });
    }

    public final <R> Single<R> map(Func1<? super T, ? extends R> func1) {
        return lift(new OperatorMap(func1));
    }

    public final Observable<T> mergeWith(Single<? extends T> single) {
        return merge(this, single);
    }

    public final Single<T> observeOn(Scheduler scheduler) {
        if (this instanceof ScalarSynchronousSingle) {
            return ((ScalarSynchronousSingle) this).scalarScheduleOn(scheduler);
        }
        return (Single<T>) lift(new OperatorObserveOn(scheduler, false));
    }

    @Experimental
    public final Single<T> onErrorResumeNext(Single<? extends T> single) {
        return new Single<>(SingleOperatorOnErrorResumeNext.withOther(this, single));
    }

    public final Single<T> onErrorReturn(Func1<Throwable, ? extends T> func1) {
        return (Single<T>) lift(OperatorOnErrorResumeNextViaFunction.withSingle(func1));
    }

    public final Single<T> retry() {
        return toObservable().retry().toSingle();
    }

    public final Single<T> retryWhen(Func1<Observable<? extends Throwable>, ? extends Observable<?>> func1) {
        return toObservable().retryWhen(func1).toSingle();
    }

    public final Subscription subscribe() {
        return subscribe((Subscriber) new Subscriber<T>() { // from class: rx.Single.14
            {
                Single.this = this;
            }

            @Override // rx.Observer
            public final void onCompleted() {
            }

            @Override // rx.Observer
            public final void onError(Throwable th) {
                throw new OnErrorNotImplementedException(th);
            }

            @Override // rx.Observer
            public final void onNext(T t) {
            }
        });
    }

    public final Single<T> subscribeOn(final Scheduler scheduler) {
        if (this instanceof ScalarSynchronousSingle) {
            return ((ScalarSynchronousSingle) this).scalarScheduleOn(scheduler);
        }
        return create(new OnSubscribe<T>() { // from class: rx.Single.19
            {
                Single.this = this;
            }

            @Override // rx.functions.Action1
            public /* bridge */ /* synthetic */ void call(Object obj) {
                call((SingleSubscriber) ((SingleSubscriber) obj));
            }

            public void call(final SingleSubscriber<? super T> singleSubscriber) {
                final Scheduler.Worker createWorker = scheduler.createWorker();
                singleSubscriber.add(createWorker);
                createWorker.schedule(new Action0() { // from class: rx.Single.19.1
                    {
                        AnonymousClass19.this = this;
                    }

                    @Override // rx.functions.Action0
                    public void call() {
                        SingleSubscriber<T> singleSubscriber2 = new SingleSubscriber<T>() { // from class: rx.Single.19.1.1
                            {
                                AnonymousClass1.this = this;
                            }

                            @Override // rx.SingleSubscriber
                            public void onError(Throwable th) {
                                try {
                                    singleSubscriber.onError(th);
                                } finally {
                                    createWorker.unsubscribe();
                                }
                            }

                            @Override // rx.SingleSubscriber
                            public void onSuccess(T t) {
                                try {
                                    singleSubscriber.onSuccess(t);
                                } finally {
                                    createWorker.unsubscribe();
                                }
                            }
                        };
                        singleSubscriber.add(singleSubscriber2);
                        Single.this.subscribe(singleSubscriber2);
                    }
                });
            }
        });
    }

    public final Single<T> takeUntil(final Completable completable) {
        return (Single<T>) lift((Observable.Operator<T, T>) new Observable.Operator<T, T>() { // from class: rx.Single.20
            {
                Single.this = this;
            }

            @Override // rx.functions.Func1
            public /* bridge */ /* synthetic */ Object call(Object obj) {
                return call((Subscriber) ((Subscriber) obj));
            }

            public Subscriber<? super T> call(Subscriber<? super T> subscriber) {
                final SerializedSubscriber serializedSubscriber = new SerializedSubscriber(subscriber, false);
                final Subscriber subscriber2 = (Subscriber<T>) new Subscriber<T>(serializedSubscriber, false) { // from class: rx.Single.20.1
                    {
                        AnonymousClass20.this = this;
                    }

                    @Override // rx.Observer
                    public void onCompleted() {
                        try {
                            serializedSubscriber.onCompleted();
                        } finally {
                            serializedSubscriber.unsubscribe();
                        }
                    }

                    @Override // rx.Observer
                    public void onError(Throwable th) {
                        try {
                            serializedSubscriber.onError(th);
                        } finally {
                            serializedSubscriber.unsubscribe();
                        }
                    }

                    @Override // rx.Observer
                    public void onNext(T t) {
                        serializedSubscriber.onNext(t);
                    }
                };
                Completable.CompletableSubscriber completableSubscriber = new Completable.CompletableSubscriber() { // from class: rx.Single.20.2
                    {
                        AnonymousClass20.this = this;
                    }

                    @Override // rx.Completable.CompletableSubscriber
                    public void onCompleted() {
                        onError(new CancellationException("Stream was canceled before emitting a terminal event."));
                    }

                    @Override // rx.Completable.CompletableSubscriber
                    public void onError(Throwable th) {
                        subscriber2.onError(th);
                    }

                    @Override // rx.Completable.CompletableSubscriber
                    public void onSubscribe(Subscription subscription) {
                        serializedSubscriber.add(subscription);
                    }
                };
                serializedSubscriber.add(subscriber2);
                subscriber.add(serializedSubscriber);
                completable.unsafeSubscribe(completableSubscriber);
                return subscriber2;
            }
        });
    }

    public final Single<T> timeout(long j2, TimeUnit timeUnit) {
        return timeout(j2, timeUnit, null, Schedulers.computation());
    }

    @Experimental
    public final BlockingSingle<T> toBlocking() {
        return BlockingSingle.from(this);
    }

    @Experimental
    public final Completable toCompletable() {
        return Completable.fromSingle(this);
    }

    public final Observable<T> toObservable() {
        return asObservable(this);
    }

    public final Subscription unsafeSubscribe(Subscriber<? super T> subscriber) {
        try {
            subscriber.onStart();
            hook.onSubscribeStart(this, this.onSubscribe).call(subscriber);
            return hook.onSubscribeReturn(subscriber);
        } catch (Throwable th) {
            Exceptions.throwIfFatal(th);
            try {
                subscriber.onError(hook.onSubscribeError(th));
                return Subscriptions.unsubscribed();
            } catch (Throwable th2) {
                Exceptions.throwIfFatal(th2);
                RuntimeException runtimeException = new RuntimeException("Error occurred attempting to subscribe [" + th.getMessage() + "] and then again while trying to pass to onError.", th2);
                hook.onSubscribeError(runtimeException);
                throw runtimeException;
            }
        }
    }

    public final <T2, R> Single<R> zipWith(Single<? extends T2> single, Func2<? super T, ? super T2, ? extends R> func2) {
        return zip(this, single, func2);
    }

    public static <T> Observable<T> concat(Single<? extends T> single, Single<? extends T> single2, Single<? extends T> single3) {
        return Observable.concat(asObservable(single), asObservable(single2), asObservable(single3));
    }

    public static <T> Single<T> from(Future<? extends T> future, long j2, TimeUnit timeUnit) {
        return new Single<>(OnSubscribeToObservableFuture.toObservableFuture(future, j2, timeUnit));
    }

    @Experimental
    public static <T, Resource> Single<T> using(Func0<Resource> func0, Func1<? super Resource, ? extends Single<? extends T>> func1, Action1<? super Resource> action1, boolean z) {
        if (func0 != null) {
            if (func1 != null) {
                if (action1 != null) {
                    return create(new SingleOnSubscribeUsing(func0, func1, action1, z));
                }
                throw new NullPointerException("disposeAction is null");
            }
            throw new NullPointerException("singleFactory is null");
        }
        throw new NullPointerException("resourceFactory is null");
    }

    public static <T1, T2, T3, R> Single<R> zip(Single<? extends T1> single, Single<? extends T2> single2, Single<? extends T3> single3, final Func3<? super T1, ? super T2, ? super T3, ? extends R> func3) {
        return SingleOperatorZip.zip(new Single[]{single, single2, single3}, new FuncN<R>() { // from class: rx.Single.7
            @Override // rx.functions.FuncN
            public R call(Object... objArr) {
                return (R) func3.call(objArr[0], objArr[1], objArr[2]);
            }
        });
    }

    @Experimental
    public final Single<T> delay(long j2, TimeUnit timeUnit) {
        return delay(j2, timeUnit, Schedulers.computation());
    }

    @Experimental
    public final Single<T> onErrorResumeNext(Func1<Throwable, ? extends Single<? extends T>> func1) {
        return new Single<>(SingleOperatorOnErrorResumeNext.withFunction(this, func1));
    }

    public final Single<T> retry(long j2) {
        return toObservable().retry(j2).toSingle();
    }

    public final Subscription subscribe(final Action1<? super T> action1) {
        if (action1 != null) {
            return subscribe((Subscriber) new Subscriber<T>() { // from class: rx.Single.15
                {
                    Single.this = this;
                }

                @Override // rx.Observer
                public final void onCompleted() {
                }

                @Override // rx.Observer
                public final void onError(Throwable th) {
                    throw new OnErrorNotImplementedException(th);
                }

                @Override // rx.Observer
                public final void onNext(T t) {
                    action1.call(t);
                }
            });
        }
        throw new IllegalArgumentException("onSuccess can not be null");
    }

    public final <E> Single<T> takeUntil(final Observable<? extends E> observable) {
        return (Single<T>) lift((Observable.Operator<T, T>) new Observable.Operator<T, T>() { // from class: rx.Single.21
            {
                Single.this = this;
            }

            @Override // rx.functions.Func1
            public /* bridge */ /* synthetic */ Object call(Object obj) {
                return call((Subscriber) ((Subscriber) obj));
            }

            /* JADX WARN: Multi-variable type inference failed */
            public Subscriber<? super T> call(Subscriber<? super T> subscriber) {
                final SerializedSubscriber serializedSubscriber = new SerializedSubscriber(subscriber, false);
                final Subscriber subscriber2 = (Subscriber<T>) new Subscriber<T>(serializedSubscriber, false) { // from class: rx.Single.21.1
                    {
                        AnonymousClass21.this = this;
                    }

                    @Override // rx.Observer
                    public void onCompleted() {
                        try {
                            serializedSubscriber.onCompleted();
                        } finally {
                            serializedSubscriber.unsubscribe();
                        }
                    }

                    @Override // rx.Observer
                    public void onError(Throwable th) {
                        try {
                            serializedSubscriber.onError(th);
                        } finally {
                            serializedSubscriber.unsubscribe();
                        }
                    }

                    @Override // rx.Observer
                    public void onNext(T t) {
                        serializedSubscriber.onNext(t);
                    }
                };
                Subscription subscription = new Subscriber<E>() { // from class: rx.Single.21.2
                    {
                        AnonymousClass21.this = this;
                    }

                    @Override // rx.Observer
                    public void onCompleted() {
                        onError(new CancellationException("Stream was canceled before emitting a terminal event."));
                    }

                    @Override // rx.Observer
                    public void onError(Throwable th) {
                        subscriber2.onError(th);
                    }

                    @Override // rx.Observer
                    public void onNext(E e2) {
                        onError(new CancellationException("Stream was canceled before emitting a terminal event."));
                    }
                };
                serializedSubscriber.add(subscriber2);
                serializedSubscriber.add(subscription);
                subscriber.add(serializedSubscriber);
                observable.unsafeSubscribe(subscription);
                return subscriber2;
            }
        });
    }

    public final Single<T> timeout(long j2, TimeUnit timeUnit, Scheduler scheduler) {
        return timeout(j2, timeUnit, null, scheduler);
    }

    private Single(Observable.OnSubscribe<T> onSubscribe) {
        this.onSubscribe = onSubscribe;
    }

    public static <T> Observable<T> concat(Single<? extends T> single, Single<? extends T> single2, Single<? extends T> single3, Single<? extends T> single4) {
        return Observable.concat(asObservable(single), asObservable(single2), asObservable(single3), asObservable(single4));
    }

    public static <T> Single<T> from(Future<? extends T> future, Scheduler scheduler) {
        return new Single(OnSubscribeToObservableFuture.toObservableFuture(future)).subscribeOn(scheduler);
    }

    public static <T1, T2, T3, T4, R> Single<R> zip(Single<? extends T1> single, Single<? extends T2> single2, Single<? extends T3> single3, Single<? extends T4> single4, final Func4<? super T1, ? super T2, ? super T3, ? super T4, ? extends R> func4) {
        return SingleOperatorZip.zip(new Single[]{single, single2, single3, single4}, new FuncN<R>() { // from class: rx.Single.8
            @Override // rx.functions.FuncN
            public R call(Object... objArr) {
                return (R) func4.call(objArr[0], objArr[1], objArr[2], objArr[3]);
            }
        });
    }

    public final Single<T> retry(Func2<Integer, Throwable, Boolean> func2) {
        return toObservable().retry(func2).toSingle();
    }

    public final <E> Single<T> takeUntil(final Single<? extends E> single) {
        return (Single<T>) lift((Observable.Operator<T, T>) new Observable.Operator<T, T>() { // from class: rx.Single.22
            {
                Single.this = this;
            }

            @Override // rx.functions.Func1
            public /* bridge */ /* synthetic */ Object call(Object obj) {
                return call((Subscriber) ((Subscriber) obj));
            }

            /* JADX WARN: Multi-variable type inference failed */
            public Subscriber<? super T> call(Subscriber<? super T> subscriber) {
                final SerializedSubscriber serializedSubscriber = new SerializedSubscriber(subscriber, false);
                final Subscriber subscriber2 = (Subscriber<T>) new Subscriber<T>(serializedSubscriber, false) { // from class: rx.Single.22.1
                    {
                        AnonymousClass22.this = this;
                    }

                    @Override // rx.Observer
                    public void onCompleted() {
                        try {
                            serializedSubscriber.onCompleted();
                        } finally {
                            serializedSubscriber.unsubscribe();
                        }
                    }

                    @Override // rx.Observer
                    public void onError(Throwable th) {
                        try {
                            serializedSubscriber.onError(th);
                        } finally {
                            serializedSubscriber.unsubscribe();
                        }
                    }

                    @Override // rx.Observer
                    public void onNext(T t) {
                        serializedSubscriber.onNext(t);
                    }
                };
                Subscription subscription = new SingleSubscriber<E>() { // from class: rx.Single.22.2
                    {
                        AnonymousClass22.this = this;
                    }

                    @Override // rx.SingleSubscriber
                    public void onError(Throwable th) {
                        subscriber2.onError(th);
                    }

                    @Override // rx.SingleSubscriber
                    public void onSuccess(E e2) {
                        onError(new CancellationException("Stream was canceled before emitting a terminal event."));
                    }
                };
                serializedSubscriber.add(subscriber2);
                serializedSubscriber.add(subscription);
                subscriber.add(serializedSubscriber);
                single.subscribe((SingleSubscriber) subscription);
                return subscriber2;
            }
        });
    }

    public final Single<T> timeout(long j2, TimeUnit timeUnit, Single<? extends T> single) {
        return timeout(j2, timeUnit, single, Schedulers.computation());
    }

    public static <T> Observable<T> concat(Single<? extends T> single, Single<? extends T> single2, Single<? extends T> single3, Single<? extends T> single4, Single<? extends T> single5) {
        return Observable.concat(asObservable(single), asObservable(single2), asObservable(single3), asObservable(single4), asObservable(single5));
    }

    public static <T> Observable<T> merge(Single<? extends T> single, Single<? extends T> single2) {
        return Observable.merge(asObservable(single), asObservable(single2));
    }

    public static <T1, T2, T3, T4, T5, R> Single<R> zip(Single<? extends T1> single, Single<? extends T2> single2, Single<? extends T3> single3, Single<? extends T4> single4, Single<? extends T5> single5, final Func5<? super T1, ? super T2, ? super T3, ? super T4, ? super T5, ? extends R> func5) {
        return SingleOperatorZip.zip(new Single[]{single, single2, single3, single4, single5}, new FuncN<R>() { // from class: rx.Single.9
            @Override // rx.functions.FuncN
            public R call(Object... objArr) {
                return (R) func5.call(objArr[0], objArr[1], objArr[2], objArr[3], objArr[4]);
            }
        });
    }

    public final Subscription subscribe(final Action1<? super T> action1, final Action1<Throwable> action12) {
        if (action1 != null) {
            if (action12 != null) {
                return subscribe((Subscriber) new Subscriber<T>() { // from class: rx.Single.16
                    {
                        Single.this = this;
                    }

                    @Override // rx.Observer
                    public final void onCompleted() {
                    }

                    @Override // rx.Observer
                    public final void onError(Throwable th) {
                        action12.call(th);
                    }

                    @Override // rx.Observer
                    public final void onNext(T t) {
                        action1.call(t);
                    }
                });
            }
            throw new IllegalArgumentException("onError can not be null");
        }
        throw new IllegalArgumentException("onSuccess can not be null");
    }

    public final Single<T> timeout(long j2, TimeUnit timeUnit, Single<? extends T> single, Scheduler scheduler) {
        if (single == null) {
            single = error(new TimeoutException());
        }
        return (Single<T>) lift(new OperatorTimeout(j2, timeUnit, asObservable(single), scheduler));
    }

    public static <T> Observable<T> concat(Single<? extends T> single, Single<? extends T> single2, Single<? extends T> single3, Single<? extends T> single4, Single<? extends T> single5, Single<? extends T> single6) {
        return Observable.concat(asObservable(single), asObservable(single2), asObservable(single3), asObservable(single4), asObservable(single5), asObservable(single6));
    }

    public static <T> Observable<T> merge(Single<? extends T> single, Single<? extends T> single2, Single<? extends T> single3) {
        return Observable.merge(asObservable(single), asObservable(single2), asObservable(single3));
    }

    public static <T1, T2, T3, T4, T5, T6, R> Single<R> zip(Single<? extends T1> single, Single<? extends T2> single2, Single<? extends T3> single3, Single<? extends T4> single4, Single<? extends T5> single5, Single<? extends T6> single6, final Func6<? super T1, ? super T2, ? super T3, ? super T4, ? super T5, ? super T6, ? extends R> func6) {
        return SingleOperatorZip.zip(new Single[]{single, single2, single3, single4, single5, single6}, new FuncN<R>() { // from class: rx.Single.10
            @Override // rx.functions.FuncN
            public R call(Object... objArr) {
                return (R) func6.call(objArr[0], objArr[1], objArr[2], objArr[3], objArr[4], objArr[5]);
            }
        });
    }

    public static <T> Observable<T> concat(Single<? extends T> single, Single<? extends T> single2, Single<? extends T> single3, Single<? extends T> single4, Single<? extends T> single5, Single<? extends T> single6, Single<? extends T> single7) {
        return Observable.concat(asObservable(single), asObservable(single2), asObservable(single3), asObservable(single4), asObservable(single5), asObservable(single6), asObservable(single7));
    }

    public static <T> Observable<T> merge(Single<? extends T> single, Single<? extends T> single2, Single<? extends T> single3, Single<? extends T> single4) {
        return Observable.merge(asObservable(single), asObservable(single2), asObservable(single3), asObservable(single4));
    }

    public static <T1, T2, T3, T4, T5, T6, T7, R> Single<R> zip(Single<? extends T1> single, Single<? extends T2> single2, Single<? extends T3> single3, Single<? extends T4> single4, Single<? extends T5> single5, Single<? extends T6> single6, Single<? extends T7> single7, final Func7<? super T1, ? super T2, ? super T3, ? super T4, ? super T5, ? super T6, ? super T7, ? extends R> func7) {
        return SingleOperatorZip.zip(new Single[]{single, single2, single3, single4, single5, single6, single7}, new FuncN<R>() { // from class: rx.Single.11
            @Override // rx.functions.FuncN
            public R call(Object... objArr) {
                return (R) func7.call(objArr[0], objArr[1], objArr[2], objArr[3], objArr[4], objArr[5], objArr[6]);
            }
        });
    }

    public static <T> Observable<T> concat(Single<? extends T> single, Single<? extends T> single2, Single<? extends T> single3, Single<? extends T> single4, Single<? extends T> single5, Single<? extends T> single6, Single<? extends T> single7, Single<? extends T> single8) {
        return Observable.concat(asObservable(single), asObservable(single2), asObservable(single3), asObservable(single4), asObservable(single5), asObservable(single6), asObservable(single7), asObservable(single8));
    }

    public static <T> Observable<T> merge(Single<? extends T> single, Single<? extends T> single2, Single<? extends T> single3, Single<? extends T> single4, Single<? extends T> single5) {
        return Observable.merge(asObservable(single), asObservable(single2), asObservable(single3), asObservable(single4), asObservable(single5));
    }

    public static <T1, T2, T3, T4, T5, T6, T7, T8, R> Single<R> zip(Single<? extends T1> single, Single<? extends T2> single2, Single<? extends T3> single3, Single<? extends T4> single4, Single<? extends T5> single5, Single<? extends T6> single6, Single<? extends T7> single7, Single<? extends T8> single8, final Func8<? super T1, ? super T2, ? super T3, ? super T4, ? super T5, ? super T6, ? super T7, ? super T8, ? extends R> func8) {
        return SingleOperatorZip.zip(new Single[]{single, single2, single3, single4, single5, single6, single7, single8}, new FuncN<R>() { // from class: rx.Single.12
            @Override // rx.functions.FuncN
            public R call(Object... objArr) {
                return (R) func8.call(objArr[0], objArr[1], objArr[2], objArr[3], objArr[4], objArr[5], objArr[6], objArr[7]);
            }
        });
    }

    public final Subscription subscribe(final Observer<? super T> observer) {
        if (observer != null) {
            return subscribe(new SingleSubscriber<T>() { // from class: rx.Single.17
                {
                    Single.this = this;
                }

                @Override // rx.SingleSubscriber
                public void onError(Throwable th) {
                    observer.onError(th);
                }

                @Override // rx.SingleSubscriber
                public void onSuccess(T t) {
                    observer.onNext(t);
                    observer.onCompleted();
                }
            });
        }
        throw new NullPointerException("observer is null");
    }

    public static <T> Observable<T> concat(Single<? extends T> single, Single<? extends T> single2, Single<? extends T> single3, Single<? extends T> single4, Single<? extends T> single5, Single<? extends T> single6, Single<? extends T> single7, Single<? extends T> single8, Single<? extends T> single9) {
        return Observable.concat(asObservable(single), asObservable(single2), asObservable(single3), asObservable(single4), asObservable(single5), asObservable(single6), asObservable(single7), asObservable(single8), asObservable(single9));
    }

    public static <T> Observable<T> merge(Single<? extends T> single, Single<? extends T> single2, Single<? extends T> single3, Single<? extends T> single4, Single<? extends T> single5, Single<? extends T> single6) {
        return Observable.merge(asObservable(single), asObservable(single2), asObservable(single3), asObservable(single4), asObservable(single5), asObservable(single6));
    }

    public static <T1, T2, T3, T4, T5, T6, T7, T8, T9, R> Single<R> zip(Single<? extends T1> single, Single<? extends T2> single2, Single<? extends T3> single3, Single<? extends T4> single4, Single<? extends T5> single5, Single<? extends T6> single6, Single<? extends T7> single7, Single<? extends T8> single8, Single<? extends T9> single9, final Func9<? super T1, ? super T2, ? super T3, ? super T4, ? super T5, ? super T6, ? super T7, ? super T8, ? super T9, ? extends R> func9) {
        return SingleOperatorZip.zip(new Single[]{single, single2, single3, single4, single5, single6, single7, single8, single9}, new FuncN<R>() { // from class: rx.Single.13
            @Override // rx.functions.FuncN
            public R call(Object... objArr) {
                return (R) func9.call(objArr[0], objArr[1], objArr[2], objArr[3], objArr[4], objArr[5], objArr[6], objArr[7], objArr[8]);
            }
        });
    }

    public static <T> Observable<T> merge(Single<? extends T> single, Single<? extends T> single2, Single<? extends T> single3, Single<? extends T> single4, Single<? extends T> single5, Single<? extends T> single6, Single<? extends T> single7) {
        return Observable.merge(asObservable(single), asObservable(single2), asObservable(single3), asObservable(single4), asObservable(single5), asObservable(single6), asObservable(single7));
    }

    public static <R> Single<R> zip(Iterable<? extends Single<?>> iterable, FuncN<? extends R> funcN) {
        return SingleOperatorZip.zip(iterableToArray(iterable), funcN);
    }

    public final Subscription subscribe(Subscriber<? super T> subscriber) {
        if (subscriber != null) {
            if (this.onSubscribe != null) {
                subscriber.onStart();
                if (!(subscriber instanceof SafeSubscriber)) {
                    subscriber = new SafeSubscriber(subscriber);
                }
                try {
                    hook.onSubscribeStart(this, this.onSubscribe).call(subscriber);
                    return hook.onSubscribeReturn(subscriber);
                } catch (Throwable th) {
                    Exceptions.throwIfFatal(th);
                    try {
                        subscriber.onError(hook.onSubscribeError(th));
                        return Subscriptions.empty();
                    } catch (Throwable th2) {
                        Exceptions.throwIfFatal(th2);
                        RuntimeException runtimeException = new RuntimeException("Error occurred attempting to subscribe [" + th.getMessage() + "] and then again while trying to pass to onError.", th2);
                        hook.onSubscribeError(runtimeException);
                        throw runtimeException;
                    }
                }
            }
            throw new IllegalStateException("onSubscribe function can not be null.");
        }
        throw new IllegalArgumentException("observer can not be null");
    }

    public static <T> Observable<T> merge(Single<? extends T> single, Single<? extends T> single2, Single<? extends T> single3, Single<? extends T> single4, Single<? extends T> single5, Single<? extends T> single6, Single<? extends T> single7, Single<? extends T> single8) {
        return Observable.merge(asObservable(single), asObservable(single2), asObservable(single3), asObservable(single4), asObservable(single5), asObservable(single6), asObservable(single7), asObservable(single8));
    }

    public static <T> Observable<T> merge(Single<? extends T> single, Single<? extends T> single2, Single<? extends T> single3, Single<? extends T> single4, Single<? extends T> single5, Single<? extends T> single6, Single<? extends T> single7, Single<? extends T> single8, Single<? extends T> single9) {
        return Observable.merge(asObservable(single), asObservable(single2), asObservable(single3), asObservable(single4), asObservable(single5), asObservable(single6), asObservable(single7), asObservable(single8), asObservable(single9));
    }

    public final Subscription subscribe(final SingleSubscriber<? super T> singleSubscriber) {
        Subscriber<T> subscriber = new Subscriber<T>() { // from class: rx.Single.18
            {
                Single.this = this;
            }

            @Override // rx.Observer
            public void onCompleted() {
            }

            @Override // rx.Observer
            public void onError(Throwable th) {
                singleSubscriber.onError(th);
            }

            @Override // rx.Observer
            public void onNext(T t) {
                singleSubscriber.onSuccess(t);
            }
        };
        singleSubscriber.add(subscriber);
        subscribe((Subscriber) subscriber);
        return subscriber;
    }
}

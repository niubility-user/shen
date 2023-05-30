package rx.internal.operators;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.Subscription;
import rx.exceptions.Exceptions;
import rx.functions.Func1;
import rx.functions.Func2;
import rx.observers.SerializedObserver;
import rx.observers.SerializedSubscriber;
import rx.subjects.PublishSubject;
import rx.subscriptions.CompositeSubscription;
import rx.subscriptions.RefCountSubscription;

/* loaded from: classes11.dex */
public final class OnSubscribeGroupJoin<T1, T2, D1, D2, R> implements Observable.OnSubscribe<R> {
    protected final Observable<T1> left;
    protected final Func1<? super T1, ? extends Observable<D1>> leftDuration;
    protected final Func2<? super T1, ? super Observable<T2>, ? extends R> resultSelector;
    protected final Observable<T2> right;
    protected final Func1<? super T2, ? extends Observable<D2>> rightDuration;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes11.dex */
    public final class ResultManager implements Subscription {
        final RefCountSubscription cancel;
        final CompositeSubscription group;
        boolean leftDone;
        int leftIds;
        boolean rightDone;
        int rightIds;
        final Subscriber<? super R> subscriber;
        final Object guard = new Object();
        final Map<Integer, Observer<T2>> leftMap = new HashMap();
        final Map<Integer, T2> rightMap = new HashMap();

        /* loaded from: classes11.dex */
        final class LeftDurationObserver extends Subscriber<D1> {
            final int id;
            boolean once = true;

            public LeftDurationObserver(int i2) {
                this.id = i2;
            }

            @Override // rx.Observer
            public void onCompleted() {
                Observer<T2> remove;
                if (this.once) {
                    this.once = false;
                    synchronized (ResultManager.this.guard) {
                        remove = ResultManager.this.leftMap.remove(Integer.valueOf(this.id));
                    }
                    if (remove != null) {
                        remove.onCompleted();
                    }
                    ResultManager.this.group.remove(this);
                }
            }

            @Override // rx.Observer
            public void onError(Throwable th) {
                ResultManager.this.errorMain(th);
            }

            @Override // rx.Observer
            public void onNext(D1 d1) {
                onCompleted();
            }
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* loaded from: classes11.dex */
        public final class LeftObserver extends Subscriber<T1> {
            LeftObserver() {
            }

            @Override // rx.Observer
            public void onCompleted() {
                ArrayList arrayList;
                synchronized (ResultManager.this.guard) {
                    ResultManager resultManager = ResultManager.this;
                    resultManager.leftDone = true;
                    if (resultManager.rightDone) {
                        arrayList = new ArrayList(ResultManager.this.leftMap.values());
                        ResultManager.this.leftMap.clear();
                        ResultManager.this.rightMap.clear();
                    } else {
                        arrayList = null;
                    }
                }
                ResultManager.this.complete(arrayList);
            }

            @Override // rx.Observer
            public void onError(Throwable th) {
                ResultManager.this.errorAll(th);
            }

            @Override // rx.Observer
            public void onNext(T1 t1) {
                int i2;
                ArrayList arrayList;
                try {
                    PublishSubject create = PublishSubject.create();
                    SerializedObserver serializedObserver = new SerializedObserver(create);
                    synchronized (ResultManager.this.guard) {
                        ResultManager resultManager = ResultManager.this;
                        i2 = resultManager.leftIds;
                        resultManager.leftIds = i2 + 1;
                        resultManager.leftMap.put(Integer.valueOf(i2), serializedObserver);
                    }
                    Observable create2 = Observable.create(new WindowObservableFunc(create, ResultManager.this.cancel));
                    LeftDurationObserver leftDurationObserver = new LeftDurationObserver(i2);
                    ResultManager.this.group.add(leftDurationObserver);
                    OnSubscribeGroupJoin.this.leftDuration.call(t1).unsafeSubscribe(leftDurationObserver);
                    R call = OnSubscribeGroupJoin.this.resultSelector.call(t1, create2);
                    synchronized (ResultManager.this.guard) {
                        arrayList = new ArrayList(ResultManager.this.rightMap.values());
                    }
                    ResultManager.this.subscriber.onNext(call);
                    Iterator it = arrayList.iterator();
                    while (it.hasNext()) {
                        serializedObserver.onNext(it.next());
                    }
                } catch (Throwable th) {
                    Exceptions.throwOrReport(th, this);
                }
            }
        }

        /* loaded from: classes11.dex */
        final class RightDurationObserver extends Subscriber<D2> {
            final int id;
            boolean once = true;

            public RightDurationObserver(int i2) {
                this.id = i2;
            }

            @Override // rx.Observer
            public void onCompleted() {
                if (this.once) {
                    this.once = false;
                    synchronized (ResultManager.this.guard) {
                        ResultManager.this.rightMap.remove(Integer.valueOf(this.id));
                    }
                    ResultManager.this.group.remove(this);
                }
            }

            @Override // rx.Observer
            public void onError(Throwable th) {
                ResultManager.this.errorMain(th);
            }

            @Override // rx.Observer
            public void onNext(D2 d2) {
                onCompleted();
            }
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* loaded from: classes11.dex */
        public final class RightObserver extends Subscriber<T2> {
            RightObserver() {
            }

            @Override // rx.Observer
            public void onCompleted() {
                ArrayList arrayList;
                synchronized (ResultManager.this.guard) {
                    ResultManager resultManager = ResultManager.this;
                    resultManager.rightDone = true;
                    if (resultManager.leftDone) {
                        arrayList = new ArrayList(ResultManager.this.leftMap.values());
                        ResultManager.this.leftMap.clear();
                        ResultManager.this.rightMap.clear();
                    } else {
                        arrayList = null;
                    }
                }
                ResultManager.this.complete(arrayList);
            }

            @Override // rx.Observer
            public void onError(Throwable th) {
                ResultManager.this.errorAll(th);
            }

            @Override // rx.Observer
            public void onNext(T2 t2) {
                int i2;
                ArrayList arrayList;
                try {
                    synchronized (ResultManager.this.guard) {
                        ResultManager resultManager = ResultManager.this;
                        i2 = resultManager.rightIds;
                        resultManager.rightIds = i2 + 1;
                        resultManager.rightMap.put(Integer.valueOf(i2), t2);
                    }
                    RightDurationObserver rightDurationObserver = new RightDurationObserver(i2);
                    ResultManager.this.group.add(rightDurationObserver);
                    OnSubscribeGroupJoin.this.rightDuration.call(t2).unsafeSubscribe(rightDurationObserver);
                    synchronized (ResultManager.this.guard) {
                        arrayList = new ArrayList(ResultManager.this.leftMap.values());
                    }
                    Iterator it = arrayList.iterator();
                    while (it.hasNext()) {
                        ((Observer) it.next()).onNext(t2);
                    }
                } catch (Throwable th) {
                    Exceptions.throwOrReport(th, this);
                }
            }
        }

        public ResultManager(Subscriber<? super R> subscriber) {
            this.subscriber = subscriber;
            CompositeSubscription compositeSubscription = new CompositeSubscription();
            this.group = compositeSubscription;
            this.cancel = new RefCountSubscription(compositeSubscription);
        }

        void complete(List<Observer<T2>> list) {
            if (list != null) {
                Iterator<Observer<T2>> it = list.iterator();
                while (it.hasNext()) {
                    it.next().onCompleted();
                }
                this.subscriber.onCompleted();
                this.cancel.unsubscribe();
            }
        }

        void errorAll(Throwable th) {
            ArrayList arrayList;
            synchronized (this.guard) {
                arrayList = new ArrayList(this.leftMap.values());
                this.leftMap.clear();
                this.rightMap.clear();
            }
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                ((Observer) it.next()).onError(th);
            }
            this.subscriber.onError(th);
            this.cancel.unsubscribe();
        }

        void errorMain(Throwable th) {
            synchronized (this.guard) {
                this.leftMap.clear();
                this.rightMap.clear();
            }
            this.subscriber.onError(th);
            this.cancel.unsubscribe();
        }

        public void init() {
            LeftObserver leftObserver = new LeftObserver();
            RightObserver rightObserver = new RightObserver();
            this.group.add(leftObserver);
            this.group.add(rightObserver);
            OnSubscribeGroupJoin.this.left.unsafeSubscribe(leftObserver);
            OnSubscribeGroupJoin.this.right.unsafeSubscribe(rightObserver);
        }

        @Override // rx.Subscription
        public boolean isUnsubscribed() {
            return this.cancel.isUnsubscribed();
        }

        @Override // rx.Subscription
        public void unsubscribe() {
            this.cancel.unsubscribe();
        }
    }

    /* loaded from: classes11.dex */
    static final class WindowObservableFunc<T> implements Observable.OnSubscribe<T> {
        final RefCountSubscription refCount;
        final Observable<T> underlying;

        /* JADX INFO: Access modifiers changed from: package-private */
        /* loaded from: classes11.dex */
        public final class WindowSubscriber extends Subscriber<T> {
            private final Subscription ref;
            final Subscriber<? super T> subscriber;

            public WindowSubscriber(Subscriber<? super T> subscriber, Subscription subscription) {
                super(subscriber);
                this.subscriber = subscriber;
                this.ref = subscription;
            }

            @Override // rx.Observer
            public void onCompleted() {
                this.subscriber.onCompleted();
                this.ref.unsubscribe();
            }

            @Override // rx.Observer
            public void onError(Throwable th) {
                this.subscriber.onError(th);
                this.ref.unsubscribe();
            }

            @Override // rx.Observer
            public void onNext(T t) {
                this.subscriber.onNext(t);
            }
        }

        public WindowObservableFunc(Observable<T> observable, RefCountSubscription refCountSubscription) {
            this.refCount = refCountSubscription;
            this.underlying = observable;
        }

        @Override // rx.functions.Action1
        public /* bridge */ /* synthetic */ void call(Object obj) {
            call((Subscriber) ((Subscriber) obj));
        }

        public void call(Subscriber<? super T> subscriber) {
            Subscription subscription = this.refCount.get();
            WindowSubscriber windowSubscriber = new WindowSubscriber(subscriber, subscription);
            windowSubscriber.add(subscription);
            this.underlying.unsafeSubscribe(windowSubscriber);
        }
    }

    public OnSubscribeGroupJoin(Observable<T1> observable, Observable<T2> observable2, Func1<? super T1, ? extends Observable<D1>> func1, Func1<? super T2, ? extends Observable<D2>> func12, Func2<? super T1, ? super Observable<T2>, ? extends R> func2) {
        this.left = observable;
        this.right = observable2;
        this.leftDuration = func1;
        this.rightDuration = func12;
        this.resultSelector = func2;
    }

    @Override // rx.functions.Action1
    public /* bridge */ /* synthetic */ void call(Object obj) {
        call((Subscriber) ((Subscriber) obj));
    }

    public void call(Subscriber<? super R> subscriber) {
        ResultManager resultManager = new ResultManager(new SerializedSubscriber(subscriber));
        subscriber.add(resultManager);
        resultManager.init();
    }
}

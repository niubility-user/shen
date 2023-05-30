package rx.internal.util;

import com.jd.dynamic.DYConstants;
import java.util.concurrent.atomic.AtomicBoolean;
import rx.Observable;
import rx.Producer;
import rx.Scheduler;
import rx.Subscriber;
import rx.Subscription;
import rx.exceptions.Exceptions;
import rx.functions.Action0;
import rx.functions.Func1;
import rx.internal.producers.SingleProducer;
import rx.internal.schedulers.EventLoopsScheduler;
import rx.observers.Subscribers;
import rx.plugins.RxJavaObservableExecutionHook;
import rx.plugins.RxJavaPlugins;

/* loaded from: classes11.dex */
public final class ScalarSynchronousObservable<T> extends Observable<T> {
    final T t;
    static RxJavaObservableExecutionHook hook = RxJavaPlugins.getInstance().getObservableExecutionHook();
    static final boolean STRONG_MODE = Boolean.valueOf(System.getProperty("rx.just.strong-mode", DYConstants.DY_FALSE)).booleanValue();

    /* loaded from: classes11.dex */
    static final class JustOnSubscribe<T> implements Observable.OnSubscribe<T> {
        final T value;

        JustOnSubscribe(T t) {
            this.value = t;
        }

        @Override // rx.functions.Action1
        public /* bridge */ /* synthetic */ void call(Object obj) {
            call((Subscriber) ((Subscriber) obj));
        }

        public void call(Subscriber<? super T> subscriber) {
            subscriber.setProducer(ScalarSynchronousObservable.createProducer(subscriber, this.value));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes11.dex */
    public static final class ScalarAsyncOnSubscribe<T> implements Observable.OnSubscribe<T> {
        final Func1<Action0, Subscription> onSchedule;
        final T value;

        ScalarAsyncOnSubscribe(T t, Func1<Action0, Subscription> func1) {
            this.value = t;
            this.onSchedule = func1;
        }

        @Override // rx.functions.Action1
        public /* bridge */ /* synthetic */ void call(Object obj) {
            call((Subscriber) ((Subscriber) obj));
        }

        public void call(Subscriber<? super T> subscriber) {
            subscriber.setProducer(new ScalarAsyncProducer(subscriber, this.value, this.onSchedule));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes11.dex */
    public static final class ScalarAsyncProducer<T> extends AtomicBoolean implements Producer, Action0 {
        private static final long serialVersionUID = -2466317989629281651L;
        final Subscriber<? super T> actual;
        final Func1<Action0, Subscription> onSchedule;
        final T value;

        public ScalarAsyncProducer(Subscriber<? super T> subscriber, T t, Func1<Action0, Subscription> func1) {
            this.actual = subscriber;
            this.value = t;
            this.onSchedule = func1;
        }

        @Override // rx.functions.Action0
        public void call() {
            Subscriber<? super T> subscriber = this.actual;
            if (subscriber.isUnsubscribed()) {
                return;
            }
            Object obj = (T) this.value;
            try {
                subscriber.onNext(obj);
                if (subscriber.isUnsubscribed()) {
                    return;
                }
                subscriber.onCompleted();
            } catch (Throwable th) {
                Exceptions.throwOrReport(th, subscriber, obj);
            }
        }

        @Override // rx.Producer
        public void request(long j2) {
            if (j2 >= 0) {
                if (j2 == 0 || !compareAndSet(false, true)) {
                    return;
                }
                this.actual.add(this.onSchedule.call(this));
                return;
            }
            throw new IllegalArgumentException("n >= 0 required but it was " + j2);
        }

        @Override // java.util.concurrent.atomic.AtomicBoolean
        public String toString() {
            return "ScalarAsyncProducer[" + this.value + ", " + get() + "]";
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes11.dex */
    public static final class WeakSingleProducer<T> implements Producer {
        final Subscriber<? super T> actual;
        boolean once;
        final T value;

        public WeakSingleProducer(Subscriber<? super T> subscriber, T t) {
            this.actual = subscriber;
            this.value = t;
        }

        @Override // rx.Producer
        public void request(long j2) {
            if (this.once) {
                return;
            }
            if (j2 < 0) {
                throw new IllegalStateException("n >= required but it was " + j2);
            } else if (j2 == 0) {
            } else {
                this.once = true;
                Subscriber<? super T> subscriber = this.actual;
                if (subscriber.isUnsubscribed()) {
                    return;
                }
                Object obj = (T) this.value;
                try {
                    subscriber.onNext(obj);
                    if (subscriber.isUnsubscribed()) {
                        return;
                    }
                    subscriber.onCompleted();
                } catch (Throwable th) {
                    Exceptions.throwOrReport(th, subscriber, obj);
                }
            }
        }
    }

    protected ScalarSynchronousObservable(T t) {
        super(hook.onCreate(new JustOnSubscribe(t)));
        this.t = t;
    }

    public static <T> ScalarSynchronousObservable<T> create(T t) {
        return new ScalarSynchronousObservable<>(t);
    }

    static <T> Producer createProducer(Subscriber<? super T> subscriber, T t) {
        if (STRONG_MODE) {
            return new SingleProducer(subscriber, t);
        }
        return new WeakSingleProducer(subscriber, t);
    }

    public T get() {
        return this.t;
    }

    public <R> Observable<R> scalarFlatMap(final Func1<? super T, ? extends Observable<? extends R>> func1) {
        return Observable.create(new Observable.OnSubscribe<R>() { // from class: rx.internal.util.ScalarSynchronousObservable.3
            @Override // rx.functions.Action1
            public /* bridge */ /* synthetic */ void call(Object obj) {
                call((Subscriber) ((Subscriber) obj));
            }

            public void call(Subscriber<? super R> subscriber) {
                Observable observable = (Observable) func1.call(ScalarSynchronousObservable.this.t);
                if (observable instanceof ScalarSynchronousObservable) {
                    subscriber.setProducer(ScalarSynchronousObservable.createProducer(subscriber, ((ScalarSynchronousObservable) observable).t));
                } else {
                    observable.unsafeSubscribe(Subscribers.wrap(subscriber));
                }
            }
        });
    }

    public Observable<T> scalarScheduleOn(final Scheduler scheduler) {
        Func1<Action0, Subscription> func1;
        if (scheduler instanceof EventLoopsScheduler) {
            final EventLoopsScheduler eventLoopsScheduler = (EventLoopsScheduler) scheduler;
            func1 = new Func1<Action0, Subscription>() { // from class: rx.internal.util.ScalarSynchronousObservable.1
                @Override // rx.functions.Func1
                public Subscription call(Action0 action0) {
                    return eventLoopsScheduler.scheduleDirect(action0);
                }
            };
        } else {
            func1 = new Func1<Action0, Subscription>() { // from class: rx.internal.util.ScalarSynchronousObservable.2
                @Override // rx.functions.Func1
                public Subscription call(final Action0 action0) {
                    final Scheduler.Worker createWorker = scheduler.createWorker();
                    createWorker.schedule(new Action0() { // from class: rx.internal.util.ScalarSynchronousObservable.2.1
                        @Override // rx.functions.Action0
                        public void call() {
                            try {
                                action0.call();
                            } finally {
                                createWorker.unsubscribe();
                            }
                        }
                    });
                    return createWorker;
                }
            };
        }
        return Observable.create(new ScalarAsyncOnSubscribe(this.t, func1));
    }
}

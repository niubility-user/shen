package rx.internal.util;

import java.util.List;
import java.util.concurrent.TimeUnit;
import rx.Notification;
import rx.Observable;
import rx.Scheduler;
import rx.exceptions.OnErrorNotImplementedException;
import rx.functions.Action1;
import rx.functions.Action2;
import rx.functions.Func0;
import rx.functions.Func1;
import rx.functions.Func2;
import rx.internal.operators.OperatorAny;
import rx.observables.ConnectableObservable;

/* loaded from: classes11.dex */
public enum InternalObservableUtils {
    ;
    
    public static final PlusOneFunc2 COUNTER = new PlusOneFunc2();
    public static final PlusOneLongFunc2 LONG_COUNTER = new PlusOneLongFunc2();
    public static final ObjectEqualsFunc2 OBJECT_EQUALS = new ObjectEqualsFunc2();
    public static final ToArrayFunc1 TO_ARRAY = new ToArrayFunc1();
    static final ReturnsVoidFunc1 RETURNS_VOID = new ReturnsVoidFunc1();
    static final NotificationErrorExtractor ERROR_EXTRACTOR = new NotificationErrorExtractor();
    public static final Action1<Throwable> ERROR_NOT_IMPLEMENTED = new Action1<Throwable>() { // from class: rx.internal.util.InternalObservableUtils.ErrorNotImplementedAction
        @Override // rx.functions.Action1
        public void call(Throwable th) {
            throw new OnErrorNotImplementedException(th);
        }
    };
    public static final Observable.Operator<Boolean, Object> IS_EMPTY = new OperatorAny(UtilityFunctions.alwaysTrue(), true);

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes11.dex */
    public static final class CollectorCaller<T, R> implements Func2<R, T, R> {
        final Action2<R, ? super T> collector;

        public CollectorCaller(Action2<R, ? super T> action2) {
            this.collector = action2;
        }

        @Override // rx.functions.Func2
        public R call(R r, T t) {
            this.collector.call(r, t);
            return r;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes11.dex */
    public static final class EqualsWithFunc1 implements Func1<Object, Boolean> {
        final Object other;

        public EqualsWithFunc1(Object obj) {
            this.other = obj;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // rx.functions.Func1
        public Boolean call(Object obj) {
            Object obj2 = this.other;
            return Boolean.valueOf(obj == obj2 || (obj != null && obj.equals(obj2)));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes11.dex */
    public static final class IsInstanceOfFunc1 implements Func1<Object, Boolean> {
        final Class<?> clazz;

        public IsInstanceOfFunc1(Class<?> cls) {
            this.clazz = cls;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // rx.functions.Func1
        public Boolean call(Object obj) {
            return Boolean.valueOf(this.clazz.isInstance(obj));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes11.dex */
    public static final class NotificationErrorExtractor implements Func1<Notification<?>, Throwable> {
        NotificationErrorExtractor() {
        }

        @Override // rx.functions.Func1
        public Throwable call(Notification<?> notification) {
            return notification.getThrowable();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes11.dex */
    public static final class ObjectEqualsFunc2 implements Func2<Object, Object, Boolean> {
        ObjectEqualsFunc2() {
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // rx.functions.Func2
        public Boolean call(Object obj, Object obj2) {
            return Boolean.valueOf(obj == obj2 || (obj != null && obj.equals(obj2)));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes11.dex */
    public static final class PlusOneFunc2 implements Func2<Integer, Object, Integer> {
        PlusOneFunc2() {
        }

        @Override // rx.functions.Func2
        public Integer call(Integer num, Object obj) {
            return Integer.valueOf(num.intValue() + 1);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes11.dex */
    public static final class PlusOneLongFunc2 implements Func2<Long, Object, Long> {
        PlusOneLongFunc2() {
        }

        @Override // rx.functions.Func2
        public Long call(Long l2, Object obj) {
            return Long.valueOf(l2.longValue() + 1);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes11.dex */
    public static final class RepeatNotificationDematerializer implements Func1<Observable<? extends Notification<?>>, Observable<?>> {
        final Func1<? super Observable<? extends Void>, ? extends Observable<?>> notificationHandler;

        public RepeatNotificationDematerializer(Func1<? super Observable<? extends Void>, ? extends Observable<?>> func1) {
            this.notificationHandler = func1;
        }

        @Override // rx.functions.Func1
        public Observable<?> call(Observable<? extends Notification<?>> observable) {
            return this.notificationHandler.call(observable.map(InternalObservableUtils.RETURNS_VOID));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes11.dex */
    public static final class ReplaySupplierBuffer<T> implements Func0<ConnectableObservable<T>> {
        private final int bufferSize;
        private final Observable<T> source;

        private ReplaySupplierBuffer(Observable<T> observable, int i2) {
            this.source = observable;
            this.bufferSize = i2;
        }

        @Override // rx.functions.Func0, java.util.concurrent.Callable
        public ConnectableObservable<T> call() {
            return this.source.replay(this.bufferSize);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes11.dex */
    public static final class ReplaySupplierBufferTime<T> implements Func0<ConnectableObservable<T>> {
        private final Scheduler scheduler;
        private final Observable<T> source;
        private final long time;
        private final TimeUnit unit;

        private ReplaySupplierBufferTime(Observable<T> observable, long j2, TimeUnit timeUnit, Scheduler scheduler) {
            this.unit = timeUnit;
            this.source = observable;
            this.time = j2;
            this.scheduler = scheduler;
        }

        @Override // rx.functions.Func0, java.util.concurrent.Callable
        public ConnectableObservable<T> call() {
            return this.source.replay(this.time, this.unit, this.scheduler);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes11.dex */
    public static final class ReplaySupplierNoParams<T> implements Func0<ConnectableObservable<T>> {
        private final Observable<T> source;

        private ReplaySupplierNoParams(Observable<T> observable) {
            this.source = observable;
        }

        @Override // rx.functions.Func0, java.util.concurrent.Callable
        public ConnectableObservable<T> call() {
            return this.source.replay();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes11.dex */
    public static final class ReplaySupplierTime<T> implements Func0<ConnectableObservable<T>> {
        private final int bufferSize;
        private final Scheduler scheduler;
        private final Observable<T> source;
        private final long time;
        private final TimeUnit unit;

        private ReplaySupplierTime(Observable<T> observable, int i2, long j2, TimeUnit timeUnit, Scheduler scheduler) {
            this.time = j2;
            this.unit = timeUnit;
            this.scheduler = scheduler;
            this.bufferSize = i2;
            this.source = observable;
        }

        @Override // rx.functions.Func0, java.util.concurrent.Callable
        public ConnectableObservable<T> call() {
            return this.source.replay(this.bufferSize, this.time, this.unit, this.scheduler);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes11.dex */
    public static final class RetryNotificationDematerializer implements Func1<Observable<? extends Notification<?>>, Observable<?>> {
        final Func1<? super Observable<? extends Throwable>, ? extends Observable<?>> notificationHandler;

        public RetryNotificationDematerializer(Func1<? super Observable<? extends Throwable>, ? extends Observable<?>> func1) {
            this.notificationHandler = func1;
        }

        @Override // rx.functions.Func1
        public Observable<?> call(Observable<? extends Notification<?>> observable) {
            return this.notificationHandler.call(observable.map(InternalObservableUtils.ERROR_EXTRACTOR));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes11.dex */
    public static final class ReturnsVoidFunc1 implements Func1<Object, Void> {
        ReturnsVoidFunc1() {
        }

        @Override // rx.functions.Func1
        public Void call(Object obj) {
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes11.dex */
    public static final class SelectorAndObserveOn<T, R> implements Func1<Observable<T>, Observable<R>> {
        final Scheduler scheduler;
        final Func1<? super Observable<T>, ? extends Observable<R>> selector;

        public SelectorAndObserveOn(Func1<? super Observable<T>, ? extends Observable<R>> func1, Scheduler scheduler) {
            this.selector = func1;
            this.scheduler = scheduler;
        }

        @Override // rx.functions.Func1
        public /* bridge */ /* synthetic */ Object call(Object obj) {
            return call((Observable) ((Observable) obj));
        }

        public Observable<R> call(Observable<T> observable) {
            return this.selector.call(observable).observeOn(this.scheduler);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes11.dex */
    public static final class ToArrayFunc1 implements Func1<List<? extends Observable<?>>, Observable<?>[]> {
        ToArrayFunc1() {
        }

        @Override // rx.functions.Func1
        public Observable<?>[] call(List<? extends Observable<?>> list) {
            return (Observable[]) list.toArray(new Observable[list.size()]);
        }
    }

    public static <T, R> Func2<R, T, R> createCollectorCaller(Action2<R, ? super T> action2) {
        return new CollectorCaller(action2);
    }

    public static final Func1<Observable<? extends Notification<?>>, Observable<?>> createRepeatDematerializer(Func1<? super Observable<? extends Void>, ? extends Observable<?>> func1) {
        return new RepeatNotificationDematerializer(func1);
    }

    public static <T, R> Func1<Observable<T>, Observable<R>> createReplaySelectorAndObserveOn(Func1<? super Observable<T>, ? extends Observable<R>> func1, Scheduler scheduler) {
        return new SelectorAndObserveOn(func1, scheduler);
    }

    public static <T> Func0<ConnectableObservable<T>> createReplaySupplier(Observable<T> observable) {
        return new ReplaySupplierNoParams(observable);
    }

    public static final Func1<Observable<? extends Notification<?>>, Observable<?>> createRetryDematerializer(Func1<? super Observable<? extends Throwable>, ? extends Observable<?>> func1) {
        return new RetryNotificationDematerializer(func1);
    }

    public static Func1<Object, Boolean> equalsWith(Object obj) {
        return new EqualsWithFunc1(obj);
    }

    public static Func1<Object, Boolean> isInstanceOf(Class<?> cls) {
        return new IsInstanceOfFunc1(cls);
    }

    public static <T> Func0<ConnectableObservable<T>> createReplaySupplier(Observable<T> observable, int i2) {
        return new ReplaySupplierBuffer(observable, i2);
    }

    public static <T> Func0<ConnectableObservable<T>> createReplaySupplier(Observable<T> observable, long j2, TimeUnit timeUnit, Scheduler scheduler) {
        return new ReplaySupplierBufferTime(observable, j2, timeUnit, scheduler);
    }

    public static <T> Func0<ConnectableObservable<T>> createReplaySupplier(Observable<T> observable, int i2, long j2, TimeUnit timeUnit, Scheduler scheduler) {
        return new ReplaySupplierTime(observable, i2, j2, timeUnit, scheduler);
    }
}

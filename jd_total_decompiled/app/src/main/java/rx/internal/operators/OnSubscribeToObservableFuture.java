package rx.internal.operators;

import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import rx.Observable;
import rx.Subscriber;
import rx.exceptions.Exceptions;
import rx.functions.Action0;
import rx.internal.producers.SingleProducer;
import rx.subscriptions.Subscriptions;

/* loaded from: classes11.dex */
public final class OnSubscribeToObservableFuture {

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes11.dex */
    public static class ToObservableFuture<T> implements Observable.OnSubscribe<T> {
        final Future<? extends T> that;
        private final long time;
        private final TimeUnit unit;

        public ToObservableFuture(Future<? extends T> future) {
            this.that = future;
            this.time = 0L;
            this.unit = null;
        }

        @Override // rx.functions.Action1
        public /* bridge */ /* synthetic */ void call(Object obj) {
            call((Subscriber) ((Subscriber) obj));
        }

        public void call(Subscriber<? super T> subscriber) {
            subscriber.add(Subscriptions.create(new Action0() { // from class: rx.internal.operators.OnSubscribeToObservableFuture.ToObservableFuture.1
                @Override // rx.functions.Action0
                public void call() {
                    ToObservableFuture.this.that.cancel(true);
                }
            }));
            try {
                if (subscriber.isUnsubscribed()) {
                    return;
                }
                TimeUnit timeUnit = this.unit;
                subscriber.setProducer(new SingleProducer(subscriber, timeUnit == null ? this.that.get() : this.that.get(this.time, timeUnit)));
            } catch (Throwable th) {
                if (subscriber.isUnsubscribed()) {
                    return;
                }
                Exceptions.throwOrReport(th, subscriber);
            }
        }

        public ToObservableFuture(Future<? extends T> future, long j2, TimeUnit timeUnit) {
            this.that = future;
            this.time = j2;
            this.unit = timeUnit;
        }
    }

    private OnSubscribeToObservableFuture() {
        throw new IllegalStateException("No instances!");
    }

    public static <T> Observable.OnSubscribe<T> toObservableFuture(Future<? extends T> future) {
        return new ToObservableFuture(future);
    }

    public static <T> Observable.OnSubscribe<T> toObservableFuture(Future<? extends T> future, long j2, TimeUnit timeUnit) {
        return new ToObservableFuture(future, j2, timeUnit);
    }
}

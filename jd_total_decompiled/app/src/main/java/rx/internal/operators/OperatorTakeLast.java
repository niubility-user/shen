package rx.internal.operators;

import java.util.ArrayDeque;
import java.util.concurrent.atomic.AtomicLong;
import rx.Observable;
import rx.Producer;
import rx.Subscriber;
import rx.functions.Func1;

/* loaded from: classes11.dex */
public final class OperatorTakeLast<T> implements Observable.Operator<T, T> {
    final int count;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes11.dex */
    public static final class TakeLastSubscriber<T> extends Subscriber<T> implements Func1<Object, T> {
        final Subscriber<? super T> actual;
        final int count;
        final AtomicLong requested = new AtomicLong();
        final ArrayDeque<Object> queue = new ArrayDeque<>();
        final NotificationLite<T> nl = NotificationLite.instance();

        public TakeLastSubscriber(Subscriber<? super T> subscriber, int i2) {
            this.actual = subscriber;
            this.count = i2;
        }

        @Override // rx.functions.Func1
        public T call(Object obj) {
            return this.nl.getValue(obj);
        }

        @Override // rx.Observer
        public void onCompleted() {
            BackpressureUtils.postCompleteDone(this.requested, this.queue, this.actual, this);
        }

        @Override // rx.Observer
        public void onError(Throwable th) {
            this.queue.clear();
            this.actual.onError(th);
        }

        @Override // rx.Observer
        public void onNext(T t) {
            if (this.queue.size() == this.count) {
                this.queue.poll();
            }
            this.queue.offer(this.nl.next(t));
        }

        void requestMore(long j2) {
            if (j2 > 0) {
                BackpressureUtils.postCompleteRequest(this.requested, j2, this.queue, this.actual, this);
            }
        }
    }

    public OperatorTakeLast(int i2) {
        if (i2 >= 0) {
            this.count = i2;
            return;
        }
        throw new IndexOutOfBoundsException("count cannot be negative");
    }

    @Override // rx.functions.Func1
    public /* bridge */ /* synthetic */ Object call(Object obj) {
        return call((Subscriber) ((Subscriber) obj));
    }

    public Subscriber<? super T> call(Subscriber<? super T> subscriber) {
        final TakeLastSubscriber takeLastSubscriber = new TakeLastSubscriber(subscriber, this.count);
        subscriber.add(takeLastSubscriber);
        subscriber.setProducer(new Producer() { // from class: rx.internal.operators.OperatorTakeLast.1
            @Override // rx.Producer
            public void request(long j2) {
                takeLastSubscriber.requestMore(j2);
            }
        });
        return takeLastSubscriber;
    }
}

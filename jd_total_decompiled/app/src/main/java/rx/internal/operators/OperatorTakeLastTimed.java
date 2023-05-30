package rx.internal.operators;

import java.util.ArrayDeque;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import rx.Observable;
import rx.Producer;
import rx.Scheduler;
import rx.Subscriber;
import rx.functions.Func1;

/* loaded from: classes11.dex */
public final class OperatorTakeLastTimed<T> implements Observable.Operator<T, T> {
    final long ageMillis;
    final int count;
    final Scheduler scheduler;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes11.dex */
    public static final class TakeLastTimedSubscriber<T> extends Subscriber<T> implements Func1<Object, T> {
        final Subscriber<? super T> actual;
        final long ageMillis;
        final int count;
        final Scheduler scheduler;
        final AtomicLong requested = new AtomicLong();
        final ArrayDeque<Object> queue = new ArrayDeque<>();
        final ArrayDeque<Long> queueTimes = new ArrayDeque<>();
        final NotificationLite<T> nl = NotificationLite.instance();

        public TakeLastTimedSubscriber(Subscriber<? super T> subscriber, int i2, long j2, Scheduler scheduler) {
            this.actual = subscriber;
            this.count = i2;
            this.ageMillis = j2;
            this.scheduler = scheduler;
        }

        @Override // rx.functions.Func1
        public T call(Object obj) {
            return this.nl.getValue(obj);
        }

        protected void evictOld(long j2) {
            long j3 = j2 - this.ageMillis;
            while (true) {
                Long peek = this.queueTimes.peek();
                if (peek == null || peek.longValue() >= j3) {
                    return;
                }
                this.queue.poll();
                this.queueTimes.poll();
            }
        }

        @Override // rx.Observer
        public void onCompleted() {
            evictOld(this.scheduler.now());
            this.queueTimes.clear();
            BackpressureUtils.postCompleteDone(this.requested, this.queue, this.actual, this);
        }

        @Override // rx.Observer
        public void onError(Throwable th) {
            this.queue.clear();
            this.queueTimes.clear();
            this.actual.onError(th);
        }

        @Override // rx.Observer
        public void onNext(T t) {
            if (this.count != 0) {
                long now = this.scheduler.now();
                if (this.queue.size() == this.count) {
                    this.queue.poll();
                    this.queueTimes.poll();
                }
                evictOld(now);
                this.queue.offer(this.nl.next(t));
                this.queueTimes.offer(Long.valueOf(now));
            }
        }

        void requestMore(long j2) {
            BackpressureUtils.postCompleteRequest(this.requested, j2, this.queue, this.actual, this);
        }
    }

    public OperatorTakeLastTimed(long j2, TimeUnit timeUnit, Scheduler scheduler) {
        this.ageMillis = timeUnit.toMillis(j2);
        this.scheduler = scheduler;
        this.count = -1;
    }

    @Override // rx.functions.Func1
    public /* bridge */ /* synthetic */ Object call(Object obj) {
        return call((Subscriber) ((Subscriber) obj));
    }

    public Subscriber<? super T> call(Subscriber<? super T> subscriber) {
        final TakeLastTimedSubscriber takeLastTimedSubscriber = new TakeLastTimedSubscriber(subscriber, this.count, this.ageMillis, this.scheduler);
        subscriber.add(takeLastTimedSubscriber);
        subscriber.setProducer(new Producer() { // from class: rx.internal.operators.OperatorTakeLastTimed.1
            @Override // rx.Producer
            public void request(long j2) {
                takeLastTimedSubscriber.requestMore(j2);
            }
        });
        return takeLastTimedSubscriber;
    }

    public OperatorTakeLastTimed(int i2, long j2, TimeUnit timeUnit, Scheduler scheduler) {
        if (i2 >= 0) {
            this.ageMillis = timeUnit.toMillis(j2);
            this.scheduler = scheduler;
            this.count = i2;
            return;
        }
        throw new IndexOutOfBoundsException("count could not be negative");
    }
}

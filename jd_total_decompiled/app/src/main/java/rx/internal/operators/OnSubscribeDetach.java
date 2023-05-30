package rx.internal.operators;

import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import rx.Observable;
import rx.Producer;
import rx.Subscriber;
import rx.Subscription;
import rx.internal.util.RxJavaPluginUtils;

/* loaded from: classes11.dex */
public final class OnSubscribeDetach<T> implements Observable.OnSubscribe<T> {
    final Observable<T> source;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes11.dex */
    public static final class DetachProducer<T> implements Producer, Subscription {
        final DetachSubscriber<T> parent;

        public DetachProducer(DetachSubscriber<T> detachSubscriber) {
            this.parent = detachSubscriber;
        }

        @Override // rx.Subscription
        public boolean isUnsubscribed() {
            return this.parent.isUnsubscribed();
        }

        @Override // rx.Producer
        public void request(long j2) {
            this.parent.innerRequest(j2);
        }

        @Override // rx.Subscription
        public void unsubscribe() {
            this.parent.innerUnsubscribe();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes11.dex */
    public static final class DetachSubscriber<T> extends Subscriber<T> {
        final AtomicReference<Subscriber<? super T>> actual;
        final AtomicReference<Producer> producer = new AtomicReference<>();
        final AtomicLong requested = new AtomicLong();

        public DetachSubscriber(Subscriber<? super T> subscriber) {
            this.actual = new AtomicReference<>(subscriber);
        }

        void innerRequest(long j2) {
            if (j2 >= 0) {
                Producer producer = this.producer.get();
                if (producer != null) {
                    producer.request(j2);
                    return;
                }
                BackpressureUtils.getAndAddRequest(this.requested, j2);
                Producer producer2 = this.producer.get();
                if (producer2 == null || producer2 == TerminatedProducer.INSTANCE) {
                    return;
                }
                producer2.request(this.requested.getAndSet(0L));
                return;
            }
            throw new IllegalArgumentException("n >= 0 required but it was " + j2);
        }

        void innerUnsubscribe() {
            this.producer.lazySet(TerminatedProducer.INSTANCE);
            this.actual.lazySet(null);
            unsubscribe();
        }

        @Override // rx.Observer
        public void onCompleted() {
            this.producer.lazySet(TerminatedProducer.INSTANCE);
            Subscriber<? super T> andSet = this.actual.getAndSet(null);
            if (andSet != null) {
                andSet.onCompleted();
            }
        }

        @Override // rx.Observer
        public void onError(Throwable th) {
            this.producer.lazySet(TerminatedProducer.INSTANCE);
            Subscriber<? super T> andSet = this.actual.getAndSet(null);
            if (andSet != null) {
                andSet.onError(th);
            } else {
                RxJavaPluginUtils.handleException(th);
            }
        }

        @Override // rx.Observer
        public void onNext(T t) {
            Subscriber<? super T> subscriber = this.actual.get();
            if (subscriber != null) {
                subscriber.onNext(t);
            }
        }

        @Override // rx.Subscriber
        public void setProducer(Producer producer) {
            if (this.producer.compareAndSet(null, producer)) {
                producer.request(this.requested.getAndSet(0L));
            } else if (this.producer.get() != TerminatedProducer.INSTANCE) {
                throw new IllegalStateException("Producer already set!");
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes11.dex */
    public enum TerminatedProducer implements Producer {
        INSTANCE;

        @Override // rx.Producer
        public void request(long j2) {
        }
    }

    public OnSubscribeDetach(Observable<T> observable) {
        this.source = observable;
    }

    @Override // rx.functions.Action1
    public /* bridge */ /* synthetic */ void call(Object obj) {
        call((Subscriber) ((Subscriber) obj));
    }

    public void call(Subscriber<? super T> subscriber) {
        DetachSubscriber detachSubscriber = new DetachSubscriber(subscriber);
        DetachProducer detachProducer = new DetachProducer(detachSubscriber);
        subscriber.add(detachProducer);
        subscriber.setProducer(detachProducer);
        this.source.unsafeSubscribe(detachSubscriber);
    }
}

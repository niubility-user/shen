package rx;

import rx.internal.util.SubscriptionList;

/* loaded from: classes11.dex */
public abstract class Subscriber<T> implements Observer<T>, Subscription {
    private static final long NOT_SET = Long.MIN_VALUE;
    private Producer producer;
    private long requested;
    private final Subscriber<?> subscriber;
    private final SubscriptionList subscriptions;

    /* JADX INFO: Access modifiers changed from: protected */
    public Subscriber() {
        this(null, false);
    }

    private void addToRequested(long j2) {
        long j3 = this.requested;
        if (j3 == Long.MIN_VALUE) {
            this.requested = j2;
            return;
        }
        long j4 = j3 + j2;
        if (j4 < 0) {
            this.requested = Long.MAX_VALUE;
        } else {
            this.requested = j4;
        }
    }

    public final void add(Subscription subscription) {
        this.subscriptions.add(subscription);
    }

    @Override // rx.Subscription
    public final boolean isUnsubscribed() {
        return this.subscriptions.isUnsubscribed();
    }

    public void onStart() {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void request(long j2) {
        if (j2 >= 0) {
            synchronized (this) {
                Producer producer = this.producer;
                if (producer != null) {
                    producer.request(j2);
                    return;
                } else {
                    addToRequested(j2);
                    return;
                }
            }
        }
        throw new IllegalArgumentException("number requested cannot be negative: " + j2);
    }

    public void setProducer(Producer producer) {
        long j2;
        Subscriber<?> subscriber;
        boolean z;
        synchronized (this) {
            j2 = this.requested;
            this.producer = producer;
            subscriber = this.subscriber;
            z = subscriber != null && j2 == Long.MIN_VALUE;
        }
        if (z) {
            subscriber.setProducer(producer);
        } else if (j2 == Long.MIN_VALUE) {
            producer.request(Long.MAX_VALUE);
        } else {
            producer.request(j2);
        }
    }

    @Override // rx.Subscription
    public final void unsubscribe() {
        this.subscriptions.unsubscribe();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Subscriber(Subscriber<?> subscriber) {
        this(subscriber, true);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Subscriber(Subscriber<?> subscriber, boolean z) {
        this.requested = Long.MIN_VALUE;
        this.subscriber = subscriber;
        this.subscriptions = (!z || subscriber == null) ? new SubscriptionList() : subscriber.subscriptions;
    }
}

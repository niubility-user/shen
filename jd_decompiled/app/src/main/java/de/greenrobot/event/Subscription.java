package de.greenrobot.event;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes11.dex */
public final class Subscription {
    volatile boolean active = true;
    final int priority;
    final Object subscriber;
    final SubscriberMethod subscriberMethod;

    /* JADX INFO: Access modifiers changed from: package-private */
    public Subscription(Object obj, SubscriberMethod subscriberMethod, int i2) {
        this.subscriber = obj;
        this.subscriberMethod = subscriberMethod;
        this.priority = i2;
    }

    public boolean equals(Object obj) {
        if (obj instanceof Subscription) {
            Subscription subscription = (Subscription) obj;
            return this.subscriber == subscription.subscriber && this.subscriberMethod.equals(subscription.subscriberMethod);
        }
        return false;
    }

    public int hashCode() {
        return this.subscriber.hashCode() + this.subscriberMethod.methodString.hashCode();
    }
}

package rx;

import rx.annotations.Beta;
import rx.internal.util.SubscriptionList;

@Beta
/* loaded from: classes11.dex */
public abstract class SingleSubscriber<T> implements Subscription {
    private final SubscriptionList cs = new SubscriptionList();

    public final void add(Subscription subscription) {
        this.cs.add(subscription);
    }

    @Override // rx.Subscription
    public final boolean isUnsubscribed() {
        return this.cs.isUnsubscribed();
    }

    public abstract void onError(Throwable th);

    public abstract void onSuccess(T t);

    @Override // rx.Subscription
    public final void unsubscribe() {
        this.cs.unsubscribe();
    }
}

package rx.internal.schedulers;

import java.util.concurrent.TimeUnit;
import rx.Scheduler;
import rx.Subscription;
import rx.functions.Action0;
import rx.subscriptions.BooleanSubscription;
import rx.subscriptions.Subscriptions;

/* loaded from: classes11.dex */
public final class ImmediateScheduler extends Scheduler {
    public static final ImmediateScheduler INSTANCE = new ImmediateScheduler();

    private ImmediateScheduler() {
    }

    @Override // rx.Scheduler
    public Scheduler.Worker createWorker() {
        return new InnerImmediateScheduler();
    }

    /* loaded from: classes11.dex */
    private class InnerImmediateScheduler extends Scheduler.Worker implements Subscription {
        final BooleanSubscription innerSubscription = new BooleanSubscription();

        InnerImmediateScheduler() {
        }

        @Override // rx.Subscription
        public boolean isUnsubscribed() {
            return this.innerSubscription.isUnsubscribed();
        }

        @Override // rx.Scheduler.Worker
        public Subscription schedule(Action0 action0, long j2, TimeUnit timeUnit) {
            return schedule(new SleepingAction(action0, this, ImmediateScheduler.this.now() + timeUnit.toMillis(j2)));
        }

        @Override // rx.Subscription
        public void unsubscribe() {
            this.innerSubscription.unsubscribe();
        }

        @Override // rx.Scheduler.Worker
        public Subscription schedule(Action0 action0) {
            action0.call();
            return Subscriptions.unsubscribed();
        }
    }
}

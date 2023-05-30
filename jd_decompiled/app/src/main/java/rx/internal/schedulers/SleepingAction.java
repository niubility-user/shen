package rx.internal.schedulers;

import rx.Scheduler;
import rx.functions.Action0;

/* loaded from: classes11.dex */
class SleepingAction implements Action0 {
    private final long execTime;
    private final Scheduler.Worker innerScheduler;
    private final Action0 underlying;

    public SleepingAction(Action0 action0, Scheduler.Worker worker, long j2) {
        this.underlying = action0;
        this.innerScheduler = worker;
        this.execTime = j2;
    }

    @Override // rx.functions.Action0
    public void call() {
        if (this.innerScheduler.isUnsubscribed()) {
            return;
        }
        long now = this.execTime - this.innerScheduler.now();
        if (now > 0) {
            try {
                Thread.sleep(now);
            } catch (InterruptedException e2) {
                Thread.currentThread().interrupt();
                throw new RuntimeException(e2);
            }
        }
        if (this.innerScheduler.isUnsubscribed()) {
            return;
        }
        this.underlying.call();
    }
}

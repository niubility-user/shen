package rx;

import java.util.concurrent.TimeUnit;
import rx.functions.Action0;
import rx.subscriptions.MultipleAssignmentSubscription;

/* loaded from: classes11.dex */
public abstract class Scheduler {
    static final long CLOCK_DRIFT_TOLERANCE_NANOS = TimeUnit.MINUTES.toNanos(Long.getLong("rx.scheduler.drift-tolerance", 15).longValue());

    /* loaded from: classes11.dex */
    public static abstract class Worker implements Subscription {
        public long now() {
            return System.currentTimeMillis();
        }

        public abstract Subscription schedule(Action0 action0);

        public abstract Subscription schedule(Action0 action0, long j2, TimeUnit timeUnit);

        public Subscription schedulePeriodically(Action0 action0, long j2, long j3, TimeUnit timeUnit) {
            long nanos = timeUnit.toNanos(j3);
            long nanos2 = TimeUnit.MILLISECONDS.toNanos(now());
            long nanos3 = nanos2 + timeUnit.toNanos(j2);
            MultipleAssignmentSubscription multipleAssignmentSubscription = new MultipleAssignmentSubscription();
            Action0 action02 = new Action0(nanos2, nanos3, multipleAssignmentSubscription, action0, nanos) { // from class: rx.Scheduler.Worker.1
                long count;
                long lastNowNanos;
                long startInNanos;
                final /* synthetic */ Action0 val$action;
                final /* synthetic */ long val$firstNowNanos;
                final /* synthetic */ long val$firstStartInNanos;
                final /* synthetic */ MultipleAssignmentSubscription val$mas;
                final /* synthetic */ long val$periodInNanos;

                {
                    Worker.this = this;
                    this.val$firstNowNanos = nanos2;
                    this.val$firstStartInNanos = nanos3;
                    this.val$mas = multipleAssignmentSubscription;
                    this.val$action = action0;
                    this.val$periodInNanos = nanos;
                    this.lastNowNanos = nanos2;
                    this.startInNanos = nanos3;
                }

                @Override // rx.functions.Action0
                public void call() {
                    long j4;
                    if (this.val$mas.isUnsubscribed()) {
                        return;
                    }
                    this.val$action.call();
                    long nanos4 = TimeUnit.MILLISECONDS.toNanos(Worker.this.now());
                    long j5 = Scheduler.CLOCK_DRIFT_TOLERANCE_NANOS;
                    long j6 = this.lastNowNanos;
                    if (nanos4 + j5 >= j6) {
                        long j7 = this.val$periodInNanos;
                        if (nanos4 < j6 + j7 + j5) {
                            long j8 = this.startInNanos;
                            long j9 = this.count + 1;
                            this.count = j9;
                            j4 = j8 + (j9 * j7);
                            this.lastNowNanos = nanos4;
                            this.val$mas.set(Worker.this.schedule(this, j4 - nanos4, TimeUnit.NANOSECONDS));
                        }
                    }
                    long j10 = this.val$periodInNanos;
                    long j11 = nanos4 + j10;
                    long j12 = this.count + 1;
                    this.count = j12;
                    this.startInNanos = j11 - (j10 * j12);
                    j4 = j11;
                    this.lastNowNanos = nanos4;
                    this.val$mas.set(Worker.this.schedule(this, j4 - nanos4, TimeUnit.NANOSECONDS));
                }
            };
            MultipleAssignmentSubscription multipleAssignmentSubscription2 = new MultipleAssignmentSubscription();
            multipleAssignmentSubscription.set(multipleAssignmentSubscription2);
            multipleAssignmentSubscription2.set(schedule(action02, j2, timeUnit));
            return multipleAssignmentSubscription;
        }
    }

    public abstract Worker createWorker();

    public long now() {
        return System.currentTimeMillis();
    }
}

package com.jingdong.common.utils.rx.internal.schedulers;

import com.huawei.hms.push.constant.RemoteMessageConst;
import com.jingdong.common.utils.rx.internal.IBaseWork;
import com.jingdong.sdk.oklog.OKLog;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import rx.Scheduler;
import rx.Subscription;
import rx.functions.Action0;
import rx.internal.schedulers.GenericScheduledExecutorService;
import rx.internal.schedulers.ScheduledAction;
import rx.plugins.RxJavaPlugins;
import rx.subscriptions.CompositeSubscription;
import rx.subscriptions.MultipleAssignmentSubscription;
import rx.subscriptions.Subscriptions;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000f\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\n\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u00032\b\u0012\u0004\u0012\u00020\u00030\u0004B\u0019\u0012\b\u00102\u001a\u0004\u0018\u000101\u0012\u0006\u0010:\u001a\u00020\u0016\u00a2\u0006\u0004\b<\u0010=J\u0019\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005H\u0016\u00a2\u0006\u0004\b\b\u0010\tJ+\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u000b\u001a\u00020\n2\b\u0010\r\u001a\u0004\u0018\u00010\fH\u0016\u00a2\u0006\u0004\b\b\u0010\u000eJ\u000f\u0010\u0010\u001a\u00020\u000fH\u0016\u00a2\u0006\u0004\b\u0010\u0010\u0011J\u000f\u0010\u0013\u001a\u00020\u0012H\u0016\u00a2\u0006\u0004\b\u0013\u0010\u0014J\u000f\u0010\u0015\u001a\u00020\u0012H\u0016\u00a2\u0006\u0004\b\u0015\u0010\u0014J\u000f\u0010\u0017\u001a\u00020\u0016H\u0016\u00a2\u0006\u0004\b\u0017\u0010\u0018J\u0017\u0010\u001a\u001a\u00020\u00122\u0006\u0010\u0019\u001a\u00020\u000fH\u0016\u00a2\u0006\u0004\b\u001a\u0010\u001bJ\u000f\u0010\u001c\u001a\u00020\u000fH\u0016\u00a2\u0006\u0004\b\u001c\u0010\u0011J\u0018\u0010\u001e\u001a\u00020\u00162\u0006\u0010\u001d\u001a\u00020\u0003H\u0096\u0002\u00a2\u0006\u0004\b\u001e\u0010\u001fR\u001d\u0010%\u001a\u00020 8B@\u0002X\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b!\u0010\"\u001a\u0004\b#\u0010$R\u001d\u0010*\u001a\u00020&8B@\u0002X\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b'\u0010\"\u001a\u0004\b(\u0010)R#\u00100\u001a\b\u0012\u0004\u0012\u00020,0+8B@\u0002X\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b-\u0010\"\u001a\u0004\b.\u0010/R\u0018\u00102\u001a\u0004\u0018\u0001018\u0002@\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b2\u00103R\u001d\u00108\u001a\u0002048B@\u0002X\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b5\u0010\"\u001a\u0004\b6\u00107R\u0016\u0010\u0019\u001a\u00020\u000f8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u0019\u00109R\u0016\u0010:\u001a\u00020\u00168\u0002@\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b:\u0010;\u00a8\u0006>"}, d2 = {"Lcom/jingdong/common/utils/rx/internal/schedulers/IOExecutorSchedulerWorker;", "Lrx/Scheduler$Worker;", "Ljava/lang/Runnable;", "Lcom/jingdong/common/utils/rx/internal/IBaseWork;", "", "Lrx/functions/Action0;", "action", "Lrx/Subscription;", "schedule", "(Lrx/functions/Action0;)Lrx/Subscription;", "", "delayTime", "Ljava/util/concurrent/TimeUnit;", "unit", "(Lrx/functions/Action0;JLjava/util/concurrent/TimeUnit;)Lrx/Subscription;", "", "isUnsubscribed", "()Z", "", "unsubscribe", "()V", "run", "", "getPriority", "()I", "needCreateNewThread", "setNeedCreateNewThread", "(Z)V", "isNeedCreateNewThread", "other", "compareTo", "(Lcom/jingdong/common/utils/rx/internal/IBaseWork;)I", "Ljava/util/concurrent/ScheduledExecutorService;", "service$delegate", "Lkotlin/Lazy;", "getService", "()Ljava/util/concurrent/ScheduledExecutorService;", "service", "Lrx/subscriptions/CompositeSubscription;", "tasks$delegate", "getTasks", "()Lrx/subscriptions/CompositeSubscription;", "tasks", "Ljava/util/concurrent/ConcurrentLinkedQueue;", "Lrx/internal/schedulers/ScheduledAction;", "queue$delegate", "getQueue", "()Ljava/util/concurrent/ConcurrentLinkedQueue;", "queue", "Ljava/util/concurrent/Executor;", "executor", "Ljava/util/concurrent/Executor;", "Ljava/util/concurrent/atomic/AtomicInteger;", "wip$delegate", "getWip", "()Ljava/util/concurrent/atomic/AtomicInteger;", "wip", "Z", RemoteMessageConst.Notification.PRIORITY, "I", "<init>", "(Ljava/util/concurrent/Executor;I)V", "personallib"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes6.dex */
public final class IOExecutorSchedulerWorker extends Scheduler.Worker implements Runnable, IBaseWork, Comparable<IBaseWork> {
    private final Executor executor;
    private boolean needCreateNewThread;
    private final int priority;

    /* renamed from: queue$delegate  reason: from kotlin metadata */
    private final Lazy queue;

    /* renamed from: service$delegate  reason: from kotlin metadata */
    private final Lazy service;

    /* renamed from: tasks$delegate  reason: from kotlin metadata */
    private final Lazy tasks;

    /* renamed from: wip$delegate  reason: from kotlin metadata */
    private final Lazy wip;

    public IOExecutorSchedulerWorker(@Nullable Executor executor, int i2) {
        Lazy lazy;
        Lazy lazy2;
        Lazy lazy3;
        Lazy lazy4;
        this.executor = executor;
        this.priority = i2;
        lazy = LazyKt__LazyJVMKt.lazy(new Function0<CompositeSubscription>() { // from class: com.jingdong.common.utils.rx.internal.schedulers.IOExecutorSchedulerWorker$tasks$2
            @Override // kotlin.jvm.functions.Function0
            @NotNull
            public final CompositeSubscription invoke() {
                return new CompositeSubscription();
            }
        });
        this.tasks = lazy;
        lazy2 = LazyKt__LazyJVMKt.lazy(new Function0<ConcurrentLinkedQueue<ScheduledAction>>() { // from class: com.jingdong.common.utils.rx.internal.schedulers.IOExecutorSchedulerWorker$queue$2
            @Override // kotlin.jvm.functions.Function0
            @NotNull
            public final ConcurrentLinkedQueue<ScheduledAction> invoke() {
                return new ConcurrentLinkedQueue<>();
            }
        });
        this.queue = lazy2;
        lazy3 = LazyKt__LazyJVMKt.lazy(new Function0<AtomicInteger>() { // from class: com.jingdong.common.utils.rx.internal.schedulers.IOExecutorSchedulerWorker$wip$2
            @Override // kotlin.jvm.functions.Function0
            @NotNull
            public final AtomicInteger invoke() {
                return new AtomicInteger();
            }
        });
        this.wip = lazy3;
        lazy4 = LazyKt__LazyJVMKt.lazy(new Function0<ScheduledExecutorService>() { // from class: com.jingdong.common.utils.rx.internal.schedulers.IOExecutorSchedulerWorker$service$2
            @Override // kotlin.jvm.functions.Function0
            public final ScheduledExecutorService invoke() {
                return GenericScheduledExecutorService.getInstance();
            }
        });
        this.service = lazy4;
    }

    private final ConcurrentLinkedQueue<ScheduledAction> getQueue() {
        return (ConcurrentLinkedQueue) this.queue.getValue();
    }

    private final ScheduledExecutorService getService() {
        return (ScheduledExecutorService) this.service.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final CompositeSubscription getTasks() {
        return (CompositeSubscription) this.tasks.getValue();
    }

    private final AtomicInteger getWip() {
        return (AtomicInteger) this.wip.getValue();
    }

    @Override // com.jingdong.common.utils.rx.internal.IBaseWork
    public int getPriority() {
        return this.priority;
    }

    @Override // com.jingdong.common.utils.rx.internal.IBaseWork
    /* renamed from: isNeedCreateNewThread  reason: from getter */
    public boolean getNeedCreateNewThread() {
        return this.needCreateNewThread;
    }

    @Override // rx.Subscription
    public boolean isUnsubscribed() {
        return getTasks().isUnsubscribed();
    }

    @Override // java.lang.Runnable
    public void run() {
        while (!getTasks().isUnsubscribed()) {
            ScheduledAction poll = getQueue().poll();
            if (poll == null) {
                return;
            }
            if (!poll.isUnsubscribed()) {
                if (!getTasks().isUnsubscribed()) {
                    poll.run();
                } else {
                    getQueue().clear();
                    return;
                }
            }
            if (getWip().decrementAndGet() == 0) {
                return;
            }
        }
        getQueue().clear();
    }

    @Override // rx.Scheduler.Worker
    @NotNull
    public Subscription schedule(@Nullable Action0 action) {
        if (getTasks().isUnsubscribed()) {
            Subscription unsubscribed = Subscriptions.unsubscribed();
            Intrinsics.checkExpressionValueIsNotNull(unsubscribed, "Subscriptions.unsubscribed()");
            return unsubscribed;
        }
        ScheduledAction scheduledAction = new ScheduledAction(action, getTasks());
        getTasks().add(scheduledAction);
        getQueue().add(scheduledAction);
        if (getWip().getAndIncrement() == 0) {
            try {
                Executor executor = this.executor;
                if (executor != null) {
                    executor.execute(this);
                }
            } catch (Throwable th) {
                if (this.needCreateNewThread || getPriority() >= -10) {
                    try {
                        Executor executor2 = this.executor;
                        if (executor2 != null) {
                            executor2.execute(this);
                        }
                    } catch (Throwable th2) {
                        if (OKLog.D) {
                            OKLog.d("IOExecutorSchedulerWorker", "schedule error1: " + th2);
                        }
                    }
                }
                getTasks().remove(scheduledAction);
                getWip().decrementAndGet();
                RxJavaPlugins rxJavaPlugins = RxJavaPlugins.getInstance();
                Intrinsics.checkExpressionValueIsNotNull(rxJavaPlugins, "RxJavaPlugins.getInstance()");
                rxJavaPlugins.getErrorHandler().handleError(th);
                if (OKLog.D) {
                    OKLog.d("IOExecutorSchedulerWorker", "schedule error: " + th);
                }
                throw th;
            }
        }
        return scheduledAction;
    }

    @Override // com.jingdong.common.utils.rx.internal.IBaseWork
    public void setNeedCreateNewThread(boolean needCreateNewThread) {
        this.needCreateNewThread = needCreateNewThread;
    }

    @Override // rx.Subscription
    public void unsubscribe() {
        getTasks().unsubscribe();
        getQueue().clear();
    }

    @Override // java.lang.Comparable
    public int compareTo(@NotNull IBaseWork other) {
        return this.priority >= other.getPriority() ? -1 : 1;
    }

    @Override // rx.Scheduler.Worker
    @NotNull
    public Subscription schedule(@Nullable final Action0 action, long delayTime, @Nullable TimeUnit unit) {
        if (delayTime <= 0) {
            return schedule(action);
        }
        if (isUnsubscribed()) {
            Subscription unsubscribed = Subscriptions.unsubscribed();
            Intrinsics.checkExpressionValueIsNotNull(unsubscribed, "Subscriptions.unsubscribed()");
            return unsubscribed;
        }
        MultipleAssignmentSubscription multipleAssignmentSubscription = new MultipleAssignmentSubscription();
        final MultipleAssignmentSubscription multipleAssignmentSubscription2 = new MultipleAssignmentSubscription();
        multipleAssignmentSubscription2.set(multipleAssignmentSubscription);
        getTasks().add(multipleAssignmentSubscription2);
        final Subscription removeMas = Subscriptions.create(new Action0() { // from class: com.jingdong.common.utils.rx.internal.schedulers.IOExecutorSchedulerWorker$schedule$removeMas$1
            @Override // rx.functions.Action0
            public final void call() {
                CompositeSubscription tasks;
                tasks = IOExecutorSchedulerWorker.this.getTasks();
                tasks.remove(multipleAssignmentSubscription2);
            }
        });
        ScheduledAction scheduledAction = new ScheduledAction(new Action0() { // from class: com.jingdong.common.utils.rx.internal.schedulers.IOExecutorSchedulerWorker$schedule$ea$1
            @Override // rx.functions.Action0
            public final void call() {
                if (multipleAssignmentSubscription2.isUnsubscribed()) {
                    return;
                }
                Subscription schedule = IOExecutorSchedulerWorker.this.schedule(action);
                multipleAssignmentSubscription2.set(schedule);
                if (Intrinsics.areEqual(schedule.getClass(), ScheduledAction.class)) {
                    if (schedule == null) {
                        throw new TypeCastException("null cannot be cast to non-null type rx.internal.schedulers.ScheduledAction");
                    }
                    ((ScheduledAction) schedule).add(removeMas);
                }
            }
        });
        multipleAssignmentSubscription.set(scheduledAction);
        try {
            ScheduledFuture<?> schedule = getService().schedule(scheduledAction, delayTime, unit);
            Intrinsics.checkExpressionValueIsNotNull(schedule, "service.schedule(ea, delayTime, unit)");
            scheduledAction.add(schedule);
            Intrinsics.checkExpressionValueIsNotNull(removeMas, "removeMas");
            return removeMas;
        } catch (RejectedExecutionException e2) {
            RxJavaPlugins rxJavaPlugins = RxJavaPlugins.getInstance();
            Intrinsics.checkExpressionValueIsNotNull(rxJavaPlugins, "RxJavaPlugins.getInstance()");
            rxJavaPlugins.getErrorHandler().handleError(e2);
            throw e2;
        }
    }
}

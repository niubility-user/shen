package com.jingdong.common.utils.rx;

import com.huawei.hms.push.constant.RemoteMessageConst;
import com.jingdong.common.utils.rx.internal.concurrent.IOThreadPool;
import com.jingdong.common.utils.rx.internal.schedulers.IOExecutorSchedulerWorker;
import com.jingdong.sdk.oklog.OKLog;
import java.util.concurrent.Executor;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.annotations.NotNull;
import rx.Scheduler;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\n\b\u0016\u0018\u0000 \u00112\u00020\u0001:\u0002\u0011\u0012B\t\b\u0012\u00a2\u0006\u0004\b\u000f\u0010\u0010J\u0011\u0010\u0003\u001a\u0004\u0018\u00010\u0002H\u0002\u00a2\u0006\u0004\b\u0003\u0010\u0004J\u0015\u0010\u0007\u001a\u00020\u00002\u0006\u0010\u0006\u001a\u00020\u0005\u00a2\u0006\u0004\b\u0007\u0010\bJ\u000f\u0010\n\u001a\u00020\tH\u0016\u00a2\u0006\u0004\b\n\u0010\u000bR\u0016\u0010\u0006\u001a\u00020\u00058\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u0006\u0010\fR\u0018\u0010\r\u001a\u0004\u0018\u00010\u00028\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\r\u0010\u000e\u00a8\u0006\u0013"}, d2 = {"Lcom/jingdong/common/utils/rx/PersonalIOScheduler;", "Lrx/Scheduler;", "Ljava/util/concurrent/Executor;", "createIOExecutor", "()Ljava/util/concurrent/Executor;", "", RemoteMessageConst.Notification.PRIORITY, "setPriority", "(I)Lcom/jingdong/common/utils/rx/PersonalIOScheduler;", "Lrx/Scheduler$Worker;", "createWorker", "()Lrx/Scheduler$Worker;", "I", "executor", "Ljava/util/concurrent/Executor;", "<init>", "()V", "Companion", "SingletonHolder", "personallib"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes6.dex */
public class PersonalIOScheduler extends Scheduler {

    /* renamed from: Companion  reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    @NotNull
    private static final PersonalIOScheduler INSTANCE = SingletonHolder.INSTANCE.getHOLDER();
    private static final String TAG = "IOScheduler";
    private Executor executor;
    private int priority;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\n\u0010\u000bR\u0019\u0010\u0003\u001a\u00020\u00028\u0006@\u0006\u00a2\u0006\f\n\u0004\b\u0003\u0010\u0004\u001a\u0004\b\u0005\u0010\u0006R\u0016\u0010\b\u001a\u00020\u00078\u0002@\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\b\u0010\t\u00a8\u0006\f"}, d2 = {"Lcom/jingdong/common/utils/rx/PersonalIOScheduler$Companion;", "", "Lcom/jingdong/common/utils/rx/PersonalIOScheduler;", "INSTANCE", "Lcom/jingdong/common/utils/rx/PersonalIOScheduler;", "getINSTANCE", "()Lcom/jingdong/common/utils/rx/PersonalIOScheduler;", "", "TAG", "Ljava/lang/String;", "<init>", "()V", "personallib"}, k = 1, mv = {1, 4, 0})
    /* loaded from: classes6.dex */
    public static final class Companion {
        private Companion() {
        }

        @NotNull
        public final PersonalIOScheduler getINSTANCE() {
            return PersonalIOScheduler.INSTANCE;
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u00c6\u0002\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0007\u0010\bR\u0019\u0010\u0003\u001a\u00020\u00028\u0006@\u0006\u00a2\u0006\f\n\u0004\b\u0003\u0010\u0004\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\t"}, d2 = {"Lcom/jingdong/common/utils/rx/PersonalIOScheduler$SingletonHolder;", "", "Lcom/jingdong/common/utils/rx/PersonalIOScheduler;", "HOLDER", "Lcom/jingdong/common/utils/rx/PersonalIOScheduler;", "getHOLDER", "()Lcom/jingdong/common/utils/rx/PersonalIOScheduler;", "<init>", "()V", "personallib"}, k = 1, mv = {1, 4, 0})
    /* loaded from: classes6.dex */
    public static final class SingletonHolder {
        public static final SingletonHolder INSTANCE = new SingletonHolder();
        @NotNull
        private static final PersonalIOScheduler HOLDER = new PersonalIOScheduler(null);

        private SingletonHolder() {
        }

        @NotNull
        public final PersonalIOScheduler getHOLDER() {
            return HOLDER;
        }
    }

    public /* synthetic */ PersonalIOScheduler(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    private final Executor createIOExecutor() {
        return IOThreadPool.INSTANCE.getINSTANCE().build().getPool();
    }

    @Override // rx.Scheduler
    @NotNull
    public Scheduler.Worker createWorker() {
        if (OKLog.D) {
            OKLog.d(TAG, "IOScheduler createNewWorker priority: " + this.priority);
        }
        return new IOExecutorSchedulerWorker(this.executor, this.priority);
    }

    @NotNull
    public final PersonalIOScheduler setPriority(int priority) {
        if (OKLog.D) {
            OKLog.d(TAG, "IOScheduler setPriority: " + priority);
        }
        this.priority = priority;
        return this;
    }

    private PersonalIOScheduler() {
        this.executor = createIOExecutor();
        this.executor = createIOExecutor();
    }
}

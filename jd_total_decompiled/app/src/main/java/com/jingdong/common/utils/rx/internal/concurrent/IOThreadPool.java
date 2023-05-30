package com.jingdong.common.utils.rx.internal.concurrent;

import com.jd.libs.hybrid.HybridSDK;
import com.jingdong.sdk.oklog.OKLog;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\r\u0018\u0000 \u001f2\u00020\u0001:\u0002\u001f B\t\b\u0002\u00a2\u0006\u0004\b\u001d\u0010\u001eJ\r\u0010\u0002\u001a\u00020\u0000\u00a2\u0006\u0004\b\u0002\u0010\u0003J?\u0010\u0002\u001a\u00020\u00002\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\u000b\u001a\u00020\u00042\b\b\u0002\u0010\r\u001a\u00020\f\u00a2\u0006\u0004\b\u0002\u0010\u000eJ\u0011\u0010\u0010\u001a\u0004\u0018\u00010\u000fH\u0016\u00a2\u0006\u0004\b\u0010\u0010\u0011J\u0019\u0010\u0015\u001a\u00020\u00142\b\u0010\u0013\u001a\u0004\u0018\u00010\u0012H\u0016\u00a2\u0006\u0004\b\u0015\u0010\u0016R\u0016\u0010\u0017\u001a\u00020\t8\u0002@\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0017\u0010\u0018R\u0016\u0010\u0019\u001a\u00020\u00048\u0002@\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0019\u0010\u001aR\u0018\u0010\u001b\u001a\u0004\u0018\u00010\u000f8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u001b\u0010\u001c\u00a8\u0006!"}, d2 = {"Lcom/jingdong/common/utils/rx/internal/concurrent/IOThreadPool;", "Lcom/jingdong/common/utils/rx/internal/concurrent/IThreadPool;", HybridSDK.APP_VERSION_CODE, "()Lcom/jingdong/common/utils/rx/internal/concurrent/IOThreadPool;", "", "corePoolSize", "maxPoolsSize", "", "keepAliveTime", "Ljava/util/concurrent/TimeUnit;", "unit", "blockingQueueSize", "", "allowCoreThreadTimeOut", "(IIJLjava/util/concurrent/TimeUnit;IZ)Lcom/jingdong/common/utils/rx/internal/concurrent/IOThreadPool;", "Ljava/util/concurrent/ThreadPoolExecutor;", "getIOThreadPoolExecutor", "()Ljava/util/concurrent/ThreadPoolExecutor;", "Ljava/lang/Runnable;", "runnable", "", "executeTask", "(Ljava/lang/Runnable;)V", "defaultAliveTimeUnit", "Ljava/util/concurrent/TimeUnit;", "defaultIOCoreSize", "I", "pool", "Ljava/util/concurrent/ThreadPoolExecutor;", "<init>", "()V", "Companion", "SingletonHolder", "personallib"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes6.dex */
public final class IOThreadPool implements IThreadPool {
    private static final boolean DEFAULT_ALLOW_CORE_THREAD_TIMEOUT = true;
    private static final int DEFAULT_BLOCKING_QUEUE_SIZE = 5;
    private static final long DEFAULT_KEEP_ALIVE_TIME = 1;
    private final TimeUnit defaultAliveTimeUnit;
    private final int defaultIOCoreSize;
    private ThreadPoolExecutor pool;

    /* renamed from: Companion  reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    @NotNull
    private static final IOThreadPool INSTANCE = SingletonHolder.INSTANCE.getHOLDER();

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0010\u0010\u0011R\u0019\u0010\u0003\u001a\u00020\u00028\u0006@\u0006\u00a2\u0006\f\n\u0004\b\u0003\u0010\u0004\u001a\u0004\b\u0005\u0010\u0006R\u0016\u0010\b\u001a\u00020\u00078\u0002@\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\b\u0010\tR\u0016\u0010\u000b\u001a\u00020\n8\u0002@\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u000b\u0010\fR\u0016\u0010\u000e\u001a\u00020\r8\u0002@\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u000e\u0010\u000f\u00a8\u0006\u0012"}, d2 = {"Lcom/jingdong/common/utils/rx/internal/concurrent/IOThreadPool$Companion;", "", "Lcom/jingdong/common/utils/rx/internal/concurrent/IOThreadPool;", "INSTANCE", "Lcom/jingdong/common/utils/rx/internal/concurrent/IOThreadPool;", "getINSTANCE", "()Lcom/jingdong/common/utils/rx/internal/concurrent/IOThreadPool;", "", "DEFAULT_ALLOW_CORE_THREAD_TIMEOUT", "Z", "", "DEFAULT_BLOCKING_QUEUE_SIZE", "I", "", "DEFAULT_KEEP_ALIVE_TIME", "J", "<init>", "()V", "personallib"}, k = 1, mv = {1, 4, 0})
    /* loaded from: classes6.dex */
    public static final class Companion {
        private Companion() {
        }

        @NotNull
        public final IOThreadPool getINSTANCE() {
            return IOThreadPool.INSTANCE;
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u00c2\u0002\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0007\u0010\bR\u0019\u0010\u0003\u001a\u00020\u00028\u0006@\u0006\u00a2\u0006\f\n\u0004\b\u0003\u0010\u0004\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\t"}, d2 = {"Lcom/jingdong/common/utils/rx/internal/concurrent/IOThreadPool$SingletonHolder;", "", "Lcom/jingdong/common/utils/rx/internal/concurrent/IOThreadPool;", "HOLDER", "Lcom/jingdong/common/utils/rx/internal/concurrent/IOThreadPool;", "getHOLDER", "()Lcom/jingdong/common/utils/rx/internal/concurrent/IOThreadPool;", "<init>", "()V", "personallib"}, k = 1, mv = {1, 4, 0})
    /* loaded from: classes6.dex */
    private static final class SingletonHolder {
        public static final SingletonHolder INSTANCE = new SingletonHolder();
        @NotNull
        private static final IOThreadPool HOLDER = new IOThreadPool(null);

        private SingletonHolder() {
        }

        @NotNull
        public final IOThreadPool getHOLDER() {
            return HOLDER;
        }
    }

    private IOThreadPool() {
        this.defaultIOCoreSize = Runtime.getRuntime().availableProcessors();
        this.defaultAliveTimeUnit = TimeUnit.MINUTES;
    }

    @NotNull
    public final IOThreadPool build() {
        if (OKLog.D) {
            OKLog.d("IOThreadPool", "defaultIOCoreSize: " + this.defaultIOCoreSize);
        }
        int i2 = this.defaultIOCoreSize;
        return build(i2, i2 * 2, 1L, this.defaultAliveTimeUnit, 5, true);
    }

    @Override // com.jingdong.common.utils.rx.internal.concurrent.IThreadPool
    public void executeTask(@Nullable Runnable runnable) {
        ThreadPoolExecutor threadPoolExecutor;
        if (runnable == null || (threadPoolExecutor = this.pool) == null) {
            return;
        }
        threadPoolExecutor.execute(runnable);
    }

    @Override // com.jingdong.common.utils.rx.internal.concurrent.IThreadPool
    @Nullable
    /* renamed from: getIOThreadPoolExecutor  reason: from getter */
    public ThreadPoolExecutor getPool() {
        return this.pool;
    }

    public /* synthetic */ IOThreadPool(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    @NotNull
    public final IOThreadPool build(int corePoolSize, int maxPoolsSize, long keepAliveTime, @NotNull TimeUnit unit, int blockingQueueSize, boolean allowCoreThreadTimeOut) {
        IOPriorityQueue iOPriorityQueue = new IOPriorityQueue(maxPoolsSize, blockingQueueSize);
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(corePoolSize, maxPoolsSize, keepAliveTime, unit, iOPriorityQueue);
        this.pool = threadPoolExecutor;
        if (threadPoolExecutor != null) {
            iOPriorityQueue.setThreadPoolExecutor(threadPoolExecutor);
            threadPoolExecutor.allowCoreThreadTimeOut(allowCoreThreadTimeOut);
        }
        return this;
    }
}

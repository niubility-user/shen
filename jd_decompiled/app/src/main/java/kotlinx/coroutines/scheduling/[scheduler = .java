package kotlinx.coroutines.scheduling;

import com.jingdong.amon.router.annotation.AnnoConst;
import com.jingdong.common.jdreactFramework.JDReactConstant;
import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionException;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.DefaultExecutor;
import kotlinx.coroutines.ExecutorCoroutineDispatcher;
import kotlinx.coroutines.InternalCoroutinesApi;
import org.jetbrains.annotations.NotNull;

@InternalCoroutinesApi
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\t\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\b\b\b\u0017\u0018\u00002\u00020\u0001B)\u0012\u0006\u0010.\u001a\u00020\u0013\u0012\u0006\u0010,\u001a\u00020\u0013\u0012\u0006\u0010/\u001a\u00020!\u0012\b\b\u0002\u0010(\u001a\u00020\u0010\u00a2\u0006\u0004\b5\u00106B'\b\u0016\u0012\b\b\u0002\u0010.\u001a\u00020\u0013\u0012\b\b\u0002\u0010,\u001a\u00020\u0013\u0012\b\b\u0002\u0010(\u001a\u00020\u0010\u00a2\u0006\u0004\b5\u00107B\u001d\b\u0017\u0012\b\b\u0002\u0010.\u001a\u00020\u0013\u0012\b\b\u0002\u0010,\u001a\u00020\u0013\u00a2\u0006\u0004\b5\u00108J\u000f\u0010\u0003\u001a\u00020\u0002H\u0002\u00a2\u0006\u0004\b\u0003\u0010\u0004J#\u0010\u000b\u001a\u00020\n2\u0006\u0010\u0006\u001a\u00020\u00052\n\u0010\t\u001a\u00060\u0007j\u0002`\bH\u0016\u00a2\u0006\u0004\b\u000b\u0010\fJ#\u0010\r\u001a\u00020\n2\u0006\u0010\u0006\u001a\u00020\u00052\n\u0010\t\u001a\u00060\u0007j\u0002`\bH\u0016\u00a2\u0006\u0004\b\r\u0010\fJ\u000f\u0010\u000e\u001a\u00020\nH\u0016\u00a2\u0006\u0004\b\u000e\u0010\u000fJ\u000f\u0010\u0011\u001a\u00020\u0010H\u0016\u00a2\u0006\u0004\b\u0011\u0010\u0012J\u0017\u0010\u0016\u001a\u00020\u00152\b\b\u0002\u0010\u0014\u001a\u00020\u0013\u00a2\u0006\u0004\b\u0016\u0010\u0017J\u0015\u0010\u0018\u001a\u00020\u00152\u0006\u0010\u0014\u001a\u00020\u0013\u00a2\u0006\u0004\b\u0018\u0010\u0017J+\u0010\u001e\u001a\u00020\n2\n\u0010\t\u001a\u00060\u0007j\u0002`\b2\u0006\u0010\u0006\u001a\u00020\u00192\u0006\u0010\u001b\u001a\u00020\u001aH\u0000\u00a2\u0006\u0004\b\u001c\u0010\u001dJ\u000f\u0010 \u001a\u00020\nH\u0000\u00a2\u0006\u0004\b\u001f\u0010\u000fJ\u0017\u0010%\u001a\u00020\n2\u0006\u0010\"\u001a\u00020!H\u0000\u00a2\u0006\u0004\b#\u0010$J\u000f\u0010'\u001a\u00020\nH\u0000\u00a2\u0006\u0004\b&\u0010\u000fR\u0016\u0010(\u001a\u00020\u00108\u0002@\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b(\u0010)R\u0016\u0010*\u001a\u00020\u00028\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b*\u0010+R\u0016\u0010,\u001a\u00020\u00138\u0002@\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b,\u0010-R\u0016\u0010.\u001a\u00020\u00138\u0002@\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b.\u0010-R\u0016\u0010/\u001a\u00020!8\u0002@\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b/\u00100R\u0016\u00104\u001a\u0002018V@\u0016X\u0096\u0004\u00a2\u0006\u0006\u001a\u0004\b2\u00103\u00a8\u00069"}, d2 = {"Lkotlinx/coroutines/scheduling/ExperimentalCoroutineDispatcher;", "Lkotlinx/coroutines/ExecutorCoroutineDispatcher;", "Lkotlinx/coroutines/scheduling/CoroutineScheduler;", "createScheduler", "()Lkotlinx/coroutines/scheduling/CoroutineScheduler;", "Lkotlin/coroutines/CoroutineContext;", AnnoConst.Constructor_Context, "Ljava/lang/Runnable;", "Lkotlinx/coroutines/Runnable;", JDReactConstant.BLOCK, "", "dispatch", "(Lkotlin/coroutines/CoroutineContext;Ljava/lang/Runnable;)V", "dispatchYield", "close", "()V", "", "toString", "()Ljava/lang/String;", "", "parallelism", "Lkotlinx/coroutines/CoroutineDispatcher;", "blocking", "(I)Lkotlinx/coroutines/CoroutineDispatcher;", "limited", "Lkotlinx/coroutines/scheduling/TaskContext;", "", "tailDispatch", "dispatchWithContext$kotlinx_coroutines_core", "(Ljava/lang/Runnable;Lkotlinx/coroutines/scheduling/TaskContext;Z)V", "dispatchWithContext", "usePrivateScheduler$kotlinx_coroutines_core", "usePrivateScheduler", "", "timeout", "shutdown$kotlinx_coroutines_core", "(J)V", "shutdown", "restore$kotlinx_coroutines_core", "restore", "schedulerName", "Ljava/lang/String;", "coroutineScheduler", "Lkotlinx/coroutines/scheduling/CoroutineScheduler;", "maxPoolSize", "I", "corePoolSize", "idleWorkerKeepAliveNs", "J", "Ljava/util/concurrent/Executor;", "getExecutor", "()Ljava/util/concurrent/Executor;", "executor", "<init>", "(IIJLjava/lang/String;)V", "(IILjava/lang/String;)V", "(II)V", "kotlinx-coroutines-core"}, k = 1, mv = {1, 4, 0})
/* renamed from: kotlinx.coroutines.scheduling.ExperimentalCoroutineDispatcher  reason: from toString */
/* loaded from: classes.dex */
public class [scheduler =  extends ExecutorCoroutineDispatcher {
    private final int corePoolSize;

    /* renamed from: coroutineScheduler  reason: from kotlin metadata and from toString */
    private CoroutineScheduler [scheduler ;
    private final long idleWorkerKeepAliveNs;
    private final int maxPoolSize;
    private final String schedulerName;

    public /* synthetic */ [scheduler = (int i2, int i3, long j2, String str, int i4, DefaultConstructorMarker defaultConstructorMarker) {
        this(i2, i3, j2, (i4 & 8) != 0 ? "CoroutineScheduler" : str);
    }

    public static /* synthetic */ CoroutineDispatcher blocking$default([scheduler =  r0, int i2, int i3, Object obj) {
        if (obj == null) {
            if ((i3 & 1) != 0) {
                i2 = TasksKt.BLOCKING_DEFAULT_PARALLELISM;
            }
            return r0.blocking(i2);
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: blocking");
    }

    private final CoroutineScheduler createScheduler() {
        return new CoroutineScheduler(this.corePoolSize, this.maxPoolSize, this.idleWorkerKeepAliveNs, this.schedulerName);
    }

    @NotNull
    public final CoroutineDispatcher blocking(int parallelism) {
        if (parallelism > 0) {
            return new LimitingDispatcher(this, parallelism, null, 1);
        }
        throw new IllegalArgumentException(("Expected positive parallelism level, but have " + parallelism).toString());
    }

    @Override // kotlinx.coroutines.ExecutorCoroutineDispatcher, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        this.[scheduler .close();
    }

    @Override // kotlinx.coroutines.CoroutineDispatcher
    /* renamed from: dispatch */
    public void mo1254dispatch(@NotNull CoroutineContext context, @NotNull Runnable block) {
        try {
            CoroutineScheduler.dispatch$default(this.[scheduler , block, null, false, 6, null);
        } catch (RejectedExecutionException unused) {
            DefaultExecutor.INSTANCE.mo1254dispatch(context, block);
        }
    }

    public final void dispatchWithContext$kotlinx_coroutines_core(@NotNull Runnable block, @NotNull TaskContext context, boolean tailDispatch) {
        try {
            this.[scheduler .dispatch(block, context, tailDispatch);
        } catch (RejectedExecutionException unused) {
            DefaultExecutor.INSTANCE.enqueue(this.[scheduler .createTask$kotlinx_coroutines_core(block, context));
        }
    }

    @Override // kotlinx.coroutines.CoroutineDispatcher
    public void dispatchYield(@NotNull CoroutineContext context, @NotNull Runnable block) {
        try {
            CoroutineScheduler.dispatch$default(this.[scheduler , block, null, true, 2, null);
        } catch (RejectedExecutionException unused) {
            DefaultExecutor.INSTANCE.dispatchYield(context, block);
        }
    }

    @Override // kotlinx.coroutines.ExecutorCoroutineDispatcher
    @NotNull
    public Executor getExecutor() {
        return this.[scheduler ;
    }

    @NotNull
    public final CoroutineDispatcher limited(int parallelism) {
        if (parallelism > 0) {
            if (parallelism <= this.corePoolSize) {
                return new LimitingDispatcher(this, parallelism, null, 0);
            }
            throw new IllegalArgumentException(("Expected parallelism level lesser than core pool size (" + this.corePoolSize + "), but have " + parallelism).toString());
        }
        throw new IllegalArgumentException(("Expected positive parallelism level, but have " + parallelism).toString());
    }

    public final void restore$kotlinx_coroutines_core() {
        usePrivateScheduler$kotlinx_coroutines_core();
    }

    public final synchronized void shutdown$kotlinx_coroutines_core(long timeout) {
        this.[scheduler .shutdown(timeout);
    }

    @Override // kotlinx.coroutines.CoroutineDispatcher
    @NotNull
    public String toString() {
        return super.toString() + "[scheduler = " + this.[scheduler  + ']';
    }

    public final synchronized void usePrivateScheduler$kotlinx_coroutines_core() {
        this.[scheduler .shutdown(1000L);
        this.[scheduler  = createScheduler();
    }

    public [scheduler = (int i2, int i3, long j2, @NotNull String str) {
        this.corePoolSize = i2;
        this.maxPoolSize = i3;
        this.idleWorkerKeepAliveNs = j2;
        this.schedulerName = str;
        this.[scheduler  = createScheduler();
    }

    public /* synthetic */ [scheduler = (int i2, int i3, String str, int i4, DefaultConstructorMarker defaultConstructorMarker) {
        this((i4 & 1) != 0 ? TasksKt.CORE_POOL_SIZE : i2, (i4 & 2) != 0 ? TasksKt.MAX_POOL_SIZE : i3, (i4 & 4) != 0 ? TasksKt.DEFAULT_SCHEDULER_NAME : str);
    }

    public [scheduler = (int i2, int i3, @NotNull String str) {
        this(i2, i3, TasksKt.IDLE_WORKER_KEEP_ALIVE_NS, str);
    }

    public /* synthetic */ [scheduler = (int i2, int i3, int i4, DefaultConstructorMarker defaultConstructorMarker) {
        this((i4 & 1) != 0 ? TasksKt.CORE_POOL_SIZE : i2, (i4 & 2) != 0 ? TasksKt.MAX_POOL_SIZE : i3);
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Binary compatibility for Ktor 1.0-beta")
    public /* synthetic */ [scheduler = (int i2, int i3) {
        this(i2, i3, TasksKt.IDLE_WORKER_KEEP_ALIVE_NS, null, 8, null);
    }
}

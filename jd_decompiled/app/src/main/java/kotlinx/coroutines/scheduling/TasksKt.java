package kotlinx.coroutines.scheduling;

import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.jvm.JvmField;
import kotlin.ranges.RangesKt___RangesKt;
import kotlinx.coroutines.internal.SystemPropsKt;
import kotlinx.coroutines.internal.SystemPropsKt__SystemProps_commonKt;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u0006\"\u001b\u0010\u0002\u001a\u00020\u0001*\u00020\u00008\u00c0\u0002@\u0000X\u0080\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0002\u0010\u0003\"\u0016\u0010\u0005\u001a\u00020\u00048\u0000@\u0001X\u0081\u0004\u00a2\u0006\u0006\n\u0004\b\u0005\u0010\u0006\"\u0016\u0010\u0007\u001a\u00020\u00048\u0000@\u0000X\u0080T\u00a2\u0006\u0006\n\u0004\b\u0007\u0010\u0006\"\u0016\u0010\t\u001a\u00020\b8\u0000@\u0000X\u0080T\u00a2\u0006\u0006\n\u0004\b\t\u0010\n\"\u0016\u0010\f\u001a\u00020\u000b8\u0000@\u0000X\u0081\u000e\u00a2\u0006\u0006\n\u0004\b\f\u0010\r\"\u0016\u0010\u000e\u001a\u00020\u00048\u0000@\u0000X\u0080T\u00a2\u0006\u0006\n\u0004\b\u000e\u0010\u0006\"\u0016\u0010\u000f\u001a\u00020\u00048\u0000@\u0001X\u0081\u0004\u00a2\u0006\u0006\n\u0004\b\u000f\u0010\u0006\"\u0016\u0010\u0011\u001a\u00020\u00108\u0000@\u0001X\u0081\u0004\u00a2\u0006\u0006\n\u0004\b\u0011\u0010\u0012\"\u0016\u0010\u0013\u001a\u00020\b8\u0000@\u0000X\u0080T\u00a2\u0006\u0006\n\u0004\b\u0013\u0010\n\"\u0016\u0010\u0014\u001a\u00020\u00108\u0000@\u0001X\u0081\u0004\u00a2\u0006\u0006\n\u0004\b\u0014\u0010\u0012\"\u0016\u0010\u0015\u001a\u00020\u00048\u0000@\u0001X\u0081\u0004\u00a2\u0006\u0006\n\u0004\b\u0015\u0010\u0006\u00a8\u0006\u0016"}, d2 = {"Lkotlinx/coroutines/scheduling/Task;", "", "isBlocking", "(Lkotlinx/coroutines/scheduling/Task;)Z", "", "CORE_POOL_SIZE", "I", "TASK_NON_BLOCKING", "", "DEFAULT_SCHEDULER_NAME", "Ljava/lang/String;", "Lkotlinx/coroutines/scheduling/TimeSource;", "schedulerTimeSource", "Lkotlinx/coroutines/scheduling/TimeSource;", "TASK_PROBABLY_BLOCKING", "MAX_POOL_SIZE", "", "IDLE_WORKER_KEEP_ALIVE_NS", "J", "DEFAULT_DISPATCHER_NAME", "WORK_STEALING_TIME_RESOLUTION_NS", "BLOCKING_DEFAULT_PARALLELISM", "kotlinx-coroutines-core"}, k = 2, mv = {1, 4, 0})
/* loaded from: classes11.dex */
public final class TasksKt {
    @JvmField
    public static final int BLOCKING_DEFAULT_PARALLELISM;
    @JvmField
    public static final int CORE_POOL_SIZE;
    @NotNull
    public static final String DEFAULT_DISPATCHER_NAME = "Dispatchers.Default";
    @NotNull
    public static final String DEFAULT_SCHEDULER_NAME = "DefaultDispatcher";
    @JvmField
    public static final long IDLE_WORKER_KEEP_ALIVE_NS;
    @JvmField
    public static final int MAX_POOL_SIZE;
    public static final int TASK_NON_BLOCKING = 0;
    public static final int TASK_PROBABLY_BLOCKING = 1;
    @JvmField
    public static final long WORK_STEALING_TIME_RESOLUTION_NS;
    @JvmField
    @NotNull
    public static TimeSource schedulerTimeSource;

    static {
        long systemProp$default;
        int systemProp$default2;
        int coerceAtLeast;
        int systemProp$default3;
        int coerceIn;
        int systemProp$default4;
        long systemProp$default5;
        systemProp$default = SystemPropsKt__SystemProps_commonKt.systemProp$default("kotlinx.coroutines.scheduler.resolution.ns", 100000L, 0L, 0L, 12, (Object) null);
        WORK_STEALING_TIME_RESOLUTION_NS = systemProp$default;
        systemProp$default2 = SystemPropsKt__SystemProps_commonKt.systemProp$default("kotlinx.coroutines.scheduler.blocking.parallelism", 16, 0, 0, 12, (Object) null);
        BLOCKING_DEFAULT_PARALLELISM = systemProp$default2;
        coerceAtLeast = RangesKt___RangesKt.coerceAtLeast(SystemPropsKt.getAVAILABLE_PROCESSORS(), 2);
        systemProp$default3 = SystemPropsKt__SystemProps_commonKt.systemProp$default("kotlinx.coroutines.scheduler.core.pool.size", coerceAtLeast, 1, 0, 8, (Object) null);
        CORE_POOL_SIZE = systemProp$default3;
        coerceIn = RangesKt___RangesKt.coerceIn(SystemPropsKt.getAVAILABLE_PROCESSORS() * 128, systemProp$default3, (int) CoroutineScheduler.MAX_SUPPORTED_POOL_SIZE);
        systemProp$default4 = SystemPropsKt__SystemProps_commonKt.systemProp$default("kotlinx.coroutines.scheduler.max.pool.size", coerceIn, 0, (int) CoroutineScheduler.MAX_SUPPORTED_POOL_SIZE, 4, (Object) null);
        MAX_POOL_SIZE = systemProp$default4;
        TimeUnit timeUnit = TimeUnit.SECONDS;
        systemProp$default5 = SystemPropsKt__SystemProps_commonKt.systemProp$default("kotlinx.coroutines.scheduler.keep.alive.sec", 60L, 0L, 0L, 12, (Object) null);
        IDLE_WORKER_KEEP_ALIVE_NS = timeUnit.toNanos(systemProp$default5);
        schedulerTimeSource = NanoTimeSource.INSTANCE;
    }

    public static final boolean isBlocking(@NotNull Task task) {
        return task.taskContext.getTaskMode() == 1;
    }
}

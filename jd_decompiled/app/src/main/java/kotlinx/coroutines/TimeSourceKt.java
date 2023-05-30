package kotlinx.coroutines;

import com.jingdong.common.jdreactFramework.JDReactConstant;
import java.util.concurrent.locks.LockSupport;
import kotlin.Metadata;
import kotlin.internal.InlineOnly;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u00004\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\u001a\u0010\u0010\u0001\u001a\u00020\u0000H\u0081\b\u00a2\u0006\u0004\b\u0001\u0010\u0002\u001a\u0010\u0010\u0003\u001a\u00020\u0000H\u0081\b\u00a2\u0006\u0004\b\u0003\u0010\u0002\u001a \u0010\u0007\u001a\u00060\u0004j\u0002`\u00052\n\u0010\u0006\u001a\u00060\u0004j\u0002`\u0005H\u0081\b\u00a2\u0006\u0004\b\u0007\u0010\b\u001a\u0010\u0010\n\u001a\u00020\tH\u0081\b\u00a2\u0006\u0004\b\n\u0010\u000b\u001a\u0010\u0010\f\u001a\u00020\tH\u0081\b\u00a2\u0006\u0004\b\f\u0010\u000b\u001a\u0010\u0010\r\u001a\u00020\tH\u0081\b\u00a2\u0006\u0004\b\r\u0010\u000b\u001a\u0010\u0010\u000e\u001a\u00020\tH\u0081\b\u00a2\u0006\u0004\b\u000e\u0010\u000b\u001a \u0010\u0012\u001a\u00020\t2\u0006\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0011\u001a\u00020\u0000H\u0081\b\u00a2\u0006\u0004\b\u0012\u0010\u0013\u001a\u0018\u0010\u0016\u001a\u00020\t2\u0006\u0010\u0015\u001a\u00020\u0014H\u0081\b\u00a2\u0006\u0004\b\u0016\u0010\u0017\"$\u0010\u0019\u001a\u0004\u0018\u00010\u00188\u0000@\u0000X\u0080\u000e\u00a2\u0006\u0012\n\u0004\b\u0019\u0010\u001a\u001a\u0004\b\u001b\u0010\u001c\"\u0004\b\u001d\u0010\u001e\u00a8\u0006\u001f"}, d2 = {"", "currentTimeMillis", "()J", "nanoTime", "Ljava/lang/Runnable;", "Lkotlinx/coroutines/Runnable;", JDReactConstant.BLOCK, "wrapTask", "(Ljava/lang/Runnable;)Ljava/lang/Runnable;", "", "trackTask", "()V", "unTrackTask", "registerTimeLoopThread", "unregisterTimeLoopThread", "", "blocker", "nanos", "parkNanos", "(Ljava/lang/Object;J)V", "Ljava/lang/Thread;", "thread", "unpark", "(Ljava/lang/Thread;)V", "Lkotlinx/coroutines/TimeSource;", "timeSource", "Lkotlinx/coroutines/TimeSource;", "getTimeSource", "()Lkotlinx/coroutines/TimeSource;", "setTimeSource", "(Lkotlinx/coroutines/TimeSource;)V", "kotlinx-coroutines-core"}, k = 2, mv = {1, 4, 0})
/* loaded from: classes11.dex */
public final class TimeSourceKt {
    @Nullable
    private static TimeSource timeSource;

    @InlineOnly
    private static final long currentTimeMillis() {
        TimeSource timeSource2 = getTimeSource();
        return timeSource2 != null ? timeSource2.currentTimeMillis() : System.currentTimeMillis();
    }

    @Nullable
    public static final TimeSource getTimeSource() {
        return timeSource;
    }

    @InlineOnly
    private static final long nanoTime() {
        TimeSource timeSource2 = getTimeSource();
        return timeSource2 != null ? timeSource2.nanoTime() : System.nanoTime();
    }

    @InlineOnly
    private static final void parkNanos(Object obj, long j2) {
        TimeSource timeSource2 = getTimeSource();
        if (timeSource2 != null) {
            timeSource2.parkNanos(obj, j2);
        } else {
            LockSupport.parkNanos(obj, j2);
        }
    }

    @InlineOnly
    private static final void registerTimeLoopThread() {
        TimeSource timeSource2 = getTimeSource();
        if (timeSource2 != null) {
            timeSource2.registerTimeLoopThread();
        }
    }

    public static final void setTimeSource(@Nullable TimeSource timeSource2) {
        timeSource = timeSource2;
    }

    @InlineOnly
    private static final void trackTask() {
        TimeSource timeSource2 = getTimeSource();
        if (timeSource2 != null) {
            timeSource2.trackTask();
        }
    }

    @InlineOnly
    private static final void unTrackTask() {
        TimeSource timeSource2 = getTimeSource();
        if (timeSource2 != null) {
            timeSource2.unTrackTask();
        }
    }

    @InlineOnly
    private static final void unpark(Thread thread) {
        TimeSource timeSource2 = getTimeSource();
        if (timeSource2 != null) {
            timeSource2.unpark(thread);
        } else {
            LockSupport.unpark(thread);
        }
    }

    @InlineOnly
    private static final void unregisterTimeLoopThread() {
        TimeSource timeSource2 = getTimeSource();
        if (timeSource2 != null) {
            timeSource2.unregisterTimeLoopThread();
        }
    }

    @InlineOnly
    private static final Runnable wrapTask(Runnable runnable) {
        Runnable wrapTask;
        TimeSource timeSource2 = getTimeSource();
        return (timeSource2 == null || (wrapTask = timeSource2.wrapTask(runnable)) == null) ? runnable : wrapTask;
    }
}

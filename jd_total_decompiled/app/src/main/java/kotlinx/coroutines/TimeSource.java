package kotlinx.coroutines;

import com.jingdong.common.jdreactFramework.JDReactConstant;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0004\b`\u0018\u00002\u00020\u0001J\u000f\u0010\u0003\u001a\u00020\u0002H&\u00a2\u0006\u0004\b\u0003\u0010\u0004J\u000f\u0010\u0005\u001a\u00020\u0002H&\u00a2\u0006\u0004\b\u0005\u0010\u0004J\u001f\u0010\t\u001a\u00060\u0006j\u0002`\u00072\n\u0010\b\u001a\u00060\u0006j\u0002`\u0007H&\u00a2\u0006\u0004\b\t\u0010\nJ\u000f\u0010\f\u001a\u00020\u000bH&\u00a2\u0006\u0004\b\f\u0010\rJ\u000f\u0010\u000e\u001a\u00020\u000bH&\u00a2\u0006\u0004\b\u000e\u0010\rJ\u000f\u0010\u000f\u001a\u00020\u000bH&\u00a2\u0006\u0004\b\u000f\u0010\rJ\u000f\u0010\u0010\u001a\u00020\u000bH&\u00a2\u0006\u0004\b\u0010\u0010\rJ\u001f\u0010\u0013\u001a\u00020\u000b2\u0006\u0010\u0011\u001a\u00020\u00012\u0006\u0010\u0012\u001a\u00020\u0002H&\u00a2\u0006\u0004\b\u0013\u0010\u0014J\u0017\u0010\u0017\u001a\u00020\u000b2\u0006\u0010\u0016\u001a\u00020\u0015H&\u00a2\u0006\u0004\b\u0017\u0010\u0018\u00a8\u0006\u0019"}, d2 = {"Lkotlinx/coroutines/TimeSource;", "", "", "currentTimeMillis", "()J", "nanoTime", "Ljava/lang/Runnable;", "Lkotlinx/coroutines/Runnable;", JDReactConstant.BLOCK, "wrapTask", "(Ljava/lang/Runnable;)Ljava/lang/Runnable;", "", "trackTask", "()V", "unTrackTask", "registerTimeLoopThread", "unregisterTimeLoopThread", "blocker", "nanos", "parkNanos", "(Ljava/lang/Object;J)V", "Ljava/lang/Thread;", "thread", "unpark", "(Ljava/lang/Thread;)V", "kotlinx-coroutines-core"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes11.dex */
public interface TimeSource {
    long currentTimeMillis();

    long nanoTime();

    void parkNanos(@NotNull Object blocker, long nanos);

    void registerTimeLoopThread();

    void trackTask();

    void unTrackTask();

    void unpark(@NotNull Thread thread);

    void unregisterTimeLoopThread();

    @NotNull
    Runnable wrapTask(@NotNull Runnable block);
}

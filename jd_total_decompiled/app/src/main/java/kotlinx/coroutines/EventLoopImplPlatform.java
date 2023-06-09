package kotlinx.coroutines;

import java.util.concurrent.locks.LockSupport;
import kotlin.Metadata;
import kotlinx.coroutines.EventLoopImplBase;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\b \u0018\u00002\u00020\u0001B\u0007\u00a2\u0006\u0004\b\u000f\u0010\u0004J\u000f\u0010\u0003\u001a\u00020\u0002H\u0004\u00a2\u0006\u0004\b\u0003\u0010\u0004J\u001f\u0010\t\u001a\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\u0007H\u0004\u00a2\u0006\u0004\b\t\u0010\nR\u0016\u0010\u000e\u001a\u00020\u000b8$@$X\u00a4\u0004\u00a2\u0006\u0006\u001a\u0004\b\f\u0010\r\u00a8\u0006\u0010"}, d2 = {"Lkotlinx/coroutines/EventLoopImplPlatform;", "Lkotlinx/coroutines/EventLoop;", "", "unpark", "()V", "", "now", "Lkotlinx/coroutines/EventLoopImplBase$DelayedTask;", "delayedTask", "reschedule", "(JLkotlinx/coroutines/EventLoopImplBase$DelayedTask;)V", "Ljava/lang/Thread;", "getThread", "()Ljava/lang/Thread;", "thread", "<init>", "kotlinx-coroutines-core"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes11.dex */
public abstract class EventLoopImplPlatform extends EventLoop {
    @NotNull
    protected abstract Thread getThread();

    /* JADX INFO: Access modifiers changed from: protected */
    public final void reschedule(long now, @NotNull EventLoopImplBase.Delayed[nanos= delayedTask) {
        if (DebugKt.getASSERTIONS_ENABLED()) {
            if (!(this != DefaultExecutor.INSTANCE)) {
                throw new AssertionError();
            }
        }
        DefaultExecutor.INSTANCE.schedule(now, delayedTask);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void unpark() {
        Thread thread = getThread();
        if (Thread.currentThread() != thread) {
            TimeSource timeSource = TimeSourceKt.getTimeSource();
            if (timeSource != null) {
                timeSource.unpark(thread);
            } else {
                LockSupport.unpark(thread);
            }
        }
    }
}

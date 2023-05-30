package kotlinx.coroutines.debug.internal;

import com.jingdong.amon.router.annotation.AnnoConst;
import com.jingdong.common.XView2.common.XView2Constants;
import java.util.List;
import kotlin.Metadata;
import kotlin.PublishedApi;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.jvm.internal.CoroutineStackFrame;
import kotlin.jvm.JvmName;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0001\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010%\u001a\u00020$\u0012\u0006\u0010\u0019\u001a\u00020\u0018\u00a2\u0006\u0004\b&\u0010'R\u001f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u00028G@\u0006\u00a2\u0006\f\n\u0004\b\u0004\u0010\u0005\u001a\u0004\b\u0004\u0010\u0006R\u001b\u0010\b\u001a\u0004\u0018\u00010\u00078\u0006@\u0006\u00a2\u0006\f\n\u0004\b\b\u0010\t\u001a\u0004\b\n\u0010\u000bR\u0019\u0010\r\u001a\u00020\f8\u0006@\u0006\u00a2\u0006\f\n\u0004\b\r\u0010\u000e\u001a\u0004\b\u000f\u0010\u0010R\u001f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00030\u00028\u0006@\u0006\u00a2\u0006\f\n\u0004\b\u0011\u0010\u0005\u001a\u0004\b\u0012\u0010\u0006R\u001b\u0010\u0014\u001a\u0004\u0018\u00010\u00138\u0006@\u0006\u00a2\u0006\f\n\u0004\b\u0014\u0010\u0015\u001a\u0004\b\u0016\u0010\u0017R\u0019\u0010\u0019\u001a\u00020\u00188\u0006@\u0006\u00a2\u0006\f\n\u0004\b\u0019\u0010\u001a\u001a\u0004\b\u001b\u0010\u001cR\u001b\u0010\u001d\u001a\u0004\u0018\u00010\u00078\u0006@\u0006\u00a2\u0006\f\n\u0004\b\u001d\u0010\t\u001a\u0004\b\u001e\u0010\u000bR\u0019\u0010 \u001a\u00020\u001f8\u0006@\u0006\u00a2\u0006\f\n\u0004\b \u0010!\u001a\u0004\b\"\u0010#\u00a8\u0006("}, d2 = {"Lkotlinx/coroutines/debug/internal/DebugCoroutineInfo;", "", "", "Ljava/lang/StackTraceElement;", "lastObservedStackTrace", "Ljava/util/List;", "()Ljava/util/List;", "Lkotlin/coroutines/jvm/internal/CoroutineStackFrame;", "lastObservedFrame", "Lkotlin/coroutines/jvm/internal/CoroutineStackFrame;", "getLastObservedFrame", "()Lkotlin/coroutines/jvm/internal/CoroutineStackFrame;", "", XView2Constants.STATE, "Ljava/lang/String;", "getState", "()Ljava/lang/String;", "creationStackTrace", "getCreationStackTrace", "Ljava/lang/Thread;", "lastObservedThread", "Ljava/lang/Thread;", "getLastObservedThread", "()Ljava/lang/Thread;", "Lkotlin/coroutines/CoroutineContext;", AnnoConst.Constructor_Context, "Lkotlin/coroutines/CoroutineContext;", "getContext", "()Lkotlin/coroutines/CoroutineContext;", "creationStackBottom", "getCreationStackBottom", "", "sequenceNumber", "J", "getSequenceNumber", "()J", "Lkotlinx/coroutines/debug/internal/DebugCoroutineInfoImpl;", "source", "<init>", "(Lkotlinx/coroutines/debug/internal/DebugCoroutineInfoImpl;Lkotlin/coroutines/CoroutineContext;)V", "kotlinx-coroutines-core"}, k = 1, mv = {1, 4, 0})
@PublishedApi
/* loaded from: classes11.dex */
public final class DebugCoroutineInfo {
    @NotNull
    private final CoroutineContext context;
    @Nullable
    private final CoroutineStackFrame creationStackBottom;
    @NotNull
    private final List<StackTraceElement> creationStackTrace;
    @Nullable
    private final CoroutineStackFrame lastObservedFrame;
    @NotNull
    private final List<StackTraceElement> lastObservedStackTrace;
    @Nullable
    private final Thread lastObservedThread;
    private final long sequenceNumber;
    @NotNull
    private final String state;

    public DebugCoroutineInfo(@NotNull kotlinx.coroutines.debug.internal.DebugCoroutineInfo debugCoroutineInfo, @NotNull CoroutineContext coroutineContext) {
        this.context = coroutineContext;
        this.creationStackBottom = debugCoroutineInfo.getCreationStackBottom();
        this.sequenceNumber = debugCoroutineInfo.sequenceNumber;
        this.creationStackTrace = debugCoroutineInfo.getCreationStackTrace();
        this.state = debugCoroutineInfo.get_state();
        this.lastObservedThread = debugCoroutineInfo.lastObservedThread;
        this.lastObservedFrame = debugCoroutineInfo.getLastObservedFrame$kotlinx_coroutines_core();
        this.lastObservedStackTrace = debugCoroutineInfo.lastObservedStackTrace();
    }

    @NotNull
    public final CoroutineContext getContext() {
        return this.context;
    }

    @Nullable
    public final CoroutineStackFrame getCreationStackBottom() {
        return this.creationStackBottom;
    }

    @NotNull
    public final List<StackTraceElement> getCreationStackTrace() {
        return this.creationStackTrace;
    }

    @Nullable
    public final CoroutineStackFrame getLastObservedFrame() {
        return this.lastObservedFrame;
    }

    @Nullable
    public final Thread getLastObservedThread() {
        return this.lastObservedThread;
    }

    public final long getSequenceNumber() {
        return this.sequenceNumber;
    }

    @NotNull
    public final String getState() {
        return this.state;
    }

    @JvmName(name = "lastObservedStackTrace")
    @NotNull
    public final List<StackTraceElement> lastObservedStackTrace() {
        return this.lastObservedStackTrace;
    }
}

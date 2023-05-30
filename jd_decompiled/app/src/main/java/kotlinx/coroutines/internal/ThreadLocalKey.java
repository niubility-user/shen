package kotlinx.coroutines.internal;

import com.jd.viewkit.templates.view.helper.JDViewKitEventHelper;
import kotlin.Metadata;
import kotlin.PublishedApi;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\b\u0081\b\u0018\u00002\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00020\u0001B\u0013\u0012\n\u0010\u0006\u001a\u0006\u0012\u0002\b\u00030\u0003\u00a2\u0006\u0004\b\u0015\u0010\u0016J\u0014\u0010\u0004\u001a\u0006\u0012\u0002\b\u00030\u0003H\u00c2\u0003\u00a2\u0006\u0004\b\u0004\u0010\u0005J\u001e\u0010\u0007\u001a\u00020\u00002\f\b\u0002\u0010\u0006\u001a\u0006\u0012\u0002\b\u00030\u0003H\u00c6\u0001\u00a2\u0006\u0004\b\u0007\u0010\bJ\u0010\u0010\n\u001a\u00020\tH\u00d6\u0001\u00a2\u0006\u0004\b\n\u0010\u000bJ\u0010\u0010\r\u001a\u00020\fH\u00d6\u0001\u00a2\u0006\u0004\b\r\u0010\u000eJ\u001a\u0010\u0012\u001a\u00020\u00112\b\u0010\u0010\u001a\u0004\u0018\u00010\u000fH\u00d6\u0003\u00a2\u0006\u0004\b\u0012\u0010\u0013R\u001a\u0010\u0006\u001a\u0006\u0012\u0002\b\u00030\u00038\u0002@\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0006\u0010\u0014\u00a8\u0006\u0017"}, d2 = {"Lkotlinx/coroutines/internal/ThreadLocalKey;", "Lkotlin/coroutines/CoroutineContext$Key;", "Lkotlinx/coroutines/internal/ThreadLocalElement;", "Ljava/lang/ThreadLocal;", "component1", "()Ljava/lang/ThreadLocal;", "threadLocal", JDViewKitEventHelper.ActionType_Copy, "(Ljava/lang/ThreadLocal;)Lkotlinx/coroutines/internal/ThreadLocalKey;", "", "toString", "()Ljava/lang/String;", "", "hashCode", "()I", "", "other", "", "equals", "(Ljava/lang/Object;)Z", "Ljava/lang/ThreadLocal;", "<init>", "(Ljava/lang/ThreadLocal;)V", "kotlinx-coroutines-core"}, k = 1, mv = {1, 4, 0})
@PublishedApi
/* loaded from: classes11.dex */
public final /* data */ class ThreadLocalKey implements CoroutineContext.Key<ThreadLocal<?>> {
    private final ThreadLocal<?> threadLocal;

    public ThreadLocalKey(@NotNull ThreadLocal<?> threadLocal) {
        this.threadLocal = threadLocal;
    }

    private final ThreadLocal<?> component1() {
        return this.threadLocal;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ ThreadLocalKey copy$default(ThreadLocalKey threadLocalKey, ThreadLocal threadLocal, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            threadLocal = threadLocalKey.threadLocal;
        }
        return threadLocalKey.copy(threadLocal);
    }

    @NotNull
    public final ThreadLocalKey copy(@NotNull ThreadLocal<?> threadLocal) {
        return new ThreadLocalKey(threadLocal);
    }

    public boolean equals(@Nullable Object other) {
        if (this != other) {
            return (other instanceof ThreadLocalKey) && Intrinsics.areEqual(this.threadLocal, ((ThreadLocalKey) other).threadLocal);
        }
        return true;
    }

    public int hashCode() {
        ThreadLocal<?> threadLocal = this.threadLocal;
        if (threadLocal != null) {
            return threadLocal.hashCode();
        }
        return 0;
    }

    @NotNull
    public String toString() {
        return "ThreadLocalKey(threadLocal=" + this.threadLocal + ")";
    }
}

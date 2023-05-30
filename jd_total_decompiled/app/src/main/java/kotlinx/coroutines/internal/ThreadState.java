package kotlinx.coroutines.internal;

import com.jingdong.amon.router.annotation.AnnoConst;
import com.jingdong.common.entity.personal.PersonalConstants;
import com.jingdong.jdsdk.a.a;
import kotlin.Metadata;
import kotlin.coroutines.CoroutineContext;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0006\b\u0002\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u000b\u001a\u00020\n\u0012\u0006\u0010\u0015\u001a\u00020\u0012\u00a2\u0006\u0004\b\u0016\u0010\u0017J\u0017\u0010\u0004\u001a\u00020\u00032\b\u0010\u0002\u001a\u0004\u0018\u00010\u0001\u00a2\u0006\u0004\b\u0004\u0010\u0005J\u000f\u0010\u0006\u001a\u0004\u0018\u00010\u0001\u00a2\u0006\u0004\b\u0006\u0010\u0007J\r\u0010\b\u001a\u00020\u0003\u00a2\u0006\u0004\b\b\u0010\tR\u0019\u0010\u000b\u001a\u00020\n8\u0006@\u0006\u00a2\u0006\f\n\u0004\b\u000b\u0010\f\u001a\u0004\b\r\u0010\u000eR\u001e\u0010\u0010\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u000f8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u0010\u0010\u0011R\u0016\u0010\u0013\u001a\u00020\u00128\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u0013\u0010\u0014\u00a8\u0006\u0018"}, d2 = {"Lkotlinx/coroutines/internal/ThreadState;", "", "value", "", "append", "(Ljava/lang/Object;)V", "take", "()Ljava/lang/Object;", "start", "()V", "Lkotlin/coroutines/CoroutineContext;", AnnoConst.Constructor_Context, "Lkotlin/coroutines/CoroutineContext;", "getContext", "()Lkotlin/coroutines/CoroutineContext;", "", a.a, "[Ljava/lang/Object;", "", "i", "I", PersonalConstants.ICON_STYLE_N, "<init>", "(Lkotlin/coroutines/CoroutineContext;I)V", "kotlinx-coroutines-core"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes11.dex */
final class ThreadState {
    private Object[] a;
    @NotNull
    private final CoroutineContext context;
    private int i;

    public ThreadState(@NotNull CoroutineContext coroutineContext, int i2) {
        this.context = coroutineContext;
        this.a = new Object[i2];
    }

    public final void append(@Nullable Object value) {
        Object[] objArr = this.a;
        int i2 = this.i;
        this.i = i2 + 1;
        objArr[i2] = value;
    }

    @NotNull
    public final CoroutineContext getContext() {
        return this.context;
    }

    public final void start() {
        this.i = 0;
    }

    @Nullable
    public final Object take() {
        Object[] objArr = this.a;
        int i2 = this.i;
        this.i = i2 + 1;
        return objArr[i2];
    }
}

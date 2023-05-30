package kotlinx.coroutines.flow;

import kotlin.Metadata;
import kotlinx.coroutines.ExperimentalCoroutinesApi;
import kotlinx.coroutines.flow.internal.NullSurrogateKt;
import kotlinx.coroutines.internal.Symbol;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001c\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0005\u001a#\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0002\"\u0004\b\u0000\u0010\u00002\u0006\u0010\u0001\u001a\u00028\u0000H\u0007\u00a2\u0006\u0004\b\u0003\u0010\u0004\"\u001c\u0010\u0006\u001a\u00020\u00058\u0002@\u0003X\u0083\u0004\u00a2\u0006\f\n\u0004\b\u0006\u0010\u0007\u0012\u0004\b\b\u0010\t\"\u0016\u0010\u000b\u001a\u00020\n8\u0002@\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u000b\u0010\f\"\u001c\u0010\r\u001a\u00020\u00058\u0002@\u0003X\u0083\u0004\u00a2\u0006\f\n\u0004\b\r\u0010\u0007\u0012\u0004\b\u000e\u0010\t\u00a8\u0006\u000f"}, d2 = {"T", "value", "Lkotlinx/coroutines/flow/MutableStateFlow;", "MutableStateFlow", "(Ljava/lang/Object;)Lkotlinx/coroutines/flow/MutableStateFlow;", "Lkotlinx/coroutines/internal/Symbol;", "NONE", "Lkotlinx/coroutines/internal/Symbol;", "NONE$annotations", "()V", "", "INITIAL_SIZE", "I", "PENDING", "PENDING$annotations", "kotlinx-coroutines-core"}, k = 2, mv = {1, 4, 0})
/* loaded from: classes11.dex */
public final class StateFlowKt {
    private static final int INITIAL_SIZE = 2;
    private static final Symbol NONE = new Symbol("NONE");
    private static final Symbol PENDING = new Symbol("PENDING");

    @ExperimentalCoroutinesApi
    @NotNull
    public static final <T> MutableStateFlow<T> MutableStateFlow(T t) {
        if (t == null) {
            t = (T) NullSurrogateKt.NULL;
        }
        return new StateFlowImpl(t);
    }

    private static /* synthetic */ void NONE$annotations() {
    }

    private static /* synthetic */ void PENDING$annotations() {
    }

    public static final /* synthetic */ Symbol access$getNONE$p() {
        return NONE;
    }

    public static final /* synthetic */ Symbol access$getPENDING$p() {
        return PENDING;
    }
}

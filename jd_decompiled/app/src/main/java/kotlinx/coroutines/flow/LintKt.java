package kotlinx.coroutines.flow;

import com.jingdong.amon.router.annotation.AnnoConst;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.coroutines.CoroutineContext;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\u001a-\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004\"\u0004\b\u0000\u0010\u0000*\b\u0012\u0004\u0012\u00028\u00000\u00012\u0006\u0010\u0003\u001a\u00020\u0002H\u0007\u00a2\u0006\u0004\b\u0005\u0010\u0006\u001a%\u0010\u0007\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004\"\u0004\b\u0000\u0010\u0000*\b\u0012\u0004\u0012\u00028\u00000\u0001H\u0007\u00a2\u0006\u0004\b\u0007\u0010\b\u001a%\u0010\t\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004\"\u0004\b\u0000\u0010\u0000*\b\u0012\u0004\u0012\u00028\u00000\u0001H\u0007\u00a2\u0006\u0004\b\t\u0010\b\u00a8\u0006\n"}, d2 = {"T", "Lkotlinx/coroutines/flow/StateFlow;", "Lkotlin/coroutines/CoroutineContext;", AnnoConst.Constructor_Context, "Lkotlinx/coroutines/flow/Flow;", "flowOn", "(Lkotlinx/coroutines/flow/StateFlow;Lkotlin/coroutines/CoroutineContext;)Lkotlinx/coroutines/flow/Flow;", "conflate", "(Lkotlinx/coroutines/flow/StateFlow;)Lkotlinx/coroutines/flow/Flow;", "distinctUntilChanged", "kotlinx-coroutines-core"}, k = 2, mv = {1, 4, 0})
/* loaded from: classes.dex */
public final class LintKt {
    @Deprecated(level = DeprecationLevel.ERROR, message = "Applying conflate operator to StateFlow has no effect. See StateFlow documentation on Operator Fusion.", replaceWith = @ReplaceWith(expression = "this", imports = {}))
    @NotNull
    public static final <T> Flow<T> conflate(@NotNull StateFlow<? extends T> stateFlow) {
        FlowKt.noImpl();
        throw null;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Applying distinctUntilChanged operator to StateFlow has no effect. See StateFlow documentation on Operator Fusion.", replaceWith = @ReplaceWith(expression = "this", imports = {}))
    @NotNull
    public static final <T> Flow<T> distinctUntilChanged(@NotNull StateFlow<? extends T> stateFlow) {
        FlowKt.noImpl();
        throw null;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Applying flowOn operator to StateFlow has no effect. See StateFlow documentation on Operator Fusion.", replaceWith = @ReplaceWith(expression = "this", imports = {}))
    @NotNull
    public static final <T> Flow<T> flowOn(@NotNull StateFlow<? extends T> stateFlow, @NotNull CoroutineContext coroutineContext) {
        FlowKt.noImpl();
        throw null;
    }
}

package kotlinx.coroutines;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a?\u0010\u0007\u001a\u00020\u0005*#\u0012\u0015\u0012\u0013\u0018\u00010\u0001\u00a2\u0006\f\b\u0002\u0012\b\b\u0003\u0012\u0004\b\b(\u0004\u0012\u0004\u0012\u00020\u00050\u0000j\u0002`\u00062\b\u0010\u0004\u001a\u0004\u0018\u00010\u0001H\u0080\b\u00a2\u0006\u0004\b\u0007\u0010\b\"<\u0010\f\u001a#\u0012\u0015\u0012\u0013\u0018\u00010\u0001\u00a2\u0006\f\b\u0002\u0012\b\b\u0003\u0012\u0004\b\b(\u0004\u0012\u0004\u0012\u00020\u00050\u0000j\u0002`\u0006*\u00020\t8\u00c0\u0002@\u0000X\u0080\u0004\u00a2\u0006\u0006\u001a\u0004\b\n\u0010\u000b\"<\u0010\f\u001a#\u0012\u0015\u0012\u0013\u0018\u00010\u0001\u00a2\u0006\f\b\u0002\u0012\b\b\u0003\u0012\u0004\b\b(\u0004\u0012\u0004\u0012\u00020\u00050\u0000j\u0002`\u0006*\u00020\r8\u00c0\u0002@\u0000X\u0080\u0004\u00a2\u0006\u0006\u001a\u0004\b\n\u0010\u000e\u00a8\u0006\u000f"}, d2 = {"Lkotlin/Function1;", "", "Lkotlin/ParameterName;", "name", "cause", "", "Lkotlinx/coroutines/CompletionHandler;", "invokeIt", "(Lkotlin/jvm/functions/Function1;Ljava/lang/Throwable;)V", "Lkotlinx/coroutines/CompletionHandlerBase;", "getAsHandler", "(Lkotlinx/coroutines/CompletionHandlerBase;)Lkotlin/jvm/functions/Function1;", "asHandler", "Lkotlinx/coroutines/CancelHandlerBase;", "(Lkotlinx/coroutines/CancelHandlerBase;)Lkotlin/jvm/functions/Function1;", "kotlinx-coroutines-core"}, k = 2, mv = {1, 4, 0})
/* loaded from: classes11.dex */
public final class CompletionHandlerKt {
    @NotNull
    public static final Function1<Throwable, Unit> getAsHandler(@NotNull CancelHandlerBase cancelHandlerBase) {
        return cancelHandlerBase;
    }

    @NotNull
    public static final Function1<Throwable, Unit> getAsHandler(@NotNull CompletionHandlerBase completionHandlerBase) {
        return completionHandlerBase;
    }

    public static final void invokeIt(@NotNull Function1<? super Throwable, Unit> function1, @Nullable Throwable th) {
        function1.invoke(th);
    }
}

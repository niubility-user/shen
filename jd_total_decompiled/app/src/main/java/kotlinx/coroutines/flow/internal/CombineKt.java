package kotlinx.coroutines.flow.internal;

import com.jingdong.jdsdk.a.a;
import kotlin.Metadata;
import kotlin.PublishedApi;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.functions.Function4;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.channels.ProduceKt;
import kotlinx.coroutines.channels.ReceiveChannel;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.internal.Symbol;
import kotlinx.coroutines.selects.SelectBuilder;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000j\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0011\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\u001a\u000f\u0010\u0001\u001a\u00020\u0000H\u0000\u00a2\u0006\u0004\b\u0001\u0010\u0002\u001a\u00a4\u0001\u0010\u0014\u001a\u00020\u0010\"\u0004\b\u0000\u0010\u0003\"\u0004\b\u0001\u0010\u0004\"\u0004\b\u0002\u0010\u0005*\b\u0012\u0004\u0012\u00028\u00020\u00062\f\u0010\b\u001a\b\u0012\u0004\u0012\u00028\u00000\u00072\f\u0010\t\u001a\b\u0012\u0004\u0012\u00028\u00010\u00072W\u0010\u0013\u001aS\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00020\u0006\u0012\u0013\u0012\u00118\u0000\u00a2\u0006\f\b\u000b\u0012\b\b\f\u0012\u0004\b\b(\r\u0012\u0013\u0012\u00118\u0001\u00a2\u0006\f\b\u000b\u0012\b\b\f\u0012\u0004\b\b(\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00100\u000f\u0012\u0006\u0012\u0004\u0018\u00010\u00110\n\u00a2\u0006\u0002\b\u0012H\u0080@\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u0014\u0010\u0015\u001a\u0090\u0001\u0010\u001c\u001a\u00020\u0010\"\u0004\b\u0000\u0010\u0005\"\u0004\b\u0001\u0010\u0016*\b\u0012\u0004\u0012\u00028\u00000\u00062\u0014\u0010\u0018\u001a\u0010\u0012\f\b\u0001\u0012\b\u0012\u0004\u0012\u00028\u00010\u00070\u00172\u0014\u0010\u001a\u001a\u0010\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00018\u00010\u00170\u001929\u0010\u0013\u001a5\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u0006\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00010\u0017\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00100\u000f\u0012\u0006\u0012\u0004\u0018\u00010\u00110\u001b\u00a2\u0006\u0002\b\u0012H\u0081@\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u001c\u0010\u001d\u001ax\u0010&\u001a\u00020\u0010*\b\u0012\u0004\u0012\u00020\u00100\u001e2\u0006\u0010 \u001a\u00020\u001f2\f\u0010\"\u001a\b\u0012\u0004\u0012\u00020\u00110!2\u000e\b\u0004\u0010#\u001a\b\u0012\u0004\u0012\u00020\u00100\u001923\b\b\u0010&\u001a-\b\u0001\u0012\u0013\u0012\u00110\u0011\u00a2\u0006\f\b\u000b\u0012\b\b\f\u0012\u0004\b\b(%\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00100\u000f\u0012\u0006\u0012\u0004\u0018\u00010\u00110$H\u0082\b\u00f8\u0001\u0000\u00a2\u0006\u0004\b&\u0010'\u001a%\u0010*\u001a\b\u0012\u0004\u0012\u00020\u00110!*\u00020(2\n\u0010)\u001a\u0006\u0012\u0002\b\u00030\u0007H\u0002\u00a2\u0006\u0004\b*\u0010+\u001ap\u0010-\u001a\b\u0012\u0004\u0012\u00028\u00020\u0007\"\u0004\b\u0000\u0010\u0003\"\u0004\b\u0001\u0010\u0004\"\u0004\b\u0002\u0010\u00052\f\u0010)\u001a\b\u0012\u0004\u0012\u00028\u00000\u00072\f\u0010,\u001a\b\u0012\u0004\u0012\u00028\u00010\u00072(\u0010\u0013\u001a$\b\u0001\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00020\u000f\u0012\u0006\u0012\u0004\u0018\u00010\u00110\u001bH\u0000\u00f8\u0001\u0000\u00a2\u0006\u0004\b-\u0010.\u001a%\u0010/\u001a\b\u0012\u0004\u0012\u00020\u00110!*\u00020(2\n\u0010)\u001a\u0006\u0012\u0002\b\u00030\u0007H\u0002\u00a2\u0006\u0004\b/\u0010+\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u00060"}, d2 = {"Lkotlinx/coroutines/internal/Symbol;", "getNull", "()Lkotlinx/coroutines/internal/Symbol;", "T1", "T2", "R", "Lkotlinx/coroutines/flow/FlowCollector;", "Lkotlinx/coroutines/flow/Flow;", "first", "second", "Lkotlin/Function4;", "Lkotlin/ParameterName;", "name", a.a, "b", "Lkotlin/coroutines/Continuation;", "", "", "Lkotlin/ExtensionFunctionType;", "transform", "combineTransformInternal", "(Lkotlinx/coroutines/flow/FlowCollector;Lkotlinx/coroutines/flow/Flow;Lkotlinx/coroutines/flow/Flow;Lkotlin/jvm/functions/Function4;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "T", "", "flows", "Lkotlin/Function0;", "arrayFactory", "Lkotlin/Function3;", "combineInternal", "(Lkotlinx/coroutines/flow/FlowCollector;[Lkotlinx/coroutines/flow/Flow;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function3;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Lkotlinx/coroutines/selects/SelectBuilder;", "", "isClosed", "Lkotlinx/coroutines/channels/ReceiveChannel;", "channel", "onClosed", "Lkotlin/Function2;", "value", "onReceive", "(Lkotlinx/coroutines/selects/SelectBuilder;ZLkotlinx/coroutines/channels/ReceiveChannel;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function2;)V", "Lkotlinx/coroutines/CoroutineScope;", "flow", "asFairChannel", "(Lkotlinx/coroutines/CoroutineScope;Lkotlinx/coroutines/flow/Flow;)Lkotlinx/coroutines/channels/ReceiveChannel;", "flow2", "zipImpl", "(Lkotlinx/coroutines/flow/Flow;Lkotlinx/coroutines/flow/Flow;Lkotlin/jvm/functions/Function3;)Lkotlinx/coroutines/flow/Flow;", "asChannel", "kotlinx-coroutines-core"}, k = 2, mv = {1, 4, 0})
/* loaded from: classes11.dex */
public final class CombineKt {
    public static final ReceiveChannel<Object> asChannel(@NotNull CoroutineScope coroutineScope, Flow<?> flow) {
        return ProduceKt.produce$default(coroutineScope, null, 0, new CombineKt$asChannel$1(flow, null), 3, null);
    }

    public static final ReceiveChannel<Object> asFairChannel(@NotNull CoroutineScope coroutineScope, Flow<?> flow) {
        return ProduceKt.produce$default(coroutineScope, null, 0, new CombineKt$asFairChannel$1(flow, null), 3, null);
    }

    @PublishedApi
    @Nullable
    public static final <R, T> Object combineInternal(@NotNull FlowCollector<? super R> flowCollector, @NotNull Flow<? extends T>[] flowArr, @NotNull Function0<T[]> function0, @NotNull Function3<? super FlowCollector<? super R>, ? super T[], ? super Continuation<? super Unit>, ? extends Object> function3, @NotNull Continuation<? super Unit> continuation) {
        Object coroutine_suspended;
        Object coroutineScope = CoroutineScopeKt.coroutineScope(new CombineKt$combineInternal$2(flowCollector, flowArr, function0, function3, null), continuation);
        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        return coroutineScope == coroutine_suspended ? coroutineScope : Unit.INSTANCE;
    }

    @Nullable
    public static final <T1, T2, R> Object combineTransformInternal(@NotNull FlowCollector<? super R> flowCollector, @NotNull Flow<? extends T1> flow, @NotNull Flow<? extends T2> flow2, @NotNull Function4<? super FlowCollector<? super R>, ? super T1, ? super T2, ? super Continuation<? super Unit>, ? extends Object> function4, @NotNull Continuation<? super Unit> continuation) {
        Object coroutine_suspended;
        Object coroutineScope = CoroutineScopeKt.coroutineScope(new CombineKt$combineTransformInternal$2(flowCollector, flow, flow2, function4, null), continuation);
        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        return coroutineScope == coroutine_suspended ? coroutineScope : Unit.INSTANCE;
    }

    @NotNull
    public static final Symbol getNull() {
        return NullSurrogateKt.NULL;
    }

    public static final void onReceive(@NotNull SelectBuilder<? super Unit> selectBuilder, boolean z, ReceiveChannel<? extends Object> receiveChannel, Function0<Unit> function0, Function2<Object, ? super Continuation<? super Unit>, ? extends Object> function2) {
        if (z) {
            return;
        }
        selectBuilder.invoke(receiveChannel.getOnReceiveOrNull(), new CombineKt$onReceive$1(function0, function2, null));
    }

    @NotNull
    public static final <T1, T2, R> Flow<R> zipImpl(@NotNull Flow<? extends T1> flow, @NotNull Flow<? extends T2> flow2, @NotNull Function3<? super T1, ? super T2, ? super Continuation<? super R>, ? extends Object> function3) {
        return new CombineKt$zipImpl$$inlined$unsafeFlow$1(flow, flow2, function3);
    }
}

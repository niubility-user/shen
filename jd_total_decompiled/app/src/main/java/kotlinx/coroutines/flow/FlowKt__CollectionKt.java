package kotlinx.coroutines.flow;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000(\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010!\n\u0000\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010#\n\u0002\u0010\"\n\u0002\b\u0002\n\u0002\u0010\u001f\n\u0002\b\u0004\u001a9\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004\"\u0004\b\u0000\u0010\u0000*\b\u0012\u0004\u0012\u00028\u00000\u00012\u000e\b\u0002\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0002H\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u0005\u0010\u0006\u001a9\u0010\t\u001a\b\u0012\u0004\u0012\u00028\u00000\b\"\u0004\b\u0000\u0010\u0000*\b\u0012\u0004\u0012\u00028\u00000\u00012\u000e\b\u0002\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0007H\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0004\b\t\u0010\n\u001a=\u0010\r\u001a\u00028\u0001\"\u0004\b\u0000\u0010\u0000\"\u0010\b\u0001\u0010\f*\n\u0012\u0006\b\u0000\u0012\u00028\u00000\u000b*\b\u0012\u0004\u0012\u00028\u00000\u00012\u0006\u0010\u0003\u001a\u00028\u0001H\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0004\b\r\u0010\u000e\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u000f"}, d2 = {"T", "Lkotlinx/coroutines/flow/Flow;", "", "destination", "", "toList", "(Lkotlinx/coroutines/flow/Flow;Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "", "", "toSet", "(Lkotlinx/coroutines/flow/Flow;Ljava/util/Set;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "", "C", "toCollection", "(Lkotlinx/coroutines/flow/Flow;Ljava/util/Collection;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "kotlinx-coroutines-core"}, k = 5, mv = {1, 4, 0}, xs = "kotlinx/coroutines/flow/FlowKt")
/* loaded from: classes11.dex */
public final /* synthetic */ class FlowKt__CollectionKt {
    /* JADX WARN: Removed duplicated region for block: B:29:0x0023  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x003e  */
    @Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final <T, C extends Collection<? super T>> Object toCollection(@NotNull Flow<? extends T> flow, @NotNull final C c2, @NotNull Continuation<? super C> continuation) {
        FlowKt__CollectionKt$toCollection$1 flowKt__CollectionKt$toCollection$1;
        Object coroutine_suspended;
        int i2;
        if (continuation instanceof FlowKt__CollectionKt$toCollection$1) {
            flowKt__CollectionKt$toCollection$1 = (FlowKt__CollectionKt$toCollection$1) continuation;
            int i3 = flowKt__CollectionKt$toCollection$1.label;
            if ((i3 & Integer.MIN_VALUE) != 0) {
                flowKt__CollectionKt$toCollection$1.label = i3 - Integer.MIN_VALUE;
                Object obj = flowKt__CollectionKt$toCollection$1.result;
                coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i2 = flowKt__CollectionKt$toCollection$1.label;
                if (i2 == 0) {
                    if (i2 == 1) {
                        Flow flow2 = (Flow) flowKt__CollectionKt$toCollection$1.L$2;
                        Collection collection = (Collection) flowKt__CollectionKt$toCollection$1.L$1;
                        Flow flow3 = (Flow) flowKt__CollectionKt$toCollection$1.L$0;
                        ResultKt.throwOnFailure(obj);
                        return collection;
                    }
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
                FlowCollector<T> flowCollector = new FlowCollector<T>() { // from class: kotlinx.coroutines.flow.FlowKt__CollectionKt$toCollection$$inlined$collect$1
                    @Override // kotlinx.coroutines.flow.FlowCollector
                    @Nullable
                    public Object emit(Object obj2, @NotNull Continuation continuation2) {
                        c2.add(obj2);
                        return Unit.INSTANCE;
                    }
                };
                flowKt__CollectionKt$toCollection$1.L$0 = flow;
                flowKt__CollectionKt$toCollection$1.L$1 = c2;
                flowKt__CollectionKt$toCollection$1.L$2 = flow;
                flowKt__CollectionKt$toCollection$1.label = 1;
                return flow.collect(flowCollector, flowKt__CollectionKt$toCollection$1) == coroutine_suspended ? coroutine_suspended : c2;
            }
        }
        flowKt__CollectionKt$toCollection$1 = new FlowKt__CollectionKt$toCollection$1(continuation);
        Object obj2 = flowKt__CollectionKt$toCollection$1.result;
        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i2 = flowKt__CollectionKt$toCollection$1.label;
        if (i2 == 0) {
        }
    }

    @Nullable
    public static final <T> Object toList(@NotNull Flow<? extends T> flow, @NotNull List<T> list, @NotNull Continuation<? super List<? extends T>> continuation) {
        return FlowKt.toCollection(flow, list, continuation);
    }

    public static /* synthetic */ Object toList$default(Flow flow, List list, Continuation continuation, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            list = new ArrayList();
        }
        return FlowKt.toList(flow, list, continuation);
    }

    @Nullable
    public static final <T> Object toSet(@NotNull Flow<? extends T> flow, @NotNull Set<T> set, @NotNull Continuation<? super Set<? extends T>> continuation) {
        return FlowKt.toCollection(flow, set, continuation);
    }

    public static /* synthetic */ Object toSet$default(Flow flow, Set set, Continuation continuation, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            set = new LinkedHashSet();
        }
        return FlowKt.toSet(flow, set, continuation);
    }
}

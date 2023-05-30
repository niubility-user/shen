package kotlinx.coroutines.flow;

import java.util.NoSuchElementException;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.InlineMarker;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.flow.internal.AbortFlowException;
import kotlinx.coroutines.flow.internal.FlowExceptions_commonKt;
import kotlinx.coroutines.flow.internal.NullSurrogateKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: Access modifiers changed from: package-private */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000,\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\u001au\u0010\u000b\u001a\u00028\u0000\"\u0004\b\u0000\u0010\u0000\"\b\b\u0001\u0010\u0001*\u00028\u0000*\b\u0012\u0004\u0012\u00028\u00010\u00022F\u0010\n\u001aB\b\u0001\u0012\u0013\u0012\u00118\u0000\u00a2\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u0006\u0012\u0013\u0012\u00118\u0001\u00a2\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u0007\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\b\u0012\u0006\u0012\u0004\u0018\u00010\t0\u0003H\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u000b\u0010\f\u001a{\u0010\u0010\u001a\u00028\u0001\"\u0004\b\u0000\u0010\u0001\"\u0004\b\u0001\u0010\r*\b\u0012\u0004\u0012\u00028\u00000\u00022\u0006\u0010\u000e\u001a\u00028\u00012H\b\u0004\u0010\n\u001aB\b\u0001\u0012\u0013\u0012\u00118\u0001\u00a2\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u000f\u0012\u0013\u0012\u00118\u0000\u00a2\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u0007\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00010\b\u0012\u0006\u0012\u0004\u0018\u00010\t0\u0003H\u0086H\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u0010\u0010\u0011\u001a#\u0010\u0012\u001a\u00028\u0000\"\u0004\b\u0000\u0010\u0001*\b\u0012\u0004\u0012\u00028\u00000\u0002H\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u0012\u0010\u0013\u001a)\u0010\u0014\u001a\u0004\u0018\u00018\u0000\"\b\b\u0000\u0010\u0001*\u00020\t*\b\u0012\u0004\u0012\u00028\u00000\u0002H\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u0014\u0010\u0013\u001a#\u0010\u0015\u001a\u00028\u0000\"\u0004\b\u0000\u0010\u0001*\b\u0012\u0004\u0012\u00028\u00000\u0002H\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u0015\u0010\u0013\u001aG\u0010\u0015\u001a\u00028\u0000\"\u0004\b\u0000\u0010\u0001*\b\u0012\u0004\u0012\u00028\u00000\u00022\"\u0010\u0018\u001a\u001e\b\u0001\u0012\u0004\u0012\u00028\u0000\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00170\b\u0012\u0006\u0012\u0004\u0018\u00010\t0\u0016H\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u0015\u0010\u0019\u001a)\u0010\u001a\u001a\u0004\u0018\u00018\u0000\"\b\b\u0000\u0010\u0001*\u00020\t*\b\u0012\u0004\u0012\u00028\u00000\u0002H\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u001a\u0010\u0013\u001aM\u0010\u001a\u001a\u0004\u0018\u00018\u0000\"\b\b\u0000\u0010\u0001*\u00020\t*\b\u0012\u0004\u0012\u00028\u00000\u00022\"\u0010\u0018\u001a\u001e\b\u0001\u0012\u0004\u0012\u00028\u0000\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00170\b\u0012\u0006\u0012\u0004\u0018\u00010\t0\u0016H\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u001a\u0010\u0019\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u001b"}, d2 = {"S", "T", "Lkotlinx/coroutines/flow/Flow;", "Lkotlin/Function3;", "Lkotlin/ParameterName;", "name", "accumulator", "value", "Lkotlin/coroutines/Continuation;", "", "operation", "reduce", "(Lkotlinx/coroutines/flow/Flow;Lkotlin/jvm/functions/Function3;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "R", "initial", "acc", "fold", "(Lkotlinx/coroutines/flow/Flow;Ljava/lang/Object;Lkotlin/jvm/functions/Function3;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "single", "(Lkotlinx/coroutines/flow/Flow;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "singleOrNull", "first", "Lkotlin/Function2;", "", "predicate", "(Lkotlinx/coroutines/flow/Flow;Lkotlin/jvm/functions/Function2;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "firstOrNull", "kotlinx-coroutines-core"}, k = 5, mv = {1, 4, 0}, xs = "kotlinx/coroutines/flow/FlowKt")
/* loaded from: classes11.dex */
public final /* synthetic */ class FlowKt__ReduceKt {
    /* JADX WARN: Removed duplicated region for block: B:10:0x0023  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0043  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0074 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:29:0x0075  */
    @Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final <T> Object first(@NotNull Flow<? extends T> flow, @NotNull Continuation<? super T> continuation) {
        FlowKt__ReduceKt$first$1 flowKt__ReduceKt$first$1;
        Object coroutine_suspended;
        int i2;
        Ref.ObjectRef objectRef;
        AbortFlowException e2;
        FlowCollector<T> flowCollector;
        T t;
        if (continuation instanceof FlowKt__ReduceKt$first$1) {
            flowKt__ReduceKt$first$1 = (FlowKt__ReduceKt$first$1) continuation;
            int i3 = flowKt__ReduceKt$first$1.label;
            if ((i3 & Integer.MIN_VALUE) != 0) {
                flowKt__ReduceKt$first$1.label = i3 - Integer.MIN_VALUE;
                Object obj = flowKt__ReduceKt$first$1.result;
                coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i2 = flowKt__ReduceKt$first$1.label;
                if (i2 != 0) {
                    ResultKt.throwOnFailure(obj);
                    final Ref.ObjectRef objectRef2 = new Ref.ObjectRef();
                    objectRef2.element = (T) NullSurrogateKt.NULL;
                    FlowCollector<T> flowCollector2 = new FlowCollector<T>() { // from class: kotlinx.coroutines.flow.FlowKt__ReduceKt$first$$inlined$collectWhile$1
                        /* JADX WARN: Multi-variable type inference failed */
                        @Override // kotlinx.coroutines.flow.FlowCollector
                        @Nullable
                        public Object emit(Object obj2, @NotNull Continuation continuation2) {
                            Ref.ObjectRef.this.element = obj2;
                            if (Boxing.boxBoolean(false).booleanValue()) {
                                return Unit.INSTANCE;
                            }
                            throw new AbortFlowException(this);
                        }
                    };
                    try {
                        flowKt__ReduceKt$first$1.L$0 = flow;
                        flowKt__ReduceKt$first$1.L$1 = objectRef2;
                        flowKt__ReduceKt$first$1.L$2 = flow;
                        flowKt__ReduceKt$first$1.L$3 = flowCollector2;
                        flowKt__ReduceKt$first$1.label = 1;
                        if (flow.collect(flowCollector2, flowKt__ReduceKt$first$1) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        objectRef = objectRef2;
                    } catch (AbortFlowException e3) {
                        objectRef = objectRef2;
                        e2 = e3;
                        flowCollector = flowCollector2;
                        FlowExceptions_commonKt.checkOwnership(e2, flowCollector);
                        t = objectRef.element;
                        if (t == NullSurrogateKt.NULL) {
                        }
                    }
                } else if (i2 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                } else {
                    flowCollector = (FlowKt__ReduceKt$first$$inlined$collectWhile$1) flowKt__ReduceKt$first$1.L$3;
                    Flow flow2 = (Flow) flowKt__ReduceKt$first$1.L$2;
                    objectRef = (Ref.ObjectRef) flowKt__ReduceKt$first$1.L$1;
                    Flow flow3 = (Flow) flowKt__ReduceKt$first$1.L$0;
                    try {
                        ResultKt.throwOnFailure(obj);
                    } catch (AbortFlowException e4) {
                        e2 = e4;
                        FlowExceptions_commonKt.checkOwnership(e2, flowCollector);
                        t = objectRef.element;
                        if (t == NullSurrogateKt.NULL) {
                        }
                    }
                }
                t = objectRef.element;
                if (t == NullSurrogateKt.NULL) {
                    return t;
                }
                throw new NoSuchElementException("Expected at least one element");
            }
        }
        flowKt__ReduceKt$first$1 = new FlowKt__ReduceKt$first$1(continuation);
        Object obj2 = flowKt__ReduceKt$first$1.result;
        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i2 = flowKt__ReduceKt$first$1.label;
        if (i2 != 0) {
        }
        t = objectRef.element;
        if (t == NullSurrogateKt.NULL) {
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0023  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0043  */
    @Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final <T> Object firstOrNull(@NotNull Flow<? extends T> flow, @NotNull Continuation<? super T> continuation) {
        FlowKt__ReduceKt$firstOrNull$1 flowKt__ReduceKt$firstOrNull$1;
        Object coroutine_suspended;
        int i2;
        Ref.ObjectRef objectRef;
        AbortFlowException e2;
        FlowCollector<T> flowCollector;
        if (continuation instanceof FlowKt__ReduceKt$firstOrNull$1) {
            flowKt__ReduceKt$firstOrNull$1 = (FlowKt__ReduceKt$firstOrNull$1) continuation;
            int i3 = flowKt__ReduceKt$firstOrNull$1.label;
            if ((i3 & Integer.MIN_VALUE) != 0) {
                flowKt__ReduceKt$firstOrNull$1.label = i3 - Integer.MIN_VALUE;
                Object obj = flowKt__ReduceKt$firstOrNull$1.result;
                coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i2 = flowKt__ReduceKt$firstOrNull$1.label;
                if (i2 != 0) {
                    ResultKt.throwOnFailure(obj);
                    final Ref.ObjectRef objectRef2 = new Ref.ObjectRef();
                    objectRef2.element = null;
                    FlowCollector<T> flowCollector2 = new FlowCollector<T>() { // from class: kotlinx.coroutines.flow.FlowKt__ReduceKt$firstOrNull$$inlined$collectWhile$1
                        /* JADX WARN: Multi-variable type inference failed */
                        @Override // kotlinx.coroutines.flow.FlowCollector
                        @Nullable
                        public Object emit(Object obj2, @NotNull Continuation continuation2) {
                            Ref.ObjectRef.this.element = obj2;
                            if (Boxing.boxBoolean(false).booleanValue()) {
                                return Unit.INSTANCE;
                            }
                            throw new AbortFlowException(this);
                        }
                    };
                    try {
                        flowKt__ReduceKt$firstOrNull$1.L$0 = flow;
                        flowKt__ReduceKt$firstOrNull$1.L$1 = objectRef2;
                        flowKt__ReduceKt$firstOrNull$1.L$2 = flow;
                        flowKt__ReduceKt$firstOrNull$1.L$3 = flowCollector2;
                        flowKt__ReduceKt$firstOrNull$1.label = 1;
                        if (flow.collect(flowCollector2, flowKt__ReduceKt$firstOrNull$1) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        objectRef = objectRef2;
                    } catch (AbortFlowException e3) {
                        objectRef = objectRef2;
                        e2 = e3;
                        flowCollector = flowCollector2;
                        FlowExceptions_commonKt.checkOwnership(e2, flowCollector);
                        return objectRef.element;
                    }
                } else if (i2 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                } else {
                    flowCollector = (FlowKt__ReduceKt$firstOrNull$$inlined$collectWhile$1) flowKt__ReduceKt$firstOrNull$1.L$3;
                    Flow flow2 = (Flow) flowKt__ReduceKt$firstOrNull$1.L$2;
                    objectRef = (Ref.ObjectRef) flowKt__ReduceKt$firstOrNull$1.L$1;
                    Flow flow3 = (Flow) flowKt__ReduceKt$firstOrNull$1.L$0;
                    try {
                        ResultKt.throwOnFailure(obj);
                    } catch (AbortFlowException e4) {
                        e2 = e4;
                        FlowExceptions_commonKt.checkOwnership(e2, flowCollector);
                        return objectRef.element;
                    }
                }
                return objectRef.element;
            }
        }
        flowKt__ReduceKt$firstOrNull$1 = new FlowKt__ReduceKt$firstOrNull$1(continuation);
        Object obj2 = flowKt__ReduceKt$firstOrNull$1.result;
        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i2 = flowKt__ReduceKt$firstOrNull$1.label;
        if (i2 != 0) {
        }
        return objectRef.element;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:10:0x0023  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0043  */
    @Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final <T, R> Object fold(@NotNull Flow<? extends T> flow, R r, @NotNull Function3<? super R, ? super T, ? super Continuation<? super R>, ? extends Object> function3, @NotNull Continuation<? super R> continuation) {
        FlowKt__ReduceKt$fold$1 flowKt__ReduceKt$fold$1;
        Object coroutine_suspended;
        int i2;
        Ref.ObjectRef objectRef;
        if (continuation instanceof FlowKt__ReduceKt$fold$1) {
            flowKt__ReduceKt$fold$1 = (FlowKt__ReduceKt$fold$1) continuation;
            int i3 = flowKt__ReduceKt$fold$1.label;
            if ((i3 & Integer.MIN_VALUE) != 0) {
                flowKt__ReduceKt$fold$1.label = i3 - Integer.MIN_VALUE;
                Object obj = flowKt__ReduceKt$fold$1.result;
                coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i2 = flowKt__ReduceKt$fold$1.label;
                if (i2 != 0) {
                    ResultKt.throwOnFailure(obj);
                    Ref.ObjectRef objectRef2 = new Ref.ObjectRef();
                    objectRef2.element = r;
                    FlowCollector<? super Object> flowKt__ReduceKt$fold$$inlined$collect$1 = new FlowKt__ReduceKt$fold$$inlined$collect$1<>(objectRef2, function3);
                    flowKt__ReduceKt$fold$1.L$0 = flow;
                    flowKt__ReduceKt$fold$1.L$1 = r;
                    flowKt__ReduceKt$fold$1.L$2 = function3;
                    flowKt__ReduceKt$fold$1.L$3 = objectRef2;
                    flowKt__ReduceKt$fold$1.L$4 = flow;
                    flowKt__ReduceKt$fold$1.label = 1;
                    if (flow.collect(flowKt__ReduceKt$fold$$inlined$collect$1, flowKt__ReduceKt$fold$1) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    objectRef = objectRef2;
                } else if (i2 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                } else {
                    Flow flow2 = (Flow) flowKt__ReduceKt$fold$1.L$4;
                    objectRef = (Ref.ObjectRef) flowKt__ReduceKt$fold$1.L$3;
                    Function3 function32 = (Function3) flowKt__ReduceKt$fold$1.L$2;
                    Object obj2 = flowKt__ReduceKt$fold$1.L$1;
                    Flow flow3 = (Flow) flowKt__ReduceKt$fold$1.L$0;
                    ResultKt.throwOnFailure(obj);
                }
                return objectRef.element;
            }
        }
        flowKt__ReduceKt$fold$1 = new FlowKt__ReduceKt$fold$1(continuation);
        Object obj3 = flowKt__ReduceKt$fold$1.result;
        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i2 = flowKt__ReduceKt$fold$1.label;
        if (i2 != 0) {
        }
        return objectRef.element;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Nullable
    private static final Object fold$$forInline(@NotNull Flow flow, Object obj, @NotNull Function3 function3, @NotNull Continuation continuation) {
        Ref.ObjectRef objectRef = new Ref.ObjectRef();
        objectRef.element = obj;
        FlowKt__ReduceKt$fold$$inlined$collect$1 flowKt__ReduceKt$fold$$inlined$collect$1 = new FlowKt__ReduceKt$fold$$inlined$collect$1(objectRef, function3);
        InlineMarker.mark(0);
        flow.collect(flowKt__ReduceKt$fold$$inlined$collect$1, continuation);
        InlineMarker.mark(2);
        InlineMarker.mark(1);
        return objectRef.element;
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0023  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0041  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x006a A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:21:0x006b  */
    /* JADX WARN: Type inference failed for: r2v1, types: [kotlinx.coroutines.internal.Symbol, T] */
    @Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final <S, T extends S> Object reduce(@NotNull Flow<? extends T> flow, @NotNull Function3<? super S, ? super T, ? super Continuation<? super S>, ? extends Object> function3, @NotNull Continuation<? super S> continuation) {
        FlowKt__ReduceKt$reduce$1 flowKt__ReduceKt$reduce$1;
        Object coroutine_suspended;
        int i2;
        Ref.ObjectRef objectRef;
        T t;
        if (continuation instanceof FlowKt__ReduceKt$reduce$1) {
            flowKt__ReduceKt$reduce$1 = (FlowKt__ReduceKt$reduce$1) continuation;
            int i3 = flowKt__ReduceKt$reduce$1.label;
            if ((i3 & Integer.MIN_VALUE) != 0) {
                flowKt__ReduceKt$reduce$1.label = i3 - Integer.MIN_VALUE;
                Object obj = flowKt__ReduceKt$reduce$1.result;
                coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i2 = flowKt__ReduceKt$reduce$1.label;
                if (i2 != 0) {
                    ResultKt.throwOnFailure(obj);
                    Ref.ObjectRef objectRef2 = new Ref.ObjectRef();
                    objectRef2.element = NullSurrogateKt.NULL;
                    FlowCollector<? super Object> flowKt__ReduceKt$reduce$$inlined$collect$1 = new FlowKt__ReduceKt$reduce$$inlined$collect$1<>(objectRef2, function3);
                    flowKt__ReduceKt$reduce$1.L$0 = flow;
                    flowKt__ReduceKt$reduce$1.L$1 = function3;
                    flowKt__ReduceKt$reduce$1.L$2 = objectRef2;
                    flowKt__ReduceKt$reduce$1.L$3 = flow;
                    flowKt__ReduceKt$reduce$1.label = 1;
                    if (flow.collect(flowKt__ReduceKt$reduce$$inlined$collect$1, flowKt__ReduceKt$reduce$1) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    objectRef = objectRef2;
                } else if (i2 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                } else {
                    Flow flow2 = (Flow) flowKt__ReduceKt$reduce$1.L$3;
                    objectRef = (Ref.ObjectRef) flowKt__ReduceKt$reduce$1.L$2;
                    Function3 function32 = (Function3) flowKt__ReduceKt$reduce$1.L$1;
                    Flow flow3 = (Flow) flowKt__ReduceKt$reduce$1.L$0;
                    ResultKt.throwOnFailure(obj);
                }
                t = objectRef.element;
                if (t == NullSurrogateKt.NULL) {
                    return t;
                }
                throw new NoSuchElementException("Empty flow can't be reduced");
            }
        }
        flowKt__ReduceKt$reduce$1 = new FlowKt__ReduceKt$reduce$1(continuation);
        Object obj2 = flowKt__ReduceKt$reduce$1.result;
        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i2 = flowKt__ReduceKt$reduce$1.label;
        if (i2 != 0) {
        }
        t = objectRef.element;
        if (t == NullSurrogateKt.NULL) {
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0023  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x003d  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0064 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0065  */
    @Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final <T> Object single(@NotNull Flow<? extends T> flow, @NotNull Continuation<? super T> continuation) {
        FlowKt__ReduceKt$single$1 flowKt__ReduceKt$single$1;
        Object coroutine_suspended;
        int i2;
        Ref.ObjectRef objectRef;
        T t;
        if (continuation instanceof FlowKt__ReduceKt$single$1) {
            flowKt__ReduceKt$single$1 = (FlowKt__ReduceKt$single$1) continuation;
            int i3 = flowKt__ReduceKt$single$1.label;
            if ((i3 & Integer.MIN_VALUE) != 0) {
                flowKt__ReduceKt$single$1.label = i3 - Integer.MIN_VALUE;
                Object obj = flowKt__ReduceKt$single$1.result;
                coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i2 = flowKt__ReduceKt$single$1.label;
                if (i2 != 0) {
                    ResultKt.throwOnFailure(obj);
                    final Ref.ObjectRef objectRef2 = new Ref.ObjectRef();
                    objectRef2.element = (T) NullSurrogateKt.NULL;
                    FlowCollector<T> flowCollector = new FlowCollector<T>() { // from class: kotlinx.coroutines.flow.FlowKt__ReduceKt$single$$inlined$collect$1
                        /* JADX WARN: Multi-variable type inference failed */
                        @Override // kotlinx.coroutines.flow.FlowCollector
                        @Nullable
                        public Object emit(Object obj2, @NotNull Continuation continuation2) {
                            Ref.ObjectRef objectRef3 = Ref.ObjectRef.this;
                            if (objectRef3.element == NullSurrogateKt.NULL) {
                                objectRef3.element = obj2;
                                return Unit.INSTANCE;
                            }
                            throw new IllegalStateException("Expected only one element".toString());
                        }
                    };
                    flowKt__ReduceKt$single$1.L$0 = flow;
                    flowKt__ReduceKt$single$1.L$1 = objectRef2;
                    flowKt__ReduceKt$single$1.L$2 = flow;
                    flowKt__ReduceKt$single$1.label = 1;
                    if (flow.collect(flowCollector, flowKt__ReduceKt$single$1) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    objectRef = objectRef2;
                } else if (i2 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                } else {
                    Flow flow2 = (Flow) flowKt__ReduceKt$single$1.L$2;
                    objectRef = (Ref.ObjectRef) flowKt__ReduceKt$single$1.L$1;
                    Flow flow3 = (Flow) flowKt__ReduceKt$single$1.L$0;
                    ResultKt.throwOnFailure(obj);
                }
                t = objectRef.element;
                if (t == NullSurrogateKt.NULL) {
                    return t;
                }
                throw new NoSuchElementException("Expected at least one element");
            }
        }
        flowKt__ReduceKt$single$1 = new FlowKt__ReduceKt$single$1(continuation);
        Object obj2 = flowKt__ReduceKt$single$1.result;
        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i2 = flowKt__ReduceKt$single$1.label;
        if (i2 != 0) {
        }
        t = objectRef.element;
        if (t == NullSurrogateKt.NULL) {
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0023  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x003d  */
    @Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final <T> Object singleOrNull(@NotNull Flow<? extends T> flow, @NotNull Continuation<? super T> continuation) {
        FlowKt__ReduceKt$singleOrNull$1 flowKt__ReduceKt$singleOrNull$1;
        Object coroutine_suspended;
        int i2;
        Ref.ObjectRef objectRef;
        if (continuation instanceof FlowKt__ReduceKt$singleOrNull$1) {
            flowKt__ReduceKt$singleOrNull$1 = (FlowKt__ReduceKt$singleOrNull$1) continuation;
            int i3 = flowKt__ReduceKt$singleOrNull$1.label;
            if ((i3 & Integer.MIN_VALUE) != 0) {
                flowKt__ReduceKt$singleOrNull$1.label = i3 - Integer.MIN_VALUE;
                Object obj = flowKt__ReduceKt$singleOrNull$1.result;
                coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i2 = flowKt__ReduceKt$singleOrNull$1.label;
                if (i2 != 0) {
                    ResultKt.throwOnFailure(obj);
                    final Ref.ObjectRef objectRef2 = new Ref.ObjectRef();
                    objectRef2.element = null;
                    FlowCollector<T> flowCollector = new FlowCollector<T>() { // from class: kotlinx.coroutines.flow.FlowKt__ReduceKt$singleOrNull$$inlined$collect$1
                        /* JADX WARN: Multi-variable type inference failed */
                        @Override // kotlinx.coroutines.flow.FlowCollector
                        @Nullable
                        public Object emit(Object obj2, @NotNull Continuation continuation2) {
                            Ref.ObjectRef objectRef3 = Ref.ObjectRef.this;
                            if (objectRef3.element == null) {
                                objectRef3.element = obj2;
                                return Unit.INSTANCE;
                            }
                            throw new IllegalStateException("Expected only one element".toString());
                        }
                    };
                    flowKt__ReduceKt$singleOrNull$1.L$0 = flow;
                    flowKt__ReduceKt$singleOrNull$1.L$1 = objectRef2;
                    flowKt__ReduceKt$singleOrNull$1.L$2 = flow;
                    flowKt__ReduceKt$singleOrNull$1.label = 1;
                    if (flow.collect(flowCollector, flowKt__ReduceKt$singleOrNull$1) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    objectRef = objectRef2;
                } else if (i2 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                } else {
                    Flow flow2 = (Flow) flowKt__ReduceKt$singleOrNull$1.L$2;
                    objectRef = (Ref.ObjectRef) flowKt__ReduceKt$singleOrNull$1.L$1;
                    Flow flow3 = (Flow) flowKt__ReduceKt$singleOrNull$1.L$0;
                    ResultKt.throwOnFailure(obj);
                }
                return objectRef.element;
            }
        }
        flowKt__ReduceKt$singleOrNull$1 = new FlowKt__ReduceKt$singleOrNull$1(continuation);
        Object obj2 = flowKt__ReduceKt$singleOrNull$1.result;
        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i2 = flowKt__ReduceKt$singleOrNull$1.label;
        if (i2 != 0) {
        }
        return objectRef.element;
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0023  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0047  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x007c A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:29:0x007d  */
    @Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final <T> Object first(@NotNull Flow<? extends T> flow, @NotNull Function2<? super T, ? super Continuation<? super Boolean>, ? extends Object> function2, @NotNull Continuation<? super T> continuation) {
        FlowKt__ReduceKt$first$3 flowKt__ReduceKt$first$3;
        Object coroutine_suspended;
        int i2;
        Function2<? super T, ? super Continuation<? super Boolean>, ? extends Object> function22;
        Ref.ObjectRef objectRef;
        AbortFlowException e2;
        FlowCollector<? super Object> flowCollector;
        T t;
        if (continuation instanceof FlowKt__ReduceKt$first$3) {
            flowKt__ReduceKt$first$3 = (FlowKt__ReduceKt$first$3) continuation;
            int i3 = flowKt__ReduceKt$first$3.label;
            if ((i3 & Integer.MIN_VALUE) != 0) {
                flowKt__ReduceKt$first$3.label = i3 - Integer.MIN_VALUE;
                Object obj = flowKt__ReduceKt$first$3.result;
                coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i2 = flowKt__ReduceKt$first$3.label;
                if (i2 != 0) {
                    ResultKt.throwOnFailure(obj);
                    Ref.ObjectRef objectRef2 = new Ref.ObjectRef();
                    objectRef2.element = (T) NullSurrogateKt.NULL;
                    FlowCollector<? super Object> flowKt__ReduceKt$first$$inlined$collectWhile$2 = new FlowKt__ReduceKt$first$$inlined$collectWhile$2<>(function2, objectRef2);
                    try {
                        flowKt__ReduceKt$first$3.L$0 = flow;
                        flowKt__ReduceKt$first$3.L$1 = function2;
                        flowKt__ReduceKt$first$3.L$2 = objectRef2;
                        flowKt__ReduceKt$first$3.L$3 = flow;
                        flowKt__ReduceKt$first$3.L$4 = flowKt__ReduceKt$first$$inlined$collectWhile$2;
                        flowKt__ReduceKt$first$3.label = 1;
                        if (flow.collect(flowKt__ReduceKt$first$$inlined$collectWhile$2, flowKt__ReduceKt$first$3) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        function22 = function2;
                        objectRef = objectRef2;
                    } catch (AbortFlowException e3) {
                        function22 = function2;
                        objectRef = objectRef2;
                        e2 = e3;
                        flowCollector = flowKt__ReduceKt$first$$inlined$collectWhile$2;
                        FlowExceptions_commonKt.checkOwnership(e2, flowCollector);
                        t = objectRef.element;
                        if (t == NullSurrogateKt.NULL) {
                        }
                    }
                } else if (i2 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                } else {
                    flowCollector = (FlowKt__ReduceKt$first$$inlined$collectWhile$2) flowKt__ReduceKt$first$3.L$4;
                    Flow flow2 = (Flow) flowKt__ReduceKt$first$3.L$3;
                    objectRef = (Ref.ObjectRef) flowKt__ReduceKt$first$3.L$2;
                    function22 = (Function2) flowKt__ReduceKt$first$3.L$1;
                    Flow flow3 = (Flow) flowKt__ReduceKt$first$3.L$0;
                    try {
                        ResultKt.throwOnFailure(obj);
                    } catch (AbortFlowException e4) {
                        e2 = e4;
                        FlowExceptions_commonKt.checkOwnership(e2, flowCollector);
                        t = objectRef.element;
                        if (t == NullSurrogateKt.NULL) {
                        }
                    }
                }
                t = objectRef.element;
                if (t == NullSurrogateKt.NULL) {
                    return t;
                }
                throw new NoSuchElementException("Expected at least one element matching the predicate " + function22);
            }
        }
        flowKt__ReduceKt$first$3 = new FlowKt__ReduceKt$first$3(continuation);
        Object obj2 = flowKt__ReduceKt$first$3.result;
        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i2 = flowKt__ReduceKt$first$3.label;
        if (i2 != 0) {
        }
        t = objectRef.element;
        if (t == NullSurrogateKt.NULL) {
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0023  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0047  */
    @Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final <T> Object firstOrNull(@NotNull Flow<? extends T> flow, @NotNull Function2<? super T, ? super Continuation<? super Boolean>, ? extends Object> function2, @NotNull Continuation<? super T> continuation) {
        FlowKt__ReduceKt$firstOrNull$3 flowKt__ReduceKt$firstOrNull$3;
        Object coroutine_suspended;
        int i2;
        Ref.ObjectRef objectRef;
        AbortFlowException e2;
        FlowCollector<? super Object> flowCollector;
        if (continuation instanceof FlowKt__ReduceKt$firstOrNull$3) {
            flowKt__ReduceKt$firstOrNull$3 = (FlowKt__ReduceKt$firstOrNull$3) continuation;
            int i3 = flowKt__ReduceKt$firstOrNull$3.label;
            if ((i3 & Integer.MIN_VALUE) != 0) {
                flowKt__ReduceKt$firstOrNull$3.label = i3 - Integer.MIN_VALUE;
                Object obj = flowKt__ReduceKt$firstOrNull$3.result;
                coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i2 = flowKt__ReduceKt$firstOrNull$3.label;
                if (i2 != 0) {
                    ResultKt.throwOnFailure(obj);
                    Ref.ObjectRef objectRef2 = new Ref.ObjectRef();
                    objectRef2.element = null;
                    FlowCollector<? super Object> flowKt__ReduceKt$firstOrNull$$inlined$collectWhile$2 = new FlowKt__ReduceKt$firstOrNull$$inlined$collectWhile$2<>(function2, objectRef2);
                    try {
                        flowKt__ReduceKt$firstOrNull$3.L$0 = flow;
                        flowKt__ReduceKt$firstOrNull$3.L$1 = function2;
                        flowKt__ReduceKt$firstOrNull$3.L$2 = objectRef2;
                        flowKt__ReduceKt$firstOrNull$3.L$3 = flow;
                        flowKt__ReduceKt$firstOrNull$3.L$4 = flowKt__ReduceKt$firstOrNull$$inlined$collectWhile$2;
                        flowKt__ReduceKt$firstOrNull$3.label = 1;
                        if (flow.collect(flowKt__ReduceKt$firstOrNull$$inlined$collectWhile$2, flowKt__ReduceKt$firstOrNull$3) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        objectRef = objectRef2;
                    } catch (AbortFlowException e3) {
                        objectRef = objectRef2;
                        e2 = e3;
                        flowCollector = flowKt__ReduceKt$firstOrNull$$inlined$collectWhile$2;
                        FlowExceptions_commonKt.checkOwnership(e2, flowCollector);
                        return objectRef.element;
                    }
                } else if (i2 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                } else {
                    flowCollector = (FlowKt__ReduceKt$firstOrNull$$inlined$collectWhile$2) flowKt__ReduceKt$firstOrNull$3.L$4;
                    Flow flow2 = (Flow) flowKt__ReduceKt$firstOrNull$3.L$3;
                    objectRef = (Ref.ObjectRef) flowKt__ReduceKt$firstOrNull$3.L$2;
                    Function2 function22 = (Function2) flowKt__ReduceKt$firstOrNull$3.L$1;
                    Flow flow3 = (Flow) flowKt__ReduceKt$firstOrNull$3.L$0;
                    try {
                        ResultKt.throwOnFailure(obj);
                    } catch (AbortFlowException e4) {
                        e2 = e4;
                        FlowExceptions_commonKt.checkOwnership(e2, flowCollector);
                        return objectRef.element;
                    }
                }
                return objectRef.element;
            }
        }
        flowKt__ReduceKt$firstOrNull$3 = new FlowKt__ReduceKt$firstOrNull$3(continuation);
        Object obj2 = flowKt__ReduceKt$firstOrNull$3.result;
        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i2 = flowKt__ReduceKt$firstOrNull$3.label;
        if (i2 != 0) {
        }
        return objectRef.element;
    }
}

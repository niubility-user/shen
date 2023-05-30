package kotlinx.coroutines.flow;

import com.jingdong.amon.router.annotation.AnnoConst;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.flow.internal.ChannelFlowOperatorImpl;
import kotlinx.coroutines.flow.internal.FusibleFlow;
import kotlinx.coroutines.flow.internal.NullSurrogateKt;
import kotlinx.coroutines.internal.Symbol;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: Access modifiers changed from: package-private */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u0011\n\u0002\b\u000e\b\u0002\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00060\u0002j\u0002`\u00032\b\u0012\u0004\u0012\u00028\u00000\u00042\b\u0012\u0004\u0012\u00028\u00000\u0005B\u000f\u0012\u0006\u0010%\u001a\u00020\u0002\u00a2\u0006\u0004\b&\u0010!J\u000f\u0010\u0007\u001a\u00020\u0006H\u0002\u00a2\u0006\u0004\b\u0007\u0010\bJ\u0017\u0010\u000b\u001a\u00020\n2\u0006\u0010\t\u001a\u00020\u0006H\u0002\u00a2\u0006\u0004\b\u000b\u0010\fJ!\u0010\u000f\u001a\u00020\n2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00028\u00000\rH\u0096@\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u000f\u0010\u0010J%\u0010\u0015\u001a\b\u0012\u0004\u0012\u00028\u00000\u00052\u0006\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0014\u001a\u00020\u0013H\u0016\u00a2\u0006\u0004\b\u0015\u0010\u0016R\u0016\u0010\u0017\u001a\u00020\u00138\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u0017\u0010\u0018R\u001e\u0010\u001a\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u00198\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u001a\u0010\u001bR\u0016\u0010\u001c\u001a\u00020\u00138\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u001c\u0010\u0018R*\u0010\u001d\u001a\u00028\u00002\u0006\u0010\u001d\u001a\u00028\u00008V@VX\u0096\u000e\u00a2\u0006\u0012\u0012\u0004\b\"\u0010#\u001a\u0004\b\u001e\u0010\u001f\"\u0004\b \u0010!R\u0016\u0010$\u001a\u00020\u00138\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b$\u0010\u0018\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006'"}, d2 = {"Lkotlinx/coroutines/flow/StateFlowImpl;", "T", "", "Lkotlinx/coroutines/internal/SynchronizedObject;", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lkotlinx/coroutines/flow/internal/FusibleFlow;", "Lkotlinx/coroutines/flow/StateFlowSlot;", "allocateSlot", "()Lkotlinx/coroutines/flow/StateFlowSlot;", "slot", "", "freeSlot", "(Lkotlinx/coroutines/flow/StateFlowSlot;)V", "Lkotlinx/coroutines/flow/FlowCollector;", "collector", "collect", "(Lkotlinx/coroutines/flow/FlowCollector;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Lkotlin/coroutines/CoroutineContext;", AnnoConst.Constructor_Context, "", "capacity", "fuse", "(Lkotlin/coroutines/CoroutineContext;I)Lkotlinx/coroutines/flow/internal/FusibleFlow;", "nSlots", "I", "", "slots", "[Lkotlinx/coroutines/flow/StateFlowSlot;", "nextIndex", "value", "getValue", "()Ljava/lang/Object;", "setValue", "(Ljava/lang/Object;)V", "value$annotations", "()V", "sequence", "initialValue", "<init>", "kotlinx-coroutines-core"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes11.dex */
public final class StateFlowImpl<T> implements MutableStateFlow<T>, FusibleFlow<T> {
    private volatile Object _state;
    private int nSlots;
    private int nextIndex;
    private int sequence;
    private StateFlowSlot[] slots = new StateFlowSlot[2];

    public StateFlowImpl(@NotNull Object obj) {
        this._state = obj;
    }

    private final StateFlowSlot allocateSlot() {
        StateFlowSlot stateFlowSlot;
        synchronized (this) {
            StateFlowSlot[] stateFlowSlotArr = this.slots;
            int length = stateFlowSlotArr.length;
            if (this.nSlots >= length) {
                Object[] copyOf = Arrays.copyOf(stateFlowSlotArr, length * 2);
                Intrinsics.checkExpressionValueIsNotNull(copyOf, "java.util.Arrays.copyOf(this, newSize)");
                this.slots = (StateFlowSlot[]) copyOf;
            }
            int i2 = this.nextIndex;
            do {
                stateFlowSlot = this.slots[i2];
                if (stateFlowSlot == null) {
                    stateFlowSlot = new StateFlowSlot();
                    this.slots[i2] = stateFlowSlot;
                }
                i2++;
                if (i2 >= this.slots.length) {
                    i2 = 0;
                }
            } while (!stateFlowSlot.allocate());
            this.nextIndex = i2;
            this.nSlots++;
        }
        return stateFlowSlot;
    }

    private final void freeSlot(StateFlowSlot slot) {
        synchronized (this) {
            slot.free();
            this.nSlots--;
            Unit unit = Unit.INSTANCE;
        }
    }

    public static /* synthetic */ void value$annotations() {
    }

    /* JADX WARN: Code restructure failed: missing block: B:26:0x0070, code lost:
        if ((!kotlin.jvm.internal.Intrinsics.areEqual(r6, r12)) != false) goto L27;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:10:0x0025  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x005d  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x006b A[Catch: all -> 0x005b, TryCatch #0 {all -> 0x005b, blocks: (B:13:0x0039, B:23:0x0067, B:25:0x006b, B:36:0x0092, B:38:0x0098, B:27:0x0072, B:31:0x0079, B:18:0x0057), top: B:44:0x0023 }] */
    /* JADX WARN: Removed duplicated region for block: B:29:0x0076  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x0078  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x008b A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:34:0x008c  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x0098 A[Catch: all -> 0x005b, TRY_LEAVE, TryCatch #0 {all -> 0x005b, blocks: (B:13:0x0039, B:23:0x0067, B:25:0x006b, B:36:0x0092, B:38:0x0098, B:27:0x0072, B:31:0x0079, B:18:0x0057), top: B:44:0x0023 }] */
    /* JADX WARN: Type inference failed for: r2v0, types: [int] */
    /* JADX WARN: Type inference failed for: r2v11 */
    /* JADX WARN: Type inference failed for: r2v12 */
    /* JADX WARN: Type inference failed for: r2v13 */
    /* JADX WARN: Type inference failed for: r2v5, types: [java.lang.Object] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:37:0x0096 -> B:23:0x0067). Please submit an issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:39:0x00a8 -> B:23:0x0067). Please submit an issue!!! */
    @Override // kotlinx.coroutines.flow.Flow
    @Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public Object collect(@NotNull FlowCollector<? super T> flowCollector, @NotNull Continuation<? super Unit> continuation) {
        StateFlowImpl$collect$1 stateFlowImpl$collect$1;
        Object coroutine_suspended;
        StateFlowSlot stateFlowSlot;
        StateFlowImpl<T> stateFlowImpl;
        Object obj;
        Object obj2;
        FlowCollector<? super T> flowCollector2;
        StateFlowSlot stateFlowSlot2;
        Object obj3;
        StateFlowSlot stateFlowSlot3;
        boolean takePending;
        Object obj4;
        try {
            if (continuation instanceof StateFlowImpl$collect$1) {
                stateFlowImpl$collect$1 = (StateFlowImpl$collect$1) continuation;
                int i2 = stateFlowImpl$collect$1.label;
                if ((i2 & Integer.MIN_VALUE) != 0) {
                    stateFlowImpl$collect$1.label = i2 - Integer.MIN_VALUE;
                    Object obj5 = stateFlowImpl$collect$1.result;
                    coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    stateFlowSlot = stateFlowImpl$collect$1.label;
                    if (stateFlowSlot != 0) {
                        ResultKt.throwOnFailure(obj5);
                        stateFlowImpl = this;
                        stateFlowSlot = allocateSlot();
                        obj = null;
                    } else if (stateFlowSlot == 1) {
                        obj2 = stateFlowImpl$collect$1.L$4;
                        Object obj6 = stateFlowImpl$collect$1.L$3;
                        StateFlowSlot stateFlowSlot4 = (StateFlowSlot) stateFlowImpl$collect$1.L$2;
                        flowCollector2 = (FlowCollector) stateFlowImpl$collect$1.L$1;
                        stateFlowImpl = (StateFlowImpl) stateFlowImpl$collect$1.L$0;
                        ResultKt.throwOnFailure(obj5);
                        stateFlowSlot2 = stateFlowSlot4;
                        obj = obj2;
                        flowCollector = flowCollector2;
                        obj3 = obj;
                        stateFlowSlot3 = stateFlowSlot2;
                        takePending = stateFlowSlot3.takePending();
                        stateFlowSlot = stateFlowSlot3;
                        if (!takePending) {
                            stateFlowImpl$collect$1.L$0 = stateFlowImpl;
                            stateFlowImpl$collect$1.L$1 = flowCollector;
                            stateFlowImpl$collect$1.L$2 = stateFlowSlot3;
                            stateFlowImpl$collect$1.L$3 = obj;
                            stateFlowImpl$collect$1.L$4 = obj3;
                            stateFlowImpl$collect$1.label = 2;
                            Object awaitPending = stateFlowSlot3.awaitPending(stateFlowImpl$collect$1);
                            stateFlowSlot = stateFlowSlot3;
                            if (awaitPending == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                        }
                    } else if (stateFlowSlot != 2) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    } else {
                        Object obj7 = stateFlowImpl$collect$1.L$4;
                        Object obj8 = stateFlowImpl$collect$1.L$3;
                        StateFlowSlot stateFlowSlot5 = (StateFlowSlot) stateFlowImpl$collect$1.L$2;
                        FlowCollector<? super T> flowCollector3 = (FlowCollector) stateFlowImpl$collect$1.L$1;
                        stateFlowImpl = (StateFlowImpl) stateFlowImpl$collect$1.L$0;
                        ResultKt.throwOnFailure(obj5);
                        obj = obj8;
                        flowCollector = flowCollector3;
                        stateFlowSlot = stateFlowSlot5;
                    }
                    obj3 = stateFlowImpl._state;
                    if (obj != null) {
                        stateFlowSlot3 = stateFlowSlot;
                    }
                    obj4 = obj3 == NullSurrogateKt.NULL ? null : obj3;
                    stateFlowImpl$collect$1.L$0 = stateFlowImpl;
                    stateFlowImpl$collect$1.L$1 = flowCollector;
                    stateFlowImpl$collect$1.L$2 = stateFlowSlot;
                    stateFlowImpl$collect$1.L$3 = obj;
                    stateFlowImpl$collect$1.L$4 = obj3;
                    stateFlowImpl$collect$1.label = 1;
                    if (flowCollector.emit(obj4, stateFlowImpl$collect$1) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    flowCollector2 = flowCollector;
                    obj2 = obj3;
                    stateFlowSlot2 = stateFlowSlot;
                    obj = obj2;
                    flowCollector = flowCollector2;
                    obj3 = obj;
                    stateFlowSlot3 = stateFlowSlot2;
                    takePending = stateFlowSlot3.takePending();
                    stateFlowSlot = stateFlowSlot3;
                    if (!takePending) {
                    }
                    obj3 = stateFlowImpl._state;
                    if (obj != null) {
                    }
                    if (obj3 == NullSurrogateKt.NULL) {
                    }
                    stateFlowImpl$collect$1.L$0 = stateFlowImpl;
                    stateFlowImpl$collect$1.L$1 = flowCollector;
                    stateFlowImpl$collect$1.L$2 = stateFlowSlot;
                    stateFlowImpl$collect$1.L$3 = obj;
                    stateFlowImpl$collect$1.L$4 = obj3;
                    stateFlowImpl$collect$1.label = 1;
                    if (flowCollector.emit(obj4, stateFlowImpl$collect$1) == coroutine_suspended) {
                    }
                }
            }
            if (stateFlowSlot != 0) {
            }
            obj3 = stateFlowImpl._state;
            if (obj != null) {
            }
            if (obj3 == NullSurrogateKt.NULL) {
            }
            stateFlowImpl$collect$1.L$0 = stateFlowImpl;
            stateFlowImpl$collect$1.L$1 = flowCollector;
            stateFlowImpl$collect$1.L$2 = stateFlowSlot;
            stateFlowImpl$collect$1.L$3 = obj;
            stateFlowImpl$collect$1.L$4 = obj3;
            stateFlowImpl$collect$1.label = 1;
            if (flowCollector.emit(obj4, stateFlowImpl$collect$1) == coroutine_suspended) {
            }
        } catch (Throwable th) {
            stateFlowImpl.freeSlot(stateFlowSlot);
            throw th;
        }
        stateFlowImpl$collect$1 = new StateFlowImpl$collect$1(this, continuation);
        Object obj52 = stateFlowImpl$collect$1.result;
        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        stateFlowSlot = stateFlowImpl$collect$1.label;
    }

    @Override // kotlinx.coroutines.flow.internal.FusibleFlow
    @NotNull
    public FusibleFlow<T> fuse(@NotNull CoroutineContext context, int capacity) {
        return (capacity == -1 || capacity == 0) ? this : new ChannelFlowOperatorImpl(this, context, capacity);
    }

    @Override // kotlinx.coroutines.flow.MutableStateFlow, kotlinx.coroutines.flow.StateFlow
    public T getValue() {
        Symbol symbol = NullSurrogateKt.NULL;
        T t = (T) this._state;
        if (t == symbol) {
            return null;
        }
        return t;
    }

    @Override // kotlinx.coroutines.flow.MutableStateFlow
    public void setValue(T t) {
        int i2;
        StateFlowSlot[] stateFlowSlotArr;
        if (t == null) {
            t = (T) NullSurrogateKt.NULL;
        }
        synchronized (this) {
            if (Intrinsics.areEqual(this._state, t)) {
                return;
            }
            this._state = t;
            int i3 = this.sequence;
            if ((i3 & 1) == 0) {
                int i4 = i3 + 1;
                this.sequence = i4;
                StateFlowSlot[] stateFlowSlotArr2 = this.slots;
                Unit unit = Unit.INSTANCE;
                while (true) {
                    for (StateFlowSlot stateFlowSlot : stateFlowSlotArr2) {
                        if (stateFlowSlot != null) {
                            stateFlowSlot.makePending();
                        }
                    }
                    synchronized (this) {
                        i2 = this.sequence;
                        if (i2 == i4) {
                            this.sequence = i4 + 1;
                            return;
                        } else {
                            stateFlowSlotArr = this.slots;
                            Unit unit2 = Unit.INSTANCE;
                        }
                    }
                    stateFlowSlotArr2 = stateFlowSlotArr;
                    i4 = i2;
                }
            } else {
                this.sequence = i3 + 2;
            }
        }
    }
}

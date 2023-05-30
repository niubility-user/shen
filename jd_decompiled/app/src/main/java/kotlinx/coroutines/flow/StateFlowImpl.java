package kotlinx.coroutines.flow;

import com.jingdong.amon.router.annotation.AnnoConst;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.flow.internal.ChannelFlowOperatorImpl;
import kotlinx.coroutines.flow.internal.FusibleFlow;
import kotlinx.coroutines.flow.internal.NullSurrogateKt;
import kotlinx.coroutines.internal.Symbol;
import org.jetbrains.annotations.NotNull;

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
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.Object collect(@org.jetbrains.annotations.NotNull kotlinx.coroutines.flow.FlowCollector<? super T> r11, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super kotlin.Unit> r12) {
        /*
            r10 = this;
            boolean r0 = r12 instanceof kotlinx.coroutines.flow.StateFlowImpl$collect$1
            if (r0 == 0) goto L13
            r0 = r12
            kotlinx.coroutines.flow.StateFlowImpl$collect$1 r0 = (kotlinx.coroutines.flow.StateFlowImpl$collect$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            kotlinx.coroutines.flow.StateFlowImpl$collect$1 r0 = new kotlinx.coroutines.flow.StateFlowImpl$collect$1
            r0.<init>(r10, r12)
        L18:
            java.lang.Object r12 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 0
            r4 = 2
            r5 = 1
            if (r2 == 0) goto L5d
            if (r2 == r5) goto L47
            if (r2 != r4) goto L3f
            java.lang.Object r11 = r0.L$4
            java.lang.Object r11 = r0.L$3
            java.lang.Object r2 = r0.L$2
            kotlinx.coroutines.flow.StateFlowSlot r2 = (kotlinx.coroutines.flow.StateFlowSlot) r2
            java.lang.Object r6 = r0.L$1
            kotlinx.coroutines.flow.FlowCollector r6 = (kotlinx.coroutines.flow.FlowCollector) r6
            java.lang.Object r7 = r0.L$0
            kotlinx.coroutines.flow.StateFlowImpl r7 = (kotlinx.coroutines.flow.StateFlowImpl) r7
            kotlin.ResultKt.throwOnFailure(r12)     // Catch: java.lang.Throwable -> L5b
            r12 = r11
            r11 = r6
            goto L67
        L3f:
            java.lang.IllegalStateException r11 = new java.lang.IllegalStateException
            java.lang.String r12 = "call to 'resume' before 'invoke' with coroutine"
            r11.<init>(r12)
            throw r11
        L47:
            java.lang.Object r11 = r0.L$4
            java.lang.Object r2 = r0.L$3
            java.lang.Object r2 = r0.L$2
            kotlinx.coroutines.flow.StateFlowSlot r2 = (kotlinx.coroutines.flow.StateFlowSlot) r2
            java.lang.Object r6 = r0.L$1
            kotlinx.coroutines.flow.FlowCollector r6 = (kotlinx.coroutines.flow.FlowCollector) r6
            java.lang.Object r7 = r0.L$0
            kotlinx.coroutines.flow.StateFlowImpl r7 = (kotlinx.coroutines.flow.StateFlowImpl) r7
            kotlin.ResultKt.throwOnFailure(r12)     // Catch: java.lang.Throwable -> L5b
            goto L8f
        L5b:
            r11 = move-exception
            goto Lab
        L5d:
            kotlin.ResultKt.throwOnFailure(r12)
            kotlinx.coroutines.flow.StateFlowSlot r12 = r10.allocateSlot()
            r7 = r10
            r2 = r12
            r12 = r3
        L67:
            java.lang.Object r6 = r7._state     // Catch: java.lang.Throwable -> L5b
            if (r12 == 0) goto L72
            boolean r8 = kotlin.jvm.internal.Intrinsics.areEqual(r6, r12)     // Catch: java.lang.Throwable -> L5b
            r8 = r8 ^ r5
            if (r8 == 0) goto L92
        L72:
            kotlinx.coroutines.internal.Symbol r8 = kotlinx.coroutines.flow.internal.NullSurrogateKt.NULL     // Catch: java.lang.Throwable -> L5b
            if (r6 != r8) goto L78
            r8 = r3
            goto L79
        L78:
            r8 = r6
        L79:
            r0.L$0 = r7     // Catch: java.lang.Throwable -> L5b
            r0.L$1 = r11     // Catch: java.lang.Throwable -> L5b
            r0.L$2 = r2     // Catch: java.lang.Throwable -> L5b
            r0.L$3 = r12     // Catch: java.lang.Throwable -> L5b
            r0.L$4 = r6     // Catch: java.lang.Throwable -> L5b
            r0.label = r5     // Catch: java.lang.Throwable -> L5b
            java.lang.Object r12 = r11.emit(r8, r0)     // Catch: java.lang.Throwable -> L5b
            if (r12 != r1) goto L8c
            return r1
        L8c:
            r9 = r6
            r6 = r11
            r11 = r9
        L8f:
            r12 = r11
            r11 = r6
            r6 = r12
        L92:
            boolean r8 = r2.takePending()     // Catch: java.lang.Throwable -> L5b
            if (r8 != 0) goto L67
            r0.L$0 = r7     // Catch: java.lang.Throwable -> L5b
            r0.L$1 = r11     // Catch: java.lang.Throwable -> L5b
            r0.L$2 = r2     // Catch: java.lang.Throwable -> L5b
            r0.L$3 = r12     // Catch: java.lang.Throwable -> L5b
            r0.L$4 = r6     // Catch: java.lang.Throwable -> L5b
            r0.label = r4     // Catch: java.lang.Throwable -> L5b
            java.lang.Object r6 = r2.awaitPending(r0)     // Catch: java.lang.Throwable -> L5b
            if (r6 != r1) goto L67
            return r1
        Lab:
            r7.freeSlot(r2)
            goto Lb0
        Laf:
            throw r11
        Lb0:
            goto Laf
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.flow.StateFlowImpl.collect(kotlinx.coroutines.flow.FlowCollector, kotlin.coroutines.Continuation):java.lang.Object");
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

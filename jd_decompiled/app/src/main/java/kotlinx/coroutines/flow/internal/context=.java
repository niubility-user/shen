package kotlinx.coroutines.flow.internal;

import com.jingdong.amon.router.annotation.AnnoConst;
import com.tencent.connect.common.Constants;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.jvm.JvmField;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.DebugKt;
import kotlinx.coroutines.DebugStringsKt;
import kotlinx.coroutines.InternalCoroutinesApi;
import kotlinx.coroutines.channels.BroadcastChannel;
import kotlinx.coroutines.channels.BroadcastKt;
import kotlinx.coroutines.channels.ProduceKt;
import kotlinx.coroutines.channels.ProducerScope;
import kotlinx.coroutines.channels.ReceiveChannel;
import kotlinx.coroutines.flow.FlowCollector;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@InternalCoroutinesApi
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0006\b'\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u00028\u00000\u0002B\u0017\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u00a2\u0006\u0004\b,\u0010-J%\u0010\u0007\u001a\b\u0012\u0004\u0012\u00028\u00000\u00022\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u0005H\u0016\u00a2\u0006\u0004\b\u0007\u0010\bJ%\u0010\t\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u0005H$\u00a2\u0006\u0004\b\t\u0010\nJ!\u0010\u000e\u001a\u00020\r2\f\u0010\f\u001a\b\u0012\u0004\u0012\u00028\u00000\u000bH\u00a4@\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u000e\u0010\u000fJ%\u0010\u0014\u001a\b\u0012\u0004\u0012\u00028\u00000\u00132\u0006\u0010\f\u001a\u00020\u00102\u0006\u0010\u0012\u001a\u00020\u0011H\u0016\u00a2\u0006\u0004\b\u0014\u0010\u0015J\u001d\u0010\u0017\u001a\b\u0012\u0004\u0012\u00028\u00000\u00162\u0006\u0010\f\u001a\u00020\u0010H\u0016\u00a2\u0006\u0004\b\u0017\u0010\u0018J!\u0010\u001b\u001a\u00020\r2\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00028\u00000\u0019H\u0096@\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u001b\u0010\u001cJ\u000f\u0010\u001e\u001a\u00020\u001dH\u0016\u00a2\u0006\u0004\b\u001e\u0010\u001fJ\u000f\u0010 \u001a\u00020\u001dH\u0016\u00a2\u0006\u0004\b \u0010\u001fR\u0016\u0010\u0006\u001a\u00020\u00058\u0006@\u0007X\u0087\u0004\u00a2\u0006\u0006\n\u0004\b\u0006\u0010!R\u0016\u0010\u0004\u001a\u00020\u00038\u0006@\u0007X\u0087\u0004\u00a2\u0006\u0006\n\u0004\b\u0004\u0010\"R\u0016\u0010%\u001a\u00020\u00058B@\u0002X\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b#\u0010$R;\u0010+\u001a$\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u000b\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0'\u0012\u0006\u0012\u0004\u0018\u00010(0&8@@\u0000X\u0080\u0004\u00f8\u0001\u0000\u00a2\u0006\u0006\u001a\u0004\b)\u0010*\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006."}, d2 = {"Lkotlinx/coroutines/flow/internal/ChannelFlow;", "T", "Lkotlinx/coroutines/flow/internal/FusibleFlow;", "Lkotlin/coroutines/CoroutineContext;", AnnoConst.Constructor_Context, "", "capacity", "fuse", "(Lkotlin/coroutines/CoroutineContext;I)Lkotlinx/coroutines/flow/internal/FusibleFlow;", "create", "(Lkotlin/coroutines/CoroutineContext;I)Lkotlinx/coroutines/flow/internal/ChannelFlow;", "Lkotlinx/coroutines/channels/ProducerScope;", Constants.PARAM_SCOPE, "", "collectTo", "(Lkotlinx/coroutines/channels/ProducerScope;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Lkotlinx/coroutines/CoroutineScope;", "Lkotlinx/coroutines/CoroutineStart;", "start", "Lkotlinx/coroutines/channels/BroadcastChannel;", "broadcastImpl", "(Lkotlinx/coroutines/CoroutineScope;Lkotlinx/coroutines/CoroutineStart;)Lkotlinx/coroutines/channels/BroadcastChannel;", "Lkotlinx/coroutines/channels/ReceiveChannel;", "produceImpl", "(Lkotlinx/coroutines/CoroutineScope;)Lkotlinx/coroutines/channels/ReceiveChannel;", "Lkotlinx/coroutines/flow/FlowCollector;", "collector", "collect", "(Lkotlinx/coroutines/flow/FlowCollector;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "", "additionalToStringProps", "()Ljava/lang/String;", "toString", "I", "Lkotlin/coroutines/CoroutineContext;", "getProduceCapacity", "()I", "produceCapacity", "Lkotlin/Function2;", "Lkotlin/coroutines/Continuation;", "", "getCollectToFun$kotlinx_coroutines_core", "()Lkotlin/jvm/functions/Function2;", "collectToFun", "<init>", "(Lkotlin/coroutines/CoroutineContext;I)V", "kotlinx-coroutines-core"}, k = 1, mv = {1, 4, 0})
/* renamed from: kotlinx.coroutines.flow.internal.ChannelFlow  reason: from toString */
/* loaded from: classes11.dex */
public abstract class context=<T> implements FusibleFlow<T> {
    @JvmField
    public final int capacity;
    @JvmField
    @NotNull
    public final CoroutineContext context;

    public context=(@NotNull CoroutineContext coroutineContext, int i2) {
        this.context = coroutineContext;
        this.capacity = i2;
    }

    static /* synthetic */ Object collect$suspendImpl(context= r2, FlowCollector flowCollector, Continuation continuation) {
        Object coroutine_suspended;
        Object coroutineScope = CoroutineScopeKt.coroutineScope(new ChannelFlow$collect$2(r2, flowCollector, null), continuation);
        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        return coroutineScope == coroutine_suspended ? coroutineScope : Unit.INSTANCE;
    }

    private final int getProduceCapacity() {
        int i2 = this.capacity;
        if (i2 == -3) {
            return -2;
        }
        return i2;
    }

    @NotNull
    public String additionalToStringProps() {
        return "";
    }

    @NotNull
    public BroadcastChannel<T> broadcastImpl(@NotNull CoroutineScope r9, @NotNull CoroutineStart start) {
        return BroadcastKt.broadcast$default(r9, this.context, getProduceCapacity(), start, null, getCollectToFun$kotlinx_coroutines_core(), 8, null);
    }

    @Override // kotlinx.coroutines.flow.Flow
    @Nullable
    public Object collect(@NotNull FlowCollector<? super T> flowCollector, @NotNull Continuation<? super Unit> continuation) {
        return collect$suspendImpl(this, flowCollector, continuation);
    }

    @Nullable
    public abstract Object collectTo(@NotNull ProducerScope<? super T> producerScope, @NotNull Continuation<? super Unit> continuation);

    @NotNull
    protected abstract context=<T> create(@NotNull CoroutineContext r1, int capacity);

    @Override // kotlinx.coroutines.flow.internal.FusibleFlow
    @NotNull
    public FusibleFlow<T> fuse(@NotNull CoroutineContext r4, int capacity) {
        CoroutineContext plus = r4.plus(this.context);
        int i2 = this.capacity;
        if (i2 != -3) {
            if (capacity != -3) {
                if (i2 != -2) {
                    if (capacity != -2) {
                        if (i2 == -1 || capacity == -1) {
                            capacity = -1;
                        } else {
                            if (DebugKt.getASSERTIONS_ENABLED()) {
                                if (!(this.capacity >= 0)) {
                                    throw new AssertionError();
                                }
                            }
                            if (DebugKt.getASSERTIONS_ENABLED()) {
                                if (!(capacity >= 0)) {
                                    throw new AssertionError();
                                }
                            }
                            capacity += this.capacity;
                            if (capacity < 0) {
                                capacity = Integer.MAX_VALUE;
                            }
                        }
                    }
                }
            }
            capacity = i2;
        }
        return (Intrinsics.areEqual(plus, this.context) && capacity == this.capacity) ? this : create(plus, capacity);
    }

    @NotNull
    public final Function2<ProducerScope<? super T>, Continuation<? super Unit>, Object> getCollectToFun$kotlinx_coroutines_core() {
        return new ChannelFlow$collectToFun$1(this, null);
    }

    @NotNull
    public ReceiveChannel<T> produceImpl(@NotNull CoroutineScope r9) {
        return ProduceKt.produce$default(r9, this.context, getProduceCapacity(), CoroutineStart.ATOMIC, null, getCollectToFun$kotlinx_coroutines_core(), 8, null);
    }

    @NotNull
    public String toString() {
        return DebugStringsKt.getClassSimpleName(this) + '[' + additionalToStringProps() + "context=" + this.context + ", capacity=" + this.capacity + ']';
    }
}

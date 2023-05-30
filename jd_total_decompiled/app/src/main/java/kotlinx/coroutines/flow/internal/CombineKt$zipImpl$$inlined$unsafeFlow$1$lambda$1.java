package kotlinx.coroutines.flow.internal;

import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.channels.ChannelIterator;
import kotlinx.coroutines.channels.ChannelsKt;
import kotlinx.coroutines.channels.ReceiveChannel;
import kotlinx.coroutines.channels.SendChannel;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.internal.Symbol;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: Access modifiers changed from: package-private */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\u0010\b\u001a\u00020\u0004\"\u0004\b\u0000\u0010\u0000\"\u0004\b\u0001\u0010\u0001\"\u0004\b\u0002\u0010\u0002*\u00020\u0003H\u008a@\u00a2\u0006\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0007"}, d2 = {"T1", "T2", "R", "LLkotlinx/coroutines/CoroutineScope;;", "", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "kotlinx/coroutines/flow/internal/CombineKt$zipImpl$1$1", "<anonymous>"}, k = 3, mv = {1, 4, 0})
/* loaded from: classes11.dex */
public final class CombineKt$zipImpl$$inlined$unsafeFlow$1$lambda$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ FlowCollector $this_unsafeFlow;
    Object L$0;
    Object L$1;
    Object L$10;
    Object L$11;
    Object L$12;
    Object L$2;
    Object L$3;
    Object L$4;
    Object L$5;
    Object L$6;
    Object L$7;
    Object L$8;
    Object L$9;
    int label;
    private CoroutineScope p$;
    final /* synthetic */ CombineKt$zipImpl$$inlined$unsafeFlow$1 this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CombineKt$zipImpl$$inlined$unsafeFlow$1$lambda$1(FlowCollector flowCollector, Continuation continuation, CombineKt$zipImpl$$inlined$unsafeFlow$1 combineKt$zipImpl$$inlined$unsafeFlow$1) {
        super(2, continuation);
        this.$this_unsafeFlow = flowCollector;
        this.this$0 = combineKt$zipImpl$$inlined$unsafeFlow$1;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        CombineKt$zipImpl$$inlined$unsafeFlow$1$lambda$1 combineKt$zipImpl$$inlined$unsafeFlow$1$lambda$1 = new CombineKt$zipImpl$$inlined$unsafeFlow$1$lambda$1(this.$this_unsafeFlow, continuation, this.this$0);
        combineKt$zipImpl$$inlined$unsafeFlow$1$lambda$1.p$ = (CoroutineScope) obj;
        return combineKt$zipImpl$$inlined$unsafeFlow$1$lambda$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((CombineKt$zipImpl$$inlined$unsafeFlow$1$lambda$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:34:0x0171 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:35:0x0172  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x018c A[Catch: all -> 0x0270, TRY_LEAVE, TryCatch #0 {all -> 0x0270, blocks: (B:36:0x0184, B:38:0x018c, B:67:0x0258), top: B:104:0x0184 }] */
    /* JADX WARN: Removed duplicated region for block: B:44:0x01c9  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x01cf A[Catch: all -> 0x0254, TryCatch #5 {all -> 0x0254, blocks: (B:42:0x01c1, B:45:0x01cf, B:49:0x01e0, B:52:0x01e9), top: B:111:0x01c1 }] */
    /* JADX WARN: Removed duplicated region for block: B:59:0x0240 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:60:0x0241  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x0258 A[Catch: all -> 0x0270, TRY_ENTER, TRY_LEAVE, TryCatch #0 {all -> 0x0270, blocks: (B:36:0x0184, B:38:0x018c, B:67:0x0258), top: B:104:0x0184 }] */
    /* JADX WARN: Removed duplicated region for block: B:93:0x0294  */
    /* JADX WARN: Removed duplicated region for block: B:99:0x02a6  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:62:0x024e -> B:113:0x0155). Please submit an issue!!! */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(@NotNull Object obj) {
        Object coroutine_suspended;
        CombineKt$zipImpl$$inlined$unsafeFlow$1$lambda$1 combineKt$zipImpl$$inlined$unsafeFlow$1$lambda$1;
        final ReceiveChannel receiveChannel;
        ReceiveChannel receiveChannel2;
        Object obj2;
        ReceiveChannel receiveChannel3;
        ReceiveChannel receiveChannel4;
        Throwable th;
        ChannelIterator channelIterator;
        CombineKt$zipImpl$$inlined$unsafeFlow$1$lambda$1 combineKt$zipImpl$$inlined$unsafeFlow$1$lambda$12;
        ReceiveChannel receiveChannel5;
        CoroutineScope coroutineScope;
        ChannelIterator channelIterator2;
        CombineKt$zipImpl$$inlined$unsafeFlow$1$lambda$1 combineKt$zipImpl$$inlined$unsafeFlow$1$lambda$13;
        Object obj3;
        ReceiveChannel receiveChannel6;
        Throwable th2;
        Object obj4;
        CoroutineScope coroutineScope2;
        Object obj5;
        ChannelIterator channelIterator3;
        ReceiveChannel receiveChannel7;
        ReceiveChannel receiveChannel8;
        CombineKt$zipImpl$$inlined$unsafeFlow$1$lambda$1 combineKt$zipImpl$$inlined$unsafeFlow$1$lambda$14;
        FlowCollector flowCollector;
        ChannelIterator channelIterator4;
        ReceiveChannel receiveChannel9;
        Throwable th3;
        Object obj6;
        Object obj7;
        Object obj8;
        CoroutineScope coroutineScope3;
        CombineKt$zipImpl$$inlined$unsafeFlow$1$lambda$1 combineKt$zipImpl$$inlined$unsafeFlow$1$lambda$15;
        ReceiveChannel receiveChannel10;
        ReceiveChannel receiveChannel11;
        Throwable th4;
        Throwable th5;
        AbortFlowException abortFlowException;
        AbortFlowException e2;
        Throwable th6;
        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = this.label;
        int i3 = 1;
        if (i2 != 0) {
            try {
                if (i2 == 1) {
                    ChannelIterator channelIterator5 = (ChannelIterator) this.L$9;
                    ReceiveChannel receiveChannel12 = (ReceiveChannel) this.L$8;
                    Throwable th7 = (Throwable) this.L$7;
                    receiveChannel = (ReceiveChannel) this.L$6;
                    CombineKt$zipImpl$$inlined$unsafeFlow$1$lambda$1 combineKt$zipImpl$$inlined$unsafeFlow$1$lambda$16 = (CombineKt$zipImpl$$inlined$unsafeFlow$1$lambda$1) this.L$5;
                    ReceiveChannel receiveChannel13 = (ReceiveChannel) this.L$4;
                    ChannelIterator channelIterator6 = (ChannelIterator) this.L$3;
                    receiveChannel2 = (ReceiveChannel) this.L$2;
                    ReceiveChannel receiveChannel14 = (ReceiveChannel) this.L$1;
                    CoroutineScope coroutineScope4 = (CoroutineScope) this.L$0;
                    ResultKt.throwOnFailure(obj);
                    obj2 = obj;
                    receiveChannel3 = receiveChannel14;
                    receiveChannel4 = receiveChannel13;
                    th = th7;
                    channelIterator = channelIterator5;
                    combineKt$zipImpl$$inlined$unsafeFlow$1$lambda$12 = this;
                    receiveChannel5 = receiveChannel12;
                    coroutineScope = coroutineScope4;
                    channelIterator2 = channelIterator6;
                    combineKt$zipImpl$$inlined$unsafeFlow$1$lambda$13 = combineKt$zipImpl$$inlined$unsafeFlow$1$lambda$16;
                    if (!((Boolean) obj2).booleanValue()) {
                    }
                } else if (i2 == 2) {
                    Object obj9 = this.L$11;
                    Object obj10 = this.L$10;
                    channelIterator = (ChannelIterator) this.L$9;
                    ReceiveChannel receiveChannel15 = (ReceiveChannel) this.L$8;
                    Throwable th8 = (Throwable) this.L$7;
                    receiveChannel = (ReceiveChannel) this.L$6;
                    CombineKt$zipImpl$$inlined$unsafeFlow$1$lambda$1 combineKt$zipImpl$$inlined$unsafeFlow$1$lambda$17 = (CombineKt$zipImpl$$inlined$unsafeFlow$1$lambda$1) this.L$5;
                    ReceiveChannel receiveChannel16 = (ReceiveChannel) this.L$4;
                    ChannelIterator channelIterator7 = (ChannelIterator) this.L$3;
                    ReceiveChannel receiveChannel17 = (ReceiveChannel) this.L$2;
                    receiveChannel3 = (ReceiveChannel) this.L$1;
                    CoroutineScope coroutineScope5 = (CoroutineScope) this.L$0;
                    ResultKt.throwOnFailure(obj);
                    obj3 = obj;
                    receiveChannel6 = receiveChannel17;
                    th2 = th8;
                    obj4 = obj9;
                    coroutineScope2 = coroutineScope5;
                    obj5 = obj10;
                    channelIterator3 = channelIterator7;
                    combineKt$zipImpl$$inlined$unsafeFlow$1$lambda$1 = this;
                    receiveChannel7 = receiveChannel15;
                    receiveChannel8 = receiveChannel16;
                    combineKt$zipImpl$$inlined$unsafeFlow$1$lambda$14 = combineKt$zipImpl$$inlined$unsafeFlow$1$lambda$17;
                    if (((Boolean) obj3).booleanValue()) {
                    }
                } else if (i2 == 3) {
                    flowCollector = (FlowCollector) this.L$12;
                    Object obj11 = this.L$11;
                    Object obj12 = this.L$10;
                    ChannelIterator channelIterator8 = (ChannelIterator) this.L$9;
                    ReceiveChannel receiveChannel18 = (ReceiveChannel) this.L$8;
                    Throwable th9 = (Throwable) this.L$7;
                    ReceiveChannel receiveChannel19 = (ReceiveChannel) this.L$6;
                    CombineKt$zipImpl$$inlined$unsafeFlow$1$lambda$1 combineKt$zipImpl$$inlined$unsafeFlow$1$lambda$18 = (CombineKt$zipImpl$$inlined$unsafeFlow$1$lambda$1) this.L$5;
                    ReceiveChannel receiveChannel20 = (ReceiveChannel) this.L$4;
                    channelIterator4 = (ChannelIterator) this.L$3;
                    ReceiveChannel receiveChannel21 = (ReceiveChannel) this.L$2;
                    ReceiveChannel receiveChannel22 = (ReceiveChannel) this.L$1;
                    CoroutineScope coroutineScope6 = (CoroutineScope) this.L$0;
                    try {
                        ResultKt.throwOnFailure(obj);
                        receiveChannel9 = receiveChannel22;
                        channelIterator = channelIterator8;
                        th3 = th9;
                        combineKt$zipImpl$$inlined$unsafeFlow$1$lambda$1 = this;
                        obj6 = coroutine_suspended;
                        obj7 = obj;
                        receiveChannel2 = receiveChannel21;
                        obj8 = obj11;
                        coroutineScope3 = coroutineScope6;
                        combineKt$zipImpl$$inlined$unsafeFlow$1$lambda$15 = combineKt$zipImpl$$inlined$unsafeFlow$1$lambda$18;
                        obj5 = obj12;
                        receiveChannel7 = receiveChannel18;
                        receiveChannel = receiveChannel19;
                        receiveChannel10 = receiveChannel20;
                        combineKt$zipImpl$$inlined$unsafeFlow$1$lambda$1.L$0 = coroutineScope3;
                        combineKt$zipImpl$$inlined$unsafeFlow$1$lambda$1.L$1 = receiveChannel9;
                        combineKt$zipImpl$$inlined$unsafeFlow$1$lambda$1.L$2 = receiveChannel2;
                        combineKt$zipImpl$$inlined$unsafeFlow$1$lambda$1.L$3 = channelIterator4;
                        combineKt$zipImpl$$inlined$unsafeFlow$1$lambda$1.L$4 = receiveChannel10;
                        combineKt$zipImpl$$inlined$unsafeFlow$1$lambda$1.L$5 = combineKt$zipImpl$$inlined$unsafeFlow$1$lambda$15;
                        combineKt$zipImpl$$inlined$unsafeFlow$1$lambda$1.L$6 = receiveChannel;
                        combineKt$zipImpl$$inlined$unsafeFlow$1$lambda$1.L$7 = th3;
                        combineKt$zipImpl$$inlined$unsafeFlow$1$lambda$1.L$8 = receiveChannel7;
                        combineKt$zipImpl$$inlined$unsafeFlow$1$lambda$1.L$9 = channelIterator;
                        combineKt$zipImpl$$inlined$unsafeFlow$1$lambda$1.L$10 = obj5;
                        combineKt$zipImpl$$inlined$unsafeFlow$1$lambda$1.L$11 = obj8;
                        combineKt$zipImpl$$inlined$unsafeFlow$1$lambda$1.label = 4;
                        if (flowCollector.emit(obj7, combineKt$zipImpl$$inlined$unsafeFlow$1$lambda$1) != obj6) {
                        }
                    } catch (Throwable th10) {
                        th = th10;
                        combineKt$zipImpl$$inlined$unsafeFlow$1$lambda$1 = this;
                        receiveChannel2 = receiveChannel21;
                        receiveChannel = receiveChannel19;
                    }
                } else if (i2 != 4) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                } else {
                    ChannelIterator channelIterator9 = (ChannelIterator) this.L$9;
                    receiveChannel7 = (ReceiveChannel) this.L$8;
                    Throwable th11 = (Throwable) this.L$7;
                    ReceiveChannel receiveChannel23 = (ReceiveChannel) this.L$6;
                    CombineKt$zipImpl$$inlined$unsafeFlow$1$lambda$1 combineKt$zipImpl$$inlined$unsafeFlow$1$lambda$19 = (CombineKt$zipImpl$$inlined$unsafeFlow$1$lambda$1) this.L$5;
                    ReceiveChannel receiveChannel24 = (ReceiveChannel) this.L$4;
                    ChannelIterator channelIterator10 = (ChannelIterator) this.L$3;
                    receiveChannel2 = (ReceiveChannel) this.L$2;
                    ReceiveChannel receiveChannel25 = (ReceiveChannel) this.L$1;
                    CoroutineScope coroutineScope7 = (CoroutineScope) this.L$0;
                    ResultKt.throwOnFailure(obj);
                    channelIterator = channelIterator9;
                    coroutineScope2 = coroutineScope7;
                    receiveChannel11 = receiveChannel23;
                    receiveChannel8 = receiveChannel24;
                    receiveChannel = receiveChannel25;
                    th4 = th11;
                    combineKt$zipImpl$$inlined$unsafeFlow$1$lambda$14 = combineKt$zipImpl$$inlined$unsafeFlow$1$lambda$19;
                    channelIterator4 = channelIterator10;
                    combineKt$zipImpl$$inlined$unsafeFlow$1$lambda$1 = this;
                    try {
                        channelIterator3 = channelIterator4;
                        combineKt$zipImpl$$inlined$unsafeFlow$1$lambda$1.L$0 = coroutineScope2;
                        combineKt$zipImpl$$inlined$unsafeFlow$1$lambda$1.L$1 = receiveChannel;
                        combineKt$zipImpl$$inlined$unsafeFlow$1$lambda$1.L$2 = receiveChannel2;
                        combineKt$zipImpl$$inlined$unsafeFlow$1$lambda$1.L$3 = channelIterator3;
                        combineKt$zipImpl$$inlined$unsafeFlow$1$lambda$1.L$4 = receiveChannel8;
                        combineKt$zipImpl$$inlined$unsafeFlow$1$lambda$1.L$5 = combineKt$zipImpl$$inlined$unsafeFlow$1$lambda$14;
                        combineKt$zipImpl$$inlined$unsafeFlow$1$lambda$1.L$6 = receiveChannel11;
                        combineKt$zipImpl$$inlined$unsafeFlow$1$lambda$1.L$7 = th4;
                        combineKt$zipImpl$$inlined$unsafeFlow$1$lambda$1.L$8 = receiveChannel7;
                        combineKt$zipImpl$$inlined$unsafeFlow$1$lambda$1.L$9 = channelIterator;
                        combineKt$zipImpl$$inlined$unsafeFlow$1$lambda$1.label = i3;
                        obj2 = channelIterator.hasNext(combineKt$zipImpl$$inlined$unsafeFlow$1$lambda$14);
                        if (obj2 != coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        ChannelIterator channelIterator11 = channelIterator3;
                        coroutineScope = coroutineScope2;
                        combineKt$zipImpl$$inlined$unsafeFlow$1$lambda$12 = combineKt$zipImpl$$inlined$unsafeFlow$1$lambda$1;
                        channelIterator2 = channelIterator11;
                        CombineKt$zipImpl$$inlined$unsafeFlow$1$lambda$1 combineKt$zipImpl$$inlined$unsafeFlow$1$lambda$110 = combineKt$zipImpl$$inlined$unsafeFlow$1$lambda$14;
                        receiveChannel4 = receiveChannel8;
                        receiveChannel5 = receiveChannel7;
                        combineKt$zipImpl$$inlined$unsafeFlow$1$lambda$13 = combineKt$zipImpl$$inlined$unsafeFlow$1$lambda$110;
                        ReceiveChannel receiveChannel26 = receiveChannel;
                        receiveChannel = receiveChannel11;
                        th = th4;
                        receiveChannel3 = receiveChannel26;
                        try {
                            if (!((Boolean) obj2).booleanValue()) {
                                obj5 = channelIterator.next();
                                combineKt$zipImpl$$inlined$unsafeFlow$1$lambda$12.L$0 = coroutineScope;
                                combineKt$zipImpl$$inlined$unsafeFlow$1$lambda$12.L$1 = receiveChannel3;
                                combineKt$zipImpl$$inlined$unsafeFlow$1$lambda$12.L$2 = receiveChannel2;
                                combineKt$zipImpl$$inlined$unsafeFlow$1$lambda$12.L$3 = channelIterator2;
                                combineKt$zipImpl$$inlined$unsafeFlow$1$lambda$12.L$4 = receiveChannel4;
                                combineKt$zipImpl$$inlined$unsafeFlow$1$lambda$12.L$5 = combineKt$zipImpl$$inlined$unsafeFlow$1$lambda$13;
                                combineKt$zipImpl$$inlined$unsafeFlow$1$lambda$12.L$6 = receiveChannel;
                                combineKt$zipImpl$$inlined$unsafeFlow$1$lambda$12.L$7 = th;
                                combineKt$zipImpl$$inlined$unsafeFlow$1$lambda$12.L$8 = receiveChannel5;
                                combineKt$zipImpl$$inlined$unsafeFlow$1$lambda$12.L$9 = channelIterator;
                                combineKt$zipImpl$$inlined$unsafeFlow$1$lambda$12.L$10 = obj5;
                                combineKt$zipImpl$$inlined$unsafeFlow$1$lambda$12.L$11 = obj5;
                                combineKt$zipImpl$$inlined$unsafeFlow$1$lambda$12.label = 2;
                                obj3 = channelIterator2.hasNext(combineKt$zipImpl$$inlined$unsafeFlow$1$lambda$12);
                                if (obj3 == coroutine_suspended) {
                                    return coroutine_suspended;
                                }
                                receiveChannel6 = receiveChannel2;
                                th2 = th;
                                obj4 = obj5;
                                ChannelIterator channelIterator12 = channelIterator2;
                                combineKt$zipImpl$$inlined$unsafeFlow$1$lambda$1 = combineKt$zipImpl$$inlined$unsafeFlow$1$lambda$12;
                                coroutineScope2 = coroutineScope;
                                channelIterator3 = channelIterator12;
                                CombineKt$zipImpl$$inlined$unsafeFlow$1$lambda$1 combineKt$zipImpl$$inlined$unsafeFlow$1$lambda$111 = combineKt$zipImpl$$inlined$unsafeFlow$1$lambda$13;
                                receiveChannel7 = receiveChannel5;
                                receiveChannel8 = receiveChannel4;
                                combineKt$zipImpl$$inlined$unsafeFlow$1$lambda$14 = combineKt$zipImpl$$inlined$unsafeFlow$1$lambda$111;
                                try {
                                    if (((Boolean) obj3).booleanValue()) {
                                        receiveChannel11 = receiveChannel;
                                        receiveChannel = receiveChannel3;
                                        th4 = th2;
                                        receiveChannel2 = receiveChannel6;
                                        i3 = 1;
                                        combineKt$zipImpl$$inlined$unsafeFlow$1$lambda$1.L$0 = coroutineScope2;
                                        combineKt$zipImpl$$inlined$unsafeFlow$1$lambda$1.L$1 = receiveChannel;
                                        combineKt$zipImpl$$inlined$unsafeFlow$1$lambda$1.L$2 = receiveChannel2;
                                        combineKt$zipImpl$$inlined$unsafeFlow$1$lambda$1.L$3 = channelIterator3;
                                        combineKt$zipImpl$$inlined$unsafeFlow$1$lambda$1.L$4 = receiveChannel8;
                                        combineKt$zipImpl$$inlined$unsafeFlow$1$lambda$1.L$5 = combineKt$zipImpl$$inlined$unsafeFlow$1$lambda$14;
                                        combineKt$zipImpl$$inlined$unsafeFlow$1$lambda$1.L$6 = receiveChannel11;
                                        combineKt$zipImpl$$inlined$unsafeFlow$1$lambda$1.L$7 = th4;
                                        combineKt$zipImpl$$inlined$unsafeFlow$1$lambda$1.L$8 = receiveChannel7;
                                        combineKt$zipImpl$$inlined$unsafeFlow$1$lambda$1.L$9 = channelIterator;
                                        combineKt$zipImpl$$inlined$unsafeFlow$1$lambda$1.label = i3;
                                        obj2 = channelIterator.hasNext(combineKt$zipImpl$$inlined$unsafeFlow$1$lambda$14);
                                        if (obj2 != coroutine_suspended) {
                                        }
                                    } else {
                                        FlowCollector flowCollector2 = combineKt$zipImpl$$inlined$unsafeFlow$1$lambda$1.$this_unsafeFlow;
                                        Function3 function3 = combineKt$zipImpl$$inlined$unsafeFlow$1$lambda$1.this$0.$transform$inlined;
                                        Object obj13 = coroutine_suspended;
                                        Symbol symbol = NullSurrogateKt.NULL;
                                        Object obj14 = obj4 == symbol ? null : obj4;
                                        Object next = channelIterator3.next();
                                        if (next == symbol) {
                                            next = null;
                                        }
                                        combineKt$zipImpl$$inlined$unsafeFlow$1$lambda$1.L$0 = coroutineScope2;
                                        combineKt$zipImpl$$inlined$unsafeFlow$1$lambda$1.L$1 = receiveChannel3;
                                        combineKt$zipImpl$$inlined$unsafeFlow$1$lambda$1.L$2 = receiveChannel6;
                                        combineKt$zipImpl$$inlined$unsafeFlow$1$lambda$1.L$3 = channelIterator3;
                                        combineKt$zipImpl$$inlined$unsafeFlow$1$lambda$1.L$4 = receiveChannel8;
                                        combineKt$zipImpl$$inlined$unsafeFlow$1$lambda$1.L$5 = combineKt$zipImpl$$inlined$unsafeFlow$1$lambda$14;
                                        combineKt$zipImpl$$inlined$unsafeFlow$1$lambda$1.L$6 = receiveChannel;
                                        combineKt$zipImpl$$inlined$unsafeFlow$1$lambda$1.L$7 = th2;
                                        combineKt$zipImpl$$inlined$unsafeFlow$1$lambda$1.L$8 = receiveChannel7;
                                        combineKt$zipImpl$$inlined$unsafeFlow$1$lambda$1.L$9 = channelIterator;
                                        combineKt$zipImpl$$inlined$unsafeFlow$1$lambda$1.L$10 = obj5;
                                        combineKt$zipImpl$$inlined$unsafeFlow$1$lambda$1.L$11 = obj4;
                                        combineKt$zipImpl$$inlined$unsafeFlow$1$lambda$1.L$12 = flowCollector2;
                                        combineKt$zipImpl$$inlined$unsafeFlow$1$lambda$1.label = 3;
                                        CoroutineScope coroutineScope8 = coroutineScope2;
                                        obj7 = function3.invoke(obj14, next, combineKt$zipImpl$$inlined$unsafeFlow$1$lambda$1);
                                        obj6 = obj13;
                                        if (obj7 == obj6) {
                                            return obj6;
                                        }
                                        flowCollector = flowCollector2;
                                        receiveChannel9 = receiveChannel3;
                                        receiveChannel10 = receiveChannel8;
                                        combineKt$zipImpl$$inlined$unsafeFlow$1$lambda$15 = combineKt$zipImpl$$inlined$unsafeFlow$1$lambda$14;
                                        th3 = th2;
                                        receiveChannel2 = receiveChannel6;
                                        channelIterator4 = channelIterator3;
                                        obj8 = obj4;
                                        coroutineScope3 = coroutineScope8;
                                        try {
                                            combineKt$zipImpl$$inlined$unsafeFlow$1$lambda$1.L$0 = coroutineScope3;
                                            combineKt$zipImpl$$inlined$unsafeFlow$1$lambda$1.L$1 = receiveChannel9;
                                            combineKt$zipImpl$$inlined$unsafeFlow$1$lambda$1.L$2 = receiveChannel2;
                                            combineKt$zipImpl$$inlined$unsafeFlow$1$lambda$1.L$3 = channelIterator4;
                                            combineKt$zipImpl$$inlined$unsafeFlow$1$lambda$1.L$4 = receiveChannel10;
                                            combineKt$zipImpl$$inlined$unsafeFlow$1$lambda$1.L$5 = combineKt$zipImpl$$inlined$unsafeFlow$1$lambda$15;
                                            combineKt$zipImpl$$inlined$unsafeFlow$1$lambda$1.L$6 = receiveChannel;
                                            combineKt$zipImpl$$inlined$unsafeFlow$1$lambda$1.L$7 = th3;
                                            combineKt$zipImpl$$inlined$unsafeFlow$1$lambda$1.L$8 = receiveChannel7;
                                            combineKt$zipImpl$$inlined$unsafeFlow$1$lambda$1.L$9 = channelIterator;
                                            combineKt$zipImpl$$inlined$unsafeFlow$1$lambda$1.L$10 = obj5;
                                            combineKt$zipImpl$$inlined$unsafeFlow$1$lambda$1.L$11 = obj8;
                                            combineKt$zipImpl$$inlined$unsafeFlow$1$lambda$1.label = 4;
                                            if (flowCollector.emit(obj7, combineKt$zipImpl$$inlined$unsafeFlow$1$lambda$1) != obj6) {
                                                return obj6;
                                            }
                                            coroutine_suspended = obj6;
                                            coroutineScope2 = coroutineScope3;
                                            receiveChannel11 = receiveChannel;
                                            receiveChannel = receiveChannel9;
                                            Throwable th12 = th3;
                                            combineKt$zipImpl$$inlined$unsafeFlow$1$lambda$14 = combineKt$zipImpl$$inlined$unsafeFlow$1$lambda$15;
                                            receiveChannel8 = receiveChannel10;
                                            th4 = th12;
                                            channelIterator3 = channelIterator4;
                                            i3 = 1;
                                            combineKt$zipImpl$$inlined$unsafeFlow$1$lambda$1.L$0 = coroutineScope2;
                                            combineKt$zipImpl$$inlined$unsafeFlow$1$lambda$1.L$1 = receiveChannel;
                                            combineKt$zipImpl$$inlined$unsafeFlow$1$lambda$1.L$2 = receiveChannel2;
                                            combineKt$zipImpl$$inlined$unsafeFlow$1$lambda$1.L$3 = channelIterator3;
                                            combineKt$zipImpl$$inlined$unsafeFlow$1$lambda$1.L$4 = receiveChannel8;
                                            combineKt$zipImpl$$inlined$unsafeFlow$1$lambda$1.L$5 = combineKt$zipImpl$$inlined$unsafeFlow$1$lambda$14;
                                            combineKt$zipImpl$$inlined$unsafeFlow$1$lambda$1.L$6 = receiveChannel11;
                                            combineKt$zipImpl$$inlined$unsafeFlow$1$lambda$1.L$7 = th4;
                                            combineKt$zipImpl$$inlined$unsafeFlow$1$lambda$1.L$8 = receiveChannel7;
                                            combineKt$zipImpl$$inlined$unsafeFlow$1$lambda$1.L$9 = channelIterator;
                                            combineKt$zipImpl$$inlined$unsafeFlow$1$lambda$1.label = i3;
                                            obj2 = channelIterator.hasNext(combineKt$zipImpl$$inlined$unsafeFlow$1$lambda$14);
                                            if (obj2 != coroutine_suspended) {
                                            }
                                        } catch (Throwable th13) {
                                            th = th13;
                                        }
                                    }
                                } catch (Throwable th14) {
                                    th6 = th14;
                                    receiveChannel2 = receiveChannel6;
                                }
                            } else {
                                Unit unit = Unit.INSTANCE;
                                try {
                                    try {
                                        ChannelsKt.cancelConsumed(receiveChannel, th);
                                    } catch (Throwable th15) {
                                        th5 = th15;
                                        if (!receiveChannel2.isClosedForReceive()) {
                                            receiveChannel2.cancel((CancellationException) new AbortFlowException(combineKt$zipImpl$$inlined$unsafeFlow$1$lambda$12.$this_unsafeFlow));
                                        }
                                        throw th5;
                                    }
                                } catch (AbortFlowException e3) {
                                    e2 = e3;
                                    FlowExceptions_commonKt.checkOwnership(e2, combineKt$zipImpl$$inlined$unsafeFlow$1$lambda$12.$this_unsafeFlow);
                                    if (!receiveChannel2.isClosedForReceive()) {
                                        abortFlowException = new AbortFlowException(combineKt$zipImpl$$inlined$unsafeFlow$1$lambda$12.$this_unsafeFlow);
                                        receiveChannel2.cancel((CancellationException) abortFlowException);
                                    }
                                    return Unit.INSTANCE;
                                }
                                if (!receiveChannel2.isClosedForReceive()) {
                                    abortFlowException = new AbortFlowException(combineKt$zipImpl$$inlined$unsafeFlow$1$lambda$12.$this_unsafeFlow);
                                    receiveChannel2.cancel((CancellationException) abortFlowException);
                                }
                                return Unit.INSTANCE;
                            }
                        } catch (Throwable th16) {
                            th6 = th16;
                            combineKt$zipImpl$$inlined$unsafeFlow$1$lambda$1 = combineKt$zipImpl$$inlined$unsafeFlow$1$lambda$12;
                        }
                    } catch (Throwable th17) {
                        th6 = th17;
                        receiveChannel = receiveChannel11;
                    }
                    i3 = 1;
                }
            } catch (Throwable th18) {
                th = th18;
                combineKt$zipImpl$$inlined$unsafeFlow$1$lambda$1 = this;
            }
        } else {
            ResultKt.throwOnFailure(obj);
            coroutineScope2 = this.p$;
            receiveChannel = CombineKt.asChannel(coroutineScope2, this.this$0.$flow$inlined);
            receiveChannel2 = CombineKt.asChannel(coroutineScope2, this.this$0.$flow2$inlined);
            if (receiveChannel2 != null) {
                ((SendChannel) receiveChannel2).invokeOnClose(new Function1<Throwable, Unit>() { // from class: kotlinx.coroutines.flow.internal.CombineKt$zipImpl$$inlined$unsafeFlow$1$lambda$1.1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(Throwable th19) {
                        invoke2(th19);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke  reason: avoid collision after fix types in other method */
                    public final void invoke2(@Nullable Throwable th19) {
                        if (receiveChannel.isClosedForReceive()) {
                            return;
                        }
                        receiveChannel.cancel((CancellationException) new AbortFlowException(CombineKt$zipImpl$$inlined$unsafeFlow$1$lambda$1.this.$this_unsafeFlow));
                    }
                });
                channelIterator3 = receiveChannel2.iterator();
                try {
                    channelIterator = receiveChannel.iterator();
                    combineKt$zipImpl$$inlined$unsafeFlow$1$lambda$14 = this;
                    combineKt$zipImpl$$inlined$unsafeFlow$1$lambda$1 = combineKt$zipImpl$$inlined$unsafeFlow$1$lambda$14;
                    receiveChannel8 = receiveChannel;
                    receiveChannel11 = receiveChannel8;
                    receiveChannel7 = receiveChannel11;
                    th4 = null;
                    combineKt$zipImpl$$inlined$unsafeFlow$1$lambda$1.L$0 = coroutineScope2;
                    combineKt$zipImpl$$inlined$unsafeFlow$1$lambda$1.L$1 = receiveChannel;
                    combineKt$zipImpl$$inlined$unsafeFlow$1$lambda$1.L$2 = receiveChannel2;
                    combineKt$zipImpl$$inlined$unsafeFlow$1$lambda$1.L$3 = channelIterator3;
                    combineKt$zipImpl$$inlined$unsafeFlow$1$lambda$1.L$4 = receiveChannel8;
                    combineKt$zipImpl$$inlined$unsafeFlow$1$lambda$1.L$5 = combineKt$zipImpl$$inlined$unsafeFlow$1$lambda$14;
                    combineKt$zipImpl$$inlined$unsafeFlow$1$lambda$1.L$6 = receiveChannel11;
                    combineKt$zipImpl$$inlined$unsafeFlow$1$lambda$1.L$7 = th4;
                    combineKt$zipImpl$$inlined$unsafeFlow$1$lambda$1.L$8 = receiveChannel7;
                    combineKt$zipImpl$$inlined$unsafeFlow$1$lambda$1.L$9 = channelIterator;
                    combineKt$zipImpl$$inlined$unsafeFlow$1$lambda$1.label = i3;
                    obj2 = channelIterator.hasNext(combineKt$zipImpl$$inlined$unsafeFlow$1$lambda$14);
                    if (obj2 != coroutine_suspended) {
                    }
                } catch (Throwable th19) {
                    th = th19;
                    combineKt$zipImpl$$inlined$unsafeFlow$1$lambda$1 = this;
                }
            } else {
                throw new TypeCastException("null cannot be cast to non-null type kotlinx.coroutines.channels.SendChannel<*>");
            }
        }
        th6 = th;
        try {
            throw th6;
        } catch (Throwable th20) {
            try {
                ChannelsKt.cancelConsumed(receiveChannel, th6);
                throw th20;
            } catch (AbortFlowException e4) {
                e2 = e4;
                combineKt$zipImpl$$inlined$unsafeFlow$1$lambda$12 = combineKt$zipImpl$$inlined$unsafeFlow$1$lambda$1;
                FlowExceptions_commonKt.checkOwnership(e2, combineKt$zipImpl$$inlined$unsafeFlow$1$lambda$12.$this_unsafeFlow);
                if (!receiveChannel2.isClosedForReceive()) {
                }
                return Unit.INSTANCE;
            } catch (Throwable th21) {
                th5 = th21;
                combineKt$zipImpl$$inlined$unsafeFlow$1$lambda$12 = combineKt$zipImpl$$inlined$unsafeFlow$1$lambda$1;
                if (!receiveChannel2.isClosedForReceive()) {
                }
                throw th5;
            }
        }
    }
}

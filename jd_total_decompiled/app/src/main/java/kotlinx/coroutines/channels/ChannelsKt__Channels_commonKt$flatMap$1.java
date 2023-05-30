package kotlinx.coroutines.channels;

import com.jingdong.app.mall.e;
import com.jingdong.sdk.platform.business.personal.R2;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX INFO: Add missing generic type declarations: [R] */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\u0010\u0006\u001a\u00020\u0003\"\u0004\b\u0000\u0010\u0000\"\u0004\b\u0001\u0010\u0001*\b\u0012\u0004\u0012\u00028\u00010\u0002H\u008a@\u00a2\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"E", "R", "Lkotlinx/coroutines/channels/ProducerScope;", "", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "<anonymous>"}, k = 3, mv = {1, 4, 0})
@DebugMetadata(c = "kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$flatMap$1", f = "Channels.common.kt", i = {0, 1, 1, 2, 2}, l = {R2.attr.listMenuViewStyle, R2.attr.listPopupWindowStyle, R2.attr.listPopupWindowStyle}, m = "invokeSuspend", n = {"$this$produce", "$this$produce", e.a, "$this$produce", e.a}, s = {"L$0", "L$0", "L$1", "L$0", "L$1"})
/* loaded from: classes11.dex */
public final class ChannelsKt__Channels_commonKt$flatMap$1<R> extends SuspendLambda implements Function2<ProducerScope<? super R>, Continuation<? super Unit>, Object> {
    final /* synthetic */ ReceiveChannel $this_flatMap;
    final /* synthetic */ Function2 $transform;
    Object L$0;
    Object L$1;
    Object L$2;
    int label;
    private ProducerScope p$;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ChannelsKt__Channels_commonKt$flatMap$1(ReceiveChannel receiveChannel, Function2 function2, Continuation continuation) {
        super(2, continuation);
        this.$this_flatMap = receiveChannel;
        this.$transform = function2;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        ChannelsKt__Channels_commonKt$flatMap$1 channelsKt__Channels_commonKt$flatMap$1 = new ChannelsKt__Channels_commonKt$flatMap$1(this.$this_flatMap, this.$transform, continuation);
        channelsKt__Channels_commonKt$flatMap$1.p$ = (ProducerScope) obj;
        return channelsKt__Channels_commonKt$flatMap$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Continuation<? super Unit> continuation) {
        return ((ChannelsKt__Channels_commonKt$flatMap$1) create(obj, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x0060 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0061  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x006f  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0097 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0098  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x009d  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:27:0x0098 -> B:14:0x0054). Please submit an issue!!! */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(@NotNull Object obj) {
        Object coroutine_suspended;
        ChannelIterator it;
        ProducerScope producerScope;
        ProducerScope producerScope2;
        ChannelIterator channelIterator;
        Object obj2;
        ChannelsKt__Channels_commonKt$flatMap$1<R> channelsKt__Channels_commonKt$flatMap$1;
        ChannelsKt__Channels_commonKt$flatMap$1<R> channelsKt__Channels_commonKt$flatMap$12;
        Object hasNext;
        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = this.label;
        if (i2 == 0) {
            ResultKt.throwOnFailure(obj);
            ProducerScope producerScope3 = this.p$;
            it = this.$this_flatMap.iterator();
            producerScope = producerScope3;
        } else if (i2 == 1) {
            ResultKt.throwOnFailure(obj);
            producerScope2 = (ProducerScope) this.L$0;
            channelIterator = (ChannelIterator) this.L$1;
            obj2 = coroutine_suspended;
            channelsKt__Channels_commonKt$flatMap$1 = this;
            if (!((Boolean) obj).booleanValue()) {
            }
        } else if (i2 == 2) {
            Object obj3 = this.L$1;
            producerScope2 = (ProducerScope) this.L$0;
            ResultKt.throwOnFailure(obj);
            Object obj4 = obj3;
            channelIterator = (ChannelIterator) this.L$2;
            obj2 = coroutine_suspended;
            channelsKt__Channels_commonKt$flatMap$1 = this;
            channelsKt__Channels_commonKt$flatMap$1.L$0 = producerScope2;
            channelsKt__Channels_commonKt$flatMap$1.L$1 = obj4;
            channelsKt__Channels_commonKt$flatMap$1.L$2 = channelIterator;
            channelsKt__Channels_commonKt$flatMap$1.label = 3;
            if (ChannelsKt.toChannel((ReceiveChannel) obj, producerScope2, channelsKt__Channels_commonKt$flatMap$1) != obj2) {
                return obj2;
            }
            channelsKt__Channels_commonKt$flatMap$12 = channelsKt__Channels_commonKt$flatMap$1;
            coroutine_suspended = obj2;
            it = channelIterator;
            producerScope = producerScope2;
            channelsKt__Channels_commonKt$flatMap$12.L$0 = producerScope;
            channelsKt__Channels_commonKt$flatMap$12.L$1 = it;
            channelsKt__Channels_commonKt$flatMap$12.label = 1;
            hasNext = it.hasNext(channelsKt__Channels_commonKt$flatMap$12);
            if (hasNext != coroutine_suspended) {
                return coroutine_suspended;
            }
            Object obj5 = coroutine_suspended;
            channelsKt__Channels_commonKt$flatMap$1 = channelsKt__Channels_commonKt$flatMap$12;
            obj = hasNext;
            producerScope2 = producerScope;
            channelIterator = it;
            obj2 = obj5;
            if (!((Boolean) obj).booleanValue()) {
                Object next = channelIterator.next();
                Function2 function2 = channelsKt__Channels_commonKt$flatMap$1.$transform;
                channelsKt__Channels_commonKt$flatMap$1.L$0 = producerScope2;
                channelsKt__Channels_commonKt$flatMap$1.L$1 = next;
                channelsKt__Channels_commonKt$flatMap$1.L$2 = channelIterator;
                channelsKt__Channels_commonKt$flatMap$1.label = 2;
                Object invoke = function2.invoke(next, channelsKt__Channels_commonKt$flatMap$1);
                if (invoke == obj2) {
                    return obj2;
                }
                obj4 = next;
                obj = invoke;
                channelsKt__Channels_commonKt$flatMap$1.L$0 = producerScope2;
                channelsKt__Channels_commonKt$flatMap$1.L$1 = obj4;
                channelsKt__Channels_commonKt$flatMap$1.L$2 = channelIterator;
                channelsKt__Channels_commonKt$flatMap$1.label = 3;
                if (ChannelsKt.toChannel((ReceiveChannel) obj, producerScope2, channelsKt__Channels_commonKt$flatMap$1) != obj2) {
                }
            } else {
                return Unit.INSTANCE;
            }
        } else if (i2 != 3) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        } else {
            it = (ChannelIterator) this.L$2;
            producerScope = (ProducerScope) this.L$0;
            ResultKt.throwOnFailure(obj);
        }
        channelsKt__Channels_commonKt$flatMap$12 = this;
        channelsKt__Channels_commonKt$flatMap$12.L$0 = producerScope;
        channelsKt__Channels_commonKt$flatMap$12.L$1 = it;
        channelsKt__Channels_commonKt$flatMap$12.label = 1;
        hasNext = it.hasNext(channelsKt__Channels_commonKt$flatMap$12);
        if (hasNext != coroutine_suspended) {
        }
    }
}

package kotlinx.coroutines.channels;

import com.jingdong.app.mall.e;
import com.jingdong.sdk.platform.business.personal.R2;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX INFO: Add missing generic type declarations: [R] */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\u0010\u0006\u001a\u00020\u0003\"\u0004\b\u0000\u0010\u0000\"\u0004\b\u0001\u0010\u0001*\b\u0012\u0004\u0012\u00028\u00010\u0002H\u008a@\u00a2\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"E", "R", "Lkotlinx/coroutines/channels/ProducerScope;", "", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "<anonymous>"}, k = 3, mv = {1, 4, 0})
@DebugMetadata(c = "kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$mapIndexed$1", f = "Channels.common.kt", i = {0, 0, 1, 1, 1, 2, 2, 2}, l = {R2.attr.motionDebug, R2.attr.motionDurationLong1, R2.attr.motionDurationLong1}, m = "invokeSuspend", n = {"$this$produce", "index", "$this$produce", "index", e.a, "$this$produce", "index", e.a}, s = {"L$0", "I$0", "L$0", "I$0", "L$1", "L$0", "I$0", "L$1"})
/* loaded from: classes11.dex */
public final class ChannelsKt__Channels_commonKt$mapIndexed$1<R> extends SuspendLambda implements Function2<ProducerScope<? super R>, Continuation<? super Unit>, Object> {
    final /* synthetic */ ReceiveChannel $this_mapIndexed;
    final /* synthetic */ Function3 $transform;
    int I$0;
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    int label;
    private ProducerScope p$;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ChannelsKt__Channels_commonKt$mapIndexed$1(ReceiveChannel receiveChannel, Function3 function3, Continuation continuation) {
        super(2, continuation);
        this.$this_mapIndexed = receiveChannel;
        this.$transform = function3;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        ChannelsKt__Channels_commonKt$mapIndexed$1 channelsKt__Channels_commonKt$mapIndexed$1 = new ChannelsKt__Channels_commonKt$mapIndexed$1(this.$this_mapIndexed, this.$transform, continuation);
        channelsKt__Channels_commonKt$mapIndexed$1.p$ = (ProducerScope) obj;
        return channelsKt__Channels_commonKt$mapIndexed$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Continuation<? super Unit> continuation) {
        return ((ChannelsKt__Channels_commonKt$mapIndexed$1) create(obj, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x007b  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x00b0 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:26:0x00b1  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x00b6  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:26:0x00b1 -> B:13:0x0060). Please submit an issue!!! */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(@NotNull Object obj) {
        Object coroutine_suspended;
        ProducerScope producerScope;
        ChannelsKt__Channels_commonKt$mapIndexed$1<R> channelsKt__Channels_commonKt$mapIndexed$1;
        ChannelIterator it;
        int i2;
        ChannelsKt__Channels_commonKt$mapIndexed$1<R> channelsKt__Channels_commonKt$mapIndexed$12;
        Object obj2;
        int i3;
        ProducerScope producerScope2;
        ChannelIterator channelIterator;
        ProducerScope producerScope3;
        ChannelsKt__Channels_commonKt$mapIndexed$1<R> channelsKt__Channels_commonKt$mapIndexed$13;
        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i4 = this.label;
        if (i4 == 0) {
            ResultKt.throwOnFailure(obj);
            producerScope = this.p$;
            channelsKt__Channels_commonKt$mapIndexed$1 = this;
            it = this.$this_mapIndexed.iterator();
            i2 = 0;
        } else if (i4 == 1) {
            it = (ChannelIterator) this.L$1;
            i2 = this.I$0;
            producerScope2 = (ProducerScope) this.L$0;
            ResultKt.throwOnFailure(obj);
            channelsKt__Channels_commonKt$mapIndexed$13 = this;
            if (!((Boolean) obj).booleanValue()) {
                Object next = it.next();
                Function3 function3 = channelsKt__Channels_commonKt$mapIndexed$13.$transform;
                Integer boxInt = Boxing.boxInt(i2);
                int i5 = i2 + 1;
                channelsKt__Channels_commonKt$mapIndexed$13.L$0 = producerScope2;
                channelsKt__Channels_commonKt$mapIndexed$13.I$0 = i5;
                channelsKt__Channels_commonKt$mapIndexed$13.L$1 = next;
                channelsKt__Channels_commonKt$mapIndexed$13.L$2 = it;
                channelsKt__Channels_commonKt$mapIndexed$13.L$3 = producerScope2;
                channelsKt__Channels_commonKt$mapIndexed$13.label = 2;
                Object invoke = function3.invoke(boxInt, next, channelsKt__Channels_commonKt$mapIndexed$13);
                if (invoke == coroutine_suspended) {
                    return coroutine_suspended;
                }
                channelsKt__Channels_commonKt$mapIndexed$12 = channelsKt__Channels_commonKt$mapIndexed$13;
                i3 = i5;
                channelIterator = it;
                producerScope3 = producerScope2;
                obj2 = next;
                obj = invoke;
                channelsKt__Channels_commonKt$mapIndexed$12.L$0 = producerScope2;
                channelsKt__Channels_commonKt$mapIndexed$12.I$0 = i3;
                channelsKt__Channels_commonKt$mapIndexed$12.L$1 = obj2;
                channelsKt__Channels_commonKt$mapIndexed$12.L$2 = channelIterator;
                channelsKt__Channels_commonKt$mapIndexed$12.label = 3;
                if (producerScope3.send(obj, channelsKt__Channels_commonKt$mapIndexed$12) != coroutine_suspended) {
                }
            } else {
                return Unit.INSTANCE;
            }
        } else if (i4 == 2) {
            producerScope3 = (ProducerScope) this.L$3;
            channelIterator = (ChannelIterator) this.L$2;
            Object obj3 = this.L$1;
            i3 = this.I$0;
            ProducerScope producerScope4 = (ProducerScope) this.L$0;
            ResultKt.throwOnFailure(obj);
            channelsKt__Channels_commonKt$mapIndexed$12 = this;
            obj2 = obj3;
            producerScope2 = producerScope4;
            channelsKt__Channels_commonKt$mapIndexed$12.L$0 = producerScope2;
            channelsKt__Channels_commonKt$mapIndexed$12.I$0 = i3;
            channelsKt__Channels_commonKt$mapIndexed$12.L$1 = obj2;
            channelsKt__Channels_commonKt$mapIndexed$12.L$2 = channelIterator;
            channelsKt__Channels_commonKt$mapIndexed$12.label = 3;
            if (producerScope3.send(obj, channelsKt__Channels_commonKt$mapIndexed$12) != coroutine_suspended) {
                return coroutine_suspended;
            }
            it = channelIterator;
            producerScope = producerScope2;
            i2 = i3;
            channelsKt__Channels_commonKt$mapIndexed$1 = channelsKt__Channels_commonKt$mapIndexed$12;
        } else if (i4 != 3) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        } else {
            it = (ChannelIterator) this.L$2;
            i2 = this.I$0;
            ResultKt.throwOnFailure(obj);
            producerScope = (ProducerScope) this.L$0;
            channelsKt__Channels_commonKt$mapIndexed$1 = this;
        }
        channelsKt__Channels_commonKt$mapIndexed$1.L$0 = producerScope;
        channelsKt__Channels_commonKt$mapIndexed$1.I$0 = i2;
        channelsKt__Channels_commonKt$mapIndexed$1.L$1 = it;
        channelsKt__Channels_commonKt$mapIndexed$1.label = 1;
        Object hasNext = it.hasNext(channelsKt__Channels_commonKt$mapIndexed$1);
        if (hasNext == coroutine_suspended) {
            return coroutine_suspended;
        }
        ChannelsKt__Channels_commonKt$mapIndexed$1<R> channelsKt__Channels_commonKt$mapIndexed$14 = channelsKt__Channels_commonKt$mapIndexed$1;
        producerScope2 = producerScope;
        obj = hasNext;
        channelsKt__Channels_commonKt$mapIndexed$13 = channelsKt__Channels_commonKt$mapIndexed$14;
        if (!((Boolean) obj).booleanValue()) {
        }
    }
}

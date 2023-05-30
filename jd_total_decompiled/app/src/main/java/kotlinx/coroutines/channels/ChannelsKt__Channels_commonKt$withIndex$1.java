package kotlinx.coroutines.channels;

import com.jingdong.app.mall.e;
import com.jingdong.sdk.platform.business.personal.R2;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.IndexedValue;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX INFO: Add missing generic type declarations: [E] */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\u0010\u0006\u001a\u00020\u0003\"\u0004\b\u0000\u0010\u0000*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u00020\u0001H\u008a@\u00a2\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"E", "Lkotlinx/coroutines/channels/ProducerScope;", "Lkotlin/collections/IndexedValue;", "", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "<anonymous>"}, k = 3, mv = {1, 4, 0})
@DebugMetadata(c = "kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$withIndex$1", f = "Channels.common.kt", i = {0, 0, 1, 1, 1}, l = {R2.attr.re_pstsTabTextSize, R2.attr.re_pstsTabTextStyle}, m = "invokeSuspend", n = {"$this$produce", "index", "$this$produce", "index", e.a}, s = {"L$0", "I$0", "L$0", "I$0", "L$1"})
/* loaded from: classes11.dex */
public final class ChannelsKt__Channels_commonKt$withIndex$1<E> extends SuspendLambda implements Function2<ProducerScope<? super IndexedValue<? extends E>>, Continuation<? super Unit>, Object> {
    final /* synthetic */ ReceiveChannel $this_withIndex;
    int I$0;
    Object L$0;
    Object L$1;
    Object L$2;
    int label;
    private ProducerScope p$;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ChannelsKt__Channels_commonKt$withIndex$1(ReceiveChannel receiveChannel, Continuation continuation) {
        super(2, continuation);
        this.$this_withIndex = receiveChannel;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        ChannelsKt__Channels_commonKt$withIndex$1 channelsKt__Channels_commonKt$withIndex$1 = new ChannelsKt__Channels_commonKt$withIndex$1(this.$this_withIndex, continuation);
        channelsKt__Channels_commonKt$withIndex$1.p$ = (ProducerScope) obj;
        return channelsKt__Channels_commonKt$withIndex$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Continuation<? super Unit> continuation) {
        return ((ChannelsKt__Channels_commonKt$withIndex$1) create(obj, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x0063  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0084  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:20:0x007f -> B:11:0x0045). Please submit an issue!!! */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(@NotNull Object obj) {
        Object coroutine_suspended;
        ProducerScope producerScope;
        int i2;
        ChannelIterator<E> it;
        ChannelsKt__Channels_commonKt$withIndex$1<E> channelsKt__Channels_commonKt$withIndex$1;
        ChannelsKt__Channels_commonKt$withIndex$1<E> channelsKt__Channels_commonKt$withIndex$12;
        ProducerScope producerScope2;
        int i3;
        ChannelIterator<E> channelIterator;
        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i4 = this.label;
        if (i4 == 0) {
            ResultKt.throwOnFailure(obj);
            producerScope = this.p$;
            i2 = 0;
            it = this.$this_withIndex.iterator();
            channelsKt__Channels_commonKt$withIndex$1 = this;
        } else if (i4 == 1) {
            channelIterator = (ChannelIterator) this.L$1;
            i3 = this.I$0;
            producerScope2 = (ProducerScope) this.L$0;
            ResultKt.throwOnFailure(obj);
            channelsKt__Channels_commonKt$withIndex$12 = this;
            if (!((Boolean) obj).booleanValue()) {
                E next = channelIterator.next();
                int i5 = i3 + 1;
                IndexedValue indexedValue = new IndexedValue(i3, next);
                channelsKt__Channels_commonKt$withIndex$12.L$0 = producerScope2;
                channelsKt__Channels_commonKt$withIndex$12.I$0 = i5;
                channelsKt__Channels_commonKt$withIndex$12.L$1 = next;
                channelsKt__Channels_commonKt$withIndex$12.L$2 = channelIterator;
                channelsKt__Channels_commonKt$withIndex$12.label = 2;
                if (producerScope2.send(indexedValue, channelsKt__Channels_commonKt$withIndex$12) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                it = channelIterator;
                producerScope = producerScope2;
                channelsKt__Channels_commonKt$withIndex$1 = channelsKt__Channels_commonKt$withIndex$12;
                i2 = i5;
            } else {
                return Unit.INSTANCE;
            }
        } else if (i4 != 2) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        } else {
            int i6 = this.I$0;
            ResultKt.throwOnFailure(obj);
            producerScope = (ProducerScope) this.L$0;
            channelsKt__Channels_commonKt$withIndex$1 = this;
            it = (ChannelIterator) this.L$2;
            i2 = i6;
        }
        channelsKt__Channels_commonKt$withIndex$1.L$0 = producerScope;
        channelsKt__Channels_commonKt$withIndex$1.I$0 = i2;
        channelsKt__Channels_commonKt$withIndex$1.L$1 = it;
        channelsKt__Channels_commonKt$withIndex$1.label = 1;
        Object hasNext = it.hasNext(channelsKt__Channels_commonKt$withIndex$1);
        if (hasNext == coroutine_suspended) {
            return coroutine_suspended;
        }
        ChannelsKt__Channels_commonKt$withIndex$1<E> channelsKt__Channels_commonKt$withIndex$13 = channelsKt__Channels_commonKt$withIndex$1;
        producerScope2 = producerScope;
        obj = hasNext;
        channelsKt__Channels_commonKt$withIndex$12 = channelsKt__Channels_commonKt$withIndex$13;
        ChannelIterator<E> channelIterator2 = it;
        i3 = i2;
        channelIterator = channelIterator2;
        if (!((Boolean) obj).booleanValue()) {
        }
    }
}

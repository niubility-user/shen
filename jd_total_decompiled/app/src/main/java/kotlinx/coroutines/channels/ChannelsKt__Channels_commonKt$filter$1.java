package kotlinx.coroutines.channels;

import com.jingdong.app.mall.e;
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
/* JADX INFO: Add missing generic type declarations: [E] */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\u0010\u0005\u001a\u00020\u0002\"\u0004\b\u0000\u0010\u0000*\b\u0012\u0004\u0012\u00028\u00000\u0001H\u008a@\u00a2\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"E", "Lkotlinx/coroutines/channels/ProducerScope;", "", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "<anonymous>"}, k = 3, mv = {1, 4, 0})
@DebugMetadata(c = "kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$filter$1", f = "Channels.common.kt", i = {0, 1, 1, 2, 2}, l = {751, 752, 752}, m = "invokeSuspend", n = {"$this$produce", "$this$produce", e.a, "$this$produce", e.a}, s = {"L$0", "L$0", "L$1", "L$0", "L$1"})
/* loaded from: classes11.dex */
public final class ChannelsKt__Channels_commonKt$filter$1<E> extends SuspendLambda implements Function2<ProducerScope<? super E>, Continuation<? super Unit>, Object> {
    final /* synthetic */ Function2 $predicate;
    final /* synthetic */ ReceiveChannel $this_filter;
    Object L$0;
    Object L$1;
    Object L$2;
    int label;
    private ProducerScope p$;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ChannelsKt__Channels_commonKt$filter$1(ReceiveChannel receiveChannel, Function2 function2, Continuation continuation) {
        super(2, continuation);
        this.$this_filter = receiveChannel;
        this.$predicate = function2;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        ChannelsKt__Channels_commonKt$filter$1 channelsKt__Channels_commonKt$filter$1 = new ChannelsKt__Channels_commonKt$filter$1(this.$this_filter, this.$predicate, continuation);
        channelsKt__Channels_commonKt$filter$1.p$ = (ProducerScope) obj;
        return channelsKt__Channels_commonKt$filter$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Continuation<? super Unit> continuation) {
        return ((ChannelsKt__Channels_commonKt$filter$1) create(obj, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:16:0x0060 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0061  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x006f  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x008f  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x00a3  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:29:0x009e -> B:14:0x0054). Please submit an issue!!! */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(@NotNull Object obj) {
        Object coroutine_suspended;
        ChannelIterator<E> it;
        ProducerScope producerScope;
        ProducerScope producerScope2;
        ChannelIterator<E> channelIterator;
        Object obj2;
        ChannelsKt__Channels_commonKt$filter$1<E> channelsKt__Channels_commonKt$filter$1;
        ChannelsKt__Channels_commonKt$filter$1<E> channelsKt__Channels_commonKt$filter$12;
        Object hasNext;
        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = this.label;
        if (i2 == 0) {
            ResultKt.throwOnFailure(obj);
            ProducerScope producerScope3 = this.p$;
            it = this.$this_filter.iterator();
            producerScope = producerScope3;
        } else if (i2 == 1) {
            ResultKt.throwOnFailure(obj);
            producerScope2 = (ProducerScope) this.L$0;
            channelIterator = (ChannelIterator) this.L$1;
            obj2 = coroutine_suspended;
            channelsKt__Channels_commonKt$filter$1 = this;
            if (!((Boolean) obj).booleanValue()) {
            }
        } else if (i2 == 2) {
            Object obj3 = this.L$1;
            producerScope2 = (ProducerScope) this.L$0;
            ResultKt.throwOnFailure(obj);
            E e2 = obj3;
            channelIterator = (ChannelIterator) this.L$2;
            obj2 = coroutine_suspended;
            channelsKt__Channels_commonKt$filter$1 = this;
            if (((Boolean) obj).booleanValue()) {
                channelsKt__Channels_commonKt$filter$1.L$0 = producerScope2;
                channelsKt__Channels_commonKt$filter$1.L$1 = e2;
                channelsKt__Channels_commonKt$filter$1.L$2 = channelIterator;
                channelsKt__Channels_commonKt$filter$1.label = 3;
                if (producerScope2.send(e2, channelsKt__Channels_commonKt$filter$1) == obj2) {
                    return obj2;
                }
            }
            channelsKt__Channels_commonKt$filter$12 = channelsKt__Channels_commonKt$filter$1;
            coroutine_suspended = obj2;
            it = channelIterator;
            producerScope = producerScope2;
            channelsKt__Channels_commonKt$filter$12.L$0 = producerScope;
            channelsKt__Channels_commonKt$filter$12.L$1 = it;
            channelsKt__Channels_commonKt$filter$12.label = 1;
            hasNext = it.hasNext(channelsKt__Channels_commonKt$filter$12);
            if (hasNext == coroutine_suspended) {
                return coroutine_suspended;
            }
            Object obj4 = coroutine_suspended;
            channelsKt__Channels_commonKt$filter$1 = channelsKt__Channels_commonKt$filter$12;
            obj = hasNext;
            producerScope2 = producerScope;
            channelIterator = it;
            obj2 = obj4;
            if (!((Boolean) obj).booleanValue()) {
                E next = channelIterator.next();
                Function2 function2 = channelsKt__Channels_commonKt$filter$1.$predicate;
                channelsKt__Channels_commonKt$filter$1.L$0 = producerScope2;
                channelsKt__Channels_commonKt$filter$1.L$1 = next;
                channelsKt__Channels_commonKt$filter$1.L$2 = channelIterator;
                channelsKt__Channels_commonKt$filter$1.label = 2;
                Object invoke = function2.invoke(next, channelsKt__Channels_commonKt$filter$1);
                if (invoke == obj2) {
                    return obj2;
                }
                e2 = next;
                obj = invoke;
                if (((Boolean) obj).booleanValue()) {
                }
                channelsKt__Channels_commonKt$filter$12 = channelsKt__Channels_commonKt$filter$1;
                coroutine_suspended = obj2;
                it = channelIterator;
                producerScope = producerScope2;
                channelsKt__Channels_commonKt$filter$12.L$0 = producerScope;
                channelsKt__Channels_commonKt$filter$12.L$1 = it;
                channelsKt__Channels_commonKt$filter$12.label = 1;
                hasNext = it.hasNext(channelsKt__Channels_commonKt$filter$12);
                if (hasNext == coroutine_suspended) {
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
        channelsKt__Channels_commonKt$filter$12 = this;
        channelsKt__Channels_commonKt$filter$12.L$0 = producerScope;
        channelsKt__Channels_commonKt$filter$12.L$1 = it;
        channelsKt__Channels_commonKt$filter$12.label = 1;
        hasNext = it.hasNext(channelsKt__Channels_commonKt$filter$12);
        if (hasNext == coroutine_suspended) {
        }
    }
}

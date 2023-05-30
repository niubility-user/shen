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
/* JADX INFO: Add missing generic type declarations: [E] */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\u0010\u0005\u001a\u00020\u0002\"\u0004\b\u0000\u0010\u0000*\b\u0012\u0004\u0012\u00028\u00000\u0001H\u008a@\u00a2\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"E", "Lkotlinx/coroutines/channels/ProducerScope;", "", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "<anonymous>"}, k = 3, mv = {1, 4, 0})
@DebugMetadata(c = "kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$drop$1", f = "Channels.common.kt", i = {0, 0, 1, 1, 2, 2, 2}, l = {700, R2.attr.cornerFamily, R2.attr.cornerFamilyBottomLeft}, m = "invokeSuspend", n = {"$this$produce", "remaining", "$this$produce", "remaining", "$this$produce", "remaining", e.a}, s = {"L$0", "I$0", "L$0", "I$0", "L$0", "I$0", "L$1"})
/* loaded from: classes11.dex */
public final class ChannelsKt__Channels_commonKt$drop$1<E> extends SuspendLambda implements Function2<ProducerScope<? super E>, Continuation<? super Unit>, Object> {
    final /* synthetic */ int $n;
    final /* synthetic */ ReceiveChannel $this_drop;
    int I$0;
    Object L$0;
    Object L$1;
    Object L$2;
    int label;
    private ProducerScope p$;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ChannelsKt__Channels_commonKt$drop$1(ReceiveChannel receiveChannel, int i2, Continuation continuation) {
        super(2, continuation);
        this.$this_drop = receiveChannel;
        this.$n = i2;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        ChannelsKt__Channels_commonKt$drop$1 channelsKt__Channels_commonKt$drop$1 = new ChannelsKt__Channels_commonKt$drop$1(this.$this_drop, this.$n, continuation);
        channelsKt__Channels_commonKt$drop$1.p$ = (ProducerScope) obj;
        return channelsKt__Channels_commonKt$drop$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Continuation<? super Unit> continuation) {
        return ((ChannelsKt__Channels_commonKt$drop$1) create(obj, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x007c A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:22:0x007d  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x008c  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x00b5 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:36:0x00b6  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x00c1  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x00d8  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:22:0x007d -> B:23:0x0084). Please submit an issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:42:0x00d6 -> B:33:0x00a7). Please submit an issue!!! */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(@NotNull Object obj) {
        Object coroutine_suspended;
        ProducerScope producerScope;
        int i2;
        Object obj2;
        ChannelsKt__Channels_commonKt$drop$1<E> channelsKt__Channels_commonKt$drop$1;
        ProducerScope producerScope2;
        ChannelsKt__Channels_commonKt$drop$1<E> channelsKt__Channels_commonKt$drop$12;
        ChannelIterator<E> it;
        int i3;
        ChannelIterator<E> it2;
        Object hasNext;
        ProducerScope producerScope3;
        Object hasNext2;
        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i4 = this.label;
        if (i4 == 0) {
            ResultKt.throwOnFailure(obj);
            producerScope = this.p$;
            int i5 = this.$n;
            if (!(i5 >= 0)) {
                throw new IllegalArgumentException(("Requested element count " + this.$n + " is less than zero.").toString());
            } else if (i5 > 0) {
                producerScope2 = producerScope;
                channelsKt__Channels_commonKt$drop$12 = this;
                it = this.$this_drop.iterator();
                i3 = i5;
                ChannelIterator<E> channelIterator = it;
                channelsKt__Channels_commonKt$drop$12.L$0 = producerScope2;
                channelsKt__Channels_commonKt$drop$12.I$0 = i3;
                channelsKt__Channels_commonKt$drop$12.L$1 = channelIterator;
                channelsKt__Channels_commonKt$drop$12.label = 1;
                hasNext = channelIterator.hasNext(channelsKt__Channels_commonKt$drop$12);
                if (hasNext != coroutine_suspended) {
                }
            } else {
                i2 = i5;
                obj2 = coroutine_suspended;
                channelsKt__Channels_commonKt$drop$1 = this;
                it2 = channelsKt__Channels_commonKt$drop$1.$this_drop.iterator();
                channelsKt__Channels_commonKt$drop$1.L$0 = producerScope;
                channelsKt__Channels_commonKt$drop$1.I$0 = i2;
                channelsKt__Channels_commonKt$drop$1.L$1 = it2;
                channelsKt__Channels_commonKt$drop$1.label = 2;
                hasNext2 = it2.hasNext(channelsKt__Channels_commonKt$drop$1);
                if (hasNext2 != obj2) {
                }
            }
        } else if (i4 == 1) {
            int i6 = this.I$0;
            ResultKt.throwOnFailure(obj);
            ProducerScope producerScope4 = (ProducerScope) this.L$0;
            i2 = i6;
            ChannelIterator<E> channelIterator2 = (ChannelIterator) this.L$1;
            obj2 = coroutine_suspended;
            channelsKt__Channels_commonKt$drop$1 = this;
            if (((Boolean) obj).booleanValue()) {
                channelIterator2.next();
                int i7 = i2 - 1;
                if (i7 == 0) {
                    i2 = i7;
                } else {
                    producerScope2 = producerScope4;
                    it = channelIterator2;
                    i3 = i7;
                    channelsKt__Channels_commonKt$drop$12 = channelsKt__Channels_commonKt$drop$1;
                    coroutine_suspended = obj2;
                    ChannelIterator<E> channelIterator3 = it;
                    channelsKt__Channels_commonKt$drop$12.L$0 = producerScope2;
                    channelsKt__Channels_commonKt$drop$12.I$0 = i3;
                    channelsKt__Channels_commonKt$drop$12.L$1 = channelIterator3;
                    channelsKt__Channels_commonKt$drop$12.label = 1;
                    hasNext = channelIterator3.hasNext(channelsKt__Channels_commonKt$drop$12);
                    if (hasNext != coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    Object obj3 = coroutine_suspended;
                    channelsKt__Channels_commonKt$drop$1 = channelsKt__Channels_commonKt$drop$12;
                    obj = hasNext;
                    producerScope4 = producerScope2;
                    i2 = i3;
                    channelIterator2 = channelIterator3;
                    obj2 = obj3;
                    if (((Boolean) obj).booleanValue()) {
                    }
                }
            }
            producerScope = producerScope4;
            it2 = channelsKt__Channels_commonKt$drop$1.$this_drop.iterator();
            channelsKt__Channels_commonKt$drop$1.L$0 = producerScope;
            channelsKt__Channels_commonKt$drop$1.I$0 = i2;
            channelsKt__Channels_commonKt$drop$1.L$1 = it2;
            channelsKt__Channels_commonKt$drop$1.label = 2;
            hasNext2 = it2.hasNext(channelsKt__Channels_commonKt$drop$1);
            if (hasNext2 != obj2) {
            }
        } else if (i4 == 2) {
            int i8 = this.I$0;
            producerScope3 = (ProducerScope) this.L$0;
            ResultKt.throwOnFailure(obj);
            i2 = i8;
            it2 = (ChannelIterator) this.L$1;
            obj2 = coroutine_suspended;
            channelsKt__Channels_commonKt$drop$1 = this;
            if (!((Boolean) obj).booleanValue()) {
            }
        } else if (i4 != 3) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        } else {
            int i9 = this.I$0;
            ResultKt.throwOnFailure(obj);
            i2 = i9;
            producerScope = (ProducerScope) this.L$0;
            it2 = (ChannelIterator) this.L$2;
            obj2 = coroutine_suspended;
            channelsKt__Channels_commonKt$drop$1 = this;
            channelsKt__Channels_commonKt$drop$1.L$0 = producerScope;
            channelsKt__Channels_commonKt$drop$1.I$0 = i2;
            channelsKt__Channels_commonKt$drop$1.L$1 = it2;
            channelsKt__Channels_commonKt$drop$1.label = 2;
            hasNext2 = it2.hasNext(channelsKt__Channels_commonKt$drop$1);
            if (hasNext2 != obj2) {
                return obj2;
            }
            producerScope3 = producerScope;
            obj = hasNext2;
            if (!((Boolean) obj).booleanValue()) {
                E next = it2.next();
                channelsKt__Channels_commonKt$drop$1.L$0 = producerScope3;
                channelsKt__Channels_commonKt$drop$1.I$0 = i2;
                channelsKt__Channels_commonKt$drop$1.L$1 = next;
                channelsKt__Channels_commonKt$drop$1.L$2 = it2;
                channelsKt__Channels_commonKt$drop$1.label = 3;
                if (producerScope3.send(next, channelsKt__Channels_commonKt$drop$1) == obj2) {
                    return obj2;
                }
                producerScope = producerScope3;
                channelsKt__Channels_commonKt$drop$1.L$0 = producerScope;
                channelsKt__Channels_commonKt$drop$1.I$0 = i2;
                channelsKt__Channels_commonKt$drop$1.L$1 = it2;
                channelsKt__Channels_commonKt$drop$1.label = 2;
                hasNext2 = it2.hasNext(channelsKt__Channels_commonKt$drop$1);
                if (hasNext2 != obj2) {
                }
            } else {
                return Unit.INSTANCE;
            }
        }
    }
}

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
@DebugMetadata(c = "kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$dropWhile$1", f = "Channels.common.kt", i = {0, 1, 1, 2, 2, 3, 4, 4}, l = {R2.attr.currencySymbolMinSize, R2.attr.currentState, R2.attr.curveFit, 731, R2.attr.customFloatValue}, m = "invokeSuspend", n = {"$this$produce", "$this$produce", e.a, "$this$produce", e.a, "$this$produce", "$this$produce", e.a}, s = {"L$0", "L$0", "L$1", "L$0", "L$1", "L$0", "L$0", "L$1"})
/* loaded from: classes11.dex */
public final class ChannelsKt__Channels_commonKt$dropWhile$1<E> extends SuspendLambda implements Function2<ProducerScope<? super E>, Continuation<? super Unit>, Object> {
    final /* synthetic */ Function2 $predicate;
    final /* synthetic */ ReceiveChannel $this_dropWhile;
    Object L$0;
    Object L$1;
    Object L$2;
    int label;
    private ProducerScope p$;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ChannelsKt__Channels_commonKt$dropWhile$1(ReceiveChannel receiveChannel, Function2 function2, Continuation continuation) {
        super(2, continuation);
        this.$this_dropWhile = receiveChannel;
        this.$predicate = function2;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        ChannelsKt__Channels_commonKt$dropWhile$1 channelsKt__Channels_commonKt$dropWhile$1 = new ChannelsKt__Channels_commonKt$dropWhile$1(this.$this_dropWhile, this.$predicate, continuation);
        channelsKt__Channels_commonKt$dropWhile$1.p$ = (ProducerScope) obj;
        return channelsKt__Channels_commonKt$dropWhile$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Continuation<? super Unit> continuation) {
        return ((ChannelsKt__Channels_commonKt$dropWhile$1) create(obj, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:19:0x0088 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0089  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0097  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x00b7  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x00c4  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x00db A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:37:0x00dc  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x00e7  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x00fc  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:26:0x00ac -> B:27:0x00af). Please submit an issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:43:0x00fa -> B:34:0x00cf). Please submit an issue!!! */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(@NotNull Object obj) {
        Object coroutine_suspended;
        ChannelIterator<E> it;
        ProducerScope producerScope;
        ChannelsKt__Channels_commonKt$dropWhile$1<E> channelsKt__Channels_commonKt$dropWhile$1;
        ProducerScope producerScope2;
        ChannelIterator<E> channelIterator;
        Object obj2;
        ChannelsKt__Channels_commonKt$dropWhile$1<E> channelsKt__Channels_commonKt$dropWhile$12;
        Object hasNext;
        ChannelIterator<E> it2;
        ChannelIterator<E> channelIterator2;
        Object hasNext2;
        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = this.label;
        if (i2 == 0) {
            ResultKt.throwOnFailure(obj);
            ProducerScope producerScope3 = this.p$;
            it = this.$this_dropWhile.iterator();
            producerScope = producerScope3;
            channelsKt__Channels_commonKt$dropWhile$1 = this;
            channelsKt__Channels_commonKt$dropWhile$1.L$0 = producerScope;
            channelsKt__Channels_commonKt$dropWhile$1.L$1 = it;
            channelsKt__Channels_commonKt$dropWhile$1.label = 1;
            hasNext = it.hasNext(channelsKt__Channels_commonKt$dropWhile$1);
            if (hasNext != coroutine_suspended) {
            }
        } else if (i2 == 1) {
            ResultKt.throwOnFailure(obj);
            producerScope2 = (ProducerScope) this.L$0;
            channelIterator = (ChannelIterator) this.L$1;
            obj2 = coroutine_suspended;
            channelsKt__Channels_commonKt$dropWhile$12 = this;
            if (((Boolean) obj).booleanValue()) {
            }
            it2 = channelsKt__Channels_commonKt$dropWhile$12.$this_dropWhile.iterator();
            channelsKt__Channels_commonKt$dropWhile$12.L$0 = producerScope2;
            channelsKt__Channels_commonKt$dropWhile$12.L$1 = it2;
            channelsKt__Channels_commonKt$dropWhile$12.label = 4;
            hasNext2 = it2.hasNext(channelsKt__Channels_commonKt$dropWhile$12);
            if (hasNext2 != obj2) {
            }
        } else if (i2 == 2) {
            Object obj3 = this.L$1;
            producerScope2 = (ProducerScope) this.L$0;
            ResultKt.throwOnFailure(obj);
            E e2 = obj3;
            channelIterator = (ChannelIterator) this.L$2;
            obj2 = coroutine_suspended;
            channelsKt__Channels_commonKt$dropWhile$12 = this;
            if (((Boolean) obj).booleanValue()) {
                channelsKt__Channels_commonKt$dropWhile$12.L$0 = producerScope2;
                channelsKt__Channels_commonKt$dropWhile$12.L$1 = e2;
                channelsKt__Channels_commonKt$dropWhile$12.label = 3;
                if (producerScope2.send(e2, channelsKt__Channels_commonKt$dropWhile$12) == obj2) {
                    return obj2;
                }
                it2 = channelsKt__Channels_commonKt$dropWhile$12.$this_dropWhile.iterator();
                channelsKt__Channels_commonKt$dropWhile$12.L$0 = producerScope2;
                channelsKt__Channels_commonKt$dropWhile$12.L$1 = it2;
                channelsKt__Channels_commonKt$dropWhile$12.label = 4;
                hasNext2 = it2.hasNext(channelsKt__Channels_commonKt$dropWhile$12);
                if (hasNext2 != obj2) {
                }
            } else {
                channelsKt__Channels_commonKt$dropWhile$1 = channelsKt__Channels_commonKt$dropWhile$12;
                coroutine_suspended = obj2;
                it = channelIterator;
                producerScope = producerScope2;
                channelsKt__Channels_commonKt$dropWhile$1.L$0 = producerScope;
                channelsKt__Channels_commonKt$dropWhile$1.L$1 = it;
                channelsKt__Channels_commonKt$dropWhile$1.label = 1;
                hasNext = it.hasNext(channelsKt__Channels_commonKt$dropWhile$1);
                if (hasNext != coroutine_suspended) {
                    return coroutine_suspended;
                }
                Object obj4 = coroutine_suspended;
                channelsKt__Channels_commonKt$dropWhile$12 = channelsKt__Channels_commonKt$dropWhile$1;
                obj = hasNext;
                producerScope2 = producerScope;
                channelIterator = it;
                obj2 = obj4;
                if (((Boolean) obj).booleanValue()) {
                    E next = channelIterator.next();
                    Function2 function2 = channelsKt__Channels_commonKt$dropWhile$12.$predicate;
                    channelsKt__Channels_commonKt$dropWhile$12.L$0 = producerScope2;
                    channelsKt__Channels_commonKt$dropWhile$12.L$1 = next;
                    channelsKt__Channels_commonKt$dropWhile$12.L$2 = channelIterator;
                    channelsKt__Channels_commonKt$dropWhile$12.label = 2;
                    Object invoke = function2.invoke(next, channelsKt__Channels_commonKt$dropWhile$12);
                    if (invoke == obj2) {
                        return obj2;
                    }
                    e2 = next;
                    obj = invoke;
                    if (((Boolean) obj).booleanValue()) {
                    }
                }
                it2 = channelsKt__Channels_commonKt$dropWhile$12.$this_dropWhile.iterator();
                channelsKt__Channels_commonKt$dropWhile$12.L$0 = producerScope2;
                channelsKt__Channels_commonKt$dropWhile$12.L$1 = it2;
                channelsKt__Channels_commonKt$dropWhile$12.label = 4;
                hasNext2 = it2.hasNext(channelsKt__Channels_commonKt$dropWhile$12);
                if (hasNext2 != obj2) {
                }
            }
        } else if (i2 == 3) {
            ResultKt.throwOnFailure(obj);
            producerScope2 = (ProducerScope) this.L$0;
            obj2 = coroutine_suspended;
            channelsKt__Channels_commonKt$dropWhile$12 = this;
            it2 = channelsKt__Channels_commonKt$dropWhile$12.$this_dropWhile.iterator();
            channelsKt__Channels_commonKt$dropWhile$12.L$0 = producerScope2;
            channelsKt__Channels_commonKt$dropWhile$12.L$1 = it2;
            channelsKt__Channels_commonKt$dropWhile$12.label = 4;
            hasNext2 = it2.hasNext(channelsKt__Channels_commonKt$dropWhile$12);
            if (hasNext2 != obj2) {
            }
        } else if (i2 == 4) {
            ResultKt.throwOnFailure(obj);
            producerScope2 = (ProducerScope) this.L$0;
            channelIterator2 = (ChannelIterator) this.L$1;
            obj2 = coroutine_suspended;
            channelsKt__Channels_commonKt$dropWhile$12 = this;
            if (!((Boolean) obj).booleanValue()) {
            }
        } else if (i2 != 5) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        } else {
            ResultKt.throwOnFailure(obj);
            it2 = (ChannelIterator) this.L$2;
            producerScope2 = (ProducerScope) this.L$0;
            obj2 = coroutine_suspended;
            channelsKt__Channels_commonKt$dropWhile$12 = this;
            channelsKt__Channels_commonKt$dropWhile$12.L$0 = producerScope2;
            channelsKt__Channels_commonKt$dropWhile$12.L$1 = it2;
            channelsKt__Channels_commonKt$dropWhile$12.label = 4;
            hasNext2 = it2.hasNext(channelsKt__Channels_commonKt$dropWhile$12);
            if (hasNext2 != obj2) {
                return obj2;
            }
            channelIterator2 = it2;
            obj = hasNext2;
            if (!((Boolean) obj).booleanValue()) {
                E next2 = channelIterator2.next();
                channelsKt__Channels_commonKt$dropWhile$12.L$0 = producerScope2;
                channelsKt__Channels_commonKt$dropWhile$12.L$1 = next2;
                channelsKt__Channels_commonKt$dropWhile$12.L$2 = channelIterator2;
                channelsKt__Channels_commonKt$dropWhile$12.label = 5;
                if (producerScope2.send(next2, channelsKt__Channels_commonKt$dropWhile$12) == obj2) {
                    return obj2;
                }
                it2 = channelIterator2;
                channelsKt__Channels_commonKt$dropWhile$12.L$0 = producerScope2;
                channelsKt__Channels_commonKt$dropWhile$12.L$1 = it2;
                channelsKt__Channels_commonKt$dropWhile$12.label = 4;
                hasNext2 = it2.hasNext(channelsKt__Channels_commonKt$dropWhile$12);
                if (hasNext2 != obj2) {
                }
            } else {
                return Unit.INSTANCE;
            }
        }
    }
}

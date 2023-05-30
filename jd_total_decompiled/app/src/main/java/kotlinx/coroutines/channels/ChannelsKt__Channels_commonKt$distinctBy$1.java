package kotlinx.coroutines.channels;

import com.jingdong.app.mall.e;
import com.jingdong.sdk.platform.business.personal.R2;
import java.util.HashSet;
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
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\u0010\u0006\u001a\u00020\u0003\"\u0004\b\u0000\u0010\u0000\"\u0004\b\u0001\u0010\u0001*\b\u0012\u0004\u0012\u00028\u00000\u0002H\u008a@\u00a2\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"E", "K", "Lkotlinx/coroutines/channels/ProducerScope;", "", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "<anonymous>"}, k = 3, mv = {1, 4, 0})
@DebugMetadata(c = "kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$distinctBy$1", f = "Channels.common.kt", i = {0, 0, 1, 1, 1, 2, 2, 2, 2}, l = {R2.attr.roundProgressColor, R2.attr.roundRadiusBottomLeft, R2.attr.roundRadiusTopLeft}, m = "invokeSuspend", n = {"$this$produce", "keys", "$this$produce", "keys", e.a, "$this$produce", "keys", e.a, "k"}, s = {"L$0", "L$1", "L$0", "L$1", "L$2", "L$0", "L$1", "L$2", "L$4"})
/* loaded from: classes11.dex */
public final class ChannelsKt__Channels_commonKt$distinctBy$1<E> extends SuspendLambda implements Function2<ProducerScope<? super E>, Continuation<? super Unit>, Object> {
    final /* synthetic */ Function2 $selector;
    final /* synthetic */ ReceiveChannel $this_distinctBy;
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    int label;
    private ProducerScope p$;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ChannelsKt__Channels_commonKt$distinctBy$1(ReceiveChannel receiveChannel, Function2 function2, Continuation continuation) {
        super(2, continuation);
        this.$this_distinctBy = receiveChannel;
        this.$selector = function2;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        ChannelsKt__Channels_commonKt$distinctBy$1 channelsKt__Channels_commonKt$distinctBy$1 = new ChannelsKt__Channels_commonKt$distinctBy$1(this.$this_distinctBy, this.$selector, continuation);
        channelsKt__Channels_commonKt$distinctBy$1.p$ = (ProducerScope) obj;
        return channelsKt__Channels_commonKt$distinctBy$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Continuation<? super Unit> continuation) {
        return ((ChannelsKt__Channels_commonKt$distinctBy$1) create(obj, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:15:0x0080 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:16:0x0081  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x0090  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x00b2  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x00d0  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x00d6  */
    /* JADX WARN: Type inference failed for: r5v13, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r6v9, types: [java.util.Collection] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:28:0x00c5 -> B:29:0x00c7). Please submit an issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:30:0x00d0 -> B:13:0x0072). Please submit an issue!!! */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(@NotNull Object obj) {
        Object coroutine_suspended;
        ProducerScope producerScope;
        ChannelsKt__Channels_commonKt$distinctBy$1<E> channelsKt__Channels_commonKt$distinctBy$1;
        HashSet hashSet;
        ChannelIterator<E> it;
        ProducerScope producerScope2;
        HashSet hashSet2;
        E e2;
        ChannelIterator<E> channelIterator;
        Object obj2;
        ChannelsKt__Channels_commonKt$distinctBy$1<E> channelsKt__Channels_commonKt$distinctBy$12;
        ProducerScope producerScope3;
        HashSet hashSet3;
        Object hasNext;
        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = this.label;
        if (i2 == 0) {
            ResultKt.throwOnFailure(obj);
            ProducerScope producerScope4 = this.p$;
            HashSet hashSet4 = new HashSet();
            producerScope = producerScope4;
            channelsKt__Channels_commonKt$distinctBy$1 = this;
            hashSet = hashSet4;
            it = this.$this_distinctBy.iterator();
            channelsKt__Channels_commonKt$distinctBy$1.L$0 = producerScope;
            channelsKt__Channels_commonKt$distinctBy$1.L$1 = hashSet;
            channelsKt__Channels_commonKt$distinctBy$1.L$2 = it;
            channelsKt__Channels_commonKt$distinctBy$1.label = 1;
            hasNext = it.hasNext(channelsKt__Channels_commonKt$distinctBy$1);
            if (hasNext != coroutine_suspended) {
            }
        } else if (i2 == 1) {
            ResultKt.throwOnFailure(obj);
            producerScope3 = (ProducerScope) this.L$0;
            hashSet3 = (HashSet) this.L$1;
            channelIterator = (ChannelIterator) this.L$2;
            obj2 = coroutine_suspended;
            channelsKt__Channels_commonKt$distinctBy$12 = this;
            if (!((Boolean) obj).booleanValue()) {
            }
        } else if (i2 == 2) {
            ?? r5 = this.L$2;
            ResultKt.throwOnFailure(obj);
            producerScope2 = (ProducerScope) this.L$0;
            hashSet2 = (HashSet) this.L$1;
            e2 = r5;
            channelIterator = (ChannelIterator) this.L$3;
            obj2 = coroutine_suspended;
            channelsKt__Channels_commonKt$distinctBy$12 = this;
            if (hashSet2.contains(obj)) {
            }
        } else if (i2 != 3) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        } else {
            Object obj3 = this.L$4;
            channelIterator = (ChannelIterator) this.L$3;
            HashSet hashSet5 = (HashSet) this.L$1;
            ProducerScope producerScope5 = (ProducerScope) this.L$0;
            ResultKt.throwOnFailure(obj);
            obj = obj3;
            obj2 = coroutine_suspended;
            channelsKt__Channels_commonKt$distinctBy$12 = this;
            hashSet5.add(obj);
            channelsKt__Channels_commonKt$distinctBy$1 = channelsKt__Channels_commonKt$distinctBy$12;
            coroutine_suspended = obj2;
            it = channelIterator;
            hashSet = hashSet5;
            producerScope = producerScope5;
            channelsKt__Channels_commonKt$distinctBy$1.L$0 = producerScope;
            channelsKt__Channels_commonKt$distinctBy$1.L$1 = hashSet;
            channelsKt__Channels_commonKt$distinctBy$1.L$2 = it;
            channelsKt__Channels_commonKt$distinctBy$1.label = 1;
            hasNext = it.hasNext(channelsKt__Channels_commonKt$distinctBy$1);
            if (hasNext != coroutine_suspended) {
                return coroutine_suspended;
            }
            Object obj4 = coroutine_suspended;
            channelsKt__Channels_commonKt$distinctBy$12 = channelsKt__Channels_commonKt$distinctBy$1;
            obj = hasNext;
            producerScope3 = producerScope;
            hashSet3 = hashSet;
            channelIterator = it;
            obj2 = obj4;
            if (!((Boolean) obj).booleanValue()) {
                E next = channelIterator.next();
                Function2 function2 = channelsKt__Channels_commonKt$distinctBy$12.$selector;
                channelsKt__Channels_commonKt$distinctBy$12.L$0 = producerScope3;
                channelsKt__Channels_commonKt$distinctBy$12.L$1 = hashSet3;
                channelsKt__Channels_commonKt$distinctBy$12.L$2 = next;
                channelsKt__Channels_commonKt$distinctBy$12.L$3 = channelIterator;
                channelsKt__Channels_commonKt$distinctBy$12.label = 2;
                Object invoke = function2.invoke(next, channelsKt__Channels_commonKt$distinctBy$12);
                if (invoke == obj2) {
                    return obj2;
                }
                HashSet hashSet6 = hashSet3;
                e2 = next;
                obj = invoke;
                producerScope2 = producerScope3;
                hashSet2 = hashSet6;
                if (hashSet2.contains(obj)) {
                    channelsKt__Channels_commonKt$distinctBy$12.L$0 = producerScope2;
                    channelsKt__Channels_commonKt$distinctBy$12.L$1 = hashSet2;
                    channelsKt__Channels_commonKt$distinctBy$12.L$2 = e2;
                    channelsKt__Channels_commonKt$distinctBy$12.L$3 = channelIterator;
                    channelsKt__Channels_commonKt$distinctBy$12.L$4 = obj;
                    channelsKt__Channels_commonKt$distinctBy$12.label = 3;
                    if (producerScope2.send(e2, channelsKt__Channels_commonKt$distinctBy$12) == obj2) {
                        return obj2;
                    }
                    hashSet5 = hashSet2;
                    producerScope5 = producerScope2;
                    hashSet5.add(obj);
                    channelsKt__Channels_commonKt$distinctBy$1 = channelsKt__Channels_commonKt$distinctBy$12;
                    coroutine_suspended = obj2;
                    it = channelIterator;
                    hashSet = hashSet5;
                    producerScope = producerScope5;
                    channelsKt__Channels_commonKt$distinctBy$1.L$0 = producerScope;
                    channelsKt__Channels_commonKt$distinctBy$1.L$1 = hashSet;
                    channelsKt__Channels_commonKt$distinctBy$1.L$2 = it;
                    channelsKt__Channels_commonKt$distinctBy$1.label = 1;
                    hasNext = it.hasNext(channelsKt__Channels_commonKt$distinctBy$1);
                    if (hasNext != coroutine_suspended) {
                    }
                } else {
                    channelsKt__Channels_commonKt$distinctBy$1 = channelsKt__Channels_commonKt$distinctBy$12;
                    coroutine_suspended = obj2;
                    it = channelIterator;
                    hashSet = hashSet2;
                    producerScope = producerScope2;
                    channelsKt__Channels_commonKt$distinctBy$1.L$0 = producerScope;
                    channelsKt__Channels_commonKt$distinctBy$1.L$1 = hashSet;
                    channelsKt__Channels_commonKt$distinctBy$1.L$2 = it;
                    channelsKt__Channels_commonKt$distinctBy$1.label = 1;
                    hasNext = it.hasNext(channelsKt__Channels_commonKt$distinctBy$1);
                    if (hasNext != coroutine_suspended) {
                    }
                }
            } else {
                return Unit.INSTANCE;
            }
        }
    }
}

package kotlinx.coroutines.channels;

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
/* JADX INFO: Add missing generic type declarations: [V] */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\u0010\u0007\u001a\u00020\u0004\"\u0004\b\u0000\u0010\u0000\"\u0004\b\u0001\u0010\u0001\"\u0004\b\u0002\u0010\u0002*\b\u0012\u0004\u0012\u00028\u00020\u0003H\u008a@\u00a2\u0006\u0004\b\u0005\u0010\u0006"}, d2 = {"E", "R", "V", "Lkotlinx/coroutines/channels/ProducerScope;", "", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "<anonymous>"}, k = 3, mv = {1, 4, 0})
@DebugMetadata(c = "kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$zip$2", f = "Channels.common.kt", i = {0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2}, l = {2201, R2.attr.unEpvAutoDark, R2.attr.unErrorButtonLeftText}, m = "invokeSuspend", n = {"$this$produce", "otherIterator", "$this$consumeEach$iv", "$this$consume$iv$iv", "cause$iv$iv", "$this$consume$iv", "$this$produce", "otherIterator", "$this$consumeEach$iv", "$this$consume$iv$iv", "cause$iv$iv", "$this$consume$iv", "e$iv", "element1", "$this$produce", "otherIterator", "$this$consumeEach$iv", "$this$consume$iv$iv", "cause$iv$iv", "$this$consume$iv", "e$iv", "element1", "element2"}, s = {"L$0", "L$1", "L$2", "L$4", "L$5", "L$6", "L$0", "L$1", "L$2", "L$4", "L$5", "L$6", "L$8", "L$9", "L$0", "L$1", "L$2", "L$4", "L$5", "L$6", "L$8", "L$9", "L$10"})
/* loaded from: classes11.dex */
public final class ChannelsKt__Channels_commonKt$zip$2<V> extends SuspendLambda implements Function2<ProducerScope<? super V>, Continuation<? super Unit>, Object> {
    final /* synthetic */ ReceiveChannel $other;
    final /* synthetic */ ReceiveChannel $this_zip;
    final /* synthetic */ Function2 $transform;
    Object L$0;
    Object L$1;
    Object L$10;
    Object L$2;
    Object L$3;
    Object L$4;
    Object L$5;
    Object L$6;
    Object L$7;
    Object L$8;
    Object L$9;
    int label;
    private ProducerScope p$;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ChannelsKt__Channels_commonKt$zip$2(ReceiveChannel receiveChannel, ReceiveChannel receiveChannel2, Function2 function2, Continuation continuation) {
        super(2, continuation);
        this.$this_zip = receiveChannel;
        this.$other = receiveChannel2;
        this.$transform = function2;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        ChannelsKt__Channels_commonKt$zip$2 channelsKt__Channels_commonKt$zip$2 = new ChannelsKt__Channels_commonKt$zip$2(this.$this_zip, this.$other, this.$transform, continuation);
        channelsKt__Channels_commonKt$zip$2.p$ = (ProducerScope) obj;
        return channelsKt__Channels_commonKt$zip$2;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Continuation<? super Unit> continuation) {
        return ((ChannelsKt__Channels_commonKt$zip$2) create(obj, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:24:0x00d4 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:27:0x00dd A[Catch: all -> 0x0148, TryCatch #1 {all -> 0x0148, blocks: (B:8:0x0033, B:22:0x00bc, B:25:0x00d5, B:27:0x00dd, B:31:0x0103, B:34:0x0111, B:39:0x0142, B:18:0x009c, B:21:0x00b1), top: B:51:0x000b }] */
    /* JADX WARN: Removed duplicated region for block: B:33:0x010b  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x0111 A[Catch: all -> 0x0148, TryCatch #1 {all -> 0x0148, blocks: (B:8:0x0033, B:22:0x00bc, B:25:0x00d5, B:27:0x00dd, B:31:0x0103, B:34:0x0111, B:39:0x0142, B:18:0x009c, B:21:0x00b1), top: B:51:0x000b }] */
    /* JADX WARN: Removed duplicated region for block: B:39:0x0142 A[Catch: all -> 0x0148, TRY_LEAVE, TryCatch #1 {all -> 0x0148, blocks: (B:8:0x0033, B:22:0x00bc, B:25:0x00d5, B:27:0x00dd, B:31:0x0103, B:34:0x0111, B:39:0x0142, B:18:0x009c, B:21:0x00b1), top: B:51:0x000b }] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:33:0x010b -> B:22:0x00bc). Please submit an issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:37:0x013a -> B:38:0x013e). Please submit an issue!!! */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(@NotNull Object obj) {
        Object coroutine_suspended;
        Throwable th;
        ReceiveChannel receiveChannel;
        Throwable th2;
        ChannelsKt__Channels_commonKt$zip$2<V> channelsKt__Channels_commonKt$zip$2;
        ProducerScope producerScope;
        ChannelIterator channelIterator;
        ReceiveChannel receiveChannel2;
        ReceiveChannel receiveChannel3;
        ChannelIterator it;
        ChannelsKt__Channels_commonKt$zip$2<V> channelsKt__Channels_commonKt$zip$22;
        Object obj2;
        Object obj3;
        ChannelIterator channelIterator2;
        ChannelsKt__Channels_commonKt$zip$2<V> channelsKt__Channels_commonKt$zip$23;
        Throwable th3;
        Object obj4;
        ChannelsKt__Channels_commonKt$zip$2<V> channelsKt__Channels_commonKt$zip$24;
        Object obj5;
        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = this.label;
        int i3 = 2;
        int i4 = 1;
        try {
            if (i2 == 0) {
                ResultKt.throwOnFailure(obj);
                ProducerScope producerScope2 = this.p$;
                ChannelIterator it2 = this.$other.iterator();
                receiveChannel = this.$this_zip;
                th2 = null;
                channelsKt__Channels_commonKt$zip$2 = this;
                producerScope = producerScope2;
                channelIterator = it2;
                receiveChannel2 = receiveChannel;
                receiveChannel3 = receiveChannel2;
                it = receiveChannel.iterator();
                channelsKt__Channels_commonKt$zip$22 = channelsKt__Channels_commonKt$zip$2;
                channelsKt__Channels_commonKt$zip$2.L$0 = producerScope;
                channelsKt__Channels_commonKt$zip$2.L$1 = channelIterator;
                channelsKt__Channels_commonKt$zip$2.L$2 = receiveChannel3;
                channelsKt__Channels_commonKt$zip$2.L$3 = channelsKt__Channels_commonKt$zip$22;
                channelsKt__Channels_commonKt$zip$2.L$4 = receiveChannel;
                channelsKt__Channels_commonKt$zip$2.L$5 = th2;
                channelsKt__Channels_commonKt$zip$2.L$6 = receiveChannel2;
                channelsKt__Channels_commonKt$zip$2.L$7 = it;
                channelsKt__Channels_commonKt$zip$2.label = i4;
                obj5 = it.hasNext(channelsKt__Channels_commonKt$zip$22);
                if (obj5 == coroutine_suspended) {
                }
                if (((Boolean) obj5).booleanValue()) {
                }
            } else if (i2 == 1) {
                it = (ChannelIterator) this.L$7;
                receiveChannel2 = (ReceiveChannel) this.L$6;
                th2 = (Throwable) this.L$5;
                receiveChannel = (ReceiveChannel) this.L$4;
                channelsKt__Channels_commonKt$zip$22 = (ChannelsKt__Channels_commonKt$zip$2) this.L$3;
                receiveChannel3 = (ReceiveChannel) this.L$2;
                channelIterator = (ChannelIterator) this.L$1;
                producerScope = (ProducerScope) this.L$0;
                ResultKt.throwOnFailure(obj);
                obj5 = obj;
                channelsKt__Channels_commonKt$zip$2 = this;
                if (((Boolean) obj5).booleanValue()) {
                }
            } else if (i2 == 2) {
                Object obj6 = this.L$9;
                Object obj7 = this.L$8;
                ChannelIterator channelIterator3 = (ChannelIterator) this.L$7;
                ReceiveChannel receiveChannel4 = (ReceiveChannel) this.L$6;
                th3 = (Throwable) this.L$5;
                ReceiveChannel receiveChannel5 = (ReceiveChannel) this.L$4;
                channelsKt__Channels_commonKt$zip$23 = (ChannelsKt__Channels_commonKt$zip$2) this.L$3;
                ReceiveChannel receiveChannel6 = (ReceiveChannel) this.L$2;
                channelIterator2 = (ChannelIterator) this.L$1;
                ProducerScope producerScope3 = (ProducerScope) this.L$0;
                try {
                    ResultKt.throwOnFailure(obj);
                    obj2 = obj;
                    channelsKt__Channels_commonKt$zip$24 = this;
                    obj3 = obj6;
                    it = channelIterator3;
                    obj4 = obj7;
                    receiveChannel2 = receiveChannel4;
                    receiveChannel = receiveChannel5;
                    receiveChannel3 = receiveChannel6;
                    producerScope = producerScope3;
                    if (((Boolean) obj2).booleanValue()) {
                    }
                } catch (Throwable th4) {
                    th = th4;
                    receiveChannel = receiveChannel5;
                    try {
                        throw th;
                    } finally {
                        ChannelsKt.cancelConsumed(receiveChannel, th);
                    }
                }
            } else if (i2 != 3) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            } else {
                it = (ChannelIterator) this.L$7;
                receiveChannel2 = (ReceiveChannel) this.L$6;
                th2 = (Throwable) this.L$5;
                receiveChannel = (ReceiveChannel) this.L$4;
                channelsKt__Channels_commonKt$zip$22 = (ChannelsKt__Channels_commonKt$zip$2) this.L$3;
                receiveChannel3 = (ReceiveChannel) this.L$2;
                channelIterator = (ChannelIterator) this.L$1;
                producerScope = (ProducerScope) this.L$0;
                ResultKt.throwOnFailure(obj);
                channelsKt__Channels_commonKt$zip$2 = this;
                i3 = 2;
                i4 = 1;
                channelsKt__Channels_commonKt$zip$2.L$0 = producerScope;
                channelsKt__Channels_commonKt$zip$2.L$1 = channelIterator;
                channelsKt__Channels_commonKt$zip$2.L$2 = receiveChannel3;
                channelsKt__Channels_commonKt$zip$2.L$3 = channelsKt__Channels_commonKt$zip$22;
                channelsKt__Channels_commonKt$zip$2.L$4 = receiveChannel;
                channelsKt__Channels_commonKt$zip$2.L$5 = th2;
                channelsKt__Channels_commonKt$zip$2.L$6 = receiveChannel2;
                channelsKt__Channels_commonKt$zip$2.L$7 = it;
                channelsKt__Channels_commonKt$zip$2.label = i4;
                obj5 = it.hasNext(channelsKt__Channels_commonKt$zip$22);
                if (obj5 == coroutine_suspended) {
                    return coroutine_suspended;
                }
                if (((Boolean) obj5).booleanValue()) {
                    obj3 = it.next();
                    channelsKt__Channels_commonKt$zip$2.L$0 = producerScope;
                    channelsKt__Channels_commonKt$zip$2.L$1 = channelIterator;
                    channelsKt__Channels_commonKt$zip$2.L$2 = receiveChannel3;
                    channelsKt__Channels_commonKt$zip$2.L$3 = channelsKt__Channels_commonKt$zip$22;
                    channelsKt__Channels_commonKt$zip$2.L$4 = receiveChannel;
                    channelsKt__Channels_commonKt$zip$2.L$5 = th;
                    channelsKt__Channels_commonKt$zip$2.L$6 = receiveChannel2;
                    channelsKt__Channels_commonKt$zip$2.L$7 = it;
                    channelsKt__Channels_commonKt$zip$2.L$8 = obj3;
                    channelsKt__Channels_commonKt$zip$2.L$9 = obj3;
                    channelsKt__Channels_commonKt$zip$2.label = i3;
                    obj2 = channelIterator.hasNext(channelsKt__Channels_commonKt$zip$2);
                    if (obj2 == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    channelsKt__Channels_commonKt$zip$24 = channelsKt__Channels_commonKt$zip$2;
                    channelIterator2 = channelIterator;
                    channelsKt__Channels_commonKt$zip$23 = channelsKt__Channels_commonKt$zip$22;
                    th3 = th;
                    obj4 = obj3;
                    if (((Boolean) obj2).booleanValue()) {
                        th2 = th3;
                        channelsKt__Channels_commonKt$zip$22 = channelsKt__Channels_commonKt$zip$23;
                        channelIterator = channelIterator2;
                        channelsKt__Channels_commonKt$zip$2 = channelsKt__Channels_commonKt$zip$24;
                        i3 = 2;
                        channelsKt__Channels_commonKt$zip$2.L$0 = producerScope;
                        channelsKt__Channels_commonKt$zip$2.L$1 = channelIterator;
                        channelsKt__Channels_commonKt$zip$2.L$2 = receiveChannel3;
                        channelsKt__Channels_commonKt$zip$2.L$3 = channelsKt__Channels_commonKt$zip$22;
                        channelsKt__Channels_commonKt$zip$2.L$4 = receiveChannel;
                        channelsKt__Channels_commonKt$zip$2.L$5 = th2;
                        channelsKt__Channels_commonKt$zip$2.L$6 = receiveChannel2;
                        channelsKt__Channels_commonKt$zip$2.L$7 = it;
                        channelsKt__Channels_commonKt$zip$2.label = i4;
                        obj5 = it.hasNext(channelsKt__Channels_commonKt$zip$22);
                        if (obj5 == coroutine_suspended) {
                        }
                        if (((Boolean) obj5).booleanValue()) {
                            return Unit.INSTANCE;
                        }
                    } else {
                        Object next = channelIterator2.next();
                        Object invoke = channelsKt__Channels_commonKt$zip$24.$transform.invoke(obj3, next);
                        channelsKt__Channels_commonKt$zip$24.L$0 = producerScope;
                        channelsKt__Channels_commonKt$zip$24.L$1 = channelIterator2;
                        channelsKt__Channels_commonKt$zip$24.L$2 = receiveChannel3;
                        channelsKt__Channels_commonKt$zip$24.L$3 = channelsKt__Channels_commonKt$zip$23;
                        channelsKt__Channels_commonKt$zip$24.L$4 = receiveChannel;
                        channelsKt__Channels_commonKt$zip$24.L$5 = th3;
                        channelsKt__Channels_commonKt$zip$24.L$6 = receiveChannel2;
                        channelsKt__Channels_commonKt$zip$24.L$7 = it;
                        channelsKt__Channels_commonKt$zip$24.L$8 = obj4;
                        channelsKt__Channels_commonKt$zip$24.L$9 = obj3;
                        channelsKt__Channels_commonKt$zip$24.L$10 = next;
                        channelsKt__Channels_commonKt$zip$24.label = 3;
                        if (producerScope.send(invoke, channelsKt__Channels_commonKt$zip$24) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        th2 = th3;
                        channelsKt__Channels_commonKt$zip$22 = channelsKt__Channels_commonKt$zip$23;
                        channelIterator = channelIterator2;
                        channelsKt__Channels_commonKt$zip$2 = channelsKt__Channels_commonKt$zip$24;
                        i3 = 2;
                        i4 = 1;
                        channelsKt__Channels_commonKt$zip$2.L$0 = producerScope;
                        channelsKt__Channels_commonKt$zip$2.L$1 = channelIterator;
                        channelsKt__Channels_commonKt$zip$2.L$2 = receiveChannel3;
                        channelsKt__Channels_commonKt$zip$2.L$3 = channelsKt__Channels_commonKt$zip$22;
                        channelsKt__Channels_commonKt$zip$2.L$4 = receiveChannel;
                        channelsKt__Channels_commonKt$zip$2.L$5 = th2;
                        channelsKt__Channels_commonKt$zip$2.L$6 = receiveChannel2;
                        channelsKt__Channels_commonKt$zip$2.L$7 = it;
                        channelsKt__Channels_commonKt$zip$2.label = i4;
                        obj5 = it.hasNext(channelsKt__Channels_commonKt$zip$22);
                        if (obj5 == coroutine_suspended) {
                        }
                        if (((Boolean) obj5).booleanValue()) {
                        }
                    }
                }
            }
        } catch (Throwable th5) {
            th = th5;
        }
    }
}

package kotlinx.coroutines.channels;

import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.jvm.JvmName;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\bf\u0018\u0000*\u0006\b\u0000\u0010\u0001 \u00012\u00020\u0002J\u0013\u0010\u0004\u001a\u00020\u0003H\u00a6B\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u0004\u0010\u0005J\u0013\u0010\u0007\u001a\u00028\u0000H\u0097@\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u0006\u0010\u0005J\u0010\u0010\u0006\u001a\u00028\u0000H\u00a6\u0002\u00a2\u0006\u0004\b\u0006\u0010\b\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\t"}, d2 = {"Lkotlinx/coroutines/channels/ChannelIterator;", "E", "", "", "hasNext", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "next", "next0", "()Ljava/lang/Object;", "kotlinx-coroutines-core"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes.dex */
public interface ChannelIterator<E> {

    @Metadata(bv = {1, 0, 3}, d1 = {}, d2 = {}, k = 3, mv = {1, 4, 0})
    /* loaded from: classes.dex */
    public static final class DefaultImpls {
        /* JADX WARN: Removed duplicated region for block: B:56:0x0023  */
        /* JADX WARN: Removed duplicated region for block: B:60:0x0035  */
        /* JADX WARN: Removed duplicated region for block: B:65:0x004b  */
        /* JADX WARN: Removed duplicated region for block: B:67:0x0050  */
        @Deprecated(level = DeprecationLevel.HIDDEN, message = "Since 1.3.0, binary compatibility with versions <= 1.2.x")
        @JvmName(name = "next")
        @Nullable
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public static /* synthetic */ <E> Object next(ChannelIterator<? extends E> channelIterator, @NotNull Continuation<? super E> continuation) {
            ChannelIterator$next0$1 channelIterator$next0$1;
            Object obj;
            Object coroutine_suspended;
            int i2;
            if (continuation instanceof ChannelIterator$next0$1) {
                channelIterator$next0$1 = (ChannelIterator$next0$1) continuation;
                int i3 = channelIterator$next0$1.label;
                if ((i3 & Integer.MIN_VALUE) != 0) {
                    channelIterator$next0$1.label = i3 - Integer.MIN_VALUE;
                    obj = channelIterator$next0$1.result;
                    coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    i2 = channelIterator$next0$1.label;
                    if (i2 != 0) {
                        ResultKt.throwOnFailure(obj);
                        channelIterator$next0$1.L$0 = channelIterator;
                        channelIterator$next0$1.label = 1;
                        obj = channelIterator.hasNext(channelIterator$next0$1);
                        if (obj == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    } else if (i2 != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    } else {
                        channelIterator = (ChannelIterator) channelIterator$next0$1.L$0;
                        ResultKt.throwOnFailure(obj);
                    }
                    if (!((Boolean) obj).booleanValue()) {
                        return channelIterator.next();
                    }
                    throw new ClosedReceiveChannelException(ChannelsKt.DEFAULT_CLOSE_MESSAGE);
                }
            }
            channelIterator$next0$1 = new ChannelIterator$next0$1(channelIterator, continuation);
            obj = channelIterator$next0$1.result;
            coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            i2 = channelIterator$next0$1.label;
            if (i2 != 0) {
            }
            if (!((Boolean) obj).booleanValue()) {
            }
        }
    }

    @Nullable
    Object hasNext(@NotNull Continuation<? super Boolean> continuation);

    E next();

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Since 1.3.0, binary compatibility with versions <= 1.2.x")
    @JvmName(name = "next")
    @Nullable
    /* synthetic */ Object next(@NotNull Continuation<? super E> continuation);
}

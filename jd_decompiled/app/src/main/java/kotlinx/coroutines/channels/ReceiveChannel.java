package kotlinx.coroutines.channels;

import com.jingdong.jdsdk.constant.CartConstant;
import java.util.concurrent.CancellationException;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.coroutines.Continuation;
import kotlin.internal.LowPriorityInOverloadResolution;
import kotlinx.coroutines.ExperimentalCoroutinesApi;
import kotlinx.coroutines.InternalCoroutinesApi;
import kotlinx.coroutines.ObsoleteCoroutinesApi;
import kotlinx.coroutines.selects.SelectClause1;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000f\bf\u0018\u0000*\u0006\b\u0000\u0010\u0001 \u00012\u00020\u0002J\u0013\u0010\u0003\u001a\u00028\u0000H\u00a6@\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u0003\u0010\u0004J\u0015\u0010\u0005\u001a\u0004\u0018\u00018\u0000H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u0005\u0010\u0004J\u001c\u0010\u0007\u001a\b\u0012\u0004\u0012\u00028\u00000\u0006H\u00a7@\u00f8\u0001\u0000\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u0007\u0010\u0004J\u0011\u0010\b\u001a\u0004\u0018\u00018\u0000H&\u00a2\u0006\u0004\b\b\u0010\tJ\u0016\u0010\u000b\u001a\b\u0012\u0004\u0012\u00028\u00000\nH\u00a6\u0002\u00a2\u0006\u0004\b\u000b\u0010\fJ!\u0010\u0011\u001a\u00020\u00102\u0010\b\u0002\u0010\u000f\u001a\n\u0018\u00010\rj\u0004\u0018\u0001`\u000eH&\u00a2\u0006\u0004\b\u0011\u0010\u0012J\u000f\u0010\u0011\u001a\u00020\u0010H\u0017\u00a2\u0006\u0004\b\u0011\u0010\u0013J\u001b\u0010\u0011\u001a\u00020\u00152\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u0014H'\u00a2\u0006\u0004\b\u0011\u0010\u0016R\u001c\u0010\u001a\u001a\b\u0012\u0004\u0012\u00028\u00000\u00178&@&X\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0018\u0010\u0019R+\u0010\u001d\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u00060\u00178&@'X\u00a7\u0004\u00f8\u0001\u0000\u00a2\u0006\f\u0012\u0004\b\u001c\u0010\u0013\u001a\u0004\b\u001b\u0010\u0019R\u001c\u0010\u001e\u001a\u00020\u00158&@'X\u00a7\u0004\u00a2\u0006\f\u0012\u0004\b \u0010\u0013\u001a\u0004\b\u001e\u0010\u001fR$\u0010#\u001a\n\u0012\u0006\u0012\u0004\u0018\u00018\u00000\u00178&@'X\u00a7\u0004\u00a2\u0006\f\u0012\u0004\b\"\u0010\u0013\u001a\u0004\b!\u0010\u0019R\u001c\u0010$\u001a\u00020\u00158&@'X\u00a7\u0004\u00a2\u0006\f\u0012\u0004\b%\u0010\u0013\u001a\u0004\b$\u0010\u001f\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006&"}, d2 = {"Lkotlinx/coroutines/channels/ReceiveChannel;", "E", "", "receive", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "receiveOrNull", "Lkotlinx/coroutines/channels/ValueOrClosed;", "receiveOrClosed", "poll", "()Ljava/lang/Object;", "Lkotlinx/coroutines/channels/ChannelIterator;", "iterator", "()Lkotlinx/coroutines/channels/ChannelIterator;", "Ljava/util/concurrent/CancellationException;", "Lkotlinx/coroutines/CancellationException;", "cause", "", "cancel", "(Ljava/util/concurrent/CancellationException;)V", "()V", "", "", "(Ljava/lang/Throwable;)Z", "Lkotlinx/coroutines/selects/SelectClause1;", "getOnReceive", "()Lkotlinx/coroutines/selects/SelectClause1;", "onReceive", "getOnReceiveOrClosed", "onReceiveOrClosed$annotations", "onReceiveOrClosed", "isClosedForReceive", "()Z", "isClosedForReceive$annotations", "getOnReceiveOrNull", "onReceiveOrNull$annotations", "onReceiveOrNull", CartConstant.KEY_GLOBAL_IS_EMPTY, "isEmpty$annotations", "kotlinx-coroutines-core"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes.dex */
public interface ReceiveChannel<E> {
    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Since 1.2.0, binary compatibility with versions <= 1.1.x")
    /* synthetic */ void cancel();

    void cancel(@Nullable CancellationException cause);

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Since 1.2.0, binary compatibility with versions <= 1.1.x")
    /* synthetic */ boolean cancel(@Nullable Throwable cause);

    @NotNull
    SelectClause1<E> getOnReceive();

    @NotNull
    SelectClause1<ValueOrClosed<E>> getOnReceiveOrClosed();

    @NotNull
    SelectClause1<E> getOnReceiveOrNull();

    boolean isClosedForReceive();

    boolean isEmpty();

    @NotNull
    ChannelIterator<E> iterator();

    @Nullable
    E poll();

    @Nullable
    Object receive(@NotNull Continuation<? super E> continuation);

    @InternalCoroutinesApi
    @Nullable
    Object receiveOrClosed(@NotNull Continuation<? super ValueOrClosed<? extends E>> continuation);

    @ObsoleteCoroutinesApi
    @Nullable
    @Deprecated(level = DeprecationLevel.WARNING, message = "Deprecated in favor of receiveOrClosed and receiveOrNull extension", replaceWith = @ReplaceWith(expression = "receiveOrNull", imports = {"kotlinx.coroutines.channels.receiveOrNull"}))
    @LowPriorityInOverloadResolution
    Object receiveOrNull(@NotNull Continuation<? super E> continuation);

    @Metadata(bv = {1, 0, 3}, d1 = {}, d2 = {}, k = 3, mv = {1, 4, 0})
    /* loaded from: classes.dex */
    public static final class DefaultImpls {
        public static /* synthetic */ void cancel$default(ReceiveChannel receiveChannel, CancellationException cancellationException, int i2, Object obj) {
            if (obj == null) {
                if ((i2 & 1) != 0) {
                    cancellationException = null;
                }
                receiveChannel.cancel(cancellationException);
                return;
            }
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: cancel");
        }

        @ExperimentalCoroutinesApi
        public static /* synthetic */ void isClosedForReceive$annotations() {
        }

        @ExperimentalCoroutinesApi
        public static /* synthetic */ void isEmpty$annotations() {
        }

        @InternalCoroutinesApi
        public static /* synthetic */ void onReceiveOrClosed$annotations() {
        }

        @Deprecated(level = DeprecationLevel.WARNING, message = "Deprecated in favor of onReceiveOrClosed and onReceiveOrNull extension", replaceWith = @ReplaceWith(expression = "onReceiveOrNull", imports = {"kotlinx.coroutines.channels.onReceiveOrNull"}))
        @LowPriorityInOverloadResolution
        @ObsoleteCoroutinesApi
        public static /* synthetic */ void onReceiveOrNull$annotations() {
        }

        public static /* synthetic */ boolean cancel$default(ReceiveChannel receiveChannel, Throwable th, int i2, Object obj) {
            if (obj == null) {
                if ((i2 & 1) != 0) {
                    th = null;
                }
                return receiveChannel.cancel(th);
            }
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: cancel");
        }
    }
}

package kotlinx.coroutines.channels;

import com.facebook.react.modules.appstate.AppStateModule;
import com.jingdong.common.jdreactFramework.JDReactConstant;
import com.jingdong.jdsdk.constant.CartConstant;
import java.util.concurrent.CancellationException;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.internal.LowPriorityInOverloadResolution;
import kotlin.jvm.functions.Function1;
import kotlinx.coroutines.; job=;
import kotlinx.coroutines.AbstractCoroutine;
import kotlinx.coroutines.ExperimentalCoroutinesApi;
import kotlinx.coroutines.InternalCoroutinesApi;
import kotlinx.coroutines.JobSupport;
import kotlinx.coroutines.ObsoleteCoroutinesApi;
import kotlinx.coroutines.selects.SelectClause1;
import kotlinx.coroutines.selects.SelectClause2;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000f\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0010\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u00020\u00030\u00022\b\u0012\u0004\u0012\u00028\u00000\u0004B%\u0012\u0006\u0010@\u001a\u00020?\u0012\f\u00104\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004\u0012\u0006\u0010A\u001a\u00020\t\u00a2\u0006\u0004\bB\u0010CJ\u000f\u0010\u0005\u001a\u00020\u0003H\u0016\u00a2\u0006\u0004\b\u0005\u0010\u0006J\u0019\u0010\u0005\u001a\u00020\t2\b\u0010\b\u001a\u0004\u0018\u00010\u0007H\u0007\u00a2\u0006\u0004\b\u0005\u0010\nJ\u001d\u0010\u0005\u001a\u00020\u00032\u000e\u0010\b\u001a\n\u0018\u00010\u000bj\u0004\u0018\u0001`\f\u00a2\u0006\u0004\b\u0005\u0010\rJ\u0017\u0010\u000e\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\u0007H\u0016\u00a2\u0006\u0004\b\u000e\u0010\u000fJ\u001b\u0010\u0011\u001a\u00020\u00032\u0006\u0010\u0010\u001a\u00028\u0000H\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u0011\u0010\u0012J\u001a\u0010\u0013\u001a\u00020\t2\b\u0010\b\u001a\u0004\u0018\u00010\u0007H\u0096\u0001\u00a2\u0006\u0004\b\u0013\u0010\nJ5\u0010\u0018\u001a\u00020\u00032#\u0010\u0017\u001a\u001f\u0012\u0015\u0012\u0013\u0018\u00010\u0007\u00a2\u0006\f\b\u0015\u0012\b\b\u0016\u0012\u0004\b\b(\b\u0012\u0004\u0012\u00020\u00030\u0014H\u0097\u0001\u00a2\u0006\u0004\b\u0018\u0010\u0019J\u0016\u0010\u001b\u001a\b\u0012\u0004\u0012\u00028\u00000\u001aH\u0096\u0003\u00a2\u0006\u0004\b\u001b\u0010\u001cJ\u0018\u0010\u001d\u001a\u00020\t2\u0006\u0010\u0010\u001a\u00028\u0000H\u0096\u0001\u00a2\u0006\u0004\b\u001d\u0010\u001eJ\u0012\u0010\u001f\u001a\u0004\u0018\u00018\u0000H\u0096\u0001\u00a2\u0006\u0004\b\u001f\u0010 J\u0013\u0010!\u001a\u00028\u0000H\u0096A\u00f8\u0001\u0000\u00a2\u0006\u0004\b!\u0010\"J\u001c\u0010$\u001a\b\u0012\u0004\u0012\u00028\u00000#H\u0097A\u00f8\u0001\u0000\u00f8\u0001\u0000\u00a2\u0006\u0004\b$\u0010\"J\u0015\u0010%\u001a\u0004\u0018\u00018\u0000H\u0097A\u00f8\u0001\u0000\u00a2\u0006\u0004\b%\u0010\"J\u001b\u0010&\u001a\u00020\u00032\u0006\u0010\u0010\u001a\u00028\u0000H\u0096A\u00f8\u0001\u0000\u00a2\u0006\u0004\b&\u0010\u0012R(\u0010+\u001a\u0014\u0012\u0004\u0012\u00028\u0000\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000(0'8\u0016@\u0016X\u0096\u0005\u00a2\u0006\u0006\u001a\u0004\b)\u0010*R\u0016\u0010,\u001a\u00020\t8\u0016@\u0017X\u0097\u0005\u00a2\u0006\u0006\u001a\u0004\b,\u0010-R%\u00101\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000#0.8\u0016@\u0017X\u0097\u0005\u00f8\u0001\u0000\u00a2\u0006\u0006\u001a\u0004\b/\u00100R\u001e\u00103\u001a\n\u0012\u0006\u0012\u0004\u0018\u00018\u00000.8\u0016@\u0017X\u0097\u0005\u00a2\u0006\u0006\u001a\u0004\b2\u00100R\"\u00104\u001a\b\u0012\u0004\u0012\u00028\u00000\u00048\u0004@\u0004X\u0084\u0004\u00a2\u0006\f\n\u0004\b4\u00105\u001a\u0004\b6\u00107R\u0019\u00109\u001a\b\u0012\u0004\u0012\u00028\u00000\u00048F@\u0006\u00a2\u0006\u0006\u001a\u0004\b8\u00107R\u0016\u0010:\u001a\u00020\t8\u0016@\u0017X\u0097\u0005\u00a2\u0006\u0006\u001a\u0004\b:\u0010-R\u0016\u0010;\u001a\u00020\t8\u0016@\u0017X\u0097\u0005\u00a2\u0006\u0006\u001a\u0004\b;\u0010-R\u0016\u0010<\u001a\u00020\t8\u0016@\u0017X\u0097\u0005\u00a2\u0006\u0006\u001a\u0004\b<\u0010-R\u001c\u0010>\u001a\b\u0012\u0004\u0012\u00028\u00000.8\u0016@\u0016X\u0096\u0005\u00a2\u0006\u0006\u001a\u0004\b=\u00100\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006D"}, d2 = {"Lkotlinx/coroutines/channels/ChannelCoroutine;", "E", "Lkotlinx/coroutines/AbstractCoroutine;", "", "Lkotlinx/coroutines/channels/Channel;", "cancel", "()V", "", "cause", "", "(Ljava/lang/Throwable;)Z", "Ljava/util/concurrent/CancellationException;", "Lkotlinx/coroutines/CancellationException;", "(Ljava/util/concurrent/CancellationException;)V", "cancelInternal", "(Ljava/lang/Throwable;)V", "element", "sendFair", "(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "close", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "handler", "invokeOnClose", "(Lkotlin/jvm/functions/Function1;)V", "Lkotlinx/coroutines/channels/ChannelIterator;", "iterator", "()Lkotlinx/coroutines/channels/ChannelIterator;", "offer", "(Ljava/lang/Object;)Z", "poll", "()Ljava/lang/Object;", "receive", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Lkotlinx/coroutines/channels/ValueOrClosed;", "receiveOrClosed", "receiveOrNull", "send", "Lkotlinx/coroutines/selects/SelectClause2;", "Lkotlinx/coroutines/channels/SendChannel;", "getOnSend", "()Lkotlinx/coroutines/selects/SelectClause2;", "onSend", "isClosedForReceive", "()Z", "Lkotlinx/coroutines/selects/SelectClause1;", "getOnReceiveOrClosed", "()Lkotlinx/coroutines/selects/SelectClause1;", "onReceiveOrClosed", "getOnReceiveOrNull", "onReceiveOrNull", "_channel", "Lkotlinx/coroutines/channels/Channel;", "get_channel", "()Lkotlinx/coroutines/channels/Channel;", "getChannel", "channel", CartConstant.KEY_GLOBAL_IS_EMPTY, "isClosedForSend", JDReactConstant.TPYE_FLAG, "getOnReceive", "onReceive", "Lkotlin/coroutines/CoroutineContext;", "parentContext", AppStateModule.APP_STATE_ACTIVE, "<init>", "(Lkotlin/coroutines/CoroutineContext;Lkotlinx/coroutines/channels/Channel;Z)V", "kotlinx-coroutines-core"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes.dex */
public class ChannelCoroutine<E> extends AbstractCoroutine<Unit> implements Channel<E> {
    @NotNull
    private final Channel<E> _channel;

    public ChannelCoroutine(@NotNull CoroutineContext coroutineContext, @NotNull Channel<E> channel, boolean z) {
        super(coroutineContext, z);
        this._channel = channel;
    }

    static /* synthetic */ Object receive$suspendImpl(ChannelCoroutine channelCoroutine, Continuation continuation) {
        return channelCoroutine._channel.receive(continuation);
    }

    static /* synthetic */ Object receiveOrClosed$suspendImpl(ChannelCoroutine channelCoroutine, Continuation continuation) {
        return channelCoroutine._channel.receiveOrClosed(continuation);
    }

    static /* synthetic */ Object receiveOrNull$suspendImpl(ChannelCoroutine channelCoroutine, Continuation continuation) {
        return channelCoroutine._channel.receiveOrNull(continuation);
    }

    static /* synthetic */ Object send$suspendImpl(ChannelCoroutine channelCoroutine, Object obj, Continuation continuation) {
        return channelCoroutine._channel.send(obj, continuation);
    }

    @Override // kotlinx.coroutines.JobSupport, kotlinx.coroutines.Job
    public /* synthetic */ void cancel() {
        cancelInternal(new ; job=(cancellationExceptionMessage(), null, this));
    }

    @Override // kotlinx.coroutines.JobSupport
    public void cancelInternal(@NotNull Throwable cause) {
        CancellationException cancellationException$default = JobSupport.toCancellationException$default(this, cause, null, 1, null);
        this._channel.cancel(cancellationException$default);
        cancelCoroutine(cancellationException$default);
    }

    @Override // kotlinx.coroutines.channels.SendChannel
    /* renamed from: close */
    public boolean cancel(@Nullable Throwable cause) {
        return this._channel.cancel(cause);
    }

    @NotNull
    public final Channel<E> getChannel() {
        return this;
    }

    @Override // kotlinx.coroutines.channels.ReceiveChannel
    @NotNull
    public SelectClause1<E> getOnReceive() {
        return this._channel.getOnReceive();
    }

    @Override // kotlinx.coroutines.channels.ReceiveChannel
    @NotNull
    public SelectClause1<ValueOrClosed<E>> getOnReceiveOrClosed() {
        return this._channel.getOnReceiveOrClosed();
    }

    @Override // kotlinx.coroutines.channels.ReceiveChannel
    @NotNull
    public SelectClause1<E> getOnReceiveOrNull() {
        return this._channel.getOnReceiveOrNull();
    }

    @Override // kotlinx.coroutines.channels.SendChannel
    @NotNull
    public SelectClause2<E, SendChannel<E>> getOnSend() {
        return this._channel.getOnSend();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @NotNull
    public final Channel<E> get_channel() {
        return this._channel;
    }

    @Override // kotlinx.coroutines.channels.SendChannel
    @ExperimentalCoroutinesApi
    public void invokeOnClose(@NotNull Function1<? super Throwable, Unit> handler) {
        this._channel.invokeOnClose(handler);
    }

    @Override // kotlinx.coroutines.channels.ReceiveChannel
    public boolean isClosedForReceive() {
        return this._channel.isClosedForReceive();
    }

    @Override // kotlinx.coroutines.channels.SendChannel
    public boolean isClosedForSend() {
        return this._channel.isClosedForSend();
    }

    @Override // kotlinx.coroutines.channels.ReceiveChannel
    public boolean isEmpty() {
        return this._channel.isEmpty();
    }

    @Override // kotlinx.coroutines.channels.SendChannel
    public boolean isFull() {
        return this._channel.isFull();
    }

    @Override // kotlinx.coroutines.channels.ReceiveChannel
    @NotNull
    public ChannelIterator<E> iterator() {
        return this._channel.iterator();
    }

    @Override // kotlinx.coroutines.channels.SendChannel
    public boolean offer(E element) {
        return this._channel.offer(element);
    }

    @Override // kotlinx.coroutines.channels.ReceiveChannel
    @Nullable
    public E poll() {
        return this._channel.poll();
    }

    @Override // kotlinx.coroutines.channels.ReceiveChannel
    @Nullable
    public Object receive(@NotNull Continuation<? super E> continuation) {
        return receive$suspendImpl(this, continuation);
    }

    @Override // kotlinx.coroutines.channels.ReceiveChannel
    @InternalCoroutinesApi
    @Nullable
    public Object receiveOrClosed(@NotNull Continuation<? super ValueOrClosed<? extends E>> continuation) {
        return receiveOrClosed$suspendImpl(this, continuation);
    }

    @Override // kotlinx.coroutines.channels.ReceiveChannel
    @ObsoleteCoroutinesApi
    @Nullable
    @Deprecated(level = DeprecationLevel.WARNING, message = "Deprecated in favor of receiveOrClosed and receiveOrNull extension", replaceWith = @ReplaceWith(expression = "receiveOrNull", imports = {"kotlinx.coroutines.channels.receiveOrNull"}))
    @LowPriorityInOverloadResolution
    public Object receiveOrNull(@NotNull Continuation<? super E> continuation) {
        return receiveOrNull$suspendImpl(this, continuation);
    }

    @Override // kotlinx.coroutines.channels.SendChannel
    @Nullable
    public Object send(E e2, @NotNull Continuation<? super Unit> continuation) {
        return send$suspendImpl(this, e2, continuation);
    }

    @Nullable
    public final Object sendFair(E e2, @NotNull Continuation<? super Unit> continuation) {
        Object coroutine_suspended;
        Channel<E> channel = this._channel;
        if (channel != null) {
            Object sendFair$kotlinx_coroutines_core = ((AbstractSendChannel) channel).sendFair$kotlinx_coroutines_core(e2, continuation);
            coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            return sendFair$kotlinx_coroutines_core == coroutine_suspended ? sendFair$kotlinx_coroutines_core : Unit.INSTANCE;
        }
        throw new TypeCastException("null cannot be cast to non-null type kotlinx.coroutines.channels.AbstractSendChannel<E>");
    }

    @Override // kotlinx.coroutines.JobSupport, kotlinx.coroutines.Job
    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Since 1.2.0, binary compatibility with versions <= 1.1.x")
    public final /* synthetic */ boolean cancel(@Nullable Throwable cause) {
        cancelInternal(new ; job=(cancellationExceptionMessage(), null, this));
        return true;
    }

    @Override // kotlinx.coroutines.JobSupport, kotlinx.coroutines.Job
    public final void cancel(@Nullable CancellationException cause) {
        if (cause == null) {
            cause = new ; job=(cancellationExceptionMessage(), null, this);
        }
        cancelInternal(cause);
    }
}

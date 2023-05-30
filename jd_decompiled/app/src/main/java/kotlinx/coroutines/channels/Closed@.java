package kotlinx.coroutines.channels;

import com.jingdong.jdsdk.constant.JshopConst;
import kotlin.Metadata;
import kotlin.jvm.JvmField;
import kotlinx.coroutines.CancellableContinuationImplKt;
import kotlinx.coroutines.DebugKt;
import kotlinx.coroutines.DebugStringsKt;
import kotlinx.coroutines.internal.LockFreeLinkedListNode;
import kotlinx.coroutines.internal.Symbol;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\n\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u0003\n\u0002\b\f\b\u0000\u0018\u0000*\u0006\b\u0000\u0010\u0001 \u00002\u00020\u00022\b\u0012\u0004\u0012\u00028\u00000\u0003B\u0011\u0012\b\u0010\u001e\u001a\u0004\u0018\u00010\u001a\u00a2\u0006\u0004\b$\u0010%J\u001b\u0010\u0007\u001a\u0004\u0018\u00010\u00062\b\u0010\u0005\u001a\u0004\u0018\u00010\u0004H\u0016\u00a2\u0006\u0004\b\u0007\u0010\bJ\u000f\u0010\n\u001a\u00020\tH\u0016\u00a2\u0006\u0004\b\n\u0010\u000bJ#\u0010\r\u001a\u0004\u0018\u00010\u00062\u0006\u0010\f\u001a\u00028\u00002\b\u0010\u0005\u001a\u0004\u0018\u00010\u0004H\u0016\u00a2\u0006\u0004\b\r\u0010\u000eJ\u0017\u0010\u000f\u001a\u00020\t2\u0006\u0010\f\u001a\u00028\u0000H\u0016\u00a2\u0006\u0004\b\u000f\u0010\u0010J\u001b\u0010\u0012\u001a\u00020\t2\n\u0010\u0011\u001a\u0006\u0012\u0002\b\u00030\u0000H\u0016\u00a2\u0006\u0004\b\u0012\u0010\u0013J\u000f\u0010\u0015\u001a\u00020\u0014H\u0016\u00a2\u0006\u0004\b\u0015\u0010\u0016R\u001c\u0010\u0019\u001a\b\u0012\u0004\u0012\u00028\u00000\u00008V@\u0016X\u0096\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0017\u0010\u0018R\u0013\u0010\u001d\u001a\u00020\u001a8F@\u0006\u00a2\u0006\u0006\u001a\u0004\b\u001b\u0010\u001cR\u0018\u0010\u001e\u001a\u0004\u0018\u00010\u001a8\u0006@\u0007X\u0087\u0004\u00a2\u0006\u0006\n\u0004\b\u001e\u0010\u001fR\u001c\u0010!\u001a\b\u0012\u0004\u0012\u00028\u00000\u00008V@\u0016X\u0096\u0004\u00a2\u0006\u0006\u001a\u0004\b \u0010\u0018R\u0013\u0010#\u001a\u00020\u001a8F@\u0006\u00a2\u0006\u0006\u001a\u0004\b\"\u0010\u001c\u00a8\u0006&"}, d2 = {"Lkotlinx/coroutines/channels/Closed;", "E", "Lkotlinx/coroutines/channels/Send;", "Lkotlinx/coroutines/channels/ReceiveOrClosed;", "Lkotlinx/coroutines/internal/LockFreeLinkedListNode$PrepareOp;", "otherOp", "Lkotlinx/coroutines/internal/Symbol;", "tryResumeSend", "(Lkotlinx/coroutines/internal/LockFreeLinkedListNode$PrepareOp;)Lkotlinx/coroutines/internal/Symbol;", "", "completeResumeSend", "()V", "value", "tryResumeReceive", "(Ljava/lang/Object;Lkotlinx/coroutines/internal/LockFreeLinkedListNode$PrepareOp;)Lkotlinx/coroutines/internal/Symbol;", "completeResumeReceive", "(Ljava/lang/Object;)V", JshopConst.JSKEY_SHOP_CLOSED, "resumeSendClosed", "(Lkotlinx/coroutines/channels/Closed;)V", "", "toString", "()Ljava/lang/String;", "getPollResult", "()Lkotlinx/coroutines/channels/Closed;", "pollResult", "", "getSendException", "()Ljava/lang/Throwable;", "sendException", "closeCause", "Ljava/lang/Throwable;", "getOfferResult", "offerResult", "getReceiveException", "receiveException", "<init>", "(Ljava/lang/Throwable;)V", "kotlinx-coroutines-core"}, k = 1, mv = {1, 4, 0})
/* renamed from: kotlinx.coroutines.channels.Closed  reason: from toString */
/* loaded from: classes11.dex */
public final class Closed@<E> extends Send implements ReceiveOrClosed<E> {
    @JvmField
    @Nullable

    /* renamed from: closeCause  reason: from kotlin metadata and from toString */
    public final Throwable Closed@;

    public Closed@(@Nullable Throwable th) {
        this.Closed@ = th;
    }

    @Override // kotlinx.coroutines.channels.ReceiveOrClosed
    public void completeResumeReceive(E value) {
    }

    @Override // kotlinx.coroutines.channels.Send
    public void completeResumeSend() {
    }

    @Override // kotlinx.coroutines.channels.ReceiveOrClosed
    @NotNull
    public Closed@<E> getOfferResult() {
        return this;
    }

    @Override // kotlinx.coroutines.channels.Send
    @NotNull
    public Closed@<E> getPollResult() {
        return this;
    }

    @NotNull
    public final Throwable getReceiveException() {
        Throwable th = this.Closed@;
        return th != null ? th : new ClosedReceiveChannelException(ChannelsKt.DEFAULT_CLOSE_MESSAGE);
    }

    @NotNull
    public final Throwable getSendException() {
        Throwable th = this.Closed@;
        return th != null ? th : new ClosedSendChannelException(ChannelsKt.DEFAULT_CLOSE_MESSAGE);
    }

    @Override // kotlinx.coroutines.channels.Send
    public void resumeSendClosed(@NotNull Closed@<?> closed) {
        if (DebugKt.getASSERTIONS_ENABLED()) {
            throw new AssertionError();
        }
    }

    @Override // kotlinx.coroutines.internal.LockFreeLinkedListNode
    @NotNull
    public String toString() {
        return "Closed@" + DebugStringsKt.getHexAddress(this) + '[' + this.Closed@ + ']';
    }

    @Override // kotlinx.coroutines.channels.ReceiveOrClosed
    @Nullable
    public Symbol tryResumeReceive(E value, @Nullable LockFreeLinkedListNode.PrepareOp otherOp) {
        Symbol symbol = CancellableContinuationImplKt.RESUME_TOKEN;
        if (otherOp != null) {
            otherOp.finishPrepare();
        }
        return symbol;
    }

    @Override // kotlinx.coroutines.channels.Send
    @Nullable
    public Symbol tryResumeSend(@Nullable LockFreeLinkedListNode.PrepareOp otherOp) {
        Symbol symbol = CancellableContinuationImplKt.RESUME_TOKEN;
        if (otherOp != null) {
            otherOp.finishPrepare();
        }
        return symbol;
    }
}

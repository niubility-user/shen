package kotlinx.coroutines.channels;

import com.jingdong.app.mall.bundle.dolphinlib.common.util.EtModelMaker;
import com.jingdong.jdsdk.constant.JshopConst;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.jvm.JvmField;
import kotlinx.coroutines.CancellableContinuation;
import kotlinx.coroutines.CancellableContinuationImplKt;
import kotlinx.coroutines.DebugKt;
import kotlinx.coroutines.DebugStringsKt;
import kotlinx.coroutines.internal.LockFreeLinkedListNode;
import kotlinx.coroutines.internal.Symbol;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0007\b\u0000\u0018\u00002\u00020\u0001B\u001f\u0012\b\u0010\u0015\u001a\u0004\u0018\u00010\u0014\u0012\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00070\u0011\u00a2\u0006\u0004\b\u0019\u0010\u001aJ\u001b\u0010\u0005\u001a\u0004\u0018\u00010\u00042\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002H\u0016\u00a2\u0006\u0004\b\u0005\u0010\u0006J\u000f\u0010\b\u001a\u00020\u0007H\u0016\u00a2\u0006\u0004\b\b\u0010\tJ\u001b\u0010\f\u001a\u00020\u00072\n\u0010\u000b\u001a\u0006\u0012\u0002\b\u00030\nH\u0016\u00a2\u0006\u0004\b\f\u0010\rJ\u000f\u0010\u000f\u001a\u00020\u000eH\u0016\u00a2\u0006\u0004\b\u000f\u0010\u0010R\u001c\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00070\u00118\u0006@\u0007X\u0087\u0004\u00a2\u0006\u0006\n\u0004\b\u0012\u0010\u0013R\u001e\u0010\u0015\u001a\u0004\u0018\u00010\u00148\u0016@\u0016X\u0096\u0004\u00a2\u0006\f\n\u0004\b\u0015\u0010\u0016\u001a\u0004\b\u0017\u0010\u0018\u00a8\u0006\u001b"}, d2 = {"Lkotlinx/coroutines/channels/SendElement;", "Lkotlinx/coroutines/channels/Send;", "Lkotlinx/coroutines/internal/LockFreeLinkedListNode$PrepareOp;", "otherOp", "Lkotlinx/coroutines/internal/Symbol;", "tryResumeSend", "(Lkotlinx/coroutines/internal/LockFreeLinkedListNode$PrepareOp;)Lkotlinx/coroutines/internal/Symbol;", "", "completeResumeSend", "()V", "Lkotlinx/coroutines/channels/Closed;", JshopConst.JSKEY_SHOP_CLOSED, "resumeSendClosed", "(Lkotlinx/coroutines/channels/Closed;)V", "", "toString", "()Ljava/lang/String;", "Lkotlinx/coroutines/CancellableContinuation;", EtModelMaker.KEY_CONT, "Lkotlinx/coroutines/CancellableContinuation;", "", "pollResult", "Ljava/lang/Object;", "getPollResult", "()Ljava/lang/Object;", "<init>", "(Ljava/lang/Object;Lkotlinx/coroutines/CancellableContinuation;)V", "kotlinx-coroutines-core"}, k = 1, mv = {1, 4, 0})
/* renamed from: kotlinx.coroutines.channels.SendElement  reason: from toString */
/* loaded from: classes11.dex */
public final class SendElement@ extends Send {
    @JvmField
    @NotNull
    public final CancellableContinuation<Unit> cont;
    @Nullable
    private final Object pollResult;

    /* JADX WARN: Multi-variable type inference failed */
    public SendElement@(@Nullable Object obj, @NotNull CancellableContinuation<? super Unit> cancellableContinuation) {
        this.pollResult = obj;
        this.cont = cancellableContinuation;
    }

    @Override // kotlinx.coroutines.channels.Send
    public void completeResumeSend() {
        this.cont.completeResume(CancellableContinuationImplKt.RESUME_TOKEN);
    }

    @Override // kotlinx.coroutines.channels.Send
    @Nullable
    public Object getPollResult() {
        return this.pollResult;
    }

    @Override // kotlinx.coroutines.channels.Send
    public void resumeSendClosed(@NotNull Closed@<?> closed) {
        CancellableContinuation<Unit> cancellableContinuation = this.cont;
        Throwable sendException = closed.getSendException();
        Result.Companion companion = Result.INSTANCE;
        cancellableContinuation.resumeWith(Result.m200constructorimpl(ResultKt.createFailure(sendException)));
    }

    @Override // kotlinx.coroutines.internal.LockFreeLinkedListNode
    @NotNull
    public String toString() {
        return "SendElement@" + DebugStringsKt.getHexAddress(this) + '(' + getPollResult() + ')';
    }

    @Override // kotlinx.coroutines.channels.Send
    @Nullable
    public Symbol tryResumeSend(@Nullable LockFreeLinkedListNode.PrepareOp otherOp) {
        Object tryResume = this.cont.tryResume(Unit.INSTANCE, otherOp != null ? otherOp.desc : null);
        if (tryResume != null) {
            if (DebugKt.getASSERTIONS_ENABLED()) {
                if (!(tryResume == CancellableContinuationImplKt.RESUME_TOKEN)) {
                    throw new AssertionError();
                }
            }
            if (otherOp != null) {
                otherOp.finishPrepare();
            }
            return CancellableContinuationImplKt.RESUME_TOKEN;
        }
        return null;
    }
}

package kotlinx.coroutines.channels;

import com.jingdong.common.jdreactFramework.JDReactConstant;
import com.jingdong.jdsdk.constant.JshopConst;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.ContinuationKt;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsJvmKt;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugProbes;
import kotlin.jvm.JvmField;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.TypeIntrinsics;
import kotlinx.coroutines.CancellableContinuationImpl;
import kotlinx.coroutines.CancellableContinuationImplKt;
import kotlinx.coroutines.CancellableContinuationKt;
import kotlinx.coroutines.DebugKt;
import kotlinx.coroutines.DebugStringsKt;
import kotlinx.coroutines.DisposableHandle;
import kotlinx.coroutines.YieldKt;
import kotlinx.coroutines.internal.AtomicKt;
import kotlinx.coroutines.internal.InlineList;
import kotlinx.coroutines.internal.LockFreeLinkedListHead;
import kotlinx.coroutines.internal.LockFreeLinkedListKt;
import kotlinx.coroutines.internal.LockFreeLinkedListNode;
import kotlinx.coroutines.internal.LockFreeLinkedList_commonKt;
import kotlinx.coroutines.internal.StackTraceRecoveryKt;
import kotlinx.coroutines.internal.Symbol;
import kotlinx.coroutines.intrinsics.UndispatchedKt;
import kotlinx.coroutines.selects.SelectClause2;
import kotlinx.coroutines.selects.SelectInstance;
import kotlinx.coroutines.selects.SelectKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0098\u0001\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\f\b \u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u00028\u00000\u0002:\u0004abcdB\u0007\u00a2\u0006\u0004\b_\u0010`J\u001b\u0010\u0006\u001a\u00020\u00052\n\u0010\u0004\u001a\u0006\u0012\u0002\b\u00030\u0003H\u0002\u00a2\u0006\u0004\b\u0006\u0010\u0007J#\u0010\n\u001a\u00020\t*\u0006\u0012\u0002\b\u00030\b2\n\u0010\u0004\u001a\u0006\u0012\u0002\b\u00030\u0003H\u0002\u00a2\u0006\u0004\b\n\u0010\u000bJ\u0019\u0010\r\u001a\u00020\t2\b\u0010\f\u001a\u0004\u0018\u00010\u0005H\u0002\u00a2\u0006\u0004\b\r\u0010\u000eJ\u001b\u0010\u000f\u001a\u00020\t2\n\u0010\u0004\u001a\u0006\u0012\u0002\b\u00030\u0003H\u0002\u00a2\u0006\u0004\b\u000f\u0010\u0010JX\u0010\u0018\u001a\u00020\t\"\u0004\b\u0001\u0010\u00112\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00028\u00010\u00122\u0006\u0010\u0014\u001a\u00028\u00002(\u0010\u0017\u001a$\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00010\b\u0012\u0006\u0012\u0004\u0018\u00010\u00160\u0015H\u0002\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u0018\u0010\u0019J\u000f\u0010\u001b\u001a\u00020\u001aH\u0002\u00a2\u0006\u0004\b\u001b\u0010\u001cJ\u0017\u0010\u001d\u001a\u00020\u00162\u0006\u0010\u0014\u001a\u00028\u0000H\u0014\u00a2\u0006\u0004\b\u001d\u0010\u001eJ#\u0010\u001f\u001a\u00020\u00162\u0006\u0010\u0014\u001a\u00028\u00002\n\u0010\u0013\u001a\u0006\u0012\u0002\b\u00030\u0012H\u0014\u00a2\u0006\u0004\b\u001f\u0010 J\u0011\u0010\"\u001a\u0004\u0018\u00010!H\u0004\u00a2\u0006\u0004\b\"\u0010#J\u001d\u0010%\u001a\b\u0012\u0002\b\u0003\u0018\u00010$2\u0006\u0010\u0014\u001a\u00028\u0000H\u0004\u00a2\u0006\u0004\b%\u0010&J#\u0010)\u001a\u000e\u0012\u0002\b\u00030'j\u0006\u0012\u0002\b\u0003`(2\u0006\u0010\u0014\u001a\u00028\u0000H\u0004\u00a2\u0006\u0004\b)\u0010*J\u001b\u0010+\u001a\u00020\t2\u0006\u0010\u0014\u001a\u00028\u0000H\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0004\b+\u0010,J\u001b\u0010.\u001a\u00020\t2\u0006\u0010\u0014\u001a\u00028\u0000H\u0080@\u00f8\u0001\u0000\u00a2\u0006\u0004\b-\u0010,J\u0015\u00100\u001a\u00020/2\u0006\u0010\u0014\u001a\u00028\u0000\u00a2\u0006\u0004\b0\u00101J\u001b\u00102\u001a\u00020\t2\u0006\u0010\u0014\u001a\u00028\u0000H\u0082@\u00f8\u0001\u0000\u00a2\u0006\u0004\b2\u0010,J\u0019\u00103\u001a\u0004\u0018\u00010\u00162\u0006\u0010+\u001a\u00020!H\u0014\u00a2\u0006\u0004\b3\u00104J\u0019\u00105\u001a\u00020/2\b\u0010\f\u001a\u0004\u0018\u00010\u0005H\u0016\u00a2\u0006\u0004\b5\u00106J)\u0010:\u001a\u00020\t2\u0018\u00109\u001a\u0014\u0012\u0006\u0012\u0004\u0018\u00010\u0005\u0012\u0004\u0012\u00020\t07j\u0002`8H\u0016\u00a2\u0006\u0004\b:\u0010;J\u0017\u0010=\u001a\u00020\t2\u0006\u0010\u0004\u001a\u00020<H\u0014\u00a2\u0006\u0004\b=\u0010>J\u0017\u0010?\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010$H\u0014\u00a2\u0006\u0004\b?\u0010@J\u001d\u0010B\u001a\b\u0012\u0004\u0012\u00028\u00000A2\u0006\u0010\u0014\u001a\u00028\u0000H\u0004\u00a2\u0006\u0004\bB\u0010CJ\u000f\u0010E\u001a\u00020DH\u0016\u00a2\u0006\u0004\bE\u0010FR\u0016\u0010H\u001a\u00020D8B@\u0002X\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\bG\u0010FR\u001c\u0010K\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u00038D@\u0004X\u0084\u0004\u00a2\u0006\u0006\u001a\u0004\bI\u0010JR\u0013\u0010L\u001a\u00020/8F@\u0006\u00a2\u0006\u0006\u001a\u0004\bL\u0010MR\u0016\u0010N\u001a\u00020/8D@\u0004X\u0084\u0004\u00a2\u0006\u0006\u001a\u0004\bN\u0010MR\u0016\u0010O\u001a\u00020/8$@$X\u00a4\u0004\u00a2\u0006\u0006\u001a\u0004\bO\u0010MR\u001c\u0010Q\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u00038D@\u0004X\u0084\u0004\u00a2\u0006\u0006\u001a\u0004\bP\u0010JR\u0016\u0010R\u001a\u00020/8$@$X\u00a4\u0004\u00a2\u0006\u0006\u001a\u0004\bR\u0010MR\u0016\u0010S\u001a\u00020/8V@\u0016X\u0096\u0004\u00a2\u0006\u0006\u001a\u0004\bS\u0010MR\u001c\u0010U\u001a\u00020T8\u0004@\u0004X\u0084\u0004\u00a2\u0006\f\n\u0004\bU\u0010V\u001a\u0004\bW\u0010XR%\u0010\\\u001a\u0014\u0012\u0004\u0012\u00028\u0000\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u00020Y8F@\u0006\u00a2\u0006\u0006\u001a\u0004\bZ\u0010[R\u0016\u0010^\u001a\u00020D8T@\u0014X\u0094\u0004\u00a2\u0006\u0006\u001a\u0004\b]\u0010F\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006e"}, d2 = {"Lkotlinx/coroutines/channels/AbstractSendChannel;", "E", "Lkotlinx/coroutines/channels/SendChannel;", "Lkotlinx/coroutines/channels/Closed;", JshopConst.JSKEY_SHOP_CLOSED, "", "helpCloseAndGetSendException", "(Lkotlinx/coroutines/channels/Closed;)Ljava/lang/Throwable;", "Lkotlin/coroutines/Continuation;", "", "helpCloseAndResumeWithSendException", "(Lkotlin/coroutines/Continuation;Lkotlinx/coroutines/channels/Closed;)V", "cause", "invokeOnCloseHandler", "(Ljava/lang/Throwable;)V", "helpClose", "(Lkotlinx/coroutines/channels/Closed;)V", "R", "Lkotlinx/coroutines/selects/SelectInstance;", "select", "element", "Lkotlin/Function2;", "", JDReactConstant.BLOCK, "registerSelectSend", "(Lkotlinx/coroutines/selects/SelectInstance;Ljava/lang/Object;Lkotlin/jvm/functions/Function2;)V", "", "countQueueSize", "()I", "offerInternal", "(Ljava/lang/Object;)Ljava/lang/Object;", "offerSelectInternal", "(Ljava/lang/Object;Lkotlinx/coroutines/selects/SelectInstance;)Ljava/lang/Object;", "Lkotlinx/coroutines/channels/Send;", "takeFirstSendOrPeekClosed", "()Lkotlinx/coroutines/channels/Send;", "Lkotlinx/coroutines/channels/ReceiveOrClosed;", "sendBuffered", "(Ljava/lang/Object;)Lkotlinx/coroutines/channels/ReceiveOrClosed;", "Lkotlinx/coroutines/internal/LockFreeLinkedListNode$AddLastDesc;", "Lkotlinx/coroutines/internal/AddLastDesc;", "describeSendBuffered", "(Ljava/lang/Object;)Lkotlinx/coroutines/internal/LockFreeLinkedListNode$AddLastDesc;", "send", "(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "sendFair$kotlinx_coroutines_core", "sendFair", "", "offer", "(Ljava/lang/Object;)Z", "sendSuspend", "enqueueSend", "(Lkotlinx/coroutines/channels/Send;)Ljava/lang/Object;", "close", "(Ljava/lang/Throwable;)Z", "Lkotlin/Function1;", "Lkotlinx/coroutines/channels/Handler;", "handler", "invokeOnClose", "(Lkotlin/jvm/functions/Function1;)V", "Lkotlinx/coroutines/internal/LockFreeLinkedListNode;", "onClosedIdempotent", "(Lkotlinx/coroutines/internal/LockFreeLinkedListNode;)V", "takeFirstReceiveOrPeekClosed", "()Lkotlinx/coroutines/channels/ReceiveOrClosed;", "Lkotlinx/coroutines/channels/AbstractSendChannel$TryOfferDesc;", "describeTryOffer", "(Ljava/lang/Object;)Lkotlinx/coroutines/channels/AbstractSendChannel$TryOfferDesc;", "", "toString", "()Ljava/lang/String;", "getQueueDebugStateString", "queueDebugStateString", "getClosedForSend", "()Lkotlinx/coroutines/channels/Closed;", "closedForSend", "isClosedForSend", "()Z", "isFullImpl", "isBufferAlwaysFull", "getClosedForReceive", "closedForReceive", "isBufferFull", JDReactConstant.TPYE_FLAG, "Lkotlinx/coroutines/internal/LockFreeLinkedListHead;", "queue", "Lkotlinx/coroutines/internal/LockFreeLinkedListHead;", "getQueue", "()Lkotlinx/coroutines/internal/LockFreeLinkedListHead;", "Lkotlinx/coroutines/selects/SelectClause2;", "getOnSend", "()Lkotlinx/coroutines/selects/SelectClause2;", "onSend", "getBufferDebugString", "bufferDebugString", "<init>", "()V", "SendBuffered", "SendBufferedDesc", "SendSelect", "TryOfferDesc", "kotlinx-coroutines-core"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes.dex */
public abstract class AbstractSendChannel<E> implements SendChannel<E> {
    private static final AtomicReferenceFieldUpdater onCloseHandler$FU = AtomicReferenceFieldUpdater.newUpdater(AbstractSendChannel.class, Object.class, "onCloseHandler");
    @NotNull
    private final LockFreeLinkedListHead queue = new LockFreeLinkedListHead();
    private volatile Object onCloseHandler = null;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\b\b\u0000\u0018\u0000*\u0006\b\u0001\u0010\u0001 \u00012\u00020\u0002B\u000f\u0012\u0006\u0010\u0016\u001a\u00028\u0001\u00a2\u0006\u0004\b\u0018\u0010\u0019J\u001b\u0010\u0006\u001a\u0004\u0018\u00010\u00052\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003H\u0016\u00a2\u0006\u0004\b\u0006\u0010\u0007J\u000f\u0010\t\u001a\u00020\bH\u0016\u00a2\u0006\u0004\b\t\u0010\nJ\u001b\u0010\r\u001a\u00020\b2\n\u0010\f\u001a\u0006\u0012\u0002\b\u00030\u000bH\u0016\u00a2\u0006\u0004\b\r\u0010\u000eJ\u000f\u0010\u0010\u001a\u00020\u000fH\u0016\u00a2\u0006\u0004\b\u0010\u0010\u0011R\u0018\u0010\u0015\u001a\u0004\u0018\u00010\u00128V@\u0016X\u0096\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0013\u0010\u0014R\u0016\u0010\u0016\u001a\u00028\u00018\u0006@\u0007X\u0087\u0004\u00a2\u0006\u0006\n\u0004\b\u0016\u0010\u0017\u00a8\u0006\u001a"}, d2 = {"Lkotlinx/coroutines/channels/AbstractSendChannel$SendBuffered;", "E", "Lkotlinx/coroutines/channels/Send;", "Lkotlinx/coroutines/internal/LockFreeLinkedListNode$PrepareOp;", "otherOp", "Lkotlinx/coroutines/internal/Symbol;", "tryResumeSend", "(Lkotlinx/coroutines/internal/LockFreeLinkedListNode$PrepareOp;)Lkotlinx/coroutines/internal/Symbol;", "", "completeResumeSend", "()V", "Lkotlinx/coroutines/channels/Closed;", JshopConst.JSKEY_SHOP_CLOSED, "resumeSendClosed", "(Lkotlinx/coroutines/channels/Closed;)V", "", "toString", "()Ljava/lang/String;", "", "getPollResult", "()Ljava/lang/Object;", "pollResult", "element", "Ljava/lang/Object;", "<init>", "(Ljava/lang/Object;)V", "kotlinx-coroutines-core"}, k = 1, mv = {1, 4, 0})
    /* renamed from: kotlinx.coroutines.channels.AbstractSendChannel$SendBuffered  reason: from toString */
    /* loaded from: classes11.dex */
    public static final class SendBuffered@<E> extends Send {
        @JvmField

        /* renamed from: element  reason: from kotlin metadata and from toString */
        public final E SendBuffered@;

        public SendBuffered@(E e2) {
            this.SendBuffered@ = e2;
        }

        @Override // kotlinx.coroutines.channels.Send
        public void completeResumeSend() {
        }

        @Override // kotlinx.coroutines.channels.Send
        @Nullable
        public Object getPollResult() {
            return this.SendBuffered@;
        }

        @Override // kotlinx.coroutines.channels.Send
        public void resumeSendClosed(@NotNull Closed@<?> r1) {
        }

        @Override // kotlinx.coroutines.internal.LockFreeLinkedListNode
        @NotNull
        public String toString() {
            return "SendBuffered@" + DebugStringsKt.getHexAddress(this) + '(' + this.SendBuffered@ + ')';
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

    /* JADX INFO: Access modifiers changed from: private */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0012\u0018\u0000*\u0004\b\u0001\u0010\u00012\u001e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00010\u00030\u0002j\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00010\u0003`\u0004B\u0017\u0012\u0006\u0010\u000b\u001a\u00020\n\u0012\u0006\u0010\f\u001a\u00028\u0001\u00a2\u0006\u0004\b\r\u0010\u000eJ\u0019\u0010\b\u001a\u0004\u0018\u00010\u00072\u0006\u0010\u0006\u001a\u00020\u0005H\u0014\u00a2\u0006\u0004\b\b\u0010\t\u00a8\u0006\u000f"}, d2 = {"Lkotlinx/coroutines/channels/AbstractSendChannel$SendBufferedDesc;", "E", "Lkotlinx/coroutines/internal/LockFreeLinkedListNode$AddLastDesc;", "Lkotlinx/coroutines/channels/AbstractSendChannel$SendBuffered;", "Lkotlinx/coroutines/internal/AddLastDesc;", "Lkotlinx/coroutines/internal/LockFreeLinkedListNode;", "affected", "", "failure", "(Lkotlinx/coroutines/internal/LockFreeLinkedListNode;)Ljava/lang/Object;", "Lkotlinx/coroutines/internal/LockFreeLinkedListHead;", "queue", "element", "<init>", "(Lkotlinx/coroutines/internal/LockFreeLinkedListHead;Ljava/lang/Object;)V", "kotlinx-coroutines-core"}, k = 1, mv = {1, 4, 0})
    /* loaded from: classes11.dex */
    public static class SendBufferedDesc<E> extends LockFreeLinkedListNode.AddLastDesc<SendBuffered@<? extends E>> {
        public SendBufferedDesc(@NotNull LockFreeLinkedListHead lockFreeLinkedListHead, E e2) {
            super(lockFreeLinkedListHead, new SendBuffered@(e2));
        }

        @Override // kotlinx.coroutines.internal.LockFreeLinkedListNode.AbstractAtomicDesc
        @Nullable
        protected Object failure(@NotNull LockFreeLinkedListNode affected) {
            if (affected instanceof Closed@) {
                return affected;
            }
            if (affected instanceof ReceiveOrClosed) {
                return AbstractChannelKt.OFFER_FAILED;
            }
            return null;
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0002\u0018\u0000*\u0004\b\u0001\u0010\u0001*\u0004\b\u0002\u0010\u00022\u00020\u00032\u00020\u0004BZ\u0012\b\u0010\u0016\u001a\u0004\u0018\u00010\u0015\u0012\f\u0010\u001b\u001a\b\u0012\u0004\u0012\u00028\u00010\u001a\u0012\f\u0010#\u001a\b\u0012\u0004\u0012\u00028\u00020\"\u0012(\u0010 \u001a$\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00010\u001e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00020\u001f\u0012\u0006\u0012\u0004\u0018\u00010\u00150\u001d\u00f8\u0001\u0000\u00a2\u0006\u0004\b%\u0010&J\u001b\u0010\b\u001a\u0004\u0018\u00010\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005H\u0016\u00a2\u0006\u0004\b\b\u0010\tJ\u000f\u0010\u000b\u001a\u00020\nH\u0016\u00a2\u0006\u0004\b\u000b\u0010\fJ\u000f\u0010\r\u001a\u00020\nH\u0016\u00a2\u0006\u0004\b\r\u0010\fJ\u001b\u0010\u0010\u001a\u00020\n2\n\u0010\u000f\u001a\u0006\u0012\u0002\b\u00030\u000eH\u0016\u00a2\u0006\u0004\b\u0010\u0010\u0011J\u000f\u0010\u0013\u001a\u00020\u0012H\u0016\u00a2\u0006\u0004\b\u0013\u0010\u0014R\u001e\u0010\u0016\u001a\u0004\u0018\u00010\u00158\u0016@\u0016X\u0096\u0004\u00a2\u0006\f\n\u0004\b\u0016\u0010\u0017\u001a\u0004\b\u0018\u0010\u0019R\u001c\u0010\u001b\u001a\b\u0012\u0004\u0012\u00028\u00010\u001a8\u0006@\u0007X\u0087\u0004\u00a2\u0006\u0006\n\u0004\b\u001b\u0010\u001cR;\u0010 \u001a$\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00010\u001e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00020\u001f\u0012\u0006\u0012\u0004\u0018\u00010\u00150\u001d8\u0006@\u0007X\u0087\u0004\u00f8\u0001\u0000\u00a2\u0006\u0006\n\u0004\b \u0010!R\u001c\u0010#\u001a\b\u0012\u0004\u0012\u00028\u00020\"8\u0006@\u0007X\u0087\u0004\u00a2\u0006\u0006\n\u0004\b#\u0010$\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006'"}, d2 = {"Lkotlinx/coroutines/channels/AbstractSendChannel$SendSelect;", "E", "R", "Lkotlinx/coroutines/channels/Send;", "Lkotlinx/coroutines/DisposableHandle;", "Lkotlinx/coroutines/internal/LockFreeLinkedListNode$PrepareOp;", "otherOp", "Lkotlinx/coroutines/internal/Symbol;", "tryResumeSend", "(Lkotlinx/coroutines/internal/LockFreeLinkedListNode$PrepareOp;)Lkotlinx/coroutines/internal/Symbol;", "", "completeResumeSend", "()V", "dispose", "Lkotlinx/coroutines/channels/Closed;", JshopConst.JSKEY_SHOP_CLOSED, "resumeSendClosed", "(Lkotlinx/coroutines/channels/Closed;)V", "", "toString", "()Ljava/lang/String;", "", "pollResult", "Ljava/lang/Object;", "getPollResult", "()Ljava/lang/Object;", "Lkotlinx/coroutines/channels/AbstractSendChannel;", "channel", "Lkotlinx/coroutines/channels/AbstractSendChannel;", "Lkotlin/Function2;", "Lkotlinx/coroutines/channels/SendChannel;", "Lkotlin/coroutines/Continuation;", JDReactConstant.BLOCK, "Lkotlin/jvm/functions/Function2;", "Lkotlinx/coroutines/selects/SelectInstance;", "select", "Lkotlinx/coroutines/selects/SelectInstance;", "<init>", "(Ljava/lang/Object;Lkotlinx/coroutines/channels/AbstractSendChannel;Lkotlinx/coroutines/selects/SelectInstance;Lkotlin/jvm/functions/Function2;)V", "kotlinx-coroutines-core"}, k = 1, mv = {1, 4, 0})
    /* renamed from: kotlinx.coroutines.channels.AbstractSendChannel$SendSelect  reason: from toString */
    /* loaded from: classes11.dex */
    public static final class SendSelect@<E, R> extends Send implements DisposableHandle {
        @JvmField
        @NotNull
        public final Function2<SendChannel<? super E>, Continuation<? super R>, Object> block;
        @JvmField
        @NotNull

        /* renamed from: channel  reason: from kotlin metadata and from toString */
        public final AbstractSendChannel<E> )[;
        @Nullable
        private final Object pollResult;
        @JvmField
        @NotNull

        /* renamed from: select  reason: from kotlin metadata and from toString */
        public final SelectInstance<R> ;

        /* JADX WARN: Multi-variable type inference failed */
        public SendSelect@(@Nullable Object obj, @NotNull AbstractSendChannel<E> abstractSendChannel, @NotNull SelectInstance<? super R> selectInstance, @NotNull Function2<? super SendChannel<? super E>, ? super Continuation<? super R>, ? extends Object> function2) {
            this.pollResult = obj;
            this.)[ = abstractSendChannel;
            this. = selectInstance;
            this.block = function2;
        }

        @Override // kotlinx.coroutines.channels.Send
        public void completeResumeSend() {
            ContinuationKt.startCoroutine(this.block, this.)[, this..getCompletion());
        }

        @Override // kotlinx.coroutines.DisposableHandle
        public void dispose() {
            remove();
        }

        @Override // kotlinx.coroutines.channels.Send
        @Nullable
        public Object getPollResult() {
            return this.pollResult;
        }

        @Override // kotlinx.coroutines.channels.Send
        public void resumeSendClosed(@NotNull Closed@<?> r2) {
            if (this..trySelect()) {
                this..resumeSelectWithException(r2.getSendException());
            }
        }

        @Override // kotlinx.coroutines.internal.LockFreeLinkedListNode
        @NotNull
        public String toString() {
            return "SendSelect@" + DebugStringsKt.getHexAddress(this) + '(' + getPollResult() + ")[" + this.)[ + ", " + this. + ']';
        }

        @Override // kotlinx.coroutines.channels.Send
        @Nullable
        public Symbol tryResumeSend(@Nullable LockFreeLinkedListNode.PrepareOp otherOp) {
            return (Symbol) this..trySelectOther(otherOp);
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0004\u0018\u0000*\u0004\b\u0001\u0010\u00012\u001e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00010\u00030\u0002j\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00010\u0003`\u0004B\u0017\u0012\u0006\u0010\u000f\u001a\u00028\u0001\u0012\u0006\u0010\u0012\u001a\u00020\u0011\u00a2\u0006\u0004\b\u0013\u0010\u0014J\u0019\u0010\b\u001a\u0004\u0018\u00010\u00072\u0006\u0010\u0006\u001a\u00020\u0005H\u0014\u00a2\u0006\u0004\b\b\u0010\tJ\u001d\u0010\r\u001a\u0004\u0018\u00010\u00072\n\u0010\f\u001a\u00060\nj\u0002`\u000bH\u0016\u00a2\u0006\u0004\b\r\u0010\u000eR\u0016\u0010\u000f\u001a\u00028\u00018\u0006@\u0007X\u0087\u0004\u00a2\u0006\u0006\n\u0004\b\u000f\u0010\u0010\u00a8\u0006\u0015"}, d2 = {"Lkotlinx/coroutines/channels/AbstractSendChannel$TryOfferDesc;", "E", "Lkotlinx/coroutines/internal/LockFreeLinkedListNode$RemoveFirstDesc;", "Lkotlinx/coroutines/channels/ReceiveOrClosed;", "Lkotlinx/coroutines/internal/RemoveFirstDesc;", "Lkotlinx/coroutines/internal/LockFreeLinkedListNode;", "affected", "", "failure", "(Lkotlinx/coroutines/internal/LockFreeLinkedListNode;)Ljava/lang/Object;", "Lkotlinx/coroutines/internal/LockFreeLinkedListNode$PrepareOp;", "Lkotlinx/coroutines/internal/PrepareOp;", "prepareOp", "onPrepare", "(Lkotlinx/coroutines/internal/LockFreeLinkedListNode$PrepareOp;)Ljava/lang/Object;", "element", "Ljava/lang/Object;", "Lkotlinx/coroutines/internal/LockFreeLinkedListHead;", "queue", "<init>", "(Ljava/lang/Object;Lkotlinx/coroutines/internal/LockFreeLinkedListHead;)V", "kotlinx-coroutines-core"}, k = 1, mv = {1, 4, 0})
    /* loaded from: classes11.dex */
    public static final class TryOfferDesc<E> extends LockFreeLinkedListNode.RemoveFirstDesc<ReceiveOrClosed<? super E>> {
        @JvmField
        public final E element;

        public TryOfferDesc(E e2, @NotNull LockFreeLinkedListHead lockFreeLinkedListHead) {
            super(lockFreeLinkedListHead);
            this.element = e2;
        }

        @Override // kotlinx.coroutines.internal.LockFreeLinkedListNode.RemoveFirstDesc, kotlinx.coroutines.internal.LockFreeLinkedListNode.AbstractAtomicDesc
        @Nullable
        protected Object failure(@NotNull LockFreeLinkedListNode affected) {
            if (affected instanceof Closed@) {
                return affected;
            }
            if (affected instanceof ReceiveOrClosed) {
                return null;
            }
            return AbstractChannelKt.OFFER_FAILED;
        }

        @Override // kotlinx.coroutines.internal.LockFreeLinkedListNode.AbstractAtomicDesc
        @Nullable
        public Object onPrepare(@NotNull LockFreeLinkedListNode.PrepareOp prepareOp) {
            LockFreeLinkedListNode lockFreeLinkedListNode = prepareOp.affected;
            if (lockFreeLinkedListNode != null) {
                Symbol tryResumeReceive = ((ReceiveOrClosed) lockFreeLinkedListNode).tryResumeReceive(this.element, prepareOp);
                if (tryResumeReceive != null) {
                    Object obj = AtomicKt.RETRY_ATOMIC;
                    if (tryResumeReceive == obj) {
                        return obj;
                    }
                    if (DebugKt.getASSERTIONS_ENABLED()) {
                        if (tryResumeReceive == CancellableContinuationImplKt.RESUME_TOKEN) {
                            return null;
                        }
                        throw new AssertionError();
                    }
                    return null;
                }
                return LockFreeLinkedList_commonKt.REMOVE_PREPARED;
            }
            throw new TypeCastException("null cannot be cast to non-null type kotlinx.coroutines.channels.ReceiveOrClosed<E>");
        }
    }

    private final int countQueueSize() {
        Object next = this.queue.getNext();
        if (next != null) {
            int i2 = 0;
            for (LockFreeLinkedListNode lockFreeLinkedListNode = (LockFreeLinkedListNode) next; (!Intrinsics.areEqual(lockFreeLinkedListNode, r0)) != false; lockFreeLinkedListNode = lockFreeLinkedListNode.getNextNode()) {
                if (lockFreeLinkedListNode instanceof LockFreeLinkedListNode) {
                    i2++;
                }
            }
            return i2;
        }
        throw new TypeCastException("null cannot be cast to non-null type kotlinx.coroutines.internal.Node /* = kotlinx.coroutines.internal.LockFreeLinkedListNode */");
    }

    private final String getQueueDebugStateString() {
        String str;
        LockFreeLinkedListNode nextNode = this.queue.getNextNode();
        if (nextNode == this.queue) {
            return "EmptyQueue";
        }
        if (nextNode instanceof Closed@) {
            str = nextNode.toString();
        } else if (nextNode instanceof Receive) {
            str = "ReceiveQueued";
        } else if (nextNode instanceof Send) {
            str = "SendQueued";
        } else {
            str = "UNEXPECTED:" + nextNode;
        }
        LockFreeLinkedListNode prevNode = this.queue.getPrevNode();
        if (prevNode != nextNode) {
            String str2 = str + ",queueSize=" + countQueueSize();
            if (prevNode instanceof Closed@) {
                return str2 + ",closedForSend=" + prevNode;
            }
            return str2;
        }
        return str;
    }

    private final void helpClose(Closed@<?> r6) {
        Object m1246constructorimpl$default = InlineList.m1246constructorimpl$default(null, 1, null);
        while (true) {
            LockFreeLinkedListNode prevNode = r6.getPrevNode();
            if (!(prevNode instanceof Receive)) {
                prevNode = null;
            }
            Receive receive = (Receive) prevNode;
            if (receive == null) {
                break;
            } else if (!receive.remove()) {
                receive.helpRemove();
            } else {
                m1246constructorimpl$default = InlineList.m1251plusimpl(m1246constructorimpl$default, receive);
            }
        }
        if (m1246constructorimpl$default != null) {
            if (!(m1246constructorimpl$default instanceof ArrayList)) {
                ((Receive) m1246constructorimpl$default).resumeReceiveClosed(r6);
            } else if (m1246constructorimpl$default != null) {
                ArrayList arrayList = (ArrayList) m1246constructorimpl$default;
                for (int size = arrayList.size() - 1; size >= 0; size--) {
                    ((Receive) arrayList.get(size)).resumeReceiveClosed(r6);
                }
            } else {
                throw new TypeCastException("null cannot be cast to non-null type kotlin.collections.ArrayList<E> /* = java.util.ArrayList<E> */");
            }
        }
        onClosedIdempotent(r6);
    }

    private final Throwable helpCloseAndGetSendException(Closed@<?> r1) {
        helpClose(r1);
        return r1.getSendException();
    }

    public final void helpCloseAndResumeWithSendException(@NotNull Continuation<?> continuation, Closed@<?> r3) {
        helpClose(r3);
        Throwable sendException = r3.getSendException();
        Result.Companion companion = Result.INSTANCE;
        continuation.resumeWith(Result.m200constructorimpl(ResultKt.createFailure(sendException)));
    }

    private final void invokeOnCloseHandler(Throwable cause) {
        Object obj;
        Object obj2 = this.onCloseHandler;
        if (obj2 == null || obj2 == (obj = AbstractChannelKt.HANDLER_INVOKED) || !onCloseHandler$FU.compareAndSet(this, obj2, obj)) {
            return;
        }
        ((Function1) TypeIntrinsics.beforeCheckcastToFunctionOfArity(obj2, 1)).invoke(cause);
    }

    public final <R> void registerSelectSend(SelectInstance<? super R> select, E element, Function2<? super SendChannel<? super E>, ? super Continuation<? super R>, ? extends Object> r5) {
        while (!select.isSelected()) {
            if (isFullImpl()) {
                SendSelect@ r0 = new SendSelect@(element, this, select, r5);
                Object enqueueSend = enqueueSend(r0);
                if (enqueueSend == null) {
                    select.disposeOnSelect(r0);
                    return;
                } else if (!(enqueueSend instanceof Closed@)) {
                    if (enqueueSend != AbstractChannelKt.ENQUEUE_FAILED && !(enqueueSend instanceof Receive)) {
                        throw new IllegalStateException(("enqueueSend returned " + enqueueSend + ' ').toString());
                    }
                } else {
                    throw StackTraceRecoveryKt.recoverStackTrace(helpCloseAndGetSendException((Closed@) enqueueSend));
                }
            }
            Object offerSelectInternal = offerSelectInternal(element, select);
            if (offerSelectInternal == SelectKt.getALREADY_SELECTED()) {
                return;
            }
            if (offerSelectInternal != AbstractChannelKt.OFFER_FAILED && offerSelectInternal != AtomicKt.RETRY_ATOMIC) {
                if (offerSelectInternal == AbstractChannelKt.OFFER_SUCCESS) {
                    UndispatchedKt.startCoroutineUnintercepted(r5, this, select.getCompletion());
                    return;
                } else if (offerSelectInternal instanceof Closed@) {
                    throw StackTraceRecoveryKt.recoverStackTrace(helpCloseAndGetSendException((Closed@) offerSelectInternal));
                } else {
                    throw new IllegalStateException(("offerSelectInternal returned " + offerSelectInternal).toString());
                }
            }
        }
    }

    @Override // kotlinx.coroutines.channels.SendChannel
    /* renamed from: close */
    public boolean cancel(@Nullable Throwable cause) {
        boolean z;
        Closed@<?> r0 = new Closed@<>(cause);
        LockFreeLinkedListNode lockFreeLinkedListNode = this.queue;
        while (true) {
            LockFreeLinkedListNode prevNode = lockFreeLinkedListNode.getPrevNode();
            z = true;
            if ((!(prevNode instanceof Closed@)) != true) {
                z = false;
                break;
            } else if (prevNode.addNext(r0, lockFreeLinkedListNode)) {
                break;
            }
        }
        if (!z) {
            LockFreeLinkedListNode prevNode2 = this.queue.getPrevNode();
            if (prevNode2 == null) {
                throw new TypeCastException("null cannot be cast to non-null type kotlinx.coroutines.channels.Closed<*>");
            }
            r0 = (Closed@) prevNode2;
        }
        helpClose(r0);
        if (z) {
            invokeOnCloseHandler(cause);
        }
        return z;
    }

    @NotNull
    public final LockFreeLinkedListNode.AddLastDesc<?> describeSendBuffered(E element) {
        return new SendBufferedDesc(this.queue, element);
    }

    @NotNull
    public final TryOfferDesc<E> describeTryOffer(E element) {
        return new TryOfferDesc<>(element, this.queue);
    }

    @Nullable
    public Object enqueueSend(@NotNull final Send send) {
        boolean z;
        LockFreeLinkedListNode prevNode;
        if (isBufferAlwaysFull()) {
            LockFreeLinkedListNode lockFreeLinkedListNode = this.queue;
            do {
                prevNode = lockFreeLinkedListNode.getPrevNode();
                if (prevNode instanceof ReceiveOrClosed) {
                    return prevNode;
                }
            } while (!prevNode.addNext(send, lockFreeLinkedListNode));
            return null;
        }
        LockFreeLinkedListNode lockFreeLinkedListNode2 = this.queue;
        LockFreeLinkedListNode.CondAddOp condAddOp = new LockFreeLinkedListNode.CondAddOp(send) { // from class: kotlinx.coroutines.channels.AbstractSendChannel$enqueueSend$$inlined$addLastIfPrevAndIf$1
            @Override // kotlinx.coroutines.internal.AtomicOp
            @Nullable
            public Object prepare(@NotNull LockFreeLinkedListNode lockFreeLinkedListNode3) {
                if (this.isBufferFull()) {
                    return null;
                }
                return LockFreeLinkedListKt.getCONDITION_FALSE();
            }
        };
        while (true) {
            LockFreeLinkedListNode prevNode2 = lockFreeLinkedListNode2.getPrevNode();
            if (!(prevNode2 instanceof ReceiveOrClosed)) {
                int tryCondAddNext = prevNode2.tryCondAddNext(send, lockFreeLinkedListNode2, condAddOp);
                z = true;
                if (tryCondAddNext != 1) {
                    if (tryCondAddNext == 2) {
                        z = false;
                        break;
                    }
                } else {
                    break;
                }
            } else {
                return prevNode2;
            }
        }
        if (z) {
            return null;
        }
        return AbstractChannelKt.ENQUEUE_FAILED;
    }

    @NotNull
    protected String getBufferDebugString() {
        return "";
    }

    @Nullable
    public final Closed@<?> getClosedForReceive() {
        LockFreeLinkedListNode nextNode = this.queue.getNextNode();
        if (!(nextNode instanceof Closed@)) {
            nextNode = null;
        }
        Closed@<?> r0 = (Closed@) nextNode;
        if (r0 != null) {
            helpClose(r0);
            return r0;
        }
        return null;
    }

    @Nullable
    public final Closed@<?> getClosedForSend() {
        LockFreeLinkedListNode prevNode = this.queue.getPrevNode();
        if (!(prevNode instanceof Closed@)) {
            prevNode = null;
        }
        Closed@<?> r0 = (Closed@) prevNode;
        if (r0 != null) {
            helpClose(r0);
            return r0;
        }
        return null;
    }

    @Override // kotlinx.coroutines.channels.SendChannel
    @NotNull
    public final SelectClause2<E, SendChannel<E>> getOnSend() {
        return (SelectClause2<E, SendChannel<? super E>>) new SelectClause2<E, SendChannel<? super E>>() { // from class: kotlinx.coroutines.channels.AbstractSendChannel$onSend$1
            {
                AbstractSendChannel.this = this;
            }

            @Override // kotlinx.coroutines.selects.SelectClause2
            public <R> void registerSelectClause2(@NotNull SelectInstance<? super R> select, E param, @NotNull Function2<? super SendChannel<? super E>, ? super Continuation<? super R>, ? extends Object> r4) {
                AbstractSendChannel.this.registerSelectSend(select, param, r4);
            }
        };
    }

    @NotNull
    public final LockFreeLinkedListHead getQueue() {
        return this.queue;
    }

    @Override // kotlinx.coroutines.channels.SendChannel
    public void invokeOnClose(@NotNull Function1<? super Throwable, Unit> handler) {
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = onCloseHandler$FU;
        if (!atomicReferenceFieldUpdater.compareAndSet(this, null, handler)) {
            Object obj = this.onCloseHandler;
            if (obj == AbstractChannelKt.HANDLER_INVOKED) {
                throw new IllegalStateException("Another handler was already registered and successfully invoked");
            }
            throw new IllegalStateException("Another handler was already registered: " + obj);
        }
        Closed@<?> closedForSend = getClosedForSend();
        if (closedForSend == null || !atomicReferenceFieldUpdater.compareAndSet(this, handler, AbstractChannelKt.HANDLER_INVOKED)) {
            return;
        }
        handler.invoke(closedForSend.Closed@);
    }

    protected abstract boolean isBufferAlwaysFull();

    public abstract boolean isBufferFull();

    @Override // kotlinx.coroutines.channels.SendChannel
    public final boolean isClosedForSend() {
        return getClosedForSend() != null;
    }

    @Override // kotlinx.coroutines.channels.SendChannel
    public boolean isFull() {
        return isFullImpl();
    }

    public final boolean isFullImpl() {
        return !(this.queue.getNextNode() instanceof ReceiveOrClosed) && isBufferFull();
    }

    @Override // kotlinx.coroutines.channels.SendChannel
    public final boolean offer(E element) {
        Object offerInternal = offerInternal(element);
        if (offerInternal == AbstractChannelKt.OFFER_SUCCESS) {
            return true;
        }
        if (offerInternal == AbstractChannelKt.OFFER_FAILED) {
            Closed@<?> closedForSend = getClosedForSend();
            if (closedForSend == null) {
                return false;
            }
            throw StackTraceRecoveryKt.recoverStackTrace(helpCloseAndGetSendException(closedForSend));
        } else if (offerInternal instanceof Closed@) {
            throw StackTraceRecoveryKt.recoverStackTrace(helpCloseAndGetSendException((Closed@) offerInternal));
        } else {
            throw new IllegalStateException(("offerInternal returned " + offerInternal).toString());
        }
    }

    @NotNull
    public Object offerInternal(E element) {
        ReceiveOrClosed<E> takeFirstReceiveOrPeekClosed;
        Symbol tryResumeReceive;
        do {
            takeFirstReceiveOrPeekClosed = takeFirstReceiveOrPeekClosed();
            if (takeFirstReceiveOrPeekClosed != null) {
                tryResumeReceive = takeFirstReceiveOrPeekClosed.tryResumeReceive(element, null);
            } else {
                return AbstractChannelKt.OFFER_FAILED;
            }
        } while (tryResumeReceive == null);
        if (DebugKt.getASSERTIONS_ENABLED()) {
            if (!(tryResumeReceive == CancellableContinuationImplKt.RESUME_TOKEN)) {
                throw new AssertionError();
            }
        }
        takeFirstReceiveOrPeekClosed.completeResumeReceive(element);
        return takeFirstReceiveOrPeekClosed.getOfferResult();
    }

    @NotNull
    public Object offerSelectInternal(E element, @NotNull SelectInstance<?> select) {
        TryOfferDesc<E> describeTryOffer = describeTryOffer(element);
        Object performAtomicTrySelect = select.performAtomicTrySelect(describeTryOffer);
        if (performAtomicTrySelect != null) {
            return performAtomicTrySelect;
        }
        ReceiveOrClosed<? super E> result = describeTryOffer.getResult();
        result.completeResumeReceive(element);
        return result.getOfferResult();
    }

    protected void onClosedIdempotent(@NotNull LockFreeLinkedListNode r1) {
    }

    @Override // kotlinx.coroutines.channels.SendChannel
    @Nullable
    public final Object send(E e2, @NotNull Continuation<? super Unit> continuation) {
        Object coroutine_suspended;
        if (offerInternal(e2) == AbstractChannelKt.OFFER_SUCCESS) {
            return Unit.INSTANCE;
        }
        Object sendSuspend = sendSuspend(e2, continuation);
        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        return sendSuspend == coroutine_suspended ? sendSuspend : Unit.INSTANCE;
    }

    @Nullable
    public final ReceiveOrClosed<?> sendBuffered(E element) {
        LockFreeLinkedListNode prevNode;
        LockFreeLinkedListNode lockFreeLinkedListNode = this.queue;
        SendBuffered@ r1 = new SendBuffered@(element);
        do {
            prevNode = lockFreeLinkedListNode.getPrevNode();
            if (prevNode instanceof ReceiveOrClosed) {
                return (ReceiveOrClosed) prevNode;
            }
        } while (!prevNode.addNext(r1, lockFreeLinkedListNode));
        return null;
    }

    @Nullable
    public final Object sendFair$kotlinx_coroutines_core(E e2, @NotNull Continuation<? super Unit> continuation) {
        Object coroutine_suspended;
        Object coroutine_suspended2;
        if (offerInternal(e2) == AbstractChannelKt.OFFER_SUCCESS) {
            Object yield = YieldKt.yield(continuation);
            coroutine_suspended2 = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            return yield == coroutine_suspended2 ? yield : Unit.INSTANCE;
        }
        Object sendSuspend = sendSuspend(e2, continuation);
        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        return sendSuspend == coroutine_suspended ? sendSuspend : Unit.INSTANCE;
    }

    @Nullable
    final /* synthetic */ Object sendSuspend(E e2, @NotNull Continuation<? super Unit> continuation) {
        Continuation intercepted;
        Object coroutine_suspended;
        intercepted = IntrinsicsKt__IntrinsicsJvmKt.intercepted(continuation);
        CancellableContinuationImpl orCreateCancellableContinuation = CancellableContinuationKt.getOrCreateCancellableContinuation(intercepted);
        while (true) {
            if (isFullImpl()) {
                SendElement@ r1 = new SendElement@(e2, orCreateCancellableContinuation);
                Object enqueueSend = enqueueSend(r1);
                if (enqueueSend == null) {
                    CancellableContinuationKt.removeOnCancellation(orCreateCancellableContinuation, r1);
                    break;
                } else if (enqueueSend instanceof Closed@) {
                    helpCloseAndResumeWithSendException(orCreateCancellableContinuation, (Closed@) enqueueSend);
                    break;
                } else if (enqueueSend != AbstractChannelKt.ENQUEUE_FAILED && !(enqueueSend instanceof Receive)) {
                    throw new IllegalStateException(("enqueueSend returned " + enqueueSend).toString());
                }
            }
            Object offerInternal = offerInternal(e2);
            if (offerInternal == AbstractChannelKt.OFFER_SUCCESS) {
                Unit unit = Unit.INSTANCE;
                Result.Companion companion = Result.INSTANCE;
                orCreateCancellableContinuation.resumeWith(Result.m200constructorimpl(unit));
                break;
            } else if (offerInternal != AbstractChannelKt.OFFER_FAILED) {
                if (offerInternal instanceof Closed@) {
                    helpCloseAndResumeWithSendException(orCreateCancellableContinuation, (Closed@) offerInternal);
                } else {
                    throw new IllegalStateException(("offerInternal returned " + offerInternal).toString());
                }
            }
        }
        Object result = orCreateCancellableContinuation.getResult();
        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (result == coroutine_suspended) {
            DebugProbes.probeCoroutineSuspended(continuation);
        }
        return result;
    }

    /* JADX WARN: Code restructure failed: missing block: B:36:0x000d, code lost:
        r1 = null;
     */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public kotlinx.coroutines.channels.ReceiveOrClosed<E> takeFirstReceiveOrPeekClosed() {
        /*
            r4 = this;
            kotlinx.coroutines.internal.LockFreeLinkedListHead r0 = r4.queue
        L2:
            java.lang.Object r1 = r0.getNext()
            if (r1 == 0) goto L2f
            kotlinx.coroutines.internal.LockFreeLinkedListNode r1 = (kotlinx.coroutines.internal.LockFreeLinkedListNode) r1
            r2 = 0
            if (r1 != r0) goto Lf
        Ld:
            r1 = r2
            goto L28
        Lf:
            boolean r3 = r1 instanceof kotlinx.coroutines.channels.ReceiveOrClosed
            if (r3 != 0) goto L14
            goto Ld
        L14:
            r2 = r1
            kotlinx.coroutines.channels.ReceiveOrClosed r2 = (kotlinx.coroutines.channels.ReceiveOrClosed) r2
            boolean r2 = r2 instanceof kotlinx.coroutines.channels.Closed@
            if (r2 == 0) goto L22
            boolean r2 = r1.isRemoved()
            if (r2 != 0) goto L22
            goto L28
        L22:
            kotlinx.coroutines.internal.LockFreeLinkedListNode r2 = r1.removeOrNext()
            if (r2 != 0) goto L2b
        L28:
            kotlinx.coroutines.channels.ReceiveOrClosed r1 = (kotlinx.coroutines.channels.ReceiveOrClosed) r1
            return r1
        L2b:
            r2.helpRemovePrev()
            goto L2
        L2f:
            kotlin.TypeCastException r0 = new kotlin.TypeCastException
        */
        //  java.lang.String r1 = "null cannot be cast to non-null type kotlinx.coroutines.internal.Node /* = kotlinx.coroutines.internal.LockFreeLinkedListNode */"
        /*
            r0.<init>(r1)
            goto L38
        L37:
            throw r0
        L38:
            goto L37
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.AbstractSendChannel.takeFirstReceiveOrPeekClosed():kotlinx.coroutines.channels.ReceiveOrClosed");
    }

    /* JADX WARN: Code restructure failed: missing block: B:36:0x000d, code lost:
        r1 = null;
     */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final kotlinx.coroutines.channels.Send takeFirstSendOrPeekClosed() {
        /*
            r4 = this;
            kotlinx.coroutines.internal.LockFreeLinkedListHead r0 = r4.queue
        L2:
            java.lang.Object r1 = r0.getNext()
            if (r1 == 0) goto L2f
            kotlinx.coroutines.internal.LockFreeLinkedListNode r1 = (kotlinx.coroutines.internal.LockFreeLinkedListNode) r1
            r2 = 0
            if (r1 != r0) goto Lf
        Ld:
            r1 = r2
            goto L28
        Lf:
            boolean r3 = r1 instanceof kotlinx.coroutines.channels.Send
            if (r3 != 0) goto L14
            goto Ld
        L14:
            r2 = r1
            kotlinx.coroutines.channels.Send r2 = (kotlinx.coroutines.channels.Send) r2
            boolean r2 = r2 instanceof kotlinx.coroutines.channels.Closed@
            if (r2 == 0) goto L22
            boolean r2 = r1.isRemoved()
            if (r2 != 0) goto L22
            goto L28
        L22:
            kotlinx.coroutines.internal.LockFreeLinkedListNode r2 = r1.removeOrNext()
            if (r2 != 0) goto L2b
        L28:
            kotlinx.coroutines.channels.Send r1 = (kotlinx.coroutines.channels.Send) r1
            return r1
        L2b:
            r2.helpRemovePrev()
            goto L2
        L2f:
            kotlin.TypeCastException r0 = new kotlin.TypeCastException
        */
        //  java.lang.String r1 = "null cannot be cast to non-null type kotlinx.coroutines.internal.Node /* = kotlinx.coroutines.internal.LockFreeLinkedListNode */"
        /*
            r0.<init>(r1)
            goto L38
        L37:
            throw r0
        L38:
            goto L37
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.AbstractSendChannel.takeFirstSendOrPeekClosed():kotlinx.coroutines.channels.Send");
    }

    @NotNull
    public String toString() {
        return DebugStringsKt.getClassSimpleName(this) + '@' + DebugStringsKt.getHexAddress(this) + '{' + getQueueDebugStateString() + '}' + getBufferDebugString();
    }
}

package kotlinx.coroutines.channels;

import com.jingdong.app.mall.bundle.dolphinlib.common.util.EtModelMaker;
import com.jingdong.common.jdreactFramework.JDReactConstant;
import com.jingdong.jdsdk.constant.CartConstant;
import com.jingdong.jdsdk.constant.JshopConst;
import java.util.ArrayList;
import java.util.concurrent.CancellationException;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.ContinuationKt;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsJvmKt;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.CoroutineStackFrame;
import kotlin.coroutines.jvm.internal.DebugProbes;
import kotlin.jvm.JvmField;
import kotlin.jvm.JvmName;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CancelHandler;
import kotlinx.coroutines.CancellableContinuation;
import kotlinx.coroutines.CancellableContinuationImpl;
import kotlinx.coroutines.CancellableContinuationImplKt;
import kotlinx.coroutines.CancellableContinuationKt;
import kotlinx.coroutines.DebugKt;
import kotlinx.coroutines.DebugStringsKt;
import kotlinx.coroutines.DisposableHandle;
import kotlinx.coroutines.channels.ChannelIterator;
import kotlinx.coroutines.channels.ValueOrClosed;
import kotlinx.coroutines.internal.AtomicKt;
import kotlinx.coroutines.internal.InlineList;
import kotlinx.coroutines.internal.LockFreeLinkedListHead;
import kotlinx.coroutines.internal.LockFreeLinkedListKt;
import kotlinx.coroutines.internal.LockFreeLinkedListNode;
import kotlinx.coroutines.internal.LockFreeLinkedList_commonKt;
import kotlinx.coroutines.internal.StackTraceRecoveryKt;
import kotlinx.coroutines.internal.Symbol;
import kotlinx.coroutines.intrinsics.UndispatchedKt;
import kotlinx.coroutines.selects.SelectClause1;
import kotlinx.coroutines.selects.SelectInstance;
import kotlinx.coroutines.selects.SelectKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0086\u0001\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0017\b \u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u00028\u00000\u00022\b\u0012\u0004\u0012\u00028\u00000\u0003:\u0006VWXYZ[B\u0007\u00a2\u0006\u0004\bU\u0010CJ\u001d\u0010\u0007\u001a\u00020\u00062\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004H\u0002\u00a2\u0006\u0004\b\u0007\u0010\bJ\u001b\u0010\u000b\u001a\u0004\u0018\u00018\u00002\b\u0010\n\u001a\u0004\u0018\u00010\tH\u0002\u00a2\u0006\u0004\b\u000b\u0010\fJT\u0010\u0016\u001a\u00020\u0015\"\u0004\b\u0001\u0010\r2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00028\u00010\u000e2\u0006\u0010\u0011\u001a\u00020\u00102$\u0010\u0014\u001a \b\u0001\u0012\u0006\u0012\u0004\u0018\u00010\t\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00010\u0013\u0012\u0006\u0012\u0004\u0018\u00010\t0\u0012H\u0002\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u0016\u0010\u0017JZ\u0010\u0019\u001a\u00020\u0015\"\u0004\b\u0001\u0010\r* \b\u0001\u0012\u0006\u0012\u0004\u0018\u00010\t\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00010\u0013\u0012\u0006\u0012\u0004\u0018\u00010\t0\u00122\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00028\u00010\u000e2\u0006\u0010\u0011\u001a\u00020\u00102\b\u0010\u0018\u001a\u0004\u0018\u00010\tH\u0002\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u0019\u0010\u001aJT\u0010\u001b\u001a\u00020\u0006\"\u0004\b\u0001\u0010\r2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00028\u00010\u000e2$\u0010\u0014\u001a \b\u0001\u0012\u0006\u0012\u0004\u0018\u00010\t\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00010\u0013\u0012\u0006\u0012\u0004\u0018\u00010\t0\u00122\u0006\u0010\u0011\u001a\u00020\u0010H\u0002\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u001b\u0010\u001cJ'\u0010\u001f\u001a\u00020\u00152\n\u0010\u001e\u001a\u0006\u0012\u0002\b\u00030\u001d2\n\u0010\u0005\u001a\u0006\u0012\u0002\b\u00030\u0004H\u0002\u00a2\u0006\u0004\b\u001f\u0010 J\u0011\u0010!\u001a\u0004\u0018\u00010\tH\u0014\u00a2\u0006\u0004\b!\u0010\"J\u001d\u0010#\u001a\u0004\u0018\u00010\t2\n\u0010\u000f\u001a\u0006\u0012\u0002\b\u00030\u000eH\u0014\u00a2\u0006\u0004\b#\u0010$J\u0013\u0010\u0005\u001a\u00028\u0000H\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u0005\u0010%J!\u0010&\u001a\u00028\u0001\"\u0004\b\u0001\u0010\r2\u0006\u0010\u0011\u001a\u00020\u0010H\u0082@\u00f8\u0001\u0000\u00a2\u0006\u0004\b&\u0010'J\u001d\u0010(\u001a\u00020\u00062\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004H\u0014\u00a2\u0006\u0004\b(\u0010\bJ\u0015\u0010)\u001a\u0004\u0018\u00018\u0000H\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0004\b)\u0010%J\u001c\u0010+\u001a\b\u0012\u0004\u0012\u00028\u00000*H\u0086@\u00f8\u0001\u0000\u00f8\u0001\u0000\u00a2\u0006\u0004\b+\u0010%J\u000f\u0010,\u001a\u0004\u0018\u00018\u0000\u00a2\u0006\u0004\b,\u0010\"J\u0019\u0010/\u001a\u00020\u00062\b\u0010.\u001a\u0004\u0018\u00010-H\u0007\u00a2\u0006\u0004\b/\u00100J\u001d\u0010/\u001a\u00020\u00152\u000e\u0010.\u001a\n\u0018\u000101j\u0004\u0018\u0001`2\u00a2\u0006\u0004\b/\u00103J\u0019\u00105\u001a\u00020\u00062\b\u0010.\u001a\u0004\u0018\u00010-H\u0000\u00a2\u0006\u0004\b4\u00100J\u0017\u00107\u001a\u00020\u00152\u0006\u00106\u001a\u00020\u0006H\u0014\u00a2\u0006\u0004\b7\u00108J\u0016\u0010:\u001a\b\u0012\u0004\u0012\u00028\u000009H\u0086\u0002\u00a2\u0006\u0004\b:\u0010;J\u0015\u0010=\u001a\b\u0012\u0004\u0012\u00028\u00000<H\u0004\u00a2\u0006\u0004\b=\u0010>J\u0017\u0010@\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010?H\u0014\u00a2\u0006\u0004\b@\u0010AJ\u000f\u0010B\u001a\u00020\u0015H\u0014\u00a2\u0006\u0004\bB\u0010CJ\u000f\u0010D\u001a\u00020\u0015H\u0014\u00a2\u0006\u0004\bD\u0010CR\"\u0010H\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000*0E8F@\u0006\u00f8\u0001\u0000\u00a2\u0006\u0006\u001a\u0004\bF\u0010GR\u0016\u0010I\u001a\u00020\u00068V@\u0016X\u0096\u0004\u00a2\u0006\u0006\u001a\u0004\bI\u0010JR\u0016\u0010K\u001a\u00020\u00068D@\u0004X\u0084\u0004\u00a2\u0006\u0006\u001a\u0004\bK\u0010JR\u001b\u0010M\u001a\n\u0012\u0006\u0012\u0004\u0018\u00018\u00000E8F@\u0006\u00a2\u0006\u0006\u001a\u0004\bL\u0010GR\u0016\u0010N\u001a\u00020\u00068$@$X\u00a4\u0004\u00a2\u0006\u0006\u001a\u0004\bN\u0010JR\u0019\u0010P\u001a\b\u0012\u0004\u0012\u00028\u00000E8F@\u0006\u00a2\u0006\u0006\u001a\u0004\bO\u0010GR\u0016\u0010Q\u001a\u00020\u00068$@$X\u00a4\u0004\u00a2\u0006\u0006\u001a\u0004\bQ\u0010JR\u0016\u0010R\u001a\u00020\u00068V@\u0016X\u0096\u0004\u00a2\u0006\u0006\u001a\u0004\bR\u0010JR\u0016\u0010T\u001a\u00020\u00068D@\u0004X\u0084\u0004\u00a2\u0006\u0006\u001a\u0004\bS\u0010J\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\\"}, d2 = {"Lkotlinx/coroutines/channels/AbstractChannel;", "E", "Lkotlinx/coroutines/channels/AbstractSendChannel;", "Lkotlinx/coroutines/channels/Channel;", "Lkotlinx/coroutines/channels/Receive;", "receive", "", "enqueueReceive", "(Lkotlinx/coroutines/channels/Receive;)Z", "", "result", "receiveOrNullResult", "(Ljava/lang/Object;)Ljava/lang/Object;", "R", "Lkotlinx/coroutines/selects/SelectInstance;", "select", "", "receiveMode", "Lkotlin/Function2;", "Lkotlin/coroutines/Continuation;", JDReactConstant.BLOCK, "", "registerSelectReceiveMode", "(Lkotlinx/coroutines/selects/SelectInstance;ILkotlin/jvm/functions/Function2;)V", "value", "tryStartBlockUnintercepted", "(Lkotlin/jvm/functions/Function2;Lkotlinx/coroutines/selects/SelectInstance;ILjava/lang/Object;)V", "enqueueReceiveSelect", "(Lkotlinx/coroutines/selects/SelectInstance;Lkotlin/jvm/functions/Function2;I)Z", "Lkotlinx/coroutines/CancellableContinuation;", EtModelMaker.KEY_CONT, "removeReceiveOnCancel", "(Lkotlinx/coroutines/CancellableContinuation;Lkotlinx/coroutines/channels/Receive;)V", "pollInternal", "()Ljava/lang/Object;", "pollSelectInternal", "(Lkotlinx/coroutines/selects/SelectInstance;)Ljava/lang/Object;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "receiveSuspend", "(ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "enqueueReceiveInternal", "receiveOrNull", "Lkotlinx/coroutines/channels/ValueOrClosed;", "receiveOrClosed", "poll", "", "cause", "cancel", "(Ljava/lang/Throwable;)Z", "Ljava/util/concurrent/CancellationException;", "Lkotlinx/coroutines/CancellationException;", "(Ljava/util/concurrent/CancellationException;)V", "cancelInternal$kotlinx_coroutines_core", "cancelInternal", "wasClosed", "onCancelIdempotent", "(Z)V", "Lkotlinx/coroutines/channels/ChannelIterator;", "iterator", "()Lkotlinx/coroutines/channels/ChannelIterator;", "Lkotlinx/coroutines/channels/AbstractChannel$TryPollDesc;", "describeTryPoll", "()Lkotlinx/coroutines/channels/AbstractChannel$TryPollDesc;", "Lkotlinx/coroutines/channels/ReceiveOrClosed;", "takeFirstReceiveOrPeekClosed", "()Lkotlinx/coroutines/channels/ReceiveOrClosed;", "onReceiveEnqueued", "()V", "onReceiveDequeued", "Lkotlinx/coroutines/selects/SelectClause1;", "getOnReceiveOrClosed", "()Lkotlinx/coroutines/selects/SelectClause1;", "onReceiveOrClosed", "isClosedForReceive", "()Z", "isEmptyImpl", "getOnReceiveOrNull", "onReceiveOrNull", "isBufferAlwaysEmpty", "getOnReceive", "onReceive", "isBufferEmpty", CartConstant.KEY_GLOBAL_IS_EMPTY, "getHasReceiveOrClosed", "hasReceiveOrClosed", "<init>", "Itr", "ReceiveElement", "ReceiveHasNext", "ReceiveSelect", "RemoveReceiveOnCancel", "TryPollDesc", "kotlinx-coroutines-core"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes.dex */
public abstract class AbstractChannel<E> extends AbstractSendChannel<E> implements Channel<E> {

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u000b\b\u0002\u0018\u0000*\u0004\b\u0001\u0010\u00012\b\u0012\u0004\u0012\u00028\u00010\u0002B\u0015\u0012\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00028\u00010\r\u00a2\u0006\u0004\b\u0016\u0010\u0017J\u0019\u0010\u0006\u001a\u00020\u00052\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003H\u0002\u00a2\u0006\u0004\b\u0006\u0010\u0007J\u0013\u0010\b\u001a\u00020\u0005H\u0096B\u00f8\u0001\u0000\u00a2\u0006\u0004\b\b\u0010\tJ\u0013\u0010\n\u001a\u00020\u0005H\u0082@\u00f8\u0001\u0000\u00a2\u0006\u0004\b\n\u0010\tJ\u0010\u0010\u000b\u001a\u00028\u0001H\u0096\u0002\u00a2\u0006\u0004\b\u000b\u0010\fR\u001f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00028\u00010\r8\u0006@\u0006\u00a2\u0006\f\n\u0004\b\u000e\u0010\u000f\u001a\u0004\b\u0010\u0010\u0011R$\u0010\u0004\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u0004\u0010\u0012\u001a\u0004\b\u0013\u0010\f\"\u0004\b\u0014\u0010\u0015\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u0018"}, d2 = {"Lkotlinx/coroutines/channels/AbstractChannel$Itr;", "E", "Lkotlinx/coroutines/channels/ChannelIterator;", "", "result", "", "hasNextResult", "(Ljava/lang/Object;)Z", "hasNext", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "hasNextSuspend", "next", "()Ljava/lang/Object;", "Lkotlinx/coroutines/channels/AbstractChannel;", "channel", "Lkotlinx/coroutines/channels/AbstractChannel;", "getChannel", "()Lkotlinx/coroutines/channels/AbstractChannel;", "Ljava/lang/Object;", "getResult", "setResult", "(Ljava/lang/Object;)V", "<init>", "(Lkotlinx/coroutines/channels/AbstractChannel;)V", "kotlinx-coroutines-core"}, k = 1, mv = {1, 4, 0})
    /* loaded from: classes.dex */
    public static final class Itr<E> implements ChannelIterator<E> {
        @NotNull
        private final AbstractChannel<E> channel;
        @Nullable
        private Object result = AbstractChannelKt.POLL_FAILED;

        public Itr(@NotNull AbstractChannel<E> abstractChannel) {
            this.channel = abstractChannel;
        }

        private final boolean hasNextResult(Object result) {
            if (result instanceof Closed@) {
                Closed@ r2 = (Closed@) result;
                if (r2.Closed@ == null) {
                    return false;
                }
                throw StackTraceRecoveryKt.recoverStackTrace(r2.getReceiveException());
            }
            return true;
        }

        @NotNull
        public final AbstractChannel<E> getChannel() {
            return this.channel;
        }

        @Nullable
        public final Object getResult() {
            return this.result;
        }

        @Override // kotlinx.coroutines.channels.ChannelIterator
        @Nullable
        public Object hasNext(@NotNull Continuation<? super Boolean> continuation) {
            Object obj = this.result;
            Object obj2 = AbstractChannelKt.POLL_FAILED;
            if (obj != obj2) {
                return Boxing.boxBoolean(hasNextResult(obj));
            }
            Object pollInternal = this.channel.pollInternal();
            this.result = pollInternal;
            if (pollInternal != obj2) {
                return Boxing.boxBoolean(hasNextResult(pollInternal));
            }
            return hasNextSuspend(continuation);
        }

        @Nullable
        final /* synthetic */ Object hasNextSuspend(@NotNull Continuation<? super Boolean> continuation) {
            Continuation intercepted;
            Object coroutine_suspended;
            intercepted = IntrinsicsKt__IntrinsicsJvmKt.intercepted(continuation);
            CancellableContinuationImpl orCreateCancellableContinuation = CancellableContinuationKt.getOrCreateCancellableContinuation(intercepted);
            ReceiveHasNext@ r1 = new ReceiveHasNext@(this, orCreateCancellableContinuation);
            while (true) {
                if (getChannel().enqueueReceive(r1)) {
                    getChannel().removeReceiveOnCancel(orCreateCancellableContinuation, r1);
                    break;
                }
                Object pollInternal = getChannel().pollInternal();
                setResult(pollInternal);
                if (pollInternal instanceof Closed@) {
                    Closed@ r2 = (Closed@) pollInternal;
                    if (r2.Closed@ == null) {
                        Boolean boxBoolean = Boxing.boxBoolean(false);
                        Result.Companion companion = Result.INSTANCE;
                        orCreateCancellableContinuation.resumeWith(Result.m200constructorimpl(boxBoolean));
                    } else {
                        Throwable receiveException = r2.getReceiveException();
                        Result.Companion companion2 = Result.INSTANCE;
                        orCreateCancellableContinuation.resumeWith(Result.m200constructorimpl(ResultKt.createFailure(receiveException)));
                    }
                } else if (pollInternal != AbstractChannelKt.POLL_FAILED) {
                    Boolean boxBoolean2 = Boxing.boxBoolean(true);
                    Result.Companion companion3 = Result.INSTANCE;
                    orCreateCancellableContinuation.resumeWith(Result.m200constructorimpl(boxBoolean2));
                    break;
                }
            }
            Object result = orCreateCancellableContinuation.getResult();
            coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (result == coroutine_suspended) {
                DebugProbes.probeCoroutineSuspended(continuation);
            }
            return result;
        }

        @Override // kotlinx.coroutines.channels.ChannelIterator
        @Deprecated(level = DeprecationLevel.HIDDEN, message = "Since 1.3.0, binary compatibility with versions <= 1.2.x")
        @JvmName(name = "next")
        @Nullable
        public /* synthetic */ Object next(@NotNull Continuation<? super E> continuation) {
            return ChannelIterator.DefaultImpls.next(this, continuation);
        }

        public final void setResult(@Nullable Object obj) {
            this.result = obj;
        }

        @Override // kotlinx.coroutines.channels.ChannelIterator
        public E next() {
            E e2 = (E) this.result;
            if (!(e2 instanceof Closed@)) {
                Object obj = AbstractChannelKt.POLL_FAILED;
                if (e2 != obj) {
                    this.result = obj;
                    return e2;
                }
                throw new IllegalStateException("'hasNext' should be called prior to 'next' invocation");
            }
            throw StackTraceRecoveryKt.recoverStackTrace(((Closed@) e2).getReceiveException());
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\b\u0002\u0018\u0000*\u0006\b\u0001\u0010\u0001 \u00002\b\u0012\u0004\u0012\u00028\u00010\u0002B\u001f\u0012\u000e\u0010\u0017\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00040\u0016\u0012\u0006\u0010\u001a\u001a\u00020\u0019\u00a2\u0006\u0004\b\u001c\u0010\u001dJ\u0017\u0010\u0005\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0003\u001a\u00028\u0001\u00a2\u0006\u0004\b\u0005\u0010\u0006J#\u0010\n\u001a\u0004\u0018\u00010\t2\u0006\u0010\u0003\u001a\u00028\u00012\b\u0010\b\u001a\u0004\u0018\u00010\u0007H\u0016\u00a2\u0006\u0004\b\n\u0010\u000bJ\u0017\u0010\r\u001a\u00020\f2\u0006\u0010\u0003\u001a\u00028\u0001H\u0016\u00a2\u0006\u0004\b\r\u0010\u000eJ\u001b\u0010\u0011\u001a\u00020\f2\n\u0010\u0010\u001a\u0006\u0012\u0002\b\u00030\u000fH\u0016\u00a2\u0006\u0004\b\u0011\u0010\u0012J\u000f\u0010\u0014\u001a\u00020\u0013H\u0016\u00a2\u0006\u0004\b\u0014\u0010\u0015R\u001e\u0010\u0017\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00040\u00168\u0006@\u0007X\u0087\u0004\u00a2\u0006\u0006\n\u0004\b\u0017\u0010\u0018R\u0016\u0010\u001a\u001a\u00020\u00198\u0006@\u0007X\u0087\u0004\u00a2\u0006\u0006\n\u0004\b\u001a\u0010\u001b\u00a8\u0006\u001e"}, d2 = {"Lkotlinx/coroutines/channels/AbstractChannel$ReceiveElement;", "E", "Lkotlinx/coroutines/channels/Receive;", "value", "", "resumeValue", "(Ljava/lang/Object;)Ljava/lang/Object;", "Lkotlinx/coroutines/internal/LockFreeLinkedListNode$PrepareOp;", "otherOp", "Lkotlinx/coroutines/internal/Symbol;", "tryResumeReceive", "(Ljava/lang/Object;Lkotlinx/coroutines/internal/LockFreeLinkedListNode$PrepareOp;)Lkotlinx/coroutines/internal/Symbol;", "", "completeResumeReceive", "(Ljava/lang/Object;)V", "Lkotlinx/coroutines/channels/Closed;", JshopConst.JSKEY_SHOP_CLOSED, "resumeReceiveClosed", "(Lkotlinx/coroutines/channels/Closed;)V", "", "toString", "()Ljava/lang/String;", "Lkotlinx/coroutines/CancellableContinuation;", EtModelMaker.KEY_CONT, "Lkotlinx/coroutines/CancellableContinuation;", "", "receiveMode", "I", "<init>", "(Lkotlinx/coroutines/CancellableContinuation;I)V", "kotlinx-coroutines-core"}, k = 1, mv = {1, 4, 0})
    /* renamed from: kotlinx.coroutines.channels.AbstractChannel$ReceiveElement  reason: from toString */
    /* loaded from: classes11.dex */
    public static final class ReceiveElement@<E> extends Receive<E> {
        @JvmField
        @NotNull
        public final CancellableContinuation<Object> cont;
        @JvmField

        /* renamed from: receiveMode  reason: from kotlin metadata and from toString */
        public final int [receiveMode;

        public ReceiveElement@(@NotNull CancellableContinuation<Object> cancellableContinuation, int i2) {
            this.cont = cancellableContinuation;
            this.[receiveMode = i2;
        }

        @Override // kotlinx.coroutines.channels.ReceiveOrClosed
        public void completeResumeReceive(E value) {
            this.cont.completeResume(CancellableContinuationImplKt.RESUME_TOKEN);
        }

        @Override // kotlinx.coroutines.channels.Receive
        public void resumeReceiveClosed(@NotNull Closed@<?> r3) {
            int i2 = this.[receiveMode;
            if (i2 == 1 && r3.Closed@ == null) {
                CancellableContinuation<Object> cancellableContinuation = this.cont;
                Result.Companion companion = Result.INSTANCE;
                cancellableContinuation.resumeWith(Result.m200constructorimpl(null));
            } else if (i2 == 2) {
                CancellableContinuation<Object> cancellableContinuation2 = this.cont;
                ValueOrClosed.Companion companion2 = ValueOrClosed.INSTANCE;
                ValueOrClosed m1227boximpl = ValueOrClosed.m1227boximpl(ValueOrClosed.m1228constructorimpl(new ValueOrClosed.Closed(r3.Closed@)));
                Result.Companion companion3 = Result.INSTANCE;
                cancellableContinuation2.resumeWith(Result.m200constructorimpl(m1227boximpl));
            } else {
                CancellableContinuation<Object> cancellableContinuation3 = this.cont;
                Throwable receiveException = r3.getReceiveException();
                Result.Companion companion4 = Result.INSTANCE;
                cancellableContinuation3.resumeWith(Result.m200constructorimpl(ResultKt.createFailure(receiveException)));
            }
        }

        @Nullable
        public final Object resumeValue(E value) {
            if (this.[receiveMode != 2) {
                return value;
            }
            ValueOrClosed.Companion companion = ValueOrClosed.INSTANCE;
            return ValueOrClosed.m1227boximpl(ValueOrClosed.m1228constructorimpl(value));
        }

        @Override // kotlinx.coroutines.internal.LockFreeLinkedListNode
        @NotNull
        public String toString() {
            return "ReceiveElement@" + DebugStringsKt.getHexAddress(this) + "[receiveMode=" + this.[receiveMode + ']';
        }

        @Override // kotlinx.coroutines.channels.ReceiveOrClosed
        @Nullable
        public Symbol tryResumeReceive(E value, @Nullable LockFreeLinkedListNode.PrepareOp otherOp) {
            Object tryResume = this.cont.tryResume(resumeValue(value), otherOp != null ? otherOp.desc : null);
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

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\b\u0002\u0018\u0000*\u0004\b\u0001\u0010\u00012\b\u0012\u0004\u0012\u00028\u00010\u0002B#\u0012\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00028\u00010\u0013\u0012\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00170\u0016\u00a2\u0006\u0004\b\u001a\u0010\u001bJ#\u0010\u0007\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0003\u001a\u00028\u00012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0004H\u0016\u00a2\u0006\u0004\b\u0007\u0010\bJ\u0017\u0010\n\u001a\u00020\t2\u0006\u0010\u0003\u001a\u00028\u0001H\u0016\u00a2\u0006\u0004\b\n\u0010\u000bJ\u001b\u0010\u000e\u001a\u00020\t2\n\u0010\r\u001a\u0006\u0012\u0002\b\u00030\fH\u0016\u00a2\u0006\u0004\b\u000e\u0010\u000fJ\u000f\u0010\u0011\u001a\u00020\u0010H\u0016\u00a2\u0006\u0004\b\u0011\u0010\u0012R\u001c\u0010\u0014\u001a\b\u0012\u0004\u0012\u00028\u00010\u00138\u0006@\u0007X\u0087\u0004\u00a2\u0006\u0006\n\u0004\b\u0014\u0010\u0015R\u001c\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00170\u00168\u0006@\u0007X\u0087\u0004\u00a2\u0006\u0006\n\u0004\b\u0018\u0010\u0019\u00a8\u0006\u001c"}, d2 = {"Lkotlinx/coroutines/channels/AbstractChannel$ReceiveHasNext;", "E", "Lkotlinx/coroutines/channels/Receive;", "value", "Lkotlinx/coroutines/internal/LockFreeLinkedListNode$PrepareOp;", "otherOp", "Lkotlinx/coroutines/internal/Symbol;", "tryResumeReceive", "(Ljava/lang/Object;Lkotlinx/coroutines/internal/LockFreeLinkedListNode$PrepareOp;)Lkotlinx/coroutines/internal/Symbol;", "", "completeResumeReceive", "(Ljava/lang/Object;)V", "Lkotlinx/coroutines/channels/Closed;", JshopConst.JSKEY_SHOP_CLOSED, "resumeReceiveClosed", "(Lkotlinx/coroutines/channels/Closed;)V", "", "toString", "()Ljava/lang/String;", "Lkotlinx/coroutines/channels/AbstractChannel$Itr;", "iterator", "Lkotlinx/coroutines/channels/AbstractChannel$Itr;", "Lkotlinx/coroutines/CancellableContinuation;", "", EtModelMaker.KEY_CONT, "Lkotlinx/coroutines/CancellableContinuation;", "<init>", "(Lkotlinx/coroutines/channels/AbstractChannel$Itr;Lkotlinx/coroutines/CancellableContinuation;)V", "kotlinx-coroutines-core"}, k = 1, mv = {1, 4, 0})
    /* renamed from: kotlinx.coroutines.channels.AbstractChannel$ReceiveHasNext  reason: from toString */
    /* loaded from: classes11.dex */
    public static final class ReceiveHasNext@<E> extends Receive<E> {
        @JvmField
        @NotNull
        public final CancellableContinuation<Boolean> cont;
        @JvmField
        @NotNull
        public final Itr<E> iterator;

        /* JADX WARN: Multi-variable type inference failed */
        public ReceiveHasNext@(@NotNull Itr<E> itr, @NotNull CancellableContinuation<? super Boolean> cancellableContinuation) {
            this.iterator = itr;
            this.cont = cancellableContinuation;
        }

        @Override // kotlinx.coroutines.channels.ReceiveOrClosed
        public void completeResumeReceive(E value) {
            this.iterator.setResult(value);
            this.cont.completeResume(CancellableContinuationImplKt.RESUME_TOKEN);
        }

        @Override // kotlinx.coroutines.channels.Receive
        public void resumeReceiveClosed(@NotNull Closed@<?> r5) {
            Object tryResumeWithException;
            if (r5.Closed@ == null) {
                tryResumeWithException = CancellableContinuation.DefaultImpls.tryResume$default(this.cont, Boolean.FALSE, null, 2, null);
            } else {
                CancellableContinuation<Boolean> cancellableContinuation = this.cont;
                Throwable receiveException = r5.getReceiveException();
                CancellableContinuation<Boolean> cancellableContinuation2 = this.cont;
                if (DebugKt.getRECOVER_STACK_TRACES() && (cancellableContinuation2 instanceof CoroutineStackFrame)) {
                    receiveException = StackTraceRecoveryKt.recoverFromStackFrame(receiveException, (CoroutineStackFrame) cancellableContinuation2);
                }
                tryResumeWithException = cancellableContinuation.tryResumeWithException(receiveException);
            }
            if (tryResumeWithException != null) {
                this.iterator.setResult(r5);
                this.cont.completeResume(tryResumeWithException);
            }
        }

        @Override // kotlinx.coroutines.internal.LockFreeLinkedListNode
        @NotNull
        public String toString() {
            return "ReceiveHasNext@" + DebugStringsKt.getHexAddress(this);
        }

        @Override // kotlinx.coroutines.channels.ReceiveOrClosed
        @Nullable
        public Symbol tryResumeReceive(E value, @Nullable LockFreeLinkedListNode.PrepareOp otherOp) {
            Object tryResume = this.cont.tryResume(Boolean.TRUE, otherOp != null ? otherOp.desc : null);
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

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0002\u0018\u0000*\u0004\b\u0001\u0010\u0001*\u0004\b\u0002\u0010\u00022\b\u0012\u0004\u0012\u00028\u00020\u00032\u00020\u0004BT\u0012\f\u0010#\u001a\b\u0012\u0004\u0012\u00028\u00020\"\u0012\f\u0010\u001b\u001a\b\u0012\u0004\u0012\u00028\u00010\u001a\u0012$\u0010 \u001a \b\u0001\u0012\u0006\u0012\u0004\u0018\u00010\u001e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00010\u001f\u0012\u0006\u0012\u0004\u0018\u00010\u001e0\u001d\u0012\u0006\u0010\u0018\u001a\u00020\u0017\u00f8\u0001\u0000\u00a2\u0006\u0004\b%\u0010&J#\u0010\t\u001a\u0004\u0018\u00010\b2\u0006\u0010\u0005\u001a\u00028\u00022\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006H\u0016\u00a2\u0006\u0004\b\t\u0010\nJ\u0017\u0010\f\u001a\u00020\u000b2\u0006\u0010\u0005\u001a\u00028\u0002H\u0016\u00a2\u0006\u0004\b\f\u0010\rJ\u001b\u0010\u0010\u001a\u00020\u000b2\n\u0010\u000f\u001a\u0006\u0012\u0002\b\u00030\u000eH\u0016\u00a2\u0006\u0004\b\u0010\u0010\u0011J\u000f\u0010\u0012\u001a\u00020\u000bH\u0016\u00a2\u0006\u0004\b\u0012\u0010\u0013J\u000f\u0010\u0015\u001a\u00020\u0014H\u0016\u00a2\u0006\u0004\b\u0015\u0010\u0016R\u0016\u0010\u0018\u001a\u00020\u00178\u0006@\u0007X\u0087\u0004\u00a2\u0006\u0006\n\u0004\b\u0018\u0010\u0019R\u001c\u0010\u001b\u001a\b\u0012\u0004\u0012\u00028\u00010\u001a8\u0006@\u0007X\u0087\u0004\u00a2\u0006\u0006\n\u0004\b\u001b\u0010\u001cR7\u0010 \u001a \b\u0001\u0012\u0006\u0012\u0004\u0018\u00010\u001e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00010\u001f\u0012\u0006\u0012\u0004\u0018\u00010\u001e0\u001d8\u0006@\u0007X\u0087\u0004\u00f8\u0001\u0000\u00a2\u0006\u0006\n\u0004\b \u0010!R\u001c\u0010#\u001a\b\u0012\u0004\u0012\u00028\u00020\"8\u0006@\u0007X\u0087\u0004\u00a2\u0006\u0006\n\u0004\b#\u0010$\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006'"}, d2 = {"Lkotlinx/coroutines/channels/AbstractChannel$ReceiveSelect;", "R", "E", "Lkotlinx/coroutines/channels/Receive;", "Lkotlinx/coroutines/DisposableHandle;", "value", "Lkotlinx/coroutines/internal/LockFreeLinkedListNode$PrepareOp;", "otherOp", "Lkotlinx/coroutines/internal/Symbol;", "tryResumeReceive", "(Ljava/lang/Object;Lkotlinx/coroutines/internal/LockFreeLinkedListNode$PrepareOp;)Lkotlinx/coroutines/internal/Symbol;", "", "completeResumeReceive", "(Ljava/lang/Object;)V", "Lkotlinx/coroutines/channels/Closed;", JshopConst.JSKEY_SHOP_CLOSED, "resumeReceiveClosed", "(Lkotlinx/coroutines/channels/Closed;)V", "dispose", "()V", "", "toString", "()Ljava/lang/String;", "", "receiveMode", "I", "Lkotlinx/coroutines/selects/SelectInstance;", "select", "Lkotlinx/coroutines/selects/SelectInstance;", "Lkotlin/Function2;", "", "Lkotlin/coroutines/Continuation;", JDReactConstant.BLOCK, "Lkotlin/jvm/functions/Function2;", "Lkotlinx/coroutines/channels/AbstractChannel;", "channel", "Lkotlinx/coroutines/channels/AbstractChannel;", "<init>", "(Lkotlinx/coroutines/channels/AbstractChannel;Lkotlinx/coroutines/selects/SelectInstance;Lkotlin/jvm/functions/Function2;I)V", "kotlinx-coroutines-core"}, k = 1, mv = {1, 4, 0})
    /* renamed from: kotlinx.coroutines.channels.AbstractChannel$ReceiveSelect  reason: from toString */
    /* loaded from: classes11.dex */
    public static final class ReceiveSelect@<R, E> extends Receive<E> implements DisposableHandle {
        @JvmField
        @NotNull
        public final Function2<Object, Continuation<? super R>, Object> block;
        @JvmField
        @NotNull
        public final AbstractChannel<E> channel;
        @JvmField

        /* renamed from: receiveMode  reason: from kotlin metadata and from toString */
        public final int ,receiveMode;
        @JvmField
        @NotNull

        /* renamed from: select  reason: from kotlin metadata and from toString */
        public final SelectInstance<R> ReceiveSelect@;

        /* JADX WARN: Multi-variable type inference failed */
        public ReceiveSelect@(@NotNull AbstractChannel<E> abstractChannel, @NotNull SelectInstance<? super R> selectInstance, @NotNull Function2<Object, ? super Continuation<? super R>, ? extends Object> function2, int i2) {
            this.channel = abstractChannel;
            this.ReceiveSelect@ = selectInstance;
            this.block = function2;
            this.,receiveMode = i2;
        }

        @Override // kotlinx.coroutines.channels.ReceiveOrClosed
        public void completeResumeReceive(E value) {
            Function2<Object, Continuation<? super R>, Object> function2 = this.block;
            if (this.,receiveMode == 2) {
                ValueOrClosed.Companion companion = ValueOrClosed.INSTANCE;
                value = (E) ValueOrClosed.m1227boximpl(ValueOrClosed.m1228constructorimpl(value));
            }
            ContinuationKt.startCoroutine(function2, value, this.ReceiveSelect@.getCompletion());
        }

        @Override // kotlinx.coroutines.DisposableHandle
        public void dispose() {
            if (remove()) {
                this.channel.onReceiveDequeued();
            }
        }

        @Override // kotlinx.coroutines.channels.Receive
        public void resumeReceiveClosed(@NotNull Closed@<?> r3) {
            if (this.ReceiveSelect@.trySelect()) {
                int i2 = this.,receiveMode;
                if (i2 == 0) {
                    this.ReceiveSelect@.resumeSelectWithException(r3.getReceiveException());
                } else if (i2 == 1) {
                    if (r3.Closed@ == null) {
                        ContinuationKt.startCoroutine(this.block, null, this.ReceiveSelect@.getCompletion());
                    } else {
                        this.ReceiveSelect@.resumeSelectWithException(r3.getReceiveException());
                    }
                } else if (i2 != 2) {
                } else {
                    Function2<Object, Continuation<? super R>, Object> function2 = this.block;
                    ValueOrClosed.Companion companion = ValueOrClosed.INSTANCE;
                    ContinuationKt.startCoroutine(function2, ValueOrClosed.m1227boximpl(ValueOrClosed.m1228constructorimpl(new ValueOrClosed.Closed(r3.Closed@))), this.ReceiveSelect@.getCompletion());
                }
            }
        }

        @Override // kotlinx.coroutines.internal.LockFreeLinkedListNode
        @NotNull
        public String toString() {
            return "ReceiveSelect@" + DebugStringsKt.getHexAddress(this) + '[' + this.ReceiveSelect@ + ",receiveMode=" + this.,receiveMode + ']';
        }

        @Override // kotlinx.coroutines.channels.ReceiveOrClosed
        @Nullable
        public Symbol tryResumeReceive(E value, @Nullable LockFreeLinkedListNode.PrepareOp otherOp) {
            return (Symbol) this.ReceiveSelect@.trySelectOther(otherOp);
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0003\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0082\u0004\u0018\u00002\u00020\u0001B\u0013\u0012\n\u0010\u000b\u001a\u0006\u0012\u0002\b\u00030\n\u00a2\u0006\u0004\b\r\u0010\u000eJ\u001a\u0010\u0005\u001a\u00020\u00042\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002H\u0096\u0002\u00a2\u0006\u0004\b\u0005\u0010\u0006J\u000f\u0010\b\u001a\u00020\u0007H\u0016\u00a2\u0006\u0004\b\b\u0010\tR\u001a\u0010\u000b\u001a\u0006\u0012\u0002\b\u00030\n8\u0002@\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u000b\u0010\f\u00a8\u0006\u000f"}, d2 = {"Lkotlinx/coroutines/channels/AbstractChannel$RemoveReceiveOnCancel;", "Lkotlinx/coroutines/CancelHandler;", "", "cause", "", "invoke", "(Ljava/lang/Throwable;)V", "", "toString", "()Ljava/lang/String;", "Lkotlinx/coroutines/channels/Receive;", "receive", "Lkotlinx/coroutines/channels/Receive;", "<init>", "(Lkotlinx/coroutines/channels/AbstractChannel;Lkotlinx/coroutines/channels/Receive;)V", "kotlinx-coroutines-core"}, k = 1, mv = {1, 4, 0})
    /* renamed from: kotlinx.coroutines.channels.AbstractChannel$RemoveReceiveOnCancel  reason: from toString */
    /* loaded from: classes11.dex */
    public final class RemoveReceiveOnCancel[ extends CancelHandler {

        /* renamed from: receive  reason: from kotlin metadata and from toString */
        private final Receive<?> RemoveReceiveOnCancel[;

        public RemoveReceiveOnCancel[(@NotNull Receive<?> receive) {
            AbstractChannel.this = r1;
            this.RemoveReceiveOnCancel[ = receive;
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(Throwable th) {
            invoke2(th);
            return Unit.INSTANCE;
        }

        @NotNull
        public String toString() {
            return "RemoveReceiveOnCancel[" + this.RemoveReceiveOnCancel[ + ']';
        }

        @Override // kotlinx.coroutines.CancelHandlerBase
        /* renamed from: invoke */
        public void invoke2(@Nullable Throwable cause) {
            if (this.RemoveReceiveOnCancel[.remove()) {
                AbstractChannel.this.onReceiveDequeued();
            }
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0004\u0018\u0000*\u0004\b\u0001\u0010\u00012\u0012\u0012\u0004\u0012\u00020\u00030\u0002j\b\u0012\u0004\u0012\u00020\u0003`\u0004B\u000f\u0012\u0006\u0010\u0010\u001a\u00020\u000f\u00a2\u0006\u0004\b\u0011\u0010\u0012J\u0019\u0010\b\u001a\u0004\u0018\u00010\u00072\u0006\u0010\u0006\u001a\u00020\u0005H\u0014\u00a2\u0006\u0004\b\b\u0010\tJ\u001d\u0010\r\u001a\u0004\u0018\u00010\u00072\n\u0010\f\u001a\u00060\nj\u0002`\u000bH\u0016\u00a2\u0006\u0004\b\r\u0010\u000e\u00a8\u0006\u0013"}, d2 = {"Lkotlinx/coroutines/channels/AbstractChannel$TryPollDesc;", "E", "Lkotlinx/coroutines/internal/LockFreeLinkedListNode$RemoveFirstDesc;", "Lkotlinx/coroutines/channels/Send;", "Lkotlinx/coroutines/internal/RemoveFirstDesc;", "Lkotlinx/coroutines/internal/LockFreeLinkedListNode;", "affected", "", "failure", "(Lkotlinx/coroutines/internal/LockFreeLinkedListNode;)Ljava/lang/Object;", "Lkotlinx/coroutines/internal/LockFreeLinkedListNode$PrepareOp;", "Lkotlinx/coroutines/internal/PrepareOp;", "prepareOp", "onPrepare", "(Lkotlinx/coroutines/internal/LockFreeLinkedListNode$PrepareOp;)Ljava/lang/Object;", "Lkotlinx/coroutines/internal/LockFreeLinkedListHead;", "queue", "<init>", "(Lkotlinx/coroutines/internal/LockFreeLinkedListHead;)V", "kotlinx-coroutines-core"}, k = 1, mv = {1, 4, 0})
    /* loaded from: classes11.dex */
    public static final class TryPollDesc<E> extends LockFreeLinkedListNode.RemoveFirstDesc<Send> {
        public TryPollDesc(@NotNull LockFreeLinkedListHead lockFreeLinkedListHead) {
            super(lockFreeLinkedListHead);
        }

        @Override // kotlinx.coroutines.internal.LockFreeLinkedListNode.RemoveFirstDesc, kotlinx.coroutines.internal.LockFreeLinkedListNode.AbstractAtomicDesc
        @Nullable
        protected Object failure(@NotNull LockFreeLinkedListNode affected) {
            if (affected instanceof Closed@) {
                return affected;
            }
            if (affected instanceof Send) {
                return null;
            }
            return AbstractChannelKt.POLL_FAILED;
        }

        @Override // kotlinx.coroutines.internal.LockFreeLinkedListNode.AbstractAtomicDesc
        @Nullable
        public Object onPrepare(@NotNull LockFreeLinkedListNode.PrepareOp prepareOp) {
            LockFreeLinkedListNode lockFreeLinkedListNode = prepareOp.affected;
            if (lockFreeLinkedListNode != null) {
                Symbol tryResumeSend = ((Send) lockFreeLinkedListNode).tryResumeSend(prepareOp);
                if (tryResumeSend != null) {
                    Object obj = AtomicKt.RETRY_ATOMIC;
                    if (tryResumeSend == obj) {
                        return obj;
                    }
                    if (DebugKt.getASSERTIONS_ENABLED()) {
                        if (tryResumeSend == CancellableContinuationImplKt.RESUME_TOKEN) {
                            return null;
                        }
                        throw new AssertionError();
                    }
                    return null;
                }
                return LockFreeLinkedList_commonKt.REMOVE_PREPARED;
            }
            throw new TypeCastException("null cannot be cast to non-null type kotlinx.coroutines.channels.Send");
        }
    }

    public final boolean enqueueReceive(Receive<? super E> receive) {
        boolean enqueueReceiveInternal = enqueueReceiveInternal(receive);
        if (enqueueReceiveInternal) {
            onReceiveEnqueued();
        }
        return enqueueReceiveInternal;
    }

    private final <R> boolean enqueueReceiveSelect(SelectInstance<? super R> select, Function2<Object, ? super Continuation<? super R>, ? extends Object> r3, int receiveMode) {
        ReceiveSelect@ r0 = new ReceiveSelect@(this, select, r3, receiveMode);
        boolean enqueueReceive = enqueueReceive(r0);
        if (enqueueReceive) {
            select.disposeOnSelect(r0);
        }
        return enqueueReceive;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final E receiveOrNullResult(Object result) {
        if (result instanceof Closed@) {
            Throwable th = ((Closed@) result).Closed@;
            if (th == null) {
                return null;
            }
            throw StackTraceRecoveryKt.recoverStackTrace(th);
        }
        return result;
    }

    public final <R> void registerSelectReceiveMode(SelectInstance<? super R> select, int receiveMode, Function2<Object, ? super Continuation<? super R>, ? extends Object> r5) {
        while (!select.isSelected()) {
            if (isEmptyImpl()) {
                if (enqueueReceiveSelect(select, r5, receiveMode)) {
                    return;
                }
            } else {
                Object pollSelectInternal = pollSelectInternal(select);
                if (pollSelectInternal == SelectKt.getALREADY_SELECTED()) {
                    return;
                }
                if (pollSelectInternal != AbstractChannelKt.POLL_FAILED && pollSelectInternal != AtomicKt.RETRY_ATOMIC) {
                    tryStartBlockUnintercepted(r5, select, receiveMode, pollSelectInternal);
                }
            }
        }
    }

    public final void removeReceiveOnCancel(CancellableContinuation<?> r2, Receive<?> receive) {
        r2.invokeOnCancellation(new RemoveReceiveOnCancel[(receive));
    }

    private final <R> void tryStartBlockUnintercepted(@NotNull Function2<Object, ? super Continuation<? super R>, ? extends Object> function2, SelectInstance<? super R> selectInstance, int i2, Object obj) {
        Object m1228constructorimpl;
        boolean z = obj instanceof Closed@;
        if (!z) {
            if (i2 == 2) {
                ValueOrClosed.Companion companion = ValueOrClosed.INSTANCE;
                if (z) {
                    m1228constructorimpl = ValueOrClosed.m1228constructorimpl(new ValueOrClosed.Closed(((Closed@) obj).Closed@));
                } else {
                    m1228constructorimpl = ValueOrClosed.m1228constructorimpl(obj);
                }
                UndispatchedKt.startCoroutineUnintercepted(function2, ValueOrClosed.m1227boximpl(m1228constructorimpl), selectInstance.getCompletion());
                return;
            }
            UndispatchedKt.startCoroutineUnintercepted(function2, obj, selectInstance.getCompletion());
        } else if (i2 == 0) {
            throw StackTraceRecoveryKt.recoverStackTrace(((Closed@) obj).getReceiveException());
        } else {
            if (i2 != 1) {
                if (i2 == 2 && selectInstance.trySelect()) {
                    ValueOrClosed.Companion companion2 = ValueOrClosed.INSTANCE;
                    UndispatchedKt.startCoroutineUnintercepted(function2, ValueOrClosed.m1227boximpl(ValueOrClosed.m1228constructorimpl(new ValueOrClosed.Closed(((Closed@) obj).Closed@))), selectInstance.getCompletion());
                    return;
                }
                return;
            }
            Closed@ r6 = (Closed@) obj;
            if (r6.Closed@ == null) {
                if (selectInstance.trySelect()) {
                    UndispatchedKt.startCoroutineUnintercepted(function2, null, selectInstance.getCompletion());
                    return;
                }
                return;
            }
            throw StackTraceRecoveryKt.recoverStackTrace(r6.getReceiveException());
        }
    }

    @Override // kotlinx.coroutines.channels.ReceiveChannel
    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Since 1.2.0, binary compatibility with versions <= 1.1.x")
    public /* synthetic */ void cancel() {
        cancel((CancellationException) null);
    }

    @Override // kotlinx.coroutines.channels.ReceiveChannel
    /* renamed from: cancelInternal$kotlinx_coroutines_core */
    public final boolean cancel(@Nullable Throwable cause) {
        boolean cancel = cancel(cause);
        onCancelIdempotent(cancel);
        return cancel;
    }

    @NotNull
    public final TryPollDesc<E> describeTryPoll() {
        return new TryPollDesc<>(getQueue());
    }

    public boolean enqueueReceiveInternal(@NotNull final Receive<? super E> receive) {
        int tryCondAddNext;
        LockFreeLinkedListNode prevNode;
        if (isBufferAlwaysEmpty()) {
            LockFreeLinkedListNode queue = getQueue();
            do {
                prevNode = queue.getPrevNode();
                if ((!(prevNode instanceof Send)) == false) {
                    return false;
                }
            } while (!prevNode.addNext(receive, queue));
        } else {
            LockFreeLinkedListNode queue2 = getQueue();
            LockFreeLinkedListNode.CondAddOp condAddOp = new LockFreeLinkedListNode.CondAddOp(receive) { // from class: kotlinx.coroutines.channels.AbstractChannel$enqueueReceiveInternal$$inlined$addLastIfPrevAndIf$1
                @Override // kotlinx.coroutines.internal.AtomicOp
                @Nullable
                public Object prepare(@NotNull LockFreeLinkedListNode lockFreeLinkedListNode) {
                    if (this.isBufferEmpty()) {
                        return null;
                    }
                    return LockFreeLinkedListKt.getCONDITION_FALSE();
                }
            };
            do {
                LockFreeLinkedListNode prevNode2 = queue2.getPrevNode();
                if ((!(prevNode2 instanceof Send)) != true) {
                    return false;
                }
                tryCondAddNext = prevNode2.tryCondAddNext(receive, queue2, condAddOp);
                if (tryCondAddNext != 1) {
                }
            } while (tryCondAddNext != 2);
            return false;
        }
        return true;
    }

    public final boolean getHasReceiveOrClosed() {
        return getQueue().getNextNode() instanceof ReceiveOrClosed;
    }

    @Override // kotlinx.coroutines.channels.ReceiveChannel
    @NotNull
    public final SelectClause1<E> getOnReceive() {
        return new SelectClause1<E>() { // from class: kotlinx.coroutines.channels.AbstractChannel$onReceive$1
            {
                AbstractChannel.this = this;
            }

            @Override // kotlinx.coroutines.selects.SelectClause1
            public <R> void registerSelectClause1(@NotNull SelectInstance<? super R> select, @NotNull Function2<? super E, ? super Continuation<? super R>, ? extends Object> r4) {
                AbstractChannel abstractChannel = AbstractChannel.this;
                if (r4 == null) {
                    throw new TypeCastException("null cannot be cast to non-null type suspend (kotlin.Any?) -> R");
                }
                abstractChannel.registerSelectReceiveMode(select, 0, r4);
            }
        };
    }

    @Override // kotlinx.coroutines.channels.ReceiveChannel
    @NotNull
    public final SelectClause1<ValueOrClosed<E>> getOnReceiveOrClosed() {
        return (SelectClause1<ValueOrClosed<? extends E>>) new SelectClause1<ValueOrClosed<? extends E>>() { // from class: kotlinx.coroutines.channels.AbstractChannel$onReceiveOrClosed$1
            {
                AbstractChannel.this = this;
            }

            @Override // kotlinx.coroutines.selects.SelectClause1
            public <R> void registerSelectClause1(@NotNull SelectInstance<? super R> select, @NotNull Function2<? super ValueOrClosed<? extends E>, ? super Continuation<? super R>, ? extends Object> r4) {
                AbstractChannel abstractChannel = AbstractChannel.this;
                if (r4 == null) {
                    throw new TypeCastException("null cannot be cast to non-null type suspend (kotlin.Any?) -> R");
                }
                abstractChannel.registerSelectReceiveMode(select, 2, r4);
            }
        };
    }

    @Override // kotlinx.coroutines.channels.ReceiveChannel
    @NotNull
    public final SelectClause1<E> getOnReceiveOrNull() {
        return new SelectClause1<E>() { // from class: kotlinx.coroutines.channels.AbstractChannel$onReceiveOrNull$1
            {
                AbstractChannel.this = this;
            }

            @Override // kotlinx.coroutines.selects.SelectClause1
            public <R> void registerSelectClause1(@NotNull SelectInstance<? super R> select, @NotNull Function2<? super E, ? super Continuation<? super R>, ? extends Object> r4) {
                AbstractChannel abstractChannel = AbstractChannel.this;
                if (r4 == null) {
                    throw new TypeCastException("null cannot be cast to non-null type suspend (kotlin.Any?) -> R");
                }
                abstractChannel.registerSelectReceiveMode(select, 1, r4);
            }
        };
    }

    protected abstract boolean isBufferAlwaysEmpty();

    public abstract boolean isBufferEmpty();

    @Override // kotlinx.coroutines.channels.ReceiveChannel
    public boolean isClosedForReceive() {
        return getClosedForReceive() != null && isBufferEmpty();
    }

    @Override // kotlinx.coroutines.channels.ReceiveChannel
    public boolean isEmpty() {
        return isEmptyImpl();
    }

    public final boolean isEmptyImpl() {
        return !(getQueue().getNextNode() instanceof Send) && isBufferEmpty();
    }

    @Override // kotlinx.coroutines.channels.ReceiveChannel
    @NotNull
    public final ChannelIterator<E> iterator() {
        return new Itr(this);
    }

    public void onCancelIdempotent(boolean wasClosed) {
        Closed@<?> closedForSend = getClosedForSend();
        if (closedForSend != null) {
            Object m1246constructorimpl$default = InlineList.m1246constructorimpl$default(null, 1, null);
            while (true) {
                LockFreeLinkedListNode prevNode = closedForSend.getPrevNode();
                if (prevNode instanceof LockFreeLinkedListHead) {
                    if (m1246constructorimpl$default == null) {
                        return;
                    }
                    if (!(m1246constructorimpl$default instanceof ArrayList)) {
                        ((Send) m1246constructorimpl$default).resumeSendClosed(closedForSend);
                        return;
                    } else if (m1246constructorimpl$default != null) {
                        ArrayList arrayList = (ArrayList) m1246constructorimpl$default;
                        for (int size = arrayList.size() - 1; size >= 0; size--) {
                            ((Send) arrayList.get(size)).resumeSendClosed(closedForSend);
                        }
                        return;
                    } else {
                        throw new TypeCastException("null cannot be cast to non-null type kotlin.collections.ArrayList<E> /* = java.util.ArrayList<E> */");
                    }
                } else if (DebugKt.getASSERTIONS_ENABLED() && !(prevNode instanceof Send)) {
                    throw new AssertionError();
                } else {
                    if (!prevNode.remove()) {
                        prevNode.helpRemove();
                    } else if (prevNode == null) {
                        throw new TypeCastException("null cannot be cast to non-null type kotlinx.coroutines.channels.Send");
                    } else {
                        m1246constructorimpl$default = InlineList.m1251plusimpl(m1246constructorimpl$default, (Send) prevNode);
                    }
                }
            }
        } else {
            throw new IllegalStateException("Cannot happen".toString());
        }
    }

    protected void onReceiveDequeued() {
    }

    protected void onReceiveEnqueued() {
    }

    @Override // kotlinx.coroutines.channels.ReceiveChannel
    @Nullable
    public final E poll() {
        Object pollInternal = pollInternal();
        if (pollInternal == AbstractChannelKt.POLL_FAILED) {
            return null;
        }
        return receiveOrNullResult(pollInternal);
    }

    @Nullable
    protected Object pollInternal() {
        Send takeFirstSendOrPeekClosed;
        Symbol tryResumeSend;
        do {
            takeFirstSendOrPeekClosed = takeFirstSendOrPeekClosed();
            if (takeFirstSendOrPeekClosed != null) {
                tryResumeSend = takeFirstSendOrPeekClosed.tryResumeSend(null);
            } else {
                return AbstractChannelKt.POLL_FAILED;
            }
        } while (tryResumeSend == null);
        if (DebugKt.getASSERTIONS_ENABLED()) {
            if (!(tryResumeSend == CancellableContinuationImplKt.RESUME_TOKEN)) {
                throw new AssertionError();
            }
        }
        takeFirstSendOrPeekClosed.completeResumeSend();
        return takeFirstSendOrPeekClosed.getPollResult();
    }

    @Nullable
    protected Object pollSelectInternal(@NotNull SelectInstance<?> select) {
        TryPollDesc<E> describeTryPoll = describeTryPoll();
        Object performAtomicTrySelect = select.performAtomicTrySelect(describeTryPoll);
        if (performAtomicTrySelect != null) {
            return performAtomicTrySelect;
        }
        describeTryPoll.getResult().completeResumeSend();
        return describeTryPoll.getResult().getPollResult();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // kotlinx.coroutines.channels.ReceiveChannel
    @Nullable
    public final Object receive(@NotNull Continuation<? super E> continuation) {
        Object pollInternal = pollInternal();
        return (pollInternal == AbstractChannelKt.POLL_FAILED || (pollInternal instanceof Closed@)) ? receiveSuspend(0, continuation) : pollInternal;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // kotlinx.coroutines.channels.ReceiveChannel
    @Nullable
    public final Object receiveOrClosed(@NotNull Continuation<? super ValueOrClosed<? extends E>> continuation) {
        Object m1228constructorimpl;
        Object pollInternal = pollInternal();
        if (pollInternal != AbstractChannelKt.POLL_FAILED) {
            if (pollInternal instanceof Closed@) {
                ValueOrClosed.Companion companion = ValueOrClosed.INSTANCE;
                m1228constructorimpl = ValueOrClosed.m1228constructorimpl(new ValueOrClosed.Closed(((Closed@) pollInternal).Closed@));
            } else {
                ValueOrClosed.Companion companion2 = ValueOrClosed.INSTANCE;
                m1228constructorimpl = ValueOrClosed.m1228constructorimpl(pollInternal);
            }
            return ValueOrClosed.m1227boximpl(m1228constructorimpl);
        }
        return receiveSuspend(2, continuation);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // kotlinx.coroutines.channels.ReceiveChannel
    @Nullable
    public final Object receiveOrNull(@NotNull Continuation<? super E> continuation) {
        Object pollInternal = pollInternal();
        return (pollInternal == AbstractChannelKt.POLL_FAILED || (pollInternal instanceof Closed@)) ? receiveSuspend(1, continuation) : pollInternal;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Nullable
    final /* synthetic */ <R> Object receiveSuspend(int i2, @NotNull Continuation<? super R> continuation) {
        Continuation intercepted;
        Object coroutine_suspended;
        intercepted = IntrinsicsKt__IntrinsicsJvmKt.intercepted(continuation);
        CancellableContinuationImpl orCreateCancellableContinuation = CancellableContinuationKt.getOrCreateCancellableContinuation(intercepted);
        if (orCreateCancellableContinuation != null) {
            ReceiveElement@ r1 = new ReceiveElement@(orCreateCancellableContinuation, i2);
            while (true) {
                if (enqueueReceive(r1)) {
                    removeReceiveOnCancel(orCreateCancellableContinuation, r1);
                    break;
                }
                Object pollInternal = pollInternal();
                if (pollInternal instanceof Closed@) {
                    r1.resumeReceiveClosed((Closed@) pollInternal);
                    break;
                } else if (pollInternal != AbstractChannelKt.POLL_FAILED) {
                    Object resumeValue = r1.resumeValue(pollInternal);
                    Result.Companion companion = Result.INSTANCE;
                    orCreateCancellableContinuation.resumeWith(Result.m200constructorimpl(resumeValue));
                    break;
                }
            }
            Object result = orCreateCancellableContinuation.getResult();
            coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (result == coroutine_suspended) {
                DebugProbes.probeCoroutineSuspended(continuation);
            }
            return result;
        }
        throw new TypeCastException("null cannot be cast to non-null type kotlinx.coroutines.CancellableContinuation<kotlin.Any?>");
    }

    @Override // kotlinx.coroutines.channels.AbstractSendChannel
    @Nullable
    public ReceiveOrClosed<E> takeFirstReceiveOrPeekClosed() {
        ReceiveOrClosed<E> takeFirstReceiveOrPeekClosed = super.takeFirstReceiveOrPeekClosed();
        if (takeFirstReceiveOrPeekClosed != null && !(takeFirstReceiveOrPeekClosed instanceof Closed@)) {
            onReceiveDequeued();
        }
        return takeFirstReceiveOrPeekClosed;
    }

    @Override // kotlinx.coroutines.channels.ReceiveChannel
    public final void cancel(@Nullable CancellationException cause) {
        if (cause == null) {
            cause = new CancellationException(DebugStringsKt.getClassSimpleName(this) + " was cancelled");
        }
        cancel(cause);
    }
}

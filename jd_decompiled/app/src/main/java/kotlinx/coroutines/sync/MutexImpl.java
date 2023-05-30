package kotlinx.coroutines.sync;

import com.jdcn.biz.client.BankCardConstants;
import com.jingdong.app.mall.bundle.dolphinlib.common.util.EtModelMaker;
import com.jingdong.common.jdreactFramework.JDReactConstant;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.ContinuationKt;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsJvmKt;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugProbes;
import kotlin.jvm.JvmField;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CancellableContinuation;
import kotlinx.coroutines.CancellableContinuationImpl;
import kotlinx.coroutines.CancellableContinuationKt;
import kotlinx.coroutines.DebugKt;
import kotlinx.coroutines.DisposableHandle;
import kotlinx.coroutines.internal.AtomicDesc;
import kotlinx.coroutines.internal.AtomicKt;
import kotlinx.coroutines.internal.AtomicOp;
import kotlinx.coroutines.internal.LockFreeLinkedListHead;
import kotlinx.coroutines.internal.LockFreeLinkedListKt;
import kotlinx.coroutines.internal.LockFreeLinkedListNode;
import kotlinx.coroutines.internal.OpDescriptor;
import kotlinx.coroutines.internal.Symbol;
import kotlinx.coroutines.intrinsics.UndispatchedKt;
import kotlinx.coroutines.selects.SelectClause2;
import kotlinx.coroutines.selects.SelectInstance;
import kotlinx.coroutines.selects.SelectKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.mp4parser.aspectj.lang.JoinPoint;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\u0013\b\u0000\u0018\u00002\u00020\u00012\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u0003\u0012\u0004\u0012\u00020\u00010\u0002:\u0006$%&'()B\u000f\u0012\u0006\u0010!\u001a\u00020\u0005\u00a2\u0006\u0004\b\"\u0010#J\u0019\u0010\u0006\u001a\u00020\u00052\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003H\u0016\u00a2\u0006\u0004\b\u0006\u0010\u0007J\u001d\u0010\t\u001a\u00020\b2\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003H\u0096@\u00f8\u0001\u0000\u00a2\u0006\u0004\b\t\u0010\nJ\u001d\u0010\u000b\u001a\u00020\b2\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003H\u0082@\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u000b\u0010\nJT\u0010\u0012\u001a\u00020\b\"\u0004\b\u0000\u0010\f2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00028\u00000\r2\b\u0010\u0004\u001a\u0004\u0018\u00010\u00032\"\u0010\u0011\u001a\u001e\b\u0001\u0012\u0004\u0012\u00020\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u000fH\u0016\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u0012\u0010\u0013J\u0017\u0010\u0014\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u0003H\u0016\u00a2\u0006\u0004\b\u0014\u0010\u0007J\u0019\u0010\u0015\u001a\u00020\b2\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003H\u0016\u00a2\u0006\u0004\b\u0015\u0010\u0016J\u000f\u0010\u0018\u001a\u00020\u0017H\u0016\u00a2\u0006\u0004\b\u0018\u0010\u0019R$\u0010\u001c\u001a\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u0003\u0012\u0004\u0012\u00020\u00010\u00028V@\u0016X\u0096\u0004\u00a2\u0006\u0006\u001a\u0004\b\u001a\u0010\u001bR\u0016\u0010\u001d\u001a\u00020\u00058V@\u0016X\u0096\u0004\u00a2\u0006\u0006\u001a\u0004\b\u001d\u0010\u001eR\u0016\u0010 \u001a\u00020\u00058@@\u0000X\u0080\u0004\u00a2\u0006\u0006\u001a\u0004\b\u001f\u0010\u001e\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006*"}, d2 = {"Lkotlinx/coroutines/sync/MutexImpl;", "Lkotlinx/coroutines/sync/Mutex;", "Lkotlinx/coroutines/selects/SelectClause2;", "", BankCardConstants.KEY_OWNER, "", "tryLock", "(Ljava/lang/Object;)Z", "", JoinPoint.SYNCHRONIZATION_LOCK, "(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "lockSuspend", "R", "Lkotlinx/coroutines/selects/SelectInstance;", "select", "Lkotlin/Function2;", "Lkotlin/coroutines/Continuation;", JDReactConstant.BLOCK, "registerSelectClause2", "(Lkotlinx/coroutines/selects/SelectInstance;Ljava/lang/Object;Lkotlin/jvm/functions/Function2;)V", "holdsLock", JoinPoint.SYNCHRONIZATION_UNLOCK, "(Ljava/lang/Object;)V", "", "toString", "()Ljava/lang/String;", "getOnLock", "()Lkotlinx/coroutines/selects/SelectClause2;", "onLock", "isLocked", "()Z", "isLockedEmptyQueueState$kotlinx_coroutines_core", "isLockedEmptyQueueState", "locked", "<init>", "(Z)V", "LockCont", "LockSelect", "LockWaiter", "LockedQueue", "TryLockDesc", "UnlockOp", "kotlinx-coroutines-core"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes11.dex */
public final class MutexImpl implements Mutex, SelectClause2<Object, Mutex> {
    static final AtomicReferenceFieldUpdater _state$FU = AtomicReferenceFieldUpdater.newUpdater(MutexImpl.class, Object.class, "_state");
    volatile Object _state;

    /* JADX INFO: Access modifiers changed from: private */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0002\u0018\u00002\u00020\u0001B\u001f\u0012\b\u0010\u000f\u001a\u0004\u0018\u00010\u0002\u0012\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00060\f\u00a2\u0006\u0004\b\u0010\u0010\u0011J\u0011\u0010\u0003\u001a\u0004\u0018\u00010\u0002H\u0016\u00a2\u0006\u0004\b\u0003\u0010\u0004J\u0017\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0002H\u0016\u00a2\u0006\u0004\b\u0007\u0010\bJ\u000f\u0010\n\u001a\u00020\tH\u0016\u00a2\u0006\u0004\b\n\u0010\u000bR\u001c\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00060\f8\u0006@\u0007X\u0087\u0004\u00a2\u0006\u0006\n\u0004\b\r\u0010\u000e\u00a8\u0006\u0012"}, d2 = {"Lkotlinx/coroutines/sync/MutexImpl$LockCont;", "Lkotlinx/coroutines/sync/MutexImpl$LockWaiter;", "", "tryResumeLockWaiter", "()Ljava/lang/Object;", "token", "", "completeResumeLockWaiter", "(Ljava/lang/Object;)V", "", "toString", "()Ljava/lang/String;", "Lkotlinx/coroutines/CancellableContinuation;", EtModelMaker.KEY_CONT, "Lkotlinx/coroutines/CancellableContinuation;", BankCardConstants.KEY_OWNER, "<init>", "(Ljava/lang/Object;Lkotlinx/coroutines/CancellableContinuation;)V", "kotlinx-coroutines-core"}, k = 1, mv = {1, 4, 0})
    /* renamed from: kotlinx.coroutines.sync.MutexImpl$LockCont  reason: from toString */
    /* loaded from: classes11.dex */
    public static final class LockCont[ extends LockWaiter {
        @JvmField
        @NotNull

        /* renamed from: cont  reason: from kotlin metadata and from toString */
        public final CancellableContinuation<Unit> ;

        /* JADX WARN: Multi-variable type inference failed */
        public LockCont[(@Nullable Object obj, @NotNull CancellableContinuation<? super Unit> cancellableContinuation) {
            super(obj);
            this. = cancellableContinuation;
        }

        @Override // kotlinx.coroutines.sync.MutexImpl.LockWaiter
        public void completeResumeLockWaiter(@NotNull Object token) {
            this..completeResume(token);
        }

        @Override // kotlinx.coroutines.internal.LockFreeLinkedListNode
        @NotNull
        public String toString() {
            return "LockCont[" + this.owner + ", " + this. + ']';
        }

        @Override // kotlinx.coroutines.sync.MutexImpl.LockWaiter
        @Nullable
        public Object tryResumeLockWaiter() {
            return CancellableContinuation.DefaultImpls.tryResume$default(this., Unit.INSTANCE, null, 2, null);
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0002\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002BN\u0012\b\u0010\u0017\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u000e\u001a\u00020\r\u0012\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00028\u00000\u0010\u0012\"\u0010\u0015\u001a\u001e\b\u0001\u0012\u0004\u0012\u00020\r\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u0014\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u0013\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u0018\u0010\u0019J\u0011\u0010\u0004\u001a\u0004\u0018\u00010\u0003H\u0016\u00a2\u0006\u0004\b\u0004\u0010\u0005J\u0017\u0010\b\u001a\u00020\u00072\u0006\u0010\u0006\u001a\u00020\u0003H\u0016\u00a2\u0006\u0004\b\b\u0010\tJ\u000f\u0010\u000b\u001a\u00020\nH\u0016\u00a2\u0006\u0004\b\u000b\u0010\fR\u0016\u0010\u000e\u001a\u00020\r8\u0006@\u0007X\u0087\u0004\u00a2\u0006\u0006\n\u0004\b\u000e\u0010\u000fR\u001c\u0010\u0011\u001a\b\u0012\u0004\u0012\u00028\u00000\u00108\u0006@\u0007X\u0087\u0004\u00a2\u0006\u0006\n\u0004\b\u0011\u0010\u0012R5\u0010\u0015\u001a\u001e\b\u0001\u0012\u0004\u0012\u00020\r\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u0014\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u00138\u0006@\u0007X\u0087\u0004\u00f8\u0001\u0000\u00a2\u0006\u0006\n\u0004\b\u0015\u0010\u0016\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u001a"}, d2 = {"Lkotlinx/coroutines/sync/MutexImpl$LockSelect;", "R", "Lkotlinx/coroutines/sync/MutexImpl$LockWaiter;", "", "tryResumeLockWaiter", "()Ljava/lang/Object;", "token", "", "completeResumeLockWaiter", "(Ljava/lang/Object;)V", "", "toString", "()Ljava/lang/String;", "Lkotlinx/coroutines/sync/Mutex;", "mutex", "Lkotlinx/coroutines/sync/Mutex;", "Lkotlinx/coroutines/selects/SelectInstance;", "select", "Lkotlinx/coroutines/selects/SelectInstance;", "Lkotlin/Function2;", "Lkotlin/coroutines/Continuation;", JDReactConstant.BLOCK, "Lkotlin/jvm/functions/Function2;", BankCardConstants.KEY_OWNER, "<init>", "(Ljava/lang/Object;Lkotlinx/coroutines/sync/Mutex;Lkotlinx/coroutines/selects/SelectInstance;Lkotlin/jvm/functions/Function2;)V", "kotlinx-coroutines-core"}, k = 1, mv = {1, 4, 0})
    /* renamed from: kotlinx.coroutines.sync.MutexImpl$LockSelect  reason: from toString */
    /* loaded from: classes11.dex */
    private static final class LockSelect[<R> extends LockWaiter {
        @JvmField
        @NotNull
        public final Function2<Mutex, Continuation<? super R>, Object> block;
        @JvmField
        @NotNull

        /* renamed from: mutex  reason: from kotlin metadata and from toString */
        public final Mutex ;
        @JvmField
        @NotNull

        /* renamed from: select  reason: from kotlin metadata and from toString */
        public final SelectInstance<R> ;

        /* JADX WARN: Multi-variable type inference failed */
        public LockSelect[(@Nullable Object obj, @NotNull Mutex mutex, @NotNull SelectInstance<? super R> selectInstance, @NotNull Function2<? super Mutex, ? super Continuation<? super R>, ? extends Object> function2) {
            super(obj);
            this. = mutex;
            this. = selectInstance;
            this.block = function2;
        }

        @Override // kotlinx.coroutines.sync.MutexImpl.LockWaiter
        public void completeResumeLockWaiter(@NotNull Object token) {
            Symbol symbol;
            if (DebugKt.getASSERTIONS_ENABLED()) {
                symbol = MutexKt.SELECT_SUCCESS;
                if (!(token == symbol)) {
                    throw new AssertionError();
                }
            }
            ContinuationKt.startCoroutine(this.block, this., this..getCompletion());
        }

        @Override // kotlinx.coroutines.internal.LockFreeLinkedListNode
        @NotNull
        public String toString() {
            return "LockSelect[" + this.owner + ", " + this. + ", " + this. + ']';
        }

        @Override // kotlinx.coroutines.sync.MutexImpl.LockWaiter
        @Nullable
        public Object tryResumeLockWaiter() {
            Symbol symbol;
            if (this..trySelect()) {
                symbol = MutexKt.SELECT_SUCCESS;
                return symbol;
            }
            return null;
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\t\b\"\u0018\u00002\u00020\u00012\u00020\u0002B\u0011\u0012\b\u0010\f\u001a\u0004\u0018\u00010\u0006\u00a2\u0006\u0004\b\u000e\u0010\u000bJ\r\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\u0004\b\u0004\u0010\u0005J\u0011\u0010\u0007\u001a\u0004\u0018\u00010\u0006H&\u00a2\u0006\u0004\b\u0007\u0010\bJ\u0017\u0010\n\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\u0006H&\u00a2\u0006\u0004\b\n\u0010\u000bR\u0018\u0010\f\u001a\u0004\u0018\u00010\u00068\u0006@\u0007X\u0087\u0004\u00a2\u0006\u0006\n\u0004\b\f\u0010\r\u00a8\u0006\u000f"}, d2 = {"Lkotlinx/coroutines/sync/MutexImpl$LockWaiter;", "Lkotlinx/coroutines/internal/LockFreeLinkedListNode;", "Lkotlinx/coroutines/DisposableHandle;", "", "dispose", "()V", "", "tryResumeLockWaiter", "()Ljava/lang/Object;", "token", "completeResumeLockWaiter", "(Ljava/lang/Object;)V", BankCardConstants.KEY_OWNER, "Ljava/lang/Object;", "<init>", "kotlinx-coroutines-core"}, k = 1, mv = {1, 4, 0})
    /* loaded from: classes11.dex */
    private static abstract class LockWaiter extends LockFreeLinkedListNode implements DisposableHandle {
        @JvmField
        @Nullable
        public final Object owner;

        public LockWaiter(@Nullable Object obj) {
            this.owner = obj;
        }

        public abstract void completeResumeLockWaiter(@NotNull Object token);

        @Override // kotlinx.coroutines.DisposableHandle
        public final void dispose() {
            remove();
        }

        @Nullable
        public abstract Object tryResumeLockWaiter();
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0005\b\u0002\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u00a2\u0006\u0004\b\b\u0010\tJ\u000f\u0010\u0003\u001a\u00020\u0002H\u0016\u00a2\u0006\u0004\b\u0003\u0010\u0004R\u0016\u0010\u0006\u001a\u00020\u00058\u0006@\u0006X\u0087\u000e\u00a2\u0006\u0006\n\u0004\b\u0006\u0010\u0007\u00a8\u0006\n"}, d2 = {"Lkotlinx/coroutines/sync/MutexImpl$LockedQueue;", "Lkotlinx/coroutines/internal/LockFreeLinkedListHead;", "", "toString", "()Ljava/lang/String;", "", BankCardConstants.KEY_OWNER, "Ljava/lang/Object;", "<init>", "(Ljava/lang/Object;)V", "kotlinx-coroutines-core"}, k = 1, mv = {1, 4, 0})
    /* renamed from: kotlinx.coroutines.sync.MutexImpl$LockedQueue  reason: from toString */
    /* loaded from: classes11.dex */
    public static final class LockedQueue[ extends LockFreeLinkedListHead {
        @JvmField
        @NotNull

        /* renamed from: owner  reason: from kotlin metadata and from toString */
        public Object LockedQueue[;

        public LockedQueue[(@NotNull Object obj) {
            this.LockedQueue[ = obj;
        }

        @Override // kotlinx.coroutines.internal.LockFreeLinkedListNode
        @NotNull
        public String toString() {
            return "LockedQueue[" + this.LockedQueue[ + ']';
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0002\u0018\u00002\u00020\u0001:\u0001\u0012B\u0019\u0012\u0006\u0010\u000e\u001a\u00020\r\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\u0004\u00a2\u0006\u0004\b\u0010\u0010\u0011J\u001d\u0010\u0005\u001a\u0004\u0018\u00010\u00042\n\u0010\u0003\u001a\u0006\u0012\u0002\b\u00030\u0002H\u0016\u00a2\u0006\u0004\b\u0005\u0010\u0006J%\u0010\t\u001a\u00020\b2\n\u0010\u0003\u001a\u0006\u0012\u0002\b\u00030\u00022\b\u0010\u0007\u001a\u0004\u0018\u00010\u0004H\u0016\u00a2\u0006\u0004\b\t\u0010\nR\u0018\u0010\u000b\u001a\u0004\u0018\u00010\u00048\u0006@\u0007X\u0087\u0004\u00a2\u0006\u0006\n\u0004\b\u000b\u0010\fR\u0016\u0010\u000e\u001a\u00020\r8\u0006@\u0007X\u0087\u0004\u00a2\u0006\u0006\n\u0004\b\u000e\u0010\u000f\u00a8\u0006\u0013"}, d2 = {"Lkotlinx/coroutines/sync/MutexImpl$TryLockDesc;", "Lkotlinx/coroutines/internal/AtomicDesc;", "Lkotlinx/coroutines/internal/AtomicOp;", "op", "", JDReactConstant.PREPARE, "(Lkotlinx/coroutines/internal/AtomicOp;)Ljava/lang/Object;", "failure", "", "complete", "(Lkotlinx/coroutines/internal/AtomicOp;Ljava/lang/Object;)V", BankCardConstants.KEY_OWNER, "Ljava/lang/Object;", "Lkotlinx/coroutines/sync/MutexImpl;", "mutex", "Lkotlinx/coroutines/sync/MutexImpl;", "<init>", "(Lkotlinx/coroutines/sync/MutexImpl;Ljava/lang/Object;)V", "PrepareOp", "kotlinx-coroutines-core"}, k = 1, mv = {1, 4, 0})
    /* loaded from: classes11.dex */
    private static final class TryLockDesc extends AtomicDesc {
        @JvmField
        @NotNull
        public final MutexImpl mutex;
        @JvmField
        @Nullable
        public final Object owner;

        @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0082\u0004\u0018\u00002\u00020\u0001B\u0013\u0012\n\u0010\u0007\u001a\u0006\u0012\u0002\b\u00030\u0006\u00a2\u0006\u0004\b\u000b\u0010\fJ\u001b\u0010\u0004\u001a\u0004\u0018\u00010\u00022\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002H\u0016\u00a2\u0006\u0004\b\u0004\u0010\u0005R \u0010\u0007\u001a\u0006\u0012\u0002\b\u00030\u00068\u0016@\u0016X\u0096\u0004\u00a2\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\t\u0010\n\u00a8\u0006\r"}, d2 = {"Lkotlinx/coroutines/sync/MutexImpl$TryLockDesc$PrepareOp;", "Lkotlinx/coroutines/internal/OpDescriptor;", "", "affected", "perform", "(Ljava/lang/Object;)Ljava/lang/Object;", "Lkotlinx/coroutines/internal/AtomicOp;", "atomicOp", "Lkotlinx/coroutines/internal/AtomicOp;", "getAtomicOp", "()Lkotlinx/coroutines/internal/AtomicOp;", "<init>", "(Lkotlinx/coroutines/sync/MutexImpl$TryLockDesc;Lkotlinx/coroutines/internal/AtomicOp;)V", "kotlinx-coroutines-core"}, k = 1, mv = {1, 4, 0})
        /* loaded from: classes11.dex */
        private final class PrepareOp extends OpDescriptor {
            @NotNull
            private final AtomicOp<?> atomicOp;

            public PrepareOp(@NotNull AtomicOp<?> atomicOp) {
                this.atomicOp = atomicOp;
            }

            @Override // kotlinx.coroutines.internal.OpDescriptor
            @NotNull
            public AtomicOp<?> getAtomicOp() {
                return this.atomicOp;
            }

            @Override // kotlinx.coroutines.internal.OpDescriptor
            @Nullable
            public Object perform(@Nullable Object affected) {
                Object atomicOp = getAtomicOp().isDecided() ? MutexKt.EMPTY_UNLOCKED : getAtomicOp();
                if (affected != null) {
                    MutexImpl._state$FU.compareAndSet((MutexImpl) affected, this, atomicOp);
                    return null;
                }
                throw new TypeCastException("null cannot be cast to non-null type kotlinx.coroutines.sync.MutexImpl");
            }
        }

        public TryLockDesc(@NotNull MutexImpl mutexImpl, @Nullable Object obj) {
            this.mutex = mutexImpl;
            this.owner = obj;
        }

        @Override // kotlinx.coroutines.internal.AtomicDesc
        public void complete(@NotNull AtomicOp<?> op, @Nullable Object failure) {
            Empty[ r4;
            if (failure != null) {
                r4 = MutexKt.EMPTY_UNLOCKED;
            } else {
                Object obj = this.owner;
                r4 = obj == null ? MutexKt.EMPTY_LOCKED : new Empty[(obj);
            }
            MutexImpl._state$FU.compareAndSet(this.mutex, op, r4);
        }

        @Override // kotlinx.coroutines.internal.AtomicDesc
        @Nullable
        public Object prepare(@NotNull AtomicOp<?> op) {
            Empty[ r2;
            Symbol symbol;
            PrepareOp prepareOp = new PrepareOp(op);
            MutexImpl mutexImpl = this.mutex;
            AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = MutexImpl._state$FU;
            r2 = MutexKt.EMPTY_UNLOCKED;
            if (!atomicReferenceFieldUpdater.compareAndSet(mutexImpl, r2, prepareOp)) {
                symbol = MutexKt.LOCK_FAIL;
                return symbol;
            }
            return prepareOp.perform(this.mutex);
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u000f\u0012\u0006\u0010\f\u001a\u00020\u000b\u00a2\u0006\u0004\b\u000e\u0010\u000fJ\u0019\u0010\u0005\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0016\u00a2\u0006\u0004\b\u0005\u0010\u0006J!\u0010\t\u001a\u00020\b2\u0006\u0010\u0003\u001a\u00020\u00022\b\u0010\u0007\u001a\u0004\u0018\u00010\u0004H\u0016\u00a2\u0006\u0004\b\t\u0010\nR\u0016\u0010\f\u001a\u00020\u000b8\u0006@\u0007X\u0087\u0004\u00a2\u0006\u0006\n\u0004\b\f\u0010\r\u00a8\u0006\u0010"}, d2 = {"Lkotlinx/coroutines/sync/MutexImpl$UnlockOp;", "Lkotlinx/coroutines/internal/AtomicOp;", "Lkotlinx/coroutines/sync/MutexImpl;", "affected", "", JDReactConstant.PREPARE, "(Lkotlinx/coroutines/sync/MutexImpl;)Ljava/lang/Object;", "failure", "", "complete", "(Lkotlinx/coroutines/sync/MutexImpl;Ljava/lang/Object;)V", "Lkotlinx/coroutines/sync/MutexImpl$LockedQueue;", "queue", "Lkotlinx/coroutines/sync/MutexImpl$LockedQueue;", "<init>", "(Lkotlinx/coroutines/sync/MutexImpl$LockedQueue;)V", "kotlinx-coroutines-core"}, k = 1, mv = {1, 4, 0})
    /* loaded from: classes11.dex */
    private static final class UnlockOp extends AtomicOp<MutexImpl> {
        @JvmField
        @NotNull
        public final LockedQueue[ queue;

        public UnlockOp(@NotNull LockedQueue[ r1) {
            this.queue = r1;
        }

        @Override // kotlinx.coroutines.internal.AtomicOp
        public void complete(@NotNull MutexImpl affected, @Nullable Object failure) {
            MutexImpl._state$FU.compareAndSet(affected, this, failure == null ? MutexKt.EMPTY_UNLOCKED : this.queue);
        }

        @Override // kotlinx.coroutines.internal.AtomicOp
        @Nullable
        public Object prepare(@NotNull MutexImpl affected) {
            Symbol symbol;
            if (this.queue.isEmpty()) {
                return null;
            }
            symbol = MutexKt.UNLOCK_FAIL;
            return symbol;
        }
    }

    public MutexImpl(boolean z) {
        this._state = z ? MutexKt.EMPTY_LOCKED : MutexKt.EMPTY_UNLOCKED;
    }

    @Override // kotlinx.coroutines.sync.Mutex
    @NotNull
    public SelectClause2<Object, Mutex> getOnLock() {
        return this;
    }

    @Override // kotlinx.coroutines.sync.Mutex
    public boolean holdsLock(@NotNull Object owner) {
        Object obj = this._state;
        if (obj instanceof Empty[) {
            if (((Empty[) obj).Empty[ == owner) {
                return true;
            }
        } else if ((obj instanceof LockedQueue[) && ((LockedQueue[) obj).LockedQueue[ == owner) {
            return true;
        }
        return false;
    }

    @Override // kotlinx.coroutines.sync.Mutex
    public boolean isLocked() {
        Symbol symbol;
        while (true) {
            Object obj = this._state;
            if (obj instanceof Empty[) {
                Object obj2 = ((Empty[) obj).Empty[;
                symbol = MutexKt.UNLOCKED;
                return obj2 != symbol;
            } else if (obj instanceof LockedQueue[) {
                return true;
            } else {
                if (!(obj instanceof OpDescriptor)) {
                    throw new IllegalStateException(("Illegal state " + obj).toString());
                }
                ((OpDescriptor) obj).perform(this);
            }
        }
    }

    public final boolean isLockedEmptyQueueState$kotlinx_coroutines_core() {
        Object obj = this._state;
        return (obj instanceof LockedQueue[) && ((LockedQueue[) obj).isEmpty();
    }

    @Override // kotlinx.coroutines.sync.Mutex
    @Nullable
    public Object lock(@Nullable Object obj, @NotNull Continuation<? super Unit> continuation) {
        Object coroutine_suspended;
        if (tryLock(obj)) {
            return Unit.INSTANCE;
        }
        Object lockSuspend = lockSuspend(obj, continuation);
        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        return lockSuspend == coroutine_suspended ? lockSuspend : Unit.INSTANCE;
    }

    @Nullable
    final /* synthetic */ Object lockSuspend(@Nullable final Object obj, @NotNull Continuation<? super Unit> continuation) {
        Continuation intercepted;
        Symbol symbol;
        Object coroutine_suspended;
        intercepted = IntrinsicsKt__IntrinsicsJvmKt.intercepted(continuation);
        final CancellableContinuationImpl orCreateCancellableContinuation = CancellableContinuationKt.getOrCreateCancellableContinuation(intercepted);
        final LockCont[ r11 = new LockCont[(obj, orCreateCancellableContinuation);
        while (true) {
            final Object obj2 = this._state;
            if (obj2 instanceof Empty[) {
                Empty[ r0 = (Empty[) obj2;
                Object obj3 = r0.Empty[;
                symbol = MutexKt.UNLOCKED;
                if (obj3 != symbol) {
                    _state$FU.compareAndSet(this, obj2, new LockedQueue[(r0.Empty[));
                } else {
                    if (_state$FU.compareAndSet(this, obj2, obj == null ? MutexKt.EMPTY_LOCKED : new Empty[(obj))) {
                        Unit unit = Unit.INSTANCE;
                        Result.Companion companion = Result.INSTANCE;
                        orCreateCancellableContinuation.resumeWith(Result.m200constructorimpl(unit));
                        break;
                    }
                }
            } else if (obj2 instanceof LockedQueue[) {
                LockedQueue[ r12 = (LockedQueue[) obj2;
                boolean z = false;
                if (r12.LockedQueue[ != obj) {
                    LockFreeLinkedListNode.CondAddOp condAddOp = new LockFreeLinkedListNode.CondAddOp(r11) { // from class: kotlinx.coroutines.sync.MutexImpl$lockSuspend$$inlined$suspendAtomicCancellableCoroutineReusable$lambda$1
                        @Override // kotlinx.coroutines.internal.AtomicOp
                        @Nullable
                        public Object prepare(@NotNull LockFreeLinkedListNode affected) {
                            if (this._state == obj2) {
                                return null;
                            }
                            return LockFreeLinkedListKt.getCONDITION_FALSE();
                        }
                    };
                    while (true) {
                        int tryCondAddNext = r12.getPrevNode().tryCondAddNext(r11, r12, condAddOp);
                        if (tryCondAddNext == 1) {
                            z = true;
                            break;
                        } else if (tryCondAddNext == 2) {
                            break;
                        }
                    }
                    if (z) {
                        CancellableContinuationKt.removeOnCancellation(orCreateCancellableContinuation, r11);
                        break;
                    }
                } else {
                    throw new IllegalStateException(("Already locked by " + obj).toString());
                }
            } else if (!(obj2 instanceof OpDescriptor)) {
                throw new IllegalStateException(("Illegal state " + obj2).toString());
            } else {
                ((OpDescriptor) obj2).perform(this);
            }
        }
        Object result = orCreateCancellableContinuation.getResult();
        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (result == coroutine_suspended) {
            DebugProbes.probeCoroutineSuspended(continuation);
        }
        return result;
    }

    @Override // kotlinx.coroutines.selects.SelectClause2
    public <R> void registerSelectClause2(@NotNull SelectInstance<? super R> select, @Nullable Object owner, @NotNull Function2<? super Mutex, ? super Continuation<? super R>, ? extends Object> block) {
        Symbol symbol;
        Symbol symbol2;
        while (!select.isSelected()) {
            final Object obj = this._state;
            if (obj instanceof Empty[) {
                Empty[ r1 = (Empty[) obj;
                Object obj2 = r1.Empty[;
                symbol = MutexKt.UNLOCKED;
                if (obj2 != symbol) {
                    _state$FU.compareAndSet(this, obj, new LockedQueue[(r1.Empty[));
                } else {
                    Object performAtomicTrySelect = select.performAtomicTrySelect(new TryLockDesc(this, owner));
                    if (performAtomicTrySelect == null) {
                        UndispatchedKt.startCoroutineUnintercepted(block, this, select.getCompletion());
                        return;
                    } else if (performAtomicTrySelect == SelectKt.getALREADY_SELECTED()) {
                        return;
                    } else {
                        symbol2 = MutexKt.LOCK_FAIL;
                        if (performAtomicTrySelect != symbol2 && performAtomicTrySelect != AtomicKt.RETRY_ATOMIC) {
                            throw new IllegalStateException(("performAtomicTrySelect(TryLockDesc) returned " + performAtomicTrySelect).toString());
                        }
                    }
                }
            } else if (obj instanceof LockedQueue[) {
                LockedQueue[ r12 = (LockedQueue[) obj;
                boolean z = false;
                if (r12.LockedQueue[ != owner) {
                    final LockSelect[ r2 = new LockSelect[(owner, this, select, block);
                    LockFreeLinkedListNode.CondAddOp condAddOp = new LockFreeLinkedListNode.CondAddOp(r2) { // from class: kotlinx.coroutines.sync.MutexImpl$registerSelectClause2$$inlined$addLastIf$1
                        @Override // kotlinx.coroutines.internal.AtomicOp
                        @Nullable
                        public Object prepare(@NotNull LockFreeLinkedListNode affected) {
                            if (this._state == obj) {
                                return null;
                            }
                            return LockFreeLinkedListKt.getCONDITION_FALSE();
                        }
                    };
                    while (true) {
                        int tryCondAddNext = r12.getPrevNode().tryCondAddNext(r2, r12, condAddOp);
                        if (tryCondAddNext == 1) {
                            z = true;
                            break;
                        } else if (tryCondAddNext == 2) {
                            break;
                        }
                    }
                    if (z) {
                        select.disposeOnSelect(r2);
                        return;
                    }
                } else {
                    throw new IllegalStateException(("Already locked by " + owner).toString());
                }
            } else if (!(obj instanceof OpDescriptor)) {
                throw new IllegalStateException(("Illegal state " + obj).toString());
            } else {
                ((OpDescriptor) obj).perform(this);
            }
        }
    }

    @NotNull
    public String toString() {
        while (true) {
            Object obj = this._state;
            if (obj instanceof Empty[) {
                return "Mutex[" + ((Empty[) obj).Empty[ + ']';
            } else if (!(obj instanceof OpDescriptor)) {
                if (!(obj instanceof LockedQueue[)) {
                    throw new IllegalStateException(("Illegal state " + obj).toString());
                }
                return "Mutex[" + ((LockedQueue[) obj).LockedQueue[ + ']';
            } else {
                ((OpDescriptor) obj).perform(this);
            }
        }
    }

    @Override // kotlinx.coroutines.sync.Mutex
    public boolean tryLock(@Nullable Object owner) {
        Symbol symbol;
        while (true) {
            Object obj = this._state;
            if (obj instanceof Empty[) {
                Object obj2 = ((Empty[) obj).Empty[;
                symbol = MutexKt.UNLOCKED;
                if (obj2 != symbol) {
                    return false;
                }
                if (_state$FU.compareAndSet(this, obj, owner == null ? MutexKt.EMPTY_LOCKED : new Empty[(owner))) {
                    return true;
                }
            } else if (obj instanceof LockedQueue[) {
                if (((LockedQueue[) obj).LockedQueue[ != owner) {
                    return false;
                }
                throw new IllegalStateException(("Already locked by " + owner).toString());
            } else if (!(obj instanceof OpDescriptor)) {
                throw new IllegalStateException(("Illegal state " + obj).toString());
            } else {
                ((OpDescriptor) obj).perform(this);
            }
        }
    }

    @Override // kotlinx.coroutines.sync.Mutex
    public void unlock(@Nullable Object owner) {
        Empty[ r2;
        Symbol symbol;
        while (true) {
            Object obj = this._state;
            if (obj instanceof Empty[) {
                if (owner == null) {
                    Object obj2 = ((Empty[) obj).Empty[;
                    symbol = MutexKt.UNLOCKED;
                    if (!(obj2 != symbol)) {
                        throw new IllegalStateException("Mutex is not locked".toString());
                    }
                } else {
                    Empty[ r1 = (Empty[) obj;
                    if (!(r1.Empty[ == owner)) {
                        throw new IllegalStateException(("Mutex is locked by " + r1.Empty[ + " but expected " + owner).toString());
                    }
                }
                AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = _state$FU;
                r2 = MutexKt.EMPTY_UNLOCKED;
                if (atomicReferenceFieldUpdater.compareAndSet(this, obj, r2)) {
                    return;
                }
            } else if (obj instanceof OpDescriptor) {
                ((OpDescriptor) obj).perform(this);
            } else if (obj instanceof LockedQueue[) {
                if (owner != null) {
                    LockedQueue[ r12 = (LockedQueue[) obj;
                    if (!(r12.LockedQueue[ == owner)) {
                        throw new IllegalStateException(("Mutex is locked by " + r12.LockedQueue[ + " but expected " + owner).toString());
                    }
                }
                LockedQueue[ r13 = (LockedQueue[) obj;
                LockFreeLinkedListNode removeFirstOrNull = r13.removeFirstOrNull();
                if (removeFirstOrNull == null) {
                    UnlockOp unlockOp = new UnlockOp(r13);
                    if (_state$FU.compareAndSet(this, obj, unlockOp) && unlockOp.perform(this) == null) {
                        return;
                    }
                } else {
                    LockWaiter lockWaiter = (LockWaiter) removeFirstOrNull;
                    Object tryResumeLockWaiter = lockWaiter.tryResumeLockWaiter();
                    if (tryResumeLockWaiter != null) {
                        Object obj3 = lockWaiter.owner;
                        if (obj3 == null) {
                            obj3 = MutexKt.LOCKED;
                        }
                        r13.LockedQueue[ = obj3;
                        lockWaiter.completeResumeLockWaiter(tryResumeLockWaiter);
                        return;
                    }
                }
            } else {
                throw new IllegalStateException(("Illegal state " + obj).toString());
            }
        }
    }
}

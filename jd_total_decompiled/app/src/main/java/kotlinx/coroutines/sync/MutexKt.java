package kotlinx.coroutines.sync;

import com.jdcn.biz.client.BankCardConstants;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.InlineMarker;
import kotlinx.coroutines.internal.Symbol;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000,\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u000e\u001a\u0017\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0001\u001a\u00020\u0000\u00a2\u0006\u0004\b\u0003\u0010\u0004\u001aD\u0010\n\u001a\u00028\u0000\"\u0004\b\u0000\u0010\u0005*\u00020\u00022\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00062\f\u0010\t\u001a\b\u0012\u0004\u0012\u00028\u00000\bH\u0086H\u00f8\u0001\u0000\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0002 \u0001\u00a2\u0006\u0004\b\n\u0010\u000b\"\u001c\u0010\r\u001a\u00020\f8\u0002@\u0003X\u0083\u0004\u00a2\u0006\f\n\u0004\b\r\u0010\u000e\u0012\u0004\b\u000f\u0010\u0010\"\u001c\u0010\u0011\u001a\u00020\f8\u0002@\u0003X\u0083\u0004\u00a2\u0006\f\n\u0004\b\u0011\u0010\u000e\u0012\u0004\b\u0012\u0010\u0010\"\u001c\u0010\u0014\u001a\u00020\u00138\u0002@\u0003X\u0083\u0004\u00a2\u0006\f\n\u0004\b\u0014\u0010\u0015\u0012\u0004\b\u0016\u0010\u0010\"\u001c\u0010\u0017\u001a\u00020\f8\u0002@\u0003X\u0083\u0004\u00a2\u0006\f\n\u0004\b\u0017\u0010\u000e\u0012\u0004\b\u0018\u0010\u0010\"\u001c\u0010\u0019\u001a\u00020\f8\u0002@\u0003X\u0083\u0004\u00a2\u0006\f\n\u0004\b\u0019\u0010\u000e\u0012\u0004\b\u001a\u0010\u0010\"\u001c\u0010\u001b\u001a\u00020\f8\u0002@\u0003X\u0083\u0004\u00a2\u0006\f\n\u0004\b\u001b\u0010\u000e\u0012\u0004\b\u001c\u0010\u0010\"\u001c\u0010\u001d\u001a\u00020\f8\u0002@\u0003X\u0083\u0004\u00a2\u0006\f\n\u0004\b\u001d\u0010\u000e\u0012\u0004\b\u001e\u0010\u0010\"\u001c\u0010\u001f\u001a\u00020\u00138\u0002@\u0003X\u0083\u0004\u00a2\u0006\f\n\u0004\b\u001f\u0010\u0015\u0012\u0004\b \u0010\u0010\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006!"}, d2 = {"", "locked", "Lkotlinx/coroutines/sync/Mutex;", "Mutex", "(Z)Lkotlinx/coroutines/sync/Mutex;", "T", "", BankCardConstants.KEY_OWNER, "Lkotlin/Function0;", "action", "withLock", "(Lkotlinx/coroutines/sync/Mutex;Ljava/lang/Object;Lkotlin/jvm/functions/Function0;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Lkotlinx/coroutines/internal/Symbol;", "ENQUEUE_FAIL", "Lkotlinx/coroutines/internal/Symbol;", "ENQUEUE_FAIL$annotations", "()V", "LOCKED", "LOCKED$annotations", "Lkotlinx/coroutines/sync/Empty;", "EMPTY_UNLOCKED", "Lkotlinx/coroutines/sync/Empty;", "EMPTY_UNLOCKED$annotations", "SELECT_SUCCESS", "SELECT_SUCCESS$annotations", "UNLOCK_FAIL", "UNLOCK_FAIL$annotations", "UNLOCKED", "UNLOCKED$annotations", "LOCK_FAIL", "LOCK_FAIL$annotations", "EMPTY_LOCKED", "EMPTY_LOCKED$annotations", "kotlinx-coroutines-core"}, k = 2, mv = {1, 4, 0})
/* loaded from: classes11.dex */
public final class MutexKt {
    private static final Empty[ EMPTY_LOCKED;
    private static final Empty[ EMPTY_UNLOCKED;
    private static final Symbol LOCKED;
    private static final Symbol UNLOCKED;
    private static final Symbol LOCK_FAIL = new Symbol("LOCK_FAIL");
    private static final Symbol ENQUEUE_FAIL = new Symbol("ENQUEUE_FAIL");
    private static final Symbol UNLOCK_FAIL = new Symbol("UNLOCK_FAIL");
    private static final Symbol SELECT_SUCCESS = new Symbol("SELECT_SUCCESS");

    static {
        Symbol symbol = new Symbol("LOCKED");
        LOCKED = symbol;
        Symbol symbol2 = new Symbol("UNLOCKED");
        UNLOCKED = symbol2;
        EMPTY_LOCKED = new Empty[(symbol);
        EMPTY_UNLOCKED = new Empty[(symbol2);
    }

    private static /* synthetic */ void EMPTY_LOCKED$annotations() {
    }

    private static /* synthetic */ void EMPTY_UNLOCKED$annotations() {
    }

    private static /* synthetic */ void ENQUEUE_FAIL$annotations() {
    }

    private static /* synthetic */ void LOCKED$annotations() {
    }

    private static /* synthetic */ void LOCK_FAIL$annotations() {
    }

    @NotNull
    public static final Mutex Mutex(boolean z) {
        return new MutexImpl(z);
    }

    public static /* synthetic */ Mutex Mutex$default(boolean z, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            z = false;
        }
        return Mutex(z);
    }

    private static /* synthetic */ void SELECT_SUCCESS$annotations() {
    }

    private static /* synthetic */ void UNLOCKED$annotations() {
    }

    private static /* synthetic */ void UNLOCK_FAIL$annotations() {
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0023  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x003c  */
    @Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final <T> Object withLock(@NotNull Mutex mutex, @Nullable Object obj, @NotNull Function0<? extends T> function0, @NotNull Continuation<? super T> continuation) {
        MutexKt$withLock$1 mutexKt$withLock$1;
        Object coroutine_suspended;
        int i2;
        try {
            if (continuation instanceof MutexKt$withLock$1) {
                mutexKt$withLock$1 = (MutexKt$withLock$1) continuation;
                int i3 = mutexKt$withLock$1.label;
                if ((i3 & Integer.MIN_VALUE) != 0) {
                    mutexKt$withLock$1.label = i3 - Integer.MIN_VALUE;
                    Object obj2 = mutexKt$withLock$1.result;
                    coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    i2 = mutexKt$withLock$1.label;
                    if (i2 != 0) {
                        ResultKt.throwOnFailure(obj2);
                        mutexKt$withLock$1.L$0 = mutex;
                        mutexKt$withLock$1.L$1 = obj;
                        mutexKt$withLock$1.L$2 = function0;
                        mutexKt$withLock$1.label = 1;
                        if (mutex.lock(obj, mutexKt$withLock$1) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    } else if (i2 != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    } else {
                        function0 = (Function0) mutexKt$withLock$1.L$2;
                        obj = mutexKt$withLock$1.L$1;
                        mutex = (Mutex) mutexKt$withLock$1.L$0;
                        ResultKt.throwOnFailure(obj2);
                    }
                    return function0.invoke();
                }
            }
            return function0.invoke();
        } finally {
            InlineMarker.finallyStart(1);
            mutex.unlock(obj);
            InlineMarker.finallyEnd(1);
        }
        mutexKt$withLock$1 = new MutexKt$withLock$1(continuation);
        Object obj22 = mutexKt$withLock$1.result;
        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i2 = mutexKt$withLock$1.label;
        if (i2 != 0) {
        }
    }

    @Nullable
    private static final Object withLock$$forInline(@NotNull Mutex mutex, @Nullable Object obj, @NotNull Function0 function0, @NotNull Continuation continuation) {
        InlineMarker.mark(0);
        mutex.lock(obj, continuation);
        InlineMarker.mark(2);
        InlineMarker.mark(1);
        try {
            return function0.invoke();
        } finally {
            InlineMarker.finallyStart(1);
            mutex.unlock(obj);
            InlineMarker.finallyEnd(1);
        }
    }

    public static /* synthetic */ Object withLock$default(Mutex mutex, Object obj, Function0 function0, Continuation continuation, int i2, Object obj2) {
        if ((i2 & 1) != 0) {
            obj = null;
        }
        InlineMarker.mark(0);
        mutex.lock(obj, continuation);
        InlineMarker.mark(2);
        InlineMarker.mark(1);
        try {
            return function0.invoke();
        } finally {
            InlineMarker.finallyStart(1);
            mutex.unlock(obj);
            InlineMarker.finallyEnd(1);
        }
    }
}

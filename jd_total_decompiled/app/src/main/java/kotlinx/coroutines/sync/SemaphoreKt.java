package kotlinx.coroutines.sync;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.InlineMarker;
import kotlinx.coroutines.CancellableContinuation;
import kotlinx.coroutines.internal.Symbol;
import kotlinx.coroutines.internal.SystemPropsKt__SystemProps_commonKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000>\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\f\u001a\u001f\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0001\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0000\u00a2\u0006\u0004\b\u0004\u0010\u0005\u001a8\u0010\t\u001a\u00028\u0000\"\u0004\b\u0000\u0010\u0006*\u00020\u00032\f\u0010\b\u001a\b\u0012\u0004\u0012\u00028\u00000\u0007H\u0086H\u00f8\u0001\u0000\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0001 \u0001\u00a2\u0006\u0004\b\t\u0010\n\u001a\u0019\u0010\u000e\u001a\u00020\r*\b\u0012\u0004\u0012\u00020\f0\u000bH\u0002\u00a2\u0006\u0004\b\u000e\u0010\u000f\u001a!\u0010\u0014\u001a\u00020\u00122\u0006\u0010\u0011\u001a\u00020\u00102\b\u0010\u0013\u001a\u0004\u0018\u00010\u0012H\u0002\u00a2\u0006\u0004\b\u0014\u0010\u0015\"\u001c\u0010\u0016\u001a\u00020\u00008\u0002@\u0003X\u0083\u0004\u00a2\u0006\f\n\u0004\b\u0016\u0010\u0017\u0012\u0004\b\u0018\u0010\u0019\"\u001c\u0010\u001b\u001a\u00020\u001a8\u0002@\u0003X\u0083\u0004\u00a2\u0006\f\n\u0004\b\u001b\u0010\u001c\u0012\u0004\b\u001d\u0010\u0019\"\u001c\u0010\u001e\u001a\u00020\u001a8\u0002@\u0003X\u0083\u0004\u00a2\u0006\f\n\u0004\b\u001e\u0010\u001c\u0012\u0004\b\u001f\u0010\u0019\"\u001c\u0010 \u001a\u00020\u00008\u0002@\u0003X\u0083\u0004\u00a2\u0006\f\n\u0004\b \u0010\u0017\u0012\u0004\b!\u0010\u0019\"\u001c\u0010\"\u001a\u00020\u001a8\u0002@\u0003X\u0083\u0004\u00a2\u0006\f\n\u0004\b\"\u0010\u001c\u0012\u0004\b#\u0010\u0019\"\u001c\u0010$\u001a\u00020\u001a8\u0002@\u0003X\u0083\u0004\u00a2\u0006\f\n\u0004\b$\u0010\u001c\u0012\u0004\b%\u0010\u0019\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006&"}, d2 = {"", "permits", "acquiredPermits", "Lkotlinx/coroutines/sync/Semaphore;", "Semaphore", "(II)Lkotlinx/coroutines/sync/Semaphore;", "T", "Lkotlin/Function0;", "action", "withPermit", "(Lkotlinx/coroutines/sync/Semaphore;Lkotlin/jvm/functions/Function0;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Lkotlinx/coroutines/CancellableContinuation;", "", "", "tryResume", "(Lkotlinx/coroutines/CancellableContinuation;)Z", "", "id", "Lkotlinx/coroutines/sync/SemaphoreSegment;", "prev", "createSegment", "(JLkotlinx/coroutines/sync/SemaphoreSegment;)Lkotlinx/coroutines/sync/SemaphoreSegment;", "MAX_SPIN_CYCLES", "I", "MAX_SPIN_CYCLES$annotations", "()V", "Lkotlinx/coroutines/internal/Symbol;", "PERMIT", "Lkotlinx/coroutines/internal/Symbol;", "PERMIT$annotations", "BROKEN", "BROKEN$annotations", "SEGMENT_SIZE", "SEGMENT_SIZE$annotations", "CANCELLED", "CANCELLED$annotations", "TAKEN", "TAKEN$annotations", "kotlinx-coroutines-core"}, k = 2, mv = {1, 4, 0})
/* loaded from: classes11.dex */
public final class SemaphoreKt {
    private static final Symbol BROKEN;
    private static final Symbol CANCELLED;
    private static final int MAX_SPIN_CYCLES;
    private static final Symbol PERMIT;
    private static final int SEGMENT_SIZE;
    private static final Symbol TAKEN;

    static {
        int systemProp$default;
        int systemProp$default2;
        systemProp$default = SystemPropsKt__SystemProps_commonKt.systemProp$default("kotlinx.coroutines.semaphore.maxSpinCycles", 100, 0, 0, 12, (Object) null);
        MAX_SPIN_CYCLES = systemProp$default;
        PERMIT = new Symbol("PERMIT");
        TAKEN = new Symbol("TAKEN");
        BROKEN = new Symbol("BROKEN");
        CANCELLED = new Symbol("CANCELLED");
        systemProp$default2 = SystemPropsKt__SystemProps_commonKt.systemProp$default("kotlinx.coroutines.semaphore.segmentSize", 16, 0, 0, 12, (Object) null);
        SEGMENT_SIZE = systemProp$default2;
    }

    private static /* synthetic */ void BROKEN$annotations() {
    }

    private static /* synthetic */ void CANCELLED$annotations() {
    }

    private static /* synthetic */ void MAX_SPIN_CYCLES$annotations() {
    }

    private static /* synthetic */ void PERMIT$annotations() {
    }

    private static /* synthetic */ void SEGMENT_SIZE$annotations() {
    }

    @NotNull
    public static final Semaphore Semaphore(int i2, int i3) {
        return new SemaphoreImpl(i2, i3);
    }

    public static /* synthetic */ Semaphore Semaphore$default(int i2, int i3, int i4, Object obj) {
        if ((i4 & 2) != 0) {
            i3 = 0;
        }
        return Semaphore(i2, i3);
    }

    private static /* synthetic */ void TAKEN$annotations() {
    }

    public static final SemaphoreSegment[id= createSegment(long j2, SemaphoreSegment[id= r4) {
        return new SemaphoreSegment[id=(j2, r4, 0);
    }

    public static final boolean tryResume(@NotNull CancellableContinuation<? super Unit> cancellableContinuation) {
        Object tryResume$default = CancellableContinuation.DefaultImpls.tryResume$default(cancellableContinuation, Unit.INSTANCE, null, 2, null);
        if (tryResume$default != null) {
            cancellableContinuation.completeResume(tryResume$default);
            return true;
        }
        return false;
    }

    /* JADX WARN: Removed duplicated region for block: B:60:0x0023  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x003a  */
    @Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final <T> Object withPermit(@NotNull Semaphore semaphore, @NotNull Function0<? extends T> function0, @NotNull Continuation<? super T> continuation) {
        SemaphoreKt$withPermit$1 semaphoreKt$withPermit$1;
        Object coroutine_suspended;
        int i2;
        try {
            if (continuation instanceof SemaphoreKt$withPermit$1) {
                semaphoreKt$withPermit$1 = (SemaphoreKt$withPermit$1) continuation;
                int i3 = semaphoreKt$withPermit$1.label;
                if ((i3 & Integer.MIN_VALUE) != 0) {
                    semaphoreKt$withPermit$1.label = i3 - Integer.MIN_VALUE;
                    Object obj = semaphoreKt$withPermit$1.result;
                    coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    i2 = semaphoreKt$withPermit$1.label;
                    if (i2 != 0) {
                        ResultKt.throwOnFailure(obj);
                        semaphoreKt$withPermit$1.L$0 = semaphore;
                        semaphoreKt$withPermit$1.L$1 = function0;
                        semaphoreKt$withPermit$1.label = 1;
                        if (semaphore.acquire(semaphoreKt$withPermit$1) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    } else if (i2 != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    } else {
                        function0 = (Function0) semaphoreKt$withPermit$1.L$1;
                        semaphore = (Semaphore) semaphoreKt$withPermit$1.L$0;
                        ResultKt.throwOnFailure(obj);
                    }
                    return function0.invoke();
                }
            }
            return function0.invoke();
        } finally {
            InlineMarker.finallyStart(1);
            semaphore.release();
            InlineMarker.finallyEnd(1);
        }
        semaphoreKt$withPermit$1 = new SemaphoreKt$withPermit$1(continuation);
        Object obj2 = semaphoreKt$withPermit$1.result;
        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i2 = semaphoreKt$withPermit$1.label;
        if (i2 != 0) {
        }
    }

    @Nullable
    private static final Object withPermit$$forInline(@NotNull Semaphore semaphore, @NotNull Function0 function0, @NotNull Continuation continuation) {
        InlineMarker.mark(0);
        semaphore.acquire(continuation);
        InlineMarker.mark(2);
        InlineMarker.mark(1);
        try {
            return function0.invoke();
        } finally {
            InlineMarker.finallyStart(1);
            semaphore.release();
            InlineMarker.finallyEnd(1);
        }
    }
}

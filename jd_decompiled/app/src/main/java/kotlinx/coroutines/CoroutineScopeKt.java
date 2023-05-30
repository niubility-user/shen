package kotlinx.coroutines;

import com.jingdong.amon.router.annotation.AnnoConst;
import com.jingdong.common.jdreactFramework.JDReactConstant;
import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugProbes;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.InlineMarker;
import kotlinx.coroutines.internal.ScopeCoroutine;
import kotlinx.coroutines.intrinsics.UndispatchedKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0004\u001a\u001c\u0010\u0003\u001a\u00020\u0000*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u0001H\u0086\u0002\u00a2\u0006\u0004\b\u0003\u0010\u0004\u001a\r\u0010\u0005\u001a\u00020\u0000\u00a2\u0006\u0004\b\u0005\u0010\u0006\u001aO\u0010\r\u001a\u00028\u0000\"\u0004\b\u0000\u0010\u00072'\u0010\f\u001a#\b\u0001\u0012\u0004\u0012\u00020\u0000\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\t\u0012\u0006\u0012\u0004\u0018\u00010\n0\b\u00a2\u0006\u0002\b\u000bH\u0086@\u00f8\u0001\u0000\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0001 \u0001\u00a2\u0006\u0004\b\r\u0010\u000e\u001a\u0015\u0010\u000f\u001a\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u0001\u00a2\u0006\u0004\b\u000f\u0010\u0010\u001a#\u0010\u0015\u001a\u00020\u0014*\u00020\u00002\u0010\b\u0002\u0010\u0013\u001a\n\u0018\u00010\u0011j\u0004\u0018\u0001`\u0012\u00a2\u0006\u0004\b\u0015\u0010\u0016\u001a%\u0010\u0015\u001a\u00020\u0014*\u00020\u00002\u0006\u0010\u0018\u001a\u00020\u00172\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u0019\u00a2\u0006\u0004\b\u0015\u0010\u001a\u001a\u0011\u0010\u001b\u001a\u00020\u0014*\u00020\u0000\u00a2\u0006\u0004\b\u001b\u0010\u001c\u001a\u0013\u0010\u001d\u001a\u00020\u0001H\u0086H\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u001d\u0010\u001e\"\u001d\u0010 \u001a\u00020\u001f*\u00020\u00008F@\u0006\u00a2\u0006\f\u0012\u0004\b\"\u0010\u001c\u001a\u0004\b \u0010!\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006#"}, d2 = {"Lkotlinx/coroutines/CoroutineScope;", "Lkotlin/coroutines/CoroutineContext;", AnnoConst.Constructor_Context, "plus", "(Lkotlinx/coroutines/CoroutineScope;Lkotlin/coroutines/CoroutineContext;)Lkotlinx/coroutines/CoroutineScope;", "MainScope", "()Lkotlinx/coroutines/CoroutineScope;", "R", "Lkotlin/Function2;", "Lkotlin/coroutines/Continuation;", "", "Lkotlin/ExtensionFunctionType;", JDReactConstant.BLOCK, "coroutineScope", "(Lkotlin/jvm/functions/Function2;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "CoroutineScope", "(Lkotlin/coroutines/CoroutineContext;)Lkotlinx/coroutines/CoroutineScope;", "Ljava/util/concurrent/CancellationException;", "Lkotlinx/coroutines/CancellationException;", "cause", "", "cancel", "(Lkotlinx/coroutines/CoroutineScope;Ljava/util/concurrent/CancellationException;)V", "", "message", "", "(Lkotlinx/coroutines/CoroutineScope;Ljava/lang/String;Ljava/lang/Throwable;)V", "ensureActive", "(Lkotlinx/coroutines/CoroutineScope;)V", "currentCoroutineContext", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "", "isActive", "(Lkotlinx/coroutines/CoroutineScope;)Z", "isActive$annotations", "kotlinx-coroutines-core"}, k = 2, mv = {1, 4, 0})
/* loaded from: classes11.dex */
public final class CoroutineScopeKt {
    @NotNull
    public static final CoroutineScope CoroutineScope(@NotNull CoroutineContext coroutineContext) {
        CompletableJob Job$default;
        if (coroutineContext.get(Job.INSTANCE) == null) {
            Job$default = JobKt__JobKt.Job$default((Job) null, 1, (Object) null);
            coroutineContext = coroutineContext.plus(Job$default);
        }
        return new kotlinx.coroutines.internal.CoroutineScope(coroutineContext);
    }

    @NotNull
    public static final CoroutineScope MainScope() {
        return new kotlinx.coroutines.internal.CoroutineScope(SupervisorKt.SupervisorJob$default((Job) null, 1, (Object) null).plus(Dispatchers.getMain()));
    }

    public static final void cancel(@NotNull CoroutineScope coroutineScope, @Nullable CancellationException cancellationException) {
        Job job = (Job) coroutineScope.getCoroutineContext().get(Job.INSTANCE);
        if (job != null) {
            job.cancel(cancellationException);
            return;
        }
        throw new IllegalStateException(("Scope cannot be cancelled because it does not have a job: " + coroutineScope).toString());
    }

    public static /* synthetic */ void cancel$default(CoroutineScope coroutineScope, CancellationException cancellationException, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            cancellationException = null;
        }
        cancel(coroutineScope, cancellationException);
    }

    @Nullable
    public static final <R> Object coroutineScope(@NotNull Function2<? super CoroutineScope, ? super Continuation<? super R>, ? extends Object> function2, @NotNull Continuation<? super R> continuation) {
        Object coroutine_suspended;
        ScopeCoroutine scopeCoroutine = new ScopeCoroutine(continuation.get$context(), continuation);
        Object startUndispatchedOrReturn = UndispatchedKt.startUndispatchedOrReturn(scopeCoroutine, scopeCoroutine, function2);
        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (startUndispatchedOrReturn == coroutine_suspended) {
            DebugProbes.probeCoroutineSuspended(continuation);
        }
        return startUndispatchedOrReturn;
    }

    @Nullable
    public static final Object currentCoroutineContext(@NotNull Continuation<? super CoroutineContext> continuation) {
        return continuation.get$context();
    }

    @Nullable
    private static final Object currentCoroutineContext$$forInline(@NotNull Continuation continuation) {
        InlineMarker.mark(3);
        Continuation continuation2 = null;
        return continuation2.get$context();
    }

    public static final void ensureActive(@NotNull CoroutineScope coroutineScope) {
        JobKt.ensureActive(coroutineScope.getCoroutineContext());
    }

    public static final boolean isActive(@NotNull CoroutineScope coroutineScope) {
        Job job = (Job) coroutineScope.getCoroutineContext().get(Job.INSTANCE);
        if (job != null) {
            return job.isActive();
        }
        return true;
    }

    public static /* synthetic */ void isActive$annotations(CoroutineScope coroutineScope) {
    }

    @NotNull
    public static final CoroutineScope plus(@NotNull CoroutineScope coroutineScope, @NotNull CoroutineContext coroutineContext) {
        return new kotlinx.coroutines.internal.CoroutineScope(coroutineScope.getCoroutineContext().plus(coroutineContext));
    }

    public static /* synthetic */ void cancel$default(CoroutineScope coroutineScope, String str, Throwable th, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            th = null;
        }
        cancel(coroutineScope, str, th);
    }

    public static final void cancel(@NotNull CoroutineScope coroutineScope, @NotNull String str, @Nullable Throwable th) {
        cancel(coroutineScope, ExceptionsKt.CancellationException(str, th));
    }
}

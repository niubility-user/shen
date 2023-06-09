package kotlinx.coroutines.intrinsics;

import com.jingdong.common.jdreactFramework.JDReactConstant;
import com.tencent.open.SocialConstants;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsJvmKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.DispatchedContinuationKt;
import kotlinx.coroutines.InternalCoroutinesApi;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000(\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a@\u0010\u0006\u001a\u00020\u0005\"\u0004\b\u0000\u0010\u0000*\u0018\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u0002\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u00012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0002H\u0007\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u0006\u0010\u0007\u001aT\u0010\u0006\u001a\u00020\u0005\"\u0004\b\u0000\u0010\b\"\u0004\b\u0001\u0010\u0000*\u001e\b\u0001\u0012\u0004\u0012\u00028\u0000\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00010\u0002\u0012\u0006\u0012\u0004\u0018\u00010\u00030\t2\u0006\u0010\n\u001a\u00028\u00002\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00010\u0002H\u0000\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u0006\u0010\u000b\u001a%\u0010\u0006\u001a\u00020\u0005*\b\u0012\u0004\u0012\u00020\u00050\u00022\n\u0010\f\u001a\u0006\u0012\u0002\b\u00030\u0002H\u0000\u00a2\u0006\u0004\b\u0006\u0010\r\u001a*\u0010\u0010\u001a\u00020\u00052\n\u0010\u0004\u001a\u0006\u0012\u0002\b\u00030\u00022\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00050\u000eH\u0082\b\u00a2\u0006\u0004\b\u0010\u0010\u0011\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u0012"}, d2 = {"T", "Lkotlin/Function1;", "Lkotlin/coroutines/Continuation;", "", "completion", "", "startCoroutineCancellable", "(Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)V", "R", "Lkotlin/Function2;", SocialConstants.PARAM_RECEIVER, "(Lkotlin/jvm/functions/Function2;Ljava/lang/Object;Lkotlin/coroutines/Continuation;)V", "fatalCompletion", "(Lkotlin/coroutines/Continuation;Lkotlin/coroutines/Continuation;)V", "Lkotlin/Function0;", JDReactConstant.BLOCK, "runSafely", "(Lkotlin/coroutines/Continuation;Lkotlin/jvm/functions/Function0;)V", "kotlinx-coroutines-core"}, k = 2, mv = {1, 4, 0})
/* loaded from: classes11.dex */
public final class CancellableKt {
    private static final void runSafely(Continuation<?> continuation, Function0<Unit> function0) {
        try {
            function0.invoke();
        } catch (Throwable th) {
            Result.Companion companion = Result.INSTANCE;
            continuation.resumeWith(Result.m200constructorimpl(ResultKt.createFailure(th)));
        }
    }

    @InternalCoroutinesApi
    public static final <T> void startCoroutineCancellable(@NotNull Function1<? super Continuation<? super T>, ? extends Object> function1, @NotNull Continuation<? super T> continuation) {
        Continuation<Unit> createCoroutineUnintercepted;
        Continuation intercepted;
        try {
            createCoroutineUnintercepted = IntrinsicsKt__IntrinsicsJvmKt.createCoroutineUnintercepted(function1, continuation);
            intercepted = IntrinsicsKt__IntrinsicsJvmKt.intercepted(createCoroutineUnintercepted);
            Result.Companion companion = Result.INSTANCE;
            DispatchedContinuationKt.resumeCancellableWith(intercepted, Result.m200constructorimpl(Unit.INSTANCE));
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            continuation.resumeWith(Result.m200constructorimpl(ResultKt.createFailure(th)));
        }
    }

    public static final <R, T> void startCoroutineCancellable(@NotNull Function2<? super R, ? super Continuation<? super T>, ? extends Object> function2, R r, @NotNull Continuation<? super T> continuation) {
        Continuation<Unit> createCoroutineUnintercepted;
        Continuation intercepted;
        try {
            createCoroutineUnintercepted = IntrinsicsKt__IntrinsicsJvmKt.createCoroutineUnintercepted(function2, r, continuation);
            intercepted = IntrinsicsKt__IntrinsicsJvmKt.intercepted(createCoroutineUnintercepted);
            Result.Companion companion = Result.INSTANCE;
            DispatchedContinuationKt.resumeCancellableWith(intercepted, Result.m200constructorimpl(Unit.INSTANCE));
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            continuation.resumeWith(Result.m200constructorimpl(ResultKt.createFailure(th)));
        }
    }

    public static final void startCoroutineCancellable(@NotNull Continuation<? super Unit> continuation, @NotNull Continuation<?> continuation2) {
        Continuation intercepted;
        try {
            intercepted = IntrinsicsKt__IntrinsicsJvmKt.intercepted(continuation);
            Result.Companion companion = Result.INSTANCE;
            DispatchedContinuationKt.resumeCancellableWith(intercepted, Result.m200constructorimpl(Unit.INSTANCE));
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            continuation2.resumeWith(Result.m200constructorimpl(ResultKt.createFailure(th)));
        }
    }
}

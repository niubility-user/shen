package kotlin.coroutines;

import com.jingdong.amon.router.annotation.AnnoConst;
import com.jingdong.common.jdreactFramework.JDReactConstant;
import com.jingdong.manto.jsapi.refact.live.JsApiLivePlayer;
import com.tencent.open.SocialConstants;
import jpbury.t;
import kotlin.Metadata;
import kotlin.NotImplementedError;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.SinceKotlin;
import kotlin.Unit;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsJvmKt;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugProbes;
import kotlin.internal.InlineOnly;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.InlineMarker;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000>\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u000e\u001a(\u0010\u0004\u001a\u00020\u0003\"\u0004\b\u0000\u0010\u0000*\b\u0012\u0004\u0012\u00028\u00000\u00012\u0006\u0010\u0002\u001a\u00028\u0000H\u0087\b\u00a2\u0006\u0004\b\u0004\u0010\u0005\u001a(\u0010\b\u001a\u00020\u0003\"\u0004\b\u0000\u0010\u0000*\b\u0012\u0004\u0012\u00028\u00000\u00012\u0006\u0010\u0007\u001a\u00020\u0006H\u0087\b\u00a2\u0006\u0004\b\b\u0010\t\u001aC\u0010\u000f\u001a\b\u0012\u0004\u0012\u00028\u00000\u0001\"\u0004\b\u0000\u0010\u00002\u0006\u0010\u000b\u001a\u00020\n2\u001a\b\u0004\u0010\u000e\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\r\u0012\u0004\u0012\u00020\u00030\fH\u0087\b\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u000f\u0010\u0010\u001aF\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00030\u0001\"\u0004\b\u0000\u0010\u0000*\u0018\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u0001\u0012\u0006\u0012\u0004\u0018\u00010\u00110\f2\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00028\u00000\u0001H\u0007\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u0013\u0010\u0014\u001a_\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00030\u0001\"\u0004\b\u0000\u0010\u0015\"\u0004\b\u0001\u0010\u0000*#\b\u0001\u0012\u0004\u0012\u00028\u0000\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00010\u0001\u0012\u0006\u0012\u0004\u0018\u00010\u00110\u0016\u00a2\u0006\u0002\b\u00172\u0006\u0010\u0018\u001a\u00028\u00002\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00028\u00010\u0001H\u0007\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u0013\u0010\u0019\u001a@\u0010\u001a\u001a\u00020\u0003\"\u0004\b\u0000\u0010\u0000*\u0018\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u0001\u0012\u0006\u0012\u0004\u0018\u00010\u00110\f2\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00028\u00000\u0001H\u0007\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u001a\u0010\u001b\u001aY\u0010\u001a\u001a\u00020\u0003\"\u0004\b\u0000\u0010\u0015\"\u0004\b\u0001\u0010\u0000*#\b\u0001\u0012\u0004\u0012\u00028\u0000\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00010\u0001\u0012\u0006\u0012\u0004\u0018\u00010\u00110\u0016\u00a2\u0006\u0002\b\u00172\u0006\u0010\u0018\u001a\u00028\u00002\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00028\u00010\u0001H\u0007\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u001a\u0010\u001c\u001a5\u0010\u001e\u001a\u00028\u0000\"\u0004\b\u0000\u0010\u00002\u001a\b\u0004\u0010\u001d\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u0001\u0012\u0004\u0012\u00020\u00030\fH\u0087H\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u001e\u0010\u001f\"\u001d\u0010$\u001a\u00020\n8\u00c6\u0002@\u0007X\u0087\u0004\u00a2\u0006\f\u0012\u0004\b\"\u0010#\u001a\u0004\b \u0010!\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006%"}, d2 = {"T", "Lkotlin/coroutines/Continuation;", "value", "", JsApiLivePlayer.CM_RESUME, "(Lkotlin/coroutines/Continuation;Ljava/lang/Object;)V", "", t.f20145j, "resumeWithException", "(Lkotlin/coroutines/Continuation;Ljava/lang/Throwable;)V", "Lkotlin/coroutines/CoroutineContext;", AnnoConst.Constructor_Context, "Lkotlin/Function1;", "Lkotlin/Result;", "resumeWith", "Continuation", "(Lkotlin/coroutines/CoroutineContext;Lkotlin/jvm/functions/Function1;)Lkotlin/coroutines/Continuation;", "", "completion", "createCoroutine", "(Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Lkotlin/coroutines/Continuation;", "R", "Lkotlin/Function2;", "Lkotlin/ExtensionFunctionType;", SocialConstants.PARAM_RECEIVER, "(Lkotlin/jvm/functions/Function2;Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Lkotlin/coroutines/Continuation;", "startCoroutine", "(Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)V", "(Lkotlin/jvm/functions/Function2;Ljava/lang/Object;Lkotlin/coroutines/Continuation;)V", JDReactConstant.BLOCK, "suspendCoroutine", "(Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getCoroutineContext", "()Lkotlin/coroutines/CoroutineContext;", "coroutineContext$annotations", "()V", "coroutineContext", "kotlin-stdlib"}, k = 2, mv = {1, 4, 0})
/* loaded from: classes11.dex */
public final class ContinuationKt {
    @SinceKotlin(version = "1.3")
    @InlineOnly
    private static final <T> Continuation<T> Continuation(final CoroutineContext coroutineContext, final Function1<? super Result<? extends T>, Unit> function1) {
        return new Continuation<T>() { // from class: kotlin.coroutines.ContinuationKt$Continuation$1
            @Override // kotlin.coroutines.Continuation
            @NotNull
            /* renamed from: getContext  reason: from getter */
            public CoroutineContext get$context() {
                return CoroutineContext.this;
            }

            @Override // kotlin.coroutines.Continuation
            public void resumeWith(@NotNull Object result) {
                function1.invoke(Result.m199boximpl(result));
            }
        };
    }

    @SinceKotlin(version = "1.3")
    @InlineOnly
    public static /* synthetic */ void coroutineContext$annotations() {
    }

    @SinceKotlin(version = "1.3")
    @NotNull
    public static final <T> Continuation<Unit> createCoroutine(@NotNull Function1<? super Continuation<? super T>, ? extends Object> function1, @NotNull Continuation<? super T> continuation) {
        Continuation<Unit> createCoroutineUnintercepted;
        Continuation intercepted;
        Object coroutine_suspended;
        createCoroutineUnintercepted = IntrinsicsKt__IntrinsicsJvmKt.createCoroutineUnintercepted(function1, continuation);
        intercepted = IntrinsicsKt__IntrinsicsJvmKt.intercepted(createCoroutineUnintercepted);
        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        return new SafeContinuation for (intercepted, coroutine_suspended);
    }

    private static final CoroutineContext getCoroutineContext() {
        throw new NotImplementedError("Implemented as intrinsic");
    }

    @SinceKotlin(version = "1.3")
    @InlineOnly
    private static final <T> void resume(@NotNull Continuation<? super T> continuation, T t) {
        Result.Companion companion = Result.INSTANCE;
        continuation.resumeWith(Result.m200constructorimpl(t));
    }

    @SinceKotlin(version = "1.3")
    @InlineOnly
    private static final <T> void resumeWithException(@NotNull Continuation<? super T> continuation, Throwable th) {
        Result.Companion companion = Result.INSTANCE;
        continuation.resumeWith(Result.m200constructorimpl(ResultKt.createFailure(th)));
    }

    @SinceKotlin(version = "1.3")
    public static final <T> void startCoroutine(@NotNull Function1<? super Continuation<? super T>, ? extends Object> function1, @NotNull Continuation<? super T> continuation) {
        Continuation<Unit> createCoroutineUnintercepted;
        Continuation intercepted;
        createCoroutineUnintercepted = IntrinsicsKt__IntrinsicsJvmKt.createCoroutineUnintercepted(function1, continuation);
        intercepted = IntrinsicsKt__IntrinsicsJvmKt.intercepted(createCoroutineUnintercepted);
        Unit unit = Unit.INSTANCE;
        Result.Companion companion = Result.INSTANCE;
        intercepted.resumeWith(Result.m200constructorimpl(unit));
    }

    @SinceKotlin(version = "1.3")
    @InlineOnly
    private static final <T> Object suspendCoroutine(Function1<? super Continuation<? super T>, Unit> function1, Continuation<? super T> continuation) {
        Continuation intercepted;
        Object coroutine_suspended;
        InlineMarker.mark(0);
        intercepted = IntrinsicsKt__IntrinsicsJvmKt.intercepted(continuation);
        SafeContinuation for  r0 = new SafeContinuation for (intercepted);
        function1.invoke(r0);
        Object orThrow = r0.getOrThrow();
        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (orThrow == coroutine_suspended) {
            DebugProbes.probeCoroutineSuspended(continuation);
        }
        InlineMarker.mark(1);
        return orThrow;
    }

    @SinceKotlin(version = "1.3")
    @NotNull
    public static final <R, T> Continuation<Unit> createCoroutine(@NotNull Function2<? super R, ? super Continuation<? super T>, ? extends Object> function2, R r, @NotNull Continuation<? super T> continuation) {
        Continuation<Unit> createCoroutineUnintercepted;
        Continuation intercepted;
        Object coroutine_suspended;
        createCoroutineUnintercepted = IntrinsicsKt__IntrinsicsJvmKt.createCoroutineUnintercepted(function2, r, continuation);
        intercepted = IntrinsicsKt__IntrinsicsJvmKt.intercepted(createCoroutineUnintercepted);
        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        return new SafeContinuation for (intercepted, coroutine_suspended);
    }

    @SinceKotlin(version = "1.3")
    public static final <R, T> void startCoroutine(@NotNull Function2<? super R, ? super Continuation<? super T>, ? extends Object> function2, R r, @NotNull Continuation<? super T> continuation) {
        Continuation<Unit> createCoroutineUnintercepted;
        Continuation intercepted;
        createCoroutineUnintercepted = IntrinsicsKt__IntrinsicsJvmKt.createCoroutineUnintercepted(function2, r, continuation);
        intercepted = IntrinsicsKt__IntrinsicsJvmKt.intercepted(createCoroutineUnintercepted);
        Unit unit = Unit.INSTANCE;
        Result.Companion companion = Result.INSTANCE;
        intercepted.resumeWith(Result.m200constructorimpl(unit));
    }
}

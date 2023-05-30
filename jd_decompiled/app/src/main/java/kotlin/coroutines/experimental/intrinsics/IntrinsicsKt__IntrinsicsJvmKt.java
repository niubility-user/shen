package kotlin.coroutines.experimental.intrinsics;

import com.jingdong.common.jdreactFramework.JDReactConstant;
import com.tencent.open.SocialConstants;
import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.coroutines.experimental.Continuation;
import kotlin.coroutines.experimental.CoroutineContext;
import kotlin.coroutines.experimental.jvm.internal.CoroutineImpl;
import kotlin.coroutines.experimental.jvm.internal.CoroutineIntrinsics;
import kotlin.internal.InlineOnly;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.TypeIntrinsics;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: Access modifiers changed from: package-private */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000.\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\n\u001aC\u0010\u0005\u001a\u0004\u0018\u00010\u0003\"\u0004\b\u0000\u0010\u0000*\u0018\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u0002\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u00012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0002H\u0087\b\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u0005\u0010\u0006\u001a\\\u0010\u0005\u001a\u0004\u0018\u00010\u0003\"\u0004\b\u0000\u0010\u0007\"\u0004\b\u0001\u0010\u0000*#\b\u0001\u0012\u0004\u0012\u00028\u0000\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00010\u0002\u0012\u0006\u0012\u0004\u0018\u00010\u00030\b\u00a2\u0006\u0002\b\t2\u0006\u0010\n\u001a\u00028\u00002\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00010\u0002H\u0087\b\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u0005\u0010\u000b\u001aF\u0010\r\u001a\b\u0012\u0004\u0012\u00020\f0\u0002\"\u0004\b\u0000\u0010\u0000*\u0018\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u0002\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u00012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0002H\u0007\u00f8\u0001\u0000\u00a2\u0006\u0004\b\r\u0010\u000e\u001a_\u0010\r\u001a\b\u0012\u0004\u0012\u00020\f0\u0002\"\u0004\b\u0000\u0010\u0007\"\u0004\b\u0001\u0010\u0000*#\b\u0001\u0012\u0004\u0012\u00028\u0000\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00010\u0002\u0012\u0006\u0012\u0004\u0018\u00010\u00030\b\u00a2\u0006\u0002\b\t2\u0006\u0010\n\u001a\u00028\u00002\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00010\u0002H\u0007\u00f8\u0001\u0000\u00a2\u0006\u0004\b\r\u0010\u000f\u001a<\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\f0\u0002\"\u0004\b\u0000\u0010\u00002\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u00022\u0010\b\u0004\u0010\u0011\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u0010H\u0082\b\u00a2\u0006\u0004\b\u0012\u0010\u0013\"\u001c\u0010\u0019\u001a\u00020\u00038F@\u0007X\u0087\u0004\u00a2\u0006\f\u0012\u0004\b\u0017\u0010\u0018\u001a\u0004\b\u0015\u0010\u0016\u0082\u0002\u0004\n\u0002\b\t\u00a8\u0006\u001a"}, d2 = {"T", "Lkotlin/Function1;", "Lkotlin/coroutines/experimental/Continuation;", "", "completion", "startCoroutineUninterceptedOrReturn", "(Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/experimental/Continuation;)Ljava/lang/Object;", "R", "Lkotlin/Function2;", "Lkotlin/ExtensionFunctionType;", SocialConstants.PARAM_RECEIVER, "(Lkotlin/jvm/functions/Function2;Ljava/lang/Object;Lkotlin/coroutines/experimental/Continuation;)Ljava/lang/Object;", "", "createCoroutineUnchecked", "(Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/experimental/Continuation;)Lkotlin/coroutines/experimental/Continuation;", "(Lkotlin/jvm/functions/Function2;Ljava/lang/Object;Lkotlin/coroutines/experimental/Continuation;)Lkotlin/coroutines/experimental/Continuation;", "Lkotlin/Function0;", JDReactConstant.BLOCK, "buildContinuationByInvokeCall$IntrinsicsKt__IntrinsicsJvmKt", "(Lkotlin/coroutines/experimental/Continuation;Lkotlin/jvm/functions/Function0;)Lkotlin/coroutines/experimental/Continuation;", "buildContinuationByInvokeCall", "getCOROUTINE_SUSPENDED", "()Ljava/lang/Object;", "COROUTINE_SUSPENDED$annotations", "()V", "COROUTINE_SUSPENDED", "kotlin-stdlib-coroutines"}, k = 5, mv = {1, 4, 0}, xs = "kotlin/coroutines/experimental/intrinsics/IntrinsicsKt")
/* loaded from: classes11.dex */
public class IntrinsicsKt__IntrinsicsJvmKt {
    @SinceKotlin(version = "1.1")
    public static /* synthetic */ void COROUTINE_SUSPENDED$annotations() {
    }

    @SinceKotlin(version = "1.1")
    @NotNull
    public static <T> Continuation<Unit> createCoroutineUnchecked(@NotNull final Function1<? super Continuation<? super T>, ? extends Object> function1, @NotNull final Continuation<? super T> continuation) {
        if (!(function1 instanceof CoroutineImpl)) {
            return CoroutineIntrinsics.interceptContinuationIfNeeded(continuation.getContext(), new Continuation<Unit>() { // from class: kotlin.coroutines.experimental.intrinsics.IntrinsicsKt__IntrinsicsJvmKt$createCoroutineUnchecked$$inlined$buildContinuationByInvokeCall$IntrinsicsKt__IntrinsicsJvmKt$1
                @Override // kotlin.coroutines.experimental.Continuation
                @NotNull
                public CoroutineContext getContext() {
                    return Continuation.this.getContext();
                }

                @Override // kotlin.coroutines.experimental.Continuation
                public void resumeWithException(@NotNull Throwable th) {
                    Continuation.this.resumeWithException(th);
                }

                @Override // kotlin.coroutines.experimental.Continuation
                public void resume(@NotNull Unit value) {
                    Object coroutine_suspended;
                    Continuation continuation2 = Continuation.this;
                    try {
                        Function1 function12 = function1;
                        if (function12 == null) {
                            throw new TypeCastException("null cannot be cast to non-null type (kotlin.coroutines.experimental.Continuation<T>) -> kotlin.Any?");
                        }
                        Object invoke = ((Function1) TypeIntrinsics.beforeCheckcastToFunctionOfArity(function12, 1)).invoke(continuation);
                        coroutine_suspended = IntrinsicsKt__IntrinsicsJvmKt.getCOROUTINE_SUSPENDED();
                        if (invoke != coroutine_suspended) {
                            if (continuation2 == null) {
                                throw new TypeCastException("null cannot be cast to non-null type kotlin.coroutines.experimental.Continuation<kotlin.Any?>");
                            }
                            continuation2.resume(invoke);
                        }
                    } catch (Throwable th) {
                        continuation2.resumeWithException(th);
                    }
                }
            });
        }
        Continuation<Unit> create = ((CoroutineImpl) function1).create(continuation);
        if (create != null) {
            return ((CoroutineImpl) create).getFacade();
        }
        throw new TypeCastException("null cannot be cast to non-null type kotlin.coroutines.experimental.jvm.internal.CoroutineImpl");
    }

    @NotNull
    public static Object getCOROUTINE_SUSPENDED() {
        Object coroutine_suspended;
        coroutine_suspended = kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        return coroutine_suspended;
    }

    @SinceKotlin(version = "1.1")
    @InlineOnly
    private static final <T> Object startCoroutineUninterceptedOrReturn(@NotNull Function1<? super Continuation<? super T>, ? extends Object> function1, Continuation<? super T> continuation) {
        if (function1 != null) {
            return ((Function1) TypeIntrinsics.beforeCheckcastToFunctionOfArity(function1, 1)).invoke(continuation);
        }
        throw new TypeCastException("null cannot be cast to non-null type (kotlin.coroutines.experimental.Continuation<T>) -> kotlin.Any?");
    }

    @SinceKotlin(version = "1.1")
    @InlineOnly
    private static final <R, T> Object startCoroutineUninterceptedOrReturn(@NotNull Function2<? super R, ? super Continuation<? super T>, ? extends Object> function2, R r, Continuation<? super T> continuation) {
        if (function2 != null) {
            return ((Function2) TypeIntrinsics.beforeCheckcastToFunctionOfArity(function2, 2)).invoke(r, continuation);
        }
        throw new TypeCastException("null cannot be cast to non-null type (R, kotlin.coroutines.experimental.Continuation<T>) -> kotlin.Any?");
    }

    @SinceKotlin(version = "1.1")
    @NotNull
    public static <R, T> Continuation<Unit> createCoroutineUnchecked(@NotNull final Function2<? super R, ? super Continuation<? super T>, ? extends Object> function2, final R r, @NotNull final Continuation<? super T> continuation) {
        if (!(function2 instanceof CoroutineImpl)) {
            return CoroutineIntrinsics.interceptContinuationIfNeeded(continuation.getContext(), new Continuation<Unit>() { // from class: kotlin.coroutines.experimental.intrinsics.IntrinsicsKt__IntrinsicsJvmKt$createCoroutineUnchecked$$inlined$buildContinuationByInvokeCall$IntrinsicsKt__IntrinsicsJvmKt$2
                @Override // kotlin.coroutines.experimental.Continuation
                @NotNull
                public CoroutineContext getContext() {
                    return Continuation.this.getContext();
                }

                @Override // kotlin.coroutines.experimental.Continuation
                public void resumeWithException(@NotNull Throwable th) {
                    Continuation.this.resumeWithException(th);
                }

                @Override // kotlin.coroutines.experimental.Continuation
                public void resume(@NotNull Unit value) {
                    Object coroutine_suspended;
                    Continuation continuation2 = Continuation.this;
                    try {
                        Function2 function22 = function2;
                        if (function22 == null) {
                            throw new TypeCastException("null cannot be cast to non-null type (R, kotlin.coroutines.experimental.Continuation<T>) -> kotlin.Any?");
                        }
                        Object invoke = ((Function2) TypeIntrinsics.beforeCheckcastToFunctionOfArity(function22, 2)).invoke(r, continuation);
                        coroutine_suspended = IntrinsicsKt__IntrinsicsJvmKt.getCOROUTINE_SUSPENDED();
                        if (invoke != coroutine_suspended) {
                            if (continuation2 == null) {
                                throw new TypeCastException("null cannot be cast to non-null type kotlin.coroutines.experimental.Continuation<kotlin.Any?>");
                            }
                            continuation2.resume(invoke);
                        }
                    } catch (Throwable th) {
                        continuation2.resumeWithException(th);
                    }
                }
            });
        }
        Continuation<Unit> create = ((CoroutineImpl) function2).create(r, continuation);
        if (create != null) {
            return ((CoroutineImpl) create).getFacade();
        }
        throw new TypeCastException("null cannot be cast to non-null type kotlin.coroutines.experimental.jvm.internal.CoroutineImpl");
    }
}

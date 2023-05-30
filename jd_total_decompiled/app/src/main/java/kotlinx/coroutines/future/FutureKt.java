package kotlinx.coroutines.future;

import com.jingdong.amon.router.annotation.AnnoConst;
import com.jingdong.common.jdreactFramework.JDReactConstant;
import java.util.concurrent.CancellationException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.function.BiConsumer;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsJvmKt;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugProbes;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CancellableContinuationImpl;
import kotlinx.coroutines.CompletableDeferred;
import kotlinx.coroutines.CompletableDeferredKt;
import kotlinx.coroutines.CoroutineContextKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Deferred;
import kotlinx.coroutines.ExceptionsKt;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.JobKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000H\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\u001a]\u0010\f\u001a\b\u0012\u0004\u0012\u00028\u00000\u000b\"\u0004\b\u0000\u0010\u0000*\u00020\u00012\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u00042'\u0010\n\u001a#\b\u0001\u0012\u0004\u0012\u00020\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u0007\u0012\u0006\u0012\u0004\u0018\u00010\b0\u0006\u00a2\u0006\u0002\b\t\u00f8\u0001\u0000\u00a2\u0006\u0004\b\f\u0010\r\u001a#\u0010\u000f\u001a\b\u0012\u0004\u0012\u00028\u00000\u000b\"\u0004\b\u0000\u0010\u0000*\b\u0012\u0004\u0012\u00028\u00000\u000e\u00a2\u0006\u0004\b\u000f\u0010\u0010\u001a\u0017\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00120\u000b*\u00020\u0011\u00a2\u0006\u0004\b\u000f\u0010\u0013\u001a\u001f\u0010\u0014\u001a\u00020\u0012*\u00020\u00112\n\u0010\f\u001a\u0006\u0012\u0002\b\u00030\u000bH\u0002\u00a2\u0006\u0004\b\u0014\u0010\u0015\u001a#\u0010\u0017\u001a\b\u0012\u0004\u0012\u00028\u00000\u000e\"\u0004\b\u0000\u0010\u0000*\b\u0012\u0004\u0012\u00028\u00000\u0016\u00a2\u0006\u0004\b\u0017\u0010\u0018\u001a#\u0010\u0019\u001a\u00028\u0000\"\u0004\b\u0000\u0010\u0000*\b\u0012\u0004\u0012\u00028\u00000\u0016H\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u0019\u0010\u001a\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u001b"}, d2 = {"T", "Lkotlinx/coroutines/CoroutineScope;", "Lkotlin/coroutines/CoroutineContext;", AnnoConst.Constructor_Context, "Lkotlinx/coroutines/CoroutineStart;", "start", "Lkotlin/Function2;", "Lkotlin/coroutines/Continuation;", "", "Lkotlin/ExtensionFunctionType;", JDReactConstant.BLOCK, "Ljava/util/concurrent/CompletableFuture;", "future", "(Lkotlinx/coroutines/CoroutineScope;Lkotlin/coroutines/CoroutineContext;Lkotlinx/coroutines/CoroutineStart;Lkotlin/jvm/functions/Function2;)Ljava/util/concurrent/CompletableFuture;", "Lkotlinx/coroutines/Deferred;", "asCompletableFuture", "(Lkotlinx/coroutines/Deferred;)Ljava/util/concurrent/CompletableFuture;", "Lkotlinx/coroutines/Job;", "", "(Lkotlinx/coroutines/Job;)Ljava/util/concurrent/CompletableFuture;", "setupCancellation", "(Lkotlinx/coroutines/Job;Ljava/util/concurrent/CompletableFuture;)V", "Ljava/util/concurrent/CompletionStage;", "asDeferred", "(Ljava/util/concurrent/CompletionStage;)Lkotlinx/coroutines/Deferred;", "await", "(Ljava/util/concurrent/CompletionStage;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "kotlinx-coroutines-jdk8"}, k = 2, mv = {1, 4, 0})
/* loaded from: classes11.dex */
public final class FutureKt {
    @NotNull
    public static final <T> CompletableFuture<T> asCompletableFuture(@NotNull final Deferred<? extends T> deferred) {
        final CompletableFuture<T> completableFuture = new CompletableFuture<>();
        setupCancellation(deferred, completableFuture);
        deferred.invokeOnCompletion(new Function1<Throwable, Unit>() { // from class: kotlinx.coroutines.future.FutureKt$asCompletableFuture$1
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Throwable th) {
                invoke2(th);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke  reason: avoid collision after fix types in other method */
            public final void invoke2(@Nullable Throwable th) {
                try {
                    completableFuture.complete(Deferred.this.getCompleted());
                } catch (Throwable th2) {
                    completableFuture.completeExceptionally(th2);
                }
            }
        });
        return completableFuture;
    }

    @NotNull
    public static final <T> Deferred<T> asDeferred(@NotNull CompletionStage<T> completionStage) {
        boolean z = completionStage instanceof Future;
        if (z && ((Future) completionStage).isDone()) {
            try {
                return CompletableDeferredKt.CompletableDeferred(((Future) completionStage).get());
            } catch (Throwable th) {
                ExecutionException executionException = !(th instanceof ExecutionException) ? null : th;
                ExecutionException executionException2 = th;
                if (executionException != null) {
                    Throwable cause = executionException.getCause();
                    executionException2 = th;
                    if (cause != null) {
                        executionException2 = cause;
                    }
                }
                CompletableDeferred CompletableDeferred$default = CompletableDeferredKt.CompletableDeferred$default(null, 1, null);
                CompletableDeferred$default.completeExceptionally(executionException2);
                return CompletableDeferred$default;
            }
        }
        final CompletableDeferred CompletableDeferred$default2 = CompletableDeferredKt.CompletableDeferred$default(null, 1, null);
        completionStage.whenComplete(new BiConsumer<T, Throwable>() { // from class: kotlinx.coroutines.future.FutureKt$asDeferred$2
            /* JADX WARN: Multi-variable type inference failed */
            @Override // java.util.function.BiConsumer
            public /* bridge */ /* synthetic */ void accept(Object obj, Throwable th2) {
                accept2((FutureKt$asDeferred$2<T, U>) obj, th2);
            }

            /* renamed from: accept  reason: avoid collision after fix types in other method */
            public final void accept2(T t, Throwable th2) {
                Throwable cause2;
                if (th2 == null) {
                    CompletableDeferred.this.complete(t);
                    return;
                }
                CompletableDeferred completableDeferred = CompletableDeferred.this;
                CompletionException completionException = (CompletionException) (!(th2 instanceof CompletionException) ? null : th2);
                if (completionException != null && (cause2 = completionException.getCause()) != null) {
                    th2 = cause2;
                }
                completableDeferred.completeExceptionally(th2);
            }
        });
        if (z) {
            JobKt.cancelFutureOnCompletion(CompletableDeferred$default2, (Future) completionStage);
        }
        return CompletableDeferred$default2;
    }

    @Nullable
    public static final <T> Object await(@NotNull final CompletionStage<T> completionStage, @NotNull Continuation<? super T> continuation) {
        Continuation intercepted;
        Object coroutine_suspended;
        if ((completionStage instanceof Future) && ((Future) completionStage).isDone()) {
            try {
                return ((Future) completionStage).get();
            } catch (ExecutionException e2) {
                Throwable cause = e2.getCause();
                if (cause != null) {
                    throw cause;
                }
                throw e2;
            }
        }
        intercepted = IntrinsicsKt__IntrinsicsJvmKt.intercepted(continuation);
        CancellableContinuationImpl cancellableContinuationImpl = new CancellableContinuationImpl(intercepted, 1);
        cancellableContinuationImpl.initCancellability();
        final ContinuationConsumer continuationConsumer = new ContinuationConsumer(cancellableContinuationImpl);
        completionStage.whenComplete(continuationConsumer);
        cancellableContinuationImpl.invokeOnCancellation(new Function1<Throwable, Unit>() { // from class: kotlinx.coroutines.future.FutureKt$await$$inlined$suspendCancellableCoroutine$lambda$1
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Throwable th) {
                invoke2(th);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke  reason: avoid collision after fix types in other method */
            public final void invoke2(@Nullable Throwable th) {
                CompletionStage completionStage2 = completionStage;
                if (!(completionStage2 instanceof CompletableFuture)) {
                    completionStage2 = null;
                }
                CompletableFuture completableFuture = (CompletableFuture) completionStage2;
                if (completableFuture != null) {
                    completableFuture.cancel(false);
                }
                ContinuationConsumer.this.cont = null;
            }
        });
        Object result = cancellableContinuationImpl.getResult();
        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (result == coroutine_suspended) {
            DebugProbes.probeCoroutineSuspended(continuation);
        }
        return result;
    }

    @NotNull
    public static final <T> CompletableFuture<T> future(@NotNull CoroutineScope coroutineScope, @NotNull CoroutineContext coroutineContext, @NotNull CoroutineStart coroutineStart, @NotNull Function2<? super CoroutineScope, ? super Continuation<? super T>, ? extends Object> function2) {
        if ((!coroutineStart.isLazy()) != false) {
            CoroutineContext newCoroutineContext = CoroutineContextKt.newCoroutineContext(coroutineScope, coroutineContext);
            CompletableFuture<T> completableFuture = new CompletableFuture<>();
            CompletableFutureCoroutine completableFutureCoroutine = new CompletableFutureCoroutine(newCoroutineContext, completableFuture);
            completableFuture.whenComplete((BiConsumer) completableFutureCoroutine);
            completableFutureCoroutine.start(coroutineStart, completableFutureCoroutine, function2);
            return completableFuture;
        }
        throw new IllegalArgumentException((coroutineStart + " start is not supported").toString());
    }

    public static /* synthetic */ CompletableFuture future$default(CoroutineScope coroutineScope, CoroutineContext coroutineContext, CoroutineStart coroutineStart, Function2 function2, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            coroutineContext = EmptyCoroutineContext.INSTANCE;
        }
        if ((i2 & 2) != 0) {
            coroutineStart = CoroutineStart.DEFAULT;
        }
        return future(coroutineScope, coroutineContext, coroutineStart, function2);
    }

    private static final void setupCancellation(@NotNull final Job job, CompletableFuture<?> completableFuture) {
        completableFuture.whenComplete(new BiConsumer<Object, Throwable>() { // from class: kotlinx.coroutines.future.FutureKt$setupCancellation$1
            /* JADX WARN: Multi-variable type inference failed */
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Throwable th) {
                Job job2 = Job.this;
                if (th != null) {
                    r0 = th instanceof CancellationException ? th : null;
                    if (r0 == null) {
                        r0 = ExceptionsKt.CancellationException("CompletableFuture was completed exceptionally", th);
                    }
                }
                job2.cancel(r0);
            }
        });
    }

    @NotNull
    public static final CompletableFuture<Unit> asCompletableFuture(@NotNull Job job) {
        final CompletableFuture<Unit> completableFuture = new CompletableFuture<>();
        setupCancellation(job, completableFuture);
        job.invokeOnCompletion(new Function1<Throwable, Unit>() { // from class: kotlinx.coroutines.future.FutureKt$asCompletableFuture$2
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Throwable th) {
                invoke2(th);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke  reason: avoid collision after fix types in other method */
            public final void invoke2(@Nullable Throwable th) {
                if (th == null) {
                    completableFuture.complete(Unit.INSTANCE);
                } else {
                    completableFuture.completeExceptionally(th);
                }
            }
        });
        return completableFuture;
    }
}

package kotlinx.coroutines.future;

import com.jingdong.app.mall.bundle.dolphinlib.common.util.EtModelMaker;
import java.util.concurrent.CompletionException;
import java.util.function.BiConsumer;
import jpbury.t;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.coroutines.Continuation;
import kotlin.jvm.JvmField;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0003\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0002\u0018\u0000*\u0004\b\u0000\u0010\u00012\u0012\u0012\u0006\u0012\u0004\u0018\u00018\u0000\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u0002B\u0017\u0012\u000e\u0010\n\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\t\u00a2\u0006\u0004\b\f\u0010\rJ#\u0010\u0007\u001a\u00020\u00062\b\u0010\u0004\u001a\u0004\u0018\u00018\u00002\b\u0010\u0005\u001a\u0004\u0018\u00010\u0003H\u0016\u00a2\u0006\u0004\b\u0007\u0010\bR\u001e\u0010\n\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\t8\u0006@\u0006X\u0087\u000e\u00a2\u0006\u0006\n\u0004\b\n\u0010\u000b\u00a8\u0006\u000e"}, d2 = {"Lkotlinx/coroutines/future/ContinuationConsumer;", "T", "Ljava/util/function/BiConsumer;", "", "result", t.f20145j, "", "accept", "(Ljava/lang/Object;Ljava/lang/Throwable;)V", "Lkotlin/coroutines/Continuation;", EtModelMaker.KEY_CONT, "Lkotlin/coroutines/Continuation;", "<init>", "(Lkotlin/coroutines/Continuation;)V", "kotlinx-coroutines-jdk8"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes11.dex */
final class ContinuationConsumer<T> implements BiConsumer<T, Throwable> {
    @JvmField
    @Nullable
    public volatile Continuation<? super T> cont;

    public ContinuationConsumer(@Nullable Continuation<? super T> continuation) {
        this.cont = continuation;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.function.BiConsumer
    public /* bridge */ /* synthetic */ void accept(Object obj, Throwable th) {
        accept2((ContinuationConsumer<T>) obj, th);
    }

    /* renamed from: accept  reason: avoid collision after fix types in other method */
    public void accept2(@Nullable T result, @Nullable Throwable exception) {
        Throwable cause;
        Continuation<? super T> continuation = this.cont;
        if (continuation != null) {
            if (exception == null) {
                Result.Companion companion = Result.INSTANCE;
                continuation.resumeWith(Result.m200constructorimpl(result));
                return;
            }
            CompletionException completionException = (CompletionException) (!(exception instanceof CompletionException) ? null : exception);
            if (completionException != null && (cause = completionException.getCause()) != null) {
                exception = cause;
            }
            Result.Companion companion2 = Result.INSTANCE;
            continuation.resumeWith(Result.m200constructorimpl(ResultKt.createFailure(exception)));
        }
    }
}

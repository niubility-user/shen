package rx.plugins;

import rx.Completable;
import rx.annotations.Experimental;

@Experimental
/* loaded from: classes11.dex */
public abstract class RxJavaCompletableExecutionHook {
    public Completable.CompletableOnSubscribe onCreate(Completable.CompletableOnSubscribe completableOnSubscribe) {
        return completableOnSubscribe;
    }

    public Completable.CompletableOperator onLift(Completable.CompletableOperator completableOperator) {
        return completableOperator;
    }

    public Throwable onSubscribeError(Throwable th) {
        return th;
    }

    public Completable.CompletableOnSubscribe onSubscribeStart(Completable completable, Completable.CompletableOnSubscribe completableOnSubscribe) {
        return completableOnSubscribe;
    }
}

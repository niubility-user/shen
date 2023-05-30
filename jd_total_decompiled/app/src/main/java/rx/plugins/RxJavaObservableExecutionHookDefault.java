package rx.plugins;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes11.dex */
public class RxJavaObservableExecutionHookDefault extends RxJavaObservableExecutionHook {
    private static RxJavaObservableExecutionHookDefault INSTANCE = new RxJavaObservableExecutionHookDefault();

    RxJavaObservableExecutionHookDefault() {
    }

    public static RxJavaObservableExecutionHook getInstance() {
        return INSTANCE;
    }
}

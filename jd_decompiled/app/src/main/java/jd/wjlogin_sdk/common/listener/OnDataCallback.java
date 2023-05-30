package jd.wjlogin_sdk.common.listener;

/* loaded from: classes.dex */
public abstract class OnDataCallback<T> extends OnCommonCallback {
    public OnDataCallback(AbsFailureProcessor absFailureProcessor) {
        super(absFailureProcessor);
    }

    @Override // jd.wjlogin_sdk.common.listener.OnCommonCallback
    public final void onSuccess() {
    }

    public abstract void onSuccess(T t);

    public final void onSuccessHandleInner(T t) {
        try {
            beforeHandleResult();
            onSuccess(t);
            afterHandleResult();
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public OnDataCallback() {
    }
}

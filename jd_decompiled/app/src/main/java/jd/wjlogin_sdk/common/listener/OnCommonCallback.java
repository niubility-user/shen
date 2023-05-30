package jd.wjlogin_sdk.common.listener;

import jd.wjlogin_sdk.model.ErrorResult;
import jd.wjlogin_sdk.model.FailResult;
import jd.wjlogin_sdk.util.LanguageToast;
import jd.wjlogin_sdk.util.b0;
import jd.wjlogin_sdk.util.y;

/* loaded from: classes.dex */
public abstract class OnCommonCallback {
    private AbsFailureProcessor failProcessor;

    public OnCommonCallback(AbsFailureProcessor absFailureProcessor) {
        this.failProcessor = absFailureProcessor;
    }

    private void handleResult(FailResult failResult) {
        AbsFailureProcessor absFailureProcessor = this.failProcessor;
        if (absFailureProcessor != null) {
            absFailureProcessor.onFailInner(failResult);
        } else {
            onFail(failResult);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void afterHandleResult() {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void beforeHandleResult() {
    }

    public abstract void onError(ErrorResult errorResult);

    public final void onErrorHandleInner(ErrorResult errorResult) {
        if (errorResult == null) {
            errorResult = b0.a(-9999, LanguageToast.getToastMsg(-102), new Exception(y.f19997g));
        }
        try {
            beforeHandleResult();
            onError(errorResult);
            afterHandleResult();
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public abstract void onFail(FailResult failResult);

    public final void onFailHandleInner(FailResult failResult) {
        if (failResult == null) {
            failResult = new FailResult();
        }
        try {
            beforeHandleResult();
            handleResult(failResult);
            afterHandleResult();
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public abstract void onSuccess();

    public final void onSuccessHandleInner() {
        try {
            beforeHandleResult();
            onSuccess();
            afterHandleResult();
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public OnCommonCallback() {
    }
}

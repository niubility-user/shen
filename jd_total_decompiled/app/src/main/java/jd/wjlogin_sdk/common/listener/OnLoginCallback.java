package jd.wjlogin_sdk.common.listener;

import jd.wjlogin_sdk.model.FailResult;

/* loaded from: classes.dex */
public abstract class OnLoginCallback extends OnCommonCallback {
    public OnLoginCallback(AbsFailureProcessor absFailureProcessor) {
        super(absFailureProcessor);
    }

    @Override // jd.wjlogin_sdk.common.listener.OnCommonCallback
    public void onFail(FailResult failResult) {
    }

    public OnLoginCallback() {
    }
}

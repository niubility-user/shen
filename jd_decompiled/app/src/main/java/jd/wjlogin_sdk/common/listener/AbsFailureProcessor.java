package jd.wjlogin_sdk.common.listener;

import jd.wjlogin_sdk.model.FailResult;

/* loaded from: classes11.dex */
public abstract class AbsFailureProcessor implements IFailSelfHandler {
    protected abstract void handleFailResult(FailResult failResult);

    @Override // jd.wjlogin_sdk.common.listener.IFailSelfHandler
    public final void onFailInner(FailResult failResult) {
        handleFailResult(failResult);
    }
}

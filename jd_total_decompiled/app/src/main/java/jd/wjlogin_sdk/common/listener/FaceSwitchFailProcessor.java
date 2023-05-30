package jd.wjlogin_sdk.common.listener;

import jd.wjlogin_sdk.model.FailResult;

/* loaded from: classes11.dex */
public abstract class FaceSwitchFailProcessor extends AbsFailureProcessor {
    public abstract void faceDataError(FailResult failResult);

    @Override // jd.wjlogin_sdk.common.listener.AbsFailureProcessor
    protected final void handleFailResult(FailResult failResult) {
        byte replyCode = failResult.getReplyCode();
        if (replyCode == 11 || replyCode == 12 || replyCode == 13 || replyCode == 14 || replyCode == -91 || replyCode == -90) {
            logout(failResult);
        } else if (replyCode == -79) {
            faceDataError(failResult);
        } else {
            onCommonHandler(failResult);
        }
    }

    public abstract void logout(FailResult failResult);

    public abstract void onCommonHandler(FailResult failResult);
}

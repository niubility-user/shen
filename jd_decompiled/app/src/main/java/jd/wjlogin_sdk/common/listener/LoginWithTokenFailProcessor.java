package jd.wjlogin_sdk.common.listener;

import jd.wjlogin_sdk.model.FailResult;

/* loaded from: classes11.dex */
public abstract class LoginWithTokenFailProcessor extends AbsFailureProcessor {
    @Override // jd.wjlogin_sdk.common.listener.AbsFailureProcessor
    protected final void handleFailResult(FailResult failResult) {
        byte replyCode = failResult.getReplyCode();
        if (replyCode >= Byte.MIN_VALUE && replyCode <= -113) {
            onSendMsg(failResult);
        } else {
            onCommonHandler(failResult);
        }
    }

    public abstract void onCommonHandler(FailResult failResult);

    public abstract void onSendMsg(FailResult failResult);
}

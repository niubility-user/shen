package jd.wjlogin_sdk.common.listener;

import jd.wjlogin_sdk.model.FailResult;

/* loaded from: classes11.dex */
public abstract class OnekeyRegisterFailProcessor extends AbsFailureProcessor {
    public abstract void fengkong(FailResult failResult);

    @Override // jd.wjlogin_sdk.common.listener.AbsFailureProcessor
    protected final void handleFailResult(FailResult failResult) {
        byte replyCode = failResult.getReplyCode();
        if (22 == replyCode) {
            registed(failResult);
        } else if (-119 == replyCode) {
            fengkong(failResult);
        } else if (104 == replyCode) {
            registedNotMonth(failResult);
        } else {
            onCommonHandler(failResult);
        }
    }

    public abstract void onCommonHandler(FailResult failResult);

    public abstract void registed(FailResult failResult);

    public abstract void registedNotMonth(FailResult failResult);
}

package jd.wjlogin_sdk.common.listener;

import jd.wjlogin_sdk.model.FailResult;

/* loaded from: classes11.dex */
public abstract class UnionLoginFailProcessor extends AbsFailureProcessor {
    public abstract void bindJDAccount(FailResult failResult);

    public abstract void handle0x24(FailResult failResult);

    public abstract void handle0x64(FailResult failResult);

    public abstract void handle0x6a(FailResult failResult);

    public abstract void handle0x8(FailResult failResult);

    public abstract void handleBetween0x77And0x7a(FailResult failResult);

    public abstract void handleBetween0x7bAnd0x7e(FailResult failResult);

    @Override // jd.wjlogin_sdk.common.listener.AbsFailureProcessor
    protected final void handleFailResult(FailResult failResult) {
        byte replyCode = failResult.getReplyCode();
        if (replyCode == 35 || replyCode == 37) {
            bindJDAccount(failResult);
        } else if (replyCode == 36) {
            handle0x24(failResult);
        } else if (replyCode == 106) {
            handle0x6a(failResult);
        } else if (replyCode == 100) {
            handle0x64(failResult);
        } else if (replyCode == 8) {
            handle0x8(failResult);
        } else if (replyCode >= 123 && replyCode <= 126) {
            handleBetween0x7bAnd0x7e(failResult);
        } else if (replyCode >= Byte.MIN_VALUE && replyCode <= -113 && replyCode != -125 && replyCode != -123) {
            onSendMsgWithoutDialog(failResult);
        } else if (replyCode == -125 || replyCode == -123) {
            onSendMsg(failResult);
        } else if (replyCode >= 119 && replyCode <= 122) {
            handleBetween0x77And0x7a(failResult);
        } else {
            onCommonHandler(failResult);
        }
    }

    public abstract void onCommonHandler(FailResult failResult);

    public abstract void onSendMsg(FailResult failResult);

    public abstract void onSendMsgWithoutDialog(FailResult failResult);
}

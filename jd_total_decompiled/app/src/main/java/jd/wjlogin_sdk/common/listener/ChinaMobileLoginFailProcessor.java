package jd.wjlogin_sdk.common.listener;

import jd.wjlogin_sdk.model.FailResult;

/* loaded from: classes11.dex */
public abstract class ChinaMobileLoginFailProcessor extends AbsFailureProcessor {
    public abstract void accountLocked(FailResult failResult);

    public abstract void accountNotExist(FailResult failResult);

    public abstract void handle0x24(FailResult failResult);

    public abstract void handle0x64(FailResult failResult);

    public abstract void handle0x6a(FailResult failResult);

    public abstract void handle0x8(FailResult failResult);

    public abstract void handleBetween0x77And0x7a(FailResult failResult);

    public abstract void handleBetween0x7bAnd0x7e(FailResult failResult);

    @Override // jd.wjlogin_sdk.common.listener.AbsFailureProcessor
    protected final void handleFailResult(FailResult failResult) {
        byte replyCode = failResult.getReplyCode();
        if (6 == replyCode || 7 == replyCode) {
            accountNotExist(failResult);
        } else if (replyCode == 35) {
            toRegister(failResult);
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
        } else if (replyCode >= 119 && replyCode <= 122) {
            handleBetween0x77And0x7a(failResult);
        } else if (replyCode >= Byte.MIN_VALUE && replyCode <= -113 && replyCode != -116 && replyCode != -125 && replyCode != -123) {
            onSendMsgWithoutDialog(failResult);
        } else if (replyCode == -125 || replyCode == -123) {
            onSendMsg(failResult);
        } else if (replyCode == -116) {
            voiceVerification(failResult);
        } else if (replyCode != -54 && replyCode != -49 && replyCode != -48 && replyCode != 126) {
            onCommonHandler(failResult);
        } else {
            accountLocked(failResult);
        }
    }

    public abstract void onCommonHandler(FailResult failResult);

    public abstract void onSendMsg(FailResult failResult);

    public abstract void onSendMsgWithoutDialog(FailResult failResult);

    public abstract void toRegister(FailResult failResult);

    public abstract void voiceVerification(FailResult failResult);
}

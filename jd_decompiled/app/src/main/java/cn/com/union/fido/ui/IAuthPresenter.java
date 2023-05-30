package cn.com.union.fido.ui;

import cn.com.union.fido.bean.authenticator.command.BaseResponse;

/* loaded from: classes.dex */
public interface IAuthPresenter {
    void authViewDisplay();

    byte[] getFidoCmd();

    String getOpType();

    void handlerAuthResult(int i2, BaseResponse baseResponse);

    void onAuthenticationFailed();

    void onAuthenticationHelp(CharSequence charSequence);
}

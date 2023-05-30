package cn.com.union.fido.ui;

import cn.com.union.fido.bean.authenticator.command.BaseResponse;

/* loaded from: classes.dex */
public interface IFidoModel {
    int getAuthenticatorIndex();

    BaseResponse getBaseResponse();

    byte[] getFidoCmd();

    String getOpType();

    int getResponseCoded();

    void setBaseResponse(BaseResponse baseResponse);

    void setResponseCoded(int i2);
}

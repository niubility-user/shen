package cn.com.union.fido.ui;

import cn.com.union.fido.bean.authenticator.command.BaseResponse;

/* loaded from: classes.dex */
public class FidoModel implements IFidoModel {
    private int authenticatorIndex;
    private BaseResponse baseResponse;
    private byte[] fidoCmd;
    private String opType;
    private int responseCoded = -1;

    public FidoModel(int i2, String str, byte[] bArr) {
        this.opType = null;
        this.fidoCmd = null;
        this.authenticatorIndex = i2;
        this.opType = str;
        this.fidoCmd = bArr;
    }

    @Override // cn.com.union.fido.ui.IFidoModel
    public int getAuthenticatorIndex() {
        return this.authenticatorIndex;
    }

    @Override // cn.com.union.fido.ui.IFidoModel
    public BaseResponse getBaseResponse() {
        return this.baseResponse;
    }

    @Override // cn.com.union.fido.ui.IFidoModel
    public byte[] getFidoCmd() {
        return this.fidoCmd;
    }

    @Override // cn.com.union.fido.ui.IFidoModel
    public String getOpType() {
        return this.opType;
    }

    @Override // cn.com.union.fido.ui.IFidoModel
    public int getResponseCoded() {
        return this.responseCoded;
    }

    public void setAuthenticatorIndex(int i2) {
        this.authenticatorIndex = i2;
    }

    @Override // cn.com.union.fido.ui.IFidoModel
    public void setBaseResponse(BaseResponse baseResponse) {
        this.baseResponse = baseResponse;
    }

    public void setFidoCmd(byte[] bArr) {
        this.fidoCmd = bArr;
    }

    public void setOpType(String str) {
        this.opType = str;
    }

    @Override // cn.com.union.fido.ui.IFidoModel
    public void setResponseCoded(int i2) {
        this.responseCoded = i2;
    }
}

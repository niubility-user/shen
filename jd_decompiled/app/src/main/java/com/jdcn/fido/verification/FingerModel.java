package com.jdcn.fido.verification;

/* loaded from: classes18.dex */
public class FingerModel implements IFingerModel {
    private int code = -1;
    private String response = "";

    @Override // com.jdcn.fido.verification.IFingerModel
    public String getBaseResponse() {
        return this.response;
    }

    @Override // com.jdcn.fido.verification.IFingerModel
    public int getResponseCoded() {
        return this.code;
    }

    @Override // com.jdcn.fido.verification.IFingerModel
    public void setBaseResponse(String str) {
        this.response = str;
    }

    @Override // com.jdcn.fido.verification.IFingerModel
    public void setResponseCoded(int i2) {
        this.code = i2;
    }
}

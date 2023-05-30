package com.laser.open.nfc.hw.entity;

/* loaded from: classes12.dex */
public class BaseResponse {
    private String resultCd;
    private String resultCode;
    private String resultMsg;

    public BaseResponse() {
    }

    public String getResultCd() {
        return this.resultCd;
    }

    public String getResultCode() {
        return this.resultCode;
    }

    public String getResultMsg() {
        return this.resultMsg;
    }

    public void setResultCd(String str) {
        this.resultCd = str;
    }

    public void setResultCode(String str) {
        this.resultCode = str;
    }

    public void setResultMsg(String str) {
        this.resultMsg = str;
    }

    public BaseResponse(int i2, String str) {
        this.resultCode = String.valueOf(i2);
        this.resultMsg = str;
    }

    public BaseResponse(int i2, int i3, String str) {
        this.resultCd = String.valueOf(i2);
        this.resultCode = i3 == -1 ? null : String.valueOf(i2);
        this.resultMsg = str;
    }
}

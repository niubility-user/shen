package com.jdjr.securehttp;

/* loaded from: classes18.dex */
public class JDJRResultMessage {
    private byte[] resultMsg;
    private String resutError;

    public JDJRResultMessage(byte[] bArr, String str) {
        this.resultMsg = bArr;
        this.resutError = str;
    }

    public String getErrorCode() {
        return this.resutError;
    }

    public byte[] getResult() {
        return this.resultMsg;
    }

    public String getResultString() {
        byte[] bArr = this.resultMsg;
        return (bArr == null || bArr.length == 0) ? "" : new String(this.resultMsg);
    }
}

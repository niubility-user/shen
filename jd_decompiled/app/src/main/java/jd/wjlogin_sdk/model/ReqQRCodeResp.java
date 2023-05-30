package jd.wjlogin_sdk.model;

/* loaded from: classes.dex */
public class ReqQRCodeResp {
    private byte[] qrCodeData;
    private String qrCodeKey;

    public byte[] getQrCodeData() {
        return this.qrCodeData;
    }

    public String getQrCodeKey() {
        return this.qrCodeKey;
    }

    public void setQrCodeData(byte[] bArr) {
        this.qrCodeData = bArr;
    }

    public void setQrCodeKey(String str) {
        this.qrCodeKey = str;
    }
}

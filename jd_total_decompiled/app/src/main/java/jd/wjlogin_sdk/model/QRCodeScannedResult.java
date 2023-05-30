package jd.wjlogin_sdk.model;

/* loaded from: classes.dex */
public class QRCodeScannedResult {
    private String buttonTip;
    private String qrCodeScannedTips;
    private String qrLoginInfo;
    private int qrLoginStatus;
    private byte type;
    private String url;

    public String getButtonTip() {
        return this.buttonTip;
    }

    public String getQrCodeScannedTips() {
        return this.qrCodeScannedTips;
    }

    public String getQrLoginInfo() {
        return this.qrLoginInfo;
    }

    public int getQrLoginStatus() {
        return this.qrLoginStatus;
    }

    public byte getType() {
        return this.type;
    }

    public String getUrl() {
        return this.url;
    }

    public void setButtonTip(String str) {
        this.buttonTip = str;
    }

    public void setQrCodeScannedTips(String str) {
        this.qrCodeScannedTips = str;
    }

    public void setQrLoginInfo(String str) {
        this.qrLoginInfo = str;
    }

    public void setQrLoginStatus(int i2) {
        this.qrLoginStatus = i2;
    }

    public void setType(byte b) {
        this.type = b;
    }

    public void setUrl(String str) {
        this.url = str;
    }
}

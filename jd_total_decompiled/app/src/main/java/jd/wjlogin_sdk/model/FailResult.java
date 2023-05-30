package jd.wjlogin_sdk.model;

/* loaded from: classes.dex */
public class FailResult {
    private String Message;
    private int intVal;
    private JumpResult jumpResult;
    private QRCodeScannedResult qrCodeScannedResult;
    private byte replyCode;
    private String strVal;

    public int getIntVal() {
        return this.intVal;
    }

    public JumpResult getJumpResult() {
        return this.jumpResult;
    }

    public String getMessage() {
        return this.Message;
    }

    public QRCodeScannedResult getQrCodeScannedResult() {
        return this.qrCodeScannedResult;
    }

    public byte getReplyCode() {
        return this.replyCode;
    }

    public String getStrVal() {
        return this.strVal;
    }

    public void setIntVal(int i2) {
        this.intVal = i2;
    }

    public void setJumpResult(JumpResult jumpResult) {
        this.jumpResult = jumpResult;
    }

    public void setMessage(String str) {
        this.Message = str;
    }

    public void setQrCodeScannedResult(QRCodeScannedResult qRCodeScannedResult) {
        this.qrCodeScannedResult = qRCodeScannedResult;
    }

    public void setReplyCode(byte b) {
        this.replyCode = b;
    }

    public void setStrVal(String str) {
        this.strVal = str;
    }
}

package jd.wjweblogin.model;

/* loaded from: classes11.dex */
public class WJFailResult {
    private int errorCode;
    private String errorMessage;
    private int httpCode;

    public int getErrorCode() {
        return this.errorCode;
    }

    public String getErrorMessage() {
        return this.errorMessage;
    }

    public int getHttpCode() {
        return this.httpCode;
    }

    public void setErrorCode(int i2) {
        this.errorCode = i2;
    }

    public void setErrorMessage(String str) {
        this.errorMessage = str;
    }

    public void setHttpCode(int i2) {
        this.httpCode = i2;
    }
}

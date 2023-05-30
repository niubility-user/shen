package jd.wjlogin_sdk.model;

import com.jd.dynamic.DYConstants;

/* loaded from: classes.dex */
public class ErrorResult {
    private int errorCode;
    private String errorMsg;
    private Exception exception;

    public ErrorResult(int i2, String str, Exception exc) {
        this.errorCode = i2;
        this.errorMsg = str;
        this.exception = exc;
    }

    public int getErrorCode() {
        return this.errorCode;
    }

    public String getErrorMsg() {
        return this.errorMsg;
    }

    public Exception getException() {
        return this.exception;
    }

    public String toString() {
        return this.errorCode + DYConstants.DY_REGEX_COMMA + this.errorMsg;
    }
}

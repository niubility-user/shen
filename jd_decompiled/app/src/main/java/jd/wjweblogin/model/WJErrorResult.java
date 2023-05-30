package jd.wjweblogin.model;

import com.jd.dynamic.DYConstants;

/* loaded from: classes11.dex */
public class WJErrorResult {
    private int errorCode;
    private String errorMsg;
    private Exception exception;

    public WJErrorResult(int i2, String str, Exception exc) {
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

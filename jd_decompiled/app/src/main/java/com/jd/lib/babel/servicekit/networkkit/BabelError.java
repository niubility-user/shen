package com.jd.lib.babel.servicekit.networkkit;

/* loaded from: classes13.dex */
public class BabelError {
    private int errorCode;
    private Throwable exception;
    private String message;
    private int responseCode;

    public BabelError() {
    }

    public int getErrorCode() {
        return this.errorCode;
    }

    public Throwable getException() {
        return this.exception;
    }

    public String getMessage() {
        return this.message;
    }

    public int getResponseCode() {
        return this.responseCode;
    }

    public void setErrorCode(int i2) {
        this.errorCode = i2;
    }

    public void setException(Throwable th) {
        this.exception = th;
    }

    public void setMessage(String str) {
        this.message = str;
    }

    public void setResponseCode(int i2) {
        this.responseCode = i2;
    }

    public BabelError(Throwable th) {
        this.exception = th;
    }

    public BabelError(String str, Throwable th) {
        this.message = str;
        this.exception = th;
    }
}

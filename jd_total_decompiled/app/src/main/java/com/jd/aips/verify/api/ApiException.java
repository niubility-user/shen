package com.jd.aips.verify.api;

import androidx.annotation.NonNull;

/* loaded from: classes12.dex */
public class ApiException extends Exception {
    static final long serialVersionUID = -7900036276320398188L;
    public int code;

    public ApiException() {
    }

    public int getCode() {
        return this.code;
    }

    @Override // java.lang.Throwable
    @NonNull
    public String toString() {
        return "ApiException{code=" + this.code + ", message='" + getMessage() + "'}";
    }

    public ApiException(int i2, String str) {
        super(str);
        this.code = i2;
    }
}

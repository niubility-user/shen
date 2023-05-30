package com.jd.dynamic.lib.error;

/* loaded from: classes13.dex */
public class DynamicException extends RuntimeException {
    private int errorCode;

    public DynamicException(String str) {
        super(str);
        this.errorCode = 300;
    }

    public DynamicException(String str, int i2) {
        super(str);
        this.errorCode = 300;
        this.errorCode = i2;
    }

    public DynamicException(String str, Throwable th, int i2) {
        super(str, th);
        this.errorCode = 300;
        this.errorCode = i2;
    }
}

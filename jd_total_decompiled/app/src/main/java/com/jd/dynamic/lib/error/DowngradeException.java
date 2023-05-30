package com.jd.dynamic.lib.error;

/* loaded from: classes13.dex */
public class DowngradeException extends DynamicException {
    private int errorCode;

    public DowngradeException(String str) {
        super(str);
        this.errorCode = 500;
    }

    public DowngradeException(String str, int i2) {
        super(str, i2);
        this.errorCode = 500;
        this.errorCode = i2;
    }

    public DowngradeException(String str, Throwable th, int i2) {
        super(str, th, i2);
        this.errorCode = 500;
        this.errorCode = i2;
    }
}

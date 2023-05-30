package com.huawei.hms.push;

import com.huawei.hms.aaid.constant.ErrorEnum;

/* loaded from: classes12.dex */
public class BaseException extends Exception {
    private final int a;
    private final ErrorEnum b;

    public BaseException(int i2) {
        ErrorEnum fromCode = ErrorEnum.fromCode(i2);
        this.b = fromCode;
        this.a = fromCode.getExternalCode();
    }

    public int getErrorCode() {
        return this.a;
    }

    @Override // java.lang.Throwable
    public String getMessage() {
        return this.b.getMessage();
    }
}

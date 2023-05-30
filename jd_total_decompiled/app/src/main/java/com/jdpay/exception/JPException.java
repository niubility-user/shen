package com.jdpay.exception;

import androidx.annotation.RequiresApi;

/* loaded from: classes18.dex */
public class JPException extends RuntimeException {
    public JPException() {
    }

    public JPException(String str) {
        super(str);
    }

    public JPException(String str, Throwable th) {
        super(str, th);
    }

    public JPException(Throwable th) {
        super(th);
    }

    @RequiresApi(api = 24)
    public JPException(String str, Throwable th, boolean z, boolean z2) {
        super(str, th, z, z2);
    }
}

package com.jdpay.exception;

import androidx.annotation.RequiresApi;

/* loaded from: classes18.dex */
public class JPOverflowExecption extends RuntimeException {
    public JPOverflowExecption() {
    }

    public JPOverflowExecption(String str) {
        super(str);
    }

    public JPOverflowExecption(String str, Throwable th) {
        super(str, th);
    }

    public JPOverflowExecption(Throwable th) {
        super(th);
    }

    @RequiresApi(api = 24)
    public JPOverflowExecption(String str, Throwable th, boolean z, boolean z2) {
        super(str, th, z, z2);
    }
}

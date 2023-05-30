package com.jingdong.sdk.oklog.a;

import com.jingdong.sdk.oklog.core.LoggerPrinter;

/* loaded from: classes.dex */
public final class a implements b {
    private com.jingdong.sdk.oklog.core.c a;

    public a() {
        LoggerPrinter loggerPrinter = new LoggerPrinter();
        this.a = loggerPrinter;
        loggerPrinter.addAdapter(new com.jingdong.sdk.oklog.core.b());
    }

    @Override // com.jingdong.sdk.oklog.a.b
    public final com.jingdong.sdk.oklog.core.c a() {
        return this.a;
    }
}

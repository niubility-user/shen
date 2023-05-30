package com.tencent.wcdb.support;

import android.util.Printer;

/* loaded from: classes9.dex */
public class LogPrinter implements Printer {
    private final int mPriority;
    private final String mTag;

    public LogPrinter(int i2, String str) {
        this.mPriority = i2;
        this.mTag = str;
    }

    @Override // android.util.Printer
    public void println(String str) {
        Log.println(this.mPriority, this.mTag, str);
    }
}

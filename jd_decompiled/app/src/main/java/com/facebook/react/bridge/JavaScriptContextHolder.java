package com.facebook.react.bridge;

import javax.annotation.concurrent.GuardedBy;

/* loaded from: classes.dex */
public class JavaScriptContextHolder {
    @GuardedBy("this")
    private long mContext;

    public JavaScriptContextHolder(long j2) {
        this.mContext = j2;
    }

    public synchronized void clear() {
        this.mContext = 0L;
    }

    @GuardedBy("this")
    public long get() {
        return this.mContext;
    }
}

package com.tencent.smtt.sdk;

/* loaded from: classes9.dex */
public abstract class SystemCoreProtector {
    public void onCookieManagerException(Exception exc) {
        throw new IllegalStateException(exc);
    }
}

package com.tencent.smtt.sdk;

import java.util.concurrent.Executor;

/* loaded from: classes9.dex */
public abstract class ProxyController {

    /* loaded from: classes9.dex */
    private static class a {
        static final ProxyController a = new e();
    }

    public static ProxyController getInstance() {
        return a.a;
    }

    public abstract void clearProxyOverride(Executor executor, Runnable runnable);

    public abstract void setProxyOverride(ProxyConfig proxyConfig, Executor executor, Runnable runnable);
}

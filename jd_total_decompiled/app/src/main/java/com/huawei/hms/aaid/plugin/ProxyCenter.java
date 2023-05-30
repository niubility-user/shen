package com.huawei.hms.aaid.plugin;

/* loaded from: classes12.dex */
public class ProxyCenter {
    private PushProxy proxy;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes12.dex */
    public static class a {
        private static ProxyCenter a = new ProxyCenter();
    }

    private static ProxyCenter getInstance() {
        return a.a;
    }

    public static PushProxy getProxy() {
        return getInstance().proxy;
    }

    public static void register(PushProxy pushProxy) {
        getInstance().proxy = pushProxy;
    }
}

package com.jingdong.jdsdk.d.c.a;

import com.jingdong.jdsdk.config.HostConfig;
import com.jingdong.sdk.platform.lib.openapi.host.IHostConfig;

/* loaded from: classes14.dex */
public class n implements IHostConfig {
    private static n a;

    private n() {
    }

    public static synchronized n a() {
        n nVar;
        synchronized (n.class) {
            if (a == null) {
                a = new n();
            }
            nVar = a;
        }
        return nVar;
    }

    @Override // com.jingdong.sdk.platform.lib.openapi.host.IHostConfig
    public String getHost(String str) {
        return HostConfig.getInstance().getHost(str);
    }
}

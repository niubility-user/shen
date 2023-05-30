package com.jingdong.jdsdk.d.c.a;

import com.jingdong.jdsdk.auraSetting.AuraBundleConfig;
import com.jingdong.jdsdk.auraSetting.AuraBundleInfos;
import com.jingdong.sdk.platform.lib.openapi.aura.IAuraBundleConfig;

/* loaded from: classes14.dex */
public class g implements IAuraBundleConfig {
    private static g a = new g();

    private g() {
    }

    private int a(int i2) {
        return i2 != 12 ? -1 : 12;
    }

    public static synchronized g b() {
        g gVar;
        synchronized (g.class) {
            if (a == null) {
                a = new g();
            }
            gVar = a;
        }
        return gVar;
    }

    @Override // com.jingdong.sdk.platform.lib.openapi.aura.IAuraBundleConfig
    public String getBundleNameFromBundleId(int i2) {
        return AuraBundleInfos.getBundleNameFromBundleId(a(i2));
    }

    @Override // com.jingdong.sdk.platform.lib.openapi.aura.IAuraBundleConfig
    public long getBundleVersionCode(String str) {
        return AuraBundleConfig.getInstance().getBundleVersionCode(str);
    }
}

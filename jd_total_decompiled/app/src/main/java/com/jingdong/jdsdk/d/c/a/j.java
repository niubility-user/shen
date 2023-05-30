package com.jingdong.jdsdk.d.c.a;

import com.jingdong.common.deeplinkhelper.DeepLinkSwitch;
import com.jingdong.sdk.platform.lib.openapi.deeplink.IDeeplink;

/* loaded from: classes14.dex */
public class j implements IDeeplink {
    private static j a;

    private j() {
    }

    public static synchronized j a() {
        j jVar;
        synchronized (j.class) {
            if (a == null) {
                a = new j();
            }
            jVar = a;
        }
        return jVar;
    }

    @Override // com.jingdong.sdk.platform.lib.openapi.deeplink.IDeeplink
    public String getScheme() {
        return "jd";
    }

    @Override // com.jingdong.sdk.platform.lib.openapi.deeplink.IDeeplink
    public boolean isSwitchOpen(String str) {
        return DeepLinkSwitch.getInstance().isSwitchOpen(str);
    }
}

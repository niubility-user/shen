package com.jd.libs.x5.hybrid;

import android.app.Application;
import com.jd.libs.hybrid.adapter.CookieAdapter;
import com.jd.libs.x5.hybrid.adapter.CookiePlugin;

/* loaded from: classes16.dex */
public class HybridSDK extends com.jd.libs.hybrid.HybridSDK {
    public static void initSDK(Application application, String str, boolean z) {
        com.jd.libs.hybrid.HybridSDK.setDebug(z);
        initSDK(application, str);
    }

    public static void initSDK(Application application, String str) {
        com.jd.libs.hybrid.HybridSDK.initSDK(application, str);
        com.jd.libs.hybrid.HybridSDK.registerAdapter(CookieAdapter.NAME, CookiePlugin.class);
    }
}

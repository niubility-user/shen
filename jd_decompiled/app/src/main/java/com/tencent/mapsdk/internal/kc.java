package com.tencent.mapsdk.internal;

import android.content.Context;

/* loaded from: classes9.dex */
public class kc extends hc {

    /* renamed from: c  reason: collision with root package name */
    private static volatile kc f16770c = null;
    private static final String d = "com.tencent.tencentmap.mapsdk.maps.offlinemap";

    private kc(Context context) {
        if (context == null) {
            return;
        }
        this.a = context.getSharedPreferences(d, 0);
        c();
    }

    public static kc a(Context context) {
        if (f16770c == null) {
            synchronized (kc.class) {
                if (f16770c == null) {
                    f16770c = new kc(context);
                }
            }
        }
        return f16770c;
    }

    private void c() {
        if (f16770c == null) {
            return;
        }
        a(new String[]{"taiwanClearCacheVersion", "taiwanStyle", "taiwanVersion", "mapPoiIcon", "worldTileCount", "rttConfigVersion", "rttConfigMd5", "closeRoadSytleNomalModeVersion", "closeRoadSytleNomalModeMd5", "closeRoadStyleTrafficModeVersion", "closeRoadStyleTrafficModeMd5", "offlineCityListVersion", "offlineCityListMd5"});
        String d2 = d(l4.f16791e);
        if (d2 != null && b7.b("4.1.0", d2) > 0) {
            b();
        }
    }
}

package com.jd.libs.hybrid.offlineload.utils;

import com.jd.libs.hybrid.base.HybridSettings;
import com.jd.libs.hybrid.base.util.PreferenceUtils;

/* loaded from: classes16.dex */
public class JDCacheSwitch {
    public static final Boolean useJdCache;

    static {
        boolean z = false;
        if (HybridSettings.getAppContext() != null && PreferenceUtils.getBoolean(HybridSettings.getAppContext(), "KEY_USE_JDCACHE", false)) {
            z = true;
        }
        useJdCache = Boolean.valueOf(z);
    }

    public static void saveJdCacheSwitch(boolean z) {
        if (HybridSettings.getAppContext() != null) {
            PreferenceUtils.putBoolean(HybridSettings.getAppContext(), "KEY_USE_JDCACHE", z);
        }
    }
}

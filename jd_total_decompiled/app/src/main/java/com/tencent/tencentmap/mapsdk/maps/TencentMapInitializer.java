package com.tencent.tencentmap.mapsdk.maps;

import android.content.Context;
import com.tencent.mapsdk.internal.f7;

/* loaded from: classes9.dex */
public final class TencentMapInitializer {
    private static volatile boolean agreePrivacy;

    public static synchronized boolean getAgreePrivacy() {
        boolean z;
        synchronized (TencentMapInitializer.class) {
            z = agreePrivacy;
        }
        return z;
    }

    public static synchronized String getDuid(Context context) {
        synchronized (TencentMapInitializer.class) {
            if (agreePrivacy) {
                return f7.e(context);
            }
            return "undefined";
        }
    }

    public static synchronized String getSuid(Context context) {
        synchronized (TencentMapInitializer.class) {
            if (agreePrivacy) {
                return f7.h(context);
            }
            return "undefined";
        }
    }

    public static synchronized void setAgreePrivacy(boolean z) {
        synchronized (TencentMapInitializer.class) {
            agreePrivacy = z;
        }
    }
}

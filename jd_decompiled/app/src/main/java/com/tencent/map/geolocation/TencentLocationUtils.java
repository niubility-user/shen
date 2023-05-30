package com.tencent.map.geolocation;

import android.content.Context;
import c.t.m.g.p6;
import c.t.m.g.u0;

/* loaded from: classes9.dex */
public class TencentLocationUtils {
    public TencentLocationUtils() {
        throw new UnsupportedOperationException();
    }

    public static boolean contains(TencentLocation tencentLocation, double d, TencentLocation tencentLocation2) {
        if (tencentLocation == null || tencentLocation2 == null) {
            throw null;
        }
        return distanceBetween(tencentLocation, tencentLocation2) <= d;
    }

    public static double distanceBetween(double d, double d2, double d3, double d4) {
        return u0.b(d, d2, d3, d4);
    }

    public static double distanceBetween(TencentLocation tencentLocation, TencentLocation tencentLocation2) {
        if (tencentLocation == null || tencentLocation2 == null) {
            throw null;
        }
        return u0.b(tencentLocation.getLatitude(), tencentLocation.getLongitude(), tencentLocation2.getLatitude(), tencentLocation2.getLongitude());
    }

    public static boolean isFromGps(TencentLocation tencentLocation) {
        if (tencentLocation == null) {
            return false;
        }
        return "gps".equals(tencentLocation.getProvider());
    }

    public static boolean isFromNetwork(TencentLocation tencentLocation) {
        if (tencentLocation == null) {
            return false;
        }
        return "network".equals(tencentLocation.getProvider());
    }

    public static boolean isSupportGps(Context context) {
        context.getClass();
        return (p6.b().a(context) & 16) == 0;
    }
}

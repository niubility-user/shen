package com.meizu.cloud.pushsdk.e.i;

import android.net.TrafficStats;
import com.meizu.cloud.pushsdk.e.b.e;
import com.meizu.cloud.pushsdk.e.d.k;
import com.meizu.cloud.pushsdk.util.MinSdkChecker;

/* loaded from: classes14.dex */
public final class a {
    public static void a(k kVar, com.meizu.cloud.pushsdk.e.b.b bVar) {
        if (bVar.t() == e.OK_HTTP_RESPONSE || kVar == null || kVar.a() == null || kVar.a().g() == null) {
            return;
        }
        try {
            try {
                kVar.a().g().close();
                if (!MinSdkChecker.isSupportNotificationChannel()) {
                    return;
                }
            } catch (Exception unused) {
                com.meizu.cloud.pushsdk.e.b.a.b("Unable to close source data");
                if (!MinSdkChecker.isSupportNotificationChannel()) {
                    return;
                }
            }
            TrafficStats.clearThreadStatsTag();
        } catch (Throwable th) {
            if (MinSdkChecker.isSupportNotificationChannel()) {
                TrafficStats.clearThreadStatsTag();
            }
            throw th;
        }
    }
}

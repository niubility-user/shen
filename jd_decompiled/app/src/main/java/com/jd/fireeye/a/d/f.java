package com.jd.fireeye.a.d;

import android.content.Context;

/* loaded from: classes13.dex */
public class f {
    private static long a;
    private static String[] b = {"com.redfinger.tw", "com.photon.hybrid", "com.qi.earthnutproxy", "com.sigma_rt.totalcontrol", "io.va.exposed", "net.s17s.s17ray", "com.qx.qiangziip", "com.fvcorp.android.aijiasuclient", "org.soutaproxy"};

    /* JADX WARN: Code restructure failed: missing block: B:7:0x0010, code lost:
        r0 = java.lang.Boolean.TRUE;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static boolean a(android.content.Context r4) {
        /*
            java.lang.Boolean r0 = java.lang.Boolean.FALSE
            r1 = 0
        L3:
            java.lang.String[] r2 = com.jd.fireeye.a.d.f.b     // Catch: java.lang.Exception -> L16
            int r3 = r2.length     // Catch: java.lang.Exception -> L16
            if (r1 >= r3) goto L16
            r2 = r2[r1]     // Catch: java.lang.Exception -> L16
            boolean r2 = com.jd.android.sdk.coreinfo.CoreInfo.System.isPkgInstalled(r4, r2)     // Catch: java.lang.Exception -> L16
            if (r2 == 0) goto L13
            java.lang.Boolean r0 = java.lang.Boolean.TRUE     // Catch: java.lang.Exception -> L16
            goto L16
        L13:
            int r1 = r1 + 1
            goto L3
        L16:
            boolean r4 = r0.booleanValue()
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jd.fireeye.a.d.f.a(android.content.Context):boolean");
    }

    public static Long b(Context context) {
        try {
            if (a(context)) {
                a |= 1;
            }
        } catch (Exception unused) {
        }
        return Long.valueOf(a);
    }
}

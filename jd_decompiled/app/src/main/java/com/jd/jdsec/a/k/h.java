package com.jd.jdsec.a.k;

import android.content.Context;

/* loaded from: classes13.dex */
public class h {
    private static long a;
    private static String[] b = {"com.yztc.studio.plugin", "de.robv.android.xposed.installer", "com.miui.miuibbs", "com.soft.apk008v", "com.dobe.igrimace", "com.igrimace.nzt", "com.baidu.md", "com.topjohnwu.magisk"};

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
            java.lang.String[] r2 = com.jd.jdsec.a.k.h.b     // Catch: java.lang.Exception -> L16
            int r3 = r2.length     // Catch: java.lang.Exception -> L16
            if (r1 >= r3) goto L16
            r2 = r2[r1]     // Catch: java.lang.Exception -> L16
            boolean r2 = com.jingdong.sdk.baseinfo.BaseInfo.isPkgInstalled(r4, r2)     // Catch: java.lang.Exception -> L16
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
        throw new UnsupportedOperationException("Method not decompiled: com.jd.jdsec.a.k.h.a(android.content.Context):boolean");
    }

    public static Long b(Context context) {
        try {
            if (a(context)) {
                a |= 1;
            }
        } catch (Exception e2) {
            com.jd.jdsec.a.l.b.b("JDSec.Security.RiskLabel", "\u6539\u673a\u68c0\u6d4b\u6a21\u5757\u5f02\u5e38" + e2.getMessage());
        }
        return Long.valueOf(a);
    }
}

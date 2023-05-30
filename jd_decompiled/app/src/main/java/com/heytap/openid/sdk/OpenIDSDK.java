package com.heytap.openid.sdk;

import android.content.Context;
import com.heytap.openid.sdk.m_c;
import jd.wjlogin_sdk.util.e;

/* loaded from: classes12.dex */
public class OpenIDSDK {
    public static void clear(Context context) {
        StringBuilder sb = new StringBuilder();
        sb.append(context.getPackageName());
        sb.append(" 2007");
        if (m_d.m_a()) {
            m_c.m_b.m_a.m_a(m_d.m_a(context));
        }
    }

    public static String getAAID(Context context) {
        return !m_d.m_a() ? "" : m_b.m_a().m_a(m_d.m_a(context), "AUID");
    }

    public static String getOAID(Context context) {
        return !m_d.m_a() ? "" : m_b.m_a().m_a(m_d.m_a(context), "OUID");
    }

    public static boolean getOAIDStatus(Context context) {
        if (m_d.m_a()) {
            return "TRUE".equalsIgnoreCase(m_b.m_a().m_a(m_d.m_a(context), "OUID_STATUS"));
        }
        return false;
    }

    public static String getUDID(Context context) {
        return !m_d.m_a() ? "" : m_b.m_a().m_a(m_d.m_a(context), e.d);
    }

    public static String getVAID(Context context) {
        return !m_d.m_a() ? "" : m_b.m_a().m_a(m_d.m_a(context), "DUID");
    }

    /* JADX WARN: Code restructure failed: missing block: B:30:0x007d, code lost:
        if ((r0 == 28 ? r6.getLongVersionCode() : r6.versionCode) >= 11200) goto L33;
     */
    /* JADX WARN: Removed duplicated region for block: B:14:0x003a  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0043  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x004a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void init(android.content.Context r6) {
        /*
            com.heytap.openid.sdk.m_c r0 = com.heytap.openid.sdk.m_c.m_b.m_a
            android.content.Context r1 = com.heytap.openid.sdk.m_d.m_a(r6)
            r0.getClass()
            java.lang.String r0 = "Y29tLmhleXRhcC5vcGVuaWQ="
            java.lang.String r0 = com.heytap.openid.sdk.m_a.m_a(r0)
            r2 = 1
            r3 = 0
            android.content.pm.PackageManager r4 = r1.getPackageManager()     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L3c
            android.content.pm.PackageInfo r4 = r4.getPackageInfo(r0, r3)     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L3c
            if (r4 == 0) goto L3c
            int r4 = r4.versionCode     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L3c
            if (r4 <= 0) goto L3c
            int r4 = android.os.Build.VERSION.SDK_INT     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L3c
            r5 = 29
            if (r4 > r5) goto L37
            java.lang.String r4 = "android"
            java.lang.String r4 = com.heytap.openid.sdk.m_a.m_a(r1, r4)     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L3c
            java.lang.String r0 = com.heytap.openid.sdk.m_a.m_a(r1, r0)     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L3c
            boolean r0 = r4.equals(r0)     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L3c
            if (r0 != 0) goto L37
            r0 = 0
            goto L38
        L37:
            r0 = 1
        L38:
            if (r0 == 0) goto L3c
            r0 = 1
            goto L3d
        L3c:
            r0 = 0
        L3d:
            com.heytap.openid.sdk.m_d.m_b = r0
            com.heytap.openid.sdk.m_d.m_a = r2
            if (r0 == 0) goto L4a
            com.heytap.openid.sdk.m_b r6 = com.heytap.openid.sdk.m_b.m_a()
            r6.m_b = r2
            return
        L4a:
            com.heytap.openid.sdk.m_b r0 = com.heytap.openid.sdk.m_b.m_a()
            r0.m_b = r3
            com.heytap.openid.sdk.m_f r0 = com.heytap.openid.sdk.m_f.m_b.m_a
            android.content.Context r6 = com.heytap.openid.sdk.m_d.m_a(r6)
            r0.getClass()
            java.lang.String r0 = "Y29tLmNvbG9yb3MubWNz"
            java.lang.String r0 = com.heytap.openid.sdk.m_a.m_a(r0)
            android.content.pm.PackageManager r6 = r6.getPackageManager()     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L80
            android.content.pm.PackageInfo r6 = r6.getPackageInfo(r0, r3)     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L80
            if (r6 == 0) goto L80
            int r0 = android.os.Build.VERSION.SDK_INT     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L80
            r1 = 28
            if (r0 > r1) goto L80
            if (r0 != r1) goto L76
            long r0 = r6.getLongVersionCode()     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L80
            goto L79
        L76:
            int r6 = r6.versionCode     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L80
            long r0 = (long) r6
        L79:
            r4 = 11200(0x2bc0, double:5.5335E-320)
            int r6 = (r0 > r4 ? 1 : (r0 == r4 ? 0 : -1))
            if (r6 < 0) goto L80
            goto L81
        L80:
            r2 = 0
        L81:
            com.heytap.openid.sdk.m_d.m_b = r2
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.heytap.openid.sdk.OpenIDSDK.init(android.content.Context):void");
    }

    public static boolean isSupported() {
        boolean z = m_d.m_a;
        return m_d.m_b;
    }
}

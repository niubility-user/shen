package com.heytap.openid.sdk;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import com.heytap.openid.sdk.m_c;
import com.heytap.openid.sdk.m_f;
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
    */
    public static void init(Context context) {
        boolean z;
        PackageInfo packageInfo;
        boolean z2;
        m_c m_cVar = m_c.m_b.m_a;
        Context m_a = m_d.m_a(context);
        m_cVar.getClass();
        String m_a2 = m_a.m_a("Y29tLmhleXRhcC5vcGVuaWQ=");
        boolean z3 = true;
        try {
            packageInfo = m_a.getPackageManager().getPackageInfo(m_a2, 0);
        } catch (PackageManager.NameNotFoundException unused) {
        }
        if (packageInfo != null && packageInfo.versionCode > 0) {
            if (Build.VERSION.SDK_INT <= 29) {
                if (!m_a.m_a(m_a, "android").equals(m_a.m_a(m_a, m_a2))) {
                    z2 = false;
                    if (z2) {
                        z = true;
                        m_d.m_b = z;
                        m_d.m_a = true;
                        if (!z) {
                            m_b.m_a().m_b = true;
                            return;
                        }
                        m_b.m_a().m_b = false;
                        m_f m_fVar = m_f.m_b.m_a;
                        Context m_a3 = m_d.m_a(context);
                        m_fVar.getClass();
                        try {
                            PackageInfo packageInfo2 = m_a3.getPackageManager().getPackageInfo(m_a.m_a("Y29tLmNvbG9yb3MubWNz"), 0);
                            if (packageInfo2 != null && (r0 = Build.VERSION.SDK_INT) <= 28) {
                            }
                        } catch (PackageManager.NameNotFoundException unused2) {
                        }
                        z3 = false;
                        m_d.m_b = z3;
                        return;
                    }
                }
            }
            z2 = true;
            if (z2) {
            }
        }
        z = false;
        m_d.m_b = z;
        m_d.m_a = true;
        if (!z) {
        }
    }

    public static boolean isSupported() {
        boolean z = m_d.m_a;
        return m_d.m_b;
    }
}

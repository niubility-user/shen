package com.jingdong.sdk.jdupgrade.a;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.text.TextUtils;
import com.jingdong.sdk.jdupgrade.a.j.h;
import com.jingdong.sdk.jdupgrade.a.j.j;
import com.jingdong.sdk.jdupgrade.a.j.k;
import java.io.File;
import java.util.Map;
import org.json.JSONObject;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes7.dex */
public class e {

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes7.dex */
    public static class a implements Runnable {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ String f15049g;

        /* renamed from: h  reason: collision with root package name */
        final /* synthetic */ String f15050h;

        a(String str, String str2) {
            this.f15049g = str;
            this.f15050h = str2;
        }

        /* JADX WARN: Removed duplicated region for block: B:24:0x006c  */
        /* JADX WARN: Removed duplicated region for block: B:25:0x0079  */
        @Override // java.lang.Runnable
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public void run() {
            /*
                r4 = this;
                boolean r0 = com.jingdong.sdk.jdupgrade.a.j.i.b()
                java.lang.String r1 = "ReportInstalled"
                if (r0 == 0) goto L69
                java.lang.String r0 = r4.f15049g
                java.lang.String r0 = com.jingdong.sdk.jdupgrade.a.e.b(r0)
                java.lang.StringBuilder r2 = new java.lang.StringBuilder
                r2.<init>()
                java.lang.String r3 = "upgradeType: "
                r2.append(r3)
                r2.append(r0)
                java.lang.String r2 = r2.toString()
                com.jingdong.sdk.jdupgrade.a.j.h.a(r1, r2)
                boolean r2 = android.text.TextUtils.isEmpty(r0)
                if (r2 == 0) goto L49
                java.lang.String r2 = com.jingdong.sdk.jdupgrade.a.c.c()
                java.lang.String r3 = "com.jingdong.app.mall"
                boolean r2 = android.text.TextUtils.equals(r3, r2)
                if (r2 == 0) goto L3a
                java.lang.String r0 = "upgradeType is empty and package is com.jingdong.app.mall, no need to report"
            L36:
                com.jingdong.sdk.jdupgrade.a.j.h.c(r1, r0)
                return
            L3a:
                java.lang.String r2 = com.jingdong.sdk.jdupgrade.a.c.c()
                java.lang.String r3 = "com.jd.jdlite"
                boolean r2 = android.text.TextUtils.equals(r3, r2)
                if (r2 == 0) goto L49
                java.lang.String r0 = "upgradeType is empty and package is com.jd.jdlite, no need to report"
                goto L36
            L49:
                java.lang.String r0 = com.jingdong.sdk.jdupgrade.a.j.j.b(r0)
                boolean r2 = android.text.TextUtils.isEmpty(r0)
                if (r2 != 0) goto L69
                org.json.JSONObject r2 = new org.json.JSONObject     // Catch: java.lang.Throwable -> L65
                r2.<init>(r0)     // Catch: java.lang.Throwable -> L65
                java.lang.String r0 = "200"
                java.lang.String r3 = "state"
                java.lang.String r2 = r2.optString(r3)     // Catch: java.lang.Throwable -> L65
                boolean r0 = r0.equals(r2)     // Catch: java.lang.Throwable -> L65
                goto L6a
            L65:
                r0 = move-exception
                r0.printStackTrace()
            L69:
                r0 = 0
            L6a:
                if (r0 == 0) goto L79
                java.lang.String r0 = r4.f15050h
                java.lang.String r2 = "VERSION_HAS_INSTALLED"
                com.jingdong.sdk.jdupgrade.a.j.k.b(r2, r0)
                java.lang.String r0 = "Current version is reported successfully!"
                com.jingdong.sdk.jdupgrade.a.j.h.c(r1, r0)
                goto L7e
            L79:
                java.lang.String r0 = "Current version failed to be reported!"
                com.jingdong.sdk.jdupgrade.a.j.h.b(r1, r0)
            L7e:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.jingdong.sdk.jdupgrade.a.e.a.run():void");
        }
    }

    private static String a(Context context) {
        try {
            ApplicationInfo applicationInfo = context.getApplicationInfo();
            if (applicationInfo == null) {
                return null;
            }
            return applicationInfo.sourceDir;
        } catch (Throwable unused) {
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void c() {
        String a2 = k.a("VERSION_HAS_INSTALLED", "");
        String str = c.i() + "(O\ufe4f0)" + c.h();
        if (a2.equals(str)) {
            h.b("ReportInstalled", "Current version has already been reported!");
            return;
        }
        String a3 = a(c.j());
        if (TextUtils.isEmpty(a3)) {
            h.b("ReportInstalled", "apkPath is null");
        } else {
            j.c().execute(new a(a3, str));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String d(String str) {
        try {
            Map<Integer, String> b = com.jingdong.sdk.jdupgrade.a.j.m.a.c.b(new File(str));
            if (b == null) {
                h.b("ReportInstalled", "execute payload map null");
                return "";
            }
            for (String str2 : b.values()) {
                try {
                    if (str2.startsWith("{")) {
                        String optString = new JSONObject(str2).optString("jdUpgradeMode");
                        if (!TextUtils.isEmpty(optString)) {
                            h.b("ReportInstalled", "to report jdUpgradeMode:" + optString);
                            return optString;
                        }
                        h.b("ReportInstalled", "no jdUpgradeMode");
                    } else {
                        continue;
                    }
                } catch (Throwable unused) {
                }
            }
            return "";
        } catch (Throwable th) {
            h.b("ReportInstalled", "execute exception," + th.getMessage());
            return "";
        }
    }
}

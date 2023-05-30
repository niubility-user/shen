package com.jingdong.sdk.jdupgrade.a;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.text.TextUtils;
import com.jingdong.common.XView2.common.XView2Constants;
import com.jingdong.sdk.jdupgrade.a.j.h;
import com.jingdong.sdk.jdupgrade.a.j.i;
import com.jingdong.sdk.jdupgrade.a.j.j;
import com.jingdong.sdk.jdupgrade.a.j.k;
import com.meizu.cloud.pushsdk.platform.message.BasicPushStatus;
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
        */
        public void run() {
            boolean z;
            String str;
            if (i.b()) {
                String d = e.d(this.f15049g);
                h.a("ReportInstalled", "upgradeType: " + d);
                if (TextUtils.isEmpty(d)) {
                    if (!TextUtils.equals(jd.wjlogin_sdk.util.f.f19954c, c.c())) {
                        str = TextUtils.equals("com.jd.jdlite", c.c()) ? "upgradeType is empty and package is com.jd.jdlite, no need to report" : "upgradeType is empty and package is com.jingdong.app.mall, no need to report";
                    }
                    h.c("ReportInstalled", str);
                    return;
                }
                String b = j.b(d);
                if (!TextUtils.isEmpty(b)) {
                    try {
                        z = BasicPushStatus.SUCCESS_CODE.equals(new JSONObject(b).optString(XView2Constants.STATE));
                    } catch (Throwable th) {
                        th.printStackTrace();
                    }
                    if (z) {
                        h.b("ReportInstalled", "Current version failed to be reported!");
                        return;
                    }
                    k.b("VERSION_HAS_INSTALLED", this.f15050h);
                    h.c("ReportInstalled", "Current version is reported successfully!");
                    return;
                }
            }
            z = false;
            if (z) {
            }
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

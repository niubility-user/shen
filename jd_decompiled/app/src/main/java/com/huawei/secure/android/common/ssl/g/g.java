package com.huawei.secure.android.common.ssl.g;

import android.content.Context;
import android.content.pm.PackageManager;

/* loaded from: classes12.dex */
public class g {
    private static final String a = "h";

    public static String a(String str) {
        Context a2 = c.a();
        if (a2 == null) {
            return "";
        }
        try {
            return a2.getPackageManager().getPackageInfo(str, 0).versionName;
        } catch (PackageManager.NameNotFoundException e2) {
            f.d(a, "getVersion NameNotFoundException : " + e2.getMessage());
            return "";
        } catch (Exception e3) {
            f.d(a, "getVersion: " + e3.getMessage());
            return "";
        } catch (Throwable unused) {
            f.d(a, "throwable");
            return "";
        }
    }
}

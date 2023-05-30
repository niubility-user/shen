package com.jd.android.sdk.coreinfo.util;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import com.jd.android.sdk.coreinfo.CoreInfo;
import com.jd.android.sdk.coreinfo.SensitiveApi;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class a {
    private static a a;

    private a() {
    }

    public static a a() {
        if (a == null) {
            synchronized (a.class) {
                if (a == null) {
                    a = new a();
                }
            }
        }
        return a;
    }

    public static boolean a(Context context, String str) {
        if (context != null && !TextUtils.isEmpty(str)) {
            PackageInfo packageInfo = null;
            try {
                packageInfo = context.getPackageManager().getPackageInfo(str, 0);
            } catch (PackageManager.NameNotFoundException unused) {
            } catch (Throwable th) {
                th.printStackTrace();
            }
            return packageInfo != null;
        }
        Logger.e("PackageInfoUtil", "isPackageInstalled parameter error!");
        return false;
    }

    public static List<PackageInfo> a(Context context, int i2) {
        SensitiveApi sensitiveApi = CoreInfo.sensitiveApi;
        if (sensitiveApi != null) {
            return sensitiveApi.getInstalledPackages(context, i2);
        }
        return new ArrayList();
    }
}

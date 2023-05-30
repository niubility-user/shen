package com.jd.jdsec.a.k;

import android.app.AppOpsManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Process;
import com.jingdong.sdk.baseinfo.BaseInfo;

/* loaded from: classes13.dex */
public class c extends e {
    private static long b;

    public static Long g(Context context, String str) {
        if (j(context)) {
            b |= 1;
        }
        if (h(context)) {
            b |= 2;
        }
        if (i(context)) {
            b |= 4;
        }
        if (Long.valueOf(str).longValue() > 0) {
            b |= 8;
        }
        return Long.valueOf(b);
    }

    private static boolean h(Context context) {
        PackageManager packageManager = context.getPackageManager();
        String[] strArr = {"com.xmstudio.locationmock", "com.yunyou", "top.xuante.moloc", "com.pdwl.mockgps", "com.lerist.fakelocation"};
        int i2 = 0;
        for (int i3 = 0; i3 < 5; i3++) {
            String str = strArr[i3];
            try {
                String[] strArr2 = packageManager.getPackageInfo(str, 4096).requestedPermissions;
                if (strArr2 != null) {
                    for (String str2 : strArr2) {
                        if (str2.equals("android.permission.ACCESS_MOCK_LOCATION") && !str.equals(context.getPackageName())) {
                            i2++;
                        }
                    }
                }
            } catch (Exception unused) {
            }
        }
        return i2 > 0;
    }

    public static boolean i(Context context) {
        boolean z = false;
        try {
            if (Build.VERSION.SDK_INT >= 23) {
                if (((AppOpsManager) context.getSystemService("appops")).checkOp("android:mock_location", Process.myUid(), jd.wjlogin_sdk.util.f.f19954c) == 0) {
                    z = true;
                }
            } else {
                z = !BaseInfo.getAndroidIdWithAOPBySecure(context.getContentResolver(), "mock_location").equals("0");
            }
        } catch (Exception unused) {
        }
        return z;
    }

    private static boolean j(Context context) {
        return false;
    }
}

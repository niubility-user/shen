package com.jdjr.risk.device.c;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PermissionInfo;
import android.os.Build;
import android.os.Looper;
import android.text.TextUtils;
import androidx.core.app.NotificationCompat;
import com.jd.dynamic.DYConstants;
import com.jingdong.common.entity.personal.PersonalConstants;
import com.jingdong.common.permission.PermissionHelper;
import com.jingdong.sdk.baseinfo.BaseInfo;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes18.dex */
public class d {
    static PackageInfo a(Context context, String str) {
        if (str != null) {
            if (!str.isEmpty()) {
                try {
                } catch (Throwable unused) {
                    return null;
                }
            }
            return context.getPackageManager().getPackageInfo(str, 0);
        }
        return null;
    }

    static String a(PackageInfo packageInfo, boolean z, int i2, PackageManager packageManager) {
        String str = com.jdjr.risk.util.constant.b.a.get(packageInfo.packageName);
        return (TextUtils.isEmpty(str) && z && w.a(i2)) ? packageInfo.applicationInfo.loadLabel(packageManager).toString() : str;
    }

    public static List<PackageInfo> a(Context context) {
        if (context != null) {
            try {
                PackageManager packageManager = context.getPackageManager();
                if (packageManager != null) {
                    return (List) packageManager.getClass().getMethod("getInstalledPackages", Integer.TYPE).invoke(packageManager, 0);
                }
                return null;
            } catch (Throwable unused) {
                return null;
            }
        }
        return null;
    }

    public static boolean b(Context context) {
        try {
            PermissionInfo permissionInfo = context.getPackageManager().getPermissionInfo(PermissionHelper.PERMISSION_GET_INSTALLED_APPS, 0);
            if (Build.VERSION.SDK_INT >= 28) {
                return permissionInfo.getProtection() == 1;
            }
            return false;
        } catch (Throwable unused) {
            return false;
        }
    }

    public static JSONArray c(Context context) {
        PackageManager packageManager;
        JSONArray jSONArray = new JSONArray();
        try {
            if (BaseInfo.isAgreedPrivacy() && Looper.myLooper() != Looper.getMainLooper() && context != null && (packageManager = context.getPackageManager()) != null) {
                List<String> q = r.q(context);
                if (q.size() > 0) {
                    Iterator<String> it = q.iterator();
                    while (it.hasNext()) {
                        PackageInfo a = a(context, it.next());
                        JSONObject jSONObject = new JSONObject();
                        if (a != null) {
                            jSONObject.put("pn", a.packageName);
                            jSONObject.put(DYConstants.DY_FIT, String.valueOf(a.firstInstallTime));
                            jSONObject.put("lut", String.valueOf(a.lastUpdateTime));
                            jSONObject.put("vc", String.valueOf(a.versionCode));
                            jSONObject.put("vn", a.versionName);
                            jSONObject.put(NotificationCompat.CATEGORY_SYSTEM, (a.applicationInfo.flags & 1) == 0 ? "0" : "1");
                            jSONObject.put(PersonalConstants.ICON_STYLE_N, a(a, true, 0, packageManager));
                        }
                        jSONArray.put(jSONObject);
                    }
                }
            }
        } catch (Throwable unused) {
        }
        return jSONArray;
    }
}

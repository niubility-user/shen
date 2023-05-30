package com.xiaomi.push;

import android.annotation.TargetApi;
import android.app.AppOpsManager;
import android.app.Application;
import android.app.NotificationManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.text.TextUtils;
import com.huawei.hms.push.constant.RemoteMessageConst;
import com.jingdong.sdk.baseinfo.BaseInfo;
import java.util.Map;

/* loaded from: classes11.dex */
public class y4 {
    private static a a;

    /* loaded from: classes11.dex */
    public interface a {
        Map<String, String> a(Context context, String str);

        /* renamed from: a */
        boolean m184a(Context context, String str);

        boolean b(Context context, String str);
    }

    /* loaded from: classes11.dex */
    public enum b {
        UNKNOWN(0),
        ALLOWED(1),
        NOT_ALLOWED(2);
        

        /* renamed from: a */
        private final int f262a;

        b(int i2) {
            this.f262a = i2;
        }

        public int a() {
            return this.f262a;
        }
    }

    public static int a(Context context) {
        Bundle bundle;
        if (context != null) {
            try {
                ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo("com.android.systemui", 128);
                if (applicationInfo == null || (bundle = applicationInfo.metaData) == null) {
                    return 0;
                }
                return bundle.getInt("SupportForPushVersionCode");
            } catch (PackageManager.NameNotFoundException unused) {
                return 0;
            }
        }
        return 0;
    }

    public static int b(Context context, String str) {
        PackageInfo packageInfo;
        try {
            packageInfo = context.getPackageManager().getPackageInfo(str, 16384);
        } catch (Exception unused) {
            packageInfo = null;
        }
        if (packageInfo != null) {
            return packageInfo.versionCode;
        }
        return 0;
    }

    private static ApplicationInfo c(Context context, String str) {
        if (str.equals(context.getPackageName())) {
            return context.getApplicationInfo();
        }
        try {
            return context.getPackageManager().getApplicationInfo(str, 0);
        } catch (PackageManager.NameNotFoundException unused) {
            g.j.a.a.a.c.o("not found app info " + str);
            return null;
        }
    }

    public static Drawable d(Context context, String str) {
        ApplicationInfo c2 = c(context, str);
        Drawable drawable = null;
        if (c2 != null) {
            try {
                drawable = c2.loadIcon(context.getPackageManager());
                if (drawable == null) {
                    drawable = c2.loadLogo(context.getPackageManager());
                }
            } catch (Exception e2) {
                g.j.a.a.a.c.o("get app icon drawable failed, " + e2);
            }
        }
        return drawable != null ? drawable : new ColorDrawable(0);
    }

    private static b e(Context context, ApplicationInfo applicationInfo) {
        int i2 = Build.VERSION.SDK_INT;
        if (applicationInfo == null || i2 < 24) {
            return b.UNKNOWN;
        }
        Boolean bool = null;
        try {
            if (applicationInfo.packageName.equals(context.getPackageName())) {
                bool = Boolean.valueOf(((NotificationManager) context.getSystemService(RemoteMessageConst.NOTIFICATION)).areNotificationsEnabled());
            } else {
                Object e2 = i2 >= 29 ? k0.e(context.getSystemService(RemoteMessageConst.NOTIFICATION), "getService", new Object[0]) : context.getSystemService("security");
                if (e2 != null) {
                    bool = (Boolean) k0.n(e2, "areNotificationsEnabledForPackage", applicationInfo.packageName, Integer.valueOf(applicationInfo.uid));
                }
            }
            if (bool != null) {
                return bool.booleanValue() ? b.ALLOWED : b.NOT_ALLOWED;
            }
        } catch (Exception e3) {
            g.j.a.a.a.c.o("are notifications enabled error " + e3);
        }
        return b.UNKNOWN;
    }

    @TargetApi(19)
    public static b f(Context context, String str, boolean z) {
        ApplicationInfo applicationInfo;
        b e2;
        b bVar;
        if (context == null || TextUtils.isEmpty(str)) {
            return b.UNKNOWN;
        }
        try {
            applicationInfo = str.equals(context.getPackageName()) ? context.getApplicationInfo() : context.getPackageManager().getApplicationInfo(str, 0);
            e2 = e(context, applicationInfo);
            bVar = b.UNKNOWN;
        } catch (Throwable th) {
            g.j.a.a.a.c.o("get app op error " + th);
        }
        if (e2 != bVar) {
            return e2;
        }
        Integer num = (Integer) k0.b(AppOpsManager.class, "OP_POST_NOTIFICATION");
        if (num == null) {
            return bVar;
        }
        Integer num2 = (Integer) k0.e((AppOpsManager) context.getSystemService("appops"), "checkOpNoThrow", num, Integer.valueOf(applicationInfo.uid), str);
        int i2 = (Integer) k0.b(AppOpsManager.class, "MODE_ALLOWED");
        int i3 = (Integer) k0.b(AppOpsManager.class, "MODE_IGNORED");
        g.j.a.a.a.c.y(String.format("get app mode %s|%s|%s", num2, i2, i3));
        if (i2 == null) {
            i2 = 0;
        }
        if (i3 == null) {
            i3 = 1;
        }
        if (num2 != null) {
            return z ? !num2.equals(i3) ? b.ALLOWED : b.NOT_ALLOWED : num2.equals(i2) ? b.ALLOWED : b.NOT_ALLOWED;
        }
        return b.UNKNOWN;
    }

    public static String g() {
        String processName = Build.VERSION.SDK_INT >= 28 ? Application.getProcessName() : (String) k0.g("android.app.ActivityThread", "currentProcessName", new Object[0]);
        return !TextUtils.isEmpty(processName) ? processName : "";
    }

    public static String h(Context context, String str) {
        PackageInfo packageInfo;
        try {
            packageInfo = context.getPackageManager().getPackageInfo(str, 16384);
        } catch (Exception unused) {
            packageInfo = null;
        }
        return packageInfo != null ? packageInfo.versionName : "1.0";
    }

    public static Map<String, String> i(Context context, String str) {
        a aVar = a;
        if (aVar == null) {
            return null;
        }
        return aVar.a(context, str);
    }

    public static boolean j(Context context) {
        String g2 = g();
        if (TextUtils.isEmpty(g2) || context == null) {
            return false;
        }
        return g2.equals(context.getPackageName());
    }

    public static boolean k(Context context, String str) {
        if (context == null || TextUtils.isEmpty(str)) {
            return false;
        }
        if (a8.i()) {
            a aVar = a;
            return aVar != null && aVar.m184a(context, str);
        }
        return context.getPackageName().equals(str);
    }

    public static int l(Context context, String str) {
        ApplicationInfo c2 = c(context, str);
        if (c2 != null) {
            int i2 = c2.icon;
            return i2 == 0 ? c2.logo : i2;
        }
        return 0;
    }

    public static String m(Context context, String str) {
        ApplicationInfo applicationInfo;
        try {
            PackageManager packageManager = context.getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(str, 0);
            return (packageInfo == null || (applicationInfo = packageInfo.applicationInfo) == null) ? str : packageManager.getApplicationLabel(applicationInfo).toString();
        } catch (PackageManager.NameNotFoundException unused) {
            return str;
        }
    }

    public static boolean n(Context context, String str) {
        a aVar = a;
        return aVar != null && aVar.b(context, str);
    }

    public static boolean o(Context context, String str) {
        PackageInfo packageInfo;
        try {
            packageInfo = context.getPackageManager().getPackageInfo(str, 0);
        } catch (PackageManager.NameNotFoundException unused) {
            packageInfo = null;
        }
        return packageInfo != null;
    }

    public static boolean p(Context context, String str) {
        return context.getPackageManager().checkPermission(str, context.getPackageName()) == 0;
    }

    public static boolean q(Context context, String str) {
        if (context == null || TextUtils.isEmpty(str)) {
            return false;
        }
        try {
            if (Settings.Secure.getInt(context.getContentResolver(), "freeform_window_state", -1) >= 0) {
                return str.equals(BaseInfo.getAndroidIdWithAOPBySecure(context.getContentResolver(), "freeform_package_name"));
            }
            return false;
        } catch (Exception unused) {
            return false;
        }
    }
}

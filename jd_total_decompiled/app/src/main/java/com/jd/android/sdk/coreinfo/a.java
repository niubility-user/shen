package com.jd.android.sdk.coreinfo;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.res.Resources;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.Display;
import com.jd.android.sdk.coreinfo.util.Logger;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class a {
    private static String a = "";
    private static String b = "";

    /* renamed from: c  reason: collision with root package name */
    private static String f1670c = "";
    private static int d;

    /* renamed from: e  reason: collision with root package name */
    private static long f1671e;

    /* renamed from: f  reason: collision with root package name */
    private static long f1672f;

    /* renamed from: g  reason: collision with root package name */
    private static long f1673g;

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String a(Context context) {
        if (TextUtils.isEmpty(a)) {
            if (context == null) {
                Logger.w("AppInfo", "context is null");
                return "";
            }
            PackageInfo c2 = c(context, 16384);
            if (c2 == null) {
                Logger.w("AppInfo", "packageInfo is null");
                return "";
            }
            a = context.getPackageManager().getApplicationLabel(c2.applicationInfo).toString();
        }
        return a;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String b(Context context) {
        if (TextUtils.isEmpty(b)) {
            if (context == null) {
                Logger.w("AppInfo", "context is null");
                return "";
            }
            b = context.getPackageName();
        }
        return b;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String c(Context context) {
        if (TextUtils.isEmpty(f1670c)) {
            if (context == null) {
                Logger.w("AppInfo", "context is null");
                return "";
            }
            PackageInfo c2 = c(context, 16384);
            if (c2 == null) {
                Logger.w("AppInfo", "packageInfo is null");
                return "";
            }
            f1670c = c2.versionName;
        }
        return f1670c;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int d(Context context) {
        if (d <= 0) {
            if (context == null) {
                Logger.w("AppInfo", "context is null");
                return 0;
            }
            PackageInfo c2 = c(context, 16384);
            if (c2 == null) {
                Logger.w("AppInfo", "packageInfo is null");
                return 0;
            }
            d = c2.versionCode;
        }
        return d;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static long e(Context context) {
        if (f1671e <= 0) {
            if (context == null) {
                Logger.w("AppInfo", "context is null");
                return 0L;
            }
            PackageInfo c2 = c(context, 16384);
            if (c2 == null) {
                Logger.w("AppInfo", "packageInfo is null");
                return 0L;
            }
            f1671e = c2.firstInstallTime;
        }
        return f1671e;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static long f(Context context) {
        if (f1672f <= 0) {
            if (context == null) {
                Logger.w("AppInfo", "context is null");
                return 0L;
            }
            PackageInfo c2 = c(context, 16384);
            if (c2 == null) {
                Logger.w("AppInfo", "packageInfo is null");
                return 0L;
            }
            f1672f = c2.lastUpdateTime;
        }
        return f1672f;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static long g(Context context) {
        if (f1673g <= 0) {
            if (context == null) {
                Logger.w("AppInfo", "context is null");
                return 0L;
            }
            if (c(context, 64) == null) {
                Logger.w("AppInfo", "packageInfo is null");
                return 0L;
            }
            f1673g = r5.signatures[0].hashCode();
        }
        return f1673g;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int h(Context context) {
        if (context == null) {
            Logger.w("AppInfo", "context is null");
            return 0;
        }
        Resources resources = context.getResources();
        return resources.getDimensionPixelSize(resources.getIdentifier("status_bar_height", "dimen", "android"));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Deprecated
    public static List<ActivityManager.RunningAppProcessInfo> i(Context context) {
        SensitiveApi sensitiveApi = CoreInfo.sensitiveApi;
        if (sensitiveApi != null) {
            return sensitiveApi.getRunningAppProcesses(context);
        }
        return new ArrayList();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Deprecated
    public static List<ActivityManager.RunningTaskInfo> b(Context context, int i2) {
        SensitiveApi sensitiveApi = CoreInfo.sensitiveApi;
        if (sensitiveApi != null) {
            return sensitiveApi.getRunningTasks(context, i2);
        }
        return new ArrayList();
    }

    private static PackageInfo c(Context context, int i2) {
        try {
            return context.getPackageManager().getPackageInfo(b(context), i2);
        } catch (Exception e2) {
            Logger.e("AppInfo", "An exception happends when call getPackageInfo().", e2);
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @TargetApi(17)
    public static boolean a(Activity activity) {
        if (activity == null) {
            Logger.w("AppInfo", "activity is null");
            return false;
        }
        Display defaultDisplay = activity.getWindowManager().getDefaultDisplay();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        defaultDisplay.getRealMetrics(displayMetrics);
        int i2 = displayMetrics.heightPixels;
        int i3 = displayMetrics.widthPixels;
        DisplayMetrics displayMetrics2 = new DisplayMetrics();
        defaultDisplay.getMetrics(displayMetrics2);
        return i2 - displayMetrics2.heightPixels > 0 || i3 - displayMetrics2.widthPixels > 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Deprecated
    public static List<ActivityManager.RunningServiceInfo> a(Context context, int i2) {
        SensitiveApi sensitiveApi = CoreInfo.sensitiveApi;
        if (sensitiveApi != null) {
            return sensitiveApi.getRunningServices(context, i2);
        }
        return new ArrayList();
    }
}

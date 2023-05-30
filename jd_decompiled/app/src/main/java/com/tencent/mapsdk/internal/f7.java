package com.tencent.mapsdk.internal;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.ConfigurationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import com.jingdong.sdk.baseinfo.BaseInfo;
import com.tencent.map.tools.Util;
import com.tencent.smtt.sdk.ProxyConfig;
import java.net.URLEncoder;

/* loaded from: classes9.dex */
public class f7 {
    private static final int a = 65537;
    public static final int b = 400;

    /* renamed from: c */
    public static final String f16507c = "TencentMapSDK";
    private static String d;

    /* renamed from: e */
    private static String f16508e;

    /* renamed from: f */
    private static String f16509f;

    public static Rect a(View view) {
        if (view == null) {
            return new Rect();
        }
        int[] iArr = new int[2];
        view.getLocationInWindow(iArr);
        int i2 = iArr[0];
        int i3 = iArr[1];
        return new Rect(i2, i3, view.getMeasuredWidth() + i2, view.getMeasuredHeight() + i3);
    }

    public static String a() {
        if (f16508e == null) {
            f16508e = BaseInfo.getDeviceModel();
        }
        return f16508e;
    }

    public static String a(Context context) {
        if (context == null) {
            return "";
        }
        PackageManager packageManager = context.getPackageManager();
        ApplicationInfo applicationInfo = null;
        try {
            applicationInfo = packageManager.getApplicationInfo(context.getPackageName(), 128);
        } catch (PackageManager.NameNotFoundException e2) {
            e2.printStackTrace();
        }
        try {
            return URLEncoder.encode((applicationInfo != null ? applicationInfo.loadLabel(packageManager) : "can't find app name").toString(), "utf-8");
        } catch (Exception unused) {
            return "";
        }
    }

    public static String a(Context context, String str) {
        Bundle bundle;
        if (context == null) {
            return "";
        }
        ApplicationInfo applicationInfo = null;
        try {
            applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128);
        } catch (PackageManager.NameNotFoundException e2) {
            e2.printStackTrace();
        }
        return (applicationInfo == null || (bundle = applicationInfo.metaData) == null) ? "" : bundle.getString(str);
    }

    public static boolean a(View view, float f2, float f3) {
        if (view == null) {
            return false;
        }
        int[] iArr = new int[2];
        view.getLocationOnScreen(iArr);
        int i2 = iArr[0];
        int i3 = iArr[1];
        return f3 >= ((float) i3) && f3 <= ((float) (view.getMeasuredHeight() + i3)) && f2 >= ((float) i2) && f2 <= ((float) (view.getMeasuredWidth() + i2));
    }

    public static String b() {
        if (f16509f == null) {
            f16509f = Util.getMD5String(Util.getUUID());
        }
        return f16509f;
    }

    public static String b(Context context) {
        if (context == null) {
            return "";
        }
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            return packageInfo.versionName + packageInfo.versionCode;
        } catch (Exception e2) {
            e2.printStackTrace();
            return "";
        }
    }

    public static String c() {
        if (d == null) {
            d = "undefined";
        }
        return d;
    }

    public static int[] c(Context context) {
        Point point2 = new Point();
        ((WindowManager) context.getSystemService("window")).getDefaultDisplay().getSize(point2);
        return new int[]{point2.x, point2.y};
    }

    public static float d(Context context) {
        if (context == null) {
            return 1.0f;
        }
        return context.getResources().getDisplayMetrics().density;
    }

    public static String e(Context context) {
        return Util.getMD5String(Util.getDuid(context));
    }

    public static int f(Context context) {
        ConfigurationInfo deviceConfigurationInfo = ((ActivityManager) context.getSystemService("activity")).getDeviceConfigurationInfo();
        return deviceConfigurationInfo != null ? deviceConfigurationInfo.reqGlEsVersion : a;
    }

    public static String g(Context context) {
        if (context == null) {
            return "";
        }
        try {
            DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
            return displayMetrics.widthPixels + ProxyConfig.MATCH_ALL_SCHEMES + displayMetrics.heightPixels;
        } catch (Exception e2) {
            e2.printStackTrace();
            return "";
        }
    }

    public static String h(Context context) {
        return Util.getMD5String(Util.getSuid(context));
    }

    public static int i(Context context) {
        return context.getResources().getDisplayMetrics().heightPixels;
    }

    public static int j(Context context) {
        return context.getResources().getDisplayMetrics().widthPixels;
    }
}

package com.tencent.smtt.utils;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;
import com.huawei.hms.framework.common.ContainerUtils;
import com.tencent.connect.common.Constants;
import com.tencent.mapsdk.internal.la;
import com.tencent.smtt.sdk.ProxyConfig;
import com.tencent.smtt.sdk.TbsConfig;
import com.tencent.smtt.sdk.WebView;

/* loaded from: classes9.dex */
public class l {
    private static String a = null;
    private static String b = "GA";

    /* renamed from: c  reason: collision with root package name */
    private static String f17966c = "GE";
    private static String d = "9422";

    /* renamed from: e  reason: collision with root package name */
    private static String f17967e = "0";

    /* renamed from: f  reason: collision with root package name */
    private static String f17968f = "";

    /* renamed from: g  reason: collision with root package name */
    private static boolean f17969g;

    /* renamed from: h  reason: collision with root package name */
    private static boolean f17970h;

    /* renamed from: i  reason: collision with root package name */
    private static boolean f17971i;

    public static String a(Context context) {
        return a(context, "0");
    }

    public static String a(Context context, String str) {
        if (TextUtils.isEmpty(a)) {
            String a2 = a(context, String.valueOf(WebView.getTbsSDKVersion(context)), str, b, f17966c, d, f17967e, f17968f, f17969g);
            a = a2;
            return a2;
        }
        return a;
    }

    /* JADX WARN: Removed duplicated region for block: B:24:0x008f  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x00b4  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x00ce  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x00fd  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private static String a(Context context, String str, String str2, String str3, String str4, String str5, String str6, String str7, boolean z) {
        String str8;
        String str9;
        StringBuilder sb = new StringBuilder();
        String str10 = b(context) + ProxyConfig.MATCH_ALL_SCHEMES + c(context);
        try {
            ApplicationInfo applicationInfo = context.getApplicationContext().getApplicationInfo();
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(applicationInfo.packageName, 0);
            str8 = applicationInfo.packageName;
            try {
                if (TextUtils.isEmpty(str7)) {
                    str7 = packageInfo.versionName;
                }
            } catch (PackageManager.NameNotFoundException e2) {
                e = e2;
                e.printStackTrace();
                str7 = "";
                String a2 = a(str8);
                String str11 = "PAD";
                str11 = "QB".equals(a2) ? "PHONE" : "PHONE";
                sb.append("QV");
                sb.append(ContainerUtils.KEY_VALUE_DELIMITER);
                sb.append("3");
                a(sb, "PL", "ADR");
                a(sb, "PR", a2);
                a(sb, "PP", str8);
                a(sb, "PPVN", str7);
                if (!TextUtils.isEmpty(str)) {
                }
                a(sb, "CO", "SYS");
                if (!TextUtils.isEmpty(str2)) {
                    a(sb, "CO", "BK");
                }
                if (!TextUtils.isEmpty(str2)) {
                }
                a(sb, "PB", str4);
                a(sb, "VE", str3);
                a(sb, "DE", str11);
                if (TextUtils.isEmpty(str6)) {
                }
                a(sb, "CHID", str6);
                a(sb, "LCID", str5);
                a(sb, "MO", "unknown");
                a(sb, "RL", str10);
                str9 = Build.VERSION.RELEASE;
                str9 = new String(str9.getBytes("UTF-8"), "ISO8859-1");
                if (!TextUtils.isEmpty(str9)) {
                }
                a(sb, "API", Build.VERSION.SDK_INT + "");
                return sb.toString();
            }
        } catch (PackageManager.NameNotFoundException e3) {
            e = e3;
            str8 = "";
        }
        String a22 = a(str8);
        String str112 = "PAD";
        if ("QB".equals(a22) ? !d(context) : !z) {
        }
        sb.append("QV");
        sb.append(ContainerUtils.KEY_VALUE_DELIMITER);
        sb.append("3");
        a(sb, "PL", "ADR");
        a(sb, "PR", a22);
        a(sb, "PP", str8);
        a(sb, "PPVN", str7);
        if (!TextUtils.isEmpty(str)) {
            a(sb, "TBSVC", str);
        }
        a(sb, "CO", "SYS");
        if (!TextUtils.isEmpty(str2) && !str2.equals("0")) {
            a(sb, "CO", "BK");
        }
        if (!TextUtils.isEmpty(str2)) {
            a(sb, "COVC", str2);
        }
        a(sb, "PB", str4);
        a(sb, "VE", str3);
        a(sb, "DE", str112);
        if (TextUtils.isEmpty(str6)) {
            str6 = "0";
        }
        a(sb, "CHID", str6);
        a(sb, "LCID", str5);
        a(sb, "MO", "unknown");
        a(sb, "RL", str10);
        str9 = Build.VERSION.RELEASE;
        try {
            str9 = new String(str9.getBytes("UTF-8"), "ISO8859-1");
        } catch (Exception unused) {
        }
        if (!TextUtils.isEmpty(str9)) {
            a(sb, "OS", str9);
        }
        a(sb, "API", Build.VERSION.SDK_INT + "");
        return sb.toString();
    }

    private static String a(String str) {
        return "com.tencent.mm".equals(str) ? "WX" : "com.tencent.mobileqq".equals(str) ? Constants.SOURCE_QQ : "com.qzone".equals(str) ? "QZ" : TbsConfig.APP_QB.equals(str) ? "QB" : la.f16825l;
    }

    private static void a(StringBuilder sb, String str, String str2) {
        sb.append(ContainerUtils.FIELD_DELIMITER);
        sb.append(str);
        sb.append(ContainerUtils.KEY_VALUE_DELIMITER);
        sb.append(str2);
    }

    private static int b(Context context) {
        Display defaultDisplay = ((WindowManager) context.getSystemService("window")).getDefaultDisplay();
        if (defaultDisplay != null) {
            return defaultDisplay.getWidth();
        }
        return -1;
    }

    private static int c(Context context) {
        Display defaultDisplay = ((WindowManager) context.getSystemService("window")).getDefaultDisplay();
        if (defaultDisplay != null) {
            return defaultDisplay.getHeight();
        }
        return -1;
    }

    private static boolean d(Context context) {
        if (f17970h) {
            return f17971i;
        }
        try {
            boolean z = (Math.min(b(context), c(context)) * 160) / e(context) >= 700;
            f17971i = z;
            f17970h = true;
            return z;
        } catch (Throwable unused) {
            return false;
        }
    }

    private static int e(Context context) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        Display defaultDisplay = ((WindowManager) context.getSystemService("window")).getDefaultDisplay();
        if (defaultDisplay != null) {
            defaultDisplay.getMetrics(displayMetrics);
            return displayMetrics.densityDpi;
        }
        return 160;
    }
}

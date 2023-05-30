package com.tencent.smtt.utils;

import android.content.Context;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;
import com.huawei.hms.framework.common.ContainerUtils;
import com.tencent.connect.common.Constants;
import com.tencent.mapsdk.internal.la;
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
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static java.lang.String a(android.content.Context r7, java.lang.String r8, java.lang.String r9, java.lang.String r10, java.lang.String r11, java.lang.String r12, java.lang.String r13, java.lang.String r14, boolean r15) {
        /*
            Method dump skipped, instructions count: 285
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.utils.l.a(android.content.Context, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, boolean):java.lang.String");
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

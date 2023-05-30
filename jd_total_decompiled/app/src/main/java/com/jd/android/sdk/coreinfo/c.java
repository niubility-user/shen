package com.jd.android.sdk.coreinfo;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.LocaleList;
import android.os.SystemClock;
import android.text.TextUtils;
import com.jd.android.sdk.coreinfo.util.CommonUtil;
import java.util.List;
import java.util.TimeZone;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class c {
    private static String a = "";
    private static String b = "";

    /* renamed from: c  reason: collision with root package name */
    private static String f1685c = "";
    private static String d = "";

    /* renamed from: e  reason: collision with root package name */
    private static String f1686e = "";

    /* renamed from: f  reason: collision with root package name */
    private static String f1687f = "";

    /* renamed from: g  reason: collision with root package name */
    private static int f1688g = 0;

    /* renamed from: h  reason: collision with root package name */
    private static String f1689h = "";

    /* renamed from: i  reason: collision with root package name */
    private static String f1690i = "";

    /* renamed from: j  reason: collision with root package name */
    private static String f1691j = "";

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String a() {
        if (TextUtils.isEmpty(a)) {
            a = CommonUtil.ensureNotEmpty(Build.DISPLAY);
        }
        return a;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String b() {
        if (TextUtils.isEmpty(b)) {
            b = CommonUtil.ensureNotEmpty(Build.TYPE);
        }
        return b;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String c() {
        if (TextUtils.isEmpty(f1685c)) {
            f1685c = CommonUtil.ensureNotEmpty(Build.TAGS);
        }
        return f1685c;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String d() {
        if (TextUtils.isEmpty(d)) {
            d = CommonUtil.ensureNotEmpty(Build.FINGERPRINT);
        }
        return d;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String e() {
        if (TextUtils.isEmpty(f1686e)) {
            f1686e = com.jd.android.sdk.coreinfo.deviceUtil.c.a();
        }
        return f1686e;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String f() {
        if (TextUtils.isEmpty(f1687f)) {
            f1687f = CommonUtil.ensureNotEmpty(Build.VERSION.RELEASE);
        }
        return f1687f;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int g() {
        if (f1688g == 0) {
            f1688g = Build.VERSION.SDK_INT;
        }
        return f1688g;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static long h() {
        return SystemClock.elapsedRealtime();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String i() {
        TimeZone timeZone;
        try {
            if (TextUtils.isEmpty(f1689h) && (timeZone = TimeZone.getDefault()) != null) {
                f1689h = timeZone.getID();
            }
        } catch (Throwable unused) {
        }
        return f1689h;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean a(Context context, String str) {
        com.jd.android.sdk.coreinfo.util.a.a();
        return com.jd.android.sdk.coreinfo.util.a.a(context, str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String b(Context context) {
        Resources resources;
        Configuration configuration;
        try {
            if (TextUtils.isEmpty(f1691j) && context != null && (resources = context.getResources()) != null && (configuration = resources.getConfiguration()) != null) {
                if (Build.VERSION.SDK_INT >= 24) {
                    LocaleList locales = configuration.getLocales();
                    if (locales != null && locales.size() > 0) {
                        f1691j = locales.get(0).getLanguage();
                    }
                } else {
                    f1691j = configuration.locale.getLanguage();
                }
            }
        } catch (Exception unused) {
        }
        return f1691j;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static List<PackageInfo> a(Context context, int i2) {
        com.jd.android.sdk.coreinfo.util.a.a();
        return com.jd.android.sdk.coreinfo.util.a.a(context, i2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String a(Context context) {
        Resources resources;
        Configuration configuration;
        try {
            if (TextUtils.isEmpty(f1690i) && context != null && (resources = context.getResources()) != null && (configuration = resources.getConfiguration()) != null) {
                if (Build.VERSION.SDK_INT >= 24) {
                    LocaleList locales = configuration.getLocales();
                    if (locales != null && locales.size() > 0) {
                        f1690i = locales.get(0).getCountry();
                    }
                } else {
                    f1690i = configuration.locale.getCountry();
                }
            }
        } catch (Exception unused) {
        }
        return f1690i;
    }
}

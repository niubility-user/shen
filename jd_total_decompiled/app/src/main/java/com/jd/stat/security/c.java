package com.jd.stat.security;

import android.app.Application;
import android.content.Context;
import android.text.TextUtils;

/* loaded from: classes18.dex */
public class c {
    public static Context a = null;
    public static Application b = null;

    /* renamed from: c */
    public static boolean f7045c = false;
    private static String d = null;

    /* renamed from: e */
    private static String f7046e = null;

    /* renamed from: f */
    private static String f7047f = null;

    /* renamed from: g */
    private static String f7048g = null;

    /* renamed from: h */
    private static String f7049h = null;

    /* renamed from: i */
    private static String f7050i = null;

    /* renamed from: j */
    private static boolean f7051j = false;

    /* renamed from: k */
    private static String f7052k = "";

    /* renamed from: l */
    private static PrivacyHelper f7053l;

    /* renamed from: m */
    private static InfoCollectHelper f7054m;

    /* renamed from: n */
    private static String f7055n;
    private static String o;
    private static WSKeyHelper p;
    private static int q;

    public static void a(Context context) {
        if (a == null) {
            if (context instanceof Application) {
                b = (Application) context;
                a = context;
            } else if (context != null) {
                Context applicationContext = context.getApplicationContext();
                a = applicationContext;
                if (applicationContext instanceof Application) {
                    b = (Application) applicationContext;
                }
            }
        }
        if (com.jd.stat.network.b.b != null || context == null) {
            return;
        }
        com.jd.stat.network.b.b = context.getApplicationContext();
    }

    public static String b() {
        String str = f7046e;
        return str == null ? "" : str;
    }

    public static String c() {
        String str = f7047f;
        return str == null ? "" : str;
    }

    public static String d() {
        String str = f7049h;
        return str == null ? "" : str;
    }

    public static boolean e() {
        return f7051j;
    }

    public static String f() {
        WSKeyHelper wSKeyHelper = p;
        if (wSKeyHelper != null) {
            f7050i = wSKeyHelper.getPin();
        }
        return TextUtils.isEmpty(f7050i) ? "" : f7050i;
    }

    public static String g() {
        String str = o;
        return str == null ? "" : str;
    }

    public static String h() {
        String str = f7048g;
        return str == null ? "" : str;
    }

    public static String i() {
        return TextUtils.isEmpty(f7052k) ? "" : f7052k;
    }

    public static PrivacyHelper j() {
        return f7053l;
    }

    public static boolean k() {
        try {
            boolean z = j() != null && j().isOpen();
            String str = "isOpen = " + z;
            return z;
        } catch (Exception e2) {
            e2.printStackTrace();
            return false;
        }
    }

    public static InfoCollectHelper l() {
        return f7054m;
    }

    public static String m() {
        String str = f7055n;
        return str == null ? "" : str;
    }

    public static String n() {
        WSKeyHelper wSKeyHelper = p;
        if (wSKeyHelper == null) {
            return "";
        }
        String wsKey = wSKeyHelper.getWsKey();
        return TextUtils.isEmpty(wsKey) ? "" : wsKey;
    }

    public static void b(String str) {
        o = str;
    }

    public static void a(TrackBaseData trackBaseData) {
        if (trackBaseData != null) {
            d = trackBaseData.getDeviceCode();
            f7046e = trackBaseData.getUnionId();
            f7047f = trackBaseData.getSubunionId();
            f7049h = trackBaseData.getPartner();
            f7050i = trackBaseData.getPin();
            f7048g = trackBaseData.getInstalltionid();
            f7052k = trackBaseData.getOaid();
            f7053l = trackBaseData.getPrivacyHelper();
            f7055n = trackBaseData.getAid();
            a(trackBaseData.getWsKeyHelper());
            a(trackBaseData.getInfoCollectHelper());
            q = trackBaseData.getTestFlag();
            f7045c = k();
        }
    }

    public static void a(String str) {
        if (com.jd.stat.common.b.b.a) {
            com.jd.stat.common.b.b.b("try update extUniqueId = " + str);
        }
        if (TextUtils.isEmpty(str)) {
            return;
        }
        d = str;
    }

    public static String a() {
        String str = d;
        return str == null ? "" : str;
    }

    public static void a(boolean z) {
        f7051j = z;
    }

    public static boolean a(String str, String str2) {
        try {
            boolean z = l() != null && l().canCollect(com.jd.stat.a.a(str), str2);
            String str3 = "infoCanCollect = code:" + z + " sceneParams:" + str2 + " key:" + str + " can:" + z;
            return z;
        } catch (Exception e2) {
            e2.printStackTrace();
            return false;
        }
    }

    public static void a(InfoCollectHelper infoCollectHelper) {
        f7054m = infoCollectHelper;
    }

    public static void a(WSKeyHelper wSKeyHelper) {
        p = wSKeyHelper;
    }

    public static boolean a(int i2) {
        return (i2 & q) != 0;
    }
}

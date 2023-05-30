package com.jd.sec;

import android.text.TextUtils;
import com.jd.sec.LogoManager;

/* loaded from: classes17.dex */
public final class a {
    private static boolean a = false;
    private static String b = null;

    /* renamed from: c */
    private static String f6866c = null;
    private static String d = null;

    /* renamed from: e */
    private static LogoManager.IEnv f6867e = null;

    /* renamed from: f */
    private static String f6868f = "CCO-RISK";

    public static String a() {
        return b;
    }

    public static void a(InitParams initParams) {
        if (initParams != null) {
            a = initParams.b();
            if (!TextUtils.isEmpty(initParams.c())) {
                b = initParams.c();
            }
            if (!TextUtils.isEmpty(initParams.d())) {
                f6866c = initParams.d();
            }
            if (!TextUtils.isEmpty(initParams.a())) {
                d = initParams.a();
            }
            if (initParams.e() != null) {
                f6867e = initParams.e();
            }
        }
    }

    public static String b() {
        return d;
    }

    public static String c() {
        return f6868f;
    }

    public static LogoManager.IEnv d() {
        return f6867e;
    }
}

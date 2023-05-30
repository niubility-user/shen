package jd.wjlogin_sdk.util;

import android.content.Context;
import android.content.SharedPreferences;

/* loaded from: classes.dex */
public class w {
    private static final String a = "WJLogin.ShareStoreUtil";
    private static SharedPreferences b;

    /* renamed from: c  reason: collision with root package name */
    private static Context f19993c;

    public static synchronized SharedPreferences a() {
        SharedPreferences sharedPreferences;
        synchronized (w.class) {
            if (b == null) {
                b = jd.wjlogin_sdk.common.b.a().getSharedPreferences(a(jd.wjlogin_sdk.common.b.a()), 0);
            }
            sharedPreferences = b;
        }
        return sharedPreferences;
    }

    public static void b(Context context) {
        f19993c = context;
    }

    public static void b(String str, String str2) {
        a().edit().putString(str, str2).apply();
    }

    public static void b(String str, long j2) {
        a().edit().putLong(str, j2).apply();
    }

    private static String a(Context context) {
        String encrypt16 = MD5.encrypt16("" + g.a(context) + g.c() + g.f());
        if (p.b) {
            p.b(a, "name = " + encrypt16);
        }
        return encrypt16;
    }

    public static void b(String str) {
        a().edit().remove(str).apply();
    }

    public static int a(String str, int i2) {
        return a().getInt(str, i2);
    }

    public static long a(String str, long j2) {
        return a().getLong(str, j2);
    }

    public static String a(String str, String str2) {
        return a().getString(str, str2);
    }

    public static boolean a(String str) {
        return a().contains(str);
    }
}

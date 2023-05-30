package c.t.m.g;

import android.content.Context;
import android.content.SharedPreferences;

/* loaded from: classes.dex */
public class f0 {
    public static Context a;

    public static int a(String str, String str2, int i2) {
        Context context = a;
        return context == null ? i2 : context.getSharedPreferences(str, 0).getInt(str2, i2);
    }

    public static long b(String str, String str2, long j2) {
        Context context = a;
        return context == null ? j2 : context.getSharedPreferences(str, 0).getLong(str2, j2);
    }

    public static String c(String str, String str2, String str3) {
        Context context = a;
        return context == null ? str3 : context.getSharedPreferences(str, 0).getString(str2, str3);
    }

    public static void d(Context context) {
        a = context;
    }

    public static void e(String str, String str2, int i2) {
        SharedPreferences.Editor edit = a.getSharedPreferences(str, 0).edit();
        edit.putInt(str2, i2);
        edit.apply();
    }

    public static void f(String str, String str2, long j2) {
        SharedPreferences.Editor edit = a.getSharedPreferences(str, 0).edit();
        edit.putLong(str2, j2);
        edit.apply();
    }

    public static void g(String str, String str2, String str3) {
        SharedPreferences.Editor edit = a.getSharedPreferences(str, 0).edit();
        edit.putString(str2, str3);
        edit.apply();
    }
}

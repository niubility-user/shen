package c.t.m.g;

import android.content.SharedPreferences;
import android.os.Build;

/* loaded from: classes.dex */
public class r3 {
    public static volatile SharedPreferences a;

    public static synchronized SharedPreferences a() {
        SharedPreferences sharedPreferences;
        synchronized (r3.class) {
            if (a == null) {
                a = y3.a().getSharedPreferences("LocationSDK", 0);
            }
            sharedPreferences = a;
        }
        return sharedPreferences;
    }

    public static SharedPreferences b(String str) {
        if ("LocationSDK".equals(str)) {
            return a();
        }
        return y3.a().getSharedPreferences(str, "com.tencent.mobileqq".equals(y3.a().getPackageName()) ? 4 : 0);
    }

    public static Object c(SharedPreferences sharedPreferences, String str, Object obj) {
        if (obj instanceof String) {
            return sharedPreferences.getString(str, (String) obj);
        }
        if (obj instanceof Integer) {
            return Integer.valueOf(sharedPreferences.getInt(str, ((Integer) obj).intValue()));
        }
        if (obj instanceof Boolean) {
            return Boolean.valueOf(sharedPreferences.getBoolean(str, ((Boolean) obj).booleanValue()));
        }
        if (obj instanceof Float) {
            return Float.valueOf(sharedPreferences.getFloat(str, ((Float) obj).floatValue()));
        }
        if (obj instanceof Long) {
            return Long.valueOf(sharedPreferences.getLong(str, ((Long) obj).longValue()));
        }
        return null;
    }

    public static Object d(String str, String str2, Object obj) {
        return c(b(str), str2, obj);
    }

    public static String e(SharedPreferences sharedPreferences, String str, String str2) {
        String str3 = (String) c(sharedPreferences, str, "");
        if (t2.c(str3)) {
            return str2;
        }
        String a2 = f5.a(str3);
        if (t2.c(a2)) {
            f(sharedPreferences, str, "");
        }
        return t2.c(a2) ? str2 : a2;
    }

    /* JADX WARN: Removed duplicated region for block: B:71:0x0051  */
    /* JADX WARN: Removed duplicated region for block: B:73:0x0055  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static void f(SharedPreferences sharedPreferences, String str, Object obj) {
        String obj2;
        SharedPreferences.Editor edit = sharedPreferences.edit();
        if (!(obj instanceof String)) {
            if (obj instanceof Integer) {
                edit.putInt(str, ((Integer) obj).intValue());
            } else if (obj instanceof Boolean) {
                edit.putBoolean(str, ((Boolean) obj).booleanValue());
            } else if (obj instanceof Float) {
                edit.putFloat(str, ((Float) obj).floatValue());
            } else if (obj instanceof Long) {
                edit.putLong(str, ((Long) obj).longValue());
            } else {
                obj2 = obj.toString();
            }
            if (Build.VERSION.SDK_INT < 9) {
                edit.apply();
                return;
            } else {
                edit.commit();
                return;
            }
        }
        obj2 = (String) obj;
        edit.putString(str, obj2);
        if (Build.VERSION.SDK_INT < 9) {
        }
    }

    public static void g(SharedPreferences sharedPreferences, String str, String str2) {
        f(sharedPreferences, str, f5.h(str2));
    }

    public static void h(String str, String str2, Object obj) {
        f(b(str), str2, obj);
    }
}

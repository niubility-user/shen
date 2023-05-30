package c.t.m.g;

import android.content.SharedPreferences;

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

    /* JADX WARN: Removed duplicated region for block: B:46:0x0051  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x0055  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void f(android.content.SharedPreferences r2, java.lang.String r3, java.lang.Object r4) {
        /*
            android.content.SharedPreferences$Editor r2 = r2.edit()
            boolean r0 = r4 instanceof java.lang.String
            if (r0 == 0) goto Le
            java.lang.String r4 = (java.lang.String) r4
        La:
            r2.putString(r3, r4)
            goto L4b
        Le:
            boolean r0 = r4 instanceof java.lang.Integer
            if (r0 == 0) goto L1c
            java.lang.Integer r4 = (java.lang.Integer) r4
            int r4 = r4.intValue()
            r2.putInt(r3, r4)
            goto L4b
        L1c:
            boolean r0 = r4 instanceof java.lang.Boolean
            if (r0 == 0) goto L2a
            java.lang.Boolean r4 = (java.lang.Boolean) r4
            boolean r4 = r4.booleanValue()
            r2.putBoolean(r3, r4)
            goto L4b
        L2a:
            boolean r0 = r4 instanceof java.lang.Float
            if (r0 == 0) goto L38
            java.lang.Float r4 = (java.lang.Float) r4
            float r4 = r4.floatValue()
            r2.putFloat(r3, r4)
            goto L4b
        L38:
            boolean r0 = r4 instanceof java.lang.Long
            if (r0 == 0) goto L46
            java.lang.Long r4 = (java.lang.Long) r4
            long r0 = r4.longValue()
            r2.putLong(r3, r0)
            goto L4b
        L46:
            java.lang.String r4 = r4.toString()
            goto La
        L4b:
            int r3 = android.os.Build.VERSION.SDK_INT
            r4 = 9
            if (r3 < r4) goto L55
            r2.apply()
            return
        L55:
            r2.commit()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: c.t.m.g.r3.f(android.content.SharedPreferences, java.lang.String, java.lang.Object):void");
    }

    public static void g(SharedPreferences sharedPreferences, String str, String str2) {
        f(sharedPreferences, str, f5.h(str2));
    }

    public static void h(String str, String str2, Object obj) {
        f(b(str), str2, obj);
    }
}

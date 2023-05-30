package com.jd.stat.common.b;

/* loaded from: classes18.dex */
public class b {
    public static boolean a;

    public static void a(boolean z) {
        a = z;
    }

    public static void b(String str) {
        boolean z = a;
    }

    public static void c(String str, String str2) {
        boolean z = a;
    }

    public static void a(String str) {
        boolean z = a;
    }

    public static void b(String str, String str2) {
        boolean z = a;
    }

    public static void a(String str, String str2) {
        boolean z = a;
    }

    public static void b(String str, Object obj) {
        if (!a || obj == null) {
            return;
        }
        obj.toString();
    }

    public static void a(String str, Object obj) {
        if (!a || obj == null) {
            return;
        }
        obj.toString();
    }

    public static void a(String str, Throwable th) {
        if (a) {
            StringBuilder sb = new StringBuilder();
            if (th != null) {
                sb.append(th.getClass().getCanonicalName());
                sb.append(":");
                sb.append(th.getMessage());
            }
            sb.toString();
        }
    }

    public static void a(Object obj) {
        if (!a || obj == null) {
            return;
        }
        obj.toString();
    }

    public static void a(String str, String str2, Throwable th) {
        try {
            boolean z = a;
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }
}

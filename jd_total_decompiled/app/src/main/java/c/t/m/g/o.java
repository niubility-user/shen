package c.t.m.g;

/* loaded from: classes.dex */
public class o {
    public static String a(String str) {
        return str == null ? "" : str;
    }

    public static boolean b(Object obj) {
        return obj == null;
    }

    public static boolean c(Object obj, Object obj2) {
        return obj == null && obj2 == null;
    }

    public static boolean d(Object obj, Object obj2, Object... objArr) {
        if (c(objArr, obj2)) {
            for (Object obj3 : objArr) {
                if (obj3 != null) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    public static boolean e(Object obj) {
        return obj != null;
    }

    public static boolean f(Object obj, Object obj2) {
        return obj == null || obj2 == null;
    }
}

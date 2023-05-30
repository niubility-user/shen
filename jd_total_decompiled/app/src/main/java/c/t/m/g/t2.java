package c.t.m.g;

import java.util.Collection;

/* loaded from: classes.dex */
public class t2 {
    @Deprecated
    public static int a(boolean z) {
        return z ? 1 : 0;
    }

    public static boolean b(Object obj) {
        return obj == null;
    }

    public static boolean c(String str) {
        return str == null || str.length() == 0;
    }

    public static boolean d(Collection collection) {
        return collection == null || collection.isEmpty();
    }

    public static boolean e(byte[] bArr) {
        return bArr == null || bArr.length == 0;
    }

    public static boolean f(double[] dArr) {
        return dArr == null || dArr.length == 0;
    }

    public static boolean g(Object... objArr) {
        return !h(objArr);
    }

    public static boolean h(Object... objArr) {
        for (Object obj : objArr) {
            if (obj == null) {
                return false;
            }
        }
        return true;
    }

    public static boolean i(Object[] objArr) {
        return objArr == null || objArr.length == 0;
    }
}

package c.t.m.g;

/* loaded from: classes.dex */
public class l2 {
    public static <T> T a(T t, T t2) {
        b(t2);
        return t != null ? t : t2;
    }

    public static void b(Object obj) {
        if (obj == null) {
            throw new NullPointerException("object is null.");
        }
    }
}

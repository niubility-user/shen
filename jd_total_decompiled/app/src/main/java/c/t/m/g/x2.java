package c.t.m.g;

import android.os.Build;

/* loaded from: classes.dex */
public class x2 {
    public static volatile String a = "";

    public static String a() {
        return e() ? a : z3.m();
    }

    public static String b() {
        return e() ? "" : z3.o();
    }

    public static String c() {
        return (!e() && Build.VERSION.SDK_INT < 29) ? z3.q() : "";
    }

    public static String d() {
        return e() ? a : z3.r();
    }

    public static boolean e() {
        return false;
    }
}

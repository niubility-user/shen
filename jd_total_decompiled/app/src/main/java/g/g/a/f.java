package g.g.a;

import android.util.Log;

/* loaded from: classes18.dex */
public class f {
    private static boolean a = true;
    private static int b = 5;

    public static void a(String str, String str2) {
        if (!a || b > 3) {
            return;
        }
        Log.isLoggable("jdtts:", 3);
    }

    public static void b(String str, String str2) {
    }

    public static void c(String str, String str2) {
        if (!a || b > 4) {
            return;
        }
        Log.isLoggable("jdtts:", 4);
    }

    public static void d(boolean z, int i2) {
        a = z;
        b = i2;
    }

    public static void e(String str, String str2) {
        if (!a || b > 2) {
            return;
        }
        Log.isLoggable("jdtts:", 2);
    }

    public static void f(String str, String str2) {
        if (!a || b > 5) {
            return;
        }
        Log.isLoggable("jdtts:", 5);
    }
}

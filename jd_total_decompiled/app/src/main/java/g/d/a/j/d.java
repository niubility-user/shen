package g.d.a.j;

/* loaded from: classes12.dex */
public class d {
    private static String a = "MCS";
    private static boolean b = true;

    /* renamed from: c  reason: collision with root package name */
    private static boolean f19445c = true;
    private static String d = "-->";

    /* renamed from: e  reason: collision with root package name */
    private static boolean f19446e = true;

    public static void a(String str) {
        if (b && f19446e) {
            String str2 = a + d + str;
        }
    }

    public static void b(String str) {
        if (f19445c && f19446e) {
            String str2 = a + d + str;
        }
    }

    public static void c(String str, String str2) {
        if (f19445c && f19446e) {
            String str3 = a + d + str2;
        }
    }

    public static void d(boolean z) {
        f19446e = z;
        if (z) {
            b = true;
            f19445c = true;
            return;
        }
        b = false;
        f19445c = false;
    }
}

package g.f.a.b;

/* loaded from: classes12.dex */
public class b {
    public static int a = 7;

    static {
        d(7);
    }

    private static String a(String... strArr) {
        if (strArr == null) {
            return null;
        }
        StringBuffer stringBuffer = new StringBuffer();
        for (String str : strArr) {
            stringBuffer.append(str);
        }
        return stringBuffer.toString();
    }

    public static void b(String str, String... strArr) {
        if (3 < a) {
            return;
        }
        String str2 = "[JDASR_LOG] " + str;
        a(strArr);
    }

    public static void c(String str, String... strArr) {
        if (6 < a) {
            return;
        }
        String str2 = "[JDASR_LOG] " + str;
        a(strArr);
    }

    public static void d(int i2) {
        a = i2;
        String str = "Changing log level to " + i2;
    }
}

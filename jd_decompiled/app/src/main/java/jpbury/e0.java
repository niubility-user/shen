package jpbury;

import android.text.TextUtils;

/* loaded from: classes11.dex */
public class e0 {
    private static final String a = "root\u72b6\u6001\u672a\u77e5";
    private static final String b = "\u672aroot";

    /* renamed from: c  reason: collision with root package name */
    private static final String f20108c = "\u5df2root";
    private static final String d = "\u4ee3\u7406\u72b6\u6001\u672a\u77e5";

    /* renamed from: e  reason: collision with root package name */
    private static final String f20109e = "\u65e0\u4ee3\u7406";

    /* renamed from: f  reason: collision with root package name */
    private static final String f20110f = "\u4ee3\u7406 ";

    /* renamed from: g  reason: collision with root package name */
    private static volatile String f20111g;

    /* renamed from: h  reason: collision with root package name */
    private static volatile String f20112h;

    public static String a() {
        String str;
        if (f20112h != null) {
            return f20112h;
        }
        try {
            String property = System.getProperty("http.proxyHost");
            String property2 = System.getProperty("http.proxyPort");
            if (TextUtils.isEmpty(property) || TextUtils.isEmpty(property2)) {
                str = f20109e;
            } else {
                str = f20110f + property + ":" + property2;
            }
        } catch (Throwable th) {
            th.printStackTrace();
            str = d;
        }
        f20112h = str;
        return f20112h;
    }

    public static String b() {
        if (f20111g != null) {
            return f20111g;
        }
        int b2 = z.b();
        f20111g = b2 != 1 ? b2 != 2 ? a : f20108c : b;
        return f20111g;
    }
}

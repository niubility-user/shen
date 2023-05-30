package jd.wjlogin_sdk.b;

import android.text.TextUtils;
import jd.wjlogin_sdk.util.b0;
import jd.wjlogin_sdk.util.l;
import jd.wjlogin_sdk.util.p;
import jd.wjlogin_sdk.util.v;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class d {
    private static final String a = "WJLogin.EncryptV6";
    static volatile String b;

    /* renamed from: c  reason: collision with root package name */
    static volatile String f19711c;

    private static String a() {
        String str = b0.b() + "#" + System.currentTimeMillis() + "@&ast";
        if (p.b) {
            p.b("EncryptorV6.generateA4Key = " + str);
        }
        return b0.d(str);
    }

    private static String b() {
        String str = b0.b() + "#" + System.currentTimeMillis() + "#azje&";
        if (p.b) {
            p.b("EncryptorA4.originalKey = " + str);
        }
        return b0.d(str);
    }

    public static String c() {
        if (!TextUtils.isEmpty(f19711c)) {
            return f19711c;
        }
        String e2 = v.e(jd.wjlogin_sdk.util.e.p);
        if (!TextUtils.isEmpty(e2)) {
            f19711c = e2;
        }
        return e2;
    }

    public static String d() {
        if (!TextUtils.isEmpty(b)) {
            return b;
        }
        String e2 = v.e(jd.wjlogin_sdk.util.e.p);
        if (!TextUtils.isEmpty(e2)) {
            b = e2;
        }
        return e2;
    }

    public static String e() {
        if (!TextUtils.isEmpty(f19711c)) {
            return f19711c;
        }
        String e2 = v.e(jd.wjlogin_sdk.util.e.r);
        if (!TextUtils.isEmpty(e2)) {
            f19711c = e2;
            return e2;
        }
        String a2 = a();
        f19711c = a2;
        v.a(jd.wjlogin_sdk.util.e.r, a2);
        return a2;
    }

    public static String f() {
        if (!TextUtils.isEmpty(b)) {
            return b;
        }
        String e2 = v.e(jd.wjlogin_sdk.util.e.p);
        if (!TextUtils.isEmpty(e2)) {
            b = e2;
            return e2;
        }
        String b2 = b();
        b = b2;
        v.a(jd.wjlogin_sdk.util.e.p, b2);
        return b2;
    }

    public static String c(String str) {
        if (str == null || "".equals(str)) {
            return "";
        }
        String d = d();
        return TextUtils.isEmpty(d) ? "" : b.a(d, str);
    }

    public static String d(String str) {
        if (str == null || "".equals(str)) {
            return "";
        }
        String f2 = f();
        return TextUtils.isEmpty(f2) ? "" : b.b(f2, str);
    }

    public static String a(String str) {
        if (str != null && !"".equals(str)) {
            try {
                JSONObject jSONObject = new JSONObject(str);
                String optString = jSONObject.optString("k");
                String optString2 = jSONObject.optString("d");
                if (!TextUtils.isEmpty(optString2) && !TextUtils.isEmpty(optString)) {
                    return b.a(optString, optString2);
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return "";
    }

    public static String b(String str) {
        return (str == null || "".equals(str)) ? "" : b.a(c(), str);
    }

    public static String e(String str) {
        if (p.b) {
            p.b(a, "encryptA4 info = " + l.a(str));
        }
        return (str == null || "".equals(str)) ? "" : b.b(e(), str);
    }
}

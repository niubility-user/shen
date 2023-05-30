package jd.wjweblogin.b;

import android.text.TextUtils;
import jd.wjweblogin.d.i;
import jd.wjweblogin.d.j;
import org.json.JSONObject;

/* loaded from: classes11.dex */
public class d {
    private static final String a = "WJWebLogin.Encrypt";
    static volatile String b;

    private static String a() {
        String str = j.b() + "#" + System.currentTimeMillis() + "#wjork&";
        if (jd.wjweblogin.d.g.b) {
            jd.wjweblogin.d.g.b("Encryptor.originalKey = " + str);
        }
        return j.b(str);
    }

    public static String b() {
        if (!TextUtils.isEmpty(b)) {
            return b;
        }
        String f2 = i.f(jd.wjweblogin.d.c.b);
        if (!TextUtils.isEmpty(f2)) {
            b = f2;
        }
        return f2;
    }

    public static String c() {
        if (!TextUtils.isEmpty(b)) {
            return b;
        }
        String f2 = i.f(jd.wjweblogin.d.c.b);
        if (!TextUtils.isEmpty(f2)) {
            b = f2;
            return f2;
        }
        String a2 = a();
        b = a2;
        i.a(jd.wjweblogin.d.c.b, a2);
        return a2;
    }

    public static String b(String str) {
        if (str == null || "".equals(str)) {
            return "";
        }
        String b2 = b();
        if (TextUtils.isEmpty(b2)) {
            return "";
        }
        String a2 = b.a(b2, str);
        try {
            if (jd.wjweblogin.d.g.b) {
                jd.wjweblogin.d.g.b(a, "decryptSPData userinfo = " + a2);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return a2;
    }

    public static String a(String str) {
        if (str != null && !"".equals(str)) {
            try {
                JSONObject jSONObject = new JSONObject(str);
                String optString = jSONObject.optString("k");
                String optString2 = jSONObject.optString("d");
                if (jd.wjweblogin.d.g.b) {
                    jd.wjweblogin.d.g.b(a, "decryptFileData userinfofile k= " + optString);
                    jd.wjweblogin.d.g.b(a, "decryptFileData userinfofile d= " + optString2);
                    jd.wjweblogin.d.g.b(a, "decryptFileData userinfofile = " + jSONObject.toString());
                }
                if (!TextUtils.isEmpty(optString2) && !TextUtils.isEmpty(optString)) {
                    String a2 = b.a(optString, optString2);
                    if (jd.wjweblogin.d.g.b) {
                        jd.wjweblogin.d.g.b(a, "decryptFileData userinfofile = " + a2);
                    }
                    return a2;
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return "";
    }

    public static String c(String str) {
        if (jd.wjweblogin.d.g.b) {
            jd.wjweblogin.d.g.b(a, "encrypt userinfo = " + str);
        }
        if (str == null || "".equals(str)) {
            return "";
        }
        String c2 = c();
        return TextUtils.isEmpty(c2) ? "" : b.b(c2, str);
    }
}

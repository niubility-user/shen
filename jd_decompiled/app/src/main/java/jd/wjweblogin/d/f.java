package jd.wjweblogin.d;

import android.text.TextUtils;
import org.json.JSONObject;

/* loaded from: classes11.dex */
public class f {
    private static final String a = "WJWebLogin.JmaReportUtil";

    public static void a(String str) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("type", str);
            g.b(a, "sendJmaClearWebLoginStatus eventid=NewLogin_CleanLoginExpo jsonparam=" + jSONObject.toString());
            if (jd.wjweblogin.common.c.a.f() != null) {
                jd.wjweblogin.common.c.a.f().jmaReport("", "NewLogin_CleanLoginExpo", jSONObject);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public static void b(String str) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("type", str);
            g.b(a, "sendJmaGoInToWebLogin eventid=NewLogin_Sdk_RefreshExpo jsonparam=" + jSONObject.toString());
            if (jd.wjweblogin.common.c.a.f() != null) {
                jd.wjweblogin.common.c.a.f().jmaReport("", "NewLogin_Sdk_RefreshExpo", jSONObject);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public static void a(String str, String str2) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("type", str);
            jSONObject.put("data", str2);
            g.b(a, "sendJmaReponseResult eventid=NewLogin_interfaceExpo jsonparam=" + jSONObject.toString());
            if (jd.wjweblogin.common.c.a.f() != null) {
                jd.wjweblogin.common.c.a.f().jmaReport("", "NewLogin_interfaceExpo", jSONObject);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public static void b(String str, String str2) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(TextUtils.equals("1", str) ? "fail_type" : "success_type", str2);
            g.b(a, "sendJmaWebLoginResult eventid=NewLogin_WebviewResultExpo jsonparam=" + jSONObject.toString());
            if (jd.wjweblogin.common.c.a.f() != null) {
                jd.wjweblogin.common.c.a.f().jmaReport("", "NewLogin_WebviewResultExpo", jSONObject);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }
}

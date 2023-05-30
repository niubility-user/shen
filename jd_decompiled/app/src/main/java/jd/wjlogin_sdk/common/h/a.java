package jd.wjlogin_sdk.common.h;

import android.text.TextUtils;
import jd.wjlogin_sdk.common.f;
import jd.wjlogin_sdk.model.IpModel;
import jd.wjlogin_sdk.util.p;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class a {
    public static IpModel a(String str) {
        try {
            return f.a().a(str);
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static String b() {
        try {
            return f.a().g();
        } catch (Exception e2) {
            e2.printStackTrace();
            return "";
        }
    }

    public static String c() {
        try {
            String h2 = f.a().h();
            String a = a();
            if (TextUtils.isEmpty(a)) {
                p.b("ExtraInfoHelper getDeviceFinger1=" + h2);
                return h2;
            }
            try {
                JSONObject jSONObject = new JSONObject(h2);
                jSONObject.put("requestId", a);
                p.b("ExtraInfoHelper getDeviceFinger2=" + jSONObject.toString());
                return jSONObject.toString();
            } catch (JSONException unused) {
                p.b("ExtraInfoHelper getDeviceFinger3=" + a);
                if (TextUtils.isEmpty(a)) {
                    return "";
                }
                return "{\"requestId\":\"" + a + "\"}";
            }
        } catch (Exception e2) {
            e2.printStackTrace();
            return "";
        }
    }

    public static String d() {
        try {
            return f.a().j();
        } catch (Exception e2) {
            e2.printStackTrace();
            return "";
        }
    }

    public static String e() {
        try {
            return f.a().k();
        } catch (Exception e2) {
            e2.printStackTrace();
            return "";
        }
    }

    public static String f() {
        try {
            return f.a().l();
        } catch (Exception e2) {
            e2.printStackTrace();
            return "";
        }
    }

    public static String g() {
        try {
            return f.a().m();
        } catch (Exception e2) {
            e2.printStackTrace();
            return "";
        }
    }

    public static String h() {
        try {
            return f.a().n();
        } catch (Exception e2) {
            e2.printStackTrace();
            return "";
        }
    }

    public static String i() {
        try {
            return f.a().o();
        } catch (Exception e2) {
            e2.printStackTrace();
            return "";
        }
    }

    public static String j() {
        try {
            return f.a().p();
        } catch (Exception e2) {
            e2.printStackTrace();
            return "";
        }
    }

    public static String k() {
        try {
            return f.a().u();
        } catch (Exception e2) {
            e2.printStackTrace();
            return "";
        }
    }

    public static boolean l() {
        try {
            return f.a().y();
        } catch (Exception e2) {
            e2.printStackTrace();
            return false;
        }
    }

    public static String a() {
        try {
            return f.a().getAntiRpId();
        } catch (Exception e2) {
            e2.printStackTrace();
            return "";
        }
    }
}

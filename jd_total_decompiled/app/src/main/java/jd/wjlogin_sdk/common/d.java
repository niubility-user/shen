package jd.wjlogin_sdk.common;

import android.text.TextUtils;
import jd.wjlogin_sdk.model.WUserSigInfo;
import jd.wjlogin_sdk.util.a0;
import jd.wjlogin_sdk.util.b0;
import jd.wjlogin_sdk.util.o;
import jd.wjlogin_sdk.util.p;
import jd.wjlogin_sdk.util.w;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class d {
    private static final String b = "WJLogin.OldUserCopyManager";
    public WUserSigInfo a = null;

    private void b() {
        try {
            if (a0.a(jd.wjlogin_sdk.util.e.f19939l, false)) {
                return;
            }
            p.b(b, "copy old userinfo start");
            WUserSigInfo c2 = c();
            if (c2 != null) {
                this.a = c2;
                i();
                p.b(b, "copy old userinfo finish.1");
                return;
            }
            WUserSigInfo wUserSigInfo = (WUserSigInfo) jd.wjlogin_sdk.util.f0.a.a(jd.wjlogin_sdk.util.e.f19936i);
            if (wUserSigInfo != null) {
                this.a = wUserSigInfo;
                i();
                p.b(b, "copy old userinfo finish.2");
                return;
            }
            WUserSigInfo wUserSigInfo2 = (WUserSigInfo) o.a(jd.wjlogin_sdk.util.e.f19935h, WUserSigInfo.class);
            if (wUserSigInfo2 != null) {
                this.a = wUserSigInfo2;
                i();
                p.b(b, "copy old userinfo finish.3");
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    private WUserSigInfo c() {
        try {
            String a = a0.a(jd.wjlogin_sdk.util.e.f19937j, "");
            if (TextUtils.isEmpty(a)) {
                return null;
            }
            String a2 = jd.wjlogin_sdk.b.b.a(b0.a(), a);
            if (TextUtils.isEmpty(a2)) {
                return null;
            }
            JSONObject jSONObject = new JSONObject(a2);
            WUserSigInfo wUserSigInfo = new WUserSigInfo();
            wUserSigInfo.createUserInfoFromJSON(jSONObject);
            return wUserSigInfo;
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:24:0x0083 A[Catch: Exception -> 0x0134, TryCatch #1 {Exception -> 0x0134, blocks: (B:3:0x0002, B:5:0x000e, B:8:0x002d, B:10:0x0035, B:11:0x0049, B:13:0x004f, B:15:0x0059, B:22:0x007d, B:24:0x0083, B:17:0x005f, B:19:0x0072, B:21:0x0078, B:26:0x0091, B:28:0x0095, B:29:0x00a9, B:31:0x00bb, B:33:0x00c1, B:35:0x00cb, B:37:0x00cf, B:38:0x00d4, B:40:0x00e2, B:52:0x0130, B:42:0x00ec, B:44:0x00ff, B:46:0x0105, B:48:0x010d, B:49:0x0121), top: B:63:0x0002, inners: #0 }] */
    /* JADX WARN: Removed duplicated region for block: B:65:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private WUserSigInfo d() {
        String a;
        try {
            boolean a2 = a0.a(jd.wjlogin_sdk.util.e.f19940m, false);
            if (p.b) {
                p.b(b, " fromJSONStrV3 copyFile= " + a2);
            }
            String str = "";
            if (!a2) {
                String a3 = a0.a(jd.wjlogin_sdk.util.e.f19941n, "");
                if (p.b) {
                    p.b(b, " fromJSONStrV3 encryptTxt= " + a3);
                }
                if (TextUtils.isEmpty(a3)) {
                    return null;
                }
                String b2 = jd.wjlogin_sdk.b.c.b();
                if (!TextUtils.isEmpty(b2)) {
                    a = jd.wjlogin_sdk.b.b.a(b2, a3);
                } else {
                    JSONObject jSONObject = new JSONObject(a3);
                    String optString = jSONObject.optString("d");
                    String optString2 = jSONObject.optString("k");
                    if (!TextUtils.isEmpty(optString) && !TextUtils.isEmpty(optString2)) {
                        a = jd.wjlogin_sdk.b.b.a(optString2, optString);
                    }
                    if (TextUtils.isEmpty(str)) {
                        JSONObject jSONObject2 = new JSONObject(str);
                        WUserSigInfo wUserSigInfo = new WUserSigInfo();
                        wUserSigInfo.createUserInfoFromJSON(jSONObject2);
                        return wUserSigInfo;
                    }
                    return null;
                }
                str = a;
                if (TextUtils.isEmpty(str)) {
                }
            } else {
                if (p.b) {
                    p.b(b, "copykey = " + a2);
                }
                String string = a0.c().getString(jd.wjlogin_sdk.util.e.f19941n, "");
                String b3 = jd.wjlogin_sdk.b.c.b();
                if (!TextUtils.isEmpty(string) && !TextUtils.isEmpty(b3)) {
                    String a4 = jd.wjlogin_sdk.b.b.a(b3, string);
                    if (TextUtils.isEmpty(a4)) {
                        return null;
                    }
                    if (p.b) {
                        p.b(b, "fromJSONStrV3   userinfoshare new JSONObject = ");
                    }
                    JSONObject jSONObject3 = new JSONObject(a4);
                    WUserSigInfo wUserSigInfo2 = new WUserSigInfo();
                    wUserSigInfo2.createUserInfoFromJSON(jSONObject3);
                    return wUserSigInfo2;
                }
                String d = a0.d();
                if (TextUtils.isEmpty(d)) {
                    return null;
                }
                try {
                    JSONObject jSONObject4 = new JSONObject(d);
                    String optString3 = jSONObject4.optString("d");
                    String optString4 = jSONObject4.optString("k");
                    if (TextUtils.isEmpty(optString3) || TextUtils.isEmpty(optString4)) {
                        return null;
                    }
                    String a5 = jd.wjlogin_sdk.b.b.a(optString4, optString3);
                    if (p.b) {
                        p.b(b, "get userinfofile jsonStr= " + a5);
                    }
                    JSONObject jSONObject5 = new JSONObject(a5);
                    WUserSigInfo wUserSigInfo3 = new WUserSigInfo();
                    wUserSigInfo3.createUserInfoFromJSON(jSONObject5);
                    return wUserSigInfo3;
                } catch (Exception e2) {
                    e2.printStackTrace();
                    return null;
                }
            }
        } catch (Exception e3) {
            if (p.b) {
                p.b(b, "get userinfofile Exception " + e3.getMessage());
            }
            e3.printStackTrace();
            return null;
        }
    }

    private void e() {
        if (this.a == null) {
            this.a = d();
        }
    }

    private void f() {
        if (w.a(jd.wjlogin_sdk.util.e.f19936i)) {
            w.b(jd.wjlogin_sdk.util.e.f19936i);
        }
        if (w.a(jd.wjlogin_sdk.util.e.f19935h)) {
            w.b(jd.wjlogin_sdk.util.e.f19935h);
        }
        if (a0.a(jd.wjlogin_sdk.util.e.f19937j)) {
            a0.b(jd.wjlogin_sdk.util.e.f19937j);
        }
    }

    private void i() {
        if (this.a != null) {
            String c2 = jd.wjlogin_sdk.b.c.c();
            String b2 = jd.wjlogin_sdk.b.b.b(c2, this.a.toJSONString());
            a0.c(jd.wjlogin_sdk.util.e.f19941n, b2);
            try {
                a(c2, b2);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    public void a() {
        b();
        a0.b(jd.wjlogin_sdk.util.e.f19939l, true);
        f();
        e();
    }

    public void g() {
        if (w.a(jd.wjlogin_sdk.config.a.d)) {
            w.b(jd.wjlogin_sdk.config.a.d);
        }
        if (w.a(jd.wjlogin_sdk.util.e.w)) {
            w.b(jd.wjlogin_sdk.util.e.w);
        }
        if (w.a(jd.wjlogin_sdk.util.e.u)) {
            w.b(jd.wjlogin_sdk.util.e.u);
        }
        if (w.a(jd.wjlogin_sdk.util.e.x)) {
            w.b(jd.wjlogin_sdk.util.e.x);
        }
        if (w.a(jd.wjlogin_sdk.util.e.o)) {
            w.b(jd.wjlogin_sdk.util.e.o);
        }
        if (w.a(jd.wjlogin_sdk.util.e.d)) {
            w.b(jd.wjlogin_sdk.util.e.d);
        }
        if (w.a(jd.wjlogin_sdk.util.e.t)) {
            w.b(jd.wjlogin_sdk.util.e.t);
        }
        if (w.a(jd.wjlogin_sdk.util.e.F)) {
            w.b(jd.wjlogin_sdk.util.e.F);
        }
    }

    public void h() {
        if (a0.a(jd.wjlogin_sdk.util.e.f19941n)) {
            a0.b(jd.wjlogin_sdk.util.e.f19941n);
        }
        if (a0.a(jd.wjlogin_sdk.util.e.f19939l)) {
            a0.b(jd.wjlogin_sdk.util.e.f19939l);
        }
        if (a0.a(jd.wjlogin_sdk.util.e.f19940m)) {
            a0.b(jd.wjlogin_sdk.util.e.f19940m);
        }
        if (a0.b()) {
            a0.a();
        }
    }

    private void a(String str, String str2) throws Exception {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("d", str2);
        jSONObject.put("k", str);
        if (p.b) {
            p.b(b, "copyKeyToFile jsonObject1= " + jSONObject.toString());
        }
        a0.c(jSONObject.toString());
        a0.b(jd.wjlogin_sdk.util.e.f19940m, true);
    }
}

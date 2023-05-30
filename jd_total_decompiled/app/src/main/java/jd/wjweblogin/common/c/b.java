package jd.wjweblogin.common.c;

import android.text.TextUtils;
import jd.wjweblogin.b.d;
import jd.wjweblogin.d.g;
import jd.wjweblogin.d.i;
import jd.wjweblogin.model.WJWebLoginSigInfo;
import org.json.JSONObject;

/* loaded from: classes11.dex */
public class b {

    /* renamed from: c  reason: collision with root package name */
    private static final String f20016c = "WJWebLogin.SavaCookieManager";
    private static final String d = "wjweblogin_users_v1";

    /* renamed from: e  reason: collision with root package name */
    private static final String f20017e = "key_wj_weblogin_version";
    public WJWebLoginSigInfo a = null;
    private Object b;

    public b(Object obj) {
        this.b = obj;
    }

    private void a(JSONObject jSONObject) {
        if (g.b) {
            g.b(f20016c, "fromLocalJson  ");
        }
        if (jSONObject == null) {
            if (g.b) {
                g.b(f20016c, "fromLocalJson  json == null");
                return;
            }
            return;
        }
        try {
            WJWebLoginSigInfo wJWebLoginSigInfo = new WJWebLoginSigInfo();
            wJWebLoginSigInfo.createUserInfoFromJSON(jSONObject);
            this.a = wJWebLoginSigInfo;
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    private void c() {
        String a;
        try {
            String g2 = i.g(d);
            if (!TextUtils.isEmpty(g2)) {
                a = d.b(g2);
            } else {
                g2 = i.e();
                a = d.a(g2);
            }
            if (g.b) {
                g.b(f20016c, "getLocalWebUsers  data: " + g2);
                g.b(f20016c, "getLocalWebUsers  decrypt: " + a);
            }
            if (TextUtils.isEmpty(a)) {
                return;
            }
            a(new JSONObject(a));
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    private void d() {
        try {
            String e2 = i.e();
            String a = d.a(e2);
            if (g.b) {
                g.b(f20016c, "getLocalUsersFromFile  data: " + e2);
                g.b(f20016c, "getLocalUsersFromFile  decrypt: " + a);
            }
            if (TextUtils.isEmpty(a)) {
                return;
            }
            a(new JSONObject(a));
        } catch (Exception e3) {
            e3.printStackTrace();
        }
    }

    private void f() {
        if (this.a == null) {
            c();
        }
    }

    private void h() {
        try {
            g.b("saveLocalWebUsers");
            WJWebLoginSigInfo wJWebLoginSigInfo = this.a;
            if (wJWebLoginSigInfo != null) {
                String jSONString = wJWebLoginSigInfo.toJSONString();
                String c2 = d.c();
                String b = jd.wjweblogin.b.b.b(c2, jSONString);
                if (g.b) {
                    g.b(f20016c, "saveLocalWebUsers SP KEY: " + c2);
                    g.b(f20016c, "saveLocalWebUsers  encryptTxt: " + b);
                    g.b(f20016c, "saveLocalWebUsers  json: " + jSONString);
                }
                i.b(d, b);
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("k", c2);
                jSONObject.put("d", b);
                if (g.b) {
                    g.b(f20016c, "saveLocalWebUsers file json: " + jSONObject.toString());
                }
                i.j(jSONObject.toString());
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public WJWebLoginSigInfo b() {
        WJWebLoginSigInfo wJWebLoginSigInfo;
        synchronized (this.b) {
            g.b(f20016c, "currentWebUserInfo");
            if (this.a != null) {
                g.b(f20016c, "currentWebUserInfo wjWebLoginSigInfo ptpin=" + this.a.getPtPin());
            }
            wJWebLoginSigInfo = this.a;
        }
        return wJWebLoginSigInfo;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void e() {
        f();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void g() {
        d();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(WJWebLoginSigInfo wJWebLoginSigInfo) {
        g.b("saveWebLoginSigInfo");
        synchronized (this.b) {
            this.a = wJWebLoginSigInfo;
            h();
        }
    }

    public void a() {
        if (g.b) {
            g.b(f20016c, "clearWebUsers");
        }
        synchronized (this.b) {
            WJWebLoginSigInfo wJWebLoginSigInfo = this.a;
            if (wJWebLoginSigInfo != null) {
                wJWebLoginSigInfo.empty();
            }
            i.b(d, "");
        }
    }
}

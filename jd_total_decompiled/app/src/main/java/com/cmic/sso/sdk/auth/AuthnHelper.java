package com.cmic.sso.sdk.auth;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.os.SystemClock;
import android.text.TextUtils;
import com.cmic.sso.sdk.e.e;
import com.cmic.sso.sdk.e.h;
import com.cmic.sso.sdk.e.j;
import com.cmic.sso.sdk.e.k;
import com.cmic.sso.sdk.e.m;
import com.cmic.sso.sdk.e.n;
import com.cmic.sso.sdk.e.o;
import com.cmic.sso.sdk.e.q;
import com.cmic.sso.sdk.e.r;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class AuthnHelper {
    public static final String SDK_VERSION = "quick_login_android_9.5.5.2";
    @SuppressLint({"StaticFieldLeak"})

    /* renamed from: c */
    private static AuthnHelper f966c;
    private final com.cmic.sso.sdk.auth.a a;
    private final Context b;
    private long d;

    /* renamed from: e */
    private final Handler f967e;

    /* renamed from: f */
    private String f968f;

    /* renamed from: g */
    private final com.cmic.sso.sdk.c f969g;

    /* loaded from: classes.dex */
    public class a implements Runnable {
        private final com.cmic.sso.sdk.a b;

        a(com.cmic.sso.sdk.a aVar) {
            AuthnHelper.this = r1;
            this.b = aVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            JSONObject a;
            if (!r.a(AuthnHelper.this.b).a() && this.b.b("doNetworkSwitch", false)) {
                a = c.a("102508", "\u6570\u636e\u7f51\u7edc\u5207\u6362\u5931\u8d25");
            } else {
                a = c.a("200023", "\u767b\u5f55\u8d85\u65f6");
            }
            AuthnHelper.this.callBackResult(a.optString("resultCode", "200023"), a.optString("desc", "\u767b\u5f55\u8d85\u65f6"), this.b, a);
        }
    }

    private AuthnHelper(Context context) {
        this.d = 8000L;
        Context applicationContext = context.getApplicationContext();
        this.b = applicationContext;
        this.f967e = new Handler(applicationContext.getMainLooper());
        this.a = com.cmic.sso.sdk.auth.a.a(applicationContext);
        r.a(applicationContext);
        k.a(applicationContext);
        j.a(applicationContext);
        this.f969g = new com.cmic.sso.sdk.c();
        n.a(new n.a() { // from class: com.cmic.sso.sdk.auth.AuthnHelper.1
            {
                AuthnHelper.this = this;
            }

            @Override // com.cmic.sso.sdk.e.n.a
            protected void a() {
                String b = k.b("AID", "");
                com.cmic.sso.sdk.e.c.b("AuthnHelper", "aid = " + b);
                if (TextUtils.isEmpty(b)) {
                    AuthnHelper.this.a();
                }
                if (com.cmic.sso.sdk.e.b.a(AuthnHelper.this.b, true)) {
                    com.cmic.sso.sdk.e.c.b("AuthnHelper", "\u751f\u6210androidkeystore\u6210\u529f");
                } else {
                    com.cmic.sso.sdk.e.c.b("AuthnHelper", "\u751f\u6210androidkeystore\u5931\u8d25");
                }
            }
        });
    }

    public static AuthnHelper getInstance(Context context) {
        if (f966c == null) {
            synchronized (AuthnHelper.class) {
                if (f966c == null) {
                    f966c = new AuthnHelper(context);
                }
            }
        }
        return f966c;
    }

    public static void setDebugMode(boolean z) {
        com.cmic.sso.sdk.e.c.a(z);
    }

    public void callBackResult(String str, String str2, com.cmic.sso.sdk.a aVar, JSONObject jSONObject) {
        final JSONObject a2;
        try {
            String b = aVar.b("traceId");
            if (e.a(b)) {
                return;
            }
            synchronized (this) {
                final TokenListener c2 = e.c(b);
                e.b(b);
                if (c2 == null) {
                    return;
                }
                aVar.a("systemEndTime", SystemClock.elapsedRealtime());
                aVar.a("endtime", o.a());
                int c3 = aVar.c("logintype");
                if (jSONObject == null) {
                    jSONObject = c.a(str, str2);
                }
                if (c3 == 3) {
                    a2 = c.a(str, aVar, jSONObject);
                    this.f969g.a();
                } else {
                    a2 = c.a(str, str2, aVar, jSONObject);
                }
                a2.put("scripExpiresIn", String.valueOf(h.a()));
                this.f967e.post(new Runnable() { // from class: com.cmic.sso.sdk.auth.AuthnHelper.6
                    {
                        AuthnHelper.this = this;
                    }

                    @Override // java.lang.Runnable
                    public void run() {
                        c2.onGetTokenComplete(a2);
                    }
                });
                com.cmic.sso.sdk.a.c.a(this.b).a(aVar);
                if (!aVar.b().j() && !q.a(aVar.b())) {
                    a(this.b, str, aVar);
                }
                if (e.a()) {
                    n.a(new n.a() { // from class: com.cmic.sso.sdk.auth.AuthnHelper.7
                        {
                            AuthnHelper.this = this;
                        }

                        @Override // com.cmic.sso.sdk.e.n.a
                        protected void a() {
                            SystemClock.sleep(10000L);
                            if (e.a()) {
                                r.a(AuthnHelper.this.b).b();
                            }
                        }
                    });
                }
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public void delScrip() {
        try {
            h.a(true, true);
            com.cmic.sso.sdk.e.c.b("AuthnHelper", "\u5220\u9664scrip");
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public JSONObject getNetworkType(Context context) {
        JSONObject jSONObject = new JSONObject();
        try {
            boolean a2 = m.a(this.b);
            com.cmic.sso.sdk.b.a.a().a(context, a2);
            String a3 = j.a().a((String) null);
            int a4 = m.a(context, a2, new com.cmic.sso.sdk.a(1));
            jSONObject.put("operatortype", a3);
            StringBuilder sb = new StringBuilder();
            sb.append(a4);
            sb.append("");
            jSONObject.put("networktype", sb.toString());
            StringBuilder sb2 = new StringBuilder();
            sb2.append("\u7f51\u7edc\u7c7b\u578b: ");
            sb2.append(a4);
            com.cmic.sso.sdk.e.c.b("AuthnHelper", sb2.toString());
            StringBuilder sb3 = new StringBuilder();
            sb3.append("\u8fd0\u8425\u5546\u7c7b\u578b: ");
            sb3.append(a3);
            com.cmic.sso.sdk.e.c.b("AuthnHelper", sb3.toString());
            return jSONObject;
        } catch (Exception unused) {
            try {
                jSONObject.put("errorDes", "\u53d1\u751f\u672a\u77e5\u9519\u8bef");
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
            return jSONObject;
        }
    }

    public void getPhoneInfo(final String str, final String str2, final TokenListener tokenListener) {
        final com.cmic.sso.sdk.a a2 = a(tokenListener);
        final a aVar = new a(a2);
        this.f967e.postDelayed(aVar, this.d);
        n.a(new n.a(this.b, a2) { // from class: com.cmic.sso.sdk.auth.AuthnHelper.4
            {
                AuthnHelper.this = this;
            }

            @Override // com.cmic.sso.sdk.e.n.a
            protected void a() {
                if (AuthnHelper.this.a(a2, str, str2, "preGetMobile", 3, tokenListener)) {
                    AuthnHelper.this.f969g.a(AuthnHelper.this.d);
                    AuthnHelper.this.a(a2, aVar);
                }
            }
        });
    }

    public void loginAuth(final String str, final String str2, final TokenListener tokenListener) {
        final com.cmic.sso.sdk.a a2 = a(tokenListener);
        final a aVar = new a(a2);
        this.f967e.postDelayed(aVar, this.d);
        n.a(new n.a(this.b, a2) { // from class: com.cmic.sso.sdk.auth.AuthnHelper.2
            {
                AuthnHelper.this = this;
            }

            @Override // com.cmic.sso.sdk.e.n.a
            protected void a() {
                if (AuthnHelper.this.a(a2, str, str2, "loginAuth", 1, tokenListener)) {
                    AuthnHelper.this.a(a2, aVar);
                }
            }
        });
    }

    public void mobileAuth(final String str, final String str2, final TokenListener tokenListener) {
        final com.cmic.sso.sdk.a a2 = a(tokenListener);
        final a aVar = new a(a2);
        this.f967e.postDelayed(aVar, this.d);
        n.a(new n.a(this.b, a2) { // from class: com.cmic.sso.sdk.auth.AuthnHelper.3
            {
                AuthnHelper.this = this;
            }

            @Override // com.cmic.sso.sdk.e.n.a
            protected void a() {
                if (AuthnHelper.this.a(a2, str, str2, "mobileAuth", 0, tokenListener)) {
                    AuthnHelper.this.a(a2, aVar);
                }
            }
        });
    }

    public void setOverTime(long j2) {
        this.d = j2;
    }

    public void a() {
        String str = "%" + q.b();
        com.cmic.sso.sdk.e.c.b("AuthnHelper", "generate aid = " + str);
        k.a("AID", str);
    }

    private com.cmic.sso.sdk.a a(TokenListener tokenListener) {
        com.cmic.sso.sdk.a aVar = new com.cmic.sso.sdk.a(64);
        String c2 = q.c();
        aVar.a(new com.cmic.sso.sdk.d.a());
        aVar.a("traceId", c2);
        com.cmic.sso.sdk.e.c.a("traceId", c2);
        if (tokenListener != null) {
            e.a(c2, tokenListener);
        }
        return aVar;
    }

    public static AuthnHelper getInstance(Context context, String str) {
        if (f966c == null) {
            synchronized (AuthnHelper.class) {
                if (f966c == null) {
                    f966c = new AuthnHelper(context, str);
                }
            }
        }
        return f966c;
    }

    private AuthnHelper(Context context, String str) {
        this(context);
        this.f968f = str;
    }

    public void a(com.cmic.sso.sdk.a aVar, final a aVar2) {
        this.a.a(aVar, new b() { // from class: com.cmic.sso.sdk.auth.AuthnHelper.5
            {
                AuthnHelper.this = this;
            }

            @Override // com.cmic.sso.sdk.auth.b
            public void a(String str, String str2, com.cmic.sso.sdk.a aVar3, JSONObject jSONObject) {
                AuthnHelper.this.f967e.removeCallbacks(aVar2);
                AuthnHelper.this.callBackResult(str, str2, aVar3, jSONObject);
            }
        });
    }

    public synchronized boolean a(com.cmic.sso.sdk.a aVar, String str, String str2, String str3, int i2, TokenListener tokenListener) {
        com.cmic.sso.sdk.a.a a2 = com.cmic.sso.sdk.a.c.a(this.b).a();
        com.cmic.sso.sdk.e.c.b("AuthnHelper", "umcConfigBean = " + a2.toString());
        aVar.a(a2);
        aVar.a("use2048PublicKey", "rsa2048".equals(this.f968f));
        aVar.a("systemStartTime", SystemClock.elapsedRealtime());
        aVar.a("starttime", o.a());
        aVar.a("loginMethod", str3);
        aVar.a("appkey", str2);
        aVar.a("appid", str);
        aVar.a("timeOut", String.valueOf(this.d));
        boolean a3 = m.a(this.b);
        com.cmic.sso.sdk.b.a.a().a(this.b, a3);
        String b = j.a().b();
        String c2 = j.a().c();
        String a4 = j.a().a(c2);
        aVar.a("operator", c2);
        aVar.a("operatortype", a4);
        aVar.a("logintype", i2);
        com.cmic.sso.sdk.e.c.b("AuthnHelper", "subId = " + b);
        if (!TextUtils.isEmpty(b)) {
            com.cmic.sso.sdk.e.c.a("AuthnHelper", "\u4f7f\u7528subId\u4f5c\u4e3a\u7f13\u5b58key = " + b);
            aVar.a("scripType", "subid");
            aVar.a("scripKey", b);
        } else if (!TextUtils.isEmpty(c2)) {
            com.cmic.sso.sdk.e.c.a("AuthnHelper", "\u4f7f\u7528operator\u4f5c\u4e3a\u7f13\u5b58key = " + c2);
            aVar.a("scripType", "operator");
            aVar.a("scripKey", c2);
        }
        int a5 = m.a(this.b, a3, aVar);
        aVar.a("networktype", a5);
        if (!a3) {
            aVar.a("authType", String.valueOf(0));
            callBackResult("200010", "\u65e0\u6cd5\u8bc6\u522bsim\u5361\u6216\u6ca1\u6709sim\u5361", aVar, null);
            return false;
        } else if (tokenListener == null) {
            callBackResult("102203", "listener\u4e0d\u80fd\u4e3a\u7a7a", aVar, null);
            return false;
        } else if (a2.g()) {
            callBackResult("200082", "\u670d\u52a1\u5668\u7e41\u5fd9\uff0c\u8bf7\u7a0d\u540e\u91cd\u8bd5", aVar, null);
            return false;
        } else {
            if (TextUtils.isEmpty(str == null ? "" : str.trim())) {
                callBackResult("102203", "appId \u4e0d\u80fd\u4e3a\u7a7a", aVar, null);
                return false;
            }
            if (TextUtils.isEmpty(str2 == null ? "" : str2.trim())) {
                callBackResult("102203", "appkey\u4e0d\u80fd\u4e3a\u7a7a", aVar, null);
                return false;
            } else if (a5 == 0) {
                callBackResult("102101", "\u672a\u68c0\u6d4b\u5230\u7f51\u7edc", aVar, null);
                return false;
            } else if ("2".equals(a4) && a2.f()) {
                callBackResult("200082", "\u670d\u52a1\u5668\u7e41\u5fd9\uff0c\u8bf7\u7a0d\u540e\u91cd\u8bd5", aVar, null);
                return false;
            } else if ("3".equals(a4) && a2.e()) {
                callBackResult("200082", "\u670d\u52a1\u5668\u7e41\u5fd9\uff0c\u8bf7\u7a0d\u540e\u91cd\u8bd5", aVar, null);
                return false;
            } else {
                return true;
            }
        }
    }

    private void a(final Context context, final String str, final com.cmic.sso.sdk.a aVar) {
        n.a(new n.a() { // from class: com.cmic.sso.sdk.auth.AuthnHelper.8
            {
                AuthnHelper.this = this;
            }

            @Override // com.cmic.sso.sdk.e.n.a
            protected void a() {
                if ("200023".equals(str)) {
                    SystemClock.sleep(10000L);
                }
                new com.cmic.sso.sdk.d.b().a(context, str, aVar);
            }
        });
    }
}

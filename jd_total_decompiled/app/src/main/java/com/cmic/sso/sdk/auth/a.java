package com.cmic.sso.sdk.auth;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.TextUtils;
import com.cmic.sso.sdk.b;
import com.cmic.sso.sdk.c.c.d;
import com.cmic.sso.sdk.e.e;
import com.cmic.sso.sdk.e.h;
import com.cmic.sso.sdk.e.k;
import com.cmic.sso.sdk.e.l;
import com.jd.dynamic.DYConstants;
import com.meizu.cloud.pushsdk.platform.message.BasicPushStatus;
import java.util.UUID;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class a {
    @SuppressLint({"StaticFieldLeak"})

    /* renamed from: c  reason: collision with root package name */
    private static a f981c;
    private final Context b;
    private final Object d = new Object();
    private final com.cmic.sso.sdk.c.c.a a = com.cmic.sso.sdk.c.c.a.a();

    private a(Context context) {
        this.b = context.getApplicationContext();
    }

    private void b(final com.cmic.sso.sdk.a aVar, final b bVar) {
        com.cmic.sso.sdk.e.c.b("AuthnBusiness", "getScripAndToken start");
        boolean b = aVar.b("isGotScrip", false);
        com.cmic.sso.sdk.e.c.b("AuthnBusiness", "isGotScrip = " + b);
        if (!b) {
            b(aVar);
            if (!aVar.b("isCacheScrip", false)) {
                c(aVar);
                if (aVar.c("networktype") == 3 && aVar.c("logintype") != 3) {
                    aVar.a("isRisk", true);
                }
            }
            if (aVar.c("logintype") == 1) {
                aVar.a("userCapaid", BasicPushStatus.SUCCESS_CODE);
            } else if (aVar.c("logintype") == 0) {
                aVar.a("userCapaid", "50");
            }
        }
        this.a.a(aVar, new d() { // from class: com.cmic.sso.sdk.auth.a.1
            @Override // com.cmic.sso.sdk.c.c.d
            public void a(String str, String str2, JSONObject jSONObject) {
                a.this.a(aVar, bVar, str, str2, jSONObject);
            }
        });
    }

    private void c(com.cmic.sso.sdk.a aVar) {
        byte[] bArr = new byte[0];
        if (aVar.b("use2048PublicKey", false)) {
            com.cmic.sso.sdk.e.c.a("AuthnBusiness", "\u4f7f\u75282048\u516c\u94a5\u5bf9\u5e94\u7684\u5bf9\u79f0\u79d8\u94a5\u751f\u6210\u65b9\u5f0f");
            bArr = com.cmic.sso.sdk.e.a.a();
        } else {
            com.cmic.sso.sdk.e.c.a("AuthnBusiness", "\u4f7f\u75281024\u516c\u94a5\u5bf9\u5e94\u7684\u5bf9\u79f0\u79d8\u94a5\u751f\u6210\u65b9\u5f0f");
            try {
                bArr = UUID.randomUUID().toString().substring(0, 16).getBytes("utf-8");
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        byte[] a = com.cmic.sso.sdk.e.a.a();
        aVar.a(b.a.a, bArr);
        aVar.a(b.a.b, a);
        aVar.a("authType", "3");
    }

    public static a a(Context context) {
        if (f981c == null) {
            synchronized (a.class) {
                if (f981c == null) {
                    f981c = new a(context);
                }
            }
        }
        return f981c;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(com.cmic.sso.sdk.a aVar, b bVar) {
        if (e.a(aVar.b("traceId"))) {
            return;
        }
        com.cmic.sso.sdk.e.c.b("AuthnBusiness", "LoginCheck method start");
        if (a(aVar)) {
            com.cmic.sso.sdk.e.c.b("AuthnBusiness", "LoginCheck method start");
            int c2 = aVar.c("logintype");
            if (aVar.b("isCacheScrip", false)) {
                String b = aVar.b("securityphone", "");
                if (c2 == 3) {
                    bVar.a("103000", DYConstants.DY_TRUE, aVar, c.a(b));
                    return;
                } else {
                    b(aVar, bVar);
                    return;
                }
            }
            b(aVar, bVar);
            return;
        }
        bVar.a("102103", "\u65e0\u6570\u636e\u7f51\u7edc", aVar, null);
    }

    private void b(com.cmic.sso.sdk.a aVar) {
        String packageName = this.b.getPackageName();
        String a = com.cmic.sso.sdk.e.d.a(l.a(this.b, packageName));
        aVar.a("apppackage", packageName);
        aVar.a("appsign", a);
    }

    private boolean a(com.cmic.sso.sdk.a aVar) {
        boolean a;
        synchronized (this.d) {
            a = h.a(aVar);
            if (a) {
                aVar.a("securityphone", k.b("securityphone", ""));
                if (3 != aVar.c("logintype")) {
                    String a2 = h.a(this.b);
                    StringBuilder sb = new StringBuilder();
                    sb.append("\u89e3\u5bc6phoneScript ");
                    sb.append(!TextUtils.isEmpty(a2));
                    com.cmic.sso.sdk.e.c.b("AuthnBusiness", sb.toString());
                    if (TextUtils.isEmpty(a2)) {
                        a = false;
                    } else {
                        aVar.a("phonescrip", a2);
                    }
                    h.a(true, false);
                }
            }
            aVar.a("isCacheScrip", a);
            StringBuilder sb2 = new StringBuilder();
            sb2.append("isCachePhoneScrip = ");
            sb2.append(a);
            com.cmic.sso.sdk.e.c.b("AuthnBusiness", sb2.toString());
        }
        if (aVar.c("networktype") == 2) {
            return a;
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:30:0x00a4  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x00fa  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void a(com.cmic.sso.sdk.a aVar, b bVar, String str, String str2, JSONObject jSONObject) {
        String b;
        String str3;
        JSONObject jSONObject2;
        String str4;
        String str5;
        String str6;
        JSONObject jSONObject3;
        if ("103000".equals(str)) {
            String optString = jSONObject.optString("resultdata");
            if (TextUtils.isEmpty(optString)) {
                b = jSONObject.toString();
            } else {
                b = com.cmic.sso.sdk.e.a.b(aVar.a(b.a.a), optString, aVar.a(b.a.b));
            }
            String str7 = null;
            try {
                jSONObject3 = new JSONObject(b);
                try {
                    String optString2 = jSONObject3.optString("phonescrip");
                    try {
                        str4 = jSONObject3.optString("securityphone");
                    } catch (JSONException e2) {
                        e = e2;
                        str4 = null;
                        str7 = optString2;
                        jSONObject2 = jSONObject3;
                        str3 = str4;
                        e.printStackTrace();
                        str5 = str4;
                        str6 = str7;
                        str7 = str3;
                        jSONObject3 = jSONObject2;
                        com.cmic.sso.sdk.e.c.b("AuthnBusiness", "securityPhone  = " + str5);
                        aVar.a("openId", str7);
                        aVar.a("phonescrip", str6);
                        aVar.a("securityphone", str5);
                        if (jSONObject3 != null) {
                        }
                    }
                    try {
                        str7 = jSONObject3.optString("openId");
                        if (TextUtils.isEmpty(str7)) {
                            str7 = jSONObject3.optString("pcid");
                        }
                        k.a("securityphone", str4);
                        str5 = str4;
                        str6 = optString2;
                    } catch (JSONException e3) {
                        e = e3;
                        str3 = str7;
                        str7 = optString2;
                        jSONObject2 = jSONObject3;
                        e.printStackTrace();
                        str5 = str4;
                        str6 = str7;
                        str7 = str3;
                        jSONObject3 = jSONObject2;
                        com.cmic.sso.sdk.e.c.b("AuthnBusiness", "securityPhone  = " + str5);
                        aVar.a("openId", str7);
                        aVar.a("phonescrip", str6);
                        aVar.a("securityphone", str5);
                        if (jSONObject3 != null) {
                        }
                    }
                } catch (JSONException e4) {
                    e = e4;
                    str4 = null;
                }
            } catch (JSONException e5) {
                e = e5;
                str3 = null;
                jSONObject2 = null;
                str4 = null;
            }
            com.cmic.sso.sdk.e.c.b("AuthnBusiness", "securityPhone  = " + str5);
            aVar.a("openId", str7);
            aVar.a("phonescrip", str6);
            aVar.a("securityphone", str5);
            if (jSONObject3 != null) {
                if (!aVar.b("isRisk", false)) {
                    h.a(this.b, str6, Long.parseLong(jSONObject3.optString("scripExpiresIn", "0")), aVar.b("scripKey", ""), aVar.b("scripType", ""));
                }
                if (aVar.c("logintype") == 3) {
                    bVar.a(str, DYConstants.DY_TRUE, aVar, c.a(str5));
                    return;
                } else if (aVar.b("isRisk", false)) {
                    aVar.a("isRisk", false);
                    aVar.a("isGotScrip", true);
                    b(aVar, bVar);
                    return;
                } else {
                    bVar.a(str, str2, aVar, jSONObject3);
                    return;
                }
            }
            com.cmic.sso.sdk.e.c.a("AuthnBusiness", "\u8fd4\u56de103000\uff0c\u4f46\u662f\u6570\u636e\u89e3\u6790\u51fa\u9519");
            bVar.a(String.valueOf(102223), "\u6570\u636e\u89e3\u6790\u5f02\u5e38", aVar, c.a(String.valueOf(102223), "\u6570\u636e\u89e3\u6790\u5f02\u5e38"));
        } else if (aVar.c("logintype") == 3) {
            bVar.a(str, DYConstants.DY_TRUE, aVar, c.b(str, str2));
        } else {
            bVar.a(str, str2, aVar, jSONObject);
        }
    }
}

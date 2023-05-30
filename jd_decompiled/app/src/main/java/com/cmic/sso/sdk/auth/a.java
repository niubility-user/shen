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
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void a(com.cmic.sso.sdk.a r21, com.cmic.sso.sdk.auth.b r22, java.lang.String r23, java.lang.String r24, org.json.JSONObject r25) {
        /*
            Method dump skipped, instructions count: 300
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.cmic.sso.sdk.auth.a.a(com.cmic.sso.sdk.a, com.cmic.sso.sdk.auth.b, java.lang.String, java.lang.String, org.json.JSONObject):void");
    }
}

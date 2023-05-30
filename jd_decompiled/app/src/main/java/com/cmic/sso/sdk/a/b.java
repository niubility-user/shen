package com.cmic.sso.sdk.a;

import android.text.TextUtils;
import com.cmic.sso.sdk.a.a;
import com.cmic.sso.sdk.auth.AuthnHelper;
import com.cmic.sso.sdk.e.k;
import com.cmic.sso.sdk.e.n;
import com.huawei.hms.framework.common.ContainerUtils;
import com.jingdong.jdsdk.constant.JshopConst;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class b {

    /* renamed from: c  reason: collision with root package name */
    private static b f962c;
    private com.cmic.sso.sdk.a.a a;
    private final com.cmic.sso.sdk.a.a b;
    private volatile boolean d = false;

    /* renamed from: e  reason: collision with root package name */
    private a f963e;

    /* loaded from: classes.dex */
    interface a {
        void a(com.cmic.sso.sdk.a.a aVar);
    }

    private b(boolean z) {
        com.cmic.sso.sdk.a.a a2 = new a.C0016a().a();
        this.b = a2;
        if (!z) {
            this.a = d();
        } else {
            this.a = a2;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public com.cmic.sso.sdk.a.a d() {
        return new a.C0016a().a(d.b(this.b.a())).c(d.a(this.b.c())).b(d.b(this.b.b())).d(d.c(this.b.d())).d(d.a(this.b.h())).e(d.b(this.b.i())).a(d.e(this.b.e())).b(d.d(this.b.f())).c(d.c(this.b.g())).f(d.f(this.b.j())).a(d.a(this.b.k())).b(d.b(this.b.l())).a();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(com.cmic.sso.sdk.a aVar) {
        if (this.d) {
            com.cmic.sso.sdk.e.c.a("UmcConfigHandle", "\u6b63\u5728\u83b7\u53d6\u914d\u7f6e\u4e2d...");
            return;
        }
        this.d = true;
        com.cmic.sso.sdk.c.c.a.a().a(false, aVar, new com.cmic.sso.sdk.c.c.d() { // from class: com.cmic.sso.sdk.a.b.1
            @Override // com.cmic.sso.sdk.c.c.d
            public void a(String str, String str2, JSONObject jSONObject) {
                try {
                    if ("103000".equals(str)) {
                        b.this.a(jSONObject);
                        k.a("sdk_config_version", AuthnHelper.SDK_VERSION);
                        b bVar = b.this;
                        bVar.a = bVar.d();
                        if (b.this.f963e != null) {
                            b.this.f963e.a(b.this.a);
                        }
                    }
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
                b.this.d = false;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void c() {
        k.a b = k.b("sso_config_xf");
        b.c();
        b.b();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(a aVar) {
        this.f963e = aVar;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public com.cmic.sso.sdk.a.a b() {
        return this.a;
    }

    public static b a(boolean z) {
        if (f962c == null) {
            synchronized (b.class) {
                if (f962c == null) {
                    f962c = new b(z);
                }
            }
        }
        return f962c;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public com.cmic.sso.sdk.a.a a() {
        return this.b;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(JSONObject jSONObject) {
        k.a b = k.b("sso_config_xf");
        try {
            if (jSONObject.has("client_valid")) {
                b.a("client_valid", System.currentTimeMillis() + (Integer.parseInt(jSONObject.getString("client_valid")) * 60 * 60 * 1000));
            }
            if (jSONObject.has("Configlist")) {
                JSONObject jSONObject2 = jSONObject.getJSONObject("Configlist");
                if (jSONObject2.has("CHANGE_HOST")) {
                    String string = jSONObject2.getString("CHANGE_HOST");
                    if (string.contains("M007")) {
                        String a2 = a(string, "M007");
                        if (!TextUtils.isEmpty(a2)) {
                            b.a("logHost", a2);
                        }
                    }
                    if (string.contains("M008")) {
                        String a3 = a(string, "M008");
                        if (!TextUtils.isEmpty(a3)) {
                            b.a("https_get_phone_scrip_host", a3);
                        }
                    }
                    if (string.contains("M009")) {
                        String a4 = a(string, "M009");
                        if (!TextUtils.isEmpty(a4)) {
                            b.a("config_host", a4);
                        }
                    }
                } else {
                    b.a("logHost");
                    b.a("https_get_phone_scrip_host");
                    b.a("config_host");
                }
                a(jSONObject2, "CLOSE_FRIEND_WAPKS", "0", b);
                a(jSONObject2, "CLOSE_LOGS_VERSION", "0", b);
                a(jSONObject2, "CLOSE_IPV4_LIST", "0", b);
                a(jSONObject2, "CLOSE_IPV6_LIST", "0", b);
                a(jSONObject2, "CLOSE_M008_SDKVERSION_LIST", "0", b);
                a(jSONObject2, "CLOSE_M008_APPID_LIST", "0", b);
                if (jSONObject2.has("LOGS_CONTROL")) {
                    String[] split = jSONObject2.getString("LOGS_CONTROL").replace(JshopConst.JSHOP_PROMOTIO_H, "").split(ContainerUtils.FIELD_DELIMITER);
                    if (split.length == 2 && !TextUtils.isEmpty(split[0]) && !TextUtils.isEmpty(split[1])) {
                        try {
                            int parseInt = Integer.parseInt(split[0]);
                            int parseInt2 = Integer.parseInt(split[1]);
                            b.a("maxFailedLogTimes", parseInt);
                            b.a("pauseTime", parseInt2);
                        } catch (Exception unused) {
                            com.cmic.sso.sdk.e.c.a("UmcConfigHandle", "\u89e3\u6790\u65e5\u5fd7\u4e0a\u62a5\u9650\u5236\u65f6\u95f4\u6b21\u6570\u5f02\u5e38");
                        }
                    }
                } else {
                    b.a("maxFailedLogTimes");
                    b.a("pauseTime");
                }
            }
            b.b();
        } catch (Exception e2) {
            com.cmic.sso.sdk.e.c.a("UmcConfigHandle", "\u914d\u7f6e\u9879\u5f02\u5e38\uff0c\u914d\u7f6e\u5931\u6548");
            e2.printStackTrace();
        }
    }

    private String a(String str, String str2) {
        String str3;
        String[] split = str.split(ContainerUtils.FIELD_DELIMITER);
        int length = split.length;
        int i2 = 0;
        while (true) {
            if (i2 >= length) {
                str3 = "";
                break;
            }
            str3 = split[i2];
            if (str3.contains(str2)) {
                break;
            }
            i2++;
        }
        return !TextUtils.isEmpty(str3) ? str3.substring(str3.lastIndexOf(ContainerUtils.KEY_VALUE_DELIMITER) + 1) : str3;
    }

    private void a(JSONObject jSONObject, String str, String str2, k.a aVar) {
        if (jSONObject.has(str)) {
            String optString = jSONObject.optString(str, str2);
            if (!"CLOSE_FRIEND_WAPKS".equals(str)) {
                if (!"0".equals(optString) && !"1".equals(optString)) {
                    return;
                }
            } else if (TextUtils.isEmpty(optString)) {
                return;
            } else {
                if (!optString.contains("CU") && !optString.contains("CT") && !optString.contains("CM")) {
                    return;
                }
            }
            aVar.a(str, jSONObject.optString(str, str2));
            return;
        }
        aVar.a(str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(final com.cmic.sso.sdk.a aVar) {
        if (d.a()) {
            n.a(new n.a() { // from class: com.cmic.sso.sdk.a.b.2
                @Override // com.cmic.sso.sdk.e.n.a
                protected void a() {
                    com.cmic.sso.sdk.e.c.b("UmcConfigHandle", "\u5f00\u59cb\u62c9\u53d6\u914d\u7f6e..");
                    b.this.b(aVar);
                }
            });
        }
    }
}

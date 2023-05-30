package com.cmic.sso.sdk.c;

import android.text.TextUtils;
import com.cmic.sso.sdk.c.b.g;
import com.cmic.sso.sdk.e.q;
import com.google.common.net.HttpHeaders;
import java.util.List;
import java.util.Map;

/* loaded from: classes.dex */
public class b {
    private String a;
    private String b;

    public com.cmic.sso.sdk.c.c.c a(com.cmic.sso.sdk.c.c.c cVar, com.cmic.sso.sdk.c.d.b bVar, com.cmic.sso.sdk.a aVar) {
        List<String> list;
        Map<String, List<String>> b = bVar.b();
        if (TextUtils.isEmpty(this.a) && (list = b.get("pplocation")) != null && list.size() > 0) {
            this.a = list.get(0);
        }
        q.b(aVar, String.valueOf(bVar.a()));
        List<String> list2 = b.get(HttpHeaders.LOCATION);
        if (list2 == null || list2.isEmpty()) {
            list2 = b.get(HttpHeaders.LOCATION.toLowerCase());
        }
        if (list2 != null && list2.size() > 0) {
            String str = list2.get(0);
            this.b = str;
            if (!TextUtils.isEmpty(str)) {
                String b2 = aVar.b("operatortype", "0");
                if ("2".equals(b2)) {
                    q.a(aVar, "getUnicomMobile");
                } else if ("3".equals(b2)) {
                    q.a(aVar, "getTelecomMobile");
                } else {
                    q.a(aVar, "NONE");
                }
            }
        }
        com.cmic.sso.sdk.e.c.b(HttpHeaders.LOCATION, this.b);
        com.cmic.sso.sdk.c.c.c a = a(this.b, cVar.f(), "GET", new com.cmic.sso.sdk.c.b.c(cVar.j().a()));
        a.a(cVar.g());
        return a;
    }

    public com.cmic.sso.sdk.c.c.c b(com.cmic.sso.sdk.c.c.c cVar, com.cmic.sso.sdk.c.d.b bVar, com.cmic.sso.sdk.a aVar) {
        String b = aVar.b("operatortype", "0");
        if ("2".equals(b)) {
            q.a(aVar, "getNewUnicomPhoneNumberNotify");
        } else if ("3".equals(b)) {
            q.a(aVar, "getNewTelecomPhoneNumberNotify");
        } else {
            q.a(aVar, "NONE");
        }
        q.b(aVar, String.valueOf(bVar.a()));
        com.cmic.sso.sdk.c.b.d dVar = new com.cmic.sso.sdk.c.b.d(cVar.j().a(), "1.0", bVar.c());
        dVar.c(aVar.b("userCapaid"));
        if (aVar.c("logintype") != 3) {
            if (aVar.b("isRisk", false)) {
                dVar.b("pre");
            } else {
                dVar.b("authz");
            }
        } else {
            dVar.b("pre");
        }
        com.cmic.sso.sdk.c.c.c a = a(this.a, cVar.f(), "POST", dVar);
        a.a(cVar.g());
        this.a = null;
        return a;
    }

    private com.cmic.sso.sdk.c.c.c a(String str, String str2, String str3, g gVar) {
        com.cmic.sso.sdk.c.c.c cVar = new com.cmic.sso.sdk.c.c.c(str, gVar, str3, str2);
        if (str3.equals("GET")) {
            cVar.a(HttpHeaders.CONTENT_TYPE, "application/x-www-form-urlencoded");
        }
        return cVar;
    }

    public String a() {
        return this.a;
    }
}

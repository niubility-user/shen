package com.jingdong.sdk.dialingtest.f;

import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import com.jd.dynamic.DYConstants;
import com.jd.jdcache.util.UrlHelper;
import com.jdcn.biz.client.BankCardConstants;
import com.jingdong.sdk.dialingtest.c.c.d;
import com.jingdong.sdk.dialingtest.d.a.b;
import java.net.InetAddress;
import java.security.cert.X509Certificate;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes7.dex */
public class b {

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes7.dex */
    public static class a implements d.b {
        final /* synthetic */ com.jingdong.sdk.dialingtest.d.a.b a;
        final /* synthetic */ String b;

        a(com.jingdong.sdk.dialingtest.d.a.b bVar, String str) {
            this.a = bVar;
            this.b = str;
        }

        @Override // com.jingdong.sdk.dialingtest.c.c.d.b
        public void a(com.jingdong.sdk.dialingtest.c.c.c cVar) {
            JSONObject a;
            String str = "";
            if (cVar != null && cVar.a == 200 && (a = cVar.a()) != null) {
                String optString = a.optString("data", "");
                if (!TextUtils.isEmpty(optString)) {
                    try {
                        str = new JSONObject(optString).optString("localDns", "");
                        com.jingdong.sdk.dialingtest.c.e.a.a("HttpTestUtil", "get operator dns ip success ,ip :" + str);
                    } catch (Exception e2) {
                        com.jingdong.sdk.dialingtest.c.e.a.a("HttpTestUtil", e2.getMessage());
                    }
                }
            }
            b.i(this.a, this.b, str);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: com.jingdong.sdk.dialingtest.f.b$b  reason: collision with other inner class name */
    /* loaded from: classes7.dex */
    public static class RunnableC0714b implements Runnable {

        /* renamed from: g  reason: collision with root package name */
        final b.a f14810g;

        /* renamed from: h  reason: collision with root package name */
        private final int f14811h;

        /* renamed from: i  reason: collision with root package name */
        private final com.jingdong.sdk.dialingtest.d.a.a f14812i;

        /* renamed from: com.jingdong.sdk.dialingtest.f.b$b$a */
        /* loaded from: classes7.dex */
        class a implements d.b {
            a() {
            }

            @Override // com.jingdong.sdk.dialingtest.c.c.d.b
            public void a(com.jingdong.sdk.dialingtest.c.c.c cVar) {
                if (cVar == null) {
                    return;
                }
                com.jingdong.sdk.dialingtest.d.a.a aVar = RunnableC0714b.this.f14812i;
                int i2 = cVar.a;
                aVar.f14758l = i2 == 0 ? "" : String.valueOf(i2);
                RunnableC0714b.this.f14812i.f14759m = b.h(cVar.b);
                RunnableC0714b.this.f14812i.f14752f = cVar.f14729c;
                RunnableC0714b.this.f14812i.o = cVar.f14734i;
                RunnableC0714b.this.f14812i.q = cVar.f14735j;
                RunnableC0714b.this.f14812i.s = cVar.f14736k;
                RunnableC0714b.this.f14812i.t = cVar.f14737l;
                if (RunnableC0714b.this.f14810g.a()) {
                    RunnableC0714b.this.f14812i.p = b.g(cVar.f14733h);
                }
                RunnableC0714b.this.f14812i.f14755i = cVar.f14732g;
                RunnableC0714b.this.f14812i.f14756j = cVar.f14731f;
                RunnableC0714b.this.f14812i.f14757k = cVar.f14730e;
                if (RunnableC0714b.this.f14810g.c()) {
                    RunnableC0714b.this.f14812i.f14760n = cVar.b();
                    com.jingdong.sdk.dialingtest.c.e.a.a("HttpTestUtil", "http test response body string: " + RunnableC0714b.this.f14812i.f14760n);
                }
                com.jingdong.sdk.dialingtest.e.a.c.c(RunnableC0714b.this.f14812i);
            }
        }

        public RunnableC0714b(b.a aVar, com.jingdong.sdk.dialingtest.d.a.a aVar2, int i2) {
            this.f14810g = aVar;
            this.f14812i = aVar2;
            this.f14811h = i2;
        }

        @Override // java.lang.Runnable
        public void run() {
            Map<String, Object> l2;
            String host = Uri.parse(this.f14810g.a).getHost();
            if (!TextUtils.isEmpty(host) && !com.jingdong.sdk.dialingtest.f.a.d(host) && (l2 = com.jingdong.sdk.dialingtest.f.a.l(host)) != null) {
                String str = l2.get("time") == null ? "" : (String) l2.get("time");
                InetAddress[] inetAddressArr = l2.get("remoteInet") == null ? null : (InetAddress[]) l2.get("remoteInet");
                this.f14812i.f14751e = str;
                if (inetAddressArr != null && inetAddressArr.length >= 1) {
                    String c2 = com.jingdong.sdk.dialingtest.f.a.c(inetAddressArr);
                    if (!TextUtils.isEmpty(c2)) {
                        this.f14812i.d = c2;
                    }
                }
            }
            com.jingdong.sdk.dialingtest.c.c.d dVar = new com.jingdong.sdk.dialingtest.c.c.d();
            dVar.d(this.f14810g.a);
            dVar.b(this.f14811h * 1000);
            dVar.h(this.f14810g.b.toUpperCase());
            dVar.i(false);
            dVar.f(true);
            if (this.f14810g.d()) {
                dVar.e(this.f14810g.f14770f);
            }
            dVar.j(this.f14810g.a());
            dVar.c(new a());
            dVar.g();
        }
    }

    public static void c(Handler handler, Object obj) {
        if (obj != null && (obj instanceof com.jingdong.sdk.dialingtest.d.a.b)) {
            com.jingdong.sdk.dialingtest.d.a.b bVar = (com.jingdong.sdk.dialingtest.d.a.b) obj;
            if (bVar.f14766i < 1) {
                return;
            }
            String e2 = com.jingdong.sdk.dialingtest.f.a.e();
            if (bVar.d()) {
                e(bVar, e2);
            } else {
                i(bVar, e2, "");
            }
            bVar.f14766i--;
            if (handler != null) {
                Message obtainMessage = handler.obtainMessage();
                obtainMessage.what = 2001;
                obtainMessage.obj = bVar;
                handler.sendMessageDelayed(obtainMessage, bVar.d * 1000);
            }
        }
    }

    private static void d(b.a aVar, com.jingdong.sdk.dialingtest.d.a.a aVar2, int i2) {
        if (aVar == null || aVar2 == null) {
            return;
        }
        String str = aVar.a;
        if (TextUtils.isEmpty(str)) {
            com.jingdong.sdk.dialingtest.c.e.a.a("HttpTestUtil", "the http test host is empty");
            return;
        }
        aVar2.f14750c = str;
        com.jingdong.sdk.dialingtest.c.e.a.a("HttpTestUtil", "http test host :" + str);
        String upperCase = TextUtils.isEmpty(aVar.b) ? "" : aVar.b.toUpperCase();
        if (!upperCase.equals(UrlHelper.METHOD_HEAD) && !upperCase.equals("GET") && !upperCase.equals("POST")) {
            com.jingdong.sdk.dialingtest.c.e.a.a("HttpTestUtil", "http test with unknown method");
        } else {
            com.jingdong.sdk.dialingtest.c.d.a.e().b(new RunnableC0714b(aVar, aVar2, i2));
        }
    }

    private static void e(com.jingdong.sdk.dialingtest.d.a.b bVar, String str) {
        com.jingdong.sdk.dialingtest.c.c.d dVar = new com.jingdong.sdk.dialingtest.c.c.d();
        dVar.d(com.jingdong.sdk.dialingtest.f.a.a());
        dVar.b(1000);
        dVar.f(true);
        dVar.h("GET");
        dVar.c(new a(bVar, str));
        dVar.a();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String g(List<X509Certificate> list) {
        String str = "";
        if (list == null || list.size() == 0) {
            return "";
        }
        try {
            JSONArray jSONArray = new JSONArray();
            for (X509Certificate x509Certificate : list) {
                if (x509Certificate != null) {
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put("version", String.valueOf(x509Certificate.getVersion()));
                    jSONObject.put("serialNumber", x509Certificate.getSerialNumber().toString());
                    jSONObject.put(BankCardConstants.KEY_ISSUER, x509Certificate.getIssuerDN().toString());
                    jSONObject.put("notBefore", x509Certificate.getNotBefore().toString());
                    jSONObject.put("notAfter", x509Certificate.getNotAfter().toString());
                    jSONObject.put("subject", x509Certificate.getSubjectDN().toString());
                    jSONObject.put("publicKeyAlgorithm", x509Certificate.getSigAlgName());
                    jSONObject.put("publicKey", x509Certificate.getPublicKey().toString());
                    jSONArray.put(jSONObject);
                }
            }
            str = jSONArray.toString();
            com.jingdong.sdk.dialingtest.c.e.a.a("HttpTestUtil", "certificate string : " + str);
            return str;
        } catch (Throwable th) {
            com.jingdong.sdk.dialingtest.c.e.a.a("HttpTestUtil", th.getMessage());
            return str;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String h(Map<String, List<String>> map) {
        String str = "";
        if (map == null || map.size() == 0) {
            return "";
        }
        try {
            JSONObject jSONObject = new JSONObject();
            for (String str2 : map.keySet()) {
                int i2 = 0;
                StringBuilder sb = new StringBuilder();
                for (String str3 : map.get(str2)) {
                    if (i2 > 0) {
                        sb.append("<--->");
                    }
                    sb.append(str3);
                    i2++;
                }
                if (str2 == null) {
                    str2 = DYConstants.DY_NULL_STR;
                }
                jSONObject.put(str2.toLowerCase(), sb.toString());
            }
            str = jSONObject.toString();
            com.jingdong.sdk.dialingtest.c.e.a.a("HttpTestUtil", "http test response header: " + str);
            return str;
        } catch (Exception e2) {
            com.jingdong.sdk.dialingtest.c.e.a.a("HttpTestUtil", e2.getMessage());
            return str;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void i(com.jingdong.sdk.dialingtest.d.a.b bVar, String str, String str2) {
        if (bVar == null || TextUtils.isEmpty(str) || bVar.f14765h == null) {
            return;
        }
        for (int i2 = 0; i2 < bVar.f14765h.size(); i2++) {
            b.a aVar = bVar.f14765h.get(i2);
            com.jingdong.sdk.dialingtest.d.a.a aVar2 = new com.jingdong.sdk.dialingtest.d.a.a();
            aVar2.a = str;
            aVar2.f14754h = str2;
            boolean z = bVar.f14767j;
            aVar2.v = z;
            if (z) {
                aVar2.u = com.jingdong.sdk.dialingtest.b.k().f14710f;
            }
            d(aVar, aVar2, bVar.f14764g);
        }
    }
}

package com.jingdong.app.mall.bundle.mobileConfig;

import android.app.Application;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import com.jd.android.sdk.coreinfo.CoreInfo;
import com.jingdong.app.mall.bundle.mobileConfig.JDMobileConfig;
import com.jingdong.app.mall.bundle.mobileConfig.c.d;
import com.jingdong.app.mall.bundle.mobileConfig.c.e;
import com.jingdong.app.mall.bundle.mobileConfig.c.f;
import com.jingdong.app.mall.bundle.mobileConfig.net.ConfigRequestParams;
import com.jingdong.app.mall.bundle.mobileConfig.net.IConfigFetcher;
import com.jingdong.app.mall.bundle.mobileConfig.net.IConfigFetcherCallBack;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicBoolean;
import org.json.JSONObject;

/* loaded from: classes12.dex */
public class a {
    private static e A = new e();
    private static a B = null;
    private static String x = "MConfigInner";
    private static String y = "api.m.jd.care";
    private static String z = "api.m.jd.com";
    private Application a;
    private String b;

    /* renamed from: c */
    private String f8225c;
    private String d;

    /* renamed from: e */
    private String f8226e;

    /* renamed from: f */
    private String f8227f;

    /* renamed from: g */
    private String f8228g;

    /* renamed from: h */
    private JDMobileConfig.IUserIdCallBack f8229h;

    /* renamed from: i */
    private JDMobileConfig.IUUIDCallBack f8230i;

    /* renamed from: m */
    private String f8234m;

    /* renamed from: n */
    private int f8235n;
    private String o;
    private Map<String, Map<String, Map<String, String>>> q;
    private IConfigFetcher s;
    private Handler t;
    private JDMobileConfig.IXTime v;
    private String w;

    /* renamed from: j */
    private boolean f8231j = false;

    /* renamed from: k */
    private boolean f8232k = false;

    /* renamed from: l */
    private final AtomicBoolean f8233l = new AtomicBoolean(false);
    private String p = "http://";
    private CopyOnWriteArrayList<JDMoblieConfigListener> r = new CopyOnWriteArrayList<>();
    boolean u = true;

    /* renamed from: com.jingdong.app.mall.bundle.mobileConfig.a$a */
    /* loaded from: classes12.dex */
    public class C0257a implements IConfigFetcherCallBack {
        C0257a() {
            a.this = r1;
        }

        @Override // com.jingdong.app.mall.bundle.mobileConfig.net.IConfigFetcherCallBack
        public void onError(Exception exc) {
            StringBuilder sb = new StringBuilder();
            sb.append("fetcher data error. ");
            sb.append(exc == null ? "" : exc.getMessage());
            com.jingdong.app.mall.bundle.mobileConfig.b.b(sb.toString());
        }

        @Override // com.jingdong.app.mall.bundle.mobileConfig.net.IConfigFetcherCallBack
        public void onSuccess(JSONObject jSONObject) {
            a.this.b(jSONObject);
        }
    }

    /* loaded from: classes12.dex */
    public class b implements Runnable {
        b() {
            a.this = r1;
        }

        @Override // java.lang.Runnable
        public void run() {
            synchronized (a.this.r) {
                com.jingdong.app.mall.bundle.mobileConfig.b.a("notify listeners");
                Iterator it = a.this.r.iterator();
                while (it.hasNext()) {
                    ((JDMoblieConfigListener) it.next()).onConfigUpdate();
                }
            }
        }
    }

    /* loaded from: classes12.dex */
    public class c implements Runnable {
        c() {
            a.this = r1;
        }

        @Override // java.lang.Runnable
        public void run() {
            synchronized (a.this.r) {
                com.jingdong.app.mall.bundle.mobileConfig.b.a("notiry listeners");
                Iterator it = a.this.r.iterator();
                while (it.hasNext()) {
                    ((JDMoblieConfigListener) it.next()).onConfigUpdate();
                }
            }
        }
    }

    private a() {
    }

    private Map<String, Map<String, Map<String, String>>> a(JSONObject jSONObject) {
        HashMap hashMap = new HashMap();
        try {
            Iterator<String> keys = jSONObject.keys();
            while (keys.hasNext()) {
                String next = keys.next();
                JSONObject jSONObject2 = jSONObject.getJSONObject(next);
                HashMap hashMap2 = new HashMap();
                if (jSONObject2 != null) {
                    Iterator<String> keys2 = jSONObject2.keys();
                    while (keys2.hasNext()) {
                        String next2 = keys2.next();
                        JSONObject jSONObject3 = jSONObject2.getJSONObject(next2);
                        if (jSONObject3 != null) {
                            Iterator<String> keys3 = jSONObject3.keys();
                            HashMap hashMap3 = new HashMap();
                            while (keys3.hasNext()) {
                                String next3 = keys3.next();
                                hashMap3.put(next3, jSONObject3.optString(next3));
                            }
                            hashMap2.put(next2, hashMap3);
                        }
                    }
                }
                hashMap.put(next, hashMap2);
            }
            com.jingdong.app.mall.bundle.mobileConfig.b.a("jsonToMap success!");
            return hashMap;
        } catch (Exception e2) {
            e2.printStackTrace();
            com.jingdong.app.mall.bundle.mobileConfig.b.b("jsonToMap error!");
            return null;
        }
    }

    public void b(JSONObject jSONObject) {
        com.jingdong.app.mall.bundle.mobileConfig.b.a("fetch data success");
        if (jSONObject == null) {
            com.jingdong.app.mall.bundle.mobileConfig.b.a("data is null!");
            return;
        }
        com.jingdong.app.mall.bundle.mobileConfig.b.a("data:" + jSONObject.toString());
        Map<String, Map<String, Map<String, String>>> a = a(jSONObject);
        if (a != null) {
            this.q = a;
            this.f8233l.set(true);
            com.jingdong.app.mall.bundle.mobileConfig.b.a("persistObject:" + d.a(this.q));
            this.t.post(new c());
        }
    }

    public static a l() {
        if (B == null) {
            synchronized (a.class) {
                if (B == null) {
                    B = new a();
                }
            }
        }
        return B;
    }

    private void p() {
        this.t.post(new b());
    }

    private void q() {
        try {
            if (TextUtils.isEmpty(this.w)) {
                return;
            }
            String a = d.a(this.a.getApplicationContext(), this.w);
            com.jingdong.app.mall.bundle.mobileConfig.b.a("default data:" + a);
            this.q = a(new JSONObject(a));
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public String a(long j2) {
        return l().k() + l().j() + f.a() + l().h() + f.o() + j2 + f.f() + l().e() + f.n() + l().n() + f.e() + l().d() + f.k() + CoreInfo.System.getAndroidVersion() + f.i() + f.j() + f.d() + f.c() + f.h() + f.g() + f.b() + l().c() + "\",\"" + f.m() + "\":\"" + l().m() + "\"}";
    }

    public String a(String str, String str2, String str3, String str4) {
        if (TextUtils.isEmpty(str)) {
            com.jingdong.app.mall.bundle.mobileConfig.b.b("namespace is empty!");
            return str4;
        } else if (TextUtils.isEmpty(str2)) {
            com.jingdong.app.mall.bundle.mobileConfig.b.b("configName is empty!");
            return str4;
        } else if (TextUtils.isEmpty(str3)) {
            com.jingdong.app.mall.bundle.mobileConfig.b.b("key is empty!");
            return str4;
        } else if (this.q == null) {
            com.jingdong.app.mall.bundle.mobileConfig.b.a("restored map is null");
            return str4;
        } else {
            Map<String, String> a = a(str, str2);
            if (a != null) {
                String str5 = a.get(str3);
                return str5 != null ? str5 : str4;
            }
            com.jingdong.app.mall.bundle.mobileConfig.b.a("no configName found:" + str2);
            return str4;
        }
    }

    public Map<String, String> a(String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            com.jingdong.app.mall.bundle.mobileConfig.b.b("namespace is empty!");
            return null;
        } else if (TextUtils.isEmpty(str2)) {
            com.jingdong.app.mall.bundle.mobileConfig.b.b("configName is empty!");
            return null;
        } else {
            Map<String, Map<String, Map<String, String>>> map = this.q;
            if (map == null) {
                com.jingdong.app.mall.bundle.mobileConfig.b.a("restored map is null");
                return null;
            }
            Map<String, Map<String, String>> map2 = map.get(str);
            if (map2 == null) {
                com.jingdong.app.mall.bundle.mobileConfig.b.a("no namespace found:" + str);
                return null;
            }
            Map<String, String> map3 = map2.get(str2);
            if (map3 == null) {
                com.jingdong.app.mall.bundle.mobileConfig.b.a("no configName found:" + str2);
                return null;
            }
            return map3;
        }
    }

    public void a(JDMobileConfig.Builder builder) {
        String str;
        if (this.f8232k) {
            return;
        }
        this.f8231j = builder.isDebug;
        this.a = builder.application;
        this.f8227f = builder.appId;
        this.f8228g = builder.userId;
        this.f8229h = builder.userIdCallBack;
        if (builder.useBetaHost) {
            this.o = y;
            this.d = "avatar-basic-config";
            this.f8226e = com.jingdong.app.mall.bundle.mobileConfig.c.c.a();
            str = "http://";
        } else {
            this.o = z;
            this.d = "avatar-basic-config";
            this.f8226e = com.jingdong.app.mall.bundle.mobileConfig.c.c.a();
            str = "https://";
        }
        this.p = str;
        this.f8234m = !TextUtils.isEmpty(builder.appVersionName) ? builder.appVersionName : CoreInfo.App.getVersionName(this.a);
        int i2 = builder.appVersionCode;
        if (i2 == 0) {
            i2 = CoreInfo.App.getVersionCode(this.a);
        }
        this.f8235n = i2;
        this.f8230i = builder.uuidCallBack;
        this.f8225c = builder.uuid;
        IConfigFetcher iConfigFetcher = builder.fetcher;
        if (iConfigFetcher == null) {
            iConfigFetcher = new com.jingdong.app.mall.bundle.mobileConfig.net.a();
        }
        this.s = iConfigFetcher;
        this.v = builder.xTime;
        this.w = builder.defaultAssetsJsonPath;
        this.t = new Handler(Looper.getMainLooper());
        com.jingdong.app.mall.bundle.mobileConfig.b.a(String.format("appId:%s, versionName:%s, versionCode:%s .", this.f8227f, this.f8234m, Integer.valueOf(this.f8235n)));
        this.u = builder.useLocalCache;
        r();
        if (builder.fetchDataWithInit) {
            com.jingdong.app.mall.bundle.mobileConfig.b.a("fetchDataWithInit:true");
            a();
        }
        this.f8232k = true;
    }

    public void a(JDMoblieConfigListener jDMoblieConfigListener) {
        synchronized (this.r) {
            this.r.add(jDMoblieConfigListener);
        }
    }

    public void a(String str) {
        this.f8228g = str;
    }

    public boolean a() {
        boolean z2 = false;
        if (A.a()) {
            if (this.s == null) {
                com.jingdong.app.mall.bundle.mobileConfig.b.b("network fetcher is null, may not init yet, return!!");
                return false;
            }
            JDMobileConfig.IXTime iXTime = this.v;
            z2 = true;
            if (iXTime != null && iXTime.isXTime()) {
                p();
                return true;
            }
            ConfigRequestParams configRequestParams = new ConfigRequestParams(c(), m());
            configRequestParams.gatewayAppid = h();
            configRequestParams.gatewaySecKey = g();
            this.s.fetch(configRequestParams, new C0257a());
        }
        return z2;
    }

    public Map<String, Map<String, Map<String, String>>> b() {
        return this.q;
    }

    public void b(JDMoblieConfigListener jDMoblieConfigListener) {
        synchronized (this.r) {
            this.r.remove(jDMoblieConfigListener);
        }
    }

    public void b(String str) {
        this.f8225c = str;
    }

    public String c() {
        return this.f8227f;
    }

    public int d() {
        return this.f8235n;
    }

    public String e() {
        return this.f8234m;
    }

    public Application f() {
        return this.a;
    }

    public String g() {
        return this.f8226e;
    }

    public String h() {
        return this.d;
    }

    public boolean i() {
        return this.f8233l.get();
    }

    public String j() {
        return this.o;
    }

    public String k() {
        return this.p;
    }

    public String m() {
        JDMobileConfig.IUserIdCallBack iUserIdCallBack = this.f8229h;
        return iUserIdCallBack != null ? iUserIdCallBack.userId() : !TextUtils.isEmpty(this.f8228g) ? this.f8228g : "";
    }

    public String n() {
        JDMobileConfig.IUUIDCallBack iUUIDCallBack = this.f8230i;
        return iUUIDCallBack != null ? iUUIDCallBack.uuid() : !TextUtils.isEmpty(this.f8225c) ? this.f8225c : !TextUtils.isEmpty(this.b) ? this.b : "";
    }

    public boolean o() {
        return this.f8231j;
    }

    public void r() {
        if (!this.u) {
            com.jingdong.app.mall.bundle.mobileConfig.b.b("useLocalCache=false, so not restore local cache!");
            return;
        }
        JDMobileConfig.IXTime iXTime = this.v;
        if (iXTime != null && iXTime.isXTime()) {
            q();
            return;
        }
        try {
            this.q = (Map) d.b();
            com.jingdong.app.mall.bundle.mobileConfig.b.a("restoreConfig success");
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }
}

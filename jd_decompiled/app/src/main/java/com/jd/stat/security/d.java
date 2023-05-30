package com.jd.stat.security;

import android.text.TextUtils;
import android.util.Base64;
import com.jd.dynamic.DYConstants;
import com.jd.stat.common.b.f;
import com.jd.stat.common.n;
import com.jd.stat.network.NetworkException;
import com.jingdong.common.jdmiaosha.utils.cache.JDNetCacheManager;
import com.jingdong.common.unification.customtheme.CustomThemeConstance;
import com.jingdong.sdk.baseinfo.BaseInfo;
import com.jingdong.sdk.platform.business.personal.R2;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes18.dex */
public class d {
    private static String a = "https://ccfjma.m.jd.com/config";
    private static String b = "http://ccf.m.jd.care/config";

    /* renamed from: c  reason: collision with root package name */
    private boolean f7056c;
    private boolean d;

    /* renamed from: e  reason: collision with root package name */
    private boolean f7057e;

    /* renamed from: f  reason: collision with root package name */
    private List<b> f7058f;

    /* renamed from: g  reason: collision with root package name */
    private c f7059g;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes18.dex */
    public static class a {
        private static d a = new d();
    }

    /* loaded from: classes18.dex */
    public interface b {
        void a();

        void b();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes18.dex */
    public class c {

        /* renamed from: m  reason: collision with root package name */
        long f7069m;
        int q;
        int r;
        int s;
        int a = R2.attr.motionEffect_translationX;
        int b = 1;

        /* renamed from: c  reason: collision with root package name */
        int f7060c = 1;
        int d = 1;

        /* renamed from: e  reason: collision with root package name */
        int f7061e = 1;

        /* renamed from: f  reason: collision with root package name */
        int f7062f = 1;

        /* renamed from: g  reason: collision with root package name */
        int f7063g = 3;

        /* renamed from: h  reason: collision with root package name */
        int f7064h = 1;

        /* renamed from: i  reason: collision with root package name */
        int f7065i = 1;

        /* renamed from: j  reason: collision with root package name */
        int f7066j = 0;

        /* renamed from: k  reason: collision with root package name */
        int f7067k = 0;

        /* renamed from: l  reason: collision with root package name */
        int f7068l = 0;

        /* renamed from: n  reason: collision with root package name */
        int f7070n = 1;
        int o = 0;
        int p = 0;
        String t = "1.0.0";
        Set<String> u = new HashSet();
        Set<String> v = new HashSet();
        Set<String> w = new HashSet();
        Set<String> x = new HashSet();
        Set<String> y = new HashSet();
        Set<String> z = new HashSet();
        Set<String> A = new HashSet();
        Set<String> B = new HashSet();
        Set<String> C = new HashSet();
        Set<String> D = new HashSet();
        Set<String> E = new HashSet();
        Set<String> F = new HashSet();
        Set<String> G = new HashSet();
        Set<String> H = new HashSet();
        Set<String> I = d.P();
        HashMap<String, com.jd.stat.security.b> J = new HashMap<>();
        int K = 0;
        int L = 60;
        int M = 60;
        int N = 1;
        int O = 1;
        int P = 0;
        int Q = 0;
        int R = 1;
        int S = 0;
        int T = 0;
        int U = 1;

        c() {
        }

        void a(JSONObject jSONObject) {
            c(jSONObject);
        }

        void b(JSONObject jSONObject) {
            c(jSONObject);
        }

        void c(JSONObject jSONObject) {
            if (jSONObject != null) {
                try {
                    com.jd.stat.common.b.b.a("[JMA CCF] \u5f00\u59cb\u89e3\u6790");
                    this.a = jSONObject.optInt("fixedinfo", R2.attr.motionEffect_translationX);
                    this.b = jSONObject.optInt("alterationinfo", 1);
                    this.f7060c = jSONObject.optInt("openall", 1);
                    this.d = jSONObject.optInt("openalltouch", 1);
                    this.f7061e = jSONObject.optInt("processtype", 1);
                    this.f7062f = jSONObject.optInt("preactivity", 1);
                    this.f7063g = jSONObject.optInt("touchsize", 5);
                    this.f7064h = jSONObject.optInt("sensorflag", 1);
                    this.f7069m = jSONObject.optLong("nextsyncdt", 0L);
                    this.t = jSONObject.optString("configver", "1.0.0");
                    this.p = jSONObject.optInt("uaswitch", 0);
                    this.q = jSONObject.optInt("reportPhoneJMASwitch", 0);
                    this.r = jSONObject.optInt("simulatorSwitch", 1);
                    this.s = jSONObject.optInt("wifiAndStation", 0);
                    this.f7070n = jSONObject.optInt("cprs", 1);
                    this.o = jSONObject.optInt("libmodify", 0);
                    this.K = jSONObject.optInt("aloc", 0);
                    this.L = jSONObject.optInt("hooknum", 60);
                    this.M = jSONObject.optInt("hcbcs", 60);
                    this.N = jSONObject.optInt("openToken", 1);
                    this.O = jSONObject.optInt("openInfo", 1);
                    this.S = jSONObject.optInt("botDetectorTestOpen", 0);
                    this.P = jSONObject.optInt("triTouch", 0);
                    this.Q = jSONObject.optInt("houdiniCheck", 0);
                    this.R = jSONObject.optInt("botDetectorOpen", 1);
                    this.y = a(jSONObject.optString("androidpagelist"));
                    this.u = a(jSONObject.optString("manage"));
                    this.v = a(jSONObject.optString("cloak"));
                    this.w = a(jSONObject.optString("filter"), "#");
                    this.z = a(jSONObject.optString("whitelist"));
                    this.A = a(jSONObject.optString("jdgroupapps"));
                    this.x = a(jSONObject.optString("hookkeys"));
                    this.B = a(jSONObject.optString("ev"));
                    this.C = a(jSONObject.optString("ssp"));
                    this.D = a(jSONObject.optString("rpList"));
                    this.E = a(jSONObject.optString("acBlackList"));
                    this.F = a(jSONObject.optString("fixCctm"));
                    this.G = a(jSONObject.optString("alterCctm"));
                    this.H = a(jSONObject.optString("riskPackageName"));
                    JSONArray b = b(jSONObject.optString("collectRules"));
                    for (int i2 = 0; i2 < b.length(); i2++) {
                        com.jd.stat.security.b bVar = new com.jd.stat.security.b(b.optJSONArray(i2));
                        if (!TextUtils.isEmpty(bVar.a())) {
                            this.J.put(bVar.a(), bVar);
                        }
                    }
                    if (TextUtils.isEmpty(jSONObject.optString("drmuuidExp"))) {
                        this.I = d.P();
                    } else {
                        this.I = a(c(jSONObject.optString("drmuuidExp")));
                    }
                    JSONObject optJSONObject = jSONObject.optJSONObject("privacy");
                    if (optJSONObject != null) {
                        this.f7065i = optJSONObject.optInt("readPhone", 1);
                        this.f7066j = optJSONObject.optInt("readProcesslist", 0);
                        this.f7067k = optJSONObject.optInt("readApplist", 0);
                        this.f7068l = optJSONObject.optInt("rus", 0);
                    }
                    this.T = jSONObject.optInt("slfOpen", 0);
                    this.U = jSONObject.optInt("shotDetOpen", 1);
                    com.jd.stat.common.b.b.a("[JMA CCF] \u89e3\u6790\u5b8c\u6210 privacy \u914d\u7f6e:\nphoneStatusReadCmd:\t" + this.f7065i + "\nprocessReadCmd:\t" + this.f7066j + "\ninstalledAppReadCmd:\t" + this.f7067k + "\nallowRusCmd:\t" + this.f7068l);
                } catch (Throwable th) {
                    com.jd.stat.common.b.b.a("SDKRemoteConfig", th);
                }
            }
        }

        private Set<String> a(String str) {
            return a(str, DYConstants.DY_REGEX_COMMA);
        }

        private JSONArray b(String str) {
            if (TextUtils.isEmpty(str)) {
                return new JSONArray();
            }
            try {
                return new JSONArray(str);
            } catch (Throwable unused) {
                return new JSONArray();
            }
        }

        private Set<String> a(Set<String> set) {
            if (set == null) {
                return new HashSet();
            }
            HashSet hashSet = new HashSet();
            Iterator<String> it = set.iterator();
            while (it.hasNext()) {
                hashSet.add(it.next().toUpperCase());
            }
            return hashSet;
        }

        private Set<String> a(String str, String str2) {
            if (TextUtils.isEmpty(str)) {
                return new HashSet();
            }
            String[] split = str.split(str2);
            if (split == null) {
                return new HashSet();
            }
            HashSet hashSet = new HashSet();
            int length = split.length;
            for (int i2 = 0; i2 < length; i2++) {
                if (!TextUtils.isEmpty(split[i2])) {
                    hashSet.add(split[i2]);
                }
            }
            return hashSet;
        }

        private Set<String> c(String str) {
            try {
                if (TextUtils.isEmpty(str)) {
                    return new HashSet();
                }
                JSONArray b = b(str);
                HashSet hashSet = new HashSet();
                for (int i2 = 0; i2 < b.length(); i2++) {
                    String optString = b.optString(i2);
                    if (optString != null) {
                        hashSet.add(optString);
                    }
                }
                return hashSet;
            } catch (Throwable unused) {
                return new HashSet();
            }
        }
    }

    static /* synthetic */ Set P() {
        return S();
    }

    private void Q() {
        JSONObject c2 = c();
        if (com.jd.stat.common.b.b.a) {
            com.jd.stat.common.b.b.b("JDMob.Security.SDKRemoteConfig", String.format("sdk local cached config json: \n%s", com.jd.stat.common.b.c.a(c2.toString())));
        }
        this.f7059g.a(c2);
        this.f7056c = true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public JSONObject R() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("udid", "");
        String f2 = com.jd.stat.security.c.f();
        if (!TextUtils.isEmpty(f2)) {
            f2 = Base64.encodeToString(f2.getBytes(), 2);
        }
        jSONObject.put("pin", f2 != null ? f2 : "");
        jSONObject.put("boundId", com.jd.stat.common.c.d(com.jd.stat.security.c.a));
        jSONObject.put("configVer", this.f7059g.t);
        jSONObject.put("client", "android");
        jSONObject.put("appVer", com.jd.stat.common.c.c(com.jd.stat.security.c.a));
        jSONObject.put("sdkVer", com.jd.stat.common.c.c());
        jSONObject.put("osVer", com.jd.stat.common.c.b());
        jSONObject.put(JDNetCacheManager.BRAND_BIZKEY, BaseInfo.getDeviceBrand());
        jSONObject.put(CustomThemeConstance.NAVI_MODEL, BaseInfo.getDeviceModel());
        jSONObject.put("rom", n.q());
        return jSONObject;
    }

    private static Set<String> S() {
        HashSet hashSet = new HashSet();
        hashSet.add("ALL_OF_THE_BRANDS");
        return hashSet;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String g(String str) {
        try {
            String str2 = "";
            char[] charArray = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRTUVWXYZ".toCharArray();
            Random random = new Random();
            for (int i2 = 0; i2 < 9; i2++) {
                str2 = str2 + charArray[random.nextInt(charArray.length)];
            }
            return str2 + new String(Base64.encode(str.getBytes(), 2));
        } catch (Exception unused) {
            return str;
        }
    }

    public Set<String> A() {
        return this.f7059g.x;
    }

    public Set<String> B() {
        return this.f7059g.y;
    }

    public Set<String> C() {
        return this.f7059g.C;
    }

    public Set<String> D() {
        return this.f7059g.D;
    }

    public Set<String> E() {
        return this.f7059g.E;
    }

    public Set<String> F() {
        return this.f7059g.F;
    }

    public Set<String> G() {
        return this.f7059g.G;
    }

    public boolean H() {
        return this.f7059g.p == 1;
    }

    public boolean I() {
        return this.f7059g.r == 1;
    }

    public boolean J() {
        return this.f7059g.s == 1;
    }

    public boolean K() {
        return this.f7059g.f7070n == 1;
    }

    public boolean L() {
        return this.f7059g.o == 1;
    }

    public int M() {
        return this.f7059g.L;
    }

    public int N() {
        return this.f7059g.M;
    }

    public boolean O() {
        return this.f7059g.Q != 0;
    }

    public JSONObject c() {
        String b2 = f.b("ccp", "");
        if (!TextUtils.isEmpty(b2)) {
            try {
                return new JSONObject(b2);
            } catch (JSONException e2) {
                e2.printStackTrace();
                return null;
            }
        }
        com.jd.stat.common.b.b.b("JDMob.Security.SDKRemoteConfig", "last cached config is Empty!!!");
        return new JSONObject();
    }

    public boolean d() {
        return this.f7059g.d == 1;
    }

    public boolean e() {
        return this.f7059g.T == 1;
    }

    public boolean f() {
        return this.f7059g.U == 1;
    }

    public int h() {
        return this.f7059g.b;
    }

    public boolean i() {
        return this.f7059g.f7061e == 1;
    }

    public boolean j() {
        return this.f7059g.f7060c == 1;
    }

    public int k() {
        return this.f7059g.f7063g;
    }

    public boolean l() {
        return this.f7059g.f7064h == 1;
    }

    public boolean m() {
        return this.f7059g.f7065i == 1;
    }

    public boolean n() {
        return this.f7059g.f7066j == 1;
    }

    public boolean o() {
        return this.f7059g.N == 1;
    }

    public boolean p() {
        return this.f7059g.O == 1;
    }

    public boolean q() {
        return this.f7059g.P == 1;
    }

    public boolean r() {
        return this.f7059g.R != 0;
    }

    public boolean s() {
        return this.f7059g.S != 0;
    }

    public boolean t() {
        return this.f7059g.f7068l == 1;
    }

    public boolean u() {
        return this.f7059g.f7067k == 1;
    }

    public boolean v() {
        return this.f7059g.q == 1;
    }

    public Set<String> w() {
        return this.f7059g.u;
    }

    public Set<String> x() {
        return this.f7059g.v;
    }

    public Set<String> y() {
        return this.f7059g.w;
    }

    public Set<String> z() {
        return this.f7059g.A;
    }

    private d() {
        this.d = false;
        this.f7057e = false;
        this.f7058f = new CopyOnWriteArrayList();
        this.f7059g = new c();
        JSONObject c2 = c();
        if (com.jd.stat.common.b.b.a) {
            com.jd.stat.common.b.b.b("JDMob.Security.SDKRemoteConfig", String.format("sdk local cached config json: \n%s", com.jd.stat.common.b.c.a(c2.toString())));
        }
        this.f7059g.a(c2);
        this.f7056c = com.jd.stat.security.c.a != null;
    }

    public boolean b() {
        return this.d;
    }

    public com.jd.stat.security.b d(String str) {
        HashMap<String, com.jd.stat.security.b> hashMap = this.f7059g.J;
        if (hashMap == null) {
            return null;
        }
        return hashMap.get(str);
    }

    public boolean e(String str) {
        com.jd.stat.security.b d = d(str);
        if (d == null) {
            return true;
        }
        return d.b();
    }

    public boolean f(String str) {
        com.jd.stat.security.b d = d(str);
        if (d == null) {
            return true;
        }
        return d.c();
    }

    public boolean b(String str) {
        Set<String> set = this.f7059g.H;
        if (set == null) {
            return false;
        }
        Iterator<String> it = set.iterator();
        while (it.hasNext()) {
            if (str.contains(it.next())) {
                return true;
            }
        }
        return false;
    }

    public static d a() {
        if (com.jd.stat.security.c.a != null && !a.a.f7056c) {
            a.a.Q();
        }
        return a.a;
    }

    public int g() {
        return this.f7059g.a;
    }

    public boolean c(String str) {
        Set<String> set = this.f7059g.H;
        if (set == null) {
            return false;
        }
        return set.contains(str);
    }

    public void a(boolean z, final b bVar, boolean z2) {
        com.jd.stat.common.b.b.a("[JMA CCF] \u5f00\u59cb\u83b7\u53d6\u51c6\u5907");
        if (z) {
            if (z2 || System.currentTimeMillis() >= this.f7059g.f7069m * 1000) {
                this.f7057e = true;
                com.jd.stat.common.b.b.a("[JMA CCF] \u83b7\u53d6\u4e2d");
                com.jd.stat.network.d dVar = new com.jd.stat.network.d(com.jd.stat.security.c.e() ? b : a) { // from class: com.jd.stat.security.d.1
                    @Override // com.jd.stat.network.d
                    protected String e() {
                        try {
                            JSONObject R = d.this.R();
                            if (com.jd.stat.common.b.b.a) {
                                com.jd.stat.common.b.b.b("JDMob.Security.SDKRemoteConfig", String.format("sdk request json: \n%s", com.jd.stat.common.b.c.a(R.toString())));
                            }
                            return URLEncoder.encode(d.this.g(R.toString()), "UTF-8");
                        } catch (UnsupportedEncodingException e2) {
                            e2.printStackTrace();
                            return null;
                        } catch (JSONException e3) {
                            e3.printStackTrace();
                            return null;
                        }
                    }
                };
                dVar.a(new com.jd.stat.network.f() { // from class: com.jd.stat.security.d.2
                    @Override // com.jd.stat.network.f
                    public void a(com.jd.stat.network.e eVar) {
                        d.this.a(eVar);
                        b bVar2 = bVar;
                        if (bVar2 != null) {
                            bVar2.a();
                        }
                        d.this.f7057e = false;
                        d.this.d = true;
                    }

                    @Override // com.jd.stat.network.f
                    public void a(NetworkException networkException) {
                        com.jd.stat.common.b.b.a("[JMA CCF] \u83b7\u53d6\u5931\u8d25" + networkException.getMessage());
                        b bVar2 = bVar;
                        if (bVar2 != null) {
                            bVar2.b();
                        }
                        d.this.f7057e = false;
                        d.this.d = false;
                    }
                });
                dVar.b(true);
                dVar.a((Object) ("SDKRemoteConfig." + System.currentTimeMillis()));
                dVar.h();
            }
        }
    }

    public void a(boolean z) {
        a(z, null, false);
    }

    public void a(JSONObject jSONObject) {
        try {
            if (this.f7059g == null || jSONObject == null || jSONObject.length() == 0) {
                return;
            }
            this.f7059g.c(jSONObject);
            f.a("ccp", jSONObject.toString());
        } catch (Exception unused) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(com.jd.stat.network.e eVar) {
        JSONObject d = eVar.d();
        if (d != null) {
            try {
                if (com.jd.stat.common.b.b.a) {
                    com.jd.stat.common.b.b.b("JDMob.Security.SDKRemoteConfig", String.format("get remote config json json: \n%s", com.jd.stat.common.b.c.a(d.toString())));
                }
                if (d.length() == 0) {
                    return;
                }
                this.f7059g.b(d);
                f.a("ccp", d.toString());
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    public boolean a(String str) {
        Set<String> set = this.f7059g.B;
        return set != null && set.contains(str);
    }

    public boolean a(String str, String str2) {
        Set<String> set = this.f7059g.I;
        if (set == null) {
            return false;
        }
        if (set.contains("ALL_OF_THE_BRANDS")) {
            return true;
        }
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        if (TextUtils.isEmpty(str2)) {
            return this.f7059g.I.contains(str.toUpperCase() + "|ALL_OF_THE_MODELS");
        }
        if (this.f7059g.I.contains(str.toUpperCase() + "|ALL_OF_THE_MODELS")) {
            return true;
        }
        return this.f7059g.I.contains(str.toUpperCase() + "|" + str2.toUpperCase());
    }
}

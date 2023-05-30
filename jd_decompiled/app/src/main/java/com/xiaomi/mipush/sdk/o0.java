package com.xiaomi.mipush.sdk;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import com.xiaomi.push.y4;
import com.xiaomi.push.z6;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

/* loaded from: classes11.dex */
public class o0 {

    /* renamed from: e */
    private static volatile o0 f18406e;
    private Context a;
    private a b;

    /* renamed from: c */
    private Map<String, a> f18407c;
    String d;

    /* loaded from: classes11.dex */
    public static class a {
        public String a;
        public String b;

        /* renamed from: c */
        public String f18408c;
        public String d;

        /* renamed from: e */
        public String f18409e;

        /* renamed from: f */
        public String f18410f;

        /* renamed from: g */
        public String f18411g;

        /* renamed from: h */
        public String f18412h;

        /* renamed from: i */
        public boolean f18413i = true;

        /* renamed from: j */
        public boolean f18414j = false;

        /* renamed from: k */
        public int f18415k = 1;

        /* renamed from: l */
        private Context f18416l;

        public a(Context context) {
            this.f18416l = context;
        }

        private String a() {
            Context context = this.f18416l;
            return y4.h(context, context.getPackageName());
        }

        public static String b(a aVar) {
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("appId", aVar.a);
                jSONObject.put("appToken", aVar.b);
                jSONObject.put("regId", aVar.f18408c);
                jSONObject.put("regSec", aVar.d);
                jSONObject.put("devId", aVar.f18410f);
                jSONObject.put("vName", aVar.f18409e);
                jSONObject.put("valid", aVar.f18413i);
                jSONObject.put("paused", aVar.f18414j);
                jSONObject.put("envType", aVar.f18415k);
                jSONObject.put("regResource", aVar.f18411g);
                return jSONObject.toString();
            } catch (Throwable th) {
                g.j.a.a.a.c.s(th);
                return null;
            }
        }

        public void c() {
            o0.b(this.f18416l).edit().clear().commit();
            this.a = null;
            this.b = null;
            this.f18408c = null;
            this.d = null;
            this.f18410f = null;
            this.f18409e = null;
            this.f18413i = false;
            this.f18414j = false;
            this.f18415k = 1;
        }

        public void d(int i2) {
            this.f18415k = i2;
        }

        public void e(String str, String str2) {
            this.f18408c = str;
            this.d = str2;
            this.f18410f = z6.z(this.f18416l);
            this.f18409e = a();
            this.f18413i = true;
        }

        public void f(String str, String str2, String str3) {
            this.a = str;
            this.b = str2;
            this.f18411g = str3;
            SharedPreferences.Editor edit = o0.b(this.f18416l).edit();
            edit.putString("appId", this.a);
            edit.putString("appToken", str2);
            edit.putString("regResource", str3);
            edit.commit();
        }

        public void g(boolean z) {
            this.f18414j = z;
        }

        public boolean h() {
            return i(this.a, this.b);
        }

        public boolean i(String str, String str2) {
            boolean equals = TextUtils.equals(this.a, str);
            boolean equals2 = TextUtils.equals(this.b, str2);
            boolean z = !TextUtils.isEmpty(this.f18408c);
            boolean z2 = !TextUtils.isEmpty(this.d);
            boolean z3 = TextUtils.isEmpty(z6.o(this.f18416l)) || TextUtils.equals(this.f18410f, z6.z(this.f18416l)) || TextUtils.equals(this.f18410f, z6.y(this.f18416l));
            boolean z4 = equals && equals2 && z && z2 && z3;
            if (!z4) {
                g.j.a.a.a.c.E(String.format("register invalid, aid=%s;atn=%s;rid=%s;rse=%s;did=%s", Boolean.valueOf(equals), Boolean.valueOf(equals2), Boolean.valueOf(z), Boolean.valueOf(z2), Boolean.valueOf(z3)));
            }
            return z4;
        }

        public void j() {
            this.f18413i = false;
            o0.b(this.f18416l).edit().putBoolean("valid", this.f18413i).commit();
        }

        public void k(String str, String str2, String str3) {
            this.f18408c = str;
            this.d = str2;
            this.f18410f = z6.z(this.f18416l);
            this.f18409e = a();
            this.f18413i = true;
            SharedPreferences.Editor edit = o0.b(this.f18416l).edit();
            edit.putString("regId", str);
            edit.putString("regSec", str2);
            edit.putString("devId", this.f18410f);
            edit.putString("vName", a());
            edit.putBoolean("valid", true);
            edit.putString("appRegion", str3);
            edit.commit();
        }
    }

    private o0(Context context) {
        this.a = context;
        r();
    }

    public static SharedPreferences b(Context context) {
        return context.getSharedPreferences("mipush", 0);
    }

    public static o0 c(Context context) {
        if (f18406e == null) {
            synchronized (o0.class) {
                if (f18406e == null) {
                    f18406e = new o0(context);
                }
            }
        }
        return f18406e;
    }

    private void r() {
        this.b = new a(this.a);
        this.f18407c = new HashMap();
        SharedPreferences b = b(this.a);
        this.b.a = b.getString("appId", null);
        this.b.b = b.getString("appToken", null);
        this.b.f18408c = b.getString("regId", null);
        this.b.d = b.getString("regSec", null);
        this.b.f18410f = b.getString("devId", null);
        if (!TextUtils.isEmpty(this.b.f18410f) && z6.l(this.b.f18410f)) {
            this.b.f18410f = z6.z(this.a);
            b.edit().putString("devId", this.b.f18410f).commit();
        }
        this.b.f18409e = b.getString("vName", null);
        this.b.f18413i = b.getBoolean("valid", true);
        this.b.f18414j = b.getBoolean("paused", false);
        this.b.f18415k = b.getInt("envType", 1);
        this.b.f18411g = b.getString("regResource", null);
        this.b.f18412h = b.getString("appRegion", null);
    }

    public int a() {
        return this.b.f18415k;
    }

    public String d() {
        return this.b.a;
    }

    public void e() {
        this.b.c();
    }

    public void f(int i2) {
        this.b.d(i2);
        b(this.a).edit().putInt("envType", i2).commit();
    }

    public void g(String str) {
        SharedPreferences.Editor edit = b(this.a).edit();
        edit.putString("vName", str);
        edit.commit();
        this.b.f18409e = str;
    }

    public void h(String str, a aVar) {
        this.f18407c.put(str, aVar);
        b(this.a).edit().putString("hybrid_app_info_" + str, a.b(aVar)).commit();
    }

    public void i(String str, String str2, String str3) {
        this.b.f(str, str2, str3);
    }

    public void j(boolean z) {
        this.b.g(z);
        b(this.a).edit().putBoolean("paused", z).commit();
    }

    public boolean k() {
        Context context = this.a;
        return !TextUtils.equals(y4.h(context, context.getPackageName()), this.b.f18409e);
    }

    public boolean l(String str, String str2) {
        return this.b.i(str, str2);
    }

    public String m() {
        return this.b.b;
    }

    public void n() {
        this.b.j();
    }

    public void o(String str, String str2, String str3) {
        this.b.k(str, str2, str3);
    }

    public boolean p() {
        if (this.b.h()) {
            return true;
        }
        g.j.a.a.a.c.o("Don't send message before initialization succeeded!");
        return false;
    }

    public String q() {
        return this.b.f18408c;
    }

    public boolean s() {
        return this.b.h();
    }

    public String t() {
        return this.b.d;
    }

    public boolean u() {
        return (TextUtils.isEmpty(this.b.a) || TextUtils.isEmpty(this.b.b) || TextUtils.isEmpty(this.b.f18408c) || TextUtils.isEmpty(this.b.d)) ? false : true;
    }

    public String v() {
        return this.b.f18411g;
    }

    public boolean w() {
        return this.b.f18414j;
    }

    public boolean x() {
        return !this.b.f18413i;
    }
}

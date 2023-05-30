package com.jdjr.risk.biometric.a;

import org.json.JSONObject;

/* loaded from: classes18.dex */
public class a {
    int A;
    JSONObject B;
    int C;
    JSONObject D;
    String E;
    String a;
    int b;

    /* renamed from: c  reason: collision with root package name */
    int f7279c;
    JSONObject d;

    /* renamed from: e  reason: collision with root package name */
    int f7280e;

    /* renamed from: f  reason: collision with root package name */
    JSONObject f7281f;

    /* renamed from: g  reason: collision with root package name */
    int f7282g;

    /* renamed from: h  reason: collision with root package name */
    JSONObject f7283h;

    /* renamed from: i  reason: collision with root package name */
    int f7284i;

    /* renamed from: j  reason: collision with root package name */
    JSONObject f7285j;

    /* renamed from: k  reason: collision with root package name */
    int f7286k;

    /* renamed from: l  reason: collision with root package name */
    JSONObject f7287l;

    /* renamed from: m  reason: collision with root package name */
    int f7288m;

    /* renamed from: n  reason: collision with root package name */
    JSONObject f7289n;
    int o;
    JSONObject p;
    int q;
    JSONObject r;
    int s;
    JSONObject t;
    int u;
    JSONObject v;
    int w;
    JSONObject x;
    int y;
    JSONObject z;

    public a(String str, int i2, int i3, JSONObject jSONObject, int i4, JSONObject jSONObject2, int i5, JSONObject jSONObject3, int i6, JSONObject jSONObject4, int i7, JSONObject jSONObject5, int i8, JSONObject jSONObject6, int i9, JSONObject jSONObject7, int i10, JSONObject jSONObject8, int i11, JSONObject jSONObject9, int i12, JSONObject jSONObject10, int i13, JSONObject jSONObject11, int i14, JSONObject jSONObject12, int i15, JSONObject jSONObject13, int i16, JSONObject jSONObject14, String str2) {
        this.a = str;
        this.b = i2;
        this.f7279c = i3;
        this.d = jSONObject;
        this.f7280e = i4;
        this.f7281f = jSONObject2;
        this.f7282g = i5;
        this.f7283h = jSONObject3;
        this.f7284i = i6;
        this.f7285j = jSONObject4;
        this.f7286k = i7;
        this.f7287l = jSONObject5;
        this.f7288m = i8;
        this.f7289n = jSONObject6;
        this.o = i9;
        this.p = jSONObject7;
        this.q = i10;
        this.r = jSONObject8;
        this.s = i11;
        this.t = jSONObject9;
        this.u = i12;
        this.v = jSONObject10;
        this.w = i13;
        this.x = jSONObject11;
        this.y = i14;
        this.z = jSONObject12;
        this.A = i15;
        this.B = jSONObject13;
        this.C = i16;
        this.D = jSONObject14;
        this.E = str2;
    }

    public boolean A() {
        JSONObject jSONObject = this.D;
        return jSONObject != null && jSONObject.optInt("useOld") == 1;
    }

    public boolean B() {
        JSONObject jSONObject = this.D;
        return jSONObject != null && jSONObject.optInt("useCco") == 1;
    }

    public int C() {
        JSONObject jSONObject = this.D;
        if (jSONObject != null) {
            return jSONObject.optInt("senDur");
        }
        return 0;
    }

    public int D() {
        JSONObject jSONObject = this.D;
        if (jSONObject != null) {
            return jSONObject.optInt("senPrd");
        }
        return 0;
    }

    public long E() {
        JSONObject jSONObject = this.D;
        if (jSONObject != null) {
            return jSONObject.optLong("isolateTime");
        }
        return 0L;
    }

    public boolean F() {
        return this.D.optInt("cltApp") == 1;
    }

    public long G() {
        return this.D.optInt("cltAppFreq");
    }

    public boolean H() {
        JSONObject jSONObject = this.B;
        return jSONObject != null && jSONObject.optInt("secInfo", 0) == 1;
    }

    public boolean a() {
        return this.b == 1;
    }

    public boolean b() {
        return this.C == 1;
    }

    public int c() {
        return this.f7279c;
    }

    public JSONObject d() {
        return this.d;
    }

    public int e() {
        return this.f7280e;
    }

    public JSONObject f() {
        return this.f7281f;
    }

    public int g() {
        return this.f7282g;
    }

    public JSONObject h() {
        return this.f7283h;
    }

    public int i() {
        return this.f7284i;
    }

    public JSONObject j() {
        return this.f7285j;
    }

    public int k() {
        return this.f7286k;
    }

    public JSONObject l() {
        return this.f7287l;
    }

    public int m() {
        return this.f7288m;
    }

    public JSONObject n() {
        return this.f7289n;
    }

    public int o() {
        return this.o;
    }

    public JSONObject p() {
        return this.p;
    }

    public int q() {
        return this.q;
    }

    public JSONObject r() {
        return this.r;
    }

    public int s() {
        return this.s;
    }

    public JSONObject t() {
        return this.t;
    }

    public int u() {
        return this.u;
    }

    public JSONObject v() {
        return this.v;
    }

    public int w() {
        return this.y;
    }

    public JSONObject x() {
        return this.z;
    }

    public int y() {
        return this.A;
    }

    public JSONObject z() {
        return this.B;
    }
}

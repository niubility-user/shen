package com.tencent.mapsdk.internal;

import com.jingdong.common.unification.customtheme.CustomThemeConstance;
import com.tencent.map.tools.json.annotation.Json;

/* loaded from: classes9.dex */
public class v6 extends w6 {
    @Json(name = "create")
    private long b;
    @Json(name = "destroy")

    /* renamed from: c  reason: collision with root package name */
    private long f17378c;
    @Json(name = "mapLoad")
    private r6 d;
    @Json(name = "oversea")

    /* renamed from: e  reason: collision with root package name */
    private t6 f17379e;
    @Json(name = "indoorLog")

    /* renamed from: f  reason: collision with root package name */
    private q6 f17380f;
    @Json(name = "darkMode")

    /* renamed from: g  reason: collision with root package name */
    private l6 f17381g;
    @Json(name = "pointEvent")

    /* renamed from: h  reason: collision with root package name */
    private u6 f17382h;
    @Json(name = "aoi")

    /* renamed from: i  reason: collision with root package name */
    private h6 f17383i;
    @Json(name = "vectorHeat")

    /* renamed from: j  reason: collision with root package name */
    private z6 f17384j;
    @Json(name = "heatMap")

    /* renamed from: k  reason: collision with root package name */
    private p6 f17385k;
    @Json(name = "arcLine")

    /* renamed from: l  reason: collision with root package name */
    private i6 f17386l;
    @Json(name = "dotScatter")

    /* renamed from: m  reason: collision with root package name */
    private m6 f17387m;
    @Json(name = "bitmapScatter")

    /* renamed from: n  reason: collision with root package name */
    private j6 f17388n;
    @Json(name = "trail")
    private x6 o;
    @Json(name = CustomThemeConstance.NAVI_MODEL)
    private n6 p;
    @Json(name = "groundOverlay")
    private o6 q;
    @Json(name = "offline")
    private s6 r;
    @Json(name = "customStyle")
    private k6 s;
    @Json(name = "ugc")
    private y6 t;

    public v6(long j2) {
        super(j2);
        this.b = j2;
    }

    public h6 b() {
        if (this.f17383i == null) {
            this.f17383i = new h6(System.currentTimeMillis() - this.a);
        }
        return this.f17383i;
    }

    public i6 c() {
        if (this.f17386l == null) {
            this.f17386l = new i6(System.currentTimeMillis() - this.a);
        }
        return this.f17386l;
    }

    public j6 d() {
        if (this.f17388n == null) {
            this.f17388n = new j6(System.currentTimeMillis() - this.a);
        }
        return this.f17388n;
    }

    public k6 e() {
        if (this.s == null) {
            this.s = new k6(System.currentTimeMillis() - this.a);
        }
        return this.s;
    }

    public l6 f() {
        if (this.f17381g == null) {
            this.f17381g = new l6(System.currentTimeMillis() - this.a);
        }
        return this.f17381g;
    }

    public m6 g() {
        if (this.f17387m == null) {
            this.f17387m = new m6(System.currentTimeMillis() - this.a);
        }
        return this.f17387m;
    }

    public n6 h() {
        if (this.p == null) {
            this.p = new n6(System.currentTimeMillis() - this.a);
        }
        return this.p;
    }

    public o6 i() {
        if (this.q == null) {
            this.q = new o6(System.currentTimeMillis() - this.a);
        }
        return this.q;
    }

    public p6 j() {
        if (this.f17385k == null) {
            this.f17385k = new p6(System.currentTimeMillis() - this.a);
        }
        return this.f17385k;
    }

    public q6 k() {
        if (this.f17380f == null) {
            this.f17380f = new q6(System.currentTimeMillis() - this.a);
        }
        return this.f17380f;
    }

    public r6 l() {
        if (this.d == null) {
            this.d = new r6(this.a);
        }
        return this.d;
    }

    public s6 m() {
        if (this.r == null) {
            this.r = new s6(System.currentTimeMillis() - this.a);
        }
        return this.r;
    }

    public t6 n() {
        if (this.f17379e == null) {
            this.f17379e = new t6(System.currentTimeMillis() - this.a);
        }
        return this.f17379e;
    }

    public u6 o() {
        if (this.f17382h == null) {
            this.f17382h = new u6(System.currentTimeMillis() - this.a);
        }
        return this.f17382h;
    }

    public x6 p() {
        if (this.o == null) {
            this.o = new x6(System.currentTimeMillis() - this.a);
        }
        return this.o;
    }

    public y6 q() {
        if (this.t == null) {
            this.t = new y6(System.currentTimeMillis() - this.a);
        }
        return this.t;
    }

    public z6 r() {
        if (this.f17384j == null) {
            this.f17384j = new z6(System.currentTimeMillis() - this.a);
        }
        return this.f17384j;
    }

    public v6 s() {
        this.f17378c = System.currentTimeMillis() - this.b;
        return this;
    }
}

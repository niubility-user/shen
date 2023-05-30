package com.jd.hybrid.downloader;

import com.jd.jdcache.util.UrlHelper;

/* loaded from: classes13.dex */
public class d implements Comparable<d> {

    /* renamed from: g  reason: collision with root package name */
    private String f2669g;

    /* renamed from: h  reason: collision with root package name */
    private String f2670h;

    /* renamed from: i  reason: collision with root package name */
    private String f2671i;

    /* renamed from: j  reason: collision with root package name */
    private String f2672j;

    /* renamed from: k  reason: collision with root package name */
    private int f2673k;

    /* renamed from: l  reason: collision with root package name */
    private b f2674l;

    /* renamed from: m  reason: collision with root package name */
    private com.jd.hybrid.downloader.n.a f2675m;

    /* renamed from: n  reason: collision with root package name */
    private boolean f2676n;
    private String o = "GET";

    public d(String str, String str2, String str3, String str4, boolean z) {
        this.f2670h = str2;
        this.f2671i = str3;
        this.f2672j = str4;
    }

    @Override // java.lang.Comparable
    /* renamed from: a  reason: merged with bridge method [inline-methods] */
    public int compareTo(d dVar) {
        return dVar.f2673k - this.f2673k;
    }

    public b b() {
        return this.f2674l;
    }

    public com.jd.hybrid.downloader.n.a c() {
        return this.f2675m;
    }

    public String d() {
        return this.f2672j;
    }

    public String e() {
        return this.f2669g;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && d.class == obj.getClass() && (obj instanceof d)) {
            return this.f2670h.equals(((d) obj).f2670h);
        }
        return false;
    }

    public int f() {
        return this.f2673k;
    }

    public String g() {
        return this.f2671i;
    }

    public String h() {
        return this.o;
    }

    public int hashCode() {
        return this.f2670h.hashCode();
    }

    public String i() {
        return this.f2670h;
    }

    public boolean j() {
        return this.f2676n;
    }

    public void k(boolean z) {
        this.f2676n = z;
    }

    public void l(b bVar) {
        this.f2674l = bVar;
    }

    public void m(com.jd.hybrid.downloader.n.a aVar) {
        this.f2675m = aVar;
    }

    public void n(String str) {
        this.f2669g = str;
    }

    public void o(int i2) {
        this.f2673k = i2;
    }

    public void p() {
        this.o = UrlHelper.METHOD_HEAD;
    }

    public void q(int i2) {
    }

    public d(String str, String str2, String str3, String str4, boolean z, int i2, boolean z2) {
        this.f2670h = str2;
        this.f2671i = str3;
        this.f2672j = str4;
        this.f2673k = i2;
        this.f2676n = z2;
    }
}

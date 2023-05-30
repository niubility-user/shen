package com.jdjr.risk.device.entity;

import com.huawei.hms.adapter.internal.CommonCode;
import com.jd.dynamic.base.interfaces.IExceptionHandler;
import org.json.JSONArray;

/* loaded from: classes18.dex */
public class e extends a {
    private String b;

    /* renamed from: c  reason: collision with root package name */
    private String f7376c;
    private String d;

    /* renamed from: e  reason: collision with root package name */
    private String f7377e;

    /* renamed from: f  reason: collision with root package name */
    private String f7378f;

    /* renamed from: g  reason: collision with root package name */
    private String f7379g;

    /* renamed from: h  reason: collision with root package name */
    private String f7380h;

    /* renamed from: i  reason: collision with root package name */
    private String f7381i;

    /* renamed from: j  reason: collision with root package name */
    private String f7382j;

    /* renamed from: k  reason: collision with root package name */
    private long f7383k;

    /* renamed from: l  reason: collision with root package name */
    private long f7384l;

    /* renamed from: m  reason: collision with root package name */
    private String f7385m;

    /* renamed from: n  reason: collision with root package name */
    private String f7386n;
    private int o;
    private int p;
    private int q;
    private String r;
    private int s;
    private int t;
    private int u;
    private String v;
    private String w;
    private String x;

    public void a(int i2) {
        this.t = i2;
        this.a.put("backCamera", Integer.valueOf(i2));
    }

    public void a(long j2) {
        this.f7383k = j2;
        this.a.put("romSize", String.valueOf(j2));
    }

    public void a(String str) {
        this.b = str;
        this.a.put(CommonCode.MapKey.HAS_RESOLUTION, str);
    }

    public void a(JSONArray jSONArray) {
        this.r = jSONArray.toString();
        this.a.put(IExceptionHandler.DynamicExceptionData.TYPE_STORAGE, jSONArray.toString());
    }

    public void b(int i2) {
        this.u = i2;
        this.a.put("frontCamera", Integer.valueOf(i2));
    }

    public void b(long j2) {
        this.f7384l = j2;
        this.a.put("ramSize", Long.valueOf(j2));
    }

    public void b(String str) {
        this.f7376c = str;
        this.a.put("dip", str);
    }

    public void c(int i2) {
        this.s = i2;
        this.a.put("numberOfCameras", Integer.valueOf(i2));
    }

    public void c(String str) {
        this.d = str;
        this.a.put("ppi", str);
    }

    public void d(int i2) {
        this.o = i2;
        this.a.put("bluetooth", Integer.valueOf(i2));
    }

    public void d(String str) {
        this.f7377e = str;
        this.a.put("hardware", str);
    }

    public void e(int i2) {
        this.p = i2;
        this.a.put("resolutionWidth", Integer.valueOf(i2));
    }

    public void e(String str) {
        this.f7378f = str;
        this.a.put("diskSpace", str);
    }

    public void f(int i2) {
        this.q = i2;
        this.a.put("resolutionHeight", Integer.valueOf(i2));
    }

    public void f(String str) {
        this.f7379g = str;
        this.a.put("freeMemory", str);
    }

    public void g(String str) {
        this.f7380h = str;
        this.a.put("usedMemory", str);
    }

    public void h(String str) {
        this.f7381i = str;
        this.a.put("mem_size", str);
    }

    public void i(String str) {
        this.f7382j = str;
        this.a.put("freeDisk", str);
    }

    public void j(String str) {
        this.f7385m = str;
        this.a.put("bluetoothAddress", str);
    }

    public void k(String str) {
        this.f7386n = str;
        this.a.put("bluetoothName", str);
    }

    public void l(String str) {
        this.v = str;
        this.a.put("sm", str);
    }

    public void m(String str) {
        this.w = str;
        this.a.put("romSurplus", str);
    }

    public void n(String str) {
        this.x = str;
        this.a.put("realResolution", str);
    }
}

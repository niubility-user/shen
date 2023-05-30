package com.tencent.mapsdk.internal;

import com.tencent.map.tools.json.JsonComposer;
import com.tencent.map.tools.json.annotation.Json;
import java.util.List;

/* loaded from: classes9.dex */
public class bi extends JsonComposer {

    /* renamed from: e  reason: collision with root package name */
    public static int f16347e = 0;

    /* renamed from: f  reason: collision with root package name */
    public static final int f16348f = 1000;

    /* renamed from: g  reason: collision with root package name */
    public static int f16349g = 100;

    /* renamed from: h  reason: collision with root package name */
    public static final int f16350h = 0;

    /* renamed from: i  reason: collision with root package name */
    public static final int f16351i = 0;

    /* renamed from: j  reason: collision with root package name */
    public static final int f16352j = 7;
    @Json(name = "version")
    private int a;
    @Json(name = "frontier")
    private ai b;
    @Json(name = "ver_data")

    /* renamed from: c  reason: collision with root package name */
    private List<xh> f16353c;
    @Json(name = "detail")
    private List<yh> d;

    public List<xh> a() {
        return this.f16353c;
    }

    public void a(int i2) {
        this.a = i2;
    }

    public void a(ai aiVar) {
        this.b = aiVar;
    }

    public void a(List<xh> list) {
        this.f16353c = list;
    }

    public ai b() {
        return this.b;
    }

    public void b(List<yh> list) {
        this.d = list;
    }

    public int c() {
        return this.a;
    }

    public List<yh> d() {
        return this.d;
    }
}

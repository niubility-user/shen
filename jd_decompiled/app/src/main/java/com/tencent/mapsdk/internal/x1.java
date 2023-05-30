package com.tencent.mapsdk.internal;

import com.tencent.mapsdk.internal.x5;
import com.tencent.tencentmap.mapsdk.maps.model.LatLng;

/* loaded from: classes9.dex */
public class x1 implements x5.a {

    /* renamed from: c  reason: collision with root package name */
    public static final double f17451c = 1.0d;
    private static final z5 d = new z5(1.0d);
    private o5 a;
    private double b;

    public x1(LatLng latLng) {
        this(latLng, 1.0d);
    }

    public x1(LatLng latLng, double d2) {
        a(latLng);
        a(d2);
    }

    @Override // com.tencent.mapsdk.internal.x5.a
    public o5 a() {
        return this.a;
    }

    public void a(double d2) {
        if (d2 < 0.0d) {
            d2 = 1.0d;
        }
        this.b = d2;
    }

    public void a(LatLng latLng) {
        this.a = d.c(latLng);
    }

    public double b() {
        return this.b;
    }

    public LatLng c() {
        return d.b(this.a);
    }
}

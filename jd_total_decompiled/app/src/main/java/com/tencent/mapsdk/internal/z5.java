package com.tencent.mapsdk.internal;

import com.tencent.tencentmap.mapsdk.maps.model.LatLng;

/* loaded from: classes9.dex */
public class z5 {
    public final double a;

    public z5(double d) {
        this.a = d;
    }

    public LatLng b(o5 o5Var) {
        double d = o5Var.a;
        double d2 = this.a;
        double d3 = ((d / d2) - 0.5d) * 360.0d;
        double degrees = 90.0d - Math.toDegrees(Math.atan(Math.exp(((-(0.5d - (o5Var.b / d2))) * 2.0d) * 3.141592653589793d)) * 2.0d);
        if (Double.isNaN(degrees)) {
            degrees = 0.0d;
        }
        if (Double.isNaN(d3)) {
            d3 = 0.0d;
        }
        return new LatLng(degrees, d3);
    }

    public o5 c(LatLng latLng) {
        double sin = Math.sin(Math.toRadians(latLng.latitude));
        double d = this.a;
        return new o5(((latLng.longitude / 360.0d) + 0.5d) * d, (((Math.log((sin + 1.0d) / (1.0d - sin)) * 0.5d) / (-6.283185307179586d)) + 0.5d) * d);
    }
}

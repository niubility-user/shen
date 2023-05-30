package com.tencent.mapsdk.internal;

import com.jd.dynamic.DYConstants;
import com.tencent.tencentmap.mapsdk.maps.interfaces.Coordinate;

/* loaded from: classes9.dex */
public class o5 implements Coordinate {
    public double a;
    public double b;

    public o5() {
    }

    public o5(double d, double d2) {
        this.a = d;
        this.b = d2;
    }

    public static boolean a(double d, double d2) {
        return Double.compare(d, d2) != 0 && Math.abs(d - d2) > 1.0E-6d;
    }

    public float a(o5 o5Var) {
        return o5Var.b(this).b();
    }

    public o5 a(double d) {
        double b = b();
        Double.isNaN(b);
        return b(d / b);
    }

    public o5 a(float f2) {
        double d = f2;
        return new o5((float) ((Math.cos(d) * this.a) - (Math.sin(d) * this.b)), (float) ((Math.sin(d) * this.a) + (Math.cos(d) * this.b)));
    }

    public o5 a(int i2) {
        double d = this.a;
        double d2 = this.b;
        int i3 = 0;
        while (i3 < i2) {
            double d3 = -d;
            i3++;
            d = d2;
            d2 = d3;
        }
        return new o5(d, d2);
    }

    public o5 a(o5 o5Var, float f2) {
        return b(o5Var).a(f2).c(o5Var);
    }

    public boolean a() {
        double d = this.a;
        if (d >= 0.0d && d <= 1.0d) {
            double d2 = this.b;
            if (d2 >= 0.0d && d2 <= 1.0d) {
                return true;
            }
        }
        return false;
    }

    public float b() {
        return (float) Math.hypot(this.a, this.b);
    }

    public o5 b(double d) {
        return new o5(this.a * d, this.b * d);
    }

    public o5 b(double d, double d2) {
        return new o5(this.a - d, this.b - d2);
    }

    public o5 b(o5 o5Var) {
        return b(o5Var.a, o5Var.b);
    }

    public o5 c() {
        return a(1.0d);
    }

    public o5 c(double d, double d2) {
        return new o5(this.a * d, this.b * d2);
    }

    public o5 c(o5 o5Var) {
        return d(o5Var.a, o5Var.b);
    }

    public o5 d(double d, double d2) {
        return new o5(this.a + d, this.b + d2);
    }

    public void e(double d, double d2) {
        this.a = d;
        this.b = d2;
    }

    public boolean equals(Object obj) {
        if (obj instanceof o5) {
            o5 o5Var = (o5) obj;
            return (a(this.a, o5Var.a) || a(this.b, o5Var.b)) ? false : true;
        }
        return false;
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.interfaces.Coordinate
    public void setX(double d) {
        this.a = d;
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.interfaces.Coordinate
    public void setY(double d) {
        this.b = d;
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.interfaces.Coordinate
    public void setZ(double d) {
    }

    public String toString() {
        return this.a + DYConstants.DY_REGEX_COMMA + this.b;
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.interfaces.Coordinate
    public double x() {
        return this.a;
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.interfaces.Coordinate
    public double y() {
        return this.b;
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.interfaces.Coordinate
    public double z() {
        return 0.0d;
    }
}

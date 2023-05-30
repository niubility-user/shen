package com.tencent.mapsdk.internal;

import com.tencent.tencentmap.mapsdk.maps.interfaces.Coordinate;

/* loaded from: classes9.dex */
public class w5 implements Coordinate, Cloneable {

    /* renamed from: c  reason: collision with root package name */
    public static final double f17429c = 2.003750834E7d;
    private double a = Double.MIN_VALUE;
    private double b = Double.MIN_VALUE;

    public w5(double d, double d2) {
        setX(d);
        setY(d2);
    }

    /* renamed from: a  reason: merged with bridge method [inline-methods] */
    public w5 clone() {
        return new w5(this.b, this.a);
    }

    public double b() {
        return this.b;
    }

    public double c() {
        return this.a;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj.hashCode() == hashCode() && (obj instanceof w5)) {
            w5 w5Var = (w5) obj;
            return Double.doubleToLongBits(w5Var.b) == Double.doubleToLongBits(this.b) && Double.doubleToLongBits(w5Var.a) == Double.doubleToLongBits(this.a);
        }
        return false;
    }

    public int hashCode() {
        long doubleToLongBits = Double.doubleToLongBits(this.b);
        long doubleToLongBits2 = Double.doubleToLongBits(this.a);
        return ((((int) (doubleToLongBits2 ^ (doubleToLongBits2 >>> 32))) + 31) * 31) + ((int) (doubleToLongBits ^ (doubleToLongBits >>> 32)));
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.interfaces.Coordinate
    public void setX(double d) {
        this.b = Math.max(-2.003750834E7d, Math.min(2.003750834E7d, d));
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.interfaces.Coordinate
    public void setY(double d) {
        this.a = Math.max(-2.003750834E7d, Math.min(2.003750834E7d, d));
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.interfaces.Coordinate
    public void setZ(double d) {
    }

    public String toString() {
        return "x=" + this.b + ",y=" + this.a;
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.interfaces.Coordinate
    public double x() {
        return this.b;
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.interfaces.Coordinate
    public double y() {
        return this.a;
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.interfaces.Coordinate
    public double z() {
        return 0.0d;
    }
}

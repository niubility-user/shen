package com.tencent.tencentmap.mapsdk.vector.utils.a;

/* loaded from: classes9.dex */
public class f {
    public final double a;
    public final double b;

    public f(double d, double d2) {
        this.a = d;
        this.b = d2;
    }

    public boolean equals(Object obj) {
        if (obj instanceof f) {
            f fVar = (f) obj;
            return this.a == fVar.a && this.b == fVar.b;
        }
        return false;
    }

    public String toString() {
        return "Point{x=" + this.a + ", y=" + this.b + '}';
    }
}

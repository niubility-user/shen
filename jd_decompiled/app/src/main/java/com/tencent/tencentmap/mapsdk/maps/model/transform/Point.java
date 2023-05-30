package com.tencent.tencentmap.mapsdk.maps.model.transform;

/* loaded from: classes9.dex */
public class Point {
    public final double x;
    public final double y;

    public Point(double d, double d2) {
        this.x = d;
        this.y = d2;
    }

    public boolean equals(Object obj) {
        if (obj instanceof Point) {
            Point point2 = (Point) obj;
            return this.x == point2.x && this.y == point2.y;
        }
        return false;
    }

    public String toString() {
        return "Point{x=" + this.x + ", y=" + this.y + '}';
    }
}

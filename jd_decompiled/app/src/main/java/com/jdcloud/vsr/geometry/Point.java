package com.jdcloud.vsr.geometry;

/* loaded from: classes18.dex */
public class Point {
    public float x;
    public float y;

    public Point() {
        this.y = 0.0f;
        this.x = 0.0f;
    }

    public void assign(Point point2) {
        this.x = point2.x;
        this.y = point2.y;
    }

    public void set(float f2, float f3) {
        this.x = f2;
        this.y = f3;
    }

    public Point(float f2, float f3) {
        this.x = f2;
        this.y = f3;
    }

    public Point(Point point2) {
        this.x = point2.x;
        this.y = point2.y;
    }
}

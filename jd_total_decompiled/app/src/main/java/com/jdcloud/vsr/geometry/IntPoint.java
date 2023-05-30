package com.jdcloud.vsr.geometry;

/* loaded from: classes18.dex */
public class IntPoint {
    public int x;
    public int y;

    public IntPoint() {
        this.y = 0;
        this.x = 0;
    }

    public void assign(IntPoint intPoint) {
        this.x = intPoint.x;
        this.y = intPoint.y;
    }

    public void set(int i2, int i3) {
        this.x = i2;
        this.y = i3;
    }

    public IntPoint(int i2, int i3) {
        this.x = i2;
        this.y = i3;
    }

    public IntPoint(IntPoint intPoint) {
        this.x = intPoint.x;
        this.y = intPoint.y;
    }
}

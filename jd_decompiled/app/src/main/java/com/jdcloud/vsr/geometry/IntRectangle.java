package com.jdcloud.vsr.geometry;

/* loaded from: classes18.dex */
public class IntRectangle {
    public int x1;
    public int x2;
    public int y1;
    public int y2;

    public IntRectangle() {
        this.y2 = 0;
        this.x2 = 0;
        this.y1 = 0;
        this.x1 = 0;
    }

    public static float fit(int i2, int i3, int i4, int i5) {
        return i2 * i5 > i3 * i4 ? i4 / i2 : i5 / i3;
    }

    public void expand(int i2) {
        this.x1 -= i2;
        this.y1 -= i2;
        this.x2 += i2;
        this.y2 += i2;
    }

    public void expandTo(int i2, int i3) {
        if (i2 < this.x1) {
            this.x1 = i2;
        }
        if (this.x2 < i2) {
            this.x2 = i2;
        }
        if (i3 < this.y1) {
            this.y1 = i3;
        }
        if (this.y2 < i3) {
            this.y2 = i3;
        }
    }

    public int getHeight() {
        return this.y2 - this.y1;
    }

    public int getWidth() {
        return this.x2 - this.x1;
    }

    public void makeReal() {
        int i2 = this.x2;
        int i3 = this.x1;
        if (i2 < i3) {
            this.x1 = i2;
            this.x2 = i3;
        }
        int i4 = this.y2;
        int i5 = this.y1;
        if (i4 < i5) {
            this.y1 = i4;
            this.y2 = i5;
        }
    }

    public IntRectangle scale(int i2, int i3) {
        return new IntRectangle(this.x1 * i2, this.y1 * i3, this.x2 * i2, this.y2 * i3);
    }

    public String toString() {
        return "((" + Integer.toString(this.x1) + ", " + Integer.toString(this.y1) + "), (" + Integer.toString(this.x2) + ", " + Integer.toString(this.y2) + "))";
    }

    public Rectangle scale(float f2, float f3) {
        return new Rectangle(this.x1 * f2, this.y1 * f3, this.x2 * f2, this.y2 * f3);
    }

    public IntRectangle(int i2, int i3, int i4) {
        int i5 = i4 / 2;
        this.x1 = i2 - i5;
        this.y1 = i3 - i5;
        this.x2 = i2 + i5;
        this.y2 = i3 + i5;
    }

    public IntRectangle(int i2, int i3, int i4, int i5) {
        this.x1 = i2;
        this.y1 = i3;
        this.x2 = i4;
        this.y2 = i5;
    }
}

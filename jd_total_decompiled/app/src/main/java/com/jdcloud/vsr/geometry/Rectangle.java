package com.jdcloud.vsr.geometry;

/* loaded from: classes18.dex */
public class Rectangle {
    public float x1;
    public float x2;
    public float y1;
    public float y2;

    public Rectangle() {
        this.y2 = 0.0f;
        this.x2 = 0.0f;
        this.y1 = 0.0f;
        this.x1 = 0.0f;
    }

    public static float fit(float f2, float f3, float f4, float f5) {
        return f2 * f5 > f3 * f4 ? f4 / f2 : f5 / f3;
    }

    public void expandTo(float f2, float f3) {
        if (f2 < this.x1) {
            this.x1 = f2;
        }
        if (this.x2 < f2) {
            this.x2 = f2;
        }
        if (f3 < this.y1) {
            this.y1 = f3;
        }
        if (this.y2 < f3) {
            this.y2 = f3;
        }
    }

    public float getCenterX() {
        return (this.x1 + this.x2) / 2.0f;
    }

    public float getCenterY() {
        return (this.y1 + this.y2) / 2.0f;
    }

    public float getHeight() {
        return this.y2 - this.y1;
    }

    public float getWidth() {
        return this.x2 - this.x1;
    }

    public Rectangle squarizeMax() {
        Rectangle rectangle = new Rectangle();
        float f2 = this.x1;
        float f3 = this.x2;
        float f4 = (f2 + f3) / 2.0f;
        float f5 = this.y1;
        float f6 = this.y2;
        float f7 = (f5 + f6) / 2.0f;
        float abs = Math.abs(Math.max(f3 - f2, f6 - f5)) / 2.0f;
        rectangle.x1 = f4 - abs;
        rectangle.y1 = f7 - abs;
        rectangle.x2 = f4 + abs;
        rectangle.y2 = f7 + abs;
        return rectangle;
    }

    public IntRectangle toIntegerRect(int i2, int i3) {
        float f2 = i2;
        float f3 = i3;
        return new IntRectangle(Math.round(this.x1 * f2), Math.round(this.y1 * f3), Math.round(this.x2 * f2), Math.round(this.y2 * f3));
    }

    public Rectangle(float f2, float f3, float f4, float f5) {
        this.x1 = f2;
        this.y1 = f3;
        this.x2 = f4;
        this.y2 = f5;
    }

    public Rectangle(float f2, float f3, float f4) {
        float f5 = f4 / 2.0f;
        this.x1 = f2 - f5;
        this.y1 = f3 - f5;
        this.x2 = f2 + f5;
        this.y2 = f3 + f5;
    }
}

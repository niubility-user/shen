package com.jdcloud.vsr.geometry;

import android.graphics.Matrix;

/* loaded from: classes18.dex */
public class AffineMapping {
    public float a11;
    public float a12;
    public float a21;
    public float a22;
    public float x;
    public float y;

    public AffineMapping() {
        this.a11 = 1.0f;
        this.a21 = 0.0f;
        this.x = 0.0f;
        this.a12 = 0.0f;
        this.a22 = 1.0f;
        this.y = 0.0f;
    }

    public static AffineMapping composeMultiple(AffineMapping... affineMappingArr) {
        if (affineMappingArr.length == 0) {
            return null;
        }
        AffineMapping affineMapping = new AffineMapping();
        affineMapping.assign(affineMappingArr[0]);
        for (int i2 = 1; i2 < affineMappingArr.length; i2++) {
            compose(affineMapping, affineMapping, affineMappingArr[i2]);
        }
        return affineMapping;
    }

    public static AffineMapping parse(String str) {
        AffineMapping affineMapping = new AffineMapping();
        affineMapping.fromString(str);
        return affineMapping;
    }

    public void assign(AffineMapping affineMapping) {
        this.x = affineMapping.x;
        this.y = affineMapping.y;
        this.a11 = affineMapping.a11;
        this.a12 = affineMapping.a12;
        this.a21 = affineMapping.a21;
        this.a22 = affineMapping.a22;
    }

    public AffineMapping compose(AffineMapping affineMapping) {
        AffineMapping affineMapping2 = new AffineMapping();
        float f2 = this.x;
        float f3 = this.a11;
        float f4 = f2 + (affineMapping.x * f3);
        float f5 = this.a12;
        float f6 = affineMapping.y;
        affineMapping2.x = f4 + (f5 * f6);
        float f7 = this.y;
        float f8 = this.a21;
        float f9 = f7 + (affineMapping.x * f8);
        float f10 = this.a22;
        affineMapping2.y = f9 + (f6 * f10);
        float f11 = f3 * affineMapping.a11;
        float f12 = affineMapping.a21;
        affineMapping2.a11 = f11 + (f5 * f12);
        float f13 = this.a11 * affineMapping.a12;
        float f14 = affineMapping.a22;
        affineMapping2.a12 = f13 + (f5 * f14);
        affineMapping2.a21 = (f8 * affineMapping.a11) + (f12 * f10);
        affineMapping2.a22 = (this.a21 * affineMapping.a12) + (f10 * f14);
        return affineMapping2;
    }

    public float det() {
        return (this.a11 * this.a22) - (this.a12 * this.a21);
    }

    public AffineMapping flipAround(float f2, float f3, boolean z, boolean z2) {
        if (z || z2) {
            float f4 = this.a11;
            float f5 = this.a12;
            float f6 = (f4 * f2) + (f5 * f3);
            float f7 = this.a21;
            float f8 = this.a22;
            float f9 = (f7 * f2) + (f8 * f3);
            if (z) {
                this.a11 = -f4;
                this.a21 = -f7;
            }
            if (z2) {
                this.a12 = -f5;
                this.a22 = -f8;
            }
            this.x += f6 - ((this.a11 * f2) + (this.a12 * f3));
            this.y += f9 - ((this.a21 * f2) + (this.a22 * f3));
            return this;
        }
        return this;
    }

    public void fromString(String str) {
        String[] split = str.split(";");
        if (split.length == 6) {
            this.a11 = Float.parseFloat(split[0]);
            this.a12 = Float.parseFloat(split[1]);
            this.x = Float.parseFloat(split[2]);
            this.a21 = Float.parseFloat(split[3]);
            this.a22 = Float.parseFloat(split[4]);
            this.y = Float.parseFloat(split[5]);
            return;
        }
        throw new IllegalArgumentException("Affine mapping can not be parsed from expression '" + str + "'");
    }

    public Rectangle getAxesBoundingBox() {
        Rectangle rectangle = new Rectangle(this.x, this.y, 0.0f);
        rectangle.expandTo(this.a11 + this.x, this.a21 + this.y);
        rectangle.expandTo(this.a12 + this.x, this.a22 + this.y);
        rectangle.expandTo(this.a11 + this.a12 + this.x, this.a21 + this.a22 + this.y);
        return rectangle;
    }

    public AffineMapping getInverse() {
        AffineMapping affineMapping = new AffineMapping();
        if (getInverse(affineMapping)) {
            return affineMapping;
        }
        return null;
    }

    public float getOrientationDegrees() {
        return (float) ((Math.atan2(this.a11, this.a12) * 180.0d) / 3.141592653589793d);
    }

    public float getScale() {
        return (float) Math.sqrt((this.a11 * this.a22) - (this.a12 * this.a21));
    }

    public Point map(float f2, float f3) {
        return new Point(mapX(f2, f3), mapY(f2, f3));
    }

    public float[] mapPoints(float[] fArr) {
        float[] fArr2 = new float[fArr.length];
        for (int i2 = 0; i2 < fArr.length / 2; i2++) {
            int i3 = i2 * 2;
            float f2 = fArr[i3];
            int i4 = i3 + 1;
            float f3 = fArr[i4];
            fArr2[i3] = mapX(f2, f3);
            fArr2[i4] = mapY(f2, f3);
        }
        return fArr2;
    }

    public float mapX(float f2, float f3) {
        return (this.a11 * f2) + (this.a12 * f3) + this.x;
    }

    public float mapY(float f2, float f3) {
        return (this.a21 * f2) + (this.a22 * f3) + this.y;
    }

    public AffineMapping resolve(float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9, float f10, float f11) {
        float f12 = f4 - f2;
        float f13 = f5 - f3;
        float f14 = (f12 * f12) + (f13 * f13);
        float f15 = (f8 - f6) / f14;
        float f16 = (f9 - f7) / f14;
        float f17 = (f12 * f15) + (f13 * f16);
        this.a22 = f17;
        this.a11 = f17;
        float f18 = (f13 * f15) - (f12 * f16);
        this.a12 = f18;
        float f19 = -f18;
        this.a21 = f19;
        float f20 = (f17 * f17) - (f18 * f19);
        if (f20 < f10 * f10) {
            float sqrt = f10 / ((float) Math.sqrt(f20));
            this.a11 *= sqrt;
            this.a12 *= sqrt;
            this.a21 *= sqrt;
            this.a22 *= sqrt;
        } else if (f20 > f11 * f11) {
            float sqrt2 = f11 / ((float) Math.sqrt(f20));
            this.a11 *= sqrt2;
            this.a12 *= sqrt2;
            this.a21 *= sqrt2;
            this.a22 *= sqrt2;
        }
        float f21 = (f2 + f4) / 2.0f;
        float f22 = (f3 + f5) / 2.0f;
        this.x = ((f6 + f8) / 2.0f) - ((this.a11 * f21) + (this.a12 * f22));
        this.y = ((f7 + f9) / 2.0f) - ((this.a21 * f21) + (this.a22 * f22));
        return this;
    }

    public AffineMapping rotate(float f2) {
        double d = f2;
        Double.isNaN(d);
        double d2 = (float) ((d * 3.141592653589793d) / 180.0d);
        float sin = (float) Math.sin(d2);
        float cos = (float) Math.cos(d2);
        float f3 = this.a11;
        float f4 = this.a12;
        float f5 = (cos * f3) + (sin * f4);
        float f6 = this.a21;
        float f7 = this.a22;
        float f8 = -sin;
        this.a12 = (f3 * f8) + (f4 * cos);
        this.a22 = (f8 * f6) + (cos * f7);
        this.a11 = f5;
        this.a21 = (cos * f6) + (sin * f7);
        return this;
    }

    public AffineMapping rotateAround(float f2, float f3, float f4) {
        float f5 = (this.a11 * f2) + (this.a12 * f3);
        float f6 = (this.a21 * f2) + (this.a22 * f3);
        rotate(f4);
        this.x += f5 - ((this.a11 * f2) + (this.a12 * f3));
        this.y += f6 - ((this.a21 * f2) + (this.a22 * f3));
        return this;
    }

    public AffineMapping scale(float f2) {
        this.a11 *= f2;
        this.a12 *= f2;
        this.a21 *= f2;
        this.a22 *= f2;
        return this;
    }

    public AffineMapping scaleAround(float f2, float f3, float f4, float f5) {
        float f6 = this.a11;
        float f7 = this.a12;
        float f8 = (f6 * f2) + (f7 * f3);
        float f9 = this.a21;
        float f10 = this.a22;
        float f11 = f6 * f4;
        this.a11 = f11;
        float f12 = f7 * f5;
        this.a12 = f12;
        float f13 = f9 * f4;
        this.a21 = f13;
        float f14 = f10 * f5;
        this.a22 = f14;
        this.x += f8 - ((f11 * f2) + (f12 * f3));
        this.y += ((f9 * f2) + (f10 * f3)) - ((f13 * f2) + (f14 * f3));
        return this;
    }

    public AffineMapping setCenterPosition(float f2, float f3) {
        float f4 = this.x;
        this.x = f4 + (f2 - (((this.a11 + this.a12) * 0.5f) + f4));
        float f5 = this.y;
        this.y = f5 + (f3 - (((this.a21 + this.a22) * 0.5f) + f5));
        return this;
    }

    public AffineMapping setFromMatrix(Matrix matrix) {
        float[] fArr = {0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 1.0f};
        matrix.mapPoints(fArr);
        float f2 = fArr[0];
        this.x = f2;
        float f3 = fArr[1];
        this.y = f3;
        this.a11 = fArr[2] - f2;
        this.a21 = fArr[3] - f3;
        this.a12 = fArr[4] - f2;
        this.a22 = fArr[5] - f3;
        return this;
    }

    public AffineMapping setIdentity() {
        this.a22 = 1.0f;
        this.a11 = 1.0f;
        this.y = 0.0f;
        this.x = 0.0f;
        this.a21 = 0.0f;
        this.a12 = 0.0f;
        return this;
    }

    public AffineMapping setTranslation(float f2, float f3) {
        this.x = f2;
        this.y = f3;
        return this;
    }

    public AffineMapping skew(float f2, float f3) {
        float tan = (float) Math.tan(Math.toRadians(f2));
        float tan2 = (float) Math.tan(Math.toRadians(f3));
        float f4 = this.a11;
        float f5 = (tan * tan2) + 1.0f;
        float f6 = this.a12;
        float f7 = this.a21;
        float f8 = this.a22;
        this.a22 = (f7 * tan) + f8;
        this.a11 = (f4 * f5) + (f6 * tan2);
        this.a12 = (f4 * tan) + f6;
        this.a21 = (f5 * f7) + (tan2 * f8);
        return this;
    }

    public AffineMapping skewAround(float f2, float f3, float f4, float f5) {
        float f6 = (this.a11 * f2) + (this.a12 * f3);
        float f7 = (this.a21 * f2) + (this.a22 * f3);
        skew(f4, f5);
        this.x += f6 - ((this.a11 * f2) + (this.a12 * f3));
        this.y += f7 - ((this.a21 * f2) + (this.a22 * f3));
        return this;
    }

    public String toString() {
        return Float.toString(this.a11) + ";" + Float.toString(this.a12) + ";" + Float.toString(this.x) + ";" + Float.toString(this.a21) + ";" + Float.toString(this.a22) + ";" + Float.toString(this.y);
    }

    public AffineMapping translate(float f2, float f3) {
        this.x += f2;
        this.y += f3;
        return this;
    }

    public Point map(Point point2) {
        return new Point(mapX(point2.x, point2.y), mapY(point2.x, point2.y));
    }

    public boolean getInverse(AffineMapping affineMapping) {
        float f2 = this.a11;
        float f3 = this.a22;
        float f4 = this.a12;
        float f5 = this.a21;
        float f6 = (f2 * f3) - (f4 * f5);
        if (f6 == 0.0f) {
            return false;
        }
        float f7 = f3 / f6;
        float f8 = f2 / f6;
        float f9 = (-f4) / f6;
        float f10 = (-f5) / f6;
        affineMapping.a11 = f7;
        affineMapping.a12 = f9;
        affineMapping.a21 = f10;
        affineMapping.a22 = f8;
        float f11 = this.x;
        float f12 = this.y;
        affineMapping.y = -((f10 * f11) + (f8 * f12));
        affineMapping.x = -((f7 * f11) + (f9 * f12));
        return true;
    }

    public AffineMapping(AffineMapping affineMapping) {
        this.a11 = affineMapping.a11;
        this.a12 = affineMapping.a12;
        this.a21 = affineMapping.a21;
        this.a22 = affineMapping.a22;
        this.x = affineMapping.x;
        this.y = affineMapping.y;
    }

    public float[] mapPoints(int[] iArr) {
        float[] fArr = new float[iArr.length];
        for (int i2 = 0; i2 < iArr.length / 2; i2++) {
            int i3 = i2 * 2;
            float f2 = iArr[i3];
            int i4 = i3 + 1;
            float f3 = iArr[i4];
            fArr[i3] = mapX(f2, f3);
            fArr[i4] = mapY(f2, f3);
        }
        return fArr;
    }

    public static void compose(AffineMapping affineMapping, AffineMapping affineMapping2, AffineMapping affineMapping3) {
        float f2 = affineMapping2.x;
        float f3 = affineMapping2.a11;
        float f4 = affineMapping3.x;
        float f5 = affineMapping2.a12;
        float f6 = affineMapping3.y;
        float f7 = affineMapping2.y;
        float f8 = affineMapping2.a21;
        float f9 = affineMapping2.a22;
        affineMapping.y = f7 + (f4 * f8) + (f6 * f9);
        affineMapping.x = f2 + (f3 * f4) + (f5 * f6);
        float f10 = affineMapping3.a11;
        float f11 = affineMapping3.a21;
        float f12 = affineMapping3.a12;
        float f13 = affineMapping3.a22;
        affineMapping.a22 = (f8 * f12) + (f9 * f13);
        affineMapping.a11 = (f3 * f10) + (f5 * f11);
        affineMapping.a12 = (f3 * f12) + (f5 * f13);
        affineMapping.a21 = (f10 * f8) + (f11 * f9);
    }

    public AffineMapping scaleAround(float f2, float f3, float f4) {
        return scaleAround(f2, f3, f4, f4);
    }

    public AffineMapping(Rectangle rectangle) {
        this.x = rectangle.x1;
        this.y = rectangle.y1;
        this.a11 = rectangle.getWidth();
        this.a22 = rectangle.getHeight();
        this.a21 = 0.0f;
        this.a12 = 0.0f;
    }
}

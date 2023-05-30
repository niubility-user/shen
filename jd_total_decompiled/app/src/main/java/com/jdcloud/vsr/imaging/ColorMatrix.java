package com.jdcloud.vsr.imaging;

import com.jdcloud.vsr.JDTObject;

/* loaded from: classes18.dex */
public class ColorMatrix extends JDTObject {
    public ColorMatrix() {
        super(newColorMatrix());
    }

    private static native void assign(long j2, long j3);

    private native float getElement(long j2, int i2, int i3);

    private static native void multiply(long j2, long j3, long j4);

    private static native long newColorMatrix();

    private static native long newColorMatrix(float f2, float f3, float f4);

    private static native long newColorMatrix(float f2, float f3, float f4, float f5, float f6);

    private native void setElement(long j2, int i2, int i3, float f2);

    public float get(int i2, int i3) {
        return getElement(this.handle, i2, i3);
    }

    public void set(int i2, int i3, float f2) {
        setElement(this.handle, i2, i3, f2);
    }

    public ColorMatrix(float f2, float f3, float f4) {
        super(newColorMatrix(f2, f3, f4));
    }

    public ColorMatrix(Color color, float f2, float f3) {
        super(newColorMatrix(color.r, color.f7275g, color.b, f2, f3));
    }
}

package com.jdcloud.vsr.utils;

import com.jdcloud.vsr.JDTObject;
import com.jdcloud.vsr.imaging.ColorMatrix;

/* loaded from: classes18.dex */
public class VariablesBundle extends JDTObject {
    /* JADX INFO: Access modifiers changed from: protected */
    public VariablesBundle(long j2) {
        super(j2);
    }

    private native void setFloat1(long j2, String str, float f2);

    private native void setFloat2(long j2, String str, float f2, float f3);

    private native void setFloat3(long j2, String str, float f2, float f3, float f4);

    private native void setFloat4(long j2, String str, float f2, float f3, float f4, float f5);

    private native void setFloatMatrix(long j2, String str, float[] fArr);

    private native void setFloatMatrixFromColorMatrix(long j2, String str, ColorMatrix colorMatrix);

    private native void setInteger1(long j2, String str, int i2);

    private native void setInteger2(long j2, String str, int i2, int i3);

    private native void setInteger3(long j2, String str, int i2, int i3, int i4);

    private native void setInteger4(long j2, String str, int i2, int i3, int i4, int i5);

    public void setColorMatrix(String str, ColorMatrix colorMatrix) {
        setFloatMatrixFromColorMatrix(this.handle, str, colorMatrix);
    }

    public void setFloat(String str, float f2) {
        setFloat1(this.handle, str, f2);
    }

    public void setFloatMatrix(String str, float[] fArr) {
        setFloatMatrix(this.handle, str, fArr);
    }

    public void setInteger(String str, int i2) {
        setInteger1(this.handle, str, i2);
    }

    public void setFloat(String str, float f2, float f3) {
        setFloat2(this.handle, str, f2, f3);
    }

    public void setInteger(String str, int i2, int i3) {
        setInteger2(this.handle, str, i2, i3);
    }

    public void setFloat(String str, float f2, float f3, float f4) {
        setFloat3(this.handle, str, f2, f3, f4);
    }

    public void setInteger(String str, int i2, int i3, int i4) {
        setInteger3(this.handle, str, i2, i3, i4);
    }

    public void setFloat(String str, float f2, float f3, float f4, float f5) {
        setFloat4(this.handle, str, f2, f3, f4, f5);
    }

    public void setInteger(String str, int i2, int i3, int i4, int i5) {
        setInteger4(this.handle, str, i2, i3, i4, i5);
    }
}

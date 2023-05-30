package com.tencent.mapsdk.internal;

import android.opengl.Matrix;
import com.jd.dynamic.DYConstants;

/* loaded from: classes9.dex */
public class c6 {
    public float a;
    public float b;

    /* renamed from: c  reason: collision with root package name */
    public float f16359c;
    public float d = 1.0f;

    public c6() {
    }

    public c6(float f2, float f3, float f4) {
        this.a = f2;
        this.b = f3;
        this.f16359c = f4;
    }

    public c6 a(float[] fArr) {
        float[] fArr2 = new float[4];
        Matrix.multiplyMV(fArr2, 0, fArr, 0, new float[]{this.a, this.b, this.f16359c, this.d}, 0);
        return new c6(fArr2[0] / fArr2[3], fArr2[1] / fArr2[3], fArr2[2] / fArr2[3]);
    }

    public boolean equals(Object obj) {
        if (obj instanceof c6) {
            c6 c6Var = (c6) obj;
            return this.a == c6Var.a && this.b == c6Var.b && this.f16359c == c6Var.f16359c;
        }
        return false;
    }

    public String toString() {
        return this.a + DYConstants.DY_REGEX_COMMA + this.b + DYConstants.DY_REGEX_COMMA + this.f16359c;
    }
}

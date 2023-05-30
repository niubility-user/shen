package com.tencent.mapsdk.internal;

import javax.microedition.khronos.opengles.GL10;

/* loaded from: classes9.dex */
public class u7 extends t7 {

    /* renamed from: n  reason: collision with root package name */
    private long f17304n;
    private long o;

    public u7(float f2, float f3, float f4, float f5, long j2, long j3) {
        super(f2, f3, f4, f5, j2 + j3);
        this.f17304n = j2;
        this.o = j3;
    }

    @Override // com.tencent.mapsdk.internal.t7, com.tencent.mapsdk.internal.q7
    public void a(GL10 gl10, long j2) {
        float f2;
        float f3;
        float f4 = this.f17269k;
        float f5 = this.f17268j;
        float f6 = f4 - f5;
        float f7 = this.f17271m;
        float f8 = this.f17270l;
        float f9 = f7 - f8;
        long j3 = this.f17304n;
        if (j2 < j3) {
            float f10 = (float) j2;
            float f11 = (float) j3;
            f2 = f5 + ((f6 * f10) / f11);
            f3 = f8 + ((f9 * f10) / f11);
        } else {
            float f12 = (float) (j2 - j3);
            float f13 = (float) this.o;
            f2 = f4 - ((f6 * f12) / f13);
            f3 = f7 - ((f9 * f12) / f13);
        }
        gl10.glScalef(f2, f3, 1.0f);
    }
}

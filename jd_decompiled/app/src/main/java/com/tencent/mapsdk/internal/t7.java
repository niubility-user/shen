package com.tencent.mapsdk.internal;

import javax.microedition.khronos.opengles.GL10;

/* loaded from: classes9.dex */
public class t7 extends q7 {

    /* renamed from: j  reason: collision with root package name */
    public float f17268j;

    /* renamed from: k  reason: collision with root package name */
    public float f17269k;

    /* renamed from: l  reason: collision with root package name */
    public float f17270l;

    /* renamed from: m  reason: collision with root package name */
    public float f17271m;

    public t7(float f2, float f3, float f4, float f5, long j2) {
        super(j2);
        this.f17268j = f2;
        this.f17269k = f3;
        this.f17270l = f4;
        this.f17271m = f5;
    }

    @Override // com.tencent.mapsdk.internal.q7
    public void a(GL10 gl10, long j2) {
        float f2 = this.f17269k;
        float f3 = this.f17268j;
        float f4 = this.f17271m;
        float f5 = this.f17270l;
        float f6 = (float) j2;
        float f7 = (float) this.a;
        gl10.glScalef(f3 + (((f2 - f3) * f6) / f7), f5 + (((f4 - f5) * f6) / f7), 1.0f);
    }
}

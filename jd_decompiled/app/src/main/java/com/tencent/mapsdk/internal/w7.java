package com.tencent.mapsdk.internal;

import javax.microedition.khronos.opengles.GL10;

/* loaded from: classes9.dex */
public class w7 extends q7 {

    /* renamed from: j  reason: collision with root package name */
    private float f17430j;

    /* renamed from: k  reason: collision with root package name */
    private float f17431k;

    /* renamed from: l  reason: collision with root package name */
    private float f17432l;

    /* renamed from: m  reason: collision with root package name */
    private float f17433m;

    public w7(float f2, float f3, float f4, float f5, long j2) {
        super(j2);
        this.f17430j = f2;
        this.f17431k = f3;
        this.f17432l = f4;
        this.f17433m = f5;
    }

    @Override // com.tencent.mapsdk.internal.q7
    public void a(GL10 gl10, long j2) {
        float f2 = this.f17431k;
        float f3 = this.f17430j;
        float f4 = this.f17433m;
        float f5 = this.f17432l;
        float f6 = (float) j2;
        float f7 = (float) this.a;
        gl10.glTranslatef(f3 + (((f2 - f3) * f6) / f7), f5 + (((f4 - f5) * f6) / f7), 0.0f);
    }
}

package com.tencent.mapsdk.internal;

import javax.microedition.khronos.opengles.GL10;

/* loaded from: classes9.dex */
public class r7 extends q7 {

    /* renamed from: j  reason: collision with root package name */
    private float f17191j;

    /* renamed from: k  reason: collision with root package name */
    private float f17192k;

    public r7(float f2, float f3, long j2) {
        super(j2);
        this.f17191j = f2;
        this.f17192k = f3;
    }

    @Override // com.tencent.mapsdk.internal.q7
    public void a(GL10 gl10, long j2) {
        float f2 = this.f17192k;
        float f3 = this.f17191j;
        float f4 = f3 + (((f2 - f3) * ((float) j2)) / ((float) this.a));
        gl10.glColor4f(f4, f4, f4, f4);
    }
}

package com.tencent.mapsdk.internal;

import android.view.animation.Interpolator;
import com.tencent.mapsdk.internal.z7;

/* loaded from: classes9.dex */
public class d8 extends z7 {

    /* renamed from: i  reason: collision with root package name */
    private float f16399i;

    /* renamed from: j  reason: collision with root package name */
    private float f16400j;

    /* renamed from: k  reason: collision with root package name */
    private float f16401k;

    /* renamed from: l  reason: collision with root package name */
    private float f16402l;

    public d8(float f2, float f3, float f4, float f5) {
        this.f16399i = 0.0f;
        this.f16400j = 0.0f;
        this.f16401k = 0.0f;
        this.f16402l = 0.0f;
        this.f16399i = f2;
        this.f16400j = f3;
        this.f16401k = f4;
        this.f16402l = f5;
    }

    @Override // com.tencent.mapsdk.internal.z7
    public void a(float f2, Interpolator interpolator) {
        if (f2 < 0.0f) {
            return;
        }
        float f3 = this.f16400j - this.f16399i;
        float interpolation = interpolator.getInterpolation(f2);
        float f4 = this.f16399i + (f3 * interpolation);
        float f5 = this.f16401k + ((this.f16402l - this.f16401k) * interpolation);
        z7.b bVar = this.f17557h;
        if (bVar != null) {
            bVar.setScale(f4, f5);
        }
    }
}

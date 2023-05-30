package com.tencent.mapsdk.internal;

import android.view.animation.Interpolator;
import com.tencent.mapsdk.internal.z7;

/* loaded from: classes9.dex */
public class y7 extends z7 {

    /* renamed from: i  reason: collision with root package name */
    private float f17488i;

    /* renamed from: j  reason: collision with root package name */
    private float f17489j;

    public y7(float f2, float f3) {
        this.f17488i = 0.0f;
        this.f17489j = 0.0f;
        this.f17488i = f2;
        this.f17489j = f3;
    }

    @Override // com.tencent.mapsdk.internal.z7
    public void a(float f2, Interpolator interpolator) {
        float interpolation = this.f17488i + ((this.f17489j - this.f17488i) * interpolator.getInterpolation(f2));
        z7.b bVar = this.f17557h;
        if (bVar != null) {
            bVar.setAlpha(interpolation);
        }
    }
}

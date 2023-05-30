package com.tencent.mapsdk.internal;

import android.view.animation.Interpolator;
import com.tencent.tencentmap.mapsdk.maps.model.IScaleAnimation;

/* loaded from: classes9.dex */
public class m7 extends i7 implements IScaleAnimation {
    public m7(float f2, float f3, float f4, float f5) {
        if (this.a == null) {
            this.a = new d8(f2, f3, f4, f5);
        }
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.model.Animation
    public void setDuration(long j2) {
        z7 z7Var = this.a;
        if (z7Var == null) {
            return;
        }
        z7Var.a(j2);
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.model.Animation
    public void setInterpolator(Interpolator interpolator) {
        z7 z7Var = this.a;
        if (z7Var == null || interpolator == null) {
            return;
        }
        z7Var.a(interpolator);
    }
}

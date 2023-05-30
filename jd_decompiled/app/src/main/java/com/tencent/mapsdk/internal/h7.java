package com.tencent.mapsdk.internal;

import android.view.animation.Interpolator;
import com.tencent.tencentmap.mapsdk.maps.model.IAlphaAnimation;

/* loaded from: classes9.dex */
public class h7 extends i7 implements IAlphaAnimation {
    public h7(float f2, float f3) {
        if (this.a == null) {
            this.a = new y7(f2, f3);
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

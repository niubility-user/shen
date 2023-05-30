package com.tencent.mapsdk.internal;

import android.view.animation.Interpolator;
import com.tencent.tencentmap.mapsdk.maps.model.Animation;
import com.tencent.tencentmap.mapsdk.maps.model.IAnimationSet;

/* loaded from: classes9.dex */
public class j7 extends i7 implements IAnimationSet {
    public j7(boolean z) {
        if (this.a == null) {
            this.a = new a8(z);
        }
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.model.IAnimationSet
    public boolean addAnimation(Animation animation) {
        z7 z7Var;
        z7 z7Var2;
        if (animation == null || !(animation instanceof i7) || (z7Var = ((i7) animation).a) == null || (z7Var2 = this.a) == null) {
            return false;
        }
        ((a8) z7Var2).a(z7Var);
        return true;
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.model.IAnimationSet
    public void cleanAnimation() {
        z7 z7Var = this.a;
        if (z7Var == null) {
            return;
        }
        ((a8) z7Var).i();
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

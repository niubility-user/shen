package com.jingdong.app.mall.home.n.h;

import android.animation.Animator;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;

/* loaded from: classes4.dex */
public class f {
    private ValueAnimator a;

    public ValueAnimator a() {
        return this.a;
    }

    public f b(float... fArr) {
        this.a = ValueAnimator.ofFloat(fArr);
        return this;
    }

    public f c(Animator.AnimatorListener animatorListener) {
        if (animatorListener != null) {
            this.a.addListener(animatorListener);
        }
        return this;
    }

    public f d(long j2) {
        this.a.setDuration(j2);
        return this;
    }

    public f e(TimeInterpolator timeInterpolator) {
        this.a.setInterpolator(timeInterpolator);
        return this;
    }

    public f f(long j2) {
        this.a.setStartDelay(j2);
        return this;
    }

    public f g(ValueAnimator.AnimatorUpdateListener animatorUpdateListener) {
        this.a.addUpdateListener(animatorUpdateListener);
        return this;
    }
}

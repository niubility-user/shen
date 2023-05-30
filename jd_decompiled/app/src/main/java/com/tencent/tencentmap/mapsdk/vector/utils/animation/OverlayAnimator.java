package com.tencent.tencentmap.mapsdk.vector.utils.animation;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ValueAnimator;

/* loaded from: classes9.dex */
public abstract class OverlayAnimator {
    public Object a;
    public long b;

    /* renamed from: c  reason: collision with root package name */
    public AnimatorSet f18015c = new AnimatorSet();

    public OverlayAnimator(Object obj, long j2) {
        this.a = obj;
        this.b = j2;
    }

    public void addAnimatorListener(Animator.AnimatorListener animatorListener) {
        this.f18015c.addListener(animatorListener);
    }

    public void cancelAnimation() {
        synchronized (this) {
            this.f18015c.cancel();
        }
    }

    public abstract ValueAnimator createSegmentAnimator(int i2);

    public void endAnimation() {
        synchronized (this) {
            this.f18015c.end();
        }
    }

    public AnimatorSet getAnimatorSet() {
        return this.f18015c;
    }

    public long getDuration() {
        return this.b;
    }

    public Object getObject() {
        return this.a;
    }

    public void setAnimatorSet(AnimatorSet animatorSet) {
        this.f18015c = animatorSet;
    }

    public void setDuration(long j2) {
        this.b = j2;
    }

    public void setObject(Object obj) {
        this.a = obj;
    }

    public void startAnimation() {
        synchronized (this) {
            if (!this.f18015c.isRunning()) {
                this.f18015c.start();
            }
        }
    }
}

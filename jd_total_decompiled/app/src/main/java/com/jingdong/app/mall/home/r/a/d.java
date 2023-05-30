package com.jingdong.app.mall.home.r.a;

import android.animation.Animator;

/* loaded from: classes4.dex */
public abstract class d implements Animator.AnimatorListener {
    private boolean isCancel = false;

    @Override // android.animation.Animator.AnimatorListener
    public void onAnimationCancel(Animator animator) {
        this.isCancel = true;
    }

    @Override // android.animation.Animator.AnimatorListener
    public void onAnimationEnd(Animator animator) {
        onEnd(animator, this.isCancel);
    }

    @Override // android.animation.Animator.AnimatorListener
    public void onAnimationRepeat(Animator animator) {
    }

    @Override // android.animation.Animator.AnimatorListener
    public void onAnimationStart(Animator animator) {
        this.isCancel = false;
    }

    protected abstract void onEnd(Animator animator, boolean z);
}

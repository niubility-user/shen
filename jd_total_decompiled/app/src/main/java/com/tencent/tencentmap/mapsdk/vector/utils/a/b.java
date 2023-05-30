package com.tencent.tencentmap.mapsdk.vector.utils.a;

import android.animation.Animator;
import com.tencent.tencentmap.mapsdk.vector.utils.animation.MarkerTranslateAnimator;

/* loaded from: classes9.dex */
public class b implements Animator.AnimatorListener {
    public final /* synthetic */ MarkerTranslateAnimator a;

    public b(MarkerTranslateAnimator markerTranslateAnimator) {
        this.a = markerTranslateAnimator;
    }

    @Override // android.animation.Animator.AnimatorListener
    public void onAnimationCancel(Animator animator) {
        this.a.f18014j = false;
    }

    @Override // android.animation.Animator.AnimatorListener
    public void onAnimationEnd(Animator animator) {
        this.a.f18014j = false;
    }

    @Override // android.animation.Animator.AnimatorListener
    public void onAnimationRepeat(Animator animator) {
    }

    @Override // android.animation.Animator.AnimatorListener
    public void onAnimationStart(Animator animator) {
    }
}

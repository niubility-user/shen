package com.jingdong.common.baseRecycleAdapter.animation;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.view.View;

/* loaded from: classes5.dex */
public class SlideInLeftAnimation implements BaseAnimation {
    @Override // com.jingdong.common.baseRecycleAdapter.animation.BaseAnimation
    public Animator[] getAnimators(View view) {
        return new Animator[]{ObjectAnimator.ofFloat(view, "translationX", -view.getRootView().getWidth(), 0.0f)};
    }
}

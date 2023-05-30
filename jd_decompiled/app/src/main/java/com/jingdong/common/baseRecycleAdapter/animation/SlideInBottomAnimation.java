package com.jingdong.common.baseRecycleAdapter.animation;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.view.View;

/* loaded from: classes5.dex */
public class SlideInBottomAnimation implements BaseAnimation {
    @Override // com.jingdong.common.baseRecycleAdapter.animation.BaseAnimation
    public Animator[] getAnimators(View view) {
        return new Animator[]{ObjectAnimator.ofFloat(view, "translationY", view.getMeasuredHeight(), 0.0f)};
    }
}

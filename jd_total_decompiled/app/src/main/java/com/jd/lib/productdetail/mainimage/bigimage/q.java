package com.jd.lib.productdetail.mainimage.bigimage;

import android.animation.ValueAnimator;
import android.view.View;
import androidx.core.view.ViewCompat;

/* loaded from: classes15.dex */
public class q implements ValueAnimator.AnimatorUpdateListener {

    /* renamed from: g */
    public final /* synthetic */ View f4623g;

    public q(PdMDropDownViewPager pdMDropDownViewPager, View view) {
        this.f4623g = view;
    }

    @Override // android.animation.ValueAnimator.AnimatorUpdateListener
    public void onAnimationUpdate(ValueAnimator valueAnimator) {
        View view = this.f4623g;
        if (view != null) {
            ViewCompat.setX(view, ((Float) valueAnimator.getAnimatedValue()).floatValue());
        }
    }
}

package com.jd.lib.productdetail.mainimage.bigimage;

import android.animation.ValueAnimator;
import android.view.View;
import androidx.core.view.ViewCompat;

/* loaded from: classes15.dex */
public class t implements ValueAnimator.AnimatorUpdateListener {

    /* renamed from: g */
    public final /* synthetic */ View f4627g;

    public t(PdMDropDownViewPager pdMDropDownViewPager, View view) {
        this.f4627g = view;
    }

    @Override // android.animation.ValueAnimator.AnimatorUpdateListener
    public void onAnimationUpdate(ValueAnimator valueAnimator) {
        View view = this.f4627g;
        if (view != null) {
            ViewCompat.setScaleY(view, ((Float) valueAnimator.getAnimatedValue()).floatValue());
        }
    }
}

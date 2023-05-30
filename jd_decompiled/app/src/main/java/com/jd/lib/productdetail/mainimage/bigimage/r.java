package com.jd.lib.productdetail.mainimage.bigimage;

import android.animation.ValueAnimator;
import android.view.View;
import androidx.core.view.ViewCompat;

/* loaded from: classes15.dex */
public class r implements ValueAnimator.AnimatorUpdateListener {

    /* renamed from: g */
    public final /* synthetic */ View f4624g;

    public r(PdMDropDownViewPager pdMDropDownViewPager, View view) {
        this.f4624g = view;
    }

    @Override // android.animation.ValueAnimator.AnimatorUpdateListener
    public void onAnimationUpdate(ValueAnimator valueAnimator) {
        View view = this.f4624g;
        if (view != null) {
            ViewCompat.setY(view, ((Float) valueAnimator.getAnimatedValue()).floatValue());
        }
    }
}

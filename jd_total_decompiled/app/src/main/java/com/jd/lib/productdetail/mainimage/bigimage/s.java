package com.jd.lib.productdetail.mainimage.bigimage;

import android.animation.ValueAnimator;
import android.view.View;
import androidx.core.view.ViewCompat;

/* loaded from: classes15.dex */
public class s implements ValueAnimator.AnimatorUpdateListener {

    /* renamed from: g  reason: collision with root package name */
    public final /* synthetic */ View f4625g;

    /* renamed from: h  reason: collision with root package name */
    public final /* synthetic */ PdMDropDownViewPager f4626h;

    public s(PdMDropDownViewPager pdMDropDownViewPager, View view) {
        this.f4626h = pdMDropDownViewPager;
        this.f4625g = view;
    }

    @Override // android.animation.ValueAnimator.AnimatorUpdateListener
    public void onAnimationUpdate(ValueAnimator valueAnimator) {
        Float f2 = (Float) valueAnimator.getAnimatedValue();
        View view = this.f4625g;
        if (view != null) {
            ViewCompat.setScaleX(view, f2.floatValue());
        }
        this.f4626h.g(f2.floatValue());
    }
}

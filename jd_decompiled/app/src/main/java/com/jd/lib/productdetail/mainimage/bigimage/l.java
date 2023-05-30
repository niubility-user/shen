package com.jd.lib.productdetail.mainimage.bigimage;

import android.animation.ValueAnimator;
import android.view.View;
import androidx.core.view.ViewCompat;

/* loaded from: classes15.dex */
public class l implements ValueAnimator.AnimatorUpdateListener {

    /* renamed from: g  reason: collision with root package name */
    public final /* synthetic */ View f4613g;

    /* renamed from: h  reason: collision with root package name */
    public final /* synthetic */ PdMDropDownViewPager f4614h;

    public l(PdMDropDownViewPager pdMDropDownViewPager, View view) {
        this.f4614h = pdMDropDownViewPager;
        this.f4613g = view;
    }

    @Override // android.animation.ValueAnimator.AnimatorUpdateListener
    public void onAnimationUpdate(ValueAnimator valueAnimator) {
        Float f2 = (Float) valueAnimator.getAnimatedValue();
        View view = this.f4613g;
        if (view != null) {
            ViewCompat.setScaleX(view, f2.floatValue());
        }
        PdMDropDownViewPager pdMDropDownViewPager = this.f4614h;
        pdMDropDownViewPager.g(pdMDropDownViewPager.C * f2.floatValue());
    }
}

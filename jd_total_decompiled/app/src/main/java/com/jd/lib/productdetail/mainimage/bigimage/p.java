package com.jd.lib.productdetail.mainimage.bigimage;

import android.animation.ValueAnimator;

/* loaded from: classes15.dex */
public class p implements ValueAnimator.AnimatorUpdateListener {

    /* renamed from: g */
    public final /* synthetic */ float f4620g;

    /* renamed from: h */
    public final /* synthetic */ float f4621h;

    /* renamed from: i */
    public final /* synthetic */ PdMDropDownViewPager f4622i;

    public p(PdMDropDownViewPager pdMDropDownViewPager, float f2, float f3) {
        this.f4622i = pdMDropDownViewPager;
        this.f4620g = f2;
        this.f4621h = f3;
    }

    @Override // android.animation.ValueAnimator.AnimatorUpdateListener
    public void onAnimationUpdate(ValueAnimator valueAnimator) {
        float floatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
        PdMDropDownViewPager pdMDropDownViewPager = this.f4622i;
        float f2 = pdMDropDownViewPager.f4590j;
        float f3 = (floatValue - f2) / (this.f4620g - f2);
        float f4 = this.f4621h;
        float f5 = pdMDropDownViewPager.f4591k;
        pdMDropDownViewPager.a(floatValue, (f3 * (f4 - f5)) + f5);
        PdMDropDownViewPager pdMDropDownViewPager2 = this.f4622i;
        if (floatValue == pdMDropDownViewPager2.f4590j) {
            pdMDropDownViewPager2.f4591k = 0.0f;
            pdMDropDownViewPager2.f4590j = 0.0f;
            pdMDropDownViewPager2.f4588h = 0;
        }
    }
}

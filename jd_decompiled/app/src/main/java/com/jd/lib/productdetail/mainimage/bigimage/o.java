package com.jd.lib.productdetail.mainimage.bigimage;

import android.animation.ValueAnimator;
import com.jd.lib.productdetail.mainimage.bigimage.PdBigImageActivity;
import com.jd.lib.productdetail.mainimage.bigimage.PdMDropDownViewPager;

/* loaded from: classes15.dex */
public class o implements ValueAnimator.AnimatorUpdateListener {

    /* renamed from: g */
    public final /* synthetic */ float f4617g;

    /* renamed from: h */
    public final /* synthetic */ float f4618h;

    /* renamed from: i */
    public final /* synthetic */ PdMDropDownViewPager f4619i;

    public o(PdMDropDownViewPager pdMDropDownViewPager, float f2, float f3) {
        this.f4619i = pdMDropDownViewPager;
        this.f4617g = f2;
        this.f4618h = f3;
    }

    @Override // android.animation.ValueAnimator.AnimatorUpdateListener
    public void onAnimationUpdate(ValueAnimator valueAnimator) {
        float floatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
        PdMDropDownViewPager pdMDropDownViewPager = this.f4619i;
        float f2 = pdMDropDownViewPager.f4591k;
        float f3 = (floatValue - f2) / (this.f4617g - f2);
        float f4 = this.f4618h;
        float f5 = pdMDropDownViewPager.f4590j;
        pdMDropDownViewPager.a((f3 * (f4 - f5)) + f5, floatValue);
        PdMDropDownViewPager pdMDropDownViewPager2 = this.f4619i;
        if (floatValue != pdMDropDownViewPager2.f4591k || pdMDropDownViewPager2.f4588h == 0) {
            return;
        }
        pdMDropDownViewPager2.f4591k = 0.0f;
        pdMDropDownViewPager2.f4590j = 0.0f;
        pdMDropDownViewPager2.f4588h = 0;
        PdMDropDownViewPager.c cVar = pdMDropDownViewPager2.f4594n;
        if (cVar != null) {
            ((PdBigImageActivity.c) cVar).a(false);
        }
    }
}

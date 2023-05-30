package com.jd.lib.productdetail.mainimage.bigimage;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import com.jd.lib.productdetail.mainimage.bigimage.PdBigImageActivity;
import com.jd.lib.productdetail.mainimage.bigimage.PdMDropDownViewPager;

/* loaded from: classes15.dex */
public class u extends AnimatorListenerAdapter {

    /* renamed from: g  reason: collision with root package name */
    public final /* synthetic */ PdMDropDownViewPager f4628g;

    public u(PdMDropDownViewPager pdMDropDownViewPager) {
        this.f4628g = pdMDropDownViewPager;
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public void onAnimationEnd(Animator animator) {
        animator.removeAllListeners();
        this.f4628g.A = true;
        PdMDropDownViewPager.c cVar = this.f4628g.f4594n;
        if (cVar != null) {
            ((PdBigImageActivity.c) cVar).a(true);
        }
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public void onAnimationStart(Animator animator) {
        this.f4628g.A = false;
        PdMDropDownViewPager.c cVar = this.f4628g.f4594n;
        if (cVar != null) {
            ((PdBigImageActivity.c) cVar).d(true);
        }
    }
}

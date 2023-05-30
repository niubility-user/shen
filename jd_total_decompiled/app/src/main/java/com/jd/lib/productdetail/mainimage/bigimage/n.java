package com.jd.lib.productdetail.mainimage.bigimage;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import com.jd.lib.productdetail.mainimage.bigimage.PdBigImageActivity;
import com.jd.lib.productdetail.mainimage.bigimage.PdMDropDownViewPager;

/* loaded from: classes15.dex */
public class n extends AnimatorListenerAdapter {

    /* renamed from: g */
    public final /* synthetic */ PdMDropDownViewPager f4616g;

    public n(PdMDropDownViewPager pdMDropDownViewPager, String str) {
        this.f4616g = pdMDropDownViewPager;
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public void onAnimationEnd(Animator animator) {
        animator.removeAllListeners();
        PdMDropDownViewPager.c cVar = this.f4616g.f4594n;
        if (cVar != null) {
            PdBigImageActivity pdBigImageActivity = PdBigImageActivity.this;
            if (pdBigImageActivity.y) {
                return;
            }
            pdBigImageActivity.isDisposeableUnableExitAnim = true;
            pdBigImageActivity.finish();
        }
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public void onAnimationStart(Animator animator) {
        PdMDropDownViewPager.c cVar = this.f4616g.f4594n;
        if (cVar != null) {
            ((PdBigImageActivity.c) cVar).d(false);
        }
    }
}

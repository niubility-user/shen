package com.jd.lib.productdetail.mainimage.bigimage;

import android.view.View;
import android.view.animation.Animation;

/* loaded from: classes15.dex */
public class b implements Animation.AnimationListener {

    /* renamed from: g  reason: collision with root package name */
    public final /* synthetic */ PdBigImageActivity f4598g;

    public b(PdBigImageActivity pdBigImageActivity) {
        this.f4598g = pdBigImageActivity;
    }

    @Override // android.view.animation.Animation.AnimationListener
    public void onAnimationEnd(Animation animation) {
        View view;
        PdBigImageActivity pdBigImageActivity = this.f4598g;
        if (pdBigImageActivity.y || (view = pdBigImageActivity.D) == null) {
            return;
        }
        view.setVisibility(0);
    }

    @Override // android.view.animation.Animation.AnimationListener
    public void onAnimationRepeat(Animation animation) {
    }

    @Override // android.view.animation.Animation.AnimationListener
    public void onAnimationStart(Animation animation) {
    }
}

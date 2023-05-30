package com.jd.lib.productdetail.mainimage.bigimage;

import android.view.View;
import android.view.animation.Animation;

/* loaded from: classes15.dex */
public class a implements Animation.AnimationListener {

    /* renamed from: g  reason: collision with root package name */
    public final /* synthetic */ PdBigImageActivity f4597g;

    public a(PdBigImageActivity pdBigImageActivity) {
        this.f4597g = pdBigImageActivity;
    }

    @Override // android.view.animation.Animation.AnimationListener
    public void onAnimationEnd(Animation animation) {
        View view = this.f4597g.p;
        if (view != null) {
            view.setVisibility(8);
        }
    }

    @Override // android.view.animation.Animation.AnimationListener
    public void onAnimationRepeat(Animation animation) {
    }

    @Override // android.view.animation.Animation.AnimationListener
    public void onAnimationStart(Animation animation) {
    }
}

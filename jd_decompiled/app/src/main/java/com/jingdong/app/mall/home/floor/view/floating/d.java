package com.jingdong.app.mall.home.floor.view.floating;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.graphics.Rect;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.RelativeLayout;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jingdong.app.mall.home.floor.common.f;
import com.jingdong.app.mall.home.r.e.h;

/* loaded from: classes4.dex */
public class d extends a {
    private AnimatorSet b = new AnimatorSet();

    @Override // com.jingdong.app.mall.home.floor.view.floating.a
    public boolean b() {
        return true;
    }

    @Override // com.jingdong.app.mall.home.floor.view.floating.a
    public boolean c() {
        return true;
    }

    @Override // com.jingdong.app.mall.home.floor.view.floating.a
    public boolean e() {
        return true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.jingdong.app.mall.home.floor.view.floating.a
    public String g() {
        return "B";
    }

    @Override // com.jingdong.app.mall.home.floor.view.floating.a
    public void h(h hVar, View view, f fVar) {
        fVar.R(30, 30);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(fVar.v(), fVar.h());
        layoutParams.addRule(11);
        fVar.G(new Rect(0, 0, hVar.d, 0), layoutParams);
        fVar.L(new Rect(6, 6, 0, 0), view);
        view.setLayoutParams(layoutParams);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.jingdong.app.mall.home.floor.view.floating.a
    public void i(h hVar, FloatLayout floatLayout) {
        int i2 = floatLayout.f9791j + (hVar.d << 1);
        int i3 = floatLayout.f9792k + (floatLayout.A ? 30 : 0);
        floatLayout.x.Q(i2);
        floatLayout.x.C(i3);
        floatLayout.x.F(new Rect(-i2, -i3, 0, 0));
        floatLayout.y.Q(floatLayout.f9791j);
        floatLayout.y.C(floatLayout.f9792k);
        floatLayout.y.F(new Rect(0, floatLayout.A ? 30 : 0, 0, 0));
        r(floatLayout);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.jingdong.app.mall.home.floor.view.floating.a
    public float m(float f2, float f3, float f4) {
        return f4;
    }

    @Override // com.jingdong.app.mall.home.floor.view.floating.a
    public void r(FloatLayout floatLayout) {
        this.b.cancel();
        super.r(floatLayout);
    }

    @Override // com.jingdong.app.mall.home.floor.view.floating.a
    public void t(FloatLayout floatLayout, int i2, boolean z) {
        RelativeLayout u = floatLayout.u();
        SimpleDraweeView r = floatLayout.r();
        if (u != null) {
            if (u.getTranslationX() == 0.0f) {
                return;
            }
            boolean z2 = floatLayout.q.get();
            this.b.cancel();
            this.b = new AnimatorSet();
            ObjectAnimator ofFloat = ObjectAnimator.ofFloat(u, "translationX", i2 + (z2 ? floatLayout.x.v() >> 1 : 0));
            ofFloat.setInterpolator(new DecelerateInterpolator());
            float[] fArr = new float[1];
            fArr[0] = z2 ? 0.5f : 1.0f;
            ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(u, "alpha", fArr);
            ofFloat2.setInterpolator(new DecelerateInterpolator());
            float[] fArr2 = new float[1];
            fArr2[0] = z2 ? 0.0f : 1.0f;
            this.b.playTogether(ofFloat, ofFloat2, ObjectAnimator.ofFloat(r, "alpha", fArr2));
            this.b.setDuration(z ? 0L : 350L);
            this.b.start();
        }
    }
}

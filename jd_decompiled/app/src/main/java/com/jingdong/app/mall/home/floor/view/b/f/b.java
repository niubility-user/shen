package com.jingdong.app.mall.home.floor.view.b.f;

import android.animation.ValueAnimator;
import android.view.animation.AccelerateDecelerateInterpolator;

/* loaded from: classes4.dex */
public class b extends c {

    /* loaded from: classes4.dex */
    class a implements ValueAnimator.AnimatorUpdateListener {
        a() {
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            float floatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
            int size = b.this.a.size();
            b bVar = b.this;
            int i2 = bVar.d;
            if (i2 < size) {
                bVar.a.valueAt(i2).h(floatValue);
            }
        }
    }

    @Override // com.jingdong.app.mall.home.floor.view.b.f.c
    protected void h() {
        this.f9747c = new a();
        ValueAnimator ofFloat = ValueAnimator.ofFloat(0.0f, 1.0f);
        this.b = ofFloat;
        ofFloat.setRepeatCount(1);
        this.b.setInterpolator(new AccelerateDecelerateInterpolator());
        this.b.setDuration(800L);
    }
}

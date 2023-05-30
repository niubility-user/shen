package com.jingdong.app.mall.home.floor.view.b.f;

import android.animation.ValueAnimator;
import android.view.animation.AccelerateDecelerateInterpolator;

/* loaded from: classes4.dex */
public class a extends c {

    /* renamed from: com.jingdong.app.mall.home.floor.view.b.f.a$a  reason: collision with other inner class name */
    /* loaded from: classes4.dex */
    class C0298a implements ValueAnimator.AnimatorUpdateListener {
        C0298a() {
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            float floatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
            int size = a.this.a.size();
            a aVar = a.this;
            int i2 = aVar.d;
            if (i2 < size) {
                aVar.a.valueAt(i2).h(floatValue);
            }
        }
    }

    @Override // com.jingdong.app.mall.home.floor.view.b.f.c
    protected void h() {
        this.f9747c = new C0298a();
        ValueAnimator ofFloat = ValueAnimator.ofFloat(1.0f, 0.9f, 0.8f, 0.9f, 1.0f);
        this.b = ofFloat;
        ofFloat.setInterpolator(new AccelerateDecelerateInterpolator());
        this.b.setStartDelay(400L);
        this.b.setDuration(800L);
    }
}

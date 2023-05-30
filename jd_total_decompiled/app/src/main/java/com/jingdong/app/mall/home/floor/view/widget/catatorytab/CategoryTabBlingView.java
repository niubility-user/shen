package com.jingdong.app.mall.home.floor.view.widget.catatorytab;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.animation.LinearInterpolator;
import com.jingdong.app.mall.home.floor.model.entity.CategoryEntity;
import com.jingdong.app.mall.home.n.h.f;

/* loaded from: classes4.dex */
public class CategoryTabBlingView extends CategoryTabNormalView {
    private ValueAnimator p;
    private boolean q;
    private ValueAnimator.AnimatorUpdateListener r;
    private AnimatorListenerAdapter s;

    /* loaded from: classes4.dex */
    class a implements ValueAnimator.AnimatorUpdateListener {
        a() {
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            CategoryTabBlingView.this.setRotation(((Float) valueAnimator.getAnimatedValue()).floatValue());
        }
    }

    /* loaded from: classes4.dex */
    class b extends AnimatorListenerAdapter {
        b() {
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            if (CategoryTabBlingView.this.q) {
                return;
            }
            CategoryTabBlingView.this.b();
        }
    }

    public CategoryTabBlingView(Context context) {
        super(context);
        new Paint(1);
        new Matrix();
        new Rect();
        this.r = new a();
        this.s = new b();
        n();
    }

    private void n() {
        if (this.p == null) {
            f fVar = new f();
            fVar.b(0.0f, 2.0f, -2.0f, 0.0f);
            fVar.d(600L);
            fVar.f(1200L);
            fVar.e(new LinearInterpolator());
            fVar.g(this.r);
            fVar.c(this.s);
            this.p = fVar.a();
        }
    }

    @Override // com.jingdong.app.mall.home.floor.view.widget.catatorytab.CategoryTabNormalView, com.jingdong.app.mall.home.floor.view.widget.catatorytab.CategoryTabItemBaseView
    public void a() {
        super.a();
    }

    @Override // com.jingdong.app.mall.home.floor.view.widget.catatorytab.CategoryTabNormalView, com.jingdong.app.mall.home.floor.view.widget.catatorytab.CategoryTabItemBaseView
    public void c(CategoryEntity.CaItem caItem, int i2) {
        super.c(caItem, i2);
    }

    @Override // com.jingdong.app.mall.home.floor.view.widget.catatorytab.CategoryTabNormalView, com.jingdong.app.mall.home.floor.view.widget.catatorytab.CategoryTabItemBaseView
    public void h() {
        this.q = false;
        ValueAnimator valueAnimator = this.p;
        if (valueAnimator != null) {
            valueAnimator.start();
        }
    }

    @Override // com.jingdong.app.mall.home.floor.view.widget.catatorytab.CategoryTabNormalView, com.jingdong.app.mall.home.floor.view.widget.catatorytab.CategoryTabItemBaseView
    public void i() {
        setRotation(0.0f);
        this.q = true;
        ValueAnimator valueAnimator = this.p;
        if (valueAnimator != null) {
            valueAnimator.cancel();
        }
    }
}

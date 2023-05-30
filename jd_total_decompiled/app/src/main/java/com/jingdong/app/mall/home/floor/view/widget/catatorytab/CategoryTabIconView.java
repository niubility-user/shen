package com.jingdong.app.mall.home.floor.view.widget.catatorytab;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Rect;
import android.text.TextUtils;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jingdong.app.mall.home.R;
import com.jingdong.app.mall.home.base.HomeDraweeView;
import com.jingdong.app.mall.home.floor.common.d;
import com.jingdong.app.mall.home.floor.common.f;
import com.jingdong.app.mall.home.floor.ctrl.e;
import com.jingdong.app.mall.home.floor.model.entity.CategoryEntity;
import com.jingdong.app.util.image.JDDisplayImageOptions;

/* loaded from: classes4.dex */
public class CategoryTabIconView extends CategoryTabItemBaseView {

    /* renamed from: j  reason: collision with root package name */
    private TextView f10237j;

    /* renamed from: k  reason: collision with root package name */
    private SimpleDraweeView f10238k;

    /* renamed from: l  reason: collision with root package name */
    private f f10239l;

    /* renamed from: m  reason: collision with root package name */
    private f f10240m;

    /* renamed from: n  reason: collision with root package name */
    private ValueAnimator f10241n;
    private ValueAnimator o;
    private int p;
    private boolean q;
    private ValueAnimator.AnimatorUpdateListener r;
    private ValueAnimator.AnimatorUpdateListener s;
    private AnimatorListenerAdapter t;

    /* loaded from: classes4.dex */
    class a implements ValueAnimator.AnimatorUpdateListener {

        /* renamed from: g  reason: collision with root package name */
        float f10242g;

        a() {
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            float floatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
            this.f10242g = CategoryTabIconView.this.p * floatValue;
            if (CategoryTabIconView.this.f10238k != null) {
                CategoryTabIconView.this.f10238k.setAlpha(floatValue);
            }
            if (CategoryTabIconView.this.f10237j != null) {
                CategoryTabIconView.this.f10237j.setPadding((int) this.f10242g, 0, d.d(16), 0);
            }
        }
    }

    /* loaded from: classes4.dex */
    class b implements ValueAnimator.AnimatorUpdateListener {
        b() {
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            float floatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
            if (CategoryTabIconView.this.f10238k != null) {
                CategoryTabIconView.this.f10238k.setScaleY(floatValue);
            }
        }
    }

    /* loaded from: classes4.dex */
    class c extends AnimatorListenerAdapter {
        c() {
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            if (CategoryTabIconView.this.q) {
                return;
            }
            CategoryTabIconView.this.b();
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            if (CategoryTabIconView.this.f10238k != null) {
                CategoryTabIconView.this.f10238k.setScaleY(0.5f);
                CategoryTabIconView.this.f10238k.setPivotX(CategoryTabIconView.this.f10238k.getWidth() >> 1);
                CategoryTabIconView.this.f10238k.setPivotY(CategoryTabIconView.this.f10238k.getHeight());
            }
        }
    }

    public CategoryTabIconView(Context context) {
        super(context);
        this.r = new a();
        this.s = new b();
        this.t = new c();
        n();
    }

    private void n() {
        com.jingdong.app.mall.home.n.h.f fVar = new com.jingdong.app.mall.home.n.h.f();
        fVar.b(0.0f, 1.0f);
        fVar.d(400L);
        fVar.f(1000L);
        fVar.e(new LinearInterpolator());
        fVar.g(this.r);
        this.f10241n = fVar.a();
        com.jingdong.app.mall.home.n.h.f fVar2 = new com.jingdong.app.mall.home.n.h.f();
        fVar2.b(0.5f, 1.1f, 1.0f, 1.0f, 1.0f, 0.8f, 1.0f, 0.85f, 1.0f);
        fVar2.d(1600L);
        fVar2.f(1200L);
        fVar2.e(new LinearInterpolator());
        fVar2.g(this.s);
        fVar2.c(this.t);
        this.o = fVar2.a();
    }

    private void o(String str) {
        if (TextUtils.isEmpty(str)) {
            this.f10238k.setImageResource(R.drawable.home_category_tab_icon_default);
            return;
        }
        String iconImg = this.f10246g.getIconImg();
        SimpleDraweeView simpleDraweeView = this.f10238k;
        JDDisplayImageOptions a2 = com.jingdong.app.mall.home.floor.ctrl.f.a();
        int i2 = R.drawable.home_category_tab_icon_default;
        e.f(iconImg, simpleDraweeView, a2.showImageOnFail(i2).showImageOnLoading(i2));
    }

    private void p() {
        SimpleDraweeView simpleDraweeView = this.f10238k;
        if (simpleDraweeView != null) {
            simpleDraweeView.setScaleX(1.0f);
            this.f10238k.setScaleY(1.0f);
        }
        TextView textView = this.f10237j;
        if (textView != null) {
            textView.setPadding(this.p, 0, d.d(16), 0);
        }
    }

    @Override // com.jingdong.app.mall.home.floor.view.widget.catatorytab.CategoryTabItemBaseView
    public void a() {
        boolean isSelect = this.f10246g.isSelect();
        TextView textView = this.f10237j;
        CategoryEntity.CaItem caItem = this.f10246g;
        textView.setText(isSelect ? caItem.getWords2() : caItem.getWords1());
        this.f10237j.getPaint().setFakeBoldText(isSelect);
        TextView textView2 = this.f10237j;
        CategoryEntity categoryEntity = this.f10246g.mCategoryEntity;
        textView2.setTextSize(0, d.d(isSelect ? categoryEntity.getSelectSize() : categoryEntity.getUnSelectSize()));
        TextView textView3 = this.f10237j;
        CategoryEntity categoryEntity2 = this.f10246g.mCategoryEntity;
        textView3.setTextColor(isSelect ? categoryEntity2.getSelectColor() : categoryEntity2.getUnSelectColor());
        if (isSelect || this.f10246g.getImgAnimType() == 4) {
            this.f10238k.setVisibility(0);
            this.f10238k.setAlpha(1.0f);
            o(this.f10246g.getIconImg());
        }
    }

    @Override // com.jingdong.app.mall.home.floor.view.widget.catatorytab.CategoryTabItemBaseView
    public void c(CategoryEntity.CaItem caItem, int i2) {
        SimpleDraweeView simpleDraweeView = this.f10238k;
        if (simpleDraweeView == null) {
            HomeDraweeView homeDraweeView = new HomeDraweeView(getContext());
            this.f10238k = homeDraweeView;
            homeDraweeView.setScaleType(ImageView.ScaleType.FIT_XY);
            this.f10238k.setId(R.id.mallfloor_item1);
            f fVar = new f(32, 32);
            this.f10240m = fVar;
            RelativeLayout.LayoutParams u = fVar.u(this.f10238k);
            u.leftMargin = d.d(8);
            u.addRule(15);
            addView(this.f10238k, u);
        } else {
            f.d(simpleDraweeView, this.f10240m, true);
        }
        this.f10238k.setAlpha(0.0f);
        TextView textView = this.f10237j;
        if (textView == null) {
            TextView textView2 = new TextView(getContext());
            this.f10237j = textView2;
            textView2.setMaxLines(1);
            this.f10237j.setGravity(17);
            f fVar2 = new f(-2, -1);
            this.f10239l = fVar2;
            fVar2.K(new Rect(0, 0, 16, 0));
            RelativeLayout.LayoutParams u2 = this.f10239l.u(this.f10237j);
            u2.addRule(15);
            addView(this.f10237j, u2);
        } else {
            f.d(textView, this.f10239l, true);
        }
        this.f10237j.setText(this.f10246g.getTabName());
        this.p = d.d(44);
        o(this.f10246g.getIconImg());
        if (this.f10246g.getImgAnimType() == 4) {
            p();
        }
    }

    @Override // com.jingdong.app.mall.home.floor.view.widget.catatorytab.CategoryTabItemBaseView
    public void h() {
        this.q = false;
        ValueAnimator valueAnimator = this.f10241n;
        if (valueAnimator != null) {
            valueAnimator.start();
        }
        ValueAnimator valueAnimator2 = this.o;
        if (valueAnimator2 != null) {
            valueAnimator2.start();
        }
    }

    @Override // com.jingdong.app.mall.home.floor.view.widget.catatorytab.CategoryTabItemBaseView
    public void i() {
        this.q = true;
        ValueAnimator valueAnimator = this.f10241n;
        if (valueAnimator != null) {
            valueAnimator.cancel();
        }
        ValueAnimator valueAnimator2 = this.o;
        if (valueAnimator2 != null) {
            valueAnimator2.cancel();
        }
        p();
    }
}

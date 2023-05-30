package com.jingdong.app.mall.home.floor.view.widget.catatorytab;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Rect;
import android.os.Build;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jingdong.app.mall.home.R;
import com.jingdong.app.mall.home.base.HomeDraweeView;
import com.jingdong.app.mall.home.floor.common.f;
import com.jingdong.app.mall.home.floor.ctrl.e;
import com.jingdong.app.mall.home.floor.model.entity.CategoryEntity;

/* loaded from: classes4.dex */
public class CategoryTabFlipperView extends CategoryTabItemBaseView {

    /* renamed from: j  reason: collision with root package name */
    private TextView[] f10227j;

    /* renamed from: k  reason: collision with root package name */
    private SimpleDraweeView[] f10228k;

    /* renamed from: l  reason: collision with root package name */
    private f[] f10229l;

    /* renamed from: m  reason: collision with root package name */
    private f[] f10230m;

    /* renamed from: n  reason: collision with root package name */
    private RelativeLayout[] f10231n;
    private ValueAnimator o;
    private boolean p;
    private boolean q;
    private int r;
    private int s;
    private int t;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class a extends AnimatorListenerAdapter {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ int f10232g;

        a(int i2) {
            this.f10232g = i2;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            super.onAnimationEnd(animator);
            if (CategoryTabFlipperView.this.q) {
                return;
            }
            CategoryTabFlipperView.this.p = !r3.p;
            String str = "onAnimationEnd----" + CategoryTabFlipperView.this.r + "--" + CategoryTabFlipperView.this.p + Thread.currentThread().getName() + CategoryTabFlipperView.this.toString();
            if (CategoryTabFlipperView.this.r > 1) {
                CategoryTabFlipperView.this.r = 0;
                CategoryTabFlipperView.this.b();
                return;
            }
            CategoryTabFlipperView.this.h();
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            super.onAnimationStart(animator);
            if (CategoryTabFlipperView.this.q) {
                return;
            }
            CategoryTabFlipperView.o(CategoryTabFlipperView.this);
            if (CategoryTabFlipperView.this.p) {
                CategoryTabFlipperView.this.f10231n[0].setTranslationY(-this.f10232g);
                CategoryTabFlipperView.this.f10231n[1].setTranslationY(0.0f);
            } else {
                CategoryTabFlipperView.this.f10231n[0].setTranslationY(0.0f);
                CategoryTabFlipperView.this.f10231n[1].setTranslationY(-this.f10232g);
            }
            String str = "onAnimationStart----" + CategoryTabFlipperView.this.r + "--" + CategoryTabFlipperView.this.p + Thread.currentThread().getName() + CategoryTabFlipperView.this.toString();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class b implements ValueAnimator.AnimatorUpdateListener {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ int f10234g;

        b(int i2) {
            this.f10234g = i2;
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            float floatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
            if (!CategoryTabFlipperView.this.p) {
                CategoryTabFlipperView.this.f10231n[0].setTranslationY(floatValue);
                CategoryTabFlipperView.this.f10231n[1].setTranslationY(floatValue - this.f10234g);
                return;
            }
            CategoryTabFlipperView.this.f10231n[0].setTranslationY(floatValue - this.f10234g);
            CategoryTabFlipperView.this.f10231n[1].setTranslationY(floatValue);
        }
    }

    /* loaded from: classes4.dex */
    class c implements e.b {
        c() {
        }

        @Override // com.jingdong.app.mall.home.floor.ctrl.e.b
        public void onFailed(String str, View view) {
            CategoryTabFlipperView categoryTabFlipperView = CategoryTabFlipperView.this;
            categoryTabFlipperView.g(categoryTabFlipperView.f10227j[0], 0);
        }

        @Override // com.jingdong.app.mall.home.floor.ctrl.e.b
        public void onStart(String str, View view) {
            CategoryTabFlipperView categoryTabFlipperView = CategoryTabFlipperView.this;
            categoryTabFlipperView.g(categoryTabFlipperView.f10227j[0], 0);
        }

        @Override // com.jingdong.app.mall.home.floor.ctrl.e.b
        public void onSuccess(String str, View view) {
            CategoryTabFlipperView categoryTabFlipperView = CategoryTabFlipperView.this;
            categoryTabFlipperView.g(categoryTabFlipperView.f10227j[0], 8);
        }
    }

    /* loaded from: classes4.dex */
    class d implements e.b {
        final /* synthetic */ int a;

        d(int i2) {
            this.a = i2;
        }

        @Override // com.jingdong.app.mall.home.floor.ctrl.e.b
        public void onFailed(String str, View view) {
            CategoryTabFlipperView categoryTabFlipperView = CategoryTabFlipperView.this;
            categoryTabFlipperView.g(categoryTabFlipperView.f10227j[this.a], 0);
        }

        @Override // com.jingdong.app.mall.home.floor.ctrl.e.b
        public void onStart(String str, View view) {
            CategoryTabFlipperView categoryTabFlipperView = CategoryTabFlipperView.this;
            categoryTabFlipperView.g(categoryTabFlipperView.f10227j[this.a], 0);
        }

        @Override // com.jingdong.app.mall.home.floor.ctrl.e.b
        public void onSuccess(String str, View view) {
            CategoryTabFlipperView categoryTabFlipperView = CategoryTabFlipperView.this;
            categoryTabFlipperView.g(categoryTabFlipperView.f10227j[this.a], 8);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class e extends com.jingdong.app.mall.home.o.a.b {
        e() {
        }

        @Override // com.jingdong.app.mall.home.o.a.b
        protected void safeRun() {
            if (CategoryTabFlipperView.this.o != null) {
                CategoryTabFlipperView.this.o.start();
            }
            String str = "startAnim----" + CategoryTabFlipperView.this.r + "--" + CategoryTabFlipperView.this.p + Thread.currentThread().getName() + toString();
        }
    }

    public CategoryTabFlipperView(Context context) {
        super(context);
        this.f10227j = new TextView[2];
        this.f10228k = new HomeDraweeView[2];
        this.f10229l = new f[2];
        this.f10230m = new f[2];
        this.f10231n = new RelativeLayout[2];
    }

    static /* synthetic */ int o(CategoryTabFlipperView categoryTabFlipperView) {
        int i2 = categoryTabFlipperView.r;
        categoryTabFlipperView.r = i2 + 1;
        return i2;
    }

    private void s() {
        t(this.s);
        this.p = false;
        u();
    }

    private void t(int i2) {
        if (this.o == null) {
            com.jingdong.app.mall.home.n.h.f fVar = new com.jingdong.app.mall.home.n.h.f();
            fVar.b(0.0f, i2);
            fVar.d(400L);
            fVar.f(1200L);
            fVar.e(new AccelerateDecelerateInterpolator());
            fVar.g(new b(i2));
            fVar.c(new a(i2));
            this.o = fVar.a();
        }
    }

    private void u() {
        this.f10231n[1].setTranslationY(-com.jingdong.app.mall.home.floor.common.d.d(this.s));
        this.f10231n[0].setTranslationY(0.0f);
    }

    @Override // com.jingdong.app.mall.home.floor.view.widget.catatorytab.CategoryTabItemBaseView
    public void a() {
        if (this.f10246g.isSelect()) {
            this.f10227j[0].setText(this.f10246g.getWords2());
            this.f10227j[0].setTextColor(this.f10246g.mCategoryEntity.getSelectColor());
            this.f10227j[0].getPaint().setFakeBoldText(true);
            if (this.t != 0) {
                com.jingdong.app.mall.home.floor.ctrl.e.p(this.f10228k[0], this.f10246g.getSelectImg(), com.jingdong.app.mall.home.floor.ctrl.e.b, new c());
                return;
            }
            return;
        }
        int i2 = 0;
        while (i2 < 2) {
            TextView textView = this.f10227j[i2];
            CategoryEntity.CaItem caItem = this.f10246g;
            textView.setText(i2 == 0 ? caItem.getWords1() : caItem.getWords2());
            this.f10227j[i2].setTextColor(this.f10246g.mCategoryEntity.getUnSelectColor());
            this.f10227j[i2].getPaint().setFakeBoldText(false);
            if (this.t != 0) {
                SimpleDraweeView simpleDraweeView = this.f10228k[i2];
                CategoryEntity.CaItem caItem2 = this.f10246g;
                com.jingdong.app.mall.home.floor.ctrl.e.p(simpleDraweeView, i2 == 0 ? caItem2.getUnSelectImg1() : caItem2.getUnSelectImg2(), com.jingdong.app.mall.home.floor.ctrl.e.b, new d(i2));
            }
            i2++;
        }
    }

    @Override // com.jingdong.app.mall.home.floor.view.widget.catatorytab.CategoryTabItemBaseView
    public void c(CategoryEntity.CaItem caItem, int i2) {
        int imgType = caItem.getImgType();
        this.t = imgType;
        int i3 = imgType != 1 ? imgType != 2 ? imgType != 3 ? -2 : 160 : 126 : 96;
        int i4 = 0;
        while (i4 < 2) {
            RelativeLayout[] relativeLayoutArr = this.f10231n;
            if (relativeLayoutArr[i4] == null) {
                relativeLayoutArr[i4] = new RelativeLayout(getContext());
                f fVar = new f(-1, -1);
                RelativeLayout[] relativeLayoutArr2 = this.f10231n;
                addView(relativeLayoutArr2[i4], fVar.u(relativeLayoutArr2[i4]));
            }
            TextView[] textViewArr = this.f10227j;
            if (textViewArr[i4] == null) {
                textViewArr[i4] = new TextView(getContext());
                this.f10227j[i4].setMaxLines(1);
                this.f10227j[i4].setId(R.id.mallfloor_item1);
                this.f10227j[i4].setGravity(17);
                this.f10227j[i4].setTextSize(0, com.jingdong.app.mall.home.floor.common.d.d(32));
                this.f10229l[i4] = new f(-2, -1);
                this.f10229l[i4].K(new Rect(16, 0, 16, 0));
                RelativeLayout.LayoutParams u = this.f10229l[i4].u(this.f10227j[i4]);
                u.addRule(13);
                this.f10231n[i4].addView(this.f10227j[i4], u);
            }
            SimpleDraweeView[] simpleDraweeViewArr = this.f10228k;
            if (simpleDraweeViewArr[i4] == null) {
                simpleDraweeViewArr[i4] = new HomeDraweeView(getContext());
                this.f10228k[i4].setScaleType(ImageView.ScaleType.FIT_XY);
                this.f10230m[i4] = new f(i3, 44);
                this.f10230m[i4].J(4, 0, 4, 0);
                RelativeLayout.LayoutParams u2 = this.f10230m[i4].u(this.f10228k[i4]);
                u2.addRule(13);
                this.f10231n[i4].addView(this.f10228k[i4], u2);
            } else {
                this.f10230m[i4].Q(i3);
                f.d(this.f10228k[i4], this.f10230m[i4], true);
            }
            if (!this.f10246g.isImageType()) {
                this.f10228k[i4].setVisibility(4);
            } else {
                this.f10228k[i4].setVisibility(0);
            }
            this.f10227j[i4].setVisibility(0);
            this.f10227j[i4].setText(i4 == 0 ? caItem.getWords1() : caItem.getWords2());
            i4++;
        }
        this.s = com.jingdong.app.mall.home.floor.common.d.d(72);
        s();
    }

    @Override // com.jingdong.app.mall.home.floor.view.widget.catatorytab.CategoryTabItemBaseView
    public void h() {
        this.q = false;
        ValueAnimator valueAnimator = this.o;
        if (valueAnimator != null && valueAnimator.isStarted() && Build.VERSION.SDK_INT < 26) {
            this.o.cancel();
            com.jingdong.app.mall.home.o.a.f.E0(new e());
            return;
        }
        ValueAnimator valueAnimator2 = this.o;
        if (valueAnimator2 != null && !valueAnimator2.isStarted()) {
            this.o.start();
        }
        String str = "startAnim----" + this.r + "--" + this.p + Thread.currentThread().getName() + toString();
    }

    @Override // com.jingdong.app.mall.home.floor.view.widget.catatorytab.CategoryTabItemBaseView
    public void i() {
        String str = "stopAnim----" + this.r + "--" + this.p + Thread.currentThread().getName() + toString();
        this.q = true;
        this.r = 0;
        ValueAnimator valueAnimator = this.o;
        if (valueAnimator != null) {
            valueAnimator.cancel();
        }
        s();
    }
}

package com.jingdong.app.mall.home.floor.view.floating;

import android.animation.ValueAnimator;
import android.graphics.Rect;
import android.text.TextUtils;
import android.util.Pair;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.facebook.drawee.drawable.ScalingUtils;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jingdong.app.mall.home.base.HomeDraweeView;
import com.jingdong.app.mall.home.floor.common.f;
import com.jingdong.app.mall.home.r.e.h;
import com.jingdong.app.util.image.assist.JDFailReason;
import com.jingdong.app.util.image.listener.JDSimpleImageLoadingListener;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes4.dex */
public class e extends com.jingdong.app.mall.home.floor.view.floating.a {

    /* renamed from: h  reason: collision with root package name */
    private static Pair<String, Boolean> f9818h;
    private SimpleDraweeView b;

    /* renamed from: c  reason: collision with root package name */
    private f f9819c;
    private ValueAnimator d;

    /* renamed from: e  reason: collision with root package name */
    private int f9820e;

    /* renamed from: f  reason: collision with root package name */
    private long f9821f;

    /* renamed from: g  reason: collision with root package name */
    private AtomicBoolean f9822g = new AtomicBoolean(true);

    /* loaded from: classes4.dex */
    class a extends JDSimpleImageLoadingListener {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ FloatLayout f9823g;

        a(e eVar, FloatLayout floatLayout) {
            this.f9823g = floatLayout;
        }

        @Override // com.jingdong.app.util.image.listener.JDSimpleImageLoadingListener, com.jingdong.app.util.image.listener.JDImageLoadingListener
        public void onLoadingFailed(String str, View view, JDFailReason jDFailReason) {
            this.f9823g.y();
        }
    }

    /* loaded from: classes4.dex */
    class b implements ValueAnimator.AnimatorUpdateListener {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ boolean f9824g;

        /* renamed from: h  reason: collision with root package name */
        final /* synthetic */ View f9825h;

        b(e eVar, boolean z, View view) {
            this.f9824g = z;
            this.f9825h = view;
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            float floatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
            if (this.f9824g) {
                floatValue = 1.0f - floatValue;
            }
            this.f9825h.setScaleX(floatValue);
            this.f9825h.setScaleY(floatValue);
            this.f9825h.setAlpha(floatValue);
            this.f9825h.setTranslationX(com.jingdong.app.mall.home.floor.common.d.d(50) * (1.0f - floatValue));
        }
    }

    @Override // com.jingdong.app.mall.home.floor.view.floating.a
    public boolean b() {
        return true;
    }

    @Override // com.jingdong.app.mall.home.floor.view.floating.a
    public boolean c() {
        return true;
    }

    @Override // com.jingdong.app.mall.home.floor.view.floating.a
    public boolean d(String str) {
        super.d(str);
        Pair<String, Boolean> pair = f9818h;
        if (pair != null && TextUtils.equals((CharSequence) pair.first, str)) {
            return ((Boolean) f9818h.second).booleanValue();
        }
        this.f9822g.set(true);
        boolean f2 = f("home_floatT_show_times", str, this.f9820e, this.f9821f);
        f9818h = new Pair<>(str, Boolean.valueOf(f2));
        return f2;
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
        fVar.G(new Rect(0, 0, 24, 0), layoutParams);
        fVar.L(new Rect(2, 3, 4, 3), view);
        view.setLayoutParams(layoutParams);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.jingdong.app.mall.home.floor.view.floating.a
    public void i(h hVar, FloatLayout floatLayout) {
        String jsonString = hVar.getJsonString("shrinkingImg");
        if (TextUtils.isEmpty(jsonString)) {
            floatLayout.y();
            return;
        }
        this.f9820e = hVar.getJsonInt("unclickTimes");
        this.f9821f = hVar.getJsonLong("unclickPeriod");
        int i2 = 155 - (floatLayout.A ? 0 : 15);
        floatLayout.x.Q(144);
        floatLayout.x.C(i2);
        floatLayout.x.F(new Rect(-144, -i2, 0, 0));
        floatLayout.y.Q(120);
        floatLayout.y.C(140);
        floatLayout.y.F(new Rect(0, floatLayout.A ? 15 : 0, 24, 0));
        r(floatLayout);
        if (this.b == null) {
            HomeDraweeView homeDraweeView = new HomeDraweeView(floatLayout.getContext());
            this.b = homeDraweeView;
            homeDraweeView.getHierarchy().setActualImageScaleType(ScalingUtils.ScaleType.FIT_XY);
            this.b.setScaleType(ImageView.ScaleType.FIT_XY);
            this.b.setContentDescription("\u6d6e\u5c42\u6d3b\u52a8");
            this.b.setClickable(true);
            f fVar = new f(70, 70);
            this.f9819c = fVar;
            fVar.E(0, 0, 0, 35);
            RelativeLayout.LayoutParams u = this.f9819c.u(this.b);
            u.addRule(12);
            u.addRule(11);
            floatLayout.w.addView(this.b, u);
        }
        f.c(this.b, this.f9819c);
        this.b.setAlpha(0.0f);
        com.jingdong.app.mall.home.floor.ctrl.e.q(this.b, jsonString, false, new a(this, floatLayout));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.jingdong.app.mall.home.floor.view.floating.a
    public float m(float f2, float f3, float f4) {
        return f4;
    }

    @Override // com.jingdong.app.mall.home.floor.view.floating.a
    public void p() {
        com.jingdong.app.mall.home.o.a.f.x0("home_floatT_show_times", "");
    }

    @Override // com.jingdong.app.mall.home.floor.view.floating.a
    public void r(FloatLayout floatLayout) {
        SimpleDraweeView simpleDraweeView = this.b;
        if (simpleDraweeView != null) {
            simpleDraweeView.animate().cancel();
            this.b.setAlpha(0.0f);
        }
        ValueAnimator valueAnimator = this.d;
        if (valueAnimator != null) {
            valueAnimator.cancel();
        }
        super.r(floatLayout);
    }

    @Override // com.jingdong.app.mall.home.floor.view.floating.a
    public void s(String str, String str2, FloatLayout floatLayout) {
        super.s(str, str2, floatLayout);
        if (this.f9822g.getAndSet(false)) {
            u(this.a);
        }
    }

    @Override // com.jingdong.app.mall.home.floor.view.floating.a
    public void t(FloatLayout floatLayout, int i2, boolean z) {
        SimpleDraweeView v = floatLayout.v();
        SimpleDraweeView r = floatLayout.r();
        if (v == null) {
            return;
        }
        boolean z2 = floatLayout.q.get();
        ValueAnimator valueAnimator = this.d;
        if (valueAnimator != null) {
            valueAnimator.cancel();
        }
        ValueAnimator ofFloat = ValueAnimator.ofFloat(0.0f, 1.0f);
        this.d = ofFloat;
        ofFloat.setDuration(z ? 0L : 300L);
        this.d.setInterpolator(new AccelerateDecelerateInterpolator());
        this.d.addUpdateListener(new b(this, z2, v));
        this.d.start();
        SimpleDraweeView simpleDraweeView = this.b;
        if (simpleDraweeView != null) {
            ViewPropertyAnimator animate = simpleDraweeView.animate();
            animate.cancel();
            animate.setDuration(z ? 0L : 300L);
            animate.alpha(z2 ? 0.65f : 0.0f);
            animate.start();
        }
        if (r != null) {
            r.setClickable(false);
            ViewPropertyAnimator animate2 = r.animate();
            animate2.cancel();
            animate2.setDuration(z ? 0L : 100L);
            animate2.setStartDelay(z2 ? 0L : 290L);
            animate2.alpha(z2 ? 0.0f : 1.0f);
            animate2.start();
            floatLayout.A(z2);
        }
    }

    public boolean u(String str) {
        return a("home_floatT_show_times", str, this.f9820e, this.f9821f);
    }

    public SimpleDraweeView v() {
        return this.b;
    }
}

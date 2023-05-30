package com.jingdong.app.mall.home.floor.view.floating;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.graphics.Rect;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Pair;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.RelativeLayout;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jingdong.app.mall.home.floor.common.f;
import com.jingdong.app.mall.home.r.e.h;
import com.jingdong.sdk.platform.business.personal.R2;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes4.dex */
public class c extends com.jingdong.app.mall.home.floor.view.floating.a {

    /* renamed from: m  reason: collision with root package name */
    private static Handler f9802m = new Handler(Looper.getMainLooper());

    /* renamed from: n  reason: collision with root package name */
    private static Pair<String, Boolean> f9803n;

    /* renamed from: h  reason: collision with root package name */
    private int f9808h;

    /* renamed from: i  reason: collision with root package name */
    private long f9809i;

    /* renamed from: l  reason: collision with root package name */
    private String f9812l;
    private AtomicBoolean b = new AtomicBoolean(false);

    /* renamed from: c  reason: collision with root package name */
    private AtomicBoolean f9804c = new AtomicBoolean(false);
    private AtomicBoolean d = new AtomicBoolean(false);

    /* renamed from: e  reason: collision with root package name */
    private AnimatorSet f9805e = new AnimatorSet();

    /* renamed from: f  reason: collision with root package name */
    private AnimatorSet f9806f = new AnimatorSet();

    /* renamed from: g  reason: collision with root package name */
    private AnimatorSet f9807g = new AnimatorSet();

    /* renamed from: j  reason: collision with root package name */
    private AtomicBoolean f9810j = new AtomicBoolean(true);

    /* renamed from: k  reason: collision with root package name */
    private AtomicBoolean f9811k = new AtomicBoolean(true);

    /* loaded from: classes4.dex */
    class a extends AnimatorListenerAdapter {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ View f9813g;

        /* renamed from: h  reason: collision with root package name */
        final /* synthetic */ View f9814h;

        a(View view, View view2) {
            this.f9813g = view;
            this.f9814h = view2;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            c.this.b.set(false);
            this.f9813g.setRotation(0.0f);
            this.f9814h.setTranslationX(0.0f);
        }
    }

    /* loaded from: classes4.dex */
    class b extends com.jingdong.app.mall.home.o.a.b {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ FloatLayout f9816g;

        b(FloatLayout floatLayout) {
            this.f9816g = floatLayout;
        }

        @Override // com.jingdong.app.mall.home.o.a.b
        protected void safeRun() {
            this.f9816g.N(!c.this.f9804c.get());
        }
    }

    @Override // com.jingdong.app.mall.home.floor.view.floating.a
    public boolean d(String str) {
        super.d(str);
        Pair<String, Boolean> pair = f9803n;
        if (pair != null && TextUtils.equals((CharSequence) pair.first, str)) {
            return ((Boolean) f9803n.second).booleanValue();
        }
        this.f9810j.set(true);
        boolean f2 = f("home_floatN_show_times", str, this.f9808h, this.f9809i);
        f9803n = new Pair<>(str, Boolean.valueOf(f2));
        return f2;
    }

    @Override // com.jingdong.app.mall.home.floor.view.floating.a
    public boolean e() {
        return true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.jingdong.app.mall.home.floor.view.floating.a
    public String g() {
        return "C";
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.jingdong.app.mall.home.floor.view.floating.a
    public void h(h hVar, View view, f fVar) {
        fVar.Q(40);
        fVar.C(40);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(fVar.v(), fVar.h());
        layoutParams.addRule(11, -1);
        fVar.L(new Rect(2, 13, 14, 3), view);
        view.setLayoutParams(layoutParams);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.jingdong.app.mall.home.floor.view.floating.a
    public void i(h hVar, FloatLayout floatLayout) {
        this.f9808h = hVar.getJsonInt("unclickTimes");
        this.f9809i = hVar.getJsonLong("unclickPeriod");
        this.f9811k.set(true);
        int i2 = (floatLayout.A ? 40 : 0) + 100;
        floatLayout.x.Q(R2.anim.lib_cashier_sdk_fragment_right_out);
        floatLayout.x.C(i2);
        floatLayout.x.F(new Rect(-136, -i2, 0, 0));
        floatLayout.y.Q(80);
        floatLayout.y.C(80);
        floatLayout.y.F(new Rect(0, floatLayout.A ? 40 : 0, 0, 0));
        r(floatLayout);
    }

    @Override // com.jingdong.app.mall.home.floor.view.floating.a
    public boolean k() {
        return this.b.get();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.jingdong.app.mall.home.floor.view.floating.a
    public float m(float f2, float f3, float f4) {
        return f4;
    }

    @Override // com.jingdong.app.mall.home.floor.view.floating.a
    public void o(boolean z, View view) {
        super.o(z, view);
        this.d.set(z);
    }

    @Override // com.jingdong.app.mall.home.floor.view.floating.a
    public void p() {
        com.jingdong.app.mall.home.o.a.f.x0("home_floatN_show_times", "");
    }

    @Override // com.jingdong.app.mall.home.floor.view.floating.a
    public void q(FloatLayout floatLayout, int i2, boolean z) {
        super.q(floatLayout, i2, z);
        RelativeLayout u = floatLayout.u();
        SimpleDraweeView v = floatLayout.v();
        SimpleDraweeView r = floatLayout.r();
        if (!this.d.get() || u == null || v == null) {
            return;
        }
        if (this.b.get() && i2 < (com.jingdong.app.mall.home.floor.common.d.f9278f >> 1)) {
            f9802m.removeCallbacksAndMessages(null);
            this.b.set(false);
            this.f9804c.set(true);
            this.f9806f.cancel();
            this.f9806f.removeAllListeners();
            this.f9805e.cancel();
            this.f9807g.cancel();
            this.f9806f = new AnimatorSet();
            ObjectAnimator ofFloat = ObjectAnimator.ofFloat(u, "alpha", 0.3f);
            ofFloat.setDuration(500L);
            this.f9806f.addListener(new a(v, u));
            this.f9806f.play(ofFloat);
            this.f9806f.start();
        }
        if (!this.f9805e.isRunning() && this.b.get() && this.f9804c.get() != z) {
            f9802m.removeCallbacksAndMessages(null);
            f9802m.postDelayed(new b(floatLayout), this.f9804c.get() ? 500L : 1000L);
            this.f9804c.set(z);
        }
        if (this.b.get() || i2 <= (com.jingdong.app.mall.home.floor.common.d.f9278f >> 1)) {
            return;
        }
        if (this.f9811k.getAndSet(false)) {
            super.s(floatLayout.s(), this.f9812l, floatLayout);
        }
        if (this.f9810j.getAndSet(false)) {
            w(this.a);
        }
        f9802m.removeCallbacksAndMessages(null);
        this.b.set(true);
        if (r != null) {
            r.setAlpha(0.0f);
        }
        v.setRotation(0.0f);
        u.setAlpha(0.0f);
        floatLayout.C(true);
        ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(v, "rotation", 0.0f, -5.0f, 5.0f, 0.0f);
        ofFloat2.setInterpolator(new AccelerateDecelerateInterpolator());
        ofFloat2.setDuration(500L);
        ObjectAnimator ofFloat3 = ObjectAnimator.ofFloat(u, "alpha", 1.0f);
        ofFloat3.setDuration(500L);
        ObjectAnimator ofFloat4 = ObjectAnimator.ofFloat(r, "alpha", 1.0f);
        ofFloat4.setDuration(500L);
        this.f9806f.cancel();
        this.f9806f.removeAllListeners();
        this.f9805e.cancel();
        this.f9807g.cancel();
        AnimatorSet animatorSet = new AnimatorSet();
        this.f9805e = animatorSet;
        animatorSet.play(ofFloat2).after(ofFloat3);
        this.f9805e.play(ofFloat4).with(ofFloat3);
        this.f9805e.start();
        this.f9804c.set(true);
        floatLayout.N(false);
    }

    @Override // com.jingdong.app.mall.home.floor.view.floating.a
    public void r(FloatLayout floatLayout) {
        this.d.set(false);
        f9802m.removeCallbacksAndMessages(null);
        this.b.set(false);
        this.f9806f.cancel();
        this.f9805e.cancel();
        this.f9807g.cancel();
        RelativeLayout u = floatLayout.u();
        if (u != null) {
            u.setTranslationX(0.0f);
        }
        super.r(floatLayout);
    }

    @Override // com.jingdong.app.mall.home.floor.view.floating.a
    public void s(String str, String str2, FloatLayout floatLayout) {
        this.f9812l = str2;
    }

    @Override // com.jingdong.app.mall.home.floor.view.floating.a
    public void t(FloatLayout floatLayout, int i2, boolean z) {
        RelativeLayout u = floatLayout.u();
        SimpleDraweeView v = floatLayout.v();
        SimpleDraweeView r = floatLayout.r();
        if (u == null || v == null) {
            return;
        }
        if (u.getTranslationX() == 0.0f || !this.b.get() || this.f9805e.isRunning()) {
            return;
        }
        boolean z2 = floatLayout.q.get();
        this.f9807g.cancel();
        this.f9807g = new AnimatorSet();
        float d = i2 + (z2 ? com.jingdong.app.mall.home.floor.common.d.d(68) : 0);
        ObjectAnimator ofFloat = z2 ? ObjectAnimator.ofFloat(v, "rotation", -45.0f) : ObjectAnimator.ofFloat(v, "rotation", -45.0f, 5.0f, -5.0f, 0.0f);
        ofFloat.setInterpolator(new DecelerateInterpolator());
        ofFloat.setDuration(z2 ? 350L : 700L);
        ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(u, "translationX", d);
        ofFloat2.setInterpolator(new DecelerateInterpolator());
        ofFloat2.setDuration(350L);
        float[] fArr = new float[1];
        fArr[0] = z2 ? 0.5f : 1.0f;
        ObjectAnimator ofFloat3 = ObjectAnimator.ofFloat(u, "alpha", fArr);
        ofFloat3.setInterpolator(new DecelerateInterpolator());
        ofFloat3.setDuration(350L);
        float[] fArr2 = new float[1];
        fArr2[0] = z2 ? 0.0f : 1.0f;
        ObjectAnimator ofFloat4 = ObjectAnimator.ofFloat(r, "alpha", fArr2);
        ofFloat4.setDuration(350L);
        if (z2) {
            this.f9807g.play(ofFloat).with(ofFloat4).before(ofFloat2);
        } else {
            this.f9807g.play(ofFloat).with(ofFloat4).after(ofFloat2);
        }
        this.f9807g.play(ofFloat3).with(ofFloat2);
        this.f9807g.start();
    }

    public boolean w(String str) {
        return a("home_floatN_show_times", str, this.f9808h, this.f9809i);
    }
}

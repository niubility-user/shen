package com.jingdong.app.mall.home.floor.bottomfloat;

import android.animation.ValueAnimator;
import android.content.Context;
import android.os.SystemClock;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jingdong.app.mall.home.base.HomeDraweeView;
import com.jingdong.app.mall.home.floor.common.d;
import com.jingdong.app.mall.home.floor.common.f;
import com.jingdong.app.mall.home.floor.common.i.s;
import com.jingdong.app.mall.home.floor.ctrl.e;
import com.jingdong.common.jdmiaosha.utils.cache.Final;
import com.jingdong.sdk.platform.business.personal.R2;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes4.dex */
public class FrameTwoLayout extends FrameBaseLayout {
    private f u;
    private SimpleDraweeView v;
    private ValueAnimator w;
    private AtomicBoolean x;

    /* loaded from: classes4.dex */
    class a implements e.b {
        a() {
            FrameTwoLayout.this = r1;
        }

        @Override // com.jingdong.app.mall.home.floor.ctrl.e.b
        public void onFailed(String str, View view) {
            FrameTwoLayout.this.p.set(false);
            FrameTwoLayout.this.r.set(true);
        }

        @Override // com.jingdong.app.mall.home.floor.ctrl.e.b
        public void onStart(String str, View view) {
        }

        @Override // com.jingdong.app.mall.home.floor.ctrl.e.b
        public void onSuccess(String str, View view) {
            FrameTwoLayout.this.p.set(true);
        }
    }

    /* loaded from: classes4.dex */
    class b implements ValueAnimator.AnimatorUpdateListener {
        b() {
            FrameTwoLayout.this = r1;
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            float floatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
            FrameTwoLayout.this.setTranslationX(floatValue);
            if (floatValue < d.d(300) - FrameTwoLayout.this.u.v()) {
                FrameTwoLayout.this.u(true);
            }
        }
    }

    /* loaded from: classes4.dex */
    public class c extends com.jingdong.app.mall.home.o.a.b {
        c() {
            FrameTwoLayout.this = r1;
        }

        @Override // com.jingdong.app.mall.home.o.a.b
        protected void safeRun() {
            FrameTwoLayout.this.v();
        }
    }

    public FrameTwoLayout(Context context) {
        super(context);
        this.x = new AtomicBoolean(false);
        f fVar = new f(R2.attr.maskedWalletDetailsButtonTextAppearance, R2.anim.lib_cashier_sdk_fragment_right_out);
        this.u = fVar;
        RelativeLayout.LayoutParams u = fVar.u(this);
        u.addRule(12);
        setLayoutParams(u);
        setClickable(true);
        HomeDraweeView homeDraweeView = new HomeDraweeView(context);
        this.v = homeDraweeView;
        homeDraweeView.setScaleType(ImageView.ScaleType.FIT_XY);
        addView(this.v, new RelativeLayout.LayoutParams(-1, -1));
    }

    private void t() {
        ValueAnimator valueAnimator = this.w;
        if (valueAnimator != null) {
            valueAnimator.cancel();
        }
        setTranslationX(d.f9279g << 1);
    }

    public void u(boolean z) {
        this.r.set(true);
        if (z) {
            v();
        } else {
            com.jingdong.app.mall.home.o.a.f.F0(new c(), 200L);
        }
    }

    @Override // com.jingdong.app.mall.home.floor.bottomfloat.FrameBaseLayout
    public boolean a() {
        FrameBaseLayout frameBaseLayout;
        return !this.x.get() && this.p.get() && (frameBaseLayout = this.f9157g) != null && frameBaseLayout.f();
    }

    @Override // com.jingdong.app.mall.home.floor.bottomfloat.FrameBaseLayout
    public void d() {
        super.d();
        t();
        this.x.set(com.jingdong.app.mall.home.o.a.f.M("isFramePlay_".concat(this.f9159i.q), 0) > 0);
        if (this.x.get() || TextUtils.isEmpty(this.f9159i.s)) {
            return;
        }
        if (!this.f9159i.a()) {
            this.x.set(true);
            u(false);
            return;
        }
        e.p(this.v, this.f9159i.s, e.b, new a());
    }

    @Override // com.jingdong.app.mall.home.floor.bottomfloat.FrameBaseLayout
    public boolean f() {
        FrameBaseLayout frameBaseLayout;
        return this.x.get() || ((this.p.get() || this.r.get()) && ((frameBaseLayout = this.f9157g) == null || frameBaseLayout.f()));
    }

    @Override // android.widget.RelativeLayout, android.view.View
    protected void onMeasure(int i2, int i3) {
        super.onMeasure(View.MeasureSpec.makeMeasureSpec(this.u.v(), 1073741824), i3);
    }

    @Override // com.jingdong.app.mall.home.floor.bottomfloat.FrameBaseLayout
    public void p() {
        FrameBaseLayout frameBaseLayout;
        if (this.s.getAndSet(true)) {
            return;
        }
        ValueAnimator valueAnimator = this.w;
        if (valueAnimator == null || !valueAnimator.isRunning()) {
            if ((this.f9159i.f9196k * 1000) - (SystemClock.elapsedRealtime() - s.f9357c) >= 20000 && f() && !this.r.get() && (frameBaseLayout = this.f9157g) != null && !frameBaseLayout.r.get() && !this.x.get()) {
                com.jingdong.app.mall.home.o.a.f.y0("isFramePlay_".concat(this.f9159i.q), 1);
                t();
                this.x.set(true);
                this.f9159i.f9190e = true;
                ValueAnimator ofFloat = ValueAnimator.ofFloat(d.f9279g, -this.u.v());
                this.w = ofFloat;
                ofFloat.addUpdateListener(new b());
                this.w.setInterpolator(new AccelerateDecelerateInterpolator());
                this.w.setDuration(Final.FIVE_SECOND);
                this.w.start();
                return;
            }
            u(false);
        }
    }

    protected void v() {
        FrameBaseLayout frameBaseLayout = this.f9157g;
        if (frameBaseLayout != null) {
            frameBaseLayout.p();
        }
    }
}

package com.jingdong.app.mall.home.xview;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.view.ViewParent;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Interpolator;
import com.jingdong.app.mall.home.MonitorTouchEventRelativeLayout;
import com.jingdong.app.mall.home.o.a.f;
import com.jingdong.app.mall.home.pullrefresh.BaseLoadingView;
import com.jingdong.app.mall.home.pulltorefresh.JDHomeBaseLoadingView;
import com.jingdong.app.mall.home.widget.HomePullRefreshRecyclerView;

/* loaded from: classes4.dex */
public class a {
    private ValueAnimator a;
    private HomePullRefreshRecyclerView b;

    /* renamed from: c  reason: collision with root package name */
    private int f11097c;

    /* renamed from: e  reason: collision with root package name */
    private HomeXview f11098e;

    /* renamed from: f  reason: collision with root package name */
    private JDHomeBaseLoadingView f11099f;
    private Interpolator d = new AccelerateDecelerateInterpolator();

    /* renamed from: g  reason: collision with root package name */
    private ValueAnimator.AnimatorUpdateListener f11100g = new C0337a();

    /* renamed from: h  reason: collision with root package name */
    private AnimatorListenerAdapter f11101h = new b();

    /* renamed from: com.jingdong.app.mall.home.xview.a$a  reason: collision with other inner class name */
    /* loaded from: classes4.dex */
    class C0337a implements ValueAnimator.AnimatorUpdateListener {
        C0337a() {
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            int intValue = ((Integer) valueAnimator.getAnimatedValue()).intValue();
            a.this.f11098e.setY(intValue);
            a.this.b.setScrollY(-((-a.this.f11097c) - Math.abs(intValue)));
        }
    }

    /* loaded from: classes4.dex */
    class b extends AnimatorListenerAdapter {
        b() {
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator animator) {
            a.this.k(false);
            a.this.f11099f.F(true);
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            a.this.k(false);
            a.this.f11098e.q(false);
            a.this.b.u0(true);
            a.this.f11099f.F(true);
            a.this.h();
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            a.this.k(true);
            a.this.f11098e.q(true);
            a.this.b.u0(false);
            a.this.f11099f.F(false);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void h() {
        HomeXview homeXview = this.f11098e;
        if (homeXview != null) {
            homeXview.setY(0.0f);
            this.f11098e.closeXView();
            this.b.t0();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void k(boolean z) {
        ViewParent parent = this.b.getParent();
        if (parent == null || !(parent instanceof MonitorTouchEventRelativeLayout)) {
            return;
        }
        MonitorTouchEventRelativeLayout monitorTouchEventRelativeLayout = (MonitorTouchEventRelativeLayout) parent;
        monitorTouchEventRelativeLayout.d(!z);
        monitorTouchEventRelativeLayout.e(z);
    }

    public void g() {
        ValueAnimator valueAnimator = this.a;
        if (valueAnimator == null) {
            return;
        }
        valueAnimator.cancel();
    }

    public void i(HomePullRefreshRecyclerView homePullRefreshRecyclerView, HomeXview homeXview, int i2) {
        if (homePullRefreshRecyclerView == null || homeXview == null) {
            return;
        }
        this.b = homePullRefreshRecyclerView;
        this.f11098e = homeXview;
        BaseLoadingView m2 = homePullRefreshRecyclerView.m();
        f.n(m2);
        this.f11099f = (JDHomeBaseLoadingView) m2;
        int i3 = -this.f11098e.getHeight();
        this.f11097c = i3;
        ValueAnimator ofInt = ValueAnimator.ofInt(0, i3);
        this.a = ofInt;
        ofInt.setDuration(i2);
        this.a.setInterpolator(this.d);
        this.a.addUpdateListener(this.f11100g);
        this.a.addListener(this.f11101h);
    }

    public boolean j() {
        ValueAnimator valueAnimator = this.a;
        return valueAnimator != null && valueAnimator.isRunning();
    }

    public void l() {
        ValueAnimator valueAnimator = this.a;
        if (valueAnimator == null) {
            return;
        }
        valueAnimator.start();
    }
}

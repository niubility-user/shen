package com.jingdong.app.mall.home.pulltorefresh;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.view.ViewParent;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Interpolator;
import com.jingdong.app.mall.home.MonitorTouchEventRelativeLayout;
import com.jingdong.app.mall.home.floor.common.d;
import com.jingdong.app.mall.home.floor.common.i.m;
import com.jingdong.app.mall.home.floor.ctrl.h;
import com.jingdong.app.mall.home.floor.ctrl.t.i;
import com.jingdong.app.mall.home.floor.ctrl.t.j;
import com.jingdong.app.mall.home.floor.ctrl.t.p;
import com.jingdong.app.mall.home.floor.model.entity.HomeWebFloorEntity;
import com.jingdong.app.mall.home.o.a.f;
import com.jingdong.app.mall.home.widget.HomePullRefreshRecyclerView;
import com.jingdong.common.recommend.RecommendMtaUtils;
import com.jingdong.jdsdk.mta.JDMtaUtils;

/* loaded from: classes4.dex */
public class b {
    private ValueAnimator a;
    private JDHomeBaseLoadingView b;

    /* renamed from: c  reason: collision with root package name */
    private HomePullRefreshRecyclerView f10583c;
    private float d;

    /* renamed from: e  reason: collision with root package name */
    private Interpolator f10584e = new AccelerateDecelerateInterpolator();

    /* renamed from: f  reason: collision with root package name */
    private boolean f10585f = false;

    /* renamed from: g  reason: collision with root package name */
    private AnimatorListenerAdapter f10586g = new a();

    /* renamed from: h  reason: collision with root package name */
    private ValueAnimator.AnimatorUpdateListener f10587h = new C0315b();

    /* loaded from: classes4.dex */
    class a extends AnimatorListenerAdapter {
        a() {
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator animator) {
            b.this.f10585f = true;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            String str;
            b.this.h(false);
            b.this.f10583c.u0(true);
            if (b.this.f10585f) {
                b.this.f10585f = false;
                return;
            }
            str = "";
            j n2 = i.p().n(1);
            if (n2 instanceof p) {
                HomeWebFloorEntity j2 = ((p) n2).j();
                str = j2 != null ? j2.sourceValue : "";
                n2.b();
            }
            JDMtaUtils.sendCommonData(b.this.b.getContext(), "Home_PullDown", str, "", this, "", "", "", RecommendMtaUtils.Home_PageId);
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            b.this.h(true);
            b.this.f10583c.u0(false);
        }
    }

    /* renamed from: com.jingdong.app.mall.home.pulltorefresh.b$b  reason: collision with other inner class name */
    /* loaded from: classes4.dex */
    class C0315b implements ValueAnimator.AnimatorUpdateListener {
        C0315b() {
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            b.this.f10583c.setScrollY((int) ((Float) valueAnimator.getAnimatedValue()).floatValue());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void h(boolean z) {
        ViewParent parent = this.f10583c.getParent();
        if (parent instanceof MonitorTouchEventRelativeLayout) {
            MonitorTouchEventRelativeLayout monitorTouchEventRelativeLayout = (MonitorTouchEventRelativeLayout) parent;
            monitorTouchEventRelativeLayout.d(!z);
            monitorTouchEventRelativeLayout.e(z);
        }
    }

    public void f() {
        ValueAnimator valueAnimator = this.a;
        if (valueAnimator == null) {
            return;
        }
        valueAnimator.cancel();
    }

    public void g(JDHomeBaseLoadingView jDHomeBaseLoadingView, float f2, int i2) {
        if (jDHomeBaseLoadingView == null || jDHomeBaseLoadingView.getParent() == null || !(jDHomeBaseLoadingView.getParent() instanceof HomePullRefreshRecyclerView)) {
            return;
        }
        this.b = jDHomeBaseLoadingView;
        ViewParent parent = jDHomeBaseLoadingView.getParent();
        f.n(parent);
        this.f10583c = (HomePullRefreshRecyclerView) parent;
        float Q = (-m.v(this.b.getContext())) + (h.N().P() > 0 ? h.Q() - d.d(h.p) : 0);
        this.d = Q;
        ValueAnimator ofFloat = ValueAnimator.ofFloat(f2, Q);
        this.a = ofFloat;
        ofFloat.setDuration(i2);
        this.a.setInterpolator(this.f10584e);
        this.a.addUpdateListener(this.f10587h);
        this.a.addListener(this.f10586g);
    }

    public void i() {
        ValueAnimator valueAnimator = this.a;
        if (valueAnimator == null) {
            return;
        }
        valueAnimator.start();
    }
}

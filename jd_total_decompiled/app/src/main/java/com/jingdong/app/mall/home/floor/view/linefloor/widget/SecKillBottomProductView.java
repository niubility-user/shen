package com.jingdong.app.mall.home.floor.view.linefloor.widget;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewParent;
import android.view.animation.LinearInterpolator;
import android.widget.RelativeLayout;
import com.jd.stat.network.ExceptionEnum;
import com.jingdong.app.mall.home.floor.view.b.d;
import com.jingdong.app.mall.home.floor.view.linefloor.base.BaseAnimateLayout;
import com.jingdong.app.mall.home.floor.view.linefloor.floor.LadySecKillView;
import com.jingdong.app.mall.home.r.e.f;
import com.jingdong.sdk.platform.business.personal.R2;

/* loaded from: classes4.dex */
public class SecKillBottomProductView extends RelativeLayout implements com.jingdong.app.mall.home.l.a {

    /* renamed from: g  reason: collision with root package name */
    private Context f9921g;

    /* renamed from: h  reason: collision with root package name */
    private LadySecKillView f9922h;

    /* renamed from: i  reason: collision with root package name */
    private com.jingdong.app.mall.home.floor.view.b.g.b f9923i;

    /* renamed from: j  reason: collision with root package name */
    private SecKillProductAbstractLayout[] f9924j;

    /* renamed from: k  reason: collision with root package name */
    private ValueAnimator f9925k;

    /* renamed from: l  reason: collision with root package name */
    private boolean f9926l;

    /* renamed from: m  reason: collision with root package name */
    private Handler f9927m;

    /* renamed from: n  reason: collision with root package name */
    private com.jingdong.app.mall.home.o.a.b f9928n;
    private boolean o;

    /* loaded from: classes4.dex */
    class a extends com.jingdong.app.mall.home.o.a.b {
        a() {
        }

        @Override // com.jingdong.app.mall.home.o.a.b
        public void safeRun() {
            if (SecKillBottomProductView.this.f9925k != null) {
                SecKillBottomProductView.this.p(true);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class b implements View.OnClickListener {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ com.jingdong.app.mall.home.floor.view.b.g.b f9930g;

        /* renamed from: h  reason: collision with root package name */
        final /* synthetic */ int f9931h;

        b(com.jingdong.app.mall.home.floor.view.b.g.b bVar, int i2) {
            this.f9930g = bVar;
            this.f9931h = i2;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            this.f9930g.t0(SecKillBottomProductView.this.f9924j[this.f9931h].r(), SecKillBottomProductView.this.f9921g, true);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class c implements ValueAnimator.AnimatorUpdateListener {
        c() {
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            SecKillProductAbstractLayout secKillProductAbstractLayout;
            int intValue = ((Integer) valueAnimator.getAnimatedValue()).intValue();
            if (intValue >= 0 && intValue <= 450) {
                secKillProductAbstractLayout = SecKillBottomProductView.this.f9924j[0];
            } else if (intValue < 1200 || intValue > 1600) {
                secKillProductAbstractLayout = null;
            } else {
                secKillProductAbstractLayout = SecKillBottomProductView.this.f9924j[1];
                intValue += ExceptionEnum.SSLEXCEPTION;
            }
            if (secKillProductAbstractLayout == null || !secKillProductAbstractLayout.s()) {
                return;
            }
            secKillProductAbstractLayout.v(intValue);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class d implements Animator.AnimatorListener {
        d() {
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator animator) {
            SecKillBottomProductView.this.f9926l = true;
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            SecKillProductAbstractLayout secKillProductAbstractLayout = SecKillBottomProductView.this.f9924j[0];
            if (secKillProductAbstractLayout != null && secKillProductAbstractLayout.s()) {
                secKillProductAbstractLayout.t();
            }
            SecKillProductAbstractLayout secKillProductAbstractLayout2 = SecKillBottomProductView.this.f9924j[1];
            if (secKillProductAbstractLayout2 != null && secKillProductAbstractLayout2.s()) {
                secKillProductAbstractLayout2.t();
            }
            if (SecKillBottomProductView.this.f9926l || SecKillBottomProductView.this.f9923i == null || SecKillBottomProductView.this.f9923i.O() <= 0) {
                SecKillBottomProductView.this.f9926l = false;
            } else {
                SecKillBottomProductView.this.f9927m.postDelayed(SecKillBottomProductView.this.f9928n, SecKillBottomProductView.this.f9923i.O() * 1000);
            }
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationRepeat(Animator animator) {
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            SecKillProductAbstractLayout secKillProductAbstractLayout = SecKillBottomProductView.this.f9924j[0];
            if (secKillProductAbstractLayout != null && secKillProductAbstractLayout.s()) {
                secKillProductAbstractLayout.u();
            }
            SecKillProductAbstractLayout secKillProductAbstractLayout2 = SecKillBottomProductView.this.f9924j[1];
            if (secKillProductAbstractLayout2 == null || !secKillProductAbstractLayout2.s()) {
                return;
            }
            secKillProductAbstractLayout2.u();
        }
    }

    public SecKillBottomProductView(Context context, LadySecKillView ladySecKillView) {
        super(context);
        this.f9924j = new SecKillProductAbstractLayout[3];
        this.f9927m = new Handler(Looper.getMainLooper());
        this.f9928n = new a();
        this.f9921g = context;
        this.f9922h = ladySecKillView;
    }

    private void l() {
        this.f9927m.removeCallbacks(this.f9928n);
        ValueAnimator valueAnimator = this.f9925k;
        if (valueAnimator != null) {
            valueAnimator.cancel();
        }
        this.o = false;
    }

    private BaseAnimateLayout m(ViewParent viewParent) {
        if (viewParent == null) {
            return null;
        }
        if (viewParent instanceof BaseAnimateLayout) {
            return (BaseAnimateLayout) viewParent;
        }
        return m(viewParent.getParent());
    }

    private void n() {
        if (this.f9925k == null) {
            ValueAnimator ofInt = ValueAnimator.ofInt(0, R2.attr.ptrMode);
            this.f9925k = ofInt;
            ofInt.addUpdateListener(new c());
            this.f9925k.addListener(new d());
            this.f9925k.setDuration(1600L);
            this.f9925k.setInterpolator(new LinearInterpolator());
            return;
        }
        l();
    }

    @Override // com.jingdong.app.mall.home.l.a
    public void c(int i2, String str, long j2) {
        BaseAnimateLayout m2;
        String d2 = this.f9923i.d(this.f9923i.g(0), i2 == 101);
        boolean L = this.f9923i.L(d2);
        SecKillProductAbstractLayout[] secKillProductAbstractLayoutArr = this.f9924j;
        SecKillProductAbstractLayout secKillProductAbstractLayout = secKillProductAbstractLayoutArr[0];
        SecKillProductAbstractLayout secKillProductAbstractLayout2 = secKillProductAbstractLayoutArr[1];
        if (secKillProductAbstractLayout == null || secKillProductAbstractLayout2 == null || !L) {
            return;
        }
        secKillProductAbstractLayout.o(d2, 0);
        secKillProductAbstractLayout2.o(d2, 1);
        if (!TextUtils.equals(d2, BaseAnimateLayout.f9827n) || (m2 = m(getParent())) == null) {
            return;
        }
        m2.p(d2);
    }

    @Override // com.jingdong.app.mall.home.l.a
    public boolean d(String str) {
        return true;
    }

    public void k(com.jingdong.app.mall.home.floor.view.b.g.b bVar) {
        this.f9923i = bVar;
        f g2 = bVar.g(0);
        if (g2 == null) {
            return;
        }
        n();
        for (int i2 = 0; i2 < 2; i2++) {
            d.a aVar = bVar.h0()[i2];
            com.jingdong.app.mall.home.floor.common.f fVar = aVar.b;
            SecKillProductAbstractLayout[] secKillProductAbstractLayoutArr = this.f9924j;
            if (secKillProductAbstractLayoutArr[i2] == null) {
                SecKillProductLayout2Pcs secKillProductLayout2Pcs = new SecKillProductLayout2Pcs(this.f9921g, this);
                secKillProductLayout2Pcs.setContentDescription("\u4eac\u4e1c\u79d2\u6740");
                this.f9924j[i2] = secKillProductLayout2Pcs;
                addView(secKillProductLayout2Pcs, fVar.u(secKillProductLayout2Pcs));
            } else {
                com.jingdong.app.mall.home.floor.common.f.c(secKillProductAbstractLayoutArr[i2], fVar);
            }
            this.f9924j[i2].q(aVar, bVar, i2);
            this.f9924j[i2].setOnClickListener(new b(bVar, i2));
        }
        com.jingdong.app.mall.home.l.b.b().a(g2.s(), this);
    }

    public void o() {
        LadySecKillView ladySecKillView = this.f9922h;
        if (ladySecKillView != null) {
            ladySecKillView.K();
        }
    }

    public void p(boolean z) {
        com.jingdong.app.mall.home.floor.view.b.g.b bVar = this.f9923i;
        if (bVar == null || bVar.O() <= 0 || this.f9923i.U(3) == null) {
            return;
        }
        if (z || !this.o) {
            this.o = true;
            this.f9925k.start();
        }
    }
}

package com.jingdong.app.mall.home.floor.ctrl;

import android.content.Context;
import android.widget.RelativeLayout;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.app.mall.home.JDHomeFragment;
import com.jingdong.app.mall.home.floor.bubble.FloatBubbleLayout;
import com.jingdong.app.mall.home.floor.bubble.a;
import com.jingdong.common.recommend.RecommendMtaUtils;
import com.jingdong.sdk.platform.business.personal.R2;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes4.dex */
public class b {
    public static AtomicBoolean d = new AtomicBoolean(true);

    /* renamed from: e  reason: collision with root package name */
    public static AtomicBoolean f9374e = new AtomicBoolean(false);

    /* renamed from: f  reason: collision with root package name */
    public static AtomicBoolean f9375f = new AtomicBoolean(false);

    /* renamed from: g  reason: collision with root package name */
    public static AtomicBoolean f9376g = new AtomicBoolean(false);
    private com.jingdong.app.mall.home.floor.common.f a;
    private FloatBubbleLayout b;

    /* renamed from: c  reason: collision with root package name */
    private int f9377c = 8;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class a extends com.jingdong.app.mall.home.o.a.b {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ RelativeLayout f9378g;

        /* renamed from: h  reason: collision with root package name */
        final /* synthetic */ JDJSONObject f9379h;

        a(RelativeLayout relativeLayout, JDJSONObject jDJSONObject) {
            this.f9378g = relativeLayout;
            this.f9379h = jDJSONObject;
        }

        @Override // com.jingdong.app.mall.home.o.a.b
        protected void safeRun() {
            b.this.d(this.f9378g, this.f9379h);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.jingdong.app.mall.home.floor.ctrl.b$b  reason: collision with other inner class name */
    /* loaded from: classes4.dex */
    public class C0290b extends FloatBubbleLayout {
        C0290b(Context context) {
            super(context);
        }

        @Override // com.jingdong.app.mall.home.floor.bubble.FloatBubbleLayout
        protected boolean c() {
            return (b.f9376g.get() || !JDHomeFragment.O0() || b.this.f9377c == 0) ? false : true;
        }

        @Override // com.jingdong.app.mall.home.floor.bubble.FloatBubbleLayout
        protected void e(SimpleDraweeView simpleDraweeView, int i2) {
            simpleDraweeView.setBackgroundColor(-1);
            com.jingdong.app.mall.home.n.h.e.d(simpleDraweeView, i2);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.jingdong.app.mall.home.floor.bubble.FloatBubbleLayout
        public void r(a.C0284a c0284a) {
            super.r(c0284a);
            b.f9375f.set(true);
        }
    }

    public void b() {
        FloatBubbleLayout floatBubbleLayout = this.b;
        if (floatBubbleLayout != null) {
            floatBubbleLayout.bringToFront();
        }
    }

    public void c(RelativeLayout relativeLayout, JDJSONObject jDJSONObject) {
        f9374e.set(false);
        f9375f.set(false);
        f9376g.set(false);
        this.f9377c = 8;
        FloatBubbleLayout floatBubbleLayout = this.b;
        if (floatBubbleLayout != null) {
            floatBubbleLayout.g();
        }
        if (relativeLayout == null || jDJSONObject == null) {
            return;
        }
        com.jingdong.app.mall.home.o.a.f.E0(new a(relativeLayout, jDJSONObject));
    }

    public void d(RelativeLayout relativeLayout, JDJSONObject jDJSONObject) {
        FloatBubbleLayout floatBubbleLayout = this.b;
        if (floatBubbleLayout != null) {
            floatBubbleLayout.b();
        }
        if (!(1 == jDJSONObject.optInt("pullBubble", 0))) {
            com.jingdong.app.mall.home.floor.common.i.m.K(this.b);
            return;
        }
        if (this.b == null) {
            this.a = new com.jingdong.app.mall.home.floor.common.f(R2.attr.behavior_hideable, 48);
            com.jingdong.app.mall.home.state.old.a.f();
            this.a.E(10, 211, 0, 0);
            this.b = new C0290b(relativeLayout.getContext());
        }
        RelativeLayout.LayoutParams u = this.a.u(this.b);
        u.topMargin = h.A + this.a.n();
        this.b.setLayoutParams(u);
        if (this.b.getParent() == relativeLayout) {
            b();
        } else {
            com.jingdong.app.mall.home.floor.common.i.m.b(relativeLayout, this.b, -1);
        }
        this.b.t("1", RecommendMtaUtils.Home_PageId, 1);
    }

    public void e(boolean z) {
        FloatBubbleLayout floatBubbleLayout = this.b;
        if (floatBubbleLayout == null || floatBubbleLayout.getParent() == null) {
            return;
        }
        this.b.s(z);
    }

    public void f() {
        FloatBubbleLayout floatBubbleLayout = this.b;
        if (floatBubbleLayout == null || floatBubbleLayout.getParent() == null || !JDHomeFragment.O0()) {
            return;
        }
        this.b.v();
    }

    public void g(boolean z) {
        if (d.get() == z) {
            return;
        }
        d.set(z);
        if (z) {
            f();
        } else {
            e(false);
        }
    }

    public void h(boolean z, int i2) {
        if (z && !f9374e.get()) {
            f9374e.set(true);
        }
        if (f9374e.get()) {
            i2 = 8;
        }
        if (this.f9377c == i2) {
            return;
        }
        this.f9377c = i2;
        if (i2 == 0) {
            e(false);
        } else {
            f();
        }
    }
}

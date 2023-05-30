package com.jingdong.app.mall.home.floor.ctrl.guide;

import android.animation.ValueAnimator;
import android.os.SystemClock;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.animation.AccelerateDecelerateInterpolator;
import com.jingdong.app.mall.home.JDHomeFragment;
import com.jingdong.app.mall.home.MonitorTouchEventRelativeLayout;
import com.jingdong.app.mall.home.floor.bottomfloat.BaseFloatPriority;
import com.jingdong.app.mall.home.floor.common.i.g;
import com.jingdong.app.mall.home.floor.model.entity.HomeWebFloorEntity;
import com.jingdong.app.mall.home.floor.model.entity.HomeWebFloorViewEntity;
import com.jingdong.app.mall.home.i;
import com.jingdong.app.mall.home.o.a.h;
import com.jingdong.app.mall.home.pullrefresh.BaseVerticalRefresh;
import com.jingdong.app.mall.home.pulltorefresh.JDHomeLoadingView;
import com.jingdong.app.mall.home.widget.HomePullRefreshRecyclerView;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes4.dex */
public class a {

    /* renamed from: j */
    private static boolean f9427j;

    /* renamed from: k */
    private static boolean f9428k;

    /* renamed from: l */
    private static boolean f9429l;
    private BaseFloatPriority a;
    private HomePullRefreshRecyclerView b;

    /* renamed from: c */
    private PullXViewGuideLayout f9430c;
    private long d;

    /* renamed from: e */
    private long f9431e;

    /* renamed from: f */
    private int f9432f;

    /* renamed from: g */
    private String f9433g;

    /* renamed from: h */
    private final AtomicBoolean f9434h;

    /* renamed from: i */
    private final AtomicBoolean f9435i;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.jingdong.app.mall.home.floor.ctrl.guide.a$a */
    /* loaded from: classes4.dex */
    public class C0292a extends com.jingdong.app.mall.home.o.a.b {

        /* renamed from: g */
        final /* synthetic */ HomeWebFloorEntity f9436g;

        /* renamed from: h */
        final /* synthetic */ HomeWebFloorViewEntity f9437h;

        /* renamed from: i */
        final /* synthetic */ HomePullRefreshRecyclerView f9438i;

        /* renamed from: j */
        final /* synthetic */ JDHomeLoadingView f9439j;

        C0292a(HomeWebFloorEntity homeWebFloorEntity, HomeWebFloorViewEntity homeWebFloorViewEntity, HomePullRefreshRecyclerView homePullRefreshRecyclerView, JDHomeLoadingView jDHomeLoadingView) {
            a.this = r1;
            this.f9436g = homeWebFloorEntity;
            this.f9437h = homeWebFloorViewEntity;
            this.f9438i = homePullRefreshRecyclerView;
            this.f9439j = jDHomeLoadingView;
        }

        @Override // com.jingdong.app.mall.home.o.a.b
        protected void safeRun() {
            a.this.i(this.f9436g, this.f9437h, this.f9438i, this.f9439j);
        }
    }

    /* loaded from: classes4.dex */
    public class b extends BaseFloatPriority {
        b(a aVar, String str, int i2) {
            super(str, i2);
        }

        @Override // com.jingdong.app.mall.home.floor.bottomfloat.BaseFloatPriority
        protected void g(int i2) {
        }

        @Override // com.jingdong.app.mall.home.floor.bottomfloat.BaseFloatPriority
        public void h() {
        }
    }

    /* loaded from: classes4.dex */
    public class c extends com.jingdong.app.mall.home.o.a.b {
        c() {
            a.this = r1;
        }

        @Override // com.jingdong.app.mall.home.o.a.b
        protected void safeRun() {
            if (a.f9427j) {
                return;
            }
            a.this.h(true);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class d extends com.jingdong.app.mall.home.o.a.b {
        d() {
            a.this = r1;
        }

        @Override // com.jingdong.app.mall.home.o.a.b
        protected void safeRun() {
            a.this.p();
        }
    }

    /* loaded from: classes4.dex */
    public class e implements ValueAnimator.AnimatorUpdateListener {

        /* renamed from: g */
        final /* synthetic */ float f9443g;

        e(float f2) {
            a.this = r1;
            this.f9443g = f2;
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            a.this.b.f(this.f9443g, ((Float) valueAnimator.getAnimatedValue()).floatValue());
        }
    }

    /* loaded from: classes4.dex */
    public static class f {
        static a a = new a(null);
    }

    /* synthetic */ a(C0292a c0292a) {
        this();
    }

    private boolean f() {
        JDHomeFragment z0;
        if (this.f9435i.get() || f9427j || this.f9430c == null || this.b == null || !JDHomeFragment.O0() || i.i() || this.b.s() != BaseVerticalRefresh.l.RESET || (z0 = JDHomeFragment.z0()) == null || z0.F0() > 0 || !this.f9430c.m()) {
            return false;
        }
        MotionEvent a = MonitorTouchEventRelativeLayout.a();
        return a == null || !(a.getAction() == 2 || a.getAction() == 7 || a.getAction() == 0);
    }

    public void h(boolean z) {
        PullXViewGuideLayout pullXViewGuideLayout;
        com.jingdong.app.mall.home.o.a.f.r0(this, " close gide");
        BaseFloatPriority baseFloatPriority = this.a;
        if (baseFloatPriority != null) {
            baseFloatPriority.b(true);
        }
        if (!z || (pullXViewGuideLayout = this.f9430c) == null) {
            return;
        }
        pullXViewGuideLayout.o();
    }

    public void i(HomeWebFloorEntity homeWebFloorEntity, HomeWebFloorViewEntity homeWebFloorViewEntity, HomePullRefreshRecyclerView homePullRefreshRecyclerView, JDHomeLoadingView jDHomeLoadingView) {
        if (f9429l || f9427j || h.c() || com.jingdong.app.mall.home.floor.bottompop.b.a()) {
            return;
        }
        this.b = homePullRefreshRecyclerView;
        this.f9430c = jDHomeLoadingView.V();
        String d2 = PullXViewGuideVideo.d(homeWebFloorViewEntity.getJsonString("videoId"));
        String jsonString = homeWebFloorViewEntity.getJsonString("guideImg");
        boolean z = TextUtils.isEmpty(d2) && TextUtils.isEmpty(jsonString);
        if (f() && !z && homeWebFloorEntity.isSameType()) {
            b bVar = new b(this, "\u4e0b\u62c9XView\u5f15\u5bfc\u89c6\u9891", 13);
            this.a = bVar;
            if (bVar.a()) {
                this.a.l();
                this.f9431e = homeWebFloorEntity.mParseTime;
                long j2 = com.jingdong.app.mall.home.n.h.c.j(homeWebFloorViewEntity.getJsonString("showGuideVideoTime"), 500);
                this.d = j2;
                this.d = Math.max(j2, 0L);
                this.f9430c.h(homeWebFloorViewEntity, d2, jsonString);
                com.jingdong.app.mall.home.o.a.f.F0(new c(), 60000L);
            }
        }
    }

    public static a j() {
        return f.a;
    }

    public void p() {
        if (f() && this.f9430c.k()) {
            com.jingdong.app.mall.home.v.c.a.b(this.f9430c, true);
            com.jingdong.app.mall.home.o.a.f.m(this.f9434h);
            f9427j = true;
            f9428k = true;
            com.jingdong.app.mall.home.floor.view.b.f.d.c(this.f9433g, this.f9432f);
            float d2 = com.jingdong.app.mall.home.floor.common.d.d(140);
            float l2 = this.b.l() * d2;
            this.f9430c.q(d2);
            this.f9430c.s();
            ValueAnimator ofFloat = ValueAnimator.ofFloat(0.0f, l2);
            ofFloat.setDuration(300L);
            ofFloat.setInterpolator(new AccelerateDecelerateInterpolator());
            ofFloat.addUpdateListener(new e(l2));
            ofFloat.start();
            return;
        }
        h(false);
    }

    public void g() {
        long elapsedRealtime = this.d - (SystemClock.elapsedRealtime() - this.f9431e);
        d dVar = new d();
        if (elapsedRealtime <= 0) {
            elapsedRealtime = 0;
        }
        com.jingdong.app.mall.home.o.a.f.F0(dVar, elapsedRealtime);
    }

    public void k() {
        l(false);
    }

    public void l(boolean z) {
        if (f9428k) {
            f9428k = false;
            HomePullRefreshRecyclerView homePullRefreshRecyclerView = this.b;
            if (homePullRefreshRecyclerView != null) {
                homePullRefreshRecyclerView.g(z);
            }
            PullXViewGuideLayout pullXViewGuideLayout = this.f9430c;
            if (pullXViewGuideLayout != null) {
                pullXViewGuideLayout.f(false);
            }
        }
    }

    public void m(HomeWebFloorEntity homeWebFloorEntity, HomeWebFloorViewEntity homeWebFloorViewEntity, HomePullRefreshRecyclerView homePullRefreshRecyclerView, JDHomeLoadingView jDHomeLoadingView) {
        if (com.jingdong.app.mall.home.o.a.f.M0() && !f9427j && !f9429l && !h.c() && !g.s() && !com.jingdong.app.mall.home.floor.bottompop.b.a()) {
            if (this.f9435i.get() || com.jingdong.app.mall.home.state.old.a.f() || com.jingdong.app.mall.home.floor.ctrl.t.i.p().v() || com.jingdong.app.mall.home.floor.ctrl.t.c.r() || homeWebFloorEntity == null || homeWebFloorViewEntity == null || homePullRefreshRecyclerView == null || jDHomeLoadingView == null || !homeWebFloorEntity.isSameType()) {
                return;
            }
            this.f9432f = com.jingdong.app.mall.home.n.h.c.h(homeWebFloorViewEntity.getJsonString("userShowTimes"), 1);
            String concat = "pullXVGuide".concat(homeWebFloorEntity.sourceValue);
            this.f9433g = concat;
            if (com.jingdong.app.mall.home.floor.view.b.f.d.g(concat, this.f9432f)) {
                com.jingdong.app.mall.home.o.a.f.F0(new C0292a(homeWebFloorEntity, homeWebFloorViewEntity, homePullRefreshRecyclerView, jDHomeLoadingView), 450L);
                return;
            }
            return;
        }
        o();
    }

    public void n() {
        f9429l = true;
    }

    public void o() {
        if (this.f9435i.getAndSet(true)) {
            return;
        }
        com.jingdong.app.mall.home.o.a.f.K0(this.f9434h);
        h(true);
        k();
    }

    private a() {
        this.f9434h = new AtomicBoolean(false);
        this.f9435i = new AtomicBoolean(false);
    }
}

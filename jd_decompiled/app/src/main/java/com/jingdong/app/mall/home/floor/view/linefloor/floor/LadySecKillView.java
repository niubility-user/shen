package com.jingdong.app.mall.home.floor.view.linefloor.floor;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.view.View;
import android.widget.RelativeLayout;
import androidx.annotation.NonNull;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jingdong.app.mall.home.base.HomeDraweeView;
import com.jingdong.app.mall.home.floor.common.MallFloorEvent;
import com.jingdong.app.mall.home.floor.common.i.m;
import com.jingdong.app.mall.home.floor.ctrl.e;
import com.jingdong.app.mall.home.floor.view.b.g.b;
import com.jingdong.app.mall.home.floor.view.linefloor.base.BaseLineLayout;
import com.jingdong.app.mall.home.floor.view.linefloor.widget.LadySecKillTitle;
import com.jingdong.app.mall.home.floor.view.linefloor.widget.SecKillBottomProductView;
import com.jingdong.app.mall.home.o.a.f;
import com.jingdong.cleanmvp.common.BaseEvent;

/* loaded from: classes4.dex */
public class LadySecKillView extends BaseLineLayout<com.jingdong.app.mall.home.floor.view.b.g.b> implements b.InterfaceC0300b {
    private LadySecKillTitle s;
    private SecKillBottomProductView t;
    private View u;
    private SimpleDraweeView v;
    private com.jingdong.app.mall.home.floor.view.b.g.b w;
    private boolean x;

    /* loaded from: classes4.dex */
    public class a implements View.OnClickListener {
        a() {
            LadySecKillView.this = r1;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            LadySecKillView.this.w.t0(0, ((BaseLineLayout) LadySecKillView.this).o, false);
        }
    }

    /* loaded from: classes4.dex */
    public class b extends com.jingdong.app.mall.home.o.a.b {

        /* renamed from: g */
        final /* synthetic */ com.jingdong.app.mall.home.floor.view.b.g.b f9862g;

        b(com.jingdong.app.mall.home.floor.view.b.g.b bVar) {
            LadySecKillView.this = r1;
            this.f9862g = bVar;
        }

        @Override // com.jingdong.app.mall.home.o.a.b
        protected void safeRun() {
            LadySecKillView.this.b(this.f9862g);
        }
    }

    /* loaded from: classes4.dex */
    public class c implements e.b {
        c() {
            LadySecKillView.this = r1;
        }

        @Override // com.jingdong.app.mall.home.floor.ctrl.e.b
        public void onFailed(String str, View view) {
            LadySecKillView.this.u.setAlpha(1.0f);
        }

        @Override // com.jingdong.app.mall.home.floor.ctrl.e.b
        public void onStart(String str, View view) {
        }

        @Override // com.jingdong.app.mall.home.floor.ctrl.e.b
        public void onSuccess(String str, View view) {
            LadySecKillView.this.u.setAlpha(0.0f);
        }
    }

    /* loaded from: classes4.dex */
    public class d implements View.OnClickListener {

        /* renamed from: g */
        final /* synthetic */ com.jingdong.app.mall.home.floor.view.b.g.b f9864g;

        d(com.jingdong.app.mall.home.floor.view.b.g.b bVar) {
            LadySecKillView.this = r1;
            this.f9864g = bVar;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            this.f9864g.t0(0, ((BaseLineLayout) LadySecKillView.this).o, false);
        }
    }

    public LadySecKillView(Context context, com.jingdong.app.mall.home.floor.view.b.a aVar) {
        super(context, aVar);
        f.G0(this);
    }

    private void B(com.jingdong.app.mall.home.floor.common.f fVar, com.jingdong.app.mall.home.floor.view.b.g.b bVar) {
        if (bVar.a == com.jingdong.app.mall.home.floor.view.b.c.SPECIAL) {
            return;
        }
        SimpleDraweeView simpleDraweeView = this.v;
        if (simpleDraweeView == null) {
            HomeDraweeView homeDraweeView = new HomeDraweeView(this.o);
            this.v = homeDraweeView;
            RelativeLayout.LayoutParams u = fVar.u(homeDraweeView);
            u.addRule(12);
            this.v.setLayoutParams(u);
            m.b(this, this.v, 0);
        } else {
            com.jingdong.app.mall.home.floor.common.f.d(simpleDraweeView, fVar, true);
        }
        this.u.setAlpha(1.0f);
        com.jingdong.app.mall.home.n.h.e.a(this.v, com.jingdong.app.mall.home.floor.common.d.d(12));
        e.p(this.v, bVar.V(), e.b, new c());
    }

    private void C(com.jingdong.app.mall.home.floor.view.b.g.b bVar) {
        int i2 = bVar.z() ? 192 : 169;
        if (bVar.F()) {
            i2 = 0;
        }
        com.jingdong.app.mall.home.floor.common.f fVar = new com.jingdong.app.mall.home.floor.common.f(326, i2);
        fVar.E(24, 0, 0, 24);
        E(bVar);
        D(fVar, bVar);
        B(fVar, bVar);
    }

    private void D(com.jingdong.app.mall.home.floor.common.f fVar, com.jingdong.app.mall.home.floor.view.b.g.b bVar) {
        if (bVar.a == com.jingdong.app.mall.home.floor.view.b.c.SPECIAL) {
            return;
        }
        View view = this.u;
        if (view == null) {
            View view2 = new View(this.o);
            this.u = view2;
            RelativeLayout.LayoutParams u = fVar.u(view2);
            u.addRule(12);
            addView(this.u, u);
        } else {
            com.jingdong.app.mall.home.floor.common.f.d(view, fVar, true);
        }
        this.u.setBackgroundColor(0);
        if (!bVar.a.useSkuMask()) {
            this.u.setAlpha(0.0f);
            return;
        }
        int[] n2 = bVar.n(0);
        if (n2.length < 1) {
            this.u.setAlpha(0.0f);
            return;
        }
        com.jingdong.app.mall.home.n.h.e.a(this.u, com.jingdong.app.mall.home.floor.common.d.d(12));
        GradientDrawable gradientDrawable = new GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM, n2.length < 3 ? com.jingdong.app.mall.home.floor.view.b.h.a.f(n2, 0, 25) : com.jingdong.app.mall.home.floor.view.b.h.a.f(n2, 0, 0, 25));
        this.u.setAlpha(1.0f);
        this.u.setBackgroundDrawable(gradientDrawable);
    }

    private void E(com.jingdong.app.mall.home.floor.view.b.g.b bVar) {
        com.jingdong.app.mall.home.floor.common.f P = bVar.P();
        SecKillBottomProductView secKillBottomProductView = this.t;
        if (secKillBottomProductView == null) {
            SecKillBottomProductView secKillBottomProductView2 = new SecKillBottomProductView(this.o, this);
            this.t = secKillBottomProductView2;
            addView(secKillBottomProductView2, P.u(secKillBottomProductView2));
        } else {
            com.jingdong.app.mall.home.floor.common.f.d(secKillBottomProductView, P, true);
        }
        com.jingdong.app.mall.home.n.h.e.d(this.t, com.jingdong.app.mall.home.floor.common.d.d(12));
        this.t.k(bVar);
    }

    private void F(boolean z, @NonNull com.jingdong.app.mall.home.floor.view.b.g.b bVar, int i2) {
        this.w = bVar;
        bVar.x0(this);
        G(bVar);
        C(bVar);
        L();
        M();
        if (z && this.x) {
            K();
        }
        setOnClickListener(new a());
    }

    private void G(com.jingdong.app.mall.home.floor.view.b.g.b bVar) {
        com.jingdong.app.mall.home.floor.common.f p0 = bVar.p0();
        LadySecKillTitle ladySecKillTitle = this.s;
        if (ladySecKillTitle == null) {
            LadySecKillTitle ladySecKillTitle2 = new LadySecKillTitle(this.o);
            this.s = ladySecKillTitle2;
            addView(ladySecKillTitle2, p0.u(ladySecKillTitle2));
        } else {
            com.jingdong.app.mall.home.floor.common.f.d(ladySecKillTitle, p0, true);
        }
        this.s.k(bVar);
        this.s.setOnClickListener(new d(bVar));
        if (Build.VERSION.SDK_INT >= 16) {
            this.s.setImportantForAccessibility(2);
        }
    }

    private void I() {
        this.s.v();
        if (m.I(this, com.jingdong.app.mall.home.a.f8560i, com.jingdong.app.mall.home.a.f8562k, false)) {
            L();
        }
    }

    private void J(int i2, int i3) {
        if (m.H(this, i2, i3, 100, true)) {
            return;
        }
        this.s.v();
    }

    private void L() {
        com.jingdong.app.mall.home.r.c.a.y("Home_SeckillExpo", "", this.w.g0().toString());
    }

    @Override // com.jingdong.app.mall.home.floor.view.linefloor.base.BaseLineLayout
    /* renamed from: H */
    public void v(@NonNull com.jingdong.app.mall.home.floor.view.b.g.b bVar, int i2) {
        this.x = false;
        F(false, bVar, i2);
        setContentDescription("\u4eac\u4e1c\u79d2\u6740");
    }

    public void K() {
        this.x = true;
        M();
        N();
    }

    protected void M() {
        if (this.x) {
            this.s.u();
        }
    }

    public void N() {
        SecKillBottomProductView secKillBottomProductView = this.t;
        if (secKillBottomProductView != null) {
            secKillBottomProductView.p(false);
        }
    }

    @Override // com.jingdong.app.mall.home.floor.view.b.g.b.InterfaceC0300b
    public void b(com.jingdong.app.mall.home.floor.view.b.g.b bVar) {
        if (f.p0()) {
            f.E0(new b(bVar));
        } else {
            F(true, bVar, 0);
        }
    }

    public void onEventMainThread(BaseEvent baseEvent) {
        if (baseEvent instanceof MallFloorEvent) {
            MallFloorEvent mallFloorEvent = (MallFloorEvent) baseEvent;
            String type = mallFloorEvent.getType();
            type.hashCode();
            if (type.equals("home_scroll_stop")) {
                J(mallFloorEvent.a(), mallFloorEvent.b());
            } else if (type.equals("home_pause")) {
                I();
            }
        }
    }
}

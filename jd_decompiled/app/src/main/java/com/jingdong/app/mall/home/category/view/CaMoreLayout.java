package com.jingdong.app.mall.home.category.view;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.jingdong.app.mall.home.JDHomeFragment;
import com.jingdong.app.mall.home.R;
import com.jingdong.app.mall.home.base.HomeImageView;
import com.jingdong.app.mall.home.category.adapter.CaAdapter;
import com.jingdong.app.mall.home.floor.common.f;
import com.jingdong.app.mall.home.floor.common.i.m;
import com.jingdong.app.mall.home.floor.ctrl.h;
import com.jingdong.app.mall.home.n.g.l;
import com.jingdong.app.mall.home.n.h.e;
import com.jingdong.common.unification.statusbar.UnStatusBarTintUtil;
import com.jingdong.sdk.platform.business.personal.R2;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.annotation.Nullable;

/* loaded from: classes4.dex */
public class CaMoreLayout extends DrawerLayout {
    private static AtomicBoolean u = new AtomicBoolean(false);
    private static CaMoreLayout v;

    /* renamed from: g */
    private ViewGroup f8750g;

    /* renamed from: h */
    private RecyclerView f8751h;

    /* renamed from: i */
    private CaAdapter f8752i;

    /* renamed from: j */
    private TextView f8753j;

    /* renamed from: k */
    private f f8754k;

    /* renamed from: l */
    private ImageView f8755l;

    /* renamed from: m */
    private f f8756m;

    /* renamed from: n */
    private CaLoadingLayout f8757n;
    private com.jingdong.app.mall.home.category.view.b o;
    private View p;
    private final FrameLayout q;
    private f r;
    private volatile List<com.jingdong.app.mall.home.n.g.u.c> s;
    private final RelativeLayout t;

    /* loaded from: classes4.dex */
    public class a extends com.jingdong.app.mall.home.o.a.b {
        a() {
        }

        @Override // com.jingdong.app.mall.home.o.a.b
        protected void safeRun() {
            CaMoreLayout.s();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class b implements View.OnClickListener {
        b() {
            CaMoreLayout.this = r1;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            CaMoreLayout caMoreLayout = CaMoreLayout.this;
            if (caMoreLayout.isDrawerOpen(caMoreLayout.q) || CaMoreLayout.this.p.getAlpha() == 1.0f) {
                CaMoreLayout.this.m();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class c extends FrameLayout {
        c(CaMoreLayout caMoreLayout, Context context) {
            super(context);
        }

        @Override // android.view.ViewGroup, android.view.View
        protected void dispatchDraw(Canvas canvas) {
            super.dispatchDraw(canvas);
            if (com.jingdong.app.mall.home.state.dark.a.h()) {
                canvas.drawColor(com.jingdong.app.mall.home.state.dark.a.f());
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class d implements DrawerLayout.DrawerListener {
        d() {
            CaMoreLayout.this = r1;
        }

        @Override // androidx.drawerlayout.widget.DrawerLayout.DrawerListener
        public void onDrawerClosed(@NonNull View view) {
            CaMoreLayout.this.e();
        }

        @Override // androidx.drawerlayout.widget.DrawerLayout.DrawerListener
        public void onDrawerOpened(@NonNull View view) {
            CaMoreLayout caMoreLayout = CaMoreLayout.this;
            caMoreLayout.u(null, caMoreLayout.s);
        }

        @Override // androidx.drawerlayout.widget.DrawerLayout.DrawerListener
        public void onDrawerSlide(@NonNull View view, float f2) {
            CaMoreLayout.this.p.setAlpha(f2);
            if (f2 == 1.0f) {
                CaMoreLayout caMoreLayout = CaMoreLayout.this;
                caMoreLayout.u(null, caMoreLayout.s);
            }
            if (f2 == 0.0f) {
                CaMoreLayout.this.e();
            }
        }

        @Override // androidx.drawerlayout.widget.DrawerLayout.DrawerListener
        public void onDrawerStateChanged(int i2) {
        }
    }

    public CaMoreLayout(Context context) {
        super(context);
        setStatusBarBackground(0);
        setScrimColor(0);
        com.jingdong.app.mall.home.o.a.f.n(context);
        this.f8750g = (ViewGroup) ((Activity) context).findViewById(16908290);
        View view = new View(context);
        this.p = view;
        view.setBackgroundColor(-1442840576);
        this.p.setAlpha(0.0f);
        this.p.setOnClickListener(new b());
        addView(this.p, new DrawerLayout.LayoutParams(-1, -1));
        c cVar = new c(this, context);
        this.q = cVar;
        RelativeLayout relativeLayout = new RelativeLayout(context);
        this.t = relativeLayout;
        relativeLayout.setOnClickListener(null);
        e.b(relativeLayout, com.jingdong.app.mall.home.floor.common.d.d(com.jingdong.app.mall.home.n.g.u.c.f10358k));
        cVar.addView(relativeLayout, new FrameLayout.LayoutParams(-1, -1));
        CaLoadingLayout caLoadingLayout = new CaLoadingLayout(context, true);
        this.f8757n = caLoadingLayout;
        relativeLayout.addView(caLoadingLayout, new RelativeLayout.LayoutParams(-1, -1));
        View view2 = new View(context);
        view2.setId(R.id.homefloor_child_item1);
        relativeLayout.addView(view2, new RelativeLayout.LayoutParams(-1, h.A));
        HomeImageView homeImageView = new HomeImageView(context);
        this.f8755l = homeImageView;
        com.jingdong.app.mall.home.floor.ctrl.e.a(homeImageView, "2627");
        f fVar = new f(162, 32);
        this.f8756m = fVar;
        fVar.F(new Rect(16, 64, 0, 0));
        RelativeLayout.LayoutParams u2 = this.f8756m.u(this.f8755l);
        u2.addRule(3, view2.getId());
        relativeLayout.addView(this.f8755l, u2);
        TextView textView = new TextView(context);
        this.f8753j = textView;
        textView.setId(R.id.homefloor_child_item2);
        this.f8753j.setMaxLines(1);
        this.f8753j.setGravity(16);
        this.f8753j.getPaint().setFakeBoldText(true);
        this.f8753j.setTextSize(0, com.jingdong.app.mall.home.floor.common.d.d(34));
        f fVar2 = new f(-1, 80);
        this.f8754k = fVar2;
        fVar2.F(new Rect(50, 40, 0, 0));
        RelativeLayout.LayoutParams u3 = this.f8754k.u(this.f8753j);
        u3.addRule(3, view2.getId());
        relativeLayout.addView(this.f8753j, u3);
        this.f8751h = new RecyclerView(context);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -1);
        layoutParams.addRule(3, this.f8753j.getId());
        relativeLayout.addView(this.f8751h, layoutParams);
        this.r = new f(R2.attr.constraintSet, -1);
        DrawerLayout.LayoutParams layoutParams2 = new DrawerLayout.LayoutParams(this.r.v(), -1);
        layoutParams2.gravity = GravityCompat.END;
        addView(cVar, layoutParams2);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        linearLayoutManager.setAutoMeasureEnabled(true);
        linearLayoutManager.setOrientation(1);
        this.f8751h.setItemAnimator(null);
        this.f8751h.setLayoutManager(linearLayoutManager);
        this.o = new com.jingdong.app.mall.home.category.view.b(context, this, this.f8757n);
        CaAdapter caAdapter = new CaAdapter(context, this, this.f8751h);
        this.f8752i = caAdapter;
        caAdapter.x(false);
        this.f8751h.setAdapter(this.f8752i);
        addDrawerListener(new d());
        setVisibility(4);
        setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
        m.b(this.f8750g, this, -1);
    }

    public boolean e() {
        if (getVisibility() == 0) {
            l();
            this.p.setAlpha(0.0f);
            closeDrawer((View) this.q, false);
            return true;
        }
        return false;
    }

    private void f() {
        if (f.c(this.f8753j, this.f8754k)) {
            f.c(this.f8755l, this.f8756m);
            this.f8753j.setTextSize(0, com.jingdong.app.mall.home.floor.common.d.d(34));
            f.c(this.q, this.r);
            this.q.offsetLeftAndRight(this.r.v());
            e.b(this.t, com.jingdong.app.mall.home.floor.common.d.d(com.jingdong.app.mall.home.n.g.u.c.f10358k));
        }
    }

    public static CaMoreLayout g(Context context) {
        com.jingdong.app.mall.home.o.a.f.n(context);
        View findViewById = ((Activity) context).findViewById(16908290);
        CaMoreLayout caMoreLayout = v;
        if (caMoreLayout == null || caMoreLayout.k() != findViewById) {
            v = new CaMoreLayout(context);
        }
        v.f();
        return v;
    }

    public static void h() {
        CaMoreLayout caMoreLayout = v;
        if (caMoreLayout != null) {
            caMoreLayout.e();
        }
    }

    private void i() {
        JDHomeFragment z0 = JDHomeFragment.z0();
        if (z0 != null) {
            z0.q0();
        }
    }

    @Nullable
    public static CaMoreLayout j() {
        return v;
    }

    private void l() {
        i();
        com.jingdong.app.mall.home.n.g.v.b.d();
        setVisibility(4);
        this.o.h();
        this.f8752i.l();
        com.jingdong.app.mall.home.n.g.v.a.a("ev_more_close");
    }

    private void o(List<com.jingdong.app.mall.home.n.g.u.c> list) {
        JDHomeFragment z0;
        if (this.f8752i.s() && (z0 = JDHomeFragment.z0()) != null && JDHomeFragment.P0() && z0.isAdded()) {
            if (this.p.getAlpha() == 0.0f) {
                this.p.setAlpha(1.0f);
            }
            this.f8753j.setVisibility(0);
            this.f8755l.setVisibility(0);
            this.f8757n.setVisibility(8);
            this.f8752i.w(list);
            this.f8751h.scrollToPosition(0);
        }
    }

    public static boolean p() {
        CaMoreLayout caMoreLayout = v;
        return caMoreLayout != null && caMoreLayout.q();
    }

    public static void r() {
        JDHomeFragment z0 = JDHomeFragment.z0();
        if (z0 != null && z0.isAdded() && JDHomeFragment.P0()) {
            if (u.get()) {
                com.jingdong.app.mall.home.o.a.f.F0(new a(), 800L);
                return;
            }
            return;
        }
        CaMoreLayout caMoreLayout = v;
        if (caMoreLayout != null) {
            caMoreLayout.e();
        }
    }

    public static void s() {
        CaMoreLayout caMoreLayout;
        if (!(u.get() || !JDHomeFragment.P0()) || (caMoreLayout = v) == null) {
            return;
        }
        caMoreLayout.e();
        u.set(false);
    }

    public ViewGroup k() {
        return this.f8750g;
    }

    void m() {
        i();
        closeDrawer(this.q);
    }

    public void n(l lVar) {
        this.f8753j.setTextColor(com.jingdong.app.mall.home.state.dark.a.e(-1, -16777216));
        this.t.setBackgroundColor(com.jingdong.app.mall.home.n.c.d());
        this.f8755l.setVisibility(4);
        this.f8753j.setVisibility(4);
        this.s = null;
        this.f8752i.l();
        this.f8757n.b();
        com.jingdong.app.mall.home.n.g.v.b.d();
        this.o.j(lVar);
        setVisibility(0);
        openDrawer(this.q);
        Context context = getContext();
        com.jingdong.app.mall.home.o.a.f.n(context);
        Activity activity = (Activity) context;
        if (com.jingdong.app.mall.home.state.dark.a.h()) {
            UnStatusBarTintUtil.setStatusBarDarkMode(activity);
        } else {
            UnStatusBarTintUtil.setStatusBarLightMode(activity);
        }
    }

    public boolean q() {
        if (getVisibility() == 0) {
            m();
            return true;
        }
        return false;
    }

    public void t() {
        u.set(true);
        com.jingdong.app.mall.home.n.g.v.b.d();
    }

    public void u(String str, List<com.jingdong.app.mall.home.n.g.u.c> list) {
        if (str != null) {
            this.f8753j.setText(str);
        }
        if (list != null) {
            this.s = list;
        }
        if (this.s != null) {
            o(this.s);
        }
    }
}

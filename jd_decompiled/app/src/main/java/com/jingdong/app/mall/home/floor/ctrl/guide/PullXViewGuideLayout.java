package com.jingdong.app.mall.home.floor.ctrl.guide;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.Shader;
import android.text.TextUtils;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jingdong.app.mall.home.JDHomeFragment;
import com.jingdong.app.mall.home.base.HomeDraweeView;
import com.jingdong.app.mall.home.floor.common.f;
import com.jingdong.app.mall.home.floor.common.i.l;
import com.jingdong.app.mall.home.floor.common.i.m;
import com.jingdong.app.mall.home.floor.ctrl.e;
import com.jingdong.app.mall.home.floor.ctrl.h;
import com.jingdong.app.mall.home.floor.model.entity.HomeWebFloorViewEntity;
import com.jingdong.app.mall.home.n.h.g;
import com.jingdong.app.mall.home.pulltorefresh.JDHomeBaseLoadingView;
import com.jingdong.sdk.platform.business.personal.R2;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes4.dex */
public class PullXViewGuideLayout extends RelativeLayout {
    private static final Rect D = new Rect(0, 1, 0, 0);
    private final Paint A;
    private final Paint B;
    private final Path C;

    /* renamed from: g */
    private AtomicBoolean f9407g;

    /* renamed from: h */
    private PullXViewGuideVideo f9408h;

    /* renamed from: i */
    private SimpleDraweeView f9409i;

    /* renamed from: j */
    private boolean f9410j;

    /* renamed from: k */
    private int f9411k;

    /* renamed from: l */
    private GuideCloseLayout f9412l;

    /* renamed from: m */
    private f f9413m;

    /* renamed from: n */
    private boolean f9414n;
    private int o;
    private float p;
    private float q;
    private float r;
    private boolean s;
    private boolean t;
    private HomeWebFloorViewEntity u;
    private RelativeLayout v;
    private boolean w;
    private boolean x;
    private int y;
    private f z;

    /* loaded from: classes4.dex */
    public class a implements View.OnClickListener {
        a() {
            PullXViewGuideLayout.this = r1;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (PullXViewGuideLayout.this.u != null && "1".equals(PullXViewGuideLayout.this.u.getJsonString("jumpActivityUrl"))) {
                com.jingdong.app.mall.home.floor.ctrl.guide.a.j().l(true);
                if (l.e(PullXViewGuideLayout.this.getContext(), PullXViewGuideLayout.this.u.getJump())) {
                    com.jingdong.app.mall.home.r.c.a.s("Home_XVIEW", PullXViewGuideLayout.this.u.sourceValue, com.jingdong.app.mall.home.r.c.b.c(PullXViewGuideLayout.this.u.srvJson).put("opentype", "1").toString());
                }
            }
        }
    }

    /* loaded from: classes4.dex */
    public class b implements View.OnClickListener {
        b() {
            PullXViewGuideLayout.this = r1;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            PullXViewGuideLayout.this.p("0");
            com.jingdong.app.mall.home.floor.ctrl.guide.a.j().k();
        }
    }

    /* loaded from: classes4.dex */
    public class c implements e.b {
        c() {
            PullXViewGuideLayout.this = r1;
        }

        @Override // com.jingdong.app.mall.home.floor.ctrl.e.b
        public void onFailed(String str, View view) {
            PullXViewGuideLayout.this.f9410j = false;
            com.jingdong.app.mall.home.floor.ctrl.guide.a.j().g();
        }

        @Override // com.jingdong.app.mall.home.floor.ctrl.e.b
        public void onStart(String str, View view) {
        }

        @Override // com.jingdong.app.mall.home.floor.ctrl.e.b
        public void onSuccess(String str, View view) {
            PullXViewGuideLayout.this.f9410j = true;
            com.jingdong.app.mall.home.floor.ctrl.guide.a.j().g();
        }
    }

    /* loaded from: classes4.dex */
    public class d extends com.jingdong.app.mall.home.o.a.b {

        /* renamed from: g */
        final /* synthetic */ int f9417g;

        d(int i2) {
            PullXViewGuideLayout.this = r1;
            this.f9417g = i2;
        }

        @Override // com.jingdong.app.mall.home.o.a.b
        protected void safeRun() {
            if (PullXViewGuideLayout.this.l()) {
                return;
            }
            PullXViewGuideLayout.this.t(this.f9417g - 1);
        }
    }

    public PullXViewGuideLayout(Context context) {
        super(context);
        this.f9407g = new AtomicBoolean(false);
        this.A = new Paint(1);
        this.B = new Paint(1);
        this.C = new Path();
        this.o = com.jingdong.app.mall.home.floor.common.d.f9279g;
        int T = h.T() + com.jingdong.app.mall.home.floor.common.d.d(20);
        this.z = new f(-1, R2.attr.boxStrokeErrorColor);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(this.z.v(), this.z.h());
        int h2 = T - this.z.h();
        this.y = h2;
        layoutParams.topMargin = h2;
        setLayoutParams(layoutParams);
        PullXViewGuideVideo pullXViewGuideVideo = new PullXViewGuideVideo(context);
        this.f9408h = pullXViewGuideVideo;
        addView(pullXViewGuideVideo, new RelativeLayout.LayoutParams(-1, -1));
        setAlpha(0.0f);
        HomeDraweeView homeDraweeView = new HomeDraweeView(context);
        this.f9409i = homeDraweeView;
        homeDraweeView.setScaleType(ImageView.ScaleType.FIT_XY);
        addView(this.f9409i, new RelativeLayout.LayoutParams(-1, -1));
        GuideCloseLayout guideCloseLayout = new GuideCloseLayout(context);
        this.f9412l = guideCloseLayout;
        guideCloseLayout.setAlpha(0.0f);
        f fVar = new f(70, 32);
        this.f9413m = fVar;
        fVar.E(24, 0, 24, 0);
        RelativeLayout.LayoutParams u = this.f9413m.u(this.f9412l);
        u.addRule(11);
        u.addRule(12);
        u.bottomMargin = ((T - h.A) - this.f9413m.m()) - this.f9413m.h();
        addView(this.f9412l, u);
    }

    private void e() {
        int T = h.T() + com.jingdong.app.mall.home.floor.common.d.d(20);
        int h2 = T - this.z.h();
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) getLayoutParams();
        if (h2 != layoutParams.topMargin) {
            this.y = h2;
            layoutParams.topMargin = h2;
            setLayoutParams(layoutParams);
            RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) this.f9412l.getLayoutParams();
            layoutParams2.bottomMargin = ((T - h.A) - this.f9413m.m()) - this.f9413m.h();
            this.f9412l.setLayoutParams(layoutParams2);
        }
    }

    private void g() {
        JDHomeFragment z0 = JDHomeFragment.z0();
        if (z0 == null) {
            this.f9412l.setVisibility(8);
            return;
        }
        RelativeLayout x0 = z0.x0();
        if (x0 == null) {
            this.f9412l.setVisibility(8);
        } else if (this.v == null) {
            RelativeLayout relativeLayout = new RelativeLayout(getContext());
            this.v = relativeLayout;
            relativeLayout.setOnClickListener(new a());
            x0.addView(this.v, new RelativeLayout.LayoutParams(-1, h.T() + com.jingdong.app.mall.home.floor.common.d.d(20) + com.jingdong.app.mall.home.floor.common.d.d(140)));
            View view = new View(getContext());
            view.setOnClickListener(new b());
            RelativeLayout.LayoutParams u = new f(130, 70).u(view);
            u.topMargin = h.A;
            u.addRule(11);
            this.v.addView(view, u);
        }
    }

    private void i(String str) {
        this.f9410j = false;
        e.p(this.f9409i, str, e.b, new c());
    }

    private void j() {
        if (this.f9407g.getAndSet(true)) {
            return;
        }
        this.f9408h.setPivotY(getHeight());
        this.f9408h.setPivotX(getWidth() >> 1);
        this.f9409i.setPivotY(getHeight());
        this.f9409i.setPivotX(getWidth() >> 1);
    }

    public void p(String str) {
        HomeWebFloorViewEntity homeWebFloorViewEntity;
        if (this.w || (homeWebFloorViewEntity = this.u) == null) {
            return;
        }
        this.w = true;
        com.jingdong.app.mall.home.r.c.b c2 = com.jingdong.app.mall.home.r.c.b.c(homeWebFloorViewEntity.srvJson);
        c2.a("closetype", str);
        com.jingdong.app.mall.home.r.c.a.s("Home_XVIEWLeadClose", "", c2.toString());
    }

    private void r(View view, float f2) {
        if (f2 > 100.0f) {
            return;
        }
        view.setScaleX(f2);
        view.setScaleY(f2);
    }

    public void t(int i2) {
        int max = Math.max(i2, 0);
        this.f9412l.tick(i2);
        if (max == 0) {
            if (this.p == this.r) {
                p("1");
                com.jingdong.app.mall.home.floor.ctrl.guide.a.j().k();
                return;
            }
            p("2");
            f(true);
            return;
        }
        com.jingdong.app.mall.home.o.a.f.F0(new d(max), 1000L);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        if (this.f9414n) {
            int d2 = com.jingdong.app.mall.home.floor.common.d.d(R2.anim.pop_in);
            if (this.B.getShader() == null) {
                this.B.setShader(new LinearGradient(0.0f, getHeight() - d2, 0.0f, getHeight(), 0, 637534208, Shader.TileMode.CLAMP));
            }
            canvas.drawRect(0.0f, getHeight() - d2, getWidth(), getHeight(), this.B);
            if (h.C) {
                if (this.A.getShader() == null) {
                    int d3 = com.jingdong.app.mall.home.floor.common.d.d(20);
                    float f2 = d3;
                    float f3 = 0.5522848f * f2;
                    int height = getHeight();
                    int i2 = height - d3;
                    float f4 = height;
                    this.C.moveTo(0.0f, f4);
                    float f5 = i2;
                    g.c(0.0f, f5, f2, f3, this.C);
                    this.C.lineTo(getWidth() - d3, f5);
                    g.f(f5, getWidth(), f2, f3, this.C);
                    this.C.lineTo(0.0f, f4);
                    this.A.setShader(new LinearGradient(0.0f, f5, 0.0f, i2 + com.jingdong.app.mall.home.floor.common.d.f9279g, com.jingdong.app.mall.home.state.dark.a.e(com.jingdong.app.mall.home.state.dark.a.d(), com.jingdong.app.mall.home.a.w), com.jingdong.app.mall.home.state.dark.a.e(com.jingdong.app.mall.home.state.dark.a.d(), com.jingdong.app.mall.home.a.x), Shader.TileMode.CLAMP));
                }
                canvas.drawPath(this.C, this.A);
            }
        }
    }

    public void f(boolean z) {
        this.s = true;
        RelativeLayout relativeLayout = this.v;
        if (relativeLayout != null && relativeLayout.getVisibility() != 8) {
            this.f9412l.animate().alpha(0.0f).start();
            this.v.setVisibility(8);
        }
        if (z && this.f9408h.getAlpha() > 0.95f) {
            this.f9408h.animate().alpha(0.0f).start();
        }
        if (!z || this.f9409i.getAlpha() <= 0.95f) {
            return;
        }
        this.f9409i.animate().alpha(0.0f).start();
    }

    public void h(HomeWebFloorViewEntity homeWebFloorViewEntity, String str, String str2) {
        this.u = homeWebFloorViewEntity;
        int h2 = com.jingdong.app.mall.home.n.h.c.h(homeWebFloorViewEntity.getJsonString("imgAutoStopTime"), 3);
        this.f9411k = h2;
        this.f9411k = Math.max(h2, 1);
        boolean z = !TextUtils.isEmpty(str);
        this.x = z;
        if (z) {
            this.f9409i.setAlpha(0.0f);
            this.f9408h.f(str);
            return;
        }
        i(str2);
    }

    public boolean k() {
        if (this.u == null) {
            return false;
        }
        if (this.x) {
            return this.f9408h.g();
        }
        return this.f9410j;
    }

    public boolean l() {
        return this.t;
    }

    public boolean m() {
        return this.y <= (-com.jingdong.app.mall.home.floor.common.d.d(140)) && this.o == com.jingdong.app.mall.home.floor.common.d.f9279g;
    }

    public void n(float f2) {
        if (this.r < 10.0f) {
            return;
        }
        int height = getHeight();
        this.f9412l.setTranslationY(f2);
        float abs = Math.abs(f2);
        this.p = abs;
        this.q = Math.max(abs, this.q);
        setAlpha(Math.max(Math.min(this.p / this.r, 1.0f), 0.0f));
        float f3 = this.p;
        boolean z = f3 > this.r;
        if (z || f3 < this.q) {
            f(false);
        }
        if (z && height > 1) {
            float f4 = height;
            float max = Math.max(((this.p + f4) - this.r) / f4, 1.0f);
            j();
            r(this.f9408h, max);
            r(this.f9409i, max);
        }
        if (!this.s || this.p >= 5.0f) {
            return;
        }
        this.s = false;
        com.jingdong.app.mall.home.floor.ctrl.guide.a.j().o();
    }

    public void o() {
        this.t = true;
        this.f9408h.h();
        RelativeLayout relativeLayout = this.v;
        if (relativeLayout != null) {
            m.K(relativeLayout);
        }
        m.K(this);
    }

    public void q(float f2) {
        this.r = f2;
    }

    public void s() {
        int i2;
        e();
        if (h.C) {
            com.jingdong.app.mall.home.n.h.e.d(this, 0);
            setTranslationY(0.0f);
        } else {
            int d2 = com.jingdong.app.mall.home.floor.common.d.d(12);
            Rect rect = D;
            rect.set(0, -8888, 0, 0);
            com.jingdong.app.mall.home.n.h.e.f(this, true, rect, d2);
            setTranslationY(com.jingdong.app.mall.home.floor.common.d.d(JDHomeBaseLoadingView.P));
        }
        this.f9414n = true;
        g();
        postInvalidate();
        if (this.x) {
            i2 = this.f9408h.e();
            this.f9408h.i();
        } else {
            i2 = this.f9411k;
        }
        this.f9412l.animate().alpha(1.0f).start();
        t(i2);
        com.jingdong.app.mall.home.r.c.a.y("Home_XVIEWLeadExpo", "", this.u.srvJson);
    }
}

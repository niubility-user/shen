package com.jingdong.app.mall.home.deploy.view.layout.year2x2_1;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import com.jingdong.app.mall.home.deploy.view.base.BaseModel;
import com.jingdong.app.mall.home.deploy.view.layout.widget.IconImageText;
import com.jingdong.app.mall.home.deploy.view.layout.widget.SkuLabel;
import com.jingdong.app.mall.home.deploy.view.layout.year.YearBaseView;
import com.jingdong.app.mall.home.floor.common.d;
import com.jingdong.app.mall.home.floor.common.f;
import com.jingdong.app.mall.home.floor.common.g;
import com.jingdong.app.mall.home.floor.view.view.GradientTextView;
import com.jingdong.app.mall.home.floor.view.widget.CountDownLayout;
import com.jingdong.app.mall.home.n.h.e;
import com.jingdong.app.mall.home.state.dark.DarkWhiteBgImageView;
import com.jingdong.sdk.platform.business.personal.R2;

/* loaded from: classes4.dex */
public class DYear2x2_1 extends YearBaseView {
    private f A;
    private SkuLabel B;
    private f C;
    private View D;
    private f E;
    private CountDownLayout F;
    private DYear2x2_1Model p;
    private final Context q;
    private IconImageText r;
    private f s;
    private DarkWhiteBgImageView t;
    private f u;
    private GradientTextView v;
    private f w;
    private GradientTextView x;
    private f y;
    private SkuLabel z;

    public DYear2x2_1(Context context) {
        super(context);
        this.q = context;
    }

    @Override // com.jingdong.app.mall.home.deploy.view.base.BaseView
    protected boolean j(BaseModel baseModel) {
        com.jingdong.app.mall.home.o.a.f.n(baseModel);
        this.p = (DYear2x2_1Model) baseModel;
        return baseModel != null;
    }

    @Override // com.jingdong.app.mall.home.deploy.view.layout.year.YearBaseView, com.jingdong.app.mall.home.deploy.view.base.BaseView
    public void k() {
        super.k();
        this.r = new IconImageText(this.q);
        f fVar = new f(-2, 54);
        this.s = fVar;
        fVar.J(16, 0, 0, 0);
        View view = this.r;
        addView(view, this.s.u(view));
        View view2 = new View(this.q);
        this.D = view2;
        view2.setBackgroundColor(0);
        f fVar2 = new f(R2.attr.actionModeShareDrawable, 152);
        this.E = fVar2;
        fVar2.E(6, 46, 0, 0);
        View view3 = this.D;
        addView(view3, this.E.u(view3));
        DarkWhiteBgImageView darkWhiteBgImageView = new DarkWhiteBgImageView(this.q);
        this.t = darkWhiteBgImageView;
        darkWhiteBgImageView.c(true);
        this.t.setBackgroundColor(-1);
        f fVar3 = new f(132, 132);
        this.u = fVar3;
        fVar3.E(204, 56, 0, 0);
        View view4 = this.t;
        addView(view4, this.u.u(view4));
        this.t.setScaleType(ImageView.ScaleType.FIT_XY);
        g gVar = new g(this.q, true);
        gVar.f(1);
        gVar.c(false);
        gVar.d(16);
        gVar.m(26);
        gVar.j(true);
        this.v = gVar.b();
        f fVar4 = new f(-2, -2);
        this.w = fVar4;
        fVar4.E(28, 64, 0, 0);
        View view5 = this.v;
        addView(view5, this.w.u(view5));
        g gVar2 = new g(this.q, true);
        gVar2.f(1);
        gVar2.c(false);
        gVar2.d(16);
        gVar2.m(26);
        gVar2.j(true);
        this.x = gVar2.b();
        f fVar5 = new f(-2, -2);
        this.y = fVar5;
        fVar5.E(28, 100, 0, 0);
        View view6 = this.x;
        addView(view6, this.y.u(view6));
        this.x.setVisibility(8);
        CountDownLayout countDownLayout = new CountDownLayout(this.q);
        this.F = countDownLayout;
        addView(countDownLayout);
        this.F.setVisibility(8);
        this.z = new SkuLabel(this.q);
        f fVar6 = new f(-2, 40);
        this.A = fVar6;
        fVar6.E(28, R2.anim.manto_translate_dialog_in, 0, 0);
        View view7 = this.z;
        addView(view7, this.A.u(view7));
        this.B = new SkuLabel(this.q);
        f fVar7 = new f(132, 40);
        this.C = fVar7;
        fVar7.E(28, R2.anim.manto_translate_dialog_in, 0, 0);
        View view8 = this.B;
        addView(view8, this.C.u(view8));
    }

    @Override // com.jingdong.app.mall.home.deploy.view.layout.year.YearBaseView, com.jingdong.app.mall.home.deploy.view.base.BaseView
    public void m() {
        super.m();
        if (this.p.w0() == null) {
            this.r.setVisibility(8);
        } else {
            this.r.setVisibility(0);
            this.r.i(this.p.w0());
        }
        f.O(this.v, 26);
        this.v.setText(this.p.q0());
        this.v.setMaxWidth(d.d(R2.anim.pickerview_dialog_scale_in));
        GradientTextView gradientTextView = this.v;
        GradientTextView.GradientType gradientType = GradientTextView.GradientType.LeftToRight;
        gradientTextView.setTextGradient(gradientType, this.p.p0());
        if (this.p.x0()) {
            this.x.setVisibility(8);
            this.F.g(this.p.k0());
            this.F.h(this.p.v0(), this.p.r0());
        } else {
            this.F.setVisibility(8);
            this.x.setVisibility(0);
            f.O(this.x, 26);
            this.x.setText(this.p.u0());
            this.x.setMaxWidth(d.d(R2.anim.pickerview_dialog_scale_in));
            this.x.setTextGradient(gradientType, this.p.R());
        }
        e.d(this.t, d.d(8));
        com.jingdong.app.mall.home.floor.ctrl.e.u(this.t, this.p.t0());
        this.z.f(this.p.m0());
        this.B.f(this.p.l0());
        this.D.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.app.mall.home.deploy.view.layout.year2x2_1.DYear2x2_1.1
            {
                DYear2x2_1.this = this;
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                DYear2x2_1.this.p.g0(DYear2x2_1.this, 0, 1);
            }
        });
        setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.app.mall.home.deploy.view.layout.year2x2_1.DYear2x2_1.2
            {
                DYear2x2_1.this = this;
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                DYear2x2_1.this.p.e0(DYear2x2_1.this);
            }
        });
    }

    @Override // com.jingdong.app.mall.home.deploy.view.layout.year.YearBaseView, com.jingdong.app.mall.home.deploy.view.base.BaseView
    public void q() {
        super.q();
        f.c(this.r, this.s);
        f.c(this.v, this.w);
        f.O(this.v, 26);
        f.c(this.x, this.y);
        f.O(this.x, 26);
        f.c(this.t, this.u);
        f.c(this.z, this.A);
        f.c(this.B, this.C);
        f.c(this.D, this.E);
    }
}

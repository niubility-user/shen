package com.jingdong.app.mall.home.deploy.view.layout.yearBanner2x2;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jingdong.app.mall.home.R;
import com.jingdong.app.mall.home.base.HomeDraweeView;
import com.jingdong.app.mall.home.deploy.view.base.BaseModel;
import com.jingdong.app.mall.home.deploy.view.layout.widget.SkuLayout;
import com.jingdong.app.mall.home.deploy.view.layout.year.YearBaseView;
import com.jingdong.app.mall.home.floor.common.d;
import com.jingdong.app.mall.home.floor.common.f;
import com.jingdong.app.mall.home.floor.ctrl.e;
import com.jingdong.sdk.platform.business.personal.R2;

/* loaded from: classes4.dex */
public class DCoreBanner2x2 extends YearBaseView {
    private DCoreBanner2x2Model p;
    private SkuLayout q;
    private SkuLayout r;
    private f s;
    private f t;
    private SimpleDraweeView u;
    private f v;

    public DCoreBanner2x2(Context context) {
        super(context);
        u(context);
        t(context);
    }

    private void t(Context context) {
        HomeDraweeView homeDraweeView = new HomeDraweeView(context);
        this.u = homeDraweeView;
        homeDraweeView.setScaleType(ImageView.ScaleType.FIT_XY);
        f fVar = new f(R2.attr.actionTextColorAlpha, 68);
        this.v = fVar;
        fVar.E(0, 0, 0, 0);
        RelativeLayout.LayoutParams u = this.v.u(this.u);
        u.addRule(14);
        addView(this.u, u);
        this.u.setContentDescription(getContext().getString(R.string.home_obstacle_free));
    }

    private void u(Context context) {
        this.q = new SkuLayout(context);
        f fVar = new f(R2.anim.pickerview_dialog_scale_in, 130);
        this.s = fVar;
        fVar.E(12, 0, 0, 12);
        RelativeLayout.LayoutParams u = this.s.u(this.q);
        u.addRule(12);
        addView(this.q, u);
        this.r = new SkuLayout(context);
        f fVar2 = new f(R2.anim.pickerview_dialog_scale_in, 130);
        this.t = fVar2;
        fVar2.E(0, 0, 12, 12);
        RelativeLayout.LayoutParams u2 = this.t.u(this.r);
        u2.addRule(11);
        u2.addRule(12);
        addView(this.r, u2);
    }

    @Override // com.jingdong.app.mall.home.deploy.view.base.BaseView
    protected boolean j(BaseModel baseModel) {
        com.jingdong.app.mall.home.o.a.f.n(baseModel);
        this.p = (DCoreBanner2x2Model) baseModel;
        return baseModel != null;
    }

    @Override // com.jingdong.app.mall.home.deploy.view.layout.year.YearBaseView, com.jingdong.app.mall.home.deploy.view.base.BaseView
    public void m() {
        super.m();
        e.m(this.u, this.p.J(), e.b);
        com.jingdong.app.mall.home.n.h.e.d(this.u, d.d(8));
        this.u.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.app.mall.home.deploy.view.layout.yearBanner2x2.DCoreBanner2x2.1
            {
                DCoreBanner2x2.this = this;
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                DCoreBanner2x2.this.p.d0(DCoreBanner2x2.this);
            }
        });
        this.q.b(this.p.k0());
        this.r.b(this.p.l0());
        setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.app.mall.home.deploy.view.layout.yearBanner2x2.DCoreBanner2x2.2
            {
                DCoreBanner2x2.this = this;
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                DCoreBanner2x2.this.p.g0(DCoreBanner2x2.this, 0, 1);
            }
        });
        this.q.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.app.mall.home.deploy.view.layout.yearBanner2x2.DCoreBanner2x2.3
            {
                DCoreBanner2x2.this = this;
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                DCoreBanner2x2.this.p.g0(DCoreBanner2x2.this, 0, 1);
            }
        });
        this.r.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.app.mall.home.deploy.view.layout.yearBanner2x2.DCoreBanner2x2.4
            {
                DCoreBanner2x2.this = this;
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                DCoreBanner2x2.this.p.g0(DCoreBanner2x2.this, 1, 2);
            }
        });
        setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.app.mall.home.deploy.view.layout.yearBanner2x2.DCoreBanner2x2.5
            {
                DCoreBanner2x2.this = this;
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                DCoreBanner2x2.this.p.e0(DCoreBanner2x2.this);
            }
        });
    }

    @Override // com.jingdong.app.mall.home.deploy.view.layout.year.YearBaseView, com.jingdong.app.mall.home.deploy.view.base.BaseView
    public void q() {
        super.q();
        f.c(this.u, this.v);
        f.c(this.q, this.s);
        f.c(this.r, this.t);
    }
}

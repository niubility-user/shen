package com.jingdong.app.mall.home.deploy.view.layout.newcomer2x4;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jingdong.app.mall.home.R;
import com.jingdong.app.mall.home.base.HomeDraweeView;
import com.jingdong.app.mall.home.deploy.view.base.BaseModel;
import com.jingdong.app.mall.home.deploy.view.layout.core.CoreBaseView;
import com.jingdong.app.mall.home.deploy.view.layout.widget.IconImageText;
import com.jingdong.app.mall.home.deploy.view.layout.widget.SkuLayout;
import com.jingdong.app.mall.home.floor.common.d;
import com.jingdong.app.mall.home.floor.common.f;
import com.jingdong.app.mall.home.floor.ctrl.e;
import com.jingdong.sdk.platform.business.personal.R2;

/* loaded from: classes4.dex */
public class DNewcomer2x4 extends CoreBaseView {
    private DNewcomer2x4Model p;
    private final Context q;
    private IconImageText r;
    private f s;
    private SimpleDraweeView t;
    private SimpleDraweeView u;
    private f v;
    private f w;
    private final SkuLayout[] x;
    private final f[] y;

    public DNewcomer2x4(Context context) {
        super(context);
        this.x = new SkuLayout[4];
        this.y = new f[4];
        this.q = context;
    }

    private void v(boolean z) {
        SimpleDraweeView simpleDraweeView = this.u;
        if (simpleDraweeView != null) {
            simpleDraweeView.setVisibility(z ? 8 : 0);
        } else if (z) {
        } else {
            HomeDraweeView homeDraweeView = new HomeDraweeView(getContext());
            this.u = homeDraweeView;
            homeDraweeView.setScaleType(ImageView.ScaleType.FIT_XY);
            f fVar = new f(324, 60);
            this.w = fVar;
            fVar.E(0, 0, 0, 12);
            RelativeLayout.LayoutParams u = this.w.u(this.u);
            u.addRule(12);
            u.addRule(14);
            addView(this.u, u);
            this.u.setContentDescription(getContext().getString(R.string.home_obstacle_free));
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.app.mall.home.deploy.view.base.BaseView
    public void i(BaseModel baseModel, int i2) {
        super.i(baseModel, i2);
        boolean t = baseModel.t();
        this.s.R(-2, 54);
        int i3 = 0;
        while (i3 < 4) {
            this.y[i3].R(R2.anim.pickerview_dialog_scale_in, R2.anim.pickerview_dialog_scale_in);
            boolean z = i3 % 2 == 0;
            this.y[i3].E(z ? 12 : 0, i3 < 2 ? 48 : 214, z ? 0 : 12, 0);
            i3++;
        }
        v(t);
        q();
    }

    @Override // com.jingdong.app.mall.home.deploy.view.base.BaseView
    protected boolean j(BaseModel baseModel) {
        com.jingdong.app.mall.home.o.a.f.n(baseModel);
        this.p = (DNewcomer2x4Model) baseModel;
        return baseModel != null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.app.mall.home.deploy.view.layout.core.CoreBaseView, com.jingdong.app.mall.home.deploy.view.base.BaseView
    public void k() {
        super.k();
        this.r = new IconImageText(this.q);
        f fVar = new f(-2, 54);
        this.s = fVar;
        fVar.J(16, 0, 0, 0);
        View view = this.r;
        addView(view, this.s.u(view));
        HomeDraweeView homeDraweeView = new HomeDraweeView(getContext());
        this.t = homeDraweeView;
        homeDraweeView.setScaleType(ImageView.ScaleType.FIT_XY);
        f fVar2 = new f(106, 28);
        this.v = fVar2;
        fVar2.E(0, 12, 12, 0);
        RelativeLayout.LayoutParams u = this.v.u(this.t);
        u.addRule(10);
        u.addRule(11);
        addView(this.t, u);
        int i2 = 0;
        while (i2 < 4) {
            View skuLayout = new SkuLayout(this.q);
            f fVar3 = new f(R2.anim.pickerview_dialog_scale_in, R2.anim.pickerview_dialog_scale_in);
            boolean z = i2 % 2 == 0;
            fVar3.E(z ? 12 : 0, i2 < 2 ? 48 : 214, z ? 0 : 12, 0);
            RelativeLayout.LayoutParams u2 = fVar3.u(skuLayout);
            u2.addRule(z ? 9 : 11);
            this.x[i2] = skuLayout;
            this.y[i2] = fVar3;
            addView(skuLayout, u2);
            i2++;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.app.mall.home.deploy.view.layout.core.CoreBaseView, com.jingdong.app.mall.home.deploy.view.base.BaseView
    public void m() {
        super.m();
        this.r.i(this.p.C0());
        e.m(this.t, this.p.A0(), e.b);
        this.t.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.app.mall.home.deploy.view.layout.newcomer2x4.DNewcomer2x4.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                DNewcomer2x4Model dNewcomer2x4Model = DNewcomer2x4.this.p;
                DNewcomer2x4 dNewcomer2x4 = DNewcomer2x4.this;
                dNewcomer2x4Model.E0(dNewcomer2x4, dNewcomer2x4.q);
            }
        });
        for (final int i2 = 0; i2 < 4; i2++) {
            this.x[i2].b(this.p.B0()[i2]);
            this.x[i2].setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.app.mall.home.deploy.view.layout.newcomer2x4.DNewcomer2x4.2
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    DNewcomer2x4.this.p.v0(DNewcomer2x4.this, i2 + 1, false);
                }
            });
        }
        SimpleDraweeView simpleDraweeView = this.u;
        if (simpleDraweeView != null) {
            e.m(simpleDraweeView, this.p.y0(), e.b);
            com.jingdong.app.mall.home.n.h.e.d(this.u, d.d(8));
            this.u.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.app.mall.home.deploy.view.layout.newcomer2x4.DNewcomer2x4.3
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    DNewcomer2x4Model dNewcomer2x4Model = DNewcomer2x4.this.p;
                    DNewcomer2x4 dNewcomer2x4 = DNewcomer2x4.this;
                    dNewcomer2x4Model.D0(dNewcomer2x4, dNewcomer2x4.q);
                }
            });
        }
        setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.app.mall.home.deploy.view.layout.newcomer2x4.DNewcomer2x4.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                DNewcomer2x4.this.p.v0(DNewcomer2x4.this, 0, false);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.app.mall.home.deploy.view.layout.core.CoreBaseView, com.jingdong.app.mall.home.deploy.view.base.BaseView
    public void q() {
        super.q();
        f.c(this.r, this.s);
        f.c(this.t, this.v);
        for (int i2 = 0; i2 < 4; i2++) {
            f.c(this.x[i2], this.y[i2]);
        }
        f.c(this.u, this.w);
    }
}

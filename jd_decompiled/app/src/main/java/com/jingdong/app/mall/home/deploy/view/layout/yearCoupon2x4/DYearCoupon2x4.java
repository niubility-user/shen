package com.jingdong.app.mall.home.deploy.view.layout.yearCoupon2x4;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jingdong.app.mall.home.R;
import com.jingdong.app.mall.home.base.HomeDraweeView;
import com.jingdong.app.mall.home.deploy.view.base.BaseModel;
import com.jingdong.app.mall.home.deploy.view.layout.widget.IconImageText;
import com.jingdong.app.mall.home.deploy.view.layout.widget.SkuLayout;
import com.jingdong.app.mall.home.deploy.view.layout.year.YearBaseView;
import com.jingdong.app.mall.home.floor.common.d;
import com.jingdong.app.mall.home.floor.common.f;
import com.jingdong.app.mall.home.floor.ctrl.e;
import com.jingdong.sdk.platform.business.personal.R2;

/* loaded from: classes4.dex */
public class DYearCoupon2x4 extends YearBaseView {
    private DYearCoupon2x4Model p;
    private final Context q;
    private IconImageText r;
    private f s;
    private SimpleDraweeView t;
    private f u;
    private final SkuLayout[] v;
    private final f[] w;

    public DYearCoupon2x4(Context context) {
        super(context);
        this.v = new SkuLayout[4];
        this.w = new f[4];
        this.q = context;
    }

    @Override // com.jingdong.app.mall.home.deploy.view.base.BaseView
    public void i(BaseModel baseModel, int i2) {
        super.i(baseModel, i2);
        baseModel.t();
        this.s.R(-2, 52);
        int i3 = 0;
        while (true) {
            int i4 = 12;
            if (i3 < 4) {
                boolean z = i3 % 2 == 0;
                this.w[i3].R(R2.anim.pickerview_dialog_scale_in, R2.anim.pickerview_dialog_scale_in);
                f fVar = this.w[i3];
                int i5 = z ? 12 : 0;
                int i6 = i3 < 2 ? 56 : 222;
                if (z) {
                    i4 = 0;
                }
                fVar.E(i5, i6, i4, 0);
                i3++;
            } else {
                this.u.R(324, 52);
                this.u.E(0, 0, 0, 12);
                q();
                return;
            }
        }
    }

    @Override // com.jingdong.app.mall.home.deploy.view.base.BaseView
    protected boolean j(BaseModel baseModel) {
        com.jingdong.app.mall.home.o.a.f.n(baseModel);
        this.p = (DYearCoupon2x4Model) baseModel;
        return baseModel != null;
    }

    @Override // com.jingdong.app.mall.home.deploy.view.layout.year.YearBaseView, com.jingdong.app.mall.home.deploy.view.base.BaseView
    public void k() {
        super.k();
        this.r = new IconImageText(this.q);
        f fVar = new f(-2, 52);
        this.s = fVar;
        fVar.J(16, 0, 0, 0);
        View view = this.r;
        addView(view, this.s.u(view));
        int i2 = 0;
        while (true) {
            if (i2 < 4) {
                View skuLayout = new SkuLayout(this.q);
                f fVar2 = new f(R2.anim.pickerview_dialog_scale_in, R2.anim.pickerview_dialog_scale_in);
                boolean z = i2 % 2 == 0;
                fVar2.E(z ? 12 : 0, i2 < 2 ? 56 : 222, z ? 0 : 12, 0);
                RelativeLayout.LayoutParams u = fVar2.u(skuLayout);
                u.addRule(z ? 9 : 11);
                this.v[i2] = skuLayout;
                this.w[i2] = fVar2;
                addView(skuLayout, u);
                i2++;
            } else {
                HomeDraweeView homeDraweeView = new HomeDraweeView(getContext());
                this.t = homeDraweeView;
                homeDraweeView.setScaleType(ImageView.ScaleType.FIT_XY);
                f fVar3 = new f(324, 52);
                this.u = fVar3;
                fVar3.E(0, 0, 0, 12);
                RelativeLayout.LayoutParams u2 = this.u.u(this.t);
                u2.addRule(12);
                u2.addRule(14);
                addView(this.t, u2);
                this.t.setContentDescription(getContext().getString(R.string.home_obstacle_free));
                return;
            }
        }
    }

    @Override // com.jingdong.app.mall.home.deploy.view.layout.year.YearBaseView, com.jingdong.app.mall.home.deploy.view.base.BaseView
    public void m() {
        super.m();
        this.r.i(this.p.l0());
        for (final int i2 = 0; i2 < 4; i2++) {
            this.v[i2].b(this.p.k0()[i2]);
            this.v[i2].setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.app.mall.home.deploy.view.layout.yearCoupon2x4.DYearCoupon2x4.1
                {
                    DYearCoupon2x4.this = this;
                }

                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    DYearCoupon2x4Model dYearCoupon2x4Model = DYearCoupon2x4.this.p;
                    DYearCoupon2x4 dYearCoupon2x4 = DYearCoupon2x4.this;
                    int i3 = i2;
                    dYearCoupon2x4Model.g0(dYearCoupon2x4, i3, i3 + 1);
                }
            });
        }
        e.m(this.t, this.p.J(), e.b);
        com.jingdong.app.mall.home.n.h.e.d(this.t, d.d(8));
        this.t.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.app.mall.home.deploy.view.layout.yearCoupon2x4.DYearCoupon2x4.2
            {
                DYearCoupon2x4.this = this;
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                DYearCoupon2x4.this.p.d0(DYearCoupon2x4.this);
            }
        });
        setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.app.mall.home.deploy.view.layout.yearCoupon2x4.DYearCoupon2x4.3
            {
                DYearCoupon2x4.this = this;
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                DYearCoupon2x4.this.p.e0(DYearCoupon2x4.this);
            }
        });
    }

    @Override // com.jingdong.app.mall.home.deploy.view.layout.year.YearBaseView, com.jingdong.app.mall.home.deploy.view.base.BaseView
    public void q() {
        super.q();
        f.c(this.r, this.s);
        for (int i2 = 0; i2 < 4; i2++) {
            f.c(this.v[i2], this.w[i2]);
        }
        f.c(this.t, this.u);
    }
}

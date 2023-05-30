package com.jingdong.app.mall.home.deploy.view.layout.year2x2;

import android.content.Context;
import android.view.View;
import android.widget.RelativeLayout;
import com.jingdong.app.mall.home.deploy.view.base.BaseModel;
import com.jingdong.app.mall.home.deploy.view.layout.widget.IconImageText;
import com.jingdong.app.mall.home.deploy.view.layout.widget.SkuLayout;
import com.jingdong.app.mall.home.deploy.view.layout.year.YearBaseView;
import com.jingdong.app.mall.home.floor.common.f;
import com.jingdong.sdk.platform.business.personal.R2;

/* loaded from: classes4.dex */
public class DYear2x2 extends YearBaseView {
    private DYear2x2Model p;
    private final Context q;
    private IconImageText r;
    private f s;
    private final SkuLayout[] t;
    private final f[] u;

    public DYear2x2(Context context) {
        super(context);
        this.t = new SkuLayout[2];
        this.u = new f[2];
        this.q = context;
    }

    @Override // com.jingdong.app.mall.home.deploy.view.base.BaseView
    public void i(BaseModel baseModel, int i2) {
        super.i(baseModel, i2);
        baseModel.t();
        this.s.R(-2, 52);
        for (int i3 = 0; i3 < 2; i3++) {
            boolean z = i3 % 2 == 0;
            this.u[i3].R(R2.anim.pickerview_dialog_scale_in, R2.anim.pickerview_dialog_scale_in);
            f fVar = this.u[i3];
            int i4 = 12;
            int i5 = z ? 12 : 0;
            if (z) {
                i4 = 0;
            }
            fVar.E(i5, 48, i4, 0);
        }
        q();
    }

    @Override // com.jingdong.app.mall.home.deploy.view.base.BaseView
    protected boolean j(BaseModel baseModel) {
        com.jingdong.app.mall.home.o.a.f.n(baseModel);
        this.p = (DYear2x2Model) baseModel;
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
        for (int i2 = 0; i2 < 2; i2++) {
            View skuLayout = new SkuLayout(this.q);
            f fVar2 = new f(R2.anim.pickerview_dialog_scale_in, R2.anim.pickerview_dialog_scale_in);
            boolean z = i2 % 2 == 0;
            fVar2.E(z ? 12 : 0, 48, z ? 0 : 12, 0);
            RelativeLayout.LayoutParams u = fVar2.u(skuLayout);
            u.addRule(z ? 9 : 11);
            this.t[i2] = skuLayout;
            this.u[i2] = fVar2;
            addView(skuLayout, u);
        }
    }

    @Override // com.jingdong.app.mall.home.deploy.view.layout.year.YearBaseView, com.jingdong.app.mall.home.deploy.view.base.BaseView
    public void m() {
        super.m();
        this.r.i(this.p.l0());
        for (final int i2 = 0; i2 < 2; i2++) {
            this.t[i2].b(this.p.k0()[i2]);
            this.t[i2].setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.app.mall.home.deploy.view.layout.year2x2.DYear2x2.1
                {
                    DYear2x2.this = this;
                }

                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    DYear2x2Model dYear2x2Model = DYear2x2.this.p;
                    DYear2x2 dYear2x2 = DYear2x2.this;
                    int i3 = i2;
                    dYear2x2Model.g0(dYear2x2, i3, i3 + 1);
                }
            });
        }
        setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.app.mall.home.deploy.view.layout.year2x2.DYear2x2.2
            {
                DYear2x2.this = this;
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                DYear2x2.this.p.e0(DYear2x2.this);
            }
        });
    }

    @Override // com.jingdong.app.mall.home.deploy.view.layout.year.YearBaseView, com.jingdong.app.mall.home.deploy.view.base.BaseView
    public void q() {
        super.q();
        f.c(this.r, this.s);
        for (int i2 = 0; i2 < 2; i2++) {
            f.c(this.t[i2], this.u[i2]);
        }
    }
}

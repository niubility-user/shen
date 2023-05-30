package com.jingdong.app.mall.home.deploy.view.layout.sale1x2;

import android.content.Context;
import android.graphics.Rect;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jingdong.app.mall.home.base.HomeDraweeView;
import com.jingdong.app.mall.home.deploy.view.base.BaseModel;
import com.jingdong.app.mall.home.deploy.view.layout.sale.SaleBaseView;
import com.jingdong.app.mall.home.deploy.view.layout.widget.IconImageText;
import com.jingdong.app.mall.home.deploy.view.layout.widget.SkuLabel;
import com.jingdong.app.mall.home.floor.common.d;
import com.jingdong.app.mall.home.floor.common.f;
import com.jingdong.app.mall.home.n.h.e;

/* loaded from: classes4.dex */
public class DSale1x2 extends SaleBaseView {
    private DSale1x2Model o;
    private final f p;
    private final f q;
    private final f r;
    private IconImageText s;
    private SimpleDraweeView t;
    private SkuLabel u;

    public DSale1x2(Context context) {
        super(context);
        this.p = new f(-2, 48);
        f fVar = new f(130, 130);
        this.q = fVar;
        fVar.F(new Rect(0, 0, 0, 10));
        f fVar2 = new f(-2, 30);
        this.r = fVar2;
        fVar2.F(new Rect(0, 0, 0, 10));
    }

    private void s() {
        this.o.J(getContext(), this, 0);
    }

    private void t() {
        SkuLabel skuLabel = this.u;
        if (skuLabel == null) {
            SkuLabel skuLabel2 = new SkuLabel(getContext());
            this.u = skuLabel2;
            RelativeLayout.LayoutParams u = this.r.u(skuLabel2);
            u.addRule(12);
            u.addRule(14);
            addView(this.u, u);
        } else {
            f.c(skuLabel, this.r);
        }
        this.u.setMinimumWidth(d.d(130));
        this.u.f(this.o.M());
    }

    private void u() {
        SimpleDraweeView simpleDraweeView = this.t;
        if (simpleDraweeView == null) {
            HomeDraweeView homeDraweeView = new HomeDraweeView(getContext());
            this.t = homeDraweeView;
            homeDraweeView.setScaleType(ImageView.ScaleType.FIT_XY);
            this.t.setBackgroundColor(-1);
            RelativeLayout.LayoutParams u = this.q.u(this.t);
            u.addRule(12);
            u.addRule(14);
            addView(this.t, u);
        } else {
            f.c(simpleDraweeView, this.q);
        }
        e.d(this.t, r());
        com.jingdong.app.mall.home.floor.ctrl.e.d(this.t, this.o.K());
    }

    private void v() {
        IconImageText iconImageText = this.s;
        if (iconImageText == null) {
            IconImageText iconImageText2 = new IconImageText(getContext());
            this.s = iconImageText2;
            RelativeLayout.LayoutParams u = this.p.u(iconImageText2);
            u.addRule(10);
            u.addRule(14);
            addView(this.s, u);
        } else {
            f.c(iconImageText, this.p);
        }
        this.s.i(this.o.P());
    }

    @Override // com.jingdong.app.mall.home.deploy.view.base.BaseView
    protected boolean j(BaseModel baseModel) {
        com.jingdong.app.mall.home.o.a.f.n(baseModel);
        DSale1x2Model dSale1x2Model = (DSale1x2Model) baseModel;
        this.o = dSale1x2Model;
        return (dSale1x2Model == null || dSale1x2Model.f8950m == null) ? false : true;
    }

    @Override // com.jingdong.app.mall.home.deploy.view.layout.sale.SaleBaseView, com.jingdong.app.mall.home.deploy.view.base.BaseView
    public void k() {
        super.k();
    }

    @Override // com.jingdong.app.mall.home.deploy.view.layout.sale.SaleBaseView, com.jingdong.app.mall.home.deploy.view.base.BaseView
    public void m() {
        super.m();
        u();
        v();
        t();
        s();
    }
}

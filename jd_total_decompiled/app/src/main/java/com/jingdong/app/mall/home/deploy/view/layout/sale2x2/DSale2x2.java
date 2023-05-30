package com.jingdong.app.mall.home.deploy.view.layout.sale2x2;

import android.content.Context;
import android.graphics.Rect;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jingdong.app.mall.home.R;
import com.jingdong.app.mall.home.base.HomeDraweeView;
import com.jingdong.app.mall.home.deploy.view.base.BaseModel;
import com.jingdong.app.mall.home.deploy.view.layout.sale.SaleBaseView;
import com.jingdong.app.mall.home.deploy.view.layout.widget.IconImageText;
import com.jingdong.app.mall.home.deploy.view.layout.widget.SkuLabel;
import com.jingdong.app.mall.home.floor.common.d;
import com.jingdong.app.mall.home.floor.common.f;
import com.jingdong.app.mall.home.n.h.e;

/* loaded from: classes4.dex */
public class DSale2x2 extends SaleBaseView {
    private SimpleDraweeView A;
    private RelativeLayout B;
    private SkuLabel C;
    private DSale2x2Model o;
    private final f p;
    private final f q;
    private final f r;
    private final f s;
    private final f t;
    private final f u;
    private final f v;
    private LinearLayout w;
    private IconImageText x;
    private IconImageText y;
    private SimpleDraweeView z;

    public DSale2x2(Context context) {
        super(context);
        this.p = new f(-1, 52);
        f fVar = new f(-2, -1);
        this.q = fVar;
        fVar.F(new Rect(16, 0, 0, 0));
        f fVar2 = new f(-2, -1);
        this.r = fVar2;
        fVar2.F(new Rect(8, 4, 0, 0));
        f fVar3 = new f(130, 130);
        this.s = fVar3;
        fVar3.F(new Rect(20, 0, 0, 10));
        f fVar4 = new f(130, 130);
        this.t = fVar4;
        fVar4.F(new Rect(0, 0, 20, 10));
        this.u = new f(-2, 30);
        f fVar5 = new f(170, 30);
        this.v = fVar5;
        fVar5.F(new Rect(0, 0, 0, 10));
    }

    private void s() {
        this.o.J(getContext(), this, 0);
        this.o.J(getContext(), this.A, 1);
    }

    private void t() {
        if (this.C == null) {
            RelativeLayout relativeLayout = new RelativeLayout(getContext());
            this.B = relativeLayout;
            RelativeLayout.LayoutParams u = this.v.u(relativeLayout);
            u.addRule(12);
            u.addRule(9);
            addView(this.B, u);
            SkuLabel skuLabel = new SkuLabel(getContext());
            this.C = skuLabel;
            RelativeLayout.LayoutParams u2 = this.u.u(skuLabel);
            u2.addRule(14);
            this.B.addView(this.C, u2);
        } else {
            f.c(this.B, this.v);
            f.c(this.C, this.u);
        }
        this.C.setMinimumWidth(d.d(130));
        this.C.f(this.o.M());
    }

    private void u() {
        SimpleDraweeView simpleDraweeView = this.z;
        if (simpleDraweeView == null) {
            HomeDraweeView homeDraweeView = new HomeDraweeView(getContext());
            this.z = homeDraweeView;
            homeDraweeView.setId(R.id.mallfloor_item_left);
            this.z.setScaleType(ImageView.ScaleType.FIT_XY);
            this.z.setBackgroundColor(-1);
            RelativeLayout.LayoutParams u = this.s.u(this.z);
            u.addRule(12);
            u.addRule(9);
            addView(this.z, u);
        } else {
            f.c(simpleDraweeView, this.s);
        }
        e.d(this.z, r());
        com.jingdong.app.mall.home.floor.ctrl.e.d(this.z, this.o.K());
        SimpleDraweeView simpleDraweeView2 = this.A;
        if (simpleDraweeView2 == null) {
            HomeDraweeView homeDraweeView2 = new HomeDraweeView(getContext());
            this.A = homeDraweeView2;
            homeDraweeView2.setScaleType(ImageView.ScaleType.FIT_XY);
            RelativeLayout.LayoutParams u2 = this.t.u(this.A);
            u2.addRule(12);
            u2.addRule(11);
            addView(this.A, u2);
        } else {
            f.c(simpleDraweeView2, this.t);
        }
        e.d(this.A, r());
        com.jingdong.app.mall.home.floor.ctrl.e.d(this.A, this.o.L());
    }

    private void v() {
        LinearLayout linearLayout = this.w;
        if (linearLayout == null) {
            LinearLayout linearLayout2 = new LinearLayout(getContext());
            this.w = linearLayout2;
            linearLayout2.setOrientation(0);
            addView(this.w, this.p.u(this.w));
            IconImageText iconImageText = new IconImageText(getContext());
            this.x = iconImageText;
            this.w.addView(this.x, this.q.i(iconImageText));
            IconImageText iconImageText2 = new IconImageText(getContext());
            this.y = iconImageText2;
            this.w.addView(this.y, this.r.i(iconImageText2));
        } else {
            f.c(linearLayout, this.p);
            f.c(this.x, this.q);
            f.c(this.y, this.r);
        }
        this.x.i(this.o.P());
        this.y.i(this.o.S());
    }

    @Override // com.jingdong.app.mall.home.deploy.view.base.BaseView
    protected boolean j(BaseModel baseModel) {
        com.jingdong.app.mall.home.o.a.f.n(baseModel);
        DSale2x2Model dSale2x2Model = (DSale2x2Model) baseModel;
        this.o = dSale2x2Model;
        return (dSale2x2Model == null || dSale2x2Model.f8950m == null) ? false : true;
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

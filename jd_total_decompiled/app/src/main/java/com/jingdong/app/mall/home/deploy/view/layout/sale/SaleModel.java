package com.jingdong.app.mall.home.deploy.view.layout.sale;

import android.content.Context;
import android.view.View;
import com.jingdong.app.mall.home.deploy.view.base.BaseModel;
import com.jingdong.app.mall.home.deploy.view.layout.sale.SaleBaseView;
import com.jingdong.app.mall.home.deploy.view.layout.widget.IconImageText;
import com.jingdong.app.mall.home.deploy.view.layout.widget.SkuLabel;
import com.jingdong.app.mall.home.floor.common.i.l;
import com.jingdong.app.mall.home.floor.common.i.m;
import com.jingdong.app.mall.home.p.b.e.b;
import com.jingdong.app.mall.home.r.e.f;
import com.jingdong.common.entity.JumpEntity;

/* loaded from: classes4.dex */
public abstract class SaleModel<V extends SaleBaseView> extends BaseModel<V> {

    /* renamed from: m */
    public f f8950m = null;

    /* renamed from: n */
    protected IconImageText.Info f8951n;
    protected SkuLabel.Info o;

    @Override // com.jingdong.app.mall.home.deploy.view.base.BaseModel
    protected void A(View view) {
    }

    @Override // com.jingdong.app.mall.home.deploy.view.base.BaseModel
    public void C() {
        f e2 = e();
        this.f8950m = e2;
        if (e2 == null) {
            return;
        }
        SkuLabel.Info a = SkuLabel.Info.a();
        a.e(m.o(this.f8950m.getJsonString("subTitleBgColor"), -381927), this.f8950m.getJsonString("subTitleBgImg"));
        a.q(m.o(this.f8950m.getJsonString("subTitleColor"), -1), 20);
        a.f(false);
        a.t(5);
        a.n(this.f8950m.getJsonString("subTitle"));
        this.o = a;
    }

    @Override // com.jingdong.app.mall.home.deploy.view.base.BaseModel
    public void E(View view, b bVar) {
        Q(bVar);
        int[] j2 = j("mar", 0);
        if (j2 != null && j2.length > 3) {
            this.f8880j.E(j2[0], j2[1], j2[2], j2[3]);
        }
        if (((SaleBaseView) this.f8882l).getParent() == view) {
            com.jingdong.app.mall.home.floor.common.f.d(this.f8882l, this.f8880j, true);
        } else {
            super.E(view, bVar);
        }
    }

    public void J(Context context, View view, final int i2) {
        if (view == null || context == null) {
            return;
        }
        view.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.app.mall.home.deploy.view.layout.sale.SaleModel.1
            {
                SaleModel.this = this;
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                JumpEntity jump = SaleModel.this.f8950m.getJump();
                String K = i2 == 0 ? SaleModel.this.K() : SaleModel.this.L();
                int i3 = i2;
                l.q(view2, jump, K, i3, i3 + 1);
            }
        });
    }

    public String K() {
        return this.f8950m.u();
    }

    public String L() {
        return this.f8950m.v();
    }

    public SkuLabel.Info M() {
        return this.o;
    }

    public String N() {
        return this.f8950m.getJsonString("tagImg");
    }

    public int O() {
        return this.f8950m.getJsonInt("tagImgWidth");
    }

    public IconImageText.Info P() {
        return this.f8951n;
    }

    protected abstract void Q(b bVar);

    @Override // com.jingdong.app.mall.home.deploy.view.base.BaseModel
    /* renamed from: R */
    public void y(V v) {
        v.b(this);
    }
}

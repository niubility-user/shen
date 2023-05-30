package com.jingdong.app.mall.home.deploy.view.layout.yearBanner2x2;

import com.jingdong.app.mall.home.deploy.view.layout.widget.SkuLabel;
import com.jingdong.app.mall.home.deploy.view.layout.widget.SkuLayout;
import com.jingdong.app.mall.home.deploy.view.layout.year.YearModel;
import com.jingdong.app.mall.home.deploy.view.layout.year.YearSkuItem;
import com.jingdong.sdk.platform.business.personal.R2;

/* loaded from: classes4.dex */
public class DCoreBanner2x2Model extends YearModel<DCoreBanner2x2> {
    private SkuLayout.Info v;
    private SkuLayout.Info w;

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.app.mall.home.deploy.view.layout.year.YearModel, com.jingdong.app.mall.home.deploy.view.base.BaseModel
    public void C() {
        super.C();
        int m2 = m(122, 122);
        int m3 = m(R2.anim.pickerview_dialog_scale_in, R2.anim.pickerview_dialog_scale_in);
        int m4 = m(130, 130);
        YearSkuItem N = N(0);
        if (N != null) {
            SkuLabel.Info a = SkuLabel.Info.a();
            a.e(P(), Q());
            a.q(R(), S());
            a.n(N.f());
            a.f(c0());
            SkuLayout.Info a2 = SkuLayout.Info.a();
            a2.l(m2, m2);
            a2.i(N.c(), O(), 8);
            a2.k(m3, m4);
            a2.e(a, 150, 30);
            this.v = a2;
        }
        YearSkuItem N2 = N(1);
        if (N2 != null) {
            SkuLabel.Info a3 = SkuLabel.Info.a();
            a3.e(P(), Q());
            a3.q(R(), S());
            a3.n(N2.f());
            a3.f(c0());
            SkuLayout.Info a4 = SkuLayout.Info.a();
            a4.l(m2, m2);
            a4.i(N2.c(), O(), 8);
            a4.k(m3, m4);
            a4.e(a3, 150, 30);
            this.w = a4;
        }
    }

    @Override // com.jingdong.app.mall.home.deploy.view.layout.year.YearModel
    protected void b0() {
        this.f8880j.R(R2.attr.actionTextColorAlpha, 218);
    }

    public SkuLayout.Info k0() {
        return this.v;
    }

    public SkuLayout.Info l0() {
        return this.w;
    }
}

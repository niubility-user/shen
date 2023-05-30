package com.jingdong.app.mall.home.deploy.view.layout.yearCoupon2x4;

import com.jingdong.app.mall.home.deploy.view.layout.widget.IconImageText;
import com.jingdong.app.mall.home.deploy.view.layout.widget.SkuLabel;
import com.jingdong.app.mall.home.deploy.view.layout.widget.SkuLayout;
import com.jingdong.app.mall.home.deploy.view.layout.year.YearModel;
import com.jingdong.app.mall.home.deploy.view.layout.year.YearSkuItem;
import com.jingdong.sdk.platform.business.personal.R2;

/* loaded from: classes4.dex */
public class DYearCoupon2x4Model extends YearModel<DYearCoupon2x4> {
    private IconImageText.Info v;
    private final SkuLayout.Info[] w = new SkuLayout.Info[4];

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.app.mall.home.deploy.view.layout.year.YearModel, com.jingdong.app.mall.home.deploy.view.base.BaseModel
    public void C() {
        super.C();
        IconImageText.Info a = IconImageText.Info.a();
        a.d(U(), 28, T());
        a.e(0, 0, 8, 0);
        a.h(W(), a0());
        a.g(V());
        a.c(true);
        a.f(Z(), 32, Y());
        this.v = a;
        for (int i2 = 0; i2 < 4; i2++) {
            YearSkuItem N = N(i2);
            if (N != null) {
                SkuLabel.Info a2 = SkuLabel.Info.a();
                a2.l(YearModel.u, 0);
                a2.e(P(), Q());
                a2.q(R(), S());
                a2.n(N.f());
                a2.f(c0());
                SkuLayout.Info a3 = SkuLayout.Info.a();
                a3.l(R2.anim.pickerview_dialog_scale_in, R2.anim.pickerview_dialog_scale_in);
                a3.i(N.c(), O(), 8);
                a3.e(a2, 150, 30);
                this.w[i2] = a3;
            }
        }
    }

    @Override // com.jingdong.app.mall.home.deploy.view.layout.year.YearModel
    protected void b0() {
        this.f8880j.R(R2.attr.actionTextColorAlpha, m(226, 226) << 1);
    }

    public SkuLayout.Info[] k0() {
        return this.w;
    }

    public IconImageText.Info l0() {
        return this.v;
    }
}

package com.jingdong.app.mall.home.deploy.view.layout.year1x2;

import com.jingdong.app.mall.home.deploy.view.layout.widget.IconImageText;
import com.jingdong.app.mall.home.deploy.view.layout.widget.SkuLabel;
import com.jingdong.app.mall.home.deploy.view.layout.widget.SkuLayout;
import com.jingdong.app.mall.home.deploy.view.layout.year.YearModel;
import com.jingdong.app.mall.home.deploy.view.layout.year.YearSkuItem;
import com.jingdong.sdk.platform.business.personal.R2;

/* loaded from: classes4.dex */
public class DYear1x2Model extends YearModel<DYear1x2> {
    private IconImageText.Info v;
    private SkuLayout.Info w;

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.app.mall.home.deploy.view.layout.year.YearModel, com.jingdong.app.mall.home.deploy.view.base.BaseModel
    public void C() {
        super.C();
        IconImageText.Info a = IconImageText.Info.a();
        a.h(W(), a0());
        a.g(V());
        a.c(true);
        a.i(5);
        a.f(Z(), 32, Y());
        this.v = a;
        SkuLabel.Info a2 = SkuLabel.Info.a();
        a2.l(YearModel.u, 0);
        a2.e(P(), Q());
        a2.q(R(), S());
        a2.n(m0());
        a2.f(c0());
        SkuLayout.Info a3 = SkuLayout.Info.a();
        a3.l(R2.anim.pickerview_dialog_scale_in, R2.anim.pickerview_dialog_scale_in);
        a3.i(k0(), O(), 8);
        a3.e(a2, 150, 30);
        this.w = a3;
    }

    @Override // com.jingdong.app.mall.home.deploy.view.layout.year.YearModel
    protected void b0() {
        this.f8880j.R(R2.anim.pop_left_bottom_out, m(109, 109) << 1);
    }

    public String k0() {
        YearSkuItem N = N(0);
        return N != null ? N.c() : "";
    }

    public SkuLayout.Info l0() {
        return this.w;
    }

    public String m0() {
        YearSkuItem N = N(0);
        return N != null ? N.f() : "";
    }

    public IconImageText.Info n0() {
        return this.v;
    }
}

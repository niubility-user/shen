package com.jingdong.app.mall.home.deploy.view.layout.core1x2;

import com.jingdong.app.mall.home.deploy.view.layout.core.CoreModel;
import com.jingdong.app.mall.home.deploy.view.layout.widget.IconImageText;
import com.jingdong.app.mall.home.deploy.view.layout.widget.SkuLabel;
import com.jingdong.app.mall.home.deploy.view.layout.widget.SkuLayout;
import com.jingdong.app.mall.home.floor.common.i.g;
import com.jingdong.app.mall.home.p.b.e.b;
import com.jingdong.sdk.platform.business.personal.R2;

/* loaded from: classes4.dex */
public class DCore1x2Model extends CoreModel<DCore1x2> {
    private IconImageText.Info r;
    private SkuLayout.Info s;

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.app.mall.home.deploy.view.layout.core.CoreModel, com.jingdong.app.mall.home.deploy.view.base.BaseModel
    public void C() {
        super.C();
        IconImageText.Info a = IconImageText.Info.a();
        a.h(f0(), l0());
        a.g(m0());
        a.c(true);
        a.i(4);
        a.f(k0(), 32, j0());
        this.r = a;
        SkuLabel.Info a2 = SkuLabel.Info.a();
        a2.g(this.f8920m);
        a2.d(Y());
        a2.q(Z(), g.i());
        a2.o(W(), a0());
        int m2 = m(R2.anim.pickerview_dialog_scale_in, R2.anim.pickerview_dialog_scale_in);
        SkuLayout.Info a3 = SkuLayout.Info.a();
        a3.l(m2, m2);
        a3.i(M(), c0(), 8);
        a3.e(a2, -2, 30);
        this.s = a3;
    }

    @Override // com.jingdong.app.mall.home.deploy.view.layout.core.CoreModel
    protected void s0(b bVar) {
        this.f8880j.R(R2.anim.pop_left_bottom_out, m(109, 109) << 1);
    }

    public SkuLayout.Info y0() {
        return this.s;
    }

    public IconImageText.Info z0() {
        return this.r;
    }
}

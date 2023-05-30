package com.jingdong.app.mall.home.deploy.view.layout.corelive2x2;

import com.jingdong.app.mall.home.deploy.view.layout.core.CoreModel;
import com.jingdong.app.mall.home.deploy.view.layout.widget.IconImageText;
import com.jingdong.app.mall.home.deploy.view.layout.widget.SkuLayout;
import com.jingdong.app.mall.home.p.b.e.b;
import com.jingdong.sdk.platform.business.personal.R2;

/* loaded from: classes4.dex */
public class DCoreLive2x2Model extends CoreModel<DCoreLive2x2> {
    private IconImageText.Info r;
    private SkuLayout.Info s;
    private SkuLayout.Info t;

    public IconImageText.Info A0() {
        return this.r;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.app.mall.home.deploy.view.layout.core.CoreModel, com.jingdong.app.mall.home.deploy.view.base.BaseModel
    public void C() {
        super.C();
        IconImageText.Info a = IconImageText.Info.a();
        a.h(g0(CoreModel.o), l0());
        a.g(m0());
        a.c(true);
        a.f(k0(), 32, j0());
        this.r = a;
        int m2 = m(124, 124);
        SkuLayout.Info a2 = SkuLayout.Info.a();
        a2.l(m2, m2);
        a2.i(M(), c0(), 8);
        this.s = a2;
        SkuLayout.Info a3 = SkuLayout.Info.a();
        a3.l(m2, m2);
        a3.i(N(), c0(), 8);
        this.t = a3;
    }

    @Override // com.jingdong.app.mall.home.deploy.view.layout.core.CoreModel
    protected void s0(b bVar) {
        this.f8880j.R(R2.attr.actionTextColorAlpha, m(109, 109) << 1);
    }

    public SkuLayout.Info y0() {
        return this.s;
    }

    public SkuLayout.Info z0() {
        return this.t;
    }
}

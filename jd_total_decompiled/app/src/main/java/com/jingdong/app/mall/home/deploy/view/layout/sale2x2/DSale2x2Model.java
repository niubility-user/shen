package com.jingdong.app.mall.home.deploy.view.layout.sale2x2;

import com.jingdong.app.mall.home.deploy.view.layout.sale.SaleModel;
import com.jingdong.app.mall.home.deploy.view.layout.widget.IconImageText;
import com.jingdong.app.mall.home.floor.common.i.m;
import com.jingdong.app.mall.home.p.b.e.b;
import com.jingdong.app.mall.home.r.e.f;
import com.jingdong.common.unification.customtheme.CustomThemeConstance;
import com.jingdong.sdk.platform.business.personal.R2;
import com.unionpay.tsmservice.mi.data.Constant;

/* loaded from: classes4.dex */
public class DSale2x2Model extends SaleModel<DSale2x2> {
    private IconImageText.Info p;

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.app.mall.home.deploy.view.layout.sale.SaleModel, com.jingdong.app.mall.home.deploy.view.base.BaseModel
    public void C() {
        super.C();
        f e2 = e();
        this.f8950m = e2;
        if (e2 == null) {
            return;
        }
        IconImageText.Info a = IconImageText.Info.a();
        a.d(O(), 28, N());
        a.e(0, 0, 8, 0);
        a.h(m.o(this.f8950m.getJsonString(Constant.KEY_TITLE_COLOR), -381927), 28);
        a.c(true);
        a.i(5);
        a.g(this.f8950m.getJsonString("title"));
        this.f8951n = a;
        IconImageText.Info a2 = IconImageText.Info.a();
        a2.h(m.o(this.f8950m.getJsonString(CustomThemeConstance.NAVI_LABEL_COLOR), -10461088), 20);
        a2.g(this.f8950m.getJsonString("labelWords"));
        this.p = a2;
    }

    @Override // com.jingdong.app.mall.home.deploy.view.layout.sale.SaleModel
    protected void Q(b bVar) {
        this.f8880j.R(336, R2.anim.slide_in_from_top);
    }

    public IconImageText.Info S() {
        return this.p;
    }
}

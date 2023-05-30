package com.jingdong.app.mall.home.deploy.view.layout.sale1x2;

import com.jingdong.app.mall.home.deploy.view.layout.sale.SaleModel;
import com.jingdong.app.mall.home.deploy.view.layout.widget.IconImageText;
import com.jingdong.app.mall.home.floor.common.i.m;
import com.jingdong.app.mall.home.p.b.e.b;
import com.jingdong.sdk.platform.business.personal.R2;
import com.unionpay.tsmservice.mi.data.Constant;

/* loaded from: classes4.dex */
public class DSale1x2Model extends SaleModel<DSale1x2> {
    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.app.mall.home.deploy.view.layout.sale.SaleModel, com.jingdong.app.mall.home.deploy.view.base.BaseModel
    public void C() {
        super.C();
        if (this.f8950m == null) {
            return;
        }
        IconImageText.Info a = IconImageText.Info.a();
        a.h(m.o(this.f8950m.getJsonString(Constant.KEY_TITLE_COLOR), -16777216), 26);
        a.c(true);
        a.i(5);
        a.g(this.f8950m.getJsonString("title"));
        this.f8951n = a;
    }

    @Override // com.jingdong.app.mall.home.deploy.view.layout.sale.SaleModel
    protected void Q(b bVar) {
        this.f8880j.R(R2.anim.pop_in, R2.anim.slide_in_from_top);
    }
}

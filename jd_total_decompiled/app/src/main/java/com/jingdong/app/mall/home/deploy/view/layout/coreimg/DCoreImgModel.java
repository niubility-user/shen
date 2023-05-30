package com.jingdong.app.mall.home.deploy.view.layout.coreimg;

import com.jingdong.app.mall.home.deploy.view.layout.core.CoreModel;
import com.jingdong.app.mall.home.floor.common.f;
import com.jingdong.app.mall.home.p.b.e.b;
import com.jingdong.sdk.platform.business.personal.R2;

/* loaded from: classes4.dex */
public class DCoreImgModel extends CoreModel<DCoreImg> {
    private String r;

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.app.mall.home.deploy.view.layout.core.CoreModel, com.jingdong.app.mall.home.deploy.view.base.BaseModel
    public void C() {
        super.C();
        this.r = this.f8920m.getJsonString("fullImg");
    }

    @Override // com.jingdong.app.mall.home.deploy.view.layout.core.CoreModel
    protected void s0(b bVar) {
        int b = this.f8879i.b();
        f fVar = this.f8880j;
        if (b <= 0) {
            b = m(109, 109) << 1;
        }
        fVar.R(R2.attr.actionTextColorAlpha, b);
    }

    public String y0() {
        return this.r;
    }
}

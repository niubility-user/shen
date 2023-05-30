package com.jingdong.app.mall.home.deploy.view.widget.text;

import com.jd.dynamic.DYConstants;
import com.jingdong.app.mall.home.deploy.view.base.BaseModel;
import com.jingdong.app.mall.home.floor.common.g;

/* loaded from: classes4.dex */
public class DTextModel extends BaseModel<DText> {
    @Override // com.jingdong.app.mall.home.deploy.view.base.BaseModel
    protected void C() {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.app.mall.home.deploy.view.base.BaseModel
    /* renamed from: J  reason: merged with bridge method [inline-methods] */
    public void y(DText dText) {
        dText.setTextColor(c(DYConstants.DY_TEXT_COLOR));
        g.k(dText, b("textBold"));
        g.o(dText, i(DYConstants.DY_TEXT_SIZE, 0));
        dText.setGravity(com.jingdong.app.mall.home.deploy.view.base.a.a(q(DYConstants.DY_GRAVITY)));
        dText.setText(q("text"));
    }
}

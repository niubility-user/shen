package com.jingdong.app.mall.home.r.f.a;

import com.jd.framework.json.JDJSONObject;
import com.jingdong.app.mall.home.floor.model.entity.FloorEntity;
import com.jingdong.app.mall.home.floor.presenter.engine.FloorEngine;
import com.jingdong.app.mall.home.floor.view.baseui.IMallFloorUI;
import com.jingdong.jdsdk.utils.Md5Encrypt;

/* loaded from: classes4.dex */
public class a0 extends b<FloorEntity, FloorEngine, IMallFloorUI> {
    public String P(com.jingdong.app.mall.home.r.e.d dVar, int i2) {
        StringBuilder sb = new StringBuilder();
        sb.append(dVar.a);
        sb.append(i2);
        JDJSONObject jDJSONObject = dVar.f10687i;
        if (jDJSONObject != null) {
            sb.append(jDJSONObject);
        }
        return Md5Encrypt.md5(sb.toString());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.app.mall.home.r.f.a.b
    public void v(com.jingdong.app.mall.home.r.e.h hVar, com.jingdong.app.mall.home.r.e.d dVar) {
        super.v(hVar, dVar);
    }
}

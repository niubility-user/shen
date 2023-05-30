package com.jingdong.app.mall.home.r.f.a;

import com.jingdong.app.mall.home.floor.model.entity.EmptyFloorEntity;
import com.jingdong.app.mall.home.floor.presenter.engine.FloorEngine;
import com.jingdong.app.mall.home.floor.view.baseui.IMallFloorUI;

/* loaded from: classes4.dex */
public class k extends b<EmptyFloorEntity, FloorEngine, IMallFloorUI> {
    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.app.mall.home.r.f.a.b
    public void v(com.jingdong.app.mall.home.r.e.h hVar, com.jingdong.app.mall.home.r.e.d dVar) {
        super.v(hVar, dVar);
        IMallFloorUI iMallFloorUI = (IMallFloorUI) c();
        if (iMallFloorUI == null) {
            return;
        }
        iMallFloorUI.onRefreshView();
    }
}

package com.jingdong.app.mall.home.floor.presenter.engine;

import com.jingdong.app.mall.home.floor.model.entity.FlexCubeEntity;
import com.jingdong.app.mall.home.r.e.d;
import com.jingdong.app.mall.home.r.e.h;

/* loaded from: classes4.dex */
public class FlexCubeEngine extends FloorEngine<FlexCubeEntity> {
    @Override // com.jingdong.app.mall.home.floor.presenter.engine.FloorEngine
    /* renamed from: i  reason: merged with bridge method [inline-methods] */
    public void e(h hVar, d dVar, FlexCubeEntity flexCubeEntity) {
        if (hVar == null || dVar == null || flexCubeEntity == null) {
            return;
        }
        super.e(hVar, dVar, flexCubeEntity);
        flexCubeEntity.setFloorInfo(dVar.f10688j);
        flexCubeEntity.setFlexCubeModel(dVar.d());
    }
}

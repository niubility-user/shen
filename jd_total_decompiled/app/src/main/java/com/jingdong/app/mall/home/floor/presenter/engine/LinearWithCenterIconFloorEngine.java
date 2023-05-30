package com.jingdong.app.mall.home.floor.presenter.engine;

import com.jingdong.app.mall.home.floor.model.entity.LinearWithCenterIconFloorEntity;
import com.jingdong.app.mall.home.r.e.d;
import com.jingdong.app.mall.home.r.e.h;

/* loaded from: classes4.dex */
public class LinearWithCenterIconFloorEngine extends FloorEngine<LinearWithCenterIconFloorEntity> {
    @Override // com.jingdong.app.mall.home.floor.presenter.engine.FloorEngine
    /* renamed from: i  reason: merged with bridge method [inline-methods] */
    public void e(h hVar, d dVar, LinearWithCenterIconFloorEntity linearWithCenterIconFloorEntity) {
        if (linearWithCenterIconFloorEntity == null || hVar == null) {
            return;
        }
        c(dVar, linearWithCenterIconFloorEntity);
        linearWithCenterIconFloorEntity.setExpo(hVar.C);
        linearWithCenterIconFloorEntity.setExpoJson(hVar.f());
        linearWithCenterIconFloorEntity.setShowClose("1".equals(hVar.getJsonString("closeSwitch")));
        linearWithCenterIconFloorEntity.setClickUrl(hVar.getJsonString("clickUrl"));
        linearWithCenterIconFloorEntity.setExpoUrl(hVar.getJsonString("exposalUrl"));
    }
}

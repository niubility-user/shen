package com.jingdong.app.mall.home.r.f.a;

import com.jingdong.app.mall.home.floor.model.entity.LinearWithCenterIconFloorEntity;
import com.jingdong.app.mall.home.floor.presenter.engine.LinearWithCenterIconFloorEngine;
import com.jingdong.app.mall.home.floor.view.baseui.IMallFloorUI;

/* loaded from: classes4.dex */
public class r extends b<LinearWithCenterIconFloorEntity, LinearWithCenterIconFloorEngine, IMallFloorUI> {
    public String P() {
        return ((LinearWithCenterIconFloorEntity) this.d).getClickUrl();
    }

    public String Q() {
        return ((LinearWithCenterIconFloorEntity) this.d).getExpoUrl();
    }

    public boolean R() {
        return ((LinearWithCenterIconFloorEntity) this.d).isShowClose();
    }
}

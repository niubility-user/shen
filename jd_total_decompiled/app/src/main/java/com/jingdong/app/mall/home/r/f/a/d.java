package com.jingdong.app.mall.home.r.f.a;

import com.jingdong.app.mall.home.floor.model.entity.DeployFloorEntity;
import com.jingdong.app.mall.home.floor.presenter.engine.DeployFloorEngine;
import com.jingdong.app.mall.home.floor.view.view.MallFloorDeploy;

/* loaded from: classes4.dex */
public class d extends b<DeployFloorEntity, DeployFloorEngine, MallFloorDeploy> {
    public DeployFloorEntity P() {
        return (DeployFloorEntity) this.d;
    }

    public void Q() {
        MallFloorDeploy mallFloorDeploy = (MallFloorDeploy) c();
        if (mallFloorDeploy == null) {
            return;
        }
        if (((DeployFloorEntity) this.d).isValid()) {
            mallFloorDeploy.onRefreshView();
            mallFloorDeploy.onSetVisible(true);
            return;
        }
        mallFloorDeploy.cleanUI();
        mallFloorDeploy.onSetVisible(false);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.app.mall.home.r.f.a.b
    public void v(com.jingdong.app.mall.home.r.e.h hVar, com.jingdong.app.mall.home.r.e.d dVar) {
        super.v(hVar, dVar);
        Q();
    }
}

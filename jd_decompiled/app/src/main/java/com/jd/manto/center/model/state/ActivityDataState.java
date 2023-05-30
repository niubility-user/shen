package com.jd.manto.center.model.state;

import com.jd.manto.center.model.entity.MantoDiscoveryEntity;
import com.jingdong.cleanmvp.engine.BaseState;

/* loaded from: classes17.dex */
public class ActivityDataState extends BaseState {
    private MantoDiscoveryEntity mMantoDiscoveryEntity;

    @Override // com.jingdong.cleanmvp.engine.BaseState
    public void clearState(int i2) {
        this.mMantoDiscoveryEntity = null;
    }

    public MantoDiscoveryEntity getMantoDiscoveryEntity() {
        return this.mMantoDiscoveryEntity;
    }

    public void setMantoDiscoveryEntity(MantoDiscoveryEntity mantoDiscoveryEntity) {
        this.mMantoDiscoveryEntity = mantoDiscoveryEntity;
    }
}

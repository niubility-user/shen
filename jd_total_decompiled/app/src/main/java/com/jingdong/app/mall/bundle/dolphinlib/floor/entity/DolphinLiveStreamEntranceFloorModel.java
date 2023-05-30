package com.jingdong.app.mall.bundle.dolphinlib.floor.entity;

import com.jd.lib.babel.ifloor.entity.BaseFloorModel;

/* loaded from: classes19.dex */
public class DolphinLiveStreamEntranceFloorModel extends BaseFloorModel {
    public DolphinLiveStreamEntranceFloorConfig boardParams;
    public LiveStreamEntity liveStreamData;

    @Override // com.jd.lib.babel.ifloor.entity.BaseFloorModel
    public boolean handleData() {
        return this.liveStreamData != null;
    }
}

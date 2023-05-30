package com.jingdong.app.mall.bundle.dolphinlib.floor.entity;

import com.jd.lib.babel.ifloor.entity.BaseFloorModel;
import java.util.List;

/* loaded from: classes19.dex */
public class DolphinTopicBillboardFloorModel extends BaseFloorModel {
    public List<AdvertGroupData> advertGroupData;
    public DolphinTopicBillboardFloorConfig boardParams;

    @Override // com.jd.lib.babel.ifloor.entity.BaseFloorModel
    public boolean handleData() {
        List<AdvertGroupData> list = this.advertGroupData;
        return (list == null || list.isEmpty()) ? false : true;
    }
}

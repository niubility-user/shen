package com.jingdong.app.mall.bundle.dolphinlib.floor.entity;

import com.jd.lib.babel.ifloor.entity.BaseFloorModel;
import java.util.List;

/* loaded from: classes19.dex */
public class DolphinSubsidyColumnFloorModel extends BaseFloorModel {
    public List<AdvertGroupData> advertGroupData;
    public DolphinCountDownGridFloorConfig boardParams;
    public List<ProductGroupData> productGroupData;

    @Override // com.jd.lib.babel.ifloor.entity.BaseFloorModel
    public boolean handleData() {
        List<ProductGroupData> list = this.productGroupData;
        return list != null && list.size() > 0;
    }
}

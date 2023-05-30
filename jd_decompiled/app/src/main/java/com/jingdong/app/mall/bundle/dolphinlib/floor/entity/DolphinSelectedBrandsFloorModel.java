package com.jingdong.app.mall.bundle.dolphinlib.floor.entity;

import com.jd.lib.babel.ifloor.entity.BaseFloorModel;
import java.util.List;

/* loaded from: classes19.dex */
public class DolphinSelectedBrandsFloorModel extends BaseFloorModel {
    public List<AdvertGroupData> advertGroupData;
    public DolphinCountDownGridFloorConfig boardParams;
    public List<ProductGroupData> productGroupData;

    @Override // com.jd.lib.babel.ifloor.entity.BaseFloorModel
    public boolean handleData() {
        List<AdvertGroupData> list = this.advertGroupData;
        return list != null && this.productGroupData != null && list.size() > 0 && this.productGroupData.size() > 0;
    }
}

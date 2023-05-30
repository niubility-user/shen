package com.jingdong.app.mall.bundle.dolphinlib.floor.entity;

import com.jd.lib.babel.ifloor.entity.BaseFloorModel;
import java.util.List;

/* loaded from: classes19.dex */
public class DolphinCountDownGridFloorModel extends BaseFloorModel {
    public DolphinCountDownGridFloorConfig boardParams;
    public List<ProductGroupData> productGroupData;

    public List<GoodsEntity> getFirstList() {
        return this.productGroupData.get(0).productList;
    }

    @Override // com.jd.lib.babel.ifloor.entity.BaseFloorModel
    public int getTotalCount() {
        return 1;
    }

    @Override // com.jd.lib.babel.ifloor.entity.BaseFloorModel
    public boolean handleData() {
        List<ProductGroupData> list = this.productGroupData;
        return list != null && list.size() > 0 && this.productGroupData.get(0).productList != null && this.productGroupData.get(0).productList.size() > 0;
    }
}

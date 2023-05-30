package com.jingdong.app.mall.bundle.dolphinlib.floor.entity;

import com.jd.lib.babel.ifloor.entity.BaseFloorModel;
import java.util.List;

/* loaded from: classes19.dex */
public class DolphinAdGridFloorModel extends BaseFloorModel {
    public DolphinAdGridFloorConfig boardParams;
    public List<ProductGroupData> productGroupData;

    @Override // com.jd.lib.babel.ifloor.entity.BaseFloorModel
    public boolean handleData() {
        List<ProductGroupData> list = this.productGroupData;
        if (list == null || list.size() <= 0) {
            return false;
        }
        if (this.productGroupData.size() > 1) {
            if (this.productGroupData.get(1).productList == null || this.productGroupData.get(1).productList.size() <= 0) {
                return false;
            }
        } else if (this.productGroupData.get(0).productList == null || this.productGroupData.get(0).productList.size() <= 0) {
            return false;
        }
        return true;
    }
}

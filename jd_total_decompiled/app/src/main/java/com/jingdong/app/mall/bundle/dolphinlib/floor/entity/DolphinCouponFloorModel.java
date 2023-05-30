package com.jingdong.app.mall.bundle.dolphinlib.floor.entity;

import com.jd.lib.babel.ifloor.entity.BaseFloorModel;
import java.util.List;

/* loaded from: classes19.dex */
public class DolphinCouponFloorModel extends BaseFloorModel {
    public DolphinCouponFloorConfig boardParams;
    public List<CouponEntity> items;

    @Override // com.jd.lib.babel.ifloor.entity.BaseFloorModel
    public boolean handleData() {
        List<CouponEntity> list = this.items;
        return list != null && list.size() > 3;
    }
}

package com.jd.lib.babel.ifloor;

import com.jingdong.app.mall.bundle.dolphinlib.common.util.FloorIdUtil;
import com.jingdong.app.mall.bundle.dolphinlib.floor.entity.DolphinStoreFloorModel;
import com.jingdong.app.mall.bundle.dolphinlib.floor.view.DolphinStoreFloor;

/* loaded from: classes13.dex */
public class Babel_Floor_Helper_00022479 {
    public static void init() {
        BabelFloorProvider.putModel(FloorIdUtil.DOLPHIN_STORE_FLOOR_ID, DolphinStoreFloorModel.class);
        BabelFloorProvider.putView(FloorIdUtil.DOLPHIN_STORE_FLOOR_ID, DolphinStoreFloor.class);
    }
}

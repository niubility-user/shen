package com.jd.lib.babel.ifloor;

import com.jingdong.app.mall.bundle.dolphinlib.common.util.FloorIdUtil;
import com.jingdong.app.mall.bundle.dolphinlib.floor.entity.DolphinSelectedBrandsFloorModel;
import com.jingdong.app.mall.bundle.dolphinlib.floor.view.DolphinSelectedBrandsFloor;

/* loaded from: classes13.dex */
public class Babel_Floor_Helper_00015867 {
    public static void init() {
        BabelFloorProvider.putModel(FloorIdUtil.DOLPHIN_SELECTED_BRANDS_FLOOR_ID, DolphinSelectedBrandsFloorModel.class);
        BabelFloorProvider.putView(FloorIdUtil.DOLPHIN_SELECTED_BRANDS_FLOOR_ID, DolphinSelectedBrandsFloor.class);
    }
}

package com.jd.lib.babel.ifloor;

import com.jingdong.app.mall.bundle.dolphinlib.common.util.FloorIdUtil;
import com.jingdong.app.mall.bundle.dolphinlib.floor.entity.DolphinSingleColumnGridFloorModel;
import com.jingdong.app.mall.bundle.dolphinlib.floor.view.DolphinSingleColumnGridFloor;

/* loaded from: classes13.dex */
public class Babel_Floor_Helper_00015917 {
    public static void init() {
        BabelFloorProvider.putModel(FloorIdUtil.DOLPHIN_SINGLE_COLUMN_GRID_FLOOR_ID, DolphinSingleColumnGridFloorModel.class);
        BabelFloorProvider.putView(FloorIdUtil.DOLPHIN_SINGLE_COLUMN_GRID_FLOOR_ID, DolphinSingleColumnGridFloor.class);
    }
}

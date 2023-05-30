package com.jd.lib.babel.ifloor;

import com.jingdong.app.mall.bundle.dolphinlib.common.util.FloorIdUtil;
import com.jingdong.app.mall.bundle.dolphinlib.floor.entity.DolphinCountDownGridFloorModel;
import com.jingdong.app.mall.bundle.dolphinlib.floor.view.DolphinCountDownGridFloor;

/* loaded from: classes13.dex */
public class Babel_Floor_Helper_00015916 {
    public static void init() {
        BabelFloorProvider.putModel(FloorIdUtil.DOLPHIN_COUNT_DOWN_GRID_FLOOR_ID, DolphinCountDownGridFloorModel.class);
        BabelFloorProvider.putView(FloorIdUtil.DOLPHIN_COUNT_DOWN_GRID_FLOOR_ID, DolphinCountDownGridFloor.class);
    }
}

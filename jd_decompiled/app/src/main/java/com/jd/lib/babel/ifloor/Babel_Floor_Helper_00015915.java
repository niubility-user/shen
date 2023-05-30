package com.jd.lib.babel.ifloor;

import com.jingdong.app.mall.bundle.dolphinlib.common.util.FloorIdUtil;
import com.jingdong.app.mall.bundle.dolphinlib.floor.entity.DolphinSubsidyColumnFloorModel;
import com.jingdong.app.mall.bundle.dolphinlib.floor.view.DolphinSubsidyColumnFloor;

/* loaded from: classes13.dex */
public class Babel_Floor_Helper_00015915 {
    public static void init() {
        BabelFloorProvider.putModel(FloorIdUtil.DOLPHIN_SUBSIDY_COLUMN_FLOOR_ID, DolphinSubsidyColumnFloorModel.class);
        BabelFloorProvider.putView(FloorIdUtil.DOLPHIN_SUBSIDY_COLUMN_FLOOR_ID, DolphinSubsidyColumnFloor.class);
    }
}

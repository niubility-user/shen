package com.jd.lib.babel.ifloor;

import com.jingdong.app.mall.bundle.dolphinlib.common.util.FloorIdUtil;
import com.jingdong.app.mall.bundle.dolphinlib.floor.entity.DolphinAdGridFloorModel;
import com.jingdong.app.mall.bundle.dolphinlib.floor.view.DolphinAdGridFloor;

/* loaded from: classes13.dex */
public class Babel_Floor_Helper_00015913 {
    public static void init() {
        BabelFloorProvider.putModel(FloorIdUtil.DOLPHIN_AD_GR_FLOOR_ID, DolphinAdGridFloorModel.class);
        BabelFloorProvider.putView(FloorIdUtil.DOLPHIN_AD_GR_FLOOR_ID, DolphinAdGridFloor.class);
    }
}

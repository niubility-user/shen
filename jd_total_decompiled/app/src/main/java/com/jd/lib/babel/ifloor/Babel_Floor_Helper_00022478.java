package com.jd.lib.babel.ifloor;

import com.jingdong.app.mall.bundle.dolphinlib.common.util.FloorIdUtil;
import com.jingdong.app.mall.bundle.dolphinlib.floor.entity.DolphinTopicBillboardFloorModel;
import com.jingdong.app.mall.bundle.dolphinlib.floor.view.DolphinTopicBillboardFloor;

/* loaded from: classes13.dex */
public class Babel_Floor_Helper_00022478 {
    public static void init() {
        BabelFloorProvider.putModel(FloorIdUtil.DOLPHIN_TOPIC_BILLBOARD_FLOOR_ID, DolphinTopicBillboardFloorModel.class);
        BabelFloorProvider.putView(FloorIdUtil.DOLPHIN_TOPIC_BILLBOARD_FLOOR_ID, DolphinTopicBillboardFloor.class);
    }
}

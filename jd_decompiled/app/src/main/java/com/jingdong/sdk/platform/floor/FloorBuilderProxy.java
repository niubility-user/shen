package com.jingdong.sdk.platform.floor;

import com.jingdong.sdk.platform.floor.entity.BaseFloorData;
import com.jingdong.sdk.platform.floor.listener.OnFloorShowedListener;

/* loaded from: classes10.dex */
public class FloorBuilderProxy {
    @Deprecated
    public static FloorBuilder getFloorBuilder(BaseFloorData baseFloorData) {
        return FloorBuilderImpl.createInstance(baseFloorData);
    }

    public static FloorBuilder getFloorBuilder(BaseFloorData baseFloorData, OnFloorShowedListener onFloorShowedListener) {
        return FloorBuilderImpl.createInstance(baseFloorData, onFloorShowedListener);
    }
}

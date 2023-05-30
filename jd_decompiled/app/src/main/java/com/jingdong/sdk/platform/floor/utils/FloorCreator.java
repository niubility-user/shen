package com.jingdong.sdk.platform.floor.utils;

import android.content.Context;
import android.view.ViewGroup;
import com.jingdong.sdk.platform.floor.BaseFloor;
import com.jingdong.sdk.platform.floor.entity.BaseFloorData;

/* loaded from: classes10.dex */
public abstract class FloorCreator<T extends BaseFloor> {
    public abstract T createFloor(Context context, BaseFloorData baseFloorData, String str, Class<T> cls, ViewGroup viewGroup);
}

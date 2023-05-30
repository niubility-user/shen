package com.jingdong.sdk.platform.floor;

import com.jingdong.sdk.platform.floor.entity.BaseTemplateEntity;
import com.jingdong.sdk.platform.floor.listener.OnFloorShowedListener;
import com.jingdong.sdk.platform.floor.utils.FloorTemplateCreator;
import java.util.ArrayList;

/* loaded from: classes10.dex */
public interface FloorBuilder {
    ArrayList<BaseTemplateEntity> build();

    int getFloorViewType(String str);

    void hideFloor(BaseTemplateEntity baseTemplateEntity);

    void hideFloor(String str, String str2);

    void onDestroy();

    void setFloorTemplateCreator(FloorTemplateCreator floorTemplateCreator);

    void setOnFloorShowedListener(OnFloorShowedListener onFloorShowedListener);

    void showAllFloors(ArrayList<BaseTemplateEntity> arrayList);

    void showFloor(BaseTemplateEntity baseTemplateEntity);

    void updateFloors();
}

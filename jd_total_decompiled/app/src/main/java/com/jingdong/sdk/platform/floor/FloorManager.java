package com.jingdong.sdk.platform.floor;

/* loaded from: classes10.dex */
public interface FloorManager {
    Class<? extends BaseFloor> getClassById(String str);

    Class<? extends BaseFloor> getClassByType(int i2);

    String getIdByType(int i2);

    int getTypeById(String str);

    boolean isEmptyLine(String str);

    boolean isFloorReUsed(int i2);

    boolean isFloorReUsed(String str);

    boolean isLine(int i2);

    boolean isLine(String str);

    void registerFloor(String str, Class<? extends BaseFloor> cls);

    void registerFloor(String str, Class<? extends BaseFloor> cls, boolean z, boolean z2);

    void setEmptyDiliverLine(String str);
}

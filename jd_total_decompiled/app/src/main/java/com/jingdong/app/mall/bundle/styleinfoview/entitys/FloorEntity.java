package com.jingdong.app.mall.bundle.styleinfoview.entitys;

import android.text.TextUtils;
import com.jingdong.app.mall.bundle.styleinfoview.temp.FloorTemplate;

/* loaded from: classes3.dex */
public class FloorEntity {
    public String floorBusiness;
    public int floorType;
    public Object mData;
    public FloorTemplate mFloorTemplate;
    public String mId;
    public int sortId;
    public boolean showSimple = false;
    public boolean isShow = false;
    public boolean isAddToFloor = false;
    public boolean isExpo = false;
    public boolean isExpo2 = false;
    public boolean expoFlag = false;

    public FloorEntity() {
    }

    public void addToFloor() {
        this.isAddToFloor = true;
    }

    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof FloorEntity)) {
            return false;
        }
        FloorEntity floorEntity = (FloorEntity) obj;
        if (floorEntity.floorType == this.floorType) {
            return (TextUtils.isEmpty(floorEntity.floorBusiness) && TextUtils.isEmpty(this.floorBusiness)) || TextUtils.equals(floorEntity.floorBusiness, this.floorBusiness);
        }
        return false;
    }

    public String getMtaParam() {
        return "";
    }

    public boolean isMe(FloorTemplate floorTemplate) {
        return false;
    }

    public FloorEntity(int i2) {
        this.floorType = i2;
    }

    public FloorEntity(int i2, String str) {
        this.floorType = i2;
        this.floorBusiness = str;
    }
}

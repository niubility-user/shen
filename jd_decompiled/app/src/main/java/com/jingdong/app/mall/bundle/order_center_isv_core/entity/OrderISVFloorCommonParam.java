package com.jingdong.app.mall.bundle.order_center_isv_core.entity;

import androidx.annotation.NonNull;
import com.jingdong.sdk.platform.base.ICloneableData;

/* loaded from: classes3.dex */
public class OrderISVFloorCommonParam implements ICloneableData, Cloneable {
    public String isvOtherJsonStr;

    @NonNull
    protected Object clone() throws CloneNotSupportedException {
        try {
            return super.clone();
        } catch (CloneNotSupportedException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    @Override // com.jingdong.sdk.platform.base.ICloneableData
    public Object cloneData() {
        try {
            return clone();
        } catch (CloneNotSupportedException e2) {
            e2.printStackTrace();
            return null;
        }
    }
}

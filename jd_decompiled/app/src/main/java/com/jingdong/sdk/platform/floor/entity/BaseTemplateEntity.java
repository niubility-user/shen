package com.jingdong.sdk.platform.floor.entity;

import android.text.TextUtils;
import com.jingdong.sdk.platform.base.BaseEntity;
import com.jingdong.sdk.platform.floor.constant.BaseFloorConstant;
import com.jingdong.sdk.platform.floor.isv.IBaseCooperateExt;

/* loaded from: classes10.dex */
public class BaseTemplateEntity extends BaseEntity {
    public String divideLine;
    public int itemViewType;
    public IBaseCooperateExt mBaseCooperateExt;
    public Object mExtData;
    public DynPopStyle mfPopStyle;
    public String mfStyleId;
    public String refId;
    public int sortId;
    private boolean isAddToFloor = false;
    public boolean isRoundChanged = false;
    public boolean isRoundUp = false;
    public boolean isRoundDown = false;
    public boolean isCoveredByNextFloor = false;
    public int coveredHeight = 0;
    public int originalPaddingTop = -1;
    public int originalPaddingBottom = -1;
    public int extraPaddingTop = -1;
    public int extraPaddingBottom = -1;

    /* loaded from: classes10.dex */
    public class DynPopStyle {
        public String height;
        public String popStyleId;

        public DynPopStyle() {
        }
    }

    public BaseTemplateEntity(String str) {
        this.mId = str;
    }

    public void addToFloor(boolean z) {
        this.isAddToFloor = z;
    }

    @Override // com.jingdong.sdk.platform.base.BaseEntity
    public final boolean equals(Object obj) {
        if (obj == null || !(obj instanceof BaseTemplateEntity)) {
            return false;
        }
        BaseTemplateEntity baseTemplateEntity = (BaseTemplateEntity) obj;
        if (baseTemplateEntity != null) {
            if (TextUtils.equals(baseTemplateEntity.mId, this.mId)) {
                return (TextUtils.isEmpty(baseTemplateEntity.bId) && TextUtils.isEmpty(this.bId)) || TextUtils.equals(baseTemplateEntity.bId, this.bId);
            }
            return false;
        }
        return super.equals(obj);
    }

    public boolean equalsContent(Object obj) {
        return this == obj;
    }

    public boolean equalsObject(Object obj) {
        return this == obj;
    }

    public boolean isAddToFloor() {
        return this.isValid && this.isAddToFloor;
    }

    public boolean isRN() {
        return BaseFloorConstant.PLATFORM_FLOOR_RN.equals(this.mId);
    }

    public BaseTemplateEntity(String str, String str2) {
        this.mId = str;
        this.bId = str2;
    }

    public BaseTemplateEntity() {
    }
}

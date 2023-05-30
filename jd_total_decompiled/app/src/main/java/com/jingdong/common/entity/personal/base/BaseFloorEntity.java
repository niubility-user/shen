package com.jingdong.common.entity.personal.base;

import androidx.annotation.NonNull;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.jingdong.sdk.platform.floor.entity.BaseTemplateEntity;

/* loaded from: classes5.dex */
public class BaseFloorEntity extends BaseTemplateEntity implements AdditionalCallback, EntityOperationExtendedCallback, SpanSizeLookup {
    public PlatformCFEntity cf;

    public BaseFloorEntity() {
        addToFloor();
    }

    @Override // com.jingdong.common.entity.personal.base.AdditionalCallback
    public void addToFloor() {
        addToFloor(true);
    }

    @Override // com.jingdong.common.entity.personal.base.EntityOperationExtendedCallback
    public void extendedOperation() {
        addToFloor(true);
        this.isShow = false;
    }

    @Override // com.jingdong.common.entity.personal.base.SpanSizeLookup
    public int getSpanSize(int i2) {
        return i2;
    }

    public void parserEncrypt(@NonNull Gson gson, @NonNull JsonObject jsonObject) {
    }

    @Override // com.jingdong.common.entity.personal.base.AdditionalCallback
    public void setDividerLine() {
        PlatformCFEntity platformCFEntity = this.cf;
        if (platformCFEntity != null) {
            this.divideLine = platformCFEntity.spl;
        }
    }

    @Override // com.jingdong.common.entity.personal.base.AdditionalCallback
    public void setSort(int i2) {
        this.sortId = i2;
    }

    public BaseFloorEntity(String str, String str2) {
        super(str, str2);
    }
}

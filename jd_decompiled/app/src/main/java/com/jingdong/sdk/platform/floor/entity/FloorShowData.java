package com.jingdong.sdk.platform.floor.entity;

import com.jingdong.sdk.platform.base.UnProguard;
import java.util.ArrayList;

/* loaded from: classes10.dex */
public class FloorShowData extends UnProguard {
    public BaseTemplateEntity floor;
    public ArrayList<BaseTemplateEntity> floors;
    public boolean isAdd;
    public boolean isUpdate = false;

    public FloorShowData(ArrayList<BaseTemplateEntity> arrayList) {
        this.floors = arrayList;
    }

    public FloorShowData(ArrayList<BaseTemplateEntity> arrayList, BaseTemplateEntity baseTemplateEntity, boolean z) {
        this.floors = arrayList;
        this.floor = baseTemplateEntity;
        this.isAdd = z;
    }
}

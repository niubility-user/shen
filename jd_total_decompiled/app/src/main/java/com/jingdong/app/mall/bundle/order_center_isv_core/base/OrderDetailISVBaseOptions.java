package com.jingdong.app.mall.bundle.order_center_isv_core.base;

import android.app.Activity;
import android.text.TextUtils;
import com.jingdong.app.mall.bundle.order_center_isv_core.entity.OrderISVFloorCommonParam;
import com.jingdong.common.impl.parse.JDJSONParser;
import com.jingdong.sdk.platform.floor.BaseFloor;
import com.jingdong.sdk.platform.floor.entity.BaseTemplateEntity;
import com.jingdong.sdk.platform.floor.isv.BaseLoadFloorOption;
import java.util.HashMap;

/* loaded from: classes3.dex */
public abstract class OrderDetailISVBaseOptions extends BaseLoadFloorOption {
    HashMap<String, Class<? extends Object>> floorDataRegisterMap;
    HashMap<String, Class<? extends BaseFloor>> floorRegisterMap;

    public OrderDetailISVBaseOptions() {
        registerFloorAndTemplate();
    }

    private Object parseJsonStringToCommonParam(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        return new JDJSONParser().parseJsonToObject(str, OrderISVFloorCommonParam.class);
    }

    private Object parseJsonStringToObj(String str, String str2) {
        HashMap<String, Class<? extends Object>> hashMap;
        return (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2) || (hashMap = this.floorDataRegisterMap) == null || hashMap.get(str) == null) ? str2 : new JDJSONParser().parseJsonToObject(str2, this.floorDataRegisterMap.get(str));
    }

    @Override // com.jingdong.sdk.platform.floor.isv.BaseLoadFloorOption
    public boolean buildTemplate(BaseTemplateEntity baseTemplateEntity) {
        if (TextUtils.isEmpty(baseTemplateEntity.mId)) {
            return false;
        }
        Object parseJsonStringToObj = parseJsonStringToObj(baseTemplateEntity.mId, baseTemplateEntity.mData.toString());
        OrderDetailISVBaseFloorData orderDetailISVBaseFloorData = new OrderDetailISVBaseFloorData();
        orderDetailISVBaseFloorData.data = parseJsonStringToObj;
        orderDetailISVBaseFloorData.cooperateExt = this.cooperateExt;
        baseTemplateEntity.mData = orderDetailISVBaseFloorData;
        return parseJsonStringToObj != null;
    }

    @Override // com.jingdong.sdk.platform.floor.isv.BaseLoadFloorOption
    public HashMap<String, Class<? extends BaseFloor>> getFloorClass() {
        return this.floorRegisterMap;
    }

    @Override // com.jingdong.sdk.platform.floor.isv.BaseLoadFloorOption
    public void onMainViewScrolled(Activity activity, int i2) {
    }

    @Override // com.jingdong.sdk.platform.floor.isv.BaseLoadFloorOption
    public void onStartBuildTemplate() {
    }

    protected void registerFloor(String str, Class<? extends Object> cls, Class<? extends BaseFloor> cls2) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        if (this.floorRegisterMap == null) {
            this.floorRegisterMap = new HashMap<>(5);
        }
        this.floorRegisterMap.put(str, cls2);
        if (this.floorDataRegisterMap == null) {
            this.floorDataRegisterMap = new HashMap<>(5);
        }
        this.floorDataRegisterMap.put(str, cls);
    }

    protected abstract void registerFloorAndTemplate();
}

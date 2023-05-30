package com.jingdong.jdma.minterface;

import java.util.HashMap;

/* loaded from: classes12.dex */
public class ExposureInterfaceParam extends BaseEvent {
    public String eventId;
    public String eventParam;
    public String jsonParam;
    public HashMap<String, String> map;
    public String orderId;
    public String page_id;
    public String page_name;
    public String page_param;
    public String shopId;
    public String sku;

    @Override // com.jingdong.jdma.minterface.BaseEvent
    protected void addDataToMap(HashMap<String, String> hashMap) {
        hashMap.put("eid", this.eventId);
        hashMap.put("ela", this.eventParam);
        hashMap.put("ctp", this.page_name);
        hashMap.put("par", this.page_param);
        hashMap.put("page_id", this.page_id);
        hashMap.put("shp", this.shopId);
        hashMap.put("sku", this.sku);
        hashMap.put("ord", this.orderId);
        hashMap.put("json_param", this.jsonParam);
    }

    @Override // com.jingdong.jdma.minterface.BaseEvent
    public final String getLogType() {
        return "ep";
    }

    @Override // com.jingdong.jdma.minterface.BaseEvent
    public final String getLts() {
        return null;
    }
}

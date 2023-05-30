package com.jingdong.jdma.minterface;

import java.util.HashMap;

/* loaded from: classes12.dex */
public class ClickInterfaceParam extends BaseEvent {
    public String event_func;
    public String event_id;
    public String event_param;
    public String jsonParam;
    public HashMap<String, String> map;
    public String next_page_name;
    public String ord;
    public String page_id;
    public String page_name;
    public String page_param;
    public String shop;
    public String sku;
    public String sku_tag;

    @Override // com.jingdong.jdma.minterface.BaseEvent
    protected void addDataToMap(HashMap<String, String> hashMap) {
        hashMap.put("cls", this.event_id);
        hashMap.put("clp", this.event_param);
        hashMap.put("event_func", this.event_func);
        hashMap.put("tar", this.next_page_name);
        hashMap.put("ctp", this.page_name);
        hashMap.put("par", this.page_param);
        hashMap.put("page_id", this.page_id);
        hashMap.put("sku_tag", this.sku_tag);
        hashMap.put("sku", this.sku);
        hashMap.put("ord", this.ord);
        hashMap.put("shp", this.shop);
        hashMap.put("json_param", this.jsonParam);
    }

    @Override // com.jingdong.jdma.minterface.BaseEvent
    public final String getLogType() {
        return "cl";
    }

    @Override // com.jingdong.jdma.minterface.BaseEvent
    public final String getLts() {
        return null;
    }
}

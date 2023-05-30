package com.jingdong.jdma.minterface;

import java.util.HashMap;

/* loaded from: classes12.dex */
public class PvInterfaceParam extends BaseEvent {
    public String click_url;
    public String lastPage;
    public String lastPageName;
    public String lastPage_param;
    public String loadTime;
    public HashMap<String, String> map;
    public String ord;
    public String page_id;
    public String page_name;
    public String page_param;
    public String ref_event_id;
    public String ref_event_param;
    public String shp;
    public String sku;
    public String sku_tag;
    public String uct;

    @Override // com.jingdong.jdma.minterface.BaseEvent
    protected void addDataToMap(HashMap<String, String> hashMap) {
        hashMap.put("ldt", this.loadTime);
        hashMap.put("uct", this.uct);
        hashMap.put("ctp", this.page_name);
        hashMap.put("par", this.page_param);
        hashMap.put("page_id", this.page_id);
        hashMap.put("sku_tag", this.sku_tag);
        hashMap.put("click_url", this.click_url);
        hashMap.put("sku", this.sku);
        hashMap.put("ord", this.ord);
        hashMap.put("shp", this.shp);
        hashMap.put("rpd", this.lastPage);
        hashMap.put("ref_clp", this.ref_event_param);
    }

    @Override // com.jingdong.jdma.minterface.BaseEvent
    public final String getLogType() {
        return "pv";
    }

    @Override // com.jingdong.jdma.minterface.BaseEvent
    public final String getLts() {
        return null;
    }
}

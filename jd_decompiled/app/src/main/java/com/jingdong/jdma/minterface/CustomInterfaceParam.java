package com.jingdong.jdma.minterface;

import java.util.HashMap;

/* loaded from: classes12.dex */
public class CustomInterfaceParam extends BaseEvent {
    public String ctp;
    public String eid;
    public String ela;
    public String eli;
    public HashMap<String, String> map;
    public String ord;
    public String par;
    public String shp;
    public String sku;

    @Override // com.jingdong.jdma.minterface.BaseEvent
    protected void addDataToMap(HashMap<String, String> hashMap) {
        hashMap.put("eid", this.eid);
        hashMap.put("ela", this.ela);
        hashMap.put("eli", this.eli);
        hashMap.put("ctp", this.ctp);
        hashMap.put("par", this.par);
        hashMap.put("shp", this.shp);
        hashMap.put("sku", this.sku);
        hashMap.put("ord", this.ord);
    }

    @Override // com.jingdong.jdma.minterface.BaseEvent
    public final String getLogType() {
        return "sr";
    }

    @Override // com.jingdong.jdma.minterface.BaseEvent
    public final String getLts() {
        return "ce";
    }
}

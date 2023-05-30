package com.jingdong.jdsdk.mta.param;

import com.jingdong.jdma.minterface.BaseEvent;
import java.util.HashMap;

/* loaded from: classes14.dex */
public class MtaClickParam extends BaseEvent {
    private static final String LOG_TYPE = "sr";
    private static final String LTS = "cl";
    public String eventId;
    public HashMap<String, String> ext;
    public String moduleId;
    public String pageId;
    public String param;

    public MtaClickParam(String str, String str2, String str3, String str4) {
        this(str, str2, str3, str4, null);
    }

    @Override // com.jingdong.jdma.minterface.BaseEvent
    public void addDataToMap(HashMap<String, String> hashMap) {
        if (hashMap != null) {
            hashMap.put("moid", this.moduleId);
            hashMap.put("pgid", this.pageId);
            hashMap.put("eid", this.eventId);
            hashMap.put("param", this.param);
            HashMap<String, String> hashMap2 = this.ext;
            if (hashMap2 != null) {
                hashMap.putAll(hashMap2);
            }
        }
    }

    @Override // com.jingdong.jdma.minterface.BaseEvent
    public String getLogType() {
        return LOG_TYPE;
    }

    @Override // com.jingdong.jdma.minterface.BaseEvent
    public String getLts() {
        return LTS;
    }

    public MtaClickParam(String str, String str2, String str3, String str4, HashMap<String, String> hashMap) {
        this.moduleId = str;
        this.pageId = str2;
        this.eventId = str3;
        this.param = str4;
        this.ext = hashMap;
    }
}

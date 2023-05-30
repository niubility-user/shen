package com.jingdong.jdsdk.mta.param;

import com.jingdong.jdma.minterface.BaseEvent;
import java.util.HashMap;

/* loaded from: classes14.dex */
public class MtaPvParam extends BaseEvent {
    private static final String LOG_TYPE = "sr";
    private static final String LTS = "pv";
    public HashMap<String, String> ext;
    public String moduleId;
    public String pageId;
    public String param;

    public MtaPvParam(String str, String str2, String str3) {
        this(str, str2, str3, null);
    }

    @Override // com.jingdong.jdma.minterface.BaseEvent
    public void addDataToMap(HashMap<String, String> hashMap) {
        if (hashMap != null) {
            hashMap.put("moid", this.moduleId);
            hashMap.put("pgid", this.pageId);
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

    public MtaPvParam(String str, String str2, String str3, HashMap<String, String> hashMap) {
        this.moduleId = str;
        this.pageId = str2;
        this.param = str3;
        this.ext = hashMap;
    }
}

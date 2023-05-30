package com.jingdong.sdk.platform.floor.isv;

import java.util.HashMap;

/* loaded from: classes10.dex */
public class MtaData {
    public String eventId;
    public HashMap<String, String> extParam;
    public boolean isClick;
    public String jsonParam;

    public MtaData(boolean z, String str, String str2) {
        this.eventId = str;
        this.jsonParam = str2;
        this.isClick = z;
    }

    public MtaData(boolean z, String str, String str2, HashMap<String, String> hashMap) {
        this.eventId = str;
        this.jsonParam = str2;
        this.extParam = hashMap;
        this.isClick = z;
    }
}

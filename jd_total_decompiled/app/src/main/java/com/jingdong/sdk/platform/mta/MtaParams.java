package com.jingdong.sdk.platform.mta;

import com.jingdong.sdk.platform.base.UnProguard;

/* loaded from: classes10.dex */
public class MtaParams extends UnProguard {
    public static final String MTA_EVENT_TYPE_CLICK = "1";
    public static final String MTA_EVENT_TYPE_EXPO = "2";
    public String eventId;
    public String eventParam;
    public String eventType;
    public String pageParam;

    public MtaParams(String str, String str2, String str3, String str4) {
        this.eventType = "1";
        this.eventId = str;
        this.eventParam = str2;
        this.pageParam = str3;
        this.eventType = str4;
    }

    public MtaParams(String str, String str2, String str3) {
        this.eventType = "1";
        this.eventId = str;
        this.eventParam = str2;
        this.pageParam = str3;
    }

    public MtaParams(String str, String str2) {
        this.eventType = "1";
        this.eventId = str;
        this.eventParam = str2;
    }

    public MtaParams(String str) {
        this.eventType = "1";
        this.eventId = str;
    }
}

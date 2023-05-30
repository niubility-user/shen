package com.jingdong.common.XView2.entity;

import com.jd.framework.json.JDJSONObject;

/* loaded from: classes5.dex */
public class MtaEntity {
    public String eventFunc;
    public String eventId;
    public String eventParam;
    public JDJSONObject jsonObjParam;
    public String nextClassName;
    public String pageId;
    public String pageName;
    public String pageParam;

    public MtaEntity() {
    }

    public MtaEntity(String str, String str2, String str3, JDJSONObject jDJSONObject) {
        this.eventId = str;
        this.eventParam = str2;
        this.pageId = str3;
        this.jsonObjParam = jDJSONObject;
    }

    public MtaEntity(String str, String str2, String str3) {
        this.eventId = str;
        this.eventParam = str2;
        this.pageId = str3;
    }

    public MtaEntity(String str, String str2, String str3, String str4, String str5, String str6, String str7) {
        this.eventId = str;
        this.eventParam = str2;
        this.eventFunc = str3;
        this.pageName = str4;
        this.pageParam = str5;
        this.nextClassName = str6;
        this.pageId = str7;
    }
}

package com.jd.viewkit.thirdinterface.model;

/* loaded from: classes18.dex */
public class JDViewKitParamsModel {
    private String activityId;
    private String pageId;

    public JDViewKitParamsModel(String str, String str2) {
        this.pageId = str;
        this.activityId = str2;
    }

    public String getActivityId() {
        return this.activityId;
    }

    public String getPageId() {
        return this.pageId;
    }

    public void setActivityId(String str) {
        this.activityId = str;
    }

    public void setPageId(String str) {
        this.pageId = str;
    }
}

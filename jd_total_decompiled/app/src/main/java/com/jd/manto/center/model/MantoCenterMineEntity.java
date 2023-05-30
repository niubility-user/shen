package com.jd.manto.center.model;

import androidx.annotation.Keep;
import java.util.List;

@Keep
/* loaded from: classes17.dex */
public class MantoCenterMineEntity {
    public static final int TYPE_ADD_APP = 4;
    public static final int TYPE_HEADER = 0;
    public static final int TYPE_LAST_USED = 1;
    public static final int TYPE_LIKED = 2;
    public static final int TYPE_MORE_APP = 3;
    public String code;
    public List<Data> data;
    public String searchWordText;
    public boolean success;
    public String tinyAppIntroduction;

    @Keep
    /* loaded from: classes17.dex */
    public static class AppInfo {
        public String actionType;
        public String appId;
        public String appName;
        public String appType;
        public String isBetaVersion;
        public String isFollow;
        public String itemType;
        public String logoUrl;
        public int nativeItemType;
        public String sectionId;
        public String title;

        public String toString() {
            return "AppInfo{title='" + this.title + "', appId='" + this.appId + "', appName='" + this.appName + "', logoUrl='" + this.logoUrl + "', appType='" + this.appType + "', nativeItemType=" + this.nativeItemType + ", isFollow='" + this.isFollow + "', sectionId='" + this.sectionId + "', itemType='" + this.itemType + "', actionType='" + this.actionType + "'}";
        }
    }

    @Keep
    /* loaded from: classes17.dex */
    public static class Data {
        public List<AppInfo> appList;
        public String title;
        public String type;
    }
}

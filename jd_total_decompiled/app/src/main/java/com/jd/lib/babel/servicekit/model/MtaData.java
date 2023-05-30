package com.jd.lib.babel.servicekit.model;

import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

/* loaded from: classes13.dex */
public class MtaData {
    private String eventId;
    private String eventParam;
    private HashMap<String, String> ext;
    private HashMap<String, String> floorSrvInfo;
    private String jsonParam;
    private String pageName;
    private String pageParam;
    private boolean split;
    private MtaType mtaType = MtaType.MTATYPE_CLICK;
    private String eventFunc = "onClick";
    private String nextClassName = "";
    private String pageId = "";

    /* loaded from: classes13.dex */
    public static class Builder {
        private MtaData mtaData;

        private Builder(String str, String str2) {
            MtaData mtaData = new MtaData();
            this.mtaData = mtaData;
            mtaData.eventId = str;
            this.mtaData.eventParam = str2;
        }

        public static Builder from(String str, String str2) {
            return new Builder(str, str2);
        }

        public MtaData build() {
            return this.mtaData;
        }

        public Builder eventFunc(String str) {
            this.mtaData.eventFunc = str;
            return this;
        }

        public Builder ext(HashMap<String, String> hashMap) {
            this.mtaData.ext = hashMap;
            return this;
        }

        public Builder floorSrvInfo(HashMap<String, String> hashMap) {
            this.mtaData.floorSrvInfo = hashMap;
            return this;
        }

        public Builder jsonParam(String str) {
            this.mtaData.jsonParam = str;
            return this;
        }

        public Builder mtaType(MtaType mtaType) {
            this.mtaData.mtaType = mtaType;
            return this;
        }

        public Builder page(String str, String str2) {
            this.mtaData.pageName = str;
            this.mtaData.pageParam = str2;
            return this;
        }

        public Builder pageId(String str) {
            this.mtaData.pageId = str;
            return this;
        }

        public Builder split(boolean z) {
            this.mtaData.split = z;
            return this;
        }

        public static Builder from(String str, String str2, MtaType mtaType) {
            return new Builder(str, str2, mtaType);
        }

        private Builder(String str, String str2, MtaType mtaType) {
            MtaData mtaData = new MtaData();
            this.mtaData = mtaData;
            mtaData.eventId = str;
            this.mtaData.eventParam = str2;
            this.mtaData.mtaType = mtaType;
        }
    }

    protected MtaData() {
    }

    public String getEventFunc() {
        return this.eventFunc;
    }

    public String getEventId() {
        return this.eventId;
    }

    public String getEventParam() {
        return this.eventParam;
    }

    public HashMap<String, String> getExt() {
        return this.ext;
    }

    public HashMap<String, String> getFloorSrvInfo() {
        return this.floorSrvInfo;
    }

    public String getJsonParam() {
        return this.jsonParam;
    }

    public String getMergedEventParam() {
        return mergeSrv(this.eventParam, this.floorSrvInfo);
    }

    public MtaType getMtaType() {
        return this.mtaType;
    }

    public String getNextClassName() {
        return this.nextClassName;
    }

    public String getPageId() {
        return this.pageId;
    }

    public String getPageName() {
        return this.pageName;
    }

    public String getPageParam() {
        return this.pageParam;
    }

    public boolean isSplit() {
        return this.split;
    }

    public String mergeSrv(String str, Map<String, String> map) {
        JSONObject jSONObject;
        if (str == null || map == null) {
            return str;
        }
        if (map.size() != 0) {
            try {
                jSONObject = new JSONObject(str);
                for (Map.Entry<String, String> entry : map.entrySet()) {
                    jSONObject.put(entry.getKey(), entry.getValue());
                }
            } catch (Exception unused) {
                return str;
            }
        }
        return jSONObject.toString();
    }
}

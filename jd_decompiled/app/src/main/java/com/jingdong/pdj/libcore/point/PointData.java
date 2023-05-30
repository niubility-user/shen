package com.jingdong.pdj.libcore.point;

import com.jd.framework.json.JDJSONArray;
import com.jd.framework.json.JDJSONObject;
import java.io.Serializable;
import java.util.List;
import point.interfaces.DJPointData;

/* loaded from: classes7.dex */
public class PointData extends DJPointData implements Serializable {
    public boolean bySelfReport;
    public Object et_model_param;
    public String event_id;
    public String expo_event_id;
    public Object extra_params;
    public JDJSONObject floorAreaObject;
    public boolean isCache;
    public boolean isDetailPage;
    public String json_param;
    public List<String> json_param_list;
    public JDJSONObject moduleAreaObject;
    public String page_id;
    public String page_name;
    public JDJSONArray paramsArray;
    public int resourceIndex;
    public String stm;
    public List<String> stms;
    public JDJSONObject subTabAreaObject;
    public String togetherKey;
    public boolean togetherReport;
    public String togetherSizeKey;

    public PointData() {
    }

    public String pointLog() {
        return ":PointData{page_id='" + this.page_id + "', page_name='" + this.page_name + "', event_id='" + this.event_id + "', expo_event_id='" + this.expo_event_id + "'}";
    }

    public String toString() {
        return "PointData{page_id='" + this.page_id + "', page_name='" + this.page_name + "', event_id='" + this.event_id + "', expo_event_id='" + this.expo_event_id + "', json_param='" + this.json_param + "', json_param_list='" + this.json_param_list + "', et_model_param='" + this.et_model_param + "'}";
    }

    public PointData(String str, String str2, String str3, String str4, String str5, boolean z, boolean z2) {
        this.page_id = str;
        this.event_id = str2;
        this.json_param = str3;
        this.page_name = str4;
        this.expo_event_id = str5;
        this.reported = z;
        this.togetherReport = z2;
    }

    public PointData(String str, String str2, String str3, String str4, String str5, boolean z, boolean z2, boolean z3) {
        this.page_id = str;
        this.event_id = str3;
        this.json_param = str5;
        this.page_name = str2;
        this.expo_event_id = str4;
        this.togetherReport = z;
        this.isCache = z2;
        this.isDetailPage = z3;
    }

    public PointData(String str, String str2, String str3, String str4, List<String> list, boolean z, boolean z2, boolean z3) {
        this.page_id = str;
        this.event_id = str3;
        this.json_param_list = list;
        this.page_name = str2;
        this.expo_event_id = str4;
        this.togetherReport = z;
        this.isCache = z2;
        this.isDetailPage = z3;
    }

    public PointData(String str, String str2, String str3, String str4, String str5, boolean z, boolean z2, String str6, boolean z3, boolean z4, Object obj) {
        this.page_id = str;
        this.page_name = str2;
        this.event_id = str3;
        this.expo_event_id = str4;
        this.json_param = str5;
        this.reported = z;
        this.togetherReport = z2;
        this.togetherKey = str6;
        this.isCache = z3;
        this.isDetailPage = z4;
        this.extra_params = obj;
    }
}

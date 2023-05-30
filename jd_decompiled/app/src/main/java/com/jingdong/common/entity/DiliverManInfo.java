package com.jingdong.common.entity;

import com.jd.framework.json.JDJSON;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.jdsdk.utils.JSONObjectProxy;
import java.io.Serializable;

/* loaded from: classes5.dex */
public class DiliverManInfo implements Serializable {
    private String picBigUrl;
    private String picUrl;
    private String staffName;
    private String staffNo;

    public DiliverManInfo(JSONObjectProxy jSONObjectProxy) {
        if (jSONObjectProxy == null) {
            return;
        }
        update(JDJSON.parseObject(jSONObjectProxy.toString()));
    }

    private void update(JDJSONObject jDJSONObject) {
        if (jDJSONObject != null) {
            setStaffNo(jDJSONObject.getString("staffNo"));
            setPicUrl(jDJSONObject.getString("staffPhoto"));
            setPicBigUrl(jDJSONObject.getString("staffPhotoBig"));
            setStaffName(jDJSONObject.getString("staffName"));
        }
    }

    public String getPicBigUrl() {
        return this.picBigUrl;
    }

    public String getPicUrl() {
        return this.picUrl;
    }

    public String getStaffName() {
        return this.staffName;
    }

    public String getStaffNo() {
        return this.staffNo;
    }

    public void setPicBigUrl(String str) {
        this.picBigUrl = str;
    }

    public void setPicUrl(String str) {
        this.picUrl = str;
    }

    public void setStaffName(String str) {
        this.staffName = str;
    }

    public void setStaffNo(String str) {
        this.staffNo = str;
    }

    public DiliverManInfo(JDJSONObject jDJSONObject) {
        update(jDJSONObject);
    }
}

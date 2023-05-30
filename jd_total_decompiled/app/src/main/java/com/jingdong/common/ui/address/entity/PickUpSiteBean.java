package com.jingdong.common.ui.address.entity;

import com.jingdong.jdsdk.constant.CartConstant;
import com.jingdong.sdk.oklog.OKLog;
import java.io.Serializable;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes6.dex */
public class PickUpSiteBean implements Serializable {
    private long siteId;
    private String siteName;
    private int weight;

    public PickUpSiteBean() {
    }

    public long getSiteId() {
        return this.siteId;
    }

    public String getSiteName() {
        return this.siteName;
    }

    public int getWeight() {
        return this.weight;
    }

    public void setSiteId(long j2) {
        this.siteId = j2;
    }

    public void setSiteName(String str) {
        this.siteName = str;
    }

    public void setWeight(int i2) {
        this.weight = i2;
    }

    public String toString() {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("siteId", getSiteId());
            jSONObject.put("siteName", getSiteName());
            jSONObject.put(CartConstant.KEY_SKU_WEIGHT, getWeight());
            if (OKLog.D) {
                OKLog.d("\u66f4\u65b0\u81ea\u63d0\u70b9toString", jSONObject.toString());
            }
            return jSONObject.toString();
        } catch (JSONException e2) {
            OKLog.e("pickUpSite", e2);
            return "";
        }
    }

    public PickUpSiteBean(long j2, String str, int i2) {
        this.siteId = j2;
        this.siteName = str;
        this.weight = i2;
    }
}

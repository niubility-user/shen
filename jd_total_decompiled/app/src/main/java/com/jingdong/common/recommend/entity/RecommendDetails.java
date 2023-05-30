package com.jingdong.common.recommend.entity;

import com.jingdong.jdsdk.utils.JSONObjectProxy;

/* loaded from: classes6.dex */
public class RecommendDetails {
    public String client_click_url;
    public String commentCount;
    public String exposureSourceValue;
    public String imageurl;
    public String sourceValue;
    public String wareId;
    public String wareTitle;
    public String wname;

    public RecommendDetails() {
    }

    public RecommendDetails(JSONObjectProxy jSONObjectProxy) {
        this.wareId = jSONObjectProxy.optString("wareId");
        this.wareTitle = jSONObjectProxy.optString("wareTitle");
        this.wname = jSONObjectProxy.optString("wname");
        this.commentCount = jSONObjectProxy.optString("commentCount");
        this.imageurl = jSONObjectProxy.optString("imageurl");
        this.sourceValue = jSONObjectProxy.optString("sourceValue");
        this.exposureSourceValue = jSONObjectProxy.optString("exposureSourceValue");
    }
}

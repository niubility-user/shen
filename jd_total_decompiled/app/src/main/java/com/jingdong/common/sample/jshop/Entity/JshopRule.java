package com.jingdong.common.sample.jshop.Entity;

import com.jingdong.jdsdk.constant.JshopConst;
import java.io.Serializable;
import org.json.JSONObject;

/* loaded from: classes6.dex */
public class JshopRule implements Serializable {
    public String curGrade;
    public String curGradeName;
    public String discount;
    public String maxOrderCount;
    public String maxOrderPrice;
    public String minOrderCount;
    public String minOrderPrice;
    public long venderId;

    public JshopRule() {
        this.minOrderPrice = "";
        this.venderId = 0L;
        this.curGradeName = "";
        this.maxOrderPrice = "";
        this.maxOrderCount = "";
        this.minOrderCount = "";
        this.discount = "";
        this.curGrade = "";
    }

    public JshopRule(JSONObject jSONObject) {
        this.minOrderPrice = "";
        this.venderId = 0L;
        this.curGradeName = "";
        this.maxOrderPrice = "";
        this.maxOrderCount = "";
        this.minOrderCount = "";
        this.discount = "";
        this.curGrade = "";
        if (jSONObject != null) {
            this.minOrderPrice = jSONObject.optString("minOrderPrice");
            this.venderId = jSONObject.optLong("venderId");
            this.curGradeName = jSONObject.optString(JshopConst.JSKEY_GRADE_NAME);
            this.maxOrderPrice = jSONObject.optString("maxOrderPrice");
            this.maxOrderCount = jSONObject.optString("maxOrderCount");
            this.minOrderCount = jSONObject.optString("minOrderCount");
            this.discount = jSONObject.optString("discount");
            this.curGrade = jSONObject.optString("curGrade");
        }
    }
}

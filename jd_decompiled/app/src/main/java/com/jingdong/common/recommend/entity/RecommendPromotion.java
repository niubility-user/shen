package com.jingdong.common.recommend.entity;

import com.jd.framework.json.anotation.JSONField;
import com.jingdong.jdsdk.constant.CartConstant;
import com.jingdong.jdsdk.utils.JSONObjectProxy;

/* loaded from: classes.dex */
public class RecommendPromotion {
    public String client_click_url;
    public String extension_id;
    @JSONField(name = "imgUrl")
    public String imgUrl;
    public String jumpUrl;
    @JSONField(name = "wareId")
    public String promotionId;
    @JSONField(name = "promotionName")
    public String promotionName;
    public String sourceValue;

    public RecommendPromotion() {
    }

    public RecommendPromotion(JSONObjectProxy jSONObjectProxy) {
        this.promotionId = jSONObjectProxy.optString("wareId");
        this.promotionName = jSONObjectProxy.optString("wname");
        this.imgUrl = jSONObjectProxy.optString("imageurl");
        this.jumpUrl = jSONObjectProxy.optString(CartConstant.KEY_JUMPURL);
        this.sourceValue = jSONObjectProxy.optString("sourceValue", "");
    }
}

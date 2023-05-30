package com.jingdong.common.recommend.entity;

import com.jd.framework.json.anotation.JSONField;
import com.jingdong.jdsdk.constant.JshopConst;
import com.jingdong.jdsdk.utils.JSONArrayPoxy;
import com.jingdong.jdsdk.utils.JSONObjectProxy;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class RecommendShop {
    public String client_click_url;
    public String exposureSourceValue;
    public int followCount;
    @JSONField(name = "jdShop")
    public boolean isJdShop;
    @JSONField(name = "imageurl")
    public String logoUrl;
    @JSONField(name = "wareId")
    public String shopId;
    @JSONField(name = "wname")
    public String shopName;
    public String shopSignboardurl;
    public String source;
    public String sourceValue;
    public String venderId;
    @JSONField(name = "subWareList")
    public List<RecommendProduct> wareList;

    public RecommendShop() {
    }

    public boolean isShowDot() {
        return "1".equals(this.source);
    }

    public RecommendShop(JSONObjectProxy jSONObjectProxy) {
        int length;
        this.shopId = jSONObjectProxy.optString("wareId");
        this.shopName = jSONObjectProxy.optString("wname");
        this.logoUrl = jSONObjectProxy.optString("imageurl");
        this.followCount = jSONObjectProxy.optInt(JshopConst.JSKEY_FLW_COUNT);
        this.sourceValue = jSONObjectProxy.optString("sourceValue");
        this.isJdShop = jSONObjectProxy.optBoolean("jdShop", false);
        this.venderId = jSONObjectProxy.optString("venderId");
        JSONArrayPoxy jSONArrayOrNull = jSONObjectProxy.getJSONArrayOrNull("subWareList");
        if (jSONArrayOrNull == null || (length = jSONArrayOrNull.length()) <= 0) {
            return;
        }
        this.wareList = new ArrayList();
        for (int i2 = 0; i2 < length; i2++) {
            JSONObjectProxy jSONObjectOrNull = jSONArrayOrNull.getJSONObjectOrNull(i2);
            if (jSONObjectOrNull != null) {
                this.wareList.add(new RecommendProduct(jSONObjectOrNull.optString("wareId"), jSONObjectOrNull.optString("wareTitle"), jSONObjectOrNull.optString("imageUrl"), jSONObjectOrNull.optString(JshopConst.JSKEY_PRODUCT_JDPRICE), jSONObjectOrNull.optString("isOpenApp"), jSONObjectOrNull.optString("channelJumpUrl")));
            }
        }
    }
}

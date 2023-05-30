package com.jingdong.common.entity.cart;

import com.jd.framework.json.JDJSONObject;
import com.jingdong.jdsdk.auraSetting.AuraConstants;
import com.jingdong.jdsdk.constant.JshopConst;

/* loaded from: classes5.dex */
public class CartEggPlus {
    public String batchId;
    public String couponAmount;
    public String couponThreshold;
    public String couponType;
    public Long jingEggDailyEndTime;
    public String jingEggDailyExpireTime;
    public String jingEggDailyIcon;
    public String jingEggDailyPrice;
    public String jingEggDailyText;

    public CartEggPlus(JDJSONObject jDJSONObject) {
        if (jDJSONObject != null) {
            this.jingEggDailyText = jDJSONObject.optString("jingEggDailyText", "");
            this.jingEggDailyPrice = jDJSONObject.optString("jingEggDailyPrice", "");
            if (jDJSONObject.containsKey("jingEggDailyEndTime")) {
                this.jingEggDailyEndTime = Long.valueOf(jDJSONObject.optLong("jingEggDailyEndTime"));
            }
            this.jingEggDailyIcon = jDJSONObject.optString("jingEggDailyIcon", "");
            this.jingEggDailyExpireTime = jDJSONObject.optString("jingEggDailyExpireTime", "");
            this.batchId = jDJSONObject.optString(JshopConst.JSKEY_BATCH_ID, "");
            this.couponThreshold = jDJSONObject.optString("couponThreshold", "");
            this.couponAmount = jDJSONObject.optString("couponAmount", "");
            this.couponType = jDJSONObject.optString(AuraConstants.MESSAGE_COUPON_TYPE_FROM_NOTICE, "");
        }
    }
}

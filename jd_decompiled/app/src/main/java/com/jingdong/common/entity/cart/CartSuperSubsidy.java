package com.jingdong.common.entity.cart;

import com.jd.framework.json.JDJSONObject;
import com.jingdong.jdsdk.constant.CartConstant;
import com.jingdong.jdsdk.constant.JshopConst;

/* loaded from: classes5.dex */
public class CartSuperSubsidy {
    public String batchId;
    public String btTagNormal;
    public String cid2Set;
    public String couDan;
    public String couDanDeg;
    public String coudanButtonText;
    public String couponDes;
    public String endTimeMillis;
    public String needMoney;
    public String skuSet;
    public String tip;

    public CartSuperSubsidy(JDJSONObject jDJSONObject) {
        if (jDJSONObject == null) {
            return;
        }
        this.skuSet = jDJSONObject.optString("skuSet", "");
        this.cid2Set = jDJSONObject.optString("cid2Set", "");
        this.batchId = jDJSONObject.optString(JshopConst.JSKEY_BATCH_ID, "");
        this.tip = jDJSONObject.optString("tip", "");
        this.needMoney = jDJSONObject.optString(CartConstant.KEY_GLOBAL_NEED_MONEY, "");
        this.couponDes = jDJSONObject.optString("couponDes", "");
        this.couDan = jDJSONObject.optString("couDan", "");
        this.couDanDeg = jDJSONObject.optString("couDanDeg", "");
        this.coudanButtonText = jDJSONObject.optString("coudanButtonText");
        this.btTagNormal = jDJSONObject.optString("btTagNormal");
        this.endTimeMillis = jDJSONObject.optString("endTimeMillis");
    }
}

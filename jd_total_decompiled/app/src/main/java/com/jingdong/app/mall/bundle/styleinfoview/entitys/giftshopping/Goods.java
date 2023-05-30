package com.jingdong.app.mall.bundle.styleinfoview.entitys.giftshopping;

import com.jd.framework.json.JDJSONObject;
import com.jingdong.jdsdk.constant.CartConstant;

/* loaded from: classes3.dex */
public class Goods {
    private String discountPrice;
    public Gift gifts;
    public String imgUrl;
    public String name;
    public int num;
    public int remainNum;
    public long skuId;
    public String stockStateDesc;
    public SuitPromotion suitPromotion;
    public String suitTip;
    public int suitType;

    public Goods() {
        this.discountPrice = "";
        this.imgUrl = "";
        this.name = "";
        this.suitTip = "";
        this.remainNum = 0;
        this.stockStateDesc = "";
        this.gifts = new Gift();
    }

    public Goods(JDJSONObject jDJSONObject) {
        this.discountPrice = "";
        this.imgUrl = "";
        this.name = "";
        this.suitTip = "";
        this.remainNum = 0;
        this.stockStateDesc = "";
        this.gifts = new Gift();
        if (jDJSONObject == null) {
            return;
        }
        this.imgUrl = jDJSONObject.optString("imgUrl");
        this.skuId = jDJSONObject.optLong("skuId");
        this.suitType = jDJSONObject.optInt("suitType");
        int optInt = jDJSONObject.optInt(CartConstant.KEY_SKU_REMAINNUM);
        this.remainNum = optInt;
        if (optInt == -1) {
            this.remainNum = 1000;
        }
        jDJSONObject.optInt("stockStateId");
        this.num = jDJSONObject.optInt("num");
        jDJSONObject.optInt("checkType");
        this.name = jDJSONObject.optString("name");
        this.stockStateDesc = jDJSONObject.optString("stockStateDesc");
        this.discountPrice = jDJSONObject.optString("discountPrice");
        this.suitTip = jDJSONObject.optString("suitTip");
        this.gifts = new Gift(jDJSONObject.optJSONObject("gift"));
        this.suitPromotion = new SuitPromotion(jDJSONObject.optJSONObject("suitPromotion"));
    }
}

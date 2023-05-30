package com.jingdong.app.mall.bundle.styleinfoview.entitys.giftshopping;

import com.jd.framework.json.JDJSONObject;

/* loaded from: classes3.dex */
public class GiftCartData {
    public static final String TAG = "GiftCartData";
    public Card card;
    public String finalPrice;
    public Goods goods;
    public int selectedCount;
    public String totalPromotionPrice;
    public String totalRePrice;
    public Wrap wrap;

    public GiftCartData(JDJSONObject jDJSONObject) {
        this.totalRePrice = "";
        this.totalPromotionPrice = "";
        this.finalPrice = "";
        this.selectedCount = 0;
        this.wrap = new Wrap();
        this.goods = new Goods();
        this.card = new Card();
        if (jDJSONObject == null) {
            return;
        }
        this.totalRePrice = jDJSONObject.optString("totalRePrice");
        this.totalPromotionPrice = jDJSONObject.optString("totalPromotionPrice");
        this.finalPrice = jDJSONObject.optString("finalPrice");
        this.selectedCount = jDJSONObject.optInt("selectedCount");
        JDJSONObject optJSONObject = jDJSONObject.optJSONObject("products");
        this.wrap = new Wrap(optJSONObject.optJSONObject("wrap"));
        this.goods = new Goods(optJSONObject.optJSONObject("goods"));
        this.card = new Card(optJSONObject.optJSONObject("card"));
    }

    public Gift getGifts() {
        Goods goods = this.goods;
        if (goods != null) {
            return goods.gifts;
        }
        return new Gift();
    }

    public GiftCartData() {
        this.totalRePrice = "";
        this.totalPromotionPrice = "";
        this.finalPrice = "";
        this.selectedCount = 0;
        this.wrap = new Wrap();
        this.goods = new Goods();
        this.card = new Card();
    }
}

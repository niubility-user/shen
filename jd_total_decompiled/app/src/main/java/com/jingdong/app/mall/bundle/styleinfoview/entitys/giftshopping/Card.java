package com.jingdong.app.mall.bundle.styleinfoview.entitys.giftshopping;

import com.jd.framework.json.JDJSONObject;

/* loaded from: classes3.dex */
public class Card {
    public int checkType;
    private String discountPrice;
    public String imgUrl;
    public String imgUrlBig;
    private boolean isSelected;
    public String name;
    public int num;
    public long skuId;
    public String stockStateDesc;

    public Card() {
        this.num = 0;
        this.checkType = 0;
        this.discountPrice = "";
        this.name = "";
        this.imgUrl = "";
        this.imgUrlBig = "";
        this.stockStateDesc = "";
        this.isSelected = false;
    }

    public boolean isSelected() {
        return this.isSelected;
    }

    public void setSelected(boolean z) {
        this.isSelected = z;
    }

    public Card(JDJSONObject jDJSONObject) {
        this.num = 0;
        this.checkType = 0;
        this.discountPrice = "";
        this.name = "";
        this.imgUrl = "";
        this.imgUrlBig = "";
        this.stockStateDesc = "";
        this.isSelected = false;
        if (jDJSONObject != null) {
            this.skuId = jDJSONObject.optLong("skuId");
            this.num = jDJSONObject.optInt("num");
            this.discountPrice = jDJSONObject.optString("discountPrice");
            this.name = jDJSONObject.optString("name");
            this.imgUrl = jDJSONObject.optString("imgUrl");
            int optInt = jDJSONObject.optInt("checkType");
            this.checkType = optInt;
            this.isSelected = optInt == 1;
            this.imgUrlBig = jDJSONObject.optString("imgUrlBig");
            this.stockStateDesc = jDJSONObject.optString("stockStateDesc");
        }
    }
}

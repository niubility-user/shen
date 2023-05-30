package com.jd.lib.productdetail.core.entitys.giftshopping;

import android.content.Context;
import android.text.TextUtils;
import com.jd.framework.json.JDJSONObject;
import com.jd.lib.productdetail.core.R;

/* loaded from: classes15.dex */
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

    public String getPrice(Context context) {
        return TextUtils.isEmpty(this.discountPrice) ? context.getString(R.string.lib_pd_core_product_detail_comments) : context.getString(R.string.lib_pd_core_pg_home_jdpirce, this.discountPrice);
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

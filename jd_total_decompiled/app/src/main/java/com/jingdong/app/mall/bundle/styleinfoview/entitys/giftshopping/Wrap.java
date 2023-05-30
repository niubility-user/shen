package com.jingdong.app.mall.bundle.styleinfoview.entitys.giftshopping;

import com.jd.framework.json.JDJSONArray;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.jdsdk.constant.CartConstant;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes3.dex */
public class Wrap {
    public int checkType;
    public String discountedPrice;
    public String imgUrl;
    public String imgUrlBig;
    private boolean isSelected;
    public String name;
    public int num;
    private int remainNum;
    public long skuId;
    public String stockStateDesc;
    private int stockStateId;

    public Wrap() {
        this.num = 1;
        this.checkType = 0;
        this.discountedPrice = "";
        this.name = "";
        this.imgUrl = "";
        this.imgUrlBig = "";
        this.isSelected = false;
        this.remainNum = 0;
        this.stockStateId = 3;
        this.stockStateDesc = "";
    }

    public static List<Wrap> toList(JDJSONArray jDJSONArray) {
        ArrayList arrayList = new ArrayList();
        if (jDJSONArray != null && jDJSONArray.size() > 0) {
            for (int i2 = 0; i2 < jDJSONArray.size(); i2++) {
                arrayList.add(new Wrap(jDJSONArray.optJSONObject(i2)));
            }
        }
        return arrayList;
    }

    public boolean isSelected() {
        return this.isSelected;
    }

    public void setSelected(boolean z) {
        this.isSelected = z;
    }

    public Wrap(JDJSONObject jDJSONObject) {
        this.num = 1;
        this.checkType = 0;
        this.discountedPrice = "";
        this.name = "";
        this.imgUrl = "";
        this.imgUrlBig = "";
        this.isSelected = false;
        this.remainNum = 0;
        this.stockStateId = 3;
        this.stockStateDesc = "";
        if (jDJSONObject != null) {
            this.skuId = jDJSONObject.optLong("skuId");
            this.num = jDJSONObject.optInt("num");
            this.name = jDJSONObject.optString("name");
            this.imgUrl = jDJSONObject.optString("imgUrl");
            this.imgUrlBig = jDJSONObject.optString("imgUrlBig");
            int optInt = jDJSONObject.optInt("checkType");
            this.checkType = optInt;
            this.isSelected = optInt == 1;
            this.discountedPrice = jDJSONObject.optString("discountPrice");
            this.remainNum = jDJSONObject.optInt(CartConstant.KEY_SKU_REMAINNUM);
            this.stockStateId = jDJSONObject.optInt("stockStateId");
            this.stockStateDesc = jDJSONObject.optString("stockStateDesc");
        }
    }
}

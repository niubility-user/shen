package com.jingdong.app.mall.bundle.styleinfoview.entitys;

import android.text.TextUtils;
import com.jd.framework.json.JDJSONArray;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.jdsdk.constant.JshopConst;
import java.util.ArrayList;

/* loaded from: classes3.dex */
public class ProductHistroyItem {
    public String imageUrl;
    public String jdPrice;
    public String name;
    public String priceLabel;
    public String skuId;

    public ProductHistroyItem(JDJSONObject jDJSONObject) {
        if (jDJSONObject != null) {
            this.skuId = jDJSONObject.optString("wareId");
            this.name = jDJSONObject.optString("wname");
            this.imageUrl = jDJSONObject.optString("imageurl");
            this.jdPrice = jDJSONObject.optString(JshopConst.JSKEY_PRODUCT_JDPRICE);
            this.priceLabel = jDJSONObject.optString("priceLabel");
            if (TextUtils.isEmpty(this.skuId)) {
                this.skuId = jDJSONObject.optString("sku");
            }
            if (TextUtils.isEmpty(this.name)) {
                this.name = jDJSONObject.optString("name");
            }
            if (TextUtils.isEmpty(this.imageUrl)) {
                this.imageUrl = jDJSONObject.optString("img");
            }
            if (TextUtils.isEmpty(this.jdPrice)) {
                this.jdPrice = jDJSONObject.optString("jprice");
            }
        }
    }

    public static ArrayList<ProductHistroyItem> toList(JDJSONArray jDJSONArray) {
        int size;
        if (jDJSONArray == null || (size = jDJSONArray.size()) <= 0) {
            return null;
        }
        ArrayList<ProductHistroyItem> arrayList = new ArrayList<>(size);
        for (int i2 = 0; i2 < size; i2++) {
            JDJSONObject optJSONObject = jDJSONArray.optJSONObject(i2);
            if (optJSONObject != null) {
                arrayList.add(new ProductHistroyItem(optJSONObject));
            }
        }
        return arrayList;
    }
}

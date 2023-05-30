package com.jd.lib.productdetail.core.entitys.promotion;

import android.text.TextUtils;
import java.util.ArrayList;

/* loaded from: classes15.dex */
public class PdStyleSelectSuitItem {
    public String bindSku;
    public String bindSkuNumText;
    public int originalSuitType;
    public String packId;
    public String packListPrice;
    public String packPrice;
    public String packType;
    public ArrayList<ProductList> productList;
    public String rightDesc;
    public String selectPackDesc;
    public String skuId;
    public String skuName;
    public String skuPicUrl;
    public int sortId;
    public int suitProductType;
    public String title;
    public String unselectPackDesc;
    public String virtualPackPrice;

    /* loaded from: classes15.dex */
    public static class ProductList {
        public String bindSku;
        public String bindSkuNumText;
        public String skuId;
        public String skuName;
        public String skuPicUrl;
        public int suitProductType;
    }

    public boolean isValid() {
        return (TextUtils.isEmpty(this.rightDesc) || TextUtils.isEmpty(this.skuName) || TextUtils.isEmpty(this.virtualPackPrice) || TextUtils.isEmpty(this.unselectPackDesc)) ? false : true;
    }
}

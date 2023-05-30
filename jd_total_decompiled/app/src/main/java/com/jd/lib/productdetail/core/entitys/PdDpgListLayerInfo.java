package com.jd.lib.productdetail.core.entitys;

import java.util.List;

/* loaded from: classes15.dex */
public class PdDpgListLayerInfo {
    public int code;
    public DetailBean detail;

    /* loaded from: classes15.dex */
    public static class DetailBean {
        public InfoBean info;
        public List<ItemsBean> items;
        public List<ItemInfoAndItem> others;
        public List<ItemInfoAndItem> relations;
        public int type;

        /* loaded from: classes15.dex */
        public static class InfoBean {
            public String business;
            public String discount;
            public String matchAdWord;
            public String matchBrief;
            public String matchId;
            public String matchPrice;
            public String matchSkuList;
            public String matchTitle;
            public String nativeDiscountStr;
            public int nativeTotalNumber;
            public String nativeTotalPriceStr;
            public String packId;
            public String shopId;
            public String shopName;
        }

        /* loaded from: classes15.dex */
        public static class ItemInfoAndItem {
            public InfoBean info;
            public List<ItemsBean> items;
            public int type;
        }

        /* loaded from: classes15.dex */
        public static class ItemsBean {
            public String color;
            public boolean isSelectedProductCheckBoxNative;
            public boolean isSelectedTextNative;
            public boolean isValid;
            public String itemName;
            public String itemUrl;
            public String nativeMatchId;
            public int nativeType;
            public String price;
            public String sale_atts;
            public String size;
            public String skuId;
            public String spec_name;
        }
    }
}

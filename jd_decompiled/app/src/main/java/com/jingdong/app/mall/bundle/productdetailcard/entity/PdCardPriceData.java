package com.jingdong.app.mall.bundle.productdetailcard.entity;

/* loaded from: classes3.dex */
public class PdCardPriceData {
    public static final String PRICE_TYPE_KEPLER = "5";
    public static final String PRICE_TYPE_PLUS = "2";
    public PdCardPriceItem bizPrice;
    public PdCardPriceItem hxPrice;
    public PdCardPriceItem jPrice;
    public PdCardPriceItem plusPrice;
    public PdCardPriceItem priceType;
    public PdCardSecondPriceInfo secondPriceInfo;

    /* loaded from: classes3.dex */
    public class PdCardPriceItem {
        public String icon;
        public String val;

        public PdCardPriceItem() {
        }
    }

    /* loaded from: classes3.dex */
    public class PdCardSecondPriceInfo {
        public String icon;
        public String val;

        public PdCardSecondPriceInfo() {
        }
    }
}

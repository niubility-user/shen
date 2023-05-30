package com.jd.lib.productdetail.core.entitys.warebusiness;

import com.jd.framework.json.JDJSONObject;

/* loaded from: classes15.dex */
public class PdStrengThenPriceEntity {
    public String memo;
    public StrengThenDataEntity strengThenData;
    public JDJSONObject strengThenDataPoint;
    public String type;
    public boolean useNewLogic;

    /* loaded from: classes15.dex */
    public static class StrengThenDataEntity {
        public SecondPriceEntity afticonbody;
        public String arrowurl;
        public DiscountlineEntity discountline;
        public GuidePriceInfo guidepriceinfo;
        public String isShowPreferential;
        public MainPricebodyEntity mainpricebody;
        public SecondPriceEntity preiconbody;
        public String pretext;
        public SecondpricebodyEntity secondpricebody;

        /* loaded from: classes15.dex */
        public static class DiscountlineEntity {
            public String crossLinePrice;
            public String discount;
            public String discountSuffix;
            public String lpType;
        }

        /* loaded from: classes15.dex */
        public static class MainPricebodyEntity {
            public boolean isCityPrice;
            public String mainPrice;
            public String mainPriceColor;
            public int mainPriceType;
        }

        /* loaded from: classes15.dex */
        public static class SecondpricebodyEntity {
            public String secondPrice;
            public String secondPriceColor;
            public String secondPriceFont;
            public String secondPriceText;
            public String secondPriceTextColor;
            public int secondPriceType;
        }
    }
}

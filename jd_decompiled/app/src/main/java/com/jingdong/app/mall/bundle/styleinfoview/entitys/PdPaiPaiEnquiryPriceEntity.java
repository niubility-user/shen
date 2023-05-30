package com.jingdong.app.mall.bundle.styleinfoview.entitys;

/* loaded from: classes3.dex */
public class PdPaiPaiEnquiryPriceEntity {
    public String code;
    public DataBean data;
    public String page;

    /* loaded from: classes3.dex */
    public class DataBean {
        public NewProductInfo newProductInfo;
        public QuoteBean quote;

        /* loaded from: classes3.dex */
        public class QuoteBean {
            public ClearVoBean clearVo;
            public int code;
            public int couponAmount;
            public String couponIds;
            public QuoteCouponVo couponVo;
            public ExtVoBean extVo;
            public OldForNewFlowVo flowVo;
            public int maxSubsidy;
            public int minBuyPrice;
            public String oldProductName;
            public PriceVoBean priceVo;
            public PdPromiseVoBean promiseVo;
            public QuotePriceBean quotePrice;
            public String recycleSubmitUrl;
            public PdPaiPaiSubsidyVo subsidyVo;

            /* loaded from: classes3.dex */
            public class ExtVoBean {
                public String recycleUrl;
                public String recycleUrlTip;

                public ExtVoBean() {
                }
            }

            /* loaded from: classes3.dex */
            public class PriceVoBean {
                public String declinePriceText;
                public String declinePriceValue;
                public double earnPrice;
                public String placeholderPriceText;
                public String[] replacePriceText;

                public PriceVoBean() {
                }
            }

            /* loaded from: classes3.dex */
            public class QuoteCouponVo {
                public String outTitle;

                public QuoteCouponVo() {
                }
            }

            /* loaded from: classes3.dex */
            public class QuotePriceBean {
                public String inquiryKey;
                public int price;

                public QuotePriceBean() {
                }
            }

            public QuoteBean() {
            }
        }

        public DataBean() {
        }
    }
}

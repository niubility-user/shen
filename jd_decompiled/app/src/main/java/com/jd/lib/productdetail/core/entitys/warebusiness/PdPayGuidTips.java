package com.jd.lib.productdetail.core.entitys.warebusiness;

import java.util.List;

/* loaded from: classes15.dex */
public class PdPayGuidTips {
    public String channel;
    public String closeIcon;
    public String content;
    public long countDown;
    public int hitType;
    public String icon;
    public String imgArrow;
    public String imgBgd;
    public OtherMap otherMap;
    public List<PdShowFormat> showFormatList;
    public long showSeconds;
    public List<String> touchstone_expids;
    public int type;

    /* loaded from: classes15.dex */
    public static class OtherMap {
        public int batchId;
        public BestCanUseCoupon bestCanUseCoupon;
        public String brandId;
        public int couponKind;
        public int couponType;
        public String discountText;
        public String promoId;
        public String promotionTitle;
        public String shopId;

        /* loaded from: classes15.dex */
        public static class BestCanUseCoupon {
            public int batchId;
            public long beginTime;
            public String businessLabel;
            public int couponKind;
            public int couponOrg;
            public int couponStyle;
            public String discountDesc;
            public long endTime;
            public int hourCoupon;
            public String id;
            public List<Integer> limitOrg;
            public String name;
            public int overlap;
            public String overlapDesc;
            public Double parValue;
            public int platform;
            public int quota;
            public int type;
            public String userLabel;
        }
    }

    /* loaded from: classes15.dex */
    public static class PdShowFormat {
        public String color;
        public Integer fontType;
        public int length;
        public int startIndex;
    }
}

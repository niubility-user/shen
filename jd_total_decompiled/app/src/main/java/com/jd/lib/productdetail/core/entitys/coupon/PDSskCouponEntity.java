package com.jd.lib.productdetail.core.entitys.coupon;

import java.util.List;

/* loaded from: classes15.dex */
public class PDSskCouponEntity {
    public int actStage;
    public String bgImg;
    public String bgImgIcon;
    public CouponInfo coupon1st;
    public CouponInfo coupon2nd;
    public CouponInfo coupon3rd;
    public String discount;
    public String icon;
    public String linkBgdImg;
    public String linkText;
    public String linkTextColor;
    public String linkUrl;
    public String preferentialTagV9;
    public String recomInfo;
    public List<SpecDataDTO> specData;
    public String text;
    public String title;
    public String touchstoneExpIds;
    public String type;

    /* loaded from: classes15.dex */
    public static class CouponInfo {
        public String bgdImg;
        public String frontIcon;
        public boolean isCovered;
        public String text;
    }

    /* loaded from: classes15.dex */
    public class SpecDataDTO {
        public String startIndex;
        public String textLength;

        public SpecDataDTO() {
        }
    }
}

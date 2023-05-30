package com.jingdong.common.shop;

/* loaded from: classes6.dex */
public class JShopCouponRevceiveEvent {
    public static final String JSHOP_COUPON_TYPE_CRM = "crm";
    public static final String JSHOP_COUPON_TYPE_HTTP = "http";
    public Boolean applicability;
    public String couponId;
    public int couponStatus;
    public String shopId;
    public String type;

    public JShopCouponRevceiveEvent(String str, String str2, String str3, Boolean bool, int i2) {
        this.shopId = str;
        this.couponId = str2;
        this.type = str3;
        this.applicability = bool;
        this.couponStatus = i2;
    }
}

package com.jingdong.common.recommend.entity;

/* loaded from: classes6.dex */
public class RecommendCouponEvent {
    public static final String RECOMMEND_COUPON_TAKE_SUCCESS_KEY = "recommend_coupon_take_success_key";
    public String mCouponId;
    public String mKey;

    public RecommendCouponEvent(String str, String str2) {
        this.mKey = str;
        this.mCouponId = str2;
    }
}

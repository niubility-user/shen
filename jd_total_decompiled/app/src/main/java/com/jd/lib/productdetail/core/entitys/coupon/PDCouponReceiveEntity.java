package com.jd.lib.productdetail.core.entitys.coupon;

import java.util.HashMap;
import java.util.List;

/* loaded from: classes15.dex */
public class PDCouponReceiveEntity {
    public static final String COUPON_BATCH_SUCCESS = "999";
    public static final String COUPON_SUCCESS = "9000";
    public static final String REAL_NAME = "54";
    public static final String SUCCESS_CODE = "0";
    public String code;
    public String mRequestId;
    public String mRequestTag;
    public String msg;
    public int position;
    public Result result;
    public int skuSource;

    /* loaded from: classes15.dex */
    public static class Result {
        public String authenUrl;
        public HashMap<String, String> batchCouponResult;
        public String bizCode;
        public String couponResult;
        public String desc;
        public List<GwcCouponParentEntity> failGwcCoupon;
        public int isBatchReceive;
        public String optCode;
        public List<GwcCouponParentEntity> successGwcCoupon;
    }
}

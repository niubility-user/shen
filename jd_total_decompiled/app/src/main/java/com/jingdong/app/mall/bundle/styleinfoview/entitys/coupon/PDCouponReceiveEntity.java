package com.jingdong.app.mall.bundle.styleinfoview.entitys.coupon;

import java.util.HashMap;

/* loaded from: classes3.dex */
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

    /* loaded from: classes3.dex */
    public class Result {
        public String authenUrl;
        public HashMap<String, String> batchCouponResult;
        public String bizCode;
        public String couponResult;
        public String desc;
        public int isBatchReceive;
        public String optCode;

        public Result() {
        }
    }
}

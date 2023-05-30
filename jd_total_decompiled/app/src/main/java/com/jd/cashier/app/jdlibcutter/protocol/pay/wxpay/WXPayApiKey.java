package com.jd.cashier.app.jdlibcutter.protocol.pay.wxpay;

/* loaded from: classes13.dex */
public interface WXPayApiKey {
    public static final int WX_AUTH_DENIED_CODE = -4;
    public static final int WX_CANCEL_CODE = -2;
    public static final int WX_COMM_CODE = -1;
    public static final int WX_GET_WX_PAY_DATA_FAILED_CODE = -6;
    public static final String WX_PAY_INVALID_ACTION = "com.jd.wxPay.invalid.action";
    public static final String WX_PAY_NONCE = "wxPayNonceStr";
    public static final String WX_PAY_PACKAGE_VALUE = "wxPayPackageValue";
    public static final String WX_PAY_PARTNER_ID = "wxPayPartnerId";
    public static final String WX_PAY_PREPAY_ID = "wxPayPrepayId";
    public static final String WX_PAY_RESULT_ACTION = "com.jd.wxPayResult";
    public static final String WX_PAY_SIGN = "wxPaySign";
    public static final String WX_PAY_TIMESTAMP = "wxPayTimeStamp";
    public static final int WX_SENT_FAILED_CODE = -3;
    public static final int WX_SUCCESS_CODE = 0;
    public static final int WX_UNSUPPORT_CODE = -5;
}

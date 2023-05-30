package com.jd.lib.cashier.sdk.common.bean;

import com.jd.lib.cashier.sdk.core.model.CashierCommonPopConfig;
import com.jd.lib.cashier.sdk.d.f.b;

/* loaded from: classes14.dex */
public class CashierGetSuccessUrlEntity extends b {
    public static final String GRADUALLY_SUCCESS = "1";
    public static final String PAY_STATUS_1 = "1";
    public static final String PAY_STATUS_2 = "2";
    public static final String PAY_STATUS_3 = "3";
    public static final String PAY_STATUS_4 = "4";
    public String businessMapInfo;
    public CashierCommonPopConfig globalPresaleCombinedPayPopup;
    public String graduallyPay;
    public GraduallyPayPopup graduallyPayPopup;
    public String payStatus;
    public String successToastText;
    public String successUrl;
    public String successUrlType;
    public String xviewType;
}

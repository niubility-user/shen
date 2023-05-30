package com.jd.lib.cashier.sdk.creditpay.bean;

import com.jd.lib.cashier.sdk.core.model.CashierCommonPopConfig;
import com.jd.lib.cashier.sdk.d.f.b;
import com.jd.lib.cashier.sdk.pay.bean.Payment;
import java.util.List;

/* loaded from: classes14.dex */
public class CreditPayEntity extends b {
    public CreditAuthInfo creditAuthInfo;
    public CashierCommonPopConfig indexPopupConfig;
    public List<Payment> jdPayChannelList;
    public CashierCommonPopConfig orderExceptionInfo;
    public String orderType = "";
    public String orderId = "";
    public String isNewUser = "";
    public String requireUUID = "";
    public String defaultStrategy = "";
    public String noCanUseChannelTip = "";
    public String payprice = "";
    public String moneyFlag = "";
    public boolean firstQuery = true;
    public boolean hiddenFrozenPriceFlag = false;
}

package com.jd.lib.cashier.sdk.freindpay.bean;

import com.jd.lib.cashier.sdk.core.model.CashierCommonPopConfig;
import com.jd.lib.cashier.sdk.d.f.b;
import java.util.List;

/* loaded from: classes14.dex */
public class CashierFriendPayEntity extends b {
    public String channelCode;
    public String countDownTime;
    public CashierCommonPopConfig countdownPopInfo;
    public String description;
    public String dynamicFlag;
    public List<String> explain;
    public String explainTitle;
    public FamilyOuterInfo familyOuterInfo;
    public List<WareInfo> itemList;
    public String moneyFlag;
    public CashierCommonPopConfig orderExceptionInfo;
    public String orderId;
    public String payprice;
    public ShareInfo shareInfo;
    public String topImageUrl;
}

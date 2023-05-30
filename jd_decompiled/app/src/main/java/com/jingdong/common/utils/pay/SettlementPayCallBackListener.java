package com.jingdong.common.utils.pay;

/* loaded from: classes6.dex */
public interface SettlementPayCallBackListener extends PayCallBackQueueListener {
    void dismissLoadingDialog();

    void jumpToOrderDetailActivity();

    void jumpToOrderListActivity();

    void riskUserJump(String str);

    void setPayId(String str, String str2, String str3);

    void start();
}

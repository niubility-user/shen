package com.jd.lib.cashier.sdk.pay.bean.convert;

import java.util.Map;

/* loaded from: classes14.dex */
public class PaymentChoseHolder {
    public String accountCode;
    public String activityId;
    public String appId;
    public String bankCode;
    public String bankPlanRate;
    public boolean canUsePaymentAcc;
    public String changetag;
    public String channelId;
    public String channelStatus;
    public String channelType;
    public String combineType = "";
    public String combinedOrderId;
    public String couponId;
    public String from;
    public String groupOrders;
    public boolean isNewCard;
    public String jdPayChannel;
    public String jumpApp;
    public String merchantFeeSubSideBy;
    public String payMarketingUUID;
    public String payToken;
    public String planId;
    public String planInfo;
    public String prizeId;
    public String productCode;
    public String requireUUID;
    public Map<String, String> tradeMap;
    public String uniqueChannelId;

    public PaymentChoseHolder copy() {
        PaymentChoseHolder paymentChoseHolder = new PaymentChoseHolder();
        paymentChoseHolder.channelId = this.channelId;
        paymentChoseHolder.uniqueChannelId = this.uniqueChannelId;
        paymentChoseHolder.planId = this.planId;
        paymentChoseHolder.couponId = this.couponId;
        paymentChoseHolder.activityId = this.activityId;
        paymentChoseHolder.channelStatus = this.channelStatus;
        paymentChoseHolder.requireUUID = this.requireUUID;
        paymentChoseHolder.planInfo = this.planInfo;
        paymentChoseHolder.payMarketingUUID = this.payMarketingUUID;
        paymentChoseHolder.prizeId = this.prizeId;
        paymentChoseHolder.channelType = this.channelType;
        paymentChoseHolder.bankPlanRate = this.bankPlanRate;
        paymentChoseHolder.merchantFeeSubSideBy = this.merchantFeeSubSideBy;
        paymentChoseHolder.accountCode = this.accountCode;
        paymentChoseHolder.bankCode = this.bankCode;
        paymentChoseHolder.isNewCard = this.isNewCard;
        paymentChoseHolder.productCode = this.productCode;
        paymentChoseHolder.from = this.from;
        paymentChoseHolder.appId = this.appId;
        paymentChoseHolder.payToken = this.payToken;
        paymentChoseHolder.jdPayChannel = this.jdPayChannel;
        paymentChoseHolder.changetag = this.changetag;
        paymentChoseHolder.tradeMap = this.tradeMap;
        paymentChoseHolder.jumpApp = this.jumpApp;
        paymentChoseHolder.canUsePaymentAcc = this.canUsePaymentAcc;
        paymentChoseHolder.groupOrders = this.groupOrders;
        paymentChoseHolder.combinedOrderId = this.combinedOrderId;
        paymentChoseHolder.combineType = this.combineType;
        return paymentChoseHolder;
    }

    public String toString() {
        return "PaymentChoseHolder{channelId='" + this.channelId + "', uniqueChannelId='" + this.uniqueChannelId + "', planId='" + this.planId + "', couponId='" + this.couponId + "', activityId='" + this.activityId + "', channelStatus='" + this.channelStatus + "', requireUUID='" + this.requireUUID + "', planInfo='" + this.planInfo + "', payMarketingUUID='" + this.payMarketingUUID + "', prizeId='" + this.prizeId + "', channelType='" + this.channelType + "', bankPlanRate='" + this.bankPlanRate + "', merchantFeeSubSideBy='" + this.merchantFeeSubSideBy + "', accountCode='" + this.accountCode + "', bankCode='" + this.bankCode + "', isNewCard=" + this.isNewCard + ", productCode='" + this.productCode + "', from='" + this.from + "', appId='" + this.appId + "', payToken='" + this.payToken + "', jdPayChannel='" + this.jdPayChannel + "', changetag='" + this.changetag + "', canUsePaymentAcc=" + this.canUsePaymentAcc + ", tradeMap=" + this.tradeMap + ", jumpApp='" + this.jumpApp + "', combineType='" + this.combineType + "', groupOrders=" + this.groupOrders + ", combinedOrderId=" + this.combinedOrderId + ", tradeMap=" + this.jumpApp + ", tradeMap=" + this.combineType + '}';
    }
}

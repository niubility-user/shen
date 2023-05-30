package com.jingdong.common.phonecharge.model;

import com.jd.framework.json.JDJSONObject;
import com.jdcn.biz.client.BankCardConstants;
import java.io.Serializable;

/* loaded from: classes5.dex */
public class FlowOrderDetail implements Serializable {
    public int areaCode;
    public String areaName;
    public int areaUsed;
    public String areaUsedName;
    public String availableCard;
    public int availableNum;
    public boolean cancelFlag;
    public String created;
    public String effectDate;
    public int faceAmount;
    public String mobile;
    public int mutCode;
    public String mutName;
    public double onlinePay;
    public long orderId;
    public double orderPrice;
    public int orderStatus;
    public String orderStatusName;
    public String payNo;
    public String payTime;
    public int payType;
    public String payTypeName;
    public String quantity;
    public String rechargeTime;
    public String refundPrice;
    public String refundTime;
    public double salePrice;
    public long skuId;
    public String skuName;
    public int source;
    public String sourceName;
    public String validDate;

    public FlowOrderDetail(JDJSONObject jDJSONObject) {
        if (jDJSONObject != null) {
            this.orderId = jDJSONObject.optLong("orderId");
            this.orderStatus = jDJSONObject.optInt("orderStatus");
            this.orderStatusName = jDJSONObject.optString("orderStatusName");
            this.skuId = jDJSONObject.optLong("skuId");
            this.skuName = jDJSONObject.optString("skuName");
            this.quantity = jDJSONObject.optString("quantity");
            this.mobile = jDJSONObject.optString("mobile");
            this.mutCode = jDJSONObject.optInt("mutCode");
            this.mutName = jDJSONObject.optString("mutName");
            this.areaCode = jDJSONObject.optInt("areaCode");
            this.areaName = jDJSONObject.optString("areaName");
            this.faceAmount = jDJSONObject.optInt("faceAmount");
            this.orderPrice = jDJSONObject.optDouble("orderPrice");
            this.onlinePay = jDJSONObject.optDouble("onlinePay");
            this.source = jDJSONObject.optInt("source");
            this.sourceName = jDJSONObject.optString("sourceName");
            this.salePrice = jDJSONObject.optDouble("salePrice");
            this.payNo = jDJSONObject.optString("payNo");
            this.payTime = jDJSONObject.optString("payTime");
            this.refundTime = jDJSONObject.optString("refundTime");
            this.refundPrice = jDJSONObject.optString("refundPrice");
            this.rechargeTime = jDJSONObject.optString("rechargeTime");
            this.created = jDJSONObject.optString("created");
            this.areaUsed = jDJSONObject.optInt("areaUsed");
            this.areaUsedName = jDJSONObject.optString("areaUsedName");
            this.payType = jDJSONObject.optInt("payType");
            this.payTypeName = jDJSONObject.optString("payTypeName");
            this.validDate = jDJSONObject.optString(BankCardConstants.KEY_VALID_DATE);
            this.effectDate = jDJSONObject.optString("effectDate");
            this.availableNum = jDJSONObject.optInt("availableNum");
            this.availableCard = jDJSONObject.optString("availableCard");
            this.cancelFlag = jDJSONObject.optBoolean("cancelFlag");
        }
    }
}

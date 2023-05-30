package com.jingdong.common.entity.cart;

import com.jd.framework.json.JDJSONObject;
import java.util.ArrayList;

/* loaded from: classes5.dex */
public class CartBalanceInfo {
    public String accountMessage;
    public String accountType;
    public int balanceBtnType;
    public String balanceType;
    public int buttonStyle;
    public String cartParamMapInfoJson;
    public boolean isAccountButtonAvailable;
    public ArrayList<CartSkuOrderInfo> skuExtOrderInfos;
    public ArrayList<CartSkuOrderInfo> skuOrderInfos;
    public String tipsMsg;
    public Integer tipsType;
    public CartTitleInfo titleInfo;

    public CartBalanceInfo(JDJSONObject jDJSONObject) {
        if (jDJSONObject == null) {
            return;
        }
        this.isAccountButtonAvailable = jDJSONObject.optBoolean("isAccountButtonAvailable");
        this.accountMessage = jDJSONObject.optString("accountMessage");
        this.buttonStyle = jDJSONObject.optInt("buttonStyle");
        this.tipsType = Integer.valueOf(jDJSONObject.optInt("tipsType"));
        this.tipsMsg = jDJSONObject.optString("tipsMsg");
        this.accountType = jDJSONObject.optString("accountType");
        this.balanceType = jDJSONObject.optString("balanceType");
        this.balanceBtnType = jDJSONObject.optInt("balanceBtnType");
        JDJSONObject optJSONObject = jDJSONObject.optJSONObject("titleInfo");
        if (optJSONObject != null) {
            this.titleInfo = new CartTitleInfo(optJSONObject);
        }
        this.skuOrderInfos = CartSkuOrderInfo.toList(jDJSONObject.optJSONArray("skuOrderInfos"));
        this.skuExtOrderInfos = CartSkuOrderInfo.toList(jDJSONObject.optJSONArray("skuExtOrderInfos"));
        JDJSONObject optJSONObject2 = jDJSONObject.optJSONObject("cartParamMapInfo");
        if (optJSONObject2 != null) {
            this.cartParamMapInfoJson = optJSONObject2.toString();
        }
    }
}

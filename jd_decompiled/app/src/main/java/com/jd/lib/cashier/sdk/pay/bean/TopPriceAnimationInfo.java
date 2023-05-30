package com.jd.lib.cashier.sdk.pay.bean;

import com.jd.lib.cashier.sdk.core.model.ICheckNullObj;

/* loaded from: classes14.dex */
public class TopPriceAnimationInfo implements ICheckNullObj {
    public boolean openAnimation;
    public String moneyFlag = "";
    public String price = "";
    public String priceExtend = "";
    public String subTitle = "";

    @Override // com.jd.lib.cashier.sdk.core.model.ICheckNullObj
    public void checkNullObjAndInit() {
    }

    public String toString() {
        return "TopPriceAnimationInfo{moneyFlag='" + this.moneyFlag + "', openAnimation=" + this.openAnimation + ", price='" + this.price + "', priceExtend='" + this.priceExtend + "', subTitle='" + this.subTitle + "'}";
    }
}

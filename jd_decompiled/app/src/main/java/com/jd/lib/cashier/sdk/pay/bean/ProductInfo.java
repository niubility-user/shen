package com.jd.lib.cashier.sdk.pay.bean;

import com.jd.lib.cashier.sdk.core.model.ICheckNullObj;

/* loaded from: classes14.dex */
public class ProductInfo implements ICheckNullObj {
    public int num;
    public long productId;
    public String unitPrice = "";
    public String totalPrice = "";
    public String canUse = "";
    public String productName = "";
    public String productImgUrl = "";

    @Override // com.jd.lib.cashier.sdk.core.model.ICheckNullObj
    public void checkNullObjAndInit() {
    }
}

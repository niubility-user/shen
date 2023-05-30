package com.jd.lib.cashier.sdk.pay.bean;

import com.jd.lib.cashier.sdk.core.model.ICheckNullObj;
import com.jd.lib.cashier.sdk.core.utils.g0;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes14.dex */
public class PublicGoodPlan implements ICheckNullObj {
    public String confirmBtn;
    public List<PublicGoodContent> items;
    public String mainTitle;

    @Override // com.jd.lib.cashier.sdk.core.model.ICheckNullObj
    public void checkNullObjAndInit() {
        if (this.items == null) {
            this.items = new ArrayList();
        }
        g0.f(this.items);
    }
}

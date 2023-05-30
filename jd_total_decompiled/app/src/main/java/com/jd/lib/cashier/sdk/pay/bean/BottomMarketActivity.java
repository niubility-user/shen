package com.jd.lib.cashier.sdk.pay.bean;

import com.jd.lib.cashier.sdk.core.model.ICheckNullObj;
import com.jd.lib.cashier.sdk.core.utils.g0;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes14.dex */
public class BottomMarketActivity implements ICheckNullObj {
    public List<MarketActivityInfo> channelList;

    @Override // com.jd.lib.cashier.sdk.core.model.ICheckNullObj
    public void checkNullObjAndInit() {
        if (this.channelList == null) {
            this.channelList = new ArrayList();
        }
        g0.f(this.channelList);
    }
}

package com.jd.lib.cashier.sdk.pay.bean;

import com.jd.lib.cashier.sdk.core.model.ICheckNullObj;
import com.jd.lib.cashier.sdk.core.utils.g0;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes14.dex */
public class GouWuJinModel implements ICheckNullObj {
    public String canUseBalance;
    public String subTitle;
    public String tip = "";
    public String title;
    public List<GouWuJinWalletInfo> walletInfos;

    @Override // com.jd.lib.cashier.sdk.core.model.ICheckNullObj
    public void checkNullObjAndInit() {
        if (this.walletInfos == null) {
            this.walletInfos = new ArrayList();
        }
        g0.f(this.walletInfos);
    }
}

package com.jd.lib.cashier.sdk.pay.bean.syncevent;

import com.jd.lib.cashier.sdk.pay.bean.GouWuJinModel;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u00a2\u0006\u0004\b\t\u0010\bR\"\u0010\u0003\u001a\u00020\u00028\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u0003\u0010\u0004\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\b\u00a8\u0006\n"}, d2 = {"Lcom/jd/lib/cashier/sdk/pay/bean/syncevent/ClickGWJIconItemEvent;", "", "Lcom/jd/lib/cashier/sdk/pay/bean/GouWuJinModel;", "gouWuJinModelVO", "Lcom/jd/lib/cashier/sdk/pay/bean/GouWuJinModel;", "getGouWuJinModelVO", "()Lcom/jd/lib/cashier/sdk/pay/bean/GouWuJinModel;", "setGouWuJinModelVO", "(Lcom/jd/lib/cashier/sdk/pay/bean/GouWuJinModel;)V", "<init>", "cashier_release"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes14.dex */
public final class ClickGWJIconItemEvent {
    @NotNull
    private GouWuJinModel gouWuJinModelVO;

    public ClickGWJIconItemEvent(@NotNull GouWuJinModel gouWuJinModel) {
        this.gouWuJinModelVO = gouWuJinModel;
    }

    @NotNull
    public final GouWuJinModel getGouWuJinModelVO() {
        return this.gouWuJinModelVO;
    }

    public final void setGouWuJinModelVO(@NotNull GouWuJinModel gouWuJinModel) {
        this.gouWuJinModelVO = gouWuJinModel;
    }
}

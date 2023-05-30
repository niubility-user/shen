package com.jd.lib.cashier.sdk.h.g;

import androidx.annotation.NonNull;
import com.jd.lib.cashier.sdk.pay.bean.Payment;
import com.jd.lib.cashier.sdk.pay.bean.dynamic.paychannel.CashierBPayChannelListData;
import com.jingdong.common.widget.custom.livewidget.bean.StatusCode;

/* loaded from: classes14.dex */
public class r extends x {
    public CashierBPayChannelListData b;

    public r(@NonNull Payment payment) {
        super(payment);
    }

    @Override // com.jd.lib.cashier.sdk.d.a.a.a
    public int getItemType() {
        return StatusCode.PLAY_INFO_BUFFERING_END;
    }
}

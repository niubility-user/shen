package com.jd.lib.productdetail.tradein;

import java.io.Serializable;

/* loaded from: classes16.dex */
public enum TradeInStep implements Serializable {
    INFORM(-1),
    SELECT_OLD_DEVICE(R.string.tradein_step_select_old_device_title),
    ESTIMATE(R.string.tradein_step_estimate_title),
    TRADEIN(R.string.tradein_step_trade_title),
    BANK(R.string.tradein_step_bank_title),
    TIME(R.string.tradein_step_time_title),
    WAY(R.string.tradein_step_tranway_title),
    TRADEIN_MODE(R.string.tradein_step_way_title);
    
    public int mTitleResId;

    TradeInStep(int i2) {
        this.mTitleResId = i2;
    }
}

package com.jd.lib.productdetail.tradein;

import java.io.Serializable;

/* loaded from: classes16.dex */
public enum TradeInPriceMode implements Serializable {
    ESTIMATED,
    FIXED;

    public static TradeInPriceMode create(int i2) {
        if (i2 == 2) {
            return FIXED;
        }
        return ESTIMATED;
    }

    public String toStringValue() {
        return this == FIXED ? "2" : "1";
    }
}

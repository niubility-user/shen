package com.jd.lib.productdetail.tradein;

import java.io.Serializable;

/* loaded from: classes16.dex */
public enum TradeInButtonType implements Serializable {
    DOUBLE,
    DEFAULT;

    public String toStringValue() {
        return this == DOUBLE ? "2" : "1";
    }
}

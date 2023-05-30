package com.jd.lib.productdetail.tradein;

import java.io.Serializable;

/* loaded from: classes16.dex */
public enum TradeInOpenLayerFrom implements Serializable {
    DOUBLE_TAB("1"),
    SALE_FLOOR("2"),
    BOTTOM_BLACK_BAR("3"),
    STYLE_LAYER("4"),
    DAOJIA("5"),
    FORM_OTHER("0");
    
    public String from;

    TradeInOpenLayerFrom(String str) {
        this.from = str;
    }
}

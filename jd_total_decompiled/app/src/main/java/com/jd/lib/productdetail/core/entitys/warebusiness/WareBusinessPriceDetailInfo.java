package com.jd.lib.productdetail.core.entitys.warebusiness;

import android.text.TextUtils;

/* loaded from: classes15.dex */
public class WareBusinessPriceDetailInfo {
    public boolean display;
    public String name;
    public String value;

    public String getDefaultValue() {
        return this.value;
    }

    public String getName() {
        return TextUtils.isEmpty(this.name) ? "" : this.name;
    }
}

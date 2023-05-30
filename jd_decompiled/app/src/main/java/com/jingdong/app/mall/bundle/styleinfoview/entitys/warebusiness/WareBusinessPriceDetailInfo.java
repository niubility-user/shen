package com.jingdong.app.mall.bundle.styleinfoview.entitys.warebusiness;

import android.text.TextUtils;

/* loaded from: classes3.dex */
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

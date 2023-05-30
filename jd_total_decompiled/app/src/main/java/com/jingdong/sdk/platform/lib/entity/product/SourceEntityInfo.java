package com.jingdong.sdk.platform.lib.entity.product;

import android.text.TextUtils;

/* loaded from: classes10.dex */
public class SourceEntityInfo {
    public static final String INTENT_EXTRA_ARG_SOURCE = "source";
    public static final String SOURCE_TYPE_SHOPPINGCART = "shoppingCart_product";
    public static final String SOURCE_TYPE_UNKNOWN = "unknown";
    public static final String SOURCE_TYPE_WEB_SITE = "shoppingCart_webSite";
    private String sourceType;
    private String sourceValue;

    public SourceEntityInfo(String str, String str2) {
        this.sourceType = str;
        this.sourceValue = str2;
    }

    public String getSourceType() {
        return TextUtils.isEmpty(this.sourceType) ? "unknown" : this.sourceType;
    }

    public String getSourceValue() {
        return TextUtils.isEmpty(this.sourceValue) ? "" : this.sourceValue;
    }

    public void setSourceType(String str) {
        this.sourceType = str;
    }

    public void setSourceValue(String str) {
        this.sourceValue = str;
    }
}

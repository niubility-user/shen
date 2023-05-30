package com.jd.lib.productdetail.core.entitys;

import android.text.TextUtils;

/* loaded from: classes15.dex */
public class PdBuyByMEntity {
    public static final String JUMP_TYPE_HTTP = "1";
    public static final String JUMP_TYPE_OPENAPP = "2";
    public static final String TYPE_CUSTOMIZE = "customize";
    public static final String TYPE_CUSTOMIZE_1 = "1";
    public static final String TYPE_CUSTOMIZE_2 = "2";
    private static final String TYPE_PRINTBAG = "onlinePrint";
    public static final String TYPE_STAGING = "staging";
    public String addCartBusinessName;
    public String addCartText;
    public String buttonContent;
    public String customize;
    public boolean doubleButton;
    public boolean forceLayer;
    public boolean isVirtual;
    public String jumpType;
    public String leftButtonText;
    public String link;
    public boolean multiRow;

    public boolean check() {
        return (TextUtils.isEmpty(this.addCartBusinessName) || TextUtils.isEmpty(this.addCartText)) ? false : true;
    }
}

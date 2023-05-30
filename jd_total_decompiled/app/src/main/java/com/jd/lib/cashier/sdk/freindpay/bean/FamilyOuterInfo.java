package com.jd.lib.cashier.sdk.freindpay.bean;

import android.text.TextUtils;

/* loaded from: classes14.dex */
public class FamilyOuterInfo {
    private static final String OUTER_SHOW_FLAG = "1";
    public String familyText;
    public String jumpUrl;
    public String showFamilyFlag;

    public boolean isShowFamilyOuter() {
        return TextUtils.equals(this.showFamilyFlag, "1");
    }
}

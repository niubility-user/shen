package com.jingdong.app.mall.bundle.jdrhsdk.api;

import android.text.TextUtils;

/* loaded from: classes2.dex */
public class JDRiskHandleOption {
    private String response;

    public String getResponse() {
        return TextUtils.isEmpty(this.response) ? "" : this.response;
    }

    public void setResponse(String str) {
        this.response = str;
    }
}

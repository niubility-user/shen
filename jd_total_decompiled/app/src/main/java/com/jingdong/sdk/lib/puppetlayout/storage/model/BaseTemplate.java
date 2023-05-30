package com.jingdong.sdk.lib.puppetlayout.storage.model;

import android.text.TextUtils;

/* loaded from: classes8.dex */
public class BaseTemplate {
    public String styleId = "";
    public String styleVersion = "";
    public String xml = null;
    public String ddTemplate = "";

    public boolean isValid() {
        return (TextUtils.isEmpty(this.styleId) || TextUtils.isEmpty(this.styleVersion) || TextUtils.isEmpty(this.xml)) ? false : true;
    }
}

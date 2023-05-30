package com.jd.lib.productdetail.core.entitys.warebusiness;

import android.text.TextUtils;

/* loaded from: classes15.dex */
public class WareBusinessArMakeUpEntity {
    public WareBusinessArMarkNew arNew;
    public String icon;
    public int jumpType;
    public String jumpUrl;
    public String preLoadUrl;

    public boolean isValid() {
        WareBusinessArMarkNew wareBusinessArMarkNew;
        return (TextUtils.isEmpty(this.jumpUrl) || (wareBusinessArMarkNew = this.arNew) == null || TextUtils.isEmpty(wareBusinessArMarkNew.icon)) ? false : true;
    }
}

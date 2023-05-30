package com.jd.lib.babel.servicekit.impl;

import com.jd.lib.babel.servicekit.iservice.IBaseInfo;
import com.jingdong.sdk.baseinfo.BaseInfo;

/* loaded from: classes13.dex */
public class InfoBaseInfo implements IBaseInfo {
    @Override // com.jd.lib.babel.servicekit.iservice.IBaseInfo
    public float getDensity() {
        return BaseInfo.getDensity();
    }

    @Override // com.jd.lib.babel.servicekit.iservice.IBaseInfo
    public float getScaledDensity() {
        return BaseInfo.getScaledDensity();
    }
}

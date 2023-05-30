package com.jd.lib.babel.servicekit.impl;

import android.app.Activity;
import android.content.Context;
import com.jd.lib.babel.servicekit.iservice.IDpi;
import com.jingdong.sdk.utils.DPIUtil;

/* loaded from: classes13.dex */
public class DpiImpl implements IDpi {
    @Override // com.jd.lib.babel.servicekit.iservice.IDpi
    public int getAppHeight(Activity activity) {
        return DPIUtil.getAppHeight(activity);
    }

    @Override // com.jd.lib.babel.servicekit.iservice.IDpi
    public int getAppWidth(Activity activity) {
        return DPIUtil.getAppWidth(activity);
    }

    @Override // com.jd.lib.babel.servicekit.iservice.IDpi
    public int getHeight(Context context) {
        return DPIUtil.getHeight(context);
    }

    @Override // com.jd.lib.babel.servicekit.iservice.IDpi
    public int getWidth(Context context) {
        return DPIUtil.getWidth(context);
    }
}

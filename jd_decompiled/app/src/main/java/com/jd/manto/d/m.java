package com.jd.manto.d;

import android.app.Activity;
import android.util.DisplayMetrics;
import com.jd.android.sdk.coreinfo.ScreenSize;
import com.jingdong.manto.sdk.api.IHostBaseInfo;
import com.jingdong.sdk.baseinfo.BaseInfo;

/* loaded from: classes17.dex */
public class m implements IHostBaseInfo {
    @Override // com.jingdong.manto.sdk.api.IHostBaseInfo
    public int getDMHeightPixels() {
        return BaseInfo.getScreenHeight();
    }

    @Override // com.jingdong.manto.sdk.api.IHostBaseInfo
    public int getDMWidthPixels() {
        return BaseInfo.getScreenWidth();
    }

    @Override // com.jingdong.manto.sdk.api.IHostBaseInfo
    public float getDensity() {
        return BaseInfo.getDensity();
    }

    @Override // com.jingdong.manto.sdk.api.IHostBaseInfo
    public String getDeviceBrand() {
        return BaseInfo.getDeviceBrand();
    }

    @Override // com.jingdong.manto.sdk.api.IHostBaseInfo
    public String getDeviceModel() {
        return BaseInfo.getDeviceModel();
    }

    @Override // com.jingdong.manto.sdk.api.IHostBaseInfo
    public DisplayMetrics getDm() {
        return BaseInfo.getDisplayMetricsObject();
    }

    @Override // com.jingdong.manto.sdk.api.IHostBaseInfo
    public DisplayMetrics getRealDm(Activity activity) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ScreenSize realScreenSize = BaseInfo.getRealScreenSize();
        displayMetrics.widthPixels = realScreenSize.widthPixels;
        displayMetrics.heightPixels = realScreenSize.heightPixels;
        return displayMetrics;
    }
}

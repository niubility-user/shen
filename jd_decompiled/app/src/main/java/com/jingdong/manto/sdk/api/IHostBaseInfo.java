package com.jingdong.manto.sdk.api;

import android.app.Activity;
import android.util.DisplayMetrics;
import com.jingdong.manto.sdk.IMantoSdkBase;

/* loaded from: classes16.dex */
public interface IHostBaseInfo extends IMantoSdkBase {
    int getDMHeightPixels();

    int getDMWidthPixels();

    float getDensity();

    String getDeviceBrand();

    String getDeviceModel();

    DisplayMetrics getDm();

    DisplayMetrics getRealDm(Activity activity);
}

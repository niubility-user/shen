package com.jingdong.aura.sdk.update.privacy;

import android.content.Context;
import android.util.DisplayMetrics;

/* loaded from: classes4.dex */
public interface IPrivacyFieldProvider {
    float getDensity(Context context);

    String getDeviceBrand();

    String getDeviceModel();

    DisplayMetrics getDisplayMetrics(Context context);

    String getNetWorkType(Context context);

    String getOsVersion();

    float getScaleDensity(Context context);

    boolean isAgreedPrivacy();
}

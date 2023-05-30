package com.jingdong.app.mall.aura;

import android.content.Context;
import android.util.DisplayMetrics;
import com.jingdong.aura.sdk.update.privacy.BasePrivacyFieldProvider;
import com.jingdong.sdk.baseinfo.BaseInfo;

/* loaded from: classes19.dex */
class k extends BasePrivacyFieldProvider {
    @Override // com.jingdong.aura.sdk.update.privacy.IPrivacyFieldProvider
    public float getDensity(Context context) {
        return BaseInfo.getDensity();
    }

    @Override // com.jingdong.aura.sdk.update.privacy.BasePrivacyFieldProvider, com.jingdong.aura.sdk.update.privacy.IPrivacyFieldProvider
    public String getDeviceBrand() {
        return BaseInfo.getDeviceBrand();
    }

    @Override // com.jingdong.aura.sdk.update.privacy.BasePrivacyFieldProvider, com.jingdong.aura.sdk.update.privacy.IPrivacyFieldProvider
    public String getDeviceModel() {
        return BaseInfo.getDeviceModel();
    }

    @Override // com.jingdong.aura.sdk.update.privacy.BasePrivacyFieldProvider, com.jingdong.aura.sdk.update.privacy.IPrivacyFieldProvider
    public DisplayMetrics getDisplayMetrics(Context context) {
        DisplayMetrics displayMetricsObject = BaseInfo.getDisplayMetricsObject();
        return displayMetricsObject == null ? super.getDisplayMetrics(context) : displayMetricsObject;
    }

    @Override // com.jingdong.aura.sdk.update.privacy.BasePrivacyFieldProvider, com.jingdong.aura.sdk.update.privacy.IPrivacyFieldProvider
    public String getNetWorkType(Context context) {
        return BaseInfo.getNetworkType();
    }

    @Override // com.jingdong.aura.sdk.update.privacy.BasePrivacyFieldProvider, com.jingdong.aura.sdk.update.privacy.IPrivacyFieldProvider
    public String getOsVersion() {
        return BaseInfo.getAndroidVersion();
    }

    @Override // com.jingdong.aura.sdk.update.privacy.IPrivacyFieldProvider
    public float getScaleDensity(Context context) {
        return BaseInfo.getScaledDensity();
    }

    @Override // com.jingdong.aura.sdk.update.privacy.BasePrivacyFieldProvider, com.jingdong.aura.sdk.update.privacy.IPrivacyFieldProvider
    public boolean isAgreedPrivacy() {
        return BaseInfo.isAgreedPrivacy();
    }
}

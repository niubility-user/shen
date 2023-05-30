package com.jingdong.aura.sdk.update.privacy;

import android.content.Context;
import android.os.Build;
import android.util.DisplayMetrics;
import com.jingdong.aura.sdk.update.b.j;
import com.jingdong.sdk.baseinfo.BaseInfo;

/* loaded from: classes4.dex */
public abstract class BasePrivacyFieldProvider implements IPrivacyFieldProvider {
    @Override // com.jingdong.aura.sdk.update.privacy.IPrivacyFieldProvider
    public String getDeviceBrand() {
        return BaseInfo.getDeviceBrand();
    }

    @Override // com.jingdong.aura.sdk.update.privacy.IPrivacyFieldProvider
    public String getDeviceModel() {
        return BaseInfo.getDeviceModel();
    }

    @Override // com.jingdong.aura.sdk.update.privacy.IPrivacyFieldProvider
    public DisplayMetrics getDisplayMetrics(Context context) {
        return context.getResources().getDisplayMetrics();
    }

    @Override // com.jingdong.aura.sdk.update.privacy.IPrivacyFieldProvider
    public String getNetWorkType(Context context) {
        return j.a(context);
    }

    @Override // com.jingdong.aura.sdk.update.privacy.IPrivacyFieldProvider
    public String getOsVersion() {
        return Build.VERSION.RELEASE;
    }

    @Override // com.jingdong.aura.sdk.update.privacy.IPrivacyFieldProvider
    public boolean isAgreedPrivacy() {
        return true;
    }
}

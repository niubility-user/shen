package com.jingdong.aura.sdk.update.privacy;

import android.content.Context;

/* loaded from: classes4.dex */
public class DefaultPrivacyFieldProvider extends BasePrivacyFieldProvider {
    @Override // com.jingdong.aura.sdk.update.privacy.IPrivacyFieldProvider
    public float getDensity(Context context) {
        return context.getResources().getDisplayMetrics().density;
    }

    @Override // com.jingdong.aura.sdk.update.privacy.IPrivacyFieldProvider
    public float getScaleDensity(Context context) {
        return context.getResources().getDisplayMetrics().scaledDensity;
    }
}

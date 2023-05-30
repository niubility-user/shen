package com.jingdong.app.mall.aura.internal;

import android.content.Intent;
import com.jingdong.aura.sdk.provided.ProvidedBundleNotFoundActivity;
import com.jingdong.aura.wrapper.listener.AuraPageCallback;
import com.jingdong.jdsdk.JdSdk;

/* loaded from: classes19.dex */
public class a implements AuraPageCallback {
    @Override // com.jingdong.aura.wrapper.listener.AuraPageCallback
    public Intent getClassNotFoundPage(Intent intent) {
        Intent intent2 = new Intent();
        intent2.setClass(JdSdk.getInstance().getApplication(), UfoPageNotFound.class);
        intent2.addFlags(1073741824);
        return intent2;
    }

    @Override // com.jingdong.aura.wrapper.listener.AuraPageCallback
    public String getProvidedBundleNotFoundPageName() {
        return ProvidedBundleNotFoundActivity.class.getName();
    }

    @Override // com.jingdong.aura.wrapper.listener.AuraPageCallback
    public Intent isRedirectToLoadingDexPage(Intent intent) {
        return null;
    }
}

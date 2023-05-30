package com.jingdong.aura.wrapper.listener;

import android.content.Intent;

/* loaded from: classes.dex */
public interface AuraPageCallback {
    Intent getClassNotFoundPage(Intent intent);

    String getProvidedBundleNotFoundPageName();

    Intent isRedirectToLoadingDexPage(Intent intent);
}

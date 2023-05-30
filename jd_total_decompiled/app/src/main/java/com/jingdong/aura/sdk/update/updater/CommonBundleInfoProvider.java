package com.jingdong.aura.sdk.update.updater;

import java.util.List;

/* loaded from: classes4.dex */
public abstract class CommonBundleInfoProvider implements IBundleInfoProvider {
    @Override // com.jingdong.aura.sdk.update.updater.IBundleInfoProvider
    public List<String> getBundleDownloadOrder() {
        return null;
    }

    @Override // com.jingdong.aura.sdk.update.updater.IBundleInfoProvider
    public abstract String getBundleNameFromUpdateID(String str);

    @Override // com.jingdong.aura.sdk.update.updater.IBundleInfoProvider
    public List<String> getProvidedWifiDownloadList() {
        return null;
    }

    @Override // com.jingdong.aura.sdk.update.updater.IBundleInfoProvider
    public String getUpdateIdFromBundleName(String str) {
        return "";
    }
}

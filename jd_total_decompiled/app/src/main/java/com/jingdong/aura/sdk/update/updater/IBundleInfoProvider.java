package com.jingdong.aura.sdk.update.updater;

import java.util.List;

/* loaded from: classes4.dex */
public interface IBundleInfoProvider {
    List<String> getBundleDownloadOrder();

    String getBundleNameFromUpdateID(String str);

    List<String> getProvidedWifiDownloadList();

    String getUpdateIdFromBundleName(String str);
}

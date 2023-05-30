package com.jingdong.common.jdreactFramework.download;

/* loaded from: classes5.dex */
public interface PluginListenerNew {
    void onDownloadProgressChanged(int i2);

    void onFailure(String str, String str2, boolean z, String str3);

    void onFinish(PluginUpdateInfo pluginUpdateInfo);
}

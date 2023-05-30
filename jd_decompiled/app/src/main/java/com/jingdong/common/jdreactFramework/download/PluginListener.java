package com.jingdong.common.jdreactFramework.download;

/* loaded from: classes5.dex */
public interface PluginListener {
    void onDownloadProgressChanged(int i2);

    void onFailure(String str);

    void onFinish(PluginUpdateInfo pluginUpdateInfo);
}

package com.facebook.react.bridge;

import android.content.res.AssetManager;

/* loaded from: classes.dex */
public interface JSBundleLoaderDelegate {
    void loadScriptFromAssets(AssetManager assetManager, String str, boolean z);

    void loadScriptFromDeltaBundle(String str, NativeDeltaClient nativeDeltaClient, boolean z);

    void loadScriptFromFile(String str, String str2, boolean z);

    void loadScriptFromSepAssets(AssetManager assetManager, String str, String str2, boolean z);

    void loadScriptFromSepCommonAssets(AssetManager assetManager, String str, String str2, boolean z);

    void loadScriptFromSepCommonFile(AssetManager assetManager, String str, String str2, boolean z);

    void loadScriptFromSepFile(String str, String str2, String str3, boolean z);

    void setSourceURLs(String str, String str2);
}

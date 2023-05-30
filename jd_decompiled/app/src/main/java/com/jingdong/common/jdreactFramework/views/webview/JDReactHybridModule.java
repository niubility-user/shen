package com.jingdong.common.jdreactFramework.views.webview;

import android.text.TextUtils;
import androidx.annotation.NonNull;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.WritableMap;
import com.jingdong.common.web.WebHybridUtils;
import com.jingdong.common.web.WebOfflineLoaderManager;
import com.jingdong.common.web.WebPreLoadHelper;

/* loaded from: classes5.dex */
public class JDReactHybridModule extends ReactContextBaseJavaModule {
    public JDReactHybridModule(@NonNull ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
    }

    @ReactMethod
    public void clearHybrid(String str, String str2, Callback callback, Callback callback2) {
        try {
            if (WebHybridUtils.hybridEnable) {
                if (!TextUtils.isEmpty(str)) {
                    WebOfflineLoaderManager.deleteOfflineLoader(str);
                }
                if (!TextUtils.isEmpty(str2)) {
                    WebPreLoadHelper.clearPreLoadData(str2);
                }
            }
            if (callback != null) {
                callback.invoke("success");
            }
        } catch (Throwable th) {
            if (callback2 != null) {
                callback2.invoke("error is :" + th.getMessage());
            }
        }
    }

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return "JDReactHybridModule";
    }

    @ReactMethod
    public void preLoadHybrid(String str, Callback callback, Callback callback2) {
        try {
            String createOfflineLoader = WebOfflineLoaderManager.createOfflineLoader(str);
            String preLoad = WebPreLoadHelper.preLoad(str);
            if (callback != null) {
                WritableMap createMap = Arguments.createMap();
                createMap.putString("offlineId", createOfflineLoader);
                createMap.putString("preLoadId", preLoad);
                callback.invoke(createMap);
            }
        } catch (Throwable th) {
            if (callback2 != null) {
                callback2.invoke("error is :" + th.getMessage());
            }
        }
    }
}

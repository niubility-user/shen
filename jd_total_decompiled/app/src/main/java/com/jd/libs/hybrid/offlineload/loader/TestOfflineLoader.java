package com.jd.libs.hybrid.offlineload.loader;

import android.content.Context;
import android.text.TextUtils;
import com.jd.libs.hybrid.base.HybridBase;
import com.jd.libs.hybrid.base.engine.ConfigEngine;
import com.jd.libs.hybrid.base.util.Log;

/* loaded from: classes16.dex */
public class TestOfflineLoader {

    /* loaded from: classes16.dex */
    public interface ConfigCallback {
        void onFail(String str);

        void onSuccess(String str);
    }

    public TestOfflineLoader(Context context) {
    }

    public void requestDebugData(String str, final ConfigCallback configCallback) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        HybridBase.getInstance().getDebugConfig(str, new ConfigEngine.Callback<String>(this) { // from class: com.jd.libs.hybrid.offlineload.loader.TestOfflineLoader.1
            @Override // com.jd.libs.hybrid.base.engine.ConfigEngine.Callback
            public void onFail(int i2, String str2) {
                Log.e("TestOfflineLoader", "[Test-Offline] fetch test data error: " + str2);
                ConfigCallback configCallback2 = configCallback;
                if (configCallback2 != null) {
                    configCallback2.onFail("Http error: " + str2);
                }
            }

            @Override // com.jd.libs.hybrid.base.engine.ConfigEngine.Callback
            public void onSuccess(String str2) {
                try {
                    Log.d("TestOfflineLoader", "[Test-Offline] test data json: " + str2);
                    ConfigCallback configCallback2 = configCallback;
                    if (configCallback2 != null) {
                        configCallback2.onSuccess(str2);
                    }
                } catch (Exception e2) {
                    Log.e("TestOfflineLoader", e2);
                    ConfigCallback configCallback3 = configCallback;
                    if (configCallback3 != null) {
                        configCallback3.onFail("Internal error: " + e2.getMessage());
                    }
                }
            }
        });
    }
}

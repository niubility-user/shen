package com.jingdong.common.jdreactFramework.modules;

import android.os.Handler;
import android.os.Looper;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.jdjr.risk.biometric.core.BiometricManager;
import com.jdjr.risk.biometric.core.JSCallback;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class JDReactBiometricModule extends ReactContextBaseJavaModule {
    public JDReactBiometricModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
    }

    @ReactMethod
    public void biometricForOpt(ReadableMap readableMap, final Callback callback, final Callback callback2) {
        final String string = readableMap.hasKey("json") ? readableMap.getString("json") : "";
        final String string2 = readableMap.hasKey("pin") ? readableMap.getString("pin") : "";
        new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.jingdong.common.jdreactFramework.modules.JDReactBiometricModule.1
            @Override // java.lang.Runnable
            public void run() {
                try {
                    BiometricManager.getInstance().biometricForJS(JDReactBiometricModule.this.getReactApplicationContext(), string, string2, new JSCallback() { // from class: com.jingdong.common.jdreactFramework.modules.JDReactBiometricModule.1.1
                        @Override // com.jdjr.risk.biometric.core.JSCallback
                        public void onFinish(int i2, JSONObject jSONObject) {
                            Callback callback3;
                            if (jSONObject != null && (callback3 = callback) != null) {
                                callback3.invoke(jSONObject.toString());
                                return;
                            }
                            Callback callback4 = callback2;
                            if (callback4 != null) {
                                callback4.invoke(new Object[0]);
                            }
                        }
                    });
                } catch (Throwable th) {
                    Callback callback3 = callback2;
                    if (callback3 != null) {
                        callback3.invoke(th.getMessage());
                    }
                }
            }
        });
    }

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return JDReactBiometricModule.class.getSimpleName();
    }
}

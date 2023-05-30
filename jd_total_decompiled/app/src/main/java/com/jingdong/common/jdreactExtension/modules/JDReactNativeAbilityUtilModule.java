package com.jingdong.common.jdreactExtension.modules;

import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.jd.sec.LogoManager;
import com.jd.stat.security.jma.JMA;
import com.jingdong.jdsdk.JdSdk;

/* loaded from: classes5.dex */
public class JDReactNativeAbilityUtilModule extends ReactContextBaseJavaModule {
    public JDReactNativeAbilityUtilModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
    }

    @ReactMethod
    public void getFinger(Promise promise) {
        try {
            LogoManager.getInstance(JdSdk.getInstance().getApplicationContext()).init();
            promise.resolve(LogoManager.getInstance(JdSdk.getInstance().getApplicationContext()).getLogo());
        } catch (Exception e2) {
            promise.reject(e2.getMessage());
        }
    }

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return "JDNativeAbilityUtil";
    }

    @ReactMethod
    public void getwhwswswws(Promise promise) {
        try {
            promise.resolve(JMA.getSoftFingerprint(JdSdk.getInstance().getApplicationContext()));
        } catch (Exception e2) {
            promise.reject(e2.getMessage());
        }
    }
}

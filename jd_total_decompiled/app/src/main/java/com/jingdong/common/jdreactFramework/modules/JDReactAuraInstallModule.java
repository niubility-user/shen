package com.jingdong.common.jdreactFramework.modules;

import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.jingdong.aura.provided.api.AuraInstallRequest;
import com.jingdong.aura.provided.api.IAuraInstallManager;
import com.jingdong.aura.serviceloder.AuraServiceLoader;
import com.jingdong.jdsdk.auraSetting.AuraBundleInfos;
import javax.annotation.Nonnull;

/* loaded from: classes.dex */
public class JDReactAuraInstallModule extends ReactContextBaseJavaModule {
    private ReactContext reactContext;

    public JDReactAuraInstallModule(@Nonnull ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
        this.reactContext = reactApplicationContext;
    }

    @ReactMethod
    public void aurainstall(ReadableMap readableMap, final Callback callback, final Callback callback2) {
        String str = "";
        if (readableMap != null) {
            try {
                str = readableMap.getString("bundleId");
                String str2 = "bundleId:" + str;
            } catch (Exception e2) {
                String str3 = "error: " + e2.getMessage();
                callback2.invoke(new Object[0]);
                return;
            }
        }
        int parseInt = Integer.parseInt(str);
        if (parseInt >= 0) {
            ((IAuraInstallManager) AuraServiceLoader.get(this.reactContext, IAuraInstallManager.class)).startInstall(this.reactContext, new AuraInstallRequest.Builder().setBundleName(AuraBundleInfos.getBundleNameFromBundleId(parseInt)).setDownloadType(2).addOnFailerListener(new AuraInstallRequest.IOnFailerListener() { // from class: com.jingdong.common.jdreactFramework.modules.JDReactAuraInstallModule.2
                @Override // com.jingdong.aura.provided.api.AuraInstallRequest.IOnFailerListener
                public void onFailure(Exception exc) {
                    Callback callback3 = callback2;
                    if (callback3 != null) {
                        callback3.invoke(exc.getMessage());
                    }
                }
            }).addOnSuccessListener(new AuraInstallRequest.IOnSuccessListener() { // from class: com.jingdong.common.jdreactFramework.modules.JDReactAuraInstallModule.1
                @Override // com.jingdong.aura.provided.api.AuraInstallRequest.IOnSuccessListener
                public void onSuccess() {
                    Callback callback3 = callback;
                    if (callback3 != null) {
                        callback3.invoke(new Object[0]);
                    }
                }
            }).build());
        } else if (callback2 != null) {
            callback2.invoke(new Object[0]);
        }
    }

    @Override // com.facebook.react.bridge.NativeModule
    @Nonnull
    public String getName() {
        return "JDReactAuraInstallModule";
    }
}

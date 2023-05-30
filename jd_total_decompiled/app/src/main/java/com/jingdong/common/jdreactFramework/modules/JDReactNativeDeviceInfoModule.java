package com.jingdong.common.jdreactFramework.modules;

import androidx.collection.ArrayMap;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.jingdong.common.jdreactFramework.listener.NativeDeviceInfoListener;
import java.util.Map;

/* loaded from: classes5.dex */
public class JDReactNativeDeviceInfoModule extends ReactContextBaseJavaModule {
    private NativeDeviceInfoListener mNativeDeviceInfoListener;

    public JDReactNativeDeviceInfoModule(ReactApplicationContext reactApplicationContext, NativeDeviceInfoListener nativeDeviceInfoListener) {
        super(reactApplicationContext);
        this.mNativeDeviceInfoListener = nativeDeviceInfoListener;
    }

    @Override // com.facebook.react.bridge.BaseJavaModule
    public Map<String, Object> getConstants() {
        ArrayMap arrayMap = new ArrayMap();
        NativeDeviceInfoListener nativeDeviceInfoListener = this.mNativeDeviceInfoListener;
        return nativeDeviceInfoListener != null ? nativeDeviceInfoListener.getConstants() : arrayMap;
    }

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return "JDReactNativeDeviceInfoModule";
    }
}

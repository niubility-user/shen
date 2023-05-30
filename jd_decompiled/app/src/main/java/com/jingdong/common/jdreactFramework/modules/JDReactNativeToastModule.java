package com.jingdong.common.jdreactFramework.modules;

import androidx.collection.ArrayMap;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.jingdong.common.jdreactFramework.listener.NativeToastModuleListener;
import java.util.Map;

/* loaded from: classes5.dex */
public class JDReactNativeToastModule extends ReactContextBaseJavaModule {
    private static final String DURATION_LONG_KEY = "LONG";
    private static final String DURATION_SHORT_KEY = "SHORT";
    private static final String TAG = "JDReactNativeToastModule";
    private NativeToastModuleListener mNativeToastModuleListener;

    public JDReactNativeToastModule(ReactApplicationContext reactApplicationContext, NativeToastModuleListener nativeToastModuleListener) {
        super(reactApplicationContext);
        this.mNativeToastModuleListener = nativeToastModuleListener;
    }

    @Override // com.facebook.react.bridge.BaseJavaModule
    public Map<String, Object> getConstants() {
        ArrayMap arrayMap = new ArrayMap();
        arrayMap.put(DURATION_SHORT_KEY, 0);
        arrayMap.put(DURATION_LONG_KEY, 1);
        return arrayMap;
    }

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return TAG;
    }

    @ReactMethod
    public void show(String str, int i2, int i3) {
        NativeToastModuleListener nativeToastModuleListener = this.mNativeToastModuleListener;
        if (nativeToastModuleListener != null) {
            nativeToastModuleListener.show(str, i2, i3);
        }
    }
}

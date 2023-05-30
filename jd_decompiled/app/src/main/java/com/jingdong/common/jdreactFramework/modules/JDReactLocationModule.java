package com.jingdong.common.jdreactFramework.modules;

import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;

/* loaded from: classes5.dex */
public class JDReactLocationModule extends ReactContextBaseJavaModule {
    public static final String EVENT_LOCATION_CHANGE = "JDReactLocationChange";
    public static final String EVENT_LOCATION_ERROR = "JDReactLocationError";

    public JDReactLocationModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
    }

    @ReactMethod
    public void cancelLocation() {
    }

    @ReactMethod
    public void getAddress(ReadableMap readableMap, Callback callback, Callback callback2) {
    }

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return "JDReactLocationModule";
    }

    @Override // com.facebook.react.bridge.BaseJavaModule, com.facebook.react.bridge.NativeModule
    public void onCatalystInstanceDestroy() {
        super.onCatalystInstanceDestroy();
        cancelLocation();
    }

    @ReactMethod
    public void startLocation(ReadableMap readableMap, Callback callback, Callback callback2) {
    }
}

package com.jingdong.common.jdreactFramework.modules;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import com.jingdong.common.jdreactFramework.JDReactHelper;

@ReactModule(name = JDReactAppearanceModule.NAME)
/* loaded from: classes5.dex */
public class JDReactAppearanceModule extends ReactContextBaseJavaModule {
    private static final String APPEARANCE_CHANGED_EVENT_NAME = "JDReact.appearanceChanged";
    public static final String NAME = "JDReactAppearanceModule";

    public JDReactAppearanceModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
    }

    public void emitAppearanceChanged(int i2, String str) {
        WritableMap createMap = Arguments.createMap();
        createMap.putInt("uiMode", i2);
        createMap.putString("uiModeName", str);
        ReactApplicationContext reactApplicationContext = getReactApplicationContext();
        if (reactApplicationContext != null) {
            ((DeviceEventManagerModule.RCTDeviceEventEmitter) reactApplicationContext.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)).emit(APPEARANCE_CHANGED_EVENT_NAME, createMap);
        }
    }

    @ReactMethod(isBlockingSynchronousMethod = true)
    public WritableMap getCurrentUIMode() {
        WritableMap createMap = Arguments.createMap();
        if (JDReactHelper.newInstance().getUIModeHelper() != null) {
            int currentUIMode = JDReactHelper.newInstance().getUIModeHelper().getCurrentUIMode();
            createMap.putInt("uiMode", currentUIMode);
            createMap.putString("uiModeName", JDReactHelper.newInstance().getUIModeHelper().getUIModeName(currentUIMode));
        }
        return createMap;
    }

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return NAME;
    }

    public void sendEvent(int i2, String str) {
        emitAppearanceChanged(i2, str);
    }
}

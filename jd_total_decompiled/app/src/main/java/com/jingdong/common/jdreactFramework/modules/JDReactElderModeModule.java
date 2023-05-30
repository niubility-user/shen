package com.jingdong.common.jdreactFramework.modules;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import com.jd.dynamic.DYConstants;
import com.jingdong.common.jdreactFramework.JDReactHelper;
import com.jingdong.jdreact.plugin.gradient.JDReactLinearGradientManager;

@ReactModule(name = JDReactElderModeModule.NAME)
/* loaded from: classes5.dex */
public class JDReactElderModeModule extends ReactContextBaseJavaModule {
    private static final String ELDERMODE_CHANGED_EVENT_NAME = "JDReact.elderModeChanged";
    public static final String NAME = "JDReactElderModeModule";

    public JDReactElderModeModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
    }

    public void emitElderModeChanged(int i2) {
        WritableMap createMap = Arguments.createMap();
        createMap.putInt("mode", i2);
        ReactApplicationContext reactApplicationContext = getReactApplicationContext();
        if (reactApplicationContext != null) {
            ((DeviceEventManagerModule.RCTDeviceEventEmitter) reactApplicationContext.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)).emit(ELDERMODE_CHANGED_EVENT_NAME, createMap);
        }
    }

    @ReactMethod(isBlockingSynchronousMethod = true)
    public WritableMap getCurrentColors() {
        WritableMap createMap = Arguments.createMap();
        if (JDReactHelper.newInstance().getElderModeHelper() != null) {
            createMap.putString(JDReactLinearGradientManager.PROP_COLORS, JDReactHelper.newInstance().getElderModeHelper().getColors());
        }
        return createMap;
    }

    @ReactMethod(isBlockingSynchronousMethod = true)
    public WritableMap getCurrentMode() {
        WritableMap createMap = Arguments.createMap();
        if (JDReactHelper.newInstance().getElderModeHelper() != null) {
            createMap.putInt("mode", JDReactHelper.newInstance().getElderModeHelper().getCurrentElderMode());
        }
        return createMap;
    }

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return NAME;
    }

    @ReactMethod(isBlockingSynchronousMethod = true)
    public WritableMap getTextSize(Float f2) {
        WritableMap createMap = Arguments.createMap();
        if (JDReactHelper.newInstance().getElderModeHelper() != null) {
            createMap.putDouble(DYConstants.DY_TEXT_SIZE, JDReactHelper.newInstance().getElderModeHelper().getTextSize(f2.floatValue()));
        }
        return createMap;
    }

    public void sendEvent(int i2) {
        emitElderModeChanged(i2);
    }
}

package com.facebook.react.uimanager;

import com.facebook.react.bridge.CatalystInstance;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.UIManager;
import com.facebook.react.uimanager.common.ViewUtil;

/* loaded from: classes12.dex */
public class UIManagerHelper {
    public static UIManager getUIManager(ReactContext reactContext, int i2) {
        CatalystInstance catalystInstance = reactContext.getCatalystInstance();
        if (i2 == 2) {
            return (UIManager) catalystInstance.getJSIModule(UIManager.class);
        }
        return (UIManager) catalystInstance.getNativeModule(UIManagerModule.class);
    }

    public static UIManager getUIManagerForReactTag(ReactContext reactContext, int i2) {
        return getUIManager(reactContext, ViewUtil.getUIManagerType(i2));
    }
}

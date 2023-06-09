package com.facebook.react.modules.common;

import com.facebook.common.logging.FLog;
import com.facebook.react.bridge.CatalystInstance;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.common.ReactConstants;

/* loaded from: classes12.dex */
public class ModuleDataCleaner {

    /* loaded from: classes12.dex */
    public interface Cleanable {
        void clearSensitiveData();
    }

    public static void cleanDataFromModules(CatalystInstance catalystInstance) {
        for (NativeModule nativeModule : catalystInstance.getNativeModules()) {
            if (nativeModule instanceof Cleanable) {
                FLog.d(ReactConstants.TAG, "Cleaning data from " + nativeModule.getName());
                ((Cleanable) nativeModule).clearSensitiveData();
            }
        }
    }
}

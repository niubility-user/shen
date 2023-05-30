package com.jingdong.common.jdreactFramework.utils;

import androidx.annotation.Nullable;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import java.util.HashMap;

/* loaded from: classes5.dex */
public class CommonUtil extends AresCommonUtils {
    static final String TAG = "CommonUtil";

    public static void invokeCallback(Callback callback, Object... objArr) {
        if (callback != null) {
            try {
                callback.invoke(objArr);
            } catch (Exception e2) {
                JLog.e(TAG, e2.toString());
            }
        }
    }

    public static void send(ReactContext reactContext, String str, HashMap hashMap) {
        if (hashMap == null || hashMap.size() <= 0) {
            return;
        }
        WritableMap createMap = Arguments.createMap();
        for (String str2 : hashMap.keySet()) {
            Object obj = hashMap.get(str2);
            if (obj != null) {
                if (obj instanceof String) {
                    createMap.putString(str2, (String) obj);
                } else if (obj instanceof WritableArray) {
                    createMap.putArray(str2, (WritableArray) obj);
                } else if (obj instanceof Boolean) {
                    createMap.putBoolean(str2, ((Boolean) obj).booleanValue());
                } else if (obj instanceof Double) {
                    createMap.putDouble(str2, ((Double) obj).doubleValue());
                } else if (obj instanceof Integer) {
                    createMap.putInt(str2, ((Integer) obj).intValue());
                } else if (obj instanceof WritableMap) {
                    createMap.putMap(str2, (WritableMap) obj);
                }
                sendEvent(reactContext, str, createMap);
            }
        }
    }

    private static void sendEvent(ReactContext reactContext, String str, @Nullable WritableMap writableMap) {
        if (reactContext != null) {
            ((DeviceEventManagerModule.RCTDeviceEventEmitter) reactContext.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)).emit(str, writableMap);
        }
    }
}

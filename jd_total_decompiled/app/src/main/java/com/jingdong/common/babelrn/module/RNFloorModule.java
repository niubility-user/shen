package com.jingdong.common.babelrn.module;

import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.jingdong.sdk.oklog.OKLog;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes5.dex */
public class RNFloorModule extends ReactContextBaseJavaModule {
    private static final String METHOD_REFRESH = "METHOD_REFRESH";
    private static String MODULE_NAME = "RNFloorModule";
    private static final String TAG = "RNFloorModule";
    private ReactApplicationContext mContext;

    public RNFloorModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
        this.mContext = reactApplicationContext;
    }

    @Override // com.facebook.react.bridge.BaseJavaModule
    public Map<String, Object> getConstants() {
        HashMap hashMap = new HashMap();
        hashMap.put(METHOD_REFRESH, METHOD_REFRESH);
        return hashMap;
    }

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return MODULE_NAME;
    }

    @ReactMethod
    public void invokeNativeMethod(String str, String str2, ReadableMap readableMap, Promise promise) {
        char c2 = '\uffff';
        try {
            if (str2.hashCode() == 1552423261 && str2.equals(METHOD_REFRESH)) {
                c2 = 0;
            }
            RNFloorEngin.getInstance().refreshPage(str);
            if (promise != null) {
                promise.resolve("");
            }
        } catch (Exception e2) {
            if (promise != null) {
                promise.reject(TAG, e2.getMessage());
            }
            OKLog.e(TAG, e2);
        }
    }

    @ReactMethod
    public void setFloorHeight(String str, int i2, int i3) {
        if (i3 < 1) {
            return;
        }
        RNFloorEngin.getInstance().setFloorHeight(str, i2, i3);
    }
}

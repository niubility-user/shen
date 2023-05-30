package com.jingdong.common.jdreactFramework.modules;

import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.jingdong.common.jdreactFramework.JDCallBackHelper;
import com.jingdong.common.jdreactFramework.JDReactHelper;
import com.jingdong.common.jdreactFramework.listener.NativeSharedDataListener;
import com.jingdong.common.jdreactFramework.utils.JLog;
import com.jingdong.common.jdreactFramework.utils.ReactModuleAvailabilityUtils;
import java.util.Map;

/* loaded from: classes5.dex */
public class JDReactNativeSharedDataModule extends ReactContextBaseJavaModule {
    private static final String TAG = "JDReactNativeSharedDataModule";
    private NativeSharedDataListener mNativeSharedDataListener;

    public JDReactNativeSharedDataModule(ReactApplicationContext reactApplicationContext, NativeSharedDataListener nativeSharedDataListener) {
        super(reactApplicationContext);
        this.mNativeSharedDataListener = nativeSharedDataListener;
    }

    public static synchronized void cleanAll() {
        synchronized (JDReactNativeSharedDataModule.class) {
            try {
                ReactModuleAvailabilityUtils.clearSharedData();
            } catch (Exception e2) {
                if (JDReactHelper.newInstance().isDebug()) {
                    JLog.e(TAG, e2.toString());
                }
            }
        }
    }

    public static synchronized Map<String, ?> getAll() {
        Map<String, ?> sharedData;
        synchronized (JDReactNativeSharedDataModule.class) {
            try {
                sharedData = ReactModuleAvailabilityUtils.getSharedData();
            } catch (Exception e2) {
                if (JDReactHelper.newInstance().isDebug()) {
                    JLog.e(TAG, e2.toString());
                    return null;
                }
                return null;
            }
        }
        return sharedData;
    }

    public static synchronized String getData(String str) {
        String str2;
        synchronized (JDReactNativeSharedDataModule.class) {
            str2 = "";
            try {
                str2 = ReactModuleAvailabilityUtils.getSharedData(str);
            } catch (Exception e2) {
                if (JDReactHelper.newInstance().isDebug()) {
                    JLog.e(TAG, e2.toString());
                }
            }
        }
        return str2;
    }

    public static synchronized void putData(String str, String str2) {
        synchronized (JDReactNativeSharedDataModule.class) {
            try {
                ReactModuleAvailabilityUtils.saveSharedData(str, str2);
            } catch (Exception e2) {
                if (JDReactHelper.newInstance().isDebug()) {
                    JLog.e(TAG, e2.toString());
                }
            }
        }
    }

    public static synchronized void removeData(String str) {
        synchronized (JDReactNativeSharedDataModule.class) {
            try {
                ReactModuleAvailabilityUtils.removeSharedData(str);
            } catch (Exception e2) {
                if (JDReactHelper.newInstance().isDebug()) {
                    JLog.e(TAG, e2.toString());
                }
            }
        }
    }

    @ReactMethod
    public void addSharedData(ReadableMap readableMap, Callback callback, Callback callback2) {
        NativeSharedDataListener nativeSharedDataListener = this.mNativeSharedDataListener;
        if (nativeSharedDataListener != null) {
            nativeSharedDataListener.addSharedData(readableMap.toHashMap(), new JDCallBackHelper(callback), new JDCallBackHelper(callback2));
        }
    }

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return TAG;
    }

    @ReactMethod
    public void getSharedData(ReadableMap readableMap, Callback callback, Callback callback2) {
        NativeSharedDataListener nativeSharedDataListener = this.mNativeSharedDataListener;
        if (nativeSharedDataListener != null) {
            nativeSharedDataListener.getSharedData(readableMap.toHashMap(), new JDCallBackHelper(callback), new JDCallBackHelper(callback2));
        }
    }

    @ReactMethod
    public void putSharedData(ReadableMap readableMap, Callback callback, Callback callback2) {
        NativeSharedDataListener nativeSharedDataListener = this.mNativeSharedDataListener;
        if (nativeSharedDataListener != null) {
            nativeSharedDataListener.putSharedData(readableMap.toHashMap(), new JDCallBackHelper(callback), new JDCallBackHelper(callback2));
        }
    }

    @ReactMethod
    public void queryAllSharedData(Callback callback, Callback callback2) {
        NativeSharedDataListener nativeSharedDataListener = this.mNativeSharedDataListener;
        if (nativeSharedDataListener != null) {
            nativeSharedDataListener.queryAllSharedData(new JDCallBackHelper(callback), new JDCallBackHelper(callback2));
        }
    }

    @ReactMethod
    public void querySharedDataByName(String str, Callback callback, Callback callback2) {
        NativeSharedDataListener nativeSharedDataListener = this.mNativeSharedDataListener;
        if (nativeSharedDataListener != null) {
            nativeSharedDataListener.querySharedDataByName(str, new JDCallBackHelper(callback), new JDCallBackHelper(callback2));
        }
    }
}

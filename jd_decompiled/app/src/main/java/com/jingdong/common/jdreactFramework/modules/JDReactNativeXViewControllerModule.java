package com.jingdong.common.jdreactFramework.modules;

import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.jingdong.common.jdreactFramework.JDCallBackHelper;
import com.jingdong.common.jdreactFramework.listener.NativeXViewControllerListener;

/* loaded from: classes5.dex */
public class JDReactNativeXViewControllerModule extends ReactContextBaseJavaModule {
    private static final String TAG = "JDReactNativeXViewControllerModule";
    private NativeXViewControllerListener mNativeXViewControllerListener;

    public JDReactNativeXViewControllerModule(ReactApplicationContext reactApplicationContext, NativeXViewControllerListener nativeXViewControllerListener) {
        super(reactApplicationContext);
        this.mNativeXViewControllerListener = nativeXViewControllerListener;
    }

    @ReactMethod
    public void closeXView(Callback callback, Callback callback2) {
        NativeXViewControllerListener nativeXViewControllerListener = this.mNativeXViewControllerListener;
        if (nativeXViewControllerListener != null) {
            nativeXViewControllerListener.closeXView(new JDCallBackHelper(callback), new JDCallBackHelper(callback2));
        }
    }

    @ReactMethod
    public void destroyXView(Callback callback, Callback callback2) {
        NativeXViewControllerListener nativeXViewControllerListener = this.mNativeXViewControllerListener;
        if (nativeXViewControllerListener != null) {
            nativeXViewControllerListener.destroyXView(new JDCallBackHelper(callback), new JDCallBackHelper(callback2));
        }
    }

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return TAG;
    }

    @ReactMethod
    public void showXView(ReadableMap readableMap, Callback callback, Callback callback2) {
        NativeXViewControllerListener nativeXViewControllerListener = this.mNativeXViewControllerListener;
        if (nativeXViewControllerListener != null) {
            nativeXViewControllerListener.showXView(readableMap.toHashMap(), new JDCallBackHelper(callback), new JDCallBackHelper(callback2));
        }
    }
}

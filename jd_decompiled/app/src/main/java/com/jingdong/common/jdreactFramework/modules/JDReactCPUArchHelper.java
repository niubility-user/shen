package com.jingdong.common.jdreactFramework.modules;

import android.os.Build;
import android.text.TextUtils;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

/* loaded from: classes5.dex */
public class JDReactCPUArchHelper extends ReactContextBaseJavaModule {
    public JDReactCPUArchHelper(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
    }

    @ReactMethod
    public void getCpuArch(Callback callback, Callback callback2) {
        String str = Build.CPU_ABI;
        if (!TextUtils.isEmpty(str)) {
            callback.invoke(str);
        } else {
            callback2.invoke("");
        }
    }

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return "RTCJDReactCPUArchHelper";
    }

    @ReactMethod
    public void is64Bit(Callback callback, Callback callback2) {
        String str = Build.CPU_ABI;
        if (TextUtils.isEmpty(str)) {
            callback.invoke(Boolean.FALSE);
        } else {
            callback.invoke(Boolean.valueOf(str.contains("arm64")));
        }
    }
}

package com.jingdong.common.babelrn.module;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.jingdong.common.babelrn.utils.BabelRNFragmentUtil;

/* loaded from: classes5.dex */
public class RNBabelModule extends ReactContextBaseJavaModule {
    private static String MODULE_NAME = "RNBabelModule";
    private ReactApplicationContext mContext;

    public RNBabelModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
        this.mContext = reactApplicationContext;
    }

    @ReactMethod
    public void enableStatusBarTint(boolean z) {
        BabelRNFragmentUtil.getInstance().enableStatusBarTint(z);
    }

    @ReactMethod
    public void exitApp() {
    }

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return MODULE_NAME;
    }
}

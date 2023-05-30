package com.facebook.react.modules.core;

import com.facebook.common.logging.FLog;
import com.facebook.react.bridge.BaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.common.JavascriptException;
import com.facebook.react.common.ReactConstants;
import com.facebook.react.devsupport.interfaces.DevSupportManager;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.util.JSStackTrace;

@ReactModule(name = ExceptionsManagerModule.NAME)
/* loaded from: classes12.dex */
public class ExceptionsManagerModule extends BaseJavaModule {
    public static final String NAME = "ExceptionsManager";
    private final DevSupportManager mDevSupportManager;

    public ExceptionsManagerModule(DevSupportManager devSupportManager) {
        this.mDevSupportManager = devSupportManager;
    }

    private void showOrThrowError(String str, ReadableArray readableArray, int i2) {
        if (this.mDevSupportManager.getDevSupportEnabled()) {
            this.mDevSupportManager.showNewJSError(str, readableArray, i2);
            return;
        }
        throw new JavascriptException(JSStackTrace.format(str, readableArray));
    }

    @ReactMethod
    public void dismissRedbox() {
        if (this.mDevSupportManager.getDevSupportEnabled()) {
            this.mDevSupportManager.hideRedboxDialog();
        }
    }

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return NAME;
    }

    @ReactMethod
    public void reportFatalException(String str, ReadableArray readableArray, int i2) {
        showOrThrowError(str, readableArray, i2);
    }

    @ReactMethod
    public void reportSoftException(String str, ReadableArray readableArray, int i2) {
        if (this.mDevSupportManager.getDevSupportEnabled()) {
            this.mDevSupportManager.showNewJSError(str, readableArray, i2);
        } else {
            FLog.e(ReactConstants.TAG, JSStackTrace.format(str, readableArray));
        }
    }

    @ReactMethod
    public void updateExceptionMessage(String str, ReadableArray readableArray, int i2) {
        if (this.mDevSupportManager.getDevSupportEnabled()) {
            this.mDevSupportManager.updateJSError(str, readableArray, i2);
        }
    }
}

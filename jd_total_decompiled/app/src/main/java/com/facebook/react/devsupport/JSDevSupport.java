package com.facebook.react.devsupport;

import android.view.View;
import com.facebook.react.bridge.JavaScriptModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.devsupport.JSCHeapCapture;
import com.facebook.react.module.annotations.ReactModule;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Nullable;

@ReactModule(name = JSDevSupport.MODULE_NAME)
/* loaded from: classes12.dex */
public class JSDevSupport extends ReactContextBaseJavaModule {
    public static final int ERROR_CODE_EXCEPTION = 0;
    public static final int ERROR_CODE_VIEW_NOT_FOUND = 1;
    public static final String MODULE_NAME = "JSDevSupport";
    @Nullable
    private volatile DevSupportCallback mCurrentCallback;

    /* loaded from: classes12.dex */
    public interface DevSupportCallback {
        void onFailure(int i2, Exception exc);

        void onSuccess(String str);
    }

    /* loaded from: classes12.dex */
    public interface JSDevSupportModule extends JavaScriptModule {
        void getJSHierarchy(int i2);
    }

    public JSDevSupport(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
        this.mCurrentCallback = null;
    }

    public synchronized void computeDeepestJSHierarchy(View view, DevSupportCallback devSupportCallback) {
        getJSHierarchy(Integer.valueOf(((View) ViewHierarchyUtil.getDeepestLeaf(view).first).getId()).intValue(), devSupportCallback);
    }

    @Override // com.facebook.react.bridge.BaseJavaModule
    public Map<String, Object> getConstants() {
        HashMap hashMap = new HashMap();
        hashMap.put("ERROR_CODE_EXCEPTION", 0);
        hashMap.put("ERROR_CODE_VIEW_NOT_FOUND", 1);
        return hashMap;
    }

    public synchronized void getJSHierarchy(int i2, DevSupportCallback devSupportCallback) {
        JSDevSupportModule jSDevSupportModule = (JSDevSupportModule) getReactApplicationContext().getJSModule(JSDevSupportModule.class);
        if (jSDevSupportModule == null) {
            devSupportCallback.onFailure(0, new JSCHeapCapture.CaptureException("JSDevSupport module not registered."));
            return;
        }
        this.mCurrentCallback = devSupportCallback;
        jSDevSupportModule.getJSHierarchy(i2);
    }

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return MODULE_NAME;
    }

    @ReactMethod
    public synchronized void onFailure(int i2, String str) {
        if (this.mCurrentCallback != null) {
            this.mCurrentCallback.onFailure(i2, new RuntimeException(str));
        }
    }

    @ReactMethod
    public synchronized void onSuccess(String str) {
        if (this.mCurrentCallback != null) {
            this.mCurrentCallback.onSuccess(str);
        }
    }
}

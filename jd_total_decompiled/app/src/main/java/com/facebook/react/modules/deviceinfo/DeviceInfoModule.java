package com.facebook.react.modules.deviceinfo;

import android.content.Context;
import com.facebook.react.bridge.BaseJavaModule;
import com.facebook.react.bridge.LifecycleEventListener;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import com.facebook.react.uimanager.DisplayMetricsHolder;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Nullable;

@ReactModule(name = DeviceInfoModule.NAME)
/* loaded from: classes12.dex */
public class DeviceInfoModule extends BaseJavaModule implements LifecycleEventListener {
    public static final String NAME = "DeviceInfo";
    private float mFontScale;
    @Nullable
    private ReactApplicationContext mReactApplicationContext;

    public DeviceInfoModule(ReactApplicationContext reactApplicationContext) {
        this((Context) reactApplicationContext);
        this.mReactApplicationContext = reactApplicationContext;
        reactApplicationContext.addLifecycleEventListener(this);
    }

    public void emitUpdateDimensionsEvent() {
        ReactApplicationContext reactApplicationContext = this.mReactApplicationContext;
        if (reactApplicationContext == null) {
            return;
        }
        ((DeviceEventManagerModule.RCTDeviceEventEmitter) reactApplicationContext.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)).emit("didUpdateDimensions", DisplayMetricsHolder.getDisplayMetricsNativeMap(this.mFontScale));
    }

    @Override // com.facebook.react.bridge.BaseJavaModule
    @Nullable
    public Map<String, Object> getConstants() {
        HashMap hashMap = new HashMap();
        hashMap.put("Dimensions", DisplayMetricsHolder.getDisplayMetricsMap(this.mFontScale));
        return hashMap;
    }

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return NAME;
    }

    @Override // com.facebook.react.bridge.LifecycleEventListener
    public void onHostDestroy() {
    }

    @Override // com.facebook.react.bridge.LifecycleEventListener
    public void onHostPause() {
    }

    @Override // com.facebook.react.bridge.LifecycleEventListener
    public void onHostResume() {
        ReactApplicationContext reactApplicationContext = this.mReactApplicationContext;
        if (reactApplicationContext == null) {
            return;
        }
        float f2 = reactApplicationContext.getResources().getConfiguration().fontScale;
        if (this.mFontScale != f2) {
            this.mFontScale = f2;
            emitUpdateDimensionsEvent();
        }
    }

    public DeviceInfoModule(Context context) {
        this.mReactApplicationContext = null;
        if (context instanceof ReactApplicationContext) {
            ReactApplicationContext reactApplicationContext = (ReactApplicationContext) context;
            if (reactApplicationContext.getCurrentActivity() != null) {
                DisplayMetricsHolder.initDisplayMetricsIfNotInitializedForActivity(reactApplicationContext.getCurrentActivity());
                this.mFontScale = context.getResources().getConfiguration().fontScale;
            }
        }
        DisplayMetricsHolder.initDisplayMetricsIfNotInitialized(context);
        this.mFontScale = context.getResources().getConfiguration().fontScale;
    }
}

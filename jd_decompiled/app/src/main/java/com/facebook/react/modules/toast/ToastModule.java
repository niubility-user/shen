package com.facebook.react.modules.toast;

import android.widget.Toast;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.UiThreadUtil;
import com.facebook.react.common.MapBuilder;
import com.facebook.react.module.annotations.ReactModule;
import java.util.HashMap;
import java.util.Map;

@ReactModule(name = ToastModule.NAME)
/* loaded from: classes12.dex */
public class ToastModule extends ReactContextBaseJavaModule {
    private static final String DURATION_LONG_KEY = "LONG";
    private static final String DURATION_SHORT_KEY = "SHORT";
    private static final String GRAVITY_BOTTOM_KEY = "BOTTOM";
    private static final String GRAVITY_CENTER = "CENTER";
    private static final String GRAVITY_TOP_KEY = "TOP";
    public static final String NAME = "ToastAndroid";

    public ToastModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
    }

    @Override // com.facebook.react.bridge.BaseJavaModule
    public Map<String, Object> getConstants() {
        HashMap newHashMap = MapBuilder.newHashMap();
        newHashMap.put(DURATION_SHORT_KEY, 0);
        newHashMap.put(DURATION_LONG_KEY, 1);
        newHashMap.put(GRAVITY_TOP_KEY, 49);
        newHashMap.put(GRAVITY_BOTTOM_KEY, 81);
        newHashMap.put(GRAVITY_CENTER, 17);
        return newHashMap;
    }

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return NAME;
    }

    @ReactMethod
    public void show(final String str, final int i2) {
        UiThreadUtil.runOnUiThread(new Runnable() { // from class: com.facebook.react.modules.toast.ToastModule.1
            @Override // java.lang.Runnable
            public void run() {
                Toast.makeText(ToastModule.this.getReactApplicationContext(), str, i2).show();
            }
        });
    }

    @ReactMethod
    public void showWithGravity(final String str, final int i2, final int i3) {
        UiThreadUtil.runOnUiThread(new Runnable() { // from class: com.facebook.react.modules.toast.ToastModule.2
            @Override // java.lang.Runnable
            public void run() {
                Toast makeText = Toast.makeText(ToastModule.this.getReactApplicationContext(), str, i2);
                makeText.setGravity(i3, 0, 0);
                makeText.show();
            }
        });
    }

    @ReactMethod
    public void showWithGravityAndOffset(final String str, final int i2, final int i3, final int i4, final int i5) {
        UiThreadUtil.runOnUiThread(new Runnable() { // from class: com.facebook.react.modules.toast.ToastModule.3
            @Override // java.lang.Runnable
            public void run() {
                Toast makeText = Toast.makeText(ToastModule.this.getReactApplicationContext(), str, i2);
                makeText.setGravity(i3, i4, i5);
                makeText.show();
            }
        });
    }
}

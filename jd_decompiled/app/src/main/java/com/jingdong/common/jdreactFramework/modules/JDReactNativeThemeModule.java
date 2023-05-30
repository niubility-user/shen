package com.jingdong.common.jdreactFramework.modules;

import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.jingdong.common.jdreactFramework.JDReactHelper;
import com.jingdong.common.unification.title.theme.ThemeTitleHelper;

/* loaded from: classes5.dex */
public class JDReactNativeThemeModule extends ReactContextBaseJavaModule {
    private static final String DEFAULT_MODULE_ID = "RN";
    private ReactContext reactContext;

    public JDReactNativeThemeModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
        this.reactContext = reactApplicationContext;
    }

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return "JDReactNativeThemeModule";
    }

    @ReactMethod
    public void getTextColor(ReadableMap readableMap, Callback callback, Callback callback2) {
        int titleTextColor = ThemeTitleHelper.getTitleTextColor(JDReactHelper.newInstance().getApplicationContext(), readableMap.hasKey("moduleId") ? readableMap.getString("moduleId") : DEFAULT_MODULE_ID);
        if (callback != null) {
            callback.invoke(Integer.valueOf(titleTextColor));
        }
    }

    @ReactMethod
    public void getThemeTitleColorStyle(ReadableMap readableMap, Callback callback, Callback callback2) {
        String themeTitleColorStyle = ThemeTitleHelper.getThemeTitleColorStyle(readableMap.hasKey("moduleId") ? readableMap.getString("moduleId") : DEFAULT_MODULE_ID);
        if (callback != null) {
            callback.invoke(themeTitleColorStyle);
        }
    }

    @ReactMethod
    public void getTitleBg(ReadableMap readableMap, Callback callback, Callback callback2) {
        String titleBgUrl = ThemeTitleHelper.getTitleBgUrl(readableMap.hasKey("moduleId") ? readableMap.getString("moduleId") : DEFAULT_MODULE_ID);
        if (titleBgUrl != null) {
            if (callback != null) {
                callback.invoke(titleBgUrl);
            }
        } else if (callback2 != null) {
            callback2.invoke(new Object[0]);
        }
    }

    @ReactMethod
    public void isThemeOpen(Callback callback, Callback callback2) {
        Boolean valueOf = Boolean.valueOf(ThemeTitleHelper.isOpen());
        if (callback != null) {
            callback.invoke(valueOf);
        }
    }
}

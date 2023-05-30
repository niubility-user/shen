package com.jingdong.common.jdreactFramework.modules;

import android.content.SharedPreferences;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableMap;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.app.mall.bundle.mobileConfig.JDMobileConfig;
import com.jingdong.common.jdreactFramework.JDReactHelper;
import com.jingdong.common.unification.customtheme.UnCustomThemeHelper;
import com.jingdong.common.unification.customtheme.inter.DownloadThemeListener;
import com.jingdong.common.utils.inter.JDOverseasUtil;
import com.jingdong.corelib.utils.Log;

/* loaded from: classes5.dex */
public class JDReactNativeMyJDModule extends ReactContextBaseJavaModule {
    private static final String DEFAULT_AREA_ID = "areaId";
    private static final String DEFAULT_MODULE_ID = "id";
    public static final String SCREEN_LOCK = "sp_screen_lock";
    public static final String SCREEN_LOCK_KEY = "screen_lock_value";
    public static final String SCREEN_LOCK_PIC_KEY = "screen_lock_pic_value";

    public JDReactNativeMyJDModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
    }

    @ReactMethod
    public void getCurrentTheme(ReadableMap readableMap, Callback callback, Callback callback2) {
        String themeId = UnCustomThemeHelper.getInstance().getThemeId();
        WritableMap createMap = Arguments.createMap();
        createMap.putString("id", themeId);
        if (callback != null) {
            callback.invoke(createMap);
        }
    }

    @ReactMethod
    public void getLoginTheme(ReadableMap readableMap, final Callback callback, final Callback callback2) {
        UnCustomThemeHelper.getInstance().setDownloadThemeListener(new DownloadThemeListener() { // from class: com.jingdong.common.jdreactFramework.modules.JDReactNativeMyJDModule.2
            {
                JDReactNativeMyJDModule.this = this;
            }

            @Override // com.jingdong.common.unification.customtheme.inter.DownloadThemeListener
            public void downloadTheme(boolean z) {
                if (z) {
                    String themeId = UnCustomThemeHelper.getInstance().getThemeId();
                    WritableMap createMap = Arguments.createMap();
                    createMap.putString("id", themeId);
                    Callback callback3 = callback;
                    if (callback3 != null) {
                        callback3.invoke(createMap);
                    }
                } else {
                    Callback callback4 = callback2;
                    if (callback4 != null) {
                        callback4.invoke(new Object[0]);
                    }
                }
                UnCustomThemeHelper.getInstance().removeThemeChangeListener();
            }
        });
    }

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return "JDReactNativeMyJDModule";
    }

    @ReactMethod
    public void getOverseasAreaId(ReadableMap readableMap, Callback callback, Callback callback2) {
        try {
            int currentOverseasArea = JDOverseasUtil.getCurrentOverseasArea();
            WritableMap createMap = Arguments.createMap();
            createMap.putInt(DEFAULT_AREA_ID, currentOverseasArea);
            if (callback != null) {
                callback.invoke(createMap);
            }
        } catch (Exception e2) {
            if (Log.D) {
                Log.e("OverSea", "getOverseasAreaId--->" + e2.toString());
            }
            if (callback2 != null) {
                callback2.invoke(new Object[0]);
            }
        }
    }

    @ReactMethod
    public void isLockOpen(Callback callback, Callback callback2) {
        try {
            Boolean valueOf = Boolean.valueOf("1".equals(JDMobileConfig.getInstance().getConfig("SKIN", "lockScreen", "lockScreen")));
            Boolean valueOf2 = Boolean.valueOf("1".equals(JDReactHelper.newInstance().getApplicationContext().getSharedPreferences(SCREEN_LOCK, 0).getString(SCREEN_LOCK_KEY, "0")));
            JDJSONObject jDJSONObject = new JDJSONObject();
            jDJSONObject.put("isOpen", (Object) valueOf);
            jDJSONObject.put("local", (Object) valueOf2);
            jDJSONObject.put("isShowTip", (Object) Boolean.valueOf(com.jingdong.app.mall.lockscreen.b.d(JDReactHelper.newInstance().getApplicationContext())));
            jDJSONObject.put("isOPPO", (Object) Boolean.valueOf(com.jingdong.app.mall.lockscreen.b.f()));
            if (Log.D) {
                Log.e("LockScreen", "LockScreen--->isLockOpen--" + jDJSONObject.toJSONString());
            }
            if (callback != null) {
                callback.invoke(jDJSONObject.toJSONString());
            }
        } catch (Exception e2) {
            if (Log.D) {
                Log.e("LockScreen", "LockScreen--->isLockOpen++" + e2.toString());
            }
            if (callback2 != null) {
                callback2.invoke(new Object[0]);
            }
        }
    }

    @ReactMethod
    public void lockChange(ReadableMap readableMap, Callback callback, Callback callback2) {
        synchronized (this) {
            try {
                Boolean valueOf = Boolean.valueOf("1".equals(JDReactHelper.newInstance().getApplicationContext().getSharedPreferences(SCREEN_LOCK, 0).getString(SCREEN_LOCK_KEY, "0")));
                String string = readableMap.hasKey("imgUrl") ? readableMap.getString("imgUrl") : "";
                if (Log.D) {
                    Log.e("LockScreen", "LockScreen--->" + valueOf + "--" + string);
                }
                if (!valueOf.booleanValue()) {
                    com.jingdong.app.mall.lockscreen.a.a(JDReactHelper.newInstance().getApplicationContext()).b();
                } else {
                    com.jingdong.app.mall.lockscreen.a.a(JDReactHelper.newInstance().getApplicationContext()).c();
                }
                SharedPreferences.Editor edit = JDReactHelper.newInstance().getApplicationContext().getSharedPreferences(SCREEN_LOCK, 0).edit();
                edit.putString(SCREEN_LOCK_PIC_KEY, string);
                edit.putString(SCREEN_LOCK_KEY, valueOf.booleanValue() ? "0" : "1");
                edit.apply();
                if (callback != null) {
                    callback.invoke(new Object[0]);
                }
            } catch (Exception e2) {
                if (Log.D) {
                    Log.e("LockScreen", "LockScreen--->" + e2.toString());
                }
                if (callback2 != null) {
                    callback2.invoke(new Object[0]);
                }
            }
        }
    }

    @ReactMethod
    public void setCurrentTheme(ReadableMap readableMap, final Callback callback, final Callback callback2) {
        if (readableMap != null && readableMap.hasKey("id")) {
            UnCustomThemeHelper.getInstance().setThemeId(readableMap.getString("id"), new DownloadThemeListener() { // from class: com.jingdong.common.jdreactFramework.modules.JDReactNativeMyJDModule.1
                {
                    JDReactNativeMyJDModule.this = this;
                }

                @Override // com.jingdong.common.unification.customtheme.inter.DownloadThemeListener
                public void downloadTheme(boolean z) {
                    if (z) {
                        Callback callback3 = callback;
                        if (callback3 != null) {
                            callback3.invoke(new Object[0]);
                        }
                    } else {
                        Callback callback4 = callback2;
                        if (callback4 != null) {
                            callback4.invoke(new Object[0]);
                        }
                    }
                    UnCustomThemeHelper.getInstance().removeThemeChangeListener();
                }
            });
        }
    }

    @ReactMethod
    public void setThemeMainImageUrl(ReadableMap readableMap, Callback callback, Callback callback2) {
        try {
            String string = readableMap.hasKey("imgUrl") ? readableMap.getString("imgUrl") : "";
            if (Log.D) {
                Log.e("LockScreen", "LockScreen--->setThemeMainImageUrl--" + string);
            }
            SharedPreferences.Editor edit = JDReactHelper.newInstance().getApplicationContext().getSharedPreferences(SCREEN_LOCK, 0).edit();
            if (string != null) {
                edit.putString(SCREEN_LOCK_PIC_KEY, string);
            }
            edit.apply();
            if (callback != null) {
                callback.invoke(new Object[0]);
            }
        } catch (Exception e2) {
            if (Log.D) {
                Log.e("LockScreen", "LockScreen--->setThemeMainImageUrl--" + e2.toString());
            }
            if (callback2 != null) {
                callback2.invoke(new Object[0]);
            }
        }
    }
}

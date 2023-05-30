package com.jingdong.common.jdreactFramework.modules;

import android.app.Activity;
import android.content.Intent;
import com.facebook.react.bridge.ActivityEventListener;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.jd.stat.security.jma.JMA;
import com.jingdong.common.jdreactFramework.utils.AbstractJDReactInitialHelper;
import com.jingdong.common.login.DeviceFinger;

/* loaded from: classes5.dex */
public class JDReactNativeJdBeanModule extends ReactContextBaseJavaModule implements ActivityEventListener {
    private static final String TAG = "JDReactNativeJdBeanModule";
    private ReactContext reactContext;

    public JDReactNativeJdBeanModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
        this.reactContext = reactApplicationContext;
        reactApplicationContext.addActivityEventListener(this);
    }

    @ReactMethod
    public void getEid(Callback callback, Callback callback2) {
        String finger = DeviceFinger.getFinger(this.reactContext);
        try {
            if (AbstractJDReactInitialHelper.getCurrentMyActivity() == null) {
                callback2.invoke(new Object[0]);
            } else {
                callback.invoke(finger);
            }
        } catch (Exception unused) {
            callback2.invoke(new Object[0]);
        }
    }

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return TAG;
    }

    @ReactMethod
    public void getShshshfpb(Callback callback, Callback callback2) {
        String softFingerprint = JMA.getSoftFingerprint(this.reactContext);
        try {
            if (AbstractJDReactInitialHelper.getCurrentMyActivity() == null) {
                callback2.invoke(new Object[0]);
            } else {
                callback.invoke(softFingerprint);
            }
        } catch (Exception unused) {
            callback2.invoke(new Object[0]);
        }
    }

    @Override // com.facebook.react.bridge.ActivityEventListener
    public void onActivityResult(Activity activity, int i2, int i3, Intent intent) {
    }

    @Override // com.facebook.react.bridge.ActivityEventListener
    public void onNewIntent(Intent intent) {
    }
}

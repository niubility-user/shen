package com.jingdong.common.jdreactFramework.modules;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.jingdong.common.cps.CpsSubUtils;
import com.jingdong.jdsdk.mta.JDMtaUtils;
import com.jingdong.sdk.log.Log;
import com.jingdong.union.common.config.UnionConstants;
import com.jingdong.union.dependency.IJumpSubCallBack;
import org.jetbrains.annotations.NotNull;

/* loaded from: classes5.dex */
public class JDReactNativeUnionModule extends ReactContextBaseJavaModule {
    public static final String TAG = "JDReactNativeUnionModule";

    public JDReactNativeUnionModule(@NotNull ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
    }

    @Override // com.facebook.react.bridge.NativeModule
    @NotNull
    public String getName() {
        return TAG;
    }

    @ReactMethod
    public void getUnplInfo(Callback callback, Callback callback2) {
        Log.d(TAG, "getUnplInfo run");
        String unpl = JDMtaUtils.getUnpl();
        if (TextUtils.isEmpty(unpl)) {
            if (callback2 != null) {
                callback2.invoke("");
            }
        } else if (callback != null) {
            callback.invoke(unpl);
        }
    }

    @ReactMethod
    public void reportSubJdUnion(String str, String str2, String str3, String str4, String str5, String str6, int i2, final Callback callback, final Callback callback2) {
        Log.d(TAG, "\u8054\u76df\u526f\u70b9\u51fb\u63a5\u53e3 reportSubJdUnion");
        Activity currentActivity = getCurrentActivity();
        if (TextUtils.isEmpty(str) && TextUtils.isEmpty(str2) && TextUtils.isEmpty(str3)) {
            if (callback2 != null) {
                callback2.invoke(-10000);
                return;
            }
            return;
        }
        Bundle bundle = new Bundle();
        bundle.putString(UnionConstants.BUNDLE_SKUID, str);
        bundle.putString("vender_id", str2);
        bundle.putString(UnionConstants.BUNDLE_ACTURL, str3);
        bundle.putString(UnionConstants.BUNDLE_REFER, str4);
        bundle.putString(UnionConstants.BUNDLE_CURRENT, str5);
        IJumpSubCallBack iJumpSubCallBack = new IJumpSubCallBack() { // from class: com.jingdong.common.jdreactFramework.modules.JDReactNativeUnionModule.1
            {
                JDReactNativeUnionModule.this = this;
            }

            @Override // com.jingdong.union.dependency.IJumpSubCallBack
            public void onResult(Context context, String str7, String str8, String str9, int i3) {
                Log.d(JDReactNativeUnionModule.TAG, "state:" + i3);
                if (i3 == 1) {
                    Callback callback3 = callback;
                    if (callback3 != null) {
                        callback3.invoke(Integer.valueOf(i3), str7, str8, str9);
                        return;
                    }
                    return;
                }
                Callback callback4 = callback2;
                if (callback4 != null) {
                    callback4.invoke(str7, str8, str9, Integer.valueOf(i3));
                }
            }
        };
        if ("1".equals(str6)) {
            CpsSubUtils.reportSubJdUnion(currentActivity, bundle, i2, iJumpSubCallBack);
        } else {
            CpsSubUtils.reportSubJdUnionNoLoading(currentActivity, bundle, i2, iJumpSubCallBack);
        }
    }
}

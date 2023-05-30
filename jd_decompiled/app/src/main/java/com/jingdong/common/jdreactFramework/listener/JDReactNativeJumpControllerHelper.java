package com.jingdong.common.jdreactFramework.listener;

import android.app.Activity;
import android.net.Uri;
import com.facebook.react.bridge.Callback;
import com.jingdong.common.jdflutter.JDFlutterCall;
import com.jingdong.common.jdflutter.JDFlutterCallResult;
import com.jingdong.common.jump.OpenAppConstant;
import com.jingdong.common.jump.OpenAppJumpBuilder;
import com.jingdong.sdk.oklog.OKLog;
import java.util.HashMap;

/* loaded from: classes5.dex */
public class JDReactNativeJumpControllerHelper implements JDFlutterCall {
    public static final String JUMPCHANNEL = "com.jd.jdflutter/jump";
    private static final String TAG = "JDReactNativeJumpControllerModule";

    @Override // com.jingdong.common.jdflutter.JDFlutterCall
    public void onMethodCall(String str, HashMap hashMap, final JDFlutterCallResult jDFlutterCallResult, Activity activity) {
        if (str.equals("jumpopenapp")) {
            toOpenapp(hashMap.containsKey("openapp_url") ? (String) hashMap.get("openapp_url") : "", activity);
            jDFlutterCallResult.success("");
        } else if (str.equals("jumpback")) {
            activity.finish();
        } else if (str.equals("fetch")) {
            JDReactNativeNetworkHelper jDReactNativeNetworkHelper = new JDReactNativeNetworkHelper();
            if (hashMap.containsKey("functionId")) {
                hashMap.put("function_id", (String) hashMap.get("functionId"));
                jDReactNativeNetworkHelper.fetchMap(hashMap, new Callback() { // from class: com.jingdong.common.jdreactFramework.listener.JDReactNativeJumpControllerHelper.1
                    @Override // com.facebook.react.bridge.Callback
                    public void invoke(Object... objArr) {
                        jDFlutterCallResult.success(objArr[0].toString());
                    }
                }, new Callback() { // from class: com.jingdong.common.jdreactFramework.listener.JDReactNativeJumpControllerHelper.2
                    @Override // com.facebook.react.bridge.Callback
                    public void invoke(Object... objArr) {
                        jDFlutterCallResult.error("", "", "");
                    }
                });
                return;
            }
            jDFlutterCallResult.error("", "", "");
        }
    }

    public void toOpenapp(String str, Activity activity) {
        if (str != null) {
            try {
                if (str.startsWith(OpenAppConstant.SCHEME_OPENAPP_1) || str.startsWith("openapp.jdmobile")) {
                    new OpenAppJumpBuilder.Builder(Uri.parse(str)).build().jump(activity);
                }
            } catch (Exception e2) {
                OKLog.e(TAG, e2);
            }
        }
    }
}

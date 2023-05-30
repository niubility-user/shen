package com.jingdong.common.jdreactFramework.listener;

import android.app.Activity;
import com.jingdong.common.jdflutter.JDFlutterCall;
import com.jingdong.common.jdflutter.JDFlutterCallResult;
import com.jingdong.common.jdreactFramework.JDCallback;
import com.jingdong.common.jdreactFramework.activities.JDReactNativeBaseActivity;
import java.util.HashMap;

/* loaded from: classes5.dex */
public class JDReactNativeSetShareUrlListener implements NativeSetShareUrlListener, JDFlutterCall {
    public static final String SHAREDDATACHANNEL = "com.jd.jdflutter/SetShareUrl";
    private static final String TAG = "JDReactNativeSetShareUrlListener";

    @Override // com.jingdong.common.jdflutter.JDFlutterCall
    public void onMethodCall(String str, HashMap hashMap, final JDFlutterCallResult jDFlutterCallResult, Activity activity) {
        if (str.equals("setUrl")) {
            setUrl(activity, hashMap, new JDCallback() { // from class: com.jingdong.common.jdreactFramework.listener.JDReactNativeSetShareUrlListener.1
                @Override // com.jingdong.common.jdreactFramework.JDCallback
                public void invoke(Object... objArr) {
                    jDFlutterCallResult.success(objArr[0].toString());
                }
            }, new JDCallback() { // from class: com.jingdong.common.jdreactFramework.listener.JDReactNativeSetShareUrlListener.2
                @Override // com.jingdong.common.jdreactFramework.JDCallback
                public void invoke(Object... objArr) {
                    jDFlutterCallResult.error("", "", "");
                }
            });
        }
    }

    @Override // com.jingdong.common.jdreactFramework.listener.NativeSetShareUrlListener
    public void setUrl(Activity activity, HashMap hashMap, JDCallback jDCallback, JDCallback jDCallback2) {
        if (activity == null || !(activity instanceof JDReactNativeBaseActivity)) {
            return;
        }
        ((JDReactNativeBaseActivity) activity).setUrl(hashMap, jDCallback, jDCallback2);
    }
}

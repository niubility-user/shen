package com.jingdong.common.jdreactFramework.listener;

import android.app.Activity;
import com.jingdong.common.jdflutter.JDFlutterCall;
import com.jingdong.common.jdflutter.JDFlutterCallResult;
import com.jingdong.common.jdreactFramework.JDReactHelper;
import com.jingdong.common.jdreactFramework.R;
import com.jingdong.jdsdk.widget.ToastUtils;
import java.util.HashMap;

/* loaded from: classes5.dex */
public class JDReactNativeToastModuleListener implements NativeToastModuleListener, JDFlutterCall {
    private static final String TAG = JDReactNativeMesssageEventListener.class.getSimpleName();
    public static final String TOASTCHANNEL = "com.jd.jdflutter/toast";

    @Override // com.jingdong.common.jdflutter.JDFlutterCall
    public void onMethodCall(String str, HashMap hashMap, JDFlutterCallResult jDFlutterCallResult, Activity activity) {
        if (str.equals("show")) {
            show(hashMap.containsKey("msg") ? (String) hashMap.get("msg") : "", hashMap.containsKey("duration") ? (int) ((Double) hashMap.get("duration")).doubleValue() : 0, hashMap.containsKey("type") ? (int) ((Double) hashMap.get("type")).doubleValue() : 2);
        }
    }

    @Override // com.jingdong.common.jdreactFramework.listener.NativeToastModuleListener
    public void show(String str, int i2, int i3) {
        if (i3 == 0) {
            ToastUtils.showToastInCenter(JDReactHelper.newInstance().getApplicationContext(), R.drawable.jdreact_jd_toast_tick, str, i2);
        } else if (i3 != 1) {
            ToastUtils.showToast(str);
        } else {
            ToastUtils.showToastInCenter(JDReactHelper.newInstance().getApplicationContext(), R.drawable.jdreact_jd_toast_exclamation, str, i2);
        }
    }
}

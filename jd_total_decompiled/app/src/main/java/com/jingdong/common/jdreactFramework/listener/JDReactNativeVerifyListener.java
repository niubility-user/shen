package com.jingdong.common.jdreactFramework.listener;

import android.app.Activity;
import android.text.TextUtils;
import com.coremedia.iso.boxes.FreeBox;
import com.jd.aips.verify.tracker.VerifyTracker;
import com.jd.verify.Verify;
import com.jingdong.common.jdflutter.JDFlutterCall;
import com.jingdong.common.jdflutter.JDFlutterCallResult;
import com.jingdong.common.jdreactFramework.JDCallback;
import com.jingdong.common.jdreactFramework.modules.JDReactNativeVerifyMoudle;
import com.jingdong.common.jdreactFramework.utils.AbstractJDReactInitialHelper;
import com.jingdong.common.utils.StatisticsReportUtil;
import com.unionpay.tsmservice.data.Constant;
import java.util.HashMap;

/* loaded from: classes5.dex */
public class JDReactNativeVerifyListener implements NativeVerifyListener, JDFlutterCall {
    public static final String VERIFYCHANNEL = "com.jd.jdflutter/verify";

    /* renamed from: verify  reason: collision with root package name */
    Verify f12340verify;

    @Override // com.jingdong.common.jdreactFramework.listener.NativeVerifyListener
    public void free(JDCallback jDCallback, JDCallback jDCallback2) {
        Activity currentMyActivity = AbstractJDReactInitialHelper.getCurrentMyActivity();
        if (currentMyActivity != null && (currentMyActivity instanceof JDReactNativeVerifyMoudle.VerfyInterface)) {
            ((JDReactNativeVerifyMoudle.VerfyInterface) currentMyActivity).freeVerify();
        } else if (jDCallback2 != null) {
            jDCallback2.invoke(new Object[0]);
        }
        if (jDCallback != null) {
            jDCallback.invoke(new Object[0]);
        }
    }

    public void freeVerify() {
        Verify verify2 = this.f12340verify;
        if (verify2 != null) {
            verify2.free();
        }
    }

    @Override // com.jingdong.common.jdreactFramework.listener.NativeVerifyListener
    public void getSeesionID(HashMap hashMap, JDCallback jDCallback, JDCallback jDCallback2) {
        if (hashMap.containsKey("params")) {
            String str = (String) hashMap.get("params");
        }
        if (jDCallback2 != null) {
            jDCallback2.invoke("getSessionID is Deprecated");
        }
    }

    @Override // com.jingdong.common.jdflutter.JDFlutterCall
    public void onMethodCall(String str, HashMap hashMap, final JDFlutterCallResult jDFlutterCallResult, Activity activity) {
        if (str.equals(FreeBox.TYPE)) {
            free(new JDCallback() { // from class: com.jingdong.common.jdreactFramework.listener.JDReactNativeVerifyListener.1
                @Override // com.jingdong.common.jdreactFramework.JDCallback
                public void invoke(Object... objArr) {
                    jDFlutterCallResult.success(objArr[0].toString());
                }
            }, new JDCallback() { // from class: com.jingdong.common.jdreactFramework.listener.JDReactNativeVerifyListener.2
                @Override // com.jingdong.common.jdreactFramework.JDCallback
                public void invoke(Object... objArr) {
                    jDFlutterCallResult.error("", "", "");
                }
            });
        } else if (str.equals(VerifyTracker.P_CODE_VERIFY)) {
            verify(hashMap, new JDCallback() { // from class: com.jingdong.common.jdreactFramework.listener.JDReactNativeVerifyListener.3
                @Override // com.jingdong.common.jdreactFramework.JDCallback
                public void invoke(Object... objArr) {
                    jDFlutterCallResult.success(objArr[0].toString());
                }
            }, new JDCallback() { // from class: com.jingdong.common.jdreactFramework.listener.JDReactNativeVerifyListener.4
                @Override // com.jingdong.common.jdreactFramework.JDCallback
                public void invoke(Object... objArr) {
                    jDFlutterCallResult.error("", "", "");
                }
            });
        } else if (str.equals("getSeesionID")) {
            getSeesionID(hashMap, new JDCallback() { // from class: com.jingdong.common.jdreactFramework.listener.JDReactNativeVerifyListener.5
                @Override // com.jingdong.common.jdreactFramework.JDCallback
                public void invoke(Object... objArr) {
                    jDFlutterCallResult.success(objArr[0].toString());
                }
            }, new JDCallback() { // from class: com.jingdong.common.jdreactFramework.listener.JDReactNativeVerifyListener.6
                @Override // com.jingdong.common.jdreactFramework.JDCallback
                public void invoke(Object... objArr) {
                    jDFlutterCallResult.error("", "", "");
                }
            });
        }
    }

    @Override // com.jingdong.common.jdreactFramework.listener.NativeVerifyListener
    public void verify(HashMap hashMap, JDCallback jDCallback, JDCallback jDCallback2) {
        String str;
        if (hashMap.containsKey("session_id")) {
            str = (String) hashMap.get("session_id");
        } else {
            jDCallback2.invoke(new Object[0]);
            str = "";
        }
        String str2 = hashMap.containsKey("uuid") ? (String) hashMap.get("uuid") : "";
        if (TextUtils.isEmpty(str2)) {
            str2 = StatisticsReportUtil.readDeviceUUID();
        }
        String str3 = str2;
        String str4 = hashMap.containsKey(Constant.KEY_COUNTRY_CODE) ? (String) hashMap.get(Constant.KEY_COUNTRY_CODE) : "";
        String str5 = hashMap.containsKey("account") ? (String) hashMap.get("account") : "";
        Activity currentMyActivity = AbstractJDReactInitialHelper.getCurrentMyActivity();
        if (currentMyActivity == null || !(currentMyActivity instanceof JDReactNativeVerifyMoudle.VerfyInterface)) {
            return;
        }
        ((JDReactNativeVerifyMoudle.VerfyInterface) currentMyActivity).showVeriyDialog(jDCallback2, jDCallback, str3, str, str4, str5);
    }
}

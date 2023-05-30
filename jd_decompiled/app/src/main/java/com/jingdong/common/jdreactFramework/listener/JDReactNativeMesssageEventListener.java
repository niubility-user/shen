package com.jingdong.common.jdreactFramework.listener;

import android.app.Activity;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import com.facebook.react.JDReactData;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReadableMap;
import com.jd.manto.router.MantoRouter;
import com.jingdong.common.jdflutter.JDFlutterCall;
import com.jingdong.common.jdflutter.JDFlutterCallResult;
import com.jingdong.common.jdreactFramework.JDCallback;
import com.jingdong.common.jdreactFramework.JDReactHelper;
import com.jingdong.common.jdreactFramework.JDReactMessageEvent;
import com.jingdong.common.jdreactFramework.utils.JLog;
import com.jingdong.common.unification.router.CallBackListener;
import com.jingdong.common.unification.router.JDRouter;
import com.jingdong.common.unification.router.builderimpl.DefaultRouterBuilder;
import de.greenrobot.event.EventBus;
import java.util.HashMap;

/* loaded from: classes5.dex */
public class JDReactNativeMesssageEventListener implements NativeMesssageEventListener, JDFlutterCall {
    public static final String MESSAGEEVENTCHANNEL = "com.jd.jdflutter/messageEvent";
    private static final String TAG = "JDReactNativeMesssageEventListener";

    @Override // com.jingdong.common.jdflutter.JDFlutterCall
    public void onMethodCall(String str, HashMap hashMap, final JDFlutterCallResult jDFlutterCallResult, Activity activity) {
        if (str.equals("sendEventToNative")) {
            sendEventToNative(hashMap.containsKey("eventName") ? (String) hashMap.get("eventName") : "", hashMap.containsKey("readableMap") ? (HashMap) hashMap.get("readableMap") : null, new JDCallback() { // from class: com.jingdong.common.jdreactFramework.listener.JDReactNativeMesssageEventListener.2
                @Override // com.jingdong.common.jdreactFramework.JDCallback
                public void invoke(Object... objArr) {
                    jDFlutterCallResult.success(objArr[0].toString());
                }
            }, new JDCallback() { // from class: com.jingdong.common.jdreactFramework.listener.JDReactNativeMesssageEventListener.3
                @Override // com.jingdong.common.jdreactFramework.JDCallback
                public void invoke(Object... objArr) {
                    jDFlutterCallResult.error("", "", "");
                }
            });
        }
    }

    @Override // com.jingdong.common.jdreactFramework.listener.NativeMesssageEventListener
    public void sendEventToJDMinProgram(HashMap hashMap, final Activity activity, final JDCallback jDCallback, final JDCallback jDCallback2) {
        if (hashMap != null) {
            final String str = hashMap.containsKey("appId") ? (String) hashMap.get("appId") : "";
            final String str2 = hashMap.containsKey("eventName") ? (String) hashMap.get("eventName") : "";
            final String str3 = hashMap.containsKey("params") ? (String) hashMap.get("params") : "";
            if (!TextUtils.isEmpty(str2)) {
                new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.jingdong.common.jdreactFramework.listener.JDReactNativeMesssageEventListener.1
                    @Override // java.lang.Runnable
                    public void run() {
                        DefaultRouterBuilder callBackListener = ((DefaultRouterBuilder) ((DefaultRouterBuilder) ((DefaultRouterBuilder) JDRouter.to("JDMPRouter", MantoRouter.SEND_TO_MANTO).putString("appId", str)).putString("eventName", str2)).putString("params", str3)).callBackListener(new CallBackListener() { // from class: com.jingdong.common.jdreactFramework.listener.JDReactNativeMesssageEventListener.1.1
                            @Override // com.jingdong.common.unification.router.CallBackListener
                            public void onComplete() {
                                JDCallback jDCallback3 = jDCallback;
                                if (jDCallback3 != null) {
                                    jDCallback3.invoke(new Object[0]);
                                }
                            }

                            @Override // com.jingdong.common.unification.router.CallBackListener
                            public void onError(int i2) {
                                JDCallback jDCallback3 = jDCallback2;
                                if (jDCallback3 != null) {
                                    jDCallback3.invoke(Integer.valueOf(i2));
                                }
                            }
                        });
                        Activity activity2 = activity;
                        if (activity2 == null) {
                            activity2 = JDReactHelper.newInstance().getCurrentMyActivity();
                        }
                        callBackListener.jump(activity2);
                    }
                });
                return;
            }
        }
        if (jDCallback2 != null) {
            jDCallback2.invoke(new Object[0]);
        }
    }

    @Override // com.jingdong.common.jdreactFramework.listener.NativeMesssageEventListener
    public void sendEventToNative(String str, Object obj, JDCallback jDCallback, JDCallback jDCallback2) {
        ReadableMap makeNativeMap;
        if (TextUtils.isEmpty(str)) {
            JLog.e(TAG, "eventName is invalid!!");
            return;
        }
        JLog.e(TAG, "eventName is " + str);
        if (obj instanceof ReadableMap) {
            makeNativeMap = (ReadableMap) obj;
        } else {
            makeNativeMap = Arguments.makeNativeMap((HashMap) obj);
        }
        if (str.equals("JDReactExposureMta")) {
            JDReactData.newInstance();
            JDReactData.addData(makeNativeMap);
        }
        JDReactMessageEvent jDReactMessageEvent = new JDReactMessageEvent(str);
        jDReactMessageEvent.setEventParams(makeNativeMap);
        EventBus.getDefault().post(jDReactMessageEvent);
    }
}

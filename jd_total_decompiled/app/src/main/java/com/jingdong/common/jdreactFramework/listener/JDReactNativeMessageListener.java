package com.jingdong.common.jdreactFramework.listener;

import android.app.Activity;
import android.content.Context;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import com.jingdong.common.deeplinkhelper.DeeplinkProductDetailHelper;
import com.jingdong.common.jdflutter.JDFlutterCall;
import com.jingdong.common.jdflutter.JDFlutterCallResult;
import com.jingdong.common.jdreactFramework.JDCallback;
import com.jingdong.common.login.LoginUserBase;
import com.jingdong.common.messagecenter.view.MessageRedObserver;
import com.jingdong.common.messagecenter.view.NewMessagRedManager;
import com.jingdong.common.messagecenter.view.NewMessageRedInfo;
import com.jingdong.common.network.HttpGroupUtils;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes5.dex */
public class JDReactNativeMessageListener implements NativeMessageListener, MessageRedObserver, JDFlutterCall {
    public static final String EVENT_MESSAGE_CHANGE = "onMessageUpdate";
    public static final String MESSAGECHANNEL = "com.jd.jdflutter/message";
    private static final String MESSAGE_REDKEY = "messageRedInfo";
    private static final String TAG = "JDReactNativeMessageListener";
    private static final int TYPE_SHOW_NONE = 2;
    private static final int TYPE_SHOW_NUMBER = 0;
    private static final int TYPE_SHOW_NUMBER99 = 4;
    private static final int TYPE_SHOW_REDDOT = 1;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes5.dex */
    public class JDReactMessageRedObserver implements MessageRedObserver {
        public final JDCallback errorCB;
        public final JDCallback successCB;

        public JDReactMessageRedObserver(JDCallback jDCallback, JDCallback jDCallback2) {
            this.successCB = jDCallback;
            this.errorCB = jDCallback2;
        }

        @Override // com.jingdong.common.messagecenter.view.MessageRedObserver
        public void onMessageRedReceived(Map<String, NewMessageRedInfo> map) {
            if (map == null || !map.containsKey(JDReactNativeMessageListener.MESSAGE_REDKEY)) {
                return;
            }
            NewMessageRedInfo newMessageRedInfo = map.get(JDReactNativeMessageListener.MESSAGE_REDKEY);
            if (newMessageRedInfo == null) {
                try {
                    this.errorCB.invoke("1");
                } catch (Exception unused) {
                }
                NewMessagRedManager.deregisterPersonalMessageObserver(this);
                return;
            }
            WritableMap createMap = Arguments.createMap();
            createMap.putInt("pattern", newMessageRedInfo.pattern);
            createMap.putInt("numPattern", newMessageRedInfo.numPattern);
            createMap.putInt("num", newMessageRedInfo.num);
            createMap.putBoolean("redPoint", newMessageRedInfo.redPoint);
            this.successCB.invoke(createMap);
            NewMessagRedManager.deregisterPersonalMessageObserver(this);
        }
    }

    private void sendEvent(String str, WritableMap writableMap) {
        Context context = Fresco.getContext();
        if (context != null && (context instanceof ReactContext)) {
            try {
                ((DeviceEventManagerModule.RCTDeviceEventEmitter) ((ReactContext) context).getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)).emit(str, writableMap);
            } catch (Exception unused) {
            }
        }
    }

    @Override // com.jingdong.common.jdreactFramework.listener.NativeMessageListener
    public void getJDMessageRedDot(JDCallback jDCallback, JDCallback jDCallback2) {
        try {
            if (LoginUserBase.hasLogin()) {
                JDReactMessageRedObserver jDReactMessageRedObserver = new JDReactMessageRedObserver(jDCallback, jDCallback2);
                NewMessagRedManager.getInstance(LoginUserBase.getLoginUserName());
                NewMessagRedManager.registPersonalMessageObserver(jDReactMessageRedObserver);
                NewMessagRedManager.getInstance(LoginUserBase.getLoginUserName());
                NewMessagRedManager.requestMessage(HttpGroupUtils.getHttpGroupaAsynPool());
            } else {
                jDCallback2.invoke("0");
            }
        } catch (Exception unused) {
            jDCallback2.invoke("1");
        }
    }

    @Override // com.jingdong.common.messagecenter.view.MessageRedObserver
    public void onMessageRedReceived(Map<String, NewMessageRedInfo> map) {
        NewMessageRedInfo newMessageRedInfo;
        int i2;
        if (map == null || !map.containsKey(MESSAGE_REDKEY) || (newMessageRedInfo = map.get(MESSAGE_REDKEY)) == null) {
            return;
        }
        if (newMessageRedInfo.isShowRedDot()) {
            i2 = 1;
        } else if (newMessageRedInfo.isShow9Number()) {
            i2 = 0;
        } else {
            i2 = newMessageRedInfo.isShow99Number() ? 4 : 2;
        }
        WritableMap createMap = Arguments.createMap();
        createMap.putInt(DeeplinkProductDetailHelper.LAYER_STYLE, i2);
        createMap.putInt("num", newMessageRedInfo.num);
        sendEvent(EVENT_MESSAGE_CHANGE, createMap);
    }

    @Override // com.jingdong.common.jdflutter.JDFlutterCall
    public void onMethodCall(String str, HashMap hashMap, final JDFlutterCallResult jDFlutterCallResult, Activity activity) {
        if (str.equals("getJDMessageRedDot")) {
            getJDMessageRedDot(new JDCallback() { // from class: com.jingdong.common.jdreactFramework.listener.JDReactNativeMessageListener.1
                @Override // com.jingdong.common.jdreactFramework.JDCallback
                public void invoke(Object... objArr) {
                    jDFlutterCallResult.success(objArr[0].toString());
                }
            }, new JDCallback() { // from class: com.jingdong.common.jdreactFramework.listener.JDReactNativeMessageListener.2
                @Override // com.jingdong.common.jdreactFramework.JDCallback
                public void invoke(Object... objArr) {
                    jDFlutterCallResult.error("", "", "");
                }
            });
        } else if (str.equals("startMsgObserving")) {
            startMsgObserving();
        } else if (str.equals("stopMsgObserving")) {
            stopMsgObserving();
        }
    }

    @Override // com.jingdong.common.jdreactFramework.listener.NativeMessageListener
    public void startMsgObserving() {
        if (NewMessagRedManager.getInstance(LoginUserBase.getLoginUserName()) != null) {
            try {
                NewMessagRedManager.getInstance(LoginUserBase.getLoginUserName());
                NewMessagRedManager.registPersonalMessageObserver(this);
            } catch (Exception unused) {
            }
        }
    }

    @Override // com.jingdong.common.jdreactFramework.listener.NativeMessageListener
    public void stopMsgObserving() {
        try {
            NewMessagRedManager.deregisterPersonalMessageObserver(this);
        } catch (Exception unused) {
        }
    }
}

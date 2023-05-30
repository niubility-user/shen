package com.jingdong.common.jdreactFramework.listener;

import android.app.Activity;
import com.jingdong.app.mall.voice.JDVoiceInputListener;
import com.jingdong.app.mall.voice.JDVoiceInputUtils;
import com.jingdong.common.jdflutter.JDFlutterCall;
import com.jingdong.common.jdflutter.JDFlutterCallResult;
import com.jingdong.common.jdreactFramework.JDCallback;
import com.jingdong.common.jdreactFramework.utils.AbstractJDReactInitialHelper;
import com.jingdong.common.jump.JumpCallbackListener;
import com.jingdong.common.jump.JumpUtil;
import java.util.HashMap;

/* loaded from: classes5.dex */
public class JDReactNativeVoiceRecognizeListener implements NativeVoiceRecognizeListener, JDFlutterCall {
    public static final String VOICERECOGNIZECHANNEL = "com.jd.jdflutter/voiceRecognize";
    private JDVoiceInputUtils jdVoiceInputUtils;

    @Override // com.jingdong.common.jdflutter.JDFlutterCall
    public void onMethodCall(String str, HashMap hashMap, final JDFlutterCallResult jDFlutterCallResult, Activity activity) {
        if (str.equals("startVoiceRecognize")) {
            startVoiceRecognize(new JDCallback() { // from class: com.jingdong.common.jdreactFramework.listener.JDReactNativeVoiceRecognizeListener.3
                @Override // com.jingdong.common.jdreactFramework.JDCallback
                public void invoke(Object... objArr) {
                    jDFlutterCallResult.success(objArr[0].toString());
                }
            }, new JDCallback() { // from class: com.jingdong.common.jdreactFramework.listener.JDReactNativeVoiceRecognizeListener.4
                @Override // com.jingdong.common.jdreactFramework.JDCallback
                public void invoke(Object... objArr) {
                    jDFlutterCallResult.error("", "", "");
                }
            });
        } else if (str.equals("stopVoiceRecognize")) {
            stopVoiceRecognize(new JDCallback() { // from class: com.jingdong.common.jdreactFramework.listener.JDReactNativeVoiceRecognizeListener.5
                @Override // com.jingdong.common.jdreactFramework.JDCallback
                public void invoke(Object... objArr) {
                    jDFlutterCallResult.success(objArr[0].toString());
                }
            }, new JDCallback() { // from class: com.jingdong.common.jdreactFramework.listener.JDReactNativeVoiceRecognizeListener.6
                @Override // com.jingdong.common.jdreactFramework.JDCallback
                public void invoke(Object... objArr) {
                    jDFlutterCallResult.error("", "", "");
                }
            });
        }
    }

    @Override // com.jingdong.common.jdreactFramework.listener.NativeVoiceRecognizeListener
    public void startVoiceRecognize(final JDCallback jDCallback, final JDCallback jDCallback2) {
        JDVoiceInputUtils jDVoiceInputUtils = this.jdVoiceInputUtils;
        if (jDVoiceInputUtils != null) {
            jDVoiceInputUtils.close();
            this.jdVoiceInputUtils = null;
        }
        JDVoiceInputUtils jDVoiceInputUtils2 = new JDVoiceInputUtils();
        this.jdVoiceInputUtils = jDVoiceInputUtils2;
        jDVoiceInputUtils2.setListener(new JDVoiceInputListener() { // from class: com.jingdong.common.jdreactFramework.listener.JDReactNativeVoiceRecognizeListener.1
            String voiceReslut = "";

            @Override // com.jingdong.app.mall.voice.JDVoiceInputListener
            public void onResult(String str, boolean z) {
                if (str != null) {
                    this.voiceReslut += str;
                }
                if (z) {
                    jDCallback.invoke(this.voiceReslut);
                }
            }

            @Override // com.jingdong.app.mall.voice.JDVoiceInputListener
            public void onVoiceInputCreate() {
            }

            @Override // com.jingdong.app.mall.voice.JDVoiceInputListener
            public void onVoiceInputDestroy() {
                if (JDReactNativeVoiceRecognizeListener.this.jdVoiceInputUtils != null) {
                    JDReactNativeVoiceRecognizeListener.this.jdVoiceInputUtils.close();
                    JDReactNativeVoiceRecognizeListener.this.jdVoiceInputUtils = null;
                }
                jDCallback.invoke(this.voiceReslut);
            }
        });
        JumpUtil.execJumpByDes(JumpUtil.VALUE_DES_VOICEINPUT, AbstractJDReactInitialHelper.getCurrentMyActivity(), null, new JumpCallbackListener() { // from class: com.jingdong.common.jdreactFramework.listener.JDReactNativeVoiceRecognizeListener.2
            @Override // com.jingdong.common.jump.JumpCallbackListener
            public void onError() {
                jDCallback2.invoke(new Object[0]);
            }

            @Override // com.jingdong.common.jump.JumpCallbackListener
            public void onSuccess() {
            }
        });
    }

    @Override // com.jingdong.common.jdreactFramework.listener.NativeVoiceRecognizeListener
    public void stopVoiceRecognize(JDCallback jDCallback, JDCallback jDCallback2) {
        JDVoiceInputUtils jDVoiceInputUtils = this.jdVoiceInputUtils;
        if (jDVoiceInputUtils != null) {
            jDVoiceInputUtils.close();
            this.jdVoiceInputUtils = null;
        }
        jDCallback.invoke(new Object[0]);
    }
}

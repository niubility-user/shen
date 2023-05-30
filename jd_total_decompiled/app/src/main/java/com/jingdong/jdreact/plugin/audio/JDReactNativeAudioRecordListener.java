package com.jingdong.jdreact.plugin.audio;

import android.app.Activity;
import android.content.Intent;
import android.text.TextUtils;
import com.jingdong.common.jdflutter.JDFlutterCall;
import com.jingdong.common.jdflutter.JDFlutterCallResult;
import com.jingdong.common.jdreactFramework.JDCallback;
import com.jingdong.common.jdreactFramework.JDReactHelper;
import com.jingdong.common.jdreactFramework.listener.NativeAudioRecordListener;
import com.jingdong.common.jdreactFramework.listener.NativeMultiMediaModuleListener;
import com.jingdong.jdreact.plugin.audio.ReactAudioRecordUtil;
import java.util.HashMap;

/* loaded from: classes13.dex */
public class JDReactNativeAudioRecordListener implements NativeAudioRecordListener, NativeMultiMediaModuleListener, JDFlutterCall {
    public static final String AUDIORECORDDCHANNEL = "com.jd.jdflutter/audioRecord";

    @Override // com.jingdong.common.jdreactFramework.listener.NativeMultiMediaModuleListener
    public void captureVideo(JDCallback jDCallback, JDCallback jDCallback2) {
    }

    @Override // com.jingdong.common.jdreactFramework.listener.NativeMultiMediaModuleListener
    public void captureVideo(HashMap hashMap, JDCallback jDCallback, JDCallback jDCallback2) {
    }

    @Override // com.jingdong.common.jdreactFramework.listener.NativeAudioRecordListener
    public void deleteRecordingFile(HashMap hashMap, JDCallback jDCallback, JDCallback jDCallback2) {
        try {
            ReactAudioRecordUtil reactAudioRecordUtil = ReactAudioRecordUtil.getInstance();
            if (hashMap.containsKey("filepath")) {
                reactAudioRecordUtil.delAudioFile((String) hashMap.get("filepath"));
                jDCallback.invoke(new Object[0]);
            } else {
                jDCallback2.invoke(new Object[0]);
            }
        } catch (Throwable th) {
            jDCallback2.invoke(th.getMessage());
        }
    }

    @Override // com.jingdong.common.jdreactFramework.listener.NativeMultiMediaModuleListener
    public void hidFullScreenVideoView(JDCallback jDCallback, JDCallback jDCallback2) {
    }

    @Override // com.jingdong.common.jdreactFramework.listener.NativeMultiMediaModuleListener
    public void imageCompressToBase64(String str, int i2, JDCallback jDCallback, JDCallback jDCallback2) {
    }

    @Override // com.jingdong.common.jdreactFramework.listener.NativeMultiMediaModuleListener
    public void imageCompression(HashMap hashMap, JDCallback jDCallback, JDCallback jDCallback2) {
    }

    @Override // com.jingdong.common.jdreactFramework.listener.NativeMultiMediaModuleListener
    public void onActivityResult(Activity activity, int i2, int i3, Intent intent) {
    }

    @Override // com.jingdong.common.jdreactFramework.listener.NativeMultiMediaModuleListener
    public void onHostDestroy() {
    }

    @Override // com.jingdong.common.jdreactFramework.listener.NativeMultiMediaModuleListener
    public void onHostPause() {
    }

    @Override // com.jingdong.common.jdreactFramework.listener.NativeMultiMediaModuleListener
    public void onHostResume() {
    }

    @Override // com.jingdong.common.jdflutter.JDFlutterCall
    public void onMethodCall(String str, HashMap hashMap, final JDFlutterCallResult jDFlutterCallResult, Activity activity) {
        if (str.equals("startRecording")) {
            startRecording(hashMap, new JDCallback() { // from class: com.jingdong.jdreact.plugin.audio.JDReactNativeAudioRecordListener.2
                @Override // com.jingdong.common.jdreactFramework.JDCallback
                public void invoke(Object... objArr) {
                    jDFlutterCallResult.success(objArr[0].toString());
                }
            }, new JDCallback() { // from class: com.jingdong.jdreact.plugin.audio.JDReactNativeAudioRecordListener.3
                @Override // com.jingdong.common.jdreactFramework.JDCallback
                public void invoke(Object... objArr) {
                    jDFlutterCallResult.error("", "", "");
                }
            }, activity);
        } else if (str.equals("stopRecording")) {
            stopRecording(new JDCallback() { // from class: com.jingdong.jdreact.plugin.audio.JDReactNativeAudioRecordListener.4
                @Override // com.jingdong.common.jdreactFramework.JDCallback
                public void invoke(Object... objArr) {
                    jDFlutterCallResult.success(objArr[0].toString());
                }
            }, new JDCallback() { // from class: com.jingdong.jdreact.plugin.audio.JDReactNativeAudioRecordListener.5
                @Override // com.jingdong.common.jdreactFramework.JDCallback
                public void invoke(Object... objArr) {
                    jDFlutterCallResult.error("", "", "");
                }
            });
        } else if (str.equals("deleteRecordingFile")) {
            deleteRecordingFile(hashMap, new JDCallback() { // from class: com.jingdong.jdreact.plugin.audio.JDReactNativeAudioRecordListener.6
                @Override // com.jingdong.common.jdreactFramework.JDCallback
                public void invoke(Object... objArr) {
                    jDFlutterCallResult.success(objArr[0].toString());
                }
            }, new JDCallback() { // from class: com.jingdong.jdreact.plugin.audio.JDReactNativeAudioRecordListener.7
                @Override // com.jingdong.common.jdreactFramework.JDCallback
                public void invoke(Object... objArr) {
                    jDFlutterCallResult.error("", "", "");
                }
            });
        }
    }

    @Override // com.jingdong.common.jdreactFramework.listener.NativeMultiMediaModuleListener
    public void pickPhoto(HashMap hashMap, JDCallback jDCallback, JDCallback jDCallback2) {
    }

    @Override // com.jingdong.common.jdreactFramework.listener.NativeMultiMediaModuleListener
    public void scanCode(HashMap hashMap, JDCallback jDCallback, JDCallback jDCallback2) {
    }

    @Override // com.jingdong.common.jdreactFramework.listener.NativeMultiMediaModuleListener
    public void showFullScreenVideoView(HashMap hashMap, JDCallback jDCallback, JDCallback jDCallback2) {
    }

    @Override // com.jingdong.common.jdreactFramework.listener.NativeMultiMediaModuleListener
    public void startPlaying(HashMap hashMap, JDCallback jDCallback, JDCallback jDCallback2) {
        try {
            if (hashMap.containsKey("filepath")) {
                ReactAudioPlayerUtil.getInstance().play((String) hashMap.get("filepath"));
                jDCallback.invoke(new Object[0]);
            } else {
                jDCallback2.invoke(new Object[0]);
            }
        } catch (Throwable th) {
            jDCallback2.invoke(th.getMessage());
        }
    }

    @Override // com.jingdong.common.jdreactFramework.listener.NativeAudioRecordListener
    public void startRecording(HashMap hashMap, final JDCallback jDCallback, final JDCallback jDCallback2, final Activity activity) {
        ReactAudioRecordUtil reactAudioRecordUtil = ReactAudioRecordUtil.getInstance();
        if (reactAudioRecordUtil.isRecording()) {
            jDCallback2.invoke(new Object[0]);
            return;
        }
        ReactAudioRecordUtil.RecorderConfig recorderConfig = new ReactAudioRecordUtil.RecorderConfig();
        if (hashMap.containsKey("encodeFormat")) {
            recorderConfig.encodeFormat = (int) ((Double) hashMap.get("encodeFormat")).doubleValue();
        }
        if (hashMap.containsKey("outputFormat")) {
            recorderConfig.outputFormat = (int) ((Double) hashMap.get("outputFormat")).doubleValue();
        }
        if (hashMap.containsKey("maxDuration")) {
            recorderConfig.maxDuration = (int) ((Double) hashMap.get("maxDuration")).doubleValue();
        }
        if (hashMap.containsKey("fileSuffix")) {
            recorderConfig.fileSuffix = (String) hashMap.get("fileSuffix");
        }
        if (hashMap.containsKey("sampleRate")) {
            recorderConfig.sampleRate = (int) ((Double) hashMap.get("sampleRate")).doubleValue();
        }
        if (hashMap.containsKey("bitRate")) {
            recorderConfig.bitRate = (int) ((Double) hashMap.get("bitRate")).doubleValue();
        }
        reactAudioRecordUtil.setRecorderConfig(recorderConfig);
        reactAudioRecordUtil.startRecord(hashMap, new ReactAudioRecordUtil.AudioUpdateListener() { // from class: com.jingdong.jdreact.plugin.audio.JDReactNativeAudioRecordListener.1
            @Override // com.jingdong.jdreact.plugin.audio.ReactAudioRecordUtil.AudioUpdateListener
            public void onException(int i2, String str) {
                JDCallback jDCallback3 = jDCallback2;
                if (jDCallback3 != null) {
                    jDCallback3.invoke(str);
                }
            }

            @Override // com.jingdong.jdreact.plugin.audio.ReactAudioRecordUtil.AudioUpdateListener
            public void onStop(String str) {
                Activity activity2 = activity;
                if (activity2 == null || activity2.isFinishing()) {
                    return;
                }
                if (TextUtils.isEmpty(str)) {
                    jDCallback2.invoke(new Object[0]);
                } else {
                    jDCallback.invoke(str);
                }
            }

            @Override // com.jingdong.jdreact.plugin.audio.ReactAudioRecordUtil.AudioUpdateListener
            public void onUpdate(double d, long j2) {
                HashMap hashMap2 = new HashMap();
                hashMap2.put("db", Double.valueOf(d));
                hashMap2.put("time", Long.valueOf(j2));
                JDReactHelper.newInstance().sendMsgToJs(activity.getApplicationContext(), "onUpdate", hashMap2);
            }
        });
    }

    @Override // com.jingdong.common.jdreactFramework.listener.NativeMultiMediaModuleListener
    public void stopPlaying(JDCallback jDCallback, JDCallback jDCallback2) {
        try {
            ReactAudioPlayerUtil.getInstance().stop();
            jDCallback.invoke(new Object[0]);
        } catch (Exception unused) {
            jDCallback2.invoke(new Object[0]);
        }
    }

    @Override // com.jingdong.common.jdreactFramework.listener.NativeAudioRecordListener
    public void stopRecording(JDCallback jDCallback, JDCallback jDCallback2) {
        try {
            ReactAudioRecordUtil.getInstance().stopRecord();
            jDCallback.invoke(new Object[0]);
        } catch (Exception unused) {
            jDCallback2.invoke(new Object[0]);
        }
    }
}

package com.jingdong.common.web.managers;

import android.os.Handler;
import com.jd.wireless.sdk.intelligent.assistant.IntelligentAssistance;
import com.jd.wireless.sdk.intelligent.assistant.IntelligentAssistanceCallBack;

/* loaded from: classes12.dex */
public class JSIntelligentManager {
    private static final String TAG = "JSIntelligentManager";
    private static final int TIME_OUT = 15000;
    private static JSIntelligentManager instance;
    private boolean isInited;
    private boolean isListener;
    private Callback mStartCallBack;
    private Callback mStopCallBack;
    private StringBuffer mContent = new StringBuffer();
    private Handler mHandler = new Handler();
    private Runnable runnable = new Runnable() { // from class: com.jingdong.common.web.managers.JSIntelligentManager.2
        @Override // java.lang.Runnable
        public void run() {
            JSIntelligentManager.this.timeout();
        }
    };
    private IntelligentAssistance intelligentAssistance = IntelligentAssistance.getInstance();

    /* loaded from: classes12.dex */
    public interface Callback {
        void callback(String str, String str2);
    }

    private JSIntelligentManager() {
    }

    public static JSIntelligentManager getInstance() {
        if (instance == null) {
            instance = new JSIntelligentManager();
        }
        return instance;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void removeTimer() {
        try {
            this.mHandler.removeCallbacks(this.runnable);
        } catch (Exception unused) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void timeout() {
        Callback callback = this.mStartCallBack;
        if (callback != null) {
            callback.callback("-2", "\u5f55\u97f3\u8d85\u65f6");
        }
        stopRecord(null);
    }

    public void init(String str, final Callback callback) {
        IntelligentAssistance intelligentAssistance = this.intelligentAssistance;
        if (intelligentAssistance != null) {
            this.isInited = false;
            intelligentAssistance.initSdk(str, new IntelligentAssistanceCallBack() { // from class: com.jingdong.common.web.managers.JSIntelligentManager.1
                @Override // com.jd.wireless.sdk.intelligent.assistant.IntelligentAssistanceCallBack
                public void changVolum(byte b) {
                }

                @Override // com.jd.wireless.sdk.intelligent.assistant.IntelligentAssistanceCallBack
                public void initComplete(byte b) {
                    JSIntelligentManager.this.isInited = true;
                    Callback callback2 = callback;
                    if (callback2 != null) {
                        callback2.callback("0", null);
                    }
                }

                @Override // com.jd.wireless.sdk.intelligent.assistant.IntelligentAssistanceCallBack
                public void phoneticRecognitionResult(String str2, boolean z) {
                    JSIntelligentManager.this.mContent.append(str2);
                    if (!z || JSIntelligentManager.this.mStopCallBack == null) {
                        return;
                    }
                    JSIntelligentManager.this.mStopCallBack.callback("0", JSIntelligentManager.this.mContent.toString());
                }

                @Override // com.jd.wireless.sdk.intelligent.assistant.IntelligentAssistanceCallBack
                public void phoneticRecognitionStart() {
                }

                @Override // com.jd.wireless.sdk.intelligent.assistant.IntelligentAssistanceCallBack
                public void recognitionError(byte b) {
                    JSIntelligentManager.this.isListener = false;
                    if (JSIntelligentManager.this.mStartCallBack != null) {
                        JSIntelligentManager.this.mStartCallBack.callback("-2", "\u8bc6\u522b\u9519\u8bef");
                    }
                    if (JSIntelligentManager.this.mStopCallBack != null) {
                        JSIntelligentManager.this.mStopCallBack.callback("-1", JSIntelligentManager.this.mContent.toString());
                    }
                    JSIntelligentManager.this.removeTimer();
                }

                @Override // com.jd.wireless.sdk.intelligent.assistant.IntelligentAssistanceCallBack
                public void speakStop() {
                }

                @Override // com.jd.wireless.sdk.intelligent.assistant.IntelligentAssistanceCallBack
                public void speechSynthesisStop(int i2) {
                }
            });
        } else if (callback != null) {
            callback.callback("-1", "");
        }
    }

    public void startRecord(Callback callback, int i2) {
        if (this.intelligentAssistance == null) {
            if (callback != null) {
                callback.callback("-1", "");
            }
        } else if (this.isListener) {
            if (callback != null) {
                callback.callback("-1", "\u5df2\u6709\u4e1a\u52a1\u6b63\u5728\u4f7f\u7528\u8bed\u97f3\u8bc6\u522b");
            }
        } else {
            this.mStopCallBack = null;
            this.mContent = new StringBuffer();
            removeTimer();
            if (!this.isInited) {
                if (callback != null) {
                    callback.callback("-1", "\u8bf7\u5148\u6267\u884c\u521d\u59cb\u5316\uff01");
                    return;
                }
                return;
            }
            try {
                this.mStartCallBack = callback;
                this.intelligentAssistance.startSpeak();
                if (callback != null) {
                    callback.callback("0", "");
                }
                this.isListener = true;
                this.mHandler.postDelayed(this.runnable, i2 <= 0 ? 15000L : i2);
            } catch (Exception unused) {
                if (callback != null) {
                    callback.callback("-1", "");
                }
            }
        }
    }

    public void stopRecord(Callback callback) {
        IntelligentAssistance intelligentAssistance = this.intelligentAssistance;
        if (intelligentAssistance == null) {
            if (callback != null) {
                callback.callback("-1", "");
                return;
            }
            return;
        }
        this.mStopCallBack = callback;
        this.mStartCallBack = null;
        if (this.isInited) {
            try {
                intelligentAssistance.stopSpeak();
            } catch (Exception unused) {
                if (callback != null) {
                    callback.callback("-1", "\u5f02\u5e38\u7ed3\u675f");
                }
            }
        } else if (callback != null) {
            callback.callback("-1", "\u5f02\u5e38\u7ed3\u675f,\u672a\u521d\u59cb\u5316");
        }
        this.isListener = false;
        removeTimer();
    }
}

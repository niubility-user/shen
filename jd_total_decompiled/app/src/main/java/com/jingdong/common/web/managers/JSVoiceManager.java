package com.jingdong.common.web.managers;

import android.os.Handler;
import android.text.TextUtils;
import com.jd.wireless.sdk.intelligent.assistant.IntelligentAssistance;
import com.jd.wireless.sdk.intelligent.assistant.IntelligentAssistanceCallBack;
import com.jingdong.corelib.utils.Log;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.jdsdk.network.toolbox.ExceptionReporter;
import com.jingdong.sdk.jdtoast.ToastUtils;

/* loaded from: classes12.dex */
public class JSVoiceManager {
    private static final int TIME_OUT = 15000;
    private static final JSVoiceManager ourInstance = new JSVoiceManager();
    private Callback _startCallback;
    private Callback _stopCallback;
    private IntelligentAssistance intelligentAssistance;
    private StringBuffer sb;
    private boolean _init = false;
    private boolean _start = false;
    private boolean _recognize = false;
    private boolean reportErr = false;
    private Runnable runnable = new Runnable() { // from class: com.jingdong.common.web.managers.JSVoiceManager.2
        @Override // java.lang.Runnable
        public void run() {
            JSVoiceManager.this.cancel();
            if (JSVoiceManager.this.reportErr) {
                ExceptionReporter.reportWebViewCommonError("recognition_err", "", "JSVoiceManager runnable \u5f55\u97f3\u8d85\u65f6", "");
            }
        }
    };
    private Handler mHandler = new Handler();

    /* loaded from: classes12.dex */
    public interface Callback {
        void callback(String str, String str2);
    }

    private JSVoiceManager() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void _removeTimer() {
        try {
            this.mHandler.removeCallbacks(this.runnable);
        } catch (Exception unused) {
        }
    }

    public static JSVoiceManager getInstance() {
        return ourInstance;
    }

    public void cancel() {
        if (this._start) {
            this._stopCallback = null;
            this.intelligentAssistance.stopSpeak();
            this._start = false;
            Callback callback = this._startCallback;
            if (callback != null) {
                callback.callback("-2", "\u53d6\u6d88");
            }
            if (this.reportErr) {
                ExceptionReporter.reportWebViewCommonError("recognition_err", "", "JSVoiceManager cancel \u5f55\u97f3\u5f02\u5e38\u5173\u95ed", "");
            }
        }
    }

    public void initSDK(final Callback callback) {
        this._init = false;
        this.reportErr = false;
        this.sb = new StringBuffer();
        if (this.intelligentAssistance == null) {
            this.intelligentAssistance = IntelligentAssistance.getInstance();
        }
        this.intelligentAssistance.initSdk("5bcd015b", new IntelligentAssistanceCallBack() { // from class: com.jingdong.common.web.managers.JSVoiceManager.1
            @Override // com.jd.wireless.sdk.intelligent.assistant.IntelligentAssistanceCallBack
            public void changVolum(byte b) {
            }

            @Override // com.jd.wireless.sdk.intelligent.assistant.IntelligentAssistanceCallBack
            public void initComplete(byte b) {
                JSVoiceManager.this._init = true;
                Callback callback2 = callback;
                if (callback2 != null) {
                    callback2.callback("0", "\u521d\u59cb\u5316\u6210\u529f");
                }
            }

            @Override // com.jd.wireless.sdk.intelligent.assistant.IntelligentAssistanceCallBack
            public void phoneticRecognitionResult(String str, boolean z) {
                if (!TextUtils.isEmpty(str)) {
                    JSVoiceManager.this.sb.append(str);
                }
                if (z) {
                    if (JSVoiceManager.this._stopCallback != null) {
                        if (Log.D) {
                            ToastUtils.showToast(JdSdk.getInstance().getApplicationContext(), JSVoiceManager.this.sb.toString());
                        }
                        JSVoiceManager.this._stopCallback.callback("0", JSVoiceManager.this.sb.toString());
                    }
                    JSVoiceManager.this._recognize = false;
                }
            }

            @Override // com.jd.wireless.sdk.intelligent.assistant.IntelligentAssistanceCallBack
            public void phoneticRecognitionStart() {
                JSVoiceManager.this._recognize = true;
            }

            @Override // com.jd.wireless.sdk.intelligent.assistant.IntelligentAssistanceCallBack
            public void recognitionError(byte b) {
                if (Log.D) {
                    ToastUtils.showToast(JdSdk.getInstance().getApplicationContext(), "\u8bc6\u522b\u9519\u8bef");
                }
                if (JSVoiceManager.this._stopCallback != null) {
                    JSVoiceManager.this._stopCallback.callback("-1", "\u8bc6\u522b\u9519\u8bef");
                    if (JSVoiceManager.this.reportErr) {
                        ExceptionReporter.reportWebViewCommonError("recognition_err", "", "JSVoiceManager | initSDK recognitionError \u8bed\u97f3\u8bc6\u522b\u9519\u8bef 1 code\uff1a" + ((int) b), "");
                    }
                }
                if (JSVoiceManager.this._startCallback != null) {
                    JSVoiceManager.this._startCallback.callback("-2", "\u8bc6\u522b\u9519\u8bef");
                    JSVoiceManager.this._start = false;
                    if (JSVoiceManager.this.reportErr) {
                        ExceptionReporter.reportWebViewCommonError("recognition_err", "", "JSVoiceManager | initSDK recognitionError \u8bed\u97f3\u8bc6\u522b\u9519\u8bef 2 code\uff1a" + ((int) b), "");
                    }
                }
                JSVoiceManager.this._recognize = false;
                JSVoiceManager.this._removeTimer();
            }

            @Override // com.jd.wireless.sdk.intelligent.assistant.IntelligentAssistanceCallBack
            public void speakStop() {
            }

            @Override // com.jd.wireless.sdk.intelligent.assistant.IntelligentAssistanceCallBack
            public void speechSynthesisStop(int i2) {
            }
        });
    }

    public void start(Callback callback, int i2) {
        if (!this._init) {
            if (callback != null) {
                callback.callback("-1", "\u672a\u521d\u59cb\u5316");
                return;
            }
            return;
        }
        this._stopCallback = null;
        this._startCallback = callback;
        this.sb.setLength(0);
        IntelligentAssistance intelligentAssistance = this.intelligentAssistance;
        if (intelligentAssistance != null) {
            intelligentAssistance.startSpeak();
        }
        callback.callback("0", "\u5f00\u59cb\u5f55\u97f3");
        this._start = true;
        this.mHandler.removeCallbacks(this.runnable);
        this.mHandler.postDelayed(this.runnable, i2 <= 0 ? 15000L : i2 * 1000);
    }

    public void stop(Callback callback) {
        if (!this._start) {
            if (callback != null) {
                callback.callback("-1", "\u672a\u5f00\u59cb\u5f55\u97f3");
                return;
            }
            return;
        }
        this._stopCallback = callback;
        this._startCallback = null;
        if (!this._recognize) {
            callback.callback("-1", "\u672a\u5f00\u59cb\u8bc6\u522b");
        }
        IntelligentAssistance intelligentAssistance = this.intelligentAssistance;
        if (intelligentAssistance != null) {
            intelligentAssistance.stopSpeak();
        }
        this._start = false;
        _removeTimer();
    }
}

package com.jd.lib.un.voice.asr;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import com.jd.lib.un.voice.VoiceConfig;
import g.f.a.c.a;
import g.f.a.c.b;
import g.f.a.c.c;
import g.f.a.c.d;
import g.f.a.c.e;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class UnAsrHelper {
    private static final long TIME_OUT = 5000;
    private static final int WHAT_STOP = 1;
    public static OnOptListener optListener;
    private d asrManager;
    private int errorCode;
    private String errorMsg;
    private Handler handler;
    private boolean isSingle;
    private boolean isStartSpeek;
    private boolean isStop;
    private OnSpeechListener listener;
    private String allResult = "";
    private long voiceTimeOut = 5000;
    private boolean timeOutEnable = true;
    private boolean allResultCallback = false;
    private boolean paritalResultEnable = false;
    private UnAsrType mType = UnAsrType.LONG;
    public UnAsrConfig config = new UnAsrConfig(VoiceConfig.getInstance().getId());
    private JSONObject configJson = this.config.longAsrConfig();

    public UnAsrHelper(Context context) {
        this.asrManager = e.a(context, a.ASR);
        this.handler = new Handler(context.getMainLooper()) { // from class: com.jd.lib.un.voice.asr.UnAsrHelper.1
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                if (message.what == 1) {
                    UnAsrHelper.this.stop();
                }
            }
        };
    }

    private void speechCallback() {
        if (this.listener == null) {
            return;
        }
        this.asrManager.a(new c() { // from class: com.jd.lib.un.voice.asr.UnAsrHelper.2
            @Override // g.f.a.c.c
            public void onEvent(final b bVar, final String str, byte[] bArr) {
                UnAsrHelper.this.handler.post(new Runnable() { // from class: com.jd.lib.un.voice.asr.UnAsrHelper.2.1
                    @Override // java.lang.Runnable
                    public void run() {
                        JSONArray jSONArray;
                        b bVar2 = bVar;
                        if (bVar2 == b.SPEECH_START) {
                            return;
                        }
                        if (bVar2 == b.SPEECH_BEGIN) {
                            if (UnAsrHelper.this.listener != null) {
                                UnAsrHelper.this.listener.onStartSpeech();
                            }
                            UnAsrHelper.this.isStartSpeek = true;
                        } else if (bVar2 == b.SPEECH_END) {
                            if (UnAsrHelper.this.isSingle) {
                                UnAsrHelper.this.listener.onEnd();
                            }
                        } else {
                            int i2 = 0;
                            String str2 = "";
                            if (bVar2 == b.SPEECH_PARITAL_RESULT) {
                                if (UnAsrHelper.this.paritalResultEnable && !TextUtils.isEmpty(str)) {
                                    try {
                                        JSONObject jSONObject = new JSONObject(str);
                                        if (jSONObject.getInt("err_code") == 0 && (jSONArray = jSONObject.getJSONArray("content")) != null && jSONArray.length() != 0) {
                                            while (i2 < jSONArray.length()) {
                                                str2 = str2 + jSONArray.getJSONObject(i2).getString("text");
                                                i2++;
                                            }
                                            if (TextUtils.isEmpty(str2) || UnAsrHelper.this.listener == null) {
                                                return;
                                            }
                                            OnSpeechListener onSpeechListener = UnAsrHelper.this.listener;
                                            if (UnAsrHelper.this.allResultCallback) {
                                                str2 = UnAsrHelper.this.allResult + str2;
                                            }
                                            onSpeechListener.onTemp(str2);
                                        }
                                    } catch (JSONException e2) {
                                        e2.printStackTrace();
                                    }
                                }
                            } else if (bVar2 == b.SPEECH_VOLUME) {
                                if (TextUtils.isEmpty(str)) {
                                    return;
                                }
                                try {
                                    JSONObject jSONObject2 = new JSONObject(str);
                                    if (UnAsrHelper.this.listener != null) {
                                        UnAsrHelper.this.listener.onVolume(jSONObject2.getInt("volume"));
                                    }
                                } catch (JSONException e3) {
                                    e3.printStackTrace();
                                }
                            } else if (bVar2 == b.SPEECH_RESULT) {
                                if (!UnAsrHelper.this.isStop && UnAsrHelper.this.timeOutEnable) {
                                    UnAsrHelper.this.handler.removeMessages(1);
                                    UnAsrHelper.this.handler.sendEmptyMessageDelayed(1, UnAsrHelper.this.voiceTimeOut);
                                }
                                if (TextUtils.isEmpty(str)) {
                                    return;
                                }
                                try {
                                    JSONObject jSONObject3 = new JSONObject(str);
                                    int i3 = jSONObject3.getInt("err_code");
                                    UnAsrHelper.this.errorCode = i3;
                                    UnAsrHelper.this.errorMsg = jSONObject3.getString("err_msg");
                                    if (i3 != 0) {
                                        if (UnAsrHelper.this.mType != UnAsrType.TOUCH) {
                                            UnAsrHelper.this.listener.onError(i3, UnAsrHelper.this.errorMsg);
                                            return;
                                        }
                                        return;
                                    }
                                    JSONArray jSONArray2 = jSONObject3.getJSONArray("content");
                                    String string = jSONObject3.getString("request_id");
                                    OnOptListener onOptListener = UnAsrHelper.optListener;
                                    if (onOptListener != null) {
                                        onOptListener.clickMta("SearchVoice", "{\"request_id\":\"" + string + "\"}");
                                    }
                                    if (jSONArray2 != null && jSONArray2.length() != 0) {
                                        String str3 = "";
                                        while (i2 < jSONArray2.length()) {
                                            str3 = str3 + jSONArray2.getJSONObject(i2).getString("text");
                                            i2++;
                                        }
                                        if (!UnAsrHelper.this.allResultCallback) {
                                            UnAsrHelper.this.allResult = str3;
                                        } else {
                                            UnAsrHelper.this.allResult = UnAsrHelper.this.allResult + str3;
                                        }
                                        if (UnAsrHelper.this.mType == UnAsrType.TOUCH) {
                                            if (UnAsrHelper.this.isStop) {
                                                if (TextUtils.isEmpty(UnAsrHelper.this.allResult)) {
                                                    if (UnAsrHelper.this.listener != null) {
                                                        UnAsrHelper.this.listener.onError(i3, jSONObject3.getString("err_msg"));
                                                        return;
                                                    }
                                                    return;
                                                }
                                                if (UnAsrHelper.this.listener != null) {
                                                    UnAsrHelper.this.listener.onResult(UnAsrHelper.this.allResult);
                                                }
                                                UnAsrHelper.this.allResult = "";
                                                return;
                                            }
                                            return;
                                        } else if (UnAsrHelper.this.listener != null) {
                                            UnAsrHelper.this.listener.onResult(UnAsrHelper.this.allResult);
                                            return;
                                        } else {
                                            return;
                                        }
                                    }
                                    if (UnAsrHelper.this.listener != null) {
                                        UnAsrHelper.this.listener.onResult("");
                                    }
                                } catch (JSONException e4) {
                                    e4.printStackTrace();
                                    UnAsrHelper.this.listener.onError(-1, "\u672a\u77e5\u9519\u8bef");
                                }
                            }
                        }
                    }
                });
            }
        });
    }

    public void cancel() {
        d dVar = this.asrManager;
        if (dVar != null) {
            dVar.c("ASR.CANCEL", null);
        }
    }

    public UnAsrHelper isParitalResultEnable(boolean z) {
        this.paritalResultEnable = z;
        if (z) {
            this.config.setPartialResult(1);
        } else {
            this.config.setPartialResult(0);
        }
        return this;
    }

    public UnAsrHelper isTimeOutEnable(boolean z) {
        this.timeOutEnable = z;
        return this;
    }

    public UnAsrHelper setArsType(UnAsrType unAsrType) {
        this.mType = unAsrType;
        if (unAsrType == UnAsrType.LONG) {
            this.isSingle = false;
            this.configJson = this.config.longAsrConfig();
        } else if (unAsrType == UnAsrType.SINGLE) {
            this.isSingle = true;
            this.configJson = this.config.singleAsrConfig();
        } else if (unAsrType == UnAsrType.TOUCH) {
            this.isSingle = true;
            this.configJson = this.config.clickAsrConfig();
            this.allResultCallback = true;
        }
        return this;
    }

    public UnAsrHelper setOnSpeechListener(OnSpeechListener onSpeechListener) {
        this.listener = onSpeechListener;
        speechCallback();
        return this;
    }

    public UnAsrHelper setVoiceTimeOut(long j2) {
        this.voiceTimeOut = j2;
        return this;
    }

    public void start() {
        if (this.mType == UnAsrType.TOUCH) {
            this.allResult = "";
        }
        d dVar = this.asrManager;
        if (dVar != null) {
            dVar.c("ASR.START", this.configJson.toString());
        }
        this.isStop = false;
        this.isStartSpeek = false;
        this.errorCode = 0;
    }

    public void stop() {
        OnSpeechListener onSpeechListener;
        OnSpeechListener onSpeechListener2;
        OnSpeechListener onSpeechListener3;
        Handler handler = this.handler;
        if (handler != null) {
            handler.removeMessages(1);
        }
        d dVar = this.asrManager;
        if (dVar != null) {
            dVar.c("ASR.STOP", null);
        }
        this.isStop = true;
        if (this.mType == UnAsrType.TOUCH) {
            if (!this.isStartSpeek && this.config.isLocalVadEnable() && (onSpeechListener3 = this.listener) != null) {
                onSpeechListener3.onError(-1, "\u6ca1\u6709\u76d1\u542c\u5230\u8bf4\u8bdd");
                return;
            } else if (!TextUtils.isEmpty(this.allResult) && (onSpeechListener2 = this.listener) != null) {
                onSpeechListener2.onResult(this.allResult);
                return;
            } else {
                int i2 = this.errorCode;
                if (i2 != 0 && (onSpeechListener = this.listener) != null) {
                    onSpeechListener.onError(i2, this.errorMsg);
                    return;
                }
            }
        }
        this.handler.postDelayed(new Runnable() { // from class: com.jd.lib.un.voice.asr.UnAsrHelper.3
            @Override // java.lang.Runnable
            public void run() {
                if (UnAsrHelper.this.listener != null) {
                    UnAsrHelper.this.listener.onEnd();
                }
            }
        }, 500L);
    }
}

package com.jd.lib.un.voice.asr;

/* loaded from: classes16.dex */
public interface OnSpeechListener {
    void onEnd();

    void onError(int i2, String str);

    void onPrepared();

    void onResult(String str);

    void onStartSpeech();

    void onTemp(String str);

    void onVolume(int i2);
}

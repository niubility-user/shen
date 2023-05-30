package com.jd.lib.un.voice.tts;

/* loaded from: classes16.dex */
public interface OnSpeechSynthesizeListener {
    void onError(String str, String str2);

    void onPlayFinish(String str);

    void onPlayPause(String str);

    void onPlayResume(String str);

    void onPlayStart(String str);

    void onSynthesizeFinish(String str);

    void onSynthesizeStart(String str);
}

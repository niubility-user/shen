package com.jd.wireless.sdk.intelligent.assistant;

/* loaded from: classes18.dex */
public interface ThirdPartySpeechSynthesizerListener {
    public static final byte SPEECH_SYNTHESIS_ERROR_CODE_NO_ERROR = 0;
    public static final byte SPEECH_SYNTHESIS_ERROR_CODE_UNKNOWN_ERROR = -127;

    void onBufferProgress(int i2, int i3, int i4, String str);

    void onCompleted(int i2);

    void onEvent(int i2, int i3, int i4);

    void onSpeakBegin();

    void onSpeakPaused();

    void onSpeakProgress(int i2, int i3, int i4);

    void onSpeakResumed();
}

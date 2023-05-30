package com.jd.wireless.sdk.intelligent.assistant;

/* loaded from: classes18.dex */
public interface ThirdPartySpeechRecognizerListener {
    public static final byte RECOGNITION_ERROR_CODE_NETWORK_ERROR = -2;
    public static final byte RECOGNITION_ERROR_CODE_THIRD_PARTY_ERROR = -3;
    public static final byte RECOGNITION_ERROR_CODE_UNKNOWN_ERROR = -127;

    void onEndOfSpeech();

    void onError(byte b);

    void onResult(String str, boolean z);

    void onRmsChanged(double d);

    void onStart();
}

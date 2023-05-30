package com.jd.wireless.sdk.intelligent.assistant;

/* loaded from: classes18.dex */
public interface IntelligentAssistanceCallBack {
    public static final byte ERROR_CODE_NOT_READY = 5;
    public static final byte ERROR_CODE_OUT_PUT_FILE_NOT_FIND = 1;
    public static final byte ERROR_CODE_TIME_SHORT = 3;
    public static final byte ERROR_CODE_WRITE_ERROR = 2;
    public static final byte ERROR_OCDE_NOT_DETECTED_VOICE = 6;
    public static final byte ERROR_OCDE_RECORD_DEVICE_INIT_FAIL = 4;
    public static final byte INIT_STATE_NORMAL = 0;
    public static final byte INIT_STATE_UNKNOWN_ERROR = -127;
    public static final byte RECOGNITION_ERROR_CODE_NETWORK_ERROR = -2;
    public static final byte RECOGNITION_ERROR_CODE_THIRD_PARTY_ERROR = -3;
    public static final byte RECOGNITION_ERROR_CODE_UNKNOWN_ERROR = -127;
    public static final byte RECOGNITION_ERROR_NOT_INIT_OR_INIT_ERROR = -1;
    public static final byte SPEECH_SYNTHESIS_ERROR_CODE_NO_ERROR = 0;
    public static final byte SPEECH_SYNTHESIS_ERROR_CODE_UNKNOWN_ERROR = -127;

    void changVolum(byte b);

    void initComplete(byte b);

    void phoneticRecognitionResult(String str, boolean z);

    void phoneticRecognitionStart();

    void recognitionError(byte b);

    void speakStop();

    void speechSynthesisStop(int i2);
}

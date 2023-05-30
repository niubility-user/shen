package com.jd.wireless.sdk.intelligent.assistant.audio.record;

@Deprecated
/* loaded from: classes18.dex */
public interface AudioRecordCallBack {
    public static final byte ERROR_CODE_NOT_READY = 5;
    public static final byte ERROR_CODE_OUT_PUT_FILE_NOT_FIND = 1;
    public static final byte ERROR_CODE_TIME_SHORT = 3;
    public static final byte ERROR_CODE_WRITE_ERROR = 2;
    public static final byte ERROR_OCDE_NOT_DETECTED_VOICE = 6;
    public static final byte ERROR_OCDE_RECORD_DEVICE_INIT_FAIL = 4;

    void changVolum(byte b);

    void recordError(byte b);

    void recordStop();
}

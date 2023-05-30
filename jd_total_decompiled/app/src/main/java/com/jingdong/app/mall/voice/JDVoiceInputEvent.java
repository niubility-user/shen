package com.jingdong.app.mall.voice;

/* loaded from: classes4.dex */
public class JDVoiceInputEvent {
    public static final String EVENT_TYPE_BEGIN = "event_type_jd_voice_input_on_begin";
    public static final String EVENT_TYPE_CREATE = "event_type_jd_voice_input_on_create";
    public static final String EVENT_TYPE_DESTROY = "event_type_jd_voice_input_on_destroy";
    public static final String EVENT_TYPE_DESTROY_AFTER = "event_type_jd_voice_input_on_destroy_after";
    public static final String EVENT_TYPE_END = "event_type_jd_voice_input_on_end";
    public static final String EVENT_TYPE_ERROR = "event_type_jd_voice_input_on_error";
    public static final String EVENT_TYPE_RESULT = "event_type_jd_voice_input_on_result";
    public static final String EVENT_TYPE_VOLUME = "event_type_jd_voice_input_on_volume";
    public String eventType;
    public String fromType;
    public boolean isLast;
    public String result;
    public int volume;

    public JDVoiceInputEvent(String str, String str2) {
        this.eventType = "";
        this.fromType = "";
        this.result = "";
        this.volume = 0;
        this.eventType = str;
        this.fromType = str2;
    }

    public JDVoiceInputEvent(String str, String str2, boolean z, String str3) {
        this.eventType = "";
        this.fromType = "";
        this.result = "";
        this.volume = 0;
        this.eventType = str;
        this.result = str2;
        this.isLast = z;
        this.fromType = str3;
    }

    public JDVoiceInputEvent(String str, int i2, String str2) {
        this.eventType = "";
        this.fromType = "";
        this.result = "";
        this.volume = 0;
        this.eventType = str;
        this.volume = i2;
        this.fromType = str2;
    }
}

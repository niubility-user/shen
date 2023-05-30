package com.jd.wireless.sdk.intelligent.assistant;

import com.jd.wireless.sdk.intelligent.assistant.audio.record.IAudioRecordCallBack;

/* loaded from: classes18.dex */
public class AudioRecordCallBack implements IAudioRecordCallBack {
    private static final String TAG = "AudioRecordCallBack";
    private IntelligentAssistance instance;
    private IntelligentAssistanceCallBack intelligentAssistanceCallBack;

    public AudioRecordCallBack(IntelligentAssistance intelligentAssistance, IntelligentAssistanceCallBack intelligentAssistanceCallBack) {
        this.instance = intelligentAssistance;
        this.intelligentAssistanceCallBack = intelligentAssistanceCallBack;
    }

    @Override // com.jd.wireless.sdk.intelligent.assistant.audio.record.AudioRecordCallBack
    public void changVolum(byte b) {
        this.intelligentAssistanceCallBack.changVolum(b);
    }

    @Override // com.jd.wireless.sdk.intelligent.assistant.audio.record.AudioRecordCallBack
    public void recordError(byte b) {
        this.intelligentAssistanceCallBack.recognitionError(b);
    }

    @Override // com.jd.wireless.sdk.intelligent.assistant.audio.record.AudioRecordCallBack
    public void recordStop() {
        this.instance.submitVoiceProceedRecognition();
    }
}

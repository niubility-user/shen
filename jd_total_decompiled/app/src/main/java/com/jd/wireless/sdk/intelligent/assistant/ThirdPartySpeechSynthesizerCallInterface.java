package com.jd.wireless.sdk.intelligent.assistant;

/* loaded from: classes18.dex */
public interface ThirdPartySpeechSynthesizerCallInterface {
    void cancelSpeechSynthesizer();

    void initThirdPartySpeechSynthesisEngine();

    void setThirdPartySpeechSynthesizerListener(ThirdPartySpeechSynthesizerListener thirdPartySpeechSynthesizerListener);

    void setVoiceName(String str);

    void startSpeechSynthesizer(String str);
}

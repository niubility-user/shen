package com.jd.wireless.sdk.intelligent.assistant;

/* loaded from: classes18.dex */
public interface ThirdPartySpeechRecognitionCallInterface {
    void cancelSpeak();

    void destroyThirdPartySpeechRecognitionEngine();

    String getVoiceSampleFormat();

    void initThirdPartySpeechRecognitionEngine(ThirdPartySpeechRecognitionConfig thirdPartySpeechRecognitionConfig);

    boolean isListening();

    void setThirdPartySpeechRecognizerListener(ThirdPartySpeechRecognizerListener thirdPartySpeechRecognizerListener);

    void setVoiceSavePath(String str);

    void startSpeak();

    void stopSpeak();
}

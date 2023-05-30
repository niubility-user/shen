package com.jd.wireless.sdk.intelligent.assistant;

/* loaded from: classes18.dex */
public class ExtendCallProxy implements ThirdPartySpeechRecognitionCallInterface, ThirdPartySpeechSynthesizerCallInterface {
    private static ExtendCallProxy callProxy;
    private ThirdPartySpeechRecognitionCallInterface thirdPartySpeechRecognitionCall;
    private ThirdPartySpeechSynthesizerCallInterface thirdPartySpeechSynthesizerCall;

    private ExtendCallProxy() {
    }

    public static void destroyInstance() {
        callProxy = null;
    }

    public static synchronized ExtendCallProxy getInstance() {
        ExtendCallProxy extendCallProxy;
        synchronized (ExtendCallProxy.class) {
            if (callProxy == null) {
                callProxy = new ExtendCallProxy();
            }
            extendCallProxy = callProxy;
        }
        return extendCallProxy;
    }

    @Override // com.jd.wireless.sdk.intelligent.assistant.ThirdPartySpeechRecognitionCallInterface
    public void cancelSpeak() {
        ThirdPartySpeechRecognitionCallInterface thirdPartySpeechRecognitionCallInterface = this.thirdPartySpeechRecognitionCall;
        if (thirdPartySpeechRecognitionCallInterface != null) {
            thirdPartySpeechRecognitionCallInterface.cancelSpeak();
        }
    }

    @Override // com.jd.wireless.sdk.intelligent.assistant.ThirdPartySpeechSynthesizerCallInterface
    public void cancelSpeechSynthesizer() {
        if (this.thirdPartySpeechRecognitionCall != null) {
            this.thirdPartySpeechSynthesizerCall.cancelSpeechSynthesizer();
        }
    }

    @Override // com.jd.wireless.sdk.intelligent.assistant.ThirdPartySpeechRecognitionCallInterface
    public void destroyThirdPartySpeechRecognitionEngine() {
        ThirdPartySpeechRecognitionCallInterface thirdPartySpeechRecognitionCallInterface = this.thirdPartySpeechRecognitionCall;
        if (thirdPartySpeechRecognitionCallInterface != null) {
            thirdPartySpeechRecognitionCallInterface.destroyThirdPartySpeechRecognitionEngine();
        }
    }

    @Override // com.jd.wireless.sdk.intelligent.assistant.ThirdPartySpeechRecognitionCallInterface
    public String getVoiceSampleFormat() {
        ThirdPartySpeechRecognitionCallInterface thirdPartySpeechRecognitionCallInterface = this.thirdPartySpeechRecognitionCall;
        if (thirdPartySpeechRecognitionCallInterface != null) {
            return thirdPartySpeechRecognitionCallInterface.getVoiceSampleFormat();
        }
        return null;
    }

    @Override // com.jd.wireless.sdk.intelligent.assistant.ThirdPartySpeechRecognitionCallInterface
    public void initThirdPartySpeechRecognitionEngine(ThirdPartySpeechRecognitionConfig thirdPartySpeechRecognitionConfig) {
        ThirdPartySpeechRecognitionCallInterface thirdPartySpeechRecognitionCallInterface = this.thirdPartySpeechRecognitionCall;
        if (thirdPartySpeechRecognitionCallInterface != null) {
            thirdPartySpeechRecognitionCallInterface.initThirdPartySpeechRecognitionEngine(thirdPartySpeechRecognitionConfig);
        }
    }

    @Override // com.jd.wireless.sdk.intelligent.assistant.ThirdPartySpeechSynthesizerCallInterface
    public void initThirdPartySpeechSynthesisEngine() {
        ThirdPartySpeechSynthesizerCallInterface thirdPartySpeechSynthesizerCallInterface = this.thirdPartySpeechSynthesizerCall;
        if (thirdPartySpeechSynthesizerCallInterface != null) {
            thirdPartySpeechSynthesizerCallInterface.initThirdPartySpeechSynthesisEngine();
        }
    }

    @Override // com.jd.wireless.sdk.intelligent.assistant.ThirdPartySpeechRecognitionCallInterface
    public boolean isListening() {
        ThirdPartySpeechRecognitionCallInterface thirdPartySpeechRecognitionCallInterface = this.thirdPartySpeechRecognitionCall;
        if (thirdPartySpeechRecognitionCallInterface != null) {
            return thirdPartySpeechRecognitionCallInterface.isListening();
        }
        return false;
    }

    public void registerThirdPartySpeechRecognitionCall(ThirdPartySpeechRecognitionCallInterface thirdPartySpeechRecognitionCallInterface, ThirdPartySpeechSynthesizerCallInterface thirdPartySpeechSynthesizerCallInterface) {
        this.thirdPartySpeechRecognitionCall = thirdPartySpeechRecognitionCallInterface;
        this.thirdPartySpeechSynthesizerCall = thirdPartySpeechSynthesizerCallInterface;
    }

    @Override // com.jd.wireless.sdk.intelligent.assistant.ThirdPartySpeechRecognitionCallInterface
    public void setThirdPartySpeechRecognizerListener(ThirdPartySpeechRecognizerListener thirdPartySpeechRecognizerListener) {
        ThirdPartySpeechRecognitionCallInterface thirdPartySpeechRecognitionCallInterface = this.thirdPartySpeechRecognitionCall;
        if (thirdPartySpeechRecognitionCallInterface != null) {
            thirdPartySpeechRecognitionCallInterface.setThirdPartySpeechRecognizerListener(thirdPartySpeechRecognizerListener);
        }
    }

    @Override // com.jd.wireless.sdk.intelligent.assistant.ThirdPartySpeechSynthesizerCallInterface
    public void setThirdPartySpeechSynthesizerListener(ThirdPartySpeechSynthesizerListener thirdPartySpeechSynthesizerListener) {
        ThirdPartySpeechSynthesizerCallInterface thirdPartySpeechSynthesizerCallInterface = this.thirdPartySpeechSynthesizerCall;
        if (thirdPartySpeechSynthesizerCallInterface != null) {
            thirdPartySpeechSynthesizerCallInterface.setThirdPartySpeechSynthesizerListener(thirdPartySpeechSynthesizerListener);
        }
    }

    @Override // com.jd.wireless.sdk.intelligent.assistant.ThirdPartySpeechSynthesizerCallInterface
    public void setVoiceName(String str) {
        ThirdPartySpeechSynthesizerCallInterface thirdPartySpeechSynthesizerCallInterface = this.thirdPartySpeechSynthesizerCall;
        if (thirdPartySpeechSynthesizerCallInterface != null) {
            thirdPartySpeechSynthesizerCallInterface.setVoiceName(str);
        }
    }

    @Override // com.jd.wireless.sdk.intelligent.assistant.ThirdPartySpeechRecognitionCallInterface
    public void setVoiceSavePath(String str) {
        ThirdPartySpeechRecognitionCallInterface thirdPartySpeechRecognitionCallInterface = this.thirdPartySpeechRecognitionCall;
        if (thirdPartySpeechRecognitionCallInterface != null) {
            thirdPartySpeechRecognitionCallInterface.setVoiceSavePath(str);
        }
    }

    @Override // com.jd.wireless.sdk.intelligent.assistant.ThirdPartySpeechRecognitionCallInterface
    public void startSpeak() {
        ThirdPartySpeechRecognitionCallInterface thirdPartySpeechRecognitionCallInterface = this.thirdPartySpeechRecognitionCall;
        if (thirdPartySpeechRecognitionCallInterface != null) {
            thirdPartySpeechRecognitionCallInterface.startSpeak();
        }
    }

    @Override // com.jd.wireless.sdk.intelligent.assistant.ThirdPartySpeechSynthesizerCallInterface
    public void startSpeechSynthesizer(String str) {
        ThirdPartySpeechSynthesizerCallInterface thirdPartySpeechSynthesizerCallInterface = this.thirdPartySpeechSynthesizerCall;
        if (thirdPartySpeechSynthesizerCallInterface != null) {
            thirdPartySpeechSynthesizerCallInterface.startSpeechSynthesizer(str);
        }
    }

    @Override // com.jd.wireless.sdk.intelligent.assistant.ThirdPartySpeechRecognitionCallInterface
    public void stopSpeak() {
        ThirdPartySpeechRecognitionCallInterface thirdPartySpeechRecognitionCallInterface = this.thirdPartySpeechRecognitionCall;
        if (thirdPartySpeechRecognitionCallInterface != null) {
            thirdPartySpeechRecognitionCallInterface.stopSpeak();
        }
    }
}

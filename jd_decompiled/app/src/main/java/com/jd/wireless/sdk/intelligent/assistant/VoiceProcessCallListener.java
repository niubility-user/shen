package com.jd.wireless.sdk.intelligent.assistant;

import com.jd.framework.json.JDJSON;
import com.jd.wireless.sdk.intelligent.assistant.bean.CallJdSpeechRecognitionServerResult;
import com.jd.wireless.sdk.intelligent.assistant.bean.JdSpeechRecognitionResult;
import com.jingdong.jdsdk.network.toolbox.HttpError;
import com.jingdong.jdsdk.network.toolbox.HttpGroup;
import com.jingdong.jdsdk.network.toolbox.HttpResponse;
import com.jingdong.sdk.oklog.OKLog;

/* loaded from: classes18.dex */
public class VoiceProcessCallListener implements HttpGroup.OnCommonListener {
    public static final String LOG_TAG = "VoiceProcessCallListener";
    private IntelligentAssistance instance;
    private IntelligentAssistanceCallBack intelligentAssistanceCallBack;

    public VoiceProcessCallListener(IntelligentAssistance intelligentAssistance, IntelligentAssistanceCallBack intelligentAssistanceCallBack) {
        this.instance = intelligentAssistance;
        this.intelligentAssistanceCallBack = intelligentAssistanceCallBack;
    }

    @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnEndListener
    public void onEnd(HttpResponse httpResponse) {
        JdSpeechRecognitionResult value = ((CallJdSpeechRecognitionServerResult) JDJSON.parseObject(httpResponse.getString(), CallJdSpeechRecognitionServerResult.class)).getValue();
        IntelligentAssistanceCallBack intelligentAssistanceCallBack = this.intelligentAssistanceCallBack;
        if (intelligentAssistanceCallBack != null) {
            intelligentAssistanceCallBack.phoneticRecognitionResult(value.getText(), true);
            this.instance.deleteVoiceFile(null);
        }
    }

    @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnErrorListener
    public void onError(HttpError httpError) {
        IntelligentAssistanceCallBack intelligentAssistanceCallBack = this.intelligentAssistanceCallBack;
        if (intelligentAssistanceCallBack != null) {
            intelligentAssistanceCallBack.recognitionError((byte) -2);
        }
    }

    @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnReadyListener
    public void onReady(HttpGroup.HttpSettingParams httpSettingParams) {
        OKLog.d(LOG_TAG, "onReady: ");
    }
}

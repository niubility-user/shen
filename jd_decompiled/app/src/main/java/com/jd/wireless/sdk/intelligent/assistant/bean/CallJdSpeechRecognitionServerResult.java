package com.jd.wireless.sdk.intelligent.assistant.bean;

import android.text.TextUtils;
import com.jd.framework.json.JDJSON;

/* loaded from: classes18.dex */
public class CallJdSpeechRecognitionServerResult {
    private String code;
    private JdSpeechRecognitionResult value;

    public String getCode() {
        return TextUtils.isEmpty(this.code) ? "0" : this.code;
    }

    public JdSpeechRecognitionResult getValue() {
        JdSpeechRecognitionResult jdSpeechRecognitionResult = this.value;
        return jdSpeechRecognitionResult == null ? new JdSpeechRecognitionResult() : jdSpeechRecognitionResult;
    }

    public void setCode(String str) {
        this.code = str;
    }

    public void setValue(String str) {
        this.value = (JdSpeechRecognitionResult) JDJSON.parseObject(str, JdSpeechRecognitionResult.class);
    }
}

package com.jd.lib.un.voice.asr;

import com.jd.lib.un.voice.VoiceConfig;
import com.jdpay.membercode.bean.CodeResultInfoBean;
import com.jingdong.common.messagecenter.MIPushMsg;
import com.jingdong.sdk.platform.business.personal.R2;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes16.dex */
public class UnAsrConfig {
    private String appId;
    private int sample = R2.id.rn_redbox_report_label;
    private String domain = "search";
    private boolean serverVadEnable = false;
    private String audioSource = "";
    private String appKey = "";
    private String secretKey = "";
    private boolean localVadEnable = true;
    private int postProcess = 1;
    private double vadStartDelay = 0.25d;
    private double vadEndDelay = 0.5d;
    private double vadEndDelayLongSpeech = 0.5d;
    private boolean longSpeech = false;
    private JSONArray phraseList = null;
    private int partialResult = 0;
    private String asrUrl = "https://ai-api.jd.com/asr";
    private String vadResPath = "assets://vad.bin";

    public UnAsrConfig() {
        this.appId = "";
        this.appId = VoiceConfig.getInstance().getId();
    }

    public JSONObject clickAsrConfig() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("POST_PROCESS", this.postProcess);
            jSONObject.put(MIPushMsg.APP_ID, this.appId);
            jSONObject.put("SAMPLE_RATE", this.sample);
            jSONObject.put("VAD_RES", this.vadResPath);
            jSONObject.put("LONG_SPEECH", false);
            jSONObject.put("PARTIAL_RESULT", this.partialResult);
            jSONObject.put("VAD_END_DELAY", this.vadEndDelay);
            jSONObject.put("LOCAL_VAD_ENABLE", this.localVadEnable);
            jSONObject.put(CodeResultInfoBean.NEXT_STEP_URL, this.asrUrl);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        return jSONObject;
    }

    public String getAppId() {
        return this.appId;
    }

    public String getAppKey() {
        return this.appKey;
    }

    public String getAsrUrl() {
        return this.asrUrl;
    }

    public String getAudioSource() {
        return this.audioSource;
    }

    public String getDomain() {
        return this.domain;
    }

    public int getPartialResult() {
        return this.partialResult;
    }

    public JSONArray getPhraseList() {
        return this.phraseList;
    }

    public int getPostProcess() {
        return this.postProcess;
    }

    public int getSample() {
        return this.sample;
    }

    public String getSecretKey() {
        return this.secretKey;
    }

    public double getVadEndDelay() {
        return this.vadEndDelay;
    }

    public double getVadEndDelayLongSpeech() {
        return this.vadEndDelayLongSpeech;
    }

    public String getVadResPath() {
        return this.vadResPath;
    }

    public double getVadStartDelay() {
        return this.vadStartDelay;
    }

    public boolean isLocalVadEnable() {
        return this.localVadEnable;
    }

    public boolean isLongSpeech() {
        return this.longSpeech;
    }

    public boolean isServerVadEnable() {
        return this.serverVadEnable;
    }

    public JSONObject longAsrConfig() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("POST_PROCESS", this.postProcess);
            jSONObject.put(MIPushMsg.APP_ID, this.appId);
            jSONObject.put("SAMPLE_RATE", this.sample);
            jSONObject.put("VAD_RES", this.vadResPath);
            jSONObject.put("LONG_SPEECH", true);
            jSONObject.put("VAD_END_DELAY", this.vadEndDelayLongSpeech);
            jSONObject.put("PARTIAL_RESULT", this.partialResult);
            jSONObject.put("LOCAL_VAD_ENABLE", this.localVadEnable);
            jSONObject.put("PUNC_PROCESS", 1);
            jSONObject.put(CodeResultInfoBean.NEXT_STEP_URL, this.asrUrl);
            jSONObject.put("PUNC_END_PROCESS", 1);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        return jSONObject;
    }

    public void setAppId(String str) {
        this.appId = str;
    }

    public void setAppKey(String str) {
        this.appKey = str;
    }

    public void setAsrUrl(String str) {
        this.asrUrl = str;
    }

    public void setAudioSource(String str) {
        this.audioSource = str;
    }

    public void setDomain(String str) {
        this.domain = str;
    }

    public void setLocalVadEnable(boolean z) {
        this.localVadEnable = z;
    }

    public void setLongSpeech(boolean z) {
        this.longSpeech = z;
    }

    public void setPartialResult(int i2) {
        this.partialResult = i2;
    }

    public void setPhraseList(JSONArray jSONArray) {
        this.phraseList = jSONArray;
    }

    public void setPostProcess(int i2) {
        this.postProcess = i2;
    }

    public void setSample(int i2) {
        this.sample = i2;
    }

    public void setSecretKey(String str) {
        this.secretKey = str;
    }

    public void setServerVadEnable(boolean z) {
        this.serverVadEnable = z;
    }

    public void setVadEndDelay(double d) {
        this.vadEndDelay = d;
    }

    public void setVadEndDelayLongSpeech(double d) {
        this.vadEndDelayLongSpeech = d;
    }

    public void setVadResPath(String str) {
        this.vadResPath = str;
    }

    public void setVadStartDelay(double d) {
        this.vadStartDelay = d;
    }

    public JSONObject singleAsrConfig() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("POST_PROCESS", this.postProcess);
            jSONObject.put(MIPushMsg.APP_ID, this.appId);
            jSONObject.put("SAMPLE_RATE", this.sample);
            jSONObject.put("VAD_RES", this.vadResPath);
            jSONObject.put("LONG_SPEECH", false);
            jSONObject.put("VAD_END_DELAY", this.vadEndDelay);
            jSONObject.put("LOCAL_VAD_ENABLE", this.localVadEnable);
            jSONObject.put("PARTIAL_RESULT", this.partialResult);
            jSONObject.put(CodeResultInfoBean.NEXT_STEP_URL, this.asrUrl);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        return jSONObject;
    }

    public UnAsrConfig(String str) {
        this.appId = "";
        this.appId = str;
    }
}

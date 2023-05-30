package com.jd.wireless.sdk.intelligent.assistant;

import com.jingdong.app.mall.voice.JDVoiceConstant;
import com.jingdong.common.entity.settlement.NewFillOrderConstant;
import org.json.JSONObject;
import tv.danmaku.ijk.media.player.IjkMediaMeta;

/* loaded from: classes18.dex */
public class ThirdPartySpeechRecognitionConfig {
    public static final byte RESULTS_RETURN_MODEL_FINISH = 1;
    public static final byte RESULTS_RETURN_MODEL_INSTANT = 0;
    public static final byte SERVICE_PROVIDER_IFLYTEK = 2;
    public static final int UPLOAD_AUDIO_DATA_STATE_UPLOAD = 1;
    private String accent;
    private String bit;
    private String domain;
    private boolean isUploadAudioData;
    private String language;
    private String postpositionSilenceMaxDuration;
    private String prepositionSilenceMaxDuration;
    private boolean punctuationEnable;
    private boolean reserveSpaceEnable;
    private byte resultsReturnModel;
    private String samplingRate;
    private String source;
    private String speechMaxDuration;
    private long speechMinDuration;
    private byte speechRecognitionServeProvider;

    public static final ThirdPartySpeechRecognitionConfig parseConfig(String str) {
        ThirdPartySpeechRecognitionConfig thirdPartySpeechRecognitionConfig = new ThirdPartySpeechRecognitionConfig();
        JSONObject jSONObject = new JSONObject(str);
        thirdPartySpeechRecognitionConfig.setLanguage(jSONObject.optString(IjkMediaMeta.IJKM_KEY_LANGUAGE, JDVoiceConstant.LANGUAGE_ZH_CN));
        thirdPartySpeechRecognitionConfig.setDomain(jSONObject.optString("domain", JDVoiceConstant.DOMAIN_IAT));
        thirdPartySpeechRecognitionConfig.setAccent(jSONObject.optString("accent", JDVoiceConstant.ACCENT_MANDARIN));
        thirdPartySpeechRecognitionConfig.setPrepositionSilenceMaxDuration(jSONObject.optString("vadBos", "0"));
        thirdPartySpeechRecognitionConfig.setPostpositionSilenceMaxDuration(jSONObject.optString("vadEos", "0"));
        thirdPartySpeechRecognitionConfig.setSamplingRate(jSONObject.optString(NewFillOrderConstant.RATE, "16000"));
        thirdPartySpeechRecognitionConfig.setBit(jSONObject.optString("bit", "8"));
        thirdPartySpeechRecognitionConfig.setSpeechMaxDuration(jSONObject.optString("maxTime", "0"));
        thirdPartySpeechRecognitionConfig.setSpeechMinDuration(jSONObject.optString("minTime", "0"));
        thirdPartySpeechRecognitionConfig.setPunctuationEnable(jSONObject.optString("asrPtt", "0"));
        thirdPartySpeechRecognitionConfig.setSpeechRecognitionServeProvider(jSONObject.optString("type", "0"));
        thirdPartySpeechRecognitionConfig.setResultsReturnModel(jSONObject.optString("resultsReturnModel", "0"));
        if (1 == jSONObject.optInt("isUploadAudioData")) {
            thirdPartySpeechRecognitionConfig.setUploadAudioData(true);
        } else {
            thirdPartySpeechRecognitionConfig.setUploadAudioData(false);
        }
        return thirdPartySpeechRecognitionConfig;
    }

    public String getAccent() {
        return this.accent;
    }

    public String getBit() {
        return this.bit;
    }

    public String getDomain() {
        return this.domain;
    }

    public String getLanguage() {
        return this.language;
    }

    public String getPostpositionSilenceMaxDuration() {
        return this.postpositionSilenceMaxDuration;
    }

    public String getPrepositionSilenceMaxDuration() {
        return this.prepositionSilenceMaxDuration;
    }

    public byte getResultsReturnModel() {
        return this.resultsReturnModel;
    }

    public String getSamplingRate() {
        return this.samplingRate;
    }

    public String getSource() {
        return this.source;
    }

    public String getSpeechMaxDuration() {
        return this.speechMaxDuration;
    }

    public long getSpeechMinDuration() {
        return this.speechMinDuration;
    }

    public byte getSpeechRecognitionServeProvider() {
        return this.speechRecognitionServeProvider;
    }

    public boolean isPunctuationEnable() {
        return this.punctuationEnable;
    }

    public boolean isReserveSpaceEnable() {
        return this.reserveSpaceEnable;
    }

    public boolean isUploadAudioData() {
        return this.isUploadAudioData;
    }

    public void setAccent(String str) {
        this.accent = str;
    }

    public void setBit(String str) {
        this.bit = str;
    }

    public void setDomain(String str) {
        this.domain = str;
    }

    public void setLanguage(String str) {
        this.language = str;
    }

    public void setPostpositionSilenceMaxDuration(String str) {
        this.postpositionSilenceMaxDuration = String.valueOf((int) (Float.parseFloat(str) * 1000.0f));
    }

    public void setPrepositionSilenceMaxDuration(String str) {
        this.prepositionSilenceMaxDuration = String.valueOf((int) (Float.parseFloat(str) * 1000.0f));
    }

    public void setPunctuationEnable(String str) {
        this.punctuationEnable = Integer.parseInt(str) != 0;
    }

    public void setReserveSpaceEnable(boolean z) {
        this.reserveSpaceEnable = z;
    }

    public void setResultsReturnModel(String str) {
        if (str != null && str.length() != 0 && Byte.parseByte(str) == 0) {
            this.resultsReturnModel = (byte) 0;
        } else {
            this.resultsReturnModel = (byte) 1;
        }
    }

    public void setSamplingRate(String str) {
        this.samplingRate = str;
    }

    public void setSource(String str) {
        this.source = str;
    }

    public void setSpeechMaxDuration(String str) {
        this.speechMaxDuration = String.valueOf((int) (Float.parseFloat(str) * 1000.0f));
    }

    public void setSpeechMinDuration(String str) {
        this.speechMinDuration = Float.parseFloat(str) * 1000.0f;
    }

    public void setSpeechRecognitionServeProvider(String str) {
        this.speechRecognitionServeProvider = Byte.parseByte(str);
    }

    public void setUploadAudioData(boolean z) {
        this.isUploadAudioData = z;
    }
}

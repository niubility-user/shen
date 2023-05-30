package com.jd.wireless.sdk.intelligent.assistant.audio.record;

import com.jingdong.app.mall.bundle.order_center_isv_core.util.OrderISVUtil;
import com.jingdong.common.entity.settlement.NewFillOrderConstant;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes18.dex */
public class RecordHandler implements Constant {
    public static final Byte SAMPLE_DIGIT_8 = (byte) 8;
    private AmrRecord amrRecord;
    private IAudioRecordCallBack audioRecordCallBack;
    private ExecutorService executorService;
    private String format;
    private byte maxDuration = Constant.MAX_DURATION_DEFAULT;
    private byte minimumDuration = 0;
    private String outputPath;
    private PcmRecord pcmRecord;
    private int sampleRate;

    public RecordHandler(String str, int i2, String str2, IAudioRecordCallBack iAudioRecordCallBack) {
        this.format = str;
        this.sampleRate = i2;
        commonalityConstruction(str2, iAudioRecordCallBack);
    }

    private void commonalityConstruction(String str, IAudioRecordCallBack iAudioRecordCallBack) {
        if (this.format.equalsIgnoreCase(Constant.FORMAT_AMR)) {
            this.outputPath = str + OrderISVUtil.MONEY_DECIMAL + this.format;
        } else {
            this.outputPath = str + ".pcm";
        }
        if (iAudioRecordCallBack != null) {
            this.audioRecordCallBack = iAudioRecordCallBack;
            this.executorService = Executors.newSingleThreadExecutor();
            return;
        }
        throw new IllegalArgumentException();
    }

    public String getFormat() {
        return this.format;
    }

    public int getSampleRate() {
        PcmRecord pcmRecord = this.pcmRecord;
        if (pcmRecord != null) {
            return pcmRecord.getSampleRate();
        }
        return 0;
    }

    public void prepare() {
        if (this.format.equalsIgnoreCase(Constant.FORMAT_AMR)) {
            this.amrRecord = new AmrRecord(this.audioRecordCallBack, this.outputPath, this.maxDuration, this.minimumDuration);
        } else {
            this.pcmRecord = new PcmRecord(this.sampleRate, this.maxDuration, this.outputPath, this.minimumDuration, this.audioRecordCallBack);
        }
    }

    public void setMaxDuration(byte b) {
        if (b >= 60 || b <= 0) {
            return;
        }
        this.maxDuration = b;
    }

    public void setMinimumDuration(byte b) {
        if (b >= 10 || b <= 0) {
            return;
        }
        this.minimumDuration = b;
    }

    public void startRecord() {
        AmrRecord amrRecord = this.amrRecord;
        if (amrRecord == null && this.pcmRecord == null) {
            this.audioRecordCallBack.recordError((byte) 5);
            return;
        }
        if (amrRecord != null) {
            amrRecord.startRecord();
        }
        PcmRecord pcmRecord = this.pcmRecord;
        if (pcmRecord != null) {
            this.executorService.execute(pcmRecord);
        }
    }

    public void stopRecord() {
        AmrRecord amrRecord = this.amrRecord;
        if (amrRecord != null) {
            amrRecord.stopRecord();
        }
        PcmRecord pcmRecord = this.pcmRecord;
        if (pcmRecord != null) {
            pcmRecord.stopRecord();
        }
    }

    public RecordHandler(String str, String str2, IAudioRecordCallBack iAudioRecordCallBack) throws JSONException {
        JSONObject jSONObject = new JSONObject(str);
        this.format = jSONObject.optString("format");
        this.sampleRate = Integer.parseInt(jSONObject.optString(NewFillOrderConstant.RATE, "8000"));
        commonalityConstruction(str2, iAudioRecordCallBack);
        setMaxDuration(Byte.parseByte(jSONObject.optString("maxTime", "30")));
        setMinimumDuration(Byte.parseByte(jSONObject.optString("minTime", "1")));
    }
}

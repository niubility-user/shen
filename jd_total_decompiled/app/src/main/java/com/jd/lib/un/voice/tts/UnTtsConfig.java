package com.jd.lib.un.voice.tts;

import com.jd.cashier.app.jdlibcutter.protocol.pair.PairKey;
import com.jd.lib.un.voice.VoiceConfig;
import com.meizu.cloud.pushsdk.constants.PushConstants;
import g.g.a.n;

/* loaded from: classes16.dex */
public class UnTtsConfig {
    private String appS;
    private String key;
    private n ttsParam;
    private String vol;
    private String tim = "0";
    public String TIM_TAOTAO_W = "0";
    public String TIM_BINBIN_F = "1";
    public String TIM_TINGTING_W = "3";

    public UnTtsConfig() {
        this.key = "";
        this.appS = "";
        this.key = VoiceConfig.getInstance().getTtsKey();
        this.appS = VoiceConfig.getInstance().getTtsS();
    }

    private n defaultConfig() {
        n nVar = new n();
        nVar.b("aue", "1");
        nVar.b("sr", "16000");
        nVar.b("serverURL", "https://aiapi.jd.com/jdai/tts_vip");
        nVar.b(PairKey.APP_KEY, this.key);
        nVar.b("appSecret", this.appS);
        nVar.b("CustomerType", "0");
        nVar.b("tte", "1");
        nVar.b("tim", this.tim);
        nVar.b("vol", "10");
        nVar.b("sp", "1.0");
        nVar.b("streamMode", "1");
        nVar.b(PushConstants.PUSH_NOTIFICATION_CREATE_TIMES_TAMP, "0");
        nVar.b("ttsModel", "taotao.dat");
        nVar.b("connectTimeout", "3000");
        nVar.b("readTimeout", "5000");
        nVar.b("playCacheNum", "0");
        nVar.b("httpProtocols", "http1");
        return nVar;
    }

    public n getTtsParam() {
        if (this.ttsParam == null) {
            this.ttsParam = defaultConfig();
        }
        return this.ttsParam;
    }

    public void setTim(String str) {
        this.tim = str;
    }

    public void setVol(String str) {
        this.vol = str;
    }

    public UnTtsConfig(String str, String str2) {
        this.key = "";
        this.appS = "";
        this.key = str;
        this.appS = str2;
    }
}

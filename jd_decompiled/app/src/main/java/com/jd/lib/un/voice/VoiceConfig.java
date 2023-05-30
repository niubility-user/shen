package com.jd.lib.un.voice;

/* loaded from: classes.dex */
public class VoiceConfig {
    private static VoiceConfig config;
    private String id = "";
    private String ttsKey;
    private String ttsS;

    private VoiceConfig() {
    }

    public static VoiceConfig getInstance() {
        VoiceConfig voiceConfig;
        VoiceConfig voiceConfig2 = config;
        if (voiceConfig2 != null) {
            return voiceConfig2;
        }
        synchronized (VoiceConfig.class) {
            if (config == null) {
                config = new VoiceConfig();
            }
            voiceConfig = config;
        }
        return voiceConfig;
    }

    public String getId() {
        return this.id;
    }

    public String getTtsKey() {
        return this.ttsKey;
    }

    public String getTtsS() {
        return this.ttsS;
    }

    public VoiceConfig setId(String str) {
        this.id = str;
        return this;
    }

    public VoiceConfig setTtsKey(String str) {
        this.ttsKey = str;
        return this;
    }

    public VoiceConfig setTtsS(String str) {
        this.ttsS = str;
        return this;
    }
}

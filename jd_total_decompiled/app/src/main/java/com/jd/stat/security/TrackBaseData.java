package com.jd.stat.security;

/* loaded from: classes18.dex */
public class TrackBaseData {
    private String aid;
    private boolean debug;
    private String deviceCode;
    private boolean enableLog;
    private InfoCollectHelper infoCollectHelper;
    private String installtionid;
    private String oaid;
    private String partner;
    private String pin;
    private PrivacyHelper privacyHelper;
    private String subunionId;
    private int testFlag;
    private String unionId;
    private boolean useRemoteConfig;
    private WSKeyHelper wsKeyHelper;

    /* loaded from: classes18.dex */
    public static class TrackBaseDataBuilder {
        private String aid;
        private boolean debug;
        private String deviceCode;
        private boolean enableLog;
        private InfoCollectHelper infoCollectHelper;
        private String installtionid;
        private String oaid;
        private String partner;
        private String pin;
        private PrivacyHelper privacyHelper;
        private boolean remoteConfig;
        private String subunionId;
        private int testFlag;
        private String unionId;
        private WSKeyHelper wsKeyHelper;

        public TrackBaseDataBuilder aid(String str) {
            this.aid = str;
            return this;
        }

        public TrackBaseData build() {
            return new TrackBaseData(this);
        }

        public TrackBaseDataBuilder debug(boolean z) {
            this.debug = z;
            return this;
        }

        public TrackBaseDataBuilder deviceCode(String str) {
            this.deviceCode = str;
            return this;
        }

        public TrackBaseDataBuilder enableLog(boolean z) {
            this.enableLog = z;
            return this;
        }

        public TrackBaseDataBuilder infoCollectHelper(InfoCollectHelper infoCollectHelper) {
            this.infoCollectHelper = infoCollectHelper;
            return this;
        }

        public TrackBaseDataBuilder installtionid(String str) {
            this.installtionid = str;
            return this;
        }

        public TrackBaseDataBuilder oaid(String str) {
            this.oaid = str;
            return this;
        }

        public TrackBaseDataBuilder partner(String str) {
            this.partner = str;
            return this;
        }

        @Deprecated
        public TrackBaseDataBuilder pin(String str) {
            this.pin = str;
            return this;
        }

        public TrackBaseDataBuilder privacyHelper(PrivacyHelper privacyHelper) {
            this.privacyHelper = privacyHelper;
            return this;
        }

        public TrackBaseDataBuilder subunionId(String str) {
            this.subunionId = str;
            return this;
        }

        public TrackBaseDataBuilder testFlag(int i2) {
            this.testFlag = i2;
            return this;
        }

        public TrackBaseDataBuilder unionId(String str) {
            this.unionId = str;
            return this;
        }

        public TrackBaseDataBuilder useRemoteConfig(boolean z) {
            this.remoteConfig = z;
            return this;
        }

        public TrackBaseDataBuilder wsKeyHelper(WSKeyHelper wSKeyHelper) {
            this.wsKeyHelper = wSKeyHelper;
            return this;
        }
    }

    public String getAid() {
        return this.aid;
    }

    public String getDeviceCode() {
        return this.deviceCode;
    }

    public InfoCollectHelper getInfoCollectHelper() {
        return this.infoCollectHelper;
    }

    public String getInstalltionid() {
        return this.installtionid;
    }

    public String getOaid() {
        return this.oaid;
    }

    public String getPartner() {
        return this.partner;
    }

    public String getPin() {
        return this.pin;
    }

    public PrivacyHelper getPrivacyHelper() {
        return this.privacyHelper;
    }

    public String getSubunionId() {
        return this.subunionId;
    }

    public int getTestFlag() {
        return this.testFlag;
    }

    public String getUnionId() {
        return this.unionId;
    }

    public WSKeyHelper getWsKeyHelper() {
        return this.wsKeyHelper;
    }

    public boolean isDebug() {
        return this.debug;
    }

    public boolean isEnableLog() {
        return this.enableLog;
    }

    public boolean useRemoteConfig() {
        return this.useRemoteConfig;
    }

    private TrackBaseData(TrackBaseDataBuilder trackBaseDataBuilder) {
        this.enableLog = false;
        this.debug = false;
        this.deviceCode = trackBaseDataBuilder.deviceCode;
        this.unionId = trackBaseDataBuilder.unionId;
        this.subunionId = trackBaseDataBuilder.subunionId;
        this.partner = trackBaseDataBuilder.partner;
        this.pin = trackBaseDataBuilder.pin;
        this.oaid = trackBaseDataBuilder.oaid;
        this.installtionid = trackBaseDataBuilder.installtionid;
        this.useRemoteConfig = trackBaseDataBuilder.remoteConfig;
        this.privacyHelper = trackBaseDataBuilder.privacyHelper;
        this.aid = trackBaseDataBuilder.aid;
        this.enableLog = trackBaseDataBuilder.enableLog;
        this.debug = trackBaseDataBuilder.debug;
        this.infoCollectHelper = trackBaseDataBuilder.infoCollectHelper;
        this.wsKeyHelper = trackBaseDataBuilder.wsKeyHelper;
        this.testFlag = trackBaseDataBuilder.testFlag;
    }
}

package com.jd.fireeye.security;

import com.jd.fireeye.security.fireeye.IMtaUtils;

/* loaded from: classes13.dex */
public class FireEyeBaseData {
    private String appKey;
    private boolean appSwitch;
    private boolean clipSwitch;
    private String deviceCode;
    private String h5Switch;
    private IMtaUtils iMtaUtils;
    private String installtionid;
    private boolean mtaSwitch;
    private String oaId;
    private String partner;
    private String publicKey;
    private String subunionId;
    private String unionId;

    /* loaded from: classes13.dex */
    public static class TrackBaseDataBuilder {
        private IMtaUtils iMtaUtils;
        private String deviceCode = "";
        private String unionId = "";
        private String subunionId = "";
        private String partner = "";
        private String installtionid = "";
        private String oaId = "";
        private String appKey = "";
        private String publicKey = "";
        private String h5Switch = "";
        private boolean appSwitch = true;
        private boolean clipSwitch = false;
        private boolean mtaSwitch = true;

        public TrackBaseDataBuilder appKey(String str) {
            this.appKey = str;
            return this;
        }

        public TrackBaseDataBuilder appSwitch(boolean z) {
            this.appSwitch = z;
            return this;
        }

        public FireEyeBaseData build() {
            return new FireEyeBaseData(this);
        }

        public TrackBaseDataBuilder clipSwitch(boolean z) {
            this.clipSwitch = z;
            return this;
        }

        public TrackBaseDataBuilder deviceCode(String str) {
            this.deviceCode = str;
            return this;
        }

        public TrackBaseDataBuilder h5Switch(String str) {
            this.h5Switch = str;
            return this;
        }

        public TrackBaseDataBuilder iMtaUtils(IMtaUtils iMtaUtils) {
            this.iMtaUtils = iMtaUtils;
            return this;
        }

        public TrackBaseDataBuilder installtionid(String str) {
            this.installtionid = str;
            return this;
        }

        public TrackBaseDataBuilder mtaSwitch(boolean z) {
            this.mtaSwitch = z;
            return this;
        }

        public TrackBaseDataBuilder oaId(String str) {
            this.oaId = str;
            return this;
        }

        public TrackBaseDataBuilder partner(String str) {
            this.partner = str;
            return this;
        }

        public TrackBaseDataBuilder publicKey(String str) {
            this.publicKey = str;
            return this;
        }

        public TrackBaseDataBuilder subunionId(String str) {
            this.subunionId = str;
            return this;
        }

        public TrackBaseDataBuilder unionId(String str) {
            this.unionId = str;
            return this;
        }
    }

    public String getAppKey() {
        return this.appKey;
    }

    public String getDeviceCode() {
        return this.deviceCode;
    }

    public String getH5Switch() {
        return this.h5Switch;
    }

    public String getInstalltionid() {
        return this.installtionid;
    }

    public String getOaId() {
        return this.oaId;
    }

    public String getPartner() {
        return this.partner;
    }

    public String getPublicKey() {
        return this.publicKey;
    }

    public String getSubunionId() {
        return this.subunionId;
    }

    public String getUnionId() {
        return this.unionId;
    }

    public IMtaUtils getiMtaUtils() {
        return this.iMtaUtils;
    }

    public boolean isAppSwitch() {
        return this.appSwitch;
    }

    public boolean isClipSwitch() {
        return this.clipSwitch;
    }

    public boolean isMtaSwitch() {
        return this.mtaSwitch;
    }

    private FireEyeBaseData(TrackBaseDataBuilder trackBaseDataBuilder) {
        this.deviceCode = trackBaseDataBuilder.deviceCode;
        this.unionId = trackBaseDataBuilder.unionId;
        this.subunionId = trackBaseDataBuilder.subunionId;
        this.partner = trackBaseDataBuilder.partner;
        this.installtionid = trackBaseDataBuilder.installtionid;
        this.oaId = trackBaseDataBuilder.oaId;
        this.appKey = trackBaseDataBuilder.appKey;
        this.publicKey = trackBaseDataBuilder.publicKey;
        this.h5Switch = trackBaseDataBuilder.h5Switch;
        this.appSwitch = trackBaseDataBuilder.appSwitch;
        this.clipSwitch = trackBaseDataBuilder.clipSwitch;
        this.mtaSwitch = trackBaseDataBuilder.mtaSwitch;
        this.iMtaUtils = trackBaseDataBuilder.iMtaUtils;
    }
}

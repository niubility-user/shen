package com.jingdong.jdma;

import com.jingdong.jdma.minterface.HttpDns;
import com.jingdong.jdma.minterface.ISwitchQuery;
import com.jingdong.jdma.minterface.JDMABaseInfo;

/* loaded from: classes12.dex */
public class JDMAConfig {
    public static final String ANDROID = "ANDROID";
    public static final String ANDROID_HD = "ANDROID-HD";
    public static final String IOS = "IOS";
    public static final String IOS_HD = "IOS-HD";
    private String installationId;
    private boolean isDataEncrypted;
    private String mAppBuildId;
    private String mAppDevice;
    private String mAppVersionCode;
    private String mAppVersionName;
    private String mChannel;
    private String mH5Url;
    private HttpDns mHttpDns;
    private ISwitchQuery mISwitchQuery;
    private JDMABaseInfo mJdmaBaseInfo;
    private String mProjectId;
    private String mReportDomain;
    private String mSiteId;
    private String mStrategyDomain;
    private String mUid;

    /* loaded from: classes12.dex */
    public static class JDMAConfigBuilder {
        private String installationId;
        private boolean isDataEncrypted = true;
        private String mAppBuildId;
        private String mAppDevice;
        private String mAppVersionCode;
        private String mAppVersionName;
        private String mChannel;
        private String mH5Url;
        private HttpDns mHttpDns;
        private ISwitchQuery mISwitchQuery;
        private JDMABaseInfo mJDMABaseInfo;
        private String mProjectId;
        private String mReportDomain;
        private String mSiteId;
        private String mStrategyDomain;
        private String mUid;

        public JDMAConfigBuilder JDMABaseInfo(JDMABaseInfo jDMABaseInfo) {
            this.mJDMABaseInfo = jDMABaseInfo;
            return this;
        }

        public JDMAConfigBuilder appBuildId(String str) {
            this.mAppBuildId = str;
            return this;
        }

        public JDMAConfigBuilder appDevice(String str) {
            this.mAppDevice = str;
            return this;
        }

        public JDMAConfigBuilder appVersionCode(String str) {
            this.mAppVersionCode = str;
            return this;
        }

        public JDMAConfigBuilder appVersionName(String str) {
            this.mAppVersionName = str;
            return this;
        }

        public JDMAConfig build() {
            return new JDMAConfig(this);
        }

        public JDMAConfigBuilder channel(String str) {
            this.mChannel = str;
            return this;
        }

        public JDMAConfigBuilder h5Url(String str) {
            this.mH5Url = str;
            return this;
        }

        public JDMAConfigBuilder httpDns(HttpDns httpDns) {
            this.mHttpDns = httpDns;
            return this;
        }

        public JDMAConfigBuilder installationId(String str) {
            this.installationId = str;
            return this;
        }

        public JDMAConfigBuilder isDataEncrypted(boolean z) {
            this.isDataEncrypted = z;
            return this;
        }

        public JDMAConfigBuilder projectId(String str) {
            this.mProjectId = str;
            return this;
        }

        public JDMAConfigBuilder reportDomain(String str) {
            this.mReportDomain = str;
            return this;
        }

        public JDMAConfigBuilder siteId(String str) {
            this.mSiteId = str;
            return this;
        }

        public JDMAConfigBuilder strategyDomain(String str) {
            this.mStrategyDomain = str;
            return this;
        }

        public JDMAConfigBuilder switchQuery(ISwitchQuery iSwitchQuery) {
            this.mISwitchQuery = iSwitchQuery;
            return this;
        }

        public JDMAConfigBuilder uid(String str) {
            this.mUid = str;
            return this;
        }
    }

    public String getAppBuildId() {
        return this.mAppBuildId;
    }

    public String getAppDevice() {
        return this.mAppDevice;
    }

    public String getAppVersionCode() {
        return this.mAppVersionCode;
    }

    public String getAppVersionName() {
        return this.mAppVersionName;
    }

    public String getChannel() {
        return this.mChannel;
    }

    public String getH5Url() {
        return this.mH5Url;
    }

    public HttpDns getHttpDns() {
        return this.mHttpDns;
    }

    public String getInstallationId() {
        return this.installationId;
    }

    public JDMABaseInfo getJdmaBaseInfo() {
        return this.mJdmaBaseInfo;
    }

    public String getProjectId() {
        return this.mProjectId;
    }

    public String getReportDomain() {
        return this.mReportDomain;
    }

    public String getSiteId() {
        return this.mSiteId;
    }

    public String getStrategyDomain() {
        return this.mStrategyDomain;
    }

    public ISwitchQuery getSwitchQuery() {
        return this.mISwitchQuery;
    }

    public String getUid() {
        return this.mUid;
    }

    public boolean isDataEncrypted() {
        return this.isDataEncrypted;
    }

    private JDMAConfig(JDMAConfigBuilder jDMAConfigBuilder) {
        this.isDataEncrypted = true;
        this.installationId = "";
        this.mUid = jDMAConfigBuilder.mUid;
        this.mSiteId = jDMAConfigBuilder.mSiteId;
        this.mAppDevice = jDMAConfigBuilder.mAppDevice;
        this.mChannel = jDMAConfigBuilder.mChannel;
        this.mProjectId = jDMAConfigBuilder.mProjectId;
        this.mAppVersionName = jDMAConfigBuilder.mAppVersionName;
        this.mAppVersionCode = jDMAConfigBuilder.mAppVersionCode;
        this.mAppBuildId = jDMAConfigBuilder.mAppBuildId;
        this.mStrategyDomain = jDMAConfigBuilder.mStrategyDomain;
        this.mReportDomain = jDMAConfigBuilder.mReportDomain;
        this.isDataEncrypted = jDMAConfigBuilder.isDataEncrypted;
        this.installationId = jDMAConfigBuilder.installationId;
        this.mHttpDns = jDMAConfigBuilder.mHttpDns;
        this.mJdmaBaseInfo = jDMAConfigBuilder.mJDMABaseInfo;
        this.mISwitchQuery = jDMAConfigBuilder.mISwitchQuery;
        this.mH5Url = jDMAConfigBuilder.mH5Url;
    }
}

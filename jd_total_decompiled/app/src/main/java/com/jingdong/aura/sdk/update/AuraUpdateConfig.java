package com.jingdong.aura.sdk.update;

import android.content.Context;
import android.text.TextUtils;
import com.jingdong.aura.sdk.provided.ui.IToastUtils;
import com.jingdong.aura.sdk.update.b.k;
import com.jingdong.aura.sdk.update.config.IMobileConfig;
import com.jingdong.aura.sdk.update.config.impl.DefaultMobileConfig;
import com.jingdong.aura.sdk.update.downloader.IDownloader;
import com.jingdong.aura.sdk.update.privacy.DefaultPrivacyFieldProvider;
import com.jingdong.aura.sdk.update.privacy.IPrivacyFieldProvider;
import com.jingdong.aura.sdk.update.provider.IUserIdProvider;
import com.jingdong.aura.sdk.update.report.IReporter;
import com.jingdong.aura.sdk.update.updater.IBundleInfoProvider;

/* loaded from: classes4.dex */
public class AuraUpdateConfig {
    private static final long DEFAULT_UPDATE_INTEVAL = 300000;
    public String appKey;
    public String appSecret;
    public int appVersionCode;
    public String appVersionName;
    public IBundleInfoProvider bundleInfoProvider;
    public String channel;
    public Context context;
    public String country;
    IDownloader downloader;
    public boolean enableLog;
    public IMobileConfig mobileConfig;
    public IPrivacyFieldProvider privacyFieldProvider;
    IReporter reporter;
    IToastUtils toastUtils;
    long updateInteval;
    public boolean useBetaHost;
    public boolean useEncrption;
    public String userId;
    public IUserIdProvider userIdProvider;
    public String uuid;

    /* loaded from: classes4.dex */
    public static class Builder {
        String appKey;
        String appSecret;
        String appVersionName;
        IBundleInfoProvider bundleInfoProvider;
        String channel;
        Context context;
        String country;
        IDownloader downloader;
        IMobileConfig mobileConfig;
        IPrivacyFieldProvider privacyFieldProvider;
        IReporter reporter;
        IToastUtils toastUtils;
        String userId;
        IUserIdProvider userIdProvider;
        String uuid;
        int appVersionCode = -1;
        boolean useBetaHost = false;
        long updateInteval = 300000;
        boolean enableLog = false;
        boolean useEncrption = true;

        public Builder(Context context) {
            this.context = context;
        }

        public AuraUpdateConfig build() {
            return new AuraUpdateConfig(this);
        }

        public Builder enableLog(boolean z) {
            this.enableLog = z;
            return this;
        }

        public Builder setAppKey(String str) {
            this.appKey = str;
            return this;
        }

        public Builder setAppSecret(String str) {
            this.appSecret = str;
            return this;
        }

        public Builder setAppVersionCode(int i2) {
            this.appVersionCode = i2;
            return this;
        }

        public Builder setAppVersionName(String str) {
            this.appVersionName = str;
            return this;
        }

        public Builder setBundleInfoProvider(IBundleInfoProvider iBundleInfoProvider) {
            this.bundleInfoProvider = iBundleInfoProvider;
            return this;
        }

        public Builder setChannel(String str) {
            this.channel = str;
            return this;
        }

        public Builder setCountry(String str) {
            this.country = str;
            return this;
        }

        public Builder setDownloader(IDownloader iDownloader) {
            this.downloader = iDownloader;
            return this;
        }

        public Builder setIsUseBetaHost(boolean z) {
            this.useBetaHost = z;
            return this;
        }

        public Builder setMobileConfig(IMobileConfig iMobileConfig) {
            this.mobileConfig = iMobileConfig;
            return this;
        }

        public Builder setPrivacyFieldProvider(IPrivacyFieldProvider iPrivacyFieldProvider) {
            this.privacyFieldProvider = iPrivacyFieldProvider;
            return this;
        }

        public Builder setReporter(IReporter iReporter) {
            this.reporter = iReporter;
            return this;
        }

        public Builder setToastUtils(IToastUtils iToastUtils) {
            this.toastUtils = iToastUtils;
            return this;
        }

        public Builder setUseEncrption(boolean z) {
            this.useEncrption = z;
            return this;
        }

        public Builder setUserId(String str) {
            this.userId = str;
            return this;
        }

        public Builder setUserIdProvider(IUserIdProvider iUserIdProvider) {
            this.userIdProvider = iUserIdProvider;
            return this;
        }

        public Builder setUuid(String str) {
            this.uuid = str;
            return this;
        }
    }

    private AuraUpdateConfig() {
        this.appVersionCode = 0;
        this.useBetaHost = false;
        this.updateInteval = 300000L;
        this.enableLog = false;
        this.useEncrption = true;
    }

    private AuraUpdateConfig(Builder builder) {
        this.appVersionCode = 0;
        this.useBetaHost = false;
        this.updateInteval = 300000L;
        this.enableLog = false;
        this.useEncrption = true;
        this.context = builder.context;
        this.appKey = builder.appKey;
        this.appSecret = builder.appSecret;
        this.userId = builder.userId;
        this.country = builder.country;
        this.channel = builder.channel;
        this.downloader = builder.downloader;
        this.userIdProvider = builder.userIdProvider;
        this.bundleInfoProvider = builder.bundleInfoProvider;
        this.privacyFieldProvider = builder.privacyFieldProvider;
        IMobileConfig iMobileConfig = builder.mobileConfig;
        this.mobileConfig = iMobileConfig == null ? new DefaultMobileConfig() : iMobileConfig;
        this.reporter = builder.reporter;
        this.useBetaHost = builder.useBetaHost;
        this.updateInteval = builder.updateInteval;
        this.enableLog = builder.enableLog;
        this.toastUtils = builder.toastUtils;
        this.appVersionName = TextUtils.isEmpty(builder.appVersionName) ? k.a(this.context) : builder.appVersionName;
        int i2 = builder.appVersionCode;
        this.appVersionCode = i2 == -1 ? k.b(this.context) : i2;
        this.uuid = TextUtils.isEmpty(builder.uuid) ? "" : builder.uuid;
        if (this.privacyFieldProvider == null) {
            this.privacyFieldProvider = new DefaultPrivacyFieldProvider();
        }
        this.useEncrption = builder.useEncrption;
    }
}

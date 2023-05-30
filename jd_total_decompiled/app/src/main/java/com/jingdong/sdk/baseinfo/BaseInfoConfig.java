package com.jingdong.sdk.baseinfo;

import android.content.Context;
import com.jd.android.sdk.coreinfo.SensitiveApi;
import com.jd.android.sdk.coreinfo.util.Supplier;

/* loaded from: classes.dex */
public class BaseInfoConfig {
    public static final int PRIVACY_METHOD_RECORD_DISABLE = 0;
    public static final int PRIVACY_METHOD_RECORD_PRE_COMBINE = 2;
    public static final int PRIVACY_METHOD_RECORD_SINGLE = 1;
    private IBackForegroundCheck backForegroundCheck;
    private IBuildConfigGetter buildConfigGetter;
    private Context context;
    private IDataCacheScheme dataCacheScheme;
    private IDensityRelateCheck densityRelateCheck;
    private Supplier<Boolean> locationPermissionCheck;
    private IPrivacyCheck privacyCheck;
    private IPrivacyInfoCallback privacyInfoCallback;
    private int privacyMethodRecordUseDBSwitch;
    private SensitiveApi sensitiveApi;

    /* loaded from: classes.dex */
    public static class Builder {
        private IBackForegroundCheck backForegroundCheck;
        private IBuildConfigGetter buildConfigGetter;
        private Context context;
        private IDataCacheScheme dataCacheScheme;
        private IDensityRelateCheck densityRelateCheck;
        private Supplier<Boolean> locationPermissionCheck;
        private IPrivacyCheck privacyCheck;
        private IPrivacyInfoCallback privacyInfoCallback;
        private int privacyMethodRecordUseDBSwitch;
        private SensitiveApi sensitiveApi;

        public BaseInfoConfig build() {
            return new BaseInfoConfig(this);
        }

        public Builder setBackForegroundCheck(IBackForegroundCheck iBackForegroundCheck) {
            this.backForegroundCheck = iBackForegroundCheck;
            return this;
        }

        public Builder setBuildConfigGetter(IBuildConfigGetter iBuildConfigGetter) {
            this.buildConfigGetter = iBuildConfigGetter;
            return this;
        }

        public Builder setContext(Context context) {
            this.context = context;
            return this;
        }

        public Builder setDataCacheScheme(IDataCacheScheme iDataCacheScheme) {
            this.dataCacheScheme = iDataCacheScheme;
            return this;
        }

        public Builder setDensityRelateCheck(IDensityRelateCheck iDensityRelateCheck) {
            this.densityRelateCheck = iDensityRelateCheck;
            return this;
        }

        public Builder setLocationPermissionCheck(Supplier<Boolean> supplier) {
            this.locationPermissionCheck = supplier;
            return this;
        }

        public Builder setPrivacyCheck(IPrivacyCheck iPrivacyCheck) {
            this.privacyCheck = iPrivacyCheck;
            return this;
        }

        public Builder setPrivacyInfoCallback(IPrivacyInfoCallback iPrivacyInfoCallback) {
            this.privacyInfoCallback = iPrivacyInfoCallback;
            return this;
        }

        public Builder setPrivacyMethodRecordUseDBSwitch(int i2) {
            this.privacyMethodRecordUseDBSwitch = i2;
            return this;
        }

        public Builder setSensitiveApi(SensitiveApi sensitiveApi) {
            this.sensitiveApi = sensitiveApi;
            return this;
        }
    }

    private BaseInfoConfig(Builder builder) {
        this.context = builder.context;
        this.privacyCheck = builder.privacyCheck;
        this.backForegroundCheck = builder.backForegroundCheck;
        this.densityRelateCheck = builder.densityRelateCheck;
        this.buildConfigGetter = builder.buildConfigGetter;
        this.privacyInfoCallback = builder.privacyInfoCallback;
        this.dataCacheScheme = builder.dataCacheScheme;
        this.locationPermissionCheck = builder.locationPermissionCheck;
        this.privacyMethodRecordUseDBSwitch = builder.privacyMethodRecordUseDBSwitch;
        this.sensitiveApi = builder.sensitiveApi;
    }

    public IBackForegroundCheck getBackForegroundCheck() {
        return this.backForegroundCheck;
    }

    public IBuildConfigGetter getBuildConfigGetter() {
        return this.buildConfigGetter;
    }

    public Context getContext() {
        return this.context;
    }

    public IDataCacheScheme getDataCacheScheme() {
        return this.dataCacheScheme;
    }

    public IDensityRelateCheck getDensityRelateCheck() {
        return this.densityRelateCheck;
    }

    public Supplier<Boolean> getLocationPermissionCheck() {
        return this.locationPermissionCheck;
    }

    public IPrivacyCheck getPrivacyCheck() {
        return this.privacyCheck;
    }

    public IPrivacyInfoCallback getPrivacyInfoCallback() {
        return this.privacyInfoCallback;
    }

    public int getPrivacyMethodRecordSwitch() {
        return this.privacyMethodRecordUseDBSwitch;
    }

    public SensitiveApi getSensitiveApi() {
        return this.sensitiveApi;
    }
}

package com.jingdong.app.mall.bundle.jdweather.config;

import java.util.Map;

/* loaded from: classes.dex */
public class JDWeatherConfig {
    private int mAnimationRepeatCount;
    private String mAppId;
    private String mAppVersion;
    private Map<String, String> mDynamicParam;
    private String mLottieJson;
    private String mModuleId;

    /* loaded from: classes.dex */
    public static class Builder {
        private int animationRepeatCount;
        private String appId;
        private String appVersion;
        private Map<String, String> dynamicParam;
        private String lottieJson;
        private String moduleId;

        public Builder addAnimationRepeatCount(int i2) {
            this.animationRepeatCount = i2;
            return this;
        }

        public Builder addAppId(String str) {
            this.appId = str;
            return this;
        }

        public Builder addAppVersion(String str) {
            this.appVersion = str;
            return this;
        }

        public Builder addDynamicParam(Map<String, String> map) {
            this.dynamicParam = map;
            return this;
        }

        public Builder addLottieJson(String str) {
            this.lottieJson = str;
            return this;
        }

        public Builder addModuleId(String str) {
            this.moduleId = str;
            return this;
        }

        public JDWeatherConfig build() {
            return new JDWeatherConfig(this);
        }
    }

    public JDWeatherConfig(Builder builder) {
        if (builder != null) {
            this.mAppId = builder.appId;
            this.mModuleId = builder.moduleId;
            this.mAppVersion = builder.appVersion;
            this.mLottieJson = builder.lottieJson;
            this.mDynamicParam = builder.dynamicParam;
            this.mAnimationRepeatCount = builder.animationRepeatCount;
        }
    }

    public int getAnimationRepeatCount() {
        return this.mAnimationRepeatCount;
    }

    public String getAppId() {
        return this.mAppId;
    }

    public String getAppVersion() {
        return this.mAppVersion;
    }

    public Map<String, String> getDynamicParam() {
        return this.mDynamicParam;
    }

    public String getLottieJson() {
        return this.mLottieJson;
    }

    public String getModuleId() {
        return this.mModuleId;
    }
}

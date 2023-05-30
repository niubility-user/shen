package com.jingdong.app.mall.bundle.mobileConfig;

import android.app.Application;
import com.jingdong.app.mall.bundle.mobileConfig.net.IConfigFetcher;
import java.util.Map;

/* loaded from: classes.dex */
public class JDMobileConfig {
    private static JDMobileConfig instance;

    /* loaded from: classes.dex */
    public static class Builder {
        String appId;
        String appVersionName;
        Application application;
        String defaultAssetsJsonPath;
        IConfigFetcher fetcher;
        String userId;
        IUserIdCallBack userIdCallBack;
        String uuid;
        IUUIDCallBack uuidCallBack;
        IXTime xTime;
        int appVersionCode = 0;
        boolean useBetaHost = false;
        boolean fetchDataWithInit = true;
        boolean useLocalCache = true;
        boolean isDebug = false;

        public Builder(Application application) {
            this.application = application;
        }

        public Builder setAppId(String str) {
            this.appId = str;
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

        public Builder setDefaultAssetsJsonPath(String str) {
            this.defaultAssetsJsonPath = str;
            return this;
        }

        public Builder setFetchDataWithInit(boolean z) {
            this.fetchDataWithInit = z;
            return this;
        }

        public Builder setFetcher(IConfigFetcher iConfigFetcher) {
            this.fetcher = iConfigFetcher;
            return this;
        }

        public Builder setIUUIDCallBack(IUUIDCallBack iUUIDCallBack) {
            this.uuidCallBack = iUUIDCallBack;
            return this;
        }

        public Builder setIUserIdCallBack(IUserIdCallBack iUserIdCallBack) {
            this.userIdCallBack = iUserIdCallBack;
            return this;
        }

        public Builder setIsDebug(boolean z) {
            this.isDebug = z;
            return this;
        }

        public Builder setIsUseBetaHost(boolean z) {
            this.useBetaHost = z;
            return this;
        }

        public Builder setUseLocalCache(boolean z) {
            this.useLocalCache = z;
            return this;
        }

        public Builder setUserId(String str) {
            this.userId = str;
            return this;
        }

        public Builder setUuid(String str) {
            this.uuid = str;
            return this;
        }

        public Builder setXTimeInterFace(IXTime iXTime) {
            this.xTime = iXTime;
            return this;
        }
    }

    /* loaded from: classes12.dex */
    public interface IUUIDCallBack {
        String uuid();
    }

    /* loaded from: classes12.dex */
    public interface IUserIdCallBack {
        String userId();
    }

    /* loaded from: classes.dex */
    public interface IXTime {
        boolean isXTime();
    }

    private JDMobileConfig() {
    }

    public static JDMobileConfig getInstance() {
        if (instance == null) {
            synchronized (JDMobileConfig.class) {
                if (instance == null) {
                    instance = new JDMobileConfig();
                }
            }
        }
        return instance;
    }

    public boolean forceCheckUpdate() {
        return a.l().a();
    }

    public Map<String, Map<String, Map<String, String>>> getAllConfig() {
        return a.l().b();
    }

    public String getConfig(String str, String str2, String str3) {
        return a.l().a(str, str2, str3, "");
    }

    public String getConfig(String str, String str2, String str3, String str4) {
        return a.l().a(str, str2, str3, str4);
    }

    public Map<String, String> getConfigs(String str, String str2) {
        return a.l().a(str, str2);
    }

    public boolean getHasUpdate() {
        return a.l().i();
    }

    public void init(Builder builder) {
        a.l().a(builder);
    }

    public void registerListener(JDMoblieConfigListener jDMoblieConfigListener) {
        a.l().a(jDMoblieConfigListener);
    }

    public void unregisterListener(JDMoblieConfigListener jDMoblieConfigListener) {
        a.l().b(jDMoblieConfigListener);
    }

    public void updateUserId(String str) {
        a.l().a(str);
    }

    public void updateUuid(String str) {
        a.l().b(str);
    }
}

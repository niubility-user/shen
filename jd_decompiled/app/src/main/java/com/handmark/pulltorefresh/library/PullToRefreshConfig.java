package com.handmark.pulltorefresh.library;

import com.jd.lib.un.utils.UnAndroidUtils;

/* loaded from: classes.dex */
public class PullToRefreshConfig {
    private static PullToRefreshConfig config;
    private IPullToRefreshConfig iPullToRefreshConfig;

    private PullToRefreshConfig() {
        if (this.iPullToRefreshConfig == null) {
            this.iPullToRefreshConfig = new IPullToRefreshConfig() { // from class: com.handmark.pulltorefresh.library.PullToRefreshConfig.1
                @Override // com.handmark.pulltorefresh.library.IPullToRefreshConfig
                public boolean lottieEnable() {
                    return true;
                }
            };
        }
    }

    public static PullToRefreshConfig getInstance() {
        PullToRefreshConfig pullToRefreshConfig;
        PullToRefreshConfig pullToRefreshConfig2 = config;
        if (pullToRefreshConfig2 != null) {
            return pullToRefreshConfig2;
        }
        synchronized (PullToRefreshConfig.class) {
            if (config == null) {
                config = new PullToRefreshConfig();
            }
            pullToRefreshConfig = config;
        }
        return pullToRefreshConfig;
    }

    public boolean isLottieEnable() {
        return UnAndroidUtils.getAndroidVersion() >= 16 && this.iPullToRefreshConfig.lottieEnable();
    }

    public void setiPullToRefreshConfig(IPullToRefreshConfig iPullToRefreshConfig) {
        this.iPullToRefreshConfig = iPullToRefreshConfig;
    }
}

package com.jingdong.sdk.platform.config;

import com.jingdong.sdk.platform.utils.PlatformTools;
import java.util.List;

/* loaded from: classes10.dex */
public class PlatformConfig {
    private static boolean isConfig;
    private static PlatformPlugin pluginList;

    /* loaded from: classes10.dex */
    public static class PlatformConfigBuilder {
        private boolean debug;
        private PlatformLog platformLog;
        private PlatformPlugin platformPlugin;

        private PlatformConfigBuilder() {
        }

        public static PlatformConfigBuilder create(boolean z) {
            PlatformConfigBuilder platformConfigBuilder = new PlatformConfigBuilder();
            platformConfigBuilder.debug = z;
            return platformConfigBuilder;
        }

        public PlatformConfigBuilder platformList(PlatformPlugin platformPlugin) {
            this.platformPlugin = platformPlugin;
            return this;
        }

        public PlatformConfigBuilder platformLog(PlatformLog platformLog) {
            this.platformLog = platformLog;
            return this;
        }
    }

    public static void config(PlatformConfigBuilder platformConfigBuilder) {
        if (isConfig || platformConfigBuilder == null) {
            return;
        }
        isConfig = true;
        PlatformTools.D = platformConfigBuilder.debug;
        PlatformTools.setLog(platformConfigBuilder.platformLog);
        setPluginList(platformConfigBuilder.platformPlugin);
    }

    public static synchronized List<String> getPluginList() {
        List<String> initList;
        synchronized (PlatformConfig.class) {
            PlatformPlugin platformPlugin = pluginList;
            initList = platformPlugin != null ? platformPlugin.getInitList() : null;
        }
        return initList;
    }

    private static synchronized void setPluginList(PlatformPlugin platformPlugin) {
        synchronized (PlatformConfig.class) {
            pluginList = platformPlugin;
        }
    }
}

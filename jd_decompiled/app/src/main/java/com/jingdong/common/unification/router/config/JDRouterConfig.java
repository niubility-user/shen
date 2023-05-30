package com.jingdong.common.unification.router.config;

import android.content.Context;
import android.text.TextUtils;

/* loaded from: classes6.dex */
public class JDRouterConfig {
    public static boolean D;
    private static RouterClassLoader loader;
    private static RouterLog log;
    private static RouterMta mta;
    private static RouterOpen routerOpen;
    private static String routerProtocol;

    /* loaded from: classes6.dex */
    public static class ConfigBuilder {
        private boolean debug;
        private boolean isRouterOpen = true;
        private RouterClassLoader loader;
        private RouterLog log;
        private RouterMta mta;
        private RouterOpen routerOpen;
        private String routerProtocol;

        ConfigBuilder(boolean z, String str) {
            this.debug = z;
            if (!TextUtils.isEmpty(str)) {
                this.routerProtocol = str;
                return;
            }
            throw new NullPointerException("routerProtocol cannot be empty");
        }

        public static ConfigBuilder create(boolean z, String str) {
            return new ConfigBuilder(z, str);
        }

        @Deprecated
        public ConfigBuilder isRouterOpen(boolean z) {
            this.isRouterOpen = z;
            return this;
        }

        public ConfigBuilder routerClassLoader(RouterClassLoader routerClassLoader) {
            this.loader = routerClassLoader;
            return this;
        }

        public ConfigBuilder routerLog(RouterLog routerLog) {
            this.log = routerLog;
            return this;
        }

        public ConfigBuilder routerMta(RouterMta routerMta) {
            this.mta = routerMta;
            return this;
        }

        public ConfigBuilder routerOpen(RouterOpen routerOpen) {
            this.routerOpen = routerOpen;
            return this;
        }
    }

    /* loaded from: classes6.dex */
    public interface RouterClassLoader {
        Class<?> loadClass(String str);
    }

    /* loaded from: classes6.dex */
    public interface RouterLog {
        void d(String str, String str2);

        void e(String str, String str2);

        void i(String str, String str2);
    }

    /* loaded from: classes6.dex */
    public interface RouterMta {
        void onMtaEvent(Context context, String str, String str2, String str3);
    }

    /* loaded from: classes6.dex */
    public interface RouterOpen {
        boolean isRouterOpen();
    }

    public static void config(ConfigBuilder configBuilder) {
        D = configBuilder.debug;
        routerOpen = configBuilder.routerOpen;
        routerProtocol = configBuilder.routerProtocol;
        log = configBuilder.log;
        mta = configBuilder.mta;
        loader = configBuilder.loader;
    }

    public static void d(String str, String str2) {
        RouterLog routerLog;
        if (!D || (routerLog = log) == null) {
            return;
        }
        routerLog.d(str, str2);
    }

    public static void e(String str, String str2) {
        RouterLog routerLog;
        if (!D || (routerLog = log) == null) {
            return;
        }
        routerLog.e(str, str2);
    }

    public static RouterClassLoader getLoader() {
        return loader;
    }

    public static String getRouterProtocol() {
        if (!TextUtils.isEmpty(routerProtocol)) {
            return routerProtocol;
        }
        throw new NullPointerException("routerProtocol cannot be empty, please invoke config(ConfigBuilder builder) method to init!");
    }

    public static void i(String str, String str2) {
        RouterLog routerLog;
        if (!D || (routerLog = log) == null) {
            return;
        }
        routerLog.i(str, str2);
    }

    public static boolean isDebug() {
        return D;
    }

    public static boolean isRouterOpen() {
        RouterOpen routerOpen2 = routerOpen;
        if (routerOpen2 != null) {
            return routerOpen2.isRouterOpen();
        }
        return true;
    }

    public static void mtaEvent(Context context, String str, String str2, String str3) {
        RouterMta routerMta = mta;
        if (routerMta != null) {
            routerMta.onMtaEvent(context, str, str2, str3);
        }
    }

    public static void register(String str, String str2) {
        MoudleClassConfig.register(str, str2);
    }

    public static void registerPackage(String str) {
        MoudleClassConfig.registerPackage(str);
    }

    public static void e(String str, Throwable th) {
        if (!D || th == null) {
            return;
        }
        th.printStackTrace();
    }
}

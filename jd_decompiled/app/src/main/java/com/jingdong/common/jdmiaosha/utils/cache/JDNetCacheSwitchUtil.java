package com.jingdong.common.jdmiaosha.utils.cache;

import android.text.TextUtils;
import com.jingdong.app.mall.bundle.mobileConfig.JDMobileConfig;
import org.jetbrains.annotations.NotNull;

/* loaded from: classes5.dex */
public class JDNetCacheSwitchUtil {
    public static boolean cacheGetSwitch(@NotNull String str) {
        return getJDBizKeySwitch(str) && getJDCacheSwitch();
    }

    private static boolean getConfigSwitch() {
        return JDMobileConfig.getInstance().getAllConfig() == null || JDMobileConfig.getInstance().getAllConfig().isEmpty();
    }

    private static boolean getJDBizKeySwitch(@NotNull String str) {
        String config = JDMobileConfig.getInstance().getConfig("JDMiaoSha", "jdssscache", "JDBizKey");
        return (!TextUtils.isEmpty(config) && config.contains(str)) || getConfigSwitch();
    }

    public static String getJDCDNDomain() {
        String config = JDMobileConfig.getInstance().getConfig("JDMiaoSha", "jdssscache", "JDCDNDomain");
        return TextUtils.isEmpty(config) ? "" : config;
    }

    private static boolean getJDCDNSwitch() {
        String config = JDMobileConfig.getInstance().getConfig("JDMiaoSha", "jdssscache", "JDCDNSwitch");
        return (!TextUtils.isEmpty(config) && "1".equals(config)) || getConfigSwitch();
    }

    private static boolean getJDCacheSwitch() {
        String config = JDMobileConfig.getInstance().getConfig("JDMiaoSha", "jdssscache", "JDCacheSwitch");
        return (!TextUtils.isEmpty(config) && "1".equals(config)) || getConfigSwitch();
    }

    private static boolean getJDLocalSwitch() {
        String config = JDMobileConfig.getInstance().getConfig("JDMiaoSha", "jdssscache", "JDLocalSwitch");
        return (!TextUtils.isEmpty(config) && "1".equals(config)) || getConfigSwitch();
    }

    public static boolean localGetSwitch(@NotNull String str) {
        return getJDBizKeySwitch(str) && getJDLocalSwitch();
    }

    public static boolean normalCDNGetSwitch(@NotNull String str) {
        return getJDBizKeySwitch(str) && getJDCDNSwitch();
    }
}

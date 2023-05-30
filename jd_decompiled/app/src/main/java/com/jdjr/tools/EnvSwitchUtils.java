package com.jdjr.tools;

/* loaded from: classes18.dex */
public class EnvSwitchUtils {
    private static final String ENV_PRO = "env_pro";
    private static final String ENV_TEST = "env_test";
    private static String currentEnvironment = "env_pro";
    private static boolean readHostConfigFlag = true;

    private static String getAppEnv() {
        Object invoke;
        if (readHostConfigFlag) {
            try {
                Class<?> cls = Class.forName("com.jingdong.jdsdk.config.HostConfig");
                return (cls == null || (invoke = cls.getMethod("getJDPayDevelopType", new Class[0]).invoke(null, new Object[0])) == null) ? ENV_PRO : ((Integer) invoke).intValue() == 2 ? ENV_TEST : ENV_PRO;
            } catch (Throwable unused) {
                return ENV_PRO;
            }
        }
        return currentEnvironment;
    }

    public static boolean isTestEnvironment() {
        return ENV_TEST.equals(getAppEnv());
    }

    public static void setCustomEnvironment(boolean z, boolean z2) {
        readHostConfigFlag = z;
        currentEnvironment = z2 ? ENV_TEST : ENV_PRO;
    }
}

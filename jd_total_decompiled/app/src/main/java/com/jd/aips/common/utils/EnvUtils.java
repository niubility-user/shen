package com.jd.aips.common.utils;

/* loaded from: classes12.dex */
public class EnvUtils {
    public static volatile int ENV = -1;
    public static final int ENV_NONE = -1;
    public static final int ENV_PRODUCT = 0;
    public static final int ENV_TEST = 2;
    public static final int ENV_UAT = 1;

    public static int getEnvType() {
        if (ENV == -1) {
            try {
                Object invoke = Class.forName("com.jingdong.jdsdk.config.HostConfig").getMethod("getJDPayDevelopType", new Class[0]).invoke(null, new Object[0]);
                if (invoke != null) {
                    ENV = ((Integer) invoke).intValue();
                }
            } catch (Throwable unused) {
                ENV = 0;
            }
        }
        return ENV;
    }
}

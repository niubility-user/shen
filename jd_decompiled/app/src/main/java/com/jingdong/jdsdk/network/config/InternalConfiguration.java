package com.jingdong.jdsdk.network.config;

import com.jingdong.manto.sdk.api.IMantoServerRequester;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: classes14.dex */
public final class InternalConfiguration {
    public static final String ATTEMPTS = "attempts";
    public static final String ATTEMPTS_TIME = "attemptsTime";
    public static final String CLIENT = "client";
    public static final String CONNECT_TIMEOUT = "connectTimeout";
    public static final String CONNECT_TIMEOUT_FOR_2G = "connectTimeoutFor2G";
    public static final String CONNECT_TIMEOUT_FOR_WIFI = "connectTimeoutForWIFI";
    public static final int DEFAULT_ENCRYPT_FAILED_THRESHOLD = 3;
    public static final String HOST = "host";
    public static final String READ_TIMEOUT = "readTimeout";
    public static final String READ_TIMEOUT_FOR_WIFI = "readTimeoutForWIFI";
    public static final String REQUEST_METHOD = "requestMethod";
    public static final String TEST_MODE = "testMode";
    public static final String UNIFORM_HOST = "api.m.jd.com";
    public static final String UNIFORM_HOST_BETA = "beta-api.m.jd.com";
    public static final String UNIFORM_HOST_CARE = "api.m.jd.care";
    public static AtomicInteger encryptFailedNum = new AtomicInteger(0);
    private static Map<String, String> localProperties;

    public static String getProperty(String str) {
        return getProperty(str, null);
    }

    private static synchronized void init() {
        synchronized (InternalConfiguration.class) {
            if (localProperties != null) {
                return;
            }
            HashMap hashMap = new HashMap();
            localProperties = hashMap;
            hashMap.put("connectTimeout", "10000");
            localProperties.put("connectTimeoutFor2G", "20000");
            localProperties.put("connectTimeoutForWIFI", "10000");
            localProperties.put("readTimeout", "15000");
            localProperties.put("readTimeoutForWIFI", "10000");
            localProperties.put("attempts", "3");
            localProperties.put("attemptsTime", "0");
            localProperties.put("requestMethod", IMantoServerRequester.POST);
            localProperties.put("host", "api.m.jd.com");
            localProperties.put("client", "android");
        }
    }

    public static String getProperty(String str, String str2) {
        init();
        String str3 = localProperties.get(str);
        return str3 == null ? str2 : str3;
    }
}

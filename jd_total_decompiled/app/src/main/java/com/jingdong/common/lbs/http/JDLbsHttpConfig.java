package com.jingdong.common.lbs.http;

import com.jingdong.jdsdk.config.Configuration;

/* loaded from: classes5.dex */
public class JDLbsHttpConfig {
    public static final String SDK_VERSION = "1.0.1";
    public static boolean isBetaHost;
    public static boolean isDebug;

    public static String getHost() {
        return (!"api.m.jd.com".equals(Configuration.getNgwHost()) || isBetaHost) ? "beta-api.m.jd.com" : "api.m.jd.com";
    }
}

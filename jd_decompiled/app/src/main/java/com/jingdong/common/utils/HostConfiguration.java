package com.jingdong.common.utils;

import com.jingdong.sdk.oklog.OKLog;

@Deprecated
/* loaded from: classes6.dex */
public class HostConfiguration {
    public static String getPortalHost() {
        return OKLog.D ? "api.m.jd.care" : "api.m.jd.com";
    }

    public static String getWmpHost() {
        return OKLog.D ? "beta-wmp-data.jd.com" : "wmp-data.jd.com";
    }
}

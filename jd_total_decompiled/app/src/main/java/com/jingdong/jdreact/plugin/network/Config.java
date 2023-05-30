package com.jingdong.jdreact.plugin.network;

import com.jingdong.jdreact.plugin.utils.CommonUtil;

/* loaded from: classes13.dex */
public class Config {
    private static String PIN = "";
    private static UUIDGenerator sUUIDGenerator;

    public static String getPIN() {
        return PIN;
    }

    public static String getUUID() {
        UUIDGenerator uUIDGenerator = sUUIDGenerator;
        if (uUIDGenerator != null) {
            return uUIDGenerator.getUUID();
        }
        return CommonUtil.getAndroidId();
    }

    public static void setLogEnable(boolean z) {
        LogUtil.init(z);
    }

    public static void setPIN(String str) {
        PIN = str;
    }

    public static void setUUIDGenerator(UUIDGenerator uUIDGenerator) {
        sUUIDGenerator = uUIDGenerator;
    }
}

package com.jingdong.common.jdreactFramework.utils;

/* loaded from: classes5.dex */
public class NetConfig {
    public static final String BETA_HOST = "beta-api.m.jd.com";
    public static final String CLIENT = "android";
    public static final String GET_META_DATA_FUNCTION_ID = "getMetaData";
    public static final String UNIFORM_HOST = "api.m.jd.com";
    public static String sAppCode = "";
    public static String sAppId = "";
    public static boolean sBeta = false;
    private static String sClient = "android";
    private static boolean sGetMetaDataDisabled = false;
    public static int sLoginType = 0;
    public static String sSecretKey = "";
    private static boolean useFunctionIDAsPath;

    public static String getClient() {
        return sClient;
    }

    public static void init(String str, String str2, String str3) {
        sAppCode = str;
        sAppId = str2;
        sSecretKey = str3;
    }

    public static boolean isGetMetaDataDisabled() {
        return sGetMetaDataDisabled;
    }

    public static boolean isUseFunctionIDAsPath() {
        return useFunctionIDAsPath;
    }

    public static void setAppCode(String str) {
        sAppCode = str;
    }

    public static void setAppId(String str) {
        sAppId = str;
    }

    public static void setBeta(boolean z) {
        sBeta = z;
    }

    public static void setClient(String str) {
        sClient = str;
    }

    public static void setGetMetaDataDisabled(boolean z) {
        sGetMetaDataDisabled = z;
    }

    public static void setLoginType(int i2) {
        sLoginType = i2;
    }

    public static void setSecretKey(String str) {
        sSecretKey = str;
    }

    public static void setUseFunctionIDAsPath(boolean z) {
        useFunctionIDAsPath = z;
    }
}

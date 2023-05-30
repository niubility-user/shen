package com.jdcn.fido.http;

import android.text.TextUtils;

/* loaded from: classes18.dex */
public class HttpUrlUtil {
    private static String HOST_URL = "https://idauth.jd.com";
    public static final String URL_FORCE_REGISTER = "/v/forceRegister";
    public static final String URL_HOST_RELEASE = "https://idauth.jd.com";
    public static final String URL_REGISTER = "/v/register";
    public static final String URL_REGISTER2 = "/v/register2";
    public static final String URL_REGISTER3 = "/v/register3";
    public static final String URL_STATUS = "/v/status";
    public static final String URL_TRANSPORT = "/v/verify";
    public static final String URL_TRANSPORT2 = "/v/verify2";
    public static final String URL_UNREGISTER = "/v/close";
    public static final String URL_VERIFYVERSION = "/v/verifyVersion";

    public static String getRequestUrl(String str) {
        return HOST_URL + str;
    }

    public static void setHostUrl(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        HOST_URL = str;
    }
}

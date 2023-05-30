package com.xiaomi.push.service;

/* loaded from: classes11.dex */
public abstract class m0 {
    public static String A = "ext_cloud_attr";
    public static String B = "ext_pkg_name";
    public static String C = "ext_notify_id";
    public static String D = "ext_clicked_button";
    public static String E = "ext_notify_type";
    public static String F = "ext_session";
    public static String G = "sig";
    public static String H = "ext_notify_title";
    public static String I = "ext_notify_description";
    public static String J = "ext_messenger";
    public static String K = "title";
    public static String L = "description";
    public static String M = "notifyId";
    public static String N = "dump";
    public static String a = "1";
    public static String b = "2";

    /* renamed from: c  reason: collision with root package name */
    public static String f19126c = "3";
    public static String d = "com.xiaomi.push.OPEN_CHANNEL";

    /* renamed from: e  reason: collision with root package name */
    public static String f19127e = "com.xiaomi.push.SEND_MESSAGE";

    /* renamed from: f  reason: collision with root package name */
    public static String f19128f = "com.xiaomi.push.SEND_IQ";

    /* renamed from: g  reason: collision with root package name */
    public static String f19129g = "com.xiaomi.push.BATCH_SEND_MESSAGE";

    /* renamed from: h  reason: collision with root package name */
    public static String f19130h = "com.xiaomi.push.SEND_PRES";

    /* renamed from: i  reason: collision with root package name */
    public static String f19131i = "com.xiaomi.push.CLOSE_CHANNEL";

    /* renamed from: j  reason: collision with root package name */
    public static String f19132j = "com.xiaomi.push.FORCE_RECONN";

    /* renamed from: k  reason: collision with root package name */
    public static String f19133k = "com.xiaomi.push.RESET_CONN";

    /* renamed from: l  reason: collision with root package name */
    public static String f19134l = "com.xiaomi.push.UPDATE_CHANNEL_INFO";

    /* renamed from: m  reason: collision with root package name */
    public static String f19135m = "com.xiaomi.push.HANDLE_FCM_MSG";

    /* renamed from: n  reason: collision with root package name */
    public static String f19136n = "com.xiaomi.push.APP_NOTIFY_MSG";
    public static String o = "com.xiaomi.push.PING_TIMER";
    public static String p = "ext_user_id";
    public static String q = "ext_user_server";
    public static String r = "ext_user_res";
    public static String s = "ext_chid";
    public static String t = "ext_receive_time";
    public static String u = "ext_broadcast_time";
    public static String v = "ext_token";
    public static String w = "ext_auth_method";
    public static String x = "ext_security";
    public static String y = "ext_kick";
    public static String z = "ext_client_attr";

    public static String a(int i2) {
        switch (i2) {
            case 0:
                return "ERROR_OK";
            case 1:
                return "ERROR_SERVICE_NOT_INSTALLED";
            case 2:
                return "ERROR_NETWORK_NOT_AVAILABLE";
            case 3:
                return "ERROR_NETWORK_FAILED";
            case 4:
                return "ERROR_ACCESS_DENIED";
            case 5:
                return "ERROR_AUTH_FAILED";
            case 6:
                return "ERROR_MULTI_LOGIN";
            case 7:
                return "ERROR_SERVER_ERROR";
            case 8:
                return "ERROR_RECEIVE_TIMEOUT";
            case 9:
                return "ERROR_READ_ERROR";
            case 10:
                return "ERROR_SEND_ERROR";
            case 11:
                return "ERROR_RESET";
            case 12:
                return "ERROR_NO_CLIENT";
            case 13:
                return "ERROR_SERVER_STREAM";
            case 14:
                return "ERROR_THREAD_BLOCK";
            case 15:
                return "ERROR_SERVICE_DESTROY";
            case 16:
                return "ERROR_SESSION_CHANGED";
            case 17:
                return "ERROR_READ_TIMEOUT";
            case 18:
                return "ERROR_CONNECTIING_TIMEOUT";
            case 19:
                return "ERROR_USER_BLOCKED";
            case 20:
                return "ERROR_REDIRECT";
            case 21:
                return "ERROR_BIND_TIMEOUT";
            case 22:
                return "ERROR_PING_TIMEOUT";
            default:
                return String.valueOf(i2);
        }
    }
}

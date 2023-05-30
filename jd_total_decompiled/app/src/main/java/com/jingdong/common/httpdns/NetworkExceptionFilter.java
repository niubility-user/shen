package com.jingdong.common.httpdns;

import com.facebook.common.logging.FLog;

/* loaded from: classes5.dex */
public class NetworkExceptionFilter {
    public static String[] NETWORK_FAIL_PATTERN = {"java.net.UnknownHostException", "java.net.ConnectException", "java.net.NoRouteToHostException", "failed to connect", "503", "not verified"};
    public static final String TAG = "NetworkExceptionFilter";

    public static boolean filter(Exception exc) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(exc.getMessage());
        stringBuffer.append(exc.getClass().getName());
        String stringBuffer2 = stringBuffer.toString();
        FLog.d(TAG, "Exception info : " + stringBuffer2);
        for (String str : NETWORK_FAIL_PATTERN) {
            if (stringBuffer2.contains(str)) {
                return true;
            }
        }
        return false;
    }
}

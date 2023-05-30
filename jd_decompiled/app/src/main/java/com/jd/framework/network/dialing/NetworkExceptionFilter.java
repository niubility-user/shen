package com.jd.framework.network.dialing;

import com.android.volley.VolleyLog;

/* loaded from: classes13.dex */
public class NetworkExceptionFilter {
    public static String[] NETWORK_FAIL_PATTERN = {"java.net.UnknownHostException", "java.net.ConnectException", "java.net.NoRouteToHostException", "failed to connect", "503"};
    public static final String TAG = "NetworkExceptionFilter";

    public static boolean filter(Exception exc) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(exc.getMessage());
        stringBuffer.append(exc.getClass().getName());
        String stringBuffer2 = stringBuffer.toString();
        if (VolleyLog.DEBUG) {
            String str = "Exception info : " + stringBuffer2;
        }
        for (String str2 : NETWORK_FAIL_PATTERN) {
            if (stringBuffer2.contains(str2)) {
                return true;
            }
        }
        return false;
    }
}

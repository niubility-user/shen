package com.android.volley.utils;

import android.text.TextUtils;
import com.android.volley.VolleyLog;
import java.net.MalformedURLException;
import java.net.URL;

/* loaded from: classes.dex */
public class UrlUtil {
    public static String getHost(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            return new URL(str).getHost();
        } catch (MalformedURLException e2) {
            if (VolleyLog.DEBUG) {
                e2.printStackTrace();
                return null;
            }
            return null;
        }
    }

    public static boolean isHttps(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        try {
            return "https".equals(new URL(str).getProtocol());
        } catch (MalformedURLException e2) {
            if (VolleyLog.DEBUG) {
                e2.printStackTrace();
                return false;
            }
            return false;
        }
    }
}

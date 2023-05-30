package com.meizu.cloud.pushsdk.e.d;

import com.jd.jdcache.util.UrlHelper;

/* loaded from: classes14.dex */
public class d {
    public static boolean a(String str) {
        return b(str) || UrlHelper.METHOD_OPTIONS.equals(str) || UrlHelper.METHOD_DELETE.equals(str) || "PROPFIND".equals(str) || "MKCOL".equals(str) || "LOCK".equals(str);
    }

    public static boolean b(String str) {
        return "POST".equals(str) || UrlHelper.METHOD_PUT.equals(str) || UrlHelper.METHOD_PATCH.equals(str) || "PROPPATCH".equals(str) || "REPORT".equals(str);
    }
}

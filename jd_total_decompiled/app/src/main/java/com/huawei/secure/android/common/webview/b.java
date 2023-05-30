package com.huawei.secure.android.common.webview;

import android.annotation.TargetApi;
import android.net.Uri;
import android.text.TextUtils;
import android.webkit.URLUtil;
import com.jd.dynamic.DYConstants;
import com.jingdong.app.mall.bundle.order_center_isv_core.util.OrderISVUtil;
import java.net.MalformedURLException;
import java.net.URL;

/* loaded from: classes12.dex */
public class b {
    private static String a(String str) {
        if (!TextUtils.isEmpty(str)) {
            return !URLUtil.isNetworkUrl(str) ? str : b(str);
        }
        com.huawei.secure.android.common.util.b.f("UriUtil", "whiteListUrl is null");
        return null;
    }

    @TargetApi(9)
    public static String b(String str) {
        if (TextUtils.isEmpty(str)) {
            com.huawei.secure.android.common.util.b.f("UriUtil", "url is null");
            return str;
        }
        try {
            if (!URLUtil.isNetworkUrl(str)) {
                com.huawei.secure.android.common.util.b.d("UriUtil", "url don't starts with http or https");
                return "";
            }
            return new URL(str.replaceAll("[\\\\#]", "/")).getHost();
        } catch (MalformedURLException e2) {
            com.huawei.secure.android.common.util.b.d("UriUtil", "getHostByURI error  MalformedURLException : " + e2.getMessage());
            return "";
        }
    }

    public static boolean c(String str, String[] strArr) {
        if (strArr != null && strArr.length != 0) {
            for (String str2 : strArr) {
                if (d(str, str2)) {
                    return true;
                }
            }
            return false;
        }
        com.huawei.secure.android.common.util.b.d("UriUtil", "whitelist is null");
        return false;
    }

    public static boolean d(String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2) || str.contains("..") || str.contains(DYConstants.DY_REGEX_AT)) {
            return false;
        }
        if (!str2.equals(str)) {
            if (!str.startsWith(str2 + "?")) {
                if (!str.startsWith(str2 + "#")) {
                    if (str2.endsWith("/")) {
                        if (Uri.parse(str).getPathSegments().size() - Uri.parse(str2).getPathSegments().size() != 1) {
                            return false;
                        }
                        return str.startsWith(str2);
                    }
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean e(String str, String[] strArr) {
        if (strArr != null && strArr.length != 0) {
            for (String str2 : strArr) {
                if (f(str, str2)) {
                    return true;
                }
            }
            return false;
        }
        com.huawei.secure.android.common.util.b.d("UriUtil", "whitelist is null");
        return false;
    }

    public static boolean f(String str, String str2) {
        String b = b(str);
        if (!TextUtils.isEmpty(b) && !TextUtils.isEmpty(str2)) {
            String a = a(str2);
            if (TextUtils.isEmpty(a)) {
                return false;
            }
            if (a.equals(b)) {
                return true;
            }
            if (b.endsWith(a)) {
                try {
                    String substring = b.substring(0, b.length() - a.length());
                    if (substring.endsWith(OrderISVUtil.MONEY_DECIMAL)) {
                        return substring.matches("^[A-Za-z0-9.-]+$");
                    }
                    return false;
                } catch (IndexOutOfBoundsException e2) {
                    com.huawei.secure.android.common.util.b.d("UriUtil", "IndexOutOfBoundsException" + e2.getMessage());
                } catch (Exception e3) {
                    com.huawei.secure.android.common.util.b.d("UriUtil", "Exception : " + e3.getMessage());
                    return false;
                }
            }
            return false;
        }
        com.huawei.secure.android.common.util.b.d("UriUtil", "url or whitelist is null");
        return false;
    }

    public static boolean g(String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return false;
        }
        return TextUtils.equals(b(str), a(str2));
    }

    public static boolean h(String str, String[] strArr) {
        if (strArr != null && strArr.length != 0) {
            for (String str2 : strArr) {
                if (g(str, str2)) {
                    return true;
                }
            }
            return false;
        }
        com.huawei.secure.android.common.util.b.d("UriUtil", "whitelist is null");
        return false;
    }
}

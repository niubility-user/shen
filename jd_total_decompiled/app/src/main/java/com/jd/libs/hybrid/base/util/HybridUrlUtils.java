package com.jd.libs.hybrid.base.util;

import android.net.Uri;
import android.text.TextUtils;
import java.util.regex.Pattern;

/* loaded from: classes16.dex */
public class HybridUrlUtils {
    public static String excludeQuery(String str) {
        Uri parse = Uri.parse(str);
        String fixPathString = fixPathString(parse.getPath());
        StringBuilder sb = new StringBuilder();
        sb.append(parse.getScheme());
        sb.append("://");
        sb.append(parse.getHost());
        if (fixPathString == null) {
            fixPathString = "";
        }
        sb.append(fixPathString);
        return sb.toString();
    }

    public static String excludeScheme(String str) {
        return str.replaceFirst(Uri.parse(str).getScheme() + "://", "");
    }

    public static String fixPathString(String str) {
        return (TextUtils.isEmpty(str) || !str.endsWith("/")) ? str : str.substring(0, str.length() - 1);
    }

    public static String getHostPath(Uri uri) {
        String fixPathString = fixPathString(uri.getPath());
        StringBuilder sb = new StringBuilder();
        sb.append(uri.getHost());
        if (fixPathString == null) {
            fixPathString = "";
        }
        sb.append(fixPathString);
        return sb.toString();
    }

    public static int getUrlVersion(String str) {
        try {
            String queryParameter = Uri.parse(str).getQueryParameter("jdhybrid_v");
            if (TextUtils.isEmpty(queryParameter) || !TextUtils.isDigitsOnly(queryParameter)) {
                return -1;
            }
            return Integer.parseInt(queryParameter);
        } catch (Exception e2) {
            Log.e("HybridUrlUtils", e2);
            return -1;
        }
    }

    public static boolean isRegexpMatched(String str, String str2) {
        if (TextUtils.isEmpty(str2) || TextUtils.isEmpty(str)) {
            return false;
        }
        return Pattern.compile(str).matcher(str2).matches();
    }

    public static boolean isSSrUrl(String str) {
        try {
            return "1".equals(Uri.parse(str).getQueryParameter("jdhybrid-offline"));
        } catch (Exception e2) {
            Log.e("HybridUrlUtils", e2);
            return false;
        }
    }

    public static boolean uriMatchHostPath(Uri uri, Uri uri2) {
        if (uri == null || uri2 == null) {
            return false;
        }
        String host = uri.getHost();
        return host != null && host.equals(uri2.getHost()) && TextUtils.equals(fixPathString(uri.getPath()), fixPathString(uri2.getPath()));
    }
}

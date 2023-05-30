package com.jingdong.common.web.util;

import android.net.Uri;
import android.text.TextUtils;
import com.jingdong.sdk.log.Log;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* loaded from: classes12.dex */
public class URLUtils {
    private static Matcher genTokenMatcher;
    private static Pattern genTokenPattern;

    public static String getQueryParameter(String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        if (!TextUtils.isEmpty(str2)) {
            try {
            } catch (Exception unused) {
                return "";
            }
        }
        return Uri.parse(str).getQueryParameter(str2);
    }

    public static boolean isGentokenUrl(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        try {
            if (genTokenPattern == null) {
                genTokenPattern = Pattern.compile("un.m.jd.com/cgi-bin/app/appjmp");
            }
            Matcher matcher = genTokenMatcher;
            if (matcher == null) {
                genTokenMatcher = genTokenPattern.matcher(str);
            } else {
                matcher.reset(str);
            }
            return genTokenMatcher.find();
        } catch (Exception e2) {
            Log.e("URLUtils", e2.getMessage(), e2);
            return false;
        }
    }

    public static String putQueryParameter(String str, String str2, String str3) {
        if (TextUtils.isEmpty(str2) || TextUtils.isEmpty(str3)) {
            return str;
        }
        Uri.Builder buildUpon = Uri.parse(str).buildUpon();
        buildUpon.appendQueryParameter(str2, str3);
        return buildUpon.toString();
    }
}

package com.jingdong.amon.router;

import android.net.Uri;
import android.text.TextUtils;

/* loaded from: classes18.dex */
public class c {
    public static String a(Uri uri) {
        if (uri == null) {
            return null;
        }
        return a(uri.getScheme(), uri.getHost());
    }

    public static String a(String str) {
        return TextUtils.isEmpty(str) ? str : str.toLowerCase();
    }

    public static String a(String str, String str2) {
        return b(a(str)) + "://" + b(a(str2));
    }

    public static String b(String str) {
        return str == null ? "" : str;
    }

    public static Uri c(String str) {
        return TextUtils.isEmpty(str) ? Uri.EMPTY : Uri.parse(str);
    }
}

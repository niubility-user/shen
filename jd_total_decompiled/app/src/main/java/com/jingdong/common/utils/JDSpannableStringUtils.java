package com.jingdong.common.utils;

import android.content.Context;
import android.text.Html;
import android.text.Spanned;

/* loaded from: classes6.dex */
public class JDSpannableStringUtils {
    public static Spanned getErrorSpanned(Context context, int i2) {
        return getErrorSpanned(context, i2, "FF0000");
    }

    public static Spanned getErrorSpanned(Context context, int i2, String str) {
        return Html.fromHtml("<font color='#" + str + "'>" + context.getString(i2) + "</font>");
    }

    public static Spanned getErrorSpanned(String str) {
        return getErrorSpanned(str, "FF0000");
    }

    public static Spanned getErrorSpanned(String str, String str2) {
        return Html.fromHtml("<font color='#" + str2 + "'>" + str + "</font>");
    }
}

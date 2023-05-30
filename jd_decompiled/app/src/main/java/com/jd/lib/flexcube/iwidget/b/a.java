package com.jd.lib.flexcube.iwidget.b;

import android.graphics.Color;
import android.text.TextUtils;
import com.jingdong.common.utils.LangUtils;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* loaded from: classes14.dex */
public class a {
    private static Pattern a = Pattern.compile("^#[0-9a-fA-F]{6}$");
    private static Pattern b = Pattern.compile("rgb\\((2[0-4][0-9]|25[0-5]|[01]?[0-9][0-9]?),(2[0-4][0-9]|25[0-5]|[01]?[0-9][0-9]?),((2[0-4][0-9]|25[0-5]|[01]?[0-9][0-9]?))\\)");

    /* renamed from: c  reason: collision with root package name */
    private static Pattern f4224c = Pattern.compile("rgba\\((2[0-4][0-9]|25[0-5]|[01]?[0-9][0-9]?),(2[0-4][0-9]|25[0-5]|[01]?[0-9][0-9]?),(2[0-4][0-9]|25[0-5]|[01]?[0-9][0-9]?),((1\\.0+|[01]|0\\.{1}\\d+))\\)");

    public static int a(String str, int i2) {
        if (TextUtils.isEmpty(str) || str.trim().length() == 0) {
            return i2;
        }
        String trim = str.trim();
        if (a.matcher(trim).find()) {
            return b(trim, i2);
        }
        if (c.b(trim, "rgba")) {
            return d(trim, i2);
        }
        return c.b(trim, "rgb") ? c(trim, i2) : i2;
    }

    public static int b(String str, int i2) {
        if (c.c(str)) {
            return i2;
        }
        try {
            return Color.parseColor(str.trim());
        } catch (Exception e2) {
            e2.printStackTrace();
            return i2;
        }
    }

    public static int c(String str, int i2) {
        if (c.c(str)) {
            return i2;
        }
        try {
            Matcher matcher = b.matcher(str.replace(LangUtils.SINGLE_SPACE, ""));
            if (matcher.find()) {
                return Color.argb(255, b.f(matcher.group(1), 0), b.f(matcher.group(2), 0), b.f(matcher.group(3), 0));
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return i2;
    }

    public static int d(String str, int i2) {
        if (c.c(str)) {
            return i2;
        }
        try {
            Matcher matcher = f4224c.matcher(str.replace(LangUtils.SINGLE_SPACE, ""));
            if (matcher.find() && matcher.groupCount() == 5) {
                return Color.argb((int) (b.e(matcher.group(4), 0) * 255.0f), b.f(matcher.group(1), 0), b.f(matcher.group(2), 0), b.f(matcher.group(3), 0));
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return i2;
    }
}

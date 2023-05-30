package com.jingdong.manto.sdk;

import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextUtils;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* loaded from: classes16.dex */
public class a implements InputFilter {
    private int a;
    private int b;

    public a(int i2, int i3) {
        this.a = i2;
        this.b = i3;
    }

    public static int a(String str) {
        if (TextUtils.isEmpty(str)) {
            return 0;
        }
        Matcher matcher = Pattern.compile("[\\u4e00-\\u9fa5]").matcher(str);
        int i2 = 0;
        while (matcher.find()) {
            int i3 = 0;
            while (i3 <= matcher.groupCount()) {
                i3++;
                i2++;
            }
        }
        return i2;
    }

    public static int a(String str, int i2) {
        String str2 = "getLength: " + str + ", " + i2;
        if (i2 == 1) {
            if (TextUtils.isEmpty(str)) {
                return 0;
            }
            return str.length();
        } else if (i2 == 2) {
            return b(str);
        } else {
            return 0;
        }
    }

    public static int b(String str) {
        int i2 = 0;
        if (TextUtils.isEmpty(str)) {
            return 0;
        }
        int a = a(str) + str.length();
        if (!TextUtils.isEmpty(str)) {
            int i3 = 0;
            while (i2 < str.length()) {
                char charAt = str.charAt(i2);
                if (charAt >= 0 && charAt <= '\u007f') {
                    i3++;
                }
                i2++;
            }
            i2 = str.length() - (i3 + a(str));
        }
        return i2 + a;
    }

    @Override // android.text.InputFilter
    public CharSequence filter(CharSequence charSequence, int i2, int i3, Spanned spanned, int i4, int i5) {
        int a = a(spanned.subSequence(0, i4).toString() + spanned.subSequence(i5, spanned.length()).toString(), this.b) + a(charSequence.subSequence(i2, i3).toString(), this.b);
        int i6 = this.a;
        return a > i6 ? charSequence.subSequence(i2, Math.max(i2, Math.min(i6 - (spanned.length() - (i5 - i4)), i3))) : charSequence;
    }
}

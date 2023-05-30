package com.jd.fireeye.b;

import android.text.TextUtils;

/* loaded from: classes13.dex */
public final class d {

    /* loaded from: classes13.dex */
    public static class a {
        public String a;
        public String b;

        public a(String str, String str2) {
            this.a = str;
            this.b = str2;
        }
    }

    public static a a(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            String b = b(c.a(str));
            return new a(b.substring(0, b.length() / 2), b.substring(b.length() / 2));
        } catch (Throwable unused) {
            return null;
        }
    }

    private static String b(String str) {
        char[] charArray = str.toCharArray();
        int length = charArray.length / 2;
        for (int i2 = 0; i2 < length; i2++) {
            int i3 = i2 * 2;
            char c2 = charArray[i3];
            int i4 = i3 + 1;
            charArray[i3] = charArray[i4];
            charArray[i4] = c2;
        }
        return new String(charArray);
    }
}

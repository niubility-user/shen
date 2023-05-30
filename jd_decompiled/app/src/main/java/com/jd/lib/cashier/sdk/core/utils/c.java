package com.jd.lib.cashier.sdk.core.utils;

/* loaded from: classes14.dex */
public class c {
    private static char a(char c2) {
        switch (c2) {
            case '0':
                return '\u96f6';
            case '1':
                return '\u4e00';
            case '2':
                return '\u4e8c';
            case '3':
                return '\u4e09';
            case '4':
                return '\u56db';
            case '5':
                return '\u4e94';
            case '6':
                return '\u516d';
            case '7':
                return '\u4e03';
            case '8':
                return '\u516b';
            case '9':
                return '\u4e5d';
            default:
                return c2;
        }
    }

    public static String b(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        }
        char[] charArray = str.toCharArray();
        StringBuilder sb = new StringBuilder();
        for (char c2 : charArray) {
            sb.append(a(c2));
        }
        return sb.toString();
    }
}

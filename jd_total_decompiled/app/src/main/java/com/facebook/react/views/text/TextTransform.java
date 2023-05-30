package com.facebook.react.views.text;

import java.text.BreakIterator;

/* loaded from: classes12.dex */
public enum TextTransform {
    NONE,
    UPPERCASE,
    LOWERCASE,
    CAPITALIZE,
    UNSET;

    /* renamed from: com.facebook.react.views.text.TextTransform$1  reason: invalid class name */
    /* loaded from: classes12.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$facebook$react$views$text$TextTransform;

        static {
            int[] iArr = new int[TextTransform.values().length];
            $SwitchMap$com$facebook$react$views$text$TextTransform = iArr;
            try {
                iArr[TextTransform.UPPERCASE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$facebook$react$views$text$TextTransform[TextTransform.LOWERCASE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$facebook$react$views$text$TextTransform[TextTransform.CAPITALIZE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    public static String apply(String str, TextTransform textTransform) {
        if (str == null) {
            return null;
        }
        int i2 = AnonymousClass1.$SwitchMap$com$facebook$react$views$text$TextTransform[textTransform.ordinal()];
        if (i2 != 1) {
            if (i2 != 2) {
                return i2 != 3 ? str : capitalize(str);
            }
            return str.toLowerCase();
        }
        return str.toUpperCase();
    }

    private static String capitalize(String str) {
        BreakIterator wordInstance = BreakIterator.getWordInstance();
        wordInstance.setText(str);
        StringBuilder sb = new StringBuilder(str.length());
        int first = wordInstance.first();
        while (true) {
            int i2 = first;
            first = wordInstance.next();
            if (first != -1) {
                String substring = str.substring(i2, first);
                if (Character.isLetterOrDigit(substring.charAt(0))) {
                    sb.append(Character.toUpperCase(substring.charAt(0)));
                    sb.append(substring.substring(1).toLowerCase());
                } else {
                    sb.append(substring);
                }
            } else {
                return sb.toString();
            }
        }
    }
}

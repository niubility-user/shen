package com.alibaba.fastjson;

/* loaded from: classes.dex */
public enum PropertyNamingStrategy {
    CamelCase,
    PascalCase,
    SnakeCase,
    KebabCase;

    /* renamed from: com.alibaba.fastjson.PropertyNamingStrategy$1  reason: invalid class name */
    /* loaded from: classes.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$alibaba$fastjson$PropertyNamingStrategy;

        static {
            int[] iArr = new int[PropertyNamingStrategy.values().length];
            $SwitchMap$com$alibaba$fastjson$PropertyNamingStrategy = iArr;
            try {
                iArr[PropertyNamingStrategy.SnakeCase.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$alibaba$fastjson$PropertyNamingStrategy[PropertyNamingStrategy.KebabCase.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$alibaba$fastjson$PropertyNamingStrategy[PropertyNamingStrategy.PascalCase.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$alibaba$fastjson$PropertyNamingStrategy[PropertyNamingStrategy.CamelCase.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    public String translate(String str) {
        char charAt;
        int i2 = AnonymousClass1.$SwitchMap$com$alibaba$fastjson$PropertyNamingStrategy[ordinal()];
        int i3 = 0;
        if (i2 == 1) {
            StringBuilder sb = new StringBuilder();
            while (i3 < str.length()) {
                char charAt2 = str.charAt(i3);
                if (charAt2 >= 'A' && charAt2 <= 'Z') {
                    char c2 = (char) (charAt2 + ' ');
                    if (i3 > 0) {
                        sb.append('_');
                    }
                    sb.append(c2);
                } else {
                    sb.append(charAt2);
                }
                i3++;
            }
            return sb.toString();
        } else if (i2 == 2) {
            StringBuilder sb2 = new StringBuilder();
            while (i3 < str.length()) {
                char charAt3 = str.charAt(i3);
                if (charAt3 >= 'A' && charAt3 <= 'Z') {
                    char c3 = (char) (charAt3 + ' ');
                    if (i3 > 0) {
                        sb2.append('-');
                    }
                    sb2.append(c3);
                } else {
                    sb2.append(charAt3);
                }
                i3++;
            }
            return sb2.toString();
        } else if (i2 != 3) {
            if (i2 == 4 && (charAt = str.charAt(0)) >= 'A' && charAt <= 'Z') {
                char[] charArray = str.toCharArray();
                charArray[0] = (char) (charArray[0] + ' ');
                return new String(charArray);
            }
            return str;
        } else {
            char charAt4 = str.charAt(0);
            if (charAt4 < 'a' || charAt4 > 'z') {
                return str;
            }
            char[] charArray2 = str.toCharArray();
            charArray2[0] = (char) (charArray2[0] - ' ');
            return new String(charArray2);
        }
    }
}

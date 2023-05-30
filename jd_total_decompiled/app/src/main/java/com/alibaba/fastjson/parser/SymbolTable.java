package com.alibaba.fastjson.parser;

import com.jd.framework.json.JDJSON;

/* loaded from: classes.dex */
public class SymbolTable {
    private final int indexMask;
    private final Entry[] symbols;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class Entry {
        final char[] chars;
        final int hashCode;
        final String value;

        Entry(String str, int i2) {
            this.value = str;
            this.chars = str.toCharArray();
            this.hashCode = i2;
        }
    }

    public SymbolTable(int i2) {
        this.indexMask = i2 - 1;
        this.symbols = new Entry[i2];
        addSymbol("$ref", 0, 4, 1185263);
        addSymbol(JDJSON.DEFAULT_TYPE_KEY, 0, 5, 62680954);
    }

    private static String subString(String str, int i2, int i3) {
        char[] cArr = new char[i3];
        str.getChars(i2, i3 + i2, cArr, 0);
        return new String(cArr);
    }

    public String addSymbol(char[] cArr, int i2, int i3, int i4) {
        int i5 = this.indexMask & i4;
        Entry entry = this.symbols[i5];
        if (entry != null) {
            boolean z = false;
            if (i4 == entry.hashCode && i3 == entry.chars.length) {
                int i6 = 0;
                while (true) {
                    if (i6 >= i3) {
                        z = true;
                        break;
                    } else if (cArr[i2 + i6] != entry.chars[i6]) {
                        break;
                    } else {
                        i6++;
                    }
                }
            }
            if (z) {
                return entry.value;
            }
            return new String(cArr, i2, i3);
        }
        String intern = new String(cArr, i2, i3).intern();
        this.symbols[i5] = new Entry(intern, i4);
        return intern;
    }

    public String addSymbol(String str, int i2, int i3, int i4) {
        int i5 = this.indexMask & i4;
        Entry entry = this.symbols[i5];
        if (entry != null) {
            if (i4 == entry.hashCode && i3 == entry.chars.length && str.regionMatches(i2, entry.value, 0, i3)) {
                return entry.value;
            }
            return subString(str, i2, i3);
        }
        if (i3 != str.length()) {
            str = subString(str, i2, i3);
        }
        String intern = str.intern();
        this.symbols[i5] = new Entry(intern, i4);
        return intern;
    }
}

package com.meizu.cloud.pushsdk.e.d;

import com.facebook.react.views.textinput.ReactEditTextInputConnectionWrapper;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/* loaded from: classes14.dex */
public final class c {
    private final String[] a;

    /* loaded from: classes14.dex */
    public static final class b {
        private final List<String> a = new ArrayList(20);

        private void e(String str, String str2) {
            if (str == null) {
                throw new IllegalArgumentException("name == null");
            }
            if (str.isEmpty()) {
                throw new IllegalArgumentException("name is empty");
            }
            int length = str.length();
            for (int i2 = 0; i2 < length; i2++) {
                char charAt = str.charAt(i2);
                if (charAt <= 31 || charAt >= '\u007f') {
                    throw new IllegalArgumentException(String.format("Unexpected char %#04x at %d in header name: %s", Integer.valueOf(charAt), Integer.valueOf(i2), str));
                }
            }
            if (str2 == null) {
                throw new IllegalArgumentException("value == null");
            }
            int length2 = str2.length();
            for (int i3 = 0; i3 < length2; i3++) {
                char charAt2 = str2.charAt(i3);
                if (charAt2 <= 31 || charAt2 >= '\u007f') {
                    throw new IllegalArgumentException(String.format("Unexpected char %#04x at %d in %s value: %s", Integer.valueOf(charAt2), Integer.valueOf(i3), str, str2));
                }
            }
        }

        public b a(String str, String str2) {
            e(str, str2);
            d(str, str2);
            return this;
        }

        public c b() {
            return new c(this);
        }

        b d(String str, String str2) {
            this.a.add(str);
            this.a.add(str2.trim());
            return this;
        }
    }

    private c(b bVar) {
        this.a = (String[]) bVar.a.toArray(new String[bVar.a.size()]);
    }

    private c(String[] strArr) {
        this.a = strArr;
    }

    public static c a(String... strArr) {
        if (strArr == null || strArr.length % 2 != 0) {
            throw new IllegalArgumentException("Expected alternating header names and values");
        }
        String[] strArr2 = (String[]) strArr.clone();
        for (int i2 = 0; i2 < strArr2.length; i2++) {
            if (strArr2[i2] == null) {
                throw new IllegalArgumentException("Headers cannot be null");
            }
            strArr2[i2] = strArr2[i2].trim();
        }
        for (int i3 = 0; i3 < strArr2.length; i3 += 2) {
            String str = strArr2[i3];
            String str2 = strArr2[i3 + 1];
            if (str.length() == 0 || str.indexOf(0) != -1 || str2.indexOf(0) != -1) {
                throw new IllegalArgumentException("Unexpected header: " + str + ": " + str2);
            }
        }
        return new c(strArr2);
    }

    private static String d(String[] strArr, String str) {
        for (int length = strArr.length - 2; length >= 0; length -= 2) {
            if (str.equalsIgnoreCase(strArr[length])) {
                return strArr[length + 1];
            }
        }
        return null;
    }

    public String b(int i2) {
        return this.a[i2 * 2];
    }

    public String c(String str) {
        return d(this.a, str);
    }

    public Set<String> e() {
        TreeSet treeSet = new TreeSet(String.CASE_INSENSITIVE_ORDER);
        int h2 = h();
        for (int i2 = 0; i2 < h2; i2++) {
            treeSet.add(b(i2));
        }
        return Collections.unmodifiableSet(treeSet);
    }

    public b f() {
        b bVar = new b();
        Collections.addAll(bVar.a, this.a);
        return bVar;
    }

    public String g(int i2) {
        return this.a[(i2 * 2) + 1];
    }

    public int h() {
        return this.a.length / 2;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        int h2 = h();
        for (int i2 = 0; i2 < h2; i2++) {
            sb.append(b(i2));
            sb.append(": ");
            sb.append(g(i2));
            sb.append(ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
        }
        return sb.toString();
    }
}

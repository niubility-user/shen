package android.content.res;

import kotlin.text.Typography;

/* loaded from: classes.dex */
public class JDMobiSec {
    public static final String n1(String str) {
        String n2 = n2(str);
        StringBuilder sb = new StringBuilder(n2.length());
        int i2 = 0;
        while (i2 < n2.length()) {
            char charAt = n2.charAt(i2);
            if (charAt == '\\') {
                char charAt2 = i2 == n2.length() + (-1) ? '\\' : n2.charAt(i2 + 1);
                if (charAt2 < '0' || charAt2 > '7') {
                    if (charAt2 == '\"') {
                        charAt = Typography.quote;
                    } else if (charAt2 == '\'') {
                        charAt = '\'';
                    } else if (charAt2 == '\\') {
                        charAt = '\\';
                    } else if (charAt2 == 'b') {
                        charAt = '\b';
                    } else if (charAt2 == 'f') {
                        charAt = '\f';
                    } else if (charAt2 == 'n') {
                        charAt = '\n';
                    } else if (charAt2 == 'r') {
                        charAt = '\r';
                    } else if (charAt2 == 't') {
                        charAt = '\t';
                    } else if (charAt2 == 'u') {
                        if (i2 >= n2.length() - 5) {
                            charAt = 'u';
                        } else {
                            StringBuilder sb2 = new StringBuilder();
                            sb2.append(n2.charAt(i2 + 2));
                            sb2.append(n2.charAt(i2 + 3));
                            sb2.append(n2.charAt(i2 + 4));
                            i2 += 5;
                            sb2.append(n2.charAt(i2));
                            sb.append(Character.toChars(Integer.parseInt(sb2.toString(), 16)));
                            i2++;
                        }
                    }
                    i2++;
                } else {
                    StringBuilder sb3 = new StringBuilder();
                    sb3.append(charAt2);
                    String sb4 = sb3.toString();
                    i2++;
                    if (i2 < n2.length() - 1) {
                        int i3 = i2 + 1;
                        if (n2.charAt(i3) >= '0' && n2.charAt(i3) <= '7') {
                            sb4 = String.valueOf(sb4) + n2.charAt(i3);
                            if (i3 < n2.length() - 1) {
                                i2 = i3 + 1;
                                if (n2.charAt(i2) >= '0' && n2.charAt(i2) <= '7') {
                                    sb4 = String.valueOf(sb4) + n2.charAt(i2);
                                }
                            }
                            i2 = i3;
                        }
                    }
                    charAt = (char) Integer.parseInt(sb4, 8);
                }
            }
            sb.append(charAt);
            i2++;
        }
        return sb.toString();
    }

    public static native String n2(String str);
}

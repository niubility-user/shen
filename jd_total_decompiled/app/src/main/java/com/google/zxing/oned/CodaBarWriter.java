package com.google.zxing.oned;

import com.jingdong.app.mall.bundle.order_center_isv_core.util.OrderISVUtil;
import java.util.Arrays;

/* loaded from: classes12.dex */
public final class CodaBarWriter extends OneDimensionalCodeWriter {
    private static final char[] START_END_CHARS = {'A', 'B', 'C', 'D'};
    private static final char[] ALT_START_END_CHARS = {'T', 'N', '*', 'E'};
    private static final char[] CHARS_WHICH_ARE_TEN_LENGTH_EACH_AFTER_DECODED = {'/', ':', '+', OrderISVUtil.MONEY_DECIMAL_CHAR};

    @Override // com.google.zxing.oned.OneDimensionalCodeWriter
    public boolean[] encode(String str) {
        int i2;
        if (str.length() >= 2) {
            char upperCase = Character.toUpperCase(str.charAt(0));
            char upperCase2 = Character.toUpperCase(str.charAt(str.length() - 1));
            char[] cArr = START_END_CHARS;
            boolean z = CodaBarReader.arrayContains(cArr, upperCase) && CodaBarReader.arrayContains(cArr, upperCase2);
            char[] cArr2 = ALT_START_END_CHARS;
            boolean z2 = CodaBarReader.arrayContains(cArr2, upperCase) && CodaBarReader.arrayContains(cArr2, upperCase2);
            if (!z && !z2) {
                throw new IllegalArgumentException("Codabar should start/end with " + Arrays.toString(cArr) + ", or start/end with " + Arrays.toString(cArr2));
            }
            int i3 = 20;
            for (int i4 = 1; i4 < str.length() - 1; i4++) {
                if (Character.isDigit(str.charAt(i4)) || str.charAt(i4) == '-' || str.charAt(i4) == '$') {
                    i3 += 9;
                } else if (!CodaBarReader.arrayContains(CHARS_WHICH_ARE_TEN_LENGTH_EACH_AFTER_DECODED, str.charAt(i4))) {
                    throw new IllegalArgumentException("Cannot encode : '" + str.charAt(i4) + '\'');
                } else {
                    i3 += 10;
                }
            }
            boolean[] zArr = new boolean[i3 + (str.length() - 1)];
            int i5 = 0;
            for (int i6 = 0; i6 < str.length(); i6++) {
                char upperCase3 = Character.toUpperCase(str.charAt(i6));
                if (i6 == 0 || i6 == str.length() - 1) {
                    if (upperCase3 == '*') {
                        upperCase3 = 'C';
                    } else if (upperCase3 == 'E') {
                        upperCase3 = 'D';
                    } else if (upperCase3 == 'N') {
                        upperCase3 = 'B';
                    } else if (upperCase3 == 'T') {
                        upperCase3 = 'A';
                    }
                }
                int i7 = 0;
                while (true) {
                    char[] cArr3 = CodaBarReader.ALPHABET;
                    if (i7 >= cArr3.length) {
                        i2 = 0;
                        break;
                    } else if (upperCase3 == cArr3[i7]) {
                        i2 = CodaBarReader.CHARACTER_ENCODINGS[i7];
                        break;
                    } else {
                        i7++;
                    }
                }
                int i8 = 0;
                boolean z3 = true;
                while (true) {
                    int i9 = 0;
                    while (i8 < 7) {
                        zArr[i5] = z3;
                        i5++;
                        if (((i2 >> (6 - i8)) & 1) == 0 || i9 == 1) {
                            z3 = !z3;
                            i8++;
                        } else {
                            i9++;
                        }
                    }
                    break;
                }
                if (i6 < str.length() - 1) {
                    zArr[i5] = false;
                    i5++;
                }
            }
            return zArr;
        }
        throw new IllegalArgumentException("Codabar should start/end with start/stop symbols");
    }
}

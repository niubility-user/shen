package com.google.zxing.oned;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

/* loaded from: classes12.dex */
public final class Code128Writer extends OneDimensionalCodeWriter {
    private static final int CODE_CODE_B = 100;
    private static final int CODE_CODE_C = 99;
    private static final int CODE_FNC_1 = 102;
    private static final int CODE_FNC_2 = 97;
    private static final int CODE_FNC_3 = 96;
    private static final int CODE_FNC_4_B = 100;
    private static final int CODE_START_B = 104;
    private static final int CODE_START_C = 105;
    private static final int CODE_STOP = 106;
    private static final char ESCAPE_FNC_1 = '\u00f1';
    private static final char ESCAPE_FNC_2 = '\u00f2';
    private static final char ESCAPE_FNC_3 = '\u00f3';
    private static final char ESCAPE_FNC_4 = '\u00f4';

    private static boolean isDigits(CharSequence charSequence, int i2, int i3) {
        int i4 = i3 + i2;
        int length = charSequence.length();
        while (i2 < i4 && i2 < length) {
            char charAt = charSequence.charAt(i2);
            if (charAt < '0' || charAt > '9') {
                if (charAt != '\u00f1') {
                    return false;
                }
                i4++;
            }
            i2++;
        }
        return i4 <= length;
    }

    @Override // com.google.zxing.oned.OneDimensionalCodeWriter, com.google.zxing.Writer
    public BitMatrix encode(String str, BarcodeFormat barcodeFormat, int i2, int i3, Map<EncodeHintType, ?> map) throws WriterException {
        if (barcodeFormat == BarcodeFormat.CODE_128) {
            return super.encode(str, barcodeFormat, i2, i3, map);
        }
        throw new IllegalArgumentException("Can only encode CODE_128, but got " + barcodeFormat);
    }

    @Override // com.google.zxing.oned.OneDimensionalCodeWriter
    public boolean[] encode(String str) {
        int length = str.length();
        if (length >= 1 && length <= 80) {
            int i2 = 0;
            for (int i3 = 0; i3 < length; i3++) {
                char charAt = str.charAt(i3);
                if (charAt < ' ' || charAt > '~') {
                    switch (charAt) {
                        case '\u00f1':
                        case '\u00f2':
                        case '\u00f3':
                        case '\u00f4':
                            break;
                        default:
                            throw new IllegalArgumentException("Bad character in input: " + charAt);
                    }
                }
            }
            ArrayList<int[]> arrayList = new ArrayList();
            int i4 = 0;
            int i5 = 0;
            int i6 = 0;
            int i7 = 1;
            while (i4 < length) {
                int i8 = 100;
                int i9 = isDigits(str, i4, i6 == 99 ? 2 : 4) ? 99 : 100;
                if (i9 == i6) {
                    switch (str.charAt(i4)) {
                        case '\u00f1':
                            i8 = 102;
                            break;
                        case '\u00f2':
                            i8 = 97;
                            break;
                        case '\u00f3':
                            i8 = 96;
                            break;
                        case '\u00f4':
                            break;
                        default:
                            if (i6 != 100) {
                                i8 = Integer.parseInt(str.substring(i4, i4 + 2));
                                i4++;
                                break;
                            } else {
                                i8 = str.charAt(i4) - ' ';
                                break;
                            }
                    }
                    i4++;
                } else {
                    i8 = i6 == 0 ? i9 == 100 ? 104 : 105 : i9;
                    i6 = i9;
                }
                arrayList.add(Code128Reader.CODE_PATTERNS[i8]);
                i5 += i8 * i7;
                if (i4 != 0) {
                    i7++;
                }
            }
            int[][] iArr = Code128Reader.CODE_PATTERNS;
            arrayList.add(iArr[i5 % 103]);
            arrayList.add(iArr[106]);
            int i10 = 0;
            for (int[] iArr2 : arrayList) {
                for (int i11 : iArr2) {
                    i10 += i11;
                }
            }
            boolean[] zArr = new boolean[i10];
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                i2 += OneDimensionalCodeWriter.appendPattern(zArr, i2, (int[]) it.next(), true);
            }
            return zArr;
        }
        throw new IllegalArgumentException("Contents length should be between 1 and 80 characters, but got " + length);
    }
}

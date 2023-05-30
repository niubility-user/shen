package com.google.zxing.pdf417.decoder;

import com.google.zxing.FormatException;
import com.google.zxing.pdf417.PDF417ResultMetadata;
import com.jingdong.app.mall.bundle.order_center_isv_core.util.OrderISVUtil;
import java.math.BigInteger;
import java.util.Arrays;
import kotlin.text.Typography;

/* loaded from: classes12.dex */
final class DecodedBitStreamParser {
    private static final int AL = 28;
    private static final int AS = 27;
    private static final int BEGIN_MACRO_PDF417_CONTROL_BLOCK = 928;
    private static final int BEGIN_MACRO_PDF417_OPTIONAL_FIELD = 923;
    private static final int BYTE_COMPACTION_MODE_LATCH = 901;
    private static final int BYTE_COMPACTION_MODE_LATCH_6 = 924;
    private static final BigInteger[] EXP900;
    private static final int LL = 27;
    private static final int MACRO_PDF417_TERMINATOR = 922;
    private static final int MAX_NUMERIC_CODEWORDS = 15;
    private static final int ML = 28;
    private static final int MODE_SHIFT_TO_BYTE_COMPACTION_MODE = 913;
    private static final int NUMBER_OF_SEQUENCE_CODEWORDS = 2;
    private static final int NUMERIC_COMPACTION_MODE_LATCH = 902;
    private static final int PAL = 29;
    private static final int PL = 25;
    private static final int PS = 29;
    private static final int TEXT_COMPACTION_MODE_LATCH = 900;
    private static final char[] PUNCT_CHARS = {';', Typography.less, Typography.greater, '@', '[', '\\', ']', '_', '`', '~', '!', '\r', '\t', ',', ':', '\n', '-', OrderISVUtil.MONEY_DECIMAL_CHAR, Typography.dollar, '/', Typography.quote, '|', '*', '(', ')', '?', '{', '}', '\''};
    private static final char[] MIXED_CHARS = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', Typography.amp, '\r', '\t', ',', ':', '#', '-', OrderISVUtil.MONEY_DECIMAL_CHAR, Typography.dollar, '/', '+', '%', '*', '=', '^'};

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.google.zxing.pdf417.decoder.DecodedBitStreamParser$1  reason: invalid class name */
    /* loaded from: classes12.dex */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$google$zxing$pdf417$decoder$DecodedBitStreamParser$Mode;

        static {
            int[] iArr = new int[Mode.values().length];
            $SwitchMap$com$google$zxing$pdf417$decoder$DecodedBitStreamParser$Mode = iArr;
            try {
                iArr[Mode.ALPHA.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$google$zxing$pdf417$decoder$DecodedBitStreamParser$Mode[Mode.LOWER.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$google$zxing$pdf417$decoder$DecodedBitStreamParser$Mode[Mode.MIXED.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$google$zxing$pdf417$decoder$DecodedBitStreamParser$Mode[Mode.PUNCT.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$google$zxing$pdf417$decoder$DecodedBitStreamParser$Mode[Mode.ALPHA_SHIFT.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$google$zxing$pdf417$decoder$DecodedBitStreamParser$Mode[Mode.PUNCT_SHIFT.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes12.dex */
    public enum Mode {
        ALPHA,
        LOWER,
        MIXED,
        PUNCT,
        ALPHA_SHIFT,
        PUNCT_SHIFT
    }

    static {
        BigInteger[] bigIntegerArr = new BigInteger[16];
        EXP900 = bigIntegerArr;
        bigIntegerArr[0] = BigInteger.ONE;
        BigInteger valueOf = BigInteger.valueOf(900L);
        bigIntegerArr[1] = valueOf;
        int i2 = 2;
        while (true) {
            BigInteger[] bigIntegerArr2 = EXP900;
            if (i2 >= bigIntegerArr2.length) {
                return;
            }
            bigIntegerArr2[i2] = bigIntegerArr2[i2 - 1].multiply(valueOf);
            i2++;
        }
    }

    private DecodedBitStreamParser() {
    }

    private static int byteCompaction(int i2, int[] iArr, int i3, StringBuilder sb) {
        int i4;
        int i5;
        int i6 = 922;
        int i7 = 923;
        long j2 = 900;
        int i8 = 6;
        if (i2 != 901) {
            if (i2 == 924) {
                int i9 = i3;
                boolean z = false;
                int i10 = 0;
                long j3 = 0;
                while (i9 < iArr[0] && !z) {
                    int i11 = i9 + 1;
                    int i12 = iArr[i9];
                    if (i12 < 900) {
                        i10++;
                        j3 = (j3 * 900) + i12;
                    } else if (i12 == 900 || i12 == 901 || i12 == 902 || i12 == 924 || i12 == 928 || i12 == i7 || i12 == i6) {
                        i9 = i11 - 1;
                        z = true;
                        if (i10 % 5 != 0 && i10 > 0) {
                            char[] cArr = new char[6];
                            for (int i13 = 0; i13 < 6; i13++) {
                                cArr[5 - i13] = (char) (j3 & 255);
                                j3 >>= 8;
                            }
                            sb.append(cArr);
                            i10 = 0;
                        }
                        i6 = 922;
                        i7 = 923;
                    }
                    i9 = i11;
                    if (i10 % 5 != 0) {
                    }
                    i6 = 922;
                    i7 = 923;
                }
                return i9;
            }
            return i3;
        }
        char[] cArr2 = new char[6];
        int[] iArr2 = new int[6];
        int i14 = i3 + 1;
        int i15 = iArr[i3];
        long j4 = 0;
        boolean z2 = false;
        loop0: while (true) {
            i4 = 0;
            while (i14 < iArr[0] && !z2) {
                int i16 = i4 + 1;
                iArr2[i4] = i15;
                j4 = (j4 * j2) + i15;
                int i17 = i14 + 1;
                i15 = iArr[i14];
                if (i15 == 900 || i15 == 901 || i15 == 902 || i15 == 924 || i15 == 928 || i15 == 923 || i15 == 922) {
                    i14 = i17 - 1;
                    i15 = i15;
                    i4 = i16;
                    j2 = 900;
                    i8 = 6;
                    z2 = true;
                } else if (i16 % 5 != 0 || i16 <= 0) {
                    i15 = i15;
                    i4 = i16;
                    i14 = i17;
                    j2 = 900;
                    i8 = 6;
                } else {
                    int i18 = 0;
                    while (i18 < i8) {
                        cArr2[5 - i18] = (char) (j4 % 256);
                        j4 >>= 8;
                        i18++;
                        i15 = i15;
                        i8 = 6;
                    }
                    sb.append(cArr2);
                    i14 = i17;
                    j2 = 900;
                    i8 = 6;
                }
            }
        }
        if (i14 != iArr[0] || i15 >= 900) {
            i5 = i4;
        } else {
            i5 = i4 + 1;
            iArr2[i4] = i15;
        }
        for (int i19 = 0; i19 < i5; i19++) {
            sb.append((char) iArr2[i19]);
        }
        return i14;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0046 A[LOOP:0: B:3:0x0011->B:20:0x0046, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:31:0x004b A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static com.google.zxing.common.DecoderResult decode(int[] r5, java.lang.String r6) throws com.google.zxing.FormatException {
        /*
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            int r1 = r5.length
            r2 = 2
            int r1 = r1 * 2
            r0.<init>(r1)
            r1 = 1
            r1 = r5[r1]
            com.google.zxing.pdf417.PDF417ResultMetadata r3 = new com.google.zxing.pdf417.PDF417ResultMetadata
            r3.<init>()
        L11:
            r4 = 0
            r4 = r5[r4]
            if (r2 >= r4) goto L50
            r4 = 913(0x391, float:1.28E-42)
            if (r1 == r4) goto L3f
            r4 = 928(0x3a0, float:1.3E-42)
            if (r1 == r4) goto L3a
            switch(r1) {
                case 900: goto L35;
                case 901: goto L3f;
                case 902: goto L30;
                default: goto L21;
            }
        L21:
            switch(r1) {
                case 922: goto L2b;
                case 923: goto L2b;
                case 924: goto L3f;
                default: goto L24;
            }
        L24:
            int r2 = r2 + (-1)
            int r1 = textCompaction(r5, r2, r0)
            goto L43
        L2b:
            com.google.zxing.FormatException r5 = com.google.zxing.FormatException.getFormatInstance()
            throw r5
        L30:
            int r1 = numericCompaction(r5, r2, r0)
            goto L43
        L35:
            int r1 = textCompaction(r5, r2, r0)
            goto L43
        L3a:
            int r1 = decodeMacroBlock(r5, r2, r3)
            goto L43
        L3f:
            int r1 = byteCompaction(r1, r5, r2, r0)
        L43:
            int r2 = r5.length
            if (r1 >= r2) goto L4b
            int r2 = r1 + 1
            r1 = r5[r1]
            goto L11
        L4b:
            com.google.zxing.FormatException r5 = com.google.zxing.FormatException.getFormatInstance()
            throw r5
        L50:
            int r5 = r0.length()
            if (r5 == 0) goto L64
            com.google.zxing.common.DecoderResult r5 = new com.google.zxing.common.DecoderResult
            java.lang.String r0 = r0.toString()
            r1 = 0
            r5.<init>(r1, r0, r1, r6)
            r5.setOther(r3)
            return r5
        L64:
            com.google.zxing.FormatException r5 = com.google.zxing.FormatException.getFormatInstance()
            goto L6a
        L69:
            throw r5
        L6a:
            goto L69
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.pdf417.decoder.DecodedBitStreamParser.decode(int[], java.lang.String):com.google.zxing.common.DecoderResult");
    }

    private static String decodeBase900toBase10(int[] iArr, int i2) throws FormatException {
        BigInteger bigInteger = BigInteger.ZERO;
        for (int i3 = 0; i3 < i2; i3++) {
            bigInteger = bigInteger.add(EXP900[(i2 - i3) - 1].multiply(BigInteger.valueOf(iArr[i3])));
        }
        String bigInteger2 = bigInteger.toString();
        if (bigInteger2.charAt(0) == '1') {
            return bigInteger2.substring(1);
        }
        throw FormatException.getFormatInstance();
    }

    private static int decodeMacroBlock(int[] iArr, int i2, PDF417ResultMetadata pDF417ResultMetadata) throws FormatException {
        if (i2 + 2 <= iArr[0]) {
            int[] iArr2 = new int[2];
            int i3 = 0;
            while (i3 < 2) {
                iArr2[i3] = iArr[i2];
                i3++;
                i2++;
            }
            pDF417ResultMetadata.setSegmentIndex(Integer.parseInt(decodeBase900toBase10(iArr2, 2)));
            StringBuilder sb = new StringBuilder();
            int textCompaction = textCompaction(iArr, i2, sb);
            pDF417ResultMetadata.setFileId(sb.toString());
            if (iArr[textCompaction] == 923) {
                int i4 = textCompaction + 1;
                int[] iArr3 = new int[iArr[0] - i4];
                boolean z = false;
                int i5 = 0;
                while (i4 < iArr[0] && !z) {
                    int i6 = i4 + 1;
                    int i7 = iArr[i4];
                    if (i7 < 900) {
                        iArr3[i5] = i7;
                        i4 = i6;
                        i5++;
                    } else if (i7 == 922) {
                        pDF417ResultMetadata.setLastSegment(true);
                        i4 = i6 + 1;
                        z = true;
                    } else {
                        throw FormatException.getFormatInstance();
                    }
                }
                pDF417ResultMetadata.setOptionalData(Arrays.copyOf(iArr3, i5));
                return i4;
            } else if (iArr[textCompaction] == 922) {
                pDF417ResultMetadata.setLastSegment(true);
                return textCompaction + 1;
            } else {
                return textCompaction;
            }
        }
        throw FormatException.getFormatInstance();
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private static void decodeTextCompaction(int[] iArr, int[] iArr2, int i2, StringBuilder sb) {
        Mode mode;
        int i3;
        Mode mode2 = Mode.ALPHA;
        Mode mode3 = mode2;
        for (int i4 = 0; i4 < i2; i4++) {
            int i5 = iArr[i4];
            char c2 = ' ';
            switch (AnonymousClass1.$SwitchMap$com$google$zxing$pdf417$decoder$DecodedBitStreamParser$Mode[mode2.ordinal()]) {
                case 1:
                    if (i5 < 26) {
                        i3 = i5 + 65;
                        c2 = (char) i3;
                        break;
                    } else if (i5 != 26) {
                        if (i5 == 27) {
                            mode2 = Mode.LOWER;
                        } else if (i5 == 28) {
                            mode2 = Mode.MIXED;
                        } else if (i5 == 29) {
                            mode = Mode.PUNCT_SHIFT;
                            c2 = 0;
                            Mode mode4 = mode;
                            mode3 = mode2;
                            mode2 = mode4;
                            break;
                        } else if (i5 == 913) {
                            sb.append((char) iArr2[i4]);
                        } else if (i5 == 900) {
                            mode2 = Mode.ALPHA;
                        }
                        c2 = 0;
                        break;
                    }
                    break;
                case 2:
                    if (i5 < 26) {
                        i3 = i5 + 97;
                        c2 = (char) i3;
                        break;
                    } else if (i5 != 26) {
                        if (i5 != 27) {
                            if (i5 == 28) {
                                mode2 = Mode.MIXED;
                            } else if (i5 == 29) {
                                mode = Mode.PUNCT_SHIFT;
                            } else if (i5 == 913) {
                                sb.append((char) iArr2[i4]);
                            } else if (i5 == 900) {
                                mode2 = Mode.ALPHA;
                            }
                            c2 = 0;
                            break;
                        } else {
                            mode = Mode.ALPHA_SHIFT;
                        }
                        c2 = 0;
                        Mode mode42 = mode;
                        mode3 = mode2;
                        mode2 = mode42;
                        break;
                    }
                    break;
                case 3:
                    if (i5 < 25) {
                        c2 = MIXED_CHARS[i5];
                        break;
                    } else {
                        if (i5 == 25) {
                            mode2 = Mode.PUNCT;
                        } else if (i5 != 26) {
                            if (i5 == 27) {
                                mode2 = Mode.LOWER;
                            } else if (i5 == 28) {
                                mode2 = Mode.ALPHA;
                            } else if (i5 == 29) {
                                mode = Mode.PUNCT_SHIFT;
                                c2 = 0;
                                Mode mode422 = mode;
                                mode3 = mode2;
                                mode2 = mode422;
                                break;
                            } else if (i5 == 913) {
                                sb.append((char) iArr2[i4]);
                            } else if (i5 == 900) {
                                mode2 = Mode.ALPHA;
                            }
                        }
                        c2 = 0;
                        break;
                    }
                    break;
                case 4:
                    if (i5 < 29) {
                        c2 = PUNCT_CHARS[i5];
                        break;
                    } else {
                        if (i5 == 29) {
                            mode2 = Mode.ALPHA;
                        } else if (i5 == 913) {
                            sb.append((char) iArr2[i4]);
                        } else if (i5 == 900) {
                            mode2 = Mode.ALPHA;
                        }
                        c2 = 0;
                        break;
                    }
                case 5:
                    if (i5 < 26) {
                        c2 = (char) (i5 + 65);
                    } else if (i5 != 26) {
                        if (i5 == 900) {
                            mode2 = Mode.ALPHA;
                            c2 = 0;
                            break;
                        }
                        mode2 = mode3;
                        c2 = 0;
                    }
                    mode2 = mode3;
                    break;
                case 6:
                    if (i5 < 29) {
                        c2 = PUNCT_CHARS[i5];
                        mode2 = mode3;
                        break;
                    } else {
                        if (i5 == 29) {
                            mode2 = Mode.ALPHA;
                        } else {
                            if (i5 == 913) {
                                sb.append((char) iArr2[i4]);
                            } else if (i5 == 900) {
                                mode2 = Mode.ALPHA;
                            }
                            mode2 = mode3;
                        }
                        c2 = 0;
                        break;
                    }
                default:
                    c2 = 0;
                    break;
            }
            if (c2 != 0) {
                sb.append(c2);
            }
        }
    }

    private static int numericCompaction(int[] iArr, int i2, StringBuilder sb) throws FormatException {
        int[] iArr2 = new int[15];
        boolean z = false;
        int i3 = 0;
        while (i2 < iArr[0] && !z) {
            int i4 = i2 + 1;
            int i5 = iArr[i2];
            if (i4 == iArr[0]) {
                z = true;
            }
            if (i5 < 900) {
                iArr2[i3] = i5;
                i3++;
            } else if (i5 == 900 || i5 == 901 || i5 == 924 || i5 == 928 || i5 == 923 || i5 == 922) {
                i4--;
                z = true;
            }
            if (i3 % 15 == 0 || i5 == 902 || z) {
                sb.append(decodeBase900toBase10(iArr2, i3));
                i3 = 0;
            }
            i2 = i4;
        }
        return i2;
    }

    private static int textCompaction(int[] iArr, int i2, StringBuilder sb) {
        int[] iArr2 = new int[(iArr[0] - i2) * 2];
        int[] iArr3 = new int[(iArr[0] - i2) * 2];
        boolean z = false;
        int i3 = 0;
        while (i2 < iArr[0] && !z) {
            int i4 = i2 + 1;
            int i5 = iArr[i2];
            if (i5 < 900) {
                iArr2[i3] = i5 / 30;
                iArr2[i3 + 1] = i5 % 30;
                i3 += 2;
            } else if (i5 != 913) {
                if (i5 != 928) {
                    switch (i5) {
                        case 900:
                            iArr2[i3] = 900;
                            i3++;
                            break;
                        default:
                            switch (i5) {
                            }
                        case 901:
                        case 902:
                            i4--;
                            z = true;
                            break;
                    }
                }
                i4--;
                z = true;
            } else {
                iArr2[i3] = 913;
                i2 = i4 + 1;
                iArr3[i3] = iArr[i4];
                i3++;
            }
            i2 = i4;
        }
        decodeTextCompaction(iArr2, iArr3, i3, sb);
        return i2;
    }
}

package com.google.zxing.pdf417.encoder;

import com.google.common.primitives.SignedBytes;
import com.google.zxing.WriterException;
import com.google.zxing.common.CharacterSetECI;
import com.jd.wireless.sdk.intelligent.assistant.audio.record.Constant;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.nio.charset.UnsupportedCharsetException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import jd.wjlogin_sdk.util.ReplyCode;

/* loaded from: classes12.dex */
final class PDF417HighLevelEncoder {
    private static final int BYTE_COMPACTION = 1;
    private static final int ECI_CHARSET = 927;
    private static final int ECI_GENERAL_PURPOSE = 926;
    private static final int ECI_USER_DEFINED = 925;
    private static final int LATCH_TO_BYTE = 924;
    private static final int LATCH_TO_BYTE_PADDED = 901;
    private static final int LATCH_TO_NUMERIC = 902;
    private static final int LATCH_TO_TEXT = 900;
    private static final byte[] MIXED;
    private static final int NUMERIC_COMPACTION = 2;
    private static final int SHIFT_TO_BYTE = 913;
    private static final int SUBMODE_ALPHA = 0;
    private static final int SUBMODE_LOWER = 1;
    private static final int SUBMODE_MIXED = 2;
    private static final int SUBMODE_PUNCTUATION = 3;
    private static final int TEXT_COMPACTION = 0;
    private static final byte[] TEXT_MIXED_RAW = {48, ReplyCode.reply0x31, ReplyCode.reply0x32, 51, ReplyCode.reply0x34, ReplyCode.reply0x35, ReplyCode.reply0x36, ReplyCode.reply0x37, ReplyCode.reply0x38, ReplyCode.reply0x39, ReplyCode.reply0x26, 13, 9, 44, 58, ReplyCode.reply0x23, 45, 46, ReplyCode.reply0x24, 47, 43, ReplyCode.reply0x25, 42, 61, 94, 0, 32, 0, 0, 0};
    private static final byte[] TEXT_PUNCTUATION_RAW = {59, Constant.MAX_DURATION_DEFAULT, 62, SignedBytes.MAX_POWER_OF_TWO, 91, 92, 93, 95, 96, ReplyCode.reply0x7e, ReplyCode.reply0x21, 13, 9, 44, 58, 10, 45, 46, ReplyCode.reply0x24, 47, ReplyCode.reply0x22, 124, 42, ReplyCode.reply0x28, ReplyCode.reply0x29, 63, ReplyCode.reply0x7b, 125, ReplyCode.reply0x27, 0};
    private static final byte[] PUNCTUATION = new byte[128];
    private static final List<String> DEFAULT_ENCODING_NAMES = Arrays.asList("Cp437", "IBM437");

    static {
        byte[] bArr = new byte[128];
        MIXED = bArr;
        byte b = 0;
        Arrays.fill(bArr, (byte) -1);
        byte b2 = 0;
        while (true) {
            byte[] bArr2 = TEXT_MIXED_RAW;
            if (b2 >= bArr2.length) {
                break;
            }
            byte b3 = bArr2[b2];
            if (b3 > 0) {
                MIXED[b3] = b2;
            }
            b2 = (byte) (b2 + 1);
        }
        Arrays.fill(PUNCTUATION, (byte) -1);
        while (true) {
            byte[] bArr3 = TEXT_PUNCTUATION_RAW;
            if (b >= bArr3.length) {
                return;
            }
            byte b4 = bArr3[b];
            if (b4 > 0) {
                PUNCTUATION[b4] = b;
            }
            b = (byte) (b + 1);
        }
    }

    private PDF417HighLevelEncoder() {
    }

    /* JADX WARN: Code restructure failed: missing block: B:15:0x0026, code lost:
        return r1 - r9;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x003f, code lost:
        return r1 - r9;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static int determineConsecutiveBinaryCount(java.lang.CharSequence r7, byte[] r8, int r9) throws com.google.zxing.WriterException {
        /*
            int r0 = r7.length()
            r1 = r9
        L5:
            if (r1 >= r0) goto L74
            char r2 = r7.charAt(r1)
            r3 = 0
            r4 = 0
        Ld:
            r5 = 13
            if (r4 >= r5) goto L23
            boolean r6 = isDigit(r2)
            if (r6 == 0) goto L23
            int r4 = r4 + 1
            int r6 = r1 + r4
            if (r6 < r0) goto L1e
            goto L23
        L1e:
            char r2 = r7.charAt(r6)
            goto Ld
        L23:
            if (r4 < r5) goto L27
            int r1 = r1 - r9
            return r1
        L27:
            r4 = 5
            if (r3 >= r4) goto L3c
            boolean r2 = isText(r2)
            if (r2 == 0) goto L3c
            int r3 = r3 + 1
            int r2 = r1 + r3
            if (r2 < r0) goto L37
            goto L3c
        L37:
            char r2 = r7.charAt(r2)
            goto L27
        L3c:
            if (r3 < r4) goto L40
            int r1 = r1 - r9
            return r1
        L40:
            char r2 = r7.charAt(r1)
            r3 = r8[r1]
            r4 = 63
            if (r3 != r4) goto L71
            if (r2 != r4) goto L4d
            goto L71
        L4d:
            com.google.zxing.WriterException r7 = new com.google.zxing.WriterException
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            r8.<init>()
            java.lang.String r9 = "Non-encodable character detected: "
            r8.append(r9)
            r8.append(r2)
            java.lang.String r9 = " (Unicode: "
            r8.append(r9)
            r8.append(r2)
            r9 = 41
            r8.append(r9)
            java.lang.String r8 = r8.toString()
            r7.<init>(r8)
            throw r7
        L71:
            int r1 = r1 + 1
            goto L5
        L74:
            int r1 = r1 - r9
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.pdf417.encoder.PDF417HighLevelEncoder.determineConsecutiveBinaryCount(java.lang.CharSequence, byte[], int):int");
    }

    private static int determineConsecutiveDigitCount(CharSequence charSequence, int i2) {
        int length = charSequence.length();
        int i3 = 0;
        if (i2 < length) {
            char charAt = charSequence.charAt(i2);
            while (isDigit(charAt) && i2 < length) {
                i3++;
                i2++;
                if (i2 < length) {
                    charAt = charSequence.charAt(i2);
                }
            }
        }
        return i3;
    }

    /* JADX WARN: Code restructure failed: missing block: B:15:0x0027, code lost:
        return (r1 - r7) - r3;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static int determineConsecutiveTextCount(java.lang.CharSequence r6, int r7) {
        /*
            int r0 = r6.length()
            r1 = r7
        L5:
            if (r1 >= r0) goto L39
            char r2 = r6.charAt(r1)
            r3 = 0
        Lc:
            r4 = 13
            if (r3 >= r4) goto L23
            boolean r5 = isDigit(r2)
            if (r5 == 0) goto L23
            if (r1 >= r0) goto L23
            int r3 = r3 + 1
            int r1 = r1 + 1
            if (r1 >= r0) goto Lc
            char r2 = r6.charAt(r1)
            goto Lc
        L23:
            if (r3 < r4) goto L28
            int r1 = r1 - r7
            int r1 = r1 - r3
            return r1
        L28:
            if (r3 <= 0) goto L2b
            goto L5
        L2b:
            char r2 = r6.charAt(r1)
            boolean r2 = isText(r2)
            if (r2 != 0) goto L36
            goto L39
        L36:
            int r1 = r1 + 1
            goto L5
        L39:
            int r1 = r1 - r7
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.pdf417.encoder.PDF417HighLevelEncoder.determineConsecutiveTextCount(java.lang.CharSequence, int):int");
    }

    private static void encodeBinary(byte[] bArr, int i2, int i3, int i4, StringBuilder sb) {
        int i5;
        if (i3 == 1 && i4 == 0) {
            sb.append('\u0391');
        } else if (i3 % 6 == 0) {
            sb.append('\u039c');
        } else {
            sb.append('\u0385');
        }
        if (i3 >= 6) {
            char[] cArr = new char[5];
            i5 = i2;
            while ((i2 + i3) - i5 >= 6) {
                long j2 = 0;
                for (int i6 = 0; i6 < 6; i6++) {
                    j2 = (j2 << 8) + (bArr[i5 + i6] & 255);
                }
                for (int i7 = 0; i7 < 5; i7++) {
                    cArr[i7] = (char) (j2 % 900);
                    j2 /= 900;
                }
                for (int i8 = 4; i8 >= 0; i8--) {
                    sb.append(cArr[i8]);
                }
                i5 += 6;
            }
        } else {
            i5 = i2;
        }
        while (i5 < i2 + i3) {
            sb.append((char) (bArr[i5] & 255));
            i5++;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String encodeHighLevel(String str, Compaction compaction, Charset charset) throws WriterException {
        CharacterSetECI characterSetECIByName;
        StringBuilder sb = new StringBuilder(str.length());
        if (charset != null && !DEFAULT_ENCODING_NAMES.contains(charset.name()) && (characterSetECIByName = CharacterSetECI.getCharacterSetECIByName(charset.name())) != null) {
            encodingECI(characterSetECIByName.getValue(), sb);
        }
        int length = str.length();
        byte[] bArr = null;
        if (compaction == Compaction.TEXT) {
            encodeText(str, 0, length, sb, 0);
        } else if (compaction == Compaction.BYTE) {
            byte[] bytes = toBytes(str, charset);
            encodeBinary(bytes, 0, bytes.length, 1, sb);
        } else if (compaction == Compaction.NUMERIC) {
            sb.append('\u0386');
            encodeNumeric(str, 0, length, sb);
        } else {
            int i2 = 0;
            int i3 = 0;
            int i4 = 0;
            while (i2 < length) {
                int determineConsecutiveDigitCount = determineConsecutiveDigitCount(str, i2);
                if (determineConsecutiveDigitCount >= 13) {
                    sb.append('\u0386');
                    encodeNumeric(str, i2, determineConsecutiveDigitCount, sb);
                    i2 += determineConsecutiveDigitCount;
                    i3 = 0;
                    i4 = 2;
                } else {
                    int determineConsecutiveTextCount = determineConsecutiveTextCount(str, i2);
                    if (determineConsecutiveTextCount < 5 && determineConsecutiveDigitCount != length) {
                        if (bArr == null) {
                            bArr = toBytes(str, charset);
                        }
                        int determineConsecutiveBinaryCount = determineConsecutiveBinaryCount(str, bArr, i2);
                        if (determineConsecutiveBinaryCount == 0) {
                            determineConsecutiveBinaryCount = 1;
                        }
                        if (determineConsecutiveBinaryCount == 1 && i4 == 0) {
                            encodeBinary(bArr, i2, 1, 0, sb);
                        } else {
                            encodeBinary(bArr, i2, determineConsecutiveBinaryCount, i4, sb);
                            i3 = 0;
                            i4 = 1;
                        }
                        i2 += determineConsecutiveBinaryCount;
                    } else {
                        if (i4 != 0) {
                            sb.append('\u0384');
                            i3 = 0;
                            i4 = 0;
                        }
                        i3 = encodeText(str, i2, determineConsecutiveTextCount, sb, i3);
                        i2 += determineConsecutiveTextCount;
                    }
                }
            }
        }
        return sb.toString();
    }

    private static void encodeNumeric(String str, int i2, int i3, StringBuilder sb) {
        StringBuilder sb2 = new StringBuilder((i3 / 3) + 1);
        BigInteger valueOf = BigInteger.valueOf(900L);
        BigInteger valueOf2 = BigInteger.valueOf(0L);
        int i4 = 0;
        while (i4 < i3 - 1) {
            sb2.setLength(0);
            int min = Math.min(44, i3 - i4);
            StringBuilder sb3 = new StringBuilder();
            sb3.append('1');
            int i5 = i2 + i4;
            sb3.append(str.substring(i5, i5 + min));
            BigInteger bigInteger = new BigInteger(sb3.toString());
            do {
                sb2.append((char) bigInteger.mod(valueOf).intValue());
                bigInteger = bigInteger.divide(valueOf);
            } while (!bigInteger.equals(valueOf2));
            for (int length = sb2.length() - 1; length >= 0; length--) {
                sb.append(sb2.charAt(length));
            }
            i4 += min;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:76:0x00f6 A[EDGE_INSN: B:76:0x00f6->B:55:0x00f6 BREAK  A[LOOP:0: B:3:0x0011->B:93:0x0011], SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:95:0x0011 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static int encodeText(java.lang.CharSequence r16, int r17, int r18, java.lang.StringBuilder r19, int r20) {
        /*
            Method dump skipped, instructions count: 293
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.pdf417.encoder.PDF417HighLevelEncoder.encodeText(java.lang.CharSequence, int, int, java.lang.StringBuilder, int):int");
    }

    private static void encodingECI(int i2, StringBuilder sb) throws WriterException {
        if (i2 >= 0 && i2 < 900) {
            sb.append('\u039f');
            sb.append((char) i2);
        } else if (i2 < 810900) {
            sb.append('\u039e');
            sb.append((char) ((i2 / 900) - 1));
            sb.append((char) (i2 % 900));
        } else if (i2 < 811800) {
            sb.append('\u039d');
            sb.append((char) (810900 - i2));
        } else {
            throw new WriterException("ECI number not in valid range from 0..811799, but was " + i2);
        }
    }

    private static boolean isAlphaLower(char c2) {
        return c2 == ' ' || (c2 >= 'a' && c2 <= 'z');
    }

    private static boolean isAlphaUpper(char c2) {
        return c2 == ' ' || (c2 >= 'A' && c2 <= 'Z');
    }

    private static boolean isDigit(char c2) {
        return c2 >= '0' && c2 <= '9';
    }

    private static boolean isMixed(char c2) {
        return MIXED[c2] != -1;
    }

    private static boolean isPunctuation(char c2) {
        return PUNCTUATION[c2] != -1;
    }

    private static boolean isText(char c2) {
        return c2 == '\t' || c2 == '\n' || c2 == '\r' || (c2 >= ' ' && c2 <= '~');
    }

    private static byte[] toBytes(String str, Charset charset) throws WriterException {
        if (charset == null) {
            Iterator<String> it = DEFAULT_ENCODING_NAMES.iterator();
            while (it.hasNext()) {
                try {
                    charset = Charset.forName(it.next());
                } catch (UnsupportedCharsetException unused) {
                }
            }
            if (charset == null) {
                throw new WriterException("No support for any encoding: " + DEFAULT_ENCODING_NAMES);
            }
        }
        return str.getBytes(charset);
    }
}

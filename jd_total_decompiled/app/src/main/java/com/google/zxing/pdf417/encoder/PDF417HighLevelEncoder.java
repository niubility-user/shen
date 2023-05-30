package com.google.zxing.pdf417.encoder;

import com.alibaba.fastjson.parser.JSONLexer;
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
    */
    private static int determineConsecutiveBinaryCount(CharSequence charSequence, byte[] bArr, int i2) throws WriterException {
        int i3;
        int i4;
        int length = charSequence.length();
        int i5 = i2;
        while (i5 < length) {
            char charAt = charSequence.charAt(i5);
            int i6 = 0;
            int i7 = 0;
            while (i7 < 13 && isDigit(charAt) && (i4 = i5 + (i7 = i7 + 1)) < length) {
                charAt = charSequence.charAt(i4);
            }
            while (i6 < 5 && isText(charAt) && (i3 = i5 + (i6 = i6 + 1)) < length) {
                charAt = charSequence.charAt(i3);
            }
            char charAt2 = charSequence.charAt(i5);
            if (bArr[i5] == 63 && charAt2 != '?') {
                throw new WriterException("Non-encodable character detected: " + charAt2 + " (Unicode: " + ((int) charAt2) + ')');
            }
            i5++;
        }
        return i5 - i2;
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
    */
    private static int determineConsecutiveTextCount(CharSequence charSequence, int i2) {
        int length = charSequence.length();
        int i3 = i2;
        while (i3 < length) {
            char charAt = charSequence.charAt(i3);
            int i4 = 0;
            while (i4 < 13 && isDigit(charAt) && i3 < length) {
                i4++;
                i3++;
                if (i3 < length) {
                    charAt = charSequence.charAt(i3);
                }
            }
            if (i4 <= 0) {
                if (!isText(charSequence.charAt(i3))) {
                    break;
                }
                i3++;
            }
        }
        return i3 - i2;
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
    */
    private static int encodeText(CharSequence charSequence, int i2, int i3, StringBuilder sb, int i4) {
        StringBuilder sb2 = new StringBuilder(i3);
        int i5 = i4;
        int i6 = 0;
        while (true) {
            int i7 = i2 + i6;
            char charAt = charSequence.charAt(i7);
            if (i5 != 0) {
                if (i5 != 1) {
                    if (i5 != 2) {
                        if (isPunctuation(charAt)) {
                            sb2.append((char) PUNCTUATION[charAt]);
                        } else {
                            sb2.append((char) 29);
                            i5 = 0;
                        }
                    } else if (isMixed(charAt)) {
                        sb2.append((char) MIXED[charAt]);
                    } else if (isAlphaUpper(charAt)) {
                        sb2.append((char) 28);
                        i5 = 0;
                    } else if (isAlphaLower(charAt)) {
                        sb2.append((char) 27);
                        i5 = 1;
                    } else {
                        int i8 = i7 + 1;
                        if (i8 < i3 && isPunctuation(charSequence.charAt(i8))) {
                            i5 = 3;
                            sb2.append((char) 25);
                        } else {
                            sb2.append((char) 29);
                            sb2.append((char) PUNCTUATION[charAt]);
                        }
                    }
                } else if (isAlphaLower(charAt)) {
                    if (charAt == ' ') {
                        sb2.append(JSONLexer.EOI);
                    } else {
                        sb2.append((char) (charAt - 'a'));
                    }
                } else if (isAlphaUpper(charAt)) {
                    sb2.append((char) 27);
                    sb2.append((char) (charAt - 'A'));
                } else if (isMixed(charAt)) {
                    sb2.append((char) 28);
                    i5 = 2;
                } else {
                    sb2.append((char) 29);
                    sb2.append((char) PUNCTUATION[charAt]);
                }
                i6++;
                if (i6 < i3) {
                    break;
                }
            } else {
                if (isAlphaUpper(charAt)) {
                    if (charAt == ' ') {
                        sb2.append(JSONLexer.EOI);
                    } else {
                        sb2.append((char) (charAt - 'A'));
                    }
                } else if (isAlphaLower(charAt)) {
                    sb2.append((char) 27);
                    i5 = 1;
                } else if (isMixed(charAt)) {
                    sb2.append((char) 28);
                    i5 = 2;
                } else {
                    sb2.append((char) 29);
                    sb2.append((char) PUNCTUATION[charAt]);
                }
                i6++;
                if (i6 < i3) {
                }
            }
        }
        int length = sb2.length();
        char c2 = 0;
        for (int i9 = 0; i9 < length; i9++) {
            if (i9 % 2 != 0) {
                c2 = (char) ((c2 * 30) + sb2.charAt(i9));
                sb.append(c2);
            } else {
                c2 = sb2.charAt(i9);
            }
        }
        if (length % 2 != 0) {
            sb.append((char) ((c2 * 30) + 29));
        }
        return i5;
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

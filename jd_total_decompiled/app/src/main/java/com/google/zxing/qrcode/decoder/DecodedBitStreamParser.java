package com.google.zxing.qrcode.decoder;

import com.google.zxing.DecodeHintType;
import com.google.zxing.FormatException;
import com.google.zxing.common.BitSource;
import com.google.zxing.common.CharacterSetECI;
import com.google.zxing.common.DecoderResult;
import com.google.zxing.common.StringUtils;
import com.jingdong.app.mall.bundle.order_center_isv_core.util.OrderISVUtil;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import kotlin.text.Typography;

/* loaded from: classes12.dex */
final class DecodedBitStreamParser {
    private static final char[] ALPHANUMERIC_CHARS = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', ' ', Typography.dollar, '%', '*', '+', '-', OrderISVUtil.MONEY_DECIMAL_CHAR, '/', ':'};
    private static final int GB2312_SUBSET = 1;

    private DecodedBitStreamParser() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:60:0x00ea A[LOOP:0: B:64:0x001d->B:60:0x00ea, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:65:0x00c9 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static DecoderResult decode(byte[] bArr, Version version, ErrorCorrectionLevel errorCorrectionLevel, Map<DecodeHintType, ?> map) throws FormatException {
        Mode forBits;
        Mode mode;
        Mode mode2;
        BitSource bitSource = new BitSource(bArr);
        StringBuilder sb = new StringBuilder(50);
        int i2 = 1;
        ArrayList arrayList = new ArrayList(1);
        boolean z = false;
        int i3 = -1;
        int i4 = -1;
        CharacterSetECI characterSetECI = null;
        while (true) {
            try {
                if (bitSource.available() < 4) {
                    forBits = Mode.TERMINATOR;
                } else {
                    forBits = Mode.forBits(bitSource.readBits(4));
                }
                Mode mode3 = forBits;
                Mode mode4 = Mode.TERMINATOR;
                if (mode3 != mode4) {
                    if (mode3 != Mode.FNC1_FIRST_POSITION && mode3 != Mode.FNC1_SECOND_POSITION) {
                        if (mode3 == Mode.STRUCTURED_APPEND) {
                            if (bitSource.available() >= 16) {
                                int readBits = bitSource.readBits(8);
                                i4 = bitSource.readBits(8);
                                i3 = readBits;
                            } else {
                                throw FormatException.getFormatInstance();
                            }
                        } else if (mode3 == Mode.ECI) {
                            characterSetECI = CharacterSetECI.getCharacterSetECIByValue(parseECIValue(bitSource));
                            if (characterSetECI == null) {
                                throw FormatException.getFormatInstance();
                            }
                        } else if (mode3 == Mode.HANZI) {
                            int readBits2 = bitSource.readBits(4);
                            int readBits3 = bitSource.readBits(mode3.getCharacterCountBits(version));
                            if (readBits2 == i2) {
                                decodeHanziSegment(bitSource, sb, readBits3);
                            }
                        } else {
                            int readBits4 = bitSource.readBits(mode3.getCharacterCountBits(version));
                            if (mode3 == Mode.NUMERIC) {
                                decodeNumericSegment(bitSource, sb, readBits4);
                            } else if (mode3 == Mode.ALPHANUMERIC) {
                                decodeAlphanumericSegment(bitSource, sb, readBits4, z);
                            } else {
                                if (mode3 == Mode.BYTE) {
                                    mode = mode4;
                                    mode2 = mode3;
                                    decodeByteSegment(bitSource, sb, readBits4, characterSetECI, arrayList, map);
                                } else {
                                    mode = mode4;
                                    mode2 = mode3;
                                    if (mode2 == Mode.KANJI) {
                                        decodeKanjiSegment(bitSource, sb, readBits4);
                                    } else {
                                        throw FormatException.getFormatInstance();
                                    }
                                }
                                if (mode2 == mode) {
                                    return new DecoderResult(bArr, sb.toString(), arrayList.isEmpty() ? null : arrayList, errorCorrectionLevel == null ? null : errorCorrectionLevel.toString(), i3, i4);
                                }
                                i2 = 1;
                            }
                        }
                    }
                    mode = mode4;
                    mode2 = mode3;
                    z = true;
                    if (mode2 == mode) {
                    }
                }
                mode = mode4;
                mode2 = mode3;
                if (mode2 == mode) {
                }
            } catch (IllegalArgumentException unused) {
                throw FormatException.getFormatInstance();
            }
        }
    }

    private static void decodeAlphanumericSegment(BitSource bitSource, StringBuilder sb, int i2, boolean z) throws FormatException {
        while (i2 > 1) {
            if (bitSource.available() >= 11) {
                int readBits = bitSource.readBits(11);
                sb.append(toAlphaNumericChar(readBits / 45));
                sb.append(toAlphaNumericChar(readBits % 45));
                i2 -= 2;
            } else {
                throw FormatException.getFormatInstance();
            }
        }
        if (i2 == 1) {
            if (bitSource.available() >= 6) {
                sb.append(toAlphaNumericChar(bitSource.readBits(6)));
            } else {
                throw FormatException.getFormatInstance();
            }
        }
        if (z) {
            for (int length = sb.length(); length < sb.length(); length++) {
                if (sb.charAt(length) == '%') {
                    if (length < sb.length() - 1) {
                        int i3 = length + 1;
                        if (sb.charAt(i3) == '%') {
                            sb.deleteCharAt(i3);
                        }
                    }
                    sb.setCharAt(length, (char) 29);
                }
            }
        }
    }

    private static void decodeByteSegment(BitSource bitSource, StringBuilder sb, int i2, CharacterSetECI characterSetECI, Collection<byte[]> collection, Map<DecodeHintType, ?> map) throws FormatException {
        String name;
        if (i2 * 8 <= bitSource.available()) {
            byte[] bArr = new byte[i2];
            for (int i3 = 0; i3 < i2; i3++) {
                bArr[i3] = (byte) bitSource.readBits(8);
            }
            if (characterSetECI == null) {
                name = StringUtils.guessEncoding(bArr, map);
            } else {
                name = characterSetECI.name();
            }
            try {
                sb.append(new String(bArr, name));
                collection.add(bArr);
                return;
            } catch (UnsupportedEncodingException unused) {
                throw FormatException.getFormatInstance();
            }
        }
        throw FormatException.getFormatInstance();
    }

    private static void decodeHanziSegment(BitSource bitSource, StringBuilder sb, int i2) throws FormatException {
        if (i2 * 13 <= bitSource.available()) {
            byte[] bArr = new byte[i2 * 2];
            int i3 = 0;
            while (i2 > 0) {
                int readBits = bitSource.readBits(13);
                int i4 = (readBits % 96) | ((readBits / 96) << 8);
                int i5 = i4 + (i4 < 959 ? 41377 : 42657);
                bArr[i3] = (byte) ((i5 >> 8) & 255);
                bArr[i3 + 1] = (byte) (i5 & 255);
                i3 += 2;
                i2--;
            }
            try {
                sb.append(new String(bArr, StringUtils.GB2312));
                return;
            } catch (UnsupportedEncodingException unused) {
                throw FormatException.getFormatInstance();
            }
        }
        throw FormatException.getFormatInstance();
    }

    private static void decodeKanjiSegment(BitSource bitSource, StringBuilder sb, int i2) throws FormatException {
        if (i2 * 13 <= bitSource.available()) {
            byte[] bArr = new byte[i2 * 2];
            int i3 = 0;
            while (i2 > 0) {
                int readBits = bitSource.readBits(13);
                int i4 = (readBits % 192) | ((readBits / 192) << 8);
                int i5 = i4 + (i4 < 7936 ? 33088 : 49472);
                bArr[i3] = (byte) (i5 >> 8);
                bArr[i3 + 1] = (byte) i5;
                i3 += 2;
                i2--;
            }
            try {
                sb.append(new String(bArr, StringUtils.SHIFT_JIS));
                return;
            } catch (UnsupportedEncodingException unused) {
                throw FormatException.getFormatInstance();
            }
        }
        throw FormatException.getFormatInstance();
    }

    private static void decodeNumericSegment(BitSource bitSource, StringBuilder sb, int i2) throws FormatException {
        while (i2 >= 3) {
            if (bitSource.available() >= 10) {
                int readBits = bitSource.readBits(10);
                if (readBits < 1000) {
                    sb.append(toAlphaNumericChar(readBits / 100));
                    sb.append(toAlphaNumericChar((readBits / 10) % 10));
                    sb.append(toAlphaNumericChar(readBits % 10));
                    i2 -= 3;
                } else {
                    throw FormatException.getFormatInstance();
                }
            } else {
                throw FormatException.getFormatInstance();
            }
        }
        if (i2 == 2) {
            if (bitSource.available() >= 7) {
                int readBits2 = bitSource.readBits(7);
                if (readBits2 < 100) {
                    sb.append(toAlphaNumericChar(readBits2 / 10));
                    sb.append(toAlphaNumericChar(readBits2 % 10));
                    return;
                }
                throw FormatException.getFormatInstance();
            }
            throw FormatException.getFormatInstance();
        } else if (i2 == 1) {
            if (bitSource.available() >= 4) {
                int readBits3 = bitSource.readBits(4);
                if (readBits3 < 10) {
                    sb.append(toAlphaNumericChar(readBits3));
                    return;
                }
                throw FormatException.getFormatInstance();
            }
            throw FormatException.getFormatInstance();
        }
    }

    private static int parseECIValue(BitSource bitSource) throws FormatException {
        int readBits = bitSource.readBits(8);
        if ((readBits & 128) == 0) {
            return readBits & 127;
        }
        if ((readBits & 192) == 128) {
            return bitSource.readBits(8) | ((readBits & 63) << 8);
        }
        if ((readBits & 224) == 192) {
            return bitSource.readBits(16) | ((readBits & 31) << 16);
        }
        throw FormatException.getFormatInstance();
    }

    private static char toAlphaNumericChar(int i2) throws FormatException {
        char[] cArr = ALPHANUMERIC_CHARS;
        if (i2 < cArr.length) {
            return cArr[i2];
        }
        throw FormatException.getFormatInstance();
    }
}

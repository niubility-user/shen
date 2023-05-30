package com.google.zxing.maxicode.decoder;

import com.google.zxing.common.DecoderResult;
import com.unionpay.tsmservice.data.Constant;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import jd.wjlogin_sdk.util.ReplyCode;

/* loaded from: classes12.dex */
final class DecodedBitStreamParser {
    private static final char ECI = '\ufffa';
    private static final char FS = 28;
    private static final char GS = 29;
    private static final char LATCHA = '\ufff7';
    private static final char LATCHB = '\ufff8';
    private static final char LOCK = '\ufff9';
    private static final char NS = '\ufffb';
    private static final char PAD = '\ufffc';
    private static final char RS = 30;
    private static final char SHIFTA = '\ufff0';
    private static final char SHIFTB = '\ufff1';
    private static final char SHIFTC = '\ufff2';
    private static final char SHIFTD = '\ufff3';
    private static final char SHIFTE = '\ufff4';
    private static final char THREESHIFTA = '\ufff6';
    private static final char TWOSHIFTA = '\ufff5';
    private static final NumberFormat NINE_DIGITS = new DecimalFormat("000000000");
    private static final NumberFormat THREE_DIGITS = new DecimalFormat(Constant.DEFAULT_CVN2);
    private static final String[] SETS = {"\nABCDEFGHIJKLMNOPQRSTUVWXYZ\ufffa\u001c\u001d\u001e\ufffb \ufffc\"#$%&'()*+,-./0123456789:\ufff1\ufff2\ufff3\ufff4\ufff8", "`abcdefghijklmnopqrstuvwxyz\ufffa\u001c\u001d\u001e\ufffb{\ufffc}~\u007f;<=>?[\\]^_ ,./:@!|\ufffc\ufff5\ufff6\ufffc\ufff0\ufff2\ufff3\ufff4\ufff7", "\u00c0\u00c1\u00c2\u00c3\u00c4\u00c5\u00c6\u00c7\u00c8\u00c9\u00ca\u00cb\u00cc\u00cd\u00ce\u00cf\u00d0\u00d1\u00d2\u00d3\u00d4\u00d5\u00d6\u00d7\u00d8\u00d9\u00da\ufffa\u001c\u001d\u001e\u00db\u00dc\u00dd\u00de\u00df\u00aa\u00ac\u00b1\u00b2\u00b3\u00b5\u00b9\u00ba\u00bc\u00bd\u00be\u0080\u0081\u0082\u0083\u0084\u0085\u0086\u0087\u0088\u0089\ufff7 \ufff9\ufff3\ufff4\ufff8", "\u00e0\u00e1\u00e2\u00e3\u00e4\u00e5\u00e6\u00e7\u00e8\u00e9\u00ea\u00eb\u00ec\u00ed\u00ee\u00ef\u00f0\u00f1\u00f2\u00f3\u00f4\u00f5\u00f6\u00f7\u00f8\u00f9\u00fa\ufffa\u001c\u001d\u001e\ufffb\u00fb\u00fc\u00fd\u00fe\u00ff\u00a1\u00a8\u00ab\u00af\u00b0\u00b4\u00b7\u00b8\u00bb\u00bf\u008a\u008b\u008c\u008d\u008e\u008f\u0090\u0091\u0092\u0093\u0094\ufff7 \ufff2\ufff9\ufff4\ufff8", "\u0000\u0001\u0002\u0003\u0004\u0005\u0006\u0007\b\t\n\u000b\f\r\u000e\u000f\u0010\u0011\u0012\u0013\u0014\u0015\u0016\u0017\u0018\u0019\u001a\ufffa\ufffc\ufffc\u001b\ufffb\u001c\u001d\u001e\u001f\u009f\u00a0\u00a2\u00a3\u00a4\u00a5\u00a6\u00a7\u00a9\u00ad\u00ae\u00b6\u0095\u0096\u0097\u0098\u0099\u009a\u009b\u009c\u009d\u009e\ufff7 \ufff2\ufff3\ufff9\ufff8", "\u0000\u0001\u0002\u0003\u0004\u0005\u0006\u0007\b\t\n\u000b\f\r\u000e\u000f\u0010\u0011\u0012\u0013\u0014\u0015\u0016\u0017\u0018\u0019\u001a\u001b\u001c\u001d\u001e\u001f !\"#$%&'()*+,-./0123456789:;<=>?"};

    private DecodedBitStreamParser() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static DecoderResult decode(byte[] bArr, int i2) {
        String postCode3;
        StringBuilder sb = new StringBuilder(144);
        if (i2 == 2 || i2 == 3) {
            if (i2 == 2) {
                postCode3 = new DecimalFormat("0000000000".substring(0, getPostCode2Length(bArr))).format(getPostCode2(bArr));
            } else {
                postCode3 = getPostCode3(bArr);
            }
            NumberFormat numberFormat = THREE_DIGITS;
            String format = numberFormat.format(getCountry(bArr));
            String format2 = numberFormat.format(getServiceClass(bArr));
            sb.append(getMessage(bArr, 10, 84));
            if (sb.toString().startsWith("[)>\u001e01\u001d")) {
                sb.insert(9, postCode3 + GS + format + GS + format2 + GS);
            } else {
                sb.insert(0, postCode3 + GS + format + GS + format2 + GS);
            }
        } else if (i2 == 4) {
            sb.append(getMessage(bArr, 1, 93));
        } else if (i2 == 5) {
            sb.append(getMessage(bArr, 1, 77));
        }
        return new DecoderResult(bArr, sb.toString(), null, String.valueOf(i2));
    }

    private static int getBit(int i2, byte[] bArr) {
        int i3 = i2 - 1;
        return ((1 << (5 - (i3 % 6))) & bArr[i3 / 6]) == 0 ? 0 : 1;
    }

    private static int getCountry(byte[] bArr) {
        return getInt(bArr, new byte[]{ReplyCode.reply0x35, ReplyCode.reply0x36, 43, 44, 45, 46, 47, 48, ReplyCode.reply0x25, ReplyCode.reply0x26});
    }

    private static int getInt(byte[] bArr, byte[] bArr2) {
        if (bArr2.length != 0) {
            int i2 = 0;
            for (int i3 = 0; i3 < bArr2.length; i3++) {
                i2 += getBit(bArr2[i3], bArr) << ((bArr2.length - i3) - 1);
            }
            return i2;
        }
        throw new IllegalArgumentException();
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private static String getMessage(byte[] bArr, int i2, int i3) {
        StringBuilder sb = new StringBuilder();
        int i4 = i2;
        int i5 = 0;
        int i6 = -1;
        int i7 = 0;
        while (i4 < i2 + i3) {
            char charAt = SETS[i5].charAt(bArr[i4]);
            switch (charAt) {
                case '\ufff0':
                case '\ufff1':
                case '\ufff2':
                case '\ufff3':
                case '\ufff4':
                    i7 = i5;
                    i5 = charAt - SHIFTA;
                    i6 = 1;
                    break;
                case '\ufff5':
                    i6 = 2;
                    i7 = i5;
                    i5 = 0;
                    break;
                case '\ufff6':
                    i6 = 3;
                    i7 = i5;
                    i5 = 0;
                    break;
                case '\ufff7':
                    i5 = 0;
                    i6 = -1;
                    break;
                case '\ufff8':
                    i5 = 1;
                    i6 = -1;
                    break;
                case '\ufff9':
                    i6 = -1;
                    break;
                case '\ufffa':
                default:
                    sb.append(charAt);
                    break;
                case '\ufffb':
                    i4 = i4 + 1 + 1 + 1 + 1 + 1;
                    sb.append(NINE_DIGITS.format((bArr[r3] << 24) + (bArr[r3] << 18) + (bArr[r3] << 12) + (bArr[r3] << 6) + bArr[i4]));
                    break;
            }
            int i8 = i6 - 1;
            if (i6 == 0) {
                i5 = i7;
            }
            i4++;
            i6 = i8;
        }
        while (sb.length() > 0 && sb.charAt(sb.length() - 1) == '\ufffc') {
            sb.setLength(sb.length() - 1);
        }
        return sb.toString();
    }

    private static int getPostCode2(byte[] bArr) {
        return getInt(bArr, new byte[]{ReplyCode.reply0x21, ReplyCode.reply0x22, ReplyCode.reply0x23, ReplyCode.reply0x24, 25, 26, 27, 28, 29, 30, 19, 20, 21, 22, 23, 24, 13, 14, 15, 16, 17, 18, 7, 8, 9, 10, 11, 12, 1, 2});
    }

    private static int getPostCode2Length(byte[] bArr) {
        return getInt(bArr, new byte[]{ReplyCode.reply0x27, ReplyCode.reply0x28, ReplyCode.reply0x29, 42, 31, 32});
    }

    private static String getPostCode3(byte[] bArr) {
        String[] strArr = SETS;
        return String.valueOf(new char[]{strArr[0].charAt(getInt(bArr, new byte[]{ReplyCode.reply0x27, ReplyCode.reply0x28, ReplyCode.reply0x29, 42, 31, 32})), strArr[0].charAt(getInt(bArr, new byte[]{ReplyCode.reply0x21, ReplyCode.reply0x22, ReplyCode.reply0x23, ReplyCode.reply0x24, 25, 26})), strArr[0].charAt(getInt(bArr, new byte[]{27, 28, 29, 30, 19, 20})), strArr[0].charAt(getInt(bArr, new byte[]{21, 22, 23, 24, 13, 14})), strArr[0].charAt(getInt(bArr, new byte[]{15, 16, 17, 18, 7, 8})), strArr[0].charAt(getInt(bArr, new byte[]{9, 10, 11, 12, 1, 2}))});
    }

    private static int getServiceClass(byte[] bArr) {
        return getInt(bArr, new byte[]{ReplyCode.reply0x37, ReplyCode.reply0x38, ReplyCode.reply0x39, 58, 59, com.jd.wireless.sdk.intelligent.assistant.audio.record.Constant.MAX_DURATION_DEFAULT, ReplyCode.reply0x31, ReplyCode.reply0x32, 51, ReplyCode.reply0x34});
    }
}

package org.apache.commons.codec.binary;

import com.jd.wireless.sdk.intelligent.assistant.audio.record.Constant;
import java.math.BigInteger;
import jd.wjlogin_sdk.util.ReplyCode;
import org.apache.commons.codec.binary.BaseNCodec;

/* loaded from: classes11.dex */
public class Base64 extends BaseNCodec {
    private static final int BITS_PER_ENCODED_BYTE = 6;
    private static final int BYTES_PER_ENCODED_BLOCK = 4;
    private static final int BYTES_PER_UNENCODED_BLOCK = 3;
    private static final int MASK_6BITS = 63;
    private final int decodeSize;
    private final byte[] decodeTable;
    private final int encodeSize;
    private final byte[] encodeTable;
    private final byte[] lineSeparator;
    static final byte[] CHUNK_SEPARATOR = {13, 10};
    private static final byte[] STANDARD_ENCODE_TABLE = {65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, ReplyCode.reply0x4f, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 97, 98, 99, ReplyCode.reply0x64, 101, 102, ReplyCode.reply0x67, ReplyCode.reply0x68, 105, ReplyCode.reply0x6a, 107, 108, 109, 110, 111, 112, 113, 114, ReplyCode.reply0x73, 116, 117, 118, ReplyCode.reply0x77, ReplyCode.reply0x78, 121, ReplyCode.reply0x7a, 48, ReplyCode.reply0x31, ReplyCode.reply0x32, 51, ReplyCode.reply0x34, ReplyCode.reply0x35, ReplyCode.reply0x36, ReplyCode.reply0x37, ReplyCode.reply0x38, ReplyCode.reply0x39, 43, 47};
    private static final byte[] URL_SAFE_ENCODE_TABLE = {65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, ReplyCode.reply0x4f, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 97, 98, 99, ReplyCode.reply0x64, 101, 102, ReplyCode.reply0x67, ReplyCode.reply0x68, 105, ReplyCode.reply0x6a, 107, 108, 109, 110, 111, 112, 113, 114, ReplyCode.reply0x73, 116, 117, 118, ReplyCode.reply0x77, ReplyCode.reply0x78, 121, ReplyCode.reply0x7a, 48, ReplyCode.reply0x31, ReplyCode.reply0x32, 51, ReplyCode.reply0x34, ReplyCode.reply0x35, ReplyCode.reply0x36, ReplyCode.reply0x37, ReplyCode.reply0x38, ReplyCode.reply0x39, 45, 95};
    private static final byte[] DECODE_TABLE = {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 62, -1, 62, -1, 63, ReplyCode.reply0x34, ReplyCode.reply0x35, ReplyCode.reply0x36, ReplyCode.reply0x37, ReplyCode.reply0x38, ReplyCode.reply0x39, 58, 59, Constant.MAX_DURATION_DEFAULT, 61, -1, -1, -1, -1, -1, -1, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, -1, -1, -1, -1, 63, -1, 26, 27, 28, 29, 30, 31, 32, ReplyCode.reply0x21, ReplyCode.reply0x22, ReplyCode.reply0x23, ReplyCode.reply0x24, ReplyCode.reply0x25, ReplyCode.reply0x26, ReplyCode.reply0x27, ReplyCode.reply0x28, ReplyCode.reply0x29, 42, 43, 44, 45, 46, 47, 48, ReplyCode.reply0x31, ReplyCode.reply0x32, 51};

    public Base64() {
        this(0);
    }

    public static byte[] decodeBase64(String str) {
        return new Base64().decode(str);
    }

    public static BigInteger decodeInteger(byte[] bArr) {
        return new BigInteger(1, decodeBase64(bArr));
    }

    public static byte[] encodeBase64(byte[] bArr) {
        return encodeBase64(bArr, false);
    }

    public static byte[] encodeBase64Chunked(byte[] bArr) {
        return encodeBase64(bArr, true);
    }

    public static String encodeBase64String(byte[] bArr) {
        return StringUtils.newStringUtf8(encodeBase64(bArr, false));
    }

    public static byte[] encodeBase64URLSafe(byte[] bArr) {
        return encodeBase64(bArr, false, true);
    }

    public static String encodeBase64URLSafeString(byte[] bArr) {
        return StringUtils.newStringUtf8(encodeBase64(bArr, false, true));
    }

    public static byte[] encodeInteger(BigInteger bigInteger) {
        if (bigInteger != null) {
            return encodeBase64(toIntegerBytes(bigInteger), false);
        }
        throw new NullPointerException("encodeInteger called with null parameter");
    }

    @Deprecated
    public static boolean isArrayByteBase64(byte[] bArr) {
        return isBase64(bArr);
    }

    public static boolean isBase64(byte b) {
        if (b != 61) {
            if (b >= 0) {
                byte[] bArr = DECODE_TABLE;
                if (b >= bArr.length || bArr[b] == -1) {
                }
            }
            return false;
        }
        return true;
    }

    static byte[] toIntegerBytes(BigInteger bigInteger) {
        int bitLength = ((bigInteger.bitLength() + 7) >> 3) << 3;
        byte[] byteArray = bigInteger.toByteArray();
        int i2 = 1;
        if (bigInteger.bitLength() % 8 == 0 || (bigInteger.bitLength() / 8) + 1 != bitLength / 8) {
            int length = byteArray.length;
            if (bigInteger.bitLength() % 8 == 0) {
                length--;
            } else {
                i2 = 0;
            }
            int i3 = bitLength / 8;
            int i4 = i3 - length;
            byte[] bArr = new byte[i3];
            System.arraycopy(byteArray, i2, bArr, i4, length);
            return bArr;
        }
        return byteArray;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.apache.commons.codec.binary.BaseNCodec
    public void decode(byte[] bArr, int i2, int i3, BaseNCodec.Context context) {
        byte b;
        if (context.eof) {
            return;
        }
        if (i3 < 0) {
            context.eof = true;
        }
        int i4 = 0;
        while (true) {
            if (i4 >= i3) {
                break;
            }
            byte[] ensureBufferSize = ensureBufferSize(this.decodeSize, context);
            int i5 = i2 + 1;
            byte b2 = bArr[i2];
            if (b2 == this.pad) {
                context.eof = true;
                break;
            }
            if (b2 >= 0) {
                byte[] bArr2 = DECODE_TABLE;
                if (b2 < bArr2.length && (b = bArr2[b2]) >= 0) {
                    int i6 = (context.modulus + 1) % 4;
                    context.modulus = i6;
                    int i7 = (context.ibitWorkArea << 6) + b;
                    context.ibitWorkArea = i7;
                    if (i6 == 0) {
                        int i8 = context.pos;
                        int i9 = i8 + 1;
                        context.pos = i9;
                        ensureBufferSize[i8] = (byte) ((i7 >> 16) & 255);
                        int i10 = i9 + 1;
                        context.pos = i10;
                        ensureBufferSize[i9] = (byte) ((i7 >> 8) & 255);
                        context.pos = i10 + 1;
                        ensureBufferSize[i10] = (byte) (i7 & 255);
                    }
                }
            }
            i4++;
            i2 = i5;
        }
        if (!context.eof || context.modulus == 0) {
            return;
        }
        byte[] ensureBufferSize2 = ensureBufferSize(this.decodeSize, context);
        int i11 = context.modulus;
        if (i11 != 1) {
            if (i11 == 2) {
                int i12 = context.ibitWorkArea >> 4;
                context.ibitWorkArea = i12;
                int i13 = context.pos;
                context.pos = i13 + 1;
                ensureBufferSize2[i13] = (byte) (i12 & 255);
            } else if (i11 == 3) {
                int i14 = context.ibitWorkArea >> 2;
                context.ibitWorkArea = i14;
                int i15 = context.pos;
                int i16 = i15 + 1;
                context.pos = i16;
                ensureBufferSize2[i15] = (byte) ((i14 >> 8) & 255);
                context.pos = i16 + 1;
                ensureBufferSize2[i16] = (byte) (i14 & 255);
            } else {
                throw new IllegalStateException("Impossible modulus " + context.modulus);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.apache.commons.codec.binary.BaseNCodec
    public void encode(byte[] bArr, int i2, int i3, BaseNCodec.Context context) {
        if (context.eof) {
            return;
        }
        if (i3 >= 0) {
            int i4 = 0;
            while (i4 < i3) {
                byte[] ensureBufferSize = ensureBufferSize(this.encodeSize, context);
                int i5 = (context.modulus + 1) % 3;
                context.modulus = i5;
                int i6 = i2 + 1;
                int i7 = bArr[i2];
                if (i7 < 0) {
                    i7 += 256;
                }
                int i8 = (context.ibitWorkArea << 8) + i7;
                context.ibitWorkArea = i8;
                if (i5 == 0) {
                    int i9 = context.pos;
                    int i10 = i9 + 1;
                    context.pos = i10;
                    byte[] bArr2 = this.encodeTable;
                    ensureBufferSize[i9] = bArr2[(i8 >> 18) & 63];
                    int i11 = i10 + 1;
                    context.pos = i11;
                    ensureBufferSize[i10] = bArr2[(i8 >> 12) & 63];
                    int i12 = i11 + 1;
                    context.pos = i12;
                    ensureBufferSize[i11] = bArr2[(i8 >> 6) & 63];
                    int i13 = i12 + 1;
                    context.pos = i13;
                    ensureBufferSize[i12] = bArr2[i8 & 63];
                    int i14 = context.currentLinePos + 4;
                    context.currentLinePos = i14;
                    int i15 = this.lineLength;
                    if (i15 > 0 && i15 <= i14) {
                        byte[] bArr3 = this.lineSeparator;
                        System.arraycopy(bArr3, 0, ensureBufferSize, i13, bArr3.length);
                        context.pos += this.lineSeparator.length;
                        context.currentLinePos = 0;
                    }
                }
                i4++;
                i2 = i6;
            }
            return;
        }
        context.eof = true;
        if (context.modulus == 0 && this.lineLength == 0) {
            return;
        }
        byte[] ensureBufferSize2 = ensureBufferSize(this.encodeSize, context);
        int i16 = context.pos;
        int i17 = context.modulus;
        if (i17 != 0) {
            if (i17 == 1) {
                int i18 = i16 + 1;
                context.pos = i18;
                byte[] bArr4 = this.encodeTable;
                int i19 = context.ibitWorkArea;
                ensureBufferSize2[i16] = bArr4[(i19 >> 2) & 63];
                int i20 = i18 + 1;
                context.pos = i20;
                ensureBufferSize2[i18] = bArr4[(i19 << 4) & 63];
                if (bArr4 == STANDARD_ENCODE_TABLE) {
                    int i21 = i20 + 1;
                    context.pos = i21;
                    byte b = this.pad;
                    ensureBufferSize2[i20] = b;
                    context.pos = i21 + 1;
                    ensureBufferSize2[i21] = b;
                }
            } else if (i17 == 2) {
                int i22 = i16 + 1;
                context.pos = i22;
                byte[] bArr5 = this.encodeTable;
                int i23 = context.ibitWorkArea;
                ensureBufferSize2[i16] = bArr5[(i23 >> 10) & 63];
                int i24 = i22 + 1;
                context.pos = i24;
                ensureBufferSize2[i22] = bArr5[(i23 >> 4) & 63];
                int i25 = i24 + 1;
                context.pos = i25;
                ensureBufferSize2[i24] = bArr5[(i23 << 2) & 63];
                if (bArr5 == STANDARD_ENCODE_TABLE) {
                    context.pos = i25 + 1;
                    ensureBufferSize2[i25] = this.pad;
                }
            } else {
                throw new IllegalStateException("Impossible modulus " + context.modulus);
            }
        }
        int i26 = context.currentLinePos;
        int i27 = context.pos;
        int i28 = i26 + (i27 - i16);
        context.currentLinePos = i28;
        if (this.lineLength <= 0 || i28 <= 0) {
            return;
        }
        byte[] bArr6 = this.lineSeparator;
        System.arraycopy(bArr6, 0, ensureBufferSize2, i27, bArr6.length);
        context.pos += this.lineSeparator.length;
    }

    @Override // org.apache.commons.codec.binary.BaseNCodec
    protected boolean isInAlphabet(byte b) {
        if (b >= 0) {
            byte[] bArr = this.decodeTable;
            if (b < bArr.length && bArr[b] != -1) {
                return true;
            }
        }
        return false;
    }

    public boolean isUrlSafe() {
        return this.encodeTable == URL_SAFE_ENCODE_TABLE;
    }

    public Base64(boolean z) {
        this(76, CHUNK_SEPARATOR, z);
    }

    public static byte[] decodeBase64(byte[] bArr) {
        return new Base64().decode(bArr);
    }

    public static byte[] encodeBase64(byte[] bArr, boolean z) {
        return encodeBase64(bArr, z, false);
    }

    public static boolean isBase64(String str) {
        return isBase64(StringUtils.getBytesUtf8(str));
    }

    public Base64(int i2) {
        this(i2, CHUNK_SEPARATOR);
    }

    public static byte[] encodeBase64(byte[] bArr, boolean z, boolean z2) {
        return encodeBase64(bArr, z, z2, Integer.MAX_VALUE);
    }

    public static boolean isBase64(byte[] bArr) {
        for (int i2 = 0; i2 < bArr.length; i2++) {
            if (!isBase64(bArr[i2]) && !BaseNCodec.isWhiteSpace(bArr[i2])) {
                return false;
            }
        }
        return true;
    }

    public Base64(int i2, byte[] bArr) {
        this(i2, bArr, false);
    }

    public static byte[] encodeBase64(byte[] bArr, boolean z, boolean z2, int i2) {
        if (bArr == null || bArr.length == 0) {
            return bArr;
        }
        Base64 base64 = z ? new Base64(z2) : new Base64(0, CHUNK_SEPARATOR, z2);
        long encodedLength = base64.getEncodedLength(bArr);
        if (encodedLength <= i2) {
            return base64.encode(bArr);
        }
        throw new IllegalArgumentException("Input array too big, the output array would be bigger (" + encodedLength + ") than the specified maximum size of " + i2);
    }

    public Base64(int i2, byte[] bArr, boolean z) {
        super(3, 4, i2, bArr == null ? 0 : bArr.length);
        this.decodeTable = DECODE_TABLE;
        if (bArr != null) {
            if (containsAlphabetOrPad(bArr)) {
                throw new IllegalArgumentException("lineSeparator must not contain base64 characters: [" + StringUtils.newStringUtf8(bArr) + "]");
            } else if (i2 > 0) {
                this.encodeSize = bArr.length + 4;
                byte[] bArr2 = new byte[bArr.length];
                this.lineSeparator = bArr2;
                System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
            } else {
                this.encodeSize = 4;
                this.lineSeparator = null;
            }
        } else {
            this.encodeSize = 4;
            this.lineSeparator = null;
        }
        this.decodeSize = this.encodeSize - 1;
        this.encodeTable = z ? URL_SAFE_ENCODE_TABLE : STANDARD_ENCODE_TABLE;
    }
}

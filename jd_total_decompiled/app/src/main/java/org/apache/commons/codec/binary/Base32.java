package org.apache.commons.codec.binary;

import jd.wjlogin_sdk.util.ReplyCode;
import org.apache.commons.codec.binary.BaseNCodec;

/* loaded from: classes11.dex */
public class Base32 extends BaseNCodec {
    private static final int BITS_PER_ENCODED_BYTE = 5;
    private static final int BYTES_PER_ENCODED_BLOCK = 8;
    private static final int BYTES_PER_UNENCODED_BLOCK = 5;
    private static final byte[] CHUNK_SEPARATOR = {13, 10};
    private static final byte[] DECODE_TABLE = {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 26, 27, 28, 29, 30, 31, -1, -1, -1, -1, -1, -1, -1, -1, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25};
    private static final byte[] ENCODE_TABLE = {65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, ReplyCode.reply0x4f, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, ReplyCode.reply0x32, 51, ReplyCode.reply0x34, ReplyCode.reply0x35, ReplyCode.reply0x36, ReplyCode.reply0x37};
    private static final byte[] HEX_DECODE_TABLE = {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, -1, -1, -1, -1, -1, -1, -1, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32};
    private static final byte[] HEX_ENCODE_TABLE = {48, ReplyCode.reply0x31, ReplyCode.reply0x32, 51, ReplyCode.reply0x34, ReplyCode.reply0x35, ReplyCode.reply0x36, ReplyCode.reply0x37, ReplyCode.reply0x38, ReplyCode.reply0x39, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, ReplyCode.reply0x4f, 80, 81, 82, 83, 84, 85, 86};
    private static final int MASK_5BITS = 31;
    private final int decodeSize;
    private final byte[] decodeTable;
    private final int encodeSize;
    private final byte[] encodeTable;
    private final byte[] lineSeparator;

    public Base32() {
        this(false);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Type inference failed for: r3v1 */
    /* JADX WARN: Type inference failed for: r3v19 */
    /* JADX WARN: Type inference failed for: r3v2, types: [int, boolean] */
    @Override // org.apache.commons.codec.binary.BaseNCodec
    public void decode(byte[] bArr, int i2, int i3, BaseNCodec.Context context) {
        byte b;
        if (context.eof) {
            return;
        }
        ?? r3 = 1;
        if (i3 < 0) {
            context.eof = true;
        }
        int i4 = i2;
        int i5 = 0;
        while (true) {
            if (i5 >= i3) {
                break;
            }
            int i6 = i4 + 1;
            byte b2 = bArr[i4];
            if (b2 == this.pad) {
                context.eof = r3;
                break;
            }
            byte[] ensureBufferSize = ensureBufferSize(this.decodeSize, context);
            if (b2 >= 0) {
                byte[] bArr2 = this.decodeTable;
                if (b2 < bArr2.length && (b = bArr2[b2]) >= 0) {
                    int i7 = (context.modulus + r3) % 8;
                    context.modulus = i7;
                    context.lbitWorkArea = (context.lbitWorkArea << 5) + b;
                    if (i7 == 0) {
                        int i8 = context.pos;
                        int i9 = i8 + 1;
                        context.pos = i9;
                        ensureBufferSize[i8] = (byte) ((r14 >> 32) & 255);
                        int i10 = i9 + 1;
                        context.pos = i10;
                        ensureBufferSize[i9] = (byte) ((r14 >> 24) & 255);
                        int i11 = i10 + 1;
                        context.pos = i11;
                        ensureBufferSize[i10] = (byte) ((r14 >> 16) & 255);
                        int i12 = i11 + 1;
                        context.pos = i12;
                        ensureBufferSize[i11] = (byte) ((r14 >> 8) & 255);
                        context.pos = i12 + 1;
                        ensureBufferSize[i12] = (byte) (r14 & 255);
                    }
                }
            }
            i5++;
            i4 = i6;
            r3 = 1;
        }
        if (!context.eof || context.modulus < 2) {
            return;
        }
        byte[] ensureBufferSize2 = ensureBufferSize(this.decodeSize, context);
        switch (context.modulus) {
            case 2:
                int i13 = context.pos;
                context.pos = i13 + 1;
                ensureBufferSize2[i13] = (byte) ((context.lbitWorkArea >> 2) & 255);
                return;
            case 3:
                int i14 = context.pos;
                context.pos = i14 + 1;
                ensureBufferSize2[i14] = (byte) ((context.lbitWorkArea >> 7) & 255);
                return;
            case 4:
                context.lbitWorkArea = context.lbitWorkArea >> 4;
                int i15 = context.pos;
                int i16 = i15 + 1;
                context.pos = i16;
                ensureBufferSize2[i15] = (byte) ((r3 >> 8) & 255);
                context.pos = i16 + 1;
                ensureBufferSize2[i16] = (byte) (r3 & 255);
                return;
            case 5:
                context.lbitWorkArea = context.lbitWorkArea >> 1;
                int i17 = context.pos;
                int i18 = i17 + 1;
                context.pos = i18;
                ensureBufferSize2[i17] = (byte) ((r3 >> 16) & 255);
                int i19 = i18 + 1;
                context.pos = i19;
                ensureBufferSize2[i18] = (byte) ((r3 >> 8) & 255);
                context.pos = i19 + 1;
                ensureBufferSize2[i19] = (byte) (r3 & 255);
                return;
            case 6:
                context.lbitWorkArea = context.lbitWorkArea >> 6;
                int i20 = context.pos;
                int i21 = i20 + 1;
                context.pos = i21;
                ensureBufferSize2[i20] = (byte) ((r3 >> 16) & 255);
                int i22 = i21 + 1;
                context.pos = i22;
                ensureBufferSize2[i21] = (byte) ((r3 >> 8) & 255);
                context.pos = i22 + 1;
                ensureBufferSize2[i22] = (byte) (r3 & 255);
                return;
            case 7:
                context.lbitWorkArea = context.lbitWorkArea >> 3;
                int i23 = context.pos;
                int i24 = i23 + 1;
                context.pos = i24;
                ensureBufferSize2[i23] = (byte) ((r3 >> 24) & 255);
                int i25 = i24 + 1;
                context.pos = i25;
                ensureBufferSize2[i24] = (byte) ((r3 >> 16) & 255);
                int i26 = i25 + 1;
                context.pos = i26;
                ensureBufferSize2[i25] = (byte) ((r3 >> 8) & 255);
                context.pos = i26 + 1;
                ensureBufferSize2[i26] = (byte) (r3 & 255);
                return;
            default:
                throw new IllegalStateException("Impossible modulus " + context.modulus);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.apache.commons.codec.binary.BaseNCodec
    public void encode(byte[] bArr, int i2, int i3, BaseNCodec.Context context) {
        int i4;
        if (context.eof) {
            return;
        }
        int i5 = 1;
        if (i3 >= 0) {
            int i6 = i2;
            int i7 = 0;
            while (i7 < i3) {
                byte[] ensureBufferSize = ensureBufferSize(this.encodeSize, context);
                int i8 = (context.modulus + i5) % 5;
                context.modulus = i8;
                int i9 = i6 + 1;
                int i10 = bArr[i6];
                if (i10 < 0) {
                    i10 += 256;
                }
                long j2 = (context.lbitWorkArea << 8) + i10;
                context.lbitWorkArea = j2;
                if (i8 == 0) {
                    int i11 = context.pos;
                    int i12 = i11 + 1;
                    context.pos = i12;
                    byte[] bArr2 = this.encodeTable;
                    ensureBufferSize[i11] = bArr2[((int) (j2 >> 35)) & 31];
                    int i13 = i12 + 1;
                    context.pos = i13;
                    ensureBufferSize[i12] = bArr2[((int) (j2 >> 30)) & 31];
                    int i14 = i13 + 1;
                    context.pos = i14;
                    i4 = i9;
                    ensureBufferSize[i13] = bArr2[((int) (j2 >> 25)) & 31];
                    int i15 = i14 + 1;
                    context.pos = i15;
                    ensureBufferSize[i14] = bArr2[((int) (j2 >> 20)) & 31];
                    int i16 = i15 + 1;
                    context.pos = i16;
                    ensureBufferSize[i15] = bArr2[((int) (j2 >> 15)) & 31];
                    int i17 = i16 + 1;
                    context.pos = i17;
                    ensureBufferSize[i16] = bArr2[((int) (j2 >> 10)) & 31];
                    int i18 = i17 + 1;
                    context.pos = i18;
                    ensureBufferSize[i17] = bArr2[((int) (j2 >> 5)) & 31];
                    int i19 = i18 + 1;
                    context.pos = i19;
                    ensureBufferSize[i18] = bArr2[((int) j2) & 31];
                    int i20 = context.currentLinePos + 8;
                    context.currentLinePos = i20;
                    int i21 = this.lineLength;
                    if (i21 > 0 && i21 <= i20) {
                        byte[] bArr3 = this.lineSeparator;
                        System.arraycopy(bArr3, 0, ensureBufferSize, i19, bArr3.length);
                        context.pos += this.lineSeparator.length;
                        context.currentLinePos = 0;
                        i7++;
                        i6 = i4;
                        i5 = 1;
                    }
                } else {
                    i4 = i9;
                }
                i7++;
                i6 = i4;
                i5 = 1;
            }
            return;
        }
        context.eof = true;
        if (context.modulus == 0 && this.lineLength == 0) {
            return;
        }
        byte[] ensureBufferSize2 = ensureBufferSize(this.encodeSize, context);
        int i22 = context.pos;
        int i23 = context.modulus;
        if (i23 != 0) {
            if (i23 == 1) {
                int i24 = i22 + 1;
                context.pos = i24;
                byte[] bArr4 = this.encodeTable;
                long j3 = context.lbitWorkArea;
                ensureBufferSize2[i22] = bArr4[((int) (j3 >> 3)) & 31];
                int i25 = i24 + 1;
                context.pos = i25;
                ensureBufferSize2[i24] = bArr4[((int) (j3 << 2)) & 31];
                int i26 = i25 + 1;
                context.pos = i26;
                byte b = this.pad;
                ensureBufferSize2[i25] = b;
                int i27 = i26 + 1;
                context.pos = i27;
                ensureBufferSize2[i26] = b;
                int i28 = i27 + 1;
                context.pos = i28;
                ensureBufferSize2[i27] = b;
                int i29 = i28 + 1;
                context.pos = i29;
                ensureBufferSize2[i28] = b;
                int i30 = i29 + 1;
                context.pos = i30;
                ensureBufferSize2[i29] = b;
                context.pos = i30 + 1;
                ensureBufferSize2[i30] = b;
            } else if (i23 == 2) {
                int i31 = i22 + 1;
                context.pos = i31;
                byte[] bArr5 = this.encodeTable;
                long j4 = context.lbitWorkArea;
                ensureBufferSize2[i22] = bArr5[((int) (j4 >> 11)) & 31];
                int i32 = i31 + 1;
                context.pos = i32;
                ensureBufferSize2[i31] = bArr5[((int) (j4 >> 6)) & 31];
                int i33 = i32 + 1;
                context.pos = i33;
                ensureBufferSize2[i32] = bArr5[((int) (j4 >> 1)) & 31];
                int i34 = i33 + 1;
                context.pos = i34;
                ensureBufferSize2[i33] = bArr5[((int) (j4 << 4)) & 31];
                int i35 = i34 + 1;
                context.pos = i35;
                byte b2 = this.pad;
                ensureBufferSize2[i34] = b2;
                int i36 = i35 + 1;
                context.pos = i36;
                ensureBufferSize2[i35] = b2;
                int i37 = i36 + 1;
                context.pos = i37;
                ensureBufferSize2[i36] = b2;
                context.pos = i37 + 1;
                ensureBufferSize2[i37] = b2;
            } else if (i23 == 3) {
                int i38 = i22 + 1;
                context.pos = i38;
                byte[] bArr6 = this.encodeTable;
                long j5 = context.lbitWorkArea;
                ensureBufferSize2[i22] = bArr6[((int) (j5 >> 19)) & 31];
                int i39 = i38 + 1;
                context.pos = i39;
                ensureBufferSize2[i38] = bArr6[((int) (j5 >> 14)) & 31];
                int i40 = i39 + 1;
                context.pos = i40;
                ensureBufferSize2[i39] = bArr6[((int) (j5 >> 9)) & 31];
                int i41 = i40 + 1;
                context.pos = i41;
                ensureBufferSize2[i40] = bArr6[((int) (j5 >> 4)) & 31];
                int i42 = i41 + 1;
                context.pos = i42;
                ensureBufferSize2[i41] = bArr6[((int) (j5 << 1)) & 31];
                int i43 = i42 + 1;
                context.pos = i43;
                byte b3 = this.pad;
                ensureBufferSize2[i42] = b3;
                int i44 = i43 + 1;
                context.pos = i44;
                ensureBufferSize2[i43] = b3;
                context.pos = i44 + 1;
                ensureBufferSize2[i44] = b3;
            } else if (i23 == 4) {
                int i45 = i22 + 1;
                context.pos = i45;
                byte[] bArr7 = this.encodeTable;
                long j6 = context.lbitWorkArea;
                ensureBufferSize2[i22] = bArr7[((int) (j6 >> 27)) & 31];
                int i46 = i45 + 1;
                context.pos = i46;
                ensureBufferSize2[i45] = bArr7[((int) (j6 >> 22)) & 31];
                int i47 = i46 + 1;
                context.pos = i47;
                ensureBufferSize2[i46] = bArr7[((int) (j6 >> 17)) & 31];
                int i48 = i47 + 1;
                context.pos = i48;
                ensureBufferSize2[i47] = bArr7[((int) (j6 >> 12)) & 31];
                int i49 = i48 + 1;
                context.pos = i49;
                ensureBufferSize2[i48] = bArr7[((int) (j6 >> 7)) & 31];
                int i50 = i49 + 1;
                context.pos = i50;
                ensureBufferSize2[i49] = bArr7[((int) (j6 >> 2)) & 31];
                int i51 = i50 + 1;
                context.pos = i51;
                ensureBufferSize2[i50] = bArr7[((int) (j6 << 3)) & 31];
                context.pos = i51 + 1;
                ensureBufferSize2[i51] = this.pad;
            } else {
                throw new IllegalStateException("Impossible modulus " + context.modulus);
            }
        }
        int i52 = context.currentLinePos;
        int i53 = context.pos;
        int i54 = i52 + (i53 - i22);
        context.currentLinePos = i54;
        if (this.lineLength <= 0 || i54 <= 0) {
            return;
        }
        byte[] bArr8 = this.lineSeparator;
        System.arraycopy(bArr8, 0, ensureBufferSize2, i53, bArr8.length);
        context.pos += this.lineSeparator.length;
    }

    @Override // org.apache.commons.codec.binary.BaseNCodec
    public boolean isInAlphabet(byte b) {
        if (b >= 0) {
            byte[] bArr = this.decodeTable;
            if (b < bArr.length && bArr[b] != -1) {
                return true;
            }
        }
        return false;
    }

    public Base32(byte b) {
        this(false, b);
    }

    public Base32(boolean z) {
        this(0, null, z, (byte) 61);
    }

    public Base32(boolean z, byte b) {
        this(0, null, z, b);
    }

    public Base32(int i2) {
        this(i2, CHUNK_SEPARATOR);
    }

    public Base32(int i2, byte[] bArr) {
        this(i2, bArr, false, (byte) 61);
    }

    public Base32(int i2, byte[] bArr, boolean z) {
        this(i2, bArr, z, (byte) 61);
    }

    public Base32(int i2, byte[] bArr, boolean z, byte b) {
        super(5, 8, i2, bArr == null ? 0 : bArr.length, b);
        if (z) {
            this.encodeTable = HEX_ENCODE_TABLE;
            this.decodeTable = HEX_DECODE_TABLE;
        } else {
            this.encodeTable = ENCODE_TABLE;
            this.decodeTable = DECODE_TABLE;
        }
        if (i2 <= 0) {
            this.encodeSize = 8;
            this.lineSeparator = null;
        } else if (bArr != null) {
            if (!containsAlphabetOrPad(bArr)) {
                this.encodeSize = bArr.length + 8;
                byte[] bArr2 = new byte[bArr.length];
                this.lineSeparator = bArr2;
                System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
            } else {
                throw new IllegalArgumentException("lineSeparator must not contain Base32 characters: [" + StringUtils.newStringUtf8(bArr) + "]");
            }
        } else {
            throw new IllegalArgumentException("lineLength " + i2 + " > 0, but lineSeparator is null");
        }
        this.decodeSize = this.encodeSize - 1;
        if (isInAlphabet(b) || BaseNCodec.isWhiteSpace(b)) {
            throw new IllegalArgumentException("pad must not be in alphabet or whitespace");
        }
    }
}

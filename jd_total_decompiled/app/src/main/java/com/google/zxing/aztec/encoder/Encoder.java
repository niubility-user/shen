package com.google.zxing.aztec.encoder;

import com.google.zxing.common.BitArray;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.reedsolomon.GenericGF;
import com.google.zxing.common.reedsolomon.ReedSolomonEncoder;

/* loaded from: classes12.dex */
public final class Encoder {
    public static final int DEFAULT_AZTEC_LAYERS = 0;
    public static final int DEFAULT_EC_PERCENT = 33;
    private static final int MAX_NB_BITS = 32;
    private static final int MAX_NB_BITS_COMPACT = 4;
    private static final int[] WORD_SIZE = {4, 6, 6, 8, 8, 8, 8, 8, 8, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12};

    private Encoder() {
    }

    private static int[] bitsToWords(BitArray bitArray, int i2, int i3) {
        int[] iArr = new int[i3];
        int size = bitArray.getSize() / i2;
        for (int i4 = 0; i4 < size; i4++) {
            int i5 = 0;
            for (int i6 = 0; i6 < i2; i6++) {
                i5 |= bitArray.get((i4 * i2) + i6) ? 1 << ((i2 - i6) - 1) : 0;
            }
            iArr[i4] = i5;
        }
        return iArr;
    }

    private static void drawBullsEye(BitMatrix bitMatrix, int i2, int i3) {
        for (int i4 = 0; i4 < i3; i4 += 2) {
            int i5 = i2 - i4;
            int i6 = i5;
            while (true) {
                int i7 = i2 + i4;
                if (i6 <= i7) {
                    bitMatrix.set(i6, i5);
                    bitMatrix.set(i6, i7);
                    bitMatrix.set(i5, i6);
                    bitMatrix.set(i7, i6);
                    i6++;
                }
            }
        }
        int i8 = i2 - i3;
        bitMatrix.set(i8, i8);
        int i9 = i8 + 1;
        bitMatrix.set(i9, i8);
        bitMatrix.set(i8, i9);
        int i10 = i2 + i3;
        bitMatrix.set(i10, i8);
        bitMatrix.set(i10, i9);
        bitMatrix.set(i10, i10 - 1);
    }

    private static void drawModeMessage(BitMatrix bitMatrix, boolean z, int i2, BitArray bitArray) {
        int i3 = i2 / 2;
        int i4 = 0;
        if (z) {
            while (i4 < 7) {
                int i5 = (i3 - 3) + i4;
                if (bitArray.get(i4)) {
                    bitMatrix.set(i5, i3 - 5);
                }
                if (bitArray.get(i4 + 7)) {
                    bitMatrix.set(i3 + 5, i5);
                }
                if (bitArray.get(20 - i4)) {
                    bitMatrix.set(i5, i3 + 5);
                }
                if (bitArray.get(27 - i4)) {
                    bitMatrix.set(i3 - 5, i5);
                }
                i4++;
            }
            return;
        }
        while (i4 < 10) {
            int i6 = (i3 - 5) + i4 + (i4 / 5);
            if (bitArray.get(i4)) {
                bitMatrix.set(i6, i3 - 7);
            }
            if (bitArray.get(i4 + 10)) {
                bitMatrix.set(i3 + 7, i6);
            }
            if (bitArray.get(29 - i4)) {
                bitMatrix.set(i6, i3 + 7);
            }
            if (bitArray.get(39 - i4)) {
                bitMatrix.set(i3 - 7, i6);
            }
            i4++;
        }
    }

    public static AztecCode encode(byte[] bArr) {
        return encode(bArr, 33, 0);
    }

    private static BitArray generateCheckWords(BitArray bitArray, int i2, int i3) {
        ReedSolomonEncoder reedSolomonEncoder = new ReedSolomonEncoder(getGF(i3));
        int i4 = i2 / i3;
        int[] bitsToWords = bitsToWords(bitArray, i3, i4);
        reedSolomonEncoder.encode(bitsToWords, i4 - (bitArray.getSize() / i3));
        BitArray bitArray2 = new BitArray();
        bitArray2.appendBits(0, i2 % i3);
        for (int i5 : bitsToWords) {
            bitArray2.appendBits(i5, i3);
        }
        return bitArray2;
    }

    static BitArray generateModeMessage(boolean z, int i2, int i3) {
        BitArray bitArray = new BitArray();
        if (z) {
            bitArray.appendBits(i2 - 1, 2);
            bitArray.appendBits(i3 - 1, 6);
            return generateCheckWords(bitArray, 28, 4);
        }
        bitArray.appendBits(i2 - 1, 5);
        bitArray.appendBits(i3 - 1, 11);
        return generateCheckWords(bitArray, 40, 4);
    }

    private static GenericGF getGF(int i2) {
        if (i2 != 4) {
            if (i2 != 6) {
                if (i2 != 8) {
                    if (i2 != 10) {
                        if (i2 != 12) {
                            return null;
                        }
                        return GenericGF.AZTEC_DATA_12;
                    }
                    return GenericGF.AZTEC_DATA_10;
                }
                return GenericGF.AZTEC_DATA_8;
            }
            return GenericGF.AZTEC_DATA_6;
        }
        return GenericGF.AZTEC_PARAM;
    }

    static BitArray stuffBits(BitArray bitArray, int i2) {
        BitArray bitArray2 = new BitArray();
        int size = bitArray.getSize();
        int i3 = (1 << i2) - 2;
        int i4 = 0;
        while (i4 < size) {
            int i5 = 0;
            for (int i6 = 0; i6 < i2; i6++) {
                int i7 = i4 + i6;
                if (i7 >= size || bitArray.get(i7)) {
                    i5 |= 1 << ((i2 - 1) - i6);
                }
            }
            int i8 = i5 & i3;
            if (i8 == i3) {
                bitArray2.appendBits(i8, i2);
            } else if (i8 == 0) {
                bitArray2.appendBits(i5 | 1, i2);
            } else {
                bitArray2.appendBits(i5, i2);
                i4 += i2;
            }
            i4--;
            i4 += i2;
        }
        return bitArray2;
    }

    private static int totalBitsInLayer(int i2, boolean z) {
        return ((z ? 88 : 112) + (i2 * 16)) * i2;
    }

    public static AztecCode encode(byte[] bArr, int i2, int i3) {
        BitArray bitArray;
        int i4;
        boolean z;
        int i5;
        int i6;
        int i7;
        BitArray encode = new HighLevelEncoder(bArr).encode();
        int size = ((encode.getSize() * i2) / 100) + 11;
        int size2 = encode.getSize() + size;
        int i8 = 0;
        int i9 = 1;
        if (i3 != 0) {
            z = i3 < 0;
            i5 = Math.abs(i3);
            if (i5 <= (z ? 4 : 32)) {
                i6 = totalBitsInLayer(i5, z);
                i4 = WORD_SIZE[i5];
                int i10 = i6 - (i6 % i4);
                bitArray = stuffBits(encode, i4);
                if (bitArray.getSize() + size <= i10) {
                    if (z && bitArray.getSize() > i4 * 64) {
                        throw new IllegalArgumentException("Data to large for user specified layer");
                    }
                } else {
                    throw new IllegalArgumentException("Data to large for user specified layer");
                }
            } else {
                throw new IllegalArgumentException(String.format("Illegal value %s for layers", Integer.valueOf(i3)));
            }
        } else {
            BitArray bitArray2 = null;
            int i11 = 0;
            int i12 = 0;
            while (i11 <= 32) {
                boolean z2 = i11 <= 3;
                int i13 = z2 ? i11 + 1 : i11;
                int i14 = totalBitsInLayer(i13, z2);
                if (size2 <= i14) {
                    int[] iArr = WORD_SIZE;
                    if (i12 != iArr[i13]) {
                        int i15 = iArr[i13];
                        i12 = i15;
                        bitArray2 = stuffBits(encode, i15);
                    }
                    int i16 = i14 - (i14 % i12);
                    if ((!z2 || bitArray2.getSize() <= i12 * 64) && bitArray2.getSize() + size <= i16) {
                        bitArray = bitArray2;
                        i4 = i12;
                        z = z2;
                        i5 = i13;
                        i6 = i14;
                    }
                }
                i11++;
                i8 = 0;
                i9 = 1;
            }
            throw new IllegalArgumentException("Data too large for an Aztec code");
        }
        BitArray generateCheckWords = generateCheckWords(bitArray, i6, i4);
        int size3 = bitArray.getSize() / i4;
        BitArray generateModeMessage = generateModeMessage(z, i5, size3);
        int i17 = z ? (i5 * 4) + 11 : (i5 * 4) + 14;
        int[] iArr2 = new int[i17];
        int i18 = 2;
        if (z) {
            for (int i19 = 0; i19 < i17; i19++) {
                iArr2[i19] = i19;
            }
            i7 = i17;
        } else {
            int i20 = i17 / 2;
            i7 = i17 + 1 + (((i20 - 1) / 15) * 2);
            int i21 = i7 / 2;
            for (int i22 = 0; i22 < i20; i22++) {
                iArr2[(i20 - i22) - 1] = (i21 - r15) - 1;
                iArr2[i20 + i22] = (i22 / 15) + i22 + i21 + i9;
            }
        }
        BitMatrix bitMatrix = new BitMatrix(i7);
        int i23 = 0;
        int i24 = 0;
        while (i23 < i5) {
            int i25 = (i5 - i23) * 4;
            int i26 = z ? i25 + 9 : i25 + 12;
            int i27 = 0;
            while (i27 < i26) {
                int i28 = i27 * 2;
                while (i8 < i18) {
                    if (generateCheckWords.get(i24 + i28 + i8)) {
                        int i29 = i23 * 2;
                        bitMatrix.set(iArr2[i29 + i8], iArr2[i29 + i27]);
                    }
                    if (generateCheckWords.get((i26 * 2) + i24 + i28 + i8)) {
                        int i30 = i23 * 2;
                        bitMatrix.set(iArr2[i30 + i27], iArr2[((i17 - 1) - i30) - i8]);
                    }
                    if (generateCheckWords.get((i26 * 4) + i24 + i28 + i8)) {
                        int i31 = (i17 - 1) - (i23 * 2);
                        bitMatrix.set(iArr2[i31 - i8], iArr2[i31 - i27]);
                    }
                    if (generateCheckWords.get((i26 * 6) + i24 + i28 + i8)) {
                        int i32 = i23 * 2;
                        bitMatrix.set(iArr2[((i17 - 1) - i32) - i27], iArr2[i32 + i8]);
                    }
                    i8++;
                    i18 = 2;
                }
                i27++;
                i8 = 0;
                i18 = 2;
            }
            i24 += i26 * 8;
            i23++;
            i8 = 0;
            i18 = 2;
        }
        drawModeMessage(bitMatrix, z, i7, generateModeMessage);
        if (z) {
            drawBullsEye(bitMatrix, i7 / 2, 5);
        } else {
            int i33 = i7 / 2;
            drawBullsEye(bitMatrix, i33, 7);
            int i34 = 0;
            int i35 = 0;
            while (i35 < (i17 / 2) - 1) {
                for (int i36 = i33 & 1; i36 < i7; i36 += 2) {
                    int i37 = i33 - i34;
                    bitMatrix.set(i37, i36);
                    int i38 = i33 + i34;
                    bitMatrix.set(i38, i36);
                    bitMatrix.set(i36, i37);
                    bitMatrix.set(i36, i38);
                }
                i35 += 15;
                i34 += 16;
            }
        }
        AztecCode aztecCode = new AztecCode();
        aztecCode.setCompact(z);
        aztecCode.setSize(i7);
        aztecCode.setLayers(i5);
        aztecCode.setCodeWords(size3);
        aztecCode.setMatrix(bitMatrix);
        return aztecCode;
    }
}

package com.jingdong.app.mall.bundle.jdbrotli;

/* loaded from: classes2.dex */
public class Huffman {
    private static final int MAX_LENGTH = 15;

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int buildHuffmanTable(int[] iArr, int i2, int i3, int[] iArr2, int i4) {
        int i5;
        int i6 = iArr[i2];
        int[] iArr3 = new int[i4];
        int[] iArr4 = new int[16];
        int[] iArr5 = new int[16];
        int i7 = 0;
        for (int i8 = 0; i8 < i4; i8++) {
            int i9 = iArr2[i8];
            iArr4[i9] = iArr4[i9] + 1;
        }
        iArr5[1] = 0;
        int i10 = 1;
        while (true) {
            if (i10 >= 15) {
                break;
            }
            int i11 = i10 + 1;
            iArr5[i11] = iArr5[i10] + iArr4[i10];
            i10 = i11;
        }
        for (int i12 = 0; i12 < i4; i12++) {
            if (iArr2[i12] != 0) {
                int i13 = iArr2[i12];
                int i14 = iArr5[i13];
                iArr5[i13] = i14 + 1;
                iArr3[i14] = i12;
            }
        }
        int i15 = 1 << i3;
        if (iArr5[15] == 1) {
            for (int i16 = 0; i16 < i15; i16++) {
                iArr[i6 + i16] = iArr3[0];
            }
            return i15;
        }
        int i17 = 0;
        int i18 = 1;
        int i19 = 2;
        while (i18 <= i3) {
            while (iArr4[i18] > 0) {
                replicateValue(iArr, i6 + i7, i19, i15, iArr3[i17] | (i18 << 16));
                i7 = getNextKey(i7, i18);
                iArr4[i18] = iArr4[i18] - 1;
                i17++;
            }
            i18++;
            i19 <<= 1;
        }
        int i20 = i15 - 1;
        int i21 = i15;
        int i22 = i6;
        int i23 = i17;
        int i24 = i3 + 1;
        int i25 = -1;
        int i26 = i7;
        int i27 = 2;
        for (i5 = 15; i24 <= i5; i5 = 15) {
            while (iArr4[i24] > 0) {
                int i28 = i26 & i20;
                if (i28 != i25) {
                    i22 += i21;
                    int nextTableBitSize = nextTableBitSize(iArr4, i24, i3);
                    int i29 = 1 << nextTableBitSize;
                    i15 += i29;
                    iArr[i6 + i28] = ((nextTableBitSize + i3) << 16) | ((i22 - i6) - i28);
                    i21 = i29;
                    i25 = i28;
                }
                replicateValue(iArr, i22 + (i26 >> i3), i27, i21, ((i24 - i3) << 16) | iArr3[i23]);
                i26 = getNextKey(i26, i24);
                iArr4[i24] = iArr4[i24] - 1;
                i23++;
            }
            i24++;
            i27 <<= 1;
        }
        return i15;
    }

    private static int getNextKey(int i2, int i3) {
        int i4 = 1 << (i3 - 1);
        while ((i2 & i4) != 0) {
            i4 >>= 1;
        }
        return (i2 & (i4 - 1)) + i4;
    }

    private static int nextTableBitSize(int[] iArr, int i2, int i3) {
        int i4;
        int i5 = 1 << (i2 - i3);
        while (i2 < 15 && (i4 = i5 - iArr[i2]) > 0) {
            i2++;
            i5 = i4 << 1;
        }
        return i2 - i3;
    }

    private static void replicateValue(int[] iArr, int i2, int i3, int i4, int i5) {
        do {
            i4 -= i3;
            iArr[i2 + i4] = i5;
        } while (i4 > 0);
    }
}

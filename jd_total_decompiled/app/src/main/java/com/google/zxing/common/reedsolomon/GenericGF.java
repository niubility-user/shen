package com.google.zxing.common.reedsolomon;

import com.jingdong.sdk.platform.business.personal.R2;

/* loaded from: classes12.dex */
public final class GenericGF {
    public static final GenericGF AZTEC_DATA_6;
    public static final GenericGF AZTEC_DATA_8;
    public static final GenericGF AZTEC_PARAM;
    public static final GenericGF DATA_MATRIX_FIELD_256;
    public static final GenericGF MAXICODE_FIELD_64;
    public static final GenericGF QR_CODE_FIELD_256;
    private final int[] expTable;
    private final int generatorBase;
    private final int[] logTable;
    private final GenericGFPoly one;
    private final int primitive;
    private final int size;
    private final GenericGFPoly zero;
    public static final GenericGF AZTEC_DATA_12 = new GenericGF(R2.color.libpaipaireplacemodel_paipai_color_262626, 4096, 1);
    public static final GenericGF AZTEC_DATA_10 = new GenericGF(R2.attr.ijkandvrplayer_max, 1024, 1);

    static {
        GenericGF genericGF = new GenericGF(67, 64, 1);
        AZTEC_DATA_6 = genericGF;
        AZTEC_PARAM = new GenericGF(19, 16, 1);
        QR_CODE_FIELD_256 = new GenericGF(285, 256, 0);
        GenericGF genericGF2 = new GenericGF(301, 256, 1);
        DATA_MATRIX_FIELD_256 = genericGF2;
        AZTEC_DATA_8 = genericGF2;
        MAXICODE_FIELD_64 = genericGF;
    }

    public GenericGF(int i2, int i3, int i4) {
        this.primitive = i2;
        this.size = i3;
        this.generatorBase = i4;
        this.expTable = new int[i3];
        this.logTable = new int[i3];
        int i5 = 1;
        for (int i6 = 0; i6 < i3; i6++) {
            this.expTable[i6] = i5;
            i5 *= 2;
            if (i5 >= i3) {
                i5 = (i5 ^ i2) & (i3 - 1);
            }
        }
        for (int i7 = 0; i7 < i3 - 1; i7++) {
            this.logTable[this.expTable[i7]] = i7;
        }
        this.zero = new GenericGFPoly(this, new int[]{0});
        this.one = new GenericGFPoly(this, new int[]{1});
    }

    public static int addOrSubtract(int i2, int i3) {
        return i2 ^ i3;
    }

    public GenericGFPoly buildMonomial(int i2, int i3) {
        if (i2 >= 0) {
            if (i3 == 0) {
                return this.zero;
            }
            int[] iArr = new int[i2 + 1];
            iArr[0] = i3;
            return new GenericGFPoly(this, iArr);
        }
        throw new IllegalArgumentException();
    }

    public int exp(int i2) {
        return this.expTable[i2];
    }

    public int getGeneratorBase() {
        return this.generatorBase;
    }

    public GenericGFPoly getOne() {
        return this.one;
    }

    public int getSize() {
        return this.size;
    }

    public GenericGFPoly getZero() {
        return this.zero;
    }

    public int inverse(int i2) {
        if (i2 != 0) {
            return this.expTable[(this.size - this.logTable[i2]) - 1];
        }
        throw new ArithmeticException();
    }

    public int log(int i2) {
        if (i2 != 0) {
            return this.logTable[i2];
        }
        throw new IllegalArgumentException();
    }

    public int multiply(int i2, int i3) {
        if (i2 == 0 || i3 == 0) {
            return 0;
        }
        int[] iArr = this.expTable;
        int[] iArr2 = this.logTable;
        return iArr[(iArr2[i2] + iArr2[i3]) % (this.size - 1)];
    }

    public String toString() {
        return "GF(0x" + Integer.toHexString(this.primitive) + ',' + this.size + ')';
    }
}

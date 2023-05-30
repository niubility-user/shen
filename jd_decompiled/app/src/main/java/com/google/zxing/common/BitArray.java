package com.google.zxing.common;

import com.jingdong.app.mall.bundle.order_center_isv_core.util.OrderISVUtil;
import java.util.Arrays;

/* loaded from: classes12.dex */
public final class BitArray implements Cloneable {
    private int[] bits;
    private int size;

    public BitArray() {
        this.size = 0;
        this.bits = new int[1];
    }

    private void ensureCapacity(int i2) {
        if (i2 > this.bits.length * 32) {
            int[] makeArray = makeArray(i2);
            int[] iArr = this.bits;
            System.arraycopy(iArr, 0, makeArray, 0, iArr.length);
            this.bits = makeArray;
        }
    }

    private static int[] makeArray(int i2) {
        return new int[(i2 + 31) / 32];
    }

    public void appendBit(boolean z) {
        ensureCapacity(this.size + 1);
        if (z) {
            int[] iArr = this.bits;
            int i2 = this.size;
            int i3 = i2 / 32;
            iArr[i3] = (1 << (i2 & 31)) | iArr[i3];
        }
        this.size++;
    }

    public void appendBitArray(BitArray bitArray) {
        int i2 = bitArray.size;
        ensureCapacity(this.size + i2);
        for (int i3 = 0; i3 < i2; i3++) {
            appendBit(bitArray.get(i3));
        }
    }

    public void appendBits(int i2, int i3) {
        if (i3 >= 0 && i3 <= 32) {
            ensureCapacity(this.size + i3);
            while (i3 > 0) {
                boolean z = true;
                if (((i2 >> (i3 - 1)) & 1) != 1) {
                    z = false;
                }
                appendBit(z);
                i3--;
            }
            return;
        }
        throw new IllegalArgumentException("Num bits must be between 0 and 32");
    }

    public void clear() {
        int length = this.bits.length;
        for (int i2 = 0; i2 < length; i2++) {
            this.bits[i2] = 0;
        }
    }

    public boolean equals(Object obj) {
        if (obj instanceof BitArray) {
            BitArray bitArray = (BitArray) obj;
            return this.size == bitArray.size && Arrays.equals(this.bits, bitArray.bits);
        }
        return false;
    }

    public void flip(int i2) {
        int[] iArr = this.bits;
        int i3 = i2 / 32;
        iArr[i3] = (1 << (i2 & 31)) ^ iArr[i3];
    }

    public boolean get(int i2) {
        return ((1 << (i2 & 31)) & this.bits[i2 / 32]) != 0;
    }

    public int[] getBitArray() {
        return this.bits;
    }

    public int getNextSet(int i2) {
        int i3 = this.size;
        if (i2 >= i3) {
            return i3;
        }
        int i4 = i2 / 32;
        int i5 = (((1 << (i2 & 31)) - 1) ^ (-1)) & this.bits[i4];
        while (i5 == 0) {
            i4++;
            int[] iArr = this.bits;
            if (i4 == iArr.length) {
                return this.size;
            }
            i5 = iArr[i4];
        }
        int numberOfTrailingZeros = (i4 * 32) + Integer.numberOfTrailingZeros(i5);
        int i6 = this.size;
        return numberOfTrailingZeros > i6 ? i6 : numberOfTrailingZeros;
    }

    public int getNextUnset(int i2) {
        int i3 = this.size;
        if (i2 >= i3) {
            return i3;
        }
        int i4 = i2 / 32;
        int i5 = (((1 << (i2 & 31)) - 1) ^ (-1)) & (this.bits[i4] ^ (-1));
        while (i5 == 0) {
            i4++;
            int[] iArr = this.bits;
            if (i4 == iArr.length) {
                return this.size;
            }
            i5 = iArr[i4] ^ (-1);
        }
        int numberOfTrailingZeros = (i4 * 32) + Integer.numberOfTrailingZeros(i5);
        int i6 = this.size;
        return numberOfTrailingZeros > i6 ? i6 : numberOfTrailingZeros;
    }

    public int getSize() {
        return this.size;
    }

    public int getSizeInBytes() {
        return (this.size + 7) / 8;
    }

    public int hashCode() {
        return (this.size * 31) + Arrays.hashCode(this.bits);
    }

    public boolean isRange(int i2, int i3, boolean z) {
        int i4;
        if (i3 >= i2) {
            if (i3 == i2) {
                return true;
            }
            int i5 = i3 - 1;
            int i6 = i2 / 32;
            int i7 = i5 / 32;
            int i8 = i6;
            while (i8 <= i7) {
                int i9 = i8 > i6 ? 0 : i2 & 31;
                int i10 = i8 < i7 ? 31 : i5 & 31;
                if (i9 == 0 && i10 == 31) {
                    i4 = -1;
                } else {
                    i4 = 0;
                    while (i9 <= i10) {
                        i4 |= 1 << i9;
                        i9++;
                    }
                }
                int i11 = this.bits[i8] & i4;
                if (!z) {
                    i4 = 0;
                }
                if (i11 != i4) {
                    return false;
                }
                i8++;
            }
            return true;
        }
        throw new IllegalArgumentException();
    }

    public void reverse() {
        int[] iArr = new int[this.bits.length];
        int i2 = (this.size - 1) / 32;
        int i3 = i2 + 1;
        for (int i4 = 0; i4 < i3; i4++) {
            long j2 = this.bits[i4];
            long j3 = ((j2 & 1431655765) << 1) | ((j2 >> 1) & 1431655765);
            long j4 = ((j3 & 858993459) << 2) | ((j3 >> 2) & 858993459);
            long j5 = ((j4 & 252645135) << 4) | ((j4 >> 4) & 252645135);
            long j6 = ((j5 & 16711935) << 8) | ((j5 >> 8) & 16711935);
            iArr[i2 - i4] = (int) (((j6 & 65535) << 16) | ((j6 >> 16) & 65535));
        }
        int i5 = this.size;
        int i6 = i3 * 32;
        if (i5 != i6) {
            int i7 = i6 - i5;
            int i8 = 1;
            for (int i9 = 0; i9 < 31 - i7; i9++) {
                i8 = (i8 << 1) | 1;
            }
            int i10 = (iArr[0] >> i7) & i8;
            for (int i11 = 1; i11 < i3; i11++) {
                int i12 = iArr[i11];
                iArr[i11 - 1] = i10 | (i12 << (32 - i7));
                i10 = (i12 >> i7) & i8;
            }
            iArr[i3 - 1] = i10;
        }
        this.bits = iArr;
    }

    public void set(int i2) {
        int[] iArr = this.bits;
        int i3 = i2 / 32;
        iArr[i3] = (1 << (i2 & 31)) | iArr[i3];
    }

    public void setBulk(int i2, int i3) {
        this.bits[i2 / 32] = i3;
    }

    public void setRange(int i2, int i3) {
        if (i3 < i2) {
            throw new IllegalArgumentException();
        }
        if (i3 == i2) {
            return;
        }
        int i4 = i3 - 1;
        int i5 = i2 / 32;
        int i6 = i4 / 32;
        int i7 = i5;
        while (i7 <= i6) {
            int i8 = 0;
            int i9 = i7 > i5 ? 0 : i2 & 31;
            int i10 = i7 < i6 ? 31 : i4 & 31;
            if (i9 == 0 && i10 == 31) {
                i8 = -1;
            } else {
                while (i9 <= i10) {
                    i8 |= 1 << i9;
                    i9++;
                }
            }
            int[] iArr = this.bits;
            iArr[i7] = i8 | iArr[i7];
            i7++;
        }
    }

    public void toBytes(int i2, byte[] bArr, int i3, int i4) {
        for (int i5 = 0; i5 < i4; i5++) {
            int i6 = 0;
            for (int i7 = 0; i7 < 8; i7++) {
                if (get(i2)) {
                    i6 |= 1 << (7 - i7);
                }
                i2++;
            }
            bArr[i3 + i5] = (byte) i6;
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(this.size);
        for (int i2 = 0; i2 < this.size; i2++) {
            if ((i2 & 7) == 0) {
                sb.append(' ');
            }
            sb.append(get(i2) ? 'X' : OrderISVUtil.MONEY_DECIMAL_CHAR);
        }
        return sb.toString();
    }

    public void xor(BitArray bitArray) {
        if (this.bits.length != bitArray.bits.length) {
            throw new IllegalArgumentException("Sizes don't match");
        }
        int i2 = 0;
        while (true) {
            int[] iArr = this.bits;
            if (i2 >= iArr.length) {
                return;
            }
            iArr[i2] = iArr[i2] ^ bitArray.bits[i2];
            i2++;
        }
    }

    /* renamed from: clone  reason: merged with bridge method [inline-methods] */
    public BitArray m14clone() {
        return new BitArray((int[]) this.bits.clone(), this.size);
    }

    public BitArray(int i2) {
        this.size = i2;
        this.bits = makeArray(i2);
    }

    BitArray(int[] iArr, int i2) {
        this.bits = iArr;
        this.size = i2;
    }
}

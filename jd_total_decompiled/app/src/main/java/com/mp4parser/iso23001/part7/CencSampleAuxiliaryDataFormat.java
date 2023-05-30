package com.mp4parser.iso23001.part7;

import com.coremedia.iso.Hex;
import java.math.BigInteger;
import java.util.Arrays;

/* loaded from: classes14.dex */
public class CencSampleAuxiliaryDataFormat {
    public byte[] iv = new byte[0];
    public Pair[] pairs = null;

    /* loaded from: classes14.dex */
    private abstract class AbstractPair implements Pair {
        private AbstractPair() {
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            Pair pair = (Pair) obj;
            return clear() == pair.clear() && encrypted() == pair.encrypted();
        }

        public String toString() {
            return "P(" + clear() + "|" + encrypted() + ")";
        }

        /* synthetic */ AbstractPair(CencSampleAuxiliaryDataFormat cencSampleAuxiliaryDataFormat, AbstractPair abstractPair) {
            this();
        }
    }

    /* loaded from: classes14.dex */
    private class ByteBytePair extends AbstractPair {
        private byte clear;
        private byte encrypted;

        public ByteBytePair(int i2, long j2) {
            super(CencSampleAuxiliaryDataFormat.this, null);
            this.clear = (byte) i2;
            this.encrypted = (byte) j2;
        }

        @Override // com.mp4parser.iso23001.part7.CencSampleAuxiliaryDataFormat.Pair
        public int clear() {
            return this.clear;
        }

        @Override // com.mp4parser.iso23001.part7.CencSampleAuxiliaryDataFormat.Pair
        public long encrypted() {
            return this.encrypted;
        }
    }

    /* loaded from: classes14.dex */
    private class ByteIntPair extends AbstractPair {
        private byte clear;
        private int encrypted;

        public ByteIntPair(int i2, long j2) {
            super(CencSampleAuxiliaryDataFormat.this, null);
            this.clear = (byte) i2;
            this.encrypted = (int) j2;
        }

        @Override // com.mp4parser.iso23001.part7.CencSampleAuxiliaryDataFormat.Pair
        public int clear() {
            return this.clear;
        }

        @Override // com.mp4parser.iso23001.part7.CencSampleAuxiliaryDataFormat.Pair
        public long encrypted() {
            return this.encrypted;
        }
    }

    /* loaded from: classes14.dex */
    private class ByteLongPair extends AbstractPair {
        private byte clear;
        private long encrypted;

        public ByteLongPair(int i2, long j2) {
            super(CencSampleAuxiliaryDataFormat.this, null);
            this.clear = (byte) i2;
            this.encrypted = j2;
        }

        @Override // com.mp4parser.iso23001.part7.CencSampleAuxiliaryDataFormat.Pair
        public int clear() {
            return this.clear;
        }

        @Override // com.mp4parser.iso23001.part7.CencSampleAuxiliaryDataFormat.Pair
        public long encrypted() {
            return this.encrypted;
        }
    }

    /* loaded from: classes14.dex */
    private class ByteShortPair extends AbstractPair {
        private byte clear;
        private short encrypted;

        public ByteShortPair(int i2, long j2) {
            super(CencSampleAuxiliaryDataFormat.this, null);
            this.clear = (byte) i2;
            this.encrypted = (short) j2;
        }

        @Override // com.mp4parser.iso23001.part7.CencSampleAuxiliaryDataFormat.Pair
        public int clear() {
            return this.clear;
        }

        @Override // com.mp4parser.iso23001.part7.CencSampleAuxiliaryDataFormat.Pair
        public long encrypted() {
            return this.encrypted;
        }
    }

    /* loaded from: classes14.dex */
    private class IntBytePair extends AbstractPair {
        private int clear;
        private byte encrypted;

        public IntBytePair(int i2, long j2) {
            super(CencSampleAuxiliaryDataFormat.this, null);
            this.clear = i2;
            this.encrypted = (byte) j2;
        }

        @Override // com.mp4parser.iso23001.part7.CencSampleAuxiliaryDataFormat.Pair
        public int clear() {
            return this.clear;
        }

        @Override // com.mp4parser.iso23001.part7.CencSampleAuxiliaryDataFormat.Pair
        public long encrypted() {
            return this.encrypted;
        }
    }

    /* loaded from: classes14.dex */
    private class IntIntPair extends AbstractPair {
        private int clear;
        private int encrypted;

        public IntIntPair(int i2, long j2) {
            super(CencSampleAuxiliaryDataFormat.this, null);
            this.clear = i2;
            this.encrypted = (int) j2;
        }

        @Override // com.mp4parser.iso23001.part7.CencSampleAuxiliaryDataFormat.Pair
        public int clear() {
            return this.clear;
        }

        @Override // com.mp4parser.iso23001.part7.CencSampleAuxiliaryDataFormat.Pair
        public long encrypted() {
            return this.encrypted;
        }
    }

    /* loaded from: classes14.dex */
    private class IntLongPair extends AbstractPair {
        private int clear;
        private long encrypted;

        public IntLongPair(int i2, long j2) {
            super(CencSampleAuxiliaryDataFormat.this, null);
            this.clear = i2;
            this.encrypted = j2;
        }

        @Override // com.mp4parser.iso23001.part7.CencSampleAuxiliaryDataFormat.Pair
        public int clear() {
            return this.clear;
        }

        @Override // com.mp4parser.iso23001.part7.CencSampleAuxiliaryDataFormat.Pair
        public long encrypted() {
            return this.encrypted;
        }
    }

    /* loaded from: classes14.dex */
    private class IntShortPair extends AbstractPair {
        private int clear;
        private short encrypted;

        public IntShortPair(int i2, long j2) {
            super(CencSampleAuxiliaryDataFormat.this, null);
            this.clear = i2;
            this.encrypted = (short) j2;
        }

        @Override // com.mp4parser.iso23001.part7.CencSampleAuxiliaryDataFormat.Pair
        public int clear() {
            return this.clear;
        }

        @Override // com.mp4parser.iso23001.part7.CencSampleAuxiliaryDataFormat.Pair
        public long encrypted() {
            return this.encrypted;
        }
    }

    /* loaded from: classes14.dex */
    public interface Pair {
        int clear();

        long encrypted();
    }

    /* loaded from: classes14.dex */
    private class ShortBytePair extends AbstractPair {
        private short clear;
        private byte encrypted;

        public ShortBytePair(int i2, long j2) {
            super(CencSampleAuxiliaryDataFormat.this, null);
            this.clear = (short) i2;
            this.encrypted = (byte) j2;
        }

        @Override // com.mp4parser.iso23001.part7.CencSampleAuxiliaryDataFormat.Pair
        public int clear() {
            return this.clear;
        }

        @Override // com.mp4parser.iso23001.part7.CencSampleAuxiliaryDataFormat.Pair
        public long encrypted() {
            return this.encrypted;
        }
    }

    /* loaded from: classes14.dex */
    private class ShortIntPair extends AbstractPair {
        private short clear;
        private int encrypted;

        public ShortIntPair(int i2, long j2) {
            super(CencSampleAuxiliaryDataFormat.this, null);
            this.clear = (short) i2;
            this.encrypted = (int) j2;
        }

        @Override // com.mp4parser.iso23001.part7.CencSampleAuxiliaryDataFormat.Pair
        public int clear() {
            return this.clear;
        }

        @Override // com.mp4parser.iso23001.part7.CencSampleAuxiliaryDataFormat.Pair
        public long encrypted() {
            return this.encrypted;
        }
    }

    /* loaded from: classes14.dex */
    private class ShortLongPair extends AbstractPair {
        private short clear;
        private long encrypted;

        public ShortLongPair(int i2, long j2) {
            super(CencSampleAuxiliaryDataFormat.this, null);
            this.clear = (short) i2;
            this.encrypted = j2;
        }

        @Override // com.mp4parser.iso23001.part7.CencSampleAuxiliaryDataFormat.Pair
        public int clear() {
            return this.clear;
        }

        @Override // com.mp4parser.iso23001.part7.CencSampleAuxiliaryDataFormat.Pair
        public long encrypted() {
            return this.encrypted;
        }
    }

    /* loaded from: classes14.dex */
    private class ShortShortPair extends AbstractPair {
        private short clear;
        private short encrypted;

        public ShortShortPair(int i2, long j2) {
            super(CencSampleAuxiliaryDataFormat.this, null);
            this.clear = (short) i2;
            this.encrypted = (short) j2;
        }

        @Override // com.mp4parser.iso23001.part7.CencSampleAuxiliaryDataFormat.Pair
        public int clear() {
            return this.clear;
        }

        @Override // com.mp4parser.iso23001.part7.CencSampleAuxiliaryDataFormat.Pair
        public long encrypted() {
            return this.encrypted;
        }
    }

    public Pair createPair(int i2, long j2) {
        if (i2 <= 127) {
            if (j2 <= 127) {
                return new ByteBytePair(i2, j2);
            }
            if (j2 <= 32767) {
                return new ByteShortPair(i2, j2);
            }
            if (j2 <= 2147483647L) {
                return new ByteIntPair(i2, j2);
            }
            return new ByteLongPair(i2, j2);
        } else if (i2 <= 32767) {
            if (j2 <= 127) {
                return new ShortBytePair(i2, j2);
            }
            if (j2 <= 32767) {
                return new ShortShortPair(i2, j2);
            }
            if (j2 <= 2147483647L) {
                return new ShortIntPair(i2, j2);
            }
            return new ShortLongPair(i2, j2);
        } else if (j2 <= 127) {
            return new IntBytePair(i2, j2);
        } else {
            if (j2 <= 32767) {
                return new IntShortPair(i2, j2);
            }
            if (j2 <= 2147483647L) {
                return new IntIntPair(i2, j2);
            }
            return new IntLongPair(i2, j2);
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        CencSampleAuxiliaryDataFormat cencSampleAuxiliaryDataFormat = (CencSampleAuxiliaryDataFormat) obj;
        if (new BigInteger(this.iv).equals(new BigInteger(cencSampleAuxiliaryDataFormat.iv))) {
            Pair[] pairArr = this.pairs;
            Pair[] pairArr2 = cencSampleAuxiliaryDataFormat.pairs;
            return pairArr == null ? pairArr2 == null : Arrays.equals(pairArr, pairArr2);
        }
        return false;
    }

    public int getSize() {
        int length = this.iv.length;
        Pair[] pairArr = this.pairs;
        return (pairArr == null || pairArr.length <= 0) ? length : length + 2 + (pairArr.length * 6);
    }

    public int hashCode() {
        byte[] bArr = this.iv;
        int hashCode = (bArr != null ? Arrays.hashCode(bArr) : 0) * 31;
        Pair[] pairArr = this.pairs;
        return hashCode + (pairArr != null ? Arrays.hashCode(pairArr) : 0);
    }

    public String toString() {
        return "Entry{iv=" + Hex.encodeHex(this.iv) + ", pairs=" + Arrays.toString(this.pairs) + '}';
    }
}

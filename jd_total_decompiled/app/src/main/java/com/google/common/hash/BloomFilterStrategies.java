package com.google.common.hash;

import com.google.common.base.Preconditions;
import com.google.common.hash.BloomFilter;
import com.google.common.math.LongMath;
import com.google.common.primitives.Ints;
import com.google.common.primitives.Longs;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicLongArray;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes12.dex */
public enum BloomFilterStrategies implements BloomFilter.Strategy {
    MURMUR128_MITZ_32 { // from class: com.google.common.hash.BloomFilterStrategies.1
        @Override // com.google.common.hash.BloomFilter.Strategy
        public <T> boolean mightContain(T t, Funnel<? super T> funnel, int i2, LockFreeBitArray lockFreeBitArray) {
            long bitSize = lockFreeBitArray.bitSize();
            long asLong = Hashing.murmur3_128().hashObject(t, funnel).asLong();
            int i3 = (int) asLong;
            int i4 = (int) (asLong >>> 32);
            for (int i5 = 1; i5 <= i2; i5++) {
                int i6 = (i5 * i4) + i3;
                if (i6 < 0) {
                    i6 ^= -1;
                }
                if (!lockFreeBitArray.get(i6 % bitSize)) {
                    return false;
                }
            }
            return true;
        }

        @Override // com.google.common.hash.BloomFilter.Strategy
        public <T> boolean put(T t, Funnel<? super T> funnel, int i2, LockFreeBitArray lockFreeBitArray) {
            long bitSize = lockFreeBitArray.bitSize();
            long asLong = Hashing.murmur3_128().hashObject(t, funnel).asLong();
            int i3 = (int) asLong;
            int i4 = (int) (asLong >>> 32);
            boolean z = false;
            for (int i5 = 1; i5 <= i2; i5++) {
                int i6 = (i5 * i4) + i3;
                if (i6 < 0) {
                    i6 ^= -1;
                }
                z |= lockFreeBitArray.set(i6 % bitSize);
            }
            return z;
        }
    },
    MURMUR128_MITZ_64 { // from class: com.google.common.hash.BloomFilterStrategies.2
        private long lowerEight(byte[] bArr) {
            return Longs.fromBytes(bArr[7], bArr[6], bArr[5], bArr[4], bArr[3], bArr[2], bArr[1], bArr[0]);
        }

        private long upperEight(byte[] bArr) {
            return Longs.fromBytes(bArr[15], bArr[14], bArr[13], bArr[12], bArr[11], bArr[10], bArr[9], bArr[8]);
        }

        @Override // com.google.common.hash.BloomFilter.Strategy
        public <T> boolean mightContain(T t, Funnel<? super T> funnel, int i2, LockFreeBitArray lockFreeBitArray) {
            long bitSize = lockFreeBitArray.bitSize();
            byte[] bytesInternal = Hashing.murmur3_128().hashObject(t, funnel).getBytesInternal();
            long lowerEight = lowerEight(bytesInternal);
            long upperEight = upperEight(bytesInternal);
            for (int i3 = 0; i3 < i2; i3++) {
                if (!lockFreeBitArray.get((Long.MAX_VALUE & lowerEight) % bitSize)) {
                    return false;
                }
                lowerEight += upperEight;
            }
            return true;
        }

        @Override // com.google.common.hash.BloomFilter.Strategy
        public <T> boolean put(T t, Funnel<? super T> funnel, int i2, LockFreeBitArray lockFreeBitArray) {
            long bitSize = lockFreeBitArray.bitSize();
            byte[] bytesInternal = Hashing.murmur3_128().hashObject(t, funnel).getBytesInternal();
            long lowerEight = lowerEight(bytesInternal);
            long upperEight = upperEight(bytesInternal);
            boolean z = false;
            for (int i3 = 0; i3 < i2; i3++) {
                z |= lockFreeBitArray.set((Long.MAX_VALUE & lowerEight) % bitSize);
                lowerEight += upperEight;
            }
            return z;
        }
    };

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes12.dex */
    public static final class LockFreeBitArray {
        private static final int LONG_ADDRESSABLE_BITS = 6;
        private final LongAddable bitCount;
        final AtomicLongArray data;

        /* JADX INFO: Access modifiers changed from: package-private */
        public LockFreeBitArray(long j2) {
            this(new long[Ints.checkedCast(LongMath.divide(j2, 64L, RoundingMode.CEILING))]);
        }

        public static long[] toPlainArray(AtomicLongArray atomicLongArray) {
            int length = atomicLongArray.length();
            long[] jArr = new long[length];
            for (int i2 = 0; i2 < length; i2++) {
                jArr[i2] = atomicLongArray.get(i2);
            }
            return jArr;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public long bitCount() {
            return this.bitCount.sum();
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public long bitSize() {
            return this.data.length() * 64;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public LockFreeBitArray copy() {
            return new LockFreeBitArray(toPlainArray(this.data));
        }

        public boolean equals(@NullableDecl Object obj) {
            if (obj instanceof LockFreeBitArray) {
                return Arrays.equals(toPlainArray(this.data), toPlainArray(((LockFreeBitArray) obj).data));
            }
            return false;
        }

        boolean get(long j2) {
            return ((1 << ((int) j2)) & this.data.get((int) (j2 >>> 6))) != 0;
        }

        public int hashCode() {
            return Arrays.hashCode(toPlainArray(this.data));
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public void putAll(LockFreeBitArray lockFreeBitArray) {
            long j2;
            long j3;
            boolean z;
            Preconditions.checkArgument(this.data.length() == lockFreeBitArray.data.length(), "BitArrays must be of equal length (%s != %s)", this.data.length(), lockFreeBitArray.data.length());
            for (int i2 = 0; i2 < this.data.length(); i2++) {
                long j4 = lockFreeBitArray.data.get(i2);
                while (true) {
                    j2 = this.data.get(i2);
                    j3 = j2 | j4;
                    if (j2 == j3) {
                        z = false;
                        break;
                    } else if (this.data.compareAndSet(i2, j2, j3)) {
                        z = true;
                        break;
                    }
                }
                if (z) {
                    this.bitCount.add(Long.bitCount(j3) - Long.bitCount(j2));
                }
            }
        }

        boolean set(long j2) {
            long j3;
            long j4;
            if (get(j2)) {
                return false;
            }
            int i2 = (int) (j2 >>> 6);
            long j5 = 1 << ((int) j2);
            do {
                j3 = this.data.get(i2);
                j4 = j3 | j5;
                if (j3 == j4) {
                    return false;
                }
            } while (!this.data.compareAndSet(i2, j3, j4));
            this.bitCount.increment();
            return true;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public LockFreeBitArray(long[] jArr) {
            Preconditions.checkArgument(jArr.length > 0, "data length is zero!");
            this.data = new AtomicLongArray(jArr);
            this.bitCount = LongAddables.create();
            long j2 = 0;
            for (long j3 : jArr) {
                j2 += Long.bitCount(j3);
            }
            this.bitCount.add(j2);
        }
    }
}

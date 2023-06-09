package com.google.common.hash;

import com.google.common.primitives.UnsignedBytes;
import java.io.Serializable;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes12.dex */
public final class Murmur3_128HashFunction extends AbstractHashFunction implements Serializable {
    private static final long serialVersionUID = 0;
    private final int seed;
    static final HashFunction MURMUR3_128 = new Murmur3_128HashFunction(0);
    static final HashFunction GOOD_FAST_HASH_128 = new Murmur3_128HashFunction(Hashing.GOOD_FAST_HASH_SEED);

    /* loaded from: classes12.dex */
    private static final class Murmur3_128Hasher extends AbstractStreamingHasher {
        private static final long C1 = -8663945395140668459L;
        private static final long C2 = 5545529020109919103L;
        private static final int CHUNK_SIZE = 16;
        private long h1;
        private long h2;
        private int length;

        Murmur3_128Hasher(int i2) {
            super(16);
            long j2 = i2;
            this.h1 = j2;
            this.h2 = j2;
            this.length = 0;
        }

        private void bmix64(long j2, long j3) {
            long mixK1 = mixK1(j2) ^ this.h1;
            this.h1 = mixK1;
            long rotateLeft = Long.rotateLeft(mixK1, 27);
            this.h1 = rotateLeft;
            long j4 = this.h2;
            long j5 = rotateLeft + j4;
            this.h1 = j5;
            this.h1 = (j5 * 5) + 1390208809;
            long mixK2 = mixK2(j3) ^ j4;
            this.h2 = mixK2;
            long rotateLeft2 = Long.rotateLeft(mixK2, 31);
            this.h2 = rotateLeft2;
            long j6 = rotateLeft2 + this.h1;
            this.h2 = j6;
            this.h2 = (j6 * 5) + 944331445;
        }

        private static long fmix64(long j2) {
            long j3 = (j2 ^ (j2 >>> 33)) * (-49064778989728563L);
            long j4 = (j3 ^ (j3 >>> 33)) * (-4265267296055464877L);
            return j4 ^ (j4 >>> 33);
        }

        private static long mixK1(long j2) {
            return Long.rotateLeft(j2 * C1, 31) * C2;
        }

        private static long mixK2(long j2) {
            return Long.rotateLeft(j2 * C2, 33) * C1;
        }

        @Override // com.google.common.hash.AbstractStreamingHasher
        public HashCode makeHash() {
            long j2 = this.h1;
            int i2 = this.length;
            long j3 = j2 ^ i2;
            this.h1 = j3;
            long j4 = this.h2 ^ i2;
            this.h2 = j4;
            long j5 = j3 + j4;
            this.h1 = j5;
            this.h2 = j4 + j5;
            this.h1 = fmix64(j5);
            long fmix64 = fmix64(this.h2);
            this.h2 = fmix64;
            long j6 = this.h1 + fmix64;
            this.h1 = j6;
            this.h2 = fmix64 + j6;
            return HashCode.fromBytesNoCopy(ByteBuffer.wrap(new byte[16]).order(ByteOrder.LITTLE_ENDIAN).putLong(this.h1).putLong(this.h2).array());
        }

        @Override // com.google.common.hash.AbstractStreamingHasher
        protected void process(ByteBuffer byteBuffer) {
            bmix64(byteBuffer.getLong(), byteBuffer.getLong());
            this.length += 16;
        }

        @Override // com.google.common.hash.AbstractStreamingHasher
        protected void processRemaining(ByteBuffer byteBuffer) {
            long j2;
            long j3;
            long j4;
            long j5;
            long j6;
            long j7;
            long j8;
            long j9;
            long j10;
            long j11;
            long j12;
            long j13;
            long j14;
            long j15;
            this.length += byteBuffer.remaining();
            long j16 = 0;
            switch (byteBuffer.remaining()) {
                case 1:
                    j2 = 0;
                    j8 = UnsignedBytes.toInt(byteBuffer.get(0)) ^ j2;
                    this.h1 ^= mixK1(j8);
                    this.h2 ^= mixK2(j16);
                    return;
                case 2:
                    j3 = 0;
                    j2 = j3 ^ (UnsignedBytes.toInt(byteBuffer.get(1)) << 8);
                    j8 = UnsignedBytes.toInt(byteBuffer.get(0)) ^ j2;
                    this.h1 ^= mixK1(j8);
                    this.h2 ^= mixK2(j16);
                    return;
                case 3:
                    j4 = 0;
                    j3 = j4 ^ (UnsignedBytes.toInt(byteBuffer.get(2)) << 16);
                    j2 = j3 ^ (UnsignedBytes.toInt(byteBuffer.get(1)) << 8);
                    j8 = UnsignedBytes.toInt(byteBuffer.get(0)) ^ j2;
                    this.h1 ^= mixK1(j8);
                    this.h2 ^= mixK2(j16);
                    return;
                case 4:
                    j5 = 0;
                    j4 = j5 ^ (UnsignedBytes.toInt(byteBuffer.get(3)) << 24);
                    j3 = j4 ^ (UnsignedBytes.toInt(byteBuffer.get(2)) << 16);
                    j2 = j3 ^ (UnsignedBytes.toInt(byteBuffer.get(1)) << 8);
                    j8 = UnsignedBytes.toInt(byteBuffer.get(0)) ^ j2;
                    this.h1 ^= mixK1(j8);
                    this.h2 ^= mixK2(j16);
                    return;
                case 5:
                    j6 = 0;
                    j5 = j6 ^ (UnsignedBytes.toInt(byteBuffer.get(4)) << 32);
                    j4 = j5 ^ (UnsignedBytes.toInt(byteBuffer.get(3)) << 24);
                    j3 = j4 ^ (UnsignedBytes.toInt(byteBuffer.get(2)) << 16);
                    j2 = j3 ^ (UnsignedBytes.toInt(byteBuffer.get(1)) << 8);
                    j8 = UnsignedBytes.toInt(byteBuffer.get(0)) ^ j2;
                    this.h1 ^= mixK1(j8);
                    this.h2 ^= mixK2(j16);
                    return;
                case 6:
                    j7 = 0;
                    j6 = j7 ^ (UnsignedBytes.toInt(byteBuffer.get(5)) << 40);
                    j5 = j6 ^ (UnsignedBytes.toInt(byteBuffer.get(4)) << 32);
                    j4 = j5 ^ (UnsignedBytes.toInt(byteBuffer.get(3)) << 24);
                    j3 = j4 ^ (UnsignedBytes.toInt(byteBuffer.get(2)) << 16);
                    j2 = j3 ^ (UnsignedBytes.toInt(byteBuffer.get(1)) << 8);
                    j8 = UnsignedBytes.toInt(byteBuffer.get(0)) ^ j2;
                    this.h1 ^= mixK1(j8);
                    this.h2 ^= mixK2(j16);
                    return;
                case 7:
                    j7 = (UnsignedBytes.toInt(byteBuffer.get(6)) << 48) ^ 0;
                    j6 = j7 ^ (UnsignedBytes.toInt(byteBuffer.get(5)) << 40);
                    j5 = j6 ^ (UnsignedBytes.toInt(byteBuffer.get(4)) << 32);
                    j4 = j5 ^ (UnsignedBytes.toInt(byteBuffer.get(3)) << 24);
                    j3 = j4 ^ (UnsignedBytes.toInt(byteBuffer.get(2)) << 16);
                    j2 = j3 ^ (UnsignedBytes.toInt(byteBuffer.get(1)) << 8);
                    j8 = UnsignedBytes.toInt(byteBuffer.get(0)) ^ j2;
                    this.h1 ^= mixK1(j8);
                    this.h2 ^= mixK2(j16);
                    return;
                case 8:
                    j9 = 0;
                    j8 = byteBuffer.getLong() ^ 0;
                    j16 = j9;
                    this.h1 ^= mixK1(j8);
                    this.h2 ^= mixK2(j16);
                    return;
                case 9:
                    j10 = 0;
                    j9 = j10 ^ UnsignedBytes.toInt(byteBuffer.get(8));
                    j8 = byteBuffer.getLong() ^ 0;
                    j16 = j9;
                    this.h1 ^= mixK1(j8);
                    this.h2 ^= mixK2(j16);
                    return;
                case 10:
                    j11 = 0;
                    j10 = j11 ^ (UnsignedBytes.toInt(byteBuffer.get(9)) << 8);
                    j9 = j10 ^ UnsignedBytes.toInt(byteBuffer.get(8));
                    j8 = byteBuffer.getLong() ^ 0;
                    j16 = j9;
                    this.h1 ^= mixK1(j8);
                    this.h2 ^= mixK2(j16);
                    return;
                case 11:
                    j12 = 0;
                    j11 = j12 ^ (UnsignedBytes.toInt(byteBuffer.get(10)) << 16);
                    j10 = j11 ^ (UnsignedBytes.toInt(byteBuffer.get(9)) << 8);
                    j9 = j10 ^ UnsignedBytes.toInt(byteBuffer.get(8));
                    j8 = byteBuffer.getLong() ^ 0;
                    j16 = j9;
                    this.h1 ^= mixK1(j8);
                    this.h2 ^= mixK2(j16);
                    return;
                case 12:
                    j13 = 0;
                    j12 = j13 ^ (UnsignedBytes.toInt(byteBuffer.get(11)) << 24);
                    j11 = j12 ^ (UnsignedBytes.toInt(byteBuffer.get(10)) << 16);
                    j10 = j11 ^ (UnsignedBytes.toInt(byteBuffer.get(9)) << 8);
                    j9 = j10 ^ UnsignedBytes.toInt(byteBuffer.get(8));
                    j8 = byteBuffer.getLong() ^ 0;
                    j16 = j9;
                    this.h1 ^= mixK1(j8);
                    this.h2 ^= mixK2(j16);
                    return;
                case 13:
                    j14 = 0;
                    j13 = j14 ^ (UnsignedBytes.toInt(byteBuffer.get(12)) << 32);
                    j12 = j13 ^ (UnsignedBytes.toInt(byteBuffer.get(11)) << 24);
                    j11 = j12 ^ (UnsignedBytes.toInt(byteBuffer.get(10)) << 16);
                    j10 = j11 ^ (UnsignedBytes.toInt(byteBuffer.get(9)) << 8);
                    j9 = j10 ^ UnsignedBytes.toInt(byteBuffer.get(8));
                    j8 = byteBuffer.getLong() ^ 0;
                    j16 = j9;
                    this.h1 ^= mixK1(j8);
                    this.h2 ^= mixK2(j16);
                    return;
                case 14:
                    j15 = 0;
                    j14 = j15 ^ (UnsignedBytes.toInt(byteBuffer.get(13)) << 40);
                    j13 = j14 ^ (UnsignedBytes.toInt(byteBuffer.get(12)) << 32);
                    j12 = j13 ^ (UnsignedBytes.toInt(byteBuffer.get(11)) << 24);
                    j11 = j12 ^ (UnsignedBytes.toInt(byteBuffer.get(10)) << 16);
                    j10 = j11 ^ (UnsignedBytes.toInt(byteBuffer.get(9)) << 8);
                    j9 = j10 ^ UnsignedBytes.toInt(byteBuffer.get(8));
                    j8 = byteBuffer.getLong() ^ 0;
                    j16 = j9;
                    this.h1 ^= mixK1(j8);
                    this.h2 ^= mixK2(j16);
                    return;
                case 15:
                    j15 = (UnsignedBytes.toInt(byteBuffer.get(14)) << 48) ^ 0;
                    j14 = j15 ^ (UnsignedBytes.toInt(byteBuffer.get(13)) << 40);
                    j13 = j14 ^ (UnsignedBytes.toInt(byteBuffer.get(12)) << 32);
                    j12 = j13 ^ (UnsignedBytes.toInt(byteBuffer.get(11)) << 24);
                    j11 = j12 ^ (UnsignedBytes.toInt(byteBuffer.get(10)) << 16);
                    j10 = j11 ^ (UnsignedBytes.toInt(byteBuffer.get(9)) << 8);
                    j9 = j10 ^ UnsignedBytes.toInt(byteBuffer.get(8));
                    j8 = byteBuffer.getLong() ^ 0;
                    j16 = j9;
                    this.h1 ^= mixK1(j8);
                    this.h2 ^= mixK2(j16);
                    return;
                default:
                    throw new AssertionError("Should never get here.");
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Murmur3_128HashFunction(int i2) {
        this.seed = i2;
    }

    @Override // com.google.common.hash.HashFunction
    public int bits() {
        return 128;
    }

    public boolean equals(@NullableDecl Object obj) {
        return (obj instanceof Murmur3_128HashFunction) && this.seed == ((Murmur3_128HashFunction) obj).seed;
    }

    public int hashCode() {
        return Murmur3_128HashFunction.class.hashCode() ^ this.seed;
    }

    @Override // com.google.common.hash.HashFunction
    public Hasher newHasher() {
        return new Murmur3_128Hasher(this.seed);
    }

    public String toString() {
        return "Hashing.murmur3_128(" + this.seed + ")";
    }
}

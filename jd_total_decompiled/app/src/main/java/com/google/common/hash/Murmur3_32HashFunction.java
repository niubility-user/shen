package com.google.common.hash;

import com.google.common.base.Charsets;
import com.google.common.base.Preconditions;
import com.google.common.primitives.Ints;
import com.google.common.primitives.UnsignedBytes;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.jingdong.sdk.platform.business.personal.R2;
import java.io.Serializable;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.Charset;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes12.dex */
public final class Murmur3_32HashFunction extends AbstractHashFunction implements Serializable {
    private static final int C1 = -862048943;
    private static final int C2 = 461845907;
    private static final int CHUNK_SIZE = 4;
    private static final long serialVersionUID = 0;
    private final int seed;
    static final HashFunction MURMUR3_32 = new Murmur3_32HashFunction(0);
    static final HashFunction GOOD_FAST_HASH_32 = new Murmur3_32HashFunction(Hashing.GOOD_FAST_HASH_SEED);

    @CanIgnoreReturnValue
    /* loaded from: classes12.dex */
    private static final class Murmur3_32Hasher extends AbstractHasher {
        private long buffer;
        private int h1;
        private int shift;
        private int length = 0;
        private boolean isDone = false;

        Murmur3_32Hasher(int i2) {
            this.h1 = i2;
        }

        private void update(int i2, long j2) {
            long j3 = this.buffer;
            int i3 = this.shift;
            long j4 = ((j2 & 4294967295L) << i3) | j3;
            this.buffer = j4;
            int i4 = i3 + (i2 * 8);
            this.shift = i4;
            this.length += i2;
            if (i4 >= 32) {
                this.h1 = Murmur3_32HashFunction.mixH1(this.h1, Murmur3_32HashFunction.mixK1((int) j4));
                this.buffer >>>= 32;
                this.shift -= 32;
            }
        }

        @Override // com.google.common.hash.Hasher
        public HashCode hash() {
            Preconditions.checkState(!this.isDone);
            this.isDone = true;
            int mixK1 = this.h1 ^ Murmur3_32HashFunction.mixK1((int) this.buffer);
            this.h1 = mixK1;
            return Murmur3_32HashFunction.fmix(mixK1, this.length);
        }

        @Override // com.google.common.hash.PrimitiveSink
        public Hasher putByte(byte b) {
            update(1, b & 255);
            return this;
        }

        @Override // com.google.common.hash.AbstractHasher, com.google.common.hash.PrimitiveSink
        public Hasher putChar(char c2) {
            update(2, c2);
            return this;
        }

        @Override // com.google.common.hash.AbstractHasher, com.google.common.hash.PrimitiveSink
        public Hasher putInt(int i2) {
            update(4, i2);
            return this;
        }

        @Override // com.google.common.hash.AbstractHasher, com.google.common.hash.PrimitiveSink
        public Hasher putLong(long j2) {
            update(4, (int) j2);
            update(4, j2 >>> 32);
            return this;
        }

        @Override // com.google.common.hash.AbstractHasher, com.google.common.hash.PrimitiveSink
        public Hasher putString(CharSequence charSequence, Charset charset) {
            if (Charsets.UTF_8.equals(charset)) {
                int length = charSequence.length();
                int i2 = 0;
                while (true) {
                    int i3 = i2 + 4;
                    if (i3 > length) {
                        break;
                    }
                    char charAt = charSequence.charAt(i2);
                    char charAt2 = charSequence.charAt(i2 + 1);
                    char charAt3 = charSequence.charAt(i2 + 2);
                    char charAt4 = charSequence.charAt(i2 + 3);
                    if (charAt >= '\u0080' || charAt2 >= '\u0080' || charAt3 >= '\u0080' || charAt4 >= '\u0080') {
                        break;
                    }
                    update(4, (charAt2 << '\b') | charAt | (charAt3 << 16) | (charAt4 << 24));
                    i2 = i3;
                }
                while (i2 < length) {
                    char charAt5 = charSequence.charAt(i2);
                    if (charAt5 < '\u0080') {
                        update(1, charAt5);
                    } else if (charAt5 < '\u0800') {
                        update(2, Murmur3_32HashFunction.charToTwoUtf8Bytes(charAt5));
                    } else if (charAt5 < '\ud800' || charAt5 > '\udfff') {
                        update(3, Murmur3_32HashFunction.charToThreeUtf8Bytes(charAt5));
                    } else {
                        int codePointAt = Character.codePointAt(charSequence, i2);
                        if (codePointAt != charAt5) {
                            i2++;
                            update(4, Murmur3_32HashFunction.codePointToFourUtf8Bytes(codePointAt));
                        } else {
                            putBytes(charSequence.subSequence(i2, length).toString().getBytes(charset));
                            return this;
                        }
                    }
                    i2++;
                }
                return this;
            }
            return super.putString(charSequence, charset);
        }

        @Override // com.google.common.hash.AbstractHasher, com.google.common.hash.PrimitiveSink
        public Hasher putBytes(byte[] bArr, int i2, int i3) {
            Preconditions.checkPositionIndexes(i2, i2 + i3, bArr.length);
            int i4 = 0;
            while (true) {
                int i5 = i4 + 4;
                if (i5 > i3) {
                    break;
                }
                update(4, Murmur3_32HashFunction.getIntLittleEndian(bArr, i4 + i2));
                i4 = i5;
            }
            while (i4 < i3) {
                putByte(bArr[i2 + i4]);
                i4++;
            }
            return this;
        }

        @Override // com.google.common.hash.AbstractHasher, com.google.common.hash.PrimitiveSink
        public Hasher putBytes(ByteBuffer byteBuffer) {
            ByteOrder order = byteBuffer.order();
            byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
            while (byteBuffer.remaining() >= 4) {
                putInt(byteBuffer.getInt());
            }
            while (byteBuffer.hasRemaining()) {
                putByte(byteBuffer.get());
            }
            byteBuffer.order(order);
            return this;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Murmur3_32HashFunction(int i2) {
        this.seed = i2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static long charToThreeUtf8Bytes(char c2) {
        return (((c2 & '?') | 128) << 16) | (((c2 >>> '\f') | 480) & 255) | ((((c2 >>> 6) & 63) | 128) << 8);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static long charToTwoUtf8Bytes(char c2) {
        return (((c2 & '?') | 128) << 8) | (((c2 >>> 6) | R2.attr.fragmentStyle) & 255);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static long codePointToFourUtf8Bytes(int i2) {
        return (((i2 >>> 18) | 240) & 255) | ((((i2 >>> 12) & 63) | 128) << 8) | ((((i2 >>> 6) & 63) | 128) << 16) | (((i2 & 63) | 128) << 24);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static HashCode fmix(int i2, int i3) {
        int i4 = i2 ^ i3;
        int i5 = (i4 ^ (i4 >>> 16)) * (-2048144789);
        int i6 = (i5 ^ (i5 >>> 13)) * (-1028477387);
        return HashCode.fromInt(i6 ^ (i6 >>> 16));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int getIntLittleEndian(byte[] bArr, int i2) {
        return Ints.fromBytes(bArr[i2 + 3], bArr[i2 + 2], bArr[i2 + 1], bArr[i2]);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int mixH1(int i2, int i3) {
        return (Integer.rotateLeft(i2 ^ i3, 13) * 5) - 430675100;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int mixK1(int i2) {
        return Integer.rotateLeft(i2 * C1, 15) * C2;
    }

    @Override // com.google.common.hash.HashFunction
    public int bits() {
        return 32;
    }

    public boolean equals(@NullableDecl Object obj) {
        return (obj instanceof Murmur3_32HashFunction) && this.seed == ((Murmur3_32HashFunction) obj).seed;
    }

    @Override // com.google.common.hash.AbstractHashFunction, com.google.common.hash.HashFunction
    public HashCode hashBytes(byte[] bArr, int i2, int i3) {
        Preconditions.checkPositionIndexes(i2, i2 + i3, bArr.length);
        int i4 = this.seed;
        int i5 = 0;
        int i6 = 0;
        while (true) {
            int i7 = i6 + 4;
            if (i7 > i3) {
                break;
            }
            i4 = mixH1(i4, mixK1(getIntLittleEndian(bArr, i6 + i2)));
            i6 = i7;
        }
        int i8 = i6;
        int i9 = 0;
        while (i8 < i3) {
            i5 ^= UnsignedBytes.toInt(bArr[i2 + i8]) << i9;
            i8++;
            i9 += 8;
        }
        return fmix(mixK1(i5) ^ i4, i3);
    }

    public int hashCode() {
        return Murmur3_32HashFunction.class.hashCode() ^ this.seed;
    }

    @Override // com.google.common.hash.AbstractHashFunction, com.google.common.hash.HashFunction
    public HashCode hashInt(int i2) {
        return fmix(mixH1(this.seed, mixK1(i2)), 4);
    }

    @Override // com.google.common.hash.AbstractHashFunction, com.google.common.hash.HashFunction
    public HashCode hashLong(long j2) {
        int i2 = (int) (j2 >>> 32);
        return fmix(mixH1(mixH1(this.seed, mixK1((int) j2)), mixK1(i2)), 8);
    }

    @Override // com.google.common.hash.AbstractHashFunction, com.google.common.hash.HashFunction
    public HashCode hashString(CharSequence charSequence, Charset charset) {
        if (Charsets.UTF_8.equals(charset)) {
            int length = charSequence.length();
            int i2 = this.seed;
            int i3 = 0;
            int i4 = 0;
            int i5 = 0;
            while (true) {
                int i6 = i4 + 4;
                if (i6 > length) {
                    break;
                }
                char charAt = charSequence.charAt(i4);
                char charAt2 = charSequence.charAt(i4 + 1);
                char charAt3 = charSequence.charAt(i4 + 2);
                char charAt4 = charSequence.charAt(i4 + 3);
                if (charAt >= '\u0080' || charAt2 >= '\u0080' || charAt3 >= '\u0080' || charAt4 >= '\u0080') {
                    break;
                }
                i2 = mixH1(i2, mixK1((charAt2 << '\b') | charAt | (charAt3 << 16) | (charAt4 << 24)));
                i5 += 4;
                i4 = i6;
            }
            long j2 = 0;
            while (i4 < length) {
                char charAt5 = charSequence.charAt(i4);
                if (charAt5 < '\u0080') {
                    j2 |= charAt5 << i3;
                    i3 += 8;
                    i5++;
                } else if (charAt5 < '\u0800') {
                    j2 |= charToTwoUtf8Bytes(charAt5) << i3;
                    i3 += 16;
                    i5 += 2;
                } else if (charAt5 >= '\ud800' && charAt5 <= '\udfff') {
                    int codePointAt = Character.codePointAt(charSequence, i4);
                    if (codePointAt == charAt5) {
                        return hashBytes(charSequence.toString().getBytes(charset));
                    }
                    i4++;
                    j2 |= codePointToFourUtf8Bytes(codePointAt) << i3;
                    i5 += 4;
                } else {
                    j2 |= charToThreeUtf8Bytes(charAt5) << i3;
                    i3 += 24;
                    i5 += 3;
                }
                if (i3 >= 32) {
                    i2 = mixH1(i2, mixK1((int) j2));
                    j2 >>>= 32;
                    i3 -= 32;
                }
                i4++;
            }
            return fmix(mixK1((int) j2) ^ i2, i5);
        }
        return hashBytes(charSequence.toString().getBytes(charset));
    }

    @Override // com.google.common.hash.AbstractHashFunction, com.google.common.hash.HashFunction
    public HashCode hashUnencodedChars(CharSequence charSequence) {
        int i2 = this.seed;
        for (int i3 = 1; i3 < charSequence.length(); i3 += 2) {
            i2 = mixH1(i2, mixK1(charSequence.charAt(i3 - 1) | (charSequence.charAt(i3) << 16)));
        }
        if ((charSequence.length() & 1) == 1) {
            i2 ^= mixK1(charSequence.charAt(charSequence.length() - 1));
        }
        return fmix(i2, charSequence.length() * 2);
    }

    @Override // com.google.common.hash.HashFunction
    public Hasher newHasher() {
        return new Murmur3_32Hasher(this.seed);
    }

    public String toString() {
        return "Hashing.murmur3_32(" + this.seed + ")";
    }
}

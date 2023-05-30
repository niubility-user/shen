package com.google.common.hash;

import com.google.common.base.Preconditions;
import java.io.Serializable;
import java.nio.ByteBuffer;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

/* loaded from: classes12.dex */
final class SipHashFunction extends AbstractHashFunction implements Serializable {
    static final HashFunction SIP_HASH_24 = new SipHashFunction(2, 4, 506097522914230528L, 1084818905618843912L);
    private static final long serialVersionUID = 0;

    /* renamed from: c  reason: collision with root package name */
    private final int f1071c;
    private final int d;
    private final long k0;
    private final long k1;

    /* loaded from: classes12.dex */
    private static final class SipHasher extends AbstractStreamingHasher {
        private static final int CHUNK_SIZE = 8;
        private long b;

        /* renamed from: c  reason: collision with root package name */
        private final int f1072c;
        private final int d;
        private long finalM;
        private long v0;
        private long v1;
        private long v2;
        private long v3;

        SipHasher(int i2, int i3, long j2, long j3) {
            super(8);
            this.v0 = 8317987319222330741L;
            this.v1 = 7237128888997146477L;
            this.v2 = 7816392313619706465L;
            this.v3 = 8387220255154660723L;
            this.b = 0L;
            this.finalM = 0L;
            this.f1072c = i2;
            this.d = i3;
            this.v0 = 8317987319222330741L ^ j2;
            this.v1 = 7237128888997146477L ^ j3;
            this.v2 = 7816392313619706465L ^ j2;
            this.v3 = 8387220255154660723L ^ j3;
        }

        private void processM(long j2) {
            this.v3 ^= j2;
            sipRound(this.f1072c);
            this.v0 = j2 ^ this.v0;
        }

        private void sipRound(int i2) {
            for (int i3 = 0; i3 < i2; i3++) {
                long j2 = this.v0;
                long j3 = this.v1;
                this.v0 = j2 + j3;
                this.v2 += this.v3;
                this.v1 = Long.rotateLeft(j3, 13);
                long rotateLeft = Long.rotateLeft(this.v3, 16);
                this.v3 = rotateLeft;
                long j4 = this.v1;
                long j5 = this.v0;
                this.v1 = j4 ^ j5;
                this.v3 = rotateLeft ^ this.v2;
                long rotateLeft2 = Long.rotateLeft(j5, 32);
                this.v0 = rotateLeft2;
                long j6 = this.v2;
                long j7 = this.v1;
                this.v2 = j6 + j7;
                this.v0 = rotateLeft2 + this.v3;
                this.v1 = Long.rotateLeft(j7, 17);
                long rotateLeft3 = Long.rotateLeft(this.v3, 21);
                this.v3 = rotateLeft3;
                long j8 = this.v1;
                long j9 = this.v2;
                this.v1 = j8 ^ j9;
                this.v3 = rotateLeft3 ^ this.v0;
                this.v2 = Long.rotateLeft(j9, 32);
            }
        }

        @Override // com.google.common.hash.AbstractStreamingHasher
        public HashCode makeHash() {
            long j2 = this.finalM ^ (this.b << 56);
            this.finalM = j2;
            processM(j2);
            this.v2 ^= 255;
            sipRound(this.d);
            return HashCode.fromLong(((this.v0 ^ this.v1) ^ this.v2) ^ this.v3);
        }

        @Override // com.google.common.hash.AbstractStreamingHasher
        protected void process(ByteBuffer byteBuffer) {
            this.b += 8;
            processM(byteBuffer.getLong());
        }

        @Override // com.google.common.hash.AbstractStreamingHasher
        protected void processRemaining(ByteBuffer byteBuffer) {
            this.b += byteBuffer.remaining();
            int i2 = 0;
            while (byteBuffer.hasRemaining()) {
                this.finalM ^= (byteBuffer.get() & 255) << i2;
                i2 += 8;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public SipHashFunction(int i2, int i3, long j2, long j3) {
        Preconditions.checkArgument(i2 > 0, "The number of SipRound iterations (c=%s) during Compression must be positive.", i2);
        Preconditions.checkArgument(i3 > 0, "The number of SipRound iterations (d=%s) during Finalization must be positive.", i3);
        this.f1071c = i2;
        this.d = i3;
        this.k0 = j2;
        this.k1 = j3;
    }

    @Override // com.google.common.hash.HashFunction
    public int bits() {
        return 64;
    }

    public boolean equals(@NullableDecl Object obj) {
        if (obj instanceof SipHashFunction) {
            SipHashFunction sipHashFunction = (SipHashFunction) obj;
            return this.f1071c == sipHashFunction.f1071c && this.d == sipHashFunction.d && this.k0 == sipHashFunction.k0 && this.k1 == sipHashFunction.k1;
        }
        return false;
    }

    public int hashCode() {
        return (int) ((((SipHashFunction.class.hashCode() ^ this.f1071c) ^ this.d) ^ this.k0) ^ this.k1);
    }

    @Override // com.google.common.hash.HashFunction
    public Hasher newHasher() {
        return new SipHasher(this.f1071c, this.d, this.k0, this.k1);
    }

    public String toString() {
        return "Hashing.sipHash" + this.f1071c + "" + this.d + "(" + this.k0 + ", " + this.k1 + ")";
    }
}

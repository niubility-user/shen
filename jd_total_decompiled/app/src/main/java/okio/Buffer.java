package okio;

import java.io.Closeable;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.ByteChannel;
import java.nio.charset.Charset;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.annotation.Nullable;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import jd.wjlogin_sdk.util.ReplyCode;
import kotlin.text.Typography;
import org.apache.commons.codec.digest.MessageDigestAlgorithms;
import tv.danmaku.ijk.media.player.IjkMediaMeta;

/* loaded from: classes11.dex */
public final class Buffer implements BufferedSource, BufferedSink, Cloneable, ByteChannel {
    private static final byte[] DIGITS = {48, ReplyCode.reply0x31, ReplyCode.reply0x32, 51, ReplyCode.reply0x34, ReplyCode.reply0x35, ReplyCode.reply0x36, ReplyCode.reply0x37, ReplyCode.reply0x38, ReplyCode.reply0x39, 97, 98, 99, ReplyCode.reply0x64, 101, 102};
    static final int REPLACEMENT_CHARACTER = 65533;
    @Nullable
    Segment head;
    long size;

    /* loaded from: classes11.dex */
    public static final class UnsafeCursor implements Closeable {
        public Buffer buffer;
        public byte[] data;
        public boolean readWrite;
        private Segment segment;
        public long offset = -1;
        public int start = -1;
        public int end = -1;

        @Override // java.io.Closeable, java.lang.AutoCloseable
        public void close() {
            if (this.buffer != null) {
                this.buffer = null;
                this.segment = null;
                this.offset = -1L;
                this.data = null;
                this.start = -1;
                this.end = -1;
                return;
            }
            throw new IllegalStateException("not attached to a buffer");
        }

        public final long expandBuffer(int i2) {
            if (i2 <= 0) {
                throw new IllegalArgumentException("minByteCount <= 0: " + i2);
            } else if (i2 <= 8192) {
                Buffer buffer = this.buffer;
                if (buffer != null) {
                    if (this.readWrite) {
                        long j2 = buffer.size;
                        Segment writableSegment = buffer.writableSegment(i2);
                        int i3 = 8192 - writableSegment.limit;
                        writableSegment.limit = 8192;
                        long j3 = i3;
                        this.buffer.size = j2 + j3;
                        this.segment = writableSegment;
                        this.offset = j2;
                        this.data = writableSegment.data;
                        this.start = 8192 - i3;
                        this.end = 8192;
                        return j3;
                    }
                    throw new IllegalStateException("expandBuffer() only permitted for read/write buffers");
                }
                throw new IllegalStateException("not attached to a buffer");
            } else {
                throw new IllegalArgumentException("minByteCount > Segment.SIZE: " + i2);
            }
        }

        public final int next() {
            long j2 = this.offset;
            if (j2 != this.buffer.size) {
                if (j2 == -1) {
                    return seek(0L);
                }
                return seek(j2 + (this.end - this.start));
            }
            throw new IllegalStateException();
        }

        public final long resizeBuffer(long j2) {
            Buffer buffer = this.buffer;
            if (buffer != null) {
                if (this.readWrite) {
                    long j3 = buffer.size;
                    if (j2 <= j3) {
                        if (j2 >= 0) {
                            long j4 = j3 - j2;
                            while (true) {
                                if (j4 <= 0) {
                                    break;
                                }
                                Buffer buffer2 = this.buffer;
                                Segment segment = buffer2.head.prev;
                                int i2 = segment.limit;
                                long j5 = i2 - segment.pos;
                                if (j5 <= j4) {
                                    buffer2.head = segment.pop();
                                    SegmentPool.recycle(segment);
                                    j4 -= j5;
                                } else {
                                    segment.limit = (int) (i2 - j4);
                                    break;
                                }
                            }
                            this.segment = null;
                            this.offset = j2;
                            this.data = null;
                            this.start = -1;
                            this.end = -1;
                        } else {
                            throw new IllegalArgumentException("newSize < 0: " + j2);
                        }
                    } else if (j2 > j3) {
                        long j6 = j2 - j3;
                        boolean z = true;
                        while (j6 > 0) {
                            Segment writableSegment = this.buffer.writableSegment(1);
                            int min = (int) Math.min(j6, 8192 - writableSegment.limit);
                            int i3 = writableSegment.limit + min;
                            writableSegment.limit = i3;
                            j6 -= min;
                            if (z) {
                                this.segment = writableSegment;
                                this.offset = j3;
                                this.data = writableSegment.data;
                                this.start = i3 - min;
                                this.end = i3;
                                z = false;
                            }
                        }
                    }
                    this.buffer.size = j2;
                    return j3;
                }
                throw new IllegalStateException("resizeBuffer() only permitted for read/write buffers");
            }
            throw new IllegalStateException("not attached to a buffer");
        }

        public final int seek(long j2) {
            if (j2 >= -1) {
                Buffer buffer = this.buffer;
                long j3 = buffer.size;
                if (j2 <= j3) {
                    if (j2 != -1 && j2 != j3) {
                        long j4 = 0;
                        Segment segment = buffer.head;
                        Segment segment2 = this.segment;
                        if (segment2 != null) {
                            long j5 = this.offset - (this.start - segment2.pos);
                            if (j5 > j2) {
                                j3 = j5;
                                segment2 = segment;
                                segment = segment2;
                            } else {
                                j4 = j5;
                            }
                        } else {
                            segment2 = segment;
                        }
                        if (j3 - j2 > j2 - j4) {
                            while (true) {
                                int i2 = segment2.limit;
                                int i3 = segment2.pos;
                                if (j2 < (i2 - i3) + j4) {
                                    break;
                                }
                                j4 += i2 - i3;
                                segment2 = segment2.next;
                            }
                        } else {
                            while (j3 > j2) {
                                segment = segment.prev;
                                j3 -= segment.limit - segment.pos;
                            }
                            segment2 = segment;
                            j4 = j3;
                        }
                        if (this.readWrite && segment2.shared) {
                            Segment unsharedCopy = segment2.unsharedCopy();
                            Buffer buffer2 = this.buffer;
                            if (buffer2.head == segment2) {
                                buffer2.head = unsharedCopy;
                            }
                            segment2 = segment2.push(unsharedCopy);
                            segment2.prev.pop();
                        }
                        this.segment = segment2;
                        this.offset = j2;
                        this.data = segment2.data;
                        int i4 = segment2.pos + ((int) (j2 - j4));
                        this.start = i4;
                        int i5 = segment2.limit;
                        this.end = i5;
                        return i5 - i4;
                    }
                    this.segment = null;
                    this.offset = j2;
                    this.data = null;
                    this.start = -1;
                    this.end = -1;
                    return -1;
                }
            }
            throw new ArrayIndexOutOfBoundsException(String.format("offset=%s > size=%s", Long.valueOf(j2), Long.valueOf(this.buffer.size)));
        }
    }

    private ByteString digest(String str) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(str);
            Segment segment = this.head;
            if (segment != null) {
                byte[] bArr = segment.data;
                int i2 = segment.pos;
                messageDigest.update(bArr, i2, segment.limit - i2);
                Segment segment2 = this.head;
                while (true) {
                    segment2 = segment2.next;
                    if (segment2 == this.head) {
                        break;
                    }
                    byte[] bArr2 = segment2.data;
                    int i3 = segment2.pos;
                    messageDigest.update(bArr2, i3, segment2.limit - i3);
                }
            }
            return ByteString.of(messageDigest.digest());
        } catch (NoSuchAlgorithmException unused) {
            throw new AssertionError();
        }
    }

    private ByteString hmac(String str, ByteString byteString) {
        try {
            Mac mac = Mac.getInstance(str);
            mac.init(new SecretKeySpec(byteString.toByteArray(), str));
            Segment segment = this.head;
            if (segment != null) {
                byte[] bArr = segment.data;
                int i2 = segment.pos;
                mac.update(bArr, i2, segment.limit - i2);
                Segment segment2 = this.head;
                while (true) {
                    segment2 = segment2.next;
                    if (segment2 == this.head) {
                        break;
                    }
                    byte[] bArr2 = segment2.data;
                    int i3 = segment2.pos;
                    mac.update(bArr2, i3, segment2.limit - i3);
                }
            }
            return ByteString.of(mac.doFinal());
        } catch (InvalidKeyException e2) {
            throw new IllegalArgumentException(e2);
        } catch (NoSuchAlgorithmException unused) {
            throw new AssertionError();
        }
    }

    @Override // okio.BufferedSource, okio.BufferedSink
    public Buffer buffer() {
        return this;
    }

    public final void clear() {
        try {
            skip(this.size);
        } catch (EOFException e2) {
            throw new AssertionError(e2);
        }
    }

    @Override // okio.Source, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
    }

    public final long completeSegmentByteCount() {
        long j2 = this.size;
        if (j2 == 0) {
            return 0L;
        }
        Segment segment = this.head.prev;
        return (segment.limit >= 8192 || !segment.owner) ? j2 : j2 - (r3 - segment.pos);
    }

    public final Buffer copyTo(OutputStream outputStream) throws IOException {
        return copyTo(outputStream, 0L, this.size);
    }

    @Override // okio.BufferedSink
    public BufferedSink emit() {
        return this;
    }

    @Override // okio.BufferedSink
    public Buffer emitCompleteSegments() {
        return this;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Buffer) {
            Buffer buffer = (Buffer) obj;
            long j2 = this.size;
            if (j2 != buffer.size) {
                return false;
            }
            long j3 = 0;
            if (j2 == 0) {
                return true;
            }
            Segment segment = this.head;
            Segment segment2 = buffer.head;
            int i2 = segment.pos;
            int i3 = segment2.pos;
            while (j3 < this.size) {
                long min = Math.min(segment.limit - i2, segment2.limit - i3);
                int i4 = 0;
                while (i4 < min) {
                    int i5 = i2 + 1;
                    int i6 = i3 + 1;
                    if (segment.data[i2] != segment2.data[i3]) {
                        return false;
                    }
                    i4++;
                    i2 = i5;
                    i3 = i6;
                }
                if (i2 == segment.limit) {
                    segment = segment.next;
                    i2 = segment.pos;
                }
                if (i3 == segment2.limit) {
                    segment2 = segment2.next;
                    i3 = segment2.pos;
                }
                j3 += min;
            }
            return true;
        }
        return false;
    }

    @Override // okio.BufferedSource
    public boolean exhausted() {
        return this.size == 0;
    }

    @Override // okio.BufferedSink, okio.Sink, java.io.Flushable
    public void flush() {
    }

    @Override // okio.BufferedSource
    public Buffer getBuffer() {
        return this;
    }

    public final byte getByte(long j2) {
        int i2;
        Util.checkOffsetAndCount(this.size, j2, 1L);
        long j3 = this.size;
        if (j3 - j2 > j2) {
            Segment segment = this.head;
            while (true) {
                int i3 = segment.limit;
                int i4 = segment.pos;
                long j4 = i3 - i4;
                if (j2 < j4) {
                    return segment.data[i4 + ((int) j2)];
                }
                j2 -= j4;
                segment = segment.next;
            }
        } else {
            long j5 = j2 - j3;
            Segment segment2 = this.head;
            do {
                segment2 = segment2.prev;
                int i5 = segment2.limit;
                i2 = segment2.pos;
                j5 += i5 - i2;
            } while (j5 < 0);
            return segment2.data[i2 + ((int) j5)];
        }
    }

    public int hashCode() {
        Segment segment = this.head;
        if (segment == null) {
            return 0;
        }
        int i2 = 1;
        do {
            int i3 = segment.limit;
            for (int i4 = segment.pos; i4 < i3; i4++) {
                i2 = (i2 * 31) + segment.data[i4];
            }
            segment = segment.next;
        } while (segment != this.head);
        return i2;
    }

    public final ByteString hmacSha1(ByteString byteString) {
        return hmac("HmacSHA1", byteString);
    }

    public final ByteString hmacSha256(ByteString byteString) {
        return hmac("HmacSHA256", byteString);
    }

    public final ByteString hmacSha512(ByteString byteString) {
        return hmac("HmacSHA512", byteString);
    }

    @Override // okio.BufferedSource
    public long indexOf(byte b) {
        return indexOf(b, 0L, Long.MAX_VALUE);
    }

    @Override // okio.BufferedSource
    public long indexOfElement(ByteString byteString) {
        return indexOfElement(byteString, 0L);
    }

    @Override // okio.BufferedSource
    public InputStream inputStream() {
        return new InputStream() { // from class: okio.Buffer.2
            {
                Buffer.this = this;
            }

            @Override // java.io.InputStream
            public int available() {
                return (int) Math.min(Buffer.this.size, 2147483647L);
            }

            @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
            public void close() {
            }

            @Override // java.io.InputStream
            public int read() {
                Buffer buffer = Buffer.this;
                if (buffer.size > 0) {
                    return buffer.readByte() & 255;
                }
                return -1;
            }

            public String toString() {
                return Buffer.this + ".inputStream()";
            }

            @Override // java.io.InputStream
            public int read(byte[] bArr, int i2, int i3) {
                return Buffer.this.read(bArr, i2, i3);
            }
        };
    }

    @Override // java.nio.channels.Channel
    public boolean isOpen() {
        return true;
    }

    public final ByteString md5() {
        return digest(MessageDigestAlgorithms.MD5);
    }

    @Override // okio.BufferedSink
    public OutputStream outputStream() {
        return new OutputStream() { // from class: okio.Buffer.1
            {
                Buffer.this = this;
            }

            @Override // java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
            public void close() {
            }

            @Override // java.io.OutputStream, java.io.Flushable
            public void flush() {
            }

            public String toString() {
                return Buffer.this + ".outputStream()";
            }

            @Override // java.io.OutputStream
            public void write(int i2) {
                Buffer.this.writeByte((int) ((byte) i2));
            }

            @Override // java.io.OutputStream
            public void write(byte[] bArr, int i2, int i3) {
                Buffer.this.write(bArr, i2, i3);
            }
        };
    }

    @Override // okio.BufferedSource
    public BufferedSource peek() {
        return Okio.buffer(new PeekSource(this));
    }

    @Override // okio.BufferedSource
    public boolean rangeEquals(long j2, ByteString byteString) {
        return rangeEquals(j2, byteString, 0, byteString.size());
    }

    @Override // okio.BufferedSource
    public int read(byte[] bArr) {
        return read(bArr, 0, bArr.length);
    }

    @Override // okio.BufferedSource
    public long readAll(Sink sink) throws IOException {
        long j2 = this.size;
        if (j2 > 0) {
            sink.write(this, j2);
        }
        return j2;
    }

    public final UnsafeCursor readAndWriteUnsafe() {
        return readAndWriteUnsafe(new UnsafeCursor());
    }

    @Override // okio.BufferedSource
    public byte readByte() {
        long j2 = this.size;
        if (j2 != 0) {
            Segment segment = this.head;
            int i2 = segment.pos;
            int i3 = segment.limit;
            int i4 = i2 + 1;
            byte b = segment.data[i2];
            this.size = j2 - 1;
            if (i4 == i3) {
                this.head = segment.pop();
                SegmentPool.recycle(segment);
            } else {
                segment.pos = i4;
            }
            return b;
        }
        throw new IllegalStateException("size == 0");
    }

    @Override // okio.BufferedSource
    public byte[] readByteArray() {
        try {
            return readByteArray(this.size);
        } catch (EOFException e2) {
            throw new AssertionError(e2);
        }
    }

    @Override // okio.BufferedSource
    public ByteString readByteString() {
        return new ByteString(readByteArray());
    }

    /* JADX WARN: Code restructure failed: missing block: B:161:0x00bb, code lost:
        r17.size -= r7;
     */
    /* JADX WARN: Code restructure failed: missing block: B:162:0x00c1, code lost:
        if (r8 == false) goto L164;
     */
    /* JADX WARN: Code restructure failed: missing block: B:165:0x00c5, code lost:
        return -r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:179:?, code lost:
        return r3;
     */
    /* JADX WARN: Removed duplicated region for block: B:154:0x00a1  */
    /* JADX WARN: Removed duplicated region for block: B:155:0x00ab  */
    @Override // okio.BufferedSource
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public long readDecimalLong() {
        byte b;
        long j2 = 0;
        if (this.size != 0) {
            long j3 = -922337203685477580L;
            long j4 = -7;
            int i2 = 0;
            boolean z = false;
            boolean z2 = false;
            loop0: while (true) {
                Segment segment = this.head;
                byte[] bArr = segment.data;
                int i3 = segment.pos;
                int i4 = segment.limit;
                while (i3 < i4) {
                    b = bArr[i3];
                    if (b >= 48 && b <= 57) {
                        int i5 = 48 - b;
                        if (j2 < j3 || (j2 == j3 && i5 < j4)) {
                            break loop0;
                        }
                        j2 = (j2 * 10) + i5;
                    } else if (b == 45 && i2 == 0) {
                        j4--;
                        z = true;
                    } else if (i2 == 0) {
                        throw new NumberFormatException("Expected leading [0-9] or '-' character but was 0x" + Integer.toHexString(b));
                    } else {
                        z2 = true;
                        if (i3 != i4) {
                            this.head = segment.pop();
                            SegmentPool.recycle(segment);
                        } else {
                            segment.pos = i3;
                        }
                        if (!!z2 || this.head == null) {
                            break;
                        }
                        j3 = -922337203685477580L;
                    }
                    i3++;
                    i2++;
                    j3 = -922337203685477580L;
                }
                if (i3 != i4) {
                }
                if (!z2) {
                    break;
                }
                break;
            }
            Buffer writeByte = new Buffer().writeDecimalLong(j2).writeByte((int) b);
            if (!z) {
                writeByte.readByte();
            }
            throw new NumberFormatException("Number too large: " + writeByte.readUtf8());
        }
        throw new IllegalStateException("size == 0");
    }

    public final Buffer readFrom(InputStream inputStream) throws IOException {
        readFrom(inputStream, Long.MAX_VALUE, true);
        return this;
    }

    @Override // okio.BufferedSource
    public void readFully(Buffer buffer, long j2) throws EOFException {
        long j3 = this.size;
        if (j3 >= j2) {
            buffer.write(this, j2);
        } else {
            buffer.write(this, j3);
            throw new EOFException();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:135:0x0093  */
    /* JADX WARN: Removed duplicated region for block: B:136:0x009d  */
    /* JADX WARN: Removed duplicated region for block: B:138:0x00a1  */
    /* JADX WARN: Removed duplicated region for block: B:145:0x00a5 A[EDGE_INSN: B:145:0x00a5->B:140:0x00a5 BREAK  A[LOOP:0: B:107:0x000b->B:149:?], SYNTHETIC] */
    @Override // okio.BufferedSource
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public long readHexadecimalUnsignedLong() {
        int i2;
        int i3;
        if (this.size != 0) {
            int i4 = 0;
            long j2 = 0;
            boolean z = false;
            do {
                Segment segment = this.head;
                byte[] bArr = segment.data;
                int i5 = segment.pos;
                int i6 = segment.limit;
                while (i5 < i6) {
                    byte b = bArr[i5];
                    if (b < 48 || b > 57) {
                        if (b >= 97 && b <= 102) {
                            i2 = b - 97;
                        } else if (b >= 65 && b <= 70) {
                            i2 = b - 65;
                        } else if (i4 == 0) {
                            throw new NumberFormatException("Expected leading [0-9a-fA-F] character but was 0x" + Integer.toHexString(b));
                        } else {
                            z = true;
                            if (i5 != i6) {
                                this.head = segment.pop();
                                SegmentPool.recycle(segment);
                            } else {
                                segment.pos = i5;
                            }
                            if (!z) {
                                break;
                            }
                        }
                        i3 = i2 + 10;
                    } else {
                        i3 = b + ReplyCode.reply0xd0;
                    }
                    if (((-1152921504606846976L) & j2) != 0) {
                        throw new NumberFormatException("Number too large: " + new Buffer().writeHexadecimalUnsignedLong(j2).writeByte((int) b).readUtf8());
                    }
                    j2 = (j2 << 4) | i3;
                    i5++;
                    i4++;
                }
                if (i5 != i6) {
                }
                if (!z) {
                }
            } while (this.head != null);
            this.size -= i4;
            return j2;
        }
        throw new IllegalStateException("size == 0");
    }

    @Override // okio.BufferedSource
    public int readInt() {
        long j2 = this.size;
        if (j2 >= 4) {
            Segment segment = this.head;
            int i2 = segment.pos;
            int i3 = segment.limit;
            if (i3 - i2 < 4) {
                return ((readByte() & 255) << 24) | ((readByte() & 255) << 16) | ((readByte() & 255) << 8) | (readByte() & 255);
            }
            byte[] bArr = segment.data;
            int i4 = i2 + 1;
            int i5 = i4 + 1;
            int i6 = ((bArr[i2] & 255) << 24) | ((bArr[i4] & 255) << 16);
            int i7 = i5 + 1;
            int i8 = i6 | ((bArr[i5] & 255) << 8);
            int i9 = i7 + 1;
            int i10 = i8 | (bArr[i7] & 255);
            this.size = j2 - 4;
            if (i9 == i3) {
                this.head = segment.pop();
                SegmentPool.recycle(segment);
            } else {
                segment.pos = i9;
            }
            return i10;
        }
        throw new IllegalStateException("size < 4: " + this.size);
    }

    @Override // okio.BufferedSource
    public int readIntLe() {
        return Util.reverseBytesInt(readInt());
    }

    @Override // okio.BufferedSource
    public long readLong() {
        long j2 = this.size;
        if (j2 >= 8) {
            Segment segment = this.head;
            int i2 = segment.pos;
            int i3 = segment.limit;
            if (i3 - i2 < 8) {
                return ((readInt() & 4294967295L) << 32) | (4294967295L & readInt());
            }
            byte[] bArr = segment.data;
            long j3 = (bArr[r11] & 255) << 48;
            int i4 = i2 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1;
            long j4 = j3 | ((bArr[i2] & 255) << 56) | ((bArr[r6] & 255) << 40) | ((bArr[r11] & 255) << 32) | ((bArr[r6] & 255) << 24) | ((bArr[r9] & 255) << 16) | ((bArr[r6] & 255) << 8) | (bArr[r9] & 255);
            this.size = j2 - 8;
            if (i4 == i3) {
                this.head = segment.pop();
                SegmentPool.recycle(segment);
            } else {
                segment.pos = i4;
            }
            return j4;
        }
        throw new IllegalStateException("size < 8: " + this.size);
    }

    @Override // okio.BufferedSource
    public long readLongLe() {
        return Util.reverseBytesLong(readLong());
    }

    @Override // okio.BufferedSource
    public short readShort() {
        long j2 = this.size;
        if (j2 >= 2) {
            Segment segment = this.head;
            int i2 = segment.pos;
            int i3 = segment.limit;
            if (i3 - i2 < 2) {
                return (short) (((readByte() & 255) << 8) | (readByte() & 255));
            }
            byte[] bArr = segment.data;
            int i4 = i2 + 1;
            int i5 = i4 + 1;
            int i6 = ((bArr[i2] & 255) << 8) | (bArr[i4] & 255);
            this.size = j2 - 2;
            if (i5 == i3) {
                this.head = segment.pop();
                SegmentPool.recycle(segment);
            } else {
                segment.pos = i5;
            }
            return (short) i6;
        }
        throw new IllegalStateException("size < 2: " + this.size);
    }

    @Override // okio.BufferedSource
    public short readShortLe() {
        return Util.reverseBytesShort(readShort());
    }

    @Override // okio.BufferedSource
    public String readString(Charset charset) {
        try {
            return readString(this.size, charset);
        } catch (EOFException e2) {
            throw new AssertionError(e2);
        }
    }

    public final UnsafeCursor readUnsafe() {
        return readUnsafe(new UnsafeCursor());
    }

    @Override // okio.BufferedSource
    public String readUtf8() {
        try {
            return readString(this.size, Util.UTF_8);
        } catch (EOFException e2) {
            throw new AssertionError(e2);
        }
    }

    @Override // okio.BufferedSource
    public int readUtf8CodePoint() throws EOFException {
        int i2;
        int i3;
        int i4;
        if (this.size != 0) {
            byte b = getByte(0L);
            if ((b & 128) == 0) {
                i2 = b & Byte.MAX_VALUE;
                i3 = 1;
                i4 = 0;
            } else if ((b & 224) == 192) {
                i2 = b & 31;
                i3 = 2;
                i4 = 128;
            } else if ((b & 240) == 224) {
                i2 = b & 15;
                i3 = 3;
                i4 = 2048;
            } else if ((b & 248) != 240) {
                skip(1L);
                return REPLACEMENT_CHARACTER;
            } else {
                i2 = b & 7;
                i3 = 4;
                i4 = 65536;
            }
            long j2 = i3;
            if (this.size >= j2) {
                for (int i5 = 1; i5 < i3; i5++) {
                    long j3 = i5;
                    byte b2 = getByte(j3);
                    if ((b2 & 192) != 128) {
                        skip(j3);
                        return REPLACEMENT_CHARACTER;
                    }
                    i2 = (i2 << 6) | (b2 & 63);
                }
                skip(j2);
                return i2 > 1114111 ? REPLACEMENT_CHARACTER : ((i2 < 55296 || i2 > 57343) && i2 >= i4) ? i2 : REPLACEMENT_CHARACTER;
            }
            throw new EOFException("size < " + i3 + ": " + this.size + " (to read code point prefixed 0x" + Integer.toHexString(b) + ")");
        }
        throw new EOFException();
    }

    @Override // okio.BufferedSource
    @Nullable
    public String readUtf8Line() throws EOFException {
        long indexOf = indexOf((byte) 10);
        if (indexOf == -1) {
            long j2 = this.size;
            if (j2 != 0) {
                return readUtf8(j2);
            }
            return null;
        }
        return readUtf8Line(indexOf);
    }

    @Override // okio.BufferedSource
    public String readUtf8LineStrict() throws EOFException {
        return readUtf8LineStrict(Long.MAX_VALUE);
    }

    @Override // okio.BufferedSource
    public boolean request(long j2) {
        return this.size >= j2;
    }

    @Override // okio.BufferedSource
    public void require(long j2) throws EOFException {
        if (this.size < j2) {
            throw new EOFException();
        }
    }

    List<Integer> segmentSizes() {
        if (this.head == null) {
            return Collections.emptyList();
        }
        ArrayList arrayList = new ArrayList();
        Segment segment = this.head;
        arrayList.add(Integer.valueOf(segment.limit - segment.pos));
        Segment segment2 = this.head;
        while (true) {
            segment2 = segment2.next;
            if (segment2 == this.head) {
                return arrayList;
            }
            arrayList.add(Integer.valueOf(segment2.limit - segment2.pos));
        }
    }

    @Override // okio.BufferedSource
    public int select(Options options) {
        int selectPrefix = selectPrefix(options, false);
        if (selectPrefix == -1) {
            return -1;
        }
        try {
            skip(options.byteStrings[selectPrefix].size());
            return selectPrefix;
        } catch (EOFException unused) {
            throw new AssertionError();
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:143:0x0055, code lost:
        if (r19 == false) goto L145;
     */
    /* JADX WARN: Code restructure failed: missing block: B:144:0x0057, code lost:
        return r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:145:0x0058, code lost:
        return r11;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public int selectPrefix(Options options, boolean z) {
        int i2;
        int i3;
        int i4;
        int i5;
        Segment segment;
        Segment segment2 = this.head;
        int i6 = -2;
        if (segment2 != null) {
            byte[] bArr = segment2.data;
            int i7 = segment2.pos;
            int i8 = segment2.limit;
            int[] iArr = options.trie;
            Segment segment3 = segment2;
            int i9 = 0;
            int i10 = -1;
            loop0: while (true) {
                int i11 = i9 + 1;
                int i12 = iArr[i9];
                int i13 = i11 + 1;
                int i14 = iArr[i11];
                if (i14 != -1) {
                    i10 = i14;
                }
                if (segment3 == null) {
                    break;
                }
                if (i12 >= 0) {
                    int i15 = i7 + 1;
                    int i16 = bArr[i7] & 255;
                    int i17 = i13 + i12;
                    while (i13 != i17) {
                        if (i16 == iArr[i13]) {
                            i2 = iArr[i13 + i12];
                            if (i15 == i8) {
                                segment3 = segment3.next;
                                i3 = segment3.pos;
                                bArr = segment3.data;
                                i8 = segment3.limit;
                                if (segment3 == segment2) {
                                    segment3 = null;
                                }
                            } else {
                                i3 = i15;
                            }
                        } else {
                            i13++;
                        }
                    }
                    return i10;
                }
                int i18 = i13 + (i12 * (-1));
                while (true) {
                    int i19 = i7 + 1;
                    int i20 = i13 + 1;
                    if ((bArr[i7] & 255) != iArr[i13]) {
                        return i10;
                    }
                    boolean z2 = i20 == i18;
                    if (i19 == i8) {
                        Segment segment4 = segment3.next;
                        i5 = segment4.pos;
                        byte[] bArr2 = segment4.data;
                        i4 = segment4.limit;
                        if (segment4 != segment2) {
                            segment = segment4;
                            bArr = bArr2;
                        } else if (!z2) {
                            break loop0;
                        } else {
                            bArr = bArr2;
                            segment = null;
                        }
                    } else {
                        Segment segment5 = segment3;
                        i4 = i8;
                        i5 = i19;
                        segment = segment5;
                    }
                    if (z2) {
                        i2 = iArr[i20];
                        i3 = i5;
                        i8 = i4;
                        segment3 = segment;
                        break;
                    }
                    i7 = i5;
                    i8 = i4;
                    i13 = i20;
                    segment3 = segment;
                }
                if (i2 >= 0) {
                    return i2;
                }
                i9 = -i2;
                i7 = i3;
                i6 = -2;
            }
        } else if (z) {
            return -2;
        } else {
            return options.indexOf(ByteString.EMPTY);
        }
    }

    public final ByteString sha1() {
        return digest(MessageDigestAlgorithms.SHA_1);
    }

    public final ByteString sha256() {
        return digest(MessageDigestAlgorithms.SHA_256);
    }

    public final ByteString sha512() {
        return digest(MessageDigestAlgorithms.SHA_512);
    }

    public final long size() {
        return this.size;
    }

    @Override // okio.BufferedSource
    public void skip(long j2) throws EOFException {
        while (j2 > 0) {
            if (this.head != null) {
                int min = (int) Math.min(j2, r0.limit - r0.pos);
                long j3 = min;
                this.size -= j3;
                j2 -= j3;
                Segment segment = this.head;
                int i2 = segment.pos + min;
                segment.pos = i2;
                if (i2 == segment.limit) {
                    this.head = segment.pop();
                    SegmentPool.recycle(segment);
                }
            } else {
                throw new EOFException();
            }
        }
    }

    public final ByteString snapshot() {
        long j2 = this.size;
        if (j2 <= 2147483647L) {
            return snapshot((int) j2);
        }
        throw new IllegalArgumentException("size > Integer.MAX_VALUE: " + this.size);
    }

    @Override // okio.Source
    public Timeout timeout() {
        return Timeout.NONE;
    }

    public String toString() {
        return snapshot().toString();
    }

    public Segment writableSegment(int i2) {
        if (i2 >= 1 && i2 <= 8192) {
            Segment segment = this.head;
            if (segment == null) {
                Segment take = SegmentPool.take();
                this.head = take;
                take.prev = take;
                take.next = take;
                return take;
            }
            Segment segment2 = segment.prev;
            return (segment2.limit + i2 > 8192 || !segment2.owner) ? segment2.push(SegmentPool.take()) : segment2;
        }
        throw new IllegalArgumentException();
    }

    @Override // okio.BufferedSink
    public long writeAll(Source source) throws IOException {
        if (source == null) {
            throw new IllegalArgumentException("source == null");
        }
        long j2 = 0;
        while (true) {
            long read = source.read(this, IjkMediaMeta.AV_CH_TOP_FRONT_CENTER);
            if (read == -1) {
                return j2;
            }
            j2 += read;
        }
    }

    public final Buffer writeTo(OutputStream outputStream) throws IOException {
        return writeTo(outputStream, this.size);
    }

    public Buffer clone() {
        Buffer buffer = new Buffer();
        if (this.size == 0) {
            return buffer;
        }
        Segment sharedCopy = this.head.sharedCopy();
        buffer.head = sharedCopy;
        sharedCopy.prev = sharedCopy;
        sharedCopy.next = sharedCopy;
        Segment segment = this.head;
        while (true) {
            segment = segment.next;
            if (segment != this.head) {
                buffer.head.prev.push(segment.sharedCopy());
            } else {
                buffer.size = this.size;
                return buffer;
            }
        }
    }

    public final Buffer copyTo(OutputStream outputStream, long j2, long j3) throws IOException {
        if (outputStream != null) {
            Util.checkOffsetAndCount(this.size, j2, j3);
            if (j3 == 0) {
                return this;
            }
            Segment segment = this.head;
            while (true) {
                int i2 = segment.limit;
                int i3 = segment.pos;
                if (j2 < i2 - i3) {
                    break;
                }
                j2 -= i2 - i3;
                segment = segment.next;
            }
            while (j3 > 0) {
                int min = (int) Math.min(segment.limit - r10, j3);
                outputStream.write(segment.data, (int) (segment.pos + j2), min);
                j3 -= min;
                segment = segment.next;
                j2 = 0;
            }
            return this;
        }
        throw new IllegalArgumentException("out == null");
    }

    @Override // okio.BufferedSource
    public long indexOf(byte b, long j2) {
        return indexOf(b, j2, Long.MAX_VALUE);
    }

    @Override // okio.BufferedSource
    public long indexOfElement(ByteString byteString, long j2) {
        int i2;
        int i3;
        long j3 = 0;
        if (j2 >= 0) {
            Segment segment = this.head;
            if (segment == null) {
                return -1L;
            }
            long j4 = this.size;
            if (j4 - j2 < j2) {
                while (j4 > j2) {
                    segment = segment.prev;
                    j4 -= segment.limit - segment.pos;
                }
            } else {
                while (true) {
                    long j5 = (segment.limit - segment.pos) + j3;
                    if (j5 >= j2) {
                        break;
                    }
                    segment = segment.next;
                    j3 = j5;
                }
                j4 = j3;
            }
            if (byteString.size() == 2) {
                byte b = byteString.getByte(0);
                byte b2 = byteString.getByte(1);
                while (j4 < this.size) {
                    byte[] bArr = segment.data;
                    i2 = (int) ((segment.pos + j2) - j4);
                    int i4 = segment.limit;
                    while (i2 < i4) {
                        byte b3 = bArr[i2];
                        if (b3 == b || b3 == b2) {
                            i3 = segment.pos;
                            return (i2 - i3) + j4;
                        }
                        i2++;
                    }
                    j4 += segment.limit - segment.pos;
                    segment = segment.next;
                    j2 = j4;
                }
                return -1L;
            }
            byte[] internalArray = byteString.internalArray();
            while (j4 < this.size) {
                byte[] bArr2 = segment.data;
                i2 = (int) ((segment.pos + j2) - j4);
                int i5 = segment.limit;
                while (i2 < i5) {
                    byte b4 = bArr2[i2];
                    for (byte b5 : internalArray) {
                        if (b4 == b5) {
                            i3 = segment.pos;
                            return (i2 - i3) + j4;
                        }
                    }
                    i2++;
                }
                j4 += segment.limit - segment.pos;
                segment = segment.next;
                j2 = j4;
            }
            return -1L;
        }
        throw new IllegalArgumentException("fromIndex < 0");
    }

    @Override // okio.BufferedSource
    public boolean rangeEquals(long j2, ByteString byteString, int i2, int i3) {
        if (j2 < 0 || i2 < 0 || i3 < 0 || this.size - j2 < i3 || byteString.size() - i2 < i3) {
            return false;
        }
        for (int i4 = 0; i4 < i3; i4++) {
            if (getByte(i4 + j2) != byteString.getByte(i2 + i4)) {
                return false;
            }
        }
        return true;
    }

    @Override // okio.BufferedSource
    public int read(byte[] bArr, int i2, int i3) {
        Util.checkOffsetAndCount(bArr.length, i2, i3);
        Segment segment = this.head;
        if (segment == null) {
            return -1;
        }
        int min = Math.min(i3, segment.limit - segment.pos);
        System.arraycopy(segment.data, segment.pos, bArr, i2, min);
        int i4 = segment.pos + min;
        segment.pos = i4;
        this.size -= min;
        if (i4 == segment.limit) {
            this.head = segment.pop();
            SegmentPool.recycle(segment);
        }
        return min;
    }

    public final UnsafeCursor readAndWriteUnsafe(UnsafeCursor unsafeCursor) {
        if (unsafeCursor.buffer == null) {
            unsafeCursor.buffer = this;
            unsafeCursor.readWrite = true;
            return unsafeCursor;
        }
        throw new IllegalStateException("already attached to a buffer");
    }

    @Override // okio.BufferedSource
    public ByteString readByteString(long j2) throws EOFException {
        return new ByteString(readByteArray(j2));
    }

    public final Buffer readFrom(InputStream inputStream, long j2) throws IOException {
        if (j2 >= 0) {
            readFrom(inputStream, j2, false);
            return this;
        }
        throw new IllegalArgumentException("byteCount < 0: " + j2);
    }

    public final UnsafeCursor readUnsafe(UnsafeCursor unsafeCursor) {
        if (unsafeCursor.buffer == null) {
            unsafeCursor.buffer = this;
            unsafeCursor.readWrite = false;
            return unsafeCursor;
        }
        throw new IllegalStateException("already attached to a buffer");
    }

    @Override // okio.BufferedSource
    public String readUtf8LineStrict(long j2) throws EOFException {
        if (j2 >= 0) {
            long j3 = j2 != Long.MAX_VALUE ? j2 + 1 : Long.MAX_VALUE;
            long indexOf = indexOf((byte) 10, 0L, j3);
            if (indexOf != -1) {
                return readUtf8Line(indexOf);
            }
            if (j3 < size() && getByte(j3 - 1) == 13 && getByte(j3) == 10) {
                return readUtf8Line(j3);
            }
            Buffer buffer = new Buffer();
            copyTo(buffer, 0L, Math.min(32L, size()));
            throw new EOFException("\\n not found: limit=" + Math.min(size(), j2) + " content=" + buffer.readByteString().hex() + Typography.ellipsis);
        }
        throw new IllegalArgumentException("limit < 0: " + j2);
    }

    @Override // okio.BufferedSink
    public Buffer writeByte(int i2) {
        Segment writableSegment = writableSegment(1);
        byte[] bArr = writableSegment.data;
        int i3 = writableSegment.limit;
        writableSegment.limit = i3 + 1;
        bArr[i3] = (byte) i2;
        this.size++;
        return this;
    }

    @Override // okio.BufferedSink
    public Buffer writeDecimalLong(long j2) {
        if (j2 == 0) {
            return writeByte(48);
        }
        boolean z = false;
        int i2 = 1;
        if (j2 < 0) {
            j2 = -j2;
            if (j2 < 0) {
                return writeUtf8("-9223372036854775808");
            }
            z = true;
        }
        if (j2 >= 100000000) {
            i2 = j2 < 1000000000000L ? j2 < 10000000000L ? j2 < 1000000000 ? 9 : 10 : j2 < 100000000000L ? 11 : 12 : j2 < 1000000000000000L ? j2 < 10000000000000L ? 13 : j2 < 100000000000000L ? 14 : 15 : j2 < 100000000000000000L ? j2 < 10000000000000000L ? 16 : 17 : j2 < 1000000000000000000L ? 18 : 19;
        } else if (j2 >= 10000) {
            i2 = j2 < 1000000 ? j2 < 100000 ? 5 : 6 : j2 < 10000000 ? 7 : 8;
        } else if (j2 >= 100) {
            i2 = j2 < 1000 ? 3 : 4;
        } else if (j2 >= 10) {
            i2 = 2;
        }
        if (z) {
            i2++;
        }
        Segment writableSegment = writableSegment(i2);
        byte[] bArr = writableSegment.data;
        int i3 = writableSegment.limit + i2;
        while (j2 != 0) {
            i3--;
            bArr[i3] = DIGITS[(int) (j2 % 10)];
            j2 /= 10;
        }
        if (z) {
            bArr[i3 - 1] = 45;
        }
        writableSegment.limit += i2;
        this.size += i2;
        return this;
    }

    @Override // okio.BufferedSink
    public Buffer writeHexadecimalUnsignedLong(long j2) {
        if (j2 == 0) {
            return writeByte(48);
        }
        int numberOfTrailingZeros = (Long.numberOfTrailingZeros(Long.highestOneBit(j2)) / 4) + 1;
        Segment writableSegment = writableSegment(numberOfTrailingZeros);
        byte[] bArr = writableSegment.data;
        int i2 = writableSegment.limit;
        for (int i3 = (i2 + numberOfTrailingZeros) - 1; i3 >= i2; i3--) {
            bArr[i3] = DIGITS[(int) (15 & j2)];
            j2 >>>= 4;
        }
        writableSegment.limit += numberOfTrailingZeros;
        this.size += numberOfTrailingZeros;
        return this;
    }

    @Override // okio.BufferedSink
    public Buffer writeInt(int i2) {
        Segment writableSegment = writableSegment(4);
        byte[] bArr = writableSegment.data;
        int i3 = writableSegment.limit;
        int i4 = i3 + 1;
        bArr[i3] = (byte) ((i2 >>> 24) & 255);
        int i5 = i4 + 1;
        bArr[i4] = (byte) ((i2 >>> 16) & 255);
        int i6 = i5 + 1;
        bArr[i5] = (byte) ((i2 >>> 8) & 255);
        bArr[i6] = (byte) (i2 & 255);
        writableSegment.limit = i6 + 1;
        this.size += 4;
        return this;
    }

    @Override // okio.BufferedSink
    public Buffer writeIntLe(int i2) {
        return writeInt(Util.reverseBytesInt(i2));
    }

    @Override // okio.BufferedSink
    public Buffer writeLong(long j2) {
        Segment writableSegment = writableSegment(8);
        byte[] bArr = writableSegment.data;
        int i2 = writableSegment.limit;
        int i3 = i2 + 1;
        bArr[i2] = (byte) ((j2 >>> 56) & 255);
        int i4 = i3 + 1;
        bArr[i3] = (byte) ((j2 >>> 48) & 255);
        int i5 = i4 + 1;
        bArr[i4] = (byte) ((j2 >>> 40) & 255);
        int i6 = i5 + 1;
        bArr[i5] = (byte) ((j2 >>> 32) & 255);
        int i7 = i6 + 1;
        bArr[i6] = (byte) ((j2 >>> 24) & 255);
        int i8 = i7 + 1;
        bArr[i7] = (byte) ((j2 >>> 16) & 255);
        int i9 = i8 + 1;
        bArr[i8] = (byte) ((j2 >>> 8) & 255);
        bArr[i9] = (byte) (j2 & 255);
        writableSegment.limit = i9 + 1;
        this.size += 8;
        return this;
    }

    @Override // okio.BufferedSink
    public Buffer writeLongLe(long j2) {
        return writeLong(Util.reverseBytesLong(j2));
    }

    @Override // okio.BufferedSink
    public Buffer writeShort(int i2) {
        Segment writableSegment = writableSegment(2);
        byte[] bArr = writableSegment.data;
        int i3 = writableSegment.limit;
        int i4 = i3 + 1;
        bArr[i3] = (byte) ((i2 >>> 8) & 255);
        bArr[i4] = (byte) (i2 & 255);
        writableSegment.limit = i4 + 1;
        this.size += 2;
        return this;
    }

    @Override // okio.BufferedSink
    public Buffer writeShortLe(int i2) {
        return writeShort((int) Util.reverseBytesShort((short) i2));
    }

    public final Buffer writeTo(OutputStream outputStream, long j2) throws IOException {
        if (outputStream != null) {
            Util.checkOffsetAndCount(this.size, 0L, j2);
            Segment segment = this.head;
            while (j2 > 0) {
                int min = (int) Math.min(j2, segment.limit - segment.pos);
                outputStream.write(segment.data, segment.pos, min);
                int i2 = segment.pos + min;
                segment.pos = i2;
                long j3 = min;
                this.size -= j3;
                j2 -= j3;
                if (i2 == segment.limit) {
                    Segment pop = segment.pop();
                    this.head = pop;
                    SegmentPool.recycle(segment);
                    segment = pop;
                }
            }
            return this;
        }
        throw new IllegalArgumentException("out == null");
    }

    @Override // okio.BufferedSink
    public Buffer writeUtf8CodePoint(int i2) {
        if (i2 < 128) {
            writeByte(i2);
        } else if (i2 < 2048) {
            writeByte((i2 >> 6) | 192);
            writeByte((i2 & 63) | 128);
        } else if (i2 < 65536) {
            if (i2 >= 55296 && i2 <= 57343) {
                writeByte(63);
            } else {
                writeByte((i2 >> 12) | 224);
                writeByte(((i2 >> 6) & 63) | 128);
                writeByte((i2 & 63) | 128);
            }
        } else if (i2 <= 1114111) {
            writeByte((i2 >> 18) | 240);
            writeByte(((i2 >> 12) & 63) | 128);
            writeByte(((i2 >> 6) & 63) | 128);
            writeByte((i2 & 63) | 128);
        } else {
            throw new IllegalArgumentException("Unexpected code point: " + Integer.toHexString(i2));
        }
        return this;
    }

    @Override // okio.BufferedSource
    public long indexOf(byte b, long j2, long j3) {
        Segment segment;
        long j4 = 0;
        if (j2 >= 0 && j3 >= j2) {
            long j5 = this.size;
            long j6 = j3 > j5 ? j5 : j3;
            if (j2 == j6 || (segment = this.head) == null) {
                return -1L;
            }
            if (j5 - j2 < j2) {
                while (j5 > j2) {
                    segment = segment.prev;
                    j5 -= segment.limit - segment.pos;
                }
            } else {
                while (true) {
                    long j7 = (segment.limit - segment.pos) + j4;
                    if (j7 >= j2) {
                        break;
                    }
                    segment = segment.next;
                    j4 = j7;
                }
                j5 = j4;
            }
            long j8 = j2;
            while (j5 < j6) {
                byte[] bArr = segment.data;
                int min = (int) Math.min(segment.limit, (segment.pos + j6) - j5);
                for (int i2 = (int) ((segment.pos + j8) - j5); i2 < min; i2++) {
                    if (bArr[i2] == b) {
                        return (i2 - segment.pos) + j5;
                    }
                }
                j5 += segment.limit - segment.pos;
                segment = segment.next;
                j8 = j5;
            }
            return -1L;
        }
        throw new IllegalArgumentException(String.format("size=%s fromIndex=%s toIndex=%s", Long.valueOf(this.size), Long.valueOf(j2), Long.valueOf(j3)));
    }

    @Override // okio.BufferedSource
    public byte[] readByteArray(long j2) throws EOFException {
        Util.checkOffsetAndCount(this.size, 0L, j2);
        if (j2 <= 2147483647L) {
            byte[] bArr = new byte[(int) j2];
            readFully(bArr);
            return bArr;
        }
        throw new IllegalArgumentException("byteCount > Integer.MAX_VALUE: " + j2);
    }

    @Override // okio.BufferedSource
    public String readString(long j2, Charset charset) throws EOFException {
        Util.checkOffsetAndCount(this.size, 0L, j2);
        if (charset != null) {
            if (j2 > 2147483647L) {
                throw new IllegalArgumentException("byteCount > Integer.MAX_VALUE: " + j2);
            } else if (j2 == 0) {
                return "";
            } else {
                Segment segment = this.head;
                if (segment.pos + j2 > segment.limit) {
                    return new String(readByteArray(j2), charset);
                }
                String str = new String(segment.data, segment.pos, (int) j2, charset);
                int i2 = (int) (segment.pos + j2);
                segment.pos = i2;
                this.size -= j2;
                if (i2 == segment.limit) {
                    this.head = segment.pop();
                    SegmentPool.recycle(segment);
                }
                return str;
            }
        }
        throw new IllegalArgumentException("charset == null");
    }

    @Override // okio.BufferedSource
    public String readUtf8(long j2) throws EOFException {
        return readString(j2, Util.UTF_8);
    }

    @Override // okio.BufferedSink
    public Buffer writeString(String str, Charset charset) {
        return writeString(str, 0, str.length(), charset);
    }

    @Override // okio.BufferedSink
    public Buffer writeUtf8(String str) {
        return writeUtf8(str, 0, str.length());
    }

    private void readFrom(InputStream inputStream, long j2, boolean z) throws IOException {
        if (inputStream == null) {
            throw new IllegalArgumentException("in == null");
        }
        while (true) {
            if (j2 <= 0 && !z) {
                return;
            }
            Segment writableSegment = writableSegment(1);
            int read = inputStream.read(writableSegment.data, writableSegment.limit, (int) Math.min(j2, 8192 - writableSegment.limit));
            if (read == -1) {
                if (!z) {
                    throw new EOFException();
                }
                return;
            }
            writableSegment.limit += read;
            long j3 = read;
            this.size += j3;
            j2 -= j3;
        }
    }

    public String readUtf8Line(long j2) throws EOFException {
        if (j2 > 0) {
            long j3 = j2 - 1;
            if (getByte(j3) == 13) {
                String readUtf8 = readUtf8(j3);
                skip(2L);
                return readUtf8;
            }
        }
        String readUtf82 = readUtf8(j2);
        skip(1L);
        return readUtf82;
    }

    public final ByteString snapshot(int i2) {
        if (i2 == 0) {
            return ByteString.EMPTY;
        }
        return new SegmentedByteString(this, i2);
    }

    @Override // okio.BufferedSink
    public Buffer write(ByteString byteString) {
        if (byteString != null) {
            byteString.write(this);
            return this;
        }
        throw new IllegalArgumentException("byteString == null");
    }

    @Override // okio.BufferedSink
    public Buffer writeString(String str, int i2, int i3, Charset charset) {
        if (str != null) {
            if (i2 < 0) {
                throw new IllegalAccessError("beginIndex < 0: " + i2);
            } else if (i3 >= i2) {
                if (i3 <= str.length()) {
                    if (charset != null) {
                        if (charset.equals(Util.UTF_8)) {
                            return writeUtf8(str, i2, i3);
                        }
                        byte[] bytes = str.substring(i2, i3).getBytes(charset);
                        return write(bytes, 0, bytes.length);
                    }
                    throw new IllegalArgumentException("charset == null");
                }
                throw new IllegalArgumentException("endIndex > string.length: " + i3 + " > " + str.length());
            } else {
                throw new IllegalArgumentException("endIndex < beginIndex: " + i3 + " < " + i2);
            }
        }
        throw new IllegalArgumentException("string == null");
    }

    @Override // okio.BufferedSink
    public Buffer writeUtf8(String str, int i2, int i3) {
        if (str != null) {
            if (i2 < 0) {
                throw new IllegalArgumentException("beginIndex < 0: " + i2);
            } else if (i3 >= i2) {
                if (i3 > str.length()) {
                    throw new IllegalArgumentException("endIndex > string.length: " + i3 + " > " + str.length());
                }
                while (i2 < i3) {
                    char charAt = str.charAt(i2);
                    if (charAt < '\u0080') {
                        Segment writableSegment = writableSegment(1);
                        byte[] bArr = writableSegment.data;
                        int i4 = writableSegment.limit - i2;
                        int min = Math.min(i3, 8192 - i4);
                        int i5 = i2 + 1;
                        bArr[i2 + i4] = (byte) charAt;
                        while (i5 < min) {
                            char charAt2 = str.charAt(i5);
                            if (charAt2 >= '\u0080') {
                                break;
                            }
                            bArr[i5 + i4] = (byte) charAt2;
                            i5++;
                        }
                        int i6 = writableSegment.limit;
                        int i7 = (i4 + i5) - i6;
                        writableSegment.limit = i6 + i7;
                        this.size += i7;
                        i2 = i5;
                    } else {
                        if (charAt < '\u0800') {
                            writeByte((charAt >> 6) | 192);
                            writeByte((charAt & '?') | 128);
                        } else if (charAt >= '\ud800' && charAt <= '\udfff') {
                            int i8 = i2 + 1;
                            char charAt3 = i8 < i3 ? str.charAt(i8) : (char) 0;
                            if (charAt <= '\udbff' && charAt3 >= '\udc00' && charAt3 <= '\udfff') {
                                int i9 = (((charAt & '\u27ff') << 10) | ('\u23ff' & charAt3)) + 65536;
                                writeByte((i9 >> 18) | 240);
                                writeByte(((i9 >> 12) & 63) | 128);
                                writeByte(((i9 >> 6) & 63) | 128);
                                writeByte((i9 & 63) | 128);
                                i2 += 2;
                            } else {
                                writeByte(63);
                                i2 = i8;
                            }
                        } else {
                            writeByte((charAt >> '\f') | 224);
                            writeByte(((charAt >> 6) & 63) | 128);
                            writeByte((charAt & '?') | 128);
                        }
                        i2++;
                    }
                }
                return this;
            } else {
                throw new IllegalArgumentException("endIndex < beginIndex: " + i3 + " < " + i2);
            }
        }
        throw new IllegalArgumentException("string == null");
    }

    private boolean rangeEquals(Segment segment, int i2, ByteString byteString, int i3, int i4) {
        int i5 = segment.limit;
        byte[] bArr = segment.data;
        while (i3 < i4) {
            if (i2 == i5) {
                segment = segment.next;
                byte[] bArr2 = segment.data;
                bArr = bArr2;
                i2 = segment.pos;
                i5 = segment.limit;
            }
            if (bArr[i2] != byteString.getByte(i3)) {
                return false;
            }
            i2++;
            i3++;
        }
        return true;
    }

    @Override // okio.BufferedSource
    public void readFully(byte[] bArr) throws EOFException {
        int i2 = 0;
        while (i2 < bArr.length) {
            int read = read(bArr, i2, bArr.length - i2);
            if (read == -1) {
                throw new EOFException();
            }
            i2 += read;
        }
    }

    @Override // okio.BufferedSink
    public Buffer write(byte[] bArr) {
        if (bArr != null) {
            return write(bArr, 0, bArr.length);
        }
        throw new IllegalArgumentException("source == null");
    }

    @Override // okio.BufferedSink
    public Buffer write(byte[] bArr, int i2, int i3) {
        if (bArr != null) {
            long j2 = i3;
            Util.checkOffsetAndCount(bArr.length, i2, j2);
            int i4 = i3 + i2;
            while (i2 < i4) {
                Segment writableSegment = writableSegment(1);
                int min = Math.min(i4 - i2, 8192 - writableSegment.limit);
                System.arraycopy(bArr, i2, writableSegment.data, writableSegment.limit, min);
                i2 += min;
                writableSegment.limit += min;
            }
            this.size += j2;
            return this;
        }
        throw new IllegalArgumentException("source == null");
    }

    public final Buffer copyTo(Buffer buffer, long j2, long j3) {
        if (buffer != null) {
            Util.checkOffsetAndCount(this.size, j2, j3);
            if (j3 == 0) {
                return this;
            }
            buffer.size += j3;
            Segment segment = this.head;
            while (true) {
                int i2 = segment.limit;
                int i3 = segment.pos;
                if (j2 < i2 - i3) {
                    break;
                }
                j2 -= i2 - i3;
                segment = segment.next;
            }
            while (j3 > 0) {
                Segment sharedCopy = segment.sharedCopy();
                int i4 = (int) (sharedCopy.pos + j2);
                sharedCopy.pos = i4;
                sharedCopy.limit = Math.min(i4 + ((int) j3), sharedCopy.limit);
                Segment segment2 = buffer.head;
                if (segment2 == null) {
                    sharedCopy.prev = sharedCopy;
                    sharedCopy.next = sharedCopy;
                    buffer.head = sharedCopy;
                } else {
                    segment2.prev.push(sharedCopy);
                }
                j3 -= sharedCopy.limit - sharedCopy.pos;
                segment = segment.next;
                j2 = 0;
            }
            return this;
        }
        throw new IllegalArgumentException("out == null");
    }

    @Override // java.nio.channels.ReadableByteChannel
    public int read(ByteBuffer byteBuffer) throws IOException {
        Segment segment = this.head;
        if (segment == null) {
            return -1;
        }
        int min = Math.min(byteBuffer.remaining(), segment.limit - segment.pos);
        byteBuffer.put(segment.data, segment.pos, min);
        int i2 = segment.pos + min;
        segment.pos = i2;
        this.size -= min;
        if (i2 == segment.limit) {
            this.head = segment.pop();
            SegmentPool.recycle(segment);
        }
        return min;
    }

    @Override // java.nio.channels.WritableByteChannel
    public int write(ByteBuffer byteBuffer) throws IOException {
        if (byteBuffer != null) {
            int remaining = byteBuffer.remaining();
            int i2 = remaining;
            while (i2 > 0) {
                Segment writableSegment = writableSegment(1);
                int min = Math.min(i2, 8192 - writableSegment.limit);
                byteBuffer.get(writableSegment.data, writableSegment.limit, min);
                i2 -= min;
                writableSegment.limit += min;
            }
            this.size += remaining;
            return remaining;
        }
        throw new IllegalArgumentException("source == null");
    }

    @Override // okio.BufferedSource
    public long indexOf(ByteString byteString) throws IOException {
        return indexOf(byteString, 0L);
    }

    @Override // okio.BufferedSource
    public long indexOf(ByteString byteString, long j2) throws IOException {
        byte[] bArr;
        if (byteString.size() != 0) {
            long j3 = 0;
            if (j2 >= 0) {
                Segment segment = this.head;
                long j4 = -1;
                if (segment == null) {
                    return -1L;
                }
                long j5 = this.size;
                if (j5 - j2 < j2) {
                    while (j5 > j2) {
                        segment = segment.prev;
                        j5 -= segment.limit - segment.pos;
                    }
                } else {
                    while (true) {
                        long j6 = (segment.limit - segment.pos) + j3;
                        if (j6 >= j2) {
                            break;
                        }
                        segment = segment.next;
                        j3 = j6;
                    }
                    j5 = j3;
                }
                byte b = byteString.getByte(0);
                int size = byteString.size();
                long j7 = 1 + (this.size - size);
                long j8 = j2;
                Segment segment2 = segment;
                long j9 = j5;
                while (j9 < j7) {
                    byte[] bArr2 = segment2.data;
                    int min = (int) Math.min(segment2.limit, (segment2.pos + j7) - j9);
                    int i2 = (int) ((segment2.pos + j8) - j9);
                    while (i2 < min) {
                        if (bArr2[i2] == b) {
                            bArr = bArr2;
                            if (rangeEquals(segment2, i2 + 1, byteString, 1, size)) {
                                return (i2 - segment2.pos) + j9;
                            }
                        } else {
                            bArr = bArr2;
                        }
                        i2++;
                        bArr2 = bArr;
                    }
                    j9 += segment2.limit - segment2.pos;
                    segment2 = segment2.next;
                    j8 = j9;
                    j4 = -1;
                }
                return j4;
            }
            throw new IllegalArgumentException("fromIndex < 0");
        }
        throw new IllegalArgumentException("bytes is empty");
    }

    @Override // okio.Source
    public long read(Buffer buffer, long j2) {
        if (buffer != null) {
            if (j2 >= 0) {
                long j3 = this.size;
                if (j3 == 0) {
                    return -1L;
                }
                if (j2 > j3) {
                    j2 = j3;
                }
                buffer.write(this, j2);
                return j2;
            }
            throw new IllegalArgumentException("byteCount < 0: " + j2);
        }
        throw new IllegalArgumentException("sink == null");
    }

    @Override // okio.BufferedSink
    public BufferedSink write(Source source, long j2) throws IOException {
        while (j2 > 0) {
            long read = source.read(this, j2);
            if (read == -1) {
                throw new EOFException();
            }
            j2 -= read;
        }
        return this;
    }

    @Override // okio.Sink
    public void write(Buffer buffer, long j2) {
        if (buffer == null) {
            throw new IllegalArgumentException("source == null");
        }
        if (buffer != this) {
            Util.checkOffsetAndCount(buffer.size, 0L, j2);
            while (j2 > 0) {
                Segment segment = buffer.head;
                if (j2 < segment.limit - segment.pos) {
                    Segment segment2 = this.head;
                    Segment segment3 = segment2 != null ? segment2.prev : null;
                    if (segment3 != null && segment3.owner) {
                        if ((segment3.limit + j2) - (segment3.shared ? 0 : segment3.pos) <= IjkMediaMeta.AV_CH_TOP_FRONT_CENTER) {
                            segment.writeTo(segment3, (int) j2);
                            buffer.size -= j2;
                            this.size += j2;
                            return;
                        }
                    }
                    buffer.head = segment.split((int) j2);
                }
                Segment segment4 = buffer.head;
                long j3 = segment4.limit - segment4.pos;
                buffer.head = segment4.pop();
                Segment segment5 = this.head;
                if (segment5 == null) {
                    this.head = segment4;
                    segment4.prev = segment4;
                    segment4.next = segment4;
                } else {
                    segment5.prev.push(segment4).compact();
                }
                buffer.size -= j3;
                this.size += j3;
                j2 -= j3;
            }
            return;
        }
        throw new IllegalArgumentException("source == this");
    }
}

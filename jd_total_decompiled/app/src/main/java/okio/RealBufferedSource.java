package okio;

import com.jingdong.jdsdk.constant.JshopConst;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import javax.annotation.Nullable;
import kotlin.text.Typography;
import tv.danmaku.ijk.media.player.IjkMediaMeta;

/* loaded from: classes11.dex */
public final class RealBufferedSource implements BufferedSource {
    public final Buffer buffer = new Buffer();
    boolean closed;
    public final Source source;

    public RealBufferedSource(Source source) {
        if (source != null) {
            this.source = source;
            return;
        }
        throw new NullPointerException("source == null");
    }

    @Override // okio.BufferedSource, okio.BufferedSink
    public Buffer buffer() {
        return this.buffer;
    }

    @Override // okio.Source, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        if (this.closed) {
            return;
        }
        this.closed = true;
        this.source.close();
        this.buffer.clear();
    }

    @Override // okio.BufferedSource
    public boolean exhausted() throws IOException {
        if (this.closed) {
            throw new IllegalStateException(JshopConst.JSKEY_SHOP_CLOSED);
        }
        return this.buffer.exhausted() && this.source.read(this.buffer, IjkMediaMeta.AV_CH_TOP_FRONT_CENTER) == -1;
    }

    @Override // okio.BufferedSource
    public Buffer getBuffer() {
        return this.buffer;
    }

    @Override // okio.BufferedSource
    public long indexOf(byte b) throws IOException {
        return indexOf(b, 0L, Long.MAX_VALUE);
    }

    @Override // okio.BufferedSource
    public long indexOfElement(ByteString byteString) throws IOException {
        return indexOfElement(byteString, 0L);
    }

    @Override // okio.BufferedSource
    public InputStream inputStream() {
        return new InputStream() { // from class: okio.RealBufferedSource.1
            {
                RealBufferedSource.this = this;
            }

            @Override // java.io.InputStream
            public int available() throws IOException {
                RealBufferedSource realBufferedSource = RealBufferedSource.this;
                if (!realBufferedSource.closed) {
                    return (int) Math.min(realBufferedSource.buffer.size, 2147483647L);
                }
                throw new IOException(JshopConst.JSKEY_SHOP_CLOSED);
            }

            @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
            public void close() throws IOException {
                RealBufferedSource.this.close();
            }

            @Override // java.io.InputStream
            public int read() throws IOException {
                RealBufferedSource realBufferedSource = RealBufferedSource.this;
                if (!realBufferedSource.closed) {
                    Buffer buffer = realBufferedSource.buffer;
                    if (buffer.size == 0 && realBufferedSource.source.read(buffer, IjkMediaMeta.AV_CH_TOP_FRONT_CENTER) == -1) {
                        return -1;
                    }
                    return RealBufferedSource.this.buffer.readByte() & 255;
                }
                throw new IOException(JshopConst.JSKEY_SHOP_CLOSED);
            }

            public String toString() {
                return RealBufferedSource.this + ".inputStream()";
            }

            @Override // java.io.InputStream
            public int read(byte[] bArr, int i2, int i3) throws IOException {
                if (!RealBufferedSource.this.closed) {
                    Util.checkOffsetAndCount(bArr.length, i2, i3);
                    RealBufferedSource realBufferedSource = RealBufferedSource.this;
                    Buffer buffer = realBufferedSource.buffer;
                    if (buffer.size == 0 && realBufferedSource.source.read(buffer, IjkMediaMeta.AV_CH_TOP_FRONT_CENTER) == -1) {
                        return -1;
                    }
                    return RealBufferedSource.this.buffer.read(bArr, i2, i3);
                }
                throw new IOException(JshopConst.JSKEY_SHOP_CLOSED);
            }
        };
    }

    @Override // java.nio.channels.Channel
    public boolean isOpen() {
        return !this.closed;
    }

    @Override // okio.BufferedSource
    public BufferedSource peek() {
        return Okio.buffer(new PeekSource(this));
    }

    @Override // okio.BufferedSource
    public boolean rangeEquals(long j2, ByteString byteString) throws IOException {
        return rangeEquals(j2, byteString, 0, byteString.size());
    }

    @Override // okio.Source
    public long read(Buffer buffer, long j2) throws IOException {
        if (buffer != null) {
            if (j2 >= 0) {
                if (!this.closed) {
                    Buffer buffer2 = this.buffer;
                    if (buffer2.size == 0 && this.source.read(buffer2, IjkMediaMeta.AV_CH_TOP_FRONT_CENTER) == -1) {
                        return -1L;
                    }
                    return this.buffer.read(buffer, Math.min(j2, this.buffer.size));
                }
                throw new IllegalStateException(JshopConst.JSKEY_SHOP_CLOSED);
            }
            throw new IllegalArgumentException("byteCount < 0: " + j2);
        }
        throw new IllegalArgumentException("sink == null");
    }

    @Override // okio.BufferedSource
    public long readAll(Sink sink) throws IOException {
        if (sink != null) {
            long j2 = 0;
            while (this.source.read(this.buffer, IjkMediaMeta.AV_CH_TOP_FRONT_CENTER) != -1) {
                long completeSegmentByteCount = this.buffer.completeSegmentByteCount();
                if (completeSegmentByteCount > 0) {
                    j2 += completeSegmentByteCount;
                    sink.write(this.buffer, completeSegmentByteCount);
                }
            }
            if (this.buffer.size() > 0) {
                long size = j2 + this.buffer.size();
                Buffer buffer = this.buffer;
                sink.write(buffer, buffer.size());
                return size;
            }
            return j2;
        }
        throw new IllegalArgumentException("sink == null");
    }

    @Override // okio.BufferedSource
    public byte readByte() throws IOException {
        require(1L);
        return this.buffer.readByte();
    }

    @Override // okio.BufferedSource
    public byte[] readByteArray() throws IOException {
        this.buffer.writeAll(this.source);
        return this.buffer.readByteArray();
    }

    @Override // okio.BufferedSource
    public ByteString readByteString() throws IOException {
        this.buffer.writeAll(this.source);
        return this.buffer.readByteString();
    }

    @Override // okio.BufferedSource
    public long readDecimalLong() throws IOException {
        byte b;
        require(1L);
        int i2 = 0;
        while (true) {
            int i3 = i2 + 1;
            if (!request(i3)) {
                break;
            }
            b = this.buffer.getByte(i2);
            if ((b < 48 || b > 57) && !(i2 == 0 && b == 45)) {
                break;
            }
            i2 = i3;
        }
        if (i2 == 0) {
            throw new NumberFormatException(String.format("Expected leading [0-9] or '-' character but was %#x", Byte.valueOf(b)));
        }
        return this.buffer.readDecimalLong();
    }

    @Override // okio.BufferedSource
    public void readFully(byte[] bArr) throws IOException {
        try {
            require(bArr.length);
            this.buffer.readFully(bArr);
        } catch (EOFException e2) {
            int i2 = 0;
            while (true) {
                Buffer buffer = this.buffer;
                long j2 = buffer.size;
                if (j2 <= 0) {
                    throw e2;
                }
                int read = buffer.read(bArr, i2, (int) j2);
                if (read == -1) {
                    throw new AssertionError();
                }
                i2 += read;
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:81:0x0032, code lost:
        if (r1 == 0) goto L83;
     */
    /* JADX WARN: Code restructure failed: missing block: B:84:0x0049, code lost:
        throw new java.lang.NumberFormatException(java.lang.String.format("Expected leading [0-9a-fA-F] character but was %#x", java.lang.Byte.valueOf(r3)));
     */
    @Override // okio.BufferedSource
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public long readHexadecimalUnsignedLong() throws IOException {
        require(1L);
        int i2 = 0;
        while (true) {
            int i3 = i2 + 1;
            if (!request(i3)) {
                break;
            }
            byte b = this.buffer.getByte(i2);
            if ((b < 48 || b > 57) && ((b < 97 || b > 102) && (b < 65 || b > 70))) {
                break;
            }
            i2 = i3;
        }
        return this.buffer.readHexadecimalUnsignedLong();
    }

    @Override // okio.BufferedSource
    public int readInt() throws IOException {
        require(4L);
        return this.buffer.readInt();
    }

    @Override // okio.BufferedSource
    public int readIntLe() throws IOException {
        require(4L);
        return this.buffer.readIntLe();
    }

    @Override // okio.BufferedSource
    public long readLong() throws IOException {
        require(8L);
        return this.buffer.readLong();
    }

    @Override // okio.BufferedSource
    public long readLongLe() throws IOException {
        require(8L);
        return this.buffer.readLongLe();
    }

    @Override // okio.BufferedSource
    public short readShort() throws IOException {
        require(2L);
        return this.buffer.readShort();
    }

    @Override // okio.BufferedSource
    public short readShortLe() throws IOException {
        require(2L);
        return this.buffer.readShortLe();
    }

    @Override // okio.BufferedSource
    public String readString(Charset charset) throws IOException {
        if (charset != null) {
            this.buffer.writeAll(this.source);
            return this.buffer.readString(charset);
        }
        throw new IllegalArgumentException("charset == null");
    }

    @Override // okio.BufferedSource
    public String readUtf8() throws IOException {
        this.buffer.writeAll(this.source);
        return this.buffer.readUtf8();
    }

    @Override // okio.BufferedSource
    public int readUtf8CodePoint() throws IOException {
        require(1L);
        byte b = this.buffer.getByte(0L);
        if ((b & 224) == 192) {
            require(2L);
        } else if ((b & 240) == 224) {
            require(3L);
        } else if ((b & 248) == 240) {
            require(4L);
        }
        return this.buffer.readUtf8CodePoint();
    }

    @Override // okio.BufferedSource
    @Nullable
    public String readUtf8Line() throws IOException {
        long indexOf = indexOf((byte) 10);
        if (indexOf == -1) {
            long j2 = this.buffer.size;
            if (j2 != 0) {
                return readUtf8(j2);
            }
            return null;
        }
        return this.buffer.readUtf8Line(indexOf);
    }

    @Override // okio.BufferedSource
    public String readUtf8LineStrict() throws IOException {
        return readUtf8LineStrict(Long.MAX_VALUE);
    }

    @Override // okio.BufferedSource
    public boolean request(long j2) throws IOException {
        Buffer buffer;
        if (j2 >= 0) {
            if (this.closed) {
                throw new IllegalStateException(JshopConst.JSKEY_SHOP_CLOSED);
            }
            do {
                buffer = this.buffer;
                if (buffer.size >= j2) {
                    return true;
                }
            } while (this.source.read(buffer, IjkMediaMeta.AV_CH_TOP_FRONT_CENTER) != -1);
            return false;
        }
        throw new IllegalArgumentException("byteCount < 0: " + j2);
    }

    @Override // okio.BufferedSource
    public void require(long j2) throws IOException {
        if (!request(j2)) {
            throw new EOFException();
        }
    }

    @Override // okio.BufferedSource
    public int select(Options options) throws IOException {
        if (this.closed) {
            throw new IllegalStateException(JshopConst.JSKEY_SHOP_CLOSED);
        }
        do {
            int selectPrefix = this.buffer.selectPrefix(options, true);
            if (selectPrefix == -1) {
                return -1;
            }
            if (selectPrefix != -2) {
                this.buffer.skip(options.byteStrings[selectPrefix].size());
                return selectPrefix;
            }
        } while (this.source.read(this.buffer, IjkMediaMeta.AV_CH_TOP_FRONT_CENTER) != -1);
        return -1;
    }

    @Override // okio.BufferedSource
    public void skip(long j2) throws IOException {
        if (this.closed) {
            throw new IllegalStateException(JshopConst.JSKEY_SHOP_CLOSED);
        }
        while (j2 > 0) {
            Buffer buffer = this.buffer;
            if (buffer.size == 0 && this.source.read(buffer, IjkMediaMeta.AV_CH_TOP_FRONT_CENTER) == -1) {
                throw new EOFException();
            }
            long min = Math.min(j2, this.buffer.size());
            this.buffer.skip(min);
            j2 -= min;
        }
    }

    @Override // okio.Source
    public Timeout timeout() {
        return this.source.timeout();
    }

    public String toString() {
        return "buffer(" + this.source + ")";
    }

    @Override // okio.BufferedSource
    public long indexOf(byte b, long j2) throws IOException {
        return indexOf(b, j2, Long.MAX_VALUE);
    }

    @Override // okio.BufferedSource
    public long indexOfElement(ByteString byteString, long j2) throws IOException {
        if (this.closed) {
            throw new IllegalStateException(JshopConst.JSKEY_SHOP_CLOSED);
        }
        while (true) {
            long indexOfElement = this.buffer.indexOfElement(byteString, j2);
            if (indexOfElement != -1) {
                return indexOfElement;
            }
            Buffer buffer = this.buffer;
            long j3 = buffer.size;
            if (this.source.read(buffer, IjkMediaMeta.AV_CH_TOP_FRONT_CENTER) == -1) {
                return -1L;
            }
            j2 = Math.max(j2, j3);
        }
    }

    @Override // okio.BufferedSource
    public boolean rangeEquals(long j2, ByteString byteString, int i2, int i3) throws IOException {
        if (!this.closed) {
            if (j2 < 0 || i2 < 0 || i3 < 0 || byteString.size() - i2 < i3) {
                return false;
            }
            for (int i4 = 0; i4 < i3; i4++) {
                long j3 = i4 + j2;
                if (!request(1 + j3) || this.buffer.getByte(j3) != byteString.getByte(i2 + i4)) {
                    return false;
                }
            }
            return true;
        }
        throw new IllegalStateException(JshopConst.JSKEY_SHOP_CLOSED);
    }

    @Override // okio.BufferedSource
    public String readUtf8LineStrict(long j2) throws IOException {
        if (j2 >= 0) {
            long j3 = j2 == Long.MAX_VALUE ? Long.MAX_VALUE : j2 + 1;
            long indexOf = indexOf((byte) 10, 0L, j3);
            if (indexOf != -1) {
                return this.buffer.readUtf8Line(indexOf);
            }
            if (j3 < Long.MAX_VALUE && request(j3) && this.buffer.getByte(j3 - 1) == 13 && request(1 + j3) && this.buffer.getByte(j3) == 10) {
                return this.buffer.readUtf8Line(j3);
            }
            Buffer buffer = new Buffer();
            Buffer buffer2 = this.buffer;
            buffer2.copyTo(buffer, 0L, Math.min(32L, buffer2.size()));
            throw new EOFException("\\n not found: limit=" + Math.min(this.buffer.size(), j2) + " content=" + buffer.readByteString().hex() + Typography.ellipsis);
        }
        throw new IllegalArgumentException("limit < 0: " + j2);
    }

    @Override // okio.BufferedSource
    public long indexOf(byte b, long j2, long j3) throws IOException {
        if (this.closed) {
            throw new IllegalStateException(JshopConst.JSKEY_SHOP_CLOSED);
        }
        if (j2 < 0 || j3 < j2) {
            throw new IllegalArgumentException(String.format("fromIndex=%s toIndex=%s", Long.valueOf(j2), Long.valueOf(j3)));
        }
        while (j2 < j3) {
            long indexOf = this.buffer.indexOf(b, j2, j3);
            if (indexOf == -1) {
                Buffer buffer = this.buffer;
                long j4 = buffer.size;
                if (j4 >= j3 || this.source.read(buffer, IjkMediaMeta.AV_CH_TOP_FRONT_CENTER) == -1) {
                    break;
                }
                j2 = Math.max(j2, j4);
            } else {
                return indexOf;
            }
        }
        return -1L;
    }

    @Override // okio.BufferedSource
    public byte[] readByteArray(long j2) throws IOException {
        require(j2);
        return this.buffer.readByteArray(j2);
    }

    @Override // okio.BufferedSource
    public ByteString readByteString(long j2) throws IOException {
        require(j2);
        return this.buffer.readByteString(j2);
    }

    @Override // okio.BufferedSource
    public String readUtf8(long j2) throws IOException {
        require(j2);
        return this.buffer.readUtf8(j2);
    }

    @Override // okio.BufferedSource
    public String readString(long j2, Charset charset) throws IOException {
        require(j2);
        if (charset != null) {
            return this.buffer.readString(j2, charset);
        }
        throw new IllegalArgumentException("charset == null");
    }

    @Override // okio.BufferedSource
    public void readFully(Buffer buffer, long j2) throws IOException {
        try {
            require(j2);
            this.buffer.readFully(buffer, j2);
        } catch (EOFException e2) {
            buffer.writeAll(this.buffer);
            throw e2;
        }
    }

    @Override // okio.BufferedSource
    public int read(byte[] bArr) throws IOException {
        return read(bArr, 0, bArr.length);
    }

    @Override // okio.BufferedSource
    public int read(byte[] bArr, int i2, int i3) throws IOException {
        long j2 = i3;
        Util.checkOffsetAndCount(bArr.length, i2, j2);
        Buffer buffer = this.buffer;
        if (buffer.size == 0 && this.source.read(buffer, IjkMediaMeta.AV_CH_TOP_FRONT_CENTER) == -1) {
            return -1;
        }
        return this.buffer.read(bArr, i2, (int) Math.min(j2, this.buffer.size));
    }

    @Override // okio.BufferedSource
    public long indexOf(ByteString byteString) throws IOException {
        return indexOf(byteString, 0L);
    }

    @Override // okio.BufferedSource
    public long indexOf(ByteString byteString, long j2) throws IOException {
        if (this.closed) {
            throw new IllegalStateException(JshopConst.JSKEY_SHOP_CLOSED);
        }
        while (true) {
            long indexOf = this.buffer.indexOf(byteString, j2);
            if (indexOf != -1) {
                return indexOf;
            }
            Buffer buffer = this.buffer;
            long j3 = buffer.size;
            if (this.source.read(buffer, IjkMediaMeta.AV_CH_TOP_FRONT_CENTER) == -1) {
                return -1L;
            }
            j2 = Math.max(j2, (j3 - byteString.size()) + 1);
        }
    }

    @Override // java.nio.channels.ReadableByteChannel
    public int read(ByteBuffer byteBuffer) throws IOException {
        Buffer buffer = this.buffer;
        if (buffer.size == 0 && this.source.read(buffer, IjkMediaMeta.AV_CH_TOP_FRONT_CENTER) == -1) {
            return -1;
        }
        return this.buffer.read(byteBuffer);
    }
}

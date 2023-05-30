package okio;

import com.jingdong.jdsdk.constant.JshopConst;
import java.io.EOFException;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import tv.danmaku.ijk.media.player.IjkMediaMeta;

/* loaded from: classes11.dex */
public final class RealBufferedSink implements BufferedSink {
    public final Buffer buffer = new Buffer();
    boolean closed;
    public final Sink sink;

    public RealBufferedSink(Sink sink) {
        if (sink != null) {
            this.sink = sink;
            return;
        }
        throw new NullPointerException("sink == null");
    }

    @Override // okio.BufferedSink
    public Buffer buffer() {
        return this.buffer;
    }

    @Override // okio.Sink, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        if (this.closed) {
            return;
        }
        Throwable th = null;
        try {
            Buffer buffer = this.buffer;
            long j2 = buffer.size;
            if (j2 > 0) {
                this.sink.write(buffer, j2);
            }
        } catch (Throwable th2) {
            th = th2;
        }
        try {
            this.sink.close();
        } catch (Throwable th3) {
            if (th == null) {
                th = th3;
            }
        }
        this.closed = true;
        if (th != null) {
            Util.sneakyRethrow(th);
        }
    }

    @Override // okio.BufferedSink
    public BufferedSink emit() throws IOException {
        if (!this.closed) {
            long size = this.buffer.size();
            if (size > 0) {
                this.sink.write(this.buffer, size);
            }
            return this;
        }
        throw new IllegalStateException(JshopConst.JSKEY_SHOP_CLOSED);
    }

    @Override // okio.BufferedSink
    public BufferedSink emitCompleteSegments() throws IOException {
        if (!this.closed) {
            long completeSegmentByteCount = this.buffer.completeSegmentByteCount();
            if (completeSegmentByteCount > 0) {
                this.sink.write(this.buffer, completeSegmentByteCount);
            }
            return this;
        }
        throw new IllegalStateException(JshopConst.JSKEY_SHOP_CLOSED);
    }

    @Override // okio.BufferedSink, okio.Sink, java.io.Flushable
    public void flush() throws IOException {
        if (!this.closed) {
            Buffer buffer = this.buffer;
            long j2 = buffer.size;
            if (j2 > 0) {
                this.sink.write(buffer, j2);
            }
            this.sink.flush();
            return;
        }
        throw new IllegalStateException(JshopConst.JSKEY_SHOP_CLOSED);
    }

    @Override // java.nio.channels.Channel
    public boolean isOpen() {
        return !this.closed;
    }

    @Override // okio.BufferedSink
    public OutputStream outputStream() {
        return new OutputStream() { // from class: okio.RealBufferedSink.1
            {
                RealBufferedSink.this = this;
            }

            @Override // java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
            public void close() throws IOException {
                RealBufferedSink.this.close();
            }

            @Override // java.io.OutputStream, java.io.Flushable
            public void flush() throws IOException {
                RealBufferedSink realBufferedSink = RealBufferedSink.this;
                if (realBufferedSink.closed) {
                    return;
                }
                realBufferedSink.flush();
            }

            public String toString() {
                return RealBufferedSink.this + ".outputStream()";
            }

            @Override // java.io.OutputStream
            public void write(int i2) throws IOException {
                RealBufferedSink realBufferedSink = RealBufferedSink.this;
                if (!realBufferedSink.closed) {
                    realBufferedSink.buffer.writeByte((int) ((byte) i2));
                    RealBufferedSink.this.emitCompleteSegments();
                    return;
                }
                throw new IOException(JshopConst.JSKEY_SHOP_CLOSED);
            }

            @Override // java.io.OutputStream
            public void write(byte[] bArr, int i2, int i3) throws IOException {
                RealBufferedSink realBufferedSink = RealBufferedSink.this;
                if (!realBufferedSink.closed) {
                    realBufferedSink.buffer.write(bArr, i2, i3);
                    RealBufferedSink.this.emitCompleteSegments();
                    return;
                }
                throw new IOException(JshopConst.JSKEY_SHOP_CLOSED);
            }
        };
    }

    @Override // okio.Sink
    public Timeout timeout() {
        return this.sink.timeout();
    }

    public String toString() {
        return "buffer(" + this.sink + ")";
    }

    @Override // okio.Sink
    public void write(Buffer buffer, long j2) throws IOException {
        if (!this.closed) {
            this.buffer.write(buffer, j2);
            emitCompleteSegments();
            return;
        }
        throw new IllegalStateException(JshopConst.JSKEY_SHOP_CLOSED);
    }

    @Override // okio.BufferedSink
    public long writeAll(Source source) throws IOException {
        if (source == null) {
            throw new IllegalArgumentException("source == null");
        }
        long j2 = 0;
        while (true) {
            long read = source.read(this.buffer, IjkMediaMeta.AV_CH_TOP_FRONT_CENTER);
            if (read == -1) {
                return j2;
            }
            j2 += read;
            emitCompleteSegments();
        }
    }

    @Override // okio.BufferedSink
    public BufferedSink writeByte(int i2) throws IOException {
        if (!this.closed) {
            this.buffer.writeByte(i2);
            return emitCompleteSegments();
        }
        throw new IllegalStateException(JshopConst.JSKEY_SHOP_CLOSED);
    }

    @Override // okio.BufferedSink
    public BufferedSink writeDecimalLong(long j2) throws IOException {
        if (!this.closed) {
            this.buffer.writeDecimalLong(j2);
            return emitCompleteSegments();
        }
        throw new IllegalStateException(JshopConst.JSKEY_SHOP_CLOSED);
    }

    @Override // okio.BufferedSink
    public BufferedSink writeHexadecimalUnsignedLong(long j2) throws IOException {
        if (!this.closed) {
            this.buffer.writeHexadecimalUnsignedLong(j2);
            return emitCompleteSegments();
        }
        throw new IllegalStateException(JshopConst.JSKEY_SHOP_CLOSED);
    }

    @Override // okio.BufferedSink
    public BufferedSink writeInt(int i2) throws IOException {
        if (!this.closed) {
            this.buffer.writeInt(i2);
            return emitCompleteSegments();
        }
        throw new IllegalStateException(JshopConst.JSKEY_SHOP_CLOSED);
    }

    @Override // okio.BufferedSink
    public BufferedSink writeIntLe(int i2) throws IOException {
        if (!this.closed) {
            this.buffer.writeIntLe(i2);
            return emitCompleteSegments();
        }
        throw new IllegalStateException(JshopConst.JSKEY_SHOP_CLOSED);
    }

    @Override // okio.BufferedSink
    public BufferedSink writeLong(long j2) throws IOException {
        if (!this.closed) {
            this.buffer.writeLong(j2);
            return emitCompleteSegments();
        }
        throw new IllegalStateException(JshopConst.JSKEY_SHOP_CLOSED);
    }

    @Override // okio.BufferedSink
    public BufferedSink writeLongLe(long j2) throws IOException {
        if (!this.closed) {
            this.buffer.writeLongLe(j2);
            return emitCompleteSegments();
        }
        throw new IllegalStateException(JshopConst.JSKEY_SHOP_CLOSED);
    }

    @Override // okio.BufferedSink
    public BufferedSink writeShort(int i2) throws IOException {
        if (!this.closed) {
            this.buffer.writeShort(i2);
            return emitCompleteSegments();
        }
        throw new IllegalStateException(JshopConst.JSKEY_SHOP_CLOSED);
    }

    @Override // okio.BufferedSink
    public BufferedSink writeShortLe(int i2) throws IOException {
        if (!this.closed) {
            this.buffer.writeShortLe(i2);
            return emitCompleteSegments();
        }
        throw new IllegalStateException(JshopConst.JSKEY_SHOP_CLOSED);
    }

    @Override // okio.BufferedSink
    public BufferedSink writeString(String str, Charset charset) throws IOException {
        if (!this.closed) {
            this.buffer.writeString(str, charset);
            return emitCompleteSegments();
        }
        throw new IllegalStateException(JshopConst.JSKEY_SHOP_CLOSED);
    }

    @Override // okio.BufferedSink
    public BufferedSink writeUtf8(String str) throws IOException {
        if (!this.closed) {
            this.buffer.writeUtf8(str);
            return emitCompleteSegments();
        }
        throw new IllegalStateException(JshopConst.JSKEY_SHOP_CLOSED);
    }

    @Override // okio.BufferedSink
    public BufferedSink writeUtf8CodePoint(int i2) throws IOException {
        if (!this.closed) {
            this.buffer.writeUtf8CodePoint(i2);
            return emitCompleteSegments();
        }
        throw new IllegalStateException(JshopConst.JSKEY_SHOP_CLOSED);
    }

    @Override // okio.BufferedSink
    public BufferedSink write(ByteString byteString) throws IOException {
        if (!this.closed) {
            this.buffer.write(byteString);
            return emitCompleteSegments();
        }
        throw new IllegalStateException(JshopConst.JSKEY_SHOP_CLOSED);
    }

    @Override // okio.BufferedSink
    public BufferedSink writeString(String str, int i2, int i3, Charset charset) throws IOException {
        if (!this.closed) {
            this.buffer.writeString(str, i2, i3, charset);
            return emitCompleteSegments();
        }
        throw new IllegalStateException(JshopConst.JSKEY_SHOP_CLOSED);
    }

    @Override // okio.BufferedSink
    public BufferedSink writeUtf8(String str, int i2, int i3) throws IOException {
        if (!this.closed) {
            this.buffer.writeUtf8(str, i2, i3);
            return emitCompleteSegments();
        }
        throw new IllegalStateException(JshopConst.JSKEY_SHOP_CLOSED);
    }

    @Override // okio.BufferedSink
    public BufferedSink write(byte[] bArr) throws IOException {
        if (!this.closed) {
            this.buffer.write(bArr);
            return emitCompleteSegments();
        }
        throw new IllegalStateException(JshopConst.JSKEY_SHOP_CLOSED);
    }

    @Override // okio.BufferedSink
    public BufferedSink write(byte[] bArr, int i2, int i3) throws IOException {
        if (!this.closed) {
            this.buffer.write(bArr, i2, i3);
            return emitCompleteSegments();
        }
        throw new IllegalStateException(JshopConst.JSKEY_SHOP_CLOSED);
    }

    @Override // java.nio.channels.WritableByteChannel
    public int write(ByteBuffer byteBuffer) throws IOException {
        if (!this.closed) {
            int write = this.buffer.write(byteBuffer);
            emitCompleteSegments();
            return write;
        }
        throw new IllegalStateException(JshopConst.JSKEY_SHOP_CLOSED);
    }

    @Override // okio.BufferedSink
    public BufferedSink write(Source source, long j2) throws IOException {
        while (j2 > 0) {
            long read = source.read(this.buffer, j2);
            if (read != -1) {
                j2 -= read;
                emitCompleteSegments();
            } else {
                throw new EOFException();
            }
        }
        return this;
    }
}

package okhttp3.internal.ws;

import com.jingdong.jdsdk.constant.JshopConst;
import java.io.IOException;
import java.util.Random;
import okio.Buffer;
import okio.BufferedSink;
import okio.ByteString;
import okio.Sink;
import okio.Timeout;
import tv.danmaku.ijk.media.player.IjkMediaMeta;

/* loaded from: classes11.dex */
final class WebSocketWriter {
    boolean activeWriter;
    final Buffer buffer = new Buffer();
    final FrameSink frameSink = new FrameSink();
    final boolean isClient;
    private final Buffer.UnsafeCursor maskCursor;
    private final byte[] maskKey;
    final Random random;
    final BufferedSink sink;
    final Buffer sinkBuffer;
    boolean writerClosed;

    /* loaded from: classes11.dex */
    final class FrameSink implements Sink {
        boolean closed;
        long contentLength;
        int formatOpcode;
        boolean isFirstFrame;

        FrameSink() {
        }

        @Override // okio.Sink, java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            if (!this.closed) {
                WebSocketWriter webSocketWriter = WebSocketWriter.this;
                webSocketWriter.writeMessageFrame(this.formatOpcode, webSocketWriter.buffer.size(), this.isFirstFrame, true);
                this.closed = true;
                WebSocketWriter.this.activeWriter = false;
                return;
            }
            throw new IOException(JshopConst.JSKEY_SHOP_CLOSED);
        }

        @Override // okio.Sink, java.io.Flushable
        public void flush() throws IOException {
            if (!this.closed) {
                WebSocketWriter webSocketWriter = WebSocketWriter.this;
                webSocketWriter.writeMessageFrame(this.formatOpcode, webSocketWriter.buffer.size(), this.isFirstFrame, false);
                this.isFirstFrame = false;
                return;
            }
            throw new IOException(JshopConst.JSKEY_SHOP_CLOSED);
        }

        @Override // okio.Sink
        public Timeout timeout() {
            return WebSocketWriter.this.sink.timeout();
        }

        @Override // okio.Sink
        public void write(Buffer buffer, long j2) throws IOException {
            if (!this.closed) {
                WebSocketWriter.this.buffer.write(buffer, j2);
                boolean z = this.isFirstFrame && this.contentLength != -1 && WebSocketWriter.this.buffer.size() > this.contentLength - IjkMediaMeta.AV_CH_TOP_FRONT_CENTER;
                long completeSegmentByteCount = WebSocketWriter.this.buffer.completeSegmentByteCount();
                if (completeSegmentByteCount <= 0 || z) {
                    return;
                }
                WebSocketWriter.this.writeMessageFrame(this.formatOpcode, completeSegmentByteCount, this.isFirstFrame, false);
                this.isFirstFrame = false;
                return;
            }
            throw new IOException(JshopConst.JSKEY_SHOP_CLOSED);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public WebSocketWriter(boolean z, BufferedSink bufferedSink, Random random) {
        if (bufferedSink == null) {
            throw new NullPointerException("sink == null");
        }
        if (random != null) {
            this.isClient = z;
            this.sink = bufferedSink;
            this.sinkBuffer = bufferedSink.buffer();
            this.random = random;
            this.maskKey = z ? new byte[4] : null;
            this.maskCursor = z ? new Buffer.UnsafeCursor() : null;
            return;
        }
        throw new NullPointerException("random == null");
    }

    private void writeControlFrame(int i2, ByteString byteString) throws IOException {
        if (!this.writerClosed) {
            int size = byteString.size();
            if (size <= 125) {
                this.sinkBuffer.writeByte(i2 | 128);
                if (this.isClient) {
                    this.sinkBuffer.writeByte(size | 128);
                    this.random.nextBytes(this.maskKey);
                    this.sinkBuffer.write(this.maskKey);
                    if (size > 0) {
                        long size2 = this.sinkBuffer.size();
                        this.sinkBuffer.write(byteString);
                        this.sinkBuffer.readAndWriteUnsafe(this.maskCursor);
                        this.maskCursor.seek(size2);
                        WebSocketProtocol.toggleMask(this.maskCursor, this.maskKey);
                        this.maskCursor.close();
                    }
                } else {
                    this.sinkBuffer.writeByte(size);
                    this.sinkBuffer.write(byteString);
                }
                this.sink.flush();
                return;
            }
            throw new IllegalArgumentException("Payload size must be less than or equal to 125");
        }
        throw new IOException(JshopConst.JSKEY_SHOP_CLOSED);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Sink newMessageSink(int i2, long j2) {
        if (!this.activeWriter) {
            this.activeWriter = true;
            FrameSink frameSink = this.frameSink;
            frameSink.formatOpcode = i2;
            frameSink.contentLength = j2;
            frameSink.isFirstFrame = true;
            frameSink.closed = false;
            return frameSink;
        }
        throw new IllegalStateException("Another message writer is active. Did you call close()?");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void writeClose(int i2, ByteString byteString) throws IOException {
        ByteString byteString2 = ByteString.EMPTY;
        if (i2 != 0 || byteString != null) {
            if (i2 != 0) {
                WebSocketProtocol.validateCloseCode(i2);
            }
            Buffer buffer = new Buffer();
            buffer.writeShort(i2);
            if (byteString != null) {
                buffer.write(byteString);
            }
            byteString2 = buffer.readByteString();
        }
        try {
            writeControlFrame(8, byteString2);
        } finally {
            this.writerClosed = true;
        }
    }

    void writeMessageFrame(int i2, long j2, boolean z, boolean z2) throws IOException {
        if (!this.writerClosed) {
            if (!z) {
                i2 = 0;
            }
            if (z2) {
                i2 |= 128;
            }
            this.sinkBuffer.writeByte(i2);
            int i3 = this.isClient ? 128 : 0;
            if (j2 <= 125) {
                this.sinkBuffer.writeByte(((int) j2) | i3);
            } else if (j2 <= 65535) {
                this.sinkBuffer.writeByte(i3 | 126);
                this.sinkBuffer.writeShort((int) j2);
            } else {
                this.sinkBuffer.writeByte(i3 | 127);
                this.sinkBuffer.writeLong(j2);
            }
            if (this.isClient) {
                this.random.nextBytes(this.maskKey);
                this.sinkBuffer.write(this.maskKey);
                if (j2 > 0) {
                    long size = this.sinkBuffer.size();
                    this.sinkBuffer.write(this.buffer, j2);
                    this.sinkBuffer.readAndWriteUnsafe(this.maskCursor);
                    this.maskCursor.seek(size);
                    WebSocketProtocol.toggleMask(this.maskCursor, this.maskKey);
                    this.maskCursor.close();
                }
            } else {
                this.sinkBuffer.write(this.buffer, j2);
            }
            this.sink.emit();
            return;
        }
        throw new IOException(JshopConst.JSKEY_SHOP_CLOSED);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void writePing(ByteString byteString) throws IOException {
        writeControlFrame(9, byteString);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void writePong(ByteString byteString) throws IOException {
        writeControlFrame(10, byteString);
    }
}

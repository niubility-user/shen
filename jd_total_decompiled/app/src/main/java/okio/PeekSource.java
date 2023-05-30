package okio;

import com.jingdong.jdsdk.constant.JshopConst;
import java.io.IOException;

/* loaded from: classes11.dex */
final class PeekSource implements Source {
    private final Buffer buffer;
    private boolean closed;
    private int expectedPos;
    private Segment expectedSegment;
    private long pos;
    private final BufferedSource upstream;

    /* JADX INFO: Access modifiers changed from: package-private */
    public PeekSource(BufferedSource bufferedSource) {
        this.upstream = bufferedSource;
        Buffer buffer = bufferedSource.buffer();
        this.buffer = buffer;
        Segment segment = buffer.head;
        this.expectedSegment = segment;
        this.expectedPos = segment != null ? segment.pos : -1;
    }

    @Override // okio.Source, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.closed = true;
    }

    @Override // okio.Source
    public long read(Buffer buffer, long j2) throws IOException {
        Segment segment;
        Segment segment2;
        if (j2 >= 0) {
            if (!this.closed) {
                Segment segment3 = this.expectedSegment;
                if (segment3 == null || (segment3 == (segment2 = this.buffer.head) && this.expectedPos == segment2.pos)) {
                    if (j2 == 0) {
                        return 0L;
                    }
                    if (this.upstream.request(this.pos + 1)) {
                        if (this.expectedSegment == null && (segment = this.buffer.head) != null) {
                            this.expectedSegment = segment;
                            this.expectedPos = segment.pos;
                        }
                        long min = Math.min(j2, this.buffer.size - this.pos);
                        this.buffer.copyTo(buffer, this.pos, min);
                        this.pos += min;
                        return min;
                    }
                    return -1L;
                }
                throw new IllegalStateException("Peek source is invalid because upstream source was used");
            }
            throw new IllegalStateException(JshopConst.JSKEY_SHOP_CLOSED);
        }
        throw new IllegalArgumentException("byteCount < 0: " + j2);
    }

    @Override // okio.Source
    public Timeout timeout() {
        return this.upstream.timeout();
    }
}

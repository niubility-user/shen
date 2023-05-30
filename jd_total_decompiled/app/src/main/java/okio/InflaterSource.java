package okio;

import com.jingdong.jdsdk.constant.JshopConst;
import java.io.EOFException;
import java.io.IOException;
import java.util.zip.DataFormatException;
import java.util.zip.Inflater;

/* loaded from: classes11.dex */
public final class InflaterSource implements Source {
    private int bufferBytesHeldByInflater;
    private boolean closed;
    private final Inflater inflater;
    private final BufferedSource source;

    public InflaterSource(Source source, Inflater inflater) {
        this(Okio.buffer(source), inflater);
    }

    private void releaseInflatedBytes() throws IOException {
        int i2 = this.bufferBytesHeldByInflater;
        if (i2 == 0) {
            return;
        }
        int remaining = i2 - this.inflater.getRemaining();
        this.bufferBytesHeldByInflater -= remaining;
        this.source.skip(remaining);
    }

    @Override // okio.Source, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        if (this.closed) {
            return;
        }
        this.inflater.end();
        this.closed = true;
        this.source.close();
    }

    @Override // okio.Source
    public long read(Buffer buffer, long j2) throws IOException {
        boolean refill;
        if (j2 >= 0) {
            if (this.closed) {
                throw new IllegalStateException(JshopConst.JSKEY_SHOP_CLOSED);
            }
            if (j2 == 0) {
                return 0L;
            }
            do {
                refill = refill();
                try {
                    Segment writableSegment = buffer.writableSegment(1);
                    int inflate = this.inflater.inflate(writableSegment.data, writableSegment.limit, (int) Math.min(j2, 8192 - writableSegment.limit));
                    if (inflate > 0) {
                        writableSegment.limit += inflate;
                        long j3 = inflate;
                        buffer.size += j3;
                        return j3;
                    }
                    if (!this.inflater.finished() && !this.inflater.needsDictionary()) {
                    }
                    releaseInflatedBytes();
                    if (writableSegment.pos == writableSegment.limit) {
                        buffer.head = writableSegment.pop();
                        SegmentPool.recycle(writableSegment);
                        return -1L;
                    }
                    return -1L;
                } catch (DataFormatException e2) {
                    throw new IOException(e2);
                }
            } while (!refill);
            throw new EOFException("source exhausted prematurely");
        }
        throw new IllegalArgumentException("byteCount < 0: " + j2);
    }

    public final boolean refill() throws IOException {
        if (this.inflater.needsInput()) {
            releaseInflatedBytes();
            if (this.inflater.getRemaining() == 0) {
                if (this.source.exhausted()) {
                    return true;
                }
                Segment segment = this.source.buffer().head;
                int i2 = segment.limit;
                int i3 = segment.pos;
                int i4 = i2 - i3;
                this.bufferBytesHeldByInflater = i4;
                this.inflater.setInput(segment.data, i3, i4);
                return false;
            }
            throw new IllegalStateException("?");
        }
        return false;
    }

    @Override // okio.Source
    public Timeout timeout() {
        return this.source.timeout();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public InflaterSource(BufferedSource bufferedSource, Inflater inflater) {
        if (bufferedSource == null) {
            throw new IllegalArgumentException("source == null");
        }
        if (inflater != null) {
            this.source = bufferedSource;
            this.inflater = inflater;
            return;
        }
        throw new IllegalArgumentException("inflater == null");
    }
}

package okhttp3.internal.huc;

import com.jingdong.jdsdk.constant.JshopConst;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.io.OutputStream;
import java.net.ProtocolException;
import java.net.SocketTimeoutException;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okio.BufferedSink;
import okio.Timeout;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes11.dex */
public abstract class OutputStreamRequestBody extends RequestBody {
    boolean closed;
    private long expectedContentLength;
    private OutputStream outputStream;
    private Timeout timeout;

    @Override // okhttp3.RequestBody
    public long contentLength() throws IOException {
        return this.expectedContentLength;
    }

    @Override // okhttp3.RequestBody
    public final MediaType contentType() {
        return null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void initOutputStream(final BufferedSink bufferedSink, final long j2) {
        this.timeout = bufferedSink.timeout();
        this.expectedContentLength = j2;
        this.outputStream = new OutputStream() { // from class: okhttp3.internal.huc.OutputStreamRequestBody.1
            private long bytesReceived;

            @Override // java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
            public void close() throws IOException {
                OutputStreamRequestBody.this.closed = true;
                long j3 = j2;
                if (j3 != -1 && this.bytesReceived < j3) {
                    throw new ProtocolException("expected " + j2 + " bytes but received " + this.bytesReceived);
                }
                bufferedSink.close();
            }

            @Override // java.io.OutputStream, java.io.Flushable
            public void flush() throws IOException {
                if (OutputStreamRequestBody.this.closed) {
                    return;
                }
                bufferedSink.flush();
            }

            @Override // java.io.OutputStream
            public void write(int i2) throws IOException {
                write(new byte[]{(byte) i2}, 0, 1);
            }

            @Override // java.io.OutputStream
            public void write(byte[] bArr, int i2, int i3) throws IOException {
                if (!OutputStreamRequestBody.this.closed) {
                    long j3 = j2;
                    if (j3 != -1 && this.bytesReceived + i3 > j3) {
                        throw new ProtocolException("expected " + j2 + " bytes but received " + this.bytesReceived + i3);
                    }
                    this.bytesReceived += i3;
                    try {
                        bufferedSink.write(bArr, i2, i3);
                        return;
                    } catch (InterruptedIOException e2) {
                        throw new SocketTimeoutException(e2.getMessage());
                    }
                }
                throw new IOException(JshopConst.JSKEY_SHOP_CLOSED);
            }
        };
    }

    public final boolean isClosed() {
        return this.closed;
    }

    public final OutputStream outputStream() {
        return this.outputStream;
    }

    public Request prepareToSendRequest(Request request) throws IOException {
        return request;
    }

    public final Timeout timeout() {
        return this.timeout;
    }
}

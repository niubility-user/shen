package okio;

import java.io.Closeable;
import java.io.IOException;

/* loaded from: classes11.dex */
public interface Source extends Closeable {
    @Override // java.io.Closeable, java.lang.AutoCloseable
    void close() throws IOException;

    long read(Buffer buffer, long j2) throws IOException;

    Timeout timeout();
}

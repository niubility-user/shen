package okio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Nullable;
import org.codehaus.mojo.animal_sniffer.IgnoreJRERequirement;

/* loaded from: classes11.dex */
public final class Okio {
    static final Logger logger = Logger.getLogger(Okio.class.getName());

    private Okio() {
    }

    public static Sink appendingSink(File file) throws FileNotFoundException {
        if (file != null) {
            return sink(new FileOutputStream(file, true));
        }
        throw new IllegalArgumentException("file == null");
    }

    public static Sink blackhole() {
        return new Sink() { // from class: okio.Okio.3
            @Override // okio.Sink, java.io.Closeable, java.lang.AutoCloseable
            public void close() throws IOException {
            }

            @Override // okio.Sink, java.io.Flushable
            public void flush() throws IOException {
            }

            @Override // okio.Sink
            public Timeout timeout() {
                return Timeout.NONE;
            }

            @Override // okio.Sink
            public void write(Buffer buffer, long j2) throws IOException {
                buffer.skip(j2);
            }
        };
    }

    public static BufferedSource buffer(Source source) {
        return new RealBufferedSource(source);
    }

    static boolean isAndroidGetsocknameError(AssertionError assertionError) {
        return (assertionError.getCause() == null || assertionError.getMessage() == null || !assertionError.getMessage().contains("getsockname failed")) ? false : true;
    }

    public static Sink sink(OutputStream outputStream) {
        return sink(outputStream, new Timeout());
    }

    public static Source source(InputStream inputStream) {
        return source(inputStream, new Timeout());
    }

    private static AsyncTimeout timeout(final Socket socket) {
        return new AsyncTimeout() { // from class: okio.Okio.4
            @Override // okio.AsyncTimeout
            protected IOException newTimeoutException(@Nullable IOException iOException) {
                SocketTimeoutException socketTimeoutException = new SocketTimeoutException("timeout");
                if (iOException != null) {
                    socketTimeoutException.initCause(iOException);
                }
                return socketTimeoutException;
            }

            @Override // okio.AsyncTimeout
            protected void timedOut() {
                try {
                    socket.close();
                } catch (AssertionError e2) {
                    if (Okio.isAndroidGetsocknameError(e2)) {
                        Okio.logger.log(Level.WARNING, "Failed to close timed out socket " + socket, (Throwable) e2);
                        return;
                    }
                    throw e2;
                } catch (Exception e3) {
                    Okio.logger.log(Level.WARNING, "Failed to close timed out socket " + socket, (Throwable) e3);
                }
            }
        };
    }

    public static BufferedSink buffer(Sink sink) {
        return new RealBufferedSink(sink);
    }

    private static Sink sink(final OutputStream outputStream, final Timeout timeout) {
        if (outputStream != null) {
            if (timeout != null) {
                return new Sink() { // from class: okio.Okio.1
                    @Override // okio.Sink, java.io.Closeable, java.lang.AutoCloseable
                    public void close() throws IOException {
                        outputStream.close();
                    }

                    @Override // okio.Sink, java.io.Flushable
                    public void flush() throws IOException {
                        outputStream.flush();
                    }

                    @Override // okio.Sink
                    public Timeout timeout() {
                        return Timeout.this;
                    }

                    public String toString() {
                        return "sink(" + outputStream + ")";
                    }

                    @Override // okio.Sink
                    public void write(Buffer buffer, long j2) throws IOException {
                        Util.checkOffsetAndCount(buffer.size, 0L, j2);
                        while (j2 > 0) {
                            Timeout.this.throwIfReached();
                            Segment segment = buffer.head;
                            int min = (int) Math.min(j2, segment.limit - segment.pos);
                            outputStream.write(segment.data, segment.pos, min);
                            int i2 = segment.pos + min;
                            segment.pos = i2;
                            long j3 = min;
                            j2 -= j3;
                            buffer.size -= j3;
                            if (i2 == segment.limit) {
                                buffer.head = segment.pop();
                                SegmentPool.recycle(segment);
                            }
                        }
                    }
                };
            }
            throw new IllegalArgumentException("timeout == null");
        }
        throw new IllegalArgumentException("out == null");
    }

    private static Source source(final InputStream inputStream, final Timeout timeout) {
        if (inputStream != null) {
            if (timeout != null) {
                return new Source() { // from class: okio.Okio.2
                    @Override // okio.Source, java.io.Closeable, java.lang.AutoCloseable
                    public void close() throws IOException {
                        inputStream.close();
                    }

                    @Override // okio.Source
                    public long read(Buffer buffer, long j2) throws IOException {
                        if (j2 < 0) {
                            throw new IllegalArgumentException("byteCount < 0: " + j2);
                        } else if (j2 == 0) {
                            return 0L;
                        } else {
                            try {
                                Timeout.this.throwIfReached();
                                Segment writableSegment = buffer.writableSegment(1);
                                int read = inputStream.read(writableSegment.data, writableSegment.limit, (int) Math.min(j2, 8192 - writableSegment.limit));
                                if (read == -1) {
                                    return -1L;
                                }
                                writableSegment.limit += read;
                                long j3 = read;
                                buffer.size += j3;
                                return j3;
                            } catch (AssertionError e2) {
                                if (Okio.isAndroidGetsocknameError(e2)) {
                                    throw new IOException(e2);
                                }
                                throw e2;
                            }
                        }
                    }

                    @Override // okio.Source
                    public Timeout timeout() {
                        return Timeout.this;
                    }

                    public String toString() {
                        return "source(" + inputStream + ")";
                    }
                };
            }
            throw new IllegalArgumentException("timeout == null");
        }
        throw new IllegalArgumentException("in == null");
    }

    public static Sink sink(Socket socket) throws IOException {
        if (socket != null) {
            if (socket.getOutputStream() != null) {
                AsyncTimeout timeout = timeout(socket);
                return timeout.sink(sink(socket.getOutputStream(), timeout));
            }
            throw new IOException("socket's output stream == null");
        }
        throw new IllegalArgumentException("socket == null");
    }

    public static Source source(File file) throws FileNotFoundException {
        if (file != null) {
            return source(new FileInputStream(file));
        }
        throw new IllegalArgumentException("file == null");
    }

    @IgnoreJRERequirement
    public static Source source(Path path, OpenOption... openOptionArr) throws IOException {
        if (path != null) {
            return source(Files.newInputStream(path, openOptionArr));
        }
        throw new IllegalArgumentException("path == null");
    }

    public static Source source(Socket socket) throws IOException {
        if (socket != null) {
            if (socket.getInputStream() != null) {
                AsyncTimeout timeout = timeout(socket);
                return timeout.source(source(socket.getInputStream(), timeout));
            }
            throw new IOException("socket's input stream == null");
        }
        throw new IllegalArgumentException("socket == null");
    }

    public static Sink sink(File file) throws FileNotFoundException {
        if (file != null) {
            return sink(new FileOutputStream(file));
        }
        throw new IllegalArgumentException("file == null");
    }

    @IgnoreJRERequirement
    public static Sink sink(Path path, OpenOption... openOptionArr) throws IOException {
        if (path != null) {
            return sink(Files.newOutputStream(path, openOptionArr));
        }
        throw new IllegalArgumentException("path == null");
    }
}

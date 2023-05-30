package com.laser.open.nfc.model.http;

import com.facebook.react.animated.InterpolationAnimatedNode;
import com.google.common.net.HttpHeaders;
import com.jingdong.common.utils.LangUtils;
import java.io.EOFException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.concurrent.TimeUnit;
import okhttp3.Connection;
import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.internal.platform.Platform;
import okio.Buffer;
import okio.BufferedSource;
import okio.GzipSource;

/* loaded from: classes12.dex */
public final class HttpLoggingInterceptor implements Interceptor {
    private static final Charset UTF8 = Charset.forName("UTF-8");
    private volatile Level level;
    private final Logger logger;

    /* loaded from: classes12.dex */
    public enum Level {
        NONE,
        BASIC,
        HEADERS,
        BODY
    }

    /* loaded from: classes12.dex */
    public interface Logger {
        public static final Logger DEFAULT = new a();

        /* loaded from: classes12.dex */
        static class a implements Logger {
            a() {
            }

            @Override // com.laser.open.nfc.model.http.HttpLoggingInterceptor.Logger
            public void log(String str) {
                Platform.get().log(4, str, null);
            }
        }

        void log(String str);
    }

    public HttpLoggingInterceptor() {
        this(Logger.DEFAULT);
    }

    private boolean bodyHasUnknownEncoding(Headers headers) {
        String str = headers.get("Content-Encoding");
        return (str == null || str.equalsIgnoreCase(InterpolationAnimatedNode.EXTRAPOLATE_TYPE_IDENTITY) || str.equalsIgnoreCase("gzip")) ? false : true;
    }

    static boolean isPlaintext(Buffer buffer) {
        try {
            Buffer buffer2 = new Buffer();
            buffer.copyTo(buffer2, 0L, buffer.size() < 64 ? buffer.size() : 64L);
            for (int i2 = 0; i2 < 16; i2++) {
                if (buffer2.exhausted()) {
                    return true;
                }
                int readUtf8CodePoint = buffer2.readUtf8CodePoint();
                if (Character.isISOControl(readUtf8CodePoint) && !Character.isWhitespace(readUtf8CodePoint)) {
                    return false;
                }
            }
            return true;
        } catch (EOFException unused) {
            return false;
        }
    }

    public Level getLevel() {
        return this.level;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v29, types: [java.lang.Long] */
    @Override // okhttp3.Interceptor
    public Response intercept(Interceptor.Chain chain) throws IOException {
        boolean z;
        long j2;
        char c2;
        String sb;
        boolean z2;
        Level level = this.level;
        Request request = chain.request();
        if (level == Level.NONE) {
            return chain.proceed(request);
        }
        boolean z3 = level == Level.BODY;
        boolean z4 = z3 || level == Level.HEADERS;
        RequestBody body = request.body();
        boolean z5 = body != null;
        Connection connection = chain.connection();
        StringBuilder sb2 = new StringBuilder();
        sb2.append("--> ");
        sb2.append(request.method());
        sb2.append(' ');
        sb2.append(request.url());
        sb2.append(connection != null ? LangUtils.SINGLE_SPACE + connection.protocol() : "");
        String sb3 = sb2.toString();
        if (!z4 && z5) {
            sb3 = sb3 + " (" + body.contentLength() + "-byte body)";
        }
        this.logger.log(sb3);
        if (z4) {
            if (z5) {
                if (body.contentType() != null) {
                    this.logger.log("Content-Type: " + body.contentType());
                }
                if (body.contentLength() != -1) {
                    this.logger.log("Content-Length: " + body.contentLength());
                }
            }
            Headers headers = request.headers();
            int size = headers.size();
            int i2 = 0;
            while (i2 < size) {
                String name = headers.name(i2);
                int i3 = size;
                if (HttpHeaders.CONTENT_TYPE.equalsIgnoreCase(name) || HttpHeaders.CONTENT_LENGTH.equalsIgnoreCase(name)) {
                    z2 = z4;
                } else {
                    z2 = z4;
                    this.logger.log(name + ": " + headers.value(i2));
                }
                i2++;
                size = i3;
                z4 = z2;
            }
            z = z4;
            if (z3 && z5) {
                if (bodyHasUnknownEncoding(request.headers())) {
                    this.logger.log("--> END " + request.method() + " (encoded body omitted)");
                } else {
                    Buffer buffer = new Buffer();
                    body.writeTo(buffer);
                    Charset charset = UTF8;
                    MediaType contentType = body.contentType();
                    if (contentType != null) {
                        charset = contentType.charset(charset);
                    }
                    this.logger.log("");
                    if (isPlaintext(buffer)) {
                        this.logger.log(buffer.readString(charset));
                        this.logger.log("--> END " + request.method() + " (" + body.contentLength() + "-byte body)");
                    } else {
                        this.logger.log("--> END " + request.method() + " (binary " + body.contentLength() + "-byte body omitted)");
                    }
                }
            } else {
                this.logger.log("--> END " + request.method());
            }
        } else {
            z = z4;
        }
        long nanoTime = System.nanoTime();
        try {
            Response proceed = chain.proceed(request);
            long millis = TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - nanoTime);
            ResponseBody body2 = proceed.body();
            long contentLength = body2.contentLength();
            String str = contentLength != -1 ? contentLength + "-byte" : "unknown-length";
            Logger logger = this.logger;
            StringBuilder sb4 = new StringBuilder();
            sb4.append("<-- ");
            sb4.append(proceed.code());
            if (proceed.message().isEmpty()) {
                j2 = contentLength;
                sb = "";
                c2 = ' ';
            } else {
                StringBuilder sb5 = new StringBuilder();
                j2 = contentLength;
                c2 = ' ';
                sb5.append(' ');
                sb5.append(proceed.message());
                sb = sb5.toString();
            }
            sb4.append(sb);
            sb4.append(c2);
            sb4.append(proceed.request().url());
            sb4.append(" (");
            sb4.append(millis);
            sb4.append("ms");
            sb4.append(z ? "" : ", " + str + " body");
            sb4.append(')');
            logger.log(sb4.toString());
            if (z) {
                Headers headers2 = proceed.headers();
                int size2 = headers2.size();
                for (int i4 = 0; i4 < size2; i4++) {
                    this.logger.log(headers2.name(i4) + ": " + headers2.value(i4));
                }
                if (z3 && okhttp3.internal.http.HttpHeaders.hasBody(proceed)) {
                    if (bodyHasUnknownEncoding(proceed.headers())) {
                        this.logger.log("<-- END HTTP (encoded body omitted)");
                    } else {
                        BufferedSource source = body2.source();
                        source.request(Long.MAX_VALUE);
                        Buffer buffer2 = source.buffer();
                        GzipSource gzipSource = null;
                        if ("gzip".equalsIgnoreCase(headers2.get("Content-Encoding"))) {
                            ?? valueOf = Long.valueOf(buffer2.size());
                            try {
                                GzipSource gzipSource2 = new GzipSource(buffer2.clone());
                                try {
                                    buffer2 = new Buffer();
                                    buffer2.writeAll(gzipSource2);
                                    gzipSource2.close();
                                    gzipSource = valueOf;
                                } catch (Throwable th) {
                                    th = th;
                                    gzipSource = gzipSource2;
                                    if (gzipSource != null) {
                                        gzipSource.close();
                                    }
                                    throw th;
                                }
                            } catch (Throwable th2) {
                                th = th2;
                            }
                        }
                        Charset charset2 = UTF8;
                        MediaType contentType2 = body2.contentType();
                        if (contentType2 != null) {
                            charset2 = contentType2.charset(charset2);
                        }
                        if (!isPlaintext(buffer2)) {
                            this.logger.log("");
                            this.logger.log("<-- END HTTP (binary " + buffer2.size() + "-byte body omitted)");
                            return proceed;
                        }
                        if (j2 != 0) {
                            this.logger.log("");
                            this.logger.log(buffer2.clone().readString(charset2));
                        }
                        if (gzipSource != null) {
                            this.logger.log("<-- END HTTP (" + buffer2.size() + "-byte, " + gzipSource + "-gzipped-byte body)");
                        } else {
                            this.logger.log("<-- END HTTP (" + buffer2.size() + "-byte body)");
                        }
                    }
                } else {
                    this.logger.log("<-- END HTTP");
                }
            }
            return proceed;
        } catch (Exception e2) {
            e2.printStackTrace();
            this.logger.log("<-- HTTP FAILED: " + e2);
            throw e2;
        }
    }

    public HttpLoggingInterceptor setLevel(Level level) {
        if (level != null) {
            this.level = level;
            return this;
        }
        throw new NullPointerException("level == null. Use Level.NONE instead.");
    }

    public HttpLoggingInterceptor(Logger logger) {
        this.level = Level.NONE;
        this.logger = logger;
    }
}

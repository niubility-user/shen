package com.meizu.cloud.pushsdk.e.h;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.logging.Logger;

/* loaded from: classes14.dex */
public final class g {

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes14.dex */
    public class a implements l {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ n f15847g;

        /* renamed from: h  reason: collision with root package name */
        final /* synthetic */ OutputStream f15848h;

        a(n nVar, OutputStream outputStream) {
            this.f15847g = nVar;
            this.f15848h = outputStream;
        }

        @Override // com.meizu.cloud.pushsdk.e.h.l
        public void c(com.meizu.cloud.pushsdk.e.h.b bVar, long j2) throws IOException {
            o.a(bVar.f15840h, 0L, j2);
            while (j2 > 0) {
                this.f15847g.a();
                j jVar = bVar.f15839g;
                int min = (int) Math.min(j2, jVar.f15858c - jVar.b);
                this.f15848h.write(jVar.a, jVar.b, min);
                int i2 = jVar.b + min;
                jVar.b = i2;
                long j3 = min;
                j2 -= j3;
                bVar.f15840h -= j3;
                if (i2 == jVar.f15858c) {
                    bVar.f15839g = jVar.e();
                    k.b(jVar);
                }
            }
        }

        @Override // com.meizu.cloud.pushsdk.e.h.l, java.lang.AutoCloseable
        public void close() throws IOException {
            this.f15848h.close();
        }

        @Override // com.meizu.cloud.pushsdk.e.h.l, java.io.Flushable
        public void flush() throws IOException {
            this.f15848h.flush();
        }

        public String toString() {
            return "sink(" + this.f15848h + ")";
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes14.dex */
    public class b implements m {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ n f15849g;

        /* renamed from: h  reason: collision with root package name */
        final /* synthetic */ InputStream f15850h;

        b(n nVar, InputStream inputStream) {
            this.f15849g = nVar;
            this.f15850h = inputStream;
        }

        @Override // com.meizu.cloud.pushsdk.e.h.m, java.io.Closeable, java.lang.AutoCloseable, com.meizu.cloud.pushsdk.e.h.l
        public void close() throws IOException {
            this.f15850h.close();
        }

        @Override // com.meizu.cloud.pushsdk.e.h.m
        public long d(com.meizu.cloud.pushsdk.e.h.b bVar, long j2) throws IOException {
            int i2 = (j2 > 0L ? 1 : (j2 == 0L ? 0 : -1));
            if (i2 < 0) {
                throw new IllegalArgumentException("byteCount < 0: " + j2);
            } else if (i2 == 0) {
                return 0L;
            } else {
                this.f15849g.a();
                j g2 = bVar.g(1);
                int read = this.f15850h.read(g2.a, g2.f15858c, (int) Math.min(j2, 2048 - g2.f15858c));
                if (read == -1) {
                    return -1L;
                }
                g2.f15858c += read;
                long j3 = read;
                bVar.f15840h += j3;
                return j3;
            }
        }

        public String toString() {
            return "source(" + this.f15850h + ")";
        }
    }

    static {
        Logger.getLogger(g.class.getName());
    }

    private g() {
    }

    public static c a(l lVar) {
        if (lVar != null) {
            return new h(lVar);
        }
        throw new IllegalArgumentException("sink == null");
    }

    public static d b(m mVar) {
        if (mVar != null) {
            return new i(mVar);
        }
        throw new IllegalArgumentException("source == null");
    }

    public static l c(OutputStream outputStream) {
        return d(outputStream, new n());
    }

    private static l d(OutputStream outputStream, n nVar) {
        if (outputStream != null) {
            if (nVar != null) {
                return new a(nVar, outputStream);
            }
            throw new IllegalArgumentException("timeout == null");
        }
        throw new IllegalArgumentException("out == null");
    }

    public static m e(File file) throws FileNotFoundException {
        if (file != null) {
            return f(new FileInputStream(file));
        }
        throw new IllegalArgumentException("file == null");
    }

    public static m f(InputStream inputStream) {
        return g(inputStream, new n());
    }

    private static m g(InputStream inputStream, n nVar) {
        if (inputStream != null) {
            if (nVar != null) {
                return new b(nVar, inputStream);
            }
            throw new IllegalArgumentException("timeout == null");
        }
        throw new IllegalArgumentException("in == null");
    }
}

package com.meizu.cloud.pushsdk.e.h;

import com.jingdong.jdsdk.constant.JshopConst;
import java.io.IOException;
import java.io.InputStream;

/* loaded from: classes14.dex */
final class i implements d {

    /* renamed from: g  reason: collision with root package name */
    private final b f15854g;

    /* renamed from: h  reason: collision with root package name */
    private final m f15855h;

    /* renamed from: i  reason: collision with root package name */
    private boolean f15856i;

    /* loaded from: classes14.dex */
    class a extends InputStream {
        a() {
        }

        @Override // java.io.InputStream
        public int available() throws IOException {
            if (i.this.f15856i) {
                throw new IOException(JshopConst.JSKEY_SHOP_CLOSED);
            }
            return (int) Math.min(i.this.f15854g.f15840h, 2147483647L);
        }

        @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            i.this.close();
        }

        @Override // java.io.InputStream
        public int read() throws IOException {
            if (i.this.f15856i) {
                throw new IOException(JshopConst.JSKEY_SHOP_CLOSED);
            }
            if (i.this.f15854g.f15840h == 0 && i.this.f15855h.d(i.this.f15854g, 2048L) == -1) {
                return -1;
            }
            return i.this.f15854g.y() & 255;
        }

        @Override // java.io.InputStream
        public int read(byte[] bArr, int i2, int i3) throws IOException {
            if (i.this.f15856i) {
                throw new IOException(JshopConst.JSKEY_SHOP_CLOSED);
            }
            o.a(bArr.length, i2, i3);
            if (i.this.f15854g.f15840h == 0 && i.this.f15855h.d(i.this.f15854g, 2048L) == -1) {
                return -1;
            }
            return i.this.f15854g.i(bArr, i2, i3);
        }

        public String toString() {
            return i.this + ".inputStream()";
        }
    }

    public i(m mVar) {
        this(mVar, new b());
    }

    public i(m mVar, b bVar) {
        if (mVar == null) {
            throw new IllegalArgumentException("source == null");
        }
        this.f15854g = bVar;
        this.f15855h = mVar;
    }

    @Override // com.meizu.cloud.pushsdk.e.h.d
    public String a() throws IOException {
        this.f15854g.b(this.f15855h);
        return this.f15854g.a();
    }

    @Override // com.meizu.cloud.pushsdk.e.h.d
    public byte[] b() throws IOException {
        this.f15854g.b(this.f15855h);
        return this.f15854g.b();
    }

    @Override // com.meizu.cloud.pushsdk.e.h.m, java.io.Closeable, java.lang.AutoCloseable, com.meizu.cloud.pushsdk.e.h.l
    public void close() throws IOException {
        if (this.f15856i) {
            return;
        }
        this.f15856i = true;
        this.f15855h.close();
        this.f15854g.u();
    }

    @Override // com.meizu.cloud.pushsdk.e.h.m
    public long d(b bVar, long j2) throws IOException {
        if (bVar != null) {
            if (j2 < 0) {
                throw new IllegalArgumentException("byteCount < 0: " + j2);
            } else if (this.f15856i) {
                throw new IllegalStateException(JshopConst.JSKEY_SHOP_CLOSED);
            } else {
                b bVar2 = this.f15854g;
                if (bVar2.f15840h == 0 && this.f15855h.d(bVar2, 2048L) == -1) {
                    return -1L;
                }
                return this.f15854g.d(bVar, Math.min(j2, this.f15854g.f15840h));
            }
        }
        throw new IllegalArgumentException("sink == null");
    }

    @Override // com.meizu.cloud.pushsdk.e.h.d
    public InputStream d() {
        return new a();
    }

    public String toString() {
        return "buffer(" + this.f15855h + ")";
    }
}

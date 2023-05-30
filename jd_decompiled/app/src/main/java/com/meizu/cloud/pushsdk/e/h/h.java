package com.meizu.cloud.pushsdk.e.h;

import com.jingdong.jdsdk.constant.JshopConst;
import java.io.IOException;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes14.dex */
public final class h implements c {

    /* renamed from: g */
    private final b f15851g;

    /* renamed from: h */
    private final l f15852h;

    /* renamed from: i */
    private boolean f15853i;

    public h(l lVar) {
        this(lVar, new b());
    }

    public h(l lVar, b bVar) {
        if (lVar == null) {
            throw new IllegalArgumentException("sink == null");
        }
        this.f15851g = bVar;
        this.f15852h = lVar;
    }

    @Override // com.meizu.cloud.pushsdk.e.h.c
    public c a(long j2) throws IOException {
        if (this.f15853i) {
            throw new IllegalStateException(JshopConst.JSKEY_SHOP_CLOSED);
        }
        this.f15851g.s(j2);
        f();
        return this;
    }

    @Override // com.meizu.cloud.pushsdk.e.h.c
    public c a(String str) throws IOException {
        if (this.f15853i) {
            throw new IllegalStateException(JshopConst.JSKEY_SHOP_CLOSED);
        }
        this.f15851g.l(str);
        f();
        return this;
    }

    @Override // com.meizu.cloud.pushsdk.e.h.c
    public c a(byte[] bArr) throws IOException {
        if (this.f15853i) {
            throw new IllegalStateException(JshopConst.JSKEY_SHOP_CLOSED);
        }
        this.f15851g.p(bArr);
        f();
        return this;
    }

    @Override // com.meizu.cloud.pushsdk.e.h.c
    public c a(byte[] bArr, int i2, int i3) throws IOException {
        if (this.f15853i) {
            throw new IllegalStateException(JshopConst.JSKEY_SHOP_CLOSED);
        }
        this.f15851g.q(bArr, i2, i3);
        f();
        return this;
    }

    @Override // com.meizu.cloud.pushsdk.e.h.c
    public long b(m mVar) throws IOException {
        if (mVar == null) {
            throw new IllegalArgumentException("source == null");
        }
        long j2 = 0;
        while (true) {
            long d = mVar.d(this.f15851g, 2048L);
            if (d == -1) {
                return j2;
            }
            j2 += d;
            f();
        }
    }

    @Override // com.meizu.cloud.pushsdk.e.h.c
    public b c() {
        return this.f15851g;
    }

    @Override // com.meizu.cloud.pushsdk.e.h.l
    public void c(b bVar, long j2) throws IOException {
        if (this.f15853i) {
            throw new IllegalStateException(JshopConst.JSKEY_SHOP_CLOSED);
        }
        this.f15851g.c(bVar, j2);
        f();
    }

    @Override // com.meizu.cloud.pushsdk.e.h.l, java.lang.AutoCloseable
    public void close() {
        if (this.f15853i) {
            return;
        }
        try {
            b bVar = this.f15851g;
            long j2 = bVar.f15840h;
            if (j2 > 0) {
                this.f15852h.c(bVar, j2);
            }
            th = null;
        } catch (Throwable th) {
            th = th;
        }
        try {
            this.f15852h.close();
        } catch (Throwable th2) {
            if (th == null) {
                th = th2;
            }
        }
        this.f15853i = true;
        if (th == null) {
            return;
        }
        o.b(th);
        throw null;
    }

    @Override // com.meizu.cloud.pushsdk.e.h.c
    public c e(e eVar) throws IOException {
        if (this.f15853i) {
            throw new IllegalStateException(JshopConst.JSKEY_SHOP_CLOSED);
        }
        this.f15851g.k(eVar);
        f();
        return this;
    }

    public c f() throws IOException {
        if (this.f15853i) {
            throw new IllegalStateException(JshopConst.JSKEY_SHOP_CLOSED);
        }
        long w = this.f15851g.w();
        if (w > 0) {
            this.f15852h.c(this.f15851g, w);
        }
        return this;
    }

    @Override // com.meizu.cloud.pushsdk.e.h.l, java.io.Flushable
    public void flush() throws IOException {
        if (this.f15853i) {
            throw new IllegalStateException(JshopConst.JSKEY_SHOP_CLOSED);
        }
        b bVar = this.f15851g;
        long j2 = bVar.f15840h;
        if (j2 > 0) {
            this.f15852h.c(bVar, j2);
        }
        this.f15852h.flush();
    }

    public String toString() {
        return "buffer(" + this.f15852h + ")";
    }
}

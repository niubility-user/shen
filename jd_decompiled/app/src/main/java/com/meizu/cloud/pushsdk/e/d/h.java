package com.meizu.cloud.pushsdk.e.d;

import com.google.common.net.HttpHeaders;
import com.jdpay.net.http.HTTP;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/* loaded from: classes14.dex */
public final class h extends j {

    /* renamed from: e  reason: collision with root package name */
    public static final g f15816e = g.a("multipart/mixed");

    /* renamed from: f  reason: collision with root package name */
    public static final g f15817f;

    /* renamed from: g  reason: collision with root package name */
    private static final byte[] f15818g;

    /* renamed from: h  reason: collision with root package name */
    private static final byte[] f15819h;

    /* renamed from: i  reason: collision with root package name */
    private static final byte[] f15820i;
    private final com.meizu.cloud.pushsdk.e.h.e a;
    private final g b;

    /* renamed from: c  reason: collision with root package name */
    private final List<b> f15821c;
    private long d = -1;

    /* loaded from: classes14.dex */
    public static final class a {
        private final com.meizu.cloud.pushsdk.e.h.e a;
        private g b;

        /* renamed from: c  reason: collision with root package name */
        private final List<b> f15822c;

        public a() {
            this(UUID.randomUUID().toString());
        }

        public a(String str) {
            this.b = h.f15816e;
            this.f15822c = new ArrayList();
            this.a = com.meizu.cloud.pushsdk.e.h.e.b(str);
        }

        public a a(c cVar, j jVar) {
            c(b.b(cVar, jVar));
            return this;
        }

        public a b(g gVar) {
            if (gVar != null) {
                if ("multipart".equals(gVar.c())) {
                    this.b = gVar;
                    return this;
                }
                throw new IllegalArgumentException("multipart != " + gVar);
            }
            throw new NullPointerException("type == null");
        }

        public a c(b bVar) {
            if (bVar != null) {
                this.f15822c.add(bVar);
                return this;
            }
            throw new NullPointerException("part == null");
        }

        public h d() {
            if (this.f15822c.isEmpty()) {
                throw new IllegalStateException("Multipart body must have at least one part.");
            }
            return new h(this.a, this.b, this.f15822c);
        }
    }

    /* loaded from: classes14.dex */
    public static final class b {
        private final c a;
        private final j b;

        private b(c cVar, j jVar) {
            this.a = cVar;
            this.b = jVar;
        }

        public static b b(c cVar, j jVar) {
            if (jVar != null) {
                if (cVar == null || cVar.c(HttpHeaders.CONTENT_TYPE) == null) {
                    if (cVar == null || cVar.c(HttpHeaders.CONTENT_LENGTH) == null) {
                        return new b(cVar, jVar);
                    }
                    throw new IllegalArgumentException("Unexpected header: Content-Length");
                }
                throw new IllegalArgumentException("Unexpected header: Content-Type");
            }
            throw new NullPointerException("body == null");
        }
    }

    static {
        g.a("multipart/alternative");
        g.a("multipart/digest");
        g.a("multipart/parallel");
        f15817f = g.a(HTTP.CONTENT_TYPE_FORM_DATA);
        f15818g = new byte[]{58, 32};
        f15819h = new byte[]{13, 10};
        f15820i = new byte[]{45, 45};
    }

    h(com.meizu.cloud.pushsdk.e.h.e eVar, g gVar, List<b> list) {
        this.a = eVar;
        this.b = g.a(gVar + "; boundary=" + eVar.d());
        this.f15821c = m.d(list);
    }

    private long h(com.meizu.cloud.pushsdk.e.h.c cVar, boolean z) throws IOException {
        com.meizu.cloud.pushsdk.e.h.b bVar;
        com.meizu.cloud.pushsdk.e.h.b bVar2;
        if (z) {
            bVar2 = new com.meizu.cloud.pushsdk.e.h.b();
            bVar = bVar2;
        } else {
            bVar = cVar;
            bVar2 = null;
        }
        int size = this.f15821c.size();
        long j2 = 0;
        for (int i2 = 0; i2 < size; i2++) {
            b bVar3 = this.f15821c.get(i2);
            c cVar2 = bVar3.a;
            j jVar = bVar3.b;
            bVar.a(f15820i);
            bVar.e(this.a);
            bVar.a(f15819h);
            if (cVar2 != null) {
                int h2 = cVar2.h();
                for (int i3 = 0; i3 < h2; i3++) {
                    bVar.a(cVar2.b(i3)).a(f15818g).a(cVar2.g(i3)).a(f15819h);
                }
            }
            g g2 = jVar.g();
            if (g2 != null) {
                bVar.a("Content-Type: ").a(g2.toString()).a(f15819h);
            }
            long a2 = jVar.a();
            if (a2 != -1) {
                bVar.a("Content-Length: ").a(a2).a(f15819h);
            } else if (z) {
                bVar2.u();
                return -1L;
            }
            byte[] bArr = f15819h;
            bVar.a(bArr);
            if (z) {
                j2 += a2;
            } else {
                jVar.f(bVar);
            }
            bVar.a(bArr);
        }
        byte[] bArr2 = f15820i;
        bVar.a(bArr2);
        bVar.e(this.a);
        bVar.a(bArr2);
        bVar.a(f15819h);
        if (z) {
            long A = j2 + bVar2.A();
            bVar2.u();
            return A;
        }
        return j2;
    }

    @Override // com.meizu.cloud.pushsdk.e.d.j
    public long a() throws IOException {
        long j2 = this.d;
        if (j2 != -1) {
            return j2;
        }
        long h2 = h(null, true);
        this.d = h2;
        return h2;
    }

    @Override // com.meizu.cloud.pushsdk.e.d.j
    public void f(com.meizu.cloud.pushsdk.e.h.c cVar) throws IOException {
        h(cVar, false);
    }

    @Override // com.meizu.cloud.pushsdk.e.d.j
    public g g() {
        return this.b;
    }
}

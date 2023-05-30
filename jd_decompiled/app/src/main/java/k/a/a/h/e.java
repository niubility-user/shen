package k.a.a.h;

import java.io.IOException;
import java.nio.charset.Charset;
import k.a.a.e.a.h;
import k.a.a.e.a.k;
import k.a.a.f.n;
import k.a.a.h.d;
import k.a.a.i.f;

/* loaded from: classes11.dex */
public class e extends b<a> {

    /* renamed from: f */
    private char[] f20281f;

    /* renamed from: g */
    private h f20282g;

    /* loaded from: classes11.dex */
    public static class a extends c {
        private String b;

        public a(String str, Charset charset) {
            super(charset);
            this.b = str;
        }
    }

    public e(n nVar, char[] cArr, d.a aVar) {
        super(nVar, aVar);
        this.f20281f = cArr;
    }

    private k.a.a.f.h u(n nVar) {
        if (nVar.a() == null || nVar.a().a() == null || nVar.a().a().size() == 0) {
            return null;
        }
        return nVar.a().a().get(0);
    }

    private k v(Charset charset) throws IOException {
        this.f20282g = f.b(n());
        k.a.a.f.h u = u(n());
        if (u != null) {
            this.f20282g.h(u);
        }
        return new k(this.f20282g, this.f20281f, charset);
    }

    @Override // k.a.a.h.d
    /* renamed from: s */
    public long a(a aVar) {
        return k.a.a.d.c.c(n().a().a());
    }

    @Override // k.a.a.h.d
    /* renamed from: t */
    public void c(a aVar, k.a.a.g.a aVar2) throws IOException {
        try {
            k v = v(aVar.a);
            for (k.a.a.f.h hVar : n().a().a()) {
                if (hVar.j().startsWith("__MACOSX")) {
                    aVar2.l(hVar.m());
                } else {
                    this.f20282g.h(hVar);
                    l(v, hVar, aVar.b, null, aVar2);
                    h();
                }
            }
            if (v != null) {
                v.close();
            }
        } finally {
            h hVar2 = this.f20282g;
            if (hVar2 != null) {
                hVar2.close();
            }
        }
    }
}

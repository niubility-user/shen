package com.meizu.cloud.pushsdk.e.f;

import com.meizu.cloud.pushsdk.e.b.e;
import com.meizu.cloud.pushsdk.e.d.k;

/* loaded from: classes14.dex */
public final class c {
    public static <T> com.meizu.cloud.pushsdk.e.b.c<T> a(com.meizu.cloud.pushsdk.e.b.b bVar) {
        int s = bVar.s();
        return s != 0 ? s != 1 ? s != 2 ? new com.meizu.cloud.pushsdk.e.b.c<>(new com.meizu.cloud.pushsdk.e.c.a()) : d(bVar) : b(bVar) : c(bVar);
    }

    private static <T> com.meizu.cloud.pushsdk.e.b.c<T> b(com.meizu.cloud.pushsdk.e.b.b bVar) {
        try {
            k a = a.a(bVar);
            if (a == null) {
                com.meizu.cloud.pushsdk.e.c.a aVar = new com.meizu.cloud.pushsdk.e.c.a();
                com.meizu.cloud.pushsdk.e.i.b.d(aVar);
                return new com.meizu.cloud.pushsdk.e.b.c<>(aVar);
            } else if (a.b() < 400) {
                com.meizu.cloud.pushsdk.e.b.c<T> cVar = new com.meizu.cloud.pushsdk.e.b.c<>("success");
                cVar.d(a);
                return cVar;
            } else {
                com.meizu.cloud.pushsdk.e.c.a aVar2 = new com.meizu.cloud.pushsdk.e.c.a(a);
                com.meizu.cloud.pushsdk.e.i.b.e(aVar2, bVar, a.b());
                com.meizu.cloud.pushsdk.e.b.c<T> cVar2 = new com.meizu.cloud.pushsdk.e.b.c<>(aVar2);
                cVar2.d(a);
                return cVar2;
            }
        } catch (com.meizu.cloud.pushsdk.e.c.a e2) {
            com.meizu.cloud.pushsdk.e.c.a aVar3 = new com.meizu.cloud.pushsdk.e.c.a(e2);
            com.meizu.cloud.pushsdk.e.i.b.d(aVar3);
            return new com.meizu.cloud.pushsdk.e.b.c<>(aVar3);
        } catch (Exception e3) {
            return new com.meizu.cloud.pushsdk.e.b.c<>(com.meizu.cloud.pushsdk.e.i.b.f(e3));
        }
    }

    private static <T> com.meizu.cloud.pushsdk.e.b.c<T> c(com.meizu.cloud.pushsdk.e.b.b bVar) {
        try {
            try {
                k c2 = a.c(bVar);
                if (c2 == null) {
                    com.meizu.cloud.pushsdk.e.c.a aVar = new com.meizu.cloud.pushsdk.e.c.a();
                    com.meizu.cloud.pushsdk.e.i.b.d(aVar);
                    com.meizu.cloud.pushsdk.e.b.c<T> cVar = new com.meizu.cloud.pushsdk.e.b.c<>(aVar);
                    com.meizu.cloud.pushsdk.e.i.a.a(c2, bVar);
                    return cVar;
                } else if (bVar.t() == e.OK_HTTP_RESPONSE) {
                    com.meizu.cloud.pushsdk.e.b.c<T> cVar2 = new com.meizu.cloud.pushsdk.e.b.c<>(c2);
                    cVar2.d(c2);
                    com.meizu.cloud.pushsdk.e.i.a.a(c2, bVar);
                    return cVar2;
                } else if (c2.b() < 400) {
                    com.meizu.cloud.pushsdk.e.b.c<T> c3 = bVar.c(c2);
                    c3.d(c2);
                    com.meizu.cloud.pushsdk.e.i.a.a(c2, bVar);
                    return c3;
                } else {
                    com.meizu.cloud.pushsdk.e.c.a aVar2 = new com.meizu.cloud.pushsdk.e.c.a(c2);
                    com.meizu.cloud.pushsdk.e.i.b.e(aVar2, bVar, c2.b());
                    com.meizu.cloud.pushsdk.e.b.c<T> cVar3 = new com.meizu.cloud.pushsdk.e.b.c<>(aVar2);
                    cVar3.d(c2);
                    com.meizu.cloud.pushsdk.e.i.a.a(c2, bVar);
                    return cVar3;
                }
            } catch (com.meizu.cloud.pushsdk.e.c.a e2) {
                com.meizu.cloud.pushsdk.e.c.a aVar3 = new com.meizu.cloud.pushsdk.e.c.a(e2);
                com.meizu.cloud.pushsdk.e.i.b.d(aVar3);
                com.meizu.cloud.pushsdk.e.b.c<T> cVar4 = new com.meizu.cloud.pushsdk.e.b.c<>(aVar3);
                com.meizu.cloud.pushsdk.e.i.a.a(null, bVar);
                return cVar4;
            } catch (Exception e3) {
                com.meizu.cloud.pushsdk.e.b.c<T> cVar5 = new com.meizu.cloud.pushsdk.e.b.c<>(com.meizu.cloud.pushsdk.e.i.b.f(e3));
                com.meizu.cloud.pushsdk.e.i.a.a(null, bVar);
                return cVar5;
            }
        } catch (Throwable th) {
            com.meizu.cloud.pushsdk.e.i.a.a(null, bVar);
            throw th;
        }
    }

    private static <T> com.meizu.cloud.pushsdk.e.b.c<T> d(com.meizu.cloud.pushsdk.e.b.b bVar) {
        try {
            try {
                k d = a.d(bVar);
                if (d == null) {
                    com.meizu.cloud.pushsdk.e.c.a aVar = new com.meizu.cloud.pushsdk.e.c.a();
                    com.meizu.cloud.pushsdk.e.i.b.d(aVar);
                    com.meizu.cloud.pushsdk.e.b.c<T> cVar = new com.meizu.cloud.pushsdk.e.b.c<>(aVar);
                    com.meizu.cloud.pushsdk.e.i.a.a(d, bVar);
                    return cVar;
                } else if (bVar.t() == e.OK_HTTP_RESPONSE) {
                    com.meizu.cloud.pushsdk.e.b.c<T> cVar2 = new com.meizu.cloud.pushsdk.e.b.c<>(d);
                    cVar2.d(d);
                    com.meizu.cloud.pushsdk.e.i.a.a(d, bVar);
                    return cVar2;
                } else if (d.b() < 400) {
                    com.meizu.cloud.pushsdk.e.b.c<T> c2 = bVar.c(d);
                    c2.d(d);
                    com.meizu.cloud.pushsdk.e.i.a.a(d, bVar);
                    return c2;
                } else {
                    com.meizu.cloud.pushsdk.e.c.a aVar2 = new com.meizu.cloud.pushsdk.e.c.a(d);
                    com.meizu.cloud.pushsdk.e.i.b.e(aVar2, bVar, d.b());
                    com.meizu.cloud.pushsdk.e.b.c<T> cVar3 = new com.meizu.cloud.pushsdk.e.b.c<>(aVar2);
                    cVar3.d(d);
                    com.meizu.cloud.pushsdk.e.i.a.a(d, bVar);
                    return cVar3;
                }
            } catch (com.meizu.cloud.pushsdk.e.c.a e2) {
                com.meizu.cloud.pushsdk.e.i.b.d(e2);
                com.meizu.cloud.pushsdk.e.b.c<T> cVar4 = new com.meizu.cloud.pushsdk.e.b.c<>(e2);
                com.meizu.cloud.pushsdk.e.i.a.a(null, bVar);
                return cVar4;
            } catch (Exception e3) {
                com.meizu.cloud.pushsdk.e.b.c<T> cVar5 = new com.meizu.cloud.pushsdk.e.b.c<>(com.meizu.cloud.pushsdk.e.i.b.f(e3));
                com.meizu.cloud.pushsdk.e.i.a.a(null, bVar);
                return cVar5;
            }
        } catch (Throwable th) {
            com.meizu.cloud.pushsdk.e.i.a.a(null, bVar);
            throw th;
        }
    }
}

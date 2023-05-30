package com.meizu.cloud.pushsdk.e.d;

import com.jd.jdcache.util.UrlHelper;
import com.meizu.cloud.pushsdk.e.d.c;

/* loaded from: classes14.dex */
public class i {
    private final f a;
    private final String b;

    /* renamed from: c  reason: collision with root package name */
    private final c f15823c;
    private final j d;

    /* renamed from: e  reason: collision with root package name */
    private final Object f15824e;

    /* loaded from: classes14.dex */
    public static class b {
        private f a;
        private String b = "GET";

        /* renamed from: c  reason: collision with root package name */
        private c.b f15825c = new c.b();
        private j d;

        /* renamed from: e  reason: collision with root package name */
        private Object f15826e;

        public b b(c cVar) {
            this.f15825c = cVar.f();
            return this;
        }

        public b c(f fVar) {
            if (fVar != null) {
                this.a = fVar;
                return this;
            }
            throw new IllegalArgumentException("url == null");
        }

        public b d(j jVar) {
            f(UrlHelper.METHOD_DELETE, jVar);
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:12:0x0045  */
        /* JADX WARN: Removed duplicated region for block: B:14:0x0049  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public com.meizu.cloud.pushsdk.e.d.i.b e(java.lang.String r7) {
            /*
                r6 = this;
                if (r7 == 0) goto L60
                r1 = 1
                r2 = 0
                r4 = 0
                r5 = 3
                java.lang.String r3 = "ws:"
                r0 = r7
                boolean r0 = r0.regionMatches(r1, r2, r3, r4, r5)
                if (r0 == 0) goto L26
                java.lang.StringBuilder r0 = new java.lang.StringBuilder
                r0.<init>()
                java.lang.String r1 = "http:"
                r0.append(r1)
                r1 = 3
            L1a:
                java.lang.String r7 = r7.substring(r1)
                r0.append(r7)
                java.lang.String r7 = r0.toString()
                goto L3f
            L26:
                r1 = 1
                r2 = 0
                r4 = 0
                r5 = 4
                java.lang.String r3 = "wss:"
                r0 = r7
                boolean r0 = r0.regionMatches(r1, r2, r3, r4, r5)
                if (r0 == 0) goto L3f
                java.lang.StringBuilder r0 = new java.lang.StringBuilder
                r0.<init>()
                java.lang.String r1 = "https:"
                r0.append(r1)
                r1 = 4
                goto L1a
            L3f:
                com.meizu.cloud.pushsdk.e.d.f r0 = com.meizu.cloud.pushsdk.e.d.f.p(r7)
                if (r0 == 0) goto L49
                r6.c(r0)
                return r6
            L49:
                java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
                java.lang.StringBuilder r1 = new java.lang.StringBuilder
                r1.<init>()
                java.lang.String r2 = "unexpected url: "
                r1.append(r2)
                r1.append(r7)
                java.lang.String r7 = r1.toString()
                r0.<init>(r7)
                throw r0
            L60:
                java.lang.IllegalArgumentException r7 = new java.lang.IllegalArgumentException
                java.lang.String r0 = "url == null"
                r7.<init>(r0)
                goto L69
            L68:
                throw r7
            L69:
                goto L68
            */
            throw new UnsupportedOperationException("Method not decompiled: com.meizu.cloud.pushsdk.e.d.i.b.e(java.lang.String):com.meizu.cloud.pushsdk.e.d.i$b");
        }

        public b f(String str, j jVar) {
            if (str == null || str.length() == 0) {
                throw new IllegalArgumentException("method == null || method.length() == 0");
            }
            if (jVar != null && !d.a(str)) {
                throw new IllegalArgumentException("method " + str + " must not have a request body.");
            } else if (jVar != null || !d.b(str)) {
                this.b = str;
                this.d = jVar;
                return this;
            } else {
                throw new IllegalArgumentException("method " + str + " must have a request body.");
            }
        }

        public b g(String str, String str2) {
            this.f15825c.a(str, str2);
            return this;
        }

        public i h() {
            if (this.a != null) {
                return new i(this);
            }
            throw new IllegalStateException("url == null");
        }

        public b i() {
            f("GET", null);
            return this;
        }

        public b j(j jVar) {
            f(UrlHelper.METHOD_PATCH, jVar);
            return this;
        }

        public b m() {
            f(UrlHelper.METHOD_HEAD, null);
            return this;
        }

        public b n(j jVar) {
            f("POST", jVar);
            return this;
        }

        public b o(j jVar) {
            f(UrlHelper.METHOD_PUT, jVar);
            return this;
        }
    }

    private i(b bVar) {
        this.a = bVar.a;
        this.b = bVar.b;
        this.f15823c = bVar.f15825c.b();
        this.d = bVar.d;
        this.f15824e = bVar.f15826e != null ? bVar.f15826e : this;
    }

    public j a() {
        return this.d;
    }

    public String b(String str) {
        return this.f15823c.c(str);
    }

    public int c() {
        if ("POST".equals(e())) {
            return 1;
        }
        if (UrlHelper.METHOD_PUT.equals(e())) {
            return 2;
        }
        if (UrlHelper.METHOD_DELETE.equals(e())) {
            return 3;
        }
        if (UrlHelper.METHOD_HEAD.equals(e())) {
            return 4;
        }
        return UrlHelper.METHOD_PATCH.equals(e()) ? 5 : 0;
    }

    public c d() {
        return this.f15823c;
    }

    public String e() {
        return this.b;
    }

    public f f() {
        return this.a;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Request{method=");
        sb.append(this.b);
        sb.append(", url=");
        sb.append(this.a);
        sb.append(", tag=");
        Object obj = this.f15824e;
        if (obj == this) {
            obj = null;
        }
        sb.append(obj);
        sb.append('}');
        return sb.toString();
    }
}

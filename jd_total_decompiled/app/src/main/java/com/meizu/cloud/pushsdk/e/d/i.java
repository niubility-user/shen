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
        */
        public b e(String str) {
            StringBuilder sb;
            int i2;
            f p;
            if (str == null) {
                throw new IllegalArgumentException("url == null");
            }
            if (!str.regionMatches(true, 0, "ws:", 0, 3)) {
                if (str.regionMatches(true, 0, "wss:", 0, 4)) {
                    sb = new StringBuilder();
                    sb.append("https:");
                    i2 = 4;
                }
                p = f.p(str);
                if (p == null) {
                    c(p);
                    return this;
                }
                throw new IllegalArgumentException("unexpected url: " + str);
            }
            sb = new StringBuilder();
            sb.append("http:");
            i2 = 3;
            sb.append(str.substring(i2));
            str = sb.toString();
            p = f.p(str);
            if (p == null) {
            }
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

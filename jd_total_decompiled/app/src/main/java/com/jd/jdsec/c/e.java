package com.jd.jdsec.c;

/* loaded from: classes13.dex */
public class e {
    private final String a;
    private final String b;

    /* renamed from: c  reason: collision with root package name */
    private final String f2738c;
    private final com.jd.jdsec.c.b d;

    /* renamed from: e  reason: collision with root package name */
    public f f2739e;

    /* renamed from: f  reason: collision with root package name */
    public com.jd.jdsec.c.a f2740f;

    /* loaded from: classes13.dex */
    public static class a {
        private String a;
        private String b;

        /* renamed from: c  reason: collision with root package name */
        private String f2741c;
        private com.jd.jdsec.c.b d;

        /* renamed from: e  reason: collision with root package name */
        private f f2742e;

        /* renamed from: f  reason: collision with root package name */
        private com.jd.jdsec.c.a f2743f;

        public e g() {
            return new e(this);
        }

        public a h(String str) {
            this.a = str;
            return this;
        }

        public a i(com.jd.jdsec.c.a aVar) {
            this.f2743f = aVar;
            return this;
        }

        public a j(com.jd.jdsec.c.b bVar) {
            this.d = bVar;
            return this;
        }

        public a k(String str) {
            this.f2741c = str;
            return this;
        }

        public a l(f fVar) {
            this.f2742e = fVar;
            return this;
        }

        public a m(String str) {
            this.b = str;
            return this;
        }
    }

    public String a() {
        return this.a;
    }

    public com.jd.jdsec.c.a b() {
        return this.f2740f;
    }

    public com.jd.jdsec.c.b c() {
        return this.d;
    }

    public String d() {
        return this.f2738c;
    }

    public String e() {
        return this.b;
    }

    public f f() {
        return this.f2739e;
    }

    private e(a aVar) {
        this.a = aVar.a;
        this.b = aVar.b;
        this.f2738c = aVar.f2741c;
        this.d = aVar.d;
        this.f2739e = aVar.f2742e;
        this.f2740f = aVar.f2743f;
    }
}

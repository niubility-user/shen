package com.meizu.cloud.pushsdk.e.d;

import com.meizu.cloud.pushsdk.e.d.c;

/* loaded from: classes14.dex */
public class k {
    private final i a;
    private final int b;

    /* renamed from: c  reason: collision with root package name */
    private final String f15828c;
    private final l d;

    /* loaded from: classes14.dex */
    public static class b {
        private i a;

        /* renamed from: c  reason: collision with root package name */
        private String f15829c;

        /* renamed from: e  reason: collision with root package name */
        private l f15830e;

        /* renamed from: f  reason: collision with root package name */
        private k f15831f;

        /* renamed from: g  reason: collision with root package name */
        private k f15832g;

        /* renamed from: h  reason: collision with root package name */
        private k f15833h;
        private int b = -1;
        private c.b d = new c.b();

        public b b(int i2) {
            this.b = i2;
            return this;
        }

        public b c(c cVar) {
            this.d = cVar.f();
            return this;
        }

        public b d(i iVar) {
            this.a = iVar;
            return this;
        }

        public b e(l lVar) {
            this.f15830e = lVar;
            return this;
        }

        public b f(String str) {
            this.f15829c = str;
            return this;
        }

        public k g() {
            if (this.a != null) {
                if (this.b >= 0) {
                    return new k(this);
                }
                throw new IllegalStateException("code < 0: " + this.b);
            }
            throw new IllegalStateException("request == null");
        }
    }

    private k(b bVar) {
        this.a = bVar.a;
        this.b = bVar.b;
        this.f15828c = bVar.f15829c;
        bVar.d.b();
        this.d = bVar.f15830e;
        k unused = bVar.f15831f;
        k unused2 = bVar.f15832g;
        k unused3 = bVar.f15833h;
    }

    public l a() {
        return this.d;
    }

    public int b() {
        return this.b;
    }

    public String toString() {
        return "Response{protocol=, code=" + this.b + ", message=" + this.f15828c + ", url=" + this.a.f() + '}';
    }
}

package com.jingdong.c.b;

import com.jingdong.c.b.f;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes10.dex */
public abstract class g implements f.a {
    private int a = 0;
    private final b b;

    /* loaded from: classes10.dex */
    public static class a {
        static com.jingdong.c.b.d.b a = new com.jingdong.c.b.d.b();
        static com.jingdong.c.b.d.a b = new com.jingdong.c.b.d.a();

        /* renamed from: c */
        static com.jingdong.c.b.d.c f12301c = new com.jingdong.c.b.d.c();

        /* renamed from: com.jingdong.c.b.g$a$a */
        /* loaded from: classes10.dex */
        public static class C0404a extends g {

            /* renamed from: c */
            private List<f> f12302c;

            C0404a(com.jingdong.c.b.b bVar) {
                super(bVar);
            }

            @Override // com.jingdong.c.b.g
            protected final List<f> c() {
                if (this.f12302c == null) {
                    ArrayList arrayList = new ArrayList();
                    this.f12302c = arrayList;
                    arrayList.add(a.a);
                    this.f12302c.add(a.b);
                    this.f12302c.add(a.f12301c);
                }
                return this.f12302c;
            }
        }

        /* loaded from: classes10.dex */
        public static class b extends g {

            /* renamed from: c */
            private List<f> f12303c;

            b(com.jingdong.c.b.b bVar) {
                super(bVar);
            }

            @Override // com.jingdong.c.b.g
            protected final List<f> c() {
                if (this.f12303c == null) {
                    ArrayList arrayList = new ArrayList();
                    this.f12303c = arrayList;
                    arrayList.add(a.b);
                    this.f12303c.add(a.f12301c);
                }
                return this.f12303c;
            }
        }

        /* loaded from: classes10.dex */
        public static class c extends g {

            /* renamed from: c */
            private List<f> f12304c;

            c(com.jingdong.c.b.b bVar) {
                super(bVar);
            }

            @Override // com.jingdong.c.b.g
            protected final List<f> c() {
                if (this.f12304c == null) {
                    ArrayList arrayList = new ArrayList();
                    this.f12304c = arrayList;
                    arrayList.add(a.f12301c);
                }
                return this.f12304c;
            }
        }

        public static g a(com.jingdong.c.b.b bVar) {
            return new C0404a(bVar);
        }

        public static g b(com.jingdong.c.b.b bVar) {
            return new b(bVar);
        }

        public static g c(com.jingdong.c.b.b bVar) {
            return new c(bVar);
        }
    }

    public g(b bVar) {
        this.b = bVar;
    }

    @Override // com.jingdong.c.b.f.a
    public final b a() {
        return this.b;
    }

    @Override // com.jingdong.c.b.f.a
    public final j b() {
        List<f> c2 = c();
        if (this.a < c2.size()) {
            int i2 = this.a;
            this.a = i2 + 1;
            return c2.get(i2).a(this);
        }
        throw new AssertionError();
    }

    protected abstract List<f> c();
}

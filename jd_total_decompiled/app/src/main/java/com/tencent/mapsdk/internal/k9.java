package com.tencent.mapsdk.internal;

import com.tencent.mapsdk.internal.ba;
import com.tencent.mapsdk.internal.j9;
import com.tencent.mapsdk.internal.m9;

/* loaded from: classes9.dex */
public abstract class k9<D extends m9> extends t9<D> implements j9<D> {

    /* loaded from: classes9.dex */
    public class a extends ba.i<Boolean> {
        public final /* synthetic */ String b;

        /* renamed from: c */
        public final /* synthetic */ m9 f16763c;

        public a(String str, m9 m9Var) {
            k9.this = r1;
            this.b = str;
            this.f16763c = m9Var;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // java.util.concurrent.Callable
        /* renamed from: a */
        public Boolean call() {
            k9.this.b(this.b, (String) this.f16763c);
            return Boolean.TRUE;
        }
    }

    /* loaded from: classes9.dex */
    public class b extends ba.i<D> {
        public final /* synthetic */ String b;

        /* renamed from: c */
        public final /* synthetic */ Class f16764c;

        public b(String str, Class cls) {
            k9.this = r1;
            this.b = str;
            this.f16764c = cls;
        }

        @Override // java.util.concurrent.Callable
        /* renamed from: a */
        public D call() {
            return (D) k9.this.b(this.b, this.f16764c);
        }
    }

    /* loaded from: classes9.dex */
    public class c extends ba.i<Boolean> {
        public final /* synthetic */ String b;

        public c(String str) {
            k9.this = r1;
            this.b = str;
        }

        @Override // java.util.concurrent.Callable
        /* renamed from: a */
        public Boolean call() {
            return Boolean.valueOf(k9.this.a(this.b));
        }
    }

    /* loaded from: classes9.dex */
    public class d extends ba.i<Boolean> {
        public d() {
            k9.this = r1;
        }

        @Override // java.util.concurrent.Callable
        /* renamed from: a */
        public Boolean call() {
            k9.this.b();
            return Boolean.TRUE;
        }
    }

    /* loaded from: classes9.dex */
    public class e extends ba.i<Long> {
        public e() {
            k9.this = r1;
        }

        @Override // java.util.concurrent.Callable
        /* renamed from: a */
        public Long call() {
            return Long.valueOf(k9.this.c());
        }
    }

    /* loaded from: classes9.dex */
    public class f extends ba.i<Long> {
        public f() {
            k9.this = r1;
        }

        @Override // java.util.concurrent.Callable
        /* renamed from: a */
        public Long call() {
            return Long.valueOf(k9.this.e());
        }
    }

    /* loaded from: classes9.dex */
    public class g extends ba.i<Long> {
        public g() {
            k9.this = r1;
        }

        @Override // java.util.concurrent.Callable
        /* renamed from: a */
        public Long call() {
            return Long.valueOf(k9.this.a());
        }
    }

    @Override // com.tencent.mapsdk.internal.j9
    public final void a(j9.a<Long> aVar) {
        ba.a((ba.i) new g()).a((ba.f) new ba.g(0L)).a((ba.c) aVar);
    }

    @Override // com.tencent.mapsdk.internal.j9
    public final void a(String str, j9.a<Boolean> aVar) {
        ba.a((ba.i) new c(str)).a((ba.f) new ba.g(Boolean.FALSE)).a((ba.c) aVar);
    }

    @Override // com.tencent.mapsdk.internal.j9
    public final void a(String str, D d2, j9.a<Boolean> aVar) {
        ba.a((ba.i) new a(str, d2)).a((ba.f) new ba.g(Boolean.FALSE)).a((ba.c) aVar);
    }

    @Override // com.tencent.mapsdk.internal.j9
    public final void a(String str, Class<D> cls, j9.a<D> aVar) {
        try {
            ba.a((ba.i) new b(str, cls)).a((ba.f) new ba.g(cls.newInstance())).a((ba.c) aVar);
        } catch (IllegalAccessException e2) {
            throw new Error("The " + cls.getSimpleName() + " must have a empty construct. #" + e2.getMessage(), e2);
        } catch (InstantiationException e3) {
            throw new Error("The " + cls.getSimpleName() + " must have a empty construct. #" + e3.getMessage(), e3);
        }
    }

    @Override // com.tencent.mapsdk.internal.j9
    public final void b(j9.a<Long> aVar) {
        ba.a((ba.i) new f()).a((ba.f) new ba.g(0L)).a((ba.c) aVar);
    }

    @Override // com.tencent.mapsdk.internal.j9
    public final void c(j9.a<Boolean> aVar) {
        ba.a((ba.i) new d()).a((ba.f) new ba.g(Boolean.FALSE)).a((ba.c) aVar);
    }

    @Override // com.tencent.mapsdk.internal.j9
    public j9<D> d() {
        return this;
    }

    @Override // com.tencent.mapsdk.internal.j9
    public final void d(j9.a<Long> aVar) {
        ba.a((ba.i) new e()).a((ba.f) new ba.g(0L)).a((ba.c) aVar);
    }
}

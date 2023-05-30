package com.tencent.mapsdk.core.utils.cache;

import androidx.annotation.Keep;
import com.tencent.mapsdk.internal.l9;
import com.tencent.mapsdk.internal.la;
import com.tencent.mapsdk.internal.m9;
import com.tencent.mapsdk.internal.n9;
import com.tencent.mapsdk.internal.qa;
import com.tencent.mapsdk.internal.t9;

/* loaded from: classes9.dex */
public final class MemoryCache<D extends m9> extends t9<D> {
    private static final float d = 0.9f;

    /* renamed from: e  reason: collision with root package name */
    private static final float f16216e = 0.15f;
    private final a b;

    /* renamed from: c  reason: collision with root package name */
    private final n9.a<D> f16217c;

    /* loaded from: classes9.dex */
    public static class a implements l9.a {
        private int b;

        /* renamed from: c  reason: collision with root package name */
        private l9.b f16218c;

        @Override // com.tencent.mapsdk.internal.l9.a
        public int a() {
            return this.b;
        }

        public a a(int i2) {
            this.b = i2;
            return this;
        }

        public <D> a a(l9.b<D> bVar) {
            this.f16218c = bVar;
            return this;
        }

        public <D> l9.b<D> b() {
            return this.f16218c;
        }

        public String toString() {
            return "Options{mMaxCacheSize=" + this.b + '}';
        }
    }

    @Keep
    public MemoryCache(a aVar) {
        this.b = aVar;
        this.f16217c = new n9.a<>(h(), aVar.b());
    }

    private int h() {
        int i2 = (int) (((float) Runtime.getRuntime().totalMemory()) * d);
        int freeMemory = (int) (((float) Runtime.getRuntime().freeMemory()) * f16216e);
        a aVar = this.b;
        return aVar != null ? Math.min(Math.max(aVar.a(), freeMemory), i2) : i2;
    }

    @Override // com.tencent.mapsdk.internal.l9, com.tencent.mapsdk.internal.s9
    public long a() {
        return this.f16217c.e();
    }

    @Override // com.tencent.mapsdk.internal.l9
    public D a(String str, Class<D> cls) {
        D d2 = (D) this.f16217c.b((n9.a<D>) str);
        qa.a(la.q, str, "get data length", Integer.valueOf(d2 == null ? 0 : d2.a()));
        qa.f(la.q, str);
        return d2;
    }

    @Override // com.tencent.mapsdk.internal.l9
    public void a(String str, D d2) {
        qa.a(la.q, str, "put");
        this.f16217c.a((n9.a<D>) str, (String) d2);
        qa.a(la.q, str, "put data length", Integer.valueOf(d2.a()));
    }

    @Override // com.tencent.mapsdk.internal.l9
    public boolean b(String str) {
        return this.f16217c.c(str) != 0;
    }

    @Override // com.tencent.mapsdk.internal.l9
    public void clear() {
        this.f16217c.b();
    }

    @Override // com.tencent.mapsdk.internal.l9
    public long f() {
        return this.f16217c.h();
    }

    @Override // com.tencent.mapsdk.internal.l9
    public long getCount() {
        return this.f16217c.i().size();
    }
}

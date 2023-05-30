package com.tencent.mapsdk.internal;

import android.graphics.Bitmap;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes9.dex */
public final class ya {
    private static final String a = "PoolUtil";
    private static final int b = 20;

    /* renamed from: c  reason: collision with root package name */
    private static final o<Object> f17501c = new a();

    /* loaded from: classes9.dex */
    public static class a implements o<Object> {
        @Override // com.tencent.mapsdk.internal.ya.o
        public void a(@NonNull Object obj) {
        }
    }

    /* loaded from: classes9.dex */
    public static class b extends f<m<Bitmap>> {
        public b(e eVar) {
            super(eVar);
        }

        @Override // com.tencent.mapsdk.internal.ya.i
        /* renamed from: b  reason: merged with bridge method [inline-methods] */
        public m<Bitmap> a() {
            e eVar = this.a;
            return new m<>(Bitmap.createBitmap(eVar.a, eVar.b, eVar.f17502c));
        }
    }

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* loaded from: classes9.dex */
    public static class c<T> implements i<List<T>> {
        @Override // com.tencent.mapsdk.internal.ya.i
        @NonNull
        /* renamed from: b  reason: merged with bridge method [inline-methods] */
        public List<T> a() {
            return new ArrayList();
        }
    }

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* loaded from: classes9.dex */
    public static class d<T> implements o<List<T>> {
        @Override // com.tencent.mapsdk.internal.ya.o
        public /* bridge */ /* synthetic */ void a(@NonNull Object obj) {
            a((List) ((List) obj));
        }

        public void a(@NonNull List<T> list) {
            list.clear();
        }
    }

    /* loaded from: classes9.dex */
    public static class e {
        public int a;
        public int b;

        /* renamed from: c  reason: collision with root package name */
        public Bitmap.Config f17502c;

        public e(int i2, int i3, Bitmap.Config config) {
            this.a = i2;
            this.b = i3;
            this.f17502c = config;
        }

        public void a(e eVar) {
            if (eVar != null) {
                this.a = eVar.a;
                this.b = eVar.b;
                this.f17502c = eVar.f17502c;
            }
        }
    }

    /* loaded from: classes9.dex */
    public static abstract class f<T> implements i<T> {
        public e a;

        public f(e eVar) {
            this.a = eVar;
        }
    }

    /* loaded from: classes9.dex */
    public static class g extends p {
        private volatile RuntimeException b;

        @Override // com.tencent.mapsdk.internal.ya.p
        public void a(boolean z) {
            this.b = z ? new RuntimeException("Released") : null;
        }

        @Override // com.tencent.mapsdk.internal.ya.p
        public void b() {
            if (this.b != null) {
                throw new IllegalStateException("Already released", this.b);
            }
        }
    }

    /* loaded from: classes9.dex */
    public static class h extends p {
        private volatile boolean b;

        @Override // com.tencent.mapsdk.internal.ya.p
        public void a(boolean z) {
            this.b = z;
        }

        @Override // com.tencent.mapsdk.internal.ya.p
        public void b() {
            if (this.b) {
                throw new IllegalStateException("Already released");
            }
        }
    }

    /* loaded from: classes9.dex */
    public interface i<T> {
        T a();
    }

    /* loaded from: classes9.dex */
    public static final class j<T> implements k<T> {
        private final i<T> a;
        private final o<T> b;

        /* renamed from: c  reason: collision with root package name */
        private final k<T> f17503c;

        public j(@NonNull k<T> kVar, @NonNull i<T> iVar, @NonNull o<T> oVar) {
            this.f17503c = kVar;
            this.a = iVar;
            this.b = oVar;
        }

        @Override // com.tencent.mapsdk.internal.ya.k
        public T a() {
            T a = this.f17503c.a();
            if (a == null) {
                a = this.a.a();
                ma.f(ya.a, "Created new " + a);
            }
            if (a instanceof l) {
                ((l) a).a().a(false);
            }
            return a;
        }

        @Override // com.tencent.mapsdk.internal.ya.k
        public boolean a(@NonNull T t) {
            if (t instanceof l) {
                ((l) t).a().a(true);
            }
            this.b.a(t);
            return this.f17503c.a(t);
        }
    }

    /* loaded from: classes9.dex */
    public interface k<T> {
        @Nullable
        T a();

        boolean a(@NonNull T t);
    }

    /* loaded from: classes9.dex */
    public interface l {
        @NonNull
        p a();
    }

    /* loaded from: classes9.dex */
    public static final class m<T> implements l {
        private final T a;
        private p b = p.a();

        public m(T t) {
            this.a = t;
        }

        @Override // com.tencent.mapsdk.internal.ya.l
        @NonNull
        public p a() {
            return this.b;
        }

        public T b() {
            return this.a;
        }
    }

    /* loaded from: classes9.dex */
    public static final class n {

        /* loaded from: classes9.dex */
        public static class a<T> implements k<T> {
            private final Object[] a;
            private int b;

            public a(int i2) {
                if (i2 <= 0) {
                    throw new IllegalArgumentException("The max pool size must be > 0");
                }
                this.a = new Object[i2];
            }

            private boolean b(@NonNull T t) {
                for (int i2 = 0; i2 < this.b; i2++) {
                    if (this.a[i2] == t) {
                        return true;
                    }
                }
                return false;
            }

            @Override // com.tencent.mapsdk.internal.ya.k
            public T a() {
                int i2 = this.b;
                if (i2 > 0) {
                    int i3 = i2 - 1;
                    Object[] objArr = this.a;
                    T t = (T) objArr[i3];
                    objArr[i3] = null;
                    this.b = i3;
                    return t;
                }
                return null;
            }

            @Override // com.tencent.mapsdk.internal.ya.k
            public boolean a(@NonNull T t) {
                if (b(t)) {
                    throw new IllegalStateException("Already in the pool!");
                }
                int i2 = this.b;
                Object[] objArr = this.a;
                if (i2 < objArr.length) {
                    objArr[i2] = t;
                    this.b = i2 + 1;
                    return true;
                }
                return false;
            }
        }

        /* loaded from: classes9.dex */
        public static class b<T> extends a<T> {

            /* renamed from: c  reason: collision with root package name */
            private final Object f17504c;

            public b(int i2) {
                super(i2);
                this.f17504c = new Object();
            }

            @Override // com.tencent.mapsdk.internal.ya.n.a, com.tencent.mapsdk.internal.ya.k
            public T a() {
                T t;
                synchronized (this.f17504c) {
                    t = (T) super.a();
                }
                return t;
            }

            @Override // com.tencent.mapsdk.internal.ya.n.a, com.tencent.mapsdk.internal.ya.k
            public boolean a(@NonNull T t) {
                boolean a;
                synchronized (this.f17504c) {
                    a = super.a(t);
                }
                return a;
            }
        }

        private n() {
        }
    }

    /* loaded from: classes9.dex */
    public interface o<T> {
        void a(@NonNull T t);
    }

    /* loaded from: classes9.dex */
    public static abstract class p {
        private static final boolean a = false;

        @NonNull
        public static p a() {
            return new h();
        }

        public abstract void a(boolean z);

        public abstract void b();
    }

    private ya() {
    }

    @NonNull
    public static <T> k<List<T>> a(int i2) {
        return a(new n.b(i2), new c(), new d());
    }

    public static k<m<Bitmap>> a(int i2, @NonNull e eVar) {
        return b(i2, new b(eVar));
    }

    @NonNull
    public static <T extends l> k<T> a(int i2, @NonNull i<T> iVar) {
        return a(new n.a(i2), iVar);
    }

    @NonNull
    public static <T extends l> k<T> a(int i2, @NonNull i<T> iVar, o<T> oVar) {
        return a(new n.b(i2), iVar, oVar);
    }

    @NonNull
    private static <T extends l> k<T> a(@NonNull k<T> kVar, @NonNull i<T> iVar) {
        return a(kVar, iVar, a());
    }

    @NonNull
    private static <T> k<T> a(@NonNull k<T> kVar, @NonNull i<T> iVar, @NonNull o<T> oVar) {
        return new j(kVar, iVar, oVar);
    }

    @NonNull
    private static <T> o<T> a() {
        return (o<T>) f17501c;
    }

    @NonNull
    public static <T> k<List<T>> b() {
        return a(20);
    }

    @NonNull
    public static <T extends l> k<T> b(int i2, @NonNull i<T> iVar) {
        return a(new n.b(i2), iVar);
    }
}

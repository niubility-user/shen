package com.jd.security.jdguard.d;

import android.content.Context;
import com.jd.security.jdguard.d.c.e;
import com.jd.security.jdguard.d.c.g;
import com.jd.security.jdguard.d.d.b;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes17.dex */
public class a {

    /* renamed from: h  reason: collision with root package name */
    private static a f6905h;
    private Context a = null;
    private com.jd.security.jdguard.d.b b = null;

    /* renamed from: c  reason: collision with root package name */
    private ScheduledExecutorService f6906c = null;
    private e d = null;

    /* renamed from: e  reason: collision with root package name */
    private String f6907e = null;

    /* renamed from: f  reason: collision with root package name */
    private AtomicBoolean f6908f = new AtomicBoolean(false);

    /* renamed from: g  reason: collision with root package name */
    private com.jd.security.jdguard.d.c.d f6909g;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.jd.security.jdguard.d.a$a  reason: collision with other inner class name */
    /* loaded from: classes17.dex */
    public class C0213a implements com.jd.security.jdguard.d.d.c {
        final /* synthetic */ long a;

        C0213a(long j2) {
            this.a = j2;
        }

        @Override // com.jd.security.jdguard.d.d.c
        public void onFailed(int i2, String str) {
        }

        @Override // com.jd.security.jdguard.d.d.c
        public void onResult(int i2, String str) {
            if (a.this.b != null) {
                if (i2 > 2) {
                    a.this.b.b(d.STATIC, System.currentTimeMillis() - this.a);
                }
                if (i2 == 1) {
                    a.this.b.a(d.STATIC, System.currentTimeMillis() - this.a, true);
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes17.dex */
    public class b implements com.jd.security.jdguard.d.d.c {
        final /* synthetic */ long a;

        b(long j2) {
            this.a = j2;
        }

        @Override // com.jd.security.jdguard.d.d.c
        public void onFailed(int i2, String str) {
        }

        @Override // com.jd.security.jdguard.d.d.c
        public void onResult(int i2, String str) {
            if (a.this.b != null) {
                if (i2 > 2) {
                    a.this.b.b(d.ENV, System.currentTimeMillis() - this.a);
                }
                if (i2 == 1) {
                    a.this.b.a(d.ENV, System.currentTimeMillis() - this.a, true);
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes17.dex */
    public static /* synthetic */ class c {
        static final /* synthetic */ int[] a;

        static {
            int[] iArr = new int[d.values().length];
            a = iArr;
            try {
                iArr[d.STATIC.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                a[d.ENV.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    /* loaded from: classes17.dex */
    public enum d {
        ENV("dyn"),
        STATIC("sta");
        
        public String key;

        d(String str) {
            this.key = str;
        }
    }

    public static a f() {
        if (f6905h == null) {
            synchronized (a.class) {
                if (f6905h == null) {
                    f6905h = new a();
                }
            }
        }
        return f6905h;
    }

    private void h(d dVar) {
        com.jd.security.jdguard.d.d.a u;
        b.C0215b c0215b = new b.C0215b();
        c0215b.h(this.a);
        c0215b.l(this.f6906c);
        c0215b.k(this.f6909g);
        c0215b.m(dVar);
        c0215b.i(this.f6907e);
        c0215b.g();
        int i2 = c.a[dVar.ordinal()];
        if (i2 == 1) {
            c0215b.j(g.c().e(d.STATIC));
            u = com.jd.security.jdguard.d.d.e.a.u();
        } else if (i2 != 2) {
            return;
        } else {
            c0215b.j(g.c().e(d.ENV));
            u = com.jd.security.jdguard.d.d.d.c.s();
        }
        u.e(c0215b.g());
        u.l(null, false);
    }

    public a b(Context context) {
        this.a = context;
        return this;
    }

    public a c(String str) {
        this.f6907e = str;
        return this;
    }

    public String d() {
        if (!this.f6908f.get()) {
            return com.jd.security.jdguard.d.d.d.c.s().d();
        }
        long currentTimeMillis = System.currentTimeMillis();
        com.jd.security.jdguard.d.d.d.c s = com.jd.security.jdguard.d.d.d.c.s();
        b bVar = new b(currentTimeMillis);
        s.l(bVar, false);
        return s.i(bVar);
    }

    public a e(e eVar) {
        this.d = eVar;
        return this;
    }

    public void g() {
        if (this.a == null || this.b == null || this.f6906c == null || this.d == null) {
            return;
        }
        this.f6909g = com.jd.security.jdguard.d.c.b.k();
        g c2 = g.c();
        c2.a(this.a);
        c2.b(this.d);
        c2.f();
        for (d dVar : d.values()) {
            h(dVar);
        }
        this.f6908f.set(true);
    }

    public a i(com.jd.security.jdguard.d.b bVar) {
        this.b = bVar;
        return this;
    }

    public a j(ScheduledExecutorService scheduledExecutorService) {
        this.f6906c = scheduledExecutorService;
        return this;
    }

    public String k() {
        if (!this.f6908f.get()) {
            return com.jd.security.jdguard.d.d.e.a.u().d();
        }
        long currentTimeMillis = System.currentTimeMillis();
        com.jd.security.jdguard.d.d.e.a u = com.jd.security.jdguard.d.d.e.a.u();
        C0213a c0213a = new C0213a(currentTimeMillis);
        try {
            if (this.f6906c != null) {
                g.c().l();
            }
            com.jd.security.jdguard.d.d.d.c.s().l(null, false);
            u.l(c0213a, false);
        } catch (Throwable unused) {
        }
        return u.i(c0213a);
    }
}

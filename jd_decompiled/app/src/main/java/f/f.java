package f;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;

/* loaded from: classes.dex */
public class f<TResult> {

    /* renamed from: l */
    private static volatile g f19371l;
    private boolean b;

    /* renamed from: c */
    private boolean f19374c;
    private TResult d;

    /* renamed from: e */
    private Exception f19375e;

    /* renamed from: f */
    private boolean f19376f;

    /* renamed from: g */
    private h f19377g;

    /* renamed from: i */
    public static final ExecutorService f19368i = f.b.a();

    /* renamed from: j */
    private static final Executor f19369j = f.b.b();

    /* renamed from: k */
    public static final Executor f19370k = f.a.c();

    /* renamed from: m */
    private static f<?> f19372m = new f<>((Object) null);

    /* renamed from: n */
    private static f<Boolean> f19373n = new f<>(Boolean.TRUE);
    private static f<Boolean> o = new f<>(Boolean.FALSE);
    private final Object a = new Object();

    /* renamed from: h */
    private List<f.d<TResult, Void>> f19378h = new ArrayList();

    /* loaded from: classes.dex */
    public class a implements f.d<TResult, Void> {
        final /* synthetic */ f.g a;
        final /* synthetic */ f.d b;

        /* renamed from: c */
        final /* synthetic */ Executor f19379c;
        final /* synthetic */ f.c d;

        a(f fVar, f.g gVar, f.d dVar, Executor executor, f.c cVar) {
            this.a = gVar;
            this.b = dVar;
            this.f19379c = executor;
        }

        @Override // f.d
        /* renamed from: a */
        public Void then(f<TResult> fVar) {
            f.f(this.a, this.b, fVar, this.f19379c, this.d);
            return null;
        }
    }

    /* loaded from: classes.dex */
    public class b implements f.d<TResult, Void> {
        final /* synthetic */ f.g a;
        final /* synthetic */ f.d b;

        /* renamed from: c */
        final /* synthetic */ Executor f19380c;
        final /* synthetic */ f.c d;

        b(f fVar, f.g gVar, f.d dVar, Executor executor, f.c cVar) {
            this.a = gVar;
            this.b = dVar;
            this.f19380c = executor;
        }

        @Override // f.d
        /* renamed from: a */
        public Void then(f<TResult> fVar) {
            f.e(this.a, this.b, fVar, this.f19380c, this.d);
            return null;
        }
    }

    /* loaded from: classes.dex */
    public static class c implements Runnable {

        /* renamed from: g */
        final /* synthetic */ f.c f19381g;

        /* renamed from: h */
        final /* synthetic */ f.g f19382h;

        /* renamed from: i */
        final /* synthetic */ f.d f19383i;

        /* renamed from: j */
        final /* synthetic */ f f19384j;

        c(f.c cVar, f.g gVar, f.d dVar, f fVar) {
            this.f19382h = gVar;
            this.f19383i = dVar;
            this.f19384j = fVar;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // java.lang.Runnable
        public void run() {
            f.c cVar = this.f19381g;
            if (cVar == null) {
                try {
                    this.f19382h.d(this.f19383i.then(this.f19384j));
                    return;
                } catch (CancellationException unused) {
                    this.f19382h.b();
                    return;
                } catch (Exception e2) {
                    this.f19382h.c(e2);
                    return;
                }
            }
            cVar.a();
            throw null;
        }
    }

    /* loaded from: classes.dex */
    public static class d implements Runnable {

        /* renamed from: g */
        final /* synthetic */ f.c f19385g;

        /* renamed from: h */
        final /* synthetic */ f.g f19386h;

        /* renamed from: i */
        final /* synthetic */ f.d f19387i;

        /* renamed from: j */
        final /* synthetic */ f f19388j;

        /* loaded from: classes.dex */
        class a<TContinuationResult> implements f.d<TContinuationResult, Void> {
            a() {
                d.this = r1;
            }

            @Override // f.d
            /* renamed from: a */
            public Void then(f<TContinuationResult> fVar) {
                f.c cVar = d.this.f19385g;
                if (cVar == null) {
                    if (fVar.r()) {
                        d.this.f19386h.b();
                    } else if (fVar.t()) {
                        d.this.f19386h.c(fVar.o());
                    } else {
                        d.this.f19386h.d(fVar.p());
                    }
                    return null;
                }
                cVar.a();
                throw null;
            }
        }

        d(f.c cVar, f.g gVar, f.d dVar, f fVar) {
            this.f19386h = gVar;
            this.f19387i = dVar;
            this.f19388j = fVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            f.c cVar = this.f19385g;
            if (cVar == null) {
                try {
                    f fVar = (f) this.f19387i.then(this.f19388j);
                    if (fVar == null) {
                        this.f19386h.d(null);
                    } else {
                        fVar.g(new a());
                    }
                    return;
                } catch (CancellationException unused) {
                    this.f19386h.b();
                    return;
                } catch (Exception e2) {
                    this.f19386h.c(e2);
                    return;
                }
            }
            cVar.a();
            throw null;
        }
    }

    /* loaded from: classes.dex */
    public static class e implements Runnable {

        /* renamed from: g */
        final /* synthetic */ f.c f19389g;

        /* renamed from: h */
        final /* synthetic */ f.g f19390h;

        /* renamed from: i */
        final /* synthetic */ Callable f19391i;

        e(f.c cVar, f.g gVar, Callable callable) {
            this.f19390h = gVar;
            this.f19391i = callable;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // java.lang.Runnable
        public void run() {
            f.c cVar = this.f19389g;
            if (cVar == null) {
                try {
                    this.f19390h.d(this.f19391i.call());
                    return;
                } catch (CancellationException unused) {
                    this.f19390h.b();
                    return;
                } catch (Exception e2) {
                    this.f19390h.c(e2);
                    return;
                }
            }
            cVar.a();
            throw null;
        }
    }

    /* renamed from: f.f$f */
    /* loaded from: classes.dex */
    public class C0827f extends f.g<TResult> {
        C0827f(f fVar) {
        }
    }

    /* loaded from: classes.dex */
    public interface g {
        void a(f<?> fVar, i iVar);
    }

    static {
        new f(true);
    }

    public f() {
    }

    public static <TResult> f<TResult> c(Callable<TResult> callable, Executor executor) {
        return d(callable, executor, null);
    }

    public static <TResult> f<TResult> d(Callable<TResult> callable, Executor executor, f.c cVar) {
        f.g gVar = new f.g();
        try {
            executor.execute(new e(cVar, gVar, callable));
        } catch (Exception e2) {
            gVar.c(new f.e(e2));
        }
        return gVar.a();
    }

    public static <TContinuationResult, TResult> void e(f.g<TContinuationResult> gVar, f.d<TResult, f<TContinuationResult>> dVar, f<TResult> fVar, Executor executor, f.c cVar) {
        try {
            executor.execute(new d(cVar, gVar, dVar, fVar));
        } catch (Exception e2) {
            gVar.c(new f.e(e2));
        }
    }

    public static <TContinuationResult, TResult> void f(f.g<TContinuationResult> gVar, f.d<TResult, TContinuationResult> dVar, f<TResult> fVar, Executor executor, f.c cVar) {
        try {
            executor.execute(new c(cVar, gVar, dVar, fVar));
        } catch (Exception e2) {
            gVar.c(new f.e(e2));
        }
    }

    public static <TResult> f<TResult>.C0827f l() {
        return new C0827f(new f());
    }

    public static <TResult> f<TResult> m(Exception exc) {
        f.g gVar = new f.g();
        gVar.c(exc);
        return gVar.a();
    }

    public static <TResult> f<TResult> n(TResult tresult) {
        if (tresult == null) {
            return (f<TResult>) f19372m;
        }
        if (tresult instanceof Boolean) {
            return ((Boolean) tresult).booleanValue() ? (f<TResult>) f19373n : (f<TResult>) o;
        }
        f.g gVar = new f.g();
        gVar.d(tresult);
        return gVar.a();
    }

    public static g q() {
        return f19371l;
    }

    private void u() {
        synchronized (this.a) {
            Iterator<f.d<TResult, Void>> it = this.f19378h.iterator();
            while (it.hasNext()) {
                try {
                    it.next().then(this);
                } catch (RuntimeException e2) {
                    throw e2;
                } catch (Exception e3) {
                    throw new RuntimeException(e3);
                }
            }
            this.f19378h = null;
        }
    }

    public <TContinuationResult> f<TContinuationResult> g(f.d<TResult, TContinuationResult> dVar) {
        return i(dVar, f19369j, null);
    }

    public <TContinuationResult> f<TContinuationResult> h(f.d<TResult, TContinuationResult> dVar, Executor executor) {
        return i(dVar, executor, null);
    }

    public <TContinuationResult> f<TContinuationResult> i(f.d<TResult, TContinuationResult> dVar, Executor executor, f.c cVar) {
        boolean s;
        f.g gVar = new f.g();
        synchronized (this.a) {
            s = s();
            if (!s) {
                this.f19378h.add(new a(this, gVar, dVar, executor, cVar));
            }
        }
        if (s) {
            f(gVar, dVar, this, executor, cVar);
        }
        return gVar.a();
    }

    public <TContinuationResult> f<TContinuationResult> j(f.d<TResult, f<TContinuationResult>> dVar) {
        return k(dVar, f19369j, null);
    }

    public <TContinuationResult> f<TContinuationResult> k(f.d<TResult, f<TContinuationResult>> dVar, Executor executor, f.c cVar) {
        boolean s;
        f.g gVar = new f.g();
        synchronized (this.a) {
            s = s();
            if (!s) {
                this.f19378h.add(new b(this, gVar, dVar, executor, cVar));
            }
        }
        if (s) {
            e(gVar, dVar, this, executor, cVar);
        }
        return gVar.a();
    }

    public Exception o() {
        Exception exc;
        synchronized (this.a) {
            if (this.f19375e != null) {
                this.f19376f = true;
                h hVar = this.f19377g;
                if (hVar != null) {
                    hVar.a();
                    this.f19377g = null;
                }
            }
            exc = this.f19375e;
        }
        return exc;
    }

    public TResult p() {
        TResult tresult;
        synchronized (this.a) {
            tresult = this.d;
        }
        return tresult;
    }

    public boolean r() {
        boolean z;
        synchronized (this.a) {
            z = this.f19374c;
        }
        return z;
    }

    public boolean s() {
        boolean z;
        synchronized (this.a) {
            z = this.b;
        }
        return z;
    }

    public boolean t() {
        boolean z;
        synchronized (this.a) {
            z = o() != null;
        }
        return z;
    }

    public boolean v() {
        synchronized (this.a) {
            if (this.b) {
                return false;
            }
            this.b = true;
            this.f19374c = true;
            this.a.notifyAll();
            u();
            return true;
        }
    }

    public boolean w(Exception exc) {
        synchronized (this.a) {
            if (this.b) {
                return false;
            }
            this.b = true;
            this.f19375e = exc;
            this.f19376f = false;
            this.a.notifyAll();
            u();
            if (!this.f19376f && q() != null) {
                this.f19377g = new h(this);
            }
            return true;
        }
    }

    public boolean x(TResult tresult) {
        synchronized (this.a) {
            if (this.b) {
                return false;
            }
            this.b = true;
            this.d = tresult;
            this.a.notifyAll();
            u();
            return true;
        }
    }

    private f(TResult tresult) {
        x(tresult);
    }

    private f(boolean z) {
        if (z) {
            v();
        } else {
            x(null);
        }
    }
}

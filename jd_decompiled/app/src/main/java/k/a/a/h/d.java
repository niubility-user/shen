package k.a.a.h;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import k.a.a.c.a;
import k.a.a.g.a;

/* loaded from: classes11.dex */
public abstract class d<T> {
    private k.a.a.g.a a;
    private boolean b;

    /* renamed from: c  reason: collision with root package name */
    private ExecutorService f20279c;

    /* loaded from: classes11.dex */
    public static class a {
        private k.a.a.g.a a;
        private boolean b;

        /* renamed from: c  reason: collision with root package name */
        private ExecutorService f20280c;

        public a(ExecutorService executorService, boolean z, k.a.a.g.a aVar) {
            this.f20280c = executorService;
            this.b = z;
            this.a = aVar;
        }
    }

    public d(a aVar) {
        this.a = aVar.a;
        this.b = aVar.b;
        this.f20279c = aVar.f20280c;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    /* renamed from: e  reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void f(Object obj) {
        try {
            g(obj, this.a);
        } catch (k.a.a.c.a unused) {
        } catch (Throwable th) {
            this.f20279c.shutdown();
            throw th;
        }
        this.f20279c.shutdown();
    }

    private void g(T t, k.a.a.g.a aVar) throws k.a.a.c.a {
        try {
            c(t, aVar);
            aVar.a();
        } catch (k.a.a.c.a e2) {
            aVar.b(e2);
            throw e2;
        } catch (Exception e3) {
            aVar.b(e3);
            throw new k.a.a.c.a(e3);
        }
    }

    protected abstract long a(T t) throws k.a.a.c.a;

    public void b(final T t) throws k.a.a.c.a {
        this.a.c();
        this.a.j(a.b.BUSY);
        this.a.g(d());
        if (this.b) {
            this.a.k(a(t));
            this.f20279c.execute(new Runnable() { // from class: k.a.a.h.a
                @Override // java.lang.Runnable
                public final void run() {
                    d.this.f(t);
                }
            });
            return;
        }
        g(t, this.a);
    }

    protected abstract void c(T t, k.a.a.g.a aVar) throws IOException;

    protected abstract a.c d();

    /* JADX INFO: Access modifiers changed from: protected */
    public void h() throws k.a.a.c.a {
        if (this.a.e()) {
            this.a.i(a.EnumC0856a.CANCELLED);
            this.a.j(a.b.READY);
            throw new k.a.a.c.a("Task cancelled", a.EnumC0855a.TASK_CANCELLED_EXCEPTION);
        }
    }
}

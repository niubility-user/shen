package g.e.c.a;

/* loaded from: classes12.dex */
public class g<TResult> {
    private final com.huawei.hmf.tasks.a.e<TResult> a = new com.huawei.hmf.tasks.a.e<>();

    /* loaded from: classes12.dex */
    class a implements Runnable {
        a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            g.this.a.l();
        }
    }

    public g() {
    }

    public g(g.e.c.a.a aVar) {
        aVar.b(new a());
    }

    public f<TResult> b() {
        return this.a;
    }

    public void c(Exception exc) {
        this.a.j(exc);
    }

    public void d(TResult tresult) {
        this.a.k(tresult);
    }
}

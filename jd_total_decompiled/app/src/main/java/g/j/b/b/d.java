package g.j.b.b;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes11.dex */
public class d implements Runnable {

    /* renamed from: g  reason: collision with root package name */
    final /* synthetic */ g.j.b.a.c f19690g;

    /* renamed from: h  reason: collision with root package name */
    final /* synthetic */ b f19691h;

    /* JADX INFO: Access modifiers changed from: package-private */
    public d(b bVar, g.j.b.a.c cVar) {
        this.f19691h = bVar;
        this.f19690g = cVar;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.f19691h.u(this.f19690g);
    }
}

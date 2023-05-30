package g.j.b.b;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes11.dex */
public class c implements Runnable {

    /* renamed from: g  reason: collision with root package name */
    final /* synthetic */ g.j.b.a.b f19688g;

    /* renamed from: h  reason: collision with root package name */
    final /* synthetic */ b f19689h;

    /* JADX INFO: Access modifiers changed from: package-private */
    public c(b bVar, g.j.b.a.b bVar2) {
        this.f19689h = bVar;
        this.f19688g = bVar2;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.f19689h.t(this.f19688g);
    }
}

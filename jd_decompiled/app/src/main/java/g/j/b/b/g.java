package g.j.b.b;

import com.xiaomi.push.i;
import java.util.concurrent.ExecutorService;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes11.dex */
public class g extends i.a {

    /* renamed from: g  reason: collision with root package name */
    final /* synthetic */ b f19694g;

    /* JADX INFO: Access modifiers changed from: package-private */
    public g(b bVar) {
        this.f19694g = bVar;
    }

    @Override // com.xiaomi.push.i.a
    public String b() {
        return "100889";
    }

    @Override // java.lang.Runnable
    public void run() {
        int q;
        ExecutorService executorService;
        q = this.f19694g.q();
        if (q > 0) {
            executorService = this.f19694g.a;
            executorService.execute(new h(this));
        }
    }
}

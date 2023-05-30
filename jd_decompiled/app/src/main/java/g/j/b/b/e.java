package g.j.b.b;

import com.xiaomi.push.i;
import java.util.concurrent.ExecutorService;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes11.dex */
public class e extends i.a {

    /* renamed from: g  reason: collision with root package name */
    final /* synthetic */ b f19692g;

    /* JADX INFO: Access modifiers changed from: package-private */
    public e(b bVar) {
        this.f19692g = bVar;
    }

    @Override // com.xiaomi.push.i.a
    public String b() {
        return "100888";
    }

    @Override // java.lang.Runnable
    public void run() {
        int a;
        ExecutorService executorService;
        a = this.f19692g.a();
        if (a > 0) {
            executorService = this.f19692g.a;
            executorService.execute(new f(this));
        }
    }
}

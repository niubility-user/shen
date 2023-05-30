package g.f.a.a;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* loaded from: classes12.dex */
public class i {
    private static volatile i b;
    private ExecutorService a = Executors.newFixedThreadPool(4);

    private i() {
    }

    public static i b() {
        if (b == null) {
            synchronized (i.class) {
                if (b == null) {
                    b = new i();
                }
            }
        }
        return b;
    }

    public void a(Runnable runnable) {
        ExecutorService executorService = this.a;
        if (executorService == null || executorService.isShutdown()) {
            return;
        }
        this.a.execute(runnable);
    }
}

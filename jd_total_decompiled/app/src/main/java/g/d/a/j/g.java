package g.d.a.j;

import android.os.Handler;
import android.os.Looper;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* loaded from: classes12.dex */
public class g {
    private static final ExecutorService a = Executors.newSingleThreadExecutor();
    private static Handler b = new Handler(Looper.getMainLooper());

    public static void a(Runnable runnable) {
        a.execute(runnable);
    }

    public static void b(Runnable runnable) {
        b.post(runnable);
    }
}

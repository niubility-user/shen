package i.a;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* loaded from: classes11.dex */
public class b {

    /* loaded from: classes11.dex */
    public static class a {
        public static ThreadPoolExecutor a = new ThreadPoolExecutor(3, Integer.MAX_VALUE, 15, TimeUnit.SECONDS, new LinkedBlockingDeque(), c.a("JDHybridDownload", false));
    }

    /* renamed from: i.a.b$b  reason: collision with other inner class name */
    /* loaded from: classes11.dex */
    public static class C0844b {
        public static ThreadPoolExecutor a = new ThreadPoolExecutor(1, Integer.MAX_VALUE, 15, TimeUnit.SECONDS, new LinkedBlockingDeque(), c.a("JDHybridRequest", false));
    }

    public static ExecutorService a() {
        return a.a;
    }

    public static ExecutorService b() {
        return C0844b.a;
    }
}

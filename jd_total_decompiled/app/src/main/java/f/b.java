package f;

import java.util.Locale;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

/* loaded from: classes.dex */
final class b {
    private static final b d = new b();
    private final ExecutorService a;
    private final ScheduledExecutorService b;

    /* renamed from: c  reason: collision with root package name */
    private final Executor f19366c;

    /* renamed from: f.b$b  reason: collision with other inner class name */
    /* loaded from: classes.dex */
    private static class ExecutorC0826b implements Executor {

        /* renamed from: g  reason: collision with root package name */
        private ThreadLocal<Integer> f19367g;

        private ExecutorC0826b() {
            this.f19367g = new ThreadLocal<>();
        }

        private int a() {
            Integer num = this.f19367g.get();
            if (num == null) {
                num = 0;
            }
            int intValue = num.intValue() - 1;
            if (intValue == 0) {
                this.f19367g.remove();
            } else {
                this.f19367g.set(Integer.valueOf(intValue));
            }
            return intValue;
        }

        private int b() {
            Integer num = this.f19367g.get();
            if (num == null) {
                num = 0;
            }
            int intValue = num.intValue() + 1;
            this.f19367g.set(Integer.valueOf(intValue));
            return intValue;
        }

        @Override // java.util.concurrent.Executor
        public void execute(Runnable runnable) {
            try {
                if (b() <= 15) {
                    runnable.run();
                } else {
                    b.a().execute(runnable);
                }
            } finally {
                a();
            }
        }
    }

    private b() {
        this.a = !c() ? Executors.newCachedThreadPool() : f.a.b();
        this.b = Executors.newSingleThreadScheduledExecutor();
        this.f19366c = new ExecutorC0826b();
    }

    public static ExecutorService a() {
        return d.a;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Executor b() {
        return d.f19366c;
    }

    private static boolean c() {
        String property = System.getProperty("java.runtime.name");
        if (property == null) {
            return false;
        }
        return property.toLowerCase(Locale.US).contains("android");
    }
}

package g.e.c.a;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;

/* loaded from: classes12.dex */
public final class h {

    /* renamed from: c  reason: collision with root package name */
    private static final h f19471c = new h();
    private final ExecutorService a = com.huawei.hmf.tasks.a.a.a();
    private final Executor b = com.huawei.hmf.tasks.a.a.b();

    private h() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ExecutorService a() {
        return f19471c.a;
    }

    public static Executor b() {
        return f19471c.b;
    }
}

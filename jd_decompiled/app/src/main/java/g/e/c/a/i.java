package g.e.c.a;

import com.huawei.hmf.tasks.a.f;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;

/* loaded from: classes12.dex */
public class i {
    private static com.huawei.hmf.tasks.a.f a = new com.huawei.hmf.tasks.a.f();

    public static <TResult> TResult a(f<TResult> fVar) throws ExecutionException, InterruptedException {
        com.huawei.hmf.tasks.a.f.c("await must not be called on the UI thread");
        if (fVar.g()) {
            return (TResult) com.huawei.hmf.tasks.a.f.b(fVar);
        }
        f.b bVar = new f.b();
        fVar.c(bVar);
        fVar.b(bVar);
        bVar.a.await();
        return (TResult) com.huawei.hmf.tasks.a.f.b(fVar);
    }

    public static <TResult> f<TResult> b(Callable<TResult> callable) {
        return a.a(h.a(), callable);
    }
}

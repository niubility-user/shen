package c.t.m.g;

import android.content.Context;
import android.text.TextUtils;
import c.t.m.g.m5;
import com.jd.dynamic.DYConstants;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/* loaded from: classes.dex */
public class u4 implements m5.b {

    /* renamed from: c  reason: collision with root package name */
    public static volatile String f708c = "";
    public static volatile boolean d;
    public Context a;
    public l4 b;

    /* loaded from: classes.dex */
    public static class a implements Runnable {

        /* renamed from: c.t.m.g.u4$a$a  reason: collision with other inner class name */
        /* loaded from: classes.dex */
        public class C0009a implements l4 {
            public C0009a(a aVar) {
            }

            @Override // c.t.m.g.l4
            public void a(String str) {
                if (t2.c(str)) {
                    return;
                }
                if (!u4.f708c.equals(str)) {
                    long currentTimeMillis = System.currentTimeMillis();
                    z0.d("LOG", "update oaid," + u4.f708c + DYConstants.DY_REGEX_COMMA + str + DYConstants.DY_REGEX_COMMA + currentTimeMillis);
                    r3.g(r3.a(), "loc_id_oaid", str);
                    r3.f(r3.a(), "loc_id_oaid_time", Long.valueOf(currentTimeMillis));
                    String unused = u4.f708c = str;
                }
                StringBuilder sb = new StringBuilder("getOaid, ");
                sb.append(u4.f708c);
                sb.append(", ");
                sb.append(str);
            }
        }

        @Override // java.lang.Runnable
        public final void run() {
            try {
                new u4(new C0009a(this));
            } catch (Throwable unused) {
            }
        }
    }

    /* loaded from: classes.dex */
    public static class b implements Runnable {

        /* renamed from: g  reason: collision with root package name */
        public final /* synthetic */ Runnable f709g;

        public b(Runnable runnable) {
            this.f709g = runnable;
        }

        @Override // java.lang.Runnable
        public final void run() {
            ExecutorService newSingleThreadExecutor = Executors.newSingleThreadExecutor();
            Future<?> submit = newSingleThreadExecutor.submit(this.f709g);
            try {
                newSingleThreadExecutor.shutdown();
                if (newSingleThreadExecutor.awaitTermination(15L, TimeUnit.SECONDS)) {
                    return;
                }
                z0.d("LOG", "get oaid terminated.");
                if (submit.isDone()) {
                    return;
                }
                submit.cancel(true);
            } catch (Throwable unused) {
            }
        }
    }

    public u4(l4 l4Var) {
        Context a2 = y3.a();
        this.a = a2;
        this.b = l4Var;
        new m5(this).d(a2);
    }

    public static synchronized String c() {
        String c2;
        synchronized (u4.class) {
            if (TextUtils.isEmpty(f708c)) {
                f708c = r3.e(r3.a(), "loc_id_oaid", "");
            }
            if (!d) {
                d = true;
                b bVar = new b(new a());
                long currentTimeMillis = System.currentTimeMillis();
                long longValue = ((Long) r3.c(r3.a(), "loc_id_oaid_time", 0L)).longValue();
                if (!z3.u() && Math.abs(currentTimeMillis - longValue) >= 432000000) {
                    d.d("th_loc_oaid", bVar);
                }
            }
            c2 = z3.c(f708c);
        }
        return c2;
    }

    @Override // c.t.m.g.m5.b
    public void a(String str, boolean z) {
        l4 l4Var = this.b;
        if (l4Var != null) {
            if (!z) {
                str = null;
            }
            l4Var.a(str);
        }
    }
}

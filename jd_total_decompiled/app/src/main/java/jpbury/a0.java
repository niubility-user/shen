package jpbury;

import android.app.Application;
import android.content.Context;
import android.os.Looper;
import androidx.annotation.Nullable;
import java.lang.ref.WeakReference;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/* loaded from: classes11.dex */
public class a0 {
    private static WeakReference<Context> a;
    private static volatile Looper b;
    private static volatile Looper d;

    /* renamed from: c  reason: collision with root package name */
    private static final Object f20070c = new Object();

    /* renamed from: e  reason: collision with root package name */
    private static final Object f20071e = new Object();

    /* renamed from: f  reason: collision with root package name */
    private static final Object f20072f = new Object();

    /* renamed from: g  reason: collision with root package name */
    private static final Set<c> f20073g = new HashSet();

    /* renamed from: h  reason: collision with root package name */
    private static final Set<c> f20074h = new HashSet();

    /* loaded from: classes11.dex */
    public class a extends g0 {
        public a(String str) {
            super(str);
        }

        @Override // jpbury.g0
        public void a(Throwable th) {
            a0.d(th);
        }
    }

    /* loaded from: classes11.dex */
    public class b extends g0 {
        public b(String str) {
            super(str);
        }

        @Override // jpbury.g0
        public void a(Throwable th) {
            a0.c(th);
        }
    }

    /* loaded from: classes11.dex */
    public interface c {
        void a(Throwable th);
    }

    @Nullable
    public static Application a() {
        Context context;
        e();
        WeakReference<Context> weakReference = a;
        if (weakReference == null || (context = weakReference.get()) == null) {
            return null;
        }
        Context applicationContext = context.getApplicationContext();
        if (applicationContext instanceof Application) {
            return (Application) applicationContext;
        }
        return null;
    }

    public static void a(Context context) {
        a = new WeakReference<>(context.getApplicationContext());
        e();
    }

    private static void a(c cVar) {
        if (cVar == null) {
            return;
        }
        Set<c> set = f20073g;
        synchronized (set) {
            set.add(cVar);
        }
    }

    @Nullable
    public static Context b() {
        e();
        WeakReference<Context> weakReference = a;
        if (weakReference == null) {
            return null;
        }
        return weakReference.get();
    }

    private static void b(c cVar) {
        if (cVar == null) {
            return;
        }
        Set<c> set = f20074h;
        synchronized (set) {
            set.add(cVar);
        }
    }

    @Nullable
    public static Looper c(c cVar) {
        e();
        a(cVar);
        return b;
    }

    private static Set<c> c() {
        Set<c> set = f20073g;
        synchronized (set) {
        }
        return set;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void c(Throwable th) {
        synchronized (f20070c) {
            b = null;
        }
        Iterator<c> it = c().iterator();
        while (it.hasNext()) {
            it.next().a(th);
        }
    }

    @Nullable
    public static Looper d(c cVar) {
        e();
        b(cVar);
        return d;
    }

    private static Set<c> d() {
        Set<c> set = f20074h;
        synchronized (set) {
        }
        return set;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void d(Throwable th) {
        synchronized (f20071e) {
            d = null;
        }
        Iterator<c> it = d().iterator();
        while (it.hasNext()) {
            it.next().a(th);
        }
    }

    private static void e() {
        synchronized (f20072f) {
            f();
            g();
        }
    }

    private static void f() {
        if (b == null) {
            try {
                b bVar = new b("JDBury_IO");
                synchronized (f20070c) {
                    bVar.start();
                    b = bVar.a();
                }
            } catch (Throwable th) {
                th.printStackTrace();
                c(th);
            }
        }
    }

    private static void g() {
        if (d == null) {
            try {
                a aVar = new a("JDBury_Parse");
                synchronized (f20071e) {
                    aVar.start();
                    d = aVar.a();
                }
            } catch (Throwable th) {
                th.printStackTrace();
                d(th);
            }
        }
    }
}

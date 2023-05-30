package jpbury;

import android.os.Looper;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.jdpay.bury.IdExtension;
import jpbury.a0;
import jpbury.c;
import jpbury.e;
import jpbury.v;

/* loaded from: classes11.dex */
public final class y {

    /* renamed from: c  reason: collision with root package name */
    private static final int f20149c = 4;
    private static final a0.c d = new a();

    /* renamed from: e  reason: collision with root package name */
    private static final Object f20150e = new Object();

    /* renamed from: f  reason: collision with root package name */
    private static j0 f20151f;
    private int a = 0;
    private long b;

    /* loaded from: classes11.dex */
    public class a implements a0.c {
        @Override // jpbury.a0.c
        public void a(Throwable th) {
            synchronized (y.f20150e) {
                j0 unused = y.f20151f = null;
            }
        }
    }

    /* loaded from: classes11.dex */
    public class b implements Runnable {
        public final /* synthetic */ String a;
        public final /* synthetic */ String b;

        /* renamed from: c  reason: collision with root package name */
        public final /* synthetic */ IdExtension f20152c;
        public final /* synthetic */ String d;

        /* renamed from: e  reason: collision with root package name */
        public final /* synthetic */ long f20153e;

        /* renamed from: f  reason: collision with root package name */
        public final /* synthetic */ String f20154f;

        /* renamed from: g  reason: collision with root package name */
        public final /* synthetic */ jpbury.f f20155g;

        /* renamed from: h  reason: collision with root package name */
        public final /* synthetic */ boolean f20156h;

        public b(String str, String str2, IdExtension idExtension, String str3, long j2, String str4, jpbury.f fVar, boolean z) {
            this.a = str;
            this.b = str2;
            this.f20152c = idExtension;
            this.d = str3;
            this.f20153e = j2;
            this.f20154f = str4;
            this.f20155g = fVar;
            this.f20156h = z;
        }

        @Override // java.lang.Runnable
        public void run() {
            y.this.a(s.a(new g(this.a, this.b, this.f20152c, this.d, this.f20153e, this.f20154f, 0, this.f20155g, this.f20156h)));
        }
    }

    /* loaded from: classes11.dex */
    public class c implements Runnable {
        public final /* synthetic */ String a;
        public final /* synthetic */ String b;

        /* renamed from: c  reason: collision with root package name */
        public final /* synthetic */ IdExtension f20158c;
        public final /* synthetic */ String d;

        /* renamed from: e  reason: collision with root package name */
        public final /* synthetic */ long f20159e;

        /* renamed from: f  reason: collision with root package name */
        public final /* synthetic */ String f20160f;

        /* renamed from: g  reason: collision with root package name */
        public final /* synthetic */ int f20161g;

        /* renamed from: h  reason: collision with root package name */
        public final /* synthetic */ jpbury.f f20162h;

        /* renamed from: i  reason: collision with root package name */
        public final /* synthetic */ boolean f20163i;

        public c(String str, String str2, IdExtension idExtension, String str3, long j2, String str4, int i2, jpbury.f fVar, boolean z) {
            this.a = str;
            this.b = str2;
            this.f20158c = idExtension;
            this.d = str3;
            this.f20159e = j2;
            this.f20160f = str4;
            this.f20161g = i2;
            this.f20162h = fVar;
            this.f20163i = z;
        }

        @Override // java.lang.Runnable
        public void run() {
            y.this.a(s.a(new g(this.a, this.b, this.f20158c, this.d, this.f20159e, this.f20160f, this.f20161g, this.f20162h, this.f20163i)));
        }
    }

    /* loaded from: classes11.dex */
    public class d implements Runnable {
        public final /* synthetic */ String a;
        public final /* synthetic */ String b;

        /* renamed from: c  reason: collision with root package name */
        public final /* synthetic */ String f20165c;
        public final /* synthetic */ String d;

        /* renamed from: e  reason: collision with root package name */
        public final /* synthetic */ String f20166e;

        /* renamed from: f  reason: collision with root package name */
        public final /* synthetic */ long f20167f;

        /* renamed from: g  reason: collision with root package name */
        public final /* synthetic */ String f20168g;

        /* renamed from: h  reason: collision with root package name */
        public final /* synthetic */ int f20169h;

        /* renamed from: i  reason: collision with root package name */
        public final /* synthetic */ jpbury.f f20170i;

        /* renamed from: j  reason: collision with root package name */
        public final /* synthetic */ boolean f20171j;

        public d(String str, String str2, String str3, String str4, String str5, long j2, String str6, int i2, jpbury.f fVar, boolean z) {
            this.a = str;
            this.b = str2;
            this.f20165c = str3;
            this.d = str4;
            this.f20166e = str5;
            this.f20167f = j2;
            this.f20168g = str6;
            this.f20169h = i2;
            this.f20170i = fVar;
            this.f20171j = z;
        }

        @Override // java.lang.Runnable
        public void run() {
            y.this.a(s.a(new h(this.a, this.b, this.f20165c, this.d, this.f20166e, this.f20167f, this.f20168g, this.f20169h, this.f20170i, this.f20171j)));
        }
    }

    /* loaded from: classes11.dex */
    public class e implements v.c {
        public final /* synthetic */ s a;
        public final /* synthetic */ int b;

        /* loaded from: classes11.dex */
        public class a implements Runnable {
            public a() {
            }

            @Override // java.lang.Runnable
            public void run() {
                if (y.this.a == 0) {
                    c.b a = jpbury.c.b().a();
                    if (a == null) {
                        return;
                    }
                    y.this.a = a.a();
                    y.this.b = a.b();
                }
                int i2 = y.this.a;
                long j2 = y.this.b;
                if (i2 > 0) {
                    y.this.a = 0;
                    y.this.b = 0L;
                    jpbury.c.b().a(0, 0L);
                    y.this.a(s.a(g.a(e.this.a.d(), i2, j2), true));
                }
            }
        }

        /* loaded from: classes11.dex */
        public class b implements Runnable {
            public b() {
            }

            @Override // java.lang.Runnable
            public void run() {
                e eVar = e.this;
                if (eVar.b == 0 && !eVar.a.h()) {
                    y.b(y.this);
                    y.this.b = System.currentTimeMillis();
                    jpbury.c.b().a(y.this.a, y.this.b);
                }
                e eVar2 = e.this;
                y.this.a(eVar2.a, eVar2.b + 1);
            }
        }

        public e(s sVar, int i2) {
            this.a = sVar;
            this.b = i2;
        }

        @Override // jpbury.v.c
        public void a() {
            y.b(new b());
        }

        @Override // jpbury.v.c
        public void a(@Nullable e.a aVar) {
            y.b(new a());
        }
    }

    /* loaded from: classes11.dex */
    public class f implements Runnable {
        public final /* synthetic */ String a;
        public final /* synthetic */ jpbury.f b;

        public f(String str, jpbury.f fVar) {
            this.a = str;
            this.b = fVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            jpbury.c.b().a(this.a, m.a(this.b));
        }
    }

    private y() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(@NonNull s sVar) {
        b();
        a(sVar, 0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(@NonNull s sVar, int i2) {
        b();
        if (i2 > 4) {
            return;
        }
        jpbury.c.b().a(sVar, new e(sVar, i2));
    }

    public static /* synthetic */ int b(y yVar) {
        int i2 = yVar.a;
        yVar.a = i2 + 1;
        return i2;
    }

    private static void b() {
        d().a();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean b(@Nullable Runnable runnable) {
        if (runnable == null) {
            return false;
        }
        if (d().a()) {
            runnable.run();
            return true;
        }
        return d().c(runnable);
    }

    public static y c() {
        return new y();
    }

    private static j0 d() {
        synchronized (f20150e) {
            if (f20151f == null) {
                Looper d2 = a0.d(d);
                if (d2 == null) {
                    return i0.d();
                }
                f20151f = new h0(d2);
            }
            return f20151f;
        }
    }

    public void a(String str, String str2, IdExtension idExtension, String str3, long j2, String str4, int i2, jpbury.f fVar, boolean z) {
        b(new c(str, str2, idExtension, str3, j2, str4, i2, fVar, z));
    }

    public void a(String str, String str2, IdExtension idExtension, String str3, long j2, String str4, jpbury.f fVar, boolean z) {
        b(new b(str, str2, idExtension, str3, j2, str4, fVar, z));
    }

    public void a(String str, String str2, String str3, String str4, String str5, long j2, String str6, int i2, jpbury.f fVar, boolean z) {
        b(new d(str, str2, str3, str4, str5, j2, str6, i2, fVar, z));
    }

    public void a(String str, @NonNull jpbury.f fVar) {
        b(new f(str, fVar));
    }
}

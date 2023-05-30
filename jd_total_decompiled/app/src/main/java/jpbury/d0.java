package jpbury;

import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;
import androidx.annotation.NonNull;
import com.jdpay.bury.IdExtension;
import com.jdpay.bury.SessionPack;
import com.jingdong.app.mall.bundle.order_center_isv_core.util.OrderISVUtil;
import com.jingdong.common.utils.LangUtils;
import jpbury.a0;

/* loaded from: classes11.dex */
public class d0 implements c0 {
    private j0 b;
    @NonNull
    private final y d;
    private final jpbury.f a = jpbury.f.a();

    /* renamed from: c */
    private final Object f20082c = new Object();

    /* renamed from: e */
    private final a0.c f20083e = new a();

    /* loaded from: classes11.dex */
    public class a implements a0.c {
        public a() {
            d0.this = r1;
        }

        @Override // jpbury.a0.c
        public void a(Throwable th) {
            synchronized (d0.this.f20082c) {
                d0.this.b = null;
            }
        }
    }

    /* loaded from: classes11.dex */
    public class b implements Runnable {
        public final /* synthetic */ k a;

        public b(k kVar) {
            d0.this = r1;
            this.a = kVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            d0.this.a.a(this.a);
        }
    }

    /* loaded from: classes11.dex */
    public class c implements Runnable {
        public final /* synthetic */ SessionPack a;
        public final /* synthetic */ String b;

        public c(SessionPack sessionPack, String str) {
            d0.this = r1;
            this.a = sessionPack;
            this.b = str;
        }

        @Override // java.lang.Runnable
        public void run() {
            d0.this.a.g();
            d0.this.a.d(this.a.getUUID());
            d0.this.a.a(this.a.getSessions());
            d0.this.d.a(this.b, d0.this.a);
        }
    }

    /* loaded from: classes11.dex */
    public class d implements Runnable {
        public final /* synthetic */ String a;
        public final /* synthetic */ String b;

        public d(String str, String str2) {
            d0.this = r1;
            this.a = str;
            this.b = str2;
        }

        @Override // java.lang.Runnable
        public void run() {
            d0.this.a.a(this.a, this.b);
        }
    }

    /* loaded from: classes11.dex */
    public class e implements Runnable {
        public final /* synthetic */ String a;
        public final /* synthetic */ String b;

        /* renamed from: c */
        public final /* synthetic */ IdExtension f20086c;
        public final /* synthetic */ String d;

        /* renamed from: e */
        public final /* synthetic */ long f20087e;

        /* renamed from: f */
        public final /* synthetic */ String f20088f;

        /* renamed from: g */
        public final /* synthetic */ boolean f20089g;

        public e(String str, String str2, IdExtension idExtension, String str3, long j2, String str4, boolean z) {
            d0.this = r1;
            this.a = str;
            this.b = str2;
            this.f20086c = idExtension;
            this.d = str3;
            this.f20087e = j2;
            this.f20088f = str4;
            this.f20089g = z;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (d0.this.f()) {
                return;
            }
            y yVar = d0.this.d;
            String str = this.a;
            String str2 = this.b;
            yVar.a(str, str2, this.f20086c, d0.this.c(str2, this.d), this.f20087e, this.f20088f, d0.this.a, this.f20089g);
        }
    }

    /* loaded from: classes11.dex */
    public class f implements Runnable {
        public final /* synthetic */ String a;
        public final /* synthetic */ String b;

        /* renamed from: c */
        public final /* synthetic */ IdExtension f20091c;
        public final /* synthetic */ String d;

        /* renamed from: e */
        public final /* synthetic */ long f20092e;

        /* renamed from: f */
        public final /* synthetic */ String f20093f;

        /* renamed from: g */
        public final /* synthetic */ int f20094g;

        /* renamed from: h */
        public final /* synthetic */ boolean f20095h;

        public f(String str, String str2, IdExtension idExtension, String str3, long j2, String str4, int i2, boolean z) {
            d0.this = r1;
            this.a = str;
            this.b = str2;
            this.f20091c = idExtension;
            this.d = str3;
            this.f20092e = j2;
            this.f20093f = str4;
            this.f20094g = i2;
            this.f20095h = z;
        }

        @Override // java.lang.Runnable
        public void run() {
            y yVar = d0.this.d;
            String str = this.a;
            String str2 = this.b;
            yVar.a(str, str2, this.f20091c, d0.this.c(str2, this.d), this.f20092e, this.f20093f, this.f20094g, d0.this.a, this.f20095h);
        }
    }

    /* loaded from: classes11.dex */
    public class g implements Runnable {
        public final /* synthetic */ String a;
        public final /* synthetic */ Throwable b;

        /* renamed from: c */
        public final /* synthetic */ String f20097c;
        public final /* synthetic */ long d;

        /* renamed from: e */
        public final /* synthetic */ String f20098e;

        /* renamed from: f */
        public final /* synthetic */ boolean f20099f;

        public g(String str, Throwable th, String str2, long j2, String str3, boolean z) {
            d0.this = r1;
            this.a = str;
            this.b = th;
            this.f20097c = str2;
            this.d = j2;
            this.f20098e = str3;
            this.f20099f = z;
        }

        @Override // java.lang.Runnable
        public void run() {
            y yVar = d0.this.d;
            String str = this.a;
            d0 d0Var = d0.this;
            yVar.a(t.f20145j, str, null, d0Var.c(str, d0Var.a(this.b, this.f20097c)), this.d, this.f20098e, 3, d0.this.a, this.f20099f);
        }
    }

    /* loaded from: classes11.dex */
    public class h implements Runnable {
        public final /* synthetic */ String a;
        public final /* synthetic */ String b;

        /* renamed from: c */
        public final /* synthetic */ String f20101c;
        public final /* synthetic */ String d;

        /* renamed from: e */
        public final /* synthetic */ String f20102e;

        /* renamed from: f */
        public final /* synthetic */ long f20103f;

        /* renamed from: g */
        public final /* synthetic */ String f20104g;

        /* renamed from: h */
        public final /* synthetic */ int f20105h;

        /* renamed from: i */
        public final /* synthetic */ boolean f20106i;

        public h(String str, String str2, String str3, String str4, String str5, long j2, String str6, int i2, boolean z) {
            d0.this = r1;
            this.a = str;
            this.b = str2;
            this.f20101c = str3;
            this.d = str4;
            this.f20102e = str5;
            this.f20103f = j2;
            this.f20104g = str6;
            this.f20105h = i2;
            this.f20106i = z;
        }

        @Override // java.lang.Runnable
        public void run() {
            y yVar = d0.this.d;
            String str = this.a;
            yVar.a(str, d0.this.c(str, this.b), this.f20101c, this.d, this.f20102e, this.f20103f, this.f20104g, this.f20105h, d0.this.a, this.f20106i);
        }
    }

    private d0(k kVar, @NonNull y yVar) {
        a(new b(kVar));
        this.d = yVar;
    }

    public String a(Throwable th, String str) {
        String stackTraceString = Log.getStackTraceString(th);
        if (TextUtils.isEmpty(str)) {
            return stackTraceString;
        }
        return (stackTraceString + " ### ") + str;
    }

    public static d0 a(k kVar) {
        return new d0(kVar, y.c());
    }

    private void a(Runnable runnable) {
        if (e().a()) {
            runnable.run();
        } else {
            e().c(runnable);
        }
    }

    private String c() {
        d();
        String f2 = this.a.f();
        TextUtils.isEmpty(f2);
        return f2;
    }

    public String c(String str, String str2) {
        StringBuilder sb = new StringBuilder();
        if (this.a.b() == null) {
            j d2 = this.a.d();
            if (d2 != null) {
                String a2 = d2.a();
                if (!TextUtils.isEmpty(a2)) {
                    sb.append(a2.replace(OrderISVUtil.MONEY_DECIMAL_CHAR, '_'));
                    sb.append(LangUtils.SINGLE_SPACE);
                }
                String b2 = d2.b();
                if (!TextUtils.isEmpty(b2)) {
                    sb.append(b2.replace(OrderISVUtil.MONEY_DECIMAL_CHAR, '_'));
                    sb.append(LangUtils.SINGLE_SPACE);
                }
            }
            String c2 = this.a.c();
            if (!TextUtils.isEmpty(c2)) {
                sb.append(c2);
                sb.append(LangUtils.SINGLE_SPACE);
            }
            this.a.b(sb.toString());
        }
        sb.append(this.a.b());
        sb.append(str);
        if (!TextUtils.isEmpty(str2)) {
            sb.append(LangUtils.SINGLE_SPACE);
            sb.append(str2);
        }
        return sb.toString();
    }

    private void d() {
        e().a();
    }

    private j0 e() {
        synchronized (this.f20082c) {
            if (this.b == null) {
                Looper d2 = a0.d(this.f20083e);
                if (d2 == null) {
                    return i0.d();
                }
                this.b = new h0(d2);
            }
            return this.b;
        }
    }

    public boolean f() {
        return "-1".equals(c());
    }

    @Override // jpbury.c0
    public SessionPack a() {
        return new SessionPack();
    }

    @Override // jpbury.c0
    public SessionPack a(String str) {
        return new SessionPack(str);
    }

    @Override // jpbury.c0
    public SessionPack a(String str, String str2, String str3) {
        return new SessionPack(str, str2, str3);
    }

    @Override // jpbury.c0
    public void a(String str, SessionPack sessionPack) {
        a(new c(sessionPack, str));
    }

    @Override // jpbury.c0
    public void a(String str, String str2) {
        a(new d(str, str2));
    }

    @Override // jpbury.c0
    public void a(String str, String str2, IdExtension idExtension, String str3, String str4, int i2, boolean z) {
        a(new f(str, str2, idExtension, str3, System.currentTimeMillis(), str4, i2, z));
    }

    @Override // jpbury.c0
    public void a(String str, String str2, IdExtension idExtension, String str3, String str4, boolean z) {
        a(new e(str, str2, idExtension, str3, System.currentTimeMillis(), str4, z));
    }

    @Override // jpbury.c0
    public void a(String str, String str2, String str3, String str4, String str5, String str6, int i2, boolean z) {
        a(new h(str, str2, str3, str4, str5, System.currentTimeMillis(), str6, i2, z));
    }

    @Override // jpbury.c0
    public void a(String str, String str2, Throwable th, String str3, boolean z) {
        a(new g(str, th, str2, System.currentTimeMillis(), str3, z));
    }

    @Override // jpbury.c0
    public SessionPack b(String str, String str2) {
        return new SessionPack(str, str2);
    }

    @Override // jpbury.c0
    public String b() {
        return this.a.f();
    }
}

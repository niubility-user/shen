package com.jingdong.jdma.e;

import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.os.Process;
import android.text.TextUtils;
import com.jingdong.jdma.common.utils.LogUtil;
import com.jingdong.jdma.common.utils.e;
import com.jingdong.jdma.common.utils.i;
import com.jingdong.jdma.common.utils.m;
import com.jingdong.jdma.common.utils.n;
import com.jingdong.jdma.common.utils.o;
import com.jingdong.jdma.f.d;
import com.jingdong.jdma.f.f;
import com.jingdong.jdma.f.g;
import com.jingdong.jdma.h.d;
import com.jingdong.jdma.minterface.MaInitCommonInfo;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes12.dex */
public class b implements com.jingdong.jdma.e.a {

    /* renamed from: c  reason: collision with root package name */
    private HandlerThread f12711c;
    private Handler d;

    /* renamed from: e  reason: collision with root package name */
    private MaInitCommonInfo f12712e;

    /* renamed from: f  reason: collision with root package name */
    private Context f12713f;

    /* renamed from: g  reason: collision with root package name */
    private f f12714g;

    /* renamed from: h  reason: collision with root package name */
    private com.jingdong.jdma.f.a f12715h;

    /* renamed from: i  reason: collision with root package name */
    private volatile long f12716i;
    private Object a = new Object();

    /* renamed from: j  reason: collision with root package name */
    private ConcurrentHashMap<Integer, Integer> f12717j = new ConcurrentHashMap<>(4);

    /* renamed from: k  reason: collision with root package name */
    private ConcurrentHashMap<Integer, Long> f12718k = new ConcurrentHashMap<>(4);

    /* renamed from: l  reason: collision with root package name */
    private ConcurrentHashMap<Integer, Long> f12719l = new ConcurrentHashMap<>(4);

    /* renamed from: m  reason: collision with root package name */
    private ConcurrentHashMap<Integer, Integer> f12720m = new ConcurrentHashMap<>(4);

    /* renamed from: n  reason: collision with root package name */
    private volatile int f12721n = 10;
    private volatile boolean b = false;

    /* loaded from: classes12.dex */
    class a extends Handler {
        a(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            int i2 = message.what;
            if (i2 == 8) {
                LogUtil.w("JDMA_EngineImpl", "handle startReport");
                b.this.g();
            } else if (i2 == 9) {
                try {
                    LogUtil.w("JDMA_EngineImpl", "handle database close");
                    com.jingdong.jdma.b.a.a(b.this.f12713f).close();
                    b.this.f12711c.quit();
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            } else if (i2 == 11) {
                LogUtil.w("JDMA_EngineImpl", "handle updateReportStrategy");
                if (b.this.b) {
                    return;
                }
                b.this.j();
            } else {
                if (i2 == 12) {
                    LogUtil.w("JDMA_EngineImpl", "handle reportFailureData");
                    if (b.this.f12716i != 0 && System.currentTimeMillis() - b.this.f12716i <= 120000) {
                        return;
                    }
                    b.this.f12716i = 0L;
                    b.this.d();
                } else if (i2 != 13) {
                    boolean z = b.this.f12716i != 0 && System.currentTimeMillis() - b.this.f12716i <= 120000;
                    LogUtil.w("JDMA_EngineImpl", "handle invokeReport isStopReportMessage: " + z);
                    if (z) {
                        return;
                    }
                    b.this.f12716i = 0L;
                    b bVar = b.this;
                    bVar.g(bVar.b(message.what));
                } else {
                    LogUtil.w("JDMA_EngineImpl", "handle pauseReport");
                    b.this.f12716i = System.currentTimeMillis();
                }
            }
        }
    }

    /* renamed from: com.jingdong.jdma.e.b$b  reason: collision with other inner class name */
    /* loaded from: classes12.dex */
    class C0490b implements d.c {
        C0490b() {
        }

        @Override // com.jingdong.jdma.h.d.c
        public void a() {
            com.jingdong.jdma.common.utils.c.o = d.e().i().x() * 1000;
            b.this.f();
        }

        @Override // com.jingdong.jdma.h.d.c
        public void b() {
            long a = d.e().i().a();
            if (a > 0) {
                b.this.b(a * 60 * 1000);
            }
        }
    }

    public b(Context context, MaInitCommonInfo maInitCommonInfo) {
        Context applicationContext = context.getApplicationContext();
        this.f12713f = applicationContext;
        if (applicationContext == null) {
            this.f12713f = context;
        }
        this.f12712e = maInitCommonInfo;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int f(int i2) {
        return i2 * 10;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void i(int i2) {
        synchronized (this.a) {
            d(i2, com.jingdong.jdma.b.a.a(this.f12713f).a(com.jingdong.jdma.f.d.a(i2).c()));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void j() {
        d.e().b(this.f12713f);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void g() {
        for (int i2 : com.jingdong.jdma.f.d.a()) {
            i(i2);
        }
        f();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void h(int i2) {
        LogUtil.d("JDMA_EngineImpl", "judgeLimitAndSendMessage()");
        if (this.b) {
            LogUtil.d("JDMA_EngineImpl", "judgeLimitAndSendMessage() return0");
        } else if (!i.a(this.f12713f)) {
            LogUtil.d("JDMA_EngineImpl", "judgeLimitAndSendMessage() return1");
        } else if (n.a().d()) {
            LogUtil.d("JDMA_EngineImpl", "judgeLimitAndSendMessage() return2");
        } else if (d.e().i().v() != 1) {
            LogUtil.d("JDMA_EngineImpl", "judgeLimitAndSendMessage() return3");
        } else {
            boolean c2 = n.a().c();
            int e2 = e(i2);
            if (e2 > 0) {
                i();
            }
            if (e2 == 0) {
                LogUtil.d("JDMA_EngineImpl", "judgeLimitAndSendMessage() return4");
            } else if (this.f12714g.e(i2)) {
                LogUtil.d("JDMA_EngineImpl", "judgeLimitAndSendMessage() return5");
            } else {
                if (c2 && !d.e().i().r().equals("1")) {
                    d.e().j();
                    d.e().i().c("1");
                }
                int a2 = com.jingdong.jdma.f.d.a(i2, com.jingdong.jdma.common.utils.c.f12682l) + e.a(i2);
                LogUtil.d("JDMA_EngineImpl", "judgeLimitAndSendMessage() eachSize: " + a2);
                int b = com.jingdong.jdma.f.d.b(i2, com.jingdong.jdma.common.utils.c.f12682l) + e.b(i2);
                LogUtil.d("JDMA_EngineImpl", "judgeLimitAndSendMessage() eachInt: " + b);
                long j2 = (long) (b * 1000);
                long c3 = c(i2);
                long currentTimeMillis = System.currentTimeMillis();
                int f2 = f(i2);
                LogUtil.d("JDMA_EngineImpl", "judgeLimitAndSendMessage() what: " + f2);
                if (c2 && ((e2 >= a2 || currentTimeMillis - c3 >= j2) && d(i2) <= 0)) {
                    c(i2, e2);
                    int b2 = e.b();
                    this.d.removeMessages(f2);
                    this.d.sendEmptyMessageDelayed(f2, b2);
                }
                if (e2 < a2 && d(i2) <= 0) {
                    long j3 = 0;
                    if (c3 != 0) {
                        long j4 = currentTimeMillis - c3;
                        j2 = j4 > j2 ? 0L : j2 - j4;
                    }
                    if (j2 >= 0) {
                        j3 = j2;
                    }
                    this.d.removeMessages(f2);
                    this.d.sendEmptyMessageDelayed(f2, j3);
                }
                this.d.removeMessages(f2);
                this.d.sendEmptyMessage(f2);
            }
        }
    }

    private void d(int i2, int i3) {
        this.f12717j.put(Integer.valueOf(i2), Integer.valueOf(i3));
        com.jingdong.jdma.f.d.a(i2, i3);
    }

    private void e() {
        synchronized (this.a) {
            for (int i2 : com.jingdong.jdma.f.d.a()) {
                d(i2, 0);
            }
        }
    }

    public void b(String str, String str2) {
        if (!this.b && d.e().i().v() == 1) {
            a(str, str2);
        }
    }

    public void c() {
        if (LogUtil.isDebug()) {
            LogUtil.d("JDMA_EngineImpl", "init,pid=" + Process.myPid());
        }
        HandlerThread handlerThread = new HandlerThread("JDMA");
        this.f12711c = handlerThread;
        handlerThread.start();
        this.d = new a(this.f12711c.getLooper());
        com.jingdong.jdma.common.utils.d.c().b(this.f12712e.site_id);
        com.jingdong.jdma.bean.a.b().a(this.f12713f, this.f12712e);
        d.e().f(this.f12712e.build);
        d.e().e(this.f12712e.appv);
        com.jingdong.jdma.f.c.a().b(this.f12712e.appv);
        com.jingdong.jdma.f.c.a().a(this.f12712e.build);
        MaInitCommonInfo maInitCommonInfo = this.f12712e;
        com.jingdong.jdma.common.utils.c.f12680j = maInitCommonInfo.zipFlag == 1;
        if (maInitCommonInfo.domainInterface != null) {
            d.e().j(this.f12712e.domainInterface.getStrategyDomain());
            d.e().i(this.f12712e.domainInterface.getReportDomain());
            d.e().g(this.f12712e.domainInterface.getH5Url());
        }
        f fVar = new f(this.f12713f);
        this.f12714g = fVar;
        fVar.a(new c());
        this.f12715h = new com.jingdong.jdma.f.a(this.f12713f, this);
        d.e().a(this.f12713f, this.f12712e.site_id, new C0490b());
        this.d.sendEmptyMessage(8);
    }

    public void f() {
        for (int i2 : com.jingdong.jdma.f.d.a()) {
            if (e(i2) > 0) {
                h(i2);
            }
        }
    }

    /* loaded from: classes12.dex */
    public class c implements com.jingdong.jdma.f.e {
        public c() {
        }

        @Override // com.jingdong.jdma.f.e
        public void a(int i2, int i3) {
            b.this.f12714g.a(i2);
            b.this.a(i2, i3);
            b.this.h(i2);
            b.this.h();
        }

        @Override // com.jingdong.jdma.f.e
        public void a(com.jingdong.jdma.bean.d.a aVar, int i2) {
            int a = aVar != null ? aVar.a() : -1;
            int f2 = b.this.f(a);
            if (i2 == -3) {
                b.this.f12714g.a(a);
            } else if (n.a().c()) {
                b.this.a(aVar);
                b.this.f12714g.a(a);
                b.this.d.sendEmptyMessage(f2);
            } else if (i2 != -2 || !b.this.a()) {
                b.this.f12714g.g(a);
                if (!b.this.f12714g.d(a)) {
                    b.this.d.sendEmptyMessage(13);
                    return;
                }
                int c2 = e.c(b.this.f12714g.b(a));
                b.this.d.removeMessages(f2);
                b.this.d.sendEmptyMessageDelayed(f2, c2);
            } else {
                b.this.a(aVar);
                b.this.f12714g.a(a);
                b.this.d.sendEmptyMessage(f2);
            }
        }

        @Override // com.jingdong.jdma.f.e
        public void a(int i2) {
            long currentTimeMillis = System.currentTimeMillis();
            if (currentTimeMillis - b.this.a(i2) > d.e().g() * 1000) {
                b.this.i(i2);
                b.this.a(i2, currentTimeMillis);
            }
        }
    }

    private int d(int i2) {
        if (this.f12720m.containsKey(Integer.valueOf(i2))) {
            return this.f12720m.get(Integer.valueOf(i2)).intValue();
        }
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void g(int i2) {
        LogUtil.d("JDMA_EngineImpl", "invokeReport()");
        d.a a2 = com.jingdong.jdma.f.d.a(i2);
        this.f12721n = com.jingdong.jdma.f.d.c(i2, com.jingdong.jdma.common.utils.c.f12682l);
        this.f12714g.a(this.f12713f, a2, this.f12721n);
        b(i2, System.currentTimeMillis());
    }

    public void a(HashMap<String, String> hashMap, String str) {
        if (!this.b && com.jingdong.jdma.h.d.e().i().v() == 1) {
            a(m.a(hashMap), str);
        }
    }

    public void i() {
        if (a()) {
            this.f12715h.d();
        }
    }

    private void b(int i2, int i3) {
        synchronized (this.a) {
            d(i2, e(i2) + i3);
        }
    }

    public void d() {
        if (a()) {
            try {
                if (n.a().d()) {
                    return;
                }
                this.f12715h.b();
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }

    private int e(int i2) {
        if (this.f12717j.containsKey(Integer.valueOf(i2))) {
            return this.f12717j.get(Integer.valueOf(i2)).intValue();
        }
        return 0;
    }

    private void a(String str, String str2) {
        if (LogUtil.isDebug() && !TextUtils.isEmpty(str)) {
            LogUtil.d("record data:" + str);
        }
        int a2 = com.jingdong.jdma.f.d.a(str2);
        int e2 = e(a2);
        String c2 = com.jingdong.jdma.f.d.a(a2).c();
        int i2 = n.a().c() ? 20000 : 10000;
        if (e2 > i2) {
            a(a2, str);
            if (com.jingdong.jdma.f.c.d) {
                com.jingdong.jdma.f.c.a().a("JDMA_EngineImpl", "count > maxLimit");
            }
            if (LogUtil.isDebug()) {
                LogUtil.e("JDMASDK", String.format("'%s' table's data total count = %s and reach %s max limit,then lost data = %s", c2, Integer.valueOf(e2), Integer.valueOf(i2), m.a(str)));
            }
        } else if (a(c2, str, str2)) {
            b(a2, 1);
        } else {
            if (!n.a().c()) {
                a(a2, str);
                if (LogUtil.isDebug()) {
                    LogUtil.e("JDMASDK", String.format("'%s' table's store fail and then send data immediately.", c2, Integer.valueOf(e2), Integer.valueOf(i2), m.a(str)));
                }
            } else if (LogUtil.isDebug()) {
                LogUtil.e("JDMASDK", String.format("'%s' table's store failed and then lost data.", c2, Integer.valueOf(e2), Integer.valueOf(i2), m.a(str)));
            }
            if (com.jingdong.jdma.f.c.d) {
                com.jingdong.jdma.f.c.a().a("JDMA_EngineImpl", "recordLog failed");
            }
        }
        if (com.jingdong.jdma.common.utils.c.f12681k) {
            e();
        } else {
            h(a2);
        }
    }

    public void b(long j2) {
        Handler handler = this.d;
        if (handler == null || handler.hasMessages(11)) {
            return;
        }
        this.d.sendEmptyMessageDelayed(11, j2 + (e.a() * 1000));
    }

    public void b() {
        LogUtil.d("JDMA_EngineImpl", "destroy");
        this.b = true;
        this.d.removeCallbacksAndMessages(null);
        com.jingdong.jdma.c.d.a().b();
        this.f12714g.a();
        o.a().b();
        this.d.sendEmptyMessage(9);
    }

    private void b(int i2, long j2) {
        this.f12718k.put(Integer.valueOf(i2), Long.valueOf(j2));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int b(int i2) {
        return i2 / 10;
    }

    private long c(int i2) {
        if (this.f12718k.containsKey(Integer.valueOf(i2))) {
            return this.f12718k.get(Integer.valueOf(i2)).longValue();
        }
        return 0L;
    }

    private void c(int i2, int i3) {
        this.f12720m.put(Integer.valueOf(i2), Integer.valueOf(i3));
    }

    private void a(int i2, String str) {
        com.jingdong.jdma.bean.c.a aVar = new com.jingdong.jdma.bean.c.a();
        aVar.b(str);
        a(i2, aVar);
    }

    private boolean a(String str, String str2, String str3) {
        return com.jingdong.jdma.b.a.a(this.f12713f).a(str, str2, str3);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(int i2, int i3) {
        synchronized (this.a) {
            int e2 = e(i2) - i3;
            int i4 = 0;
            if (e2 < 0) {
                e2 = 0;
            }
            d(i2, e2);
            int d = d(i2) - i3;
            if (d >= 0) {
                i4 = d;
            }
            c(i2, i4);
        }
    }

    public void h() {
        if (a() && (com.jingdong.jdma.common.utils.c.f12674c | com.jingdong.jdma.common.utils.c.f12675e | com.jingdong.jdma.common.utils.c.d | com.jingdong.jdma.common.utils.c.f12676f) == 0) {
            this.f12715h.c();
        }
    }

    public int a(String str) {
        return com.jingdong.jdma.b.a.a(this.f12713f).a(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public long a(int i2) {
        if (this.f12719l.containsKey(Integer.valueOf(i2))) {
            return this.f12719l.get(Integer.valueOf(i2)).longValue();
        }
        return 0L;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(int i2, long j2) {
        this.f12719l.put(Integer.valueOf(i2), Long.valueOf(j2));
    }

    @Override // com.jingdong.jdma.e.a
    public void a(long j2) {
        if (a()) {
            this.d.sendEmptyMessageDelayed(12, j2);
        }
    }

    public void a(com.jingdong.jdma.bean.d.a aVar) {
        if (a()) {
            int b = this.f12715h.b(aVar);
            if (b < 0) {
                b = 0;
            }
            a(aVar != null ? aVar.a() : -1, b);
        }
    }

    public boolean a() {
        return this.f12715h.a();
    }

    private void a(int i2, com.jingdong.jdma.bean.c.a aVar) {
        try {
            if (i2 == d.a.f12731g.a()) {
                g.a(com.jingdong.jdma.f.d.b(i2), aVar, com.jingdong.jdma.f.d.a(i2).b(), (com.jingdong.jdma.bean.d.a) null, (com.jingdong.jdma.f.b) null, true);
            } else {
                g.a(com.jingdong.jdma.f.d.b(i2), aVar, com.jingdong.jdma.f.d.a(i2).b(), (com.jingdong.jdma.f.b) null, (com.jingdong.jdma.bean.d.a) null, true);
            }
        } catch (Throwable unused) {
        }
    }
}

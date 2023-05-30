package performance.jd.jdreportperformance.d;

import android.app.Application;
import android.content.Context;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import com.facebook.react.modules.appstate.AppStateModule;
import com.jingdong.common.jdreactFramework.JDReactConstant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicBoolean;
import performance.jd.jdreportperformance.entity.NetworkChangedReceiver;
import performance.jd.jdreportperformance.entity.StategyEntity;
import performance.jd.jdreportperformance.f.d;
import performance.jd.jdreportperformance.f.e;
import performance.jd.jdreportperformance.minterface.InitInformation;

/* loaded from: classes.dex */
public class b {

    /* renamed from: k */
    private static volatile b f20441k;
    private final Queue<HashMap<String, String>> a = new LinkedList();
    private final Queue<HashMap<String, String>> b = new LinkedList();

    /* renamed from: c */
    private final Object f20442c = new Object();
    private volatile long d = 0;

    /* renamed from: e */
    private final Object f20443e = new Object();

    /* renamed from: f */
    private long f20444f = 0;

    /* renamed from: g */
    private volatile AtomicBoolean f20445g = new AtomicBoolean(true);

    /* renamed from: h */
    private HandlerThread f20446h;

    /* renamed from: i */
    private Handler f20447i;

    /* renamed from: j */
    private volatile InitInformation.IPerformanceController f20448j;

    /* loaded from: classes.dex */
    public class a extends Handler {
        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        a(Looper looper) {
            super(looper);
            b.this = r1;
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            switch (message.what) {
                case 1001:
                    b.this.h();
                    return;
                case 1002:
                    b.this.j();
                    return;
                case 1003:
                    b.this.g();
                    return;
                default:
                    return;
            }
        }
    }

    /* renamed from: performance.jd.jdreportperformance.d.b$b */
    /* loaded from: classes.dex */
    public class RunnableC0857b implements Runnable {
        RunnableC0857b() {
            b.this = r1;
        }

        @Override // java.lang.Runnable
        public void run() {
            b.this.o();
        }
    }

    private b() {
        this.f20446h = null;
        this.f20447i = null;
        performance.jd.jdreportperformance.b.b.b.b("ReportManager", " construct ReportManager");
        i();
        k();
        HandlerThread handlerThread = new HandlerThread("JDPerformanceReporter");
        this.f20446h = handlerThread;
        handlerThread.start();
        this.f20447i = new a(this.f20446h.getLooper());
        c.b().a(new e());
        c.b().a(new d());
        c.b().a(new performance.jd.jdreportperformance.f.b());
        c.b().a(new performance.jd.jdreportperformance.f.a());
    }

    public static b d() {
        if (f20441k == null) {
            synchronized (b.class) {
                if (f20441k == null) {
                    f20441k = new b();
                }
            }
        }
        return f20441k;
    }

    public static boolean g(HashMap<String, String> hashMap) {
        if (a(hashMap) && b(hashMap)) {
            d(hashMap);
            boolean a2 = c.b().a(new performance.jd.jdreportperformance.f.c(hashMap));
            StringBuilder sb = new StringBuilder();
            sb.append("execute result ");
            sb.append(a2 ? "success " : JDReactConstant.FAILED);
            performance.jd.jdreportperformance.b.b.b.b("ReportManager", sb.toString());
            return true;
        }
        return false;
    }

    private void i() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        Context a2 = performance.jd.jdreportperformance.a.b().a();
        if (a2 != null) {
            a2.registerReceiver(NetworkChangedReceiver.a(), intentFilter);
            try {
                ((Application) a2).registerActivityLifecycleCallbacks(performance.jd.jdreportperformance.entity.a.a());
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }

    public void j() {
        this.f20445g.set(true);
        h();
    }

    private void k() {
        c.b().a(new RunnableC0857b());
    }

    public void o() {
        long b = performance.jd.jdreportperformance.d.a.a().b();
        performance.jd.jdreportperformance.b.b.b.b("ReportManager", "update db data count, count: " + b);
        if (b >= 0) {
            synchronized (this.f20442c) {
                this.d = b;
            }
        }
    }

    public InitInformation.IPerformanceController e() {
        return this.f20448j;
    }

    public void f(HashMap<String, String> hashMap) {
        synchronized (this.b) {
            this.b.offer(hashMap);
            try {
                this.b.notifyAll();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    public void h() {
        performance.jd.jdreportperformance.b.b.b.b("ReportManager", "notify commonReportLock");
        c.b().c();
        synchronized (this.f20443e) {
            try {
                this.f20443e.notifyAll();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    public List<performance.jd.jdreportperformance.e.b> l() {
        List<performance.jd.jdreportperformance.e.b> b;
        synchronized (this.f20443e) {
            while (true) {
                long b2 = performance.jd.jdreportperformance.e.c.a().b();
                if (!a(b2) || (b = performance.jd.jdreportperformance.d.a.a().b(b2)) == null || b.isEmpty()) {
                    try {
                        performance.jd.jdreportperformance.b.b.b.b("ReportManager", "takeCommonReportDataFromDB is blocked");
                        this.f20443e.wait();
                    } catch (InterruptedException e2) {
                        e2.printStackTrace();
                    }
                }
            }
        }
        return b;
    }

    public List<HashMap<String, String>> m() {
        LinkedList linkedList;
        synchronized (this.a) {
            while (this.a.isEmpty()) {
                try {
                    performance.jd.jdreportperformance.b.b.b.b("ReportManager", "takeRealTimeReportData is blocked");
                    this.a.wait();
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
            linkedList = new LinkedList();
            while (!this.a.isEmpty()) {
                linkedList.add(this.a.poll());
            }
        }
        return linkedList;
    }

    public List<HashMap<String, String>> n() {
        LinkedList linkedList;
        synchronized (this.b) {
            while (this.b.isEmpty()) {
                try {
                    performance.jd.jdreportperformance.b.b.b.b("ReportManager", "takeRecordData to write db is blocked");
                    this.b.wait();
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
            linkedList = new LinkedList();
            while (!this.b.isEmpty()) {
                linkedList.add(this.b.poll());
            }
        }
        return linkedList;
    }

    private static boolean b(HashMap<String, String> hashMap) {
        String str = hashMap.get("typeId");
        String str2 = hashMap.get("chId");
        StategyEntity a2 = performance.jd.jdreportperformance.e.c.a().a(str, str2);
        boolean equals = a2 != null ? "1".equals(a2.ret) : false;
        if (!equals) {
            performance.jd.jdreportperformance.b.b.b.a("ReportManager", "this data don't need report, typeId: " + str + ", chId: " + str2);
        }
        return equals;
    }

    public static boolean c(HashMap<String, String> hashMap) {
        String str;
        String str2;
        StategyEntity a2;
        if (hashMap == null || (a2 = performance.jd.jdreportperformance.e.c.a().a((str = hashMap.get("typeId")), (str2 = hashMap.get("chId")))) == null) {
            return false;
        }
        boolean equals = "1".equals(a2.rt);
        StringBuilder sb = new StringBuilder();
        sb.append("typeid=");
        sb.append(str);
        sb.append(", chid=");
        sb.append(str2);
        sb.append(equals ? ", real time report" : ", common report");
        performance.jd.jdreportperformance.b.b.b.b("ReportManager", sb.toString());
        return equals;
    }

    public void a(InitInformation initInformation) {
        if (this.f20448j != null || initInformation == null) {
            return;
        }
        this.f20448j = initInformation.controller;
    }

    public void e(HashMap<String, String> hashMap) {
        synchronized (this.a) {
            this.a.offer(hashMap);
            try {
                this.a.notifyAll();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    public static boolean a(ArrayList<HashMap<String, String>> arrayList) {
        Iterator<HashMap<String, String>> it = arrayList.iterator();
        boolean z = true;
        while (it.hasNext()) {
            HashMap<String, String> next = it.next();
            if (next != null && !g(next)) {
                z = false;
            }
        }
        return z;
    }

    private static boolean a(HashMap<String, String> hashMap) {
        if (TextUtils.isEmpty(hashMap.get("typeId")) || TextUtils.isEmpty(hashMap.get("chId"))) {
            performance.jd.jdreportperformance.b.b.b.a("ReportManager", "typeId or chId is empty,ignore this data");
            return false;
        }
        return true;
    }

    public void f() {
        synchronized (this.f20442c) {
            this.d++;
        }
    }

    private static void d(HashMap<String, String> hashMap) {
        hashMap.put("net", performance.jd.jdreportperformance.b.b.d.a);
        hashMap.put(AppStateModule.APP_STATE_BACKGROUND, performance.jd.jdreportperformance.entity.a.a().b());
        if (TextUtils.isEmpty(hashMap.get("occurTime"))) {
            hashMap.put("occurTime", performance.jd.jdreportperformance.b.b.a.b());
        }
    }

    public void g() {
        c.b().a(new e());
    }

    public void b() {
        Handler handler = this.f20447i;
        if (handler != null) {
            handler.sendEmptyMessageDelayed(1003, 120000L);
        }
    }

    public long c() {
        return this.d;
    }

    private boolean a(long j2) {
        long c2 = performance.jd.jdreportperformance.e.c.a().c();
        performance.jd.jdreportperformance.b.b.b.b("ReportManager", " current report interval is " + c2 + "s, current unit report count is " + j2 + ", current db data count is " + this.d);
        this.f20447i.removeMessages(1001);
        if (this.f20445g.get() && this.d > 0) {
            if (performance.jd.jdreportperformance.e.c.a().d() && (this.d >= j2 || System.currentTimeMillis() - this.f20444f >= c2 * 1000)) {
                this.f20444f = System.currentTimeMillis();
                this.f20447i.sendEmptyMessageDelayed(1001, (c2 * 1000) + 50);
                return true;
            }
            this.f20447i.sendEmptyMessageDelayed(1001, (c2 * 1000) + 50);
            return false;
        }
        performance.jd.jdreportperformance.b.b.b.b("ReportManager", "not need send loop msg");
        return false;
    }

    public void a(int i2) {
        synchronized (this.f20442c) {
            this.d -= i2;
            if (this.d < 0) {
                o();
            }
        }
    }

    public void a() {
        this.f20445g.set(false);
        Handler handler = this.f20447i;
        if (handler != null) {
            handler.sendEmptyMessageDelayed(1002, 120000L);
        }
    }
}

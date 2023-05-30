package com.jingdong.jdma.f;

import android.content.Context;
import com.jingdong.jdma.common.utils.LogUtil;
import com.jingdong.jdma.f.d;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* loaded from: classes12.dex */
public class f {
    private com.jingdong.jdma.f.e b;

    /* renamed from: c  reason: collision with root package name */
    private Context f12733c;
    private ConcurrentHashMap d = new ConcurrentHashMap(4);

    /* renamed from: e  reason: collision with root package name */
    private ConcurrentHashMap f12734e = new ConcurrentHashMap(4);
    private ThreadPoolExecutor a = new ThreadPoolExecutor(0, 1, 60, TimeUnit.SECONDS, new LinkedBlockingQueue(8), new a(this));

    /* loaded from: classes12.dex */
    class a extends ThreadPoolExecutor.DiscardPolicy {
        a(f fVar) {
        }

        @Override // java.util.concurrent.ThreadPoolExecutor.DiscardPolicy, java.util.concurrent.RejectedExecutionHandler
        public void rejectedExecution(Runnable runnable, ThreadPoolExecutor threadPoolExecutor) {
            if (com.jingdong.jdma.f.c.d) {
                com.jingdong.jdma.f.c.a().a("DBThreadPoolExecutors", "rejectedExecution");
            }
        }
    }

    /* loaded from: classes12.dex */
    class b implements Runnable {
        final /* synthetic */ d.a a;
        final /* synthetic */ Context b;

        /* renamed from: c  reason: collision with root package name */
        final /* synthetic */ int f12735c;

        b(d.a aVar, Context context, int i2) {
            this.a = aVar;
            this.b = context;
            this.f12735c = i2;
        }

        @Override // java.lang.Runnable
        public void run() {
            com.jingdong.jdma.bean.d.a aVar = new com.jingdong.jdma.bean.d.a(this.a.c(), this.a.a());
            List<com.jingdong.jdma.bean.c.a> a = com.jingdong.jdma.b.a.a(this.b).a(this.a.c(), (String) null, this.f12735c);
            if (LogUtil.isDebug()) {
                LogUtil.d("JDMA_ReportManager", "readDataFromDB, list'size=" + a.size());
            }
            if (a.isEmpty()) {
                f.this.a(aVar, -3);
                return;
            }
            int size = a.size();
            String[] strArr = new String[size];
            for (int i2 = 0; i2 < size; i2++) {
                strArr[i2] = a.get(i2).a() + "";
            }
            aVar.a(strArr);
            f.this.a(this.a, a, aVar);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes12.dex */
    public class c implements com.jingdong.jdma.f.b {
        c() {
        }

        @Override // com.jingdong.jdma.f.b
        public void a() {
        }

        @Override // com.jingdong.jdma.f.b
        public void a(com.jingdong.jdma.bean.d.a aVar) {
            f.this.a(aVar);
        }

        @Override // com.jingdong.jdma.f.b
        public void a(com.jingdong.jdma.bean.d.a aVar, com.jingdong.jdma.bean.b.c.a aVar2) {
            int b = aVar2 instanceof com.jingdong.jdma.bean.b.c.b ? ((com.jingdong.jdma.bean.b.c.b) aVar2).b() : 0;
            if (!com.jingdong.jdma.h.d.e().a() || !com.jingdong.jdma.h.d.e().b().a(b)) {
                f.this.a(aVar, b != 0 ? -2 : -1);
            } else {
                f.this.a(aVar);
            }
        }

        @Override // com.jingdong.jdma.f.b
        public void a(int i2) {
            f.this.c(i2);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes12.dex */
    public class d implements Runnable {
        final /* synthetic */ com.jingdong.jdma.bean.d.a a;

        d(com.jingdong.jdma.bean.d.a aVar) {
            this.a = aVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            int a;
            Context context = f.this.f12733c;
            com.jingdong.jdma.f.e eVar = f.this.b;
            int i2 = 0;
            if (this.a != null && (a = com.jingdong.jdma.b.a.a(context).a(this.a.c(), this.a.b())) >= 0) {
                i2 = a;
            }
            f fVar = f.this;
            com.jingdong.jdma.bean.d.a aVar = this.a;
            fVar.h(aVar != null ? aVar.a() : -1);
            if (eVar != null) {
                com.jingdong.jdma.bean.d.a aVar2 = this.a;
                eVar.a(aVar2 != null ? aVar2.a() : -1, i2);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes12.dex */
    public class e implements Runnable {
        final /* synthetic */ com.jingdong.jdma.bean.d.a a;
        final /* synthetic */ int b;

        e(com.jingdong.jdma.bean.d.a aVar, int i2) {
            this.a = aVar;
            this.b = i2;
        }

        @Override // java.lang.Runnable
        public void run() {
            f fVar = f.this;
            com.jingdong.jdma.bean.d.a aVar = this.a;
            fVar.h(aVar != null ? aVar.a() : -1);
            com.jingdong.jdma.f.e eVar = f.this.b;
            if (eVar != null) {
                eVar.a(this.a, this.b);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.jingdong.jdma.f.f$f  reason: collision with other inner class name */
    /* loaded from: classes12.dex */
    public class RunnableC0493f implements Runnable {
        final /* synthetic */ int a;

        RunnableC0493f(int i2) {
            this.a = i2;
        }

        @Override // java.lang.Runnable
        public void run() {
            com.jingdong.jdma.f.e eVar = f.this.b;
            if (eVar != null) {
                eVar.a(this.a);
            }
        }
    }

    public f(Context context) {
        this.f12733c = context;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void c(int i2) {
        this.a.execute(new RunnableC0493f(i2));
    }

    private void f(int i2) {
        this.d.put(Integer.valueOf(i2), Boolean.TRUE);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void h(int i2) {
        this.d.put(Integer.valueOf(i2), Boolean.FALSE);
    }

    public synchronized boolean d(int i2) {
        boolean z;
        if (this.f12734e.containsKey(Integer.valueOf(i2))) {
            z = ((Integer) this.f12734e.get(Integer.valueOf(i2))).intValue() <= 3;
        }
        return z;
    }

    public boolean e(int i2) {
        return this.d.containsKey(Integer.valueOf(i2)) && ((Boolean) this.d.get(Integer.valueOf(i2))).booleanValue();
    }

    public synchronized void g(int i2) {
        this.f12734e.put(Integer.valueOf(i2), Integer.valueOf((this.f12734e.containsKey(Integer.valueOf(i2)) ? ((Integer) this.f12734e.get(Integer.valueOf(i2))).intValue() : 0) + 1));
    }

    public synchronized int b(int i2) {
        return this.f12734e.containsKey(Integer.valueOf(i2)) ? ((Integer) this.f12734e.get(Integer.valueOf(i2))).intValue() : 0;
    }

    public synchronized void a(Context context, d.a aVar, int i2) {
        LogUtil.d("JDMA_ReportManager", "reportLog()");
        if (e(aVar.a())) {
            return;
        }
        f(aVar.a());
        try {
            this.a.execute(new b(aVar, context, i2));
        } catch (Throwable unused) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(d.a aVar, List<com.jingdong.jdma.bean.c.a> list, com.jingdong.jdma.bean.d.a aVar2) {
        LogUtil.d("JDMA_ReportManager", "sendData()");
        try {
            c cVar = new c();
            if (aVar.a() == d.a.f12731g.a()) {
                g.a(com.jingdong.jdma.f.d.b(aVar.a()), list, aVar.b(), aVar2, (com.jingdong.jdma.f.b) cVar, true);
            } else {
                g.a(com.jingdong.jdma.f.d.b(aVar.a()), list, aVar.b(), (com.jingdong.jdma.f.b) cVar, aVar2, true);
            }
        } catch (Throwable unused) {
            h(aVar.a());
        }
    }

    public void a(com.jingdong.jdma.f.e eVar) {
        this.b = eVar;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void a(com.jingdong.jdma.bean.d.a aVar) {
        this.a.execute(new d(aVar));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void a(com.jingdong.jdma.bean.d.a aVar, int i2) {
        this.a.execute(new e(aVar, i2));
    }

    public void a() {
        try {
            this.a.shutdown();
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public synchronized void a(int i2) {
        if (this.f12734e.containsKey(Integer.valueOf(i2))) {
            this.f12734e.remove(Integer.valueOf(i2));
        }
    }
}
